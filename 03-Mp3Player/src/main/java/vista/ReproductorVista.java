/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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


/**
 *
 * @author diotallevi
 */
public class ReproductorVista extends JFrame{
    
    public JProgressBar barraProgreso = new JProgressBar(0, 100);
    public JLabel lblTitulo = new JLabel("Título: -");
    public JLabel lblArtista = new JLabel("Artista: -");
    public JLabel lblAlbum = new JLabel("Álbum: -");
    public DefaultListModel<String> modeloLista = new DefaultListModel<>();
    public JList<String> listaCanciones = new JList<>(modeloLista);
    
    /*
    public JButton btnPlay = new JButton("Reproducr");
    public JButton btnPausa = new JButton("Pausar");
    public JButton btnStop = new JButton("Detener");
    */
    public JButton btnControl = new JButton("reproducir");
    public JButton btnSiguiente = new JButton("Siguiente");
    public JButton btnAnterior = new JButton("Anterior");
    public JButton btnAbrir = new JButton("Abrir archivo");

    
    
    public ReproductorVista() {
        super("Mp3 en JAVA!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Usamos BorderLayout para la ventana principal
        this.setLayout(new BorderLayout(10, 10)); 

        // --- PANEL DE INFORMACIÓN (ARRIBA) ---
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblArtista.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblAlbum.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        panelInfo.add(lblTitulo);
        panelInfo.add(Box.createVerticalStrut(5)); // Espacio entre l�neas
        panelInfo.add(lblArtista);
        panelInfo.add(Box.createVerticalStrut(5));
        panelInfo.add(lblAlbum);

        // --- PANEL CENTRAL (BARRA DE PROGRESO) ---
        JPanel panelProgreso = new JPanel(new BorderLayout());
        panelProgreso.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        barraProgreso.setStringPainted(true); // Muestra el porcentaje %
        panelProgreso.add(barraProgreso, BorderLayout.CENTER);
        
        // --- PANEL LISTA ---
        JScrollPane scrollLista = new JScrollPane(listaCanciones);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Playlist"));
        scrollLista.setPreferredSize(new Dimension(200, 150));

        // --- PANEL DE BOTONES (ABAJO) ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnAbrir);
        panelBotones.add(btnAnterior);
        panelBotones.add(btnControl);
        panelBotones.add(btnSiguiente);

        // AGREGAR TODO AL FRAME PRINCIPAL
        this.add(panelInfo, BorderLayout.NORTH);
        this.add(scrollLista, BorderLayout.WEST);
        this.add(panelProgreso, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        this.setVisible(true);
        
        

    }
    
}
