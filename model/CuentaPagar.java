package model;

public class CuentaPagar {
    private int id;
    private int idCompra;
    private int idProveedor;
    private double montoTotal;
    private double montoPendiente;
    private String estado;

    public CuentaPagar(int idCompra, int idProveedor, double montoTotal, double montoPendiente, String estado) {
        this.idCompra = idCompra;
        this.idProveedor = idProveedor;
        this.montoTotal = montoTotal;
        this.montoPendiente = montoPendiente;
        this.estado = estado;
    }

    public CuentaPagar(int id, int idCompra, int idProveedor, double montoTotal, double montoPendiente, String estado) {
        this.id = id;
        this.idCompra = idCompra;
        this.idProveedor = idProveedor;
        this.montoTotal = montoTotal;
        this.montoPendiente = montoPendiente;
        this.estado = estado;
    }

    public int getId() { return id; }
    public int getIdCompra() { return idCompra; }
    public int getIdProveedor() { return idProveedor; }
    public double getMontoTotal() { return montoTotal; }
    public double getMontoPendiente() { return montoPendiente; }
    public String getEstado() { return estado; }

    public void setMontoPendiente(double montoPendiente) { this.montoPendiente = montoPendiente; }
    public void setEstado(String estado) { this.estado = estado; }
}