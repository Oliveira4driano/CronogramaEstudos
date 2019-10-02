/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

/**
 *
 * @author Dev-2810
 */
public class Calendario {
    
    private Calendar dtinico;
    private Calendar dtprova;

    public Calendario() {
    }

    public Calendario(Calendar dtinico, Calendar dtprova) {
        this.dtinico = dtinico;
        this.dtprova = dtprova;
    }

    public Calendar getDtinico() {
        return dtinico;
    }

    public void setDtinico(Calendar dtinico) {
        this.dtinico = dtinico;
    }

    public Calendar getDtprova() {
        return dtprova;
    }

    public void setDtprova(Calendar dtprova) {
        this.dtprova = dtprova;
    }




    
}
