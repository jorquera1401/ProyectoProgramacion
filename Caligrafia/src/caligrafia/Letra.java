/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

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
    
    public Letra(){
        this.posicionPuntos = new int[10][10];
    }
    
    
    public void generarPUntos(){
        if(this.letra=='a'){
            this.posicionPuntos[0][5]=10;
            this.posicionPuntos[5][0]=10;
            this.posicionPuntos[5][9]=10;
            this.posicionPuntos[9][0]=10;
            this.posicionPuntos[9][9]=10;
        }
    }
    
    @Override
    public void paint(Graphics g){
        for(int i=0;i<this.posicionPuntos.length;i++){
            for(int j=0;j<this.posicionPuntos.length;j++){
                if(posicionPuntos[i][j]!=0){
                    g.drawLine(i, j, j+10, j+10);
                }
            }
        }
        g.fillRect(50, 200, 40, 30);
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
