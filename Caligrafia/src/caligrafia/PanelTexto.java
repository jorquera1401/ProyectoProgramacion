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
                    
                case 'p':
                    c.setCurve(this.startx+35,this.starty,
                            this.dx-28,this.dy+28,
                            this.dx+32,this.dy-95,
                            this.dx+4,this.dy+46);
                    g2.draw(c);
                    this.startx = this.dx + 35;
                    this.starty = this.dy;
                    this.dx += 35;
                    
                    break;
                case 'q':
                    c.setCurve(this.startx,this.starty,
                            this.dx+77,this.dy+9,
                            this.dx-41,this.dy-77,
                            this.dx+36,this.dy+51);
                    g2.draw(c);
                    qc.setCurve(this.dx+6,this.dy+27,
                            this.dx+37,this.dy,
                            this.dx+37,this.dy
                            );
                    g2.draw(qc);
                    this.startx=this.dx+37;
                    this.starty=this.dy;
                    this.dx+=37;
                    break;
                case 'r':
                    c.setCurve(this.startx,this.starty,
                            this.dx+34,this.dy-46,
                            this.dx-27,this.dy-2,
                            this.dx+20,this.dy-20);
                    g2.draw(c);
                    c.setCurve(this.dx+20,this.dy-20,
                            this.dx+49,this.dy-13,
                            this.dx+5,this.dy-52,
                            this.dx+32,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+32;
                    this.starty=this.dy;
                    this.dx+=32;
                    break;
                case 'u':
                    c.setCurve(this.startx,this.starty,
                            this.dx+32,this.dy-62,
                            this.dx-24,this.dy+23,
                            this.dx+16,this.dy-7);
                    g2.draw(c);
                    c.setCurve(this.dx+16,this.dy-7,
                            this.dx+36,this.dy-28,
                            this.dx+3,this.dy-35,
                            this.dx+30,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+30;
                    this.starty=this.dy;
                    this.dx+=30;
                    
                    break;
                case 'v':
                    c.setCurve(this.startx,this.starty,
                            this.dx+7,this.dy-78,
                            this.dx+10,this.dy+57,
                            this.dx+20,this.dy-30);
                    g2.draw(c);
                    c.setCurve(this.dx+20,this.dy-30,
                            this.dx+30,this.dy-39,
                            this.dx+32,this.dy+8,
                            this.dx+38,this.dy);
                    
                    g2.draw(c);
                    
                    this.startx=this.dx+38;
                    this.starty=this.dy;
                    this.dx+=38;
                    
                    break;
                case 'w':
                    c.setCurve(this.startx,this.starty,
                            this.dx+1,this.dy-100,
                            this.dx+10,this.dy+63,
                            this.dx+20,this.dy-29);
                    g2.draw(c);
                    c.setCurve(this.dx+20,this.dy-29,
                            this.dx+26,this.dy+62,
                            this.dx+43,this.dy-96,
                            this.dx+50,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+50;
                    this.starty=this.dy;
                    this.dx+=50;
                    break;
                case 'x':
                    qc.setCurve(this.startx,this.starty,this.dx+19,this.dy-9,this.dx+24,this.dy-24);
                    g2.draw(qc);
                    qc.setCurve(this.dx,this.dy-24,this.dx+8,this.dy-10,this.dx+30,this.dy);
                    g2.draw(qc);
                    this.startx=this.dx+30;
                    this.starty=this.dy;
                    this.dx+=30;
                    break;
                case 'y':
                    c.setCurve(this.startx,this.starty,
                            this.dx,this.dy-20,
                            this.dx+15,this.dy+30,
                            this.dx+15,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+15, this.starty, 
                            this.dx+16, this.dy+50, 
                            this.dx-15, this.dy+50, 
                            this.dx+26, this.dy);
                    g2.draw(c);
                    this.startx=this.dx+26;
                    this.starty=this.dy;
                    this.dx+=26;
                    break;
                case 'z':
                    qc.setCurve(this.startx, this.starty, this.dx+23, this.dy-30, this.dx+10, this.dy+6);
                    c.setCurve(this.startx+10,this.starty+6,
                            this.dx+20,this.dy+50,
                            this.dx-10,this.dy+50,
                            this.dx+25,this.dy);
                    g2.draw(c);
                    g2.draw(qc);
                    this.startx=this.dx+25;
                    this.starty=this.dy;
                    this.dx+=25;
                    break;
                case 'X':
                    c.setCurve(this.startx,this.starty-50,
                            this.dx+16,this.dy-50,
                            this.dx+29,this.dy+6,
                            this.dx+50,this.dy);
                     g2.draw(c);
                    c.setCurve(this.startx,this.starty,
                            this.dx+22,this.dy-50,
                            this.dx+39,this.dy-36,
                            this.dx+50,this.dy-50);
                     g2.draw(c);
                     
                    this.startx=this.dx+50;
                    this.starty=this.dy;
                    this.dx+=50;
                    break;
                case 'Y':
                    c.setCurve(this.dx+40,this.dy-50,
                            this.dx+42,this.dy+83,
                            this.dx-48,this.dy+53,
                            this.dx+50,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+50;
                    this.starty=this.dy;
                    this.dx+=50;
                    break;  
                case 'Z':
                    c.setCurve(this.startx,this.starty-44,
                            this.dx+36,this.dy-75,
                            this.dx-43,this.dy-43,
                            this.dx+50,this.dy-50);
                    
                    g2.draw(c);
                    c.setCurve(this.startx,this.starty,
                            this.dx+21,this.dy-22,
                            this.dx+40,this.dy-41,
                            this.dx+50,this.dy-50);
                    g2.draw(c);
                     c.setCurve(this.startx,this.starty,
                            this.dx+13,this.dy-9,
                            this.dx+34,this.dy+3,
                            this.dx+50,this.dy);
                     g2.draw(c);
                     c.setCurve(this.startx+14,this.starty-22,
                            this.dx+20,this.dy-28,
                            this.dx+28,this.dy-19,
                            this.dx+39,this.dy-24);
                     g2.draw(c);
                     this.startx=this.dx+50;
                    this.starty=this.dy;
                    this.dx+=50;
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
