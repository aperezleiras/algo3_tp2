package edu.fiuba.algo3.modelo;

public class Juego {
    Jugador jugador1 = new Jugador(1);
    Jugador jugador2 = new Jugador(2);

    Pais argentina = new Pais("Argentina");
    Pais brasil = new Pais("Brasil");


    Juego(){
        /* Setup básico de un juego con 2 jugadores y 2 países */
        jugador1.asignarPais(argentina);
        jugador2.asignarPais(brasil);
        argentina.agregarLimitrofe(brasil);
        brasil.agregarLimitrofe(argentina);

        jugador1.colocarEjercitos(3, argentina);
        jugador2.colocarEjercitos(2, brasil);
    }

}
