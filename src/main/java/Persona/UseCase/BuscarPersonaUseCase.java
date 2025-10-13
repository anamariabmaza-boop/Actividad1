package Persona.UseCase;

import Persona.Exception.PersonaNoEncontrada;
import Persona.Input.BuscarPersonaInput;
import Persona.Output.BuscarPersonaRepositorio;
import Persona.Output.GuardarPersonaRepositorio;
import org.example.Persona;

import java.util.Optional;
// En los test pruebo los escenarios posibles, encontro una persona, no la encontro o falla el repo
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
    }

}
