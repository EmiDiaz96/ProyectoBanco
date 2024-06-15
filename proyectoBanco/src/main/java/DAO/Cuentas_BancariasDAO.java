package DAO;

import java.util.List;

import clases.Cuentas_Bancarias;

public interface Cuentas_BancariasDAO {
    Cuentas_Bancarias obtenerPorId(int cuentaId);
    List<Cuentas_Bancarias> obtenerPorCliente(int clienteId);
    List<Cuentas_Bancarias> obtenerTodos();
    void insertar(Cuentas_Bancarias cuenta);
    void actualizar(Cuentas_Bancarias cuenta);
    void eliminar(int cuentaId);
}
