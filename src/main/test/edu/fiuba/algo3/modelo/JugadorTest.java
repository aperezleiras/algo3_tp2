package edu.fiuba.algo3.modelo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void alAsignarleUnPaisAUnJugadorEsePaisLePertenece() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());

        jugador.asignarPais(argentina);
        assertTrue(jugador.paisMePertenece(argentina));
    }

    @Test
    public void alColocarTresEjercitosEnUnPaisQueLePerteneceSeAgreganSatisfactoriamente() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());

        jugador.asignarPais(argentina);
        jugador.colocarEjercitos(3, argentina);

        assertEquals(argentina.cantidadEjercitos(), 4);
    }

    @Test
    public void intentarColocarEjercitosEnUnPaisAjenoLanzaPaisInvalidoError() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());

        assertThrows(PaisInvalidoError.class,
                ()->{
                    jugador.colocarEjercitos(3, argentina);
                });
    }

    @Test
    public void intentarTransferirEjercitosAUnPaisAjenoLanzaPaisInvalidoError() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos());
        jugador.asignarPais(argentina);

        assertThrows(PaisInvalidoError.class,
                ()->{
                    jugador.transferirEjercitosDesde(argentina, brasil, 1);
                });
    }

    @Test
    public void intentarTransferirEjercitosDesdeUnPaisAjenoLanzaPaisInvalidoError() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos());
        jugador.asignarPais(argentina);

        assertThrows(PaisInvalidoError.class,
                ()->{
                    jugador.transferirEjercitosDesde(brasil, argentina, 1);
                });
    }

    @Test
    public void intentarTransferirEjercitosEntrePaisesNoLimitrofesLanzaPaisesNoConectadosError() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos());

        jugador.asignarPais(argentina);
        jugador.asignarPais(brasil);

        assertThrows(PaisesNoConectadosError.class,
                ()->{
                    jugador.transferirEjercitosDesde(argentina, brasil, 1);
                });
    }

    @Test
    public void intentarTransferirUnEjercitoDesdeUnPaisConUnEjercitoLanzaCantidadATransferirInvalidaError() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos());
        argentina.agregarLimitrofe(brasil);

        jugador.asignarPais(argentina);
        jugador.asignarPais(brasil);

        assertThrows(CantidadATransferirInvalidaError.class,
                ()->{
                    jugador.transferirEjercitosDesde(argentina, brasil, 1);
                });
    }

    @Test
    public void intentarTransferirDosEjercitosDesdeUnPaisConDosEjercitosLanzaCantidadATransferirInvalidaError() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos());
        argentina.agregarLimitrofe(brasil);

        jugador.asignarPais(argentina);
        jugador.asignarPais(brasil);
        jugador.colocarEjercitos(1, argentina);

        assertThrows(CantidadATransferirInvalidaError.class,
                ()->{
                    jugador.transferirEjercitosDesde(argentina, brasil, 2);
                });
    }

    @Test
    public void laTransfenciaDeUnEjercitoDesdeUnPaisConDosEjercitosAUnoConUnEjercitoSeRealizaCorrectamente() {
        Jugador jugador = new Jugador(1);
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos());
        argentina.agregarLimitrofe(brasil);

        jugador.asignarPais(argentina);
        jugador.asignarPais(brasil);
        jugador.colocarEjercitos(1, argentina);
        jugador.transferirEjercitosDesde(argentina, brasil, 1);

        assertEquals(argentina.cantidadEjercitos(), 1);
        assertEquals(brasil.cantidadEjercitos(), 2);
    }

    @Test
    public void intentarAtacarAUnPaisPropioLanzaPaisInvalidoError() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos());
        argentina.agregarLimitrofe(brasil);

        Jugador jugador1 = new Jugador(1);
        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);

        assertThrows(PaisInvalidoError.class,
                ()->{
                    jugador1.atacarPaisDesde(argentina, brasil);
                });
    }

    @Test
    public void intentarAtacarAUnPaisNoLimitrofeLanzaPaisesNoConectadosError() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos());

        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        jugador1.asignarPais(argentina);
        jugador2.asignarPais(brasil);

        assertThrows(PaisesNoConectadosError.class,
                ()->{
                    jugador1.atacarPaisDesde(argentina, brasil);
                });
    }

    @Test
    public void intentarAtacarDesdeUnPaisAjenoLanzaPaisInvalidoError() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos());

        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        jugador1.asignarPais(argentina);
        jugador2.asignarPais(brasil);

        assertThrows(PaisInvalidoError.class,
                ()->{
                    jugador1.atacarPaisDesde(brasil, argentina);
                });
    }

    @Test
    public void intentarAtacarDesdeUnPaisConUnEjercitoLanzaInsuficientesEjercitosError() {
        Pais argentina = new Pais("Argentina", new ArrayList<>(), new Ejercitos());
        Pais brasil = new Pais("Brasil", new ArrayList<>(), new Ejercitos());
        argentina.agregarLimitrofe(brasil);

        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        jugador1.asignarPais(argentina);
        jugador2.asignarPais(brasil);

        assertThrows(InsuficientesEjercitosError.class,
                ()->{
                    jugador1.atacarPaisDesde(argentina, brasil);
                });
    }

    @Test
    public void ataqueSatisfactorio() {

    }
}
