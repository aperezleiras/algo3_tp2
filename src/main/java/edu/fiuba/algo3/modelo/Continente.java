package edu.fiuba.algo3.modelo;

import java.util.List;
import java.util.stream.Collectors;

public class Continente {

    private String nombre;
    private List<Pais> paises;
    private int cantidadEjercitosExtra;

    public Continente(String nombre, List<Pais> paises, int cantidadEjercitosExtra) {
        this.nombre = nombre;
        this.paises = paises;
        this.cantidadEjercitosExtra = cantidadEjercitosExtra;
    }

    public int ejercitosExtra(Jugador jugador) {
        return (completo(jugador)) ? cantidadEjercitosExtra : 0;
    }

    public boolean completo(Jugador jugador){
        List<Jugador> jugadores = paises.stream().map(Pais::getJugador).collect(Collectors.toList());

        return  jugadores.stream().allMatch(jugador::equals);
    }

    public boolean tienePais(Pais pais){
        return paises.contains(pais);
    }
}
