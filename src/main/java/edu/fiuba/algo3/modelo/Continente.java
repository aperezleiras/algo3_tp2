package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Continente {

    private String nombre;
    private List<Pais> paises;
    private int cantidadEjercitosExtra;

    public Continente(String nombre) {
        this.nombre = nombre;
        paises = new ArrayList<>();
        asignarEjercitosExtraSegunContinente();
    }

    public void agregarPais(Pais pais){
        paises.add(pais);
    }

    public boolean tienePais(Pais pais){
        return paises.contains(pais);
    }

    public void setCantidadEjercitosExtra(int cantidadEjercitosExtra) {
        this.cantidadEjercitosExtra = cantidadEjercitosExtra;
    }

    private void asignarEjercitosExtraSegunContinente() {    ////////////////////////
        switch (nombre) {
            case "Asia": cantidadEjercitosExtra = 7; break;
            case "Europa": cantidadEjercitosExtra = 5; break;
            case "America del Norte": cantidadEjercitosExtra = 5; break;
            case "America del Sur": cantidadEjercitosExtra = 3; break;
            case "Africa": cantidadEjercitosExtra = 3; break;
            case "Oceania": cantidadEjercitosExtra = 2; break;
        }
    }

    public int obtenerEjercitosExtra(Jugador jugador) {
        return (completo(jugador)) ? cantidadEjercitosExtra : 0;
    }

    public boolean completo(Jugador jugador){
        List<Jugador> jugadores = paises.stream().map(Pais::getJugador).collect(Collectors.toList());

        return  jugadores.stream().allMatch(jugador::equals);
    }
}
