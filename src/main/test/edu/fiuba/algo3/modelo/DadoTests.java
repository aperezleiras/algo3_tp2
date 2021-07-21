package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DadoTests {

    private Dado dado;

    @BeforeEach
    public void setUp(){
        dado = new Dado();
    }

    @Test
    public void unEjercitoAtacanteConDosEjercitosTiraUnDado() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(2));

        List<Integer> dadosAtacante = dado.obtenerDadoAtacante(argentina);

        assertEquals(dadosAtacante.size(), 1);
    }


    @Test
    public void unEjercitoAtacanteConTresEjercitosTiraDosDados() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(3));

        List<Integer> dadosAtacante = dado.obtenerDadoAtacante(argentina);

        assertEquals(dadosAtacante.size(), 2);
    }

    @Test
    public void unEjercitoAtacanteConCuatroEjercitosTiraTresDados() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(4));

        List<Integer> dadosAtacante = dado.obtenerDadoAtacante(argentina);

        assertEquals(dadosAtacante.size(), 3);
    }

    @Test
    public void unEjercitoAtacanteConCincoEjercitosTiraTresDados() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(5));

        List<Integer> dadosAtacante = dado.obtenerDadoAtacante(argentina);

        assertEquals(dadosAtacante.size(), 3);
    }

    @Test
    public void unEjercitoDefensorConUnEjercitoTiraUnDado() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));

        List<Integer> dadosAtacante = dado.obtenerDadoDefensor(argentina);

        assertEquals(dadosAtacante.size(), 1);
    }


    @Test
    public void unEjercitoDefensorConDosEjercitosTiraDosDados() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(2));

        List<Integer> dadosAtacante = dado.obtenerDadoDefensor(argentina);

        assertEquals(dadosAtacante.size(), 2);
    }


    @Test
    public void unEjercitoDefensorConTresEjercitosTiraTresDados() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(3));

        List<Integer> dadosAtacante = dado.obtenerDadoDefensor(argentina);

        assertEquals(dadosAtacante.size(), 3);
    }



    @Test
    public void unEjercitoDefensorConCuatroEjercitosTiraTresDados() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(4));

        List<Integer> dadosAtacante = dado.obtenerDadoDefensor(argentina);

        assertEquals(dadosAtacante.size(), 3);
    }
}
