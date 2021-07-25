package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class JuegoTests {

    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugador3;
    private Jugador jugador4;

    @Test
    public void JuegoCon2JugadoresCadaUnoTiene25Paises() throws FileNotFoundException {
        Juego juego = new Juego(2);

        jugador1 = juego.getJugador(1);
        jugador2 = juego.getJugador(2);

        assertEquals(jugador1.obtenerCantidadPaises(), 25);
        assertEquals(jugador2.obtenerCantidadPaises(), 25);
    }

    @Test
    public void JuegoCon3JugadoresCadaUnoTiene16o17Paises() throws FileNotFoundException {
        Juego juego = new Juego(3);

        jugador1 = juego.getJugador(1);
        jugador2 = juego.getJugador(2);
        jugador3 = juego.getJugador(3);

        assertTrue(jugador1.obtenerCantidadPaises()<18 && 15<jugador1.obtenerCantidadPaises());
        assertTrue(jugador2.obtenerCantidadPaises()<18 && 15<jugador2.obtenerCantidadPaises());
        assertTrue(jugador3.obtenerCantidadPaises()<18 && 15<jugador3.obtenerCantidadPaises());
    }

    @Test
    public void JuegoCon4JugadoresCadaUnoTiene12o13Paises() throws FileNotFoundException {
        Juego juego = new Juego(4);

        jugador1 = juego.getJugador(1);
        jugador2 = juego.getJugador(2);
        jugador3 = juego.getJugador(3);
        jugador4 = juego.getJugador(4);

        assertTrue(jugador1.obtenerCantidadPaises()<14 && 11<jugador1.obtenerCantidadPaises());
        assertTrue(jugador2.obtenerCantidadPaises()<14 && 11<jugador2.obtenerCantidadPaises());
        assertTrue(jugador3.obtenerCantidadPaises()<14 && 11<jugador3.obtenerCantidadPaises());
        assertTrue(jugador4.obtenerCantidadPaises()<14 && 11<jugador4.obtenerCantidadPaises());

    }

    @Test
    public void LosPaisesNoSeRepitenEntreJugadores() throws FileNotFoundException {
        Juego juego = new Juego(2);

        jugador1 = juego.getJugador(1);
        jugador2 = juego.getJugador(2);

        jugador1.paises.forEach(pais -> assertFalse(jugador2.paises.contains(pais)));
    }
}
