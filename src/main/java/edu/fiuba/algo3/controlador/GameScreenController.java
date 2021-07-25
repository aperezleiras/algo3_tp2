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
    public Group botonesPaises;

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
        // hacer que busque a ver si selecciono un pais suyo o no

    }

    public void iniciarPartida(ArrayList<String> nombresJugadores) throws FileNotFoundException {
        juego = new Juego(nombresJugadores.size()); // Ahora mismo Juego() recibe la cantidad, pero habria que pasarle los nombres
        jugadores = juego.getJugadores();
        paises = juego.getPaises();
        mapBotonesPaises.forEach((paisNombre, botonPais) -> {
             actualizarBotonPais(paisNombre, botonPais);
        });
    }

    private void actualizarBotonPais(String paisNombre, Button botonPais){
        botonPais.setStyle("-fx-background-color: " + paises.get(paisNombre).getJugador().getColor() + "; -fx-background-radius: 100; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 100; -fx-border-insets: -1;");
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

}
