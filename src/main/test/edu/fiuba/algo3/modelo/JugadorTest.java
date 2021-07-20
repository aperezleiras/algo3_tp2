package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    private Jugador jugador1;
    private Jugador jugador2;

    private Pais argentina;
    private Pais brasil;

    @BeforeEach
    public void setUp() {
        jugador1 = new Jugador(1);
        jugador2 = new Jugador(2);
        argentina = new Pais("Argentina", new ArrayList<>(Arrays.asList("Brasil")), new Ejercitos(1));
        brasil = new Pais("Brasil", new ArrayList<>(Arrays.asList("Argentina")), new Ejercitos(1));
    }

    @Test
    public void alAsignarleUnPaisAUnJugadorEsePaisLePertenece() {
        jugador1.asignarPais(argentina);
        assertTrue(jugador1.paisMePertenece(argentina));
    }

    //==========================================================================COLOCAR

    @Test
    public void alColocar3EjercitosEnUnPaisQueLePerteneceSeAgreganSatisfactoriamente() {
        jugador1.asignarPais(argentina);
        jugador1.agregarEjercitosDisponibles(3);

        assertTrue(argentina.cantidadEjercitos() == 1);
        jugador1.colocarEjercitos(3, argentina);
        assertTrue(argentina.cantidadEjercitos() == 4);
    }

    @Test
    public void intentarColocarMasEjercitosQueLosDisponiblesLanzaCantidadEjercitosInsuficienteException() {
        jugador1.asignarPais(argentina);
        jugador1.agregarEjercitosDisponibles(1);

        assertThrows(CantidadEjercitosInsuficienteException.class,
                ()->{
                    jugador1.colocarEjercitos(2, argentina);
                });
    }

    @Test
    public void intentarColocarEjercitosEnUnPaisAjenoLanzaPaisInvalidoException() {
        jugador1.agregarEjercitosDisponibles(3);
        assertThrows(PaisInvalidoException.class,
                ()->{
                    jugador1.colocarEjercitos(3, argentina);
                });
    }

    //==========================================================================TRANSFERIR

    @Test
    public void intentarTransferirEjercitosAUnPaisAjenoLanzaPaisInvalidoException() {
        jugador1.asignarPais(argentina);
        argentina.agregarEjercitos(3);

        assertThrows(PaisInvalidoException.class,
                ()->{
                    jugador1.transferirEjercitosDesde(argentina, brasil, 1);
                });
    }

    @Test
    public void intentarTransferirEjercitosDesdeUnPaisAjenoLanzaPaisInvalidoException() {
        jugador1.asignarPais(argentina);
        brasil.agregarEjercitos(3);

        assertThrows(PaisInvalidoException.class,
                ()->{
                    jugador1.transferirEjercitosDesde(brasil, argentina, 1);
                });
    }

    @Test
    public void intentarTransferirEjercitosEntrePaisesNoLimitrofesLanzaPaisNoLimitrofeException() {
        Pais francia = new Pais("Francia", new ArrayList<>(), new Ejercitos(1));

        jugador1.asignarPais(argentina);
        jugador1.asignarPais(francia);
        argentina.agregarEjercitos(3);

        assertThrows(PaisNoLimitrofeException.class,
                ()->{
                    jugador1.transferirEjercitosDesde(argentina, francia, 1);
                });
    }

    @Test
    public void intentarTransferirUnEjercitoDesdeUnPaisConUnEjercitoLanzaCantidadATransferirInvalidaException() {
        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);

        assertTrue(argentina.cantidadEjercitos() == 1);
        assertThrows(CantidadATransferirInvalidaException.class,
                ()->{
                    jugador1.transferirEjercitosDesde(argentina, brasil, 1);
                });
    }

    @Test
    public void intentarTransferirDosEjercitosDesdeUnPaisConDosEjercitosLanzaCantidadATransferirInvalidaException() {
        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);

        argentina.agregarEjercitos(1);
        assertTrue(argentina.cantidadEjercitos() == 2);
        assertThrows(CantidadATransferirInvalidaException.class,
                ()->{
                    jugador1.transferirEjercitosDesde(argentina, brasil, 2);
                });
    }

    @Test
    public void laTransfenciaDeUnEjercitoDesdeUnPaisConDosEjercitosAUnoConUnEjercitoSeRealizaCorrectamente() {
        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);
        argentina.agregarEjercitos(1);
        jugador1.transferirEjercitosDesde(argentina, brasil, 1);

        assertEquals(argentina.cantidadEjercitos(), 1);
        assertEquals(brasil.cantidadEjercitos(), 2);
    }

    //==========================================================================ATACAR

    @Test
    public void intentarAtacarAUnPaisPropioLanzaPaisInvalidoException() {
        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);
        argentina.agregarEjercitos(3);

        assertThrows(PaisInvalidoException.class,
                ()->{
                    jugador1.atacarPaisDesde(argentina, brasil);
                });
    }

    @Test
    public void intentarAtacarAUnPaisNoLimitrofeLanzaPaisNoLimitrofeException() {
        Pais francia = new Pais("Francia", new ArrayList<>(), new Ejercitos(1));

        jugador1.asignarPais(argentina);
        jugador2.asignarPais(francia);
        argentina.agregarEjercitos(3);

        assertThrows(PaisNoLimitrofeException.class,
                ()->{
                    jugador1.atacarPaisDesde(argentina, francia);
                });
    }

    @Test
    public void intentarAtacarDesdeUnPaisAjenoLanzaPaisInvalidoException() {
        jugador1.asignarPais(argentina);
        jugador2.asignarPais(brasil);
        argentina.agregarEjercitos(3);

        assertThrows(PaisInvalidoException.class,
                ()->{
                    jugador1.atacarPaisDesde(brasil, argentina);
                });
    }

    @Test
    public void intentarAtacarDesdeUnPaisConUnEjercitoLanzaCantidadEjercitosInsuficienteException() {
        jugador1.asignarPais(argentina);
        jugador2.asignarPais(brasil);

        assertEquals(argentina.cantidadEjercitos(), 1);
        assertThrows(CantidadEjercitosInsuficienteException.class,
                ()->{
                    jugador1.atacarPaisDesde(argentina, brasil);
                });
    }

    //===============================================================================================CARTAS

    @Test
    public void unJugadorLevantaUnaCartaYEstaLePertenece() {
        CartaPais cartaArgentina = new CartaPais(argentina, Simbolo.BARCO);
        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(cartaArgentina)));

        jugador1.levantarCartaPais(mazo);
        assertTrue(jugador1.cartaMePertenece(cartaArgentina));
    }

    @Test
    public void unJugadorDevuelveUnaCartaAlMazoYEstaYaNoLePertenece() {
        CartaPais cartaArgentina = new CartaPais(argentina, Simbolo.BARCO);
        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(cartaArgentina)));

        jugador1.levantarCartaPais(mazo);
        assertTrue(jugador1.cartaMePertenece(cartaArgentina));
        jugador1.devolverCartaA(cartaArgentina, mazo);
        assertFalse(jugador1.cartaMePertenece(cartaArgentina));
    }

    @Test
    public void siUnaCartaEsActivadaPorUnJugadorQueNoLaPoseeSeLanzaCartaNoMePerteneceException() {
        CartaPais cartaArgentina = new CartaPais(argentina, Simbolo.BARCO);

        assertThrows(CartaNoMePerteneceException.class,
                () -> {
                    jugador1.activarCarta(cartaArgentina);
                });
    }

    @Test
    public void alActivarUnaCartaYaActivadaSeLanzaCartaYaActivadaException() {
        CartaPais cartaArgentina = new CartaPais(argentina, Simbolo.BARCO);
        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(cartaArgentina)));

        mazo.agregarCarta(cartaArgentina);
        jugador1.asignarPais(argentina);
        jugador1.levantarCartaPais(mazo);
        jugador1.activarCarta(cartaArgentina);

        assertThrows(CartaYaActivadaException.class,
                () -> {
                    jugador1.activarCarta(cartaArgentina);
                });
    }

    @Test
    public void unJugadorActivaUnaCartaDeUnPaisAjenoYSeLanzaPaisNoMePerteneceException() {
        CartaPais cartaBrasil = new CartaPais(brasil, Simbolo.BARCO);
        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(cartaBrasil)));
        mazo.agregarCarta(cartaBrasil);
        jugador1.levantarCartaPais(mazo);

        assertThrows(PaisNoMePerteneceException.class,
                () -> {
                    jugador1.activarCarta(cartaBrasil);
                });
    }

    @Test
    public void unJugadorActivaUnaCartaSatisfactoriamenteYSeAgreganDosEjercitosAlPais() {
        CartaPais cartaArgentina = new CartaPais(argentina, Simbolo.BARCO);
        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(cartaArgentina)));

        jugador1.asignarPais(argentina);
        mazo.agregarCarta(cartaArgentina);
        jugador1.levantarCartaPais(mazo);

        assertEquals(argentina.cantidadEjercitos(), 1);
        jugador1.activarCarta(cartaArgentina);
        assertEquals(argentina.cantidadEjercitos(), 3);
    }

    //==================================================================================== CANJE

    @Test
    public void siUnJugadorSolicitaUnCanjeConCartasQueNoPoseeSeLanzaCartaNoMePerteneceException() {
        Pais chile = new Pais("Chile", new ArrayList<>(Arrays.asList("Argentina")), new Ejercitos(1));

        CartaPais carta1 = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.BARCO);
        CartaPais carta3 = new CartaPais(chile, Simbolo.BARCO);

        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(carta1, carta2, carta3)));
        jugador1.levantarCartaPais(mazo);

        assertThrows(CartaNoMePerteneceException.class,
                ()->{
                    jugador1.canjearCartas(carta1, carta2, carta3, mazo);
                });
    }

    @Test
    public void siUnJugadorSolicitaUnCanjeConCartasQueNoSonCanjeableSeLanzaCartasNoCanjeablesException() {
        Pais chile = new Pais("Chile", new ArrayList<>(Arrays.asList("Argentina")), new Ejercitos(1));

        CartaPais carta1 = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.BARCO);
        CartaPais carta3 = new CartaPais(chile, Simbolo.GLOBO);

        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(carta1, carta2, carta3)));
        jugador1.levantarCartaPais(mazo);
        jugador1.levantarCartaPais(mazo);
        jugador1.levantarCartaPais(mazo);

        assertThrows(CartasNoCanjeablesException.class,
                ()->{
                    jugador1.canjearCartas(carta1, carta2, carta3, mazo);
                });
    }

    @Test
    public void alRealizarseUnCanjeCorrectamenteElJugadorDejaDeTenerLasCartas() {
        Pais chile = new Pais("Chile", new ArrayList<>(Arrays.asList("Argentina")), new Ejercitos(1));

        CartaPais carta1 = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.CAÑON);
        CartaPais carta3 = new CartaPais(chile, Simbolo.GLOBO);

        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(carta1, carta2, carta3)));
        jugador1.levantarCartaPais(mazo);
        jugador1.levantarCartaPais(mazo);
        jugador1.levantarCartaPais(mazo);

        jugador1.canjearCartas(carta1, carta2, carta3, mazo);

        assertFalse(jugador1.cartaMePertenece(carta1));
        assertFalse(jugador1.cartaMePertenece(carta2));
        assertFalse(jugador1.cartaMePertenece(carta3));
    }

    @Test
    public void alRealizarseUnCanjeCorrectamenteSeAgreganEjercitosDisponiblesAlJugador() {
        Pais chile = new Pais("Chile", new ArrayList<>(Arrays.asList("Argentina")), new Ejercitos(1));

        CartaPais carta1 = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.BARCO);
        CartaPais carta3 = new CartaPais(chile, Simbolo.BARCO);

        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(carta1, carta2, carta3)));
        jugador1.levantarCartaPais(mazo);
        jugador1.levantarCartaPais(mazo);
        jugador1.levantarCartaPais(mazo);

        assertTrue(jugador1.obtenerCantidadEjercitosDisponibles() == 0);
        jugador1.canjearCartas(carta1, carta2, carta3, mazo);
        assertTrue(jugador1.obtenerCantidadEjercitosDisponibles() > 0);
    }

    @Test
    public void elPrimerCanjeOtorgaTresEjercitosAColocar() {
        Pais chile = new Pais("Chile", new ArrayList<>(Arrays.asList("Argentina")), new Ejercitos(1));

        CartaPais carta1 = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.BARCO);
        CartaPais carta3 = new CartaPais(chile, Simbolo.BARCO);

        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(carta1, carta2, carta3)));
        jugador1.levantarCartaPais(mazo);
        jugador1.levantarCartaPais(mazo);
        jugador1.levantarCartaPais(mazo);

        assertEquals(jugador1.obtenerCantidadEjercitosDisponibles(), 0);
        jugador1.canjearCartas(carta1, carta2, carta3, mazo);
        assertEquals(jugador1.obtenerCantidadEjercitosDisponibles(), 3);
    }

    @Test
    public void elSegundoCanjeOtorgaSieteEjercitosAColocar() {
        Pais chile = new Pais("Chile", new ArrayList<>(Arrays.asList("Argentina")), new Ejercitos(1));

        CartaPais carta1 = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.BARCO);
        CartaPais carta3 = new CartaPais(chile, Simbolo.BARCO);

        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(carta1, carta2, carta3)));

        for (int i = 0; i < 1; i ++) {
            jugador1.levantarCartaPais(mazo);
            jugador1.levantarCartaPais(mazo);
            jugador1.levantarCartaPais(mazo);
            jugador1.canjearCartas(carta1, carta2, carta3, mazo);
        }
        for (int i = 0; i < 3; i ++) jugador1.levantarCartaPais(mazo);
        int ejercitosDisponiblesAntes = jugador1.obtenerCantidadEjercitosDisponibles();

        jugador1.canjearCartas(carta1, carta2, carta3, mazo);
        assertEquals(jugador1.obtenerCantidadEjercitosDisponibles() - ejercitosDisponiblesAntes, 7);
    }

    @Test
    public void elTercerCanjeOtorgaDiezEjercitosAColocar() {
        Pais chile = new Pais("Chile", new ArrayList<>(Arrays.asList("Argentina")), new Ejercitos(1));

        CartaPais carta1 = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.BARCO);
        CartaPais carta3 = new CartaPais(chile, Simbolo.BARCO);

        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(carta1, carta2, carta3)));

        for (int i = 0; i < 2; i ++) {
            jugador1.levantarCartaPais(mazo);
            jugador1.levantarCartaPais(mazo);
            jugador1.levantarCartaPais(mazo);
            jugador1.canjearCartas(carta1, carta2, carta3, mazo);
        }
        for (int i = 0; i < 3; i ++) jugador1.levantarCartaPais(mazo);
        int ejercitosDisponiblesAntes = jugador1.obtenerCantidadEjercitosDisponibles();

        jugador1.canjearCartas(carta1, carta2, carta3, mazo);
        assertEquals(jugador1.obtenerCantidadEjercitosDisponibles() - ejercitosDisponiblesAntes, 10);
    }

    @Test
    public void elCuartoCanjeOtorgaQuinceEjercitosAColocar() {
        Pais chile = new Pais("Chile", new ArrayList<>(Arrays.asList("Argentina")), new Ejercitos(1));

        CartaPais carta1 = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.BARCO);
        CartaPais carta3 = new CartaPais(chile, Simbolo.BARCO);

        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(carta1, carta2, carta3)));

        for (int i = 0; i < 4; i ++) {
            jugador1.levantarCartaPais(mazo);
            jugador1.levantarCartaPais(mazo);
            jugador1.levantarCartaPais(mazo);
            jugador1.canjearCartas(carta1, carta2, carta3, mazo);
        }
        for (int i = 0; i < 3; i ++) jugador1.levantarCartaPais(mazo);
        int ejercitosDisponiblesAntes = jugador1.obtenerCantidadEjercitosDisponibles();

        jugador1.canjearCartas(carta1, carta2, carta3, mazo);
        assertEquals(jugador1.obtenerCantidadEjercitosDisponibles() - ejercitosDisponiblesAntes, 15);
    }

    @Test
    public void elQuintoCanjeOtorgaVeinteEjercitosAColocar() {
        Pais chile = new Pais("Chile", new ArrayList<>(Arrays.asList("Argentina")), new Ejercitos(1));

        CartaPais carta1 = new CartaPais(argentina, Simbolo.BARCO);
        CartaPais carta2 = new CartaPais(brasil, Simbolo.BARCO);
        CartaPais carta3 = new CartaPais(chile, Simbolo.BARCO);

        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(carta1, carta2, carta3)));

        for (int i = 0; i < 5; i ++) {
            jugador1.levantarCartaPais(mazo);
            jugador1.levantarCartaPais(mazo);
            jugador1.levantarCartaPais(mazo);
            jugador1.canjearCartas(carta1, carta2, carta3, mazo);
        }
        for (int i = 0; i < 3; i ++) jugador1.levantarCartaPais(mazo);
        int ejercitosDisponiblesAntes = jugador1.obtenerCantidadEjercitosDisponibles();

        jugador1.canjearCartas(carta1, carta2, carta3, mazo);
        assertEquals(jugador1.obtenerCantidadEjercitosDisponibles() - ejercitosDisponiblesAntes, 20);
    }

}
