package Persona.Output;

import Persona.Exception.RespositorioException;

public interface EliminarPersonaRepositorio {
    boolean existePorDni(String dni);
    //true si la persona existe
    void eliminarPorDni(String dni) throws RespositorioException;//Puede lanzar RespositorioException si falla
    //elimina la persona por dni
}
