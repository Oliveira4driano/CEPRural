package TELA;



import DAO.CepDAO;
import DAO.ComunidadeDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Modelo.CEP;

import Modelo.CEPTabelaModelo;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aluno
 */
public class Main extends javax.swing.JFrame {
    private CEP cep = new CEP();
    private List<CEP> ceps = new ArrayList<CEP>(); //tabela 
    private CEP cepSelecionado;
    
    private ComunidadeDAO comunidadeDAO;
    private List<Modelo.Comunidade> comunidades = new ArrayList<Modelo.Comunidade>();
    
    
    public Main() {
      cep.setCepcodigo("");
    
      initComponents();
      pesquisar();
      preencherComunidade();
      setLocationRelativeTo(null);    
    }
    
    
    public void preencherComunidade(){
        comunidadeDAO = new ComunidadeDAO();
        comunidades = new ArrayList<Modelo.Comunidade>();
        try {
            comunidades = comunidadeDAO.listar();
            for (Modelo.Comunidade comunidade : comunidades) {
                jComboBoxComunidade.addItem(comunidade.getNome());
            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        campoCEP = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBoxComunidade = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItemCEPRural = new javax.swing.JMenuItem();
        jMenuItemComunidade = new javax.swing.JMenuItem();
        jMenuItemTipoProp = new javax.swing.JMenuItem();
        jMenuItemEstado = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("PESQUISAR CEP RURAL");

        campoCEP.setToolTipText("");
        campoCEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCEPActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/magnifier_icon-icons.com_56922 - Cópia.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBoxComunidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxComunidadeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("FILTRAR COMUNIDADE");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CEP Rural", "Comunidade", "Proprietário", "Tipo Propriedade", "Mapa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        jMenuBar1.setAlignmentX(1.5F);
        jMenuBar1.setAlignmentY(1.0F);
        jMenuBar1.setMargin(new java.awt.Insets(0, 5, 0, 0));

        jMenu1.setText("Geral");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu3.setText("Administrador");

        jMenu2.setText("Gerenciar");

        jMenuItem3.setText("Usuario");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Grupo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenu3.add(jMenu2);

        jMenuItem2.setText("Visualizar Logs");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenu4.setText("Gerenciar");

        jMenuItemCEPRural.setText("CEPs Rurais");
        jMenuItemCEPRural.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCEPRuralActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemCEPRural);

        jMenuItemComunidade.setText("Comunidades");
        jMenuItemComunidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemComunidadeActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemComunidade);

        jMenuItemTipoProp.setText("Tipos de Propriedade");
        jMenuItemTipoProp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTipoPropActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemTipoProp);

        jMenuItemEstado.setText("Estados");
        jMenuItemEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEstadoActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemEstado);

        jMenu3.add(jMenu4);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Sobre");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoCEP)
                                .addGap(2, 2, 2)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(jLabel2)
                                .addGap(93, 93, 93))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxComunidade, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxComunidade)
                    .addComponent(campoCEP)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoCEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCEPActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_campoCEPActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void aActionPerformed(java.awt.event.ActionEvent evt) { 
    }   
        
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new LogPesquisaTela().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItemEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEstadoActionPerformed
        // TODO add your handling code here:
        new EstadoPesquisaTela().setVisible(true);
    }//GEN-LAST:event_jMenuItemEstadoActionPerformed

    private void jMenuItemComunidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemComunidadeActionPerformed
        // TODO add your handling code here:
        new ComunidadePesquisaTela().setVisible(true);
    }//GEN-LAST:event_jMenuItemComunidadeActionPerformed

    private void jMenuItemTipoPropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTipoPropActionPerformed
        // TODO add your handling code here:
        new TipoPesquisaTela().setVisible(true);
    }//GEN-LAST:event_jMenuItemTipoPropActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        new GrupoPesquisaTela().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void tabelaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseReleased
        if(evt.getClickCount() > 1){
           cepSelecionado =ceps.get(tabela.getSelectedRow());
           String urlFinal = "http://maps.google.com.br/maps?hl=pt-br&biw=1600&bih=718&q=" + 
                   cepSelecionado.getLat()  + "%2C" + 
                   cepSelecionado.getLongi() + "&um=1&ie=UTF-8&sa=N&tab=wl";
       
            try {
                abreNavegador(urlFinal);
               // Application.launch(Maps.class);

            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tabelaMouseReleased

    private void jComboBoxComunidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxComunidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxComunidadeActionPerformed

    private void jMenuItemCEPRuralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCEPRuralActionPerformed
        new CepPesquisaTela().setVisible(true);
    }//GEN-LAST:event_jMenuItemCEPRuralActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new UsuarioPesquisaTela().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    
    public void preencherFiltro(){
        cep.setCepcodigo(campoCEP.getText());
    }
    private void pesquisarBD(){
         CepDAO dao= new CepDAO();  
         
        try{
           ceps= dao.pesquisar(cep);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Ocorreu um erro de banco de dados: "+e.getMessage());
                   
        }         
    }  
    
    private void preencherTabela(){
        tabela.setModel(new CEPTabelaModelo(ceps));
    } 
    public void pesquisar(){
        preencherFiltro();
        pesquisarBD();
        preencherTabela();
        campoCEP.getCursor();     
    }  
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoCEP;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxComunidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItemCEPRural;
    private javax.swing.JMenuItem jMenuItemComunidade;
    private javax.swing.JMenuItem jMenuItemEstado;
    private javax.swing.JMenuItem jMenuItemTipoProp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables


    public static void abreNavegador(String url) throws Exception {
        try {
            
            System.out.println("-----------------"+url);
            URI uri = new URI(url);
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }
            if (desktop != null) {
                desktop.browse(uri);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (URISyntaxException use) {
            use.printStackTrace();
        }
    }
}
