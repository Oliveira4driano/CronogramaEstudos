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
public class MateriaTabelaModelo extends AbstractTableModel{
    private List<Materia> materias = new ArrayList<Materia>();
    private int qtdcoluna =3;

    public MateriaTabelaModelo(List<Materia> materias) {
        this.materias = materias;
    }
    
    @Override
    public int getRowCount() {
        return materias.size();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return qtdcoluna; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Materia materia = materias.get(rowIndex);
        
        switch(columnIndex){
          //  case 0:
              //  return materia.getId();
            case 0: 
                return materia.getNome();
            case 1:
                return materia.getConhecimento();
            case 2:
                return materia.getEdital().getNome();
        }
        return null;
    }
    
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: 
                return "Matéria";
            case 1:
                return "Conhecimentos";
            case 2:
                return "Edital";
         /*   case 3:
              return "Meta semanal";
            case 4:
                return "Qtd Questões";
          */
        }
        return null;
        
    }
    
}
