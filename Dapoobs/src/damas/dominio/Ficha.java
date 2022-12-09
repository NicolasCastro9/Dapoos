package damas.dominio;

public interface Ficha {
    public static final int FICHA_AZUL = 0;
    public static final int REYNA_AZUL = 1;
    public static final int FICHA_ROJA = 2;
    public static final int REYNA_ROJA = 3;

    public void mover(Casilla destino);
    public boolean MovimentoValido(Casilla destino);
    public int getTipo();
}
