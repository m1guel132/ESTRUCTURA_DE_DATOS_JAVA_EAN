/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaDoblementeEnlazadaPKG;

/**
 *
 * @author diotallevi
 */
public class ListaDoblementeEnlazada {
    
    Nodo cabeza;
    
    // LIST-INSERTAR(L, x)
    public void insertar (int entradaValor) {
        Nodo nuevoObjetoNodo = new Nodo(entradaValor);
        nuevoObjetoNodo.next = cabeza;
        //Validar si es el único nodo
        if (cabeza != null) {
            cabeza.prev = nuevoObjetoNodo;
        }
        cabeza = nuevoObjetoNodo;
        nuevoObjetoNodo.prev = null;
    }
    
    // LIST-ELIMINAR(L, x)
    public void delete(Nodo entradaValor) {
        
        //Eliminar de lista
        if (entradaValor.prev != null) {
            entradaValor.prev.next = entradaValor.next;
        } else {
            cabeza = entradaValor.next;
        }

        //si es único en la lista
        if (entradaValor.next != null) {
            entradaValor.next.prev = entradaValor.prev;
        }
    }
    
    // LIST-SEARCH(L, k)
    public Nodo search(int entradaValor) {
        Nodo objetoNodo = cabeza;
        while (objetoNodo != null && objetoNodo.getKey() != entradaValor) {
            objetoNodo = objetoNodo.next;
        }
        return objetoNodo;
    }
    
    public void recorrer() {
    Nodo actual = cabeza;

        while (actual != null) {
            System.out.print(actual.getKey() + " <-> ");
            actual = actual.next;
        }
        System.out.println("Null");
    }
    
}
