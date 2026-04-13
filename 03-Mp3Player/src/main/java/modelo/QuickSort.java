/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;

/**
 *
 * @author diotallevi
 */
public class QuickSort {

    // Método principal de la recursión (CLRS Capítulo 7.1)
    public static void sort(File[] A, int p, int r, boolean porTamaño) {
        if (p < r) {
            int q = partition(A, p, r, porTamaño);  // División (Divide): Reorganiza el arreglo in-place
            sort(A, p, q - 1, porTamaño);           // Conquista izquierda
            sort(A, q + 1, r, porTamaño);           // Conquista derecha
            // No hay paso de combinación (Combine) porque el arreglo ya se ordena en la división
        }
    }

    // Procedimiento PARTITION: El núcleo de QuickSort
    private static int partition(File[] A, int p, int r, boolean porTamaño) {
        File x = A[r];       // Se elige el último elemento como pivote
        int i = p - 1;      // Índice del límite de los elementos menores al pivote

        // 1. Recorrer el subarreglo y clasificar elementos respecto al pivote
        for (int j = p; j < r; j++) {
            // Si el elemento actual es menor o igual al pivote, lo pasamos a la "zona de menores"
            if (comparar(A[j], x, porTamaño) <= 0) {
                i++;
                swap(A, i, j);
            }
        }

        // 2. Colocar el pivote en su posición final correcta
        // Intercambiamos el pivote con el primer elemento de la "zona de mayores"
        swap(A, i + 1, r);

        // 3. Retornar el índice donde quedó el pivote
        return i + 1;
    }
    
    private static void swap(File[] arr, int i, int j) {
        File temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int comparar(File a, File b, boolean porTamano) {
    if (porTamano) {
        return Long.compare(a.length(), b.length());
    } else {
        // Normalizar: minúsculas, sin extensión, sin espacios extra
        String nombreA = normalizar(a.getName());
        String nombreB = normalizar(b.getName());
        return nombreA.compareTo(nombreB);
    }
}

private static String normalizar(String nombre) {
    // Quitar extensión
    int dot = nombre.lastIndexOf('.');
    String sinExt = (dot > 0) ? nombre.substring(0, dot) : nombre;
    // Minúsculas y sin espacios al inicio/fin
    return sinExt.trim().toLowerCase();
    }
}