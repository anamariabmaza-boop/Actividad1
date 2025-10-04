package org.example.data;

import Persona.Output.GuardarPersonaRepositorio;
import org.example.Persona;

public class GuardarPersonaRepo implements GuardarPersonaRepositorio {

    @Override
    public boolean guardarPersona(Persona persona) {
        return false;
    }

    @Override
    public boolean existePersona(String dni) {
        return false;
    }
}
