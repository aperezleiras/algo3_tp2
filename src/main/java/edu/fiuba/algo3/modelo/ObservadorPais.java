package edu.fiuba.algo3.modelo;

import javafx.scene.control.Button;

public class ObservadorPais implements IObservador {

    private Pais pais;
    private Button botonPais;

    public ObservadorPais(Button unBotonPais){
        botonPais = unBotonPais;
    }

    public void actualizar(){
        botonPais.setStyle("-fx-background-color: " + pais.getJugador().getColor() + "; -fx-background-radius: 100; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 100; -fx-border-insets: -1;");
        botonPais.setText(String.valueOf(pais.getEjercitos().getCantidad()));
    }

    public void asignarPais(Pais pais) {
        this.pais = pais;
    }
}
