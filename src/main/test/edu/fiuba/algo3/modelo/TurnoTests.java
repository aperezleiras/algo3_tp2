package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnoTests {

    @Test
    public void finalizarAtaqueCambiaFaseAReagrupar() {
        Jugador jugador1 = Mockito.mock(Jugador.class);
        when(jugador1.estaHabilitadoLevantarCarta()).thenReturn(true);

        Turno turno = new Turno(new ArrayList<>(Arrays.asList(jugador1)));

        turno.finalizarAtaque(new MazoCartasPais(new ArrayList<>()));

        assertEquals(Fase.REAGRUPAR, turno.obtenerFase());
    }

    @Test
    public void finalizarAtaqueNoCambiaDeJugador(){
        Jugador jugador1 = Mockito.mock(Jugador.class);
        Jugador jugador2 = Mockito.mock(Jugador.class);

        Turno turno = new Turno(new ArrayList<>(Arrays.asList(jugador1, jugador2)));

        turno.finalizarAtaque(new MazoCartasPais(new ArrayList<>()));

        assertEquals(jugador1, turno.obtenerJugadorActual());
    }

    @Test
    public void finalizarReagrupeCambiaDeJugador(){
        Jugador jugador1 = Mockito.mock(Jugador.class);
        Jugador jugador2 = Mockito.mock(Jugador.class);

        Turno turno = new Turno(new ArrayList<>(Arrays.asList(jugador1, jugador2)));

        turno.finalizarReagrupe();

        assertEquals(jugador2, turno.obtenerJugadorActual());
    }

    @Test
    public void finalizarReagrupeCambiaAFaseAtacarSiNoEsUltimoJugador() {
        Jugador jugador1 = Mockito.mock(Jugador.class);
        Jugador jugador2 = Mockito.mock(Jugador.class);

        Turno turno = new Turno(new ArrayList<>(Arrays.asList(jugador1, jugador2)));

        turno.finalizarReagrupe();

        assertEquals(Fase.ATACAR, turno.obtenerFase());
    }

    @Test
    public void finalizarReagrupeCambiaAFaseColocarSiEsUltimoJugador() {
        Jugador jugador1 = Mockito.mock(Jugador.class);

        Turno turno = new Turno(new ArrayList<>(Arrays.asList(jugador1)));

        turno.finalizarReagrupe();

        assertEquals(Fase.COLOCAR, turno.obtenerFase());
    }

    @Test
    public void finalizarColocacionCambiaDeJugador(){
        Jugador jugador1 = Mockito.mock(Jugador.class);
        Jugador jugador2 = Mockito.mock(Jugador.class);

        Turno turno = new Turno(new ArrayList<>(Arrays.asList(jugador1, jugador2)));

        turno.finalizarColocacion();

        assertEquals(jugador2, turno.obtenerJugadorActual());
    }

    @Test
    public void finalizarColocacionCambiaAFaseAtacarSiEsElUltimoJugador() {
        Jugador jugador1 = Mockito.mock(Jugador.class);

        Turno turno = new Turno(new ArrayList<>(Arrays.asList(jugador1)));

        turno.finalizarColocacion();

        assertEquals(Fase.ATACAR, turno.obtenerFase());
    }

    @Test
    public void finalizarColocacionCambiaASiguienteJugadorSiNoEsElUltimoJugador() {
        Jugador jugador1 = Mockito.mock(Jugador.class);
        Jugador jugador2 = Mockito.mock(Jugador.class);

        Turno turno = new Turno(new ArrayList<>(Arrays.asList(jugador1, jugador2)));

        turno.finalizarColocacion();

        assertEquals(jugador2, turno.obtenerJugadorActual());
    }

}
