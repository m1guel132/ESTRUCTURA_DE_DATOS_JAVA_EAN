/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;

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
}
