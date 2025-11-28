package control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import dao.FacturaDAO;
import dao.ProductoDAO;
import model.Factura;
import utils.ValidacionFecha;

public class FacturaController {

    private FacturaDAO facturaDAO;
    @SuppressWarnings("unused")
	private ProductoDAO productoDAO;

    public FacturaController( FacturaDAO facturaDAO,  ProductoDAO productoDAO) {
        this.facturaDAO =facturaDAO;
        this.productoDAO = productoDAO;
    }

    public List<Factura> listarFacturas() {
        return facturaDAO.listar();
    }

    public String agregarFactura(String metodoPago, int clienteId, int cajeroId) {

        // al crearse la factura el sistema toma la fecha y hora actual y las junta en un String para guardarlas en la base de datos
        // esto es porque el tipo de dato Date de java no es compatible con MySQL
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now().withNano(0);
        String fechaHora = fecha.toString() + " " + hora.toString();

        Factura f = new Factura(fechaHora, metodoPago, clienteId, cajeroId);

        return facturaDAO.insertar(f) ? "Factura creada correctamente." : "Error al crear factura.";
    }

    public String eliminarFactura(int id) {
        return facturaDAO.eliminar(id) ? "Factura eliminada." : "Factura no encontrada.";
    }

    public String editar(int id, String fecha, String metodoPago, int clienteId, int cajeroId) {
        if (fecha == null) return "Error: fecha nula.";

        //esto es para verificar que la fecha ingresada tenga el formato yyyy-MM-dd HH:mm:ss ya que el tipo de dato Date de java no es compatible con mysql
        //por tanto tiene que guardarse como String
        if (!ValidacionFecha.isValidTimestamp(fecha)) {
            return "Error: La fecha debe tener el formato yyyy-MM-dd HH:mm:ss y ser válida.";
        }

        Factura f = new Factura(id, fecha, metodoPago, clienteId, cajeroId);
        return facturaDAO.editar(f) ? "Factura actualizada." : "Factura no encontrada.";
    }

    public Factura buscar(int id) {
        return facturaDAO.buscar(id);
    }

    //productos de factura

    public String agregarProducto(int idFactura, String idProducto, int cantidad) {
        
        if (!facturaDAO.agregarProducto(idFactura, idProducto, cantidad)){
        return "Error al agregar producto a la factura.";
    }

        Factura factura = facturaDAO.buscar(idFactura);

        if (factura != null) {
            double nuevoTotal = facturaDAO.calcularTotal(idFactura);
            factura.setTotal(nuevoTotal);
        }

        return "Producto agregado correctamente.";
        
    }

    public String editarCantidad(int idFactura, String idProducto, int cantidad) {
        return facturaDAO.actualizarCantidad(idFactura, idProducto, cantidad) ? "Cantidad actualizada." : "Error actualizando.";
    }

    public String eliminarProducto(int idFactura, String idProducto) {
        return facturaDAO.eliminarProducto(idFactura, idProducto) ? "Producto eliminado." : "Error eliminando.";
    }

}
