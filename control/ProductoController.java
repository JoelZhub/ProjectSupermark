package control;

import model.*;
import dao.ProductoDAO;

import java.util.ArrayList;
import java.util.List;


public class ProductoController {

    private ProductoDAO dao;

    public ProductoController(ProductoDAO dao) {
        this.dao = dao;
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
    
    @SuppressWarnings("null")
	public List<Producto> listarProductosStockBajo(){
    		List<Producto> productosBajo = new ArrayList<>();
    		 var list = dao.listar();
    		    for (Producto p : list) {
    		        if (p.getCantida() <= 10) {
    		            productosBajo.add(p);
    		        }
    		    }

    		    return productosBajo;
    }
    
    // campo para validar los elementos que se coloquen en el form -> si ya tienen otra logica implementada entonces quitar este metodo
    //y usar el que crearon.
    public boolean validarProductoInformacionBase(Producto prod) {
    	
    	if(prod.getNombre().length() < 3 || prod.getNombre()== null)  return false;
    	if(prod.getCantida() <= 0) return false;
    	if(prod.getActivo() > 2 ) return false;
    	if(prod.getCategoria() == null) return false;
    	if(prod.getPrecio() <= 0) return false;
    	if(prod.getUnidad().length() < 2 || prod.getUnidad() == null) return false;
    	return true;
    	
    }
    
  

    public String inhabilitarProducto(int id) {
        return dao.eliminar(id) ? "Producto inhabilitado correctamente." : "Producto no encontrado.";
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