package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CanjeTests {

    private Pais argentina;
    private Pais chile;
    private Pais brasil;

    private Jugador jugador;


    @BeforeEach
    public void setUp() {

        argentina = new Pais("Argentina", Arrays.asList("Brasil", "Chile"), new Ejercitos(1));
        chile = new Pais("Chile", Arrays.asList("Argentina"), new Ejercitos(1));
        brasil = new Pais("brasil", Arrays.asList("Argentina"), new Ejercitos(1));

        Continente sudamerica = new Continente("America del Sur");
        sudamerica.agregarPais(argentina);
        sudamerica.agregarPais(brasil);
        sudamerica.agregarPais(chile);

        jugador = new Jugador(1, new DepositoEjercitos(new ArrayList<>(Arrays.asList(sudamerica))));
    }


    @Test
    public void siUnJugadorSolicitaUnCanjeConCartasQueNoPoseeSeLanzaCartaNoMePerteneceException() {
        CartaPais carta1 = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.BARCO);
        CartaPais carta3 = new CartaPais(chile, Simbolo.BARCO);

        ArrayList<CartaPais> cartas = new ArrayList<>(Arrays.asList(carta1, carta2, carta3));
        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>());

        assertThrows(CartaNoMePerteneceException.class,
                ()->{
                    jugador.canjearCartas(cartas, mazo);
                });
    }


    @Test
    public void DosIgualesUnaDistintaNoSonCanjeables() {
        List<Simbolo>  simbolos = Arrays.asList(Simbolo.BARCO, Simbolo.BARCO, Simbolo.GLOBO);
        List<CartaPais> cartas =generarCartas(simbolos);

        Jugador jugador = mock(Jugador.class);
        when(jugador.cartaMePertenece(any(CartaPais.class))).thenReturn(true);

        Canje canje = new Canje(cartas, jugador);

        assertFalse(canje.cartasSonCanjeables());
    }

    @Test
    public void CanjeConCartasQueNoSonCanjeableSeLanzaCartasNoCanjeablesException() {
        List<Simbolo>  simbolos = Arrays.asList(Simbolo.BARCO, Simbolo.BARCO, Simbolo.GLOBO);
        List<CartaPais> cartas =generarCartas(simbolos);

        Jugador jugador = mock(Jugador.class);
        when(jugador.cartaMePertenece(any(CartaPais.class))).thenReturn(true);

        Canje canje = new Canje(cartas, jugador);

        assertThrows(CartasNoCanjeablesException.class, () -> canje.efectuarCanje(null));
    }

    @Test
    public void todasDistintasSonCanjeables() {
        //arrange
        List<Simbolo>  simbolos = Arrays.asList(Simbolo.BARCO, Simbolo.CAÑON, Simbolo.GLOBO);

        //act
        boolean canjeables = validarCanje(simbolos);

        //assert
        Assertions.assertTrue(canjeables);
    }

    @Test
    public void todasIgualesSonCanjeables() {
        //arrange
        List<Simbolo>  simbolos = Arrays.asList(Simbolo.BARCO, Simbolo.BARCO, Simbolo.BARCO);

        //act
        boolean canjeables = validarCanje(simbolos);

        //assert
        Assertions.assertTrue(canjeables);
    }

    @Test
    public void todasIgualesConUnComodinSonCanjeables() {
        //arrange
        List<Simbolo>  simbolos = Arrays.asList(Simbolo.BARCO, Simbolo.BARCO, Simbolo.COMODIN);

        //act
        boolean canjeables = validarCanje(simbolos);

        //assert
        Assertions.assertTrue(canjeables);
    }


    @Test
    public void todasDistintasConUnComodinSonCanjeables() {
        //arrange
        List<Simbolo>  simbolos = Arrays.asList(Simbolo.BARCO, Simbolo.COMODIN, Simbolo.CAÑON);

        //act
        boolean canjeables = validarCanje(simbolos);

        //assert
        Assertions.assertTrue(canjeables);
    }

    @Test
    public void dosComodinesSonCanjeables() {
        //arrange
        List<Simbolo>  simbolos = Arrays.asList(Simbolo.BARCO, Simbolo.COMODIN, Simbolo.COMODIN);

        //act
        boolean canjeables = validarCanje(simbolos);

        //assert
        Assertions.assertTrue(canjeables);
    }

    public boolean validarCanje(List<Simbolo> simbolos){
        //arrange
        List<CartaPais> cartas = generarCartas(simbolos);

        Canje canje = new Canje(cartas, jugador);

        //act
        return canje.cartasSonCanjeables();
    }

    private List<CartaPais> generarCartas(List<Simbolo> simbolos) {
        return new ArrayList<>(
                Arrays.asList(
                        new CartaPais(argentina, simbolos.get(0)),
                        new CartaPais(brasil, simbolos.get(1)),
                        new CartaPais(chile, simbolos.get(2))
                )
        );
    }
}
