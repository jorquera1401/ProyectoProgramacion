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
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author mjorquera
 */
public class PanelTexto extends JPanel{
    
    private String palabra;
    private ArrayList<Letra> letras;
    private char letra;
    //private double alto;
    //private double ancho;
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
        this.letras = new ArrayList<>();
        this.startx = 0;
        this.starty = 300;
        this.dx = 0;
        this.dy = 300;
       // this.coordenadas = new Coordenada(0, 300, 0, 300);
        this.tamanyoLetra = 50;
    }
    
    //Sin implementar
//    public  void cargarLetras(){
//        ArrayList<Double> al = new ArrayList<>();
//        HashMap<Integer, ArrayList> coordenadas = new HashMap<>();
//        
//        al.add(0, 0.0);
//        al.add(1, 0.0);
//        al.add(2, 2.5);
//        al.add(3, 0.0);
//        al.add(4, 2.5);
//        al.add(5, 0.0);
//        al.add(6, 5.0);
//        al.add(7, -12.5);
//        coordenadas.put(1, al);
//        
//        al.add(0, 15.0);
//        al.add(1, -23.9);
//        al.add(2, 0.0);
//        al.add(3, 20.0);
//        al.add(4, 0.0);
//        al.add(5, -5.0);
//        al.add(6, 15.9);
//        al.add(7, 0.0);
//        coordenadas.put(1, al);
//        
//        al.add(0, 15.0);
//        al.add(1, -23.9);
//        al.add(2, 0.0);
//        al.add(3, 20.0);
//        al.add(4, 0.0);
//        al.add(5, -5.0);
//        al.add(6, 15.9);
//        al.add(7, 0.0);
//        coordenadas.put(1, al);
//        //coord
//        Letra l = new Letra('a', coordenadas);
//        this.letras.add(l);
//    }
    
   
    @Override
    public void paint(Graphics g){
        g.setColor(Color.red);
 //       g.fillRect(0, 0, 10, 10);
        Graphics2D g2 = (Graphics2D) g;
        
        QuadCurve2D qc = new QuadCurve2D.Float();
        CubicCurve2D c = new CubicCurve2D.Double();
        
        BasicStroke stroke = new BasicStroke(2.0f);
        g2.setStroke(stroke);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //Point2D p = new Point2D.Double();
        
        for(int i = 0; i < this.palabra.length(); i++){
            this.letra = this.palabra.charAt(i);
            
            switch(this.letra){
                case '.':
                    if(((i+1) < this.palabra.length() && this.palabra.charAt(i+1) != '.' ) || (i+1) >= this.palabra.length()){
                        c.setCurve(this.dx+4, this.dy, 
                            this.dx+4, this.dy, 
                            this.dx+4, this.dy, 
                            this.dx+4,this.dy);                    
                        g2.draw(c);
                        this.startx = this.dx + 8;
                        this.starty = this.dy;
                        this.dx += 8;
                    }
                    else if((i+2) < this.palabra.length() && this.palabra.charAt(i+1) == '.' && this.palabra.charAt(i+2) != '.'){
                        this.startx = this.dx + 12;
                        this.starty = this.dy;
                        this.dx += 12;
                        i++;
                    }
                    else if((i+2) < this.palabra.length() && this.palabra.charAt(i+1) == '.' && this.palabra.charAt(i+2) == '.'){
                        c.setCurve(this.dx+4, this.dy, 
                            this.dx+4, this.dy, 
                            this.dx+4, this.dy, 
                            this.dx+4,this.dy);                    
                        g2.draw(c);
                        c.setCurve(this.dx+8, this.dy, 
                            this.dx+8, this.dy, 
                            this.dx+8, this.dy, 
                            this.dx+8,this.dy);                    
                        g2.draw(c);
                        c.setCurve(this.dx+12, this.dy, 
                            this.dx+12, this.dy, 
                            this.dx+12, this.dy, 
                            this.dx+12,this.dy);                    
                        g2.draw(c);
                        this.startx = this.dx + 16;
                        this.starty = this.dy;
                        this.dx += 16;
                        i+=2;
                    }
                    break;
                case ',':
                    c.setCurve(this.dx+4, this.dy, 
                            this.dx+4, this.dy, 
                            this.dx+4, this.dy, 
                            this.dx+4,this.dy);                    
                    g2.draw(c);
                    c.setCurve(this.dx+4, this.dy, 
                            this.dx+3, this.dy+1, 
                            this.dx+3, this.dy+2, 
                            this.dx+2,this.dy+3);                    
                    g2.draw(c);
                    this.startx = this.dx + 8;
                    this.starty = this.dy;
                    this.dx += 8;
                    break;
                case ';':
                    c.setCurve(this.dx+4, this.dy-35, 
                        this.dx+4, this.dy-35, 
                        this.dx+4, this.dy-35, 
                        this.dx+4,this.dy-35);                    
                    g2.draw(c);
                    c.setCurve(this.dx+4, this.dy, 
                            this.dx+4, this.dy, 
                            this.dx+4, this.dy, 
                            this.dx+4,this.dy);                    
                    g2.draw(c);
                    c.setCurve(this.dx+4, this.dy, 
                            this.dx+3, this.dy+1, 
                            this.dx+3, this.dy+2, 
                            this.dx+2,this.dy+3);                    
                    g2.draw(c);
                    this.startx = this.dx + 8;
                    this.starty = this.dy;
                    this.dx += 8;
                    break;
                case ':':
                    c.setCurve(this.dx+4, this.dy-35, 
                        this.dx+4, this.dy-35, 
                        this.dx+4, this.dy-35, 
                        this.dx+4,this.dy-35);                    
                    g2.draw(c);
                    c.setCurve(this.dx+4, this.dy, 
                            this.dx+4, this.dy, 
                            this.dx+4, this.dy, 
                            this.dx+4,this.dy);                    
                    g2.draw(c);
                    this.startx = this.dx + 8;
                    this.starty = this.dy;
                    this.dx += 8;
                    break;
                case '"':
                    c.setCurve(this.dx+4, this.dy-35, 
                            this.dx+4, this.dy-35, 
                            this.dx+4, this.dy-35, 
                            this.dx+4,this.dy-35);                    
                    g2.draw(c);
                    c.setCurve(this.dx+4, this.dy-35, 
                            this.dx+4, this.dy-34, 
                            this.dx+4, this.dy-33, 
                            this.dx+4,this.dy-32);                    
                    g2.draw(c);
                    c.setCurve(this.dx+8, this.dy-35, 
                            this.dx+8, this.dy-35, 
                            this.dx+8, this.dy-35, 
                            this.dx+8,this.dy-35);                    
                    g2.draw(c);
                    c.setCurve(this.dx+8, this.dy-35, 
                            this.dx+8, this.dy-34, 
                            this.dx+8, this.dy-33, 
                            this.dx+8,this.dy-32);                    
                    g2.draw(c);
                    this.startx = this.dx + 12;
                    this.starty = this.dy;
                    this.dx += 12;
                    break;
                case '´':
                    c.setCurve(this.dx+4, this.dy-35, 
                            this.dx+4, this.dy-35, 
                            this.dx+4, this.dy-35, 
                            this.dx+4,this.dy-35);                    
                    g2.draw(c);
                    c.setCurve(this.dx+4, this.dy-35, 
                            this.dx+3, this.dy-34, 
                            this.dx+3, this.dy-33, 
                            this.dx+2,this.dy-32);                    
                    g2.draw(c);
                    this.startx = this.dx + 8;
                    this.starty = this.dy;
                    this.dx += 8;
                    break;
                case '?':
                    c.setCurve(this.dx+10,this.dy-40,
                            this.dx+44,this.dy-47,
                            this.dx+41,this.dy-6,
                            this.dx+13,this.dy-12);
                    g2.draw(c);
                    c.setCurve(this.dx+13,this.dy-12,
                            this.dx+13,this.dy-12,
                            this.dx+13,this.dy-12,
                            this.dx+13,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+13,this.dy+10,
                            this.dx+11,this.dy+10,
                            this.dx+14,this.dy+10,
                            this.dx+12,this.dy+10);
                    g2.draw(c);
                    this.startx=this.dx+30;
                    this.starty=this.dy;
                    this.dx+=30;
                    break;
                case '¿':
                    c.setCurve(this.dx+30,this.dy+10,
                            this.dx-4,this.dy+8,
                            this.dx,this.dy-12,
                            this.dx+30,this.dy-26);
                    g2.draw(c);
                    c.setCurve(this.dx+30,this.dy-38,
                            this.dx+30,this.dy-38,
                            this.dx+30,this.dy-38,
                            this.dx+30,this.dy-26);
                    g2.draw(c);
                    c.setCurve(this.dx+30,this.dy-47,
                            this.dx+29,this.dy-47,
                            this.dx+32,this.dy-47,
                            this.dx+31,this.dy-47);
                    g2.draw(c);
                    this.startx=this.dx+50;
                    this.starty=this.dy;
                    this.dx+=50;
                    break;
                case '!':
                    c.setCurve(this.dx+10,this.dy-50,
                            this.dx+10,this.dy-50,
                            this.dx+10,this.dy-50,
                            this.dx+10,this.dy-15);
                    g2.draw(c);
                    c.setCurve(this.dx+10,this.dy-3,
                            this.dx+10,this.dy-2,
                            this.dx+10,this.dy-4,
                            this.dx+10,this.dy-3);
                    g2.draw(c);
                    this.startx=this.dx+16;
                    this.starty=this.dy;
                    this.dx+=16;
                    break;
                    
                case '¡':
                    c.setCurve(this.dx+10,this.dy-35,
                            this.dx+10,this.dy-35,
                            this.dx+10,this.dy-35,
                            this.dx+10,this.dy-0);
                    g2.draw(c);
                    c.setCurve(this.dx+10,this.dy-47,
                            this.dx+10,this.dy-48,
                            this.dx+10,this.dy-49,
                            this.dx+10,this.dy-47);
                    g2.draw(c);
                    this.startx=this.dx+16;
                    this.starty=this.dy;
                    this.dx+=16;
                    break;
                    
                case '(':
                    c.setCurve(this.dx+24,this.dy-40,
                            this.dx+5,this.dy-30,
                            this.dx+5,this.dy-10,
                            this.dx+24,this.dy-0);
                    g2.draw(c);
                    this.startx=this.dx+35;
                    this.starty=this.dy;
                    this.dx+=35;
                    break;
                    
                case ')':
                    c.setCurve(this.dx+7,this.dy-40,
                            this.dx+25,this.dy-30,
                            this.dx+25,this.dy-10,
                            this.dx+7,this.dy-0);
                    g2.draw(c);
                    this.startx=this.dx+30;
                    this.starty=this.dy;
                    this.dx+=30;
                    break;
                case '-':
                    c.setCurve(this.dx+5,this.dy-20,
                            this.dx+5,this.dy-20,
                            this.dx+5,this.dy-20,
                            this.dx+20,this.dy-20);
                    g2.draw(c);
                    this.startx=this.dx+25;
                    this.starty=this.dy;
                    this.dx+=25;
                    break;
                case '_':
                    c.setCurve(this.dx+5,this.dy,
                            this.dx+5,this.dy,
                            this.dx+5,this.dy,
                            this.dx+30,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+35;
                    this.starty=this.dy;
                    this.dx+=35;
                    break;
                case '[':
                    c.setCurve(this.dx+5,this.dy-50,
                            this.dx+5,this.dy-50,
                            this.dx+5,this.dy-50,
                            this.dx+20,this.dy-50);
                    g2.draw(c);
                    
                    c.setCurve(this.startx+5,this.dy,
                            this.dx+5,this.dy,
                            this.dx+5,this.dy,
                            this.dx+20,this.dy);
                    g2.draw(c);
                    c.setCurve(this.startx+5,this.dy,
                            this.dx+5,this.dy,
                            this.dx+5,this.dy,
                            this.dx+5,this.dy-50);
                    g2.draw(c);
                    this.startx=this.dx+25;
                    this.starty=this.dy;
                    this.dx+=25;
                    break;
                case ']':
                    
                    c.setCurve(this.dx+5,this.dy-50,
                            this.dx+5,this.dy-50,
                            this.dx+5,this.dy-50,
                            this.dx+20,this.dy-50);
                    g2.draw(c);
                    
                    c.setCurve(this.startx+5,this.dy,
                            this.dx+5,this.dy,
                            this.dx+5,this.dy,
                            this.dx+20,this.dy);
                    g2.draw(c);
                    c.setCurve(this.startx+20,this.dy,
                            this.dx+20,this.dy,
                            this.dx+20,this.dy,
                            this.dx+20,this.dy-50);
                    g2.draw(c);
                    this.startx=this.dx+25;
                    this.starty=this.dy;
                    this.dx+=25;
                    break;
                    
                case '{':
                    c.setCurve(this.dx+5,this.dy,
                            this.dx+25,this.dy-25,
                            this.dx+5,this.dy-50,
                            this.dx+25,this.dy-50);
                    g2.draw(c);
                    c.setCurve(this.startx+5,this.dy,
                            this.dx+25,this.dy+25,
                            this.dx+5,this.dy+50,
                            this.dx+25,this.dy+50);
                    g2.draw(c);
                    this.startx=this.dx+30;
                    this.starty=this.dy;
                    this.dx+=30;
                    break;
                case '}':
                    c.setCurve(this.dx+25,this.dy,
                            this.dx+5,this.dy-25,
                            this.dx+25,this.dy-50,
                            this.dx+5,this.dy-50);
                    g2.draw(c);
                    c.setCurve(this.startx+25,this.dy,
                            this.dx+5,this.dy+25,
                            this.dx+25,this.dy+50,
                            this.dx+5,this.dy+50);
                    g2.draw(c);
                    this.startx=this.dx+30;
                    this.starty=this.dy;
                    this.dx+=30;
                    break;
                case '>':
                    c.setCurve(this.dx+25,this.dy,
                            this.dx+25,this.dy,
                            this.dx+5,this.dy-25,
                            this.dx+5,this.dy-25);
                    g2.draw(c);
                    c.setCurve(this.dx+25,this.dy,
                            this.dx+25,this.dy,
                            this.dx+5,this.dy+25,
                            this.dx+5,this.dy+25);
                    g2.draw(c);
                    c.setCurve(this.dx+35,this.dy,
                            this.dx+35,this.dy,
                            this.dx+15,this.dy-25,
                            this.dx+15,this.dy-25);
                    g2.draw(c);
                    c.setCurve(this.dx+35,this.dy,
                            this.dx+35,this.dy,
                            this.dx+15,this.dy+25,
                            this.dx+15,this.dy+25);
                    g2.draw(c);
                    this.startx=this.dx+40;
                    this.starty=this.dy;
                    this.dx+=40;
                    break;
                case '<':
                    c.setCurve(this.startx+5,this.starty,
                            this.dx+5,this.dy,
                            this.dx+25,this.dy-25,
                            this.dx+25,this.dy-25);
                    g2.draw(c);
                    c.setCurve(this.startx+5,this.dy,
                            this.dx+5,this.dy,
                            this.dx+25,this.dy+25,
                            this.dx+25,this.dy+25);
                    g2.draw(c);
                    c.setCurve(this.startx+15,this.starty,
                            this.dx+15,this.dy,
                            this.dx+35,this.dy-25,
                            this.dx+35,this.dy-25);
                    g2.draw(c);
                    c.setCurve(this.startx+15,this.dy,
                            this.dx+15,this.dy,
                            this.dx+35,this.dy+25,
                            this.dx+35,this.dy+25);
                    g2.draw(c);
                    this.startx=this.dx+40;
                    this.starty=this.dy;
                    this.dx+=40;
                    break;
                case ' ':
                    this.dx += 30;
                    this.startx = this.dx;
                    this.starty = this.dy;
                    break;
                case 'a':
                    c.setCurve(this.startx, this.starty, 
                            this.dx+3, this.dy, 
                            this.dx+3, this.dy, 
                            this.dx+6,this.dy-7);                    
                    g2.draw(c);
                    c.setCurve(this.dx+15, this.dy-15, 
                            this.dx+3, this.dy-12, 
                            this.dx+3, this.dy-3, 
                            this.dx+15,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+15, this.dy-15, 
                            this.dx+23, this.dy-12, 
                            this.dx+23, this.dy-3, 
                            this.dx+15,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+20, this.dy-12.5, 
                            this.dx+23, this.dy-7, 
                            this.dx+23, this.dy-2, 
                            this.dx+30,this.dy);
                    g2.draw(c);
                    this.startx = this.dx + 30;
                    this.starty = this.dy;
                    this.dx += 30;
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
                case 'c':
                    c.setCurve(this.startx, this.starty, 
                            this.dx+2.5, this.dy, 
                            this.dx+2.5, this.dy, 
                            this.dx+9,this.dy-7);                    
                    g2.draw(c);
                    c.setCurve(this.dx+20, this.dy-15, 
                            this.dx+5, this.dy-10, 
                            this.dx+5, this.dy-5, 
                            this.dx+20,this.dy);
                    g2.draw(c);
                    this.startx = this.dx + 20;
                    this.starty = this.dy;
                    this.dx += 20;  
                    break;
                case 'd':
                     c.setCurve(this.startx, this.starty, 
                            this.dx+5, this.dy, 
                            this.dx+5, this.dy, 
                            this.dx+8,this.dy-5);                    
                    g2.draw(c);
                    c.setCurve(this.dx+20, this.dy-15, 
                            this.dx+5, this.dy-10, 
                            this.dx+5, this.dy+5, 
                            this.dx+20,this.dy-5);
                    g2.draw(c);
                    c.setCurve(this.dx+20, this.dy-39, 
                            this.dx+20, this.dy-5, 
                            this.dx+20, this.dy-2, 
                            this.dx+30,this.dy);
                    g2.draw(c);
                    this.startx = this.dx + 30;
                    this.starty = this.dy;
                    this.dx += 30;
                    break;
                case 'e':
                    c.setCurve(this.startx, this.starty, 
                            this.dx+5, this.dy, 
                            this.dx+15, this.dy, 
                            this.dx+25,this.dy-14);
                    g2.draw(c);
                    c.setCurve(this.dx+25, this.dy-14, 
                            this.dx+5, this.dy-20, 
                            this.dx+5, this.dy, 
                            this.dx+25,this.dy);
                    g2.draw(c);
                    this.startx = this.dx + 25;
                    this.starty = this.dy;
                    this.dx += 25;
                    break;
                case 'f':
                    c.setCurve(this.startx, this.starty, 
                            this.dx+12, this.dy-20, 
                            this.dx+18, this.dy-45, 
                            this.dx+7, this.dy-35);
                    g2.draw(c);
                    c.setCurve(this.dx+7, this.dy-35, 
                            this.dx+2, this.dy-20, 
                            this.dx+3, this.dy+10, 
                            this.dx+3, this.dy+17);
                    g2.draw(c);
                    c.setCurve(this.dx+3, this.dy+17, 
                            this.dx+10, this.dy+13, 
                            this.dx+10, this.dy, 
                            this.dx+3, this.dy);
                    g2.draw(c); 
                    c.setCurve(this.dx+3, this.dy, 
                            this.dx+5, this.dy, 
                            this.dx+5, this.dy, 
                            this.dx+12, this.dy-3);
                    g2.draw(c);
                    this.startx = this.dx + 12;
                    this.starty = this.dy - 3;
                    this.dx += 12;
                    break;
                case 'g':
                    c.setCurve(this.startx, this.starty, 
                            this.dx+3, this.dy, 
                            this.dx+3, this.dy, 
                            this.dx+6,this.dy-7);                    
                    g2.draw(c);
                    c.setCurve(this.dx+15, this.dy-15, 
                            this.dx+3, this.dy-12, 
                            this.dx+3, this.dy-3, 
                            this.dx+15,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+15, this.dy-15, 
                            this.dx+23, this.dy-12, 
                            this.dx+23, this.dy-3, 
                            this.dx+15,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+20, this.dy-12.5, 
                            this.dx+30, this.dy+30, 
                            this.dx-5, this.dy+30, 
                            this.dx+30,this.dy-5);
                    g2.draw(c);
                    this.startx = this.dx + 30;
                    this.starty = this.dy - 5;
                    this.dx += 30;
                    break;
                case 'h':
                    c.setCurve(this.startx, this.starty, 
                            this.dx+12, this.dy-20, 
                            this.dx+18, this.dy-45, 
                            this.dx+7, this.dy-35);
                    g2.draw(c);
                    c.setCurve(this.dx+7, this.dy-35, 
                            this.dx+5, this.dy-20, 
                            this.dx+6, this.dy+10, 
                            this.dx+6, this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+7, this.dy, 
                            this.dx+15, this.dy-30, 
                            this.dx+18, this.dy+10, 
                            this.dx+22, this.dy-3);
                    g2.draw(c);
                    this.startx = this.dx + 22;
                    this.starty = this.dy - 3;
                    this.dx += 22;
                    break;
                case 'i':
                    c.setCurve(this.startx, this.starty, 
                            this.dx+10, this.dy-2, 
                            this.dx+13, this.dy-7, 
                            this.dx+15,this.dy-15);                    
                    g2.draw(c);
                    c.setCurve(this.dx+30, this.dy, 
                            this.dx+25, this.dy-2, 
                            this.dx+17, this.dy-7, 
                            this.dx+15,this.dy-15);                    
                    g2.draw(c);
                    c.setCurve(this.dx+15, this.dy-22, 
                            this.dx+15, this.dy-22, 
                            this.dx+15, this.dy-22, 
                            this.dx+15,this.dy-22);                    
                    g2.draw(c);
                    this.startx = this.dx + 30;
                    this.starty = this.dy;
                    this.dx += 30;
                    break;
                case 'j':
                    c.setCurve(this.startx,this.starty,
                            this.dx+7,this.dy-15,
                            this.dx+12,this.dy-15,
                            this.dx+7,this.dy-17);
                    g2.draw(c);
                    c.setCurve(this.dx+10, this.dy-15,
                                this.dx+52, this.dy+35,
                                this.dx-3, this.dy+35,
                                this.dx+35, this.dy-5);
                    g2.draw(c);
                    this.startx = this.dx +35;
                    this.starty = this.dy-5;
                    this.dx += 35;
                    break;
                    
                case 'k':
                    c.setCurve(this.startx, this.starty, 
                            this.dx+32, this.dy-17, 
                            this.dx+10, this.dy-50, 
                            this.dx+10, this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+10, this.dy-5,
                                this.dx+25, this.dy-28,
                                this.dx+30, this.dy-3,
                                this.dx+10, this.dy-5);
                    g2.draw(c);
                    c.setCurve(this.dx+10, this.dy-5,
                                this.dx+16, this.dy-8,
                                this.dx+9, this.dy+2,
                                this.dx+25, this.dy);
                    g2.draw(c);
                    this.startx = this.dx + 25;
                    this.starty = this.dy;
                    this.dx += 25;
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
                case 'm':
                    c.setCurve(this.startx, this.starty, 
                            this.dx+10, this.dy-9, 
                            this.dx+14, this.dy-25, 
                            this.dx+19, this.dy-3);
                    g2.draw(c);
                    c.setCurve(this.dx+19, this.dy-3, 
                            this.dx+14, this.dy-25, 
                            this.dx+28, this.dy-14, 
                            this.dx+28, this.dy-3);
                    g2.draw(c);
                    c.setCurve(this.dx+28, this.dy-3, 
                            this.dx+35, this.dy-15, 
                            this.dx+26, this.dy-20, 
                            this.dx+39, this.dy-3);
                    g2.draw(c);
                    this.startx = this.dx + 39;
                    this.starty = this.dy-1;
                    this.dx += 39;
                    
                    break;   
                case 'n':
                    c.setCurve(this.startx, this.starty, 
                            this.dx+10, this.dy-35, 
                            this.dx+11, this.dy-5, 
                            this.dx+19, this.dy-3);
                    g2.draw(c);
                    c.setCurve(this.dx+19, this.dy-3, 
                            this.dx+26, this.dy-29, 
                            this.dx+32, this.dy-5, 
                            this.dx+38, this.dy-3);
                    g2.draw(c);
                    this.startx = this.dx + 38;
                    this.starty = this.dy-3;
                    this.dx += 38;
                    break;
                case 'ñ':
                    c.setCurve(this.startx+5, this.starty-23, 
                            this.dx+19, this.dy-33, 
                            this.dx+16, this.dy-13, 
                            this.dx+33, this.dy-23);
                    g2.draw(c);
                    c.setCurve(this.startx, this.starty, 
                            this.dx+10, this.dy-35, 
                            this.dx+11, this.dy-5, 
                            this.dx+19, this.dy-3);
                    g2.draw(c);
                    c.setCurve(this.dx+19, this.dy-3, 
                            this.dx+26, this.dy-29, 
                            this.dx+32, this.dy-5, 
                            this.dx+38, this.dy-3);
                    g2.draw(c);
                    this.startx = this.dx + 38;
                    this.starty = this.dy-3;
                    this.dx += 38;
                    break;
                case 'o':
                    c.setCurve(this.startx, this.starty,
                                this.dx+3, this.dy,   
                                this.dx+7, this.dy,
                                this.dx+10, this.dy-10);
                    g2.draw(c);
                    c.setCurve(this.dx+12, this.dy-12,
                               this.dx+12, this.dy+5,
                               this.dx+28, this.dy+5,
                               this.dx+30, this.dy-14);
                    g2.draw(c);
                    c.setCurve(this.dx+12, this.dy-12,
                               this.dx+20, this.dy-30,
                               this.dx+29, this.dy-12,
                               this.dx+38, this.dy-15);
                    g2.draw(c);
                    this.startx = this.dx + 38;
                    this.starty = this.dy-15;
                    this.dx += 38;
                    break;
                case 'p':
                    c.setCurve(this.startx,this.starty,
                            this.dx+11,this.dy-12,
                            this.dx,this.dy,
                            this.dx+11,this.dy-12);
                    g2.draw(c);
                    c.setCurve(c);
                    c.setCurve(this.dx+11,this.dy,
                            this.dx+29,this.dy+5,
                            this.dx+7,this.dy-50,
                            this.dx+11,this.dy+19);
                    g2.draw(c);
                    
                    c.setCurve(this.dx+11,this.dy,
                            this.dx+15,this.dy+3,
                            this.dx+20,this.dy+3,
                            this.dx+25,this.dy-5);
                    g2.draw(c);
                    
                    this.startx = this.dx +25;
                    this.starty = this.dy-5;
                    this.dx += 25;
                    break;
                case 'q':
                    c.setCurve(this.startx,this.starty,
                            this.dx+5,this.dy-12,
                            this.dx,this.dy,
                            this.dx+5,this.dy-12);
                    g2.draw(c);
                    c.setCurve(this.dx+20, this.dy-5,
                                this.dx, this.dy-35,
                                this.dx+2, this.dy+4,
                                this.dx+19, this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+20, this.dy-5,
                            this.dx+17, this.dy+25,    
                            this.dx+33, this.dy+25,
                            this.dx+19, this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+20, this.dy-5,
                               this.dx+15, this.dy+3,
                               this.dx+20, this.dy+3,
                               this.dx+25, this.dy-5);
                    g2.draw(c);
                    this.startx = this.dx +25;
                    this.starty = this.dy-5;
                    this.dx += 25;
                    break;
                case 'r':
                    c.setCurve(this.startx,this.starty,
                            this.dx+2,this.dy-4,
                            this.dx+4,this.dy-2,
                            this.dx+4,this.dy-13);
                    g2.draw(c);
                    c.setCurve(this.dx+4,this.dy-13,
                            this.dx+8,this.dy-13,
                            this.dx+13,this.dy-13,
                            this.dx+17,this.dy-14);
                    g2.draw(c);
                    c.setCurve(this.dx+17,this.dy-14,
                            this.dx+15,this.dy-15,
                            this.dx+19,this.dy-6,
                            this.dx+27,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+27;
                    this.starty=this.dy;
                    this.dx+=37;
                    break;
                case 's':
                    c.setCurve(this.startx,this.starty,
                            this.dx+5,this.dy-1,
                            this.dx+16,this.dy-2,
                            this.dx+17,this.dy-14);
                    g2.draw(c);
                    c.setCurve(this.dx+17,this.dy-14,
                            this.dx+22,this.dy+9,
                            this.dx+5,this.dy,
                            this.dx,this.dy-1);
                    g2.draw(c);
                    c.setCurve(this.dx+4,this.dy-1,
                            this.dx+4,this.dy-1,
                            this.dx+22,this.dy+8,
                            this.dx+28,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+38;
                    this.starty=this.dy;
                    this.dx+=28;
                    break;
                case 't':
                    c.setCurve(this.startx,this.starty,
                            this.dx+5,this.dy-1,
                            this.dx+15,this.dy-8,
                            this.dx+15,this.dy-37);
                    g2.draw(c);
                    c.setCurve(this.dx+15,this.dy-37,
                            this.dx+15,this.dy-18,
                            this.dx+13,this.dy-3,
                            this.dx+30,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+11,this.dy-26,
                            this.dx+11,this.dy-26,
                            this.dx+11,this.dy-26,
                            this.dx+30,this.dy-26);
                    g2.draw(c);
                    this.startx=this.dx+30;
                    this.starty=this.dy;
                    this.dx+=30;
                    break;
                case 'u':
                    c.setCurve(this.startx,this.starty,
                            this.dx+7,this.dy+2,
                            this.dx+6,this.dy-4,
                            this.dx+6,this.dy-15);
                    g2.draw(c);
                    c.setCurve(this.dx+6,this.dy-15,
                            this.dx+3,this.dy+6,
                            this.dx+26,this.dy+0,
                            this.dx+22,this.dy-15);
                    g2.draw(c);
                    c.setCurve(this.dx+22,this.dy-15,
                            this.dx+20,this.dy,
                            this.dx+30,this.dy-1,
                            this.dx+40,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+40;
                    this.starty=this.dy;
                    this.dx+=40;
                    break;
                case 'v':
                    c.setCurve(this.startx,this.starty,
                            this.dx+3,this.dy-19,
                            this.dx+14,this.dy-18,
                            this.dx+13,this.dy-2);
                    g2.draw(c);
                    c.setCurve(this.dx+13,this.dy-2,
                            this.dx+17,this.dy+5,
                            this.dx+31,this.dy+6,
                            this.dx+31,this.dy-13);
                    g2.draw(c);
                    c.setCurve(this.dx+31,this.dy-13,
                            this.dx+26,this.dy-13,
                            this.dx+26,this.dy-13,
                            this.dx+45,this.dy-13);
                    g2.draw(c);
                    this.startx=this.dx+45;
                    this.starty=this.dy-13;
                    this.dx+=45;
                    break;
                case 'w':
                    c.setCurve(this.startx,this.starty,
                            this.dx+18,this.dy,
                            this.dx+29,this.dy-15,
                            this.dx+17,this.dy-8);
                    g2.draw(c);
                    c.setCurve(this.dx+17,this.dy-8,
                            this.dx+14,this.dy+4,
                            this.dx+35,this.dy+6,
                            this.dx+34,this.dy-12);
                    g2.draw(c);
                    c.setCurve(this.dx+34,this.dy-12,
                            this.dx+26,this.dy+6,
                            this.dx+53,this.dy+9,
                            this.dx+50,this.dy-12);
                    g2.draw(c);
                    c.setCurve(this.dx+50,this.dy-12,
                            this.dx+50,this.dy-12,
                            this.dx+50,this.dy-12,
                            this.dx+60,this.dy-12);
                    g2.draw(c);
                    this.startx=this.dx+60;
                    this.starty=this.dy-12;
                    this.dx+=70;
                    break;
                case 'x':
                    c.setCurve(this.startx,this.starty,
                            this.dx+12,this.dy-64,
                            this.dx+8,this.dy+16,
                            this.dx+35,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+5,this.dy-2,
                            this.dx+14,this.dy-7,
                            this.dx+14,this.dy-7,
                            this.dx+22,this.dy-18);
                    g2.draw(c);
                    
                    this.startx=this.dx+35;
                    this.starty=this.dy;
                    this.dx+=35;
                    break;
                case 'y':
                    c.setCurve(this.startx,this.starty,
                            this.dx+8,this.dy-55,
                            this.dx+9,this.dy+32,
                            this.dx+13,this.dy-6);
                    g2.draw(c);
                    c.setCurve(this.dx+13, this.dy-6, 
                            this.dx+5, this.dy+99, 
                            this.dx-18, this.dy, 
                            this.dx+31, this.dy);
                    g2.draw(c);
                    this.startx=this.dx+41;
                    this.starty=this.dy;
                    this.dx+=41;
                    break;
                case 'z':
                    c.setCurve(this.startx,this.starty,
                            this.dx+19,this.dy-21,
                            this.dx+52,this.dy-21,
                            this.dx+26,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+30, this.dy-5, 
                            this.dx+15, this.dy+119, 
                            this.dx-28, this.dy, 
                            this.dx+51, this.dy);
                    g2.draw(c);
                    this.startx=this.dx+50;
                    this.starty=this.dy;
                    this.dx+=50;
                    break;
                case 'A':
                    c.setCurve(this.dx+20, this.dy-39, 
                            this.dx-5, this.dy-35, 
                            this.dx-5, this.dy, 
                            this.dx+20,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+20, this.dy-39, 
                            this.dx+40, this.dy-35, 
                            this.dx+40, this.dy, 
                            this.dx+20,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+36, this.dy-20, 
                            this.dx+40, this.dy-7, 
                            this.dx+42, this.dy, 
                            this.dx+45,this.dy);
                    g2.draw(c);
                    this.startx = this.dx + 45;
                    this.starty = this.dy;
                    this.dx += 45;
                    break;
                case 'B':
                    c.setCurve(this.dx+3, this.dy, 
                            this.dx+3, this.dy-20, 
                            this.dx+3, this.dy-30, 
                            this.dx+3,this.dy-40);
                    g2.draw(c);
                    c.setCurve(this.dx+3, this.dy-37, 
                            this.dx+30, this.dy-45, 
                            this.dx+30, this.dy-20, 
                            this.dx+3,this.dy-20);
                    g2.draw(c);
                    c.setCurve(this.dx+3, this.dy-20, 
                            this.dx+30, this.dy-25, 
                            this.dx+30, this.dy+7, 
                            this.dx+3,this.dy-5);
                    g2.draw(c);
                    c.setCurve(this.dx+3, this.dy-5, 
                            this.dx+4, this.dy-5, 
                            this.dx+9, this.dy-5, 
                            this.dx+20,this.dy-5);
                    g2.draw(c);
                    this.startx = this.dx + 20;
                    this.starty = this.dy;
                    this.dx += 20;
                    break;
                case 'C':
                    c.setCurve(this.dx+30, this.dy-35, 
                            this.dx-5, this.dy-50, 
                            this.dx-5, this.dy+15, 
                            this.dx+30,this.dy-5);
                    g2.draw(c);
                    this.startx = this.dx + 30;
                    this.starty = this.dy - 5;
                    this.dx += 30;
                    break;
                case 'D':
                    c.setCurve(this.dx+10, this.dy-39, 
                            this.dx+10, this.dy-30, 
                            this.dx+13, this.dy+10, 
                            this.dx+3,this.dy-5);
                    g2.draw(c);
                    c.setCurve(this.dx+3, this.dy-5, 
                            this.dx-5, this.dy-20, 
                            this.dx+10, this.dy-10, 
                            this.dx+20,this.dy-3);
                    g2.draw(c);
                    c.setCurve(this.dx+20, this.dy-3, 
                            this.dx+30, this.dy-10, 
                            this.dx+30, this.dy-50, 
                            this.dx+10,this.dy-38);
                    g2.draw(c);
                    c.setCurve(this.dx+10, this.dy-38, 
                            this.dx+13, this.dy-35, 
                            this.dx+18, this.dy-35, 
                            this.dx+25,this.dy-40);
                    g2.draw(c);
                    this.startx = this.dx + 25;
                    this.starty = this.dy;
                    this.dx += 25;
                    break;
                case 'E':
                    c.setCurve(this.dx+25, this.dy-37, 
                            this.dx-5, this.dy-45, 
                            this.dx-5, this.dy-20, 
                            this.dx+15,this.dy-20);
                    g2.draw(c);
                    c.setCurve(this.dx+15, this.dy-20, 
                            this.dx-5, this.dy-25, 
                            this.dx-5, this.dy+7, 
                            this.dx+25,this.dy-5);
                    g2.draw(c);
                    this.startx = this.dx + 25;
                    this.starty = this.dy;
                    this.dx += 25;
                    break;
                case 'F':
                    c.setCurve(this.dx+15, this.dy-38, 
                            this.dx+15, this.dy-30, 
                            this.dx+18, this.dy+10, 
                            this.dx,this.dy-5);
                    g2.draw(c);
                    c.setCurve(this.dx, this.dy-36, 
                            this.dx+7.5, this.dy-40, 
                            this.dx+22.5, this.dy-34, 
                            this.dx+30,this.dy-40);
                    g2.draw(c);
                    c.setCurve(this.dx+7.5, this.dy-20, 
                            this.dx+7.5, this.dy-20, 
                            this.dx+20, this.dy-20, 
                            this.dx+20,this.dy-20);
                    g2.draw(c);
                    this.startx = this.dx + 20;
                    this.starty = this.dy;
                    this.dx += 20;
                    break;
                case 'G': 
                    c.setCurve(this.dx+10, this.dy-38, 
                            this.dx+15, this.dy-30, 
                            this.dx+10, this.dy-10, 
                            this.dx,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+10, this.dy-38, 
                            this.dx-5, this.dy-20, 
                            this.dx+22.5, this.dy-10, 
                            this.dx+25,this.dy-35);
                    g2.draw(c);
                    c.setCurve(this.dx+25, this.dy-35, 
                            this.dx+30, this.dy-10, 
                            this.dx+10, this.dy+10, 
                            this.dx,this.dy-5);
                    g2.draw(c);
                    c.setCurve(this.dx, this.dy-5, 
                            this.dx+2, this.dy-4, 
                            this.dx+8, this.dy-4, 
                            this.dx+10,this.dy-5);
                    g2.draw(c);
                    this.startx = this.dx + 25;
                    this.starty = this.dy;
                    this.dx += 25;
                    break;
                case 'H':
                    c.setCurve(this.dx, this.dy-35, 
                            this.dx+13, this.dy-45, 
                            this.dx+10, this.dy-10, 
                            this.dx+10,this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+25, this.dy, 
                            this.dx+5, this.dy-40, 
                            this.dx, this.dy+5, 
                            this.dx+30,this.dy-20);
                    g2.draw(c);
                    c.setCurve(this.dx+25, this.dy-40, 
                            this.dx+25, this.dy-30, 
                            this.dx+25, this.dy-20, 
                            this.dx+25,this.dy);
                    g2.draw(c);
                    this.startx = this.dx + 30;
                    this.starty = this.dy;
                    this.dx += 30;
                    break;
                case 'I':
                    c.setCurve(this.dx, this.dy-15, 
                            this.dx+5, this.dy+5, 
                            this.dx+20, this.dy, 
                            this.dx+25,this.dy-20);
                    g2.draw(c);
                    c.setCurve(this.dx+15, this.dy-20, 
                            this.dx+10, this.dy-50, 
                            this.dx+30, this.dy-50, 
                            this.dx+25,this.dy-20);
                    g2.draw(c);
                    c.setCurve(this.dx+15, this.dy-20, 
                            this.dx+20, this.dy, 
                            this.dx+25, this.dy, 
                            this.dx+30,this.dy-5);
                    g2.draw(c);
                    c.setCurve(this.dx, this.dy-15, 
                            this.dx+5, this.dy-10, 
                            this.dx+10, this.dy-10, 
                            this.dx+15,this.dy-10);
                    g2.draw(c);
                    this.startx = this.dx + 30;
                    this.starty = this.dy - 5;
                    this.dx += 30;
                    break;
                case 'J':
                    c.setCurve(this.dx+18,this.dy-40,
                            this.dx+47,this.dy+25,
                            this.dx-15,this.dy+52,
                            this.dx+31,this.dy-4);
                    g2.draw(c);
                    c.setCurve(this.dx+18, this.dy-40,
                                this.dx+4, this.dy-29,
                                this.dx-1, this.dy+1,
                                this.dx+28, this.dy-5);
                    g2.draw(c);
                    this.startx = this.dx +31;
                    this.starty = this.dy-4;
                    this.dx += 31;
                    break;
                    
                case 'K':
                    c.setCurve(this.dx+10, this.dy, 
                            this.dx+10, this.dy-5, 
                            this.dx+5, this.dy-40, 
                            this.dx, this.dy-38);
                    g2.draw(c);
                    c.setCurve(this.dx+10, this.dy-20,
                                this.dx+11, this.dy-15,
                                this.dx+25, this.dy-25,
                                this.dx+25, this.dy-30);
                    g2.draw(c);
                    c.setCurve(this.dx+10, this.dy-20,
                                this.dx+11, this.dy-15,
                                this.dx+25, this.dy+2,
                                this.dx+25, this.dy);
                    g2.draw(c);
                    this.startx = this.dx +25;
                    this.starty = this.dy;
                    this.dx += 25;
                    break;
                case 'L':
                    c.setCurve(this.dx, this.dy-32,
                            this.dx+45, this.dy-23,
                            this.dx+5, this.dy-60,
                            this.dx+11, this.dy-13);
                    g2.draw(c);
                    c.setCurve(this.dx+11, this.dy-13,
                            
                            this.dx-4, this.dy+23,
                            this.dx-6, this.dy-29,
                            this.dx+27, this.dy);
                    g2.draw(c);
                    this.startx = this.dx + 27;
                    this.starty = this.dy;
                    this.dx += 27;
                    break;
                case 'M':
                    c.setCurve(this.dx, this.dy-38, 
                            this.dx+12, this.dy-46, 
                            this.dx+9, this.dy-32, 
                            this.dx+10, this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+9, this.dy-32, 
                            this.dx+13, this.dy-50, 
                            this.dx+20, this.dy-25, 
                            this.dx+20, this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+20, this.dy-25, 
                            this.dx+21, this.dy-60, 
                            this.dx+30, this.dy-12, 
                            this.dx+30, this.dy);
                    g2.draw(c);
                    this.startx = this.dx + 35;
                    this.starty = this.dy;
                    this.dx += 35;
                    
                    break;
                    
                case 'N':
                    c.setCurve(this.dx, this.dy-38, 
                            this.dx+12, this.dy-46, 
                            this.dx+9, this.dy-32, 
                            this.dx+10, this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+9, this.dy-32, 
                            this.dx+13, this.dy-50, 
                            this.dx+20, this.dy-25, 
                            this.dx+20, this.dy);
                    g2.draw(c);
                    this.startx = this.dx + 25;
                    this.starty = this.dy;
                    this.dx += 25;
                    break;
                case 'Ñ':
                    c.setCurve(this.dx+1, this.starty-45, 
                            this.dx+10, this.dy-58, 
                            this.dx+10, this.dy-32, 
                            this.dx+20, this.dy-45);
                    g2.draw(c);
                    c.setCurve(this.dx, this.dy-38, 
                            this.dx+12, this.dy-46, 
                            this.dx+11, this.dy-36, 
                            this.dx+10, this.dy);
                    g2.draw(c);
                    c.setCurve(this.dx+9, this.dy-32, 
                            this.dx+13, this.dy-50, 
                            this.dx+20, this.dy-25, 
                            this.dx+20, this.dy);
                    g2.draw(c);
                    this.startx = this.dx + 25;
                    this.starty = this.dy;
                    this.dx += 25;
                    break;
                case 'O':
                    c.setCurve(this.dx, this.dy-26,
                               this.dx-4, this.dy+8,
                               this.dx+46, this.dy+9,
                               this.dx+40, this.dy-26);
                    g2.draw(c);
                    c.setCurve(this.dx, this.dy-26,
                               this.dx+7, this.dy-46,
                               this.dx+40, this.dy-42,
                               this.dx+40, this.dy-26);
                    g2.draw(c);
                    c.setCurve(this.dx+12, this.dy-35,
                                this.dx+19, this.dy-10,
                                this.dx+40, this.dy-10,
                                this.dx+45, this.dy-35);
                    g2.draw(c);
                    this.startx = this.dx + 45;
                    this.starty = this.dy-35;
                    this.dx += 36;
                    break;
                case 'P':
                    c.setCurve(this.dx+5,this.dy,
                            this.dx+5,this.dy,
                            this.dx+5,this.dy-21,
                            this.dx+5,this.dy-40);
                    g2.draw(c);
                    c.setCurve(c);
                    c.setCurve(this.dx+5,this.dy-21,
                            this.dx+20,this.dy-14,
                            this.dx+29,this.dy-45,
                            
                            this.dx+5,this.dy-40);
                    g2.draw(c);
                    this.startx = this.dx +29;
                    this.starty = this.dy;
                    this.dx += 21;
                    
                    break;
                case 'Q':
                    c.setCurve(this.dx+8,this.dy-15,
                            this.dx+41,this.dy-78,
                            this.dx+45,this.dy-2,
                            this.dx+13,this.dy-5);
                    g2.draw(c);
                    c.setCurve(this.dx+13, this.dy-5,
                            this.dx-16, this.dy+12,
                            this.dx+7, this.dy-20,
                            this.dx+26, this.dy);
                    g2.draw(c);
                    this.startx = this.dx +35;
                    this.starty = this.dy;
                    this.dx += 35;
                    break;
                case 'R':
                    c.setCurve(this.dx+1,this.dy-5,
                            this.dx+4,this.dy+13,
                            this.dx+7,this.dy-4,
                            this.dx+5,this.dy-32);
                    g2.draw(c);
                    c.setCurve(this.dx+2,this.dy-21,
                            this.dx-5,this.dy-59,
                            this.dx+55,this.dy-27,
                            this.dx+10,this.dy-7);
                    g2.draw(c);
                    c.setCurve(this.dx+10,this.dy-7,
                            this.dx+9,this.dy-22,
                            this.dx+26,this.dy-14,
                            this.dx+35,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+35;
                    this.starty=this.dy;
                    this.dx+=35;
                    break;
                case 'S':
                    c.setCurve(this.dx,this.dy,
                            this.dx+57,this.dy-35,
                            this.dx-3,this.dy-64,
                            this.dx+30,this.dy-9);
                    g2.draw(c);
                    c.setCurve(this.dx+30,this.dy-9,
                            this.dx+45,this.dy+26,
                            this.dx-22,this.dy-24,
                            this.dx+48,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+48;
                    this.starty=this.dy;
                    this.dx+=48;
                    break;
                case 'T':
                    c.setCurve(this.dx+4,this.dy-28,
                            this.dx-6,this.dy-47,
                            this.dx+15,this.dy-33,
                            this.dx+30,this.dy-28);
                    g2.draw(c);
                    c.setCurve(this.dx+30,this.dy-28,
                            this.dx+43,this.dy-67,
                            this.dx-20,this.dy+17,
                            this.dx+38,this.dy+1);
                    g2.draw(c);
                    
                    c.setCurve(this.dx+3,this.dy-20,
                            this.dx+26,this.dy-20,
                            this.dx+26,this.dy-20,
                            this.dx+26,this.dy-20);
                    g2.draw(c);
                    
                     this.startx=this.dx+38;
                    this.starty=this.dy;
                    this.dx+=38;
                    break;
                case 'U':
                    c.setCurve(this.dx,this.dy-40,
                            this.dx-2,this.dy+9,
                            this.dx+24,this.dy+15,
                            this.dx+27,this.dy-40);
                    g2.draw(c);
                    c.setCurve(this.dx+27,this.dy-40,
                            this.dx+18,this.dy-1,
                            this.dx+25,this.dy+3,
                            this.dx+35,this.dy);
                     g2.draw(c);
                     this.startx=this.dx+35;
                    this.starty=this.dy;
                    this.dx+=35;
                     
                     break;
                case 'V':
                    c.setCurve(this.dx,this.dy-34,
                            this.dx+8,this.dy-46,
                            this.dx+14,this.dy-36,
                            this.dx+8,this.dy-1);
                    g2.draw(c);
                    c.setCurve(this.dx+8,this.dy-1,
                            this.dx+13,this.dy+2,
                            this.dx+30,this.dy+3,
                            this.dx+25,this.dy-40);
                     g2.draw(c);
                    c.setCurve(this.dx+25,this.dy-40,
                            this.dx+30,this.dy-40,
                            this.dx+30,this.dy-40,
                            this.dx+43,this.dy-40);
                     g2.draw(c);
                    this.startx=this.dx+43;
                    this.starty=this.dy-40;
                    this.dx+=43;
                     
                     break;
                case 'W':
                    c.setCurve(this.dx,this.dy-30,
                            this.dx+6,this.dy-60,
                            this.dx+5,this.dy+55,
                            this.dx+22,this.dy-40);
                     g2.draw(c);
                    c.setCurve(this.dx+22,this.dy-40,
                            this.dx+5,this.dy+11,
                            this.dx+42,this.dy+13,
                            this.dx+42,this.dy-40);
                    g2.draw(c);
                    c.setCurve(this.dx+40,this.dy-40,
                            this.dx+40,this.dy-40,
                            this.dx+40,this.dy-40,
                            this.dx+50,this.dy-40);
                    g2.draw(c);
                    
                    this.startx=this.dx+50;
                    this.starty=this.dy-40;
                    this.dx+=50;
                    break;
                case 'X':
                    c.setCurve(this.dx,this.dy-30,
                            this.dx+11,this.dy-40,
                            this.dx+24,this.dy+6,
                            this.dx+35,this.dy);
                     g2.draw(c);
                    c.setCurve(this.dx,this.dy,
                            this.dx+17,this.dy-40,
                            this.dx+34,this.dy-36,
                            this.dx+35,this.dy-40);
                     g2.draw(c);
                     
                    this.startx=this.dx+35;
                    this.starty=this.dy;
                    this.dx+=35;
                    break;
                case 'Y':
                    c.setCurve(this.dx,this.dy-34,
                            this.dx+10,this.dy-64,
                            this.dx+5,this.dy+53,
                            this.dx+28,this.dy-29);
                    g2.draw(c);
                    c.setCurve(this.dx+28,this.dy-29,
                            this.dx+10,this.dy+85,
                            this.dx-13,this.dy-7,
                            this.dx+34,this.dy);
                    g2.draw(c);
                    this.startx=this.dx+39;
                    this.starty=this.dy;
                    this.dx+=39;
                    break;  
                case 'Z':
                    c.setCurve(this.dx,this.dy-34,
                            this.dx+16,this.dy-65,
                            this.dx-23,this.dy-33,
                            this.dx+30,this.dy-40);
                    
                    g2.draw(c);
                    c.setCurve(this.dx,this.dy,
                            this.dx+1,this.dy-12,
                            this.dx+20,this.dy-31,
                            this.dx+30,this.dy-40);
                    g2.draw(c);
                     c.setCurve(this.dx,this.dy,
                            this.dx+3,this.dy-9,
                            this.dx+14,this.dy+3,
                            this.dx+30,this.dy);
                     g2.draw(c);
                     c.setCurve(this.dx+1,this.dy-15,
                            this.dx+10,this.dy-22,
                            this.dx+18,this.dy-14,
                            this.dx+29,this.dy-18);
                     g2.draw(c);
                    this.startx=this.dx+40;
                    this.starty=this.dy;
                    this.dx+=40;
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
