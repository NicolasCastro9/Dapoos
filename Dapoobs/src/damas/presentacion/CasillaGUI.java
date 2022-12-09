package damas.presentacion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CasillaGUI extends JButton {

    // Colores
    public static final Color COR_CLARA = new Color(255, 255, 250);
    public static final Color COR_ESCURA = new Color(0, 0, 0);
    private static final Color COR_DESTAQUE = new Color(100, 220, 220);

    // Iconos
    private static final URL FICHAROJA_URL = CasillaGUI.class.getResource("/Imagenes/damas1-1.png");
    private static final URL REYNAROJA_URL = CasillaGUI.class.getResource("/Imagenes/Reyna-1.png");
    private static final URL FICHAAZUL_URL = CasillaGUI.class.getResource("/Imagenes/damas2-1.png");
    private static final URL REYNAAZUL_URL = CasillaGUI.class.getResource("/Imagenes/Reyna-2.png");

    private static final Icon FICHAROJA = new ImageIcon(FICHAROJA_URL);
    private static final Icon REYNAROJA = new ImageIcon(REYNAROJA_URL);
    private static final Icon FICHAAZUL = new ImageIcon(FICHAAZUL_URL);
    private static final Icon REYNAAZUL = new ImageIcon(REYNAAZUL_URL);

    private int x;
    private int y;
    private Color cor;

    public CasillaGUI(int x, int y, Color cor, TableroGUI tablero) {
        this.x = x;
        this.y = y;
        this.cor = cor;
        setIcon(null);
        setBackground(cor);
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(cor, 1));
        setContentAreaFilled(false);
        prerateActions(tablero);
    }

    private void prerateActions(TableroGUI tablero) {
        addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            tablero.getJuego().mover((CasillaGUI) e.getSource());
        }
        });
    }

    public int getPosicionX() {
        return this.x;
    }

    public int getPosicionY() {
        return this.y;
    }

    public void PonerRoja() {
        setIcon(FICHAROJA);
    }

    public void PonerAzul() {
        setIcon(FICHAAZUL);
    }

    public void PonerRRoja() {
        setIcon(REYNAROJA);
    }

    public void PonerRAzul() {
        setIcon(REYNAAZUL);
    }

    public void apagarFicha() {
        setIcon(null);
    }

    public boolean posicionFicha() {
        return getIcon() != null;
    }
    public void destacar() {
        setBackground(COR_DESTAQUE);
    }
    public void Prender() {
        setBackground(cor);
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

}
