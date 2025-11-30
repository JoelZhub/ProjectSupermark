package model;

public class Cliente extends Persona {

    private String identificacion;
    private String clasificacion;

    public Cliente(String identificacion, String nombre, String clasificacion, String telefono) {
        super(nombre, telefono);
        this.identificacion = identificacion;
        this.clasificacion = clasificacion;
    }

    public Cliente(String identificacion, String nombre, String clasificacion, String telefono, int activo) {
        super(nombre, telefono, activo);
        this.identificacion = identificacion;
        this.clasificacion = clasificacion;
    }


	public void setClasificacion(String clasificacion) {
		
		this.clasificacion = clasificacion;
	}
	
	public String getClasificacion() {
		return clasificacion;
	}
	public String getIdentificacion() {
		
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion =identificacion;
	}
	
    
    

   
}
