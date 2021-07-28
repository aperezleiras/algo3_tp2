package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ObjetivoOcupacionContinenteTests {

    @Test
    public void unJugadorQueControlaSoloAsiaNoCumpleElObjetivoDeOcuparEuropa() {
        Jugador jugador1 = new Jugador(1, new DepositoEjercitos(new ArrayList<>()), "j");

        Continente europa = Mockito.mock(Continente.class);
        when(europa.completo(jugador1)).thenReturn(false);

        Continente asia = Mockito.mock(Continente.class);
        when(asia.completo(jugador1)).thenReturn(true);

        IObjetivo obj = new ObjetivoOcupacionContinente(europa);
        boolean cumplido = obj.cumplido(jugador1);

        assertFalse(cumplido);
    }

    @Test
    public void unJugadorQueControlaAsiaYEuropaCumpleElObjetivoDeOcuparEuropa() {
        Jugador jugador1 = new Jugador(1, new DepositoEjercitos(new ArrayList<>()), "j");

        Continente europa = Mockito.mock(Continente.class);
        when(europa.completo(jugador1)).thenReturn(true);

        Continente asia = Mockito.mock(Continente.class);
        when(asia.completo(jugador1)).thenReturn(true);

        IObjetivo obj = new ObjetivoOcupacionContinente(europa);
        boolean cumplido = obj.cumplido(jugador1);

        assertTrue(cumplido);
    }
}
