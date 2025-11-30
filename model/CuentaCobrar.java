package model;

public class CuentaCobrar {
    private int id;
    private String idCliente;
    private String nombreCliente;
    private int idFactura;
    private double montoTotal;
    private double montoPendiente;
    private String estado;

    public CuentaCobrar(String idCliente, int idFactura, double montoTotal, double montoPendiente, String estado) {
        this.idCliente = idCliente;
        this.idFactura = idFactura;
        this.montoTotal = montoTotal;
        this.montoPendiente = montoPendiente;
        this.estado = estado;
    }

    public CuentaCobrar(int id, String idCliente, int idFactura, double montoTotal, double montoPendiente, String estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.idFactura = idFactura;
        this.montoTotal = montoTotal;
        this.montoPendiente = montoPendiente;
        this.estado = estado;
    }

    public CuentaCobrar(int id, String idCliente, String nombreCliente, int idFactura, double montoTotal, double montoPendiente, String estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.idFactura = idFactura;
        this.montoTotal = montoTotal;
        this.montoPendiente = montoPendiente;
        this.estado = estado;
    }

    public int getId() { return id; }
    public String getIdCliente() { return idCliente; }
    public String getNombreCliente() { return nombreCliente; }
    public int getIdFactura() { return idFactura; }
    public double getMontoTotal() { return montoTotal; }
    public double getMontoPendiente() { return montoPendiente; }
    public String getEstado() { return estado; }

    public void setMontoPendiente(double v) { this.montoPendiente = v; }
    public void setEstado(String estado) { this.estado = estado; }
}