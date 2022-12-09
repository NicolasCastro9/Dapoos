package damas.dominio;

public class Tablero {
    public static final int MAX_FILA = 10;
    public static final int MAX_COLUMNAS = 10;
    public static final int X_IZQUIERDA = -1;
    public static final int X_DERECHA = 1;
    public static final int Y_ABAJO = -1;
    public static final int Y_ARRIBA = 1;
    private Casilla[][] casillas;
    public Tablero() {
        montarTablero();
    }
    private void montarTablero() {
        casillas = new Casilla[MAX_FILA][MAX_COLUMNAS];
        for (int x = 0; x < MAX_FILA; x++) {
            for (int y = 0; y < MAX_COLUMNAS; y++) {
                Casilla casilla = new Casilla(x, y);
                casillas[x][y] = casilla;
            }
        }
    }
    public Casilla getCasilla(int x, int y) {
        return casillas[x][y];
    }
}