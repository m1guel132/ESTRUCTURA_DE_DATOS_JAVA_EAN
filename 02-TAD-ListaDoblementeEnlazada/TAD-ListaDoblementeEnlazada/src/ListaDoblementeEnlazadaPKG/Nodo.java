/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaDoblementeEnlazadaPKG;

/**
 *
 * @author diotallevi
 */
public class Nodo {
    
    private int key;
    Nodo next;
    Nodo prev;

    //Constructor
    public Nodo(int key) { 
        this.key = key; 
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
      
}
