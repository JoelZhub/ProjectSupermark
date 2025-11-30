package model;

public class CompraProveedor {
    private int idCompra;
    private int idProducto;
    private int idProveedor;
    private int cantidad;
    private String fecha;
    private double precio;

    public CompraProveedor(int idProducto, int idProveedor, int cantidad, String fecha, double precio) {
        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.precio = precio;
    }

    public CompraProveedor(int idCompra, int idProducto, int idProveedor, int cantidad, String fecha, double precio) {
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.precio = precio;
    }

    public int getIdCompra() { return idCompra; }
    public void setIdCompra(int idCompra) { this.idCompra = idCompra; }
    public int getIdProducto() { return idProducto; }
    public int getIdProveedor() { return idProveedor; }
    public int getCantidad() { return cantidad; }
    public String getFecha() { return fecha; }
    public double getPrecio() { return precio; }
}