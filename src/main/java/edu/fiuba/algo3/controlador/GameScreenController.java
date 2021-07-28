package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.exception.CantidadEjercitosInsuficienteException;
import edu.fiuba.algo3.exception.PaisInvalidoException;
import edu.fiuba.algo3.exception.PaisNoLimitrofeException;
import edu.fiuba.algo3.modelo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
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
    public Label labelErrorAtacar;
    public Label labelTurno;
    public Label labelFase;
    public Button botonJugadorActual;



    public Juego juego;
    public ArrayList<Jugador> jugadores;
    public HashMap<String, Pais> paises;
    public HashMap<String, Button> mapBotonesPaises = new HashMap<>();
    public Turno turno;

    int i2 = 0;


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

    public void avanzar(){
        switch (turno.obtenerFase()){
            case ATACAR:
                turno.finalizarAtaque(juego.getMazoCartasPais());
                break;
            case COLOCAR:
                turno.finalizarColocacion();
                break;
            case REAGRUPAR:
                turno.finalizarReagrupe();
        }
        switch (turno.obtenerFase()){
            case ATACAR:
                groupTransferir.setVisible(false);
                groupAtacar.setVisible(true);
                groupColocar.setVisible(false);
                break;
            case COLOCAR:
                groupColocar.setVisible(true);
                groupTransferir.setVisible(false);
                break;
            case REAGRUPAR:
                groupTransferir.setVisible(true);
                groupAtacar.setVisible(false);
        }
        labelTurno.setText(turno.obtenerJugadorActual().getNombre());
        labelFase.setText("Fase: " + turno.obtenerFase().toString());
        botonJugadorActual.setStyle("-fx-background-color: " + turno.obtenerJugadorActual().getColor() + "; -fx-background-radius: 100; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 100; -fx-border-insets: -1;");
    }


    public void seleccionarPais(ActionEvent event){

        String nombrePais = ((Button)event.getSource()).getAccessibleText();
        if(turno.obtenerFase() == Fase.COLOCAR){
            if(turno.obtenerJugadorActual().paisMePertenece(paises.get(nombrePais))){
                textPaisColocar.setText(nombrePais); // hacer que busque a ver si selecciono un pais suyo o no
                sliderCantidadColocar.setMax(10); // el máximo tiene que ser la cantidad disponible  a colocar del jugador
            }
        } else if(turno.obtenerFase() == Fase.ATACAR){
            if(turno.obtenerJugadorActual().paisMePertenece(paises.get(nombrePais))){
                textPaisOrigenAtacar.setText(nombrePais);
            } else {textPaisDestinoAtacar.setText(nombrePais);}
        } else if (turno.obtenerFase() == Fase.REAGRUPAR){
            if(turno.obtenerJugadorActual().paisMePertenece(paises.get(nombrePais))){
                if(i2 %2 == 0){
                    textPaisOrigenTransferir.setText(nombrePais);
                } else {textPaisDestinoTransferir.setText(nombrePais);}
                i2++; //AUXILIAR
            }
        }


    }

    public void iniciarPartida(ArrayList<String> nombresJugadores) throws FileNotFoundException {
        juego = new Juego(nombresJugadores); // Ahora mismo Juego() recibe la cantidad, pero habria que pasarle los nombres

        paises = juego.getPaises();
        jugadores = juego.getJugadores();
        turno = new Turno(jugadores);
        paises.forEach( (nombre, pais) ->  {
            pais.asignarObservador(new ObservadorPais(mapBotonesPaises.get(nombre)));
        });

        juego.asignarPaises();
        juego.crearObjetivosParticulares();
        juego.asignarObjetivos();

        labelTurno.setText(turno.obtenerJugadorActual().getNombre());
        labelFase.setText("Fase " + turno.obtenerFase().toString());
        botonJugadorActual.setStyle("-fx-background-color: " + turno.obtenerJugadorActual().getColor() + "; -fx-background-radius: 100; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 100; -fx-border-insets: -1;");
    }

    public void verObjetivo() throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("/vista/ObjetivoScreen.fxml")));
        Parent root = loader.load();
        ObjetivoScreenController objetivoScreenController = loader.getController();
        objetivoScreenController.setearTexto(turno.obtenerJugadorActual().getTextoObjetivo());
        Stage objetivoStage = new Stage();

        objetivoStage.initModality(Modality.APPLICATION_MODAL);
        objetivoStage.setTitle("Ver objetivo");

        objetivoStage.setScene(new Scene(root));
        objetivoStage.setResizable(false);

        objetivoStage.show();
    }

    public void atacar(){
        if(!textPaisDestinoAtacar.getText().isEmpty() || !textPaisOrigenAtacar.getText().isEmpty()){
            try {
                turno.rondaAtacar(paises.get(textPaisOrigenAtacar.getText()),paises.get(textPaisDestinoAtacar.getText()));
            } catch (CantidadEjercitosInsuficienteException e){
                labelErrorAtacar.setText("Cantidad de ejércitos insuficiente.");
            } catch (PaisInvalidoException e){
                labelErrorAtacar.setText("País invalido");
            } catch (PaisNoLimitrofeException e){
                labelErrorAtacar.setText("Los países no son limítrofes");
            }

        }
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

    public void transferirEjercitos(){
        turno.obtenerJugadorActual().transferirEjercitosDesde(paises.get(textPaisOrigenTransferir.getText()),paises.get(textPaisDestinoTransferir.getText()), (int) sliderCantidadTransferir.getValue());
    }

    public void actualizarCantidadColocar(){
        String nombrePais = textPaisColocar.getText();
        if (nombrePais.isEmpty()){
            return;
        } else {
            labelCantidadColocar.setText("Cantidad: " + String.valueOf((int) sliderCantidadColocar.getValue()));
        }
    }

    public void actualizarCantidadTransferir(){
        String nombrePais = textPaisOrigenTransferir.getText();
        if (nombrePais.isEmpty()){
            return;
        } else {
            labelCantidadTransferir.setText("Cantidad: " + String.valueOf((int) sliderCantidadTransferir.getValue()));
        }
    }

}
