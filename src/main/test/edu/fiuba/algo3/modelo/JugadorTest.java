package edu.fiuba.algo3.modelo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void alAsignarleUnPaisAUnJugadorEsePaisLePertenece() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());

        jugador.asignarPais(argentina);
        assertTrue(jugador.paisMePertenece(argentina));
    }

    @Test
    public void alColocar3EjercitosEnUnPaisQueLePerteneceSeAgreganSatisfactoriamente() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());

        jugador.asignarPais(argentina);
        jugador.colocarEjercitos(3, argentina);

        assertTrue(argentina.cantidadEjercitosSuperiorA(3));
        assertFalse(argentina.cantidadEjercitosSuperiorA(4));
    }

    @Test
    // Eventualmente hacer una excepcion para esto
    public void noSePermiteTransferirEjercitosDesdeUnPaisConUnEjercito() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos());

        jugador.asignarPais(argentina);
        jugador.asignarPais(brasil);

        jugador.transferirEjercitosDesde(argentina, brasil, 1);

        assertTrue(argentina.cantidadEjercitosSuperiorA(0));
        assertFalse(argentina.cantidadEjercitosSuperiorA(1));

        assertTrue(brasil.cantidadEjercitosSuperiorA(0));
        assertFalse(brasil.cantidadEjercitosSuperiorA(1));
    }

    @Test
    // Eventualmente hacer una excepcion para esto
    public void noSePermiteTransferirEjercitosAUnPaisQueNoLePertenece() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos());

        jugador.asignarPais(argentina);
        jugador.colocarEjercitos(3, argentina);

        jugador.transferirEjercitosDesde(argentina, brasil, 1);

        assertTrue(argentina.cantidadEjercitosSuperiorA(3));
        assertFalse(argentina.cantidadEjercitosSuperiorA(4));

        assertTrue(brasil.cantidadEjercitosSuperiorA(0));
        assertFalse(brasil.cantidadEjercitosSuperiorA(1));
    }

}
