package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CantidadAQuitarInvalidaException;
import edu.fiuba.algo3.exception.CantidadATransferirInvalidaException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PaisTest {

    @Test
    public void nombreSeAsigna(){
        Pais argentina = new Pais("Argentina", Arrays.asList("Brasil"), new Ejercitos(1));
        assertEquals(argentina.getNombre(), "Argentina");
    }

    @Test
    public void cantidadDeEjercitosSeAsignaCorrectamente(){
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(3));
        assertEquals(argentina.cantidadEjercitos(), 3);
    }

    @Test
    public void paisSinTieneEjercitos(){
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(0));
        assertTrue(argentina.noTieneEjercitos());
    }

    @Test
    public void seAgregaUnEjercito(){
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(0));
        assertTrue(argentina.noTieneEjercitos());
        argentina.agregarEjercitos(1);
        assertEquals(argentina.cantidadEjercitos(), 1);
    }

    @Test
    public void siSeQuitaUnEjercitoDeUnPaisSinEjercitosLanzaCantidadAQuitarInvalidaException(){
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(0));
        assertTrue(argentina.noTieneEjercitos());
        assertThrows(CantidadAQuitarInvalidaException.class,
                () -> {
                    argentina.quitarEjercitos(1);
                });
    }


    @Test
    public void unPaisLimitrofeSeAgregaCorrectamente() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos(1));

        argentina.agregarLimitrofe("Brasil");
        brasil.agregarLimitrofe("Argentina");

        assertTrue(brasil.esLimitrofeCon(argentina));
        assertTrue(argentina.esLimitrofeCon(brasil));
    }

    @Test
    public void porDefectoUnPaisNoEsLimitrofe() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos(1));

        assertFalse(argentina.esLimitrofeCon(brasil));
    }

    //faltan varios tests pero que serian basicamente los mismos que hay en jugador

}
