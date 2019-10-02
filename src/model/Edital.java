/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Date;



/**
 *
 * @author Dev-2810
 */
public class Edital {
    
    private int id;
    private String nome;
    private String cargo;
    private String banca;
    private String dataprova;

    public Edital() {
    }

    public Edital(int id, String nome, String cargo, String banca, String dataprova) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.banca = banca;
        this.dataprova = dataprova;
    }

    public long getId() {
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getBanca() {
        return banca;
    }

    public void setBanca(String banca) {
        this.banca = banca;
    }

    public String getDataprova() {
        return dataprova;
    }

    public void setDataprova(String dataprova) {
        this.dataprova = dataprova;
    }
    
    
    
    
}
