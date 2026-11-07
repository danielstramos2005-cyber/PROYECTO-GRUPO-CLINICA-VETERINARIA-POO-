
package clinicaveterinaria;

public class Cita implements IGestionable {
    private int idCita;
    private Mascota mascota;
    private Veterinario veterinario;
    private String fechaHora;
    private String motivo;
    private String estado;
    private Consulta consulta;

    
    public Cita(int idCita, Mascota mascota, Veterinario veterinario, String fechaHora, String motivo) {
        this.idCita = idCita;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = "Pendiente";
        this.consulta = null;
    }

    
    public int getIdCita() {
        return idCita;
    }
    public Mascota getMascota() {
        return mascota;
    }
    public Veterinario getVeterinario() {
        return veterinario;
    }
    public String getFechaHora() {
        return fechaHora;
    }
    public String getMotivo() {
        return motivo;
    }
    public String getEstado() {
        return estado;
    }
    public Consulta getConsulta() {
        return consulta;
    }

    
    public void confirmarCita() {
        if (estado.equals("Pendiente")) {
            estado = "Confirmada";
        }
    }

    
    public void cancelarCita() {
        if (!estado.equals("Atendida")) {
            estado = "Cancelada";
        }
    }

    
    public boolean registrarConsulta(Consulta consulta) {
        if (consulta == null || this.consulta != null || estado.equals("Cancelada")){
            return false;
        }
        this.consulta = consulta;
        estado = "Atendida";
        return true;
    }

    
    @Override
    public String mostrarInformacion() {
        return "ID de cita: " + idCita
                + "\nMascota: " + mascota.getNombre()
                + "\nVeterinario: " + veterinario.getNombreApellido()
                + "\nFecha y hora: " + fechaHora
                + "\nMotivo: " + motivo
                + "\nEstado: " + estado;
    }
}