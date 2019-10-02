/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dev-2810
 */
public class Materia {
    private int id;
    private String nome;
    private String conhecimento;
    private Edital edital;


    
    public Materia() {
        this.edital = new Edital();
    }

    public Materia(int id, String nome, String conhecimento, Edital edital) {
        this.id = id;
        this.nome = nome;
        this.conhecimento = conhecimento;
        this.edital = edital;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConhecimento() {
        return conhecimento;
    }

    public void setConhecimento(String conhecimento) {
        this.conhecimento = conhecimento;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }
   

  
    
    

   

  
}
