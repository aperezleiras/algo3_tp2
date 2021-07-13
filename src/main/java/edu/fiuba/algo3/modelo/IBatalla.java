package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exception.CantidadEjercitosInsuficienteException;
import edu.fiuba.algo3.exception.PaisNoLimitrofeException;

public interface IBatalla {

    void realizarAtaque() throws PaisNoLimitrofeException, CantidadEjercitosInsuficienteException;
}
