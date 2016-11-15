package tareas;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Adrian Navarro
 */
public class Tarea {
    private Date fecha;
    private String descripcion;
    private String categoria;
    private int prioridad;

    public Tarea() {

    }
    
    public Tarea(Date fecha, String descripcion, String categoria, int prioridad) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.prioridad = prioridad;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.fecha);
        hash = 79 * hash + Objects.hashCode(this.descripcion);
        hash = 79 * hash + Objects.hashCode(this.categoria);
        hash = 79 * hash + this.prioridad;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tarea other = (Tarea) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Tarea{" + "fecha=" + fecha + ", descripcion=" + descripcion 
                + ", categoria=" + categoria + ", prioridad=" + prioridad + '}';
    }
}
