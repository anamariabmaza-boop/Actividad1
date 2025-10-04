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
        // doNothing() es el comportamiento por defecto para void, lo dejamos explicito:
        //no pasa nada cuando llamo a un metodo void
        //doNothing().when(repo).eliminarPorDni(dni);

        EliminarPersonaUseCase useCase = new EliminarPersonaUseCase(repo);

        try {
            useCase.eliminarPersona(dni);
        } catch (Exception e) {
            fail("No se esperaba ninguna excepción, pero se lanzó: " + e.getMessage());
        }

        verify(repo).existePorDni(dni);
        verify(repo).eliminarPorDni(dni);
    }
    @Test
    void eliminarPersona_deberiaLanzarPersonaNoEncontradaCuandoNoExiste() {
        String dni = "99999999";
        when(repo.existePorDni(dni)).thenReturn(false);

        EliminarPersonaUseCase useCase = new EliminarPersonaUseCase(repo);

        try {
            useCase.eliminarPersona(dni);
        } catch (PersonaNoEncontrada e) {
            Assertions.assertEquals("99999999", e.getMessage());
        }

        verify(repo).existePorDni(dni);
    }
    @Test
    void eliminarPersona_deberiaLanzarEliminarPersonaExceptionCuandoRepositorioFalla() {

        String dni = "55555555";
        when(repo.existePorDni(dni)).thenReturn(true); // el repo dice que existe


        EliminarPersonaUseCase useCase = new EliminarPersonaUseCase(repo);

        try {
            useCase.eliminarPersona(dni);

        } catch (EliminarPersonaException e) {
            Assertions.assertEquals("Error al eliminar persona con DNI: 55555555", e.getMessage());
        }

        verify(repo).existePorDni(dni);
        verify(repo).eliminarPorDni(dni);
    }
}