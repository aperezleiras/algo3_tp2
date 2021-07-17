package edu.fiuba.algo3.modelo;

public class CartaPais {

    private Pais pais;
    private String simbolo; // todo: pensar si deberiamos tener una clase simbolo

    public CartaPais(Pais unPais, String unSimbolo){
        pais = unPais;
        simbolo = unSimbolo;
    }
}
