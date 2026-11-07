package clinicaveterinaria;
import java.util.ArrayList;
public class Mascota implements IGestionable{
    private String nombre;
    private String especie;
    private String raza;
    private int anioNacimiento;
    private String sexo;
    private double peso;
    private int idMascota;
    private Cliente propietario;
    private ArrayList<Consulta> historialMedico;

    
    public Mascota(String nombre, String especie, String raza, int anioNacimiento, String sexo, double peso, int idMascota) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.anioNacimiento = anioNacimiento;
        this.sexo = sexo;
        this.peso = peso;
        this.idMascota = idMascota;
        this.historialMedico = new ArrayList<>();
    }
    
    
    public String getNombre() {
        return nombre;
    }
    public String getEspecie() {
        return especie;
    }
    public String getRaza() {
        return raza;
    }
    public int getAnioNacimiento() {
        return anioNacimiento;
    }
    public String getSexo() {
        return sexo;
    }
    public double getPeso() {
        return peso;
    }
    public int getIdMascota() {
        return idMascota;
    }
    public ArrayList<Consulta> getHistorialMedico() {
        return new ArrayList<> (historialMedico);
    }

    public Cliente getPropietario() {
        return propietario;
    }
    public void setPropietario(Cliente propietario) {
        this.propietario = propietario;
    }
    
    
    public void agregarConsulta(Consulta consulta){
        if(consulta != null){
            historialMedico.add(consulta);
        }
    }
    
    
    public void mostrarHistorialMascota(){
        if(historialMedico.isEmpty()){
            System.out.println("La mascota consultada no tiene historial registrado.");
        } else {
            for (Consulta consulta : historialMedico){
                        System.out.println(consulta.mostrarInformacion());
                        System.out.println("----------------------------");
            }
        }
    }

    
    @Override
    public String mostrarInformacion(){
        return "ID: " + idMascota
                + "\nNombre: " + nombre
                + "\nEspecie: " + especie
                + "\nRaza: " + raza
                + "\nAño de nacimiento: " + anioNacimiento
                + "\nSexo: " + sexo
                + "\nPeso: " + peso;
    }
}