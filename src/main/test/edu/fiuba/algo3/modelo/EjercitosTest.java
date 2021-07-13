package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EjercitosTest {

    @Test
    public void porDefectoEjercitosTieneUnEjercito() {
        Ejercitos ejercitos = new Ejercitos();

        assertEquals(ejercitos.getCantidad(), 1);
    }

    @Test
    public void seLeAgreganDosEjercitosAUnGrupoDeUnEjercitoYPasaATenerTres() {
        Ejercitos ejercitos = new Ejercitos();
        ejercitos.agregarEjercitos(2);

        assertEquals(ejercitos.getCantidad(), 3);
    }

    @Test
    public void seLeQuitanDosEjercitosAUnGrupoDeCuatroEjercitoYPasaATenerDos() {
        Ejercitos ejercitos = new Ejercitos();
        ejercitos.agregarEjercitos(3);
        ejercitos.quitarEjercitos(2);

        assertEquals(ejercitos.getCantidad(), 2);
    }

    @Test
    // Eventualmente crear excepción para esto
    public void intentarTransferirDosEjercitosDesdeUnPaisConUnEjercitoLanzaCantidadATransferirInvalidaError() {
        Ejercitos ejercitos = new Ejercitos();
        Pais unPais = new Pais("Argentina", new ArrayList<>(), new Ejercitos());

        assertThrows(CantidadATransferirInvalidaError.class,
                ()->{
                    ejercitos.transferirEjercitos(unPais, 2);
                });
    }

    @Test
    public void intentarTransferirDosEjercitosDesdeUnPaisConDosEjercitosLanzaCantidadATransferirInvalidaError() {
        Ejercitos ejercitos = new Ejercitos();
        Pais unPais = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        ejercitos.agregarEjercitos(1);

        assertThrows(CantidadATransferirInvalidaError.class,
                ()->{
                    ejercitos.transferirEjercitos(unPais, 2);
                });
    }

    @Test
    public void alIntentarTransferirTresEjercitosAOtroPaisTeniendoDosEjercitoSeTransfierenSatisfactoriamente() {
        Ejercitos ejercitos = new Ejercitos();
        Pais unPais = new Pais("Argentina", new ArrayList<>(), new Ejercitos());

        ejercitos.agregarEjercitos(2);
        ejercitos.transferirEjercitos(unPais, 2);

        assertEquals(ejercitos.getCantidad(), 1);
    }

    @Test
    public void unEjercitoAtacanteConDosEjercitosTiraUnDado() {
        Ejercitos ejercitos = new Ejercitos();
        ejercitos.agregarEjercitos(1);

        Dados dadosAtacante = ejercitos.calcularDadosAtacante();

        assertEquals(dadosAtacante.cantidad(), 1);
    }

    @Test
    public void unEjercitoAtacanteConTresEjercitosTiraDosDados() {
        Ejercitos ejercitos = new Ejercitos();
        ejercitos.agregarEjercitos(2);

        Dados dadosAtacante = ejercitos.calcularDadosAtacante();

        assertEquals(dadosAtacante.cantidad(), 2);
    }

    @Test
    public void unEjercitoAtacanteConCuatroEjercitosTiraTresDados() {
        Ejercitos ejercitos = new Ejercitos();
        ejercitos.agregarEjercitos(3);

        Dados dadosAtacante = ejercitos.calcularDadosAtacante();

        assertEquals(dadosAtacante.cantidad(), 3);
    }

    @Test
    public void unEjercitoAtacanteConCincoEjercitosTiraTresDados() {
        Ejercitos ejercitos = new Ejercitos();
        ejercitos.agregarEjercitos(4);

        Dados dadosAtacante = ejercitos.calcularDadosAtacante();

        assertEquals(dadosAtacante.cantidad(), 3);
    }

    @Test
    public void unEjercitoDefensorConUnEjercitoTiraUnDado() {
        Ejercitos ejercitos = new Ejercitos();

        Dados dadosDefensor = ejercitos.calcularDadosDefensor();

        assertEquals(dadosDefensor.cantidad(), 1);
    }

    @Test
    public void unEjercitoDefensorConDosEjercitosTiraDosDados() {
        Ejercitos ejercitos = new Ejercitos();
        ejercitos.agregarEjercitos(1);

        Dados dadosDefensor = ejercitos.calcularDadosDefensor();

        assertEquals(dadosDefensor.cantidad(), 2);
    }

    @Test
    public void unEjercitoDefensorConTresEjercitosTiraTresDados() {
        Ejercitos ejercitos = new Ejercitos();
        ejercitos.agregarEjercitos(2);

        Dados dadosDefensor = ejercitos.calcularDadosDefensor();

        assertEquals(dadosDefensor.cantidad(), 3);
    }

    @Test
    public void unEjercitoDefensorConCuatroEjercitosTiraTresDados() {
        Ejercitos ejercitos = new Ejercitos();
        ejercitos.agregarEjercitos(3);

        Dados dadosDefensor = ejercitos.calcularDadosDefensor();

        assertEquals(dadosDefensor.cantidad(), 3);
    }
}