package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        jugador = new Jugador(1);
    }

    @Test
    public void CanjeConCartasQueNoSonCanjeableSeLanzaCartasNoCanjeablesException() {
        List<CartaPais> cartas = generarCartas(Arrays.asList(Simbolo.BARCO, Simbolo.BARCO, Simbolo.CAÑON));


        MazoCartasPais mazo = new MazoCartasPais(cartas);

        Canje canje = new Canje(cartas, jugador);

        assertThrows(CartasNoCanjeablesException.class, () -> canje.efectuarCanje(mazo));
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
