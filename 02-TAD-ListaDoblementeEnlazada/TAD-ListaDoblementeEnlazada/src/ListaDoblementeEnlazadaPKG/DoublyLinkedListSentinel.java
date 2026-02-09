/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaDoblementeEnlazadaPKG;

/**
 *
 * @author diotallevi
 */
public class DoublyLinkedListSentinel {
    
    private final Nodo nil; // El objeto L.nil de Cormen
    
    //Constructor
    public DoublyLinkedListSentinel() {
        nil = new Nodo(0); // El valor de la clave en nil es irrelevante
        nil.next = nil;    // Al inicio, nil se apunta a si mismo
        nil.prev = nil;
    }
    
    // LIST-INSERT'(L, x)
    public void insert(int key) {
        Nodo x = new Nodo(key);
        x.next = nil.next;
        nil.next.prev = x;
        nil.next = x;
        x.prev = nil;
    }

    // LIST-SEARCH'(L, k)
    public Nodo search(int k) {
        Nodo x = nil.next;
        while (x != nil && x.getKey() != k) {
            x = x.next;
        }
        return x;
    }

    // LIST-DELETE'(L, x)
    public void delete(Nodo x) {
        // En Cormen, esta es la version más elegante: 2 líneas de c�digo.
        x.prev.next = x.next;
        x.next.prev = x.prev;
    }
    
    // M�todo para imprimir (Recorrer)
    public void printList() {
        Nodo current = nil.next;
        while (current != nil) {
            System.out.print(current.getKey() + " <-> ");
            current = current.next;
        }
        System.out.println("NIL");
    }
    
}
