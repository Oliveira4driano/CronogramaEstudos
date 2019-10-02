/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Dev-2810
 */
public class Conteudo {
    
    private int id;
    private String nome;
    private String tempo;
    private String data;
    private String revisao;
    private Materia materia;
    
   
     public Conteudo() {
         this.materia = new Materia();
    }

    public Conteudo(int id, String nome, String tempo, String data, String revisao, Materia materia) {
        this.id = id;
        this.nome = nome;
        this.tempo = tempo;
        this.data = data;
        this.revisao = revisao;
        this.materia = materia;
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

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRevisao() {
        return revisao;
    }

    public void setRevisao(String revisao) {
        this.revisao = revisao;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

   
}
