/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caligrafia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author mjorquera
 */
public class Ventana extends JFrame{
    
    private PanelTexto panelTexto;
    private JButton ejecutar_btn;
    private JTextField texto_txt;
    private JPanel herramientas_panel;
    
    
    public Ventana(){
        init();
        acciones();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Inicializa los elementos que contendra la ventana
     */
    private void init(){
        setSize(600,400);
        setTitle("Herramienta Imprenta");
        
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        this.texto_txt = new JTextField();
        this.ejecutar_btn = new JButton("Ejecutar");
        this.herramientas_panel = new JPanel();
        this.herramientas_panel.setLayout(new GridLayout(1,2));
        this.herramientas_panel.add(this.texto_txt);
        this.herramientas_panel.add(this.ejecutar_btn);
        
        this.panelTexto = new PanelTexto();
        
        this.panelTexto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.herramientas_panel.setBorder(BorderFactory.createLineBorder(Color.yellow));
        
        c.add(this.panelTexto,BorderLayout.CENTER);
        c.add(this.herramientas_panel,BorderLayout.SOUTH);
        
        
    }
    
    private void acciones(){
        this.ejecutar_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelTexto.setPalabra(texto_txt.getText());
                panelTexto.separarLetras();
                repaint();
            
            }
        });
        
        
    }
}
