package damas.presentacion;

import javax.swing.*;
import java.awt.*;

public class damasGUI extends JFrame {

    private JPanel Inicio, Extra;
    private JButton BJugar, BOpciones,BRegresar;

    private JComboBox modo,dificultad,fichas,casillas,comodines;

    public damasGUI() {
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        setTitle("Damas");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(pantalla.width / 2, pantalla.height / 2);
        setLocationRelativeTo(null);
        PanelExtra();
        PanelInicial();
    }

    private void PanelInicial() {

        Inicio = new JPanel();
        Inicio.setLayout(new BorderLayout());
        Inicio.setBackground(Color.white);
        ImageIcon o1 = new ImageIcon(getClass().getResource("/Imagenes/t1.png"));
        JPanel imagen = new JPanel();
        JLabel label1 = new JLabel(null, o1, SwingConstants.CENTER);
        imagen.add(label1);
        Inicio.add(label1, BorderLayout.NORTH);
        Inicio.setBackground(new Color(20, 70, 90));
        this.add(Inicio);

        JPanel panelCentro = new JPanel();
        Inicio.add(panelCentro);
        panelCentro.setLayout(new GridLayout(4, 1, 1, 10));
        BJugar = new JButton("Jugar");
        BJugar.setBackground(Color.CYAN);
        BJugar.setFont(new Font("Ink free", Font.BOLD, 50));
        BOpciones = new JButton("Opciones");
        BOpciones.setBackground(Color.CYAN);
        BOpciones.setFont(new Font("Ink free", Font.BOLD, 50));
        panelCentro.add(BJugar);
        panelCentro.add(BOpciones);
        panelCentro.setBackground(new Color(20, 70, 90));
    }

    private void PanelExtra() {

        Extra = new JPanel();
        Extra.setLayout(new BorderLayout());
        Extra.setBackground(new Color(20, 70, 90));

        ImageIcon o1 = new ImageIcon(getClass().getResource("/Imagenes/t1.png"));
        JPanel imagen = new JPanel();
        JLabel label1 = new JLabel(null, o1, SwingConstants.CENTER);
        imagen.add(label1);
        Extra.add(label1, BorderLayout.NORTH);

        JPanel Centro = new JPanel();
        Centro.setLayout(new FlowLayout(FlowLayout.CENTER,80,50));
        Centro.setBackground(new Color(20,70,90));
        Extra.add(Centro,BorderLayout.CENTER);

        modo = new JComboBox<>();
        modo.addItem("Jugador vs Jugador");
        modo.addItem("Jugador vs Maquina");
        JLabel label2 = new JLabel("Modo de Juego : ");
        label2.setFont(new Font("Ink free", Font.BOLD, 20));
        label2.setForeground(Color.white);
        Centro.add(label2);
        Centro.add(modo);

        dificultad = new JComboBox<>();
        dificultad.addItem("Normal");
        dificultad.addItem("Quicktime");
        JLabel label3 = new JLabel("Grado de Dificultad : ");
        label3.setFont(new Font("Ink free", Font.BOLD, 20));
        label3.setForeground(Color.white);
        Centro.add(label3);
        Centro.add(dificultad);

        fichas = new JComboBox<>();
        fichas.addItem("Desactivado");
        fichas.addItem("Activado");
        JLabel label4 = new JLabel("Fichas Especiales : ");
        label4.setFont(new Font("Ink free", Font.BOLD, 20));
        label4.setForeground(Color.white);
        Centro.add(label4);
        Centro.add(fichas);

        casillas = new JComboBox<>();
        casillas.addItem("Desactivado");
        casillas.addItem("Activado");
        JLabel label6 = new JLabel("Casillas Especiales : ");
        label6.setFont(new Font("Ink free", Font.BOLD, 20));
        label6.setForeground(Color.white);
        Centro.add(label6);
        Centro.add(casillas);

        comodines = new JComboBox<>();
        comodines.addItem("Desactivado");
        comodines.addItem("Activado");
        JLabel label5 = new JLabel("Comodines : ");
        label5.setFont(new Font("Ink free", Font.BOLD, 20));
        label5.setForeground(Color.white);
        Centro.add(label5);
        Centro.add(comodines);

        BRegresar = new JButton("Regresar");
        BRegresar.setFont(new Font("Ink free",Font.BOLD,30));
        BRegresar.setBackground(Color.cyan);
        Extra.add(BRegresar,BorderLayout.SOUTH);
        this.add(Extra);
    }

    private void prepareActions() {
        BJugar.addActionListener(e -> Jugar());
        BOpciones.addActionListener(e -> Opciones());
        BRegresar.addActionListener(e -> Regresar());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void Jugar() {
        JuegoGUI tablero = new JuegoGUI();
        this.setVisible(false);
        tablero.setVisible(true);
    }

    public void Opciones() {
        Inicio.setVisible(false);
        Extra.setVisible(true);
        add(Extra);
    }

    public void Regresar(){
        Extra.setVisible(false);
        Inicio.setVisible(true);
        add(Inicio);
    }

}
