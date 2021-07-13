package edu.fiuba.algo3.exception;

public class CantidadEjercitosInsuficienteException extends Exception{
    public CantidadEjercitosInsuficienteException() {
        super("No tiene suficientes ejercitos");
    }
}
