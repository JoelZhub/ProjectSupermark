package model;

public class CuentaCobrarPago {
    private int idPago;
    private int idCuenta;
    private String fecha;
    private double monto;

    public CuentaCobrarPago(int idCuenta, String fecha, double monto) {
        this.idCuenta = idCuenta;
        this.fecha = fecha;
        this.monto = monto;
    }

    public CuentaCobrarPago(int idPago, int idCuenta, String fecha, double monto) {
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