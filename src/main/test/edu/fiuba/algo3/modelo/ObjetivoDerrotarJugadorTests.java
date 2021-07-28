package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ObjetivoDerrotarJugadorTests {

    @Test
    public void unJugadorDerrotaADosJugadoresPeroNingunoEsElObjetivoYElObjetivoNoSeCumple() {
        Jugador jugador1 = Mockito.mock(Jugador.class);
        Jugador jugador2 = Mockito.mock(Jugador.class);
        Jugador jugador3 = Mockito.mock(Jugador.class);
        Jugador jugador4 = Mockito.mock(Jugador.class);

        when(jugador1.derrotoA(jugador2)).thenReturn(true);
        when(jugador1.derrotoA(jugador3)).thenReturn(true);
        when(jugador1.derrotoA(jugador4)).thenReturn(false);

        IObjetivo obj = new ObjetivoDerrotarJugador(jugador4);

        boolean cumplido = obj.cumplido(jugador1);

        assertFalse(cumplido);
    }

    @Test
    public void unJugadorDerrotaAlJugadorObjetivoYElObjetivoSeCumple() {
        Jugador jugador1 = Mockito.mock(Jugador.class);
        Jugador jugador2 = Mockito.mock(Jugador.class);

        when(jugador1.derrotoA(jugador2)).thenReturn(true);

        IObjetivo obj = new ObjetivoDerrotarJugador(jugador2);

        boolean cumplido = obj.cumplido(jugador1);

        assertTrue(cumplido);
    }
}
