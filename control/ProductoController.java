package control;

import model.*;
import dao.ProductoDAO;
import java.util.List;


public class ProductoController {

    private ProductoDAO dao;

    public ProductoController() {
        this.dao = new ProductoDAO();
    }

    public List<Producto> listarProductos() {
        return dao.listar();
    }

    public String agregar(int id, String nombre, double precio, String cat, int cant, String unidad) {
        try {
            Categoria categoria = Categoria.valueOf(cat.toUpperCase());
            Producto p = new Producto(id, nombre, precio, categoria, cant, unidad);

            if (dao.buscar(id) != null)
                return "Error: Ya existe un producto con ese código.";

            return dao.insertar(p) ? "Producto agregado" : "Error al agregar";

        } catch (IllegalArgumentException e) {
            return "Categoría inválida.";
        }
    }

    public String eliminar(int id) {
        return dao.eliminar(id) ? "Producto eliminado correctamente." : "Producto no encontrado.";
    }

    public String editar(int id, String nombre, double precio, String cat, int cant, String unidad) {
        try {
            Categoria categoria = Categoria.valueOf(cat.toUpperCase());
            Producto p = new Producto(id, nombre, precio, categoria, cant, unidad);
            return dao.editar(p) ? "Actualizado" : "No encontrado";

        } catch (IllegalArgumentException e) {
            return "Categoría inválida.";
        }
    }
    
    public Producto buscar(int id) {
        return dao.buscar(id);
    }
}