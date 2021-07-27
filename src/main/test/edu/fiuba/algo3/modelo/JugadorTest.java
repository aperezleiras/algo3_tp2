package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.*;
import javafx.scene.control.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    private Jugador jugador1;
    private Jugador jugador2;

    private Pais argentina;
    private Pais brasil;
    private Pais chile;
    private Continente sudamerica;

    @BeforeEach
    public void setUp() {
        argentina = new Pais("Argentina", new ArrayList<>(Arrays.asList("Brasil")), new Ejercitos(1));
        brasil = new Pais("Brasil", new ArrayList<>(Arrays.asList("Argentina")), new Ejercitos(1));
        chile = new Pais("Chile", new ArrayList<>(Arrays.asList("Argentina")), new Ejercitos(1));
        sudamerica = new Continente("America del Sur");
        sudamerica.agregarPais(argentina);
        sudamerica.agregarPais(brasil);
        sudamerica.agregarPais(chile);
        DepositoEjercitos depo1 = new DepositoEjercitos(new ArrayList<>(Arrays.asList(sudamerica)));
        DepositoEjercitos depo2 = new DepositoEjercitos(new ArrayList<>(Arrays.asList(sudamerica)));

        jugador1 = new Jugador(1, depo1);
        jugador2 = new Jugador(2, depo2);
    }

    //<editor-fold desc="Paises">

    @Test
    public void alAsignarleUnPaisAUnJugadorEsePaisLePertenece() {
        jugador1.asignarPais(argentina);

        assertTrue(jugador1.paisMePertenece(argentina));
    }

    @Test
    public void alAsignarDosPaisesAUnJugadorLePertenecen(){
        jugador1.asignarPais(Arrays.asList(argentina, brasil));

        assertTrue(jugador1.paisMePertenece(argentina));
        assertTrue(jugador1.paisMePertenece(brasil));
    }

    @Test
    public void seBuscaUnPaisPorNombre() {
        jugador1.asignarPais(argentina);

        assertEquals(argentina, jugador1.buscarPais("Argentina"));
    }

    @Test
    public void noEncontrarUnPaisDevuelveNull() {
        assertNull(jugador1.buscarPais("Alemania"));
    }
    //</editor-fold>

    //<editor-fold desc="Ejercitos">

    @Test
    public void jugadorComienzaCon0EjercitosGeneralesDisponibles() {
        assertEquals(jugador1.obtenerEjercitosGeneralesDisponibles(), 0);
    }

    @Test
    public void despuesDeColocarUnEjercitoElJugadorTieneUnEjercitoMenos() {
        jugador1.asignarPais(argentina);
        jugador1.agregarEjercitosGenerales(2);
        jugador1.colocarEjercitos(argentina, 1);

        assertEquals(jugador1.obtenerEjercitosGeneralesDisponibles(), 1);
    }

    @Test
    public void alColocar3EjercitosEnUnPaisQueLePerteneceSeAgreganSatisfactoriamente() {
        jugador1.asignarPais(argentina);
        jugador1.agregarEjercitosGenerales(3);

        assertEquals(1, argentina.cantidadEjercitos());

        jugador1.colocarEjercitos(argentina, 3);

        assertEquals(4, argentina.cantidadEjercitos());
    }

    // todo: tira excepcion en turno ahora
    //  Queda mas simple lanzandola en deposito y que se vaya elevando, para no tener que validar desde tan arriba (desde turno o jugador) posiblemente rompiendo encapusulamiento
    @Test
    public void intentarColocarMasEjercitosQueLosDisponiblesLanzaCantidadEjercitosInsuficienteException() {
        jugador1.asignarPais(argentina);
        jugador1.agregarEjercitosGenerales(1);

        assertThrows(CantidadEjercitosInsuficienteException.class,
                () -> jugador1.colocarEjercitos(argentina, 2));
    }

    @Test
    public void intentarColocarEjercitosEnUnPaisAjenoLanzaPaisNoMePerteneceException() {
        jugador1.agregarEjercitosGenerales(3);

        assertThrows(PaisNoMePerteneceException.class, ()-> jugador1.colocarEjercitos(argentina, 3));
    }

    //</editor-fold>

    //<editor-fold desc="Transferir">

    @Test
    public void intentarTransferirEjercitosAUnPaisAjenoLanzaPaisInvalidoException() {
        jugador1.asignarPais(argentina);
        argentina.agregarEjercitos(3);

        assertThrows(PaisInvalidoException.class,
                ()-> jugador1.transferirEjercitosDesde(argentina, brasil, 1));
    }

    @Test
    public void intentarTransferirEjercitosDesdeUnPaisAjenoLanzaPaisInvalidoException() {
        jugador1.asignarPais(argentina);
        brasil.agregarEjercitos(3);

        assertThrows(PaisInvalidoException.class,
                () -> jugador1.transferirEjercitosDesde(brasil, argentina, 1));
    }

    @Test
    public void intentarTransferirEjercitosEntrePaisesNoLimitrofesLanzaPaisNoLimitrofeException() {
        Pais francia = new Pais("Francia", new ArrayList<>(), new Ejercitos(1));

        jugador1.asignarPais(argentina);
        jugador1.asignarPais(francia);
        argentina.agregarEjercitos(3);

        assertThrows(PaisNoLimitrofeException.class,
                ()-> jugador1.transferirEjercitosDesde(argentina, francia, 1));
    }

    @Test
    public void intentarTransferirUnEjercitoDesdeUnPaisConUnEjercitoLanzaCantidadATransferirInvalidaException() {
        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);

        assertTrue(argentina.cantidadEjercitos() == 1);
        assertThrows(CantidadATransferirInvalidaException.class,
                () -> jugador1.transferirEjercitosDesde(argentina, brasil, 1));
    }

    @Test
    public void intentarTransferirDosEjercitosDesdeUnPaisConDosEjercitosLanzaCantidadATransferirInvalidaException() {
        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);
        argentina.agregarEjercitos(1);

        assertTrue(argentina.cantidadEjercitos() == 2);
        assertThrows(CantidadATransferirInvalidaException.class,
                () -> jugador1.transferirEjercitosDesde(argentina, brasil, 2));
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

    //</editor-fold>

    //<editor-fold desc="Atacar">

    @Test
    public void intentarAtacarAUnPaisPropioLanzaPaisInvalidoException() {
        jugador1.asignarPais(argentina);
        jugador1.asignarPais(brasil);
        argentina.agregarEjercitos(3);

        assertThrows(PaisInvalidoException.class,
                () -> jugador1.atacarPaisDesde(argentina, brasil));
    }

    @Test
    public void intentarAtacarAUnPaisNoLimitrofeLanzaPaisNoLimitrofeException() {
        Pais francia = new Pais("Francia", new ArrayList<>(), new Ejercitos(1));

        jugador1.asignarPais(argentina);
        jugador2.asignarPais(francia);
        argentina.agregarEjercitos(3);

        assertThrows(PaisNoLimitrofeException.class,
                () -> jugador1.atacarPaisDesde(argentina, francia));
    }

    @Test
    public void intentarAtacarDesdeUnPaisAjenoLanzaPaisInvalidoException() {
        jugador1.asignarPais(argentina);
        jugador2.asignarPais(brasil);
        argentina.agregarEjercitos(3);

        assertThrows(PaisInvalidoException.class,
                () -> jugador1.atacarPaisDesde(brasil, argentina));
    }

    @Test
    public void intentarAtacarDesdeUnPaisConUnEjercitoLanzaCantidadEjercitosInsuficienteException() {
        jugador1.asignarPais(argentina);
        jugador2.asignarPais(brasil);

        assertEquals(argentina.cantidadEjercitos(), 1);
        assertThrows(CantidadEjercitosInsuficienteException.class,
                () -> jugador1.atacarPaisDesde(argentina, brasil));
    }

    //</editor-fold>

    //<editor-fold desc="Cartas">

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
                () -> jugador1.activarCarta(cartaArgentina));
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
                () -> jugador1.activarCarta(cartaArgentina));
    }

    @Test
    public void unJugadorActivaUnaCartaDeUnPaisAjenoYSeLanzaPaisNoMePerteneceException() {
        CartaPais cartaBrasil = new CartaPais(brasil, Simbolo.BARCO);
        MazoCartasPais mazo = new MazoCartasPais(new ArrayList<>(Arrays.asList(cartaBrasil)));

        mazo.agregarCarta(cartaBrasil);
        jugador1.levantarCartaPais(mazo);

        assertThrows(PaisNoMePerteneceException.class,
                () -> jugador1.activarCarta(cartaBrasil));
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

    //</editor-fold desc>

    //<editor-fold desc="Canje">

    /*@Test
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
    }*/

    @Test
    public void siUnJugadorSolicitaUnCanjeConCartasQueNoSonCanjeableSeLanzaCartasNoCanjeablesException() {

        ArrayList<CartaPais> cartas = generarCartasNoCanjeables();
        ArrayList<CartaPais> cartasMazo = new ArrayList<>(cartas);
        ArrayList<CartaPais> cartasCanje = new ArrayList<>(cartas);

        MazoCartasPais mazo = new MazoCartasPais(cartasMazo);

        assertThrows(CartasNoCanjeablesException.class, () -> simularCanje(cartasCanje, mazo, jugador1));
    }

    @Test
    public void alRealizarseUnCanjeCorrectamenteElJugadorDejaDeTenerLasCartas() {

        ArrayList<CartaPais> cartas = generarCartasCanjeables();
        ArrayList<CartaPais> cartasMazo = new ArrayList<>(cartas);
        ArrayList<CartaPais> cartasCanje = new ArrayList<>(cartas);

        MazoCartasPais mazo = new MazoCartasPais(cartasMazo);

        simularCanje(cartasCanje, mazo, jugador1);
        cartasMazo.forEach(c -> assertFalse(jugador1.cartaMePertenece(c)));
    }

    @Test
    public void elPrimerCanjeOtorgaTresEjercitosAColocar() {

        simularCanjes(1);

        assertEquals(jugador1.obtenerEjercitosGeneralesDisponibles(), 3);
    }

    @Test
    public void elSegundoCanjeOtorgaSieteEjercitosAColocar() {

        simularCanjes(2);

        assertEquals(10, jugador1.obtenerEjercitosGeneralesDisponibles());
    }

    @Test
    public void elTercerCanjeOtorgaDiezEjercitosAColocar() {

        simularCanjes(3);

        assertEquals(20,jugador1.obtenerEjercitosGeneralesDisponibles());
    }

    @Test
    public void elCuartoCanjeOtorgaQuinceEjercitosAColocar() {

        simularCanjes(4);

        assertEquals(35, jugador1.obtenerEjercitosGeneralesDisponibles() );
    }

    @Test
    public void elQuintoCanjeOtorgaVeinteEjercitosAColocar() {

        simularCanjes( 5);

        assertEquals( 55, jugador1.obtenerEjercitosGeneralesDisponibles());
    }

    //<editor-fold desc="Metodos auxiliares">

    private void simularCanje(List<CartaPais> cartas, MazoCartasPais mazo, Jugador jugador){
        for (int i = 0; i < 3; i++) {
            jugador.levantarCartaPais(mazo);
        }
        jugador.canjearCartas(cartas, mazo);
    }

    private void simularCanjes(int cantidad){
        ArrayList<CartaPais> cartas = generarCartasCanjeables();
        ArrayList<CartaPais> cartasMazo = new ArrayList<>(cartas);
        ArrayList<CartaPais> cartasCanje = new ArrayList<>(cartas);

        MazoCartasPais mazo = new MazoCartasPais(cartasMazo);

        for (int i = 0; i < cantidad; i++) {
            simularCanje(cartasCanje, mazo, jugador1);
        }
    }

    private ArrayList<CartaPais> generarCartasCanjeables() {
        return new ArrayList<>(Arrays.asList(
                new CartaPais(argentina, Simbolo.BARCO),
                new CartaPais(brasil, Simbolo.BARCO),
                new CartaPais(chile, Simbolo.BARCO)));
    }

    private ArrayList<CartaPais> generarCartasNoCanjeables() {
        return new ArrayList<>(Arrays.asList(
                new CartaPais(argentina, Simbolo.CAÑON),
                new CartaPais(brasil, Simbolo.BARCO),
                new CartaPais(chile, Simbolo.BARCO)));
    }
    //</editor-fold>

    //</editor-fold>

}
