/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package controlador;

import modelo.ReproductorModelo;
import vista.ReproductorVista;

/**
 *
 * @author diotallevi
 */
public class Mp3Player {

    public static void main(String[] args){
        //Establecer el nivel de log a solo advertencias o errores
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "WARN");
        
        ReproductorModelo objetoModelo = new ReproductorModelo();
        ReproductorVista objetoVista = new ReproductorVista();
        new Controlador(objetoModelo, objetoVista);  
    }
}
