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
import javafx.scene.shape.Shape;
import java.awt.geom.QuadCurve2D;
import javax.swing.*;

/**
 *
 * @author mjorquera
 */
public class Letra extends JPanel{
    
    private char letra;
   private double x,y,height,width;
    
    public Letra(char letra){
        this.letra=letra;
        x=0;
        y=0;
    }
    
    
    public void generarPUntos(){
        System.out.println("x: "+x+" y:"+y+" width:"+this.width+" height: "+this.height);
    }
    
    @Override
    public void paint(Graphics g){
        this.height=this.getHeight();
        this.width=this.getWidth();
        g.setColor(Color.red);
 //       g.fillRect(0, 0, 10, 10);
        Graphics2D g2d = (Graphics2D) g;
        
        QuadCurve2D q2 = new QuadCurve2D.Float();
        q2.setCurve(10,50,60,1,100,50);
        g2d.draw(q2);
        q2.setCurve(10, 50, 120, 1, 100, 50);
        g2d.draw(q2);
        
        g.drawRect(0, 0, getWidth(), getHeight());
       
    }

    /**
     * @return the letra
     */
    public char getLetra() {
        return letra;
    }

    /**
     * @param letra the letra to set
     */
    public void setLetra(char letra) {
        this.letra = letra;
    }
    
    
    
}
