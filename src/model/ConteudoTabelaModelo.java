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
public class ConteudoTabelaModelo extends AbstractTableModel{
    
    private List<Conteudo> conteudos = new ArrayList<Conteudo>();
   
    private int qtdColuna = 5;

    public ConteudoTabelaModelo(List<Conteudo> conteudos) {
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
            case 0:
                return "Codigo";//"Conteudo";
            case 1:
                return "Conteudo";//"Meta Diária";
            case 2:
                return "Estudo Diario";
           case 3:
                return "Data de Estudo";
            case 4:
                return "Revisao";
            case 5:
                return "Status";
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
            case 0:
                return conteudo.getId(); //conteudo.getNome();
            case 1:
                return conteudo.getNome(); //cronometro.getTempo(); 
            case 2:
                return conteudo.getTempo();
            case 3:
                return conteudo.getData();
            case 4:
                return conteudo.getRevisao();
           /* case 5:
                return conteudo.getNome();*/
            
        }
        return null;
    }
    
}
