package control;

import java.util.List;

import dao.OfertaDAO;
import model.Oferta;

public class OfertasController {
	
	@SuppressWarnings("unused")
	private OfertaDAO ofertas;
	public OfertasController(OfertaDAO ofertas) {
		this.ofertas = ofertas;
	}
	
	public boolean validarDatosOfertas(Oferta of) {
		
		if(of.getOferta().length() < 2 || of.getOferta() == null) return false;
		if(of.getFechaFin() == null) return false;
		if(of.getFechaInicio() == null) return false;
		if(of.getPorcentaje()  <= 0 || of.getPorcentaje() > 100) return false;
		if(of.getIdProducto() == 0) return false;
		if(of.getFechaFin().before(of.getFechaInicio())) return false;
		return true;
	}
	
	public List<Oferta> listarOfertas(){
		return ofertas.listar();
	}
	
	public boolean desactivarOferta(int id){
		return ofertas.eliminar(id);
	}
	
	public boolean crearOferta(Oferta of) {
		return ofertas.insertar(of);
	}
	
	public boolean editarOferta(Oferta of) {
		return ofertas.editar(of);
	}
	
	public Oferta buscarOferta(int id) {
		return ofertas.buscar(id);
	}
	
	
}

