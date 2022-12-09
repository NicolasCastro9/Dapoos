package damas.presentacion;

import java.awt.Color;
import javax.swing.JPanel;
import damas.dominio.*;
import damas.dominio.Tablero;

/**
 *
 * 
 *
 *
 */
public class TableroGUI extends JPanel {

    private JuegoGUI juegoGUI;
    private CasillaGUI[][] casas;
    public TableroGUI(JuegoGUI juegoGUI) {
        this.juegoGUI = juegoGUI;
        initComponents();
        crearCasillas();
    }

    /**
     *
     */
    private void crearCasillas() {
        casas = new CasillaGUI[10][10];
        for (int y = 9; y >= 0; y--) {
            for (int x = 0; x < 10; x++) {
                Color cor = calcularCasilla(x, y);
                CasillaGUI casa = new CasillaGUI(x, y, cor, this);
                casas[x][y] = casa;
                add(casa);
            }
        }
    }
    private Color calcularCasilla(int x, int y) {
        // linea par
        if (x % 2 == 0) {
            if (y % 2 == 0) {
                return CasillaGUI.COR_ESCURA;
            }
            else {
                return CasillaGUI.COR_CLARA;
            }
        }
        // linea Impar
        else {
            if (y % 2 == 0) {
                return CasillaGUI.COR_CLARA;
            }
            else {
                return CasillaGUI.COR_ESCURA;
            }
        }
    }
    public void actualizar(Dapoobs dapoobs) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                CasillaGUI casillaGUI = casas[x][y];
                Tablero tablero = dapoobs.getTablero();
                Casilla casilla = tablero.getCasilla(x, y);
                if (casilla.posicionFicha()) {
                    Dama peca = casilla.getFicha();
                    switch (peca.getTipo()) {
                        case Dama.FICHA_AZUL:
                            casillaGUI.PonerRoja();
                            break;
                        case Reyna.REYNA_AZUL:
                            casillaGUI.PonerRRoja();
                            break;
                        case Dama.FICHA_ROJA:
                            casillaGUI.PonerAzul();
                            break;
                        case Reyna.REYNA_ROJA:
                            casillaGUI.PonerRAzul();
                            break;
                    }
                }
                else {
                    casillaGUI.apagarFicha();
                }
            }
        }
    }
    public JuegoGUI getJuego() {
        return juegoGUI;
    }
    private void initComponents() {
        setLayout(new java.awt.GridLayout(10, 10));
    }
}
