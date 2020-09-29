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
public class TipoPropriedadeTabelaModelo extends AbstractTableModel {
    private List<TipoPropriedade> tipoPropriedades;

    public TipoPropriedadeTabelaModelo(List<TipoPropriedade> tipoPropriedades) {
        this.tipoPropriedades = tipoPropriedades;
    }
    
    @Override
    public int getRowCount() {
        return tipoPropriedades.size();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return 2;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: 
                return "Codigo";
            case 1:
                return "Tipo Propriedade";
        }
        return null;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TipoPropriedade tipoPropriedade = tipoPropriedades.get(linha);
        switch(coluna){
            case 0:
                return tipoPropriedade.getCodigo();
            case 1:
                return tipoPropriedade.getTipo();
        }
        return null;
    }
    
}
