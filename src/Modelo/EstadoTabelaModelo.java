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
public class EstadoTabelaModelo extends AbstractTableModel {
    private List<Estado> estados;
    private int qtdColunas =3;

    public EstadoTabelaModelo(List<Estado> estados) {
        this.estados = estados;
    }
       
    @Override
    public int getRowCount() {
        return estados.size();
    }

    @Override
    public int getColumnCount() {
        return qtdColunas;
    }
    
    @Override
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: 
                return "Codigo";
            case 1:
                return "Estado";
            case 2:
                return "Sigla";
                   
        }
        return null;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Estado estado = estados.get(linha);
        switch(coluna){
            case 0:
                return estado.getCodigo();
            case 1:
                return estado.getNome();
            case 2:
                return estado.getSigla();
        }
        return null;
    }
    
}
