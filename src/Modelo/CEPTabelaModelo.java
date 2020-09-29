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
public class CEPTabelaModelo extends AbstractTableModel {
    private List<CEP> cep;

    public CEPTabelaModelo(List<CEP> cep) {
        this.cep = cep;
    }
    
        
    @Override
    public int getRowCount() {
        return cep.size();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return 5;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: 
                return "Codigo";
            case 1:
                return "Nome proprietario"; 
            case 2:
                return "Comunidade"; 
            case 3:
                return "Tipo Propriedade";
            case 4: 
                return "Estado";
        }
        return null;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        CEP cepi = cep.get(linha);
        switch(coluna){
            case 0:
                return cepi.getCepcodigo();
            case 1:           
                return cepi.getCepnomeprop();
            case 2:
                return cepi.getCepcomcodigo().getNome();
            case 3:
                return cepi.getCeptprcodigo().getTipo(); 
            case 4:
                return cepi.getCepcomcodigo().getEstado().getNome();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }
    
}
