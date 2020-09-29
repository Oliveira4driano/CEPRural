/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Grupo;
import Conexao.ControlaConexao;
import Modelo.Usuario;
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
public class UsuarioDAO {
     private List<Usuario> usuarios;//1

     
    public void alterarSenha(Usuario usuario, String senha) throws Exception{     
        try {
            Connection conexao = ControlaConexao.getConnection();
            String query ="{Call sp_alterarSenhaUsuario(?, ?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
            instrucao.setString(1, usuario.getLogin());
            instrucao.setString(2, senha);
            instrucao.executeQuery();
            conexao.close();
            
            JOptionPane.showMessageDialog(null, "Atencao!", "Registro Salvo com Sucesso!", JOptionPane.OK_CANCEL_OPTION);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Falha na conexao!", JOptionPane.ERROR_MESSAGE);// Criar uma tela aparecendo essa mensagem e mais detalhes    
            System.out.println(e.getMessage());
        }        
    }
         
         
    public void inserir(Usuario usuario, String senha) throws Exception{     
        try {
            Connection conexao = ControlaConexao.getConnection();
            String query ="{Call sp_criarUsuario(?, ?, ?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
            instrucao.setString(1, usuario.getLogin());
            instrucao.setString(2, senha);
            instrucao.setInt(3, usuario.getGrupo().getCodigo());
            instrucao.executeQuery();
            conexao.close();
            
            JOptionPane.showMessageDialog(null, "Atencao!", "Registro Salvo com Sucesso!", JOptionPane.OK_CANCEL_OPTION);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Falha na conexao!", JOptionPane.ERROR_MESSAGE);// Criar uma tela aparecendo essa mensagem e mais detalhes    
            System.out.println(e.getMessage());
        }        
    }/*
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
    public List<Grupo> listar(){
        List<Grupo> grupos = new ArrayList<Grupo >();
        try {
            Connection conexao = ControlaConexao.getConnection();
            String query ="{call sp_listarGrupo}";
            CallableStatement instrucao = conexao.prepareCall(query);
            ResultSet results = instrucao.executeQuery();
            
            while(results.next()){
                Grupo grupo = new Grupo();
                grupo.setCodigo(results.getInt("grucodigo"));
                grupo.setDescricao(results.getString("grudescricao"));
            //    grupo.getPermissao().setStatus(results.getString("perstatus"));
                grupos.add(grupo);
            }
                
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return grupos; //1
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
      
   public List<Usuario> pesquisar(Usuario usuario){
            
       try {
           ResultSet resultados;
           //List<Grupo> grupos2;
           usuarios = new ArrayList<Usuario>();
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_pesquisarUsuario(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setString(1,usuario.getLogin());
           resultados = instrucao.executeQuery();
           while (resultados.next()) {
               Usuario  grupoSaida = new Usuario(); 
               grupoSaida.setCodigo(resultados.getInt("usucodigo"));
               grupoSaida.setLogin(resultados.getString("usulogin"));
               grupoSaida.getGrupo().setDescricao(resultados.getString("grudescricao"));
               usuarios.add(grupoSaida);
           }
           return usuarios;
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
