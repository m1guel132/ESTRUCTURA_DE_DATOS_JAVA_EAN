/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import modelo.ReproductorModelo;
import vista.ReproductorVista;

/**
 *
 * @author diotallevi
 */
public class Controlador implements ActionListener, BasicPlayerListener{
    
    private ReproductorModelo modelo;
    private ReproductorVista vista;
    private Estado estadoActual = Estado.stop;
    //private boolean estaPausado=false;
    private long tamanoArchivo;
    
    //Constructor
    public Controlador (ReproductorModelo entradaObjetoModelo, ReproductorVista entradaObjetoVista){
        this.modelo=entradaObjetoModelo;
        this.vista=entradaObjetoVista;
        
        this.modelo.setControlador(this); // Activar eventos de la librería de audio para que pueda funcionar la barra de progreso
        
        //Agregando Eventos
        this.vista.btnControl.addActionListener(this);
        this.vista.btnSiguiente.addActionListener(this);
        this.vista.btnAnterior.addActionListener(this);
        this.vista.btnAbrir.addActionListener(this);
    }
    
    private enum Estado {
        stop,
        play,
        pause
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == vista.btnAbrir) {
                File carpeta = seleccionarCarpeta();
                if (carpeta != null) {
                    try {
                        // 1. Esto dispara el método opened(source, properties) automáicamente
                        modelo.cargarCarpeta(carpeta);
                        
                        vista.modeloLista.clear();
                        
                        for (File archivo : modelo.obtenerCanciones()) {
                            vista.modeloLista.addElement(archivo.getName());
                        }
                            
                        if (!modelo.estaVacia()) {
                            modelo.reproducir();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vista, "Error al abrir el archivo: " + ex.getMessage());
                    }
                }
            } 
            else if (e.getSource() == vista.btnControl) {
                switch (estadoActual) {
                    
                    case stop -> {
                        modelo.reproducir();
                        estadoActual = Estado.play;
                    }
                        
                    case play -> {
                        modelo.pausar();
                        estadoActual = Estado.pause;
                    }
                      
                    case pause -> {
                        modelo.reanudar();
                        estadoActual = Estado.play;
                    }                   
                }
            }
            else if (e.getSource() == vista.btnSiguiente) {
                modelo.siguiente();
            }
            else if (e.getSource() == vista.btnAnterior) {
                modelo.anterior();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }
    
    //Método empleado para seleccionar el archivo, retorna la ruta como un string
    public File seleccionarCarpeta () {
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        int seleccion = fileChooser.showOpenDialog(vista);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }

    @Override
    public void opened(Object source, Map properties) {
    // Usamos un método seguro para obtener los datos
    String titulo = obtenerPropiedad(properties, "title", "Desconocido");
    String artista = obtenerPropiedad(properties, "author", "Desconocido");
    String album = obtenerPropiedad(properties, "album", "Desconocido");
    String genero = obtenerPropiedad(properties, "mp3.id3tag.genre", "Desconocido");

    // Actualizamos la vista
    vista.lblTitulo.setText("Título: " + titulo);
    vista.lblArtista.setText("Artista: " + artista);
    vista.lblAlbum.setText("Álbum: " + album);
    vista.lblGenero.setText("Genero: " + genero);
    
    if (properties.containsKey("duration")) {
        long microsegundos = Long.parseLong(properties.get("duration").toString());
        
        long segundosTotales = microsegundos / 1_000_000;
        long minutos = segundosTotales / 60;
        long segundos = segundosTotales % 60;
        
        
        vista.lblDuracion.setText(
            "Duración: " + String.format("%02d:%02d", minutos, segundos)
        );

    }
    
    if (properties.containsKey("audio.length.bytes")) {
        tamanoArchivo = Long.parseLong(properties.get("audio.length.bytes").toString());
    }
    
    // Forzamos a la vista a reacomodarse si los textos son largos
    vista.pack(); 
}
    @Override
    public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
        // 1. Calcular porcentaje para la barra
        if (tamanoArchivo > 0) {
            float progreso = (bytesread * 100.0f) / tamanoArchivo;
            vista.barraProgreso.setValue((int) progreso);
        }

        // 2. Convertir microsegundos a Minutos:Segundos
        long segundosTotales = microseconds / 1000000;
        long minutos = segundosTotales / 60;
        long segundos = segundosTotales % 60;

        // 3. Escribir el tiempo sobre la barra (ej: "02:45")
        vista.barraProgreso.setString(String.format("%02d:%02d", minutos, segundos));
    }

    @Override
    public void stateUpdated(BasicPlayerEvent bpe) {
        if (bpe.getCode() == BasicPlayerEvent.STOPPED) {
            vista.barraProgreso.setValue(0);
        }
    }

    @Override
    public void setController(BasicController bc) {
        throw new UnsupportedOperationException(""); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Método auxiliar para evitar el "null"
    private String obtenerPropiedad(Map props, String llave, String valorDefecto) {
        Object valor = props.get(llave);
        return (valor != null && !valor.toString().isEmpty()) ? valor.toString() : valorDefecto;
    }
    
    //Método para limpiar labels cuando se oprime detener
    public void limpiarLabels() {
        vista.lblTitulo.setText("Título: -");
        vista.lblArtista.setText("Artista: -");
        vista.lblAlbum.setText("Álbum: -");
        vista.lblGenero.setText("Genero: -");
        vista.lblDuracion.setText("Duracion: -");
        vista.barraProgreso.setValue(0);
        vista.barraProgreso.setString("00:00");
    }
}
