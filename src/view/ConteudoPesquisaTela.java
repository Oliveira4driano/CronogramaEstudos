/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controle.ConteudoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Conteudo;
import model.ConteudoTabelaModelo1;

/**
 *
 * @author Dev-2810
 */
public class ConteudoPesquisaTela extends javax.swing.JFrame {

    Conteudo conteudo = new Conteudo();
    Conteudo conteudoSelecionado;
    List<Conteudo> conteudos = new ArrayList<>();
    
    
    private ConteudoDAO daoConteudo = new ConteudoDAO();
    
    
  
    
    
    public ConteudoPesquisaTela() {
        initComponents();
        setLocationRelativeTo(null);
        listarConteudoBD();
        preencheTabelaConteudo();
    }

   public void preencheTabelaConteudo(){
        tabelaConteudo.setModel(new ConteudoTabelaModelo1(conteudos));
        tabelaConteudo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
     // tabelaConteudo.getColumnModel().getColumn(0).setPreferredWidth(70); 
      tabelaConteudo.getColumnModel().getColumn(0).setPreferredWidth(401); 
      tabelaConteudo.getColumnModel().getColumn(1).setPreferredWidth(235); 
      //tabelaConteudo.getColumnModel().getColumn(2).setPreferredWidth(130);
     // tabelaConteudo.getColumnModel().getColumn(3).setPreferredWidth(90);
     // tabelaConteudo.getColumnModel().getColumn(4).setPreferredWidth(240);
    }
   
    public void listarConteudoBD(){
        
        try {
            conteudos = daoConteudo.listaConteudo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Ocorreu um erro de banco de dados: "+e.getMessage());
        } 
    }
    public void preencherFiltro(){
       conteudo.setNome( campoConteudo.getText());
    }
    private void pesquisarFiltro(){
       // MateriaDAO dao = new MateriaDAO();
         try{
            conteudos=daoConteudo.pesquisar(conteudo);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this,"Ocorreu um erro de banco de dados: "+e.getMessage());
        }
    }
    private void pesquisar(){
        preencherFiltro();
        pesquisarFiltro();
        preencheTabelaConteudo();
        campoConteudo.setText("");
    }
    
     public void selecionarConteudo(){
        this.conteudoSelecionado = conteudos.get(tabelaConteudo.getSelectedRow());  
    }
    public void abrirTelaConsulta(){
        new ConteudoAlteraTela(conteudoSelecionado).setVisible(true);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        campoConteudo = new javax.swing.JTextField();
        botaoPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConteudo = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pesquisar Conteudo");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisar Conteudo"));

        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        tabelaConteudo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaConteudo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaConteudoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaConteudo);

        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campoConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoPesquisar)
                        .addGap(0, 189, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new PrincipalTela().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ConteudoCadastroTela().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        pesquisar();
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void tabelaConteudoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaConteudoMouseClicked
        selecionarConteudo();
        if(evt.getClickCount()>1){
           conteudoSelecionado = conteudos.get(tabelaConteudo.getSelectedRow());
            try {
                abrirTelaConsulta();
            } catch (Exception ex) {
                Logger.getLogger(EditalPesquisaTela.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        
    }                  
    }//GEN-LAST:event_tabelaConteudoMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConteudoPesquisaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConteudoPesquisaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConteudoPesquisaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConteudoPesquisaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConteudoPesquisaTela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JTextField campoConteudo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaConteudo;
    // End of variables declaration//GEN-END:variables
}
