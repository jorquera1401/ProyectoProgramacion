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
                    c.setCurve(this.dx+13,this.dy-17,
                            this.dx+13,this.dy-17,
                            this.dx+13,this.dy-17,
                            this.dx+6,this.dy+44);
                    g2.draw(c);
                    c.setCurve(this.startx,this.starty,
                            
                            this.dx+28,this.dy-51,
                            this.dx-1,this.dy+12,
                            
                            this.dx+36,this.dy);
                    g2.draw(c);
                    this.startx = this.dx + 35;
                    this.starty = this.dy;
                    this.dx += 36;
                    
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
                            this.dx+12,this.dy-4,
                            this.dx+14,this.dy-12,
                            this.dx+14,this.dy-23);
                    g2.draw(c);
                    c.setCurve(this.dx+14,this.dy-23,
                            this.dx+18,this.dy-23,
                            this.dx+23,this.dy-23,
                            this.dx+27,this.dy-24);
                    g2.draw(c);
                    c.setCurve(this.dx+27,this.dy-24,
                            this.dx+25,this.dy-15,
                            this.dx+29,this.dy-6,
                            this.dx+37,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+32;
                    this.starty=this.dy;
                    this.dx+=32;
                    break;
                case 's':
                    c.setCurve(this.startx,this.starty,
                            this.dx+10,this.dy-1,
                            this.dx+26,this.dy-12,
                            this.dx+27,this.dy-24);
                    g2.draw(c);
                    c.setCurve(this.dx+27,this.dy-24,
                            this.dx+42,this.dy+9,
                            this.dx+15,this.dy,
                            this.dx+10,this.dy-1);
                    g2.draw(c);
                    c.setCurve(this.dx+10,this.dy-1,
                            this.dx+10,this.dy-1,
                            this.dx+32,this.dy+8,
                            this.dx+38,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+38;
                    this.starty=this.dy;
                    this.dx+=38;
                    break;
                case 't':
                    c.setCurve(this.startx,this.starty,
                            this.dx+10,this.dy-1,
                            this.dx+20,this.dy-18,
                            this.dx+20,this.dy-47);
                    g2.draw(c);
                    c.setCurve(this.dx+20,this.dy-47,
                            this.dx+20,this.dy-18,
                            this.dx+18,this.dy-3,
                            this.dx+37,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+11,this.dy-36,
                            this.dx+11,this.dy-36,
                            this.dx+11,this.dy-36,
                            this.dx+30,this.dy-36);
                    g2.draw(c);
                    this.startx=this.dx+37;
                    this.starty=this.dy;
                    this.dx+=37;
                    break;
                case 'u':
                    c.setCurve(this.startx,this.starty,
                            this.dx+17,this.dy+2,
                            this.dx+16,this.dy-14,
                            this.dx+16,this.dy-25);
                    g2.draw(c);
                    c.setCurve(this.dx+16,this.dy-25,
                            this.dx+13,this.dy+6,
                            this.dx+36,this.dy+10,
                            this.dx+32,this.dy-25);
                    g2.draw(c);
                    c.setCurve(this.dx+32,this.dy-25,
                            this.dx+30,this.dy,
                            this.dx+40,this.dy-1,
                            this.dx+50,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+50;
                    this.starty=this.dy;
                    this.dx+=50;
                    break;
                case 'v':
                    c.setCurve(this.startx,this.starty,
                            this.dx+8,this.dy-29,
                            this.dx+19,this.dy-28,
                            this.dx+18,this.dy-2);
                    g2.draw(c);
                    c.setCurve(this.dx+18,this.dy-2,
                            this.dx+22,this.dy+5,
                            this.dx+36,this.dy+6,
                            this.dx+36,this.dy-23);
                    g2.draw(c);
                    c.setCurve(this.dx+36,this.dy-23,
                            this.dx+36,this.dy-23,
                            this.dx+36,this.dy-23,
                            this.dx+55,this.dy-23);
                    g2.draw(c);
                    this.startx=this.dx+55;
                    this.starty=this.dy;
                    this.dx+=55;
                    break;
                case 'w':
                    c.setCurve(this.startx,this.starty,
                            this.dx+28,this.dy,
                            this.dx+39,this.dy-45,
                            this.dx+27,this.dy-8);
                    g2.draw(c);
                    c.setCurve(this.dx+27,this.dy-8,
                            this.dx+24,this.dy+4,
                            this.dx+45,this.dy+6,
                            this.dx+44,this.dy-22);
                    g2.draw(c);
                    c.setCurve(this.dx+44,this.dy-22,
                            this.dx+36,this.dy+6,
                            this.dx+63,this.dy+9,
                            this.dx+60,this.dy-22);
                    g2.draw(c);
                    c.setCurve(this.dx+60,this.dy-22,
                            this.dx+60,this.dy-22,
                            this.dx+60,this.dy-22,
                            this.dx+70,this.dy-22);
                    g2.draw(c);
                    this.startx=this.dx+70;
                    this.starty=this.dy;
                    this.dx+=70;
                    break;
                case 'x':
                    c.setCurve(this.startx,this.starty,
                            this.dx+22,this.dy-74,
                            this.dx+18,this.dy+16,
                            this.dx+45,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+15,this.dy-2,
                            this.dx+24,this.dy-17,
                            this.dx+24,this.dy-17,
                            this.dx+32,this.dy-28);
                    g2.draw(c);
                    
                    this.startx=this.dx+45;
                    this.starty=this.dy;
                    this.dx+=45;
                    break;
                case 'y':
                    c.setCurve(this.startx,this.starty,
                            this.dx+18,this.dy-75,
                            this.dx+9,this.dy+52,
                            this.dx+33,this.dy-26);
                    g2.draw(c);
                    c.setCurve(this.dx+33, this.dy-26, 
                            this.dx+15, this.dy+119, 
                            this.dx-28, this.dy, 
                            this.dx+51, this.dy);
                    g2.draw(c);
                    this.startx=this.dx+51;
                    this.starty=this.dy;
                    this.dx+=51;
                    break;
                case 'z':
                    c.setCurve(this.startx,this.starty,
                            this.dx+19,this.dy-41,
                            this.dx+62,this.dy-21,
                            this.dx+26,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+33, this.dy-3, 
                            this.dx+15, this.dy+119, 
                            this.dx-28, this.dy, 
                            this.dx+51, this.dy);
                    g2.draw(c);
                    this.startx=this.dx+50;
                    this.starty=this.dy;
                    this.dx+=50;
                    break;
                case 'P':
                    c.setCurve(this.startx+8,this.starty-8,
                            this.dx,this.dy+9,
                            this.dx+38,this.dy-6,
                            this.dx+23,this.dy-37);
                    g2.draw(c);
                    c.setCurve(this.dx+17,this.dy-28,
                            this.dx+3,this.dy-75,
                            this.dx+86,this.dy-32,
                            this.dx+30,this.dy-25);
                    g2.draw(c);
                    this.startx=this.dx+50;
                    this.starty=this.dy;
                    this.dx+=50;
                    break;
                case 'Q':
                    c.setCurve(this.startx+15,this.starty-47,
                            this.dx-36,this.dy+11,
                            this.dx+79,this.dy+31,
                            this.dx+25,this.dy-47);
                    g2.draw(c);
                    c.setCurve(this.startx+22,this.starty-13,
                            this.dx+41,this.dy-16,
                            this.dx+32,this.dy+2,
                            this.dx+50,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+50;
                    this.starty=this.dy;
                    this.dx+=50;
                    break;
                case 'R':
                    c.setCurve(this.startx+4,this.starty-5,
                            this.dx+19,this.dy+23,
                            this.dx+22,this.dy-14,
                            this.dx+20,this.dy-42);
                    g2.draw(c);
                    c.setCurve(this.dx+12,this.dy-31,
                            this.dx-10,this.dy-69,
                            this.dx+90,this.dy-37,
                            this.dx+25,this.dy-17);
                    g2.draw(c);
                    c.setCurve(this.dx+25,this.dy-17,
                            this.dx+24,this.dy-32,
                            this.dx+41,this.dy-24,
                            this.dx+50,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+50;
                    this.starty=this.dy;
                    this.dx+=50;
                    break;
                case 'S':
                    c.setCurve(this.startx,this.starty,
                            this.dx+82,this.dy-45,
                            this.dx-8,this.dy-84,
                            this.dx+45,this.dy-19);
                    g2.draw(c);
                    c.setCurve(this.dx+45,this.dy-19,
                            this.dx+60,this.dy+36,
                            this.dx-47,this.dy-34,
                            this.dx+63,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+63;
                    this.starty=this.dy;
                    this.dx+=63;
                    break;
                case 'T':
                    c.setCurve(this.startx+9,this.starty-38,
                            this.dx-11,this.dy-57,
                            this.dx+30,this.dy-43,
                            this.dx+45,this.dy-38);
                    g2.draw(c);
                    c.setCurve(this.dx+45,this.dy-38,
                            this.dx+58,this.dy-77,
                            this.dx-35,this.dy+27,
                            this.dx+53,this.dy+1);
                    g2.draw(c);
                    
                    c.setCurve(this.dx+10,this.dy-30,
                            this.dx+36,this.dy-30,
                            this.dx+36,this.dy-30,
                            this.dx+36,this.dy-30);
                    g2.draw(c);
                    
                     this.startx=this.dx+53;
                    this.starty=this.dy;
                    this.dx+=53;
                    break;
                case 'U':
                    c.setCurve(this.startx,this.starty-50,
                            this.dx-2,this.dy+9,
                            this.dx+39,this.dy+25,
                            this.dx+42,this.dy-50);
                    g2.draw(c);
                    c.setCurve(this.dx+42,this.dy-50,
                            this.dx+33,this.dy-1,
                            this.dx+40,this.dy+3,
                            this.dx+60,this.dy);
                     g2.draw(c);
                     this.startx=this.dx+60;
                    this.starty=this.dy;
                    this.dx+=60;
                     
                     break;
                case 'V':
                    c.setCurve(this.startx,this.starty-44,
                            this.dx+13,this.dy-56,
                            this.dx+19,this.dy-46,
                            this.dx+13,this.dy-11);
                    g2.draw(c);
                    c.setCurve(this.dx+13,this.dy-11,
                            this.dx+18,this.dy+12,
                            this.dx+50,this.dy+3,
                            this.dx+45,this.dy-50);
                     g2.draw(c);
                    c.setCurve(this.dx+45,this.dy-50,
                            this.dx+45,this.dy-50,
                            this.dx+45,this.dy-50,
                            this.dx+55,this.dy-50);
                     g2.draw(c);
                    this.startx=this.dx+55;
                    this.starty=this.dy;
                    this.dx+=55;
                     
                     break;
                case 'W':
                    c.setCurve(this.startx,this.starty-47,
                            this.dx+16,this.dy-70,
                            this.dx+15,this.dy+75,
                            this.dx+32,this.dy-50);
                     g2.draw(c);
                    c.setCurve(this.dx+32,this.dy-50,
                            this.dx+15,this.dy+21,
                            this.dx+69,this.dy+13,
                            this.dx+60,this.dy-50);
                    g2.draw(c);
                    c.setCurve(this.dx+60,this.dy-50,
                            this.dx+60,this.dy-50,
                            this.dx+60,this.dy-50,
                            this.dx+70,this.dy-50);
                    g2.draw(c);
                    
                    this.startx=this.dx+70;
                    this.starty=this.dy;
                    this.dx+=70;
                    break;
                case 'X':
                    c.setCurve(this.startx,this.starty-50,
                            this.dx+16,this.dy-50,
                            this.dx+29,this.dy+6,
                            this.dx+50,this.dy);
                     g2.draw(c);
                    c.setCurve(this.dx,this.dy,
                            this.dx+22,this.dy-50,
                            this.dx+39,this.dy-36,
                            this.dx+50,this.dy-50);
                     g2.draw(c);
                     
                    this.startx=this.dx+50;
                    this.starty=this.dy;
                    this.dx+=50;
                    break;
                case 'Y':
                    c.setCurve(this.startx,this.starty-44,
                            this.dx+25,this.dy-74,
                            this.dx+10,this.dy+63,
                            this.dx+47,this.dy-39);
                    g2.draw(c);
                    c.setCurve(this.dx+47,this.dy-39,
                            this.dx+30,this.dy+135,
                            this.dx-33,this.dy-7,
                            this.dx+74,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+74;
                    this.starty=this.dy;
                    this.dx+=74;
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
             ////////////////////////7
                case '?':
                    c.setCurve(this.startx+10,this.starty-50,
                            this.dx+44,this.dy-57,
                            this.dx+41,this.dy-16,
                            this.dx+13,this.dy-22);
                    g2.draw(c);
                    c.setCurve(this.dx+13,this.dy-22,
                            this.dx+13,this.dy-22,
                            this.dx+13,this.dy-22,
                            this.dx+13,this.dy-10);
                    g2.draw(c);
                    c.setCurve(this.dx+13,this.dy,
                            this.dx+11,this.dy,
                            this.dx+14,this.dy,
                            this.dx+12,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+30;
                    this.starty=this.dy;
                    this.dx+=30;
                    break;
                case '¿':
                    c.setCurve(this.startx+30,this.starty-50,
                            this.dx-4,this.dy-58,
                            this.dx,this.dy-8,
                            this.dx+30,this.dy-22);
                    g2.draw(c);
                    c.setCurve(this.dx+30,this.dy-22,
                            this.dx+30,this.dy-22,
                            this.dx+30,this.dy-22,
                            this.dx+30,this.dy-10);
                    g2.draw(c);
                    c.setCurve(this.dx+30,this.dy,
                            this.dx+29,this.dy,
                            this.dx+32,this.dy,
                            this.dx+31,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+50;
                    this.starty=this.dy;
                    this.dx+=50;
                    break;
                case '!':
                    c.setCurve(this.startx+10,this.starty-50,
                            this.dx+10,this.dy-50,
                            this.dx+10,this.dy-50,
                            this.dx+10,this.dy-15);
                    g2.draw(c);
                    c.setCurve(this.dx+10,this.dy-3,
                            this.dx+10,this.dy-2,
                            this.dx+10,this.dy-4,
                            this.dx+10,this.dy-3);
                    g2.draw(c);
                    break;
                    
                case '¡':
                    c.setCurve(this.startx+10,this.starty-35,
                            this.dx+10,this.dy-35,
                            this.dx+10,this.dy-35,
                            this.dx+10,this.dy-0);
                    g2.draw(c);
                    c.setCurve(this.dx+10,this.dy-47,
                            this.dx+10,this.dy-48,
                            this.dx+10,this.dy-49,
                            this.dx+10,this.dy-47);
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
