package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ContinenteTests {

    private List<Pais> paises;
    private Continente continente;
    private Jugador jugador;
    private int ejercitosEsperados;

    @BeforeEach
    private void setUp(){
        paises = crearPaises();
        ejercitosEsperados = 10;
        continente = crearContinente(paises, ejercitosEsperados);
        jugador = new Jugador(0);
    }

    @Test
    public void ContinenteCompletoPorUnJugador() {
        // arrange
        paises.forEach(jugador::asignarPais);

        //act & assert
        Assertions.assertTrue(continente.completo(jugador));
    }

    @Test
    public void ContinenteNoCompletoPorUnJugador(){
        //act & assert
        Assertions.assertFalse(continente.completo(jugador));
    }

    @Test
    public void ContinenteTienePais(){
        //act & assert
        Assertions.assertTrue(continente.tienePais(paises.get(0)));
    }

    @Test
    public void ContinenteNoTienePais(){
        // arrange
        Pais pais = new Pais("Argentina", null, null);

        //act & assert
        Assertions.assertFalse(continente.tienePais(pais));
    }

    @Test
    public void ContinenteDevuelve_10_Ejertcitos(){
        // arrange
        paises.forEach(jugador::asignarPais);

        //act
        int ejercitosExtrasActuales = continente.obtenerEjercitosExtra(jugador);

        // assert
        Assertions.assertEquals(ejercitosEsperados, ejercitosExtrasActuales);
    }

    @Test
    public void ContinenteDevuelve_0_Ejertcitos(){
        // arrange
        jugador.asignarPais(paises.get(0));

        //act
        int ejercitosExtrasActuales = continente.obtenerEjercitosExtra(jugador);

        // assert
        Assertions.assertEquals(0, ejercitosExtrasActuales);
    }

    private List<Pais> crearPaises(){
        return Arrays.asList(
                new Pais("Argentina", null, null),
                new Pais("Brasil", null, null),
                new Pais("Uruguay", null, null)
        );
    }

    private Continente crearContinente(List<Pais> paises , int ejercitosExtras){
       return new Continente("America del Sur", paises, ejercitosExtras);
    }

}
