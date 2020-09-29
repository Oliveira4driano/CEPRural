/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexao.ControlaConexao;

import Modelo.TipoPropriedade;
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
public class TipoDAO {
    private List<TipoPropriedade> tipoPropriedades;
    
    
    public void inserir(TipoPropriedade tipoPropriedade){
        try {
            Connection conexao = ControlaConexao.getConnection();
            String query = "{call sp_inserirTipo(?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
            instrucao.setString(1, tipoPropriedade.getTipo());
            instrucao.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }    
    }
    
    public void alterar(TipoPropriedade tipoPropriedade){
        try {
            Connection conexao = ControlaConexao.getConnection();
            String query ="{call sp_alterarTipo(?,?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
            instrucao.setInt(1,tipoPropriedade.getCodigo());
            instrucao.setString(2,tipoPropriedade.getTipo());
            instrucao.execute();
            conexao.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }    
        
    }
    public void deletar(TipoPropriedade tipoPropriedade) throws Exception{
       try {
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_excluirTipo(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setInt(1, tipoPropriedade.getCodigo());
           instrucao.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }    
   }
    public List<TipoPropriedade> pesquisar(TipoPropriedade tipoPropriedade){
            
       try {
           ResultSet resultados;
           tipoPropriedades = new ArrayList<TipoPropriedade>();
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_pesquisarTipo(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setString(1,tipoPropriedade.getTipo());
           resultados = instrucao.executeQuery();
           
           
           while (resultados.next()) {
               TipoPropriedade  tipoSaida = new TipoPropriedade(); 
               tipoSaida.setCodigo(resultados.getInt("tprcodigo"));
               tipoSaida.setTipo(resultados.getString("tprtipo"));
              
               tipoPropriedades.add(tipoSaida);
           }
           return tipoPropriedades;
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }      
        return null;
        
   }
    
   public List<TipoPropriedade> listar(){
            
       try {
           ResultSet resultados;
           tipoPropriedades = new ArrayList<TipoPropriedade>();
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_listarTipo()}";
           CallableStatement instrucao = conexao.prepareCall(query);
           resultados = instrucao.executeQuery();
           while (resultados.next()) {
               TipoPropriedade  tipoSaida = new TipoPropriedade(); 
               tipoSaida.setCodigo(resultados.getInt("tprcodigo"));
               tipoSaida.setTipo(resultados.getString("tprtipo"));
              
               tipoPropriedades.add(tipoSaida);
           }
           return tipoPropriedades;
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }        
        return null;
        
   } 
    
}
