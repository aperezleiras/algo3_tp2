package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Pais {

    Ejercitos ejercitos = new Ejercitos();
    ArrayList limitrofes = new ArrayList();

    Pais(){
    }

    public void colocarEjercitos(int cantidad) {
        ejercitos.agregarEjercitos(cantidad);
    }

    public void atacarA(Pais paisDestino, int cantidad) {

    }

    public boolean esLimitrofeCon(Pais unPais) {
        return limitrofes.contains(unPais);
    }

    public void transferirEjercitosA(Pais paisDestino, int cantidad) {
        ejercitos.transferirEjercitos(paisDestino, cantidad);
    }
}
