package org.example;

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

    @Test
    public void getApellido_DeberiaDevolverApellidoCorrecto() {
        Persona persona = Persona.create(
                "Ana",
                "Maza",
                LocalDate.of(1999, 5, 15),
                "99.999.999",
                165.0f,
                60.0f
        );

        Assertions.assertEquals("Maza", persona.getApellido());
    }

    @Test
    public void getNombre_DeberiaDevolverNombreCorrecto() {
        Persona persona = Persona.create(
                "Carlos",
                "Lopez",
                LocalDate.of(1985, 3, 10),
                "11.111.111",
                175.0f,
                70.0f
        );

        Assertions.assertEquals("Carlos", persona.getNombre());
    }
    @Test
    public void getDni_DeberiaDevolverDniCorrecto() {
        Persona p1 = Persona.create( "Carlos", "Lopez",
                LocalDate.of(1985, 3, 10), "55.555.555"
        ,175.0f, 70.0f);
        Persona p2 = Persona.create("Ana", "Maria",
                 LocalDate.of(2001, 8, 27),
                "43.611.357", 166.0f, 75.0f);

        Assertions.assertEquals("55.555.555", p1.getDni());
        Assertions.assertEquals("43.611.357", p2.getDni());
    }
@Test
    public void getAltura_DeberiaDevolverAlturaCorrecto() {
        Persona p1 = Persona.create("Ana", "Maza"
        , LocalDate.of(2001, 8, 27), "43611357",
                166.0f, 75.0f);
        Assertions.assertEquals(166.0f, p1.getAltura());

}

@Test
    public void create_NombreDesprolijo_Normalizar() {
        Persona persona = Persona.create("aNa ", " MazA",
                LocalDate.of(2001,8,27), "43611357",
                166.0f, 75.0f);

        String nombreNormalizado = persona.getNombre();
        String apellidoNormalizado = persona.getApellido();

        Assertions.assertEquals("Ana", nombreNormalizado);
        Assertions.assertEquals("Maza", apellidoNormalizado);
    }

}

