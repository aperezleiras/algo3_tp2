package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EjercitosTest {

    @Test
    public void porDefectoEjercitosTieneUnEjercito() {
        Ejercitos ejercitos = new Ejercitos(1);

        //assertTrue(ejercitos.tieneCantidad(1));
        //assertFalse(ejercitos.tieneCantidad(2));
        assertEquals(ejercitos.cantidad, 1);
    }

    @Test
    public void seLeAgreganDosEjercitosAUnGrupoDeUnEjercitoYPasaATenerTres() {
        Ejercitos ejercitos = new Ejercitos(1);
        ejercitos.agregarEjercitos(2);

        assertTrue(ejercitos.cantidadEjercitosSuperiorA(2));
        assertFalse(ejercitos.cantidadEjercitosSuperiorA(3));
    }

    @Test
    public void seLeQuitanDosEjercitosAUnGrupoDeCuatroEjercitoYPasaATenerDos() {
        Ejercitos ejercitos = new Ejercitos(1);
        ejercitos.agregarEjercitos(3);
        ejercitos.quitarEjercitos(2);

        assertTrue(ejercitos.cantidadEjercitosSuperiorA(1));
        assertFalse(ejercitos.cantidadEjercitosSuperiorA(2));
    }

    @Test
    // Eventualmente crear excepción para esto
    public void alIntentarTransferirDosEjercitosAOtroPaisTeniendoUnSoloEjercitoNoSeTransfiere() {
        Ejercitos ejercitos = new Ejercitos(1);
        Pais unPais = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));

        ejercitos.transferirEjercitos(unPais, 2);

        assertTrue(ejercitos.cantidadEjercitosSuperiorA(0));
        assertFalse(ejercitos.cantidadEjercitosSuperiorA(1));
    }

    @Test
    // Eventualmente crear excepción para esto
    public void alIntentarTransferirDosEjercitosAOtroPaisTeniendoDosEjercitoNoSeTransfiere() {
        Ejercitos ejercitos = new Ejercitos(1);
        Pais unPais = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));

        ejercitos.agregarEjercitos(1);
        ejercitos.transferirEjercitos(unPais, 2);

        assertTrue(ejercitos.cantidadEjercitosSuperiorA(1));
        assertFalse(ejercitos.cantidadEjercitosSuperiorA(2));
    }

    @Test
    // Eventualmente crear excepción para esto
    public void alIntentarTransferirTresEjercitosAOtroPaisTeniendoDosEjercitoSeTransfierenSatisfactoriamente() {
        Ejercitos ejercitos = new Ejercitos(1);
        Pais unPais = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));

        ejercitos.agregarEjercitos(2);
        ejercitos.transferirEjercitos(unPais, 2);

        assertTrue(ejercitos.cantidadEjercitosSuperiorA(0));
        assertFalse(ejercitos.cantidadEjercitosSuperiorA(1));
    }
}