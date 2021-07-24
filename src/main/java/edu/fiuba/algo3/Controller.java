package edu.fiuba.algo3;


import edu.fiuba.algo3.exception.*;
import edu.fiuba.algo3.modelo.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    @FXML
    public ChoiceBox<String> CBAtacante;
    public ChoiceBox<String> CBDefensor;
    public Circle CircleEspania;
    public Label LabelEspania;
    public Circle CircleFrancia;
    public Label LabelFrancia;
    public Circle CircleSahara;
    public Label LabelSahara;
    public Circle CircleZaire;
    public Label LabelZaire;
    public Circle CircleSudafrica;
    public Label LabelSudafrica;
    public Circle CircleMadagascar;
    public Label LabelMadagascar;
    public Circle CircleEtiopia;
    public Label LabelEtiopia;
    public Circle CircleEgipto;
    public Label LabelEgipto;
    public Circle CircleItalia;
    public Label LabelItalia;
    public Circle CircleAlemania;
    public Label LabelAlemania;
    public Circle CirclePolonia;
    public Label LabelPolonia;
    public Circle CircleRusia;
    public Label LabelRusia;
    public Circle CircleSuecia;
    public Label LabelSuecia;
    public Circle CircleGranBretania;
    public Label LabelGranBretania;
    public Circle CircleTurquia;
    public Label LabelTurquia;
    public Circle CircleIsrael;
    public Label LabelIsrael;
    public Circle CircleArabia;
    public Label LabelArabia;
    public Circle CircleChina;
    public Label LabelChina;
    public Circle CircleIndia;
    public Label LabelIndia;
    public Circle CircleAustralia;
    public Label LabelAustralia;
    public Circle CircleSumatra;
    public Label LabelSumatra;
    public Circle CircleMalasia;
    public Label LabelMalasia;
    public Circle CircleBorneo;
    public Label LabelBorneo;
    public Circle CircleJava;
    public Label LabelJava;
    public Circle CircleIran;
    public Label LabelIran;
    public Circle CircleGobi;
    public Label LabelGobi;
    public Circle CircleMongolia;
    public Label LabelMongolia;
    public Circle CircleAral;
    public Label LabelAral;
    public Circle CircleSiberia;
    public Label LabelSiberia;
    public Circle CircleTartaria;
    public Label LabelTartaria;
    public Circle CircleTaymir;
    public Label LabelTaymir;
    public Circle CircleKamtchatka;
    public Label LabelKamtchatka;
    public Circle CircleJapon;
    public Label LabelJapon;
    public Circle CircleBrasil;
    public Label LabelBrasil;
    public Circle CircleArgentina;
    public Label LabelArgentina;
    public Circle CircleUruguay;
    public Label LabelUruguay;
    public Circle CirclePeru;
    public Label LabelPeru;
    public Circle CircleChile;
    public Label LabelChile;
    public Circle CircleColombia;
    public Label LabelColombia;
    public Circle CircleMexico;
    public Label LabelMexico;
    public Circle CircleIslandia;
    public Label LabelIslandia;
    public Circle CircleGroenlandia;
    public Label LabelGroenlandia;
    public Circle CircleCalifornia;
    public Label LabelCalifornia;
    public Circle CircleOregon;
    public Label LabelOregon;
    public Circle CircleAlaska;
    public Label LabelAlaska;
    public Circle CircleYukon;
    public Label LabelYukon;
    public Circle CircleCanada;
    public Label LabelCanada;
    public Circle CircleLabrador;
    public Label LabelLabrador;
    public Circle CircleTerranova;
    public Label LabelTerranova;
    public Circle CircleNuevaYork;
    public Label LabelNuevaYork;

    public Label LabelErrores;

    Jugador jugador1 = new Jugador(1);
    Jugador jugador2 = new Jugador(2);
    //Jugador jugador3 = new Jugador(3);
    //Jugador jugador4 = new Jugador(4);

    Jugador[] jugadores = {jugador1,jugador2};
    Juego juego = new Juego(Arrays.asList(jugadores));
    HashMap<String, Pais> mapPaises = juego.getPaises();
    HashMap<String, Label> mapLabels = new HashMap<>();
    HashMap<String, Circle> mapCircles= new HashMap<>();
    public Controller() throws FileNotFoundException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CBAtacante.getItems().addAll(mapPaises.keySet().stream().sorted().collect(Collectors.toList())); // todo: hay que ordenar esto alfabeticamente
        CBDefensor.getItems().addAll(mapPaises.keySet().stream().sorted().collect(Collectors.toList())); // todo: hay que ordenar esto alfabeticamente
        cargarLabelsCircles(mapLabels, mapCircles);
        mapPaises.values().forEach(p -> {
            mapCircles.get(p.getNombre()).setFill(p.getJugador().getColor());
            mapLabels.get(p.getNombre()).setText(Integer.toString(p.getEjercitos().getCantidad()));
        });
    }

    private void cargarLabelsCircles(HashMap<String, Label> mapLabels, HashMap<String, Circle> mapCircles) {
        mapCircles.put("España",CircleEspania);
        mapLabels.put("España",LabelEspania);
        mapCircles.put("Francia",CircleFrancia);
        mapLabels.put("Francia",LabelFrancia);
        mapCircles.put("Sahara",CircleSahara);
        mapLabels.put("Sahara",LabelSahara);
        mapCircles.put("Zaire",CircleZaire);
        mapLabels.put("Zaire",LabelZaire);
        mapCircles.put("Sudafrica",CircleSudafrica);
        mapLabels.put("Sudafrica",LabelSudafrica);
        mapCircles.put("Madagascar",CircleMadagascar);
        mapLabels.put("Madagascar",LabelMadagascar);
        mapCircles.put("Etiopia",CircleEtiopia);
        mapLabels.put("Etiopia",LabelEtiopia);
        mapCircles.put("Egipto",CircleEgipto);
        mapLabels.put("Egipto",LabelEgipto);
        mapCircles.put("Italia",CircleItalia);
        mapLabels.put("Italia",LabelItalia);
        mapCircles.put("Alemania",CircleAlemania);
        mapLabels.put("Alemania",LabelAlemania);
        mapCircles.put("Polonia",CirclePolonia);
        mapLabels.put("Polonia",LabelPolonia);
        mapCircles.put("Rusia",CircleRusia);
        mapLabels.put("Rusia",LabelRusia);
        mapCircles.put("Suecia",CircleSuecia);
        mapLabels.put("Suecia",LabelSuecia);
        mapCircles.put("Gran Bretaña",CircleGranBretania);
        mapLabels.put("Gran Bretaña",LabelGranBretania);
        mapCircles.put("Turquia",CircleTurquia);
        mapLabels.put("Turquia",LabelTurquia);
        mapCircles.put("Israel",CircleIsrael);
        mapLabels.put("Israel",LabelIsrael);
        mapCircles.put("Arabia",CircleArabia);
        mapLabels.put("Arabia",LabelArabia);
        mapCircles.put("China",CircleChina);
        mapLabels.put("China",LabelChina);
        mapCircles.put("India",CircleIndia);
        mapLabels.put("India",LabelIndia);
        mapCircles.put("Australia",CircleAustralia);
        mapLabels.put("Australia",LabelAustralia);
        mapCircles.put("Sumatra",CircleSumatra);
        mapLabels.put("Sumatra",LabelSumatra);
        mapCircles.put("Malasia",CircleMalasia);
        mapLabels.put("Malasia",LabelMalasia);
        mapCircles.put("Borneo",CircleBorneo);
        mapLabels.put("Borneo",LabelBorneo);
        mapCircles.put("Java",CircleJava);
        mapLabels.put("Java",LabelJava);
        mapCircles.put("Iran",CircleIran);
        mapLabels.put("Iran",LabelIran);
        mapCircles.put("Gobi",CircleGobi);
        mapLabels.put("Gobi",LabelGobi);
        mapCircles.put("Mongolia",CircleMongolia);
        mapLabels.put("Mongolia",LabelMongolia);
        mapCircles.put("Aral",CircleAral);
        mapLabels.put("Aral",LabelAral);
        mapCircles.put("Siberia",CircleSiberia);
        mapLabels.put("Siberia",LabelSiberia);
        mapCircles.put("Tartaria",CircleTartaria);
        mapLabels.put("Tartaria",LabelTartaria);
        mapCircles.put("Taymir",CircleTaymir);
        mapLabels.put("Taymir",LabelTaymir);
        mapCircles.put("Kamtchatka",CircleKamtchatka);
        mapLabels.put("Kamtchatka",LabelKamtchatka);
        mapCircles.put("Japon",CircleJapon);
        mapLabels.put("Japon",LabelJapon);
        mapCircles.put("Brasil",CircleBrasil);
        mapLabels.put("Brasil",LabelBrasil);
        mapCircles.put("Argentina",CircleArgentina);
        mapLabels.put("Argentina",LabelArgentina);
        mapCircles.put("Uruguay",CircleUruguay);
        mapLabels.put("Uruguay",LabelUruguay);
        mapCircles.put("Peru",CirclePeru);
        mapLabels.put("Peru",LabelPeru);
        mapCircles.put("Chile",CircleChile);
        mapLabels.put("Chile",LabelChile);
        mapCircles.put("Colombia",CircleColombia);
        mapLabels.put("Colombia",LabelColombia);
        mapCircles.put("Mexico",CircleMexico);
        mapLabels.put("Mexico",LabelMexico);
        mapCircles.put("Islandia",CircleIslandia);
        mapLabels.put("Islandia",LabelIslandia);
        mapCircles.put("Groenlandia",CircleGroenlandia);
        mapLabels.put("Groenlandia",LabelGroenlandia);
        mapCircles.put("California",CircleCalifornia);
        mapLabels.put("California",LabelCalifornia);
        mapCircles.put("Oregon",CircleOregon);
        mapLabels.put("Oregon",LabelOregon);
        mapCircles.put("Alaska",CircleAlaska);
        mapLabels.put("Alaska",LabelAlaska);
        mapCircles.put("Yukon",CircleYukon);
        mapLabels.put("Yukon",LabelYukon);
        mapCircles.put("Canada",CircleCanada);
        mapLabels.put("Canada",LabelCanada);
        mapCircles.put("Labrador",CircleLabrador);
        mapLabels.put("Labrador",LabelLabrador);
        mapCircles.put("Terranova",CircleTerranova);
        mapLabels.put("Terranova",LabelTerranova);
        mapCircles.put("Nueva York",CircleNuevaYork);
        mapLabels.put("Nueva York",LabelNuevaYork);


    }

    public void atacar(){
        Pais atacante = mapPaises.get(CBAtacante.getValue());
        Pais defensor = mapPaises.get(CBDefensor.getValue());
        try {
            jugador1.atacarPaisDesde(atacante,defensor);
        } catch(PaisInvalidoException e){
            LabelErrores.setText("Pais inválido.");
        } catch(CantidadEjercitosInsuficienteException e){
            LabelErrores.setText("Cantidad de ejercitos insuficiente.");
        } catch(Exception e){
            LabelErrores.setText("ERROR");
        }

        mapLabels.get(atacante.getNombre()).setText(Integer.toString(atacante.getEjercitos().getCantidad()));
        mapLabels.get(defensor.getNombre()).setText(Integer.toString(defensor.getEjercitos().getCantidad()));
        mapCircles.get(defensor.getNombre()).setFill(defensor.getJugador().getColor());
    }




}
