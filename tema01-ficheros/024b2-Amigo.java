package manejadoramigos;

import java.io.Serializable;

public class Amigo implements Serializable {
    String nombre;
    String correo;
    int anyo;
    int movil;

    public Amigo(String nombre, String correo, int anyo,int movil) {
        this.nombre = nombre;
        this.correo = correo;
        this.anyo = anyo;
        this.movil = movil;
    }
    
    public void escribir (){
        System.out.println("nombe: " + nombre);
        System.out.println("Correo: " + correo);
        System.out.println("Año: " + anyo);
        System.out.println("Movil: " + movil);
        System.out.println();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public int getMovil() {
        return movil;
    }

    public void setMovil(int movil) {
        this.movil = movil;
    }
    
    
}
