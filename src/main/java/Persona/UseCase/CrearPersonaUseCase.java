package Persona.UseCase;
import Persona.Exception.PersonaException;
import Persona.Exception.RespositorioException;
import Persona.Input.CrearPersonaInput;
import Persona.Output.GuardarPersonaRepositorio;
import org.example.Persona;

import java.time.LocalDate;

public class CrearPersonaUseCase implements CrearPersonaInput {
    private GuardarPersonaRepositorio guardarPersonaRepositorio;

    public CrearPersonaUseCase(GuardarPersonaRepositorio guardarPersonaRepositorio) {
        this.guardarPersonaRepositorio = guardarPersonaRepositorio;
    }

    //CORRECCION
    @Override
    public boolean crearPersona(String nombre, String apellido,
                                String dni, LocalDate fechaNacimiento,
                                float altura, float peso) {

        Persona persona = Persona.create(nombre, apellido, fechaNacimiento, dni, altura, peso);
        if(this.guardarPersonaRepositorio.existePersona(dni)){
            throw new PersonaException("Algo salio mal");
        }
        if(!this.guardarPersonaRepositorio.guardarPersona(persona)){
            throw new RespositorioException("Error al guardar persona");
        }
        return true;

    }

}
