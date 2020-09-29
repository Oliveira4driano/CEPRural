/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TELA;


import DAO.UsuarioDAO;
import Modelo.Usuario;
import javax.swing.JOptionPane;


/**
 *
 * @author Adriano
 */



public class UsuarioConsultaTela extends javax.swing.JFrame {
    
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;
    
    public UsuarioConsultaTela(Usuario usuario) {
        this();
        this.usuario=usuario;
        preencherDados();
        
    }
    

    public UsuarioConsultaTela() {
        initComponents();  
        setLocationRelativeTo(this);
    }
  
   
    private void preencherDados(){
        LabelNome.setText(usuario.getLogin());
        LabelGrupo.setText(usuario.getGrupo().getDescricao());
    //    LabelPermissao.setText(grupo.getPermissao().getStatus());
    }
    
    private void liberarTela(){
        dispose();
    }
    
    private void alterar(){
        //GrupoAlteraTela tela=new GrupoAlteraTela(usuario);
     //   tela.setVisible(true);
        dispose();
    }
    
    //Ações Botões
    
    private void deletar(){
        usuarioDAO = new UsuarioDAO();
            
        try{
        //    usuarioDAO.deletar(usuario);
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

        LabelNome = new javax.swing.JLabel();
        botaoAlterar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        botaoExcluir = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        LabelGrupo = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar Grupo");

        LabelNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LabelNome.setText("LabelNome");

        botaoAlterar.setText("Alterar senha");
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

        jLabel3.setText("Grupo");

        LabelGrupo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LabelGrupo.setText("LabelNome");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jSeparator3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LabelGrupo))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botaoAlterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoCancelar)))
                        .addGap(0, 142, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(LabelGrupo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAlterar)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoExcluir))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        int resposta=JOptionPane.showConfirmDialog(this,"Deseja realmente excluir "+usuario.getLogin()+"?",
                                                 "Exclusão de Estado",JOptionPane.YES_NO_OPTION,
                                                  JOptionPane.QUESTION_MESSAGE);
        if(resposta==JOptionPane.YES_OPTION) {
            try{
                deletar();
                liberarTela();
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,"Erro:"+ e.getMessage());
            }
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        liberarTela();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
    //    alterar();
    
        String resposta = JOptionPane.showInputDialog(this,"Escreva a nova senha");
        if(resposta!=null) {
            try{
                usuarioDAO = new UsuarioDAO();
                usuarioDAO.alterarSenha(usuario, resposta);
                liberarTela();
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,"Erro:"+ e.getMessage());
            }
        }
    }//GEN-LAST:event_botaoAlterarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelGrupo;
    private javax.swing.JLabel LabelNome;
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
