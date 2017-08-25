/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author mjorquera
 */
public class PanelTexto extends JPanel{
    
    private String palabra;
    private Letra[] letras;
    
    public PanelTexto(){
       
    }
    
    //carga las letras al panel 
    public  void cargarLetras(){
        setLayout(new GridLayout(1, this.palabra.length()));
        for(int i=0;i<this.letras.length;i++){
            this.add(letras[i]);
        }
        System.out.println("Hay "+this.palabra.length()+" eleemntos");
        
    }
    
    /**
     * separa cada una de las letras que contiene la palabra ingresada
     */
    public void separarLetras(){
        this.letras=new Letra[this.palabra.length()];
        for(int  i=0;i<this.palabra.length();i++){
            System.out.println(palabra.charAt(i));
       //     this.letras[i].setLetra(this.palabra.charAt(i));
        }
        
        cargarLetras();
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.red);
        g.drawRect(40, 60, 100,100);
    
    }

    /**
     * @return the palabra
     */
    public String getPalabra() {
        return palabra;
    }

    /**
     * @param palabra the palabra to set
     */
    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    
}
