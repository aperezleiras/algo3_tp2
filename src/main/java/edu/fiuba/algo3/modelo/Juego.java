package edu.fiuba.algo3.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Juego {
    private final List<Jugador> jugadores;

    private HashMap<String, Continente> continentes = new HashMap<String, Continente>();
    private HashMap<String, Pais> paises = new HashMap<String, Pais>();

    private List<CartaPais> cartas = new ArrayList<>();
    //private final MazoCartasPais mazoCartasPais;

    public Juego(List<Jugador> jugadores) throws FileNotFoundException {
        this.jugadores = jugadores;
        cargar_paises(paises, continentes);
        cargar_cartas(paises, cartas);
        asignarPaises(new ArrayList<Pais>(paises.values()));
    }

    void asignarPaises(List<Pais> listaPaises) {
        Collections.shuffle(listaPaises);
        for(int i = 0; i<listaPaises.size();i++){
            jugadores.get(i % jugadores.size()).asignarPais(listaPaises.get(i));
        }
    }

    public void asignarEjercitos(int jugadorId, Pais pais) {
        Jugador jugador = jugadores.get(jugadorId);

        int cantidadEjercitos = obtenerCantidadEjercitos(jugadorId); //Aca podriamos acumular las otras cantidades calculadas

        jugador.colocarEjercitos(cantidadEjercitos, pais);
    }

    public int obtenerCantidadEjercitos(int jugadorId) {

        Jugador jugador = jugadores.get(jugadorId);

        return jugador.obtenerCantidadPaises() / 2;
    }


    /* TODO revisear implementacion con o sin sobrecarga
        public int obtenerCantidadEjercitos(Jugador jugador){
            return  jugador.obtenerCantidadPaises() / 2;
        }
    */

    /* TODO pensarlo mejor la implementacion
    public void colocarEjercitosContinente(Jugador jugador, Continente continente){
        int cantidad = obtenerCantidadEjercitos(jugador, continente);

        Pais pais;

        while (cantidad > 0){
            pais = elergirPais(jugador, continente);
            pais.agregarEjercitos(1);
            cantidad--;
        }
    }*/

    public int obtenerCantidadEjercitos(Jugador jugador, Continente continente){
        return  continente.getEjercitosExtra(jugador);
    }

    //TODO pensar implementacion
    public Pais elergirPais(Jugador jugador, Continente continente){
        return null;
    }


    public static void cargar_paises(HashMap<String, Pais> paises ,HashMap<String, Continente> continentes) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("textfiles/Fronteras.csv"));
        scan.nextLine();

        while (scan.hasNextLine()){
            String[] parametros = (scan.nextLine()).split("\"");
            String nombrePais = (parametros[0]).split(",")[0];
            String nombreContinente = (parametros[0]).split(",")[1];

            if(!continentes.containsKey(nombreContinente)) {
                Continente continente = new Continente(nombreContinente);
                continentes.put(nombreContinente, continente);
            }

            List<String> limitrofes = Arrays.asList((parametros[1]).split(","));
            Pais pais = new Pais(nombrePais, limitrofes, new Ejercitos(1));
            paises.put(nombrePais, pais);
            continentes.get(nombreContinente).agregarPais(pais);
        }
    }

    public static void cargar_cartas(HashMap<String, Pais> paises, List<CartaPais> cartas) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("textfiles/Cartas.csv"));
        scan.nextLine();

        while(scan.hasNextLine()){
            String[] parametros = (scan.nextLine()).split(",");
            String nombrePais = parametros[0];
            String simbolo = parametros[1];

            CartaPais cartaPais = new CartaPais(paises.get(nombrePais), simbolo);
            cartas.add(cartaPais);
        }
    }
}