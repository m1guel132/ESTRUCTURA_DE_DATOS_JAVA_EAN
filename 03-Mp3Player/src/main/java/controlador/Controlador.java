package controlador;

import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import modelo.MusicBrainzService;
import modelo.MusicBrainzService.Recomendacion;
import modelo.ReproductorModelo;
import vista.ReproductorVista;

/**
 * Controlador MVC del reproductor MP3.
 * Dispara búsquedas a MusicBrainz de forma asíncrona (SwingWorker)
 * cada vez que abre una nueva canción.
 *
 * @author diotallevi / migue
 */
public class Controlador implements ActionListener, BasicPlayerListener {

    private ReproductorModelo modelo;
    private ReproductorVista  vista;
    private Estado estadoActual = Estado.stop;
    private long   tamanoArchivo;

    private final MusicBrainzService musicBrainz = new MusicBrainzService();
    private String generoActual = "pop";

    private enum Estado { stop, play, pause }

    // ── Constructor ──────────────────────────────────────────────
    public Controlador(ReproductorModelo entradaObjetoModelo, ReproductorVista entradaObjetoVista) {
        this.modelo = entradaObjetoModelo;
        this.vista  = entradaObjetoVista;

        this.modelo.setControlador(this);

        this.vista.btnControl.addActionListener(this);
        this.vista.btnSiguiente.addActionListener(this);
        this.vista.btnAnterior.addActionListener(this);
        this.vista.btnAbrir.addActionListener(this);
        this.vista.btnQuickSortName.addActionListener(this);
        this.vista.btnQuickSortTime.addActionListener(this);
        this.vista.btnMergeSortName.addActionListener(this);
        this.vista.btnMergeSortTime.addActionListener(this);
        this.vista.btnBuscarRec.addActionListener(this);
    }

    // ── Eventos de botones ───────────────────────────────────────
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == vista.btnAbrir) {
                File carpeta = seleccionarCarpeta();
                if (carpeta != null) {
                    try {
                        modelo.cargarCarpeta(carpeta);
                        vista.modeloLista.clear();
                        for (File archivo : modelo.obtenerCanciones()) {
                            vista.modeloLista.addElement(archivo.getName());
                        }
                        if (!modelo.estaVacia()) {
                            modelo.reproducir();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vista, "Error al abrir: " + ex.getMessage());
                    }
                }
            }
            else if (e.getSource() == vista.btnControl) {
                switch (estadoActual) {
                    case stop  -> { modelo.reproducir(); estadoActual = Estado.play;  vista.btnControl.setText("Pausar");    }
                    case play  -> { modelo.pausar();     estadoActual = Estado.pause; vista.btnControl.setText("Reanudar"); }
                    case pause -> { modelo.reanudar();   estadoActual = Estado.play;  vista.btnControl.setText("Pausar");    }
                }
            }
            else if (e.getSource() == vista.btnSiguiente)    { modelo.siguiente(); }
            else if (e.getSource() == vista.btnAnterior)     { modelo.anterior();  }
            else if (e.getSource() == vista.btnQuickSortName){ modelo.ordenarQuickSortNombre();   refrescarLista(); }
            else if (e.getSource() == vista.btnQuickSortTime){ modelo.ordenarQuickSortDuracion(); refrescarLista(); }
            else if (e.getSource() == vista.btnMergeSortName){ modelo.ordenarMergeSortNombre();   refrescarLista(); }
            else if (e.getSource() == vista.btnMergeSortTime){ modelo.ordenarMergeSortDuracion(); refrescarLista(); }
            else if (e.getSource() == vista.btnBuscarRec)    { buscarRecomendacionesAsync(generoActual); }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }

    // ── Eventos de BasicPlayer ───────────────────────────────────
    @Override
    public void opened(Object source, Map properties) {
        String titulo  = obtenerPropiedad(properties, "title",            "Desconocido");
        String artista = obtenerPropiedad(properties, "author",           "Desconocido");
        String album   = obtenerPropiedad(properties, "album",            "Desconocido");
        String genero  = obtenerPropiedad(properties, "mp3.id3tag.genre", "Desconocido");

        vista.lblTitulo.setText("Título: "  + titulo);
        vista.lblArtista.setText("Artista: " + artista);
        vista.lblAlbum.setText("Álbum: "    + album);
        vista.lblGenero.setText("Genero: "  + genero);

        if (properties.containsKey("duration")) {
            long ms  = Long.parseLong(properties.get("duration").toString());
            long seg = ms / 1_000_000;
            vista.lblDuracion.setText("Duración: " + String.format("%02d:%02d", seg / 60, seg % 60));
        }

        if (properties.containsKey("audio.length.bytes")) {
            tamanoArchivo = Long.parseLong(properties.get("audio.length.bytes").toString());
        }

        vista.pack();

        // Búsqueda automática al cambiar de canción
        generoActual = genero;
        buscarRecomendacionesAsync(generoActual);
    }

    @Override
    public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
        if (tamanoArchivo > 0) {
            vista.barraProgreso.setValue((int)((bytesread * 100.0f) / tamanoArchivo));
        }
        long seg = microseconds / 1_000_000;
        vista.barraProgreso.setString(String.format("%02d:%02d", seg / 60, seg % 60));
    }

    @Override
    public void stateUpdated(BasicPlayerEvent bpe) {
        if (bpe.getCode() == BasicPlayerEvent.STOPPED) {
            vista.barraProgreso.setValue(0);
            estadoActual = Estado.stop;
            vista.btnControl.setText("Reproducir");
        }
    }

    @Override
    public void setController(BasicController bc) { }

    // ── Búsqueda asíncrona (no bloquea la UI) ────────────────────
    private void buscarRecomendacionesAsync(String genero) {

        vista.limpiarRecomendaciones("Buscando: \"" + genero + "\"...");
        vista.btnBuscarRec.setEnabled(false);

        new SwingWorker<List<Recomendacion>, Void>() {

            @Override
            protected List<Recomendacion> doInBackground() {
                return musicBrainz.buscarPorGenero(genero);
            }

            @Override
            protected void done() {
                try {
                    List<Recomendacion> resultados = get();
                    vista.limpiarRecomendaciones(
                        resultados.isEmpty()
                            ? "Sin resultados para: \"" + genero + "\""
                            : resultados.size() + " resultado(s) para: \"" + genero + "\""
                    );
                    for (Recomendacion r : resultados) {
                        vista.agregarRecomendacion(r.titulo, r.artista, r.album, r.generos);
                    }
                } catch (Exception ex) {
                    vista.setEstadoBusqueda("Error: " + ex.getMessage());
                } finally {
                    vista.btnBuscarRec.setEnabled(true);
                }
            }
        }.execute();
    }

    // ── Auxiliares ───────────────────────────────────────────────
    public File seleccionarCarpeta() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);
        return fc.showOpenDialog(vista) == JFileChooser.APPROVE_OPTION
                ? fc.getSelectedFile() : null;
    }

    private String obtenerPropiedad(Map props, String llave, String valorDefecto) {
        Object valor = props.get(llave);
        return (valor != null && !valor.toString().isEmpty()) ? valor.toString() : valorDefecto;
    }

    public void limpiarLabels() {
        vista.lblTitulo.setText("Título: -");
        vista.lblArtista.setText("Artista: -");
        vista.lblAlbum.setText("Álbum: -");
        vista.lblGenero.setText("Genero: -");
        vista.lblDuracion.setText("Duracion: -");
        vista.barraProgreso.setValue(0);
        vista.barraProgreso.setString("00:00");
    }

    private void refrescarLista() {
        vista.modeloLista.clear();
        for (File f : modelo.obtenerCanciones()) {
            vista.modeloLista.addElement(f.getName());
        }
    }
}