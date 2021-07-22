package edu.fiuba.algo3.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Juego {

    private final List<Jugador> jugadores;
    private HashMap<String, Continente> continentes = new HashMap<>();
    private HashMap<String, Pais> paises = new HashMap<>();
    private MazoCartasPais mazoCartasPais = new MazoCartasPais(new ArrayList<>());

    public Juego(List<Jugador> jugadores) throws FileNotFoundException {
        this.jugadores = jugadores;
        cargar_paises(paises, continentes);
        cargar_cartas(paises, mazoCartasPais);
        asignarPaises(new ArrayList<>(paises.values()));
    }

    void asignarPaises(List<Pais> listaPaises) {
        Collections.shuffle(listaPaises);
        for (int i = 0; i < listaPaises.size(); i++) {
            jugadores.get(i % jugadores.size()).asignarPais(listaPaises.get(i));
        }
    }
/*
    public Continente getContinentePorNombre(String nombre) {
        return continentes.get(nombre);
    }

    private HashMap<Continente, Integer> obtenerEjercitosPorContinente(Jugador jugador) {
        HashMap<Continente, Integer> ejercitosPorContinente = new HashMap<>();
        int cantidadEjercitos;

        for (Continente continente : continentes.values()) {
            cantidadEjercitos = continente.getEjercitosExtra(jugador);
            ejercitosPorContinente.put(continente, cantidadEjercitos);
        }
        return ejercitosPorContinente;
    }
*/
    public static void cargar_paises(HashMap<String, Pais> paises ,HashMap<String, Continente> continentes) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("textfiles/Fronteras.csv"));
        scan.nextLine();

        while (scan.hasNextLine()){
            String[] parametros = (scan.nextLine()).split("\"");
            String nombrePais = (parametros[0]).split(",")[0];
            String nombreContinente = (parametros[0]).split(",")[1];

            if (!continentes.containsKey(nombreContinente)) {
                Continente continente = new Continente(nombreContinente);
                continentes.put(nombreContinente, continente);
            }

            List<String> limitrofes = Arrays.asList((parametros[1]).split(","));
            Pais pais = new Pais(nombrePais, limitrofes, new Ejercitos(1));
            paises.put(nombrePais, pais);
            continentes.get(nombreContinente).agregarPais(pais);
        }
    }

    public static void cargar_cartas(HashMap<String, Pais> paises, MazoCartasPais mazo) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("textfiles/Cartas.csv"));
        scan.nextLine();

        while (scan.hasNextLine()) {
            String[] parametros = (scan.nextLine()).split(",");
            String nombrePais = parametros[0];
            String simbolo = parametros[1];

            CartaPais cartaPais = new CartaPais(paises.get(nombrePais), Simbolo.valueOf(simbolo.toUpperCase()));
            mazo.agregarCarta(cartaPais);
        }

        mazo.mezclar();
    }
}