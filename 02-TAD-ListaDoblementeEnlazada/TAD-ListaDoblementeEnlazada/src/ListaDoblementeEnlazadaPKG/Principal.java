package ListaDoblementeEnlazadaPKG;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author diotallevi
 */
import java.util.Scanner;

public class Principal {
    
    // ================= LISTA SIN CENTINELA =================
    private static void ejecutarListaNormal(Scanner sc) {
        ListaDoblementeEnlazada lista = new ListaDoblementeEnlazada();
        int opcion;

        do {
            System.out.println("\n--- Lista doblemente enlazada ---");
            System.out.println("1. Insertar");
            System.out.println("2. Buscar");
            System.out.println("3. Eliminar");
            System.out.println("4. Recorrer");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Valor a insertar: ");
                    lista.insertar(sc.nextInt());
                    break;

                case 2:
                    System.out.print("Valor a buscar: ");
                    Nodo encontrado = lista.search(sc.nextInt());
                    System.out.println(encontrado != null
                            ? "Nodo encontrado"
                            : "Nodo no encontrado");
                    break;

                case 3:
                    System.out.print("Valor a eliminar: ");
                    Nodo eliminar = lista.search(sc.nextInt());
                    if (eliminar != null) {
                        lista.delete(eliminar);
                        System.out.println("Nodo eliminado");
                    } else {
                        System.out.println("Nodo no encontrado");
                    }
                    break;

                case 4:
                    lista.recorrer();
                    break;
            }

        } while (opcion != 0);
    }

    private static void ejecutarListaNormalConCentinela(Scanner sc) {
        ListaDoblementeEnlazadaConCentinela lista = new ListaDoblementeEnlazadaConCentinela();
        int opcion;

        do {
            System.out.println("\n--- Lista doblemente enlazada ---");
            System.out.println("1. Insertar");
            System.out.println("2. Buscar");
            System.out.println("3. Eliminar");
            System.out.println("4. Recorrer");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Valor a insertar: ");
                    lista.insertar(sc.nextInt());
                    break;

                case 2:
                    System.out.print("Valor a buscar: ");
                    Nodo encontrado = lista.search(sc.nextInt());
                    System.out.println(encontrado != null
                            ? "Nodo encontrado"
                            : "Nodo no encontrado");
                    break;

                case 3:
                    System.out.print("Valor a eliminar: ");
                    Nodo eliminar = lista.search(sc.nextInt());
                    if (eliminar != null) {
                        lista.delete(eliminar);
                        System.out.println("Nodo eliminado");
                    } else {
                        System.out.println("Nodo no encontrado");
                    }
                    break;

                case 4:
                    lista.recorrer();
                    break;
            }

        } while (opcion != 0);
    }
    
    // ================= LISTA CON CENTINELA =================
    private static void ejecutarListaCentinela(Scanner sc) {
        DoublyLinkedListSentinel lista = new DoublyLinkedListSentinel();
        int opcion;

        do {
            System.out.println("\n--- Lista doblemente enlazada con centinela ---");
            System.out.println("1. Insertar");
            System.out.println("2. Buscar");
            System.out.println("3. Eliminar");
            System.out.println("4. Recorrer");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Valor a insertar: ");
                    lista.insert(sc.nextInt());
                    break;

                case 2:
                    System.out.print("Valor a buscar: ");
                    Nodo encontrado = lista.search(sc.nextInt());
                    System.out.println(encontrado != lista.search(-1) && encontrado != null
                            ? "Nodo encontrado"
                            : "Nodo no encontrado");
                    break;

                case 3:
                    System.out.print("Valor a eliminar: ");
                    Nodo eliminar = lista.search(sc.nextInt());
                    if (eliminar != null && eliminar != lista.search(-1)) {
                        lista.delete(eliminar);
                        System.out.println("Nodo eliminado");
                    } else {
                        System.out.println("Nodo no encontrado");
                    }
                    break;

                case 4:
                    lista.printList();
                    break;
            }

        } while (opcion != 0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Seleccione el tipo de lista:");
        System.out.println("1. Lista doblemente enlazada (sin centinela)");
        System.out.println("2. Lista doblemente enlazada (con centinela)");
        System.out.println("3. Lista doblemente enlazada con centinela");
        System.out.print("Opción: ");
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                ejecutarListaNormal(sc);
                break;
            case 2:
                ejecutarListaCentinela(sc);
                break;
            case 3:
                ejecutarListaNormalConCentinela(sc);
            default:
                System.out.println("Opción no válida");
        }
        
        //invocando un destructor
        sc.close();
    }    
}

