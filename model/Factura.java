
package model;

public class Factura {

    private int idFactura;
    private String fecha;
    private double total;
    private String metodoPago;
    private int clienteId;
    private int cajeroId;

    public Factura(String fecha, String metodoPago, int clienteId, int cajeroId) {
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.clienteId = clienteId;
        this.cajeroId = cajeroId;
        this.total = 0;
    }

    public Factura(int idFactura, String fecha, String metodoPago, int clienteId, int cajeroId){
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.clienteId = clienteId;
        this.cajeroId = cajeroId;
        this.total = 0; 
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getFecha() {
        return fecha;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public int getClienteId() {
        return clienteId;
    }

    public int getCajeroId() {
        return cajeroId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
