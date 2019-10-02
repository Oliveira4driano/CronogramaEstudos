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
public class ConteudoTabelaModelo1 extends AbstractTableModel{
    
    private List<Conteudo> conteudos = new ArrayList<>();
   
    private int qtdColuna = 2;

    public ConteudoTabelaModelo1(List<Conteudo> conteudos) {
        this.conteudos = conteudos;
    }
    
    

    @Override
    public int getRowCount() {
        return conteudos.size(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
       return qtdColuna;//To change body of generated methods, choose Tools | Templates.
    }
    public String getColumnName(int coluna){
        switch(coluna){
           /* case 0:
                return "Codigo";//"Conteudo";*/
            case 0:
                return "Conteudo";//"Meta Diária";
            
            case 1:
                return "Materia";
         /*   case 6:
                return "Peso";
            case 7:
                return "Status";*/
            
        }
        return null;
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Conteudo conteudo = conteudos.get(rowIndex);
       // Cronometro cronometro = cronometros.get(rowIndex);
        switch(columnIndex){
          /*  case 0:
                return conteudo.getId(); //conteudo.getNome();*/
            case 0:
                return conteudo.getNome(); //cronometro.getTempo(); 
           
            case 1:
                return conteudo.getMateria().getNome();
            
        }
        return null;
    }
    
}
