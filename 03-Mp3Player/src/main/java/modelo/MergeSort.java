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
public class MergeSort {

    // Método principal de la recursión (CLRS Capítulo 2.3)
    public static void sort(File[] A, int p, int r, boolean porTamaño) {
        if (p < r) {
            int q = (p + r) / 2;     // División (Divide)
            sort(A, p, q, porTamaño);           // Conquista izquierda
            sort(A, q + 1, r, porTamaño);       // Conquista derecha
            merge(A, p, q, r, porTamaño);       // Combinación (Combine) sin centinelas
        }
    }

    // Procedimiento MERGE adaptado para producción (sin valores infinitos)
    private static void merge(File[] A, int p, int q, int r, boolean porTamaño) {
        int n1 = q - p + 1;
        int n2 = r - q;

        // Crear arreglos con el tamaño exacto (sin espacio extra)
        File[] L = new File[n1];
        File[] R = new File[n2];

        // Copiar datos a los arreglos temporales L y R
        for (int i = 0; i < n1; i++) {
            L[i] = A[p + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = A[q + 1 + j];
        }

        int i = 0; // índice inicial del subarreglo L
        int j = 0; // índice inicial del subarreglo R
        int k = p; // índice inicial del subarreglo fusionado
        
        // 1. Mezclar ambos arreglos hasta que uno de los dos se agote
        while (i < n1 && j < n2) {
            if (comparar(L[i], R[j], porTamaño) <= 0) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
            k++;
        }

        // 2. Copiar los elementos restantes de L (si quedó alguno)
        while (i < n1) {
            A[k] = L[i];
            i++;
            k++;
        }

        // 3. Copiar los elementos restantes de R (si quedó alguno)
        // Nota: Solo uno de estos dos últimos bucles while se ejecutará en cada llamada
        while (j < n2) {
            A[k] = R[j];
            j++;
            k++;
        }
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

