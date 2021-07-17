package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Continente {

    private String nombre; // No se si cumple alguna función esto pero lo dejo x las dudas
    List<Pais> paises = new ArrayList<>();

    public Continente(String nombreContinente){
        nombre = nombreContinente;
    }

    public void agregarPais(Pais pais) {
        paises.add(pais);
    }
}
