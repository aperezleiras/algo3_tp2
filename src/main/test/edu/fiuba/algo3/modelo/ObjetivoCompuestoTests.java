package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ObjetivoCompuestoTests {

    @Test
    public void unJugadorQueCumplio1De2ObjetivosParticularesNoCumpleElObjetivoCompuesto() {
        IObjetivo obj1 = Mockito.mock(ObjetivoOcupacionContinente.class);
        IObjetivo obj2 = Mockito.mock(ObjetivoOcupacionPaises.class);
        Jugador jugador = Mockito.mock(Jugador.class);

        when(obj1.cumplido(jugador)).thenReturn(true);
        when(obj2.cumplido(jugador)).thenReturn(false);

        IObjetivo obj = new ObjetivoCompuesto(new ArrayList<>(Arrays.asList(obj1, obj2)));

        boolean cumplido = obj.cumplido(jugador);
        assertFalse(cumplido);
    }

    @Test
    public void unJugadorQueCumplio2De2ObjetivosParticularesCumpleElObjetivoCompuesto() {
        IObjetivo obj1 = Mockito.mock(ObjetivoOcupacionContinente.class);
        IObjetivo obj2 = Mockito.mock(ObjetivoOcupacionPaises.class);
        Jugador jugador = Mockito.mock(Jugador.class);

        when(obj1.cumplido(jugador)).thenReturn(true);
        when(obj2.cumplido(jugador)).thenReturn(true);

        IObjetivo obj = new ObjetivoCompuesto(new ArrayList<>(Arrays.asList(obj1, obj2)));

        boolean cumplido = obj.cumplido(jugador);
        assertTrue(cumplido);
    }
}
