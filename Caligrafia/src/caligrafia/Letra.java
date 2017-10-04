/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mjorquera
 * Clase sin implememtar
 */
public class Letra {
    
//    private Point puntoInicio;
//    private CubicCurve2D[] curvas;
//    private char caracter;
//    private int dx;
    private char letra;
    private HashMap<Integer, ArrayList> coordenadas;
    
    
    public Letra(char letra, HashMap<Integer, ArrayList> coordenadas){
//        this.curvas = curvas;
//        this.puntoInicio = new Point(0, 0);
//        this.caracter = caracter;
//        this.dx = dx;
        this.letra = letra;
        this.coordenadas = new HashMap<>();
    }        

    public char getLetra() {
        return letra;
    }

    public HashMap<Integer, ArrayList> getCoordenadas() {
        return coordenadas;
    }
    
    
}
