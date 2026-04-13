package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

/*
package vista;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
*/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author migue
 */

public class ReproductorVistaModerno extends JFrame {

    // ── Paleta ──────────────────────────────────────────────────
    private static final Color BG_MAIN    = new Color(0xF5F7FA);
    private static final Color BG_PANEL   = new Color(0xFFFFFF);
    private static final Color BG_SIDEBAR = new Color(0xECEFF4);
    private static final Color ACCENT     = new Color(0x4A90D9);
    private static final Color ACCENT_HOV = new Color(0x357ABD);
    private static final Color BTN_SEC    = new Color(0xDDE3ED);
    private static final Color BTN_SEC_HOV= new Color(0xC8D0E0);
    private static final Color TEXT_PRI   = new Color(0x2C3E50);
    private static final Color TEXT_SEC   = new Color(0x7F8C8D);
    private static final Color PROGRESS   = new Color(0x4A90D9);

    // ── Componentes públicos (sin cambiar nombres) ───────────────
    public JProgressBar barraProgreso   = new JProgressBar(0, 100);
    public JLabel lblTitulo             = new JLabel("Título: -");
    public JLabel lblArtista            = new JLabel("Artista: -");
    public JLabel lblAlbum              = new JLabel("Álbum: -");
    public JLabel lblDuracion           = new JLabel("Duración: -");
    public JLabel lblGenero             = new JLabel("Género: -");
    public DefaultListModel<String> modeloLista = new DefaultListModel<>();
    public JList<String> listaCanciones = new JList<>(modeloLista);

    public JButton btnControl        = crearBotonAccent("▶  Reproducir", true);
    public JButton btnSiguiente      = crearBotonSecundario("⏭  Siguiente");
    public JButton btnAnterior       = crearBotonSecundario("⏮  Anterior");
    public JButton btnAbrir          = crearBotonSecundario("📂  Abrir archivo");
    public JButton btnQuickSortName  = crearBotonSecundario("Quick Sort / Nombre");
    public JButton btnQuickSortTime  = crearBotonSecundario("Quick Sort / Duración");
    public JButton btnMergueSortName = crearBotonSecundario("Merge Sort / Nombre");
    public JButton btnMergueSortTime = crearBotonSecundario("Merge Sort / Duración");

    // ────────────────────────────────────────────────────────────
    public ReproductorVistaModerno() {
        super("MP3 Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(BG_MAIN);

        add(buildPanelInfo(),     BorderLayout.NORTH);
        add(buildSidebar(),       BorderLayout.WEST);
        add(buildPanelCentral(),  BorderLayout.CENTER);
        add(buildPanelBotones(),  BorderLayout.SOUTH);

        setMinimumSize(new Dimension(750, 420));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ── Panel superior: metadata ─────────────────────────────────
    private JPanel buildPanelInfo() {
        JPanel p = new JPanel(new GridLayout(1, 3, 20, 0));
        p.setBackground(BG_PANEL);
        p.setBorder(new EmptyBorder(16, 24, 16, 24));

        // Columna izquierda
        JPanel col1 = infoColumn();
        col1.add(styledLabel(lblTitulo,  Font.BOLD, 14, TEXT_PRI));
        col1.add(styledLabel(lblArtista, Font.PLAIN, 12, TEXT_SEC));

        // Columna centro
        JPanel col2 = infoColumn();
        col2.add(styledLabel(lblAlbum,  Font.PLAIN, 12, TEXT_SEC));
        col2.add(styledLabel(lblGenero, Font.PLAIN, 12, TEXT_SEC));

        // Columna derecha
        JPanel col3 = infoColumn();
        col3.add(styledLabel(lblDuracion, Font.PLAIN, 12, TEXT_SEC));

        p.add(col1); p.add(col2); p.add(col3);

        // Separador inferior
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(BG_MAIN);
        wrapper.add(p, BorderLayout.CENTER);
        wrapper.add(buildSeparator(), BorderLayout.SOUTH);
        return wrapper;
    }

    // ── Sidebar: playlist ────────────────────────────────────────
    private JScrollPane buildSidebar() {
        listaCanciones.setBackground(BG_SIDEBAR);
        listaCanciones.setForeground(TEXT_PRI);
        listaCanciones.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        listaCanciones.setSelectionBackground(ACCENT);
        listaCanciones.setSelectionForeground(Color.WHITE);
        listaCanciones.setFixedCellHeight(28);
        listaCanciones.setBorder(new EmptyBorder(4, 8, 4, 8));

        JScrollPane scroll = new JScrollPane(listaCanciones);
        scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(0xDDE3ED)));
        scroll.setPreferredSize(new Dimension(220, 0));
        scroll.getViewport().setBackground(BG_SIDEBAR);

        JLabel titulo = new JLabel("  Playlist");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        titulo.setForeground(TEXT_SEC);
        titulo.setOpaque(true);
        titulo.setBackground(BG_SIDEBAR);
        titulo.setBorder(new EmptyBorder(10, 8, 6, 0));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(BG_SIDEBAR);
        wrapper.add(titulo, BorderLayout.NORTH);
        wrapper.add(scroll, BorderLayout.CENTER);

        JScrollPane outer = new JScrollPane();
        outer.setViewportView(wrapper);
        outer.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(0xDDE3ED)));
        outer.setPreferredSize(new Dimension(220, 0));
        return scroll;
    }

    // ── Panel central: progreso ───────────────────────────────────
    private JPanel buildPanelCentral() {
        barraProgreso.setStringPainted(true);
        barraProgreso.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        barraProgreso.setForeground(PROGRESS);
        barraProgreso.setBackground(new Color(0xDDE3ED));
        barraProgreso.setBorderPainted(false);
        barraProgreso.setPreferredSize(new Dimension(0, 10));

        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(BG_MAIN);
        p.setBorder(new EmptyBorder(30, 30, 10, 30));
        p.add(barraProgreso, BorderLayout.SOUTH);
        return p;
    }

    // ── Panel inferior: botones ───────────────────────────────────
    private JPanel buildPanelBotones() {
        // Fila 1: controles de reproducción
        JPanel filaControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        filaControl.setBackground(BG_PANEL);
        filaControl.add(btnAbrir);
        filaControl.add(btnAnterior);
        filaControl.add(btnControl);
        filaControl.add(btnSiguiente);

        // Fila 2: ordenamiento
        JPanel filaSort = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        filaSort.setBackground(BG_PANEL);
        filaSort.add(btnQuickSortName);
        filaSort.add(btnQuickSortTime);
        filaSort.add(btnMergueSortName);
        filaSort.add(btnMergueSortTime);

        JLabel lblSort = new JLabel("Ordenar playlist:");
        lblSort.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblSort.setForeground(TEXT_SEC);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(BG_PANEL);
        wrapper.setBorder(new EmptyBorder(12, 16, 16, 16));

        filaControl.setAlignmentX(Component.CENTER_ALIGNMENT);
        filaSort.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSort.setAlignmentX(Component.CENTER_ALIGNMENT);

        wrapper.add(filaControl);
        wrapper.add(Box.createVerticalStrut(10));
        wrapper.add(lblSort);
        wrapper.add(Box.createVerticalStrut(4));
        wrapper.add(filaSort);

        JPanel outer = new JPanel(new BorderLayout());
        outer.setBackground(BG_MAIN);
        outer.add(buildSeparator(), BorderLayout.NORTH);
        outer.add(wrapper, BorderLayout.CENTER);
        return outer;
    }

    // ── Helpers de construcción ───────────────────────────────────
    private JPanel infoColumn() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(BG_PANEL);
        return p;
    }

    private JLabel styledLabel(JLabel lbl, int style, int size, Color color) {
        lbl.setFont(new Font("Segoe UI", style, size));
        lbl.setForeground(color);
        return lbl;
    }

    private JSeparator buildSeparator() {
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(0xDDE3ED));
        sep.setBackground(BG_MAIN);
        return sep;
    }

    // ── Factories de botones ──────────────────────────────────────
    private JButton crearBotonAccent(String texto, boolean primary) {
        JButton btn = new RoundButton(texto);
        btn.setBackground(ACCENT);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(ACCENT_HOV); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(ACCENT); }
        });
        return btn;
    }

    private JButton crearBotonSecundario(String texto) {
        JButton btn = new RoundButton(texto);
        btn.setBackground(BTN_SEC);
        btn.setForeground(TEXT_PRI);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(BTN_SEC_HOV); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(BTN_SEC); }
        });
        return btn;
    }

    // ── Botón con bordes redondeados (inner class) ────────────────
    static class RoundButton extends JButton {
        RoundButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setMargin(new Insets(6, 16, 6, 16));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension d = super.getPreferredSize();
            return new Dimension(d.width + 10, d.height + 4);
        }
    }
}