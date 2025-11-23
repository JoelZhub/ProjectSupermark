package utils;

import java.util.List;

public interface Operaciones<Clase> {
    boolean insertar(Clase obj);
    boolean editar(Clase obj);
    boolean eliminar(int id);
    Clase buscar(int id);
    List<Clase> listar();
}