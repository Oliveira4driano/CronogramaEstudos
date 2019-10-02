/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controle.ConteudoDAO;
import controle.EditalDAO;
import controle.MateriaDAO;
import java.awt.Dialog;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Conteudo;
import model.Edital;
import model.Materia;

/**
 *
 * @author Dev-2810
 */
public class ConteudoAlteraTela extends javax.swing.JFrame {
    private Conteudo conteudo = new Conteudo(); 
    private ConteudoDAO conteudoDAO;
  //  private List<Conteudo> conteudos = new ArrayList<Conteudo>();
  //  private Materia materia = new Materia();
    private List<Materia> materias = new ArrayList<Materia>();
    private MateriaDAO materiaDAO;
    
    
    public ConteudoAlteraTela(){
        
    }
    
    public ConteudoAlteraTela(Conteudo conteudo) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.conteudo = conteudo;
        preencheDados();
        preencherMateria();
    }
    
    
     public void preencherMateria(){
        materiaDAO = new MateriaDAO();
        materias = new ArrayList<Materia>();
        try {
            materias = materiaDAO.listaMateria();
            materias.forEach((e) -> {
           // for(Estado  e: estados){
            comboMateria.addItem(e.getNome());
            });
                       
        } catch (Exception ex) {
            Logger.getLogger(ConteudoAlteraTela.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    private void preencheDados(){
      campoConteudo.setText(conteudo.getNome().toUpperCase());
       for (Materia materia : materias) {
             if(materia.getNome().equals(conteudo.getMateria().getNome())){
                 comboMateria.setSelectedItem(materia.getNome());
             }
       }
   }

    private void preencheObjetos(){
      conteudo.setNome(campoConteudo.getText().toUpperCase());
      conteudo.setMateria((Materia) comboMateria.getSelectedItem());
      Materia mat = materias.get(comboMateria.getSelectedIndex());
            conteudo.getMateria().setId(mat.getId());
      
    //  materia.setEdital((Edital) comboEdital.getSelectedItem());   
   }
    
    private void alterarBD() throws SQLException{
        ConteudoDAO dao = new ConteudoDAO();
        try {
            dao.alterarConteudo(conteudo);
                JOptionPane.showMessageDialog(this,"Registro salvo com sucesso!");
         
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Erro Registro salvo com sucesso!");
        }

    }
    

    private void limparcampo(){
        campoConteudo.setText("");
    }
    
    private void salvar() throws SQLException{
              preencheObjetos();
             alterarBD();           
             limparcampo();
      
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
        setTitle("Alterar Conteudo");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Alterar conteudo"));

        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        jButton1.setText("Cancelar");
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
                        .addComponent(jButton1))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(botaoSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
           
            salvar();
          
        } catch (Exception ex) {
            Logger.getLogger(ConteudoAlteraTela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ConteudoPesquisaTela().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ConteudoAlteraTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConteudoAlteraTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConteudoAlteraTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConteudoAlteraTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new ConteudoAlteraTela().setVisible(true);
            }
       });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JTextField campoConteudo;
    private javax.swing.JComboBox<String> comboMateria;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
