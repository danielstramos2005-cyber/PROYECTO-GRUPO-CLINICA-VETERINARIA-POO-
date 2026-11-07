package clinicaveterinaria;

import java.util.ArrayList;

public class Cliente extends Persona implements IGestionable{
    
    private ArrayList<Mascota> mascotas;

    
    public Cliente(String numeroCedula, String nombreApellido, String numeroTelefono, String correoElectronico) {
        super(numeroCedula, nombreApellido, numeroTelefono, correoElectronico);
        this.mascotas = new ArrayList<>();
    }

    
    public boolean agregarMascota(Mascota mascota){
        if(mascota == null || buscarMascota(mascota.getIdMascota()) != null) {
            return false;
        }
        if(mascota.getPropietario() != null && mascota.getPropietario() != this){
            return false;
        }
        mascotas.add(mascota);
        mascota.setPropietario(this);
        return true;
    }
    
    
    public boolean eliminarMascota(int idMascota){
        Mascota mascota = buscarMascota(idMascota);
        if (mascota == null){
            return false;
        }
        mascotas.remove(mascota);
        mascota.setPropietario(null);
        return true;
    }
    
    
    public Mascota buscarMascota(int idMascota){
        for(Mascota mascota : mascotas){
            if(mascota.getIdMascota() == idMascota){
                return mascota;
            }
        }
        return null;
    }
    
    
    public ArrayList<Mascota> getMascotas(){
        return new ArrayList<>(mascotas);
    }
    
    
    @Override
    public String mostrarInformacion() {
        return super.mostrarDatos() + "\nCantidad de mascotas: " + mascotas.size();
    }
}