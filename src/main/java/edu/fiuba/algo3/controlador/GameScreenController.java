package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GameScreenController implements Initializable {

    @FXML
    public Button botonAvanzarFase;
    public Group groupTransferir;
    public Group groupAtacar;
    public Group groupColocar;
    public Group groupCanjear;
    public Group botonesPaises;
    public Label labelCantidadColocar;
    public Slider sliderCantidadColocar;
    public TextField textPaisColocar;
    public Label labelCantidadTransferir;
    public Slider sliderCantidadTransferir;
    public TextField textPaisDestinoTransferir;
    public TextField textPaisOrigenTransferir;
    public TextField textPaisOrigenAtacar;
    public TextField textPaisDestinoAtacar;

    public Juego juego;
    public ArrayList<Jugador> jugadores;
    public HashMap<String, Pais> paises;
    public HashMap<String, Button> mapBotonesPaises = new HashMap<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarBotonesPaises();
    }

    private void cargarBotonesPaises() {
        botonesPaises.getChildren().forEach(node -> {
            String nombrePais = node.getAccessibleText();
            Button botonPais = (Button) node;
            mapBotonesPaises.put(nombrePais,botonPais);
        });
    }

    public void avanzarFase(){

    }

    public void seleccionarPais(ActionEvent event){
        String nombrePais = ((Button)event.getSource()).getAccessibleText();
        textPaisColocar.setText(nombrePais); // hacer que busque a ver si selecciono un pais suyo o no
        sliderCantidadColocar.setMax(10); // el máximo tiene que ser la cantidad disponible  a colocar del jugador

    }

    public void iniciarPartida(ArrayList<String> nombresJugadores) throws FileNotFoundException {
        juego = new Juego(nombresJugadores.size()); // Ahora mismo Juego() recibe la cantidad, pero habria que pasarle los nombres
        paises = juego.getPaises();
        jugadores = juego.getJugadores();

        paises.forEach( (nombre, pais) ->  {
            pais.asignarObservador(new ObservadorPais(mapBotonesPaises.get(nombre)));
        });

        juego.asignarPaises();
    }

    public void verObjetivo() throws IOException {
        Parent root = FXMLLoader.load((getClass().getResource("/vista/ObjetivoScreen.fxml")));
        // todo: Acá habría que sacar del jugador actual el texto de la misión
        Stage objetivoStage = new Stage();

        objetivoStage.initModality(Modality.APPLICATION_MODAL);
        objetivoStage.setTitle("Ver objetivo");

        objetivoStage.setScene(new Scene(root));
        objetivoStage.setResizable(false);
        objetivoStage.show();
    }

    public void atacar(){

    }

    public void colocarEjercitos(){
        String nombrePais = textPaisColocar.getText();
        if (nombrePais.isEmpty()){
            return;
        } else {
            Pais pais = paises.get(textPaisColocar.getText());
            int cantidad = (int) sliderCantidadColocar.getValue();
            pais.agregarEjercitos(cantidad);
            //pais.getJugador().colocarEjercitos(pais, cantidad); // probablemento esto saque de 'jugadorActual' mas que del pais
        }
    }

    public void actualizarCantidadColocar(){
        String nombrePais = textPaisColocar.getText();
        if (nombrePais.isEmpty()){
            return;
        } else {
            labelCantidadColocar.setText("Cantidad: " + String.valueOf((int) sliderCantidadColocar.getValue()));
        }
    }

}
