package edu.fiuba.algo3.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Juego {

    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private HashMap<String, Continente> continentes = new HashMap<>();
    private HashMap<String, Pais> paises = new HashMap<>();
    private MazoCartasPais mazoCartasPais = new MazoCartasPais(new ArrayList<>());


    public Juego(ArrayList<String> nombresJugadores) throws FileNotFoundException {


        cargarPaises(paises, continentes);
        cargarCartas(paises, mazoCartasPais);

        ArrayList<Continente> listaContinentes = new ArrayList<>(continentes.values());
        for (int i = 0; i < nombresJugadores.size(); i ++) {
            jugadores.add(new Jugador(i, new DepositoEjercitos(listaContinentes), nombresJugadores.get(i)));
        }

    }

    public void asignarPaises() {
        ArrayList<Pais> listaPaises = new ArrayList<>(paises.values());
        Collections.shuffle(listaPaises);
        for (int i = 0; i < listaPaises.size(); i++) {
            jugadores.get(i % jugadores.size()).asignarPais(listaPaises.get(i));
        }
    }

    public void cargarPaises(HashMap<String, Pais> paises , HashMap<String, Continente> continentes) throws FileNotFoundException {
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

    public static void cargarCartas(HashMap<String, Pais> paises, MazoCartasPais mazo) throws FileNotFoundException {
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

    public HashMap<String, Pais> getPaises() {
        return paises;
    }

    public HashMap<String, Continente> getContinentes() {
        return continentes;
    }

    public Jugador getJugador(int idJugador) {
        return jugadores.get(idJugador - 1);
    }

    public ArrayList<Jugador> getJugadores() { return jugadores;}

    public MazoCartasPais getMazoCartasPais() {
        return mazoCartasPais;
    }
}