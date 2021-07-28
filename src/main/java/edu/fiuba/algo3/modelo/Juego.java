package edu.fiuba.algo3.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Juego {

    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private HashMap<String, Continente> continentes = new HashMap<>();
    private HashMap<String, Pais> paises = new HashMap<>();
    private MazoCartasPais mazoCartasPais = new MazoCartasPais(new ArrayList<>());
    private ArrayList<IObjetivo> objetivos = new ArrayList<>();


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

    public int getCantidadObjetivos() {
        return objetivos.size();
    }

    // todo: Hacer que esto se lea de un archivo
    public void crearObjetivosParticulares() {
//        Ocupar África, 5 países de América del Norte y 4 países de Europa.
        IObjetivo obj1a = new ObjetivoOcupacionContinente(continentes.get("Africa"));
        IObjetivo obj1b = new ObjetivoOcupacionParcialContinente(continentes.get("America del Norte"), 5);
        IObjetivo obj1c = new ObjetivoOcupacionParcialContinente(continentes.get("Europa"), 4);
        IObjetivo obj1 = new ObjetivoCompuesto(Arrays.asList(obj1a, obj1b, obj1c));
        objetivos.add(obj1);

//        Ocupar América del Sur, 7 países de Europa y 3 países limítrofes entre sí en cualquier lugar del mapa.
        // Implementar - o no.

//        Ocupar Asia y 2 países de América del Sur.
        IObjetivo obj2a = new ObjetivoOcupacionContinente(continentes.get("Asia"));
        IObjetivo obj2b = new ObjetivoOcupacionParcialContinente(continentes.get("America del Sur"), 2);
        IObjetivo obj2 = new ObjetivoCompuesto(Arrays.asList(obj2a, obj2b));
        objetivos.add(obj2);

//        Ocupar Europa, 4 países de Asia y 2 países de América de Sur.
        IObjetivo obj3a = new ObjetivoOcupacionContinente(continentes.get("Europa"));
        IObjetivo obj3b = new ObjetivoOcupacionParcialContinente(continentes.get("Asia"), 4);
        IObjetivo obj3c = new ObjetivoOcupacionParcialContinente(continentes.get("America del Sur"), 2);
        IObjetivo obj3 = new ObjetivoCompuesto(Arrays.asList(obj3a, obj3b, obj3c));
        objetivos.add(obj3);

//        Ocupar América del Norte, 2 países de Oceanía y 4 de Asia.
        IObjetivo obj4a = new ObjetivoOcupacionContinente(continentes.get("America del Norte"));
        IObjetivo obj4b = new ObjetivoOcupacionParcialContinente(continentes.get("Oceania"), 2);
        IObjetivo obj4c = new ObjetivoOcupacionParcialContinente(continentes.get("Asia"), 4);
        IObjetivo obj4 = new ObjetivoCompuesto(Arrays.asList(obj4a, obj4b, obj4c));
        objetivos.add(obj4);

//        Ocupar 2 países de Oceanía, 2 países de África, 2 países de América del Sur, 3 países de Europa, 4 de América del Norte y 3 de Asia.
        IObjetivo obj5a = new ObjetivoOcupacionParcialContinente(continentes.get("Oceania"), 2);
        IObjetivo obj5b = new ObjetivoOcupacionParcialContinente(continentes.get("Africa"), 2);
        IObjetivo obj5c = new ObjetivoOcupacionParcialContinente(continentes.get("America del Sur"), 2);
        IObjetivo obj5d = new ObjetivoOcupacionParcialContinente(continentes.get("Europa"), 3);
        IObjetivo obj5e = new ObjetivoOcupacionParcialContinente(continentes.get("America del Norte"), 4);
        IObjetivo obj5f = new ObjetivoOcupacionParcialContinente(continentes.get("Asia"), 3);
        IObjetivo obj5 = new ObjetivoCompuesto(Arrays.asList(obj5a, obj5b, obj5c, obj5d, obj5e, obj5f));
        objetivos.add(obj5);

//        Ocupar Oceanía, América del Norte y 2 países de Europa.
        IObjetivo obj6a = new ObjetivoOcupacionContinente(continentes.get("Oceania"));
        IObjetivo obj6b = new ObjetivoOcupacionContinente(continentes.get("America del Norte"));
        IObjetivo obj6c = new ObjetivoOcupacionParcialContinente(continentes.get("Europa"), 2);
        IObjetivo obj6 = new ObjetivoCompuesto(Arrays.asList(obj6a, obj6b, obj6c));
        objetivos.add(obj6);

//        Ocupar América del Sur, África y 5 países de América del Norte.
        IObjetivo obj7a = new ObjetivoOcupacionContinente(continentes.get("America del Sur"));
        IObjetivo obj7b = new ObjetivoOcupacionContinente(continentes.get("Africa"));
        IObjetivo obj7c = new ObjetivoOcupacionParcialContinente(continentes.get("America del Norte"), 5);
        IObjetivo obj7 = new ObjetivoCompuesto(Arrays.asList(obj7a, obj7b, obj7c));
        objetivos.add(obj7);

//        Derrotar jugadores
        IObjetivo objJugador;
        for (Jugador jugador : jugadores) {
            objJugador = new ObjetivoDerrotarJugador(jugador);
            objetivos.add(objJugador);
        }
    }

    public void actualizarObjetivosDerrotarJugador() {
        //todo: cuando un jugador es derrotado los objetivos que involucraban su derrota se tienen que actualizar para cada jugador
    }

    public void asignarObjetivos() {
        Random rand = new Random();
        IObjetivo obj = null;
        boolean objetivoValido = false;

        for (Jugador jugador : jugadores) {
            while (!objetivoValido) {
                obj = objetivos.get(rand.nextInt(objetivos.size()));
                objetivoValido = obj.esValidoPara(jugador);
            }
            jugador.asignarObjetivo(obj);
            jugador.asignarObjetivo(new ObjetivoOcupacionPaises(30));
        }
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