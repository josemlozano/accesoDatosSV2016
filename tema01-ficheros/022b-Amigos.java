/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaserializable;

import java.io.Serializable;

/**
 *
 * @author david_000
 */
public class Amigos implements Serializable {
    private String nombre;
    private String eMail;
    private int anyoNac;
    
    public Amigos(String nombre, String eMail, int anyoNac) {
        this.nombre = nombre;
        this.eMail = eMail;
        this.anyoNac = anyoNac;
    }
}
