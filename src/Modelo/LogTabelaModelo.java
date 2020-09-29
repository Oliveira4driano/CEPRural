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
public class LogTabelaModelo extends AbstractTableModel {
    private List<Log> logs;

    public LogTabelaModelo(List<Log> logs) {
        this.logs = logs;
    }
    
        
    @Override
    public int getRowCount() {
        return logs.size();
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
                return "Usuário";
            case 2:
                return "Data";
            case 3:
                return "Hora";
            case 4:
                return "Descrição";
        }
        return null;
    }
    @Override
    public Object getValueAt(int linha, int coluna) {
        Log log = logs.get(linha);
        switch(coluna){
            case 0:
                return log.getCodigo();
            case 1:
                return log.getUsuario().getLogin();
            case 2:
                return log.getData();
            case 3:
                return log.getHora();
             case 4:
                return log.getDescricao();                
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }
    
}
