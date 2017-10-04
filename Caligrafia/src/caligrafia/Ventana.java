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
    private JPanel herramientasPanel, panelOpciones,regexPanel;
    private JButton ocultar;
    private boolean activo;
    
    public Ventana(){
        init();
        acciones();
        this.activo=false;
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
        
        JPanel fuentes = new JPanel();
        fuentes.setLayout(new GridLayout(1,3));
        fuentes.add(new JButton("Subrayado"));fuentes.add(new JButton("Cursiva"));fuentes.add(new JButton("Negrita"));
        JPanel puntos = new JPanel();
        puntos.setLayout(new GridLayout(1,2));
        puntos.add(new JButton("Puntos de control"));
        puntos.add(new JLabel(""));
        
        this.panelOpciones = new JPanel();
        this.panelOpciones.setLayout(new GridLayout(1,3));
        this.panelOpciones.add(puntos);
        this.panelOpciones.add(fuentes);
        this.panelOpciones.add(new JLabel("FUTBOLISTAS"));
                
                
        this.panelTexto = new PanelTexto();
        
        this.panelTexto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
       
        this.herramientasPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
        
        
        c.add(this.panelTexto,BorderLayout.CENTER);
        c.add(this.herramientasPanel,BorderLayout.SOUTH);
        c.add(this.panelOpciones,BorderLayout.NORTH);
        
        this.ocultar = new JButton(">");
        JPanel re = new JPanel();
        re.add(ocultar);
        this.regexPanel = new JPanel();
        
        this.regexPanel.setVisible(false);
        this.regexPanel.add(fuentes);
        
        
        c.add(re,BorderLayout.EAST);
    }
    
    private void acciones(){
        this.ocultar.addActionListener(new ActionListener() {
         
            @Override
            public void actionPerformed(ActionEvent e) {
                if(activo){
                    activo=false;
                    regexPanel.setVisible(true);
                }else{
                    activo=true;
                    regexPanel.setVisible(false);
                }
                            
            }
        });
        
        this.ejecutarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelTexto.setPalabra(textoTxt.getText());
                panelTexto.setStartx(0);
                panelTexto.setStarty(300);
                panelTexto.setDx(0);
                panelTexto.setDy(300);
                //panelTexto.separarLetras();
                //panelTexto.
                repaint();
            
            }
        });
        if(activo)
            this.regexPanel.setVisible(true);
        else
            this.regexPanel.setVisible(false);
        
    }
}
