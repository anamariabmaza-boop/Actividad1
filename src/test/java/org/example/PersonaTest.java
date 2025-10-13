package org.example;

import Persona.Exception.PersonaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PersonaTest {

    @Test
    public void create_AtributosCorrectos_Success() {
        Persona nuevaPersona = Persona.create(
                "Juan",
                "Perez",
                LocalDate.of(2000, 1, 1),
                "12.345.678",
                180.0f,
                80.5f
        );

        Assertions.assertNotNull(nuevaPersona);
        Assertions.assertEquals("Perez", nuevaPersona.getApellido());
        Assertions.assertEquals("Juan", nuevaPersona.getNombre());
        Assertions.assertEquals("12.345.678", nuevaPersona.getDni());
        Assertions.assertEquals(LocalDate.of(2000,1,1), nuevaPersona.getFechaNacimiento());
        Assertions.assertEquals(180.0f, nuevaPersona.getAltura());
        Assertions.assertEquals(80.5f, nuevaPersona.getPeso());
    }

    //BORRO TEST REDUNDANTES

@Test
    public void create_NombreApellidoDesprolijo_Normalizar() {
        Persona persona = Persona.create("aNa ", " MazA",
                LocalDate.of(2001,8,27), "43611357",
                166.0f, 75.0f);

        String nombreNormalizado = persona.getNombre();
        String apellidoNormalizado = persona.getApellido();

        Assertions.assertEquals("Ana", nombreNormalizado);
        Assertions.assertEquals("Maza", apellidoNormalizado);
    }

    //AGREGO TEST DE VALIDACION DE PERSONA
    @Test
    void create_conNombreNulo_DeberiaLanzarExcepcion() {
        PersonaException exception = Assertions.assertThrows(PersonaException.class, () -> {
            Persona.create(null,
                    "Maza",
                    LocalDate.now(),
                    "43611357",
                    166.0f,
                    75.0f);});
        Assertions.assertEquals("El nombre no puede ser nulo", exception.getMessage());
    }
    @Test
    void create_conApellidoNulo_DeberiaLanzarExcepcion() {
        PersonaException exception = Assertions.assertThrows(PersonaException.class, () -> {
            Persona.create("Ana",
                    null,
                    LocalDate.now(),
                    "43611357",
                    166.0f,
                    75.0f);});
        Assertions.assertEquals("El apellido no puede ser nulo", exception.getMessage());
    }
    @Test
    void create_conAlturaNegativa_deberiaLanzarPersonaException() {
        PersonaException exception = Assertions.assertThrows(PersonaException.class, () -> {
            Persona.create(
                    "Juan",
                    "Perez", LocalDate.now(),
                    "123",
                    -170f,
                    70f);
        });
        Assertions.assertEquals("La altura debe ser mayor a 0", exception.getMessage());
    }
    @Test
    void create_conPesoNegativo_deberiaLanzarPersonaException() {
        PersonaException exception = Assertions.assertThrows(PersonaException.class, () -> {
            Persona.create(
                    "Ana",
                    "Maza",
                    LocalDate.now(),
                    "123",
                    170f,
                    -70);
        });
        Assertions.assertEquals("El peso debe ser mayor a 0", exception.getMessage());
    }
    @Test
    void create_conFechaNacimientoFutura_deberiaLanzarPersonaException() {
        PersonaException exception = Assertions.assertThrows(PersonaException.class, () -> {
            Persona.create(
                    "Ana",
                    "Maza",
                    LocalDate.of(2026, 8, 27),
                    "123",
                    170f,
                    70);
        });
        Assertions.assertEquals("La fecha no debe ser futura", exception.getMessage());
    }

}

