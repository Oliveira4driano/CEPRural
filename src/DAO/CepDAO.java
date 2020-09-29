/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexao.ControlaConexao;

import Modelo.CEP;
import Modelo.Comunidade;
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
public class CepDAO {
    private List<CEP> ceps;
    
    public void inserir(CEP cep) throws Exception{     
        try {
            Connection conexao = ControlaConexao.getConnection();
            String query ="{call sp_inserirCep(?,?,?,?,?,?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
            instrucao.setString(1,cep.getCepcodigo());
            instrucao.setString(2,cep.getLat());
            instrucao.setString(3,cep.getLongi());
            instrucao.setString(4,cep.getCepnomeprop());
            instrucao.setInt(5,cep.getCepcomcodigo().getCodigo());
            instrucao.setInt(6,cep.getCeptprcodigo().getCodigo());
            //instrucao.setInt(7,cep.getCepcomcodigo().getEstado().getCodigo());
            instrucao.executeQuery();
            conexao.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }        
    }
    
     public void deletar(CEP cep) throws Exception{
       try {
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_excluirCep(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setInt(1, cep.getCepidcodigo());
           instrucao.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }    
   }
    
    
    
    public List<CEP> pesquisar(CEP cep){
            
       try {
           ResultSet resultados;
           ceps = new ArrayList<CEP>();
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_pesquisarCEP(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setString(1,cep.getCepcodigo());
           resultados = instrucao.executeQuery();
           
           
           while (resultados.next()) {
               
               CEP  tipoSaida = new CEP(); 
               tipoSaida.setCepcodigo(resultados.getString("cepcodigo"));
               tipoSaida.setLat(resultados.getString("ceplat"));
               tipoSaida.setLongi(resultados.getString("ceplongi"));
               tipoSaida.setCepnomeprop(resultados.getString("cepnomeprop"));
               tipoSaida.getCepcomcodigo().setNome(resultados.getString("comnome"));
               tipoSaida.getCeptprcodigo().setTipo(resultados.getString("tprtipo"));
               tipoSaida.getCepcomcodigo().getEstado().setNome(resultados.getString("estnome"));          
               
               
               ceps.add(tipoSaida);
           }
           return ceps;
       } catch (SQLException e) {
           System.out.println(e.getMessage()+"Erro");
       }       
        return null;
        
   }
    public List<CEP> listar(CEP cep){
            
       try {
           ResultSet resultados;
           ceps = new ArrayList<CEP>();
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_listarCEP(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setString(1,cep.getCepcodigo());
           resultados = instrucao.executeQuery();
           
           
           while (resultados.next()) {
               
               CEP  tipoSaida = new CEP(); 
               tipoSaida.setCepcodigo(resultados.getString("cepcodigo"));
               tipoSaida.setCepnomeprop(resultados.getString("cepnomeprop"));
               tipoSaida.getCepcomcodigo().setNome(resultados.getString("comnome"));
               tipoSaida.getCeptprcodigo().setTipo(resultados.getString("tprtipo"));
               tipoSaida.getCepcomcodigo().getEstado().setNome(resultados.getString("estnome"));          
               
               
               ceps.add(tipoSaida);
           }
           return ceps;
       } catch (SQLException e) {
           System.out.println(e.getMessage()+"Erro");
       }       
        return null;
        
   }
    
}
