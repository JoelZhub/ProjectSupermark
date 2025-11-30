package model;

public class RegistroInventario {
    private String nombre;
    private int cantidad;
    private String unidad;
    private double precio;

    public RegistroInventario(String nombre, String unidad, double precio, int cantidad) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.precio = precio;
        this.cantidad = cantidad;
        
    }

    public String getNombre(){return nombre;}
    public double getPrecio(){ return precio; }
    public int getCantidad(){ return cantidad;}
    public String getUnidad(){return unidad; }
    public double getValorTotal() { return this.precio * this.cantidad; }
}
