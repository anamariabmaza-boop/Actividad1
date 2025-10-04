package org.example;

import Persona.Exception.PersonaNoEncontrada;
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
    public void buscarPersnaDniTest() {

        //Arrange
        Persona p1 = Persona.create("Ana", "Maza", LocalDate.of(2001,8,27),
                "43611357",166.0f,  75.0f);
        // Persona.create: metodo estatico de fabrica que devuelve un objeto Persona con los campos que le pase
        //sirve para crear objetos de prueba de forma rapida y clara

        when(repositorio.buscarPersona("43611357")).thenReturn(p1);
        //Ya configure el comportamiento del el metodo que mi cu usa
        //Ahi falseaste la dependencia que tu caso de uso implemento/ uso

        //Ahora hay que inyectar la dependecia mockeada al C.U.
        BuscarPersonaInput buscarPersonaInput = new BuscarPersonaUseCase(repositorio);

        //Act
        //llamo al cu
        //Ahora debo llamar a mi cu de mi input y le paso el p;arametro

        Persona encontrada = buscarPersonaInput.buscarPersona("43611357");

        //Assert

        Assertions.assertNotNull(encontrada.getDni());
        Assertions.assertEquals("43611357", encontrada.getDni());
        //Verifico que el mock haya sido usado
        //verify(repositorio).existePersona("43611357");
        //Ver verify y otros
        verify(repositorio).buscarPersona("43611357");

    }
    @Test
    public void buscarPersonaPorDni_NoExisteLanzaExcepcion() {
        String dni = "99999999";
        when(repositorio.buscarPersona(dni)).thenReturn(null);

        BuscarPersonaInput buscarPersonaInput = new BuscarPersonaUseCase(repositorio);

        try {
            buscarPersonaInput.buscarPersona(dni);
        }catch (PersonaNoEncontrada e){
            Assertions.assertEquals("Persona no encontrada", e.getMessage());
        }
        verify(repositorio).buscarPersona(dni);
    }


}
