package damas.dominio;

public class Reyna extends Dama {

    public Reyna(Casilla casilla, int tipo) {
        super(casilla, tipo);
    }

    public boolean MovimentoValido(Casilla destino) {
        int distanciaX = Math.abs((destino.getX() - casilla.getX()));
        int distanciaY = Math.abs((destino.getY() - casilla.getY()));
        if (distanciaX == distanciaY) return true;
        return false;
    }
}
