/*

 */
package ordenadoresclasicos;

import java.io.Serializable;

/**
 *
 * @author Adrian Sanchis Gallego
 */
public class Ordenador implements Serializable {

    private String marca;
    private String modelo;
    private int anyo;

    public Ordenador(String marca, String modelo, int anyo) {
        this.marca = marca;
        this.modelo = modelo;
        this.anyo = anyo;
    }

    public void Mostrar() {
        System.out.println("Marca: " + this.marca + " Modelo: " + this.modelo + " Año: " + this.anyo);
    }

}
