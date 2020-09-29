/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Grupo;
import Conexao.ControlaConexao;
import Modelo.Permissao;
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
public class PermissaoDAO {
    
     private List<Permissao> permissoes;
    
     public void inserir(Permissao perm) throws Exception{     
        try {
            Connection conexao = ControlaConexao.getConnection();
            // idgrupo int, p_nomebanco varchar(50), p_tabela varchar(50), p_manipulacao varchar(80))
            String query ="{Call sp_darPermissao(?, ?, ?, ?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
            instrucao.setInt(1,perm.getGrupo().getCodigo());
            instrucao.setString(2, ControlaConexao.CONEXAO_NOMEBANCO);
            instrucao.setString(3, perm.getTabela());
            instrucao.setString(4, perm.getDescricao());
            instrucao.executeQuery();
            conexao.close();
            
            JOptionPane.showMessageDialog(null, "Atencao!", "Registro Salvo com Sucesso!", JOptionPane.OK_CANCEL_OPTION);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Falha na conexao!", JOptionPane.ERROR_MESSAGE);// Criar uma tela aparecendo essa mensagem e mais detalhes    
            System.out.println(e.getMessage());
        }        
    }
    public void deletar(Permissao perm) throws Exception{     
        try {
            Connection conexao = ControlaConexao.getConnection();
            // idgrupo int, p_nomebanco varchar(50), p_tabela varchar(50), p_manipulacao varchar(80))
            String query ="{Call sp_removerPrivilegios(?, ?, ?, ?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
            instrucao.setInt(1,perm.getGrupo().getCodigo());
            instrucao.setString(2, ControlaConexao.CONEXAO_NOMEBANCO);
            instrucao.setString(3, perm.getTabela());
            instrucao.setString(4, perm.getDescricao());
            instrucao.executeQuery();
            conexao.close();
            
            JOptionPane.showMessageDialog(null, "Atencao!", "Excluido com sucesso!", JOptionPane.OK_CANCEL_OPTION);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Falha na conexao!", JOptionPane.ERROR_MESSAGE);// Criar uma tela aparecendo essa mensagem e mais detalhes    
            System.out.println(e.getMessage());
        }        
    }
          /*
   public void alterar(Grupo grupo){
        try {
            Connection conexao = ControlaConexao.getConnection();
            String query ="{call sp_alterarGrupo(?,?,?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
            instrucao.setString(1,grupo.getDescricao());
           // instrucao.setInt(2, grupo.getPermissao().getCodigo());
            instrucao.setInt(3,grupo.getCodigo());
            instrucao.execute();
            conexao.close();
            
        } catch (SQLException e) {
        }
        
    } 
    public List<Permissao> listar(){
        List<Permissao> permissoes = new ArrayList<Permissao >();
        try {
            Connection conexao = ControlaConexao.getConnection();
            String query ="{call sp_listarPermissao(?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
            instrucao.setString(1, permissoes);
            ResultSet results = instrucao.executeQuery();
            
            while(results.next()){
                Permissao perm = new Permissao();
                perm.setCodigo(results.getInt("pertabela"));
                perm.setDescricao(results.getString("perdescricao"));
                permissoes.add(perm);
            }
                
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return permissoes; //1
    } 
    
   public void deletar(Grupo grupo) throws Exception{
       try {
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_excluirGrupo(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setInt(1, grupo.getCodigo());
           instrucao.execute();
       } catch (SQLException e) {
           throw new Exception(e);
       }
   }*/
      
   public List<Permissao> pesquisar(Permissao perm){
            
       try {
           ResultSet resultados;
           //List<Grupo> grupos2;
           permissoes = new ArrayList<Permissao>();
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_pesquisarPermissao(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setInt(1, perm.getGrupo().getCodigo());
           resultados = instrucao.executeQuery();
           while (resultados.next()) {
               Permissao  permissaoSaida = new Permissao(); 
               
               permissaoSaida.setTabela(resultados.getString("pertabela"));
               permissaoSaida.setDescricao(resultados.getString("perdescricao"));
               permissaoSaida.getGrupo().setCodigo(resultados.getInt("pergrucodigo"));
               
               permissoes.add(permissaoSaida);
           }
           return permissoes;
       } catch (SQLException e) {
           System.out.println("ERRO: " + e.getMessage());
       }       
        return null;
        
   }
     
}





  /*
public Grupo buscarPorId(int codigo){
       Grupo g = null;
       try {
           Connection conexao = ControlaConexao.getConnection();
           String query ="{call sp_buscarPorId(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setInt(1, codigo);
           ResultSet results = instrucao.executeQuery();
           while(results.next()){
               Grupo grupo = new Grupo();
               System.out.println(results.getInt("codigo"));
               grupo.setCodigo(results.getInt("codigo"));
               grupo.setDescricao(results.getString("descricao"));
               grupo.getPermissao().setStatus(results.getString("permissao"));
               
           }
           
       } catch (SQLException e) {
           g=null;
       }
        return g;
       
   }
        String inserirProduto = "insert into grupo (grucodigo, grudescricao,grupercodigo)values(?,?,?);";       
        try{
            Connection conexao = ControlaConexao.getConnection();
            PreparedStatement instrucao = conexao.prepareStatement(inserirProduto);
            instrucao.setString(1, "0");
            instrucao.setString(2, grupo.getDescricao());
            instrucao.setInt(3,grupo.getPermissao().getStatus());
            instrucao.execute();
            conexao.close();
        } catch(SQLException e){
            throw new BDException(e);
        } */
