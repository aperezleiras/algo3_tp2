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
    void setUp(){
        dadoAtacante = Mockito.mock(Dado.class);
        dadoDefensor = Mockito.mock(Dado.class);

        Ejercitos ejercitosArgentina = new Ejercitos(3);
        Ejercitos ejercitosBrasil = new Ejercitos(2);

        atacante = new Pais("Argentina", Arrays.asList("Brasil"), ejercitosArgentina);
        defensor = new Pais("Brasil", Arrays.asList("Argentina"), ejercitosBrasil);
    }

    @Test
    void PaisConquistaAOtro() throws PaisNoLimitrofeException, CantidadEjercitosInsuficienteException {
        //arrange
        Jugador elDibu = new Jugador(0);
        Jugador neyPasto = new Jugador(1);
        atacante.asignarJugador(elDibu);
        defensor.asignarJugador(neyPasto);

        when(dadoAtacante.obtenerDado(any(Pais.class))).thenReturn(Arrays.asList(6,6));
        when(dadoDefensor.obtenerDado(any(Pais.class))).thenReturn(Arrays.asList(1,1));

        IBatalla batalla = new Batalla(atacante, defensor, dadoAtacante, dadoDefensor);

        //act
        batalla.realizarAtaque();

        //assert
        Assertions.assertEquals(elDibu, defensor.getJugador());

    }

    @Test
    void PaisDefensorGana() throws PaisNoLimitrofeException, CantidadEjercitosInsuficienteException {
        //arrange
        when(dadoAtacante.obtenerDado(any(Pais.class))).thenReturn(Arrays.asList(1,1));
        when(dadoDefensor.obtenerDado(any(Pais.class))).thenReturn(Arrays.asList(6,6));

        IBatalla batalla = new Batalla(atacante, defensor, dadoAtacante, dadoDefensor);

        //act
        batalla.realizarAtaque();

        //assert
        //TODO viola encapsulamiento?
        Assertions.assertEquals(1, atacante.getEjercitos().getCantidad());
    }

    @Test
    void PaisAtacanteGanaYNoConquista() throws PaisNoLimitrofeException, CantidadEjercitosInsuficienteException {
        //arrange
        when(dadoAtacante.obtenerDado(any(Pais.class))).thenReturn(Arrays.asList(6,1));
        when(dadoDefensor.obtenerDado(any(Pais.class))).thenReturn(Arrays.asList(1,1));

        IBatalla batalla = new Batalla(atacante, defensor, dadoAtacante, dadoDefensor);

        //act
        batalla.realizarAtaque();

        //assert
        Assertions.assertEquals(1, defensor.getEjercitos().getCantidad());
    }

    @Test
    void PaisAtacanteNoLimitrofeException(){
        //arrange
        Pais atacante = new Pais("Noruega", Arrays.asList("Suecia"), new Ejercitos(3));

        IBatalla batalla = new Batalla(atacante, defensor, dadoAtacante, dadoDefensor);

        //act & assert
        Assertions.assertThrows(PaisNoLimitrofeException.class, batalla::realizarAtaque);
    }

    @Test
    void PaisAtacaConEjercitosInsuficientesException(){
        //arrange
        Pais atacante = new Pais("Argentina", Arrays.asList("Brasil"), new Ejercitos(1));

        IBatalla batalla = new Batalla(atacante, defensor, dadoAtacante, dadoDefensor);

        //act & assert
        Assertions.assertThrows(CantidadEjercitosInsuficienteException.class, batalla::realizarAtaque);
    }

}
