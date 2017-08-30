/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;
import javax.swing.*;

/**
 *
 * @author mjorquera
 */
public class Letra extends JPanel{
    
    private char letra;
    private int[][] posicionPuntos;
    private double x,y,height,width;
    
    public Letra(char letra){
        this.posicionPuntos = new int[10][10];
        x=0;
        y=0;
        height=this.getHeight();
        width=this.getWidth();
    }
    
    
    public void generarPUntos(){
       
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.red);
        g.fillRect(0, 0, 10, 10);
        g.drawRect(0, 0, this.getWidth(), this.getHeight());
        Graphics2D g2d = (Graphics2D) g;
        QuadCurve2D q2 = new QuadCurve2D.Float();
        q2.setCurve(x, y,height ,width, x, y);
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
