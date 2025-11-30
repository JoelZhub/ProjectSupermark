package model;

public class RegistroCuentaCobrar {
    private int id;
    private String cliente;
    private double montoTotal;
    private double montoPendiente;
    private String estado;

    public RegistroCuentaCobrar(int id, String cliente, double montoTotal, double montoPendiente, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.montoTotal = montoTotal;
        this.montoPendiente = montoPendiente;
        this.estado = estado;
    }

    public int getId() { return id; }
    public String getCliente() { return cliente; }
    public double getMontoTotal() { return montoTotal; }
    public double getMontoPendiente() { return montoPendiente; }
    public String getEstado() { return estado; }
}