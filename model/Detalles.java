package model;

import java.util.Date;

public class Detalles {

    private int idProducto;
    private int idDetalleProducto;
    private String marca;
    private Proveedor proveedor; 
    private String origen; // Importado o Nacional
    private Date fechaAgregado ;

    public Detalles() { }

    public int getIdProducto() {
        return idProducto;
    }

    public int getIdDetalleProducto() {	
    	return idDetalleProducto;
    }
    
    public void setIdDetalleProducto(int idDetalleProducto) {
    	this.idDetalleProducto = idDetalleProducto;
    }
    
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Date getFechaAgregado() {
        return fechaAgregado;
    }

    public void setFechaAgregado(Date fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }
}
