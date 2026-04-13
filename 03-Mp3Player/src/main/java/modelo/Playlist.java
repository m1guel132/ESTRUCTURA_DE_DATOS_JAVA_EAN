/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author migue
 */
public class Playlist {
    
    private Nodo head;
    private Nodo actual;
    
    public void agregar (File cancion) {
        
        Nodo nuevo = new Nodo(cancion);
        
        if (head == null) {
            head = nuevo;
            actual = nuevo;
        } else {
            Nodo temp = head;
            
            while (temp.next != null) {
                temp = temp.next;
            }
            
            temp.next = nuevo;
            nuevo.prev = temp;
        }
    }
    
    public File getActual() {
        return actual != null ? actual.cancion : null;
    }
    
    public File siguiente() {
        if (actual != null && actual.next != null) {
            actual = actual.next;
            return actual.cancion;
        }
        return null;
    }
    
    public File anterior() {
        if (actual != null && actual.prev != null) {
            actual = actual.prev;
            return actual.cancion;
        }
        return null;
    }
    
    public boolean estaVacia() {
        return head == null;
    }
    
    public void limpiar() {
        head = null;
        actual = null;
    }
    
    public File[] obtenerTodas() {
        
        int contador = 0;
        Nodo temp = head;
        
        while (temp != null) {
            contador++;
            temp = temp.next;
        }
        
        File[] arreglo = new File[contador];
        
        temp = head;
        int i = 0;
        
        while (temp != null) {
            arreglo[i++] = temp.cancion;
            temp = temp.next;
        }
        
        return arreglo;
    }
    
    public void reordenar(File[] ordenado) {
    
    head = null;
    actual = null;

    for (File f : ordenado) {
        Nodo nuevo = new Nodo(f);
        if (head == null) {
            head = nuevo;
            actual = head;
        } else {
            
            Nodo temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = nuevo;
            nuevo.prev = temp; 
        }
    }
}
}
