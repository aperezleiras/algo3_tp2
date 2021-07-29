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
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
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
    public Group groupEjercitosDisponibles;
    public Button botonSeleccionarPaisOrigenReagrupe;
    public Button botonSeleccionarPaisDestinoReagrupe;

    public Juego juego;
    public ArrayList<Jugador> jugadores;
    public HashMap<String, Pais> paises;
    public HashMap<String, Button> mapBotonesPaises = new HashMap<>();
    public Turno turno;
    private boolean reagrupeSeleccionarOrigen = true;




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


    public void finalizarColocacion(){
        turno.finalizarColocacion();
    }

    public void finalizarAtaque(){
        turno.finalizarAtaque(juego.getMazoCartasPais());
    }

    public void finalizarReagrupe(){
        turno.finalizarReagrupe();
    }


    public void seleccionarPais(ActionEvent event){

        String nombrePais = ((Button)event.getSource()).getAccessibleText();
        Pais pais = paises.get(nombrePais);
        switch (turno.obtenerFase()){
            case COLOCAR:
                if(turno.obtenerJugadorActual().paisMePertenece(pais)){
                    textPaisColocar.setText(nombrePais);
                    sliderCantidadColocar.setMax(turno.obtenerJugadorActual().obtenerTotalEjercitos()); //Si quiero que muestre la mayor cantidad posible, hay que cambiar un par de cosas
                }
                break;
            case ATACAR:
                if(turno.obtenerJugadorActual().paisMePertenece(pais)){
                    textPaisOrigenAtacar.setText(nombrePais);
                } else {textPaisDestinoAtacar.setText(nombrePais);}
                break;
            case REAGRUPAR:
                if(turno.obtenerJugadorActual().paisMePertenece(paises.get(nombrePais))){
                    if(reagrupeSeleccionarOrigen) textPaisOrigenTransferir.setText(nombrePais);
                    else textPaisDestinoTransferir.setText(nombrePais);
                }
                break;
        }
    }

    public void iniciarPartida(ArrayList<String> nombresJugadores) throws FileNotFoundException {
        juego = new Juego(nombresJugadores); // Ahora mismo Juego() recibe la cantidad, pero habria que pasarle los nombres

        paises = juego.getPaises();
        jugadores = juego.getJugadores();
        jugadores.forEach(jugador -> jugador.asignarObservador(new ObservadorJugador(groupEjercitosDisponibles)));
        turno = new Turno(jugadores);
        turno.obtenerJugadorActual().actualizarObservadores();
        turno.asignarObservador(new ObservadorTurno(groupTransferir, groupAtacar, groupColocar, groupCanjear, labelTurno, labelFase, botonJugadorActual));
        paises.forEach( (nombre, pais) ->  {
            pais.asignarObservador(new ObservadorPais(mapBotonesPaises.get(nombre)));
        });

        juego.asignarPaises();
        juego.cargarObjetivos();
        juego.asignarObjetivos();

        labelTurno.setText("Turno de: " + turno.obtenerJugadorActual().getNombre());
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
        objetivoStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icono.png")));
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
            turno.colocarEjercito(pais, cantidad);
            sliderCantidadColocar.setMax(turno.obtenerJugadorActual().obtenerTotalEjercitos());
            actualizarCantidadColocar();
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

    public void seleccionarPaisOrigenReagrupe(){
        botonSeleccionarPaisDestinoReagrupe.setEffect(null);
        botonSeleccionarPaisOrigenReagrupe.setEffect(new Glow());
        reagrupeSeleccionarOrigen = true;
    }

    public void seleccionarPaisDestinoReagrupe(){
        botonSeleccionarPaisOrigenReagrupe.setEffect(null);
        botonSeleccionarPaisDestinoReagrupe.setEffect(new Glow());
        reagrupeSeleccionarOrigen = false;
    }

}
