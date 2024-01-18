package cajeroautomatico.testpackage;

import cajeroautomatico.CajeroAutomatico;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CajeroAutomaticoTest {

    @Test
    void testValidacionWithFloat() {
        String tipo = "float";
        String prueba = "3.14";
        Number result = CajeroAutomatico.validacion(tipo, prueba);
        assertEquals(3.14f, result);
    }

    @Test
    void testValidacionWithInt() {
        String tipo = "int";
        String prueba = "42";
        Number result = CajeroAutomatico.validacion(tipo, prueba);
        assertEquals(42, result);
    }

    @Test
    void testValidacionWithDouble() {
        String tipo = "double";
        String prueba = "2.71";
        Number result = CajeroAutomatico.validacion(tipo, prueba);
        assertEquals(2.71, result);
    }

    @Test
    void testValidacionWithLong() {
        String tipo = "long";
        String prueba = "123456789";
        Number result = CajeroAutomatico.validacion(tipo, prueba);
        assertEquals(123456789L, result);
    }


    @Test
    void testEsDeTipo(){
        String tipo = "int";
        String prueba = "42";
        boolean result = CajeroAutomatico.esDeTipo(tipo, prueba);
        assertTrue(result);
    }

}
