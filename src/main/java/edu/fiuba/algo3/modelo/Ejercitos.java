package edu.fiuba.algo3.modelo;

public class Ejercitos {

    int cantidad = 1;
    Jugador jugador;

    Ejercitos() {}

    public int getCantidad() {
        return cantidad;
    }

    public boolean noQuedanEjercitos() {
        return (cantidad == 0);
    }

    public void agregarEjercitos(int unaCantidad) {
        cantidad += unaCantidad;
    }

    public void transferirEjercitos(Pais paisDestino, int unaCantidad) {
        if (cantidad > unaCantidad) {
            quitarEjercitos(unaCantidad);
            paisDestino.colocarEjercitos(unaCantidad);
        }
    }

    public boolean cantidadEjercitosSuperiorA(int unaCantidad) {
        return (cantidad > unaCantidad);
    }

    public void quitarEjercitos(int unaCantidad) {
        if (cantidad >= unaCantidad)
            cantidad -= unaCantidad;
    }

    public Dados calcularDados() {
        int cantidad_dados;
        if (cantidad > 3) cantidad_dados = 3;
        else cantidad_dados = cantidad-1;
        return (new Dados(cantidad_dados));
    }

    // Metodo a llamar cuando el pais defensor queda con cero ejercitos en una batalla
    // Cambia el dueño de los ejercitos y le transfiere un ejercito
    public void conquistarA(Pais paisEnemigo) {
        Ejercitos ejercitosEnemigos = paisEnemigo.getEjercitos();
        ejercitosEnemigos.asignarJugador(jugador);
        transferirEjercitos(paisEnemigo, 1);
    }

    public void asignarJugador(Jugador unJugador) {
        jugador = unJugador;
    }
}

