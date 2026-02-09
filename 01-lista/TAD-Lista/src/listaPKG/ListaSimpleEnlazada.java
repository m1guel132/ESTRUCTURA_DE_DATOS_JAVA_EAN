/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listaPKG;

/**
 *
 * @author diotallevi
 * Bicolas - Lista doble?
 */
public class ListaSimpleEnlazada {
    
    private Nodo cabeza;
    
    public ListaSimpleEnlazada() {
        this.cabeza = null;
    }
    
    // Insertar al inicio de la lista
    public void insertarInicio(int dato) {
        Nodo nuevo = new Nodo(); //1
        nuevo.setDato(dato);//1
        nuevo.sig = cabeza;//1
        cabeza = nuevo;//1
        //O(1) -> Tiempo
    }

    // Insertar al final de la lista
    public void insertarFinal(int dato) {
        Nodo nuevo = new Nodo();
        nuevo.setDato(dato);
        if (cabeza == null) {
            //Si la lista doble (bicola) está vacía
            cabeza = nuevo;
        } else {
            //Asigna al final
            Nodo actual = cabeza;
            while (actual.sig != null) {
                //Recorre hasta el final y luego asigna
                actual = actual.sig;
            }
            actual.sig = nuevo;
        }
    }
    
    // Insertar en una posición específica
    public void insertarEnPosicion(int dato, int posicion) {
        if (posicion == 0) {
            //Recursión indirecta
            insertarInicio(dato);
        } else {
            Nodo nuevo = new Nodo();
            nuevo.setDato(dato);
            Nodo actual = cabeza;
            for (int i = 0; i < posicion - 1 && actual != null; i++) {
                //Desconectar la fila y luego conectar
                actual = actual.sig;
            }
            if (actual != null) {
                nuevo.sig = actual.sig;
                actual.sig = nuevo;
            } else {
                System.out.println("Posición fuera de rango");
            }
        }
    }
    
    // Eliminar un nodo específico por valor
    public void eliminarNodo(int valor) {
        if (cabeza == null) {
            System.out.println("Lista vacía");
            return;
        }

        if (cabeza.getDato() == valor) {
            cabeza = cabeza.sig;
        } else {
            Nodo actual = cabeza;
            while (actual.sig != null && actual.sig.getDato() != valor) {
                actual = actual.sig;
            }
            if (actual.sig != null) {
                actual.sig = actual.sig.sig;
            } else {
                System.out.println("Valor no encontrado en la lista");
            }
        }
    }
    
    // Buscar un nodo en la lista
    public int buscarNodo(int valor) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getDato() == valor) {
                return actual.getDato();
            }
            actual = actual.sig;
        }
        return -1;
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
            return "Lista vacìa";
        } 
    }
    
}
