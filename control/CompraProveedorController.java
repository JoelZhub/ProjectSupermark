package control;

import dao.CompraProveedorDAO;
import model.CompraProveedor;

import java.util.List;

public class CompraProveedorController {

    private CompraProveedorDAO dao;

    public CompraProveedorController() {
        dao = new CompraProveedorDAO();
    }

    public List<CompraProveedor> listarCompras() {
        return dao.listar();
    }

    public String agregarCompra(int idProducto, int idProveedor, int cantidad, String fecha, double precio) {
        CompraProveedor c = new CompraProveedor(idProducto, idProveedor, cantidad, fecha, precio);
        return dao.insertar(c) ? "Compra registrada." : "Error registrando compra.";
    }

    public String editarCompra(CompraProveedor c) {
        return dao.editar(c) ? "Compra actualizada." : "Error actualizando compra.";
    }

    public String eliminarCompra(int id) {
        return dao.eliminar(id) ? "Compra eliminada." : "Error eliminando compra.";
    }

    public CompraProveedor buscarCompra(int id) {
        return dao.buscar(id);
    }
}