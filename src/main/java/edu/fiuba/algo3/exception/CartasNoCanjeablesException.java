package edu.fiuba.algo3.exception;

public class CartasNoCanjeablesException extends RuntimeException {
    public CartasNoCanjeablesException() { super("Esas cartas no son canjeables"); }
}
