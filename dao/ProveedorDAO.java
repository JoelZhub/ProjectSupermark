package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Proveedor;
import utils.Operaciones;

public class ProveedorDAO implements Operaciones<Proveedor>  {

	@Override
	public boolean insertar(Proveedor po) {
		if(po == null) return false;
		
		String sql = "Insert into proveedores (rncProveedor, nombre, telefono, correo, calle, ciudad, pais) "
				+ "values (?, ?, ?, ?, ?, ?, ?) ";
//		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql) ){
	
			stmt.setString(1, po.getRncProveedor());
			stmt.setString(2, po.getNombre());
			stmt.setString(3, po.getTelefono());
			stmt.setString(4, po.getCorreo());
			stmt.setString(5, po.getCalle());
			stmt.setString(6, po.getCiudad());
			stmt.setString(7, po.getPais());
			return stmt.executeUpdate() > 0;
			
			
		} catch (SQLException e) {
            System.out.println("Error insertar un proveedor: " + e.getMessage());
            return false;
            
        }
		
	}

	@Override
	public boolean editar(Proveedor po ) {
		
	if(po == null) return false;
		
		String sql = "update proveedores set rncProveedor=?, nombre=?, telefono=?, correo=?, calle=?, ciudad=?, pais=?, activo=?  where idProveedor=?";
//		
		try(Connection con = DBConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql) ){
	
			stmt.setInt(1,  Integer.parseInt(po.getRncProveedor()));
			stmt.setString(2, po.getNombre());
			stmt.setString(3, po.getTelefono());
			stmt.setString(4, po.getCorreo());
			stmt.setString(5, po.getCalle());
			stmt.setString(6, po.getCiudad());
			stmt.setString(7, po.getPais());	
			stmt.setInt(8, po.getActivo());
			stmt.setInt(9, po.getIdProveedor());
			return stmt.executeUpdate() > 0;
			
			
		} catch (SQLException e) {
            System.out.println("Error al editar un proveedor: " + e.getMessage());
            return false;
            
        }
		
		
	}

	@Override
	public boolean eliminar(int id) {
		
		if(id == 0) return false;
		
		String sql = "update proveedores set activo=? where idProveedor=? ";
//		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql) ){
			stmt.setInt(1, 0);
			stmt.setInt(2, id);
			return stmt.executeUpdate() > 0;
	
		} catch (SQLException e) {
            System.out.println("Error al editar un proveedor: " + e.getMessage());
            return false;
            
        }
		
		
	}

	@Override
	public Proveedor buscar(int id) {
		
		if(id == 0) return null;
		
		String sql = "select * from proveedores where idProveedor=? ";
//		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql) ){
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
			return  new Proveedor(
						
							rs.getInt("idProveedor"),
							rs.getString("rncProveedor"),
							rs.getString("nombre"),
							rs.getString("telefono"),
							rs.getString("correo"),
							rs.getString("calle"),
							rs.getString("ciudad"),
							rs.getString("pais"),

							rs.getInt("activo")	
					);
					
		}else {
			return null;
		}
	
		} catch (SQLException e) {
            System.out.println("Error al editar un proveedor: " + e.getMessage());
            return null;
            
        }
	}

	@Override
	public List<Proveedor> listar() {
		String sql = "select * from proveedores";
		
		List<Proveedor> p = new ArrayList<>();
		
		try(Connection con = DBConnection.getConnection();
				 Statement stmt = con.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)){
			
			while(rs.next()){
					
				Proveedor po = new Proveedor(
						rs.getInt("idProveedor"),
						rs.getString("rncProveedor"),
						rs.getString("nombre"),
						rs.getString("telefono"),
						rs.getString("correo"),
						rs.getString("calle"),
						rs.getString("ciudad"),
						rs.getString("pais"),
						rs.getInt("activo")
						);
				
				p.add(po);	
			}
			
			
		}catch (SQLException e) {
            System.out.println("Error al listar proveedores: " + e.getMessage());
        }
		
		return p;
	}

}
