/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ControlaConexao;
import Modelo.Log;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Adriano
 */
public class LogDAO {
    
     private List<Log > logs;
    
     public void inserir(Log log) throws Exception{     
        try {
            Connection conexao = ControlaConexao.getConnection();
            // idgrupo int, p_nomebanco varchar(50), p_tabela varchar(50), p_manipulacao varchar(80))
            String query ="{Call sp_darPermissao(?, ?, ?, ?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
         /*   instrucao.setInt(1,perm.getGrupo().getCodigo());
            instrucao.setString(2, ControlaConexao.CONEXAO_NOMEBANCO);
            instrucao.setString(3, perm.getTabela());
            instrucao.setString(4, perm.getDescricao());
            instrucao.executeQuery();
            conexao.close();
            
            JOptionPane.showMessageDialog(null, "Atencao!", "Registro Salvo com Sucesso!", JOptionPane.OK_CANCEL_OPTION);
            */
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Falha na conexao!", JOptionPane.ERROR_MESSAGE);// Criar uma tela aparecendo essa mensagem e mais detalhes    
            System.out.println(e.getMessage());
        }        
    }
      
   public List<Log> pesquisar(Log log){
            
       try {
           ResultSet resultados;
           //List<Grupo> grupos2;
           logs = new ArrayList<Log>();
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_pesquisarLog(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setString(1, log.getUsuario().getLogin());
           resultados = instrucao.executeQuery();
           while (resultados.next()) {
               
               Log log1 = new Log();
              
               log1.setCodigo(resultados.getInt("logcodigo"));
               log1.getUsuario().setLogin(resultados.getString("usulogin"));
               log1.setData(resultados.getString("logdata"));
               log1.setHora(resultados.getString("loghora"));
               log1.setDescricao(resultados.getString("logdescricao"));
               
               logs.add(log1);
           }
           return logs;
       } catch (SQLException e) {
           System.out.println("ERRO: " + e.getMessage());
       }       
        return null;
        
   }
     
}