package TELA;


import Conexao.ControlaConexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;


/**
 *
 * @author Aluno
 */
public class Log extends javax.swing.JFrame{
    
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                 
                new Log().setVisible(true);
                
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });       
    }

    public Log(){
        initComponents();
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(900, 550);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaoLogar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(800, 450));
        getContentPane().setLayout(null);

        botaoLogar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        botaoLogar.setText("ENTRAR");
        botaoLogar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoLogarMouseClicked(evt);
            }
        });
        botaoLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLogarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoLogar);
        botaoLogar.setBounds(150, 360, 600, 47);

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("CEP RURAL");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(340, 30, 270, 70);
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(150, 290, 290, 40);
        getContentPane().add(txtSenha);
        txtSenha.setBounds(460, 290, 290, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("POR UMA GESTÃO SOCIOAMBIENTAL INCLUDENTE NA AMAZÔNIA ");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(150, 110, 650, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ACESSE O SISTEMA DIGITANDO SEU USUÁRIO E SENHA");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(210, 160, 410, 30);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("USUÁRIO");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(150, 250, 100, 22);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("SENHA");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(460, 250, 70, 20);

        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/tela_principal.jpg"))); // NOI18N
        Fundo.setText("aaa");
        getContentPane().add(Fundo);
        Fundo.setBounds(10, 80, 900, 500);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLogarActionPerformed
        
        //Connection conexao;
        try {
            try{ 
                
                 ControlaConexao.CONEXAO_USER = txtUsuario.getText();
                 ControlaConexao.CONEXAO_PASSWORD = txtSenha.getText();
                 
                 Connection conexao;
                 conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/cep_rural",  txtUsuario.getText(), txtSenha.getText());
                 conexao.close(); 

                 
                
                 setVisible(false);
                 new Main().setVisible(true);
                  
            }catch(SQLException ex){
                 JOptionPane.showMessageDialog(null, ex.getMessage(), "Falha na conexao", JOptionPane.ERROR_MESSAGE);			
            }
        } 
        catch (Exception ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoLogarActionPerformed

    private void botaoLogarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoLogarMouseClicked
        // TODO add your handling code here: 
    }//GEN-LAST:event_botaoLogarMouseClicked
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fundo;
    private javax.swing.JButton botaoLogar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
} 
