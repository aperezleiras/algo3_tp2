package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CantidadEjercitosInsuficienteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DepositoEjercitosTests {

    private DepositoEjercitos depo;

    private Continente sudamerica;
    private Continente europa;
    private ArrayList<Continente> continentes;
    private Pais argentina;
    private Pais brasil;
    private Pais chile;
    private Pais dinamarca;
    private Pais francia;

    @BeforeEach
    public void setUp() {
        sudamerica = new Continente("America del Sur");
        europa = new Continente("Europa");
        continentes = new ArrayList<>(Arrays.asList(sudamerica, europa));
        argentina = new Pais("Argentina", null, new Ejercitos(0));
        brasil = new Pais("Brasil", null, new Ejercitos(0));
        chile = new Pais("Chile", null, new Ejercitos(0));
        dinamarca = new Pais("Dinamarca", null, new Ejercitos(0));
        francia = new Pais("Francia", null, new Ejercitos(0));
        sudamerica.agregarPais(argentina);
        sudamerica.agregarPais(brasil);
        sudamerica.agregarPais(chile);
        europa.agregarPais(dinamarca);
        europa.agregarPais(francia);

        depo = new DepositoEjercitos(new ArrayList<>(Arrays.asList(sudamerica, europa)));
    }

    @Test
    public void seAgreganDosGruposDeEjercitosDePropositoGeneral() {
        depo.agregarEjercitosGenerales(2);
        depo.agregarEjercitosGenerales(3);

        int ejercitosGeneralesDisponibles = depo.obtenerEjercitosGenerales();

        assertEquals(ejercitosGeneralesDisponibles, 5);
    }

    @Test
    public void seAgreganEjercitosContinentales() {
        depo.agregarEjercitosPorContinente(sudamerica, 3);
        depo.agregarEjercitosPorContinente(europa, 0);

        int ejercitosParaAmericaDelSur = depo.obtenerEjercitosContinente(sudamerica);
        int ejercitosParaEuropa = depo.obtenerEjercitosContinente(europa);

        assertEquals(ejercitosParaAmericaDelSur, 3);
        assertEquals(ejercitosParaEuropa, 0);
    }

    @Test
    public void seAgreganTresEjercitosAUnPaisYLosEjercitosContinentalesSonSuficientes() {
        depo.agregarEjercitosPorContinente(sudamerica, 3);
        depo.agregarEjercitosPorContinente(europa, 4);
        depo.agregarEjercitosGenerales(2);

        depo.agregarEjercitosAPais(argentina, 3);

        assertEquals(depo.obtenerEjercitosContinente(sudamerica), 0); // Se extrajeron 3 ejercitos de DepositoSudamerica
        assertEquals(depo.obtenerEjercitosContinente(europa), 4);   // Se extrajeron 0 ejercitos de DepositoEuropa
        assertEquals(depo.obtenerEjercitosGenerales(), 2);  // Se extrajeron 0 ejercitos de DepositoGeneral
        assertEquals(argentina.cantidadEjercitos(), 3); // Se colocaron 3 ejercitos en Argentina
    }

    @Test
    public void seAgreganTresEjercitosAUnPaisYSoloDosProvienenDeEjercitosContinentales() {
        depo.agregarEjercitosPorContinente(sudamerica, 2);
        depo.agregarEjercitosPorContinente(europa, 4);
        depo.agregarEjercitosGenerales(2);

        depo.agregarEjercitosAPais(argentina, 3);

        assertEquals(depo.obtenerEjercitosContinente(sudamerica), 0); // Se extrajeron 2 ejercitos de DepositoSudamerica
        assertEquals(depo.obtenerEjercitosContinente(europa), 4);   // Se extrajeron 0 ejercitos de DepositoEuropa
        assertEquals(depo.obtenerEjercitosGenerales(), 1);  // Se extrajo 1 ejercito de DepositoGeneral
        assertEquals(argentina.cantidadEjercitos(), 3); // Se colocaron 3 ejercitos en Argentina
    }

    @Test
    public void seAgreganTresEjercitosAUnPaisYTodosProvienenDeEjercitosGenerales() {
        depo.agregarEjercitosPorContinente(sudamerica, 0);
        depo.agregarEjercitosPorContinente(europa, 4);
        depo.agregarEjercitosGenerales(4);

        depo.agregarEjercitosAPais(argentina, 3);

        assertEquals(depo.obtenerEjercitosContinente(sudamerica), 0); // Se extrajeron 0 ejercitos de DepositoSudamerica
        assertEquals(depo.obtenerEjercitosContinente(europa), 4);   // Se extrajeron 0 ejercitos de DepositoEuropa
        assertEquals(depo.obtenerEjercitosGenerales(), 1);  // Se extrajeron 3 ejercitos de DepositoGeneral
        assertEquals(argentina.cantidadEjercitos(), 3); // Se colocaron 3 ejercitos en Argentina
    }

    @Test
    public void alNoHaberSuficientesEjercitosEnLosDepositosSeLanzaCantidadEjercitosInsuficienteException() {
        depo.agregarEjercitosPorContinente(sudamerica, 2);
        depo.agregarEjercitosPorContinente(europa, 4);
        depo.agregarEjercitosGenerales(3);

        assertThrows(CantidadEjercitosInsuficienteException.class,
                () -> {
                    depo.agregarEjercitosAPais(argentina, 6);
                });
        assertEquals(depo.obtenerEjercitosContinente(sudamerica), 2); // No se modifica la cantidad
        assertEquals(depo.obtenerEjercitosContinente(europa), 4);   // No se modifica la cantidad
        assertEquals(depo.obtenerEjercitosGenerales(), 3);  // No se modifica la cantidad
        assertEquals(argentina.cantidadEjercitos(), 0); // No se modifica la cantidad
    }

}
