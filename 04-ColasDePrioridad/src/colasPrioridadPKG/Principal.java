package colasPrioridadPKG;

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
    public static void main(String[] args) {
        // TODO code application logic here
        ColaPrioridad urgencias = new ColaPrioridad();

        // Simulamos la llegada de pacientes a un hospital
        urgencias.insertar("Paciente con resfriado", 4);
        urgencias.insertar("Paciente con infarto", 1);
        urgencias.insertar("Paciente con fractura", 2);
        urgencias.insertar("Paciente con dolor de cabeza", 4);

        System.out.println("\n--- Atendiendo pacientes ---");
        
        while (!urgencias.estaVacia()) {
            System.out.println("Atendiendo a: " + urgencias.extraer());
        }
    }
    
}
