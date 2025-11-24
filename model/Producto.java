package model;

public class Producto {

    private int idProducto;
    private String nombre;
    private double precio;
    private Categoria categoria;
    private int cantidad;
    private String unidad;
    private Oferta oferta;
    private Detalles detalles;

    public Producto(String nombre, double precio, Categoria categoria, int cantidad, String unidad){
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }


    public Producto(int idProducto, String nombre, double precio, Categoria categoria, int cantidad, String unidad){
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    public int getCodigo() {
        return idProducto;
    }

    public void setCodigo(int idProducto) {
        this.idProducto = idProducto;
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
}