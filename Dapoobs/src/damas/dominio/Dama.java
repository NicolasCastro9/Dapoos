package damas.dominio;

public class Dama implements Ficha {
    protected Casilla casilla;
    protected int tipo;
    public Dama(Casilla casilla, int tipo) {
        this.casilla = casilla;
        this.tipo = tipo;
        casilla.colocarDama(this);
    }
    public void mover(Casilla destino) {
        casilla.removerficha();
        destino.colocarDama(this);
        casilla = destino;
    }
    public boolean MovimentoValido(Casilla destino) {

        int distanciaX = Math.abs(destino.getX() - casilla.getX());
        int distanciaY = Math.abs(destino.getY() - casilla.getY());
        if ((distanciaX == 0) || (distanciaY == 0)) return false;
        if ((distanciaX <= 2 || distanciaY <= 2) && (distanciaX == distanciaY)) {
            return true;
        }
        return false;
    }
    public int getTipo() {
        return tipo;
    }
}
