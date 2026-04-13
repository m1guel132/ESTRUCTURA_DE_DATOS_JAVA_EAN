/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;

/**
 *
 * @author migue
 */
public class Ordenamiento {
        
    public static void quickSortNombre(File[] arr) {
        QuickSort.sort(arr, 0, arr.length - 1, false);
    }
    
    public static void quickSortDuracion(File[] arr) {
        QuickSort.sort(arr, 0, arr.length - 1, true);
    }
    
    public static void mergeSortNombre(File[] arr) {
        MergeSort.sort(arr, 0, arr.length - 1, false);
    }

    public static void mergeSortDuracion(File[] arr) {
        MergeSort.sort(arr, 0, arr.length - 1, true);
    }
   
} 
