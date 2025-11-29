package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Oferta;
import utils.Operaciones;

public class OfertaDAO implements Operaciones<Oferta>  {

	@Override
	public boolean insertar(Oferta of) {
		
	 if(of == null) return false;
	 
	 String sql = "Insert into ofertas (oferta, monto, dateInicio, dateFin, idProducto) "
				+ "values (?, ?, ?, ?, ?)";
	 
	 try(Connection con = DBConnection.getConnection();
			 PreparedStatement stmt = con.prepareStatement(sql)){
		
		 java.util.Date fecha = of.getFechaInicio();
		 java.util.Date fecha2 = of.getFechaFin();
		 
		 stmt.setString(1, of.getOferta());
		 stmt.setDouble(2, of.getPorcentaje());
		 stmt.setDate(3, new java.sql.Date(fecha.getTime()));
		 stmt.setDate(4, new java.sql.Date(fecha2.getTime()));
		 stmt.setInt(5, of.getIdProducto());
	 
		 return stmt.executeUpdate() > 0;		
		 
	 }catch(SQLException e) {
		 System.out.println("Error insertar la oferta: " + e.getMessage());
		 return false;
	 }
	 
	}

	@Override
	public boolean editar(Oferta of) {
		
		if(of == null ) return false;
		
		String sql = "update ofertas set oferta=?, monto=?, dateInicio=?, dateFin=?, activo=?, idProducto=?  where idOferta=?";
		
		try(Connection con = DBConnection.getConnection();
				 PreparedStatement stmt = con.prepareStatement(sql)){
			 
			java.util.Date fecha = of.getFechaInicio();
			java.util.Date fecha2 = of.getFechaFin();
			
			stmt.setString(1, of.getOferta());
			stmt.setDouble(2, of.getPorcentaje());
			stmt.setDate(3, new java.sql.Date(fecha.getTime()));
			stmt.setDate(4, new java.sql.Date(fecha2.getTime()));
			stmt.setInt(5, of.getActivo());
			stmt.setInt(6, of.getIdProducto());
			stmt.setInt(7, of.getIdOferta());
			 
		return stmt.executeUpdate() > 0;
			
		}catch(SQLException e) {
			 System.out.println("Error al editar la oferta: " + e.getMessage());
			 return false;
		 }
		 
		
		
	}

	@Override
	public boolean eliminar(int id) {
		
		if(id == 0) return false;
		
		String sql = "update ofertas set activo=? where idOferta=?";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, 0);
			stmt.setInt(2, id);
			return stmt.executeUpdate() > 0;
			
		}catch(SQLException e) {
			 System.out.println("Error al inhabilitar la oferta: " + e.getMessage());
			 return false;
			
		}
		
		
	}

	@Override
	public Oferta buscar(int id) {
		
		String sql = "select * from ofertas where idOferta=?";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				
			return new Oferta(
						rs.getInt("idOferta"),
						rs.getString("oferta"),
						rs.getInt("idProducto"),
						rs.getDouble("Monto"),
						rs.getDate("dateInicio"),
						rs.getDate("dateFin"),
						rs.getInt("activo")
						);
			}else {
				return null;
			}
			
			
		}catch(SQLException e) {
			 System.out.println("Error al inhabilitar la oferta: " + e.getMessage());
			 return null;
			
		}
	
	}

	@Override
	public List<Oferta> listar() {
		String sql = "select * from ofertas";
		
		List<Oferta> ofertas = new ArrayList<>();
		
		try(Connection con = DBConnection.getConnection();
		 Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql)){
			
			while(rs.next()) {
				
				Oferta o = new Oferta(
						rs.getInt("idOferta"),
						rs.getString("oferta"),
						rs.getInt("idProducto"),
						rs.getDouble("Monto"),
						rs.getDate("dateInicio"),
						rs.getDate("dateFin"),
						rs.getInt("activo")
						);
				ofertas.add(o);
			}
			
	
			
		}catch(SQLException e) {
			
			System.out.println("Error al obtener lista de las ofertas: " + e.getMessage());
		 return null;
		}
		
		return ofertas;
		
	}

}
