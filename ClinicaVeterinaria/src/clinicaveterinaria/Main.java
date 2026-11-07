package clinicaveterinaria;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        GestorClinicaVeterinaria gestor = new GestorClinicaVeterinaria();

        int opcion;
        do {
            System.out.println("\n==================================");
            System.out.println("     CLINICA VETERINARIA");
            System.out.println("==================================");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar veterinario");
            System.out.println("3. Registrar mascota");
            System.out.println("4. Agendar cita");
            System.out.println("5. Confirmar / cancelar cita");
            System.out.println("6. Registrar consulta");
            System.out.println("7. Listar clientes y mascotas");
            System.out.println("8. Listar veterinarios");
            System.out.println("9. Listar citas");
            System.out.println("10. Consultar historial medico");
            System.out.println("0. Salir");
            System.out.println("==================================");
            opcion = leerEntero(sc, "Seleccione una opcion: ");
            switch (opcion) {
                case 1:
                    registrarCliente(sc, gestor);
                    break;
                case 2:
                    registrarVeterinario(sc, gestor);
                    break;
                case 3:
                    registrarMascota(sc, gestor);
                    break;
                case 4:
                    agendarCita(sc, gestor);
                    break;
                case 5:
                    gestionarCita(sc, gestor);
                    break;
                case 6:
                    registrarConsulta(sc, gestor);
                    break;
                case 7:
                    listarClientes(gestor);
                    break;
                case 8:
                    listarVeterinarios(gestor);
                    break;
                case 9:
                    listarCitas(gestor);
                    break;
                case 10:
                    consultarHistorial(sc, gestor);
                    break;
                case 0:
                    System.out.println("Gracias por utilizar el sistema.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
        sc.close();
    }

    private static void registrarCliente(Scanner sc, GestorClinicaVeterinaria gestor) {
        System.out.println("\n--- REGISTRAR CLIENTE ---");
        String cedula = leerTexto(sc, "Cedula: ");
        String nombre = leerTexto(sc, "Nombre completo: ");
        String telefono = leerTexto(sc, "Telefono: ");
        String correo = leerTexto(sc, "Correo electronico: ");
        Cliente cliente = new Cliente(cedula, nombre, telefono, correo);
        if (gestor.registrarCliente(cliente)) {
            System.out.println("Cliente registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar el cliente.");
        }
    }

    private static void registrarVeterinario(Scanner sc, GestorClinicaVeterinaria gestor) {
        System.out.println("\n--- REGISTRAR VETERINARIO ---");
        String cedula = leerTexto(sc, "Cedula: ");
        String nombre = leerTexto(sc, "Nombre completo: ");
        String telefono = leerTexto(sc, "Telefono: ");
        String correo = leerTexto(sc, "Correo electronico: ");
        String especialidad = leerTexto(sc, "Especialidad: ");
        String licencia = leerTexto(sc, "Codigo de licencia: ");
        Veterinario veterinario = new Veterinario(especialidad, licencia, cedula, nombre, telefono, correo);
        if (gestor.registrarVeterinario(veterinario)) {
            System.out.println("Veterinario registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar el veterinario.");
        }
    }

    private static void registrarMascota(Scanner sc, GestorClinicaVeterinaria gestor) {
        System.out.println("\n--- REGISTRAR MASCOTA ---");
        String cedula = leerTexto(sc, "Cedula del propietario: ");
        Cliente cliente = gestor.buscarCliente(cedula);
        if (cliente == null) {
            System.out.println("No existe ese cliente.");
            return;
        }
        int id = leerEntero(sc, "ID de la mascota: ");
        String nombre = leerTexto(sc, "Nombre: ");
        String especie = leerTexto(sc, "Especie: ");
        String raza = leerTexto(sc, "Raza: ");
        int anio = leerEntero(sc, "Anio de nacimiento: ");
        String sexo = leerTexto(sc, "Sexo: ");
        double peso = leerDouble(sc, "Peso en kg: ");
        Mascota mascota = new Mascota(nombre, especie, raza, anio, sexo, peso, id);
        if (cliente.agregarMascota(mascota)) {
            System.out.println("Mascota registrada correctamente.");
        } else {
            System.out.println("No se pudo registrar la mascota.");
        }
    }

    private static void agendarCita(Scanner sc, GestorClinicaVeterinaria gestor) {
        System.out.println("\n--- AGENDAR CITA ---");
        String cedula = leerTexto(sc, "Cedula del propietario: ");
        Cliente cliente = gestor.buscarCliente(cedula);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        int idMascota = leerEntero(sc, "ID de la mascota: ");
        Mascota mascota = cliente.buscarMascota(idMascota);
        if (mascota == null) {
            System.out.println("Mascota no encontrada.");
            return;
        }
        String licencia = leerTexto(sc, "Codigo de licencia del veterinario: ");
        Veterinario veterinario = gestor.buscarVeterinario(licencia);
        if (veterinario == null) {
            System.out.println("Veterinario no encontrado.");
            return;
        }
        int idCita = leerEntero(sc, "ID de la cita: ");
        String fechaHora = leerTexto(sc, "Fecha y hora (AAAA-MM-DD HH:MM): ");
        String motivo = leerTexto(sc, "Motivo de la cita: ");
        Cita cita = new Cita(idCita, mascota, veterinario, fechaHora, motivo);
        if (gestor.agendarCita(cita)) {
            System.out.println("Cita agendada correctamente.");
        } else {
            System.out.println("No se pudo agendar la cita.");
        }
    }

    private static void gestionarCita(Scanner sc, GestorClinicaVeterinaria gestor) {
        System.out.println("\n--- GESTIONAR CITA ---");
        int id = leerEntero(sc, "ID de la cita: ");
        Cita cita = gestor.buscarCita(id);
        if (cita == null) {
            System.out.println("Cita no encontrada.");
            return;
        }
        System.out.println("1. Confirmar cita");
        System.out.println("2. Cancelar cita");
        int opcion = leerEntero(sc, "Seleccione una opcion: ");
        if (opcion == 1) {
            cita.confirmarCita();
        } else if (opcion == 2) {
            cita.cancelarCita();
        } else {
            System.out.println("Opcion no valida.");
            return;
        }
        System.out.println("Estado actual: " + cita.getEstado());
    }

    private static void registrarConsulta(Scanner sc, GestorClinicaVeterinaria gestor) {
        System.out.println("\n--- REGISTRAR CONSULTA ---");
        int idCita = leerEntero(sc, "ID de la cita: ");
        Cita cita = gestor.buscarCita(idCita);
        if (cita == null) {
            System.out.println("Cita no encontrada.");
            return;
        }
        int idConsulta = leerEntero(sc, "ID de la consulta: ");
        String diagnostico = leerTexto(sc, "Diagnostico: ");
        String tratamiento = leerTexto(sc, "Tratamiento: ");
        double costo = leerDouble(sc, "Costo: ");
        String observaciones = leerTexto(sc, "Observaciones: ");
        Consulta consulta = new Consulta(idConsulta, cita, diagnostico, tratamiento, costo, observaciones);
        if (gestor.registrarConsulta(idCita, consulta)) {
            System.out.println("Consulta registrada correctamente.");
        } else {
            System.out.println("No se pudo registrar la consulta.");
        }
    }

    private static void listarClientes(GestorClinicaVeterinaria gestor) {
        System.out.println("\n--- CLIENTES Y MASCOTAS ---");
        for (Cliente cliente : gestor.getClientes()) {
            mostrarInformacion(cliente);
            for (Mascota mascota : cliente.getMascotas()) {
                mostrarInformacion(mascota);
            }
            System.out.println("----------------------------");
        }
    }

    private static void listarVeterinarios(GestorClinicaVeterinaria gestor) {
        System.out.println("\n--- VETERINARIOS ---");
        for (Veterinario veterinario : gestor.getVeterinarios()) {
            mostrarInformacion(veterinario);
            System.out.println("----------------------------");
        }
    }

    private static void listarCitas(GestorClinicaVeterinaria gestor) {
        System.out.println("\n--- CITAS REGISTRADAS ---");
        for (Cita cita : gestor.getCitas()) {
            mostrarInformacion(cita);
            if (cita.getConsulta() != null) {
                System.out.println("Consulta asociada:");
                mostrarInformacion(cita.getConsulta());
            }
            System.out.println("----------------------------");
        }
    }

    private static void consultarHistorial(Scanner sc, GestorClinicaVeterinaria gestor) {
        System.out.println("\n--- HISTORIAL MEDICO ---");
        String cedula = leerTexto(sc, "Cedula del propietario: ");
        Cliente cliente = gestor.buscarCliente(cedula);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        int idMascota = leerEntero(sc, "ID de la mascota: ");
        Mascota mascota = cliente.buscarMascota(idMascota);
        if (mascota == null) {
            System.out.println("Mascota no encontrada.");
            return;
        }
        mascota.mostrarHistorialMascota();
    }

    private static void mostrarInformacion(IGestionable objeto) {
        System.out.println(objeto.mostrarInformacion());
    }

    private static String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.println("Debe ingresar un numero entero.");
            sc.nextLine();
            System.out.print(mensaje);
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    private static double leerDouble(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextDouble()) {
            System.out.println("Debe ingresar un numero valido.");
            sc.nextLine();
            System.out.print(mensaje);
        }
        double valor = sc.nextDouble();
        sc.nextLine();
        return valor;
    }
}
