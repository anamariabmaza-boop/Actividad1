package Persona.UseCase;

import Persona.Input.EliminarPersonaInput;
import Persona.Output.EliminarPersonaRepositorio;
import Persona.Exception.PersonaNoEncontrada;
import Persona.Exception.EliminarPersonaException;
import Persona.Exception.RespositorioException;

public class EliminarPersonaUseCase implements EliminarPersonaInput {
    private final EliminarPersonaRepositorio repo;

    public EliminarPersonaUseCase(EliminarPersonaRepositorio repo) {
        this.repo = repo;
    }

    @Override
    public void eliminarPersona(String dni) {
        if(dni == null || dni.isBlank()){
            throw new PersonaNoEncontrada("DNI no puede ser nulo");
        }
        if (!repo.existePorDni(dni)) {
            throw new PersonaNoEncontrada(dni);
        }

        try {
            repo.eliminarPorDni(dni);
        } catch (RespositorioException e) {
            throw new EliminarPersonaException("Error al eliminar persona con DNI: " + dni);
        }
    }
}
