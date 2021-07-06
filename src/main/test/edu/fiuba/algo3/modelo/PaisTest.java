package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaisTest {

    @Test
    public void unPaisLimitrofeSeAgregaCorrectamente() {
        Pais argentina = new Pais("Argentina");
        Pais brasil = new Pais("Brasil");

        argentina.agregarLimitrofe(brasil);
        assertTrue(argentina.esLimitrofeCon(brasil));
    }

    @Test
    public void porDefectoUnPaisNoEsLimitrofe() {
        Pais argentina = new Pais("Argentina");
        Pais brasil = new Pais("Brasil");

        assertFalse(argentina.esLimitrofeCon(brasil));
    }


}
