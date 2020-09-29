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
public class PermissaoTabelaModelo extends AbstractTableModel{
    private List<Permissao> permissoes;
    
    private int qtdcoluna = 2;     
    

    public PermissaoTabelaModelo(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
    @Override
    public int getRowCount() {
        return permissoes.size();
    }

    @Override
    public int getColumnCount() {
        return qtdcoluna;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Permissao permissao = permissoes.get(linha);
        switch(coluna){
            case 0: return permissao.getTabela();
            case 1: return permissao.getDescricao();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: return "Tabela";
            case 1: return "Permissao";
        }
        return null;
    }
    
}
