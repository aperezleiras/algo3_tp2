package edu.fiuba.algo3.exception;

public class CantidadEjercitosInsuficienteException extends RuntimeException{
    public CantidadEjercitosInsuficienteException() {
        super("No tiene suficientes ejercitos");
    }
}
