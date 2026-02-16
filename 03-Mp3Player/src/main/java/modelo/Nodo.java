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

public class Nodo {
    
    public File cancion;
    Nodo next;
    Nodo prev;
    
    public Nodo(File cancion) {
        this.cancion = cancion;
    }
    
    public File getKey() {
        return cancion;
    }
    
    public void setKey(File cancion) {
        this.cancion = cancion;
    }
}
