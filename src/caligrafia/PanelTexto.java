/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import javax.swing.JPanel;

/**
 *
 * @author mjorquera
 */
public class PanelTexto extends JPanel{

    private String oracion;
    //private Palabra palabra;
    private ArrayList<Letra> letras;
    private ArrayList<Palabra> palabras;
    //Dos coordenadas para referenciar el punto de incio de una letra.
    private double startx;
    private double starty;
    //Dos indices para referenciar el punto de incio de una letra pero relativo a la linea inferior.
    private double dx;
    private double dy;
    //Tamanyo de escalado de la letra.
    private double s;
    private boolean control;
    private boolean invert;
    //Expresion regular.
    private String regex;

    public PanelTexto(){
        this.oracion = "";
//        this.palabra = new Palabra("");
        this.regex = "";
        this.letras = new ArrayList<>();
        //this.palabras = new ArrayList<>();
        this.s = 1.0;
        this.startx = 10 ;
        this.starty = 65 * this.s;
        this.dx = 10;
        this.dy = 65 * this.s;
        this.invert = false;
        this.cargarLetras();
        this.cargarPalabras();
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.red);
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(2.0f);

        g2.setStroke(stroke);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        CubicCurve2D c = new CubicCurve2D.Double();
        
        int incremento;
        int posicion;
        int termino;
        if(invert){
            incremento = -1;
            posicion = this.palabras.size()-1;
            termino = -1;
        }else{
            incremento = 1;
            posicion = 0;
            termino = this.palabras.size();
        }
        while(posicion != termino && !this.palabras.isEmpty()){
            //Se establecen los puntos de incio y termino dependiendo de las letras de la palabra.
            this.palabras.get(posicion).setStartx(this.startx);
            this.palabras.get(posicion).setStarty(this.starty);

            this.palabras.get(posicion).setEndx(this.dx + this.palabras.get(posicion).getAncho());
            this.palabras.get(posicion).setEndy(this.dy);
            //Se verifica si es necesario agandar el panel horizontalmente
            if(this.palabras.get(posicion).getAncho()+20 >= this.getWidth()){
                this.setPreferredSize(new Dimension((int)this.palabras.get(posicion).getAncho()+20, (int) (this.getHeight())));
            }
            //Condicion para saber si es necesario el salto de linea.
            else if(this.getWidth() < this.palabras.get(posicion).getEndx()){
                this.dx = 10;
                this.startx = this.dx;
                this.dy += 65 * this.palabras.get(posicion).getS();
                this.starty = this.dy;
            }
            //Se verifica si es necesario agandar el panel verticalmente
            else if(this.dy > this.getHeight()){
                this.setPreferredSize(new Dimension((int)this.getWidth(), (int) (this.getHeight()+(65 * this.palabras.get(posicion).getS()))));
            }

            //Se setean los estilos.
            if(this.palabras.get(posicion).isSubrayado()){
                stroke = new BasicStroke(2.0f);
                if((posicion + incremento) != termino && this.palabras.get(posicion + incremento).isSubrayado()){
                    g2.drawLine((int)this.palabras.get(posicion).getStartx(), (int)this.dy + 5,(int) (this.palabras.get(posicion).getEndx() + (30 * this.palabras.get(posicion).getS())), (int)this.dy + 5);
                }else{
                    g2.drawLine((int)this.palabras.get(posicion).getStartx(), (int)this.dy + 5,(int) this.palabras.get(posicion).getEndx(), (int)this.dy + 5);
                }
            }
            if(this.palabras.get(posicion).isNegrita()){
                stroke = new BasicStroke(5.0f);
            }else{
                stroke = new BasicStroke(2.0f);
            }
            g2.setStroke(stroke);

            if(this.palabras.get(posicion).isCursiva()){
                this.dx += 0.5*this.starty*this.palabras.get(posicion).getS();
                this.startx = this.dx;
            }
            //Se imprime la palabra en el panel.
            for(int i = 0; i < palabras.get(posicion).getString().length(); i++){
                for(int j = 0; j < this.letras.size(); j++){
                    if(this.letras.get(j).getLetra() == this.palabras.get(posicion).getString().charAt(i)){
                        Set<Integer> keys = this.letras.get(j).getCoordenadas().keySet();
                        Iterator<Integer> it = keys.iterator();
                        ArrayList<Double> al = new ArrayList<>();
                        if(j < 27){
                            al = this.letras.get(j).getCoordenadas().get(it.next());
                            if(this.palabras.get(posicion).isCursiva()){
                                c.setCurve((this.startx+al.get(0)*this.palabras.get(posicion).getS())-0.5*(this.starty+al.get(1)*this.palabras.get(posicion).getS()), this.starty+al.get(1)*this.palabras.get(posicion).getS(),
                                            (this.dx+al.get(2)*this.palabras.get(posicion).getS())-0.5*(this.dy+al.get(3)*this.palabras.get(posicion).getS()), this.dy+al.get(3)*this.palabras.get(posicion).getS(),
                                            (this.dx+al.get(4)*this.palabras.get(posicion).getS())-0.5*(this.dy+al.get(5)*this.palabras.get(posicion).getS()), this.dy+al.get(5)*this.palabras.get(posicion).getS(),
                                            (this.dx+al.get(6)*this.palabras.get(posicion).getS())-0.5*(this.dy+al.get(7)*this.palabras.get(posicion).getS()), this.dy+al.get(7)*this.palabras.get(posicion).getS());
                            }else{
                                c.setCurve(this.startx+al.get(0)*this.palabras.get(posicion).getS(), this.starty+al.get(1)*this.palabras.get(posicion).getS(),
                                            this.dx+al.get(2)*this.palabras.get(posicion).getS(), this.dy+al.get(3)*this.palabras.get(posicion).getS(),
                                            this.dx+al.get(4)*this.palabras.get(posicion).getS(), this.dy+al.get(5)*this.palabras.get(posicion).getS(),
                                            this.dx+al.get(6)*this.palabras.get(posicion).getS(), this.dy+al.get(7)*this.palabras.get(posicion).getS());
                            }
                            g2.draw(c);
                        //se activa cuando se presiona el boton de los puntos de control
                        if(control){
                            g2.setColor(Color.blue);
                            g2.fillOval((int)c.getX1(),(int)c.getY1(),5,5);
                            g2.fillOval((int)c.getX2(),(int)c.getY2(),5,5);
                            g2.fillOval((int)c.getCtrlX1(),(int)c.getCtrlY1(),5,5);
                            g2.fillOval((int)c.getCtrlX2(),(int)c.getCtrlY2(),5,5);
                            g2.setColor(Color.red);}
                        }
                        while(it.hasNext()){
                            al = this.letras.get(j).getCoordenadas().get(it.next());
                            if(this.palabras.get(posicion).isCursiva()){
                                c.setCurve((this.dx+al.get(0)*this.palabras.get(posicion).getS())-0.5*(this.dy+al.get(1)*this.palabras.get(posicion).getS()), this.dy+al.get(1)*this.palabras.get(posicion).getS(),
                                            (this.dx+al.get(2)*this.palabras.get(posicion).getS())-0.5*(this.dy+al.get(3)*this.palabras.get(posicion).getS()), this.dy+al.get(3)*this.palabras.get(posicion).getS(),
                                            (this.dx+al.get(4)*this.palabras.get(posicion).getS())-0.5*(this.dy+al.get(5)*this.palabras.get(posicion).getS()), this.dy+al.get(5)*this.palabras.get(posicion).getS(),
                                            (this.dx+al.get(6)*this.palabras.get(posicion).getS())-0.5*(this.dy+al.get(7)*this.palabras.get(posicion).getS()), this.dy+al.get(7)*this.palabras.get(posicion).getS());
                            }else{
                                c.setCurve(this.dx+al.get(0)*this.palabras.get(posicion).getS(), this.dy+al.get(1)*this.palabras.get(posicion).getS(),
                                        this.dx+al.get(2)*this.palabras.get(posicion).getS(), this.dy+al.get(3)*this.palabras.get(posicion).getS(),
                                        this.dx+al.get(4)*this.palabras.get(posicion).getS(), this.dy+al.get(5)*this.palabras.get(posicion).getS(),
                                        this.dx+al.get(6)*this.palabras.get(posicion).getS(), this.dy+al.get(7)*this.palabras.get(posicion).getS());
                            }
                            g2.draw(c);

                            if(control){
                                g2.setColor(Color.blue);
                                g2.fillOval((int)c.getX1(),(int)c.getY1(),5,5);
                                g2.fillOval((int)c.getX2(),(int)c.getY2(),5,5);
                                g2.fillOval((int)c.getCtrlX1(),(int)c.getCtrlY1(),5,5);
                                g2.fillOval((int)c.getCtrlX2(),(int)c.getCtrlY2(),5,5);
                                g2.setColor(Color.red);
                            }
                        }
                        g2.setColor(Color.red);
                        this.startx = this.dx + this.letras.get(j).getAncho() * this.palabras.get(posicion).getS();
                        this.dx += this.letras.get(j).getAncho() * this.palabras.get(posicion).getS();

                        if(j < 27){
                            this.starty = this.dy + al.get(7) * this.palabras.get(posicion).getS();
                        }
                        else{
                            this.starty = this.dy;
                        }
                    }
                }
            }
            this.dx += 30 * this.palabras.get(posicion).getS();
            this.startx = this.dx;

            posicion += incremento;
        }
    }

    public void cargarPalabras(){
        this.palabras = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(this.oracion);
        StringTokenizer stRegEx = new StringTokenizer(this.regex, ",");
        //Se leen las palabras de la oracion.
        while(st.hasMoreTokens()){
            Palabra p = new Palabra(st.nextToken());
            //Se lee la expresion regular correspondiente a la palabra.
            if(stRegEx.hasMoreTokens()){
                String next = stRegEx.nextToken();
                for(int i = 0; i < next.length(); i++){
                    if(next.charAt(i) == 'S'){
                        p.setSubrayado(true);
                    }
                    else if(next.charAt(i) == 'N'){
                        p.setNegrita(true);
                    }
                    else if(next.charAt(i) == 'K'){
                        p.setCursiva(true);
                    }
                    else if(next.charAt(i) == '1'){
                        p.setS(1.0);
                    }
                    else if(next.charAt(i) == '2'){
                        p.setS(1.5);
                    }
                    else if(next.charAt(i) == '3'){
                        p.setS(2.0);
                    }
                    else if(next.charAt(i) == '4'){
                        p.setS(2.5);
                    }
                    else if(next.charAt(i) == '5'){
                        p.setS(3.0);
                    }
                    else if(next.charAt(i) == '6'){
                        p.setS(3.5);
                    }
                    else if(next.charAt(i) == '7'){
                        p.setS(4.0);
                    }
                    else if(next.charAt(i) == '8'){
                        p.setS(4.5);
                    }
                    else if(next.charAt(i) == '9'){
                        p.setS(5.0);
                    }
                }
            }

            double anchoPalabra = 0;
            for(int i = 0; i < p.getString().length(); i++){
                for(int j = 0; j < this.letras.size(); j++){
                    if(this.letras.get(j).getLetra() == p.getString().charAt(i)){
                        anchoPalabra += this.letras.get(j).getAncho() * p.getS();
                    }
                }
            }
            p.setAncho(anchoPalabra);
            this.palabras.add(p);
        }
    }

    /**
     * Funcion para cargar las coordenadas de cada simbolo y letra
     */
    public final  void cargarLetras(){
        ArrayList<Double> al = new ArrayList<>();
        HashMap<Integer, ArrayList> coordenadas = new HashMap<>();

        Letra l = new Letra('a', 30.0);

        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 3.0);
        al.add(3, 0.0);
        al.add(4, 3.0);
        al.add(5, 0.0);
        al.add(6, 6.0);
        al.add(7, -7.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 15.0);
        al.add(1, -15.0);
        al.add(2, 3.0);
        al.add(3, -12.0);
        al.add(4, 3.0);
        al.add(5, -3.0);
        al.add(6, 15.0);
        al.add(7, 0.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 15.0);
        al.add(1, -15.0);
        al.add(2, 23.0);
        al.add(3, -12.0);
        al.add(4, 23.0);
        al.add(5, -3.0);
        al.add(6, 15.0);
        al.add(7, 0.0);
        coordenadas.put(3, al);

        al = new ArrayList<>();
        al.add(0, 20.0);
        al.add(1, -12.5);
        al.add(2, 23.0);
        al.add(3, -7.0);
        al.add(4, 23.0);
        al.add(5, -2.0);
        al.add(6, 30.0);
        al.add(7, 0.0);
        coordenadas.put(4, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('b', 25.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 20.0);
        al.add(3, -50.0);
        al.add(4, -10.0);
        al.add(5, -50.0);
        al.add(6, 10.0);
        al.add(7, 0.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 10.0);
        al.add(1, 0.0);
        al.add(2, 15.0);
        al.add(3, 0.0);
        al.add(4, 15.0);
        al.add(5, 0.0);
        al.add(6, 15.0);
        al.add(7, -15.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 15.0);
        al.add(1, -15.0);
        al.add(2, 20.0);
        al.add(3, -10.0);
        al.add(4, 20.0);
        al.add(5, -10.0);
        al.add(6, 25.0);
        al.add(7, -15.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('c', 20.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 2.5);
        al.add(3, 0.0);
        al.add(4, 2.5);
        al.add(5, 0.0);
        al.add(6, 9.0);
        al.add(7, -7.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 20.0);
        al.add(1, -15.0);
        al.add(2, 5.0);
        al.add(3, -10.0);
        al.add(4, 5.0);
        al.add(5, -5.0);
        al.add(6, 20.0);
        al.add(7, 0.0);
        coordenadas.put(2, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('d', 30.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 5.0);
        al.add(3, 0.0);
        al.add(4, 5.0);
        al.add(5, 0.0);
        al.add(6, 8.0);
        al.add(7, -5.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 20.0);
        al.add(1, -15.0);
        al.add(2, 5.0);
        al.add(3, -10.0);
        al.add(4, 5.0);
        al.add(5, 5.0);
        al.add(6, 20.0);
        al.add(7, -5.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 20.0);
        al.add(1, -39.0);
        al.add(2, 20.0);
        al.add(3, -5.0);
        al.add(4, 20.0);
        al.add(5, -2.0);
        al.add(6, 30.0);
        al.add(7, 0.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('e', 25.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 5.0);
        al.add(3, 0.0);
        al.add(4, 15.0);
        al.add(5, 0.0);
        al.add(6, 25.0);
        al.add(7, -14.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 25.0);
        al.add(1, -14.0);
        al.add(2, 5.0);
        al.add(3, -50.0);
        al.add(4, 5.0);
        al.add(5, 0.0);
        al.add(6, 25.0);
        al.add(7, 0.0);
        coordenadas.put(2, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('f', 12.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 12.0);
        al.add(3, -20.0);
        al.add(4, 18.0);
        al.add(5, -45.0);
        al.add(6, 7.0);
        al.add(7, -35.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 7.0);
        al.add(1, -35.0);
        al.add(2, 2.0);
        al.add(3, -20.0);
        al.add(4, 3.0);
        al.add(5, 10.0);
        al.add(6, 3.0);
        al.add(7, 17.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 3.0);
        al.add(1, 17.0);
        al.add(2, 10.0);
        al.add(3, 13.0);
        al.add(4, 10.0);
        al.add(5, 0.0);
        al.add(6, 3.0);
        al.add(7, 0.0);
        coordenadas.put(3, al);

        al = new ArrayList<>();
        al.add(0, 3.0);
        al.add(1, 0.0);
        al.add(2, 5.0);
        al.add(3, 0.0);
        al.add(4, 5.0);
        al.add(5, 0.0);
        al.add(6, 12.0);
        al.add(7, -3.0);
        coordenadas.put(4, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('g', 30.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 3.0);
        al.add(3, 0.0);
        al.add(4, 3.0);
        al.add(5, 0.0);
        al.add(6, 6.0);
        al.add(7, -7.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 15.0);
        al.add(1, -15.0);
        al.add(2, 3.0);
        al.add(3, -12.0);
        al.add(4, 3.0);
        al.add(5, -3.0);
        al.add(6, 15.0);
        al.add(7, 0.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 15.0);
        al.add(1, -15.0);
        al.add(2, 23.0);
        al.add(3, -12.0);
        al.add(4, 23.0);
        al.add(5, -3.0);
        al.add(6, 15.0);
        al.add(7, 0.0);
        coordenadas.put(3, al);

        al = new ArrayList<>();
        al.add(0, 20.0);
        al.add(1, -12.5);
        al.add(2, 30.0);
        al.add(3, 30.0);
        al.add(4, -5.0);
        al.add(5, 30.0);
        al.add(6, 30.0);
        al.add(7, -5.0);
        coordenadas.put(4, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('h', 22.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 12.0);
        al.add(3, -20.0);
        al.add(4, 18.0);
        al.add(5, -45.0);
        al.add(6, 7.0);
        al.add(7, -35.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 7.0);
        al.add(1, -35.0);
        al.add(2, 5.0);
        al.add(3, -20.0);
        al.add(4, 6.0);
        al.add(5, 10.0);
        al.add(6, 6.0);
        al.add(7, 0.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 7.0);
        al.add(1, 0.0);
        al.add(2, 15.0);
        al.add(3, -30.0);
        al.add(4, 18.0);
        al.add(5, 10.0);
        al.add(6, 22.0);
        al.add(7, -3.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('i', 30.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 10.0);
        al.add(3, -2.0);
        al.add(4, 13.0);
        al.add(5, -7.0);
        al.add(6, 15.0);
        al.add(7, -15.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 15.0);
        al.add(1, -22.0);
        al.add(2, 15.0);
        al.add(3, -22.0);
        al.add(4, 15.0);
        al.add(5, -22.0);
        al.add(6, 15.0);
        al.add(7, -22.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 15.0);
        al.add(1, -15.0);
        al.add(2, 17.0);
        al.add(3, -7.0);
        al.add(4, 25.0);
        al.add(5, -2.0);
        al.add(6, 30.0);
        al.add(7, 0.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('j', 35.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 7.0);
        al.add(3, -15.0);
        al.add(4, 12.0);
        al.add(5, -15.0);
        al.add(6, 7.0);
        al.add(7, -17.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 10.0);
        al.add(1, -15.0);
        al.add(2, 52.0);
        al.add(3, 35.0);
        al.add(4, -3.0);
        al.add(5, 35.0);
        al.add(6, 35.0);
        al.add(7, -5.0);
        coordenadas.put(2, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('k', 25.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 32.0);
        al.add(3, -17.0);
        al.add(4, 10.0);
        al.add(5, -50.0);
        al.add(6, 10.0);
        al.add(7, 0.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 10.0);
        al.add(1, -5.0);
        al.add(2, 25.0);
        al.add(3, -28.0);
        al.add(4, 30.0);
        al.add(5, -3.0);
        al.add(6, 10.0);
        al.add(7, -5.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 10.0);
        al.add(1, -5.0);
        al.add(2, 16.0);
        al.add(3, -8.0);
        al.add(4, 9.0);
        al.add(5, 2.0);
        al.add(6, 25.0);
        al.add(7, 0.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('l', 10.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 20.0);
        al.add(3, -50.0);
        al.add(4, -10.0);
        al.add(5, -50.0);
        al.add(6, 10.0);
        al.add(7, 0.0);
        coordenadas.put(1, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('m', 39.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 10.0);
        al.add(3, -9.0);
        al.add(4, 14.0);
        al.add(5, -25.0);
        al.add(6, 19.0);
        al.add(7, -3.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 19.0);
        al.add(1, -3.0);
        al.add(2, 14.0);
        al.add(3, -25.0);
        al.add(4, 28.0);
        al.add(5, -14.0);
        al.add(6, 28.0);
        al.add(7, -3.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 28.0);
        al.add(1, -3.0);
        al.add(2, 35.0);
        al.add(3, -15.0);
        al.add(4, 26.0);
        al.add(5, -20.0);
        al.add(6, 39.0);
        al.add(7, -3.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('n', 38.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 10.0);
        al.add(3, -35.0);
        al.add(4, 11.0);
        al.add(5, -5.0);
        al.add(6, 19.0);
        al.add(7, -3.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 19.0);
        al.add(1, -3.0);
        al.add(2, 26.0);
        al.add(3, -29.0);
        al.add(4, 32.0);
        al.add(5, -5.0);
        al.add(6, 38.0);
        al.add(7, -3.0);
        coordenadas.put(2, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('ñ', 38.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 5.0);
        al.add(1, -23.0);
        al.add(2, 19.0);
        al.add(3, -33.0);
        al.add(4, 16.0);
        al.add(5, -13.0);
        al.add(6, 33.0);
        al.add(7, -23.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 10.0);
        al.add(3, -35.0);
        al.add(4, 1.0);
        al.add(5, -5.0);
        al.add(6, 19.0);
        al.add(7, -3.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 19.0);
        al.add(1, -3.0);
        al.add(2, 26.0);
        al.add(3, -29.0);
        al.add(4, 32.0);
        al.add(5, -5.0);
        al.add(6, 38.0);
        al.add(7, -3.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('o', 38.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 3.0);
        al.add(3, .0);
        al.add(4, 7.0);
        al.add(5, 0.0);
        al.add(6, 10.0);
        al.add(7, -10.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 12.0);
        al.add(1, -12.0);
        al.add(2, 12.0);
        al.add(3, 5.0);
        al.add(4, 28.0);
        al.add(5, 5.0);
        al.add(6, 30.0);
        al.add(7, -14.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 12.0);
        al.add(1, -12.0);
        al.add(2, 20.0);
        al.add(3, -30.0);
        al.add(4, 29.0);
        al.add(5, -12.0);
        al.add(6, 38.0);
        al.add(7, -15.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('p', 25.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 11.0);
        al.add(3, -12.0);
        al.add(4, 0.0);
        al.add(5, 0.0);
        al.add(6, 11.0);
        al.add(7, -12.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 11.0);
        al.add(1, 0.0);
        al.add(2, 29.0);
        al.add(3, 5.0);
        al.add(4, 7.0);
        al.add(5, -50.0);
        al.add(6, 11.0);
        al.add(7, 19.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 11.0);
        al.add(1, 0.0);
        al.add(2, 15.0);
        al.add(3, 3.0);
        al.add(4, 20.0);
        al.add(5, 3.0);
        al.add(6, 25.0);
        al.add(7, -5.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('q', 25.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, 0.0);
        al.add(2, 5.0);
        al.add(3, -12.0);
        al.add(4, 0.0);
        al.add(5, 0.0);
        al.add(6, 5.0);
        al.add(7, -12.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 20.0);
        al.add(1, -5.0);
        al.add(2, 0.0);
        al.add(3, -35.0);
        al.add(4, 2.0);
        al.add(5, 4.0);
        al.add(6, 19.0);
        al.add(7, 0.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 20.0);
        al.add(1, -5.0);
        al.add(2, 17.0);
        al.add(3, 25.0);
        al.add(4, 33.0);
        al.add(5, 25.0);
        al.add(6, 19.0);
        al.add(7, 0.0);
        coordenadas.put(3, al);

        al = new ArrayList<>();
        al.add(0, 20.0);
        al.add(1, -5.0);
        al.add(2, 15.0);
        al.add(3, 3.0);
        al.add(4, 20.0);
        al.add(5, 3.0);
        al.add(6, 25.0);
        al.add(7, -5.0);
        coordenadas.put(4, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('r', 27.0);
        coordenadas = new HashMap<>();

        al= new ArrayList<>();
        al.add(0,0.0); al.add(1,0.0);
        al.add(2,2.0);  al.add(3,-4.0);
        al.add(4,4.0);  al.add(5,-2.0);
        al.add(6,4.0);  al.add(7,-13.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,4.0);  al.add(1,-13.0);
        al.add(2,8.0);  al.add(3,-13.0);
        al.add(4,13.0);  al.add(5,-13.0);
        al.add(6,17.0);  al.add(7,-14.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,17.0);  al.add(1,-14.0);
        al.add(2,15.0);  al.add(3,-15.0);
        al.add(4,19.0);  al.add(5,-6.0);
        al.add(6,27.0);  al.add(7,0.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('s', 28.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);  al.add(1,0.0);
        al.add(2,5.0);  al.add(3,-1.0);
        al.add(4,16.0);  al.add(5,-2.0);
        al.add(6,17.0);  al.add(7,-14.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,17.0);  al.add(1,-14.0);
        al.add(2,22.0);  al.add(3,9.0);
        al.add(4,8.0);  al.add(5,0.0);
        al.add(6,6.0);  al.add(7,-1.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,4.0);  al.add(1,-1.0);
        al.add(2,4.0);  al.add(3,-1.0);
        al.add(4,22.0);  al.add(5,8.0);
        al.add(6,28.0);  al.add(7,0.0);
        coordenadas.put(3,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);


        l = new Letra('t', 30.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);  al.add(1,0.0);
        al.add(2,5.0);  al.add(3,-1.0);
        al.add(4,15.0);  al.add(5,-8.0);
        al.add(6,15.0);  al.add(7,-37.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,11.0);  al.add(1,-26.0);
        al.add(2,11.0);  al.add(3,-26.0);
        al.add(4,11.0);  al.add(5,-26.0);
        al.add(6,30.0);  al.add(7,-26.0);

        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,15.0);  al.add(1,-37.0);
        al.add(2,15.0);  al.add(3,-18.0);
        al.add(4,15.0);  al.add(5,-3.0);
        al.add(6,30.0);  al.add(7,0.0);
        coordenadas.put(3,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('u', 40.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);  al.add(1,0.0);
        al.add(2,7.0);  al.add(3,2.0);
        al.add(4,6.0);  al.add(5,-4.0);
        al.add(6,6.0);  al.add(7,-15.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,6.0);  al.add(1,-15.0);
        al.add(2,3.0);  al.add(3,6.0);
        al.add(4,26.0);  al.add(5,0.0);
        al.add(6,22.0);  al.add(7,-15.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,22.0);  al.add(1,-15.0);
        al.add(2,20.0);  al.add(3,0.0);
        al.add(4,30.0);  al.add(5,-1.0);
        al.add(6,40.0);  al.add(7,0.0);
        coordenadas.put(3,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('v', 45.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);  al.add(1,0.0);
        al.add(2,3.0);  al.add(3,-19.0);
        al.add(4,14.0);  al.add(5,-18.0);
        al.add(6,13.0);  al.add(7,-2.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,13.0);  al.add(1,-2.0);
        al.add(2,17.0);  al.add(3,5.0);
        al.add(4,31.0);  al.add(5,6.0);
        al.add(6,31.0);  al.add(7,-13.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,31.0);  al.add(1,-13.0);
        al.add(2,26.0);  al.add(3,-13.0);
        al.add(4,26.0);  al.add(5,-13.0);
        al.add(6,45.0);  al.add(7,-13.0);
        coordenadas.put(3,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('w', 60.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);  al.add(1,0.0);
        al.add(2,18.0);  al.add(3,0.0);
        al.add(4,29.0);  al.add(5,-15.0);
        al.add(6,17.0);  al.add(7,-8.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,17.0);  al.add(1,-8.0);
        al.add(2,14.0);  al.add(3,4.0);
        al.add(4,35.0);  al.add(5,6.0);
        al.add(6,34.0);  al.add(7,-12.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,34.0);  al.add(1,-12.0);
        al.add(2,26.0);  al.add(3,6.0);
        al.add(4,53.0);  al.add(5,9.0);
        al.add(6,50.0);  al.add(7,-12.0);
        coordenadas.put(3,al);

        al=new ArrayList<>();
        al.add(0,50.0);  al.add(1,-12.0);
        al.add(2,50.0);  al.add(3,-12.0);
        al.add(4,50.0);  al.add(5,-12.0);
        al.add(6,60.0);  al.add(7,-12.0);
        coordenadas.put(4,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('x', 35.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);  al.add(1,0.0);
        al.add(2,12.0);  al.add(3,-64.0);
        al.add(4,8.0);  al.add(5,+16.0);
        al.add(6,35.0);  al.add(7,0.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,5.0);  al.add(1,-2.0);
        al.add(2,14.0);  al.add(3,-7.0);
        al.add(4,14.0);  al.add(5,-7.0);
        al.add(6,22.0);  al.add(7,-18.0);
        coordenadas.put(2,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('y', 41.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);  al.add(1,0.0);
        al.add(2,8.0);  al.add(3,-55.0);
        al.add(4,9.0);  al.add(5,32.0);
        al.add(6,13.0);  al.add(7,-6.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,13.0);  al.add(1,-6.0);
        al.add(2,5.0);  al.add(3,99.0);
        al.add(4,-18.0);  al.add(5,0.0);
        al.add(6,31.0);  al.add(7,0.0);
        coordenadas.put(2,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('z', 50.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);  al.add(1,0.0);
        al.add(2,19.0);  al.add(3,-21.0);
        al.add(4,52.0);  al.add(5,-21.0);
        al.add(6,26.0);  al.add(7,0.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,30.0);  al.add(1,-5.0);
        al.add(2,15.0);  al.add(3,119.0);
        al.add(4,-28.0);  al.add(5,0.0);
        al.add(6,51.0);  al.add(7,0.0);
        coordenadas.put(2,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('A', 45.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 20.0);
        al.add(1, -39.0);
        al.add(2, -5.0);
        al.add(3, -35.0);
        al.add(4, -5.0);
        al.add(5, 0.0);
        al.add(6, 20.0);
        al.add(7, 0.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 20.0);
        al.add(1, -39.0);
        al.add(2, 40.0);
        al.add(3, -35.0);
        al.add(4, 40.0);
        al.add(5, 0.0);
        al.add(6, 20.0);
        al.add(7, 0.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 36.0);
        al.add(1, -20.0);
        al.add(2, 40.0);
        al.add(3, -7.0);
        al.add(4, 42.0);
        al.add(5, 0.0);
        al.add(6, 45.0);
        al.add(7, 0.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('B', 20.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 3.0);
        al.add(1, 0.0);
        al.add(2, 3.0);
        al.add(3, -20.0);
        al.add(4, 3.0);
        al.add(5, -30.0);
        al.add(6, 3.0);
        al.add(7, -40.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 3.0);
        al.add(1, -37.0);
        al.add(2, 30.0);
        al.add(3, -45.0);
        al.add(4, 30.0);
        al.add(5, -20.0);
        al.add(6, 3.0);
        al.add(7, -20.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 3.0);
        al.add(1, -20.0);
        al.add(2, 30.0);
        al.add(3, -25.0);
        al.add(4, 30.0);
        al.add(5, 7.0);
        al.add(6, 3.0);
        al.add(7, -5.0);
        coordenadas.put(3, al);

        al = new ArrayList<>();
        al.add(0, 3.0);
        al.add(1, -5.0);
        al.add(2, 4.0);
        al.add(3, -5.0);
        al.add(4, 9.0);
        al.add(5, -5.0);
        al.add(6, 20.0);
        al.add(7, -5.0);
        coordenadas.put(4, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('C', 30.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 30.0);
        al.add(1, -35.0);
        al.add(2, -5.0);
        al.add(3, -50.0);
        al.add(4, -5.0);
        al.add(5, 15.0);
        al.add(6, 30.0);
        al.add(7, -5.0);
        coordenadas.put(1, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('D', 25.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 10.0);
        al.add(1, -39.0);
        al.add(2, 10.0);
        al.add(3, -30.0);
        al.add(4, 13.0);
        al.add(5, 10.0);
        al.add(6, 3.0);
        al.add(7, -5.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 3.0);
        al.add(1, -5.0);
        al.add(2, -5.0);
        al.add(3, -20.0);
        al.add(4, 10.0);
        al.add(5, -10.0);
        al.add(6, 20.0);
        al.add(7, -3.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 20.0);
        al.add(1, -3.0);
        al.add(2, 30.0);
        al.add(3, -10.0);
        al.add(4, 30.0);
        al.add(5, -50.0);
        al.add(6, 10.0);
        al.add(7, -38.0);
        coordenadas.put(3, al);

        al = new ArrayList<>();
        al.add(0, 10.0);
        al.add(1, -38.0);
        al.add(2, 13.0);
        al.add(3, -35.0);
        al.add(4, 18.0);
        al.add(5, -35.0);
        al.add(6, 25.0);
        al.add(7, -40.0);
        coordenadas.put(4, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('E', 25.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 25.0);
        al.add(1, -37.0);
        al.add(2, -5.0);
        al.add(3, -45.0);
        al.add(4, -5.0);
        al.add(5, -20.0);
        al.add(6, 15.0);
        al.add(7, -20.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 15.0);
        al.add(1, -20.0);
        al.add(2, -5.0);
        al.add(3, -25.0);
        al.add(4, -5.0);
        al.add(5, 7.0);
        al.add(6, 25.0);
        al.add(7, -5.0);
        coordenadas.put(2, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('F', 20.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 15.0);
        al.add(1, -38.0);
        al.add(2, 15.0);
        al.add(3, -30.0);
        al.add(4, 18.0);
        al.add(5, 10.0);
        al.add(6, 0.0);
        al.add(7, -5.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, -36.0);
        al.add(2, 7.5);
        al.add(3, -40.0);
        al.add(4, 22.5);
        al.add(5, -34.0);
        al.add(6, 30.0);
        al.add(7, -40.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 7.5);
        al.add(1, -20.0);
        al.add(2, 7.5);
        al.add(3, -20.0);
        al.add(4, 20.0);
        al.add(5, -20.0);
        al.add(6, 20.0);
        al.add(7, -20.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('G', 25.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 10.0);
        al.add(1, -38.0);
        al.add(2, 15.0);
        al.add(3, -30.0);
        al.add(4, 10.0);
        al.add(5, -10.0);
        al.add(6, 0.0);
        al.add(7, 0.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 10.0);
        al.add(1, -38.0);
        al.add(2, -5.0);
        al.add(3, -20.0);
        al.add(4, 22.5);
        al.add(5, -10.0);
        al.add(6, 25.0);
        al.add(7, -35.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 25.0);
        al.add(1, -35.0);
        al.add(2, 30.0);
        al.add(3, -10.0);
        al.add(4, 10.0);
        al.add(5, 10.0);
        al.add(6, 0.0);
        al.add(7, -5.0);
        coordenadas.put(3, al);

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, -5.0);
        al.add(2, 2.0);
        al.add(3, -4.0);
        al.add(4, 8.0);
        al.add(5, -4.0);
        al.add(6, 10.0);
        al.add(7, -5.0);
        coordenadas.put(4, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('H', 30.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, -35.0);
        al.add(2, 13.0);
        al.add(3, -45.0);
        al.add(4, 10.0);
        al.add(5, -10.0);
        al.add(6, 10.0);
        al.add(7, 0.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 25.0);
        al.add(1, 0.0);
        al.add(2, 5.0);
        al.add(3, -40.0);
        al.add(4, 0.0);
        al.add(5, 5.0);
        al.add(6, 30.0);
        al.add(7, -20.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 25.0);
        al.add(1, -40.0);
        al.add(2, 25.0);
        al.add(3, -30.0);
        al.add(4, 25.0);
        al.add(5, -20.0);
        al.add(6, 25.0);
        al.add(7, 0.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('I', 30.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, -15.0);
        al.add(2, 5.0);
        al.add(3, 5.0);
        al.add(4, 20.0);
        al.add(5, 0.0);
        al.add(6, 25.0);
        al.add(7, -20.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 15.0);
        al.add(1, -20.0);
        al.add(2, 10.0);
        al.add(3, -50.0);
        al.add(4, 30.0);
        al.add(5, -50.0);
        al.add(6, 25.0);
        al.add(7, -20.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 15.0);
        al.add(1, -20.0);
        al.add(2, 20.0);
        al.add(3, 0.0);
        al.add(4, 25.0);
        al.add(5, 0.0);
        al.add(6, 30.0);
        al.add(7, -5.0);
        coordenadas.put(3, al);

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, -15.0);
        al.add(2, 5.0);
        al.add(3, -10.0);
        al.add(4, 10.0);
        al.add(5, -10.0);
        al.add(6, 15.0);
        al.add(7, -10.0);
        coordenadas.put(4, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('J', 31.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 18.0);
        al.add(1, -40.0);
        al.add(2, 47.0);
        al.add(3, 25.0);
        al.add(4, -15.0);
        al.add(5, 52.0);
        al.add(6, 31.0);
        al.add(7, -4.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 18.0);
        al.add(1, -40.0);
        al.add(2, 4.0);
        al.add(3, -29.0);
        al.add(4, -1.0);
        al.add(5, 1.0);
        al.add(6, 28.0);
        al.add(7, -5.0);
        coordenadas.put(2, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('K', 25.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0,   10.0);
        al.add(1, 0.0);
        al.add(2, 10.0);
        al.add(3, -5.0);
        al.add(4, 5.0);
        al.add(5, -40.0);
        al.add(6, 0.0);
        al.add(7, -38.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 10.0);
        al.add(1, -20.0);
        al.add(2, 11.0);
        al.add(3, -15.0);
        al.add(4, 25.0);
        al.add(5, -25.0);
        al.add(6, 25.0);
        al.add(7, -30.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 10.0);
        al.add(1, -20.0);
        al.add(2, 11.0);
        al.add(3, -15.0);
        al.add(4, 25.0);
        al.add(5, 2.0);
        al.add(6, 25.0);
        al.add(7, 0.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('L', 27.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, -32.0);
        al.add(2, 45.0);
        al.add(3, -23.0);
        al.add(4, 5.0);
        al.add(5, -60.0);
        al.add(6, 11.0);
        al.add(7, -13.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 11.0);
        al.add(1, -13.0);
        al.add(2, -4.0);
        al.add(3, 23.0);
        al.add(4, -6.0);
        al.add(5, -29.0);
        al.add(6, 27.0);
        al.add(7, 0.0);
        coordenadas.put(2, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('M', 35.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0,   0.0);
        al.add(1, -38.0);
        al.add(2, 12.0);
        al.add(3, -46.0);
        al.add(4, 9.0);
        al.add(5, -32.0);
        al.add(6, 10.0);
        al.add(7, 0.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 9.0);
        al.add(1, -32.0);
        al.add(2, 13.0);
        al.add(3, -50.0);
        al.add(4, 20.0);
        al.add(5, -25.0);
        al.add(6, 20.0);
        al.add(7, 0.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 20.0);
        al.add(1, -25.0);
        al.add(2, 21.0);
        al.add(3, -60.0);
        al.add(4, 30.0);
        al.add(5, -12.0);
        al.add(6, 30.0);
        al.add(7, 0.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('N', 25.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, -38.0);
        al.add(2, 12.0);
        al.add(3, -46.0);
        al.add(4, 9.0);
        al.add(5, -32.0);
        al.add(6, 10.0);
        al.add(7, 0.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 9.0);
        al.add(1, -32.0);
        al.add(2, 13.0);
        al.add(3, -50.0);
        al.add(4, 20.0);
        al.add(5, -25.0);
        al.add(6, 20.0);
        al.add(7, 0.0);
        coordenadas.put(2, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('Ñ', 25.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0,   1.0);
        al.add(1, -45.0);
        al.add(2, 10.0);
        al.add(3, -58.0);
        al.add(4, 10.0);
        al.add(5, -32.0);
        al.add(6, 20.0);
        al.add(7, -45.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, -38.0);
        al.add(2, 12.0);
        al.add(3, -46.0);
        al.add(4, 11.0);
        al.add(5, -36.0);
        al.add(6, 10.0);
        al.add(7, 0.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 9.0);
        al.add(1, -32.0);
        al.add(2, 13.0);
        al.add(3, -50.0);
        al.add(4, 20.0);
        al.add(5, -25.0);
        al.add(6, 20.0);
        al.add(7, 0.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

         l = new Letra('O', 45.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, -26.0);
        al.add(2, -4.0);
        al.add(3, 8.0);
        al.add(4, 46.0);
        al.add(5, 9.0);
        al.add(6, 40.0);
        al.add(7, -26.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 0.0);
        al.add(1, -26.0);
        al.add(2, 7.0);
        al.add(3, -46.0);
        al.add(4, 40.0);
        al.add(5, -42.0);
        al.add(6, 40.0);
        al.add(7, -26.0);
        coordenadas.put(2, al);

        al = new ArrayList<>();
        al.add(0, 12.0);
        al.add(1, -35.0);
        al.add(2, 19.0);
        al.add(3, -10.0);
        al.add(4, 40.0);
        al.add(5, -10.0);
        al.add(6, 45.0);
        al.add(7, -35.0);
        coordenadas.put(3, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('P', 29.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 5.0);
        al.add(1, 0.0);
        al.add(2, 5.0);
        al.add(3, 0.0);
        al.add(4, 5.0);
        al.add(5, -21.0);
        al.add(6, 5.0);
        al.add(7, -40.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 5.0);
        al.add(1, -21.0);
        al.add(2, 20.0);
        al.add(3, -14.0);
        al.add(4, 29.0);
        al.add(5, -45.0);
        al.add(6, 5.0);
        al.add(7, -40.0);
        coordenadas.put(2, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('Q', 35.0);
        coordenadas = new HashMap<>();

        al = new ArrayList<>();
        al.add(0, 8.0);
        al.add(1, -15.0);
        al.add(2, 41.0);
        al.add(3, -78.0);
        al.add(4, 45.0);
        al.add(5, -2.0);
        al.add(6, 13.0);
        al.add(7, -5.0);
        coordenadas.put(1, al);

        al = new ArrayList<>();
        al.add(0, 13.0);
        al.add(1, -5.0);
        al.add(2, -16.0);
        al.add(3, 12.0);
        al.add(4, 7.0);
        al.add(5, -20.0);
        al.add(6, 26.0);
        al.add(7, 0.0);
        coordenadas.put(2, al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('R', 35.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,1.0);
        al.add(1,-5.0);
        al.add(2,4.0);
        al.add(3,13.0);
        al.add(4,7.0);
        al.add(5,-4.0);
        al.add(6,5.0);
        al.add(7,-32.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,2.0);
        al.add(1,-21.0);
        al.add(2,-5.0);
        al.add(3,-59.0);
        al.add(4,55.0);
        al.add(5,-27.0);
        al.add(6,10.0);
        al.add(7,-7.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,10.0);
        al.add(1,-7.0);
        al.add(2,9.0);
        al.add(3,-22.0);
        al.add(4,26.0);
        al.add(5,-14.0);
        al.add(6,35.0);
        al.add(7,0.0);
        coordenadas.put(3,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('S', 48.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);
        al.add(1,0.0);
        al.add(2,57.0);
        al.add(3,-35.0);
        al.add(4,-3.0);
        al.add(5,-64.0);
        al.add(6,30.0);
        al.add(7,-9.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,30.0);
        al.add(1,-9.0);
        al.add(2,45.0);
        al.add(3,26.0);
        al.add(4,-22.0);
        al.add(5,-24.0);
        al.add(6,48.0);
        al.add(7,0.0);
        coordenadas.put(2,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('T', 26.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,4.0);
        al.add(1,-28.0);
        al.add(2,-6.0);
        al.add(3,-47.0);
        al.add(4,15.0);
        al.add(5,-33.0);
        al.add(6,30.0);
        al.add(7,-28.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,30.0);
        al.add(1,-28.0);
        al.add(2,43.0);
        al.add(3,-67.0);
        al.add(4,-20.0);
        al.add(5,17.0);
        al.add(6,38.0);
        al.add(7,1.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,3.0);
        al.add(1,-20.0);
        al.add(2,26.0);
        al.add(3,-20.0);
        al.add(4,26.0);
        al.add(5,-20.0);
        al.add(6,26.0);
        al.add(7,-20.0);
        coordenadas.put(3,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('U', 35.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);
        al.add(1,-40.0);
        al.add(2,-2.0);
        al.add(3,9.0);
        al.add(4,24.0);
        al.add(5,15.0);
        al.add(6,27.0);
        al.add(7,-40.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,27.0);
        al.add(1,-40.0);
        al.add(2,18.0);
        al.add(3,-1.0);
        al.add(4,25.0);
        al.add(5,3.0);
        al.add(6,35.0);
        al.add(7,0.0);
        coordenadas.put(2,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('V', 43.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);
        al.add(1,-34.0);
        al.add(2,8.0);
        al.add(3,-46.0);
        al.add(4,14.0);
        al.add(5,-36.0);
        al.add(6,8.0);
        al.add(7,-1.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,8.0);
        al.add(1,-1.0);
        al.add(2,13.0);
        al.add(3,2.0);
        al.add(4,30.0);
        al.add(5,3.0);
        al.add(6,25.0);
        al.add(7,-40.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,25.0);
        al.add(1,-40.0);
        al.add(2,30.0);
        al.add(3,-40.0);
        al.add(4,30.0);
        al.add(5,-40.0);
        al.add(6,43.0);
        al.add(7,-40.0);
        coordenadas.put(3,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('W', 50.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);
        al.add(1,-30.0);
        al.add(2,6.0);
        al.add(3,-60.0);
        al.add(4,5.0);
        al.add(5,55.0);
        al.add(6,22.0);
        al.add(7,-40.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,22.0);
        al.add(1,-40.0);
        al.add(2,5.0);
        al.add(3,11.0);
        al.add(4,42.0);
        al.add(5,13.0);
        al.add(6,42.0);
        al.add(7,-40.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,40.0);
        al.add(1,-40.0);
        al.add(2,40.0);
        al.add(3,-40.0);
        al.add(4,40.0);
        al.add(5,-40.0);
        al.add(6,50.0);
        al.add(7,-40.0);
        coordenadas.put(3,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('X', 35.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);
        al.add(1,-30.0);
        al.add(2,11.0);
        al.add(3,-40.0);
        al.add(4,24.0);
        al.add(5,6.0);
        al.add(6,35.0);
        al.add(7,0.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,0.0);
        al.add(1,0.0);
        al.add(2,17.0);
        al.add(3,-40.0);
        al.add(4,34.0);
        al.add(5,-36.0);
        al.add(6,35.0);
        al.add(7,-40.0);
        coordenadas.put(2,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('Y', 34.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);
        al.add(1,-34.0);
        al.add(2,10.0);
        al.add(3,-64.0);
        al.add(4,5.0);
        al.add(5,53.0);
        al.add(6,28.0);
        al.add(7,-29.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,28.0);
        al.add(1,-29.0);
        al.add(2,10.0);
        al.add(3,85.0);
        al.add(4,-13.0);
        al.add(5,-7.0);
        al.add(6,34.0);
        al.add(7,0.0);
        coordenadas.put(2,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('Z', 29.0);
        coordenadas = new HashMap<>();

        al=new ArrayList<>();
        al.add(0,0.0);
        al.add(1,-34.0);
        al.add(2,16.0);
        al.add(3,-65.0);
        al.add(4,-23.0);
        al.add(5,-33.0);
        al.add(6,30.0);
        al.add(7,-40.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,0.0);
        al.add(1,0.0);
        al.add(2,1.0);
        al.add(3,-12.0);
        al.add(4,20.0);
        al.add(5,-31.0);
        al.add(6,30.0);
        al.add(7,-40.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,0.0);
        al.add(1,0.0);
        al.add(2,3.0);
        al.add(3,-9.0);
        al.add(4,14.0);
        al.add(5,3.0);
        al.add(6,30.0);
        al.add(7,0.0);
        coordenadas.put(3,al);

        al=new ArrayList<>();
        al.add(0,1.0);
        al.add(1,-15.0);
        al.add(2,10.0);
        al.add(3,-22.0);
        al.add(4,18.0);
        al.add(5,-14.0);
        al.add(6,29.0);
        al.add(7,-18.0);
        coordenadas.put(4,al);

        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('.', 8.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,4.0);
        al.add(1,0.0);
        al.add(2,4.0);
        al.add(3,0.0);
        al.add(4,4.0);
        al.add(5,0.0);
        al.add(6,4.0);
        al.add(7,0.0);
        coordenadas.put(1,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra(',', 8.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,4.0);
        al.add(1,0.0);
        al.add(2,4.0);
        al.add(3,0.0);
        al.add(4,4.0);
        al.add(5,0.0);
        al.add(6,4.0);
        al.add(7,0.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,4.0);
        al.add(1,0.0);
        al.add(2,3.0);
        al.add(3,1.0);
        al.add(4,3.0);
        al.add(5,2.0);
        al.add(6,2.0);
        al.add(7,3.0);
        coordenadas.put(2,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra(';', 8.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,4.0);
        al.add(1,-15.0);
        al.add(2,4.0);
        al.add(3,-15.0);
        al.add(4,4.0);
        al.add(5,-15.0);
        al.add(6,4.0);
        al.add(7,-15.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,4.0);
        al.add(1,0.0);
        al.add(2,4.0);
        al.add(3,0.0);
        al.add(4,4.0);
        al.add(5,0.0);
        al.add(6,4.0);
        al.add(7,0.0);
        coordenadas.put(2,al);
        al=new ArrayList<>();
        al.add(0,4.0);
        al.add(1,0.0);
        al.add(2,3.0);
        al.add(3,1.0);
        al.add(4,3.0);
        al.add(5,2.0);
        al.add(6,2.0);
        al.add(7,3.0);
        coordenadas.put(3,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra(':', 8.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,4.0);
        al.add(1,-15.0);
        al.add(2,4.0);
        al.add(3,-15.0);
        al.add(4,4.0);
        al.add(5,-15.0);
        al.add(6,4.0);
        al.add(7,-15.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,4.0);
        al.add(1,0.0);
        al.add(2,4.0);
        al.add(3,0.0);
        al.add(4,4.0);
        al.add(5,0.0);
        al.add(6,4.0);
        al.add(7,0.0);
        coordenadas.put(2,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('"', 12.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,4.0);
        al.add(1,-20.0);
        al.add(2,4.0);
        al.add(3,-20.0);
        al.add(4,4.0);
        al.add(5,-20.0);
        al.add(6,4.0);
        al.add(7,-20.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,4.0);
        al.add(1,-20.0);
        al.add(2,4.0);
        al.add(3,-19.0);
        al.add(4,4.0);
        al.add(5,-18.0);
        al.add(6,4.0);
        al.add(7,-17.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,8.0);
        al.add(1,-20.0);
        al.add(2,8.0);
        al.add(3,-20.0);
        al.add(4,8.0);
        al.add(5,-20.0);
        al.add(6,8.0);
        al.add(7,-20.0);
        coordenadas.put(3,al);
        al=new ArrayList<>();
        al.add(0,8.0);
        al.add(1,-20.0);
        al.add(2,8.0);
        al.add(3,-19.0);
        al.add(4,8.0);
        al.add(5,-18.0);
        al.add(6,8.0);
        al.add(7,-17.0);
        coordenadas.put(4,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('´', 8.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,4.0);
        al.add(1,-20.0);
        al.add(2,4.0);
        al.add(3,-20.0);
        al.add(4,4.0);
        al.add(5,-20.0);
        al.add(6,4.0);
        al.add(7,-20.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,4.0);
        al.add(1,-20.0);
        al.add(2,3.0);
        al.add(3,-19.0);
        al.add(4,3.0);
        al.add(5,-18.0);
        al.add(6,2.0);
        al.add(7,-17.0);
        coordenadas.put(2,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('¿', 40.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,30.0);
        al.add(1,10.0);
        al.add(2,0.0);
        al.add(3,18.0);
        al.add(4,1.0);
        al.add(5,-20.0);
        al.add(6,30.0);
        al.add(7,-16.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,30.0);
        al.add(1,-28.0);
        al.add(2,30.0);
        al.add(3,-28.0);
        al.add(4,30.0);
        al.add(5,-28.0);
        al.add(6,30.0);
        al.add(7,-16.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,30.0);
        al.add(1,-40.0);
        al.add(2,29.0);
        al.add(3,-40.0);
        al.add(4,32.0);
        al.add(5,-40.0);
        al.add(6,31.0);
        al.add(7,-40.0);
        coordenadas.put(3,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('?', 30.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,10.0);
        al.add(1,-40.0);
        al.add(2,44.0);
        al.add(3,-47.0);
        al.add(4,41.0);
        al.add(5,-6.0);
        al.add(6,13.0);
        al.add(7,-12.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,13.0);
        al.add(1,-12.0);
        al.add(2,13.0);
        al.add(3,-12.0);
        al.add(4,13.0);
        al.add(5,-12.0);
        al.add(6,13.0);
        al.add(7,0.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,13.0);
        al.add(1,10.0);
        al.add(2,11.0);
        al.add(3,10.0);
        al.add(4,14.0);
        al.add(5,10.0);
        al.add(6,12.0);
        al.add(7,10.0);
        coordenadas.put(3,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);


        l = new Letra('!', 16.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,10.0);
        al.add(1,-24.0);
        al.add(2,10.0);
        al.add(3,-24.0);
        al.add(4,10.0);
        al.add(5,-24.0);
        al.add(6,10.0);
        al.add(7,-8.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,10.0);
        al.add(1,-3.0);
        al.add(2,10.0);
        al.add(3,-2.0);
        al.add(4,10.0);
        al.add(5,-4.0);
        al.add(6,10.0);
        al.add(7,-3.0);
        coordenadas.put(2,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('¡', 16.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,10.0);
        al.add(1,-15.0);
        al.add(2,10.0);
        al.add(3,-15.0);
        al.add(4,10.0);
        al.add(5,-15.0);
        al.add(6,10.0);
        al.add(7,0.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,10.0);
        al.add(1,-24.0);
        al.add(2,10.0);
        al.add(3,-25.0);
        al.add(4,10.0);
        al.add(5,-26.0);
        al.add(6,10.0);
        al.add(7,-24.0);
        coordenadas.put(2,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('(', 35.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,24.0);
        al.add(1,-40.0);
        al.add(2,5.0);
        al.add(3,-30.0);
        al.add(4,5.0);
        al.add(5,-10.0);
        al.add(6,24.0);
        al.add(7,0.0);
        coordenadas.put(1,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra(')', 30.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,7.0);
        al.add(1,-40.0);
        al.add(2,25.0);
        al.add(3,-30.0);
        al.add(4,25.0);
        al.add(5,-10.0);
        al.add(6,7.0);
        al.add(7,0.0);
        coordenadas.put(1,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('-', 25.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,5.0);
        al.add(1,-10.0);
        al.add(2,5.0);
        al.add(3,-10.0);
        al.add(4,5.0);
        al.add(5,-10.0);
        al.add(6,10.0);
        al.add(7,-10.0);
        coordenadas.put(1,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('_', 35.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,5.0);
        al.add(1,0.0);
        al.add(2,5.0);
        al.add(3,0.0);
        al.add(4,5.0);
        al.add(5,0.0);
        al.add(6,15.0);
        al.add(7,0.0);
        coordenadas.put(1,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('[', 25.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,5.0);
        al.add(1,-30.0);
        al.add(2,5.0);
        al.add(3,-30.0);
        al.add(4,5.0);
        al.add(5,-30.0);
        al.add(6,15.0);
        al.add(7,-30.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,5.0);
        al.add(1,0.0);
        al.add(2,5.0);
        al.add(3,0.0);
        al.add(4,5.0);
        al.add(5,0.0);
        al.add(6,15.0);
        al.add(7,0.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,5.0);
        al.add(1,0.0);
        al.add(2,5.0);
        al.add(3,0.0);
        al.add(4,5.0);
        al.add(5,0.0);
        al.add(6,5.0);
        al.add(7,-30.0);
        coordenadas.put(3,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);


        l = new Letra(']', 25.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,5.0);
        al.add(1,-30.0);
        al.add(2,5.0);
        al.add(3,-30.0);
        al.add(4,5.0);
        al.add(5,-30.0);
        al.add(6,15.0);
        al.add(7,-30.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,5.0);
        al.add(1,0.0);
        al.add(2,5.0);
        al.add(3,0.0);
        al.add(4,5.0);
        al.add(5,0.0);
        al.add(6,15.0);
        al.add(7,0.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,15.0);
        al.add(1,0.0);
        al.add(2,15.0);
        al.add(3,0.0);
        al.add(4,15.0);
        al.add(5,0.0);
        al.add(6,15.0);
        al.add(7,-30.0);
        coordenadas.put(3,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('{', 30.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,20.0);
        al.add(1,-20.0);
        al.add(2,31.0);
        al.add(3,-27.0);
        al.add(4,14.0);
        al.add(5,-40.0);
        al.add(6,29.0);
        al.add(7,-40.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,20.0);
        al.add(1,-20.0);
        al.add(2,30.0);
        al.add(3,-16.0);
        al.add(4,8.0);
        al.add(5,-1.0);
        al.add(6,31.0);
        al.add(7,0.0);
        coordenadas.put(2,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('}', 30.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,20.0);
        al.add(1,-20.0);
        al.add(2,8.0);
        al.add(3,-23.0);
        al.add(4,32.0);
        al.add(5,-40.0);
        al.add(6,11.0);
        al.add(7,-40.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,20.0);
        al.add(1,-20.0);
        al.add(2,9.0);
        al.add(3,-12.0);
        al.add(4,33.0);
        al.add(5,0.0);
        al.add(6,9.0);
        al.add(7,0.0);
        coordenadas.put(2,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('>', 40.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,20.0);
        al.add(1,-20.0);
        al.add(2,20.0);
        al.add(3,-20.0);
        al.add(4,5.0);
        al.add(5,-34.0);
        al.add(6,5.0);
        al.add(7,-34.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,20.0);
        al.add(1,-20.0);
        al.add(2,20.0);
        al.add(3,-20.0);
        al.add(4,5.0);
        al.add(5,-4.0);
        al.add(6,5.0);
        al.add(7,-4.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,25.0);
        al.add(1,-20.0);
        al.add(2,25.0);
        al.add(3,-20.0);
        al.add(4,10.0);
        al.add(5,-34.0);
        al.add(6,10.0);
        al.add(7,-34.0);
        coordenadas.put(3,al);

        al=new ArrayList<>();
        al.add(0,25.0);
        al.add(1,-20.0);
        al.add(2,25.0);
        al.add(3,-20.0);
        al.add(4,10.0);
        al.add(5,-4.0);
        al.add(6,10.0);
        al.add(7,-4.0);
        coordenadas.put(4,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);

        l = new Letra('<', 40.0);
        coordenadas = new HashMap<>();
        al=new ArrayList<>();
        al.add(0,5.0);
        al.add(1,-20.0);
        al.add(2,5.0);
        al.add(3,-20.0);
        al.add(4,20.0);
        al.add(5,-34.0);
        al.add(6,20.0);
        al.add(7,-34.0);
        coordenadas.put(1,al);

        al=new ArrayList<>();
        al.add(0,5.0);
        al.add(1,-20.0);
        al.add(2,5.0);
        al.add(3,-20.0);
        al.add(4,20.0);
        al.add(5,-4.0);
        al.add(6,20.0);
        al.add(7,-4.0);
        coordenadas.put(2,al);

        al=new ArrayList<>();
        al.add(0,10.0);
        al.add(1,-20.0);
        al.add(2,10.0);
        al.add(3,-20.0);
        al.add(4,25.0);
        al.add(5,-34.0);
        al.add(6,25.0);
        al.add(7,-34.0);
        coordenadas.put(3,al);

        al=new ArrayList<>();
        al.add(0,10.0);
        al.add(1,-20.0);
        al.add(2,10.0);
        al.add(3,-20.0);
        al.add(4,25.0);
        al.add(5,-4.0);
        al.add(6,25.0);
        al.add(7,-4.0);
        coordenadas.put(4,al);
        l.setCoordenadas(coordenadas);
        this.letras.add(l);
    }
    /**
     * @return the oracion
     */
    public String getPalabra() {
        return oracion;
    }

    /**
     * @param palabra the oracion to set
     */
    public void setPalabra(String palabra) {
        this.oracion = palabra;
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

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public boolean getControl(){
        return this.control;
    }

    public void setControl(boolean control){
        this.control=control;
    }

    public boolean isInvert() {
        return invert;
    }

    public void setInvert(boolean invert) {
        this.invert = invert;
    }


}
