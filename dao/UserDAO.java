package dao;

import db.DBConnection;
import model.User;
import model.Rol;
import utils.Operaciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Operaciones<User> {

    @Override
    public boolean insertar(User u) {
        if (u == null) return false;

        if (buscarPorEmail(u.getEmail()) != null) return false;

        String sql = "INSERT INTO usuarios (nombre, email, pin, rol) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPassword());
            stmt.setString(4, u.getRol().name());

            int rows = stmt.executeUpdate();
            if (rows == 0) return false;

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                u.setId(rs.getInt(1));
            }

            return true;

        } catch (SQLException e) {
            System.out.println("Error insertar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean editar(User u) {
        if (u == null) return false;

        String sql = "UPDATE usuarios SET nombre=?, email=?, pin=?, rol=? WHERE idUsuario=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPassword());
            stmt.setString(4, u.getRol().name());
            stmt.setInt(5, u.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error editar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE idUsuario=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public User buscar(int id) {
        String sql = "SELECT * FROM usuarios WHERE idUsuario=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("idUsuario"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("pin"),
                        Rol.valueOf(rs.getString("rol"))
                );
            }

        } catch (SQLException e) {
            System.out.println("Error buscar usuario: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<User> listar() {
        String sql = "SELECT * FROM usuarios";
        List<User> lista = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                User u = new User(
                        rs.getInt("idUsuario"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("pin"),
                        Rol.valueOf(rs.getString("rol"))
                );

                lista.add(u);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }

        return lista;
    }

    public User buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("idUsuario"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("pin"),
                        Rol.valueOf(rs.getString("rol"))
                );
            }

        } catch (SQLException e) {
            System.out.println("Error buscar usuario por email: " + e.getMessage());
        }

        return null;
    }
}