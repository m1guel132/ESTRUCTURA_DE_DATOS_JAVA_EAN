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
    private Playlist playlist;
    private String ruta;
    private BasicPlayerListener listener; // Lo usaremos para comunicar eventos al controlador
    
    //constructor
    public ReproductorModelo(){
        this.reproductor = new BasicPlayer();
        this.playlist = new Playlist();
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
    
    public void cargarCarpeta(File carpeta) {
        
        playlist.limpiar();
        
        File[] archivos = carpeta.listFiles();
        
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".mp3")) {
                    playlist.agregar(archivo);
                }
            }
        } else {
            System.out.println("Es directorio: " + carpeta.isDirectory());
            System.out.println("Ruta: " + carpeta.getAbsolutePath());

        }
    }
      
    //Método para reproducir
    public void reproducir() throws Exception {
        
        File actual = playlist.getActual();
        if (actual != null) {
            this.reproductor.open(actual);
            this.reproductor.play();
        }
    }
    
    public void siguiente() throws Exception {
        
        File siguiente = playlist.siguiente();
        
        if (siguiente != null) {
            this.reproductor.open(siguiente);
            this.reproductor.play();
        }
    }
    
    public void anterior() throws Exception {
        
        File anterior = playlist.anterior();
        
        if (anterior != null) {
            this.reproductor.open(anterior);
            this.reproductor.play();
        }
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
    
    public boolean estaVacia() {
        return playlist.estaVacia();
    }

    public void setRuta(File rutaArchivo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
