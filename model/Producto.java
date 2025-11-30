package model;

import java.util.Objects;

public class Producto {

    private int idProducto;
    private String nombre;
    private double precio;
    private Categoria categoria;
    private int cantidad;
    private String unidad;
    private Oferta oferta;
    private Detalles detalles;
    private int activo;
    
    public Producto(String nombre, double precio, Categoria categoria, int cantidad, String unidad){
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    
    
    
    public Producto(int idProducto, String nombre, double precio, Categoria categoria, int cantidad,
    		String unidad, int activo){    	
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.activo = activo;
        
    }

    public int getCodigo() {
        return idProducto;
    }

    public void setCodigo(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public int getActivo() {
    	return activo;
    }
    public void setActivo(int activo) {
    	this.activo = activo;	
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getCantida() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    
    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public Detalles getDetalles() {
        return detalles;
    }

    public void setDetalles(Detalles detalles) {
        this.detalles = detalles;
    }

    public boolean verificarOferta() {
        if (oferta == null) return false;
        return oferta.activa();
    }
    
    //este lo agregue para la seleccion del producto, tanto para la factura como al momento de crear las ofertas y eso
    //internamente se busca por el id como estaba definido pero  a la vista el usuario selecciona por nombre y de ahi entonces 
    //hace al operacion pertinente
    public String toString() {
    	return nombre;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Producto producto = (Producto) obj;
        return idProducto == producto.idProducto; 
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }
}