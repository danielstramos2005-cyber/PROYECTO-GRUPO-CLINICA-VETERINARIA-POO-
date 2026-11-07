
package clinicaveterinaria;

import java.util.ArrayList;

public class GestorClinicaVeterinaria {
    
    private ArrayList<Cliente> clientes;
    private ArrayList<Veterinario> veterinarios;
    private ArrayList<Cita> citas;
    
    
    public GestorClinicaVeterinaria() {
        this.clientes = new ArrayList<>();
        this.veterinarios = new ArrayList<>();
        this.citas = new ArrayList<>();
    }
    
    
    public ArrayList<Cliente> getClientes() {
        return new ArrayList<>(clientes);
    }
    public ArrayList<Veterinario> getVeterinarios() {
        return new ArrayList<>(veterinarios);
    }
    public ArrayList<Cita> getCitas() {
        return new ArrayList<>(citas);
    }
    
    
    public boolean registrarCliente(Cliente cliente) {
    if (cliente == null || buscarCliente(cliente.getNumeroCedula()) != null) {
        return false;
    }
    clientes.add(cliente);
    return true;
    }
    
    
    public Cliente buscarCliente(String numeroCedula) {
        for (Cliente cliente : clientes) {
            if (cliente.getNumeroCedula().equals(numeroCedula)) {
                return cliente;
            }
        }
        return null;
    }
    
    
    public Veterinario buscarVeterinario(String codigoLicencia) {
        for (Veterinario veterinario : veterinarios) {
            if (veterinario.getCodigoLicencia().equals(codigoLicencia)) {
                return veterinario;
            }
        }
        return null;
    }
    
    
    public boolean registrarVeterinario(Veterinario veterinario) {
        if (veterinario == null || buscarVeterinario(veterinario.getCodigoLicencia()) != null) {
            return false;
        }
        veterinarios.add(veterinario);
        return true;
    }
    
    
    private boolean veterinarioDisponible(Cita nuevaCita) {
        for (Cita cita : citas) {
            boolean mismoVeterinario = cita.getVeterinario().getCodigoLicencia().equals(nuevaCita.getVeterinario().getCodigoLicencia());
            
            boolean mismaFechaHora = cita.getFechaHora().equals(nuevaCita.getFechaHora());

            boolean citaActiva = cita.getEstado().equals("Pendiente") || cita.getEstado().equals("Confirmada");
            
            if (mismoVeterinario && mismaFechaHora && citaActiva) {
                return false;
            }
        }
        return true;
    }
    
    
    public boolean agendarCita(Cita cita) {
        if (cita == null || cita.getMascota() == null || cita.getVeterinario() == null || buscarCita(cita.getIdCita()) != null) {
            return false;
        }
        Cliente propietario = cita.getMascota().getPropietario();
        if (propietario == null) {
            return false;
        }
        Cliente clienteRegistrado = buscarCliente(propietario.getNumeroCedula());
        if (clienteRegistrado == null || clienteRegistrado.buscarMascota(cita.getMascota().getIdMascota()) == null) {
            return false;
        }
        if (buscarVeterinario(cita.getVeterinario().getCodigoLicencia()) == null) {
            return false;
        }
        if (!veterinarioDisponible(cita)) {
            return false;
        }
        citas.add(cita);
        return true;
    }
    
    
    public Cita buscarCita(int idCita) {
        for (Cita cita : citas) {
            if (cita.getIdCita() == idCita) {
                return cita;
            }
        }
        return null;
    }
    
    
    public boolean registrarConsulta(int idCita, Consulta consulta) {
        Cita cita = buscarCita(idCita);
        if (cita == null || consulta == null
                || consulta.getCita() != cita) {
            return false;
        }
        if (!cita.registrarConsulta(consulta)) {
            return false;
        }
        cita.getMascota().agregarConsulta(consulta);
        return true;
    }
}