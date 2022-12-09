package damas.dominio;
import java.util.ArrayList;
public class Dapoobs {

    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private int turno = 1;
    private int jugada = 0;
    private int jugadasincomerficha = 0;
    private ArrayList<Casilla> fichascomer;
    private Casilla casillaBloqueada;

    public Dapoobs() {
        tablero = new Tablero();
        fichascomer = new ArrayList<Casilla>();
        jugador1 = new Jugador("ROJO");
        jugador2 = new Jugador("AZUL");
        turno = 1;
        jugada = 0;
        jugadasincomerficha = 0;
        casillaBloqueada = null;

        colocarfichas(tablero);
    }
    public void moverDama(int origenX, int origenY, int destinoX, int destinoY) {
        Casilla origen = tablero.getCasilla(origenX, origenY);
        Casilla destino = tablero.getCasilla(destinoX, destinoY);
        Dama ficha = origen.getFicha();
        if (casillaBloqueada == null) {
            if ((getTurno() == 1 && (ficha.getTipo() == Dama.FICHA_AZUL || ficha.getTipo() == Dama.REYNA_AZUL)) ||
                (getTurno() == 2 && (ficha.getTipo() == Dama.FICHA_ROJA || ficha.getTipo() == Dama.REYNA_ROJA))) {
                if (ficha.MovimentoValido(destino)) {
                    if (ValidarMovimiento(origen, destino)) {
                        ficha.mover(destino);
                        if (fichascomer.size() > 0) {
                            comerficha();
                            jugadasincomerficha = 0;
                            if (Verificarjugada(destino)) {
                                casillaBloqueada = destino;
                            } else {
                                Cambioturno();
                            }
                        } else {
                            jugadasincomerficha++;
                            Cambioturno();
                        }
                        jugada++;
                        if (VerificarCoronar(destino)) Coronar(destino);
                    }
                }
            }
        } else {
            if ((origen.equals(casillaBloqueada))) {
                if(ValidarMovimiento(origen, destino)) {
                    if (fichascomer.size() != 0) {
                        casillaBloqueada = null;
                        moverDama(origenX, origenY, destinoX, destinoY);
                    }
                }
            }
        }
    }
    private boolean ValidarMovimiento(Casilla origen, Casilla destino) {
        Dama ficha = origen.getFicha();
        int casillasconfichasseguidas = 0;
        if (destino.getFicha() != null) return false;
        // SENTIDO MOVIMIENTO
        int sentidoX = (destino.getX() - origen.getX());
        int sentidoY = (destino.getY() - origen.getY());
        int distanciaX = Math.abs(sentidoX); 
        int distanciaY = Math.abs(sentidoY);
        if ((distanciaX == 0) || (distanciaY == 0)) return false;
        sentidoX = sentidoX/distanciaX;
        sentidoY = sentidoY/distanciaY;
        // REGLA DE 2 CASILLAS DE SEPARACION
        if ((distanciaX == 2 && distanciaY == 2) &&
            ((ficha.getTipo() == Dama.FICHA_AZUL) || (ficha.getTipo() == Dama.FICHA_ROJA))) {
            Casilla casilla = tablero.getCasilla((destino.getX() - sentidoX), (destino.getY() - sentidoY));
            if (casilla.getFicha() == null) return false;
        } else {
            // REGLA DE 1 CASILLAS DE SEPARACION
            if (ficha.getTipo() == Dama.FICHA_AZUL) {
                if ((distanciaX == 1 || distanciaY == 1) && (distanciaX == distanciaY) && sentidoY == 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                // REGLA MOVIMIENTO ROJAS
                if (ficha.getTipo() == Dama.FICHA_ROJA) {
                    if ((distanciaX == 1 || distanciaY == 1) && (distanciaX == distanciaY) && sentidoY == -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        //REVISA CASILLAS
        // 1 - SI HAY MAS DE UNA FICHA SEGUIDA EN EL CAMINO (VERDADERO RETORNA FALSO)
        // 2 - SI HAY NO MAS DE UNA FICHA SEGUIDA EN EL CAMINO (VERDADERO RETORNA FALSO)
        int i = origen.getX();
        int j = origen.getY();
        while (!((i == destino.getX()) || (j == destino.getY()))) {
            i += sentidoX;
            j += sentidoY;
            Casilla actual = tablero.getCasilla(i, j);
            Dama fichactual = actual.getFicha();
            if (!(fichactual == null)) {
                casillasconfichasseguidas += 1;
                // MISMO TIPO
                if ((ficha.getTipo() == Ficha.FICHA_AZUL || ficha.getTipo() == Ficha.REYNA_AZUL) && (fichactual.getTipo() == Dama.FICHA_AZUL || fichactual.getTipo() == Dama.REYNA_AZUL)) {
                    if (fichascomer.size() > 0) fichascomer.removeAll(fichascomer);
                    return false;
                }
                if ((ficha.getTipo() == Ficha.FICHA_ROJA || ficha.getTipo() == Ficha.REYNA_ROJA) && (fichactual.getTipo() == Dama.FICHA_ROJA || fichactual.getTipo() == Dama.REYNA_ROJA)) {
                    if (fichascomer.size() > 0) fichascomer.removeAll(fichascomer);
                    return false;
                }
            } else {
                // SI HAY FICHAS PARA COMER SEGUIDAS
                if (casillasconfichasseguidas == 1) {
                    Casilla casilla = tablero.getCasilla((actual.getX() - sentidoX), (actual.getY() - sentidoY));
                    fichascomer.add(casilla);
                }
                casillasconfichasseguidas = 0;
            }
            if (casillasconfichasseguidas == 2) {
                if (fichascomer.size() > 0) fichascomer.removeAll(fichascomer);
                return false;
            }
        }
        return true;
    }
    private boolean Verificar(Casilla origem, int deltaX, int deltaY) {
        Dama ficha = origem.getFicha();
        int x = origem.getX();
        int y = origem.getY();
        int Fichaseguidasenelcamino = 0;
        // Tipo
        if ((ficha.getTipo() == Ficha.FICHA_AZUL) || (ficha.getTipo() == Ficha.FICHA_ROJA)) {
            x += deltaX;
            y += deltaY;
            try {
                Dama pecaAtual = tablero.getCasilla(x, y).getFicha();
                if (!( pecaAtual == null)) {
                    if (tablero.getCasilla((x + deltaX), (y + deltaY)).getFicha() != null) {
                        return false;
                    }
                    // Color
                    if ((ficha.getTipo() == Ficha.FICHA_AZUL) &&
                        ((pecaAtual.getTipo() == Ficha.REYNA_AZUL || pecaAtual.getTipo() == Ficha.FICHA_AZUL))) {
                            return false;
                    } else {
                        if ((ficha.getTipo() == Ficha.FICHA_ROJA) &&
                        ((pecaAtual.getTipo() == Ficha.REYNA_ROJA || pecaAtual.getTipo() == Ficha.FICHA_ROJA))) {
                            return false;
                        }
                    }

                    return true;
                }
            } catch (Exception e) {
                return false;
            }

        } else {
            while (!((x == -1 || x == 10) || (y == -1 || y == 10))) {
                x += deltaX;
                y += deltaY;
                try {
                    Dama fichaactual = tablero.getCasilla(x, y).getFicha();
                    if (!( fichaactual == null)) {
                        Fichaseguidasenelcamino += 1;
                        if ((ficha.getTipo() == Ficha.REYNA_AZUL) &&
                            ((fichaactual.getTipo() == Ficha.FICHA_AZUL) || (fichaactual.getTipo() == Ficha.REYNA_AZUL))) {
                                return false;
                        } else {
                            if ((ficha.getTipo() == Ficha.REYNA_ROJA) &&
                            ((fichaactual.getTipo() == Ficha.FICHA_ROJA) || (fichaactual.getTipo() == Ficha.REYNA_ROJA))) {
                                    return false;
                            }   
                        }
                    } else {
                        if (Fichaseguidasenelcamino == 1) {
                            return true;
                        }
                        if (Fichaseguidasenelcamino == 2) {
                            return false;
                        }
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }

    private boolean Verificarjugada(Casilla origen) {
        if (Verificar(origen, Tablero.X_IZQUIERDA, Tablero.Y_ARRIBA)) {
            return true;
        } else {
            if (Verificar(origen, Tablero.X_DERECHA, Tablero.Y_ARRIBA)) {
                return true;
            } else {
                if (Verificar(origen, Tablero.X_DERECHA, Tablero.Y_ABAJO)) {
                    return true;
                } else {
                    if (Verificar(origen, Tablero.X_IZQUIERDA, Tablero.Y_ABAJO)) {
                        return true;
                    }                    
                }
            }

        }
        return false;
    }
    private void comerficha() {

        int fichascomidas = fichascomer.size();
        if (getTurno() == 1) jugador1.addPunto(1);
        if (getTurno() == 2) jugador2.addPunto(1);
        for (Casilla casilla : fichascomer) {
            casilla.removerficha();
        }
        fichascomer.removeAll(fichascomer);
        jugadasincomerficha = 0;
    }
    private boolean VerificarCoronar(Casilla casilla) {
        // Azules
        if (casilla.getFicha().getTipo() == Ficha.FICHA_AZUL) {
            if (casilla.getY() == 9) return true;
        }
        // Rojas
        if (casilla.getFicha().getTipo() == Ficha.FICHA_ROJA) {
            if (casilla.getY() == 0) return true;
        }
        return false;
    }

    private void Coronar(Casilla casilla) {
        Dama dama = casilla.getFicha();
        if (dama.getTipo() == Ficha.FICHA_AZUL) {
            Reyna reyna = new Reyna(casilla, Ficha.REYNA_AZUL);
            dama = (Reyna) reyna;
        } else {
            Reyna reyna = new Reyna(casilla, Ficha.REYNA_ROJA);
            dama = (Reyna) reyna;
        }
    }
    public void colocarfichas(Tablero tablero) {
        //SUPERIOR
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 3; y++) {
                if((x % 2 == 0) && (y % 2 == 0)) {
                    Casilla casilla = tablero.getCasilla(x, y);
                    new Dama(casilla, Dama.FICHA_AZUL);
                }
                else if ((x % 2 != 0) && (y % 2 != 0)){
                    Casilla casilla = tablero.getCasilla(x, y);
                    new Dama(casilla, Ficha.FICHA_AZUL);
                }
            }
        }
        //INFERIOR
        for (int x = 0; x < 10; x++) {
            for (int y = 7; y < 10; y++) {
                if ((x % 2 != 0) && (y % 2 != 0)) {
                    Casilla casilla = tablero.getCasilla(x, y);
                    new Dama(casilla, Ficha.FICHA_ROJA);
                }
                else if ((x % 2 == 0) && (y % 2 == 0)) {
                    Casilla casilla = tablero.getCasilla(x, y);
                    new Dama(casilla, Ficha.FICHA_ROJA);
                }
            }
        }
    }
    public void Cambioturno() {
        if (turno == 1) {
            turno = 2;
        } else {
            turno = 1;
        }
    }
    public int getGanador() {
        if (jugador1.getPuntos() == 15) return 1;
        if (jugador2.getPuntos() == 15) return 2;
        return 0;
    }
    public Tablero getTablero() {
        return tablero;
    }
    public Jugador getJugador1() {
        return jugador1;
    }
    public Jugador getJugador2() {
        return jugador2;
    }
    public int getTurno() {
        return turno;
    }
    public int getJugadasincomer() {
        return jugadasincomerficha;
    }
    public int getJugada() {
        return jugada;
    }

    public String toString() {
        String retorno = "Turno: ";
        if (getTurno() == 1) {
            retorno += jugador1.getNombre();
            retorno += "\n";
        } else if (getTurno() == 2) {
            retorno += jugador2.getNombre();
            retorno += "\n";
        }
        retorno += "\n";
        retorno += "Nº de jugadas hechas: " + getJugada() + "\n";
        retorno += "\n";
        retorno += "Informacion del(a) jugador(a) " + jugador1.getNombre() + "\n";
        retorno += "Puntos: " + jugador1.getPuntos() + "\n";
        retorno += "Nº de fichas restantes: " + (15 - jugador2.getPuntos()) + "\n";
        retorno += "\n";
        retorno += "Informacion del(a) jugador(a) " + jugador2.getNombre() + "\n";
        retorno += "Puntos: " + jugador2.getPuntos() + "\n";
        retorno += "Nº de fichas restantes: " + (15 - jugador1.getPuntos()) + "\n";
        retorno += "\n";
        if (casillaBloqueada != null) {
            retorno += "\n";
            retorno += "Mueve la ficha en la casilla " + casillaBloqueada.getX() + ":" + casillaBloqueada.getY() + "!";
        }
        return retorno;
    }
}