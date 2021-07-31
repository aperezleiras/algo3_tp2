package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.CartaPais;
import edu.fiuba.algo3.modelo.IObservador;
import edu.fiuba.algo3.modelo.Jugador;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ObservadorJugador implements IObservador {

    private Jugador jugador;
    private ArrayList<Label> labelsEjercitos = new ArrayList<>();
    private ArrayList<Button> botonesTarjetasPais = new ArrayList<>();
    int contador = 1; // feo
    Image imagenBarco = new Image(getClass().getResourceAsStream("/img/barco.jpg"));
    Image imagenCanion = new Image(getClass().getResourceAsStream("/img/canion.jpg"));
    Image imagenGlobo = new Image(getClass().getResourceAsStream("/img/globo.jpg"));
    Image imagenComodin = new Image(getClass().getResourceAsStream("/img/comodin.png"));

    public ObservadorJugador(Group groupEjercitosDisponibles, Group groupTarjetasPais){
        groupEjercitosDisponibles.getChildren().forEach(node -> {
            labelsEjercitos.add((Label) node);
        });
        groupTarjetasPais.getChildren().forEach(node -> {
            botonesTarjetasPais.add((Button) node);
        });
    }

    @Override
    public void actualizar() {
        actualizarLabelsEjercitosDisponibles();
        actualizarTarjetasPais();
    }

    private void actualizarTarjetasPais(){
        List<CartaPais> cartas = jugador.getCartas();
        for(int i = 0; i<5;i++){
            try {
                CartaPais carta = cartas.get(i);
                Button boton = botonesTarjetasPais.get(i);
                boton.setVisible(true);
                boton.setText(carta.getPais().getNombre());
                ImageView imagen = (ImageView) boton.getGraphic();
                switch(carta.getSimbolo()){
                    case BARCO:
                        imagen.setImage(imagenBarco); // Esto se puede ahorra el switch dandola a la carta la imagen y listo
                        break;
                    case CAÑON:
                        imagen.setImage(imagenCanion);
                        break;
                    case GLOBO:
                        imagen.setImage(imagenGlobo);
                        break;
                    case COMODIN:
                        imagen.setImage(imagenComodin);
                        break;
                }
            } catch (IndexOutOfBoundsException e){
                botonesTarjetasPais.get(i).setVisible(false);
            }
        }
    }

    private void actualizarLabelsEjercitosDisponibles(){
        labelsEjercitos.get(0).setText("Generales: " + jugador.obtenerEjercitosGeneralesDisponibles());

        jugador.getEjercitosPorContinente().forEach( (continente, cantidad) -> {
            labelsEjercitos.get(contador).setText(continente.getNombre() + ": " + cantidad);
            contador++;
        });
        contador = 1;
    }

    public void asignarJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
