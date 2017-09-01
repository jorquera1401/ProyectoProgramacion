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
    private double alto;
    private double ancho;
    private double[] puntos;
    
    public Letra(char letra, double ancho, double alto){
        this.letra=letra;
        this.dx=0;
        this.dy=0;
        this.alto=alto;
        this.ancho=ancho;
        
    }
    
    
    public void generarPUntos(){
        switch(letra){
            case 'a':
                
            case 'b':
                
            case 'c':
           
            
                
        }
    }
    
   
    
    @Override
    public void paint(Graphics g){
        this.setAlto(this.getHeight());
        this.setAncho(this.getWidth());
        g.setColor(Color.red);
 //       g.fillRect(0, 0, 10, 10);
        Graphics2D g2 = (Graphics2D) g;
        
        QuadCurve2D qc = new QuadCurve2D.Float();
        CubicCurve2D c = new CubicCurve2D.Double();
        
        switch(letra){
            case 'a':
                qc.setCurve(this.dx,this.alto,this.ancho/2,dy,this.ancho,this.alto);
                g2.draw(qc);
                break;
            case 'b':
                c.setCurve(this.dx+(this.ancho/10), this.dy, 
                        this.dx, this.alto/4, 
                        this.dx+(2*(this.ancho/10)), this.alto-(this.alto/4), 
                        this.dx+(this.ancho/10), this.alto);
                g2.draw(c);
                c.setCurve(dx, dx, 
                        dx, dx,
                        dx, dx,
                        dx, dx);
                g2.draw(c);
                break;
            case 'f':
                qc.setCurve(this.dx+10, this.alto, this.alto/2, this.dy, this.ancho*(0.66), this.alto/10);
                g2.draw(qc);
                
                c.setCurve(80, 370, 120, 320, 150, 380, 180, 340);
                g2.draw(c);
                break;
        }
        
    }


    /**
     * @return the dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * @param x the dx to set
     */
    public void setX(double x) {
        this.dx = x;
    }

    /**
     * @return the dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * @param y the dy to set
     */
    public void setY(double y) {
        this.dy = y;
    }

    /**
     * @return the alto
     */
    public double getAlto() {
        return alto;
    }

    /**
     * @param alto the alto to set
     */
    public void setAlto(double alto) {
        this.alto = alto;
    }

    /**
     * @return the ancho
     */
    public double getAncho() {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(double ancho) {
        this.ancho = ancho;
    }
    
    
    
}
