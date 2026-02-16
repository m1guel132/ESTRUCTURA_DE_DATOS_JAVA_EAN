/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colasPrioridadPKG;

/**
 *
 * @author diotallevi
 */
public class Nodo {
    
    private String dato;
    private int prioridad;
    Nodo siguiente;

    //constructor
    public Nodo(String dato, int prioridad) {
        this.dato = dato;
        this.prioridad = prioridad;
        this.siguiente = null;
    }

    public String getDato() {
        return dato;
    }

    public int getPrioridad() {
        return prioridad;
    }
}
