/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;
import javax.swing.JPanel;

/**
 *
 * @author mjorquera
 */
public class PanelTexto extends JPanel{
    
    private String palabra;
    private Letra[] letras;
    private char letra;
    private double alto;
    private double ancho;
    //Dos coordenadas para referenciar el punto de incio de una letra.
    private double startx;
    private double starty;
    //Dos indices para referenciar el punto de incio de una letra pero relativo a la linea inferior.
    private double dx;
    private double dy;
    //Tamanyo de escalado de la letra.
    private int tamanyoLetra;
    
    public PanelTexto(){
        this.palabra = "";
        this.startx = 0;
        this.starty = 10;
        this.dx = 0;
        this.dy = 0;
        this.tamanyoLetra = 10;
    }
    /*
    /   Remueve el panel principal y lo transforma en gridlayout, de tamanaño del 
        largo del panel
    */
    public  void cargarLetras(){
        this.removeAll();
        setLayout(new GridLayout(1, this.palabra.length()));
        
        for(int i=0;i<this.letras.length;i++){
              this.add(this.letras[i]);
        }
        this.validate();        
    }
    
    /**
     * separa cada una de las letras que contiene la palabra ingresada
     * A cada panel de letra se asigna su ancho y alto respectivo
     */
    public void separarLetras(){
        this.letras=new Letra[this.palabra.length()];
        
        ancho = this.getWidth()/this.palabra.length();
        alto  =this.getHeight()/this.palabra.length();
        
        for(int  i=0;i<this.palabra.length();i++){
            
            letra = this.palabra.charAt(i);
            this.letras[i]= new Letra(letra,ancho,alto);
        }
        
        cargarLetras();
    }
   
    @Override
    public void paint(Graphics g){
        g.setColor(Color.red);
 //       g.fillRect(0, 0, 10, 10);
        Graphics2D g2 = (Graphics2D) g;
        
        QuadCurve2D qc = new QuadCurve2D.Float();
        CubicCurve2D c = new CubicCurve2D.Double();
        for(int i = 0; i < this.palabra.length(); i++){
            this.letra = this.palabra.charAt(i);
            
            switch(this.letra){
                case 'a':
                    qc.setCurve(this.dx,this.alto,this.ancho/2,dy,this.ancho,this.alto);
                    g2.draw(qc);
                    break;
                case 'b':
                c.setCurve(this.dx+(this.ancho/15), this.dy, 
                        this.dx, this.alto/4, 
                        this.dx+(2*(this.ancho/15)), this.alto-(this.alto/4), 
                        this.dx+(this.ancho/10), this.alto);
                g2.draw(c);
                c.setCurve(this.dx+(this.ancho/15), this.alto/2, 
                        this.ancho/2, this.alto/4,
                        this.ancho/2, this.alto-(this.alto/4),
                        this.dx+(this.ancho/10), this.alto);
                g2.draw(c);
                break;
            }
        }
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
