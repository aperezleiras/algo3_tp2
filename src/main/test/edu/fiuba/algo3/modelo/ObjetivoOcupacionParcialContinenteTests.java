package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ObjetivoOcupacionParcialContinenteTests {

    @Test
    public void unJugadorCon2PaisesEnEuropaNoCumpleObjetivoDeConquistar4PaisesEnEuropa() {
        Pais pais1 = Mockito.mock(Pais.class);
        Pais pais2 = Mockito.mock(Pais.class);
        Pais pais3 = Mockito.mock(Pais.class);

        Continente europa = Mockito.mock(Continente.class);
        when(europa.tienePais(pais1)).thenReturn(true);
        when(europa.tienePais(pais2)).thenReturn(true);
        when(europa.tienePais(pais3)).thenReturn(false);

        Jugador jugador1 = new Jugador(1, new DepositoEjercitos(new ArrayList<>()), "j");
        jugador1.asignarPais(pais1);
        jugador1.asignarPais(pais2);
        jugador1.asignarPais(pais3);

        IObjetivo obj = new ObjetivoOcupacionParcialContinente(europa, 4);
        boolean cumplido = obj.cumplido(jugador1);

        assertFalse(cumplido);
    }

    @Test
    public void unJugadorCon2PaisesEnAfricaCumpleObjetivoDeConquistar2PaisesEnAfrica() {
        Pais pais1 = Mockito.mock(Pais.class);
        Pais pais2 = Mockito.mock(Pais.class);
        Pais pais3 = Mockito.mock(Pais.class);

        Continente africa = Mockito.mock(Continente.class);
        when(africa.tienePais(pais1)).thenReturn(true);
        when(africa.tienePais(pais2)).thenReturn(true);
        when(africa.tienePais(pais3)).thenReturn(false);

        Jugador jugador1 = new Jugador(1, new DepositoEjercitos(new ArrayList<>()), "j");
        jugador1.asignarPais(pais1);
        jugador1.asignarPais(pais2);
        jugador1.asignarPais(pais3);

        IObjetivo obj = new ObjetivoOcupacionParcialContinente(africa, 2);
        boolean cumplido = obj.cumplido(jugador1);

        assertTrue(cumplido);
    }
}
