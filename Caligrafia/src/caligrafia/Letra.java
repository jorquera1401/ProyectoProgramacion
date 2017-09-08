/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.CubicCurve2D;

/**
 *
 * @author mjorquera
 */
public class Letra {
    
    private Point puntoInicio;
    private CubicCurve2D[] curvas;
    private char caracter;
    private int dx;
    
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
    }
}
