package dao;

import db.DBConnection;
import model.Categoria;
import model.Detalles;
import model.Producto;
import utils.Operaciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements Operaciones<Producto> {

    @Override
    public boolean insertar(Producto p) {
        if (p == null) return false;

        if (buscar(p.getCodigo()) != null) {
            return false;
        }

        String sql = "INSERT INTO productos (nombre, precio, categoria, cantidad, unidad) VALUES (?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO detalles_producto (idProducto, idProveedor, marca, origen) VALUES(?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection()) {

        	PreparedStatement stmt = con.prepareStatement(sql);
        	PreparedStatement stmt2 = con.prepareStatement(sql2);
        	
            stmt.setString(1, p.getNombre());
            stmt.setDouble(2, p.getPrecio());
            stmt.setString(3, p.getCategoria().name());
            stmt.setInt(4, p.getCantida());
            stmt.setString(5, p.getUnidad());
            
            stmt2.setInt(1, p.getDetalles().getIdProducto());
            stmt2.setInt(2, p.getDetalles().getProveedor().getIdProveedor());
            stmt2.setString(3, p.getDetalles().getMarca());
            stmt2.setString(4, p.getDetalles().getOrigen());
            
            return stmt.executeUpdate() > 0 && stmt2.executeUpdate() > 0 ;

        } catch (SQLException e) {
            System.out.println("Error insertar producto: " + e.getMessage());
            return false;
        }
    }

    
    
    
    @Override
    public boolean editar(Producto p) {
        if (p == null) return false;

        String sql = "UPDATE productos SET nombre=?, precio=?, categoria=?, cantidad=?, unidad=?, activo=? WHERE idProducto=?";
        String sql2 = "UPDATE detalles_producto SET idProducto=?, idProveedor=?, marca=?, origen=?, fechaAgregado=? WHERE idDetalleProducto=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             PreparedStatement stmt2 = (p.getDetalles() != null ? con.prepareStatement(sql2) : null)) {
        	
            stmt.setString(1, p.getNombre());
            stmt.setDouble(2, p.getPrecio());
            stmt.setString(3, p.getCategoria().name());
            stmt.setInt(4, p.getCantida());
            stmt.setString(5, p.getUnidad());
            stmt.setInt(6, p.getActivo());
            stmt.setInt(7, p.getCodigo());

            int rowsProducto = stmt.executeUpdate();
            
            int rowsDetalles = 1; 
            if (p.getDetalles() != null) {
                java.util.Date fecha = p.getDetalles().getFechaAgregado();
                stmt2.setInt(1, p.getDetalles().getIdProducto());
                stmt2.setInt(2, p.getDetalles().getProveedor().getIdProveedor());
                stmt2.setString(3, p.getDetalles().getMarca());
                stmt2.setString(4, p.getDetalles().getOrigen());
                stmt2.setDate(5, new java.sql.Date(fecha.getTime()));
                stmt2.setInt(6, p.getDetalles().getIdDetalleProducto());

                rowsDetalles = stmt2.executeUpdate();
            }

            return rowsProducto > 0 && rowsDetalles > 0;

        } catch (SQLException e) {
            System.out.println("Error editar producto: " + e.getMessage());
            return false;
        }
    }
    
    public boolean editarProductoStock(Producto p) {
    	 String sql = "UPDATE productos SET cantidad=? WHERE idProducto=?";
    	 
    	 try(Connection con = DBConnection.getConnection();
 				PreparedStatement stmt  = con.prepareStatement(sql)){
 			stmt.setInt(1, p.getCantida());
 			stmt.setInt(2, p.getCodigo());
 			return stmt.executeUpdate() > 0;
 		
 		}catch(SQLException e) {
 			
 			 System.out.println("Error al editar el stock del producto: " + e.getMessage());
 	            return false;
 		}
    	
    }

    
    //el metodo original lo comente
    
    /*
     *@Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE idProducto = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar producto: " + e.getMessage());
            return false;
        }
    
     * */
    @Override
    public boolean eliminar(int id) {
        String sql = "UPDATE productos SET activo = ? WHERE idProducto = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, 0);
        	stmt.setInt(2, id);
            
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al inhabilitar el producto: " + e.getMessage());
            return false;
        }
    }
    
    
    
   
    
    //aqui tambien agregue el campo activo y el idProveedor, y la clase detalles
    
    @SuppressWarnings("unused")
	@Override
	public Producto buscar(int id) {
	    String sql = "SELECT p.*, d.idDetalleProducto, d.marca, d.idProveedor, d.origen, d.fechaAgregado " +
	                 "FROM productos p " +
	                 "LEFT JOIN detalles_producto d ON p.idProducto = d.idProducto " +
	                 "WHERE p.idProducto = ?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            Producto producto = new Producto(
	                    rs.getInt("idProducto"),
	                    rs.getString("nombre"),
	                    rs.getDouble("precio"),
	                    Categoria.valueOf(rs.getString("categoria")),
	                    rs.getInt("cantidad"),
	                    rs.getString("unidad"),
	                    rs.getInt("activo")
	            );

	            int idDetalle = rs.getInt("idDetalleProducto");
	            if (!rs.wasNull()) { 
	                Detalles detalles = new Detalles(
	                        idDetalle,
	                        rs.getInt("idProducto"),
	                        rs.getString("marca"),
	                        rs.getInt("idProveedor"),
	                        rs.getString("origen"),
	                        rs.getDate("fechaAgregado")
	                );
	                producto.setDetalles(detalles);
	            }

	            return producto;
	        }

	    } catch (SQLException e) {
	        System.out.println("Error buscar producto: " + e.getMessage());
	    }

	    return null;
	}

    
  
    //igual, aunque aqui altere la consulta sql y emple un join para unir los datos, entre la table detalles y productos
  
    @Override
    public List<Producto> listar() {
    
        String sql = "SELECT p.*, d.idDetalleProducto, d.marca, d.idProveedor, d.origen, d.fechaAgregado " +
                     "FROM productos p " +
                     "LEFT JOIN detalles_producto d ON p.idProducto = d.idProducto";

        List<Producto> lista = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        Categoria.valueOf(rs.getString("categoria")),
                        rs.getInt("cantidad"),
                        rs.getString("unidad"),
                        rs.getInt("activo")
                );

                
                int idDetalle = rs.getInt("idDetalleProducto");
                if (!rs.wasNull()) {
                    Detalles d = new Detalles(
                            idDetalle,
                            rs.getInt("idProducto"),
                            rs.getString("marca"),
                            rs.getInt("idProveedor"),
                            rs.getString("origen"),
                            rs.getDate("fechaAgregado")
                    );
                    p.setDetalles(d);
                }

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }

        return lista;
    }

}