package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.exception.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void alAsignarleUnPaisAUnJugadorEsePaisLePertenece() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));

        jugador.asignarPais(argentina);
        assertTrue(jugador.paisMePertenece(argentina));
    }

    @Test
    public void alColocar3EjercitosEnUnPaisQueLePerteneceSeAgreganSatisfactoriamente() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));

        jugador.asignarPais(argentina);
        jugador.colocarEjercitos(3, argentina);

        assertTrue(argentina.cantidadEjercitosSuperiorA(3));
        assertFalse(argentina.cantidadEjercitosSuperiorA(4));
    }

    @Test
    public void intentarColocarEjercitosEnUnPaisAjenoLanzaPaisInvalidoException() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));

        assertThrows(PaisInvalidoException.class,
                ()->{
                    jugador.colocarEjercitos(3, argentina);
                });
    }

    @Test
    public void intentarTransferirEjercitosAUnPaisAjenoLanzaPaisInvalidoException() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos(1));
        jugador.asignarPais(argentina);

        assertThrows(PaisInvalidoException.class,
                ()->{
                    jugador.transferirEjercitosDesde(argentina, brasil, 1);
                });
    }

    @Test
    public void intentarTransferirEjercitosDesdeUnPaisAjenoLanzaPaisInvalidoException() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos(1));
        jugador.asignarPais(argentina);

        assertThrows(PaisInvalidoException.class,
                ()->{
                    jugador.transferirEjercitosDesde(brasil, argentina, 1);
                });
    }

    @Test
    public void intentarTransferirEjercitosEntrePaisesNoLimitrofesLanzaPaisNoLimitrofeException() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos(1));

        jugador.asignarPais(argentina);
        jugador.asignarPais(brasil);

        assertThrows(PaisNoLimitrofeException.class,
                ()->{
                    jugador.transferirEjercitosDesde(argentina, brasil, 1);
                });
    }

    @Test
    public void intentarTransferirUnEjercitoDesdeUnPaisConUnEjercitoLanzaCantidadATransferirInvalidaException() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", Arrays.asList("Brasil"), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", Arrays.asList("Argentina"), new Ejercitos(1));

        jugador.asignarPais(argentina);
        jugador.asignarPais(brasil);

        assertThrows(CantidadATransferirInvalidaException.class,
                ()->{
                    jugador.transferirEjercitosDesde(argentina, brasil, 1);
                });
    }

    @Test
    public void intentarTransferirDosEjercitosDesdeUnPaisConDosEjercitosLanzaCantidadATransferirInvalidaException() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", Arrays.asList("Brasil"), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", Arrays.asList("Argentina"), new Ejercitos(1));

        jugador.asignarPais(argentina);
        jugador.asignarPais(brasil);

        assertThrows(CantidadATransferirInvalidaException.class,
                ()->{
                    jugador.transferirEjercitosDesde(argentina, brasil, 2);
                });
    }

    @Test
    public void laTransfenciaDeUnEjercitoDesdeUnPaisConDosEjercitosAUnoConUnEjercitoSeRealizaCorrectamente() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", Arrays.asList("Brasil"), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", Arrays.asList("Argentina"), new Ejercitos(1));

        jugador.asignarPais(argentina);
        jugador.asignarPais(brasil);
        jugador.colocarEjercitos(1, argentina);
        jugador.transferirEjercitosDesde(argentina, brasil, 1);

        assertEquals(argentina.cantidadEjercitos(), 1);
        assertEquals(brasil.cantidadEjercitos(), 2);
    }

    @Test
    public void intentarAtacarAUnPaisPropioLanzaPaisInvalidoException() {
        Pais argentina = new Pais("Argentina", Arrays.asList("Brasil"), new Ejercitos(1));
        Pais brasil = new Pais("Brasil", Arrays.asList("Argentina"), new Ejercitos(1));

        Jugador jugador1 = new Jugador(1);
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

        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
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

        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
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


        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        jugador1.asignarPais(argentina);
        jugador2.asignarPais(brasil);

        assertThrows(CantidadEjercitosInsuficienteException.class,
                ()->{
                    jugador1.atacarPaisDesde(argentina, brasil);
                });
    }

}
