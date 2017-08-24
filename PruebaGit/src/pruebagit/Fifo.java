/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebagit;

/**
 *
 * @author mjorquera
 */
public interface Fifo {
    public boolean isEmpty();
    public void inqueue(String item);
    public String dequeue();
}
