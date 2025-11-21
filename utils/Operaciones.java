package utils;

import java.util.List;

public interface Operaciones<Clase> {
    boolean insertar(Clase obj);
    boolean editar(Clase obj);
    boolean eliminar(String id);
    Clase buscar(String id);
    List<Clase> listar();
}