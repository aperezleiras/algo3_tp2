package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CanjeTests {

    private Pais argentina;
    private Pais chile;
    private Pais brasil;

    private Jugador jugador;


    @BeforeEach
    public void setUp() {
        argentina = new Pais("Argentina", Arrays.asList("Brasil", "Chile"), new Ejercitos(1));
        chile = new Pais("Chile", Arrays.asList("Argentina"), new Ejercitos(1));
        brasil = new Pais("brasil", Arrays.asList("Argentina"), new Ejercitos(1));
        jugador = new Jugador(1);
    }

    @Test
    public void siUnJugadorSolicitaUnCanjeConCartasQueNoPoseeSeLanzaCartaNoMePerteneceException() {
        CartaPais carta1 = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.BARCO);
        CartaPais carta3 = new CartaPais(chile, Simbolo.BARCO);

        ArrayList<CartaPais> cartas = new ArrayList<>();
        MazoCartasPais mazo = new MazoCartasPais(cartas);

        assertThrows(CartaNoMePerteneceException.class,
                ()->{
                    jugador.canjearCartas(carta1, carta2, carta3, mazo);
                });
    }

    @Test
    public void siUnJugadorSolicitaUnCanjeConCartasQueNoSonCanjeableSeLanzaCartasNoCanjeablesException() {
        ArrayList<CartaPais> cartas = new ArrayList<>();
        CartaPais carta1 = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.GLOBO);
        CartaPais carta3 = new CartaPais(chile, Simbolo.BARCO);
        cartas.add(carta1);
        cartas.add(carta2);
        cartas.add(carta3);
        MazoCartasPais mazo = new MazoCartasPais(cartas);
        jugador.levantarCartaPais(mazo);
        jugador.levantarCartaPais(mazo);
        jugador.levantarCartaPais(mazo);

        assertThrows(CartasNoCanjeablesException.class,
                ()->{
                    jugador.canjearCartas(carta1, carta2, carta3, mazo);
                });
    }
}
