package model;

//class abstract persona para proveedores y clientes 
public abstract class Persona {

    protected String nombre;
    protected String telefono;
    protected int activo;

    public Persona(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.activo = 1;
    }

    public Persona(String nombre, String telefono, int activo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.activo = activo;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public int getActivo() { return activo; }
    public void setActivo(int activo) { this.activo = activo; }

    @Override
    public String toString() {
        return nombre;
    }
}
