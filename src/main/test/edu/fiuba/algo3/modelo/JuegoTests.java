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

    private Jugador jugador_1;
    private Jugador jugador_2;
    private Jugador jugador_3;
    private Jugador jugador_4;

    @BeforeEach
    public void setUp(){
        jugador_1 = new Jugador(1);
        jugador_2 = new Jugador(2);
        jugador_3 = new Jugador(3);
        jugador_4 = new Jugador(4);
    }

    @Test
    public void JuegoCon2JugadoresCadaUnoTiene25Paises() throws FileNotFoundException {
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador_1);
        jugadores.add(jugador_2);

        Juego juego = new Juego(jugadores);

        assertEquals(jugador_1.obtenerCantidadPaises(), 25);
        assertEquals(jugador_2.obtenerCantidadPaises(), 25);
    }

    @Test
    public void JuegoCon3JugadoresCadaUnoTiene16o17Paises() throws FileNotFoundException {
        List<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador_1);
        jugadores.add(jugador_2);
        jugadores.add(jugador_3);

        Juego juego = new Juego(jugadores);

        //TODO y estos print??
        //System.out.println(jugador_1.obtenerCantidadPaises());
        //System.out.println(jugador_2.obtenerCantidadPaises());
        //System.out.println(jugador_3.obtenerCantidadPaises());

        assertTrue(jugador_1.obtenerCantidadPaises()<18 && 15<jugador_1.obtenerCantidadPaises());
        assertTrue(jugador_2.obtenerCantidadPaises()<18 && 15<jugador_2.obtenerCantidadPaises());
        assertTrue(jugador_3.obtenerCantidadPaises()<18 && 15<jugador_3.obtenerCantidadPaises());

    }

    @Test
    public void JuegoCon4JugadoresCadaUnoTiene12o13Paises() throws FileNotFoundException {
        List<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador_1);
        jugadores.add(jugador_2);
        jugadores.add(jugador_3);
        jugadores.add(jugador_4);

        Juego juego = new Juego(jugadores);

        assertTrue(jugador_1.obtenerCantidadPaises()<14 && 11<jugador_1.obtenerCantidadPaises());
        assertTrue(jugador_2.obtenerCantidadPaises()<14 && 11<jugador_2.obtenerCantidadPaises());
        assertTrue(jugador_3.obtenerCantidadPaises()<14 && 11<jugador_3.obtenerCantidadPaises());
        assertTrue(jugador_4.obtenerCantidadPaises()<14 && 11<jugador_4.obtenerCantidadPaises());

    }

    @Test
    public void LosPaisesNoSeRepitenEntreJugadores() throws FileNotFoundException {
        List<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador_1);
        jugadores.add(jugador_2);

        Juego juego = new Juego(jugadores);

        jugador_1.paises.forEach(pais -> assertFalse(jugador_2.paises.contains(pais)));

    }

    @Test
    public void unJugadorConTodosLosPaisesRecibe25EjercitosGenerales() throws FileNotFoundException {
        List<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador_1);
        Juego juego = new Juego(jugadores);

        juego.asignarEjercitosDisponibles(0);
        assertEquals(jugador_1.obtenerCantidadEjercitosDisponibles(), 25);
    }

    @Test
    public void unJugadorConTodosLosPaisesDeAmericaDelSurRecibe4EjercitosParaEseContinente() throws FileNotFoundException {
        List<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador_1);
        Juego juego = new Juego(jugadores);

        juego.asignarEjercitosDisponibles(0);
        int ejercitosDisponibles = jugador_1.obtenerCantidadEjercitosDisponibleEnContinente(juego.getContinentePorNombre("America del Sur"));
        assertEquals(ejercitosDisponibles, 3);
    }
}
