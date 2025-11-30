package model;

import java.util.Objects;

public class Proveedor  extends Persona{

    
    
    private int idProveedor;
    private String rncProveedor;
    private String calle;
    private String ciudad;
    private String pais;
    private String correo;

    public Proveedor(String rncProveedor, String nombre, String telefono, String correo, String calle,
                     String ciudad, String pais) {
        super(nombre, telefono);
        this.rncProveedor = rncProveedor;
        this.calle = calle;
        this.ciudad = ciudad;
        this.pais = pais;
        this.correo = correo;
    }

    public Proveedor(int idProveedor, String rncProveedor, String nombre, String telefono, String correo,
                     String calle, String ciudad, String pais, int activo) {
        super(nombre, telefono, activo);
        this.idProveedor = idProveedor;
        this.rncProveedor = rncProveedor;
        this.calle = calle;
        this.ciudad = ciudad;
        this.pais = pais;
        this.correo = correo;
    }
    
    
    public int getActivo() {
    	return activo;
    }
    
    public void setActivo(int activo) {
    	this.activo = activo;
    }
    public int getIdProveedor() {
        return idProveedor;
    }

    public String getRncProveedor() {
    	
    	return rncProveedor;
    }
    
    public String getCalle() {
    	return calle;
    }
    
    public void setCalle(String calle) {	
    	this.calle = calle;
    }
    
    public String getPais() {
    	return pais;
    }
    public void setPais(String pais) {
    	this.pais = pais;
    }
    
    public String getCiudad() {
    	return ciudad;
    }
  
    public void setCiudad(String ciudad) {
    	this.ciudad = ciudad;
    }
    
    public void setRncProveedor(String rncProveedor) {
    	
    	this.rncProveedor = rncProveedor;
    }
    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
   
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Proveedor proveedor = (Proveedor) obj;
        return idProveedor == proveedor.idProveedor; 
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProveedor);
    }
}
