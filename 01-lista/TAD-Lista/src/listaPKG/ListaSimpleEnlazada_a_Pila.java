package listaPKG;

public class ListaSimpleEnlazada_a_Pila {
    private Nodo cabeza;

    public ListaSimpleEnlazada_a_Pila() {
        this.cabeza = null;
    }

    // Insertar al inicio de la lista
    public void agregarNodo(int dato) {
        Nodo nuevo = new Nodo(); //1
        nuevo.setDato(dato);//1
        nuevo.sig = cabeza;//1
        cabeza = nuevo;//1
        //O(1) -> Tiempo
    }

    // Eliminar un nodo específico por valor
    public void eliminarNodo() {
        if (cabeza == null) {
            System.out.println("Lista vacía");
            return;
        }

        Nodo actual = cabeza;
        if (actual.sig != null) {
            cabeza = actual.sig;
        } else {
            System.out.println("Valor no encontrado en la lista");
        }
    }

    // Buscar un nodo en la lista
    public int buscarNodo(int valor) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getDato() == valor) {
                return actual.getDato() ;
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
                contenido += "[" + actual.getDato() + "] -> ";
                actual = actual.sig; // Mover al siguiente nodo
            }

            contenido += "NULL";
            return contenido;
        }else{
            return "Lista vacìa";
        }
    }
}
