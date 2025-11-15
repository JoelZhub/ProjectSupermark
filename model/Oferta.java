package model;

import java.util.Date;

public class Oferta {

    private String idProducto;
    private double porcentaje; 
    private Date fechaInicio;
    private Date fechaFin;

    public Oferta() { }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean activa() {
        Date hoy = new Date();
        return hoy.after(fechaInicio) && hoy.before(fechaFin);
    }

    public double aplicarDescuento(double precioOriginal) {
        return precioOriginal - (precioOriginal * (porcentaje / 100));
    }
}