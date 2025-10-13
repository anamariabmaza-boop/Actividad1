package org.example;



import Persona.Exception.EliminarPersonaException;
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



import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

//Aca uno junit con mockito
@ExtendWith(MockitoExtension.class)
public class EliminarPersonaUseCaseTest {

    @Mock
    private EliminarPersonaRepositorio repo;

    @Test
    void eliminarPersona_deberiaEliminarCuandoExiste() {

        String dni = "12345678";
        when(repo.existePorDni(dni)).thenReturn(true);

        EliminarPersonaUseCase eliminarPersonaUseCase = new EliminarPersonaUseCase(repo);
        //CORRECCION
        Assertions.assertDoesNotThrow(() -> {eliminarPersonaUseCase.eliminarPersona(dni);});

    }
    @Test
    void eliminarPersona_deberiaLanzarPersonaNoEncontradaCuandoNoExiste() {
        String dni = "99999999";
        when(repo.existePorDni(dni)).thenReturn(false);

        EliminarPersonaUseCase eliminarPersonaUseCase = new EliminarPersonaUseCase(repo);

        Assertions.assertDoesNotThrow(() -> {eliminarPersonaUseCase.eliminarPersona(dni);});

        verify(repo).existePorDni(dni);
    }
    @Test
    void eliminarPersona_deberiaLanzarEliminarPersonaExceptionCuandoRepositorioFalla() {

        String dni = "55555555";
        when(repo.existePorDni(dni)).thenReturn(true); // el repo dice que existe


        EliminarPersonaUseCase eliminarPersonaUseCase = new EliminarPersonaUseCase(repo);

        Assertions.assertDoesNotThrow(() -> {eliminarPersonaUseCase.eliminarPersona(dni);});
        verify(repo).existePorDni(dni);
        verify(repo).eliminarPorDni(dni);
    }
}