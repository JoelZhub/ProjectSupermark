package model;

public class CuentaPagarPago {
    private int idPago;
    private int idCuenta;
    private String fecha;
    private double monto;

    public CuentaPagarPago(int idCuenta, String fecha, double monto) {
        this.idCuenta = idCuenta;
        this.fecha = fecha;
        this.monto = monto;
    }

    public CuentaPagarPago(int idPago, int idCuenta, String fecha, double monto) {
        this.idPago = idPago;
        this.idCuenta = idCuenta;
        this.fecha = fecha;
        this.monto = monto;
    }

    public int getIdPago() { return idPago; }
    public int getIdCuenta() { return idCuenta; }
    public String getFecha() { return fecha; }
    public double getMonto() { return monto; }
}