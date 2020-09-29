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
public class GrupoTabelaModelo extends AbstractTableModel{
    private List<Grupo> grupos;
    
    private int qtdcoluna = 2;     
    

    public GrupoTabelaModelo(List<Grupo> grupos) {
        this.grupos = grupos;
    }
    
    @Override
    public int getRowCount() {
        return grupos.size();
    }

    @Override
    public int getColumnCount() {
        return qtdcoluna;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Grupo grupo = grupos.get(linha);
        switch(coluna){
            case 0: return grupo.getCodigo();
            case 1: return grupo.getDescricao();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: return "Codigo";
            case 1: return "Nome";
        }
        return null;
    }
    
}
