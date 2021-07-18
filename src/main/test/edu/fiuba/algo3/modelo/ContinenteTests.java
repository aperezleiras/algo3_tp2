package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContinenteTests {
    @Test
    public void ContinenteCompletoPorUnJugador(){
        // arrange
        Jugador jugador = new Jugador(0);
        List<Pais> paises = Arrays.asList(
                new Pais("Argentina", null, null),
                new Pais("Brasil", null, null),
                new Pais("Uruguay", null, null)
        );

        paises.forEach(jugador::asignarPais);

        Continente continente = new Continente("America del Sur");
        paises.forEach(continente::agregarPais);

        //act & assert
        Assertions.assertTrue(continente.completo(jugador));
    }

    @Test
    public void ContinenteNoCompletoPorUnHJugador(){
        // arrange
        Jugador jugador = new Jugador(0);

        List<Pais> paises = Arrays.asList(
                new Pais("Argentina", null, null),
                new Pais("Brasil", null, null),
                new Pais("Uruguay", null, null)
        );

        Continente continente = new Continente("America del Sur");
        paises.forEach(continente::agregarPais);


        //act & assert
        Assertions.assertFalse(continente.completo(jugador));
    }

    @Test
    public void ContinenteTienePais(){
        // arrange
        Pais pais = new Pais("Argentina", null, null);

        Continente continente = new Continente("America del Sur");
        continente.agregarPais(pais);

        //act & assert
        Assertions.assertTrue(continente.tienePais(pais));
    }

    @Test
    public void ContinenteNoTienePais(){
        // arrange
        Pais pais = new Pais("Argentina", null, null);

        Continente continente = new Continente("America del Sur");

        //act & assert
        Assertions.assertFalse(continente.tienePais(pais));
    }

    @Test
    public void ContinenteDevuelve_10_Ejertcitos(){
        // arrange
        int ejercitosExtrasEsperados = 10;
        Jugador jugador = new Jugador(0);
        List<Pais> paises = Arrays.asList(
                new Pais("Argentina", null, null),
                new Pais("Brasil", null, null),
                new Pais("Uruguay", null, null)
        );

        paises.forEach(jugador::asignarPais);

        Continente continente = new Continente("America del Sur");
        continente.setCantidadEjercitosExtra(ejercitosExtrasEsperados);
        paises.forEach(continente::agregarPais);

        //act
        int ejercitosExtrasActuales = continente.getEjercitosExtra(jugador);

        // assert
        Assertions.assertEquals(ejercitosExtrasEsperados, ejercitosExtrasActuales);
    }

    @Test
    public void ContinenteDevuelve_0_Ejertcitos(){
        // arrange
        int ejercitosExtras = 10;
        int ejercitosExtrasEsperados = 0;

        Jugador jugador = new Jugador(0);
        List<Pais> paises = Arrays.asList(
                new Pais("Argentina", null, null),
                new Pais("Brasil", null, null),
                new Pais("Uruguay", null, null)
        );

        Continente continente = new Continente("America del Sur");
        continente.setCantidadEjercitosExtra(ejercitosExtras);
        paises.forEach(continente::agregarPais);

        jugador.asignarPais(paises.get(0));


        //act
        int ejercitosExtrasActuales = continente.getEjercitosExtra(jugador);

        // assert
        Assertions.assertEquals(ejercitosExtrasEsperados, ejercitosExtrasActuales);
    }

}
