package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ObjetivoOcupacionPaisesTests {


    @Test
    public void unJugadorCon30PaisesCumpleElObjetivoDeOcupar20() {
        Jugador jugador1 = Mockito.mock(Jugador.class);
        when(jugador1.obtenerCantidadPaises()).thenReturn(30);

        IObjetivo obj = new ObjetivoOcupacionPaises(20);
        boolean cumplido = obj.cumplido(jugador1);

        assertTrue(cumplido);
    }

    @Test
    public void unJugadorCon30PaisesNoCumpleElObjetivoDeOcupar35() {
        Jugador jugador1 = Mockito.mock(Jugador.class);
        when(jugador1.obtenerCantidadPaises()).thenReturn(30);

        IObjetivo obj = new ObjetivoOcupacionPaises(35);
        boolean cumplido = obj.cumplido(jugador1);

        assertFalse(cumplido);
    }
}
