package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CantidadEjercitosInsuficienteException;
import edu.fiuba.algo3.exception.PaisNoLimitrofeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.*;

public class BatallaTests {

    private IDado dadoAtacante;

    private IDado dadoDefensor;

    private Pais atacante;

    private Pais defensor;

    @BeforeEach
    public void setUp(){
        dadoAtacante = Mockito.mock(Dado.class);
        dadoDefensor = Mockito.mock(Dado.class);

        Ejercitos ejercitosArgentina = new Ejercitos(3);
        Ejercitos ejercitosBrasil = new Ejercitos(2);

        atacante = new Pais("Argentina", Arrays.asList("Brasil"), ejercitosArgentina);
        defensor = new Pais("Brasil", Arrays.asList("Argentina"), ejercitosBrasil);
    }

    @Test
    public void PaisConquistaAOtroYCambiaJugador() {
        //arrange
        Jugador elDibu = new Jugador(0);
        Jugador neyPasto = new Jugador(1);
        atacante.asignarJugador(elDibu);
        defensor.asignarJugador(neyPasto);

        when(dadoAtacante.obtenerDadoAtacante(any(Pais.class))).thenReturn(Arrays.asList(6,6));
        when(dadoDefensor.obtenerDadoDefensor(any(Pais.class))).thenReturn(Arrays.asList(1,1));

        IBatalla batalla = new Batalla(atacante, defensor, dadoAtacante, dadoDefensor);

        //act
        batalla.realizarAtaque();

        //assert
        Assertions.assertEquals(elDibu, defensor.getJugador());

    }

    @Test
    public void PaisConquistaAOtroYSeTransfierenEjercitos() {
        //arrange
        Jugador elDibu = new Jugador(0);
        Jugador neyPasto = new Jugador(1);
        atacante.asignarJugador(elDibu);
        defensor.asignarJugador(neyPasto);

        when(dadoAtacante.obtenerDadoAtacante(any(Pais.class))).thenReturn(Arrays.asList(6,6));
        when(dadoDefensor.obtenerDadoDefensor(any(Pais.class))).thenReturn(Arrays.asList(1,1));

        IBatalla batalla = new Batalla(atacante, defensor, dadoAtacante, dadoDefensor);

        //act
        batalla.realizarAtaque();

        //assert
        Assertions.assertEquals(defensor.cantidadEjercitos(), 1);
        Assertions.assertEquals(atacante.cantidadEjercitos(), 2);

    }

    @Test
    public void PaisDefensorGana() {
        //arrange
        when(dadoAtacante.obtenerDadoAtacante(any(Pais.class))).thenReturn(Arrays.asList(1,1));
        when(dadoDefensor.obtenerDadoDefensor(any(Pais.class))).thenReturn(Arrays.asList(6,6));

        IBatalla batalla = new Batalla(atacante, defensor, dadoAtacante, dadoDefensor);

        //act
        batalla.realizarAtaque();

        //assert
        Assertions.assertEquals(atacante.cantidadEjercitos(),1);
    }


    @Test
    public void AmbosPaisesPierden1Ejercito() {
        //arrange
        when(dadoAtacante.obtenerDadoAtacante(any(Pais.class))).thenReturn(Arrays.asList(6,1));
        when(dadoDefensor.obtenerDadoDefensor(any(Pais.class))).thenReturn(Arrays.asList(1,1));

        IBatalla batalla = new Batalla(atacante, defensor, dadoAtacante, dadoDefensor);

        //act
        batalla.realizarAtaque();

        //assert
        Assertions.assertEquals(atacante.cantidadEjercitos(),2);
        Assertions.assertEquals(defensor.cantidadEjercitos(),1);
    }

    @Test
    public void PaisAtacanteNoLimitrofeException(){
        //arrange
        Pais atacante = new Pais("Noruega", Arrays.asList("Suecia"), new Ejercitos(3));

        IBatalla batalla = new Batalla(atacante, defensor, dadoAtacante, dadoDefensor);

        //act & assert
        Assertions.assertThrows(PaisNoLimitrofeException.class, batalla::realizarAtaque);
    }

    @Test
    public void PaisAtacaConEjercitosInsuficientesException(){
        //arrange
        Pais atacante = new Pais("Argentina", Arrays.asList("Brasil"), new Ejercitos(1));

        IBatalla batalla = new Batalla(atacante, defensor, dadoAtacante, dadoDefensor);

        //act & assert
        Assertions.assertThrows(CantidadEjercitosInsuficienteException.class, batalla::realizarAtaque);
    }

}
