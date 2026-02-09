/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listaPKG;

/**
 *
 * @author diotallevi
 */
public class Nodo {
    
    private int dato;
    Nodo sig;
    
    //Constructor
    public Nodo(){
        dato=0;
        sig=null;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }   
}
