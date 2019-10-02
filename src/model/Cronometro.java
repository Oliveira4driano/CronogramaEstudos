/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Dev-2810
 */
public final class Cronometro {
    
    private int codigo;
    private String tempo; 
  
    
    private Timer timer;  
    private int currentSegundo = 0;
    private int currentMinuto = 0;
    private int currentHora = 0;
    private int velocidade = 1000;
   
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getTempo() {
        return tempo;
    }
    
    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public int getCodigo() {
        return codigo;
    }
    
    
    public void iniciarContagem(JLabel label) {
        ActionListener action = new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                currentSegundo++;
                
                if(currentSegundo==60){
                    currentMinuto++;
                    currentSegundo = 0;
                }
                
                if(currentMinuto==60){
                    currentHora++;
                    currentMinuto = 0;
                }
                
                String hr = currentHora <= 9? "0"+currentHora:currentHora+"";
                String min = currentMinuto <= 9? "0"+currentMinuto:currentMinuto+"";
                String seg = currentSegundo <= 9? "0"+currentSegundo:currentSegundo+"";
                
                label.setText(hr+":"+min+":"+seg);  
            }  
        };  
        this.timer = new Timer(velocidade, action);  
        this.timer.start();
    }
    
    public void pausar(){
       this.timer.stop();
    }

    public void zerarTime(JLabel label) {
        this.timer.stop();
        currentHora = 0;
        currentMinuto = 0;
        currentSegundo = 0;
        label.setText("00:00:00");
    }
    
    private long begin, end ;
     
 
    public  void start ( ) { 
        begin =  System.currentTimeMillis ( ) ; 
    }
 
    public  void stop ( ) { 
        end =  System.currentTimeMillis ( ) ; 
    }
 
    public  long getTime ( )  { 
        return end - begin ; 
    }
 
    public  long getMilliseconds ( )  { 
        return end - begin ; 
    }
 
    public  double getSeconds ( ){ 
        return ( end - begin )  /  1000.0 ; 
    }
 
    public double getMinutes ( )  { 
        return  ( end - begin )  /  60000.0 ; 
    }
 
    public double getHours ( )  { 
        return  ( end - begin )  /  3600000.0 ; 
    }



  

   
    
    
    
}
