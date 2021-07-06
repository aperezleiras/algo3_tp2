package edu.fiuba.algo3.modelo;

public class Ejercitos {

    int cantidad = 1;
    Jugador jugador;

    Ejercitos(){
    }

    public void agregarEjercitos(int unaCantidad) {
        cantidad += unaCantidad;
    }

    public void transferirEjercitos(Pais paisDestino, int unaCantidad) {
        if(unaCantidad-1 <= cantidad){
            quitarCantidad(unaCantidad);
            paisDestino.colocarEjercitos(unaCantidad);
        }
    }

    public boolean tieneCantidad(int unaCantidad) {
        return (cantidad >= unaCantidad);
    }

    public void quitarCantidad(int unaCantidad) {
        cantidad -= unaCantidad;
    }

    public Dados calcularDados(){
        int cantidad_dados;
        if(cantidad > 3): cantidad_dados = 3;
        else cantidad_dados = cantidad-1;
        return(new Dados(cantidad_dados));
    }
}

