package clinicaveterinaria;

public abstract class Persona {
    private String numeroCedula;
    private String nombreApellido;
    private String numeroTelefono;
    private String correoElectronico;

    public Persona(String numeroCedula, String nombreApellido, String numeroTelefono, String correoElectronico) {
        this.numeroCedula = numeroCedula;
        this.nombreApellido = nombreApellido;
        this.numeroTelefono = numeroTelefono;
        this.correoElectronico = correoElectronico;
    }

    
    public String getNumeroCedula() {
        return numeroCedula;
    }
    public String getNombreApellido() {
        return nombreApellido;
    }
    public String getNumeroTelefono() {
        return numeroTelefono;
    }
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    
    
    public String mostrarDatos() {
        return "Cédula: " + numeroCedula
                + "\nNombre: " + nombreApellido
                + "\nTeléfono: " + numeroTelefono
                + "\nCorreo: " + correoElectronico;
    }
}