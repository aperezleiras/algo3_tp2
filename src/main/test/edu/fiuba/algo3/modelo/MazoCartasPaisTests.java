package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.MazoVacioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MazoCartasPaisTests {

    private MazoCartasPais mazo;

    private Jugador jugador1 = new Jugador(1);

    private CartaPais cartaArgentina;
    private CartaPais cartaBrasil;
    private CartaPais cartaChile;

    private Pais argentina = new Pais("Argentina", Arrays.asList("Brasil", "Chile"), new Ejercitos(1));
    private Pais brasil = new Pais("Brasil", Arrays.asList("Argentina"), new Ejercitos(1));
    private Pais chile = new Pais("Chile", Arrays.asList("Argentina"), new Ejercitos(1));

    private ArrayList<CartaPais> cartas;


    @BeforeEach
    public void setUp() {
        cartaArgentina = new CartaPais(argentina, Simbolo.BARCO);
        cartaBrasil = new CartaPais(brasil, Simbolo.BARCO);
        cartaChile = new CartaPais(chile, Simbolo.BARCO);
        cartas = new ArrayList<>(Arrays.asList(cartaArgentina, cartaBrasil, cartaChile));
    }

    @Test
    public void lasCartasSeSacanEnElOrdenQueSeAgregaron() {
        mazo = new MazoCartasPais(new ArrayList<>());
        mazo.agregarCarta(cartaArgentina);
        mazo.agregarCarta(cartaBrasil);
        mazo.agregarCarta(cartaChile);

        CartaPais carta1 = mazo.levantarCarta();
        CartaPais carta2 = mazo.levantarCarta();
        CartaPais carta3 = mazo.levantarCarta();

        assertEquals(carta1, cartaArgentina);
        assertEquals(carta2, cartaBrasil);
        assertEquals(carta3, cartaChile);

    }

    @Test
    public void alIntentarLevantarCartaDeMazoVacioSeLanzaMazoVacioException() {
        mazo = new MazoCartasPais(new ArrayList<>());

        assertThrows(MazoVacioException.class,
                () -> {
                    mazo.levantarCarta();
                });
    }
}
