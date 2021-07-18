package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    private Jugador jugador1;
    private Jugador jugador2;

    @BeforeEach
    public void setUp() {
        jugador1 = new Jugador(1);
        jugador2 = new Jugador(2);
        jugador1.agregarEjercitosDisponibles(10);
        jugador2.agregarEjercitosDisponibles(10);
    }

    @Test
    public void alAsignarleUnPaisAUnJugadorEsePaisLePertenece() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));

        jugador1.asignarPais(argentina);
        assertTrue(jugador1.paisMePertenece(argentina));
    }

    @Test
    public void alColocar3EjercitosEnUnPaisQueLePerteneceSeAgreganSatisfactoriamente() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));

        jugador1.asignarPais(argentina);
        jugador1.colocarEjercitos(3, argentina);

        assertTrue(argentina.cantidadEjercitosSuperiorA(3));
        assertFalse(argentina.cantidadEjercitosSuperiorA(4));
    }

    @Test
    public void intentarColocarMasEjercitosQueLosDisponiblesSeLanzaCantidadEjercitosInsuficienteException() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos(1));
        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);

        jugador1.colocarEjercitos(9, argentina);
        assertThrows(CantidadEjercitosInsuficienteException.class,
                ()->{
                    jugador1.colocarEjercitos(2, brasil);
                });
    }

    @Test
    public void intentarColocarEjercitosEnUnPaisAjenoLanzaPaisInvalidoException() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));

        assertThrows(PaisInvalidoException.class,
                ()->{
                    jugador1.colocarEjercitos(3, argentina);
                });
    }

    @Test
    public void intentarTransferirEjercitosAUnPaisAjenoLanzaPaisInvalidoException() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos(1));
        jugador1.asignarPais(argentina);

        assertThrows(PaisInvalidoException.class,
                ()->{
                    jugador1.transferirEjercitosDesde(argentina, brasil, 1);
                });
    }

    @Test
    public void intentarTransferirEjercitosDesdeUnPaisAjenoLanzaPaisInvalidoException() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos(1));
        jugador1.asignarPais(argentina);

        assertThrows(PaisInvalidoException.class,
                ()->{
                    jugador1.transferirEjercitosDesde(brasil, argentina, 1);
                });
    }

    @Test
    public void intentarTransferirEjercitosEntrePaisesNoLimitrofesLanzaPaisNoLimitrofeException() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos(1));

        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);

        assertThrows(PaisNoLimitrofeException.class,
                ()->{
                    jugador1.transferirEjercitosDesde(argentina, brasil, 1);
                });
    }

    @Test
    public void intentarTransferirUnEjercitoDesdeUnPaisConUnEjercitoLanzaCantidadATransferirInvalidaException() {
        Pais argentina = new Pais("Argentina", Arrays.asList("Brasil"), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", Arrays.asList("Argentina"), new Ejercitos(1));

        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);

        assertThrows(CantidadATransferirInvalidaException.class,
                ()->{
                    jugador1.transferirEjercitosDesde(argentina, brasil, 1);
                });
    }

    @Test
    public void intentarTransferirDosEjercitosDesdeUnPaisConDosEjercitosLanzaCantidadATransferirInvalidaException() {
        Pais argentina = new Pais("Argentina", Arrays.asList("Brasil"), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", Arrays.asList("Argentina"), new Ejercitos(1));

        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);

        assertThrows(CantidadATransferirInvalidaException.class,
                ()->{
                    jugador1.transferirEjercitosDesde(argentina, brasil, 2);
                });
    }

    @Test
    public void laTransfenciaDeUnEjercitoDesdeUnPaisConDosEjercitosAUnoConUnEjercitoSeRealizaCorrectamente() {
        Pais argentina = new Pais("Argentina", Arrays.asList("Brasil"), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", Arrays.asList("Argentina"), new Ejercitos(1));

        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);
        jugador1.colocarEjercitos(1, argentina);
        jugador1.transferirEjercitosDesde(argentina, brasil, 1);

        assertEquals(argentina.cantidadEjercitos(), 1);
        assertEquals(brasil.cantidadEjercitos(), 2);
    }

    @Test
    public void intentarAtacarAUnPaisPropioLanzaPaisInvalidoException() {
        Pais argentina = new Pais("Argentina", Arrays.asList("Brasil"), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", Arrays.asList("Argentina"), new Ejercitos(1));

        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);

        assertThrows(PaisInvalidoException.class,
                ()->{
                    jugador1.atacarPaisDesde(argentina, brasil);
                });
    }

    @Test
    public void intentarAtacarAUnPaisNoLimitrofeLanzaPaisNoLimitrofeException() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos(1));

        jugador1.asignarPais(argentina);
        jugador2.asignarPais(brasil);

        assertThrows(PaisNoLimitrofeException.class,
                ()->{
                    jugador1.atacarPaisDesde(argentina, brasil);
                });
    }

    @Test
    public void intentarAtacarDesdeUnPaisAjenoLanzaPaisInvalidoException() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos(1));

        jugador1.asignarPais(argentina);
        jugador2.asignarPais(brasil);

        assertThrows(PaisInvalidoException.class,
                ()->{
                    jugador1.atacarPaisDesde(brasil, argentina);
                });
    }

    @Test
    public void intentarAtacarDesdeUnPaisConUnEjercitoLanzaCantidadEjercitosInsuficienteException() {
        Pais argentina = new Pais("Argentina", Arrays.asList("Brasil"), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", Arrays.asList("Argentina"), new Ejercitos(1));


        jugador1.asignarPais(argentina);
        jugador2.asignarPais(brasil);

        assertThrows(CantidadEjercitosInsuficienteException.class,
                ()->{
                    jugador1.atacarPaisDesde(argentina, brasil);
                });
    }

}
