package org.example;

import Persona.Exception.PersonaNoEncontrada;
import Persona.Exception.RespositorioException;
import Persona.Input.CrearPersonaInput;
import Persona.Output.GuardarPersonaRepositorio;
import Persona.UseCase.CrearPersonaUseCase;
import Persona.UseCase.EliminarPersonaUseCase;
import Persona.Output.EliminarPersonaRepositorio;
import Persona.Input.BuscarPersonaInput;
import Persona.Output.BuscarPersonaRepositorio;
import Persona.UseCase.BuscarPersonaUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

//Aca uno junit con mockito
@ExtendWith(MockitoExtension.class)

public class BuscarPersonaUseCaseTest {
    @Mock
//Ahora decir que dependecias debo mockear
//Que serian los outputs
    BuscarPersonaRepositorio repositorio;

    @Test
    public void buscarPersonaDniTest() {

        //Arrange
        Persona p1 = Persona.create("Ana", "Maza", LocalDate.of(2001,8,27),
                "43611357",166.0f,  75.0f);

        when(repositorio.buscarPersona("43611357")).thenReturn(p1);

        BuscarPersonaInput buscarPersonaInput = new BuscarPersonaUseCase(repositorio);
        Persona encontrada = buscarPersonaInput.buscarPersona("43611357");
        //CORRECCION
        Assertions.assertEquals("43611357", encontrada.getDni());

    }
    @Test
    public void buscarPersonaPorDni_NoExisteLanzaExcepcion() {
        String dni = "99999999";
        when(repositorio.buscarPersona(dni)).thenReturn(null);

        BuscarPersonaInput buscarPersonaInput = new BuscarPersonaUseCase(repositorio);
        //MODIFICACION
        Assertions.assertThrows(PersonaNoEncontrada.class, () -> {buscarPersonaInput.buscarPersona(dni);});

    }
    //NUEVO TEST
    @Test
    public void buscarPersonaPorDni_RepositorioExcepcion() {
        String dni = "99999999";
        when(repositorio.buscarPersona(dni)).thenThrow(new RespositorioException("Algo Salio Mal"));

        BuscarPersonaInput  buscarPersonaInput = new BuscarPersonaUseCase(repositorio);

        RespositorioException exception = Assertions.assertThrows(RespositorioException.class, () -> {buscarPersonaInput.buscarPersona(dni);});
        Assertions.assertEquals("Algo Salio Mal", exception.getMessage());
    }
}
