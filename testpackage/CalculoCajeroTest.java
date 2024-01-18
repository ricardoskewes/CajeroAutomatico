package cajeroautomatico.testpackage;

import cajeroautomatico.CajeroAutomatico;
import cajeroautomatico.CalculoCajero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CalculoCajeroTest {

    @Test
    void testBarraDeProgreso(){
        String mens = "Hola estoy cargando este mensaje";
        long num = 1L;
        CalculoCajero.barraDeProgreso(mens, num);
    }

    @Test
    void testRedondearYPresentar(){
        double num = 4.5;
        String result = CalculoCajero.redondearYPresentar(num);
        assertEquals("$4,50", result);
    }
}
