package ordenadoresclasicos;

import java.io.Serializable;

public class Ordenadores implements Serializable {
    private String marca;
    private String modelo;
    private int anyo;
    
    public Ordenadores(String marca, String modelo, int anyo) {
        this.marca = marca;
        this.modelo = modelo;
        this.anyo = anyo;
    }
    
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public int getAnyo() {
        return anyo;
    }
}
