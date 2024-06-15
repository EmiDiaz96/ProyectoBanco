package proyectoBanco.proyectoBanco;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import DAO.ClienteDAO;
import DAO.ClienteDAOImpl;
import DAO.Cuentas_BancariasDAO;
import DAO.Cuentas_BancariasDAOImpl;
import DAO.Tipo_TransaccionDAO;
import DAO.Tipo_TransaccionDAOImpl;
import DAO.TransaccionesDAO;
import DAO.TransaccionesDAOImpl;
import clases.Cliente;
import clases.Cuentas_Bancarias;
import clases.Tipo_Transaccion;
import clases.Transacciones;
import conexion.Conexion;

public class App {	
static Connection conectar = null;	
private static Scanner scanner = new Scanner(System.in);
    public static void main( String[] args){
    	try {
            // Establecer conexión a la base de datos
    		Conexion.conectar();

			// Crear instancias de DAO
            ClienteDAO clienteDAO = new ClienteDAOImpl(conectar);
            Cuentas_BancariasDAO cuentasDAO = new Cuentas_BancariasDAOImpl(conectar);
            Tipo_TransaccionDAO tipoTransaccionDAO = new Tipo_TransaccionDAOImpl(conectar);
            TransaccionesDAO transaccionesDAO = new TransaccionesDAOImpl(conectar);

            // Menú interactivo
            while (true) {
                System.out.println("\n=== Menú de Operaciones ===");
                System.out.println("1. Listar Clientes");
                System.out.println("2. Agregar Cliente");
                System.out.println("3. Listar Cuentas Bancarias por Cliente");
                System.out.println("4. Registrar Transacción");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después de nextInt()

                switch (opcion) {
                    case 1:
                        listarClientes(clienteDAO);
                        break;
                    case 2:
                        agregarCliente(scanner, clienteDAO);
                        break;
                    case 3:
                        listarCuentasPorCliente(scanner, cuentasDAO);
                        break;
                    case 4:
                        registrarTransaccion(scanner, cuentasDAO, tipoTransaccionDAO, transaccionesDAO);
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opción inválida. Inténtelo de nuevo.");
                        break;
                }
            }
        } finally {         
        	scanner.close();
        }
    }

    private static void listarClientes(ClienteDAO clienteDAO) {
        List<Cliente> clientes = clienteDAO.obtenerTodos();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("\n=== Clientes Registrados ===");
            for (Cliente cliente : clientes) {
                System.out.println(cliente.getClienteId() + ": " + cliente.getNombre() + " " + cliente.getApellido());
            }
        }
    }

    private static void agregarCliente(Scanner scanner, ClienteDAO clienteDAO) {
        System.out.println("\n=== Agregar Nuevo Cliente ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Fecha de Nacimiento (AAAA-MM-DD): ");
        String fechaNacimientoStr = scanner.nextLine();

        try {
            Cliente nuevoCliente = new Cliente();
            clienteDAO.insertar(nuevoCliente);
            System.out.println("Cliente agregado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: Formato de fecha incorrecto. Use AAAA-MM-DD.");
        }
    }

    private static void listarCuentasPorCliente(Scanner scanner, Cuentas_BancariasDAO cuentasDAO) {
        System.out.println("\n=== Listar Cuentas Bancarias por Cliente ===");
        System.out.print("Ingrese el ID del Cliente: ");
        int clienteId = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de nextInt()

        List<Cuentas_Bancarias> cuentas = cuentasDAO.obtenerPorCliente(clienteId);
        if (cuentas.isEmpty()) {
            System.out.println("El cliente no tiene cuentas bancarias registradas.");
        } else {
            System.out.println("\n=== Cuentas Bancarias del Cliente ===");
            for (Cuentas_Bancarias cuenta : cuentas) {
                System.out.println("ID: " + cuenta.getCuentaId() + ", Número: " + cuenta.getNumeroCuenta() +
                        ", Tipo: " + cuenta.getTipoCuenta() + ", Saldo: $" + cuenta.getSaldo());
            }
        }
    }

    private static void registrarTransaccion(Scanner scanner, Cuentas_BancariasDAO cuentasDAO,
                                             Tipo_TransaccionDAO tipoTransaccionDAO, TransaccionesDAO transaccionesDAO) {
        System.out.println("\n=== Registrar Transacción ===");
        System.out.print("Ingrese el ID de la Cuenta Bancaria: ");
        int cuentaId = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de nextInt()

        // Verificar si la cuenta existe
        Cuentas_Bancarias cuenta = cuentasDAO.obtenerPorId(cuentaId);
        if (cuenta == null) {
            System.out.println("La cuenta bancaria con ID " + cuentaId + " no existe.");
            return;
        }

        // Mostrar tipos de transacción disponibles
        List<Tipo_Transaccion> tiposTransaccion = tipoTransaccionDAO.obtenerTodos();
        if (tiposTransaccion.isEmpty()) {
            System.out.println("No hay tipos de transacción registrados.");
            return;
        }

        System.out.println("\n=== Tipos de Transacción Disponibles ===");
        for (Tipo_Transaccion tipo : tiposTransaccion) {
            System.out.println(tipo.getTipoTransaccionId() + ": " + tipo.getNombre());
        }

        System.out.print("Seleccione el ID del Tipo de Transacción: ");
        int tipoTransaccionId = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de nextInt()

        // Verificar si el tipo de transacción existe
        Tipo_Transaccion tipoTransaccion = tipoTransaccionDAO.obtenerPorId(tipoTransaccionId);
        if (tipoTransaccion == null) {
            System.out.println("El tipo de transacción con ID " + tipoTransaccionId + " no existe.");
            return;
        }

        // Ingresar el monto y descripción de la transacción
        System.out.print("Ingrese el monto de la transacción: ");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea después de nextDouble()
        System.out.print("Ingrese una descripción para la transacción: ");
        String descripcion = scanner.nextLine();

        // Crear la nueva transacción
        Transacciones transaccion = new Transacciones(0, cuentaId, tipoTransaccionId, monto, null, descripcion);
        transaccionesDAO.insertar(transaccion);
        System.out.println("Transacción registrada exitosamente.");
    }
}