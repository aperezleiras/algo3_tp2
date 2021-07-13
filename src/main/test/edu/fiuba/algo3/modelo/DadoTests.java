package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DadoTests {

    @Test
    public void unEjercitoAtacanteConDosEjercitosTiraUnDado() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(2));
        IDado dadoAtacante = new Dado();

        List<Integer> dadosAtacante = dadoAtacante.obtenerDadoAtacante(argentina);

        assertEquals(dadosAtacante.size(), 1);
    }


    @Test
    public void unEjercitoAtacanteConTresEjercitosTiraDosDados() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(3));
        IDado dadoAtacante = new Dado();

        List<Integer> dadosAtacante = dadoAtacante.obtenerDadoAtacante(argentina);

        assertEquals(dadosAtacante.size(), 2);
    }

    @Test
    public void unEjercitoAtacanteConCuatroEjercitosTiraTresDados() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(4));
        IDado dadoAtacante = new Dado();

        List<Integer> dadosAtacante = dadoAtacante.obtenerDadoAtacante(argentina);

        assertEquals(dadosAtacante.size(), 3);
    }

    @Test
    public void unEjercitoAtacanteConCincoEjercitosTiraTresDados() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(5));
        IDado dadoAtacante = new Dado();

        List<Integer> dadosAtacante = dadoAtacante.obtenerDadoAtacante(argentina);

        assertEquals(dadosAtacante.size(), 3);
    }

    @Test
    public void unEjercitoDefensorConUnEjercitoTiraUnDado() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));
        IDado dadoDefensor = new Dado();

        List<Integer> dadosAtacante = dadoDefensor.obtenerDadoDefensor(argentina);

        assertEquals(dadosAtacante.size(), 1);
    }


    @Test
    public void unEjercitoDefensorConDosEjercitosTiraDosDados() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(2));
        IDado dadoDefensor = new Dado();

        List<Integer> dadosAtacante = dadoDefensor.obtenerDadoDefensor(argentina);

        assertEquals(dadosAtacante.size(), 2);
    }


    @Test
    public void unEjercitoDefensorConTresEjercitosTiraTresDados() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(3));
        IDado dadoDefensor = new Dado();

        List<Integer> dadosAtacante = dadoDefensor.obtenerDadoDefensor(argentina);

        assertEquals(dadosAtacante.size(), 3);
    }



    @Test
    public void unEjercitoDefensorConCuatroEjercitosTiraTresDados() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(4));
        IDado dadoDefensor = new Dado();

        List<Integer> dadosAtacante = dadoDefensor.obtenerDadoDefensor(argentina);

        assertEquals(dadosAtacante.size(), 3);
    }
}
