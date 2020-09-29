/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Adriano
 */
public class ControlaConexao {
    //public static String CONEXAO_HOST = "";
    //public static String CONEXAO_PORTA = "";
    //public static String CONEXAO_BANCO = "";
    public static String CONEXAO_USER = "";
    public static String CONEXAO_PASSWORD = "";
    public static String CONEXAO_NOMEBANCO= "cep_rural";      

    public static Connection getConnection(){
      
        try{
           String host="jdbc:mysql://localhost:3306/cep_rural?noAccessToProcedureBodies=true";
           // return DriverManager.getConnection(host, "root", "root");
           //String host = "jdbc:mysql://"+CONEXAO_HOST+":"+CONEXAO_PORTA+"/"+CONEXAO_BANCO;
           return DriverManager.getConnection(host, CONEXAO_USER, CONEXAO_PASSWORD);
                    
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void fecharInstrucao(PreparedStatement instrucao){
        if(instrucao != null){
            try{
                instrucao.close();
            } catch(SQLException e){
                System.out.println("Erro: " + e.getMessage());
            }
        }        
    }
    
    public static void fecharConexao(Connection conexao){
        if(conexao != null){
            try{
                conexao.close();
            } catch(SQLException e){
                System.out.println("Erro: " + e.getMessage());
            }
        }   
    }
    
}
