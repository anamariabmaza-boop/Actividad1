package Persona.Input;
import org.example.Persona;

import java.time.LocalDate;
public interface CrearPersonaInput {
    Boolean crearPersona(String nombre,
                         String apellido,
                         String dni,
                         LocalDate fechaNacimiento,
                         float altura,
                         float peso);
}
