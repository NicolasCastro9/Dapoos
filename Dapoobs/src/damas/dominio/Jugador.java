package damas.dominio;
public class Jugador {
    public static final String DEFAULT_NAME = "Anonimo";
    private String nombre;
    private int puntos;

    public Jugador(String nombre) {

        if (validarNombre(nombre)) {
            this.nombre = nombre;
        } else {
            this.nombre = DEFAULT_NAME;
        }
        puntos = 0;
    }
    private boolean validarNombre(String nome) {
        if (nome.length() > 16) return false;
        return true;
    }
    public void addPunto(int puntos) {
        this.puntos += puntos;
    }

    public int getPuntos() {
        return puntos;
    }
    public String getNombre() {
        return nombre;
    }

}
