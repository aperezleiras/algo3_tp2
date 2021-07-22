package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TurnoTests {

    @Test
    public void test(){
        assertEquals(1,1);

    }


    @Test
    public void enLaRondaDeColocacionSeActivanLasTarjetasDePaisDelJugador() {
        Turno turno = new Turno();
        Jugador jugador1 = new Jugador(1);
        Pais argentina = new Pais("Argentina", null, new Ejercitos(1));
        Pais brasil = new Pais("Brasil", null, new Ejercitos(1));
        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);
        CartaPais cartaArg = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais cartaBr = new CartaPais(brasil, Simbolo.CAÑON);
        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(cartaArg, cartaBr)));
        jugador1.levantarCartaPais(mazo);
        jugador1.levantarCartaPais(mazo);

        turno.agregarEjercitosSegunCartas(jugador1);

        assertEquals(argentina.cantidadEjercitos(), 3);
        assertEquals(brasil.cantidadEjercitos(), 3);
    }

    @Test
    public void enLaRondaDeAtaqueUnJugadorConquistaDosPaises() {
        Turno turno = new Turno();
        Jugador atacante = new Jugador(1);
        Jugador defensor = new Jugador(2);
        Pais argentina = new Pais("Argentina", null, new Ejercitos(3));
        Pais brasil = new Pais("Brasil", null, new Ejercitos(2));
        Pais chile = new Pais("Brasil", null, new Ejercitos(2));
        atacante.asignarPais(argentina);
        defensor.asignarPais(brasil);
        defensor.asignarPais(chile);
        CartaPais cartaArg = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais cartaBr = new CartaPais(brasil, Simbolo.CAÑON);
        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(cartaArg, cartaBr)));

        IDado dado = Mockito.mock(Dado.class);
        when(dado.obtenerDadoAtacante(any(Pais.class))).thenReturn(Arrays.asList(6,6));
        when(dado.obtenerDadoDefensor(any(Pais.class))).thenReturn(Arrays.asList(1,1));

        turno.rondaAtacar(atacante, defensor, mazo);

    }

}
