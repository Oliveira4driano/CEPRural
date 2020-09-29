/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano
 */
public class UsuarioTabelaModelo extends AbstractTableModel{
    private List<Usuario> usuarios;
    
    private int qtdcoluna = 3;     
    

    public UsuarioTabelaModelo(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return qtdcoluna;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Usuario usuario = usuarios.get(linha);
        switch(coluna){
            case 0: return usuario.getCodigo();
            case 1: return usuario.getLogin();
            case 2: return usuario.getGrupo().getDescricao();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: return "Codigo";
            case 1: return "Usuario";
            case 2: return "Grupo";
        }
        return null;
    }
    
}
