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
public class EditalTabelaModelo extends AbstractTableModel {
    
    private List<Edital> editais = new ArrayList<Edital>();
    private int qtdColuna = 4;

    public EditalTabelaModelo(List<Edital>  editais) {
        this.editais = editais;
    }
      

    @Override
    public int getRowCount() {
        return editais.size();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return qtdColuna;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getColumnName(int coluna){
        switch(coluna){
            case 0:
                return "Nome";//"Conteudo";
            case 1:
                return "Cargo";//"Meta Diária";
            case 2:
                return "Banca";
           case 3:
                return "Data da Prova";           
        }
        return null;
        
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Edital edital = editais.get(linha);
        
        switch(coluna){
            case 0:
                return edital.getNome();
            case 1:
                return edital.getCargo();
            case 2:
                return edital.getBanca();
            case 3:
                return edital.getDataprova();
               
        }
        return null;
        
    }
    
}
