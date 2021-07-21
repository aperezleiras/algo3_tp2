package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CantidadEjercitosInsuficienteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DepositoEjercitosTests {

    private DepositoEjercitos depo;

    private Continente sudamerica;
    private Continente europa;
    private Pais argentina;
    private Pais brasil;
    private Pais chile;
    private Pais dinamarca;
    private Pais francia;

    @BeforeEach
    public void setUp() {
        depo = new DepositoEjercitos();

        sudamerica = new Continente("America del Sur");
        europa = new Continente("Europa");
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
    }

    @Test
    public void seAgreganDosGruposDeEjercitosDePropositoGeneral() {
        depo.agregarEjercitosDisponibles(2);
        depo.agregarEjercitosDisponibles(3);

        int ejercitosGeneralesDisponibles = depo.getEjercitosGenerales();

        assertEquals(ejercitosGeneralesDisponibles, 5);
    }

    @Test
    public void seAgreganEjercitosContinentales() {
        HashMap<Continente, Integer> ejercitosPorContinente = new HashMap<>();
        ejercitosPorContinente.put(sudamerica, 3);
        ejercitosPorContinente.put(europa, 0);
        depo.agregarEjercitosDisponibles(ejercitosPorContinente);

        int ejercitosParaAmericaDelSur = depo.getEjercitosContinente(sudamerica);
        int ejercitosParaEuropa = depo.getEjercitosContinente(europa);

        assertEquals(ejercitosParaAmericaDelSur, 3);
        assertEquals(ejercitosParaEuropa, 0);
    }

    @Test
    public void seAgreganTresEjercitosAUnPaisYLosEjercitosContinentalesSonSuficientes() {
        HashMap<Continente, Integer> ejercitosPorContinente = new HashMap<>();
        ejercitosPorContinente.put(sudamerica, 3);
        ejercitosPorContinente.put(europa, 4);
        depo.agregarEjercitosDisponibles(ejercitosPorContinente);
        depo.agregarEjercitosDisponibles(2);

        depo.agregarEjercitosAPais(argentina, 3);

        assertEquals(depo.getEjercitosContinente(sudamerica), 0); // Se extrajeron 3 ejercitos de DepositoSudamerica
        assertEquals(depo.getEjercitosContinente(europa), 4);   // Se extrajeron 0 ejercitos de DepositoEuropa
        assertEquals(depo.getEjercitosGenerales(), 2);  // Se extrajeron 0 ejercitos de DepositoGeneral
        assertEquals(argentina.cantidadEjercitos(), 3); // Se colocaron 3 ejercitos en Argentina
    }

    @Test
    public void seAgreganTresEjercitosAUnPaisYSoloDosProvienenDeEjercitosContinentales() {
        HashMap<Continente, Integer> ejercitosPorContinente = new HashMap<>();
        ejercitosPorContinente.put(sudamerica, 2);
        ejercitosPorContinente.put(europa, 4);
        depo.agregarEjercitosDisponibles(ejercitosPorContinente);
        depo.agregarEjercitosDisponibles(2);

        depo.agregarEjercitosAPais(argentina, 3);

        assertEquals(depo.getEjercitosContinente(sudamerica), 0); // Se extrajeron 2 ejercitos de DepositoSudamerica
        assertEquals(depo.getEjercitosContinente(europa), 4);   // Se extrajeron 0 ejercitos de DepositoEuropa
        assertEquals(depo.getEjercitosGenerales(), 1);  // Se extrajo 1 ejercito de DepositoGeneral
        assertEquals(argentina.cantidadEjercitos(), 3); // Se colocaron 3 ejercitos en Argentina
    }

    @Test
    public void seAgreganTresEjercitosAUnPaisYTodosProvienenDeEjercitosGenerales() {
        HashMap<Continente, Integer> ejercitosPorContinente = new HashMap<>();
        ejercitosPorContinente.put(sudamerica, 0);
        ejercitosPorContinente.put(europa, 4);
        depo.agregarEjercitosDisponibles(ejercitosPorContinente);
        depo.agregarEjercitosDisponibles(4);

        depo.agregarEjercitosAPais(argentina, 3);

        assertEquals(depo.getEjercitosContinente(sudamerica), 0); // Se extrajeron 0 ejercitos de DepositoSudamerica
        assertEquals(depo.getEjercitosContinente(europa), 4);   // Se extrajeron 0 ejercitos de DepositoEuropa
        assertEquals(depo.getEjercitosGenerales(), 1);  // Se extrajeron 3 ejercitos de DepositoGeneral
        assertEquals(argentina.cantidadEjercitos(), 3); // Se colocaron 3 ejercitos en Argentina
    }

    @Test
    public void alNoHaberSuficientesEjercitosEnLosDepositosSeLanzaCantidadEjercitosInsuficienteException() {
        HashMap<Continente, Integer> ejercitosPorContinente = new HashMap<>();
        ejercitosPorContinente.put(sudamerica, 2);
        ejercitosPorContinente.put(europa, 4);
        depo.agregarEjercitosDisponibles(ejercitosPorContinente);
        depo.agregarEjercitosDisponibles(3);

        assertThrows(CantidadEjercitosInsuficienteException.class,
                () -> {
                    depo.agregarEjercitosAPais(argentina, 6);
                });
        assertEquals(depo.getEjercitosContinente(sudamerica), 2); // No se modifica la cantidad
        assertEquals(depo.getEjercitosContinente(europa), 4);   // No se modifica la cantidad
        assertEquals(depo.getEjercitosGenerales(), 3);  // No se modifica la cantidad
        assertEquals(argentina.cantidadEjercitos(), 0); // No se modifica la cantidad
    }

}
