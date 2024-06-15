package DAO;

import java.util.List;

import clases.Cliente;

public interface ClienteDAO {
    Cliente obtenerPorId(int clienteId);
    List<Cliente> obtenerTodos();
    void insertar(Cliente cliente);
    void actualizar(Cliente cliente);
    void eliminar(int clienteId);
}
