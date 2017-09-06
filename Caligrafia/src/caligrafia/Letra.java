/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;
import javax.swing.*;

/**
 *
 * @author mjorquera
 */
public class Letra extends JPanel{
    
    private char letra;
    private double dx;
    private double dy; 
    //private double alto;
    //private double ancho;
    //private double[] puntos;
    
    public Letra(char letra, double ancho, double alto){
        this.letra=letra;
        this.dx=0;
        this.dy=0;
        //this.alto=alto;
        //this.ancho=ancho;
        
    }        
    
    @Override
    public void paint(Graphics g){
        //this.setAlto(this.getHeight());
        //this.setAncho(this.getWidth());
        g.setColor(Color.red);
 //       g.fillRect(0, 0, 10, 10);
        Graphics2D g2 = (Graphics2D) g;
        
        QuadCurve2D qc = new QuadCurve2D.Float();
        CubicCurve2D c = new CubicCurve2D.Double();
        
        /*switch(letra){
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
            case 'f':
                qc.setCurve(this.dx+10, this.alto, this.alto/7, this.dy, this.ancho/5, this.alto/10);
                g2.draw(qc);
                
                //c.setCurve(80, 370, 120, 320, 150, 380, 180, 340);
                c.setCurve(this.dx, this.alto/2, this.ancho/4, this.alto/3, this.ancho/4, this.alto/3, this.dx+(this.ancho/4), this.alto/2);
                g2.draw(c);
                break;
        }*/
        
    }



    
    
    
}
