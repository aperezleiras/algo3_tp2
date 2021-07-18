package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CantidadATransferirInvalidaException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EjercitosTest {

    @Test
    public void porDefectoEjercitosTieneUnEjercito() {
        Ejercitos ejercitos = new Ejercitos(1);

        assertEquals(ejercitos.getCantidad(), 1);
    }

    @Test
    public void seLeAgreganDosEjercitosAUnGrupoDeUnEjercitoYPasaATenerTres() {
        Ejercitos ejercitos = new Ejercitos(1);
        ejercitos.agregarEjercitos(2);

        assertEquals(ejercitos.getCantidad(), 3);
    }

    @Test
    public void seLeQuitanDosEjercitosAUnGrupoDeCuatroEjercitoYPasaATenerDos() {
        Ejercitos ejercitos = new Ejercitos(1);
        ejercitos.agregarEjercitos(3);
        ejercitos.quitarEjercitos(2);

        assertEquals(ejercitos.getCantidad(), 2);
    }

    @Test
    // Eventualmente crear excepción para esto
    public void intentarTransferirDosEjercitosDesdeUnPaisConUnEjercitoLanzaCantidadATransferirInvalidaException() {
        Ejercitos ejercitos = new Ejercitos(1);
        Pais unPais = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));

        assertThrows(CantidadATransferirInvalidaException.class,
                () -> {
                    ejercitos.transferirEjercitos(unPais, 2);
                });
    }

    @Test
    public void intentarTransferirDosEjercitosDesdeUnPaisConDosEjercitosLanzaCantidadATransferirInvalidaException() {
        Ejercitos ejercitos = new Ejercitos(1);
        Pais unPais = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));
        ejercitos.agregarEjercitos(1);

        assertThrows(CantidadATransferirInvalidaException.class,
                () -> {
                    ejercitos.transferirEjercitos(unPais, 2);
                });
    }

    @Test
    public void alIntentarTransferirTresEjercitosAOtroPaisTeniendoDosEjercitoSeTransfierenSatisfactoriamente() {
        Ejercitos ejercitos = new Ejercitos(1);
        Pais unPais = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));

        ejercitos.agregarEjercitos(2);
        ejercitos.transferirEjercitos(unPais, 2);

        assertEquals(ejercitos.getCantidad(), 1);
    }

}

