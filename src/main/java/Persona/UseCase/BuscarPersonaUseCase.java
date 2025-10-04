package Persona.UseCase;

import Persona.Exception.PersonaNoEncontrada;
import Persona.Input.BuscarPersonaInput;
import Persona.Output.BuscarPersonaRepositorio;
import Persona.Output.GuardarPersonaRepositorio;
import org.example.Persona;

import java.util.Optional;
// En los test puebo los escenarios posibles, encontro una perona, no la encontro o falla el repo
public class BuscarPersonaUseCase implements BuscarPersonaInput {
 private BuscarPersonaRepositorio buscarPersonaRepositorio;

    public BuscarPersonaUseCase(BuscarPersonaRepositorio buscarPersonaRepositorio) {
        this.buscarPersonaRepositorio=buscarPersonaRepositorio;
    }

    @Override
    public Persona buscarPersona(String dni){
        Persona persona = buscarPersonaRepositorio.buscarPersona(dni);
        if (persona == null) {
            throw new PersonaNoEncontrada("Persona no encontrada");
        }
        return persona;
        //devuelve Optional.empty() si no existe
    }

}
//DEvuelve una perosina y recibe un dni
   /* public Persona buscarPersona(String dni){
     //Por que creo a la persona dentro del cu??
     //Si lo hago asi mezclo responsabilidades y rompo la separacion de responsabilidades
     //Puedo hacerlo con Optional<optional> (que puede o no existir)
     //es como una caja que puede o no contener un valor
     //es para evitar null
    if(buscarPersonaRepositorio.existePersona(dni)){
        return Persona.create("Ana", "Maza", java.time.LocalDate.of(2001,8,27),
                dni, 166.0f, 75.0f);

    }
    return null;
 }*/