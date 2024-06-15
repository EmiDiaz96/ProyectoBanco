package DAO;

import java.util.List;

import clases.Transacciones;

public interface TransaccionesDAO {
    Transacciones obtenerPorId(int transaccionId);
    List<Transacciones> obtenerPorCuenta(int cuentaId);
    List<Transacciones> obtenerTodos();
    void insertar(Transacciones transaccion);
    void actualizar(Transacciones transaccion);
    void eliminar(int transaccionId);
}