package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.exception.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.vista.ObservadorJugador;
import edu.fiuba.algo3.vista.ObservadorPais;
import edu.fiuba.algo3.vista.ObservadorTurno;
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
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.media.AudioClip;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

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
    public Label labelErrorTransferir;
    public Label labelTurno;
    public Label labelFase;
    public Button botonJugadorActual;
    public Group groupEjercitosDisponibles;
    public Button botonSeleccionarPaisOrigenReagrupe;
    public Button botonSeleccionarPaisDestinoReagrupe;
    public Group groupTarjetasPais;
    public Label labelErrorColocar;
    public Button botonCanjear;
    public Button botonActivarCarta;
    public Label labelErrorCanjear;
    public ImageView dadoAtacante1;
    public ImageView dadoAtacante2;
    public ImageView dadoAtacante3;
    public ImageView dadoDefensor1;
    public ImageView dadoDefensor2;
    public ImageView dadoDefensor3;


    public AudioClip audioClick, audioDrums, audioExplosion, audioMusic, audioClick_2;
    public Juego juego;
    public ArrayList<Jugador> jugadores;
    public HashMap<String, Pais> paises;
    public HashMap<String, Button> mapBotonesPaises = new HashMap<>();
    public Turno turno;
    private boolean reagrupeSeleccionarOrigen = true;
    private HashMap<String, CartaPais> cartas = new HashMap<>();
    private ArrayList<CartaPais> cartasSeleccionadas = new ArrayList<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarBotonesPaises();
        audioClick = new AudioClip(getClass().getResource("/soundfx/click.mp3").toString());
        audioDrums = new AudioClip(getClass().getResource("/soundfx/drums.mp3").toString());
        audioExplosion = new AudioClip(getClass().getResource("/soundfx/explosion.mp3").toString());
        audioMusic = new AudioClip(getClass().getResource("/soundfx/music.mp3").toString());
        audioClick_2 = new AudioClip(getClass().getResource("/soundfx/click_2.mp3").toString());
        audioClick.setVolume(0.5);
        audioDrums.setVolume(0.5);
        audioClick_2.setVolume(0.5);
        audioMusic.setCycleCount(AudioClip.INDEFINITE);
        audioMusic.play(0.5);

    }

    private void cargarBotonesPaises() {
        botonesPaises.getChildren().forEach(node -> {
            String nombrePais = node.getAccessibleText();
            Button botonPais = (Button) node;
            mapBotonesPaises.put(nombrePais,botonPais);
        });
    }

    public void finalizarColocacion(){

        audioClick.play();
        labelErrorColocar.setText(" ");
        textPaisColocar.setText("");
        sliderCantidadColocar.setMax(0);
        turno.finalizarColocacion();
    }

    public void finalizarAtaque(){
        audioClick.play();
        labelErrorAtacar.setText(" ");
        textPaisDestinoAtacar.setText("");
        textPaisOrigenAtacar.setText("");

        turno.finalizarAtaque(juego.getMazoCartasPais());
    }

    public void finalizarReagrupe(){
        audioClick.play();
        labelErrorTransferir.setText(" ");
        textPaisOrigenTransferir.setText("");
        textPaisDestinoTransferir.setText(" ");
        labelCantidadTransferir.setText("Cantidad: 0");
        turno.finalizarReagrupe();
    }

    public void finalizarCanjes(){
        audioClick.play();
        botonCanjear.setDisable(true);
        cartasSeleccionadas.clear();
        groupTarjetasPais.getChildren().forEach(boton -> {
            boton.setEffect(null);
        });
        labelErrorCanjear.setText(" ");
        turno.finalizarCanjes();
    }

    public void seleccionarPais(ActionEvent event){
        audioClick_2.play();
        String nombrePais = ((Button)event.getSource()).getAccessibleText();
        Pais pais = paises.get(nombrePais);
        switch (turno.obtenerFase()){
            case COLOCAR:
                if (turno.obtenerJugadorActual().paisMePertenece(pais)) {
                    textPaisColocar.setText(nombrePais);
                    sliderCantidadColocar.setMax(turno.obtenerJugadorActual().obtenerTotalEjercitos()); //Si quiero que muestre la mayor cantidad posible, hay que cambiar un par de cosas
                }
                break;
            case ATACAR:
                if (turno.obtenerJugadorActual().paisMePertenece(pais))
                    textPaisOrigenAtacar.setText(nombrePais);
                else
                    textPaisDestinoAtacar.setText(nombrePais);
                break;
            case REAGRUPAR:
                if (turno.obtenerJugadorActual().paisMePertenece(pais)) {
                    if (reagrupeSeleccionarOrigen) {
                        sliderCantidadTransferir.setMax(pais.cantidadEjercitos()-1);
                        textPaisOrigenTransferir.setText(nombrePais);
                    } else {
                        textPaisDestinoTransferir.setText(nombrePais);
                    }
                }
                break;
        }
    }

    public void iniciarPartida(ArrayList<String> nombresJugadores) throws FileNotFoundException {
        juego = new Juego(nombresJugadores);

        paises = juego.getPaises();
        jugadores = juego.getJugadores();
        MazoCartasPais mazoCartas = juego.getMazoCartasPais();
        List<CartaPais> listaCartas = mazoCartas.getCartas();
        listaCartas.forEach(cartaPais -> {
            cartas.put(cartaPais.getPais().getNombre(),cartaPais);
        });
        jugadores.forEach(jugador -> jugador.asignarObservador(new ObservadorJugador(groupEjercitosDisponibles, groupTarjetasPais)));
        turno = new Turno(jugadores);
        turno.obtenerJugadorActual().actualizarObservadores();
        ArrayList<ImageView> imageViewDados = new ArrayList<>(Arrays.asList(dadoAtacante1, dadoAtacante2, dadoAtacante3, dadoDefensor1, dadoDefensor2, dadoDefensor3));
        turno.asignarObservador(new ObservadorTurno(groupTransferir, groupAtacar, groupColocar, groupCanjear, labelTurno, labelFase, botonJugadorActual, imageViewDados));
        paises.forEach( (nombre, pais) ->  {
            pais.asignarObservador(new ObservadorPais(mapBotonesPaises.get(nombre)));
        });

        juego.asignarPaises();
        juego.cargarObjetivos();
        juego.asignarObjetivos();
        labelTurno.setText("Turno de: " + turno.obtenerJugadorActual().getNombre());
        labelFase.setText("Fase: " + turno.obtenerFase().toString());
        botonJugadorActual.setStyle("-fx-background-color: " + turno.obtenerJugadorActual().getColor() + "; -fx-background-radius: 100; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 100; -fx-border-insets: -1;");
    }

    public void verObjetivo() throws IOException {
        audioClick.play();
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
        labelErrorAtacar.setText(" ");
        if(!textPaisDestinoAtacar.getText().isEmpty() || !textPaisOrigenAtacar.getText().isEmpty()){
            try {
                turno.rondaAtacar(paises.get(textPaisOrigenAtacar.getText()),paises.get(textPaisDestinoAtacar.getText()));
                audioExplosion.play();
            } catch (CantidadEjercitosInsuficienteException e){
                labelErrorAtacar.setText("Cantidad de ejércitos insuficiente.");
            } catch (PaisInvalidoException e){
                labelErrorAtacar.setText("País invalido.");
            } catch (PaisNoLimitrofeException e){
                labelErrorAtacar.setText("Los países no son limítrofes.");
            }
        }
    }

    public void colocarEjercitos(){
        audioDrums.play();
        labelErrorColocar.setText(" ");
        String nombrePais = textPaisColocar.getText();

        if (nombrePais.isEmpty())
            return;

        Pais pais = paises.get(textPaisColocar.getText());
        int cantidad = (int) sliderCantidadColocar.getValue();
        try {
            turno.colocarEjercito(pais, cantidad);
            sliderCantidadColocar.setMax(turno.obtenerJugadorActual().obtenerTotalEjercitos());
            actualizarCantidadColocar();
        } catch (CantidadEjercitosInsuficienteException e){
            labelErrorColocar.setText("Cantidad de ejércitos insuficiente.");
        }

    }

    public void seleccionarTarjeta(ActionEvent event){
        audioClick_2.play();
        Button boton = ((Button)event.getSource());
        CartaPais carta = cartas.get(boton.getText());

        if(cartasSeleccionadas.contains(carta)){
            cartasSeleccionadas.remove(carta);
            boton.setEffect(null);
            botonCanjear.setDisable(true);
        } else if(cartasSeleccionadas.size() < 3){
                boton.setEffect(new SepiaTone());
                cartasSeleccionadas.add(carta);
        }
        if (cartasSeleccionadas.size() == 1) botonActivarCarta.setDisable(false);
        else if(cartasSeleccionadas.size() == 3) botonCanjear.setDisable(false);
        else {
            botonActivarCarta.setDisable(true);
            botonCanjear.setDisable(true);
        }
    }

    public void realizarCanje(){
        audioClick.play();
        try{
            turno.realizarCanje(cartasSeleccionadas, juego.getMazoCartasPais());
            groupTarjetasPais.getChildren().forEach(boton -> {
                boton.setEffect(null);
            });
            cartasSeleccionadas.clear();
            botonCanjear.setDisable(true);
        } catch (CartasNoCanjeablesException e){
            labelErrorCanjear.setText("Las cartas seleccionadas no son canjeables.");
        }
    }

    public void activarCarta(){
        audioClick.play();
        try{turno.agregarEjercitosSegunCarta(cartasSeleccionadas.get(0));
        } catch (CartaYaActivadaException e){
            labelErrorCanjear.setText("Carta ya fue activada.");
        } catch (PaisNoMePerteneceException e){
            labelErrorCanjear.setText("País no le pertenece.");
        }

    }

    public void transferirEjercitos(){
        labelErrorTransferir.setText(" ");
        try {
            turno.rondaReagrupar(paises.get(textPaisOrigenTransferir.getText()),paises.get(textPaisDestinoTransferir.getText()), (int) sliderCantidadTransferir.getValue());
            audioDrums.play();
            sliderCantidadTransferir.setMax(paises.get(textPaisOrigenTransferir.getText()).cantidadEjercitos()-1);
            labelCantidadTransferir.setText("Cantidad: " + String.valueOf((int) sliderCantidadTransferir.getValue()));
        } catch (CantidadATransferirInvalidaException e) {
            labelErrorTransferir.setText("Cantidad de ejércitos insuficiente.");
        } catch (PaisNoLimitrofeException e){
            labelErrorTransferir.setText("Combinación de paises inválida.");
        }
    }

    public void actualizarCantidadColocar(){
        String nombrePais = textPaisColocar.getText();

        if (nombrePais.isEmpty())
            return;

        labelCantidadColocar.setText("Cantidad: " + String.valueOf((int) sliderCantidadColocar.getValue()));
    }

    public void actualizarCantidadTransferir(){
        String nombrePais = textPaisOrigenTransferir.getText();

        if (nombrePais.isEmpty())
            return;

        labelCantidadTransferir.setText("Cantidad: " + String.valueOf((int) sliderCantidadTransferir.getValue()));
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

    public void silenciarSonidos(ActionEvent event){
        Button boton = ((Button)event.getSource());
        if(audioDrums.getVolume() == 0.0){
            boton.setEffect(new ColorAdjust(0,0,0,0));
            audioDrums.setVolume(0.5);
            audioClick.setVolume(0.5);
            audioExplosion.setVolume(1.0);
            audioClick_2.setVolume(0.5);
        } else {
            boton.setEffect(new ColorAdjust(0,-1,0,0));
            audioDrums.setVolume(0.0);
            audioClick.setVolume(0.0);
            audioExplosion.setVolume(0.0);
            audioClick_2.setVolume(0.0);
        }
    }

    public void silenciarMusica(ActionEvent event){
        Button boton = ((Button)event.getSource());
        if(!audioMusic.isPlaying()){
            boton.setEffect(new ColorAdjust(0,0,0,0));
            audioMusic.play();
        } else {
            boton.setEffect(new ColorAdjust(0,-1,0,0));
            audioMusic.stop();
        }
    }

}
