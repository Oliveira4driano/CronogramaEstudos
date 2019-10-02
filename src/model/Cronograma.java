/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Dev-2810
 */
public class Cronograma {
    
    private int codigo; 
    private String nomemateria;
    private String diaSemana;
    private String semana;
    private String estudoDiario;
    private Materia materia;
    
   
  

    public Cronograma() {
    }

    public Cronograma(int codigo, String nomemateria, String diaSemana, String semana, String estudoDiario, Materia materia) {
        this.codigo = codigo;
        this.nomemateria = nomemateria;
        this.diaSemana = diaSemana;
        this.semana = semana;
        this.estudoDiario = estudoDiario;
        this.materia = materia;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomemateria() {
        return nomemateria;
    }

    public void setNomemateria(String nomemateria) {
        this.nomemateria = nomemateria;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public String getEstudoDiario() {
        return estudoDiario;
    }

    public void setEstudoDiario(String estudoDiario) {
        this.estudoDiario = estudoDiario;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    
    
}
