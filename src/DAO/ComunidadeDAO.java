/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ControlaConexao;
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
public class ComunidadeDAO {
    private List<Comunidade> comunidades;
    
    public void inserir(Comunidade comunidade) throws Exception{     
        try {
            Connection conexao = ControlaConexao.getConnection();
            String query ="{Call sp_inserirComunidade(?,?,?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
            instrucao.setString(1,comunidade.getNome());
            instrucao.setInt(2,comunidade.getQtd());
            instrucao.setInt(3,comunidade.getEstado().getCodigo());
            instrucao.executeQuery();
            conexao.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }           
    }
    
    public void alterar(Comunidade comunidade){
        try {
            Connection conexao = ControlaConexao.getConnection();
            String query ="{call sp_alterarComunidade(?,?,?,?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
            instrucao.setInt(1, comunidade.getCodigo());
            instrucao.setString(2,comunidade.getNome());
            instrucao.setInt(3, comunidade.getQtd());
            instrucao.setInt(4, comunidade.getEstado().getCodigo());
            instrucao.execute();
            conexao.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }    
        
    }
    public void deletar(Comunidade comunidade) throws Exception{
       try {
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_excluirComunidade(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setInt(1, comunidade.getCodigo());
           instrucao.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }    
   }
    public List<Comunidade> pesquisar(Comunidade comunidade){
            
       try {
           ResultSet resultados;
           
           comunidades = new ArrayList<Comunidade>();
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_pesquisarComunidade(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setString(1,comunidade.getNome());
           resultados = instrucao.executeQuery();
           
           
           while (resultados.next()) {
               Comunidade  comunidadeSaida = new Comunidade(); 
               comunidadeSaida.setCodigo(resultados.getInt("comcodigo"));
               comunidadeSaida.setNome(resultados.getString("comnome"));
               comunidadeSaida.setQtd(resultados.getInt("comqtdprop"));
               comunidadeSaida.getEstado().setSigla(resultados.getString("estsigla"));
               comunidades.add(comunidadeSaida);
           }
           return comunidades;
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }         
        return null;
        
   }
    
   public List<Comunidade> listar(){
            
       try {
           ResultSet resultados;
           
           comunidades = new ArrayList<Comunidade>();
           Connection conexao = ControlaConexao.getConnection();
           String query = "{call sp_listarComunidade()}";
           CallableStatement instrucao = conexao.prepareCall(query);
           resultados = instrucao.executeQuery();
         
           while (resultados.next()) {
               Comunidade  comunidadeSaida = new Comunidade(); 
               comunidadeSaida.setCodigo(resultados.getInt("comcodigo"));
               comunidadeSaida.setNome(resultados.getString("comnome"));
               //comunidadeSaida.setQtd(resultados.getInt("comqtdprop"));
               //comunidadeSaida.getEstado().setSigla(resultados.getString("estsigla"));
               comunidades.add(comunidadeSaida);
               
           }
           return comunidades;
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Erro");
        }    
        return null;
        
   }
    
}
