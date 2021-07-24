package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        Continente sudamerica = new Continente("America del Sur");
        sudamerica.agregarPais(argentina);
        sudamerica.agregarPais(brasil);
        sudamerica.agregarPais(chile);

        jugador = new Jugador(1, new DepositoEjercitos(new ArrayList<>(Arrays.asList(sudamerica))));
    }


    @Test
    public void siUnJugadorSolicitaUnCanjeConCartasQueNoPoseeSeLanzaCartaNoMePerteneceException() {
        CartaPais carta1 = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.BARCO);
        CartaPais carta3 = new CartaPais(chile, Simbolo.BARCO);

        ArrayList<CartaPais> cartas = new ArrayList<>(Arrays.asList(carta1, carta2, carta3));
        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>());

        assertThrows(CartaNoMePerteneceException.class,
                ()->{
                    jugador.canjearCartas(cartas, mazo);
                });
    }


    @Test
    public void DosIgualesUnaDistintaNoSonCanjeables() {
        List<CartaPais> cartas = new ArrayList<>(
                Arrays.asList(
                        new CartaPais(argentina, Simbolo.BARCO),
                        new CartaPais(brasil, Simbolo.BARCO),
                        new CartaPais(chile, Simbolo.GLOBO)
                )
        );

        MazoCartasPais mazo = new MazoCartasPais(cartas);
        Canje canje = new Canje(cartas, jugador);

        assertFalse(canje.cartasSonCanjeables());
    }

    /*
    @Test
    public void alRealizarseUnCanjeCorrectamenteSeAgreganEjercitosDisponiblesAlJugador() {
        List<CartaPais> cartas = new ArrayList<>(
                Arrays.asList(
                        new CartaPais(argentina, Simbolo.BARCO),
                        new CartaPais(brasil, Simbolo.GLOBO),
                        new CartaPais(chile, Simbolo.CAÑON)
                )
        );

        MazoCartasPais mazo = new MazoCartasPais(cartas);

        jugador.levantarCartaPais(mazo);
        jugador.levantarCartaPais(mazo);
        jugador.levantarCartaPais(mazo);

        Canje canje = new Canje(cartas, jugador);

        assertEquals(0, jugador.obtenerEjercitosGeneralesDisponibles());

        canje.efectuarCanje(mazo);

        assertTrue(jugador.obtenerEjercitosGeneralesDisponibles() > 0);
    }
    */

    @Test
    public void todasDistintasSonCanjeables() {
        //arrange
        List<CartaPais> cartas = new ArrayList<>(
                Arrays.asList(
                        new CartaPais(argentina, Simbolo.BARCO),
                        new CartaPais(brasil, Simbolo.GLOBO),
                        new CartaPais(chile, Simbolo.CAÑON)
                )
        );

        Canje canje = new Canje(cartas, jugador);

        //act
        Assertions.assertTrue(canje.cartasSonCanjeables());
    }

    @Test
    public void todasIgualesSonCanjeables() {
        //arrange
        List<CartaPais> cartas = new ArrayList<>(
                Arrays.asList(
                        new CartaPais(argentina, Simbolo.BARCO),
                        new CartaPais(brasil, Simbolo.BARCO),
                        new CartaPais(chile, Simbolo.BARCO)
                )
        );

        Canje canje = new Canje(cartas, jugador);

        //act
        Assertions.assertTrue(canje.cartasSonCanjeables());
    }

    @Test
    public void todasIgualesConUnComodinSonCanjeables() {
        //arrange
        List<CartaPais> cartas = new ArrayList<>(
                Arrays.asList(
                        new CartaPais(argentina, Simbolo.BARCO),
                        new CartaPais(brasil, Simbolo.BARCO),
                        new CartaPais(chile, Simbolo.COMODIN)
                )
        );

        Canje canje = new Canje(cartas, jugador);

        //act
        Assertions.assertTrue(canje.cartasSonCanjeables());
    }

    @Test
    public void todasDistintasConUnComodinSonCanjeables() {
        //arrange
        List<CartaPais> cartas = new ArrayList<>(
                Arrays.asList(
                        new CartaPais(argentina, Simbolo.BARCO),
                        new CartaPais(brasil, Simbolo.COMODIN),
                        new CartaPais(chile, Simbolo.CAÑON)
                )
        );

        Canje canje = new Canje(cartas, jugador);

        //act
        Assertions.assertTrue(canje.cartasSonCanjeables());
    }

    @Test
    public void dosComodinesSonCanjeables() {
        //arrange
        List<CartaPais> cartas = new ArrayList<>(
                Arrays.asList(
                        new CartaPais(argentina, Simbolo.BARCO),
                        new CartaPais(brasil, Simbolo.COMODIN),
                        new CartaPais(chile, Simbolo.COMODIN)
                )
        );

        Canje canje = new Canje(cartas, jugador);

        //act
        Assertions.assertTrue(canje.cartasSonCanjeables());
    }
}
