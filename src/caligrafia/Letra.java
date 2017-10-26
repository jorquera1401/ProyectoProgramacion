/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mjorquera
 * 
 */
public class Letra {
    
    private char letra;
    private double ancho;
    private HashMap<Integer, ArrayList> coordenadas;
    
    
    public Letra(char letra, double ancho){
        this.letra = letra;
        this.ancho = ancho;
        this.coordenadas = new HashMap<>();
    }        

    public char getLetra() {
        return letra;
    }

    public HashMap<Integer, ArrayList> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(HashMap<Integer, ArrayList> coordenadas) {
        this.coordenadas = coordenadas;
    }

    public double getAncho() {
        return ancho;
    }
    
    
}
