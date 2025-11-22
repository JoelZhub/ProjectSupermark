package control;
import model.*;
import utils.Operaciones;

import java.util.ArrayList;
import java.util.List;


public class ProductoController implements Operaciones {

    private List<Producto> productos;

    public ProductoController() {
        this.productos = new ArrayList<>();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public String agregar(Object obj) {
        if (!(obj instanceof Producto)) {
            return "Error: El objeto no es un producto válido.";
        }

        Producto producto = (Producto) obj;

        if (existeProducto(producto.getCodigo())) {
            return "Error: Ya existe un producto con ese código.";
        }

        productos.add(producto);
        return "Producto agregado correctamente. ID: " + producto.getCodigo();
    }

    @Override
    public String eliminar(String id) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(id)) {
                productos.remove(p);
                return "Producto eliminado correctamente.";
            }
        }
        return "Producto no encontrado.";
    }

    @Override
    public String editar(String id, Object nuevosDatos) {
        if (!(nuevosDatos instanceof Producto)) {
            return "Error: Datos inválidos para edición.";
        }

        Producto nuevoProducto = (Producto) nuevosDatos;

        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equals(id)) {
                productos.set(i, nuevoProducto);
                return "Producto actualizado correctamente.";
            }
        }
        return "Producto no encontrado.";
    }

    @Override
    public Object buscar(String id) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public boolean existeProducto(String codigo) {
        return productos.stream().anyMatch(p -> p.getCodigo().equals(codigo));
    }

    public List<Producto> listarProductos() {
        return productos;
    }
}
