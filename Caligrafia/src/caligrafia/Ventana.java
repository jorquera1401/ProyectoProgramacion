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
    private JButton ejecutarBtn;
    private JTextField textoTxt;
    private JPanel herramientasPanel;
    
    
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
        this.ejecutarBtn = new JButton("Ejecutar");
        this.herramientasPanel = new JPanel();
        this.herramientasPanel.setLayout(new GridLayout(1,2));
        this.herramientasPanel.add(this.textoTxt);
        this.herramientasPanel.add(this.ejecutarBtn);
        
        this.panelTexto = new PanelTexto();
        
        this.panelTexto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.herramientasPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
        
        c.add(this.panelTexto,BorderLayout.CENTER);
        c.add(this.herramientasPanel,BorderLayout.SOUTH);
        
        
    }
    
    private void acciones(){
        this.ejecutarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelTexto.setPalabra(textoTxt.getText());
                //panelTexto.separarLetras();
                //panelTexto.
                repaint();
            
            }
        });
        
        
    }
}
