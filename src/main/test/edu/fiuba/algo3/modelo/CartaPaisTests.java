package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CartaPaisTests {

    private Pais argentina;
    private Pais chile;
    private Pais brasil;

    private Jugador jugador1;
    private Jugador jugador2;


    @BeforeEach
    public void setUp() {
        argentina = new Pais("Argentina", Arrays.asList("Brasil", "Chile"), new Ejercitos(1));
        chile = new Pais("Chile", Arrays.asList("Argentina"), new Ejercitos(1));
        brasil = new Pais("brasil", Arrays.asList("Argentina"), new Ejercitos(1));
        jugador1 = new Jugador(1);
        jugador2 = new Jugador(2);
        jugador1.asignarPais(argentina);
        jugador1.asignarPais(chile);
        jugador2.asignarPais(brasil);
    }

    @Test
    public void porDefectoUnaCartaNoEstaActivada() {
        CartaPais carta = new CartaPais(argentina, Simbolo.BARCO);

        assertFalse(carta.activada());
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
        CartaPais carta2 = new CartaPais(brasil, Simbolo.CANION);
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
