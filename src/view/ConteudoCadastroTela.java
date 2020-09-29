/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controle.ConteudoDAO;
import controle.MateriaDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Conteudo;
import model.Materia;

/**
 *
 * @author Dev-2810
 */
public class ConteudoCadastroTela extends javax.swing.JFrame {
    private Conteudo conteudo; 
    private ConteudoDAO conteudoDAO;
  //  private Materia materia = new Materia();
    private List<Materia> materias = new ArrayList<>();
    private MateriaDAO materiaDAO;
    
    
    public ConteudoCadastroTela() {
        initComponents();
        this.setLocationRelativeTo(null);
        preencherMateria();
    }

    public void preencherMateria(){
        
        materias = new ArrayList<>();
        materiaDAO = new MateriaDAO();
        try {
           
           materias =  materiaDAO.lista();
        } catch (Exception e) {
        }
        comboMateria.removeAllItems();
        for (Materia materia : materias) {
              //  comboMateria.addItem(materia.getId()+" - "+ materia.getNome());
              comboMateria.addItem( materia.getNome());
               // System.out.println("combo"+materia);
                
            }
    }
           
    public void limpaCampos(){
        campoConteudo.setText("");
        comboMateria.setSelectedIndex(0);
        
    }
    public void preencherConteudo(){
        conteudo = new Conteudo();
       //    String x =comboMateria.getSelectedItem().toString().substring(0,1);
          // conteudo.getMateira().setId(Integer.valueOf(x));
           conteudo.setNome(campoConteudo.getText().toUpperCase());
           Materia mat = materias.get(comboMateria.getSelectedIndex());
           int x = mat.getId();
           System.out.println("mat"+x);
           //conteudo.setMateria(x);
           conteudo.getMateria().setId(x);   
        
    }
    
    public void salvar() throws Exception{
        conteudoDAO = new ConteudoDAO();
        try {                     
           if(conteudoDAO.incluir(conteudo)){
                JOptionPane.showMessageDialog(this,"Registro salvo com sucesso!");
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Ocorreu um erro de banco de dados!");
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        campoConteudo = new javax.swing.JTextField();
        botaoSalvar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        comboMateria = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Conteudo");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de conteudo"));

        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        jButton1.setText("Cancelar");
        jButton1.setMaximumSize(new java.awt.Dimension(65, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(65, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Conteúdo da Matéria:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(botaoSalvar)
                        .addGap(70, 70, 70)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(comboMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addComponent(campoConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvar)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed

        try {
            preencherConteudo();
            salvar();
            limpaCampos();
        } catch (Exception ex) {
            Logger.getLogger(ConteudoCadastroTela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new PrincipalTela().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JTextField campoConteudo;
    private javax.swing.JComboBox<String> comboMateria;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
