/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    //private double alto;
    //private double ancho;
    //Dos coordenadas para referenciar el punto de incio de una letra.
    private double startx;
    private double starty;
    //Dos indices para referenciar el punto de incio de una letra pero relativo a la linea inferior.
    private double dx;
    private double dy;
    private Coordenada coordenadas;
    //Tamanyo de escalado de la letra.
    private int tamanyoLetra;
    
    public PanelTexto(){
        this.palabra = "";
        this.startx = 0;
        this.starty = 300;
        this.dx = 0;
        this.dy = 300;
       // this.coordenadas = new Coordenada(0, 300, 0, 300);
        this.tamanyoLetra = 50;
    }
    
    
//    public  void cargarLetras(){
//        CubicCurve2D c = new CubicCurve2D.Double();
//        c.setCurve(this.startx, this.starty, 
//                            this.dx+20, this.dy-50, 
//                            this.dx-10, this.dy-50, 
//                            this.dx+10, this.dy);
//    }
    
    /**
     * separa cada una de las letras que contiene la palabra ingresada
     * A cada panel de letra se asigna su ancho y alto respectivo
     
    public void separarLetras(){
        this.letras=new Letra[this.palabra.length()];
        
        ancho = this.getWidth()/this.palabra.length();
        alto  =this.getHeight()/this.palabra.length();
        
        for(int  i=0;i<this.palabra.length();i++){
            
            letra = this.palabra.charAt(i);
            this.letras[i]= new Letra(letra,ancho,alto);
        }
        
        cargarLetras();
    }*/
   
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.red);
 //       g.fillRect(0, 0, 10, 10);
        BasicStroke stroke= new BasicStroke(3f);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(stroke);
        
        QuadCurve2D qc = new QuadCurve2D.Float();
        CubicCurve2D c = new CubicCurve2D.Double();
        for(int i = 0; i < this.palabra.length(); i++){
            this.letra = this.palabra.charAt(i);
            
            switch(this.letra){
                case 'a':
                    //qc.setCurve(this.dx,this.alto,this.ancho/2,dy,this.ancho,this.alto);
                    
                    g2.draw(qc);
                    break;
                case 'b':
                    c.setCurve(this.startx, this.starty, 
                            this.dx+20, this.dy-50, 
                            this.dx-10, this.dy-50, 
                            this.dx+10, this.dy);
                    g2.draw(c);
                    qc.setCurve(this.dx+10, this.dy, 
                            this.dx+15, this.dy,
                            this.dx+15, this.dy-15);
                    g2.draw(qc);
                    qc.setCurve(this.dx+15, this.dy-15, 
                            this.dx+20, this.dy-10,
                            this.dx+25, this.dy-15);
                    g2.draw(qc);
                    this.startx = this.dx + 25;
                    this.starty = this.dy - 15;
                    this.dx += 25;                            
                    break;
                case 'f':
                    //c.setCurve(this.startx, this.starty, this.dx+10, this.dy-50, this.ancho/5, this.alto/10, this.dx, this.dy);
                    c.setCurve(this.startx, this.starty, 
                            this.dx+20, this.dy-50, 
                            this.dx-10, this.dy-50, 
                            this.dx+10, this.dy);
                    g2.draw(c);                   
                    //c.setCurve(this.dx, this.alto/2, this.ancho/4, this.alto/3, this.ancho/4, this.alto/3, this.dx+(this.ancho/4), this.alto/2);
                    g2.draw(c);
                    break;
                case 'l':
                    c.setCurve(this.startx, this.starty, 
                            this.dx+20, this.dy-50, 
                            this.dx-10, this.dy-50, 
                            this.dx+10, this.dy);
                    g2.draw(c);
                    this.startx = this.dx + 10;
                    this.starty = this.dy;
                    this.dx += 10;
                    break;
                    
                case 'z':
                    c.setCurve(this.startx,this.starty,
                            this.dx+20,this.dy+50,
                            this.dx-10,this.dy+50,
                            this.dx+10,this.dy);
                    qc.setCurve(this.startx, this.starty, this.dx+20, this.dy-20, this.dx+23, this.dy);
                    g2.draw(c);
                    g2.draw(qc);
                    this.startx=this.dx+10;
                    this.starty=this.dy;
                    this.dx+=10;
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

    public double getStartx() {
        return startx;
    }
        
    public void setStartx(double startx) {
        this.startx = startx;
    }

    public double getStarty() {
        return starty;
    }

    public void setStarty(double starty) {
        this.starty = starty;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getTamanyoLetra() {
        return tamanyoLetra;
    }

    public void setTamanyoLetra(int tamanyoLetra) {
        this.tamanyoLetra = tamanyoLetra;
    }
    
    
}
