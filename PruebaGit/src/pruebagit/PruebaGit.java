/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebagit;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author mjorquera
 */
public class PruebaGit implements Fifo{
   
    
    private int pos_actual;
    private int elemento;
    private String[] pila;
    private int tamano;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Tamaño : ");
        Scanner entrada=new Scanner(System.in);
        PruebaGit pila = new PruebaGit(Integer.parseInt(entrada.next()));
        String textual="";
        
        while(!textual.equals("0")){
            System.out.println("valor: ");
            textual=entrada.next();
            if(textual.equals("-"))
                System.out.println(pila.dequeue());
            else
                pila.inqueue(textual);
        }
    }
    
    public PruebaGit(int tamano){
        this.tamano=tamano;
        pila=new String[this.tamano];
        pos_actual=0;
        elemento=0;
    }

    @Override
    public boolean isEmpty() {
        if(elemento==pos_actual)
            return true;
        return false;
    }

    @Override
    public void inqueue(String item) {
        pila[pos_actual]=item;
        pos_actual++;
    }

    @Override
    public String dequeue() {
        if(!isEmpty()){
            String palabra=pila[elemento];
            elemento++;
            return palabra;
        }
        return "NO hay ";
            
    }
    
    
}
