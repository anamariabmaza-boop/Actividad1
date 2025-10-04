package Persona.Output;
import org.example.Persona;
import java.time.LocalDate;
public interface GuardarPersonaRepositorio {
    boolean guardarPersona(Persona persona);
    boolean existePersona(String dni);

}
