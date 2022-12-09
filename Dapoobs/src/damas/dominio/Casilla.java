package damas.dominio;
public class Casilla {

    private int x;
    private int y;
    private Dama ficha;
    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        this.ficha = null;
    }
    public void colocarDama(Dama ficha) {
        this.ficha = ficha;
    }
    public void removerficha() {
        ficha = null;
    }
    public Dama getFicha() {
        return ficha;
    }
    public boolean posicionFicha() {
        return ficha != null;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
