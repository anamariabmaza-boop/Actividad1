package Persona.Input;
import org.example.Persona;

import java.time.LocalDate;
//Cualquier clase que implemente CrearPersonaInput debe tener un metodo
//llamado crearPersona que reciba los datos de una persona y devuelva un Boolean
public interface CrearPersonaInput {
    boolean crearPersona(String nombre,
                         String apellido,
                         String dni,
                         LocalDate fechaNacimiento,
                         float altura,
                         float peso);
}
