package clinicaveterinaria;

public class Veterinario extends Persona implements IGestionable{
    private String especialidad;
    private String codigoLicencia;

    
    public Veterinario(String especialidad, String codigoLicencia, String numeroCedula, String nombreApellido, String numeroTelefono, String correoElectronico) {
        super(numeroCedula, nombreApellido, numeroTelefono, correoElectronico);
        this.especialidad = especialidad;
        this.codigoLicencia = codigoLicencia;
    }

    
    public String getEspecialidad() {
        return especialidad;
    }
    public String getCodigoLicencia() {
        return codigoLicencia;
    }
    
    
    @Override
    public String mostrarInformacion(){
        return super.mostrarDatos()
                + "\nEspecialidad: " + especialidad
                + "\nCódigo de licencia: " + codigoLicencia;
    }
}