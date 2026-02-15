package listaPKG;

import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author diotallevi
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    
    public static void invocarFila(){
        Fila myLista=new Fila();
        myLista.agregarNodo(10);
        myLista.agregarNodo(20);
        myLista.agregarNodo(30);
        myLista.agregarNodo(40);
        System.out.println(myLista.obtenerLista());
        
        myLista.eliminarNodo();
        System.out.println(myLista.obtenerLista());
        
        System.out.println("Buscando nodo en Fila");
        System.out.println(myLista.buscarNodo(10));
    }
    
    public static void invocarPila(){
        Pila myPila=new Pila();
        myPila.agregarNodo(100);
        myPila.agregarNodo(150);
        myPila.agregarNodo(200);
        myPila.agregarNodo(250);
        myPila.agregarNodo(300);
        System.out.println(myPila.obtenerPila());
        
        myPila.eliminarNodo();
        System.out.println(myPila.obtenerPila());

        System.out.println("Buscando nodo en Fila");
        System.out.println(myPila.buscarNodo(200));

    }
    
    public static void invocarListaSimple(){
        ListaSimpleEnlazada lista = new ListaSimpleEnlazada();
        lista.insertarInicio(10);
        lista.insertarInicio(20);
        lista.insertarFinal(30);
        System.out.println("Lista: " + lista.obtenerLista());

        lista.insertarEnPosicion(25, 2);
        System.out.println("Lista luego de ingresar valor: " + lista.obtenerLista());

        lista.eliminarNodo(20);
        System.out.println("Después de eliminar 20: " + lista.obtenerLista());

    }

    public static void invocarListaSimple_a_pila(){
        ListaSimpleEnlazada_a_Pila lista = new ListaSimpleEnlazada_a_Pila();
        lista.agregarNodo(10);
        System.out.println("Lista: " + lista.obtenerLista());

        lista.agregarNodo(20);
        System.out.println("Lista: " + lista.obtenerLista());

        lista.agregarNodo(30);
        System.out.println("Lista: " + lista.obtenerLista());

        System.out.println("Buscar: " + lista.buscarNodo(20));

        lista.eliminarNodo();
        System.out.println("Después de eliminar : " + lista.obtenerLista());

        lista.eliminarNodo();
        System.out.println("Después de eliminar : " + lista.obtenerLista());

        lista.agregarNodo(100);
        System.out.println("Lista: " + lista.obtenerLista());

    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner entrada=new Scanner(System.in);
        System.out.println("Que tipo de Estructura "
                + "desea invocar?\n1. Pila\n2. Cola\n3. Lista simplemente enlazada \n4. Lista a pila");
        int seleccion=entrada.nextInt();
        switch (seleccion) {
            case 1 -> invocarFila();
            case 2 -> invocarPila();
            case 3 -> invocarListaSimple();
            case 4 -> invocarListaSimple_a_pila();
            default -> System.out.println("No está definido!");
        }
    }   
}
