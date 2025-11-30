package model;

public class RegistroVentaDiaria {
    private String nombreProducto;
    private double precio;
    private int cantidadVendida;
    private double total;

    public RegistroVentaDiaria(String nombreProducto, double precio, int cantidadVendida) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.cantidadVendida = cantidadVendida;
        this.total = precio * cantidadVendida;
    }

    public String getNombreProducto() { return nombreProducto; }
    public double getPrecio() { return precio; }
    public int getCantidadVendida() { return cantidadVendida; }
    public double getTotal() { return total; }
}