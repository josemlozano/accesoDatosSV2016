package resolvedorecuacionessegundogrado;

public class ResolvedorEcuacionesSegundoGrado {
    
    /**
     * Devuelve la primera solución de una ecuación de segundo 
     * grado de la forma y = a x2 + bx + c.
     * Se devuelve como cadena, para que una cadena vacía indique
     * que no hay solucion.
     * @param a Coeficiente de x2
     * @param b Coeficiente de x
     * @param c Término independiente
     * @return Solución, convertida a cadena, o cadena vacía si
     * no hay solución (raíz negativa)
     */
    public static String primeraSolucion(double a, double b, double c) {
        
        double discriminante = b*b - 4*a*c;
        
        if (discriminante < 0)
            return "";  // Si no hay solución
        
        double resultado = -b + Math.sqrt(discriminante) / 2 * a;
        
        return ""+resultado;
    }
                
    
    /**
     * Devuelve la segunda solución de una ecuación de segundo 
     * grado de la forma y = a x2 + bx + c.
     * Se devuelve como cadena, para que una cadena vacía indique
     * que no hay solucion.
     * @param a Coeficiente de x2
     * @param b Coeficiente de x
     * @param c Término independiente
     * @return Solución, convertida a cadena, o cadena vacía si
     * no hay solución (raíz negativa)
     */
    public static String segundaSolucion(double a, double b, double c) {
        
        double discriminante = b*b - 4*a*c;
        
        if (discriminante < 0)
            return "";  // Si no hay solución
        
        double resultado = -b - Math.sqrt(discriminante) / 2 * a;
        
        return ""+resultado;
    }
}
