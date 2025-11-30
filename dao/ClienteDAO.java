package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import db.DBConnection;
import model.Cliente;
import utils.Operaciones;
public class ClienteDAO implements Operaciones<Cliente>  {

	@Override
	public boolean insertar(Cliente cl) {
		if(cl == null) return false;
		
		String sql = "INSERT INTO clientes(identificacion, nombre, clasificacion, telefono) VALUES (?, ?,?,?)";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement stmt  = con.prepareStatement(sql)){
			
			stmt.setString(1, cl.getIdentificacion());
			stmt.setString(2, cl.getNombre());
			stmt.setString(3, cl.getClasificacion());
			stmt.setString(4, cl.getTelefono());
			return stmt.executeUpdate() > 0;
		
		}catch(SQLException e) {
			
			 System.out.println("Error al insertar un cliente: " + e.getMessage());
	            return false;
		}
	
	}

	@Override
	public boolean editar(Cliente cl) {
		
		String sql = "UPDATE clientes set nombre=?, clasificacion=?, telefono=?, activo=? WHERE identificacion=?";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement stmt  = con.prepareStatement(sql)){
			stmt.setString(1, cl.getNombre());
			stmt.setString(2, cl.getClasificacion());
			stmt.setString(3, cl.getTelefono());
			stmt.setInt(4, cl.getActivo());
			stmt.setString(5, cl.getIdentificacion());
			return stmt.executeUpdate() > 0;
		
		}catch(SQLException e) {
			
			 System.out.println("Error al insertar un cliente: " + e.getMessage());
	            return false;
		}
	}

	@Override
	public boolean eliminar(int id) {
		
		if(id == 0) return false;
		String sql = "UPDATE clientes set activo=? WHERE identificacion=?";
		String valor = Integer.toString(id);
		try(Connection con = DBConnection.getConnection();
			PreparedStatement stmt  = con.prepareStatement(sql)){
			stmt.setInt(1, 0 );
			stmt.setString(2, valor);
			return stmt.executeUpdate() > 0;
		
		}catch(SQLException e) {
			
			 System.out.println("Error al insertar un cliente: " + e.getMessage());
	            return false;
		}
	}

	@Override
	public Cliente buscar(int id) {
		
		if(id == 0) return null;
		
		String sql = "SELECT * FROM clientes where identificacion=?";
		String valor = Integer.toString(id);
		try(Connection con = DBConnection.getConnection();
				PreparedStatement stmt  = con.prepareStatement(sql)){
				stmt.setString(1,  valor);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()) {
					
					return new Cliente(
							rs.getString("identificacion"),
							rs.getString("nombre"),
							rs.getString("clasificacion"),
							rs.getString("telefono"),
							rs.getInt("activo")
							);
				}else {
					
					return null;
				}
				
			
			}catch(SQLException e) {
				
				 System.out.println("Error al insertar un cliente: " + e.getMessage());
		         return null;
			}
		
	
	}

	
	public List<Cliente> listar() {
	
		String sql = "SELECT * FROM clientes";
		
		List<Cliente> clientes = new ArrayList<>();
	
		try(Connection con = DBConnection.getConnection();
				 Statement stmt = con.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)){
			
			while(rs.next()){
				Cliente cl = new Cliente(
						rs.getString("identificacion"),
						rs.getString("nombre"),
						rs.getString("clasificacion"),
						rs.getString("telefono"),
						rs.getInt("activo")
						);
				
				clientes.add(cl);	
			}
			
			
		}catch (SQLException e) {
           System.out.println("Error al listar proveedores: " + e.getMessage());
       }
		
		return clientes;
	}

}
