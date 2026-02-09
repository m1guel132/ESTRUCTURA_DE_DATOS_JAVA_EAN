/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listaPKG;

/**
 *
 * @author diotallevi
 */
public class Fila {
    
    private Nodo cabeza;
    private Nodo cola;
    
    //constructor
    public Fila(){
        cabeza=null;
        cola=null;
    }
    
    public void agregarNodo(int dato){
        Nodo nuevo=new Nodo();
        nuevo.setDato(dato);
        if (cola==null){
            cabeza=nuevo;
            cola=nuevo;
        }else{
            cola.sig=nuevo;
            cola=nuevo;
        }
    }
    
    // Método para eliminar el último nodo de la fila (LIFO)
    public int eliminarNodo() {
        //Si la lista está vacía
        if (cabeza == null) {
            throw new RuntimeException("Fila vac�a");
        } else if (cabeza == cola) { // Solo un nodo en la fila
            int dato = cola.getDato();
            cabeza = null;
            cola = null;
            return dato;
        } else {
            Nodo actual = cabeza;
            /*Este ciclo se hace para conocer el antepenúltimo 
            /elemento de la fila*/
            while (actual.sig != cola) {
                actual = actual.sig;
            }
            int dato = cola.getDato();
            cola = actual;
            cola.sig = null;
            return dato;
        }
    }
    
    public String obtenerLista(){
        String contenido="";
        if (cabeza != null){
            Nodo actual=cabeza;
            while (actual != null) {
                contenido = contenido + actual.getDato() + " ";
                actual = actual.sig; // Mover al siguiente nodo
            }
            return contenido;
        }else{
            return "Cola vacía";
        } 
    }
    
    public int buscarNodo(int datoEntrada){
        Nodo actual=cabeza;
        while (actual != null){
            if (actual.getDato()== datoEntrada){
                return actual.getDato();
            }
        actual = actual.sig;
        }
        return -1;//Nodo No encontrado
    }  
}
