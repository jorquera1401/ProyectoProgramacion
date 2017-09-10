/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

<<<<<<< HEAD
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;
import javafx.scene.shape.Shape;
import java.awt.geom.QuadCurve2D;
import javax.swing.*;
=======
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.CubicCurve2D;
>>>>>>> fe9bc3ae4ff7f70e9d2b55912cf33515877abb51

/**
 *
 * @author mjorquera
 */
<<<<<<< HEAD
public class Letra extends JPanel{
    
    private char letra;
   private double x,y,height,width;
    
    public Letra(char letra){
        this.letra=letra;
        x=0;
        y=0;
    }
=======
public class Letra {
>>>>>>> fe9bc3ae4ff7f70e9d2b55912cf33515877abb51
    
    private Point puntoInicio;
    private CubicCurve2D[] curvas;
    private char caracter;
    private int dx;
    
<<<<<<< HEAD
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
=======
    public Letra(CubicCurve2D[] curvas, char caracter, int dx){
        this.curvas = curvas;
        this.puntoInicio = new Point(0, 0);
        this.caracter = caracter;
        this.dx = dx;
    }        
    
    public int dibujar(Point puntoInicio, Graphics2D g2d){
        for(CubicCurve2D c : this.curvas){
            g2d.draw(c);
        }
        return this.dx;
>>>>>>> fe9bc3ae4ff7f70e9d2b55912cf33515877abb51
    }
}
