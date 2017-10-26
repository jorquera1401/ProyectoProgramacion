/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

/**
 *
 * @author renecsc
 */
public class Palabra {
    private String string;
    //punto incial
    private double startx;
    private double starty;
    //punto final
    private double endx;
    private double endy;
    //ancho/largo/size
    private double ancho;
    //Caracteristicas de la impresion.
    private boolean subrayado;
    private boolean negrita;
    private boolean cursiva;
    private double s;
    
    public Palabra(String palabra){
        this.string = palabra;
        this.subrayado = false;
        this.negrita = false;
        this.cursiva = false;
        this.s = 1.0;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
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

    public double getEndx() {
        return endx;
    }

    public void setEndx(double endx) {
        this.endx = endx;
    }

    public double getEndy() {
        return endy;
    }

    public void setEndy(double endy) {
        this.endy = endy;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }
    
    public boolean isSubrayado() {
        return subrayado;
    }

    public void setSubrayado(boolean subrayado) {
        this.subrayado = subrayado;
    }

    public boolean isNegrita() {
        return negrita;
    }

    public void setNegrita(boolean negrita) {
        this.negrita = negrita;
    }

    public boolean isCursiva() {
        return cursiva;
    }

    public void setCursiva(boolean cursiva) {
        this.cursiva = cursiva;
    }

    public double getS() {
        return s;
    }

    public void setS(double s) {
        this.s = s;
    }
    
}
