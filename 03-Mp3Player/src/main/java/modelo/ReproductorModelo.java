/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

/**
 *
 * @author diotallevi
 */
public class ReproductorModelo {
    
    private BasicPlayer reproductor;
    private String ruta;
    private BasicPlayerListener listener; // Lo usaremos para comunicar eventos al controlador
    
    //constructor
    public ReproductorModelo(){
        this.reproductor=new BasicPlayer();
    }

    //Método SET para iniciar el atributo de la ruta
    public void setRuta(String ruta) throws Exception{
        this.ruta = ruta;
        this.reproductor.open(new File(this.ruta));

    }
    
    // Método para que el controlador se suscriba a los eventos
    public void setControlador(BasicPlayerListener entradaListener) {
        this.listener=entradaListener;
        this.reproductor.addBasicPlayerListener(this.listener);
    }
      
    //Método para reproducir
    public void reproducir () throws Exception{
        this.reproductor.play();
    }
    
    //Método para pausar la reproducción
    public void pausar() throws Exception {
        this.reproductor.pause();
    }

    //Método para reanudar la reproducción
    public void reanudar() throws Exception {
        this.reproductor.resume();
    }

    //Método para detener la reproducción
    public void detener() throws Exception {
        this.reproductor.stop();
    }
}
