/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TELA;


import DAO.GrupoDAO;
import Modelo.Grupo;
import javax.swing.JOptionPane;


/**
 *
 * @author Adriano
 */



public class GrupoConsultaTela extends javax.swing.JFrame {
    
    private Grupo grupo;
    private GrupoDAO grupoDAO;
    
    public GrupoConsultaTela(Grupo grupo) {
        this();
        this.grupo=grupo;
        preencherDados();
        
    }
    

    public GrupoConsultaTela() {
        initComponents();  
        setLocationRelativeTo(this);
    }
  
   
    private void preencherDados(){
        LabelNome.setText(grupo.getDescricao());
    //    LabelPermissao.setText(grupo.getPermissao().getStatus());
    }
    
    private void liberarTela(){
        dispose();
    }
    
    private void alterar(){
        GrupoAlteraTela tela=new GrupoAlteraTela(grupo);
        tela.setVisible(true);
        dispose();
    }
    
    //Ações Botões
    
    private void deletar(){
        grupoDAO = new GrupoDAO();
            
        try{
            grupoDAO.deletar(grupo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Ocorreu um erro de banco de dados");
        }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        LabelNome = new javax.swing.JLabel();
        botaoAlterar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        botaoExcluir = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        botaoExcluir1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar Grupo");

        jLabel1.setText("Nome");

        LabelNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LabelNome.setText("LabelNome");

        botaoAlterar.setText("Alterar");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        botaoExcluir1.setText("Permissao");
        botaoExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botaoAlterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoExcluir1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoExcluir)
                                .addGap(83, 83, 83)
                                .addComponent(botaoCancelar)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(LabelNome)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelNome)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAlterar)
                    .addComponent(botaoExcluir)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoExcluir1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        int resposta=JOptionPane.showConfirmDialog(this,"Deseja realmente excluir "+grupo.getDescricao()+"?",
                                                 "Exclusão de Estado",JOptionPane.YES_NO_OPTION,
                                                  JOptionPane.QUESTION_MESSAGE);
        if(resposta==JOptionPane.YES_OPTION)
        try{
            deletar();
            liberarTela();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Erro:"+ e.getMessage());
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        liberarTela();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        alterar();
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluir1ActionPerformed
        new GrupoPermissaoTela(grupo).setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoExcluir1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelNome;
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoExcluir1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}