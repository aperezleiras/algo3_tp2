package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CartaNoMePerteneceException;
import edu.fiuba.algo3.exception.CartaYaActivadaException;
import edu.fiuba.algo3.exception.PaisNoMePerteneceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CartaPaisTests {

    private MazoCartasPais mazo;

    private Pais argentina;
    private Pais chile;
    private Pais brasil;

    private Jugador jugador1;
    private Jugador jugador2;


    @BeforeEach
    public void setUp() {
        mazo = new MazoCartasPais(new ArrayList<>());
        argentina = new Pais("Argentina", Arrays.asList("Brasil", "Chile"), new Ejercitos(1));
        chile = new Pais("Chile", Arrays.asList("Argentina"), new Ejercitos(1));
        brasil = new Pais("Brasil", Arrays.asList("Argentina"), new Ejercitos(1));
        jugador1 = new Jugador(1);
        jugador2 = new Jugador(2);
        jugador1.asignarPais(argentina);
        jugador1.asignarPais(chile);
        jugador2.asignarPais(brasil);
    }

    @Test
    public void porDefectoUnaCartaNoEstaActivada() {
        CartaPais carta = new CartaPais(argentina, Simbolo.BARCO);

        assertFalse(carta.fueActivada());
    }

    @Test
    public void siUnaCartaEsActivadaPorUnJugadorQueNoLaPoseeSeLanzaCartaNoMePerteneceException() {
        CartaPais cartaArgentina = new CartaPais(argentina, Simbolo.BARCO);

        assertThrows(CartaNoMePerteneceException.class,
                () -> {
                    jugador1.activarCarta(cartaArgentina);
                });
    }

    @Test
    public void alActivarUnaCartaYaActivadaSeLanzaCartaYaActivadaException() {
        CartaPais cartaArgentina = new CartaPais(argentina, Simbolo.BARCO);
        mazo.agregarCarta(cartaArgentina);
        jugador1.levantarCartaPais(mazo);
        jugador1.activarCarta(cartaArgentina);

        assertThrows(CartaYaActivadaException.class,
                () -> {
                    jugador1.activarCarta(cartaArgentina);
                });
    }

    @Test
    public void unJugadorActivaUnaCartaDeUnPaisAjenoYSeLanzaPaisNoMePerteneceException() {
        CartaPais cartaBrasil = new CartaPais(brasil, Simbolo.BARCO);
        mazo.agregarCarta(cartaBrasil);
        jugador1.levantarCartaPais(mazo);

        assertThrows(PaisNoMePerteneceException.class,
                () -> {
                    jugador1.activarCarta(cartaBrasil);
                });
    }

    @Test
    public void unJugadorActivaUnaCartaSatisfactoriamenteYSeAgreganDosEjercitosAlPais() {
        CartaPais cartaArgentina = new CartaPais(argentina, Simbolo.BARCO);
        mazo.agregarCarta(cartaArgentina);
        jugador1.levantarCartaPais(mazo);

        assertEquals(argentina.cantidadEjercitos(), 1);
        jugador1.activarCarta(cartaArgentina);
        assertEquals(argentina.cantidadEjercitos(), 3);
    }

    @Test
    public void alDevolverUnaCartaActivadaAlMazoEstaPuedeSerActivadaNuevamente() {
        CartaPais cartaChile = new CartaPais(chile, Simbolo.GLOBO);
        mazo.agregarCarta(cartaChile);
        jugador1.levantarCartaPais(mazo);
        jugador1.activarCarta(cartaChile);

        cartaChile.devolverA(mazo);

        assertFalse(cartaChile.fueActivada());
    }

    @Test
    public void tresCartasDelMismoSimboloConCanjeables() {
        CartaPais carta1 = new CartaPais(argentina, Simbolo.GLOBO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.GLOBO);
        CartaPais carta3 = new CartaPais(chile, Simbolo.GLOBO);

        assertTrue(carta1.esCanjeableCon(carta2, carta3));
    }

    @Test
    public void tresCartasDeDistintoSimboloConCanjeables() {
        CartaPais carta1 = new CartaPais(argentina, Simbolo.GLOBO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.CAÑON);
        CartaPais carta3 = new CartaPais(chile, Simbolo.BARCO);

        assertTrue(carta1.esCanjeableCon(carta2, carta3));
    }

    @Test
    public void dosCartasDeMismoSimboloYOtraDistintaNoSonCanjeables() {
        CartaPais carta1 = new CartaPais(argentina, Simbolo.GLOBO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.GLOBO);
        CartaPais carta3 = new CartaPais(chile, Simbolo.BARCO);

        assertFalse(carta1.esCanjeableCon(carta2, carta3));
    }
}
