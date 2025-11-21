package view.modules.products;

import control.ProductoController;
import model.Producto;

public class productoServicio {

	//este controlador es para poder validar los campos  de los formularios de creacion
	//edicion, eliminar, etc.  si ya se habia creado uno eliminar este y solamente pasarle los valores
	//que aqui se encuentren 
	
	private ProductoController productoC;
	
//	public  productoServicio(ProductoController producto) {
//		this.producto = producto;
//	}
//	
	public boolean validarCreacionProducto(Producto producto, String criterio) {
		if(criterio.equals("continuar")) {
			
				if(producto.getCategoria() == null) return false;
				if(producto.getCodigo().length() < 6 || producto.getCategoria() == null) return false;
				if(producto.getNombre() == null || producto.getNombre().length() < 3) return false;
				if(producto.getPrecio() == 0) return false;
				if(producto.getCantida() == 0) return false;
				if(producto.getUnidad() == null || producto.getUnidad().length() < 4) return false;
		}else {
			if(producto.getDetalles().getMarca() == null) return false;
			if(producto.getDetalles().getOrigen() == null) return false;
			if(producto.getDetalles().getProveedor() == null) return false;
			if(producto.getDetalles().getFechaAgregado() == null) return false;
		}
		
		return true;
	}
	
	public String crearPruducto(Producto producto) {	
		return productoC.agregar(producto);
	}
	
	
	

	
	
}

