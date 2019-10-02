/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dev-2810
 */
public class CronogramaTabelaModeloDomingo extends AbstractTableModel{
    
    private List<Cronograma> cronogramas = new ArrayList<>();
    private int qtdcoluna = 1;

    public CronogramaTabelaModeloDomingo(List<Cronograma> cronogramas) {
        this.cronogramas = cronogramas;
    }
    
    @Override
    public int getRowCount() {
        return cronogramas.size();//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return qtdcoluna;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public String getColumnName(int coluna){
        
         switch(coluna){
           case 0:
                return "";//"Conteudo";
        
          
            
        }
        return null;

    } 
    
     @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cronograma cronograma = cronogramas.get(rowIndex);
       // Cronometro cronometro = cronometros.get(rowIndex);
        switch(columnIndex){
          /*  case 0:
                return conteudo.getId(); //conteudo.getNome();*/
            case 0:
                return cronograma.getNomemateria(); //cronometro.getTempo(); 
           
           // case 1:
             //   return cronograma.;          
        }
        return null;
    }
    
}
