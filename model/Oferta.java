package model;

import java.util.Date;

public class Oferta {

    private int idProducto;
    private int idOferta;
    private double porcentaje; 
    private Date fechaInicio;
    private Date fechaFin;
    private String oferta;
    private int activo;
    
    
    public Oferta(String oferta, double porcentaje, Date fechaInicio, Date fechaFin) { 
    	this.oferta = oferta;
    	this.porcentaje = porcentaje;
    	this.fechaInicio = fechaInicio;
    	this.fechaFin = fechaFin;
    	
    	
    }

    public Oferta(int idOferta, String oferta, int idProducto, double porcentaje, Date FechaInicio, Date fechaFin, int activo) {
    		
    
    	
    	
    }
    
    
    //cambie el tipo de dato del idProducto de  esta clase por INT puesto que en la clase productos 
    //esta como int y entonces me estaba chocando con schema de la table.
    public int getIdProducto() {
        return idProducto;
    }
    
    public String getOferta() {
 
    	return oferta;
    }
    
    public int getActivo() {
    	return activo;
    }
    
    public void setActivo(int activo) {
    	this.activo = activo;
    }
    public void setOferta(String oferta) {
    	this.oferta = oferta;
    }
    
    public void setIdProducto(int idProducto) {
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