/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ControlaConexao;
import Modelo.Estado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adriano
 */
public class EstadoDAO {
    private List<Estado> estados;
    
    public void inserir(Estado estado) throws Exception{     
        try {
            Connection conexao = ControlaConexao.getConnection();
            String query ="{Call sp_inserirEstado(?,?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
            instrucao.setString(1, estado.getNome());
            instrucao.setString(2,estado.getSigla());
            instrucao.executeQuery();
            conexao.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }       
    }
    
    public void alterar(Estado estado){
        try {
            Connection conexao = ControlaConexao.getConnection();
            String query ="{call sp_alterarEstado(?,?,?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
            instrucao.setInt(1,estado.getCodigo());
            instrucao.setString(2,estado.getNome());
            instrucao.setString(3, estado.getSigla());
            instrucao.execute();
            conexao.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }    
        
    }
    public void deletar(Estado estado) throws Exception{
       try {
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_excluirEstado(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setInt(1, estado.getCodigo());
           instrucao.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }    
   }
    public List<Estado> pesquisar(Estado estado){
            
       try {
           ResultSet resultados;
           
           estados = new ArrayList<Estado>();
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_pesquisarEstado(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setString(1,estado.getNome());
           resultados = instrucao.executeQuery();
           
           
           while (resultados.next()) {
               Estado  estadoSaida = new Estado(); 
               estadoSaida.setCodigo(resultados.getInt("estcodigo"));
               estadoSaida.setNome(resultados.getString("estnome"));
               estadoSaida.setSigla(resultados.getString("estsigla"));
               estados.add(estadoSaida);
           }
           return estados;
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }    
        return null;
        
   }
    
    public List<Estado> listar(){
            
       try {
           ResultSet resultados;
           
           estados = new ArrayList<Estado>();
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_listarEstado()}";
           CallableStatement instrucao = conexao.prepareCall(query);
           resultados = instrucao.executeQuery();
         
           while (resultados.next()) {
               Estado  estadoSaida = new Estado(); 
               estadoSaida.setCodigo(resultados.getInt("estcodigo"));
               estadoSaida.setSigla(resultados.getString("estsigla"));
               estados.add(estadoSaida);
           }
           return estados;
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }       
        return null;
        
   }
    
    
}
