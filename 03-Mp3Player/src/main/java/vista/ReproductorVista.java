package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Vista principal del reproductor MP3.
 * Organizada en dos pestañas:
 *   - "Reproductor": controles, playlist y barra de progreso.
 *   - "Recomendaciones": tabla con resultados de MusicBrainz.
 *
 * @author diotallevi / migue
 */
public class ReproductorVista extends JFrame {

    // ── Pestaña Reproductor ──────────────────────────────────────
    public JProgressBar barraProgreso  = new JProgressBar(0, 100);
    public JLabel lblTitulo            = new JLabel("Título: -");
    public JLabel lblArtista           = new JLabel("Artista: -");
    public JLabel lblAlbum             = new JLabel("Álbum: -");
    public JLabel lblDuracion          = new JLabel("Duración: -");
    public JLabel lblGenero            = new JLabel("Género: -");
    public DefaultListModel<String> modeloLista = new DefaultListModel<>();
    public JList<String> listaCanciones         = new JList<>(modeloLista);

    public JButton btnControl         = new JButton("Reproducir");
    public JButton btnSiguiente       = new JButton("Siguiente");
    public JButton btnAnterior        = new JButton("Anterior");
    public JButton btnAbrir           = new JButton("Abrir archivo");
    public JButton btnQuickSortName   = new JButton("Quick Sort por nombre");
    public JButton btnQuickSortTime   = new JButton("Quick Sort por duración");
    public JButton btnMergeSortName   = new JButton("Merge Sort por nombre");
    public JButton btnMergeSortTime   = new JButton("Merge Sort por duración");

    // ── Pestaña Recomendaciones ──────────────────────────────────
    // Columnas de la tabla
    private static final String[] COLUMNAS = {"Título", "Artista", "Álbum", "Géneros"};

    // Modelo de tabla no editable
    public DefaultTableModel modeloTabla = new DefaultTableModel(COLUMNAS, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // el usuario no puede editar celdas
        }
    };

    public JTable tablaRecomendaciones = new JTable(modeloTabla);
    public JLabel lblEstadoBusqueda    = new JLabel("Esperando reproducción...");
    public JButton btnBuscarRec        = new JButton("Buscar recomendaciones");

    // ── Componente raíz ──────────────────────────────────────────
    public JTabbedPane pestanas        = new JTabbedPane();

    // ────────────────────────────────────────────────────────────
    public ReproductorVista() {
        super("Mp3 en JAVA!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10));

        pestanas.addTab("▶  Reproductor",    construirPanelReproductor());
        pestanas.addTab("★  Recomendaciones", construirPanelRecomendaciones());

        this.add(pestanas, BorderLayout.CENTER);

        this.pack();
        this.setMinimumSize(new Dimension(700, 450));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // ── Constructor del panel Reproductor ────────────────────────
    private JPanel construirPanelReproductor() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // --- Info superior ---
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblTitulo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblArtista.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblAlbum.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblDuracion.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblGenero.setAlignmentX(Component.RIGHT_ALIGNMENT);

        panelInfo.add(lblTitulo);
        panelInfo.add(Box.createVerticalStrut(5));
        panelInfo.add(lblArtista);
        panelInfo.add(Box.createVerticalStrut(5));
        panelInfo.add(lblAlbum);
        panelInfo.add(Box.createVerticalStrut(5));
        panelInfo.add(lblGenero);
        panelInfo.add(Box.createVerticalStrut(5));
        panelInfo.add(lblDuracion);

        // --- Barra de progreso ---
        JPanel panelProgreso = new JPanel(new BorderLayout());
        panelProgreso.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        barraProgreso.setStringPainted(true);
        panelProgreso.add(barraProgreso, BorderLayout.CENTER);

        // --- Playlist lateral ---
        JScrollPane scrollLista = new JScrollPane(listaCanciones);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Playlist"));
        scrollLista.setPreferredSize(new Dimension(200, 150));

        // --- Botones inferiores ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnAbrir);
        panelBotones.add(btnAnterior);
        panelBotones.add(btnControl);
        panelBotones.add(btnSiguiente);
        panelBotones.add(btnQuickSortName);
        panelBotones.add(btnQuickSortTime);
        panelBotones.add(btnMergeSortName);
        panelBotones.add(btnMergeSortTime);

        panel.add(panelInfo,    BorderLayout.NORTH);
        panel.add(scrollLista,  BorderLayout.WEST);
        panel.add(panelProgreso, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    // ── Constructor del panel Recomendaciones ────────────────────
    private JPanel construirPanelRecomendaciones() {

        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Encabezado con estado y botón ---
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 0));
        lblEstadoBusqueda.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
        panelSuperior.add(lblEstadoBusqueda, BorderLayout.CENTER);
        panelSuperior.add(btnBuscarRec,       BorderLayout.EAST);

        // --- Tabla de resultados ---
        tablaRecomendaciones.setFillsViewportHeight(true);
        tablaRecomendaciones.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaRecomendaciones.getTableHeader().setReorderingAllowed(false);

        // Anchos sugeridos por columna
        tablaRecomendaciones.getColumnModel().getColumn(0).setPreferredWidth(160); // Título
        tablaRecomendaciones.getColumnModel().getColumn(1).setPreferredWidth(120); // Artista
        tablaRecomendaciones.getColumnModel().getColumn(2).setPreferredWidth(140); // Álbum
        tablaRecomendaciones.getColumnModel().getColumn(3).setPreferredWidth(180); // Géneros

        JScrollPane scrollTabla = new JScrollPane(tablaRecomendaciones);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Canciones encontradas en MusicBrainz"));

        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scrollTabla,   BorderLayout.CENTER);

        return panel;
    }

    /**
     * Limpia la tabla y muestra un mensaje de estado.
     * Llamar desde el Event Dispatch Thread.
     */
    public void limpiarRecomendaciones(String mensaje) {
        modeloTabla.setRowCount(0);
        lblEstadoBusqueda.setText(mensaje);
    }

    /**
     * Agrega una fila a la tabla de recomendaciones.
     */
    public void agregarRecomendacion(String titulo, String artista, String album, String generos) {
        modeloTabla.addRow(new Object[]{titulo, artista, album, generos});
    }

    /**
     * Actualiza el label de estado de la búsqueda.
     */
    public void setEstadoBusqueda(String mensaje) {
        lblEstadoBusqueda.setText(mensaje);
    }
}