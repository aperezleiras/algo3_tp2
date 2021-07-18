package edu.fiuba.algo3.exception;

public class CartaYaActivadaException extends RuntimeException {
    public CartaYaActivadaException() { super("Esta carta ya fue activada"); }
}
