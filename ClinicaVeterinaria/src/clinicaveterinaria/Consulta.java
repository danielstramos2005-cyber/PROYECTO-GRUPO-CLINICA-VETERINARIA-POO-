package clinicaveterinaria;

public class Consulta implements IGestionable{
    private int idConsulta;
    private Cita cita;
    private String diagnostico;
    private String tratamiento;
    private double costo;
    private String observaciones;

    public Consulta(int idConsulta, Cita cita, String diagnostico, String tratamiento, double costo, String observaciones) {
        this.idConsulta = idConsulta;
        this.cita = cita;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.costo = costo;
        this.observaciones = observaciones;
    }

    
    public int getIdConsulta() {
        return idConsulta;
    }
    public Cita getCita() {
        return cita;
    }
    public String getDiagnostico() {
        return diagnostico;
    }
    public String getTratamiento() {
        return tratamiento;
    }
    public double getCosto() {
        return costo;
    }
    public String getObservaciones() {
        return observaciones;
    }
    
    
    public void actualizarDiagnostico(String diagnostico){
        this.diagnostico = diagnostico;
    }
    
    
    public void actualizarTratamiento(String tratamiento){
        this.tratamiento = tratamiento;
    }
    
    
    @Override
    public String mostrarInformacion() {
        return "ID de consulta: " + idConsulta
                + "\nDiagnóstico: " + diagnostico
                + "\nTratamiento: " + tratamiento
                + "\nCosto: $" + costo
                + "\nObservaciones: " + observaciones;
    }
}