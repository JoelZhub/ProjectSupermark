package model;

public class Producto {

    private String idProducto;
    private String nombre;
    private double precio;
    private Categoria categoria;
    private int cantidad;
    private String unidad; // Unitario o Lb
    private Oferta oferta; // Puede ser null
    private Detalles detalles;

    public Producto(String idProducto, String nombre, double precio, Categoria categoria, int cantidad, String unidad){
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

   

	public String getCodigo() {
        return idProducto;
    }

    public void setCodigo(String codigo) {
        this.idProducto = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public void setUnidad(String unidad) {
        this.unidad = unidad;
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