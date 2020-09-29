/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.toedter.calendar.JDateChooser;
import controle.ConteudoDAO;
import controle.EditalDAO;
import controle.MateriaDAO;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Conteudo;
import model.ConteudoTabelaModelo;
import model.Cronometro;

import model.Edital;
import model.Materia;
import model.MateriaTabelaModelo;



/**
 *
 * @author Dev-2810
 */
public final class PrincipalTela extends javax.swing.JFrame {
    
   private final Conteudo conteudo = new Conteudo();
   private final Cronometro cronometro = new Cronometro();
   private final Materia materiaSelecionada = new Materia();  //Criada para não mexer no filtro para não sumir o filtro que estava na tela
   
   
   private List<Materia> materias = new ArrayList<>();
   private List<Conteudo> conteudos =  new ArrayList<>();
    private List<Edital> editais = new ArrayList<>();
    
   private final MateriaDAO daoMateria = new MateriaDAO(); 
   private final EditalDAO daoE = new EditalDAO();
   private ConteudoDAO daoConteudo = new ConteudoDAO();
  
   
  
   
   protected int codigo;
    
    public PrincipalTela() {
        this.conteudos = new ArrayList<>();
        initComponents();
        this.setLocationRelativeTo(null);
         pesquisarMateriaBD();
        preencheTabelaMateria();
        //pesquisarEditalBD();
        botoes(false);  
        versao.setText("2019@ v.1.5");
            
            
    }
    private void preencherFiltro(){
     materiaSelecionada.setNome(campoPesquisa.getText());
    }
    
    private void pesquisarFiltro() throws ClassNotFoundException{
       // MateriaDAO dao = new MateriaDAO();
         try{
            materias=daoMateria.pesquisar(materiaSelecionada);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this,"Ocorreu um erro de banco de dados: "+e.getMessage());
        }
    }
    private void pesquisar() throws ClassNotFoundException{
        preencherFiltro();
        pesquisarFiltro();
        preencheTabelaMateria();
    }
    
    //preebcher dados no edital
    protected void pesquisarEditalBD(){
       
       int linha = tabelaMateria.getSelectedRow();
       String nome = String.valueOf(tabelaMateria.getValueAt(linha, 2));
        try {
            editais = daoE.buscarPornome(nome);
            
            editais.stream().map((edital) -> {
                labelEdital.setText( edital.getNome());
               return edital;
           }).map((edital) -> {
               labelCargo.setText(edital.getCargo());
               return edital;
           }).map((edital) -> {
               labelBancar.setText(edital.getBanca());   
               return edital;
           }).map((edital) -> {
               labelProva.setText(edital.getDataprova());
               return edital;
           }).forEachOrdered((Edital edital) -> {
               labelDias.setText("Faltam: "+String.valueOf(dataProva(edital.getDataprova()))+" dia(s) para a prova");
            });
            
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this,"Ocorreu um erro de banco de dados!"+e);
        }
    }
        
    private void pesquisarMateriaBD(){
        
        try {  
            materias = daoMateria.lista();        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Ocorreu um erro de banco de dados: "+e.getMessage());
        }
    }
    
    private void pesquisarConteudoBD(){
        int linha = tabelaMateria.getSelectedRow();
        //System.out.println("linha "+ linha);
        String nome = String.valueOf(tabelaMateria.getValueAt(linha, 0));
        
        //System.out.println("nome "+ nome);
        try {
            conteudos =   daoConteudo.buscarPornome(nome);
          
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this,"Ocorreu um erro de banco de dados!"+e);
        }
    }
    protected void conteudoSelecionado(){
        int linha = tabelaConteudo.getSelectedRow();
       String nome = String.valueOf(tabelaConteudo.getValueAt(linha, 0));
       campoidconteudo.setText(nome);
        
    }
    
    protected void preencheTabelaConteudo(){
      
      tabelaConteudo.setModel(new ConteudoTabelaModelo(conteudos));
      tabelaConteudo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      tabelaConteudo.getColumnModel().getColumn(0).setPreferredWidth(90); 
      tabelaConteudo.getColumnModel().getColumn(1).setPreferredWidth(501); 
      tabelaConteudo.getColumnModel().getColumn(2).setPreferredWidth(105); 
      tabelaConteudo.getColumnModel().getColumn(3).setPreferredWidth(135); 
                
    }
    
    protected void preencheTabelaMateria(){
      
        tabelaMateria.setModel(new MateriaTabelaModelo(materias));
        tabelaMateria.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabelaMateria.getColumnModel().getColumn(0).setPreferredWidth(420); 
        tabelaMateria.getColumnModel().getColumn(1).setPreferredWidth(160); 
        tabelaMateria.getColumnModel().getColumn(2).setPreferredWidth(160); 
     //   tabelaMateria.getColumnModel().getColumn(3).setPreferredWidth(105);
        
          
    }
    
    public long dataProva(String dtprova){
          
        long dtretorno = 0, fuso =  (3600000*24); // hora para compensar horário de verão  
        Date d1, d2;
      
        DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");  
	           df.setLenient(false); //não deixa inserir um valor alem de 12 meses
       try {   	        
                d1 = new Date();  //data atual 
	        d2 = df.parse (dtprova);  //dasta da prova (parse converte de string para data)
	        
                dtretorno = (d2.getTime() - d1.getTime())+ fuso;
               
       } catch (ParseException ex) {
           Logger.getLogger(PrincipalTela.class.getName()).log(Level.SEVERE, null, ex);
       }
       return (dtretorno/ 86400000L) ;
        
       
        
        
    }
    
    private void sobre(){
        JOptionPane.showMessageDialog(null, "Instituto Federal do Amazonas\nDesenvolvedor: Adriano de oliveira"
                + " \nFone:(92)99292-1431"
                + "   Email: oliveira.ifam@gmail.com"+
                "\nManaus-AM\n"+
                "Aceito Doações:\n"+
                "Banco: Bradesco"+
                " AG:482"+
                "  CC: 33902-4"+
                "\nPaypal: aoliveira2810@gmail.com");
    }
    private void botoes(boolean condicao){
        botaoIniciar.setEnabled(condicao);
        botaoPausar.setEnabled(condicao);
        botaoParar.setEnabled(condicao);
        botaoGravar.setEnabled(condicao);
        campoidconteudo.setEnabled(condicao);
    }
    private void cronometroIniciar(){   
        cronometro.iniciarContagem(lcronometro); 
        botaoIniciar.setEnabled(false);
        botaoPausar.setEnabled(true);
        
    } 
    private void cronometroPausar(){
        cronometro.pausar();
        botaoPausar.setEnabled(false);
        botaoIniciar.setEnabled(true);
    }
    private void cronometroZerar(){
        cronometro.zerarTime(lcronometro);
    }
    protected void preencherConteudo(){
       
       
        codigo = Integer.parseInt(campoidconteudo.getText());
        conteudo.setId(codigo); 
        cronometroPausar();
        String x = lcronometro.getText();          
        conteudo.setTempo(x);       
        dataRevisao();
    }
    private void dataRevisao(){
      //  codigo = Integer.parseInt(campoidconteudo.getText());
      //  conteudo.setId(codigo);    
        JDateChooser jd = new JDateChooser();
        String message ="Data de Revisao:\n";
        Object[] params = {message,jd}; 
        Object[] options = { "Salvar", "Cancelar" };
        JOptionPane.showOptionDialog(null,params,"Data de Revisao",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
        //JOptionPane.showConfirmDialog(null,params,"Data de Revisao",JOptionPane.YES_NO_OPTION);
   
        try {
            if(options[1] == "Cancelar"){
            conteudo.setRevisao("");
            cronometroZerar();
            campoidconteudo.setText("");
            botoes(false);
            }
            if(options[0] == "Salvar"){            
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String s=sdf.format(((JDateChooser)params[1]).getDate());//Casting params[1] makes me able to get its information   
                conteudo.setRevisao(s);                 
            }
            
        } catch (Exception e) {
            System.out.println("eee"+e);
        }
        
       
        
        
       
        //creditos https://stackoverflow.com/questions/35347519/display-jdatechooser-in-joptionpane adaptado para 
    }
    
    
    private void preencherData() throws ParseException{
        
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
	Date date = new Date(); 
        String x = dateFormat.format(date);
        codigo = Integer.parseInt(campoidconteudo.getText());
        conteudo.setId(codigo);    
        conteudo.setData(x);     
    }

    private void inserirData(){
         daoConteudo = new ConteudoDAO();   
        try {
            daoConteudo.alterarData(conteudo);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this,"InserirData Ocorreu um erro de banco de dados!"+e);
        }
        
    }
    

    private void cronometroGravar() throws ClassNotFoundException{
          // ConteudoDAO conteudoDAO;     
        try {      
               daoConteudo.alterar(conteudo);
               cronometroZerar();
               campoidconteudo.setText("");
               botoes(false);
               JOptionPane.showMessageDialog(this,"Gravado com sucesso!");
           
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this,"Ocorreu um erro de banco de dados!"+e);
        }    
        
    }
    
    
    private void sair(){
        System.exit(0);
    }
 
  
    
   


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelEdital = new javax.swing.JLabel();
        labelCargo = new javax.swing.JLabel();
        labelBancar = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelProva = new javax.swing.JLabel();
        labelDias = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConteudo = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaMateria = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        botaoIniciar = new javax.swing.JButton();
        botaoParar = new javax.swing.JButton();
        lcronometro = new javax.swing.JLabel();
        botaoPausar = new javax.swing.JButton();
        botaoGravar = new javax.swing.JButton();
        campoidconteudo = new javax.swing.JTextField();
        campoPesquisa = new javax.swing.JTextField();
        botaoPesquisar = new javax.swing.JButton();
        versao = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cronograma de estudos");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cronograma de Estudos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Edital:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Cargo:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Banca:");

        labelEdital.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelEdital.setText("Edital");

        labelCargo.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelCargo.setText("Cargo");

        labelBancar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelBancar.setText("Banca");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Data da prova:");

        labelProva.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelProva.setText("Prova");

        labelDias.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelDias.setText("dias");
        labelDias.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelDias)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(labelEdital))
                        .addGap(144, 144, 144)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(labelCargo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(labelBancar))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelProva)
                            .addComponent(jLabel8))
                        .addGap(89, 89, 89))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEdital, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCargo)
                    .addComponent(labelBancar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelProva))
                .addGap(18, 18, 18)
                .addComponent(labelDias)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabelaConteudo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Conteúdo", "Meta diaria", "Estudo diario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaConteudo.getTableHeader().setReorderingAllowed(false);
        tabelaConteudo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaConteudoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaConteudo);
        if (tabelaConteudo.getColumnModel().getColumnCount() > 0) {
            tabelaConteudo.getColumnModel().getColumn(0).setMaxWidth(50);
            tabelaConteudo.getColumnModel().getColumn(0).setHeaderValue("Codigo");
            tabelaConteudo.getColumnModel().getColumn(1).setResizable(false);
            tabelaConteudo.getColumnModel().getColumn(2).setResizable(false);
            tabelaConteudo.getColumnModel().getColumn(3).setResizable(false);
        }

        tabelaMateria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1", "Título 2", "Título 3"
            }
        ));
        tabelaMateria.getTableHeader().setReorderingAllowed(false);
        tabelaMateria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaMateriaMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaMateria);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tempo de Estudo"));

        botaoIniciar.setText("Iniciar");
        botaoIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIniciarActionPerformed(evt);
            }
        });

        botaoParar.setText("Zerar");
        botaoParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPararActionPerformed(evt);
            }
        });

        lcronometro.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lcronometro.setForeground(new java.awt.Color(255, 0, 0));
        lcronometro.setText("00:00:00");

        botaoPausar.setText("Pausar");
        botaoPausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPausarActionPerformed(evt);
            }
        });

        botaoGravar.setText("Gravar");
        botaoGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGravarActionPerformed(evt);
            }
        });

        campoidconteudo.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(botaoIniciar)
                        .addGap(18, 18, 18)
                        .addComponent(botaoPausar)
                        .addGap(18, 18, 18)
                        .addComponent(botaoParar)
                        .addGap(18, 18, 18)
                        .addComponent(botaoGravar))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(campoidconteudo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(lcronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lcronometro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(campoidconteudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoIniciar)
                    .addComponent(botaoParar)
                    .addComponent(botaoPausar)
                    .addComponent(botaoGravar))
                .addGap(8, 8, 8))
        );

        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        versao.setText("jLabel1");

        jMenu3.setText("Menu");

        jMenuItem1.setText("Edital");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Materia");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setText("Conteúdo");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Sair");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Cronograma");

        jMenuItem6.setText("cronograma");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuBar2.add(jMenu4);

        jMenu1.setText("Sobre");

        jMenuItem5.setText("Desenvolvedor");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar2.add(jMenu1);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botaoPesquisar))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1049, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(versao)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(498, 498, 498)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoPesquisar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(versao)
                        .addGap(10, 10, 10))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
      new EditalPesquisaTela().setVisible(true);
      dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new MateriaPesquisaTela().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new ConteudoPesquisaTela().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void tabelaMateriaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMateriaMouseReleased
        
        if(evt.getClickCount()>1){
            //selecionarMateria();
            pesquisarConteudoBD();
           preencheTabelaConteudo();
           pesquisarEditalBD();
            botoes(false);
            
        }
        
    }//GEN-LAST:event_tabelaMateriaMouseReleased

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        sair();         
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        sobre();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void botaoIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIniciarActionPerformed
        cronometroIniciar();
       try {
           preencherData();
           inserirData();
           
       } catch (ParseException ex) {
           Logger.getLogger(PrincipalTela.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    }//GEN-LAST:event_botaoIniciarActionPerformed

    private void botaoPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPararActionPerformed
        cronometroZerar();
        botoes(true);
    }//GEN-LAST:event_botaoPararActionPerformed

    private void botaoPausarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPausarActionPerformed
      cronometroPausar();
    }//GEN-LAST:event_botaoPausarActionPerformed

    private void botaoGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGravarActionPerformed
        preencherConteudo();
       try {
           cronometroGravar();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(PrincipalTela.class.getName()).log(Level.SEVERE, null, ex);
       }
        pesquisarConteudoBD();
        preencheTabelaConteudo();
        
        
      
       
    }//GEN-LAST:event_botaoGravarActionPerformed

    private void tabelaConteudoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaConteudoMouseClicked
        if(evt.getClickCount()>1){
            botoes(true);
            conteudoSelecionado();
            //cronometroZerar();
        }
    }//GEN-LAST:event_tabelaConteudoMouseClicked

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        new CronogramaTela().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
       try {
           pesquisar();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(PrincipalTela.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    public static void main(String args[]) {
 
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       //</editor-fold>
       //</editor-fold>
       
        //</editor-fold>
        //</editor-fold>
        
        
        
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PrincipalTela().setVisible(true);
        });
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoGravar;
    private javax.swing.JButton botaoIniciar;
    private javax.swing.JButton botaoParar;
    private javax.swing.JButton botaoPausar;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField campoPesquisa;
    private javax.swing.JTextField campoidconteudo;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelBancar;
    private javax.swing.JLabel labelCargo;
    private javax.swing.JLabel labelDias;
    private javax.swing.JLabel labelEdital;
    private javax.swing.JLabel labelProva;
    private javax.swing.JLabel lcronometro;
    private javax.swing.JTable tabelaConteudo;
    private javax.swing.JTable tabelaMateria;
    private javax.swing.JLabel versao;
    // End of variables declaration//GEN-END:variables
}
