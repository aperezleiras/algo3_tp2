package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PaisTest {

    @Test
    public void unPaisLimitrofeSeAgregaCorrectamente() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos());

        argentina.agregarLimitrofe(brasil);
        assertTrue(argentina.esLimitrofeCon(brasil));
    }

    @Test
    public void porDefectoUnPaisNoEsLimitrofe() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos());

        assertFalse(argentina.esLimitrofeCon(brasil));
    }


}
