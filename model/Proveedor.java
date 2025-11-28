package model;

public class Proveedor {

    private int idProveedor;
    private String rncProveedor;
    private String nombre;
    private String telefono;
    private String calle;
    private String ciudad;
    private String pais;
    private String correo;
    private int activo;
    
    
    public Proveedor(String rncProveedor, String nombre, String telefono, String calle, String 
    		ciudad, String pais, String correo) {
    	
    	this.rncProveedor = rncProveedor;
    	this.nombre = nombre;
    	this.telefono = telefono;
    	this.calle = calle;
    	this.ciudad = ciudad;
    	this.pais = pais;
    	this.correo = correo;
    	
    }
    
    public Proveedor(int idProveedor, String rncProveedor, String nombre, String telefono, String calle, String 
    		ciudad, String pais, String correo, int activo) {
    	
    	this.idProveedor = idProveedor;
    	this.rncProveedor = rncProveedor;
    	this.nombre = nombre;
    	this.telefono = telefono;
    	this.calle = calle;
    	this.ciudad = ciudad;
    	this.pais = pais;
    	this.correo = correo;
    	this.activo = activo;
    	
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
