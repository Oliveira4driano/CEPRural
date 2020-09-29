/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TELA;


import DAO.GrupoDAO;
import Modelo.Grupo;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author Adriano
 */
public class GrupoAlteraTela extends javax.swing.JFrame {

    private Grupo grupo;
    
    public GrupoAlteraTela(Grupo grupo) {
        
        initComponents();
        setLocationRelativeTo(null);
        this.grupo = grupo;
     //   preencherDados();
        
    }
     
    private void preencherObjeto(){
       grupo.setDescricao(campoNome.getText());
      /*  if(jCheckBoxADM.isSelected()){
            grupo.getPermissao().setCodigo(1);    
        }
        else{
            grupo.getPermissao().setCodigo(2); 
        }  */   
    } 
    private void alterarBD(){
        GrupoDAO dao = new GrupoDAO();
        /*grupo.setDescricao(campoNome.getText());
        if(jCheckBoxADM.isSelected()){
        grupo.getPermissao().setCodigo(1);
        }else{
            grupo.getPermissao().setCodigo(2);

        }*/
        try{
            dao.alterar(grupo);
            
            JOptionPane.showMessageDialog(this,"Registro Alterado com Sucesso!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Ocorreu um erro de banco de dados");
        }
        
    }
    /*
    private void preencherDados(){
        
        campoNome.setText(grupo.getDescricao());
        
        
        if(grupo.getPermissao().getStatus().equals("V")){
            jCheckBoxADM.setSelected(true);  
        }
        else{
            jCheckBoxADM.setSelected(false);
        }
       
    }
    */
    //Ações dos Botões
    
    private void alterar(){
        preencherObjeto();
        alterarBD();
        
    }
    
    private void liberarTela(){
        dispose();
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
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        rotuloNome = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        botaoSalvar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alterar Estado");

        jLabel1.setText("Grupo de Dados");

        rotuloNome.setText("Nome");

        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rotuloNome)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(botaoSalvar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botaoCancelar))
                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(campoNome)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rotuloNome)
                .addGap(6, 6, 6)
                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvar)
                    .addComponent(botaoCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        alterar();
        liberarTela();
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        liberarTela();
    }//GEN-LAST:event_botaoCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JTextField campoNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel rotuloNome;
    // End of variables declaration//GEN-END:variables
}
