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

    public String agregar(Producto producto) {
        try {
            if (dao.buscar(producto.getCodigo()) != null)
                return "Error: Ya existe un producto con ese código.";

            return dao.insertar(producto) ? "Producto agregado" : "Error al agregar";

        } catch (IllegalArgumentException e) {
            return "Categoría inválida.";
        }
    }

    public String eliminar(int id) {
        return dao.eliminar(id) ? "Producto eliminado correctamente." : "Producto no encontrado.";
    }

    public String editar(Producto producto) {
        try {
            return dao.editar(producto) ? "Actualizado" : "No encontrado";

        } catch (IllegalArgumentException e) {
            return "Categoría inválida.";
        }
    }
    
    public Producto buscar(int id) {
        return dao.buscar(id);
    }
}