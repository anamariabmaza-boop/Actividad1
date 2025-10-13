package org.example;
import Persona.Exception.PersonaException;
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

import java.net.http.WebSocketHandshakeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

//Aca uno junit con mockito
@ExtendWith(MockitoExtension.class)
public class CrearPersonaUseCaseTest {

    @Mock
    GuardarPersonaRepositorio guardarPersonaRepositorio;
    @Test
    public void crearPersonaFlujoNormalTest() {
        // Arrange
        String nombre = "Ana";
        String apellido = "Maza";
        String dni = "43611357";
        LocalDate fechaNacimiento = LocalDate.of(2001, 8, 27);
        float altura = 175.0f;
        float peso = 70.0f;

        when(guardarPersonaRepositorio.existePersona(dni)).thenReturn(false);
        when(guardarPersonaRepositorio.guardarPersona(any(Persona.class))).thenReturn(true);

        CrearPersonaInput crearPersonaInput = new CrearPersonaUseCase(guardarPersonaRepositorio);

        boolean resultado = crearPersonaInput.crearPersona(nombre, apellido, dni, fechaNacimiento, altura, peso);

        Assertions.assertTrue(resultado);

        verify(guardarPersonaRepositorio).existePersona(dni);
        verify(guardarPersonaRepositorio).guardarPersona(any(Persona.class));
    }
    @Test
    public void crearPersonaCuandoYaExisteTest() {
        String nombre = "Ana";
        String apellido = "Maza";
        String dni = "43611357";
        LocalDate fechaNacimiento = LocalDate.of(2001, 8, 27);
        float altura = 166.0f;
        float peso = 75.0f;

        when(guardarPersonaRepositorio.existePersona(dni)).thenReturn(true);

        CrearPersonaInput crearPersonaInput = new CrearPersonaUseCase(guardarPersonaRepositorio);

        Assertions.assertThrows(PersonaException.class, () -> {crearPersonaInput.crearPersona(nombre, apellido
        , dni, fechaNacimiento, altura, peso);});
    }

    @Test
    public void crearPersonaConNombreVacioTest() {
        // Arrange
        String nombre = "";  //nombre vacio
        String apellido = "Silva";
        String dni = "99887766";
        LocalDate fechaNacimiento = LocalDate.of(1975, 12, 5);
        float altura = 165.0f;
        float peso = 60.0f;

        CrearPersonaInput crearPersonaInput = new CrearPersonaUseCase(guardarPersonaRepositorio);
        //Assertions.assertThrows(PersonaException.class, ()->{crearPersonaInput.crearPersona(nombre, apellido, dni, fechaNacimiento, altura, peso);});
        PersonaException escepcion = Assertions.assertThrows(PersonaException.class, ()->{crearPersonaInput.crearPersona(nombre, apellido, dni, fechaNacimiento, altura, peso);});
        Assertions.assertEquals("El nombre no puede estar vacio", escepcion.getMessage());
    }
    @Test
    public void crearPersonaConFechaNacimientoFuturaTest() {
        // Arrange
        String nombre = "Lucia";
        String apellido = "Martinez";
        String dni = "44332211";
        LocalDate fechaNacimiento = LocalDate.now().plusDays(1);  // fecha futura
        float altura = 160.0f;
        float peso = 55.0f;

        CrearPersonaInput crearPersonaInput = new CrearPersonaUseCase(guardarPersonaRepositorio);
        PersonaException exception = Assertions.assertThrows(PersonaException.class, () -> {crearPersonaInput.crearPersona(nombre, apellido, dni, fechaNacimiento, altura, peso);});
        Assertions.assertEquals("La fecha no debe ser futura", exception.getMessage());
        //Aca si es necesario en verify porque no hay un when
        verify(guardarPersonaRepositorio).existePersona(dni);
        }

        //AGREGO NUEVO TEST
    @Test
    public void errorAlGuardarEnRepositorioTest() {
        String nombre = "Lucia";
        String apellido = "Martinez";
        String dni = "44332211";
        LocalDate fechaNacimiento = LocalDate.of(1975, 12, 5);
        float altura = 160.0f;
        float peso = 55.0f;

        when(guardarPersonaRepositorio.existePersona(dni)).thenReturn(false);
        when(guardarPersonaRepositorio.guardarPersona(any(Persona.class))).thenReturn(false);

        CrearPersonaInput crearPersonaInput = new CrearPersonaUseCase(guardarPersonaRepositorio);

        RespositorioException exception = Assertions.assertThrows(RespositorioException.class,
                ()->{crearPersonaInput.crearPersona(nombre, apellido, dni, fechaNacimiento, altura, peso);});
        Assertions.assertEquals("Error al guardar persona", exception.getMessage());
    }

}
