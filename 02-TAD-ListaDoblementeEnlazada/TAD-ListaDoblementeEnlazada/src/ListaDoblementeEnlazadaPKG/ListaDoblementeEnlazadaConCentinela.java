package ListaDoblementeEnlazadaPKG;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author diotallevi
 */
public class ListaDoblementeEnlazadaConCentinela {
    
    private final Nodo centinela;

    public ListaDoblementeEnlazadaConCentinela() {
        centinela = new Nodo(0);
        centinela.prev = centinela;
        centinela.next = centinela;
    }
    
    // LIST-INSERTAR(L, x)
    public void insertar (int entradaValor) {
        Nodo nuevoObjetoNodo = new Nodo(entradaValor);
        nuevoObjetoNodo.next = centinela.next;
        //Validar si es el único nodo
        centinela.next.prev = nuevoObjetoNodo;
        centinela.next =  nuevoObjetoNodo;
        nuevoObjetoNodo.prev = centinela;
    }
    
    // LIST-ELIMINAR(L, x)
    public void delete(Nodo entradaValor) {
        
        /* //Eliminar de lista
        if (entradaValor.prev != null) {
            entradaValor.prev.next = entradaValor.next;
        } else {
            cabeza = entradaValor.next;
        }

        //si es único en la lista
        if (entradaValor.next != null) {
            entradaValor.next.prev = entradaValor.prev;
        }
         */

        entradaValor.prev.next = entradaValor.next;
        entradaValor.next.prev = entradaValor.prev;
    }
    
    // LIST-SEARCH(L, k)
    public Nodo search(int entradaValor) {
        /* Nodo objetoNodo = cabeza;
        while (objetoNodo != null && objetoNodo.getKey() != entradaValor) {
            objetoNodo = objetoNodo.next;
        }
        return objetoNodo; */

        Nodo nuevoObjetoNodo = centinela.next;
        while (nuevoObjetoNodo != centinela && nuevoObjetoNodo.getKey() != entradaValor) {
            nuevoObjetoNodo = nuevoObjetoNodo.next;
        }
        return nuevoObjetoNodo;
    }
    
    public void recorrer() {
    Nodo actual = centinela.next;

        while (actual != centinela) {
            System.out.print( "[" + actual.getKey() + "] <-> ");
            actual = actual.next;
        }
        System.out.println("CENTINELA");
    }
    
}
