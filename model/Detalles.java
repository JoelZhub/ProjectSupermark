package model;

import java.util.Date;

public class Detalles {

    private int idProducto;
    private int idDetalleProducto;
    private String marca;
    
    @SuppressWarnings("unused")
	private int idProveedor;  // este campo lo agregue para poder crear el objecto en
    //el dao de productos, es decir los detalles del producto que se busco
    // o en su defecto en la lista, ya que sino lo ponia de esta manera  
    //y va a tener que  crear instancias de objectos en  el dao y no seria modular esa parte
    
    private Proveedor proveedor; 
    private String origen; // Importado o Nacional
    private Date fechaAgregado ;

    public Detalles(int idProducto, String marca, Proveedor proveedor, String origen) {
    	this.idProducto = idProducto;
    	this.marca = marca;
    	this.proveedor = proveedor;
    	this.origen = origen;
    }

    public Detalles(int idDetalleProducto,int idProducto, String marca, int idProveedor, String origen, Date fechaAgreadado) {
    	this.idProducto = idProducto;
    	this.idDetalleProducto = idDetalleProducto;
    	this.marca = marca;
    	this.idProveedor = idProveedor;
    	this.origen = origen;
    	this.fechaAgregado= fechaAgreadado;
    	
    }
    
    public Detalles(int idDetalleProducto,int idProducto, String marca, Proveedor proveedor, String origen, Date fechaAgreadado) {
    	this.idProducto = idProducto;
    	this.idDetalleProducto = idDetalleProducto;
    	this.marca = marca;
    	this.proveedor = proveedor;
    	this.origen = origen;
    	this.fechaAgregado= fechaAgreadado;
    	
    }
    
  
    public int getIdProveedor() {
    	return idProveedor;
    }
    
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
