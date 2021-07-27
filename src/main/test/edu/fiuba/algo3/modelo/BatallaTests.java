package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CantidadEjercitosInsuficienteException;
import edu.fiuba.algo3.exception.PaisNoLimitrofeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class BatallaTests {

    private IDado dado;
    private Pais atacante;
    private Pais defensor;
    private ObservadorPais observadorPais;
    @BeforeEach
    public void setUp(){

        dado = Mockito.mock(Dado.class);

        Ejercitos ejercitosArgentina = new Ejercitos(3);
        Ejercitos ejercitosBrasil = new Ejercitos(2);

        atacante = new Pais("Argentina", Arrays.asList("Brasil"), ejercitosArgentina);
        defensor = new Pais("Brasil", Arrays.asList("Argentina"), ejercitosBrasil);

    }

    @Test
    public void PaisConquistaAOtroYCambiaJugador() {
        //arrange
        Jugador elDibu = new Jugador(0, new DepositoEjercitos(new ArrayList<>()));
        Jugador neyPasto = new Jugador(1, new DepositoEjercitos(new ArrayList<>()));
        atacante.asignarJugador(elDibu);
        defensor.asignarJugador(neyPasto);

        when(dado.obtenerDadoAtacante(any(Pais.class))).thenReturn(Arrays.asList(6,6));
        when(dado.obtenerDadoDefensor(any(Pais.class))).thenReturn(Arrays.asList(1,1));

        Batalla batalla = new Batalla(atacante, defensor, dado);

        //act
        batalla.realizarAtaque();

        //assert
        Assertions.assertEquals(elDibu, defensor.getJugador());

    }

    @Test
    public void PaisConquistaAOtroYSeTransfierenEjercitos() {
        //arrange
        Jugador elDibu = new Jugador(0, new DepositoEjercitos(new ArrayList<>()));
        Jugador neyPasto = new Jugador(1, new DepositoEjercitos(new ArrayList<>()));
        atacante.asignarJugador(elDibu);
        defensor.asignarJugador(neyPasto);

        when(dado.obtenerDadoAtacante(any(Pais.class))).thenReturn(Arrays.asList(6,6));
        when(dado.obtenerDadoDefensor(any(Pais.class))).thenReturn(Arrays.asList(1,1));

        Batalla batalla = new Batalla(atacante, defensor, dado);

        //act
        batalla.realizarAtaque();

        //assert
        Assertions.assertEquals(defensor.cantidadEjercitos(), 1);
        Assertions.assertEquals(atacante.cantidadEjercitos(), 2);

    }

    @Test
    public void AtacantePierde2Ejercitos() {
        //arrange
        when(dado.obtenerDadoAtacante(any(Pais.class))).thenReturn(Arrays.asList(1,1));
        when(dado.obtenerDadoDefensor(any(Pais.class))).thenReturn(Arrays.asList(6,6));

        Batalla batalla = new Batalla(atacante, defensor, dado);

        //act
        batalla.realizarAtaque();

        //assert
        Assertions.assertEquals(atacante.cantidadEjercitos(),1);
    }

    @Test
    public void AmbosPaisesPierden1Ejercito() {
        //arrange
        when(dado.obtenerDadoAtacante(any(Pais.class))).thenReturn(Arrays.asList(6,1));
        when(dado.obtenerDadoDefensor(any(Pais.class))).thenReturn(Arrays.asList(1,1));

        Batalla batalla = new Batalla(atacante, defensor, dado);

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

        Batalla batalla = new Batalla(atacante, defensor, dado);

        //act & assert
        Assertions.assertThrows(PaisNoLimitrofeException.class, batalla::realizarAtaque);
    }

    @Test
    public void PaisAtacaConEjercitosInsuficientesException(){
        //arrange
        Pais atacante = new Pais("Argentina", Arrays.asList("Brasil"), new Ejercitos(1));

        Batalla batalla = new Batalla(atacante, defensor, dado);

        //act & assert
        Assertions.assertThrows(CantidadEjercitosInsuficienteException.class, batalla::realizarAtaque);
    }

}
