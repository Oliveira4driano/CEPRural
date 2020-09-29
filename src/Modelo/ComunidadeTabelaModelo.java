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
public class ComunidadeTabelaModelo extends AbstractTableModel {
    private List<Comunidade> comunidades;

    public ComunidadeTabelaModelo(List<Comunidade> comunidades) {
        this.comunidades = comunidades;
    }
    
        
    @Override
    public int getRowCount() {
        return comunidades.size();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return 4;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: 
                return "Codigo";
            case 1:
                return "Nome";
            case 2:
                return "Quantidade";
            case 3:
                return "Estado";
        }
        return null;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Comunidade comunidade = comunidades.get(linha);
        switch(coluna){
            case 0:
                return comunidade.getCodigo();
            case 1:
                return comunidade.getNome();
            case 2:
                return comunidade.getQtd();
            case 3:
                return comunidade.getEstado().getSigla();
                 
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }
    
}
