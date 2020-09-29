package TELA;

import Conexao.ControlaConexao;
import DAO.PermissaoDAO;
import DAO.UsuarioDAO;
import Modelo.Grupo;
import Modelo.Permissao;
import Modelo.PermissaoTabelaModelo;
import Modelo.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonathan
 */
public class GrupoPermissaoTela extends javax.swing.JFrame {

    private List<Permissao> permissoes = new ArrayList<Permissao>(); //tabela 
    private Permissao permissaoSelect;
    private Permissao perm = new Permissao();
    private Grupo grp;
   
    public GrupoPermissaoTela(Grupo grp) {
        this();
        this.grp=grp;
        carregaTabelas();  
        preencherNomeGrp();
    }
    
    public GrupoPermissaoTela() {
        initComponents();
        setLocationRelativeTo(this);
    }
    private void preencherNomeGrp(){
        labelPermGrp.setText("Permissao ao: " + grp.getDescricao() + "(" +grp.getCodigo() + ")");
        perm.getGrupo().setCodigo(grp.getCodigo());
        pesquisarBD();
        preencherTabela();
    }
    private void pesquisarBD(){
         PermissaoDAO dao= new PermissaoDAO();  
         permissoes = dao.pesquisar(perm);
    }  
    private void preencherTabela(){
        tabela.setModel(new  PermissaoTabelaModelo(permissoes));
    }
     private void preencherDados(){
         
        String pegarDescricao = "";
        
        if(rbtnSelect.isSelected()) {
            pegarDescricao = pegarDescricao + "select,";
        }
        if(rbtnUpdate.isSelected()) {
            pegarDescricao = pegarDescricao + "update,";
        }
        if(rbtnInsert.isSelected()) {
            pegarDescricao = pegarDescricao + "insert,";
        }
        if(rbtnDelete.isSelected()) {
            pegarDescricao = pegarDescricao + "delete,";
        }
        perm.setDescricao(pegarDescricao);
        perm.setTabela(comboGrp.getSelectedItem().toString());
        
    }
    
    private void inserirBD() throws Exception{
       PermissaoDAO dao = new PermissaoDAO();
       dao.inserir(perm);  
    }
    
    private void limparDados(){
        rbtnSelect.setSelected(false);
        rbtnUpdate.setSelected(false);
        rbtnInsert.setSelected(false);
        rbtnDelete.setSelected(false);
        comboGrp.setSelectedIndex(-1);
              
    } 
    private void salvar() throws Exception{
        preencherDados();
        inserirBD();
        pesquisarBD();
        preencherTabela();
        limparDados();
    }
    public void selecionarPermissao(){
        this.permissaoSelect = permissoes.get(tabela.getSelectedRow());  
    } 
    private void liberarTela(){
        dispose();
    }
    public void carregaTabelas()  
    {  
        Connection conexao = null;
        CallableStatement instrucao = null;
        
        try{
            conexao = ControlaConexao.getConnection();
            String query = "show tables";
            instrucao = conexao.prepareCall(query);
            ResultSet results = instrucao.executeQuery();
            while(results.next()){
                comboGrp.addItem(results.getString(1));
            }
                
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        } finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }  
        
    }       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPermGrp = new javax.swing.JLabel();
        botaoCadastrar = new javax.swing.JButton();
        BotaoCancelar = new javax.swing.JButton();
        comboGrp = new javax.swing.JComboBox<>();
        rbtnSelect = new javax.swing.JCheckBox();
        rbtnInsert = new javax.swing.JCheckBox();
        rbtnUpdate = new javax.swing.JCheckBox();
        rbtnDelete = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Grupo");

        labelPermGrp.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelPermGrp.setText("Permissão");

        botaoCadastrar.setText("CADASTRAR");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        BotaoCancelar.setText("VOLTAR");
        BotaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelarActionPerformed(evt);
            }
        });

        comboGrp.setToolTipText("");
        comboGrp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboGrpActionPerformed(evt);
            }
        });

        rbtnSelect.setText("SELECT");
        rbtnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnSelectActionPerformed(evt);
            }
        });

        rbtnInsert.setText("INSERT");

        rbtnUpdate.setText("UPDATE");

        rbtnDelete.setText("DELETE");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
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
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtnDelete)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botaoCadastrar)
                                .addGap(18, 18, 18)
                                .addComponent(BotaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rbtnInsert)
                            .addComponent(rbtnUpdate)
                            .addComponent(rbtnSelect)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(comboGrp, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(labelPermGrp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelPermGrp)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboGrp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbtnSelect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtnInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoCancelar)
                            .addComponent(botaoCadastrar)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        try {
            salvar();
            
        } catch (Exception ex) {
            Logger.getLogger(GrupoPermissaoTela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void BotaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelarActionPerformed
            liberarTela();        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoCancelarActionPerformed

    private void comboGrpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGrpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboGrpActionPerformed

    private void rbtnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnSelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnSelectActionPerformed

    private void tabelaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseReleased
        selecionarPermissao();
        if(evt.getClickCount() > 1){

            int resposta=JOptionPane.showConfirmDialog(this,
                    "Deseja realmente excluir a permissao de "+ permissaoSelect.getDescricao()+
                    " da tabela " + permissaoSelect.getTabela() + " desse grupo?",
                     "Exclusão de Permissao",
                         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
           
            if(resposta==JOptionPane.YES_OPTION) {
                try{
                    PermissaoDAO dao = new PermissaoDAO();
                    dao.deletar(permissaoSelect);
                    pesquisarBD();
                    preencherTabela();
                    
                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(this,"Erro:"+ e.getMessage());
                }
                    System.out.println(perm.getTabela() + " " + perm.getDescricao());
            }
        }
    }//GEN-LAST:event_tabelaMouseReleased

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
            java.util.logging.Logger.getLogger(GrupoPermissaoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GrupoPermissaoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GrupoPermissaoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GrupoPermissaoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                GrupoPermissaoTela tela = new GrupoPermissaoTela();
                tela.setLocationRelativeTo(null);
                tela.setVisible(true);
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoCancelar;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JComboBox<String> comboGrp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelPermGrp;
    private javax.swing.JCheckBox rbtnDelete;
    private javax.swing.JCheckBox rbtnInsert;
    private javax.swing.JCheckBox rbtnSelect;
    private javax.swing.JCheckBox rbtnUpdate;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
