package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clases.Cuentas_Bancarias;

public class Cuentas_BancariasDAOImpl implements Cuentas_BancariasDAO {
    private Connection conexion;

    public Cuentas_BancariasDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Cuentas_Bancarias obtenerPorId(int cuentaId) {
        Cuentas_Bancarias cuenta = null;
        String sql = "SELECT * FROM Cuentas_Bancarias WHERE cuenta_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cuentaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cuenta = new Cuentas_Bancarias(
                    rs.getInt("cuenta_id"),
                    rs.getInt("cliente_id"),
                    rs.getString("numero_cuenta"),
                    rs.getString("tipo_cuenta"),
                    rs.getDouble("saldo"),
                    rs.getDate("fecha_apertura")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuenta;
    }

    @Override
    public List<Cuentas_Bancarias> obtenerPorCliente(int clienteId) {
        List<Cuentas_Bancarias> cuentas = new ArrayList<>();
        String sql = "SELECT * FROM Cuentas_Bancarias WHERE cliente_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cuentas_Bancarias cuenta = new Cuentas_Bancarias(
                    rs.getInt("cuenta_id"),
                    rs.getInt("cliente_id"),
                    rs.getString("numero_cuenta"),
                    rs.getString("tipo_cuenta"),
                    rs.getDouble("saldo"),
                    rs.getDate("fecha_apertura")
                );
                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentas;
    }

    @Override
    public List<Cuentas_Bancarias> obtenerTodos() {
        List<Cuentas_Bancarias> cuentas = new ArrayList<>();
        String sql = "SELECT * FROM Cuentas_Bancarias";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cuentas_Bancarias cuenta = new Cuentas_Bancarias(
                    rs.getInt("cuenta_id"),
                    rs.getInt("cliente_id"),
                    rs.getString("numero_cuenta"),
                    rs.getString("tipo_cuenta"),
                    rs.getDouble("saldo"),
                    rs.getDate("fecha_apertura")
                );
                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentas;
    }

    @Override
    public void insertar(Cuentas_Bancarias cuenta) {
        String sql = "INSERT INTO Cuentas_Bancarias (cliente_id, numero_cuenta, tipo_cuenta, saldo, fecha_apertura) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cuenta.getClienteId());
            stmt.setString(2, cuenta.getNumeroCuenta());
            stmt.setString(3, cuenta.getTipoCuenta());
            stmt.setDouble(4, cuenta.getSaldo());
            stmt.setDate(5, new java.sql.Date(cuenta.getFechaApertura().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Cuentas_Bancarias cuenta) {
        String sql = "UPDATE Cuentas_Bancarias SET cliente_id = ?, numero_cuenta = ?, tipo_cuenta = ?, " +
                     "saldo = ?, fecha_apertura = ? WHERE cuenta_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cuenta.getClienteId());
            stmt.setString(2, cuenta.getNumeroCuenta());
            stmt.setString(3, cuenta.getTipoCuenta());
            stmt.setDouble(4, cuenta.getSaldo());
            stmt.setTimestamp(5, new java.sql.Timestamp(cuenta.getFechaApertura().getTime()));
            stmt.setInt(6, cuenta.getCuentaId());

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Cuenta bancaria actualizada exitosamente.");
            } else {
                System.out.println("No se encontró la cuenta bancaria con ID: " + cuenta.getCuentaId());
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar la cuenta bancaria: " + e.getMessage());
        }
    }

	@Override
	public void eliminar(int cuentaId) {
		// TODO Auto-generated method stub
		
	}
}