package edu.fiuba.algo3.modelo;

import javafx.scene.control.Button;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTests {

    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugador3;
    private Jugador jugador4;
    HashMap<String, Button> mapBotones;
    ArrayList<String> nombres = new ArrayList<>();


    @Test
    public void JuegoCon2JugadoresCadaUnoTiene25Paises() throws FileNotFoundException {

        nombres.add("Pablo");
        nombres.add("Agustin");

        Juego juego = new Juego(nombres);
        HashMap<String, Pais> paises = juego.getPaises();
        juego.asignarPaises();
        jugador1 = juego.getJugador(1);
        jugador2 = juego.getJugador(2);

        assertEquals(jugador1.obtenerCantidadPaises(), 25);
        assertEquals(jugador2.obtenerCantidadPaises(), 25);
    }

    @Test
    public void JuegoCon3JugadoresCadaUnoTiene16o17Paises() throws FileNotFoundException {

        nombres.add("Pablo");
        nombres.add("Agustin");
        nombres.add("Ariadna");

        Juego juego = new Juego(nombres);
        HashMap<String, Pais> paises = juego.getPaises();
        juego.asignarPaises();
        jugador1 = juego.getJugador(1);
        jugador2 = juego.getJugador(2);
        jugador3 = juego.getJugador(3);

        assertTrue(jugador1.obtenerCantidadPaises()<18 && 15<jugador1.obtenerCantidadPaises());
        assertTrue(jugador2.obtenerCantidadPaises()<18 && 15<jugador2.obtenerCantidadPaises());
        assertTrue(jugador3.obtenerCantidadPaises()<18 && 15<jugador3.obtenerCantidadPaises());
    }

    @Test
    public void JuegoCon4JugadoresCadaUnoTiene12o13Paises() throws FileNotFoundException {

        nombres.add("Pablo");
        nombres.add("Agustin");
        nombres.add("Ariadna");
        nombres.add("Franco");

        Juego juego = new Juego(nombres);
        juego.asignarPaises();

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
        nombres.add("Pablo");
        nombres.add("Agustin");

        Juego juego = new Juego(nombres);

        juego.asignarPaises();
        jugador1 = juego.getJugador(1);
        jugador2 = juego.getJugador(2);

        jugador1.getPaises().forEach(pais -> assertFalse(jugador2.getPaises().contains(pais)));
    }
}
