package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clases.Tipo_Transaccion;

public class Tipo_TransaccionDAOImpl implements Tipo_TransaccionDAO {
    private Connection conexion;

    public Tipo_TransaccionDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Tipo_Transaccion obtenerPorId(int tipoTransaccionId) {
        Tipo_Transaccion tipoTransaccion = null;
        String sql = "SELECT * FROM Tipo_Transaccion WHERE tipo_transaccion_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, tipoTransaccionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tipoTransaccion = new Tipo_Transaccion(
                    rs.getInt("tipo_transaccion_id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipoTransaccion;
    }

    @Override
    public List<Tipo_Transaccion> obtenerTodos() {
        List<Tipo_Transaccion> tiposTransaccion = new ArrayList<>();
        String sql = "SELECT * FROM Tipo_Transaccion";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tipo_Transaccion tipoTransaccion = new Tipo_Transaccion(
                    rs.getInt("tipo_transaccion_id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
                tiposTransaccion.add(tipoTransaccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposTransaccion;
    }

    @Override
    public void insertar(Tipo_Transaccion tipoTransaccion) {
        String sql = "INSERT INTO Tipo_Transaccion (nombre, descripcion) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, tipoTransaccion.getNombre());
            stmt.setString(2, tipoTransaccion.getDescripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Tipo_Transaccion tipoTransaccion) {
        String sql = "UPDATE Tipo_Transaccion SET nombre = ?, descripcion = ? WHERE tipo_transaccion_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, tipoTransaccion.getNombre());
            stmt.setString(2, tipoTransaccion.getDescripcion());
            stmt.setInt(3, tipoTransaccion.getTipoTransaccionId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int tipoTransaccionId) {
        String sql = "DELETE FROM Tipo_Transaccion WHERE tipo_transaccion_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, tipoTransaccionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
