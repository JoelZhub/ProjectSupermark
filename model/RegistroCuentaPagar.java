package model;

public class RegistroCuentaPagar {
    private int id;
    private String proveedor;
    private double montoTotal;
    private double montoPendiente;
    private String estado;

    public RegistroCuentaPagar(int id, String proveedor, double montoTotal, double montoPendiente, String estado) {
        this.id = id;
        this.proveedor = proveedor;
        this.montoTotal = montoTotal;
        this.montoPendiente = montoPendiente;
        this.estado = estado;
    }

    public int getId() { return id; }
    public String getProveedor() { return proveedor; }
    public double getMontoTotal() { return montoTotal; }
    public double getMontoPendiente() { return montoPendiente; }
    public String getEstado() { return estado; }
}