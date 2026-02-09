/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listaPKG;

/**
 *
 * @author diotallevi
 */
public class Pila {
    
    private Nodo cabeza;
    
    public Pila (){
        cabeza=null;
    }
    
    public void agregarNodo (int dato){
        Nodo nuevo=new Nodo();
        nuevo.setDato(dato);
        if (cabeza == null){
            cabeza=nuevo;
        }else{
            nuevo.sig=cabeza;
            cabeza=nuevo;
        }
    }
    
    public int eliminarNodo(){
        if (cabeza == null) {
            throw new RuntimeException("Pila vacía");
        } else {
            int dato = cabeza.getDato();
            cabeza = cabeza.sig;
            return dato;
        }
    }
    
    // Método para buscar un nodo en la pila
    public int buscarNodo(int valor) {
        Nodo actual = cabeza; //1 
        while (actual != null) { //N
            if (actual.getDato() == valor) { //N^2
                return actual.getDato(); // N
            }
            actual = actual.sig;//N
        }
        //1+N+N^2+N+N redondeo N^2 log (n)
        return -1; // Nodo no encontrado
    }
    
    public String obtenerPila(){
        String contenido="";
        if (cabeza != null){
            Nodo actual=cabeza;
            while (actual != null) {
                contenido = contenido + actual.getDato() + " ";
                actual = actual.sig; // Mover al siguiente nodo
            }
            return contenido;
        }else{
            return "Pila vacìa";
        } 
    }
    
}
