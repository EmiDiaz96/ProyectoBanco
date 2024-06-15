package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clases.Transacciones;

public class TransaccionesDAOImpl implements TransaccionesDAO {
    private Connection conexion;

    public TransaccionesDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Transacciones obtenerPorId(int transaccionId) {
        Transacciones transaccion = null;
        String sql = "SELECT * FROM Transacciones WHERE transaccion_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, transaccionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                transaccion = new Transacciones(
                    rs.getInt("transaccion_id"),
                    rs.getInt("cuenta_id"),
                    rs.getInt("tipo_transaccion_id"),
                    rs.getDouble("monto"),
                    rs.getDate("fecha_transaccion"),
                    rs.getString("descripcion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaccion;
    }

    @Override
    public List<Transacciones> obtenerPorCuenta(int cuentaId) {
        List<Transacciones> transacciones = new ArrayList<>();
        String sql = "SELECT * FROM Transacciones WHERE cuenta_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cuentaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transacciones transaccion = new Transacciones(
                    rs.getInt("transaccion_id"),
                    rs.getInt("cuenta_id"),
                    rs.getInt("tipo_transaccion_id"),
                    rs.getDouble("monto"),
                    rs.getDate("fecha_transaccion"),
                    rs.getString("descripcion")
                );
                transacciones.add(transaccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacciones;
    }

    @Override
    public List<Transacciones> obtenerTodos() {
        List<Transacciones> transacciones = new ArrayList<>();
        String sql = "SELECT * FROM Transacciones";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transacciones transaccion = new Transacciones(
                    rs.getInt("transaccion_id"),
                    rs.getInt("cuenta_id"),
                    rs.getInt("tipo_transaccion_id"),
                    rs.getDouble("monto"),
                    rs.getDate("fecha_transaccion"),
                    rs.getString("descripcion")
                );
                transacciones.add(transaccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacciones;
    }

    @Override
    public void insertar(Transacciones transaccion) {
        String sql = "INSERT INTO Transacciones (cuenta_id, tipo_transaccion_id, monto, fecha_transaccion, descripcion) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, transaccion.getCuentaId());
            stmt.setInt(2, transaccion.getTipoTransaccionId());
            stmt.setDouble(3, transaccion.getMonto());
            stmt.setDate(4, new java.sql.Date(transaccion.getFechaTransaccion().getTime()));
            stmt.setString(5, transaccion.getDescripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Transacciones transaccion) {
        String sql = "UPDATE Transacciones SET cuenta_id = ?, tipo_transaccion_id = ?, " +
                     "monto = ?, fecha_transaccion = ?, descripcion = ? WHERE transaccion_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, transaccion.getCuentaId());
            stmt.setInt(2, transaccion.getTipoTransaccionId());
            stmt.setDouble(3, transaccion.getMonto());
            stmt.setDate(4, new java.sql.Date(transaccion.getFechaTransaccion().getTime()));
            stmt.setString(5, transaccion.getDescripcion());
            stmt.setInt(6, transaccion.getTransaccionId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int transaccionId) {
        String sql = "DELETE FROM Transacciones WHERE transaccion_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, transaccionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
