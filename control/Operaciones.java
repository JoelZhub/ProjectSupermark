package control;

public interface Operaciones {
    String agregar(Object obj);
    String eliminar(String id);
    String editar(String id, Object nuevosDatos);
    Object buscar(String id);
}