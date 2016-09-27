/**
 * @author chao
 */

package pruebaserializable;

import java.io.Serializable;

public class Amigos implements Serializable {
    private String nombre;
    private String email;
    private int anyoNacimiento;
    
    public Amigos(String nombre, String email, int anyoNacimiento) {
        this.nombre = nombre;
        this.email = email;
        this.anyoNacimiento = anyoNacimiento;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public int getAnyoNacimiento() {
        return anyoNacimiento;
    }
    
    public void mostrar() {
        System.out.println("Nombre: " + nombre + " E-mail: " + email + 
            " Año nacimiento: " + anyoNacimiento);
    }
}
