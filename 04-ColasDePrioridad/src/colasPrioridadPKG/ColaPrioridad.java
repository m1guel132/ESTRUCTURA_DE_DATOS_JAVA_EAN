/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colasPrioridadPKG;

/**
 *
 * @author diotallevi
 */
public class ColaPrioridad {
    private Nodo frente; // Apunta al elemento con mayor prioridad (el primero)

    public ColaPrioridad() {
        this.frente = null;
    }

    // Verifica si la cola está vacía
    public boolean estaVacia() {
        return frente == null;
    }

    // Método para insertar (Enqueue) manteniendo el orden de prioridad
    public void insertar(String dato, int prioridad) {
        Nodo nuevoNodo = new Nodo(dato, prioridad);

        // Caso A: La cola está vacía o el nuevo nodo tiene MÁS prioridad que el frente
        // (Recordemos: menor número = mayor prioridad)
        if (estaVacia() || frente.getPrioridad() > prioridad) {
            nuevoNodo.siguiente = frente;
            frente = nuevoNodo;
        } 
        // Caso B: Buscar la posición correcta en el resto de la lista
        else {
            Nodo actual = frente;
            // Avanzamos mientras el siguiente nodo tenga MÁS o IGUAL prioridad
            while (actual.siguiente != null && actual.siguiente.getPrioridad() <= prioridad) {
                actual = actual.siguiente;
            }
            // Insertamos el nuevo nodo en la posición encontrada
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
        }
        System.out.println("Insertado: " + dato + " (Prioridad: " + prioridad + ")");
    }

    // Método para extraer el elemento de mayor prioridad (Dequeue)
    public String extraer() {
        if (estaVacia()) {
            return "La cola está vacía.";
        }
        String datoExtraido = frente.getDato();
        frente = frente.siguiente; // Movemos el frente al siguiente nodo
        return datoExtraido;
    }

    // Método para ver quién es el próximo sin sacarlo (Peek)
    public String verFrente() {
        if (estaVacia()) {
            return "La cola está vacía.";
        }
        return frente.getDato() + " (Prioridad: " + frente.getPrioridad() + ")";
    }   
}
