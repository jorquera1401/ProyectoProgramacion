/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import javax.swing.JPanel;

/**
 *
 * @author mjorquera
 */
public class PanelTexto extends JPanel{
    
    private String palabra;
    private Letra[] letras;
    
    public PanelTexto(){
        this.palabra="";
       
    }
    
    //carga las letras al panel 
    public  void cargarLetras(){
        setLayout(new GridLayout(1, this.palabra.length()));
        for(int i=0;i<this.letras.length;i++){
            this.add(letras[i]);
        }
        
    }
    
    /**
     * separa cada una de las letras que contiene la palabra ingresada
     */
    public void separarLetras(){
        this.letras=new Letra[this.palabra.length()];
        for(int  i=0;i<this.palabra.length();i++){
            System.out.println(palabra.charAt(i));
       //     this.letras[i].setLetra(this.palabra.charAt(i));
        }
        
     //   cargarLetras();
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.red);
        // g.drawRect(40, 60, 100,100);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        // g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize)); 
        //     g.drawString(this.palabra, 100, 200);
        
        g.setFont(new Font("TimesRoman", Font.PLAIN, 106));
        
        //    g.drawString("a", 45, 150);
        if(this.palabra.equals("a")){
            g.drawLine(200, 10, 20, 100);
            g.drawLine(200, 10, 380, 100);
            g.drawLine(100, 60, 300, 60);
            
        }
        if(this.palabra.equals("b")){
            g.drawLine(50, 10, 50, 200);
            g.drawOval(50,150,100,100);
        }
        //Casteo
        Graphics2D g2 = (Graphics2D) g;
        /*
        //Se crea un objeto de la clase QuadCurve2D
        QuadCurve2D q = new QuadCurve2D.Float();
        //Se setean los puntos de control
        q.setCurve(100, 100, 300, 300, 100, 300);
        //Se dibuja el shape
        g2.draw(q);
        
        // create new CubicCurve2D.Double
        CubicCurve2D c = new CubicCurve2D.Double();
        //draw CubicCurve2D.Double with set coordinates
        c.setCurve(200, 200, 400, 50, 300, 500, 500, 200);
        g2.draw(c);           
        
        // draw Ellipse2D.Double
        g2.draw(new Ellipse2D.Double(400, 400, 100, 50));
        
        // draw Arc2D.Double
        g2.draw(new Arc2D.Double(50, 50, 100, 50, 100, 135, Arc2D.OPEN));
        // draw Line2D.Double
            g2.draw(new Line2D.Double(100, 400, 180, 370));
        */
        if(this.palabra.equals("f")){
            QuadCurve2D q = new QuadCurve2D.Float();
            //Se setean los puntos de control
            q.setCurve(110, 500, 160, 30, 210, 250);
            // create new CubicCurve2D.Double
            CubicCurve2D c = new CubicCurve2D.Double();
            //draw CubicCurve2D.Double with set coordinates
            c.setCurve(80, 370, 120, 320, 150, 380, 180, 340);
            g2.draw(c);
            //Se dibuja el shape
            g2.draw(q);
        }
    }

    /**
     * @return the palabra
     */
    public String getPalabra() {
        return palabra;
    }

    /**
     * @param palabra the palabra to set
     */
    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    
}
