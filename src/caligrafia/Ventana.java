/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 *
 * @author mjorquera
 */
public class Ventana extends JFrame{
    
    private PanelTexto panelTexto;
    private JTextField textoTxt, regexTxt;
    private JPanel herramientasPanel;
    private JToggleButton puntosControlTggl;
    private JScrollPane scroll;
    //private JScrollBar barraSb;
    
    public Ventana(){
        init();
        acciones();
        
    }
    
    /**
     * Inicializa los elementos que contendra la ventana
     */
    private void init(){
        setSize(800,600);
        setTitle("Herramienta Imprenta");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        this.textoTxt = new JTextField();
        this.puntosControlTggl=new JToggleButton("Desactivado");
        this.herramientasPanel = new JPanel();
        this.herramientasPanel.setLayout(new GridLayout(2,2));
        
        //Paneles sur
        JPanel textoEntrada = new JPanel();
        textoEntrada.setLayout(new GridLayout(1,2));
        textoEntrada.add(new JLabel("Texto") );
        textoEntrada.add(this.textoTxt);
        this.textoTxt.setLocation(-40, 0);
        JPanel regexPanel = new JPanel();
        regexPanel.setLayout(new GridLayout(1,2));
        regexPanel.add(new JLabel("RegEx"));
        this.regexTxt = new JTextField();
        regexPanel.add(this.regexTxt);
        
        this.herramientasPanel.add(textoEntrada);
        this.herramientasPanel.add(regexPanel);
        this.herramientasPanel.add(new JLabel());this.herramientasPanel.add(new JLabel());
        this.panelTexto = new PanelTexto();
        
        
        
        this.panelTexto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //this.herramientasPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        JPanel panelSuperior= new JPanel();
        panelSuperior.setLayout(new GridLayout(1,4));
        panelSuperior.add(new JLabel("Puntos de Control"));
        panelSuperior.add(this.puntosControlTggl);panelSuperior.add(new JLabel());panelSuperior.add(new JLabel());        
        
        //ScrollBar
        //this.barraSb = new JScrollBar();
        //this.panelTexto.add(this.barraSb);
        this.panelTexto.setPreferredSize(new Dimension(790, 500));
        this.scroll = new JScrollPane();
        this.scroll.setBounds(5, 10, 900, 800);
        this.scroll.setViewportView(panelTexto);
                       
        
        c.add(panelSuperior,BorderLayout.NORTH);
        c.add(scroll,BorderLayout.CENTER);
        c.add(this.herramientasPanel,BorderLayout.SOUTH);
        
        
    }
    
    private void acciones(){
        this.textoTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent k){
                actualizarDatos();
            }
            
        });
        this.regexTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent k){
                actualizarDatos();
            }
            
        });
      
        this.puntosControlTggl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                String texto = puntosControlTggl.getText();
                if(texto.equals("Activado")){
                    puntosControlTggl.setText("Desactivado");
                    panelTexto.setControl(false);
                    
                }else if(texto.equals("Desactivado")){
                    puntosControlTggl.setText("Activado");
                    panelTexto.setControl(true);
                }              
                actualizarDatos();
            }
        });
        
        this.addComponentListener(new ComponentListener(){
            @Override
            public void componentResized(ComponentEvent ce) {                
                actualizarDatos();
            }

            @Override
            public void componentMoved(ComponentEvent ce) {
                
            }

            @Override
            public void componentShown(ComponentEvent ce) {
                
            }

            @Override
            public void componentHidden(ComponentEvent ce) {
                
            }
        });
        this.panelTexto.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {                
                scroll.revalidate();
            }
        });

        this.scroll.getViewport().addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent ce) {
                scroll.revalidate();
                actualizarDatos();
            }            
        });        
    }
    private void actualizarDatos(){
        panelTexto.setPalabra(textoTxt.getText());
        panelTexto.setRegex(regexTxt.getText());
        panelTexto.setStartx(10);
        panelTexto.setStarty(65 * panelTexto.getS());
        panelTexto.setDx(10);
        panelTexto.setDy(65 * panelTexto.getS());                

        repaint();        
    }    
}
