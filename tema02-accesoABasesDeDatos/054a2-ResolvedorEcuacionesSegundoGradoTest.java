package resolvedorecuacionessegundogrado;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ResolvedorEcuacionesSegundoGradoTest {
    
    public ResolvedorEcuacionesSegundoGradoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testPrimeraSolucion1() {
        System.out.println("primeraSolucion1 x2-4");
        assertEquals(
            ResolvedorEcuacionesSegundoGrado.primeraSolucion(
            1, 0, -4), "2");
    }

    @Test
    public void testSegundaSolucion1() {
        System.out.println("segundaSolucion1 x2-4");
        assertEquals(
            ResolvedorEcuacionesSegundoGrado.segundaSolucion(
            1, 0, -4), "-2");
    }
    
    @Test
    public void testPrimeraSolucion2() {
        System.out.println("primeraSolucion2 2x2-8");
        assertEquals(
            ResolvedorEcuacionesSegundoGrado.primeraSolucion(
            2, 0, -8), "2");
    }

    @Test
    public void testSegundaSolucion2() {
        System.out.println("segundaSolucion2 2x2-8");
        assertEquals(
            ResolvedorEcuacionesSegundoGrado.segundaSolucion(
            2, 0, -8), "-2");
    }
    
    @Test
    public void testPrimeraSolucion3() {
        System.out.println("primeraSolucion3 x2+4x+4");
        assertEquals(
            ResolvedorEcuacionesSegundoGrado.primeraSolucion(
            1, 4, 4), "-2");
    }

    @Test
    public void testSegundaSolucion3() {
        System.out.println("segundaSolucion3 x2+4x+4");
        assertEquals(
            ResolvedorEcuacionesSegundoGrado.segundaSolucion(
            1, 4, 4), "");
    }
    
    @Test
    public void testPrimeraSolucion4() {
        System.out.println("primeraSolucion4 x2+4");
        assertEquals(
            ResolvedorEcuacionesSegundoGrado.primeraSolucion(
            1, 0, 4), "");
    }

    @Test
    public void testSegundaSolucion4() {
        System.out.println("segundaSolucion4 x2+4");
        assertEquals(
            ResolvedorEcuacionesSegundoGrado.segundaSolucion(
            1, 0, 4), "");
    }
}
