package ejercicioserializardeserializar;

import java.io.Serializable;

/**
 *
 * @author Adrian
 */
public class Amigo implements Serializable {
    private String nombre;
    private String eMail;
    private int anyoNac;
    private String movil;
    
    public Amigo(String nombre, String eMail, int anyoNac, String movil) {
        this.nombre = nombre;
        this.eMail = eMail;
        this.anyoNac = anyoNac;
        this.movil= movil;
    }
    
    public String mostrar(){
        return "Nombre :"+nombre+" email :"+eMail+" año de nacimiento :"+anyoNac
                +" numero de telefono :"+movil;
    }
}
