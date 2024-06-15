package DAO;

import java.util.List;

import clases.Tipo_Transaccion;

public interface Tipo_TransaccionDAO {
    Tipo_Transaccion obtenerPorId(int tipoTransaccionId);
    List<Tipo_Transaccion> obtenerTodos();
    void insertar(Tipo_Transaccion tipoTransaccion);
    void actualizar(Tipo_Transaccion tipoTransaccion);
    void eliminar(int tipoTransaccionId);
}
