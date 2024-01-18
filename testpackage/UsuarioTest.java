package cajeroautomatico.testpackage;

import cajeroautomatico.CajeroAutomatico;
import cajeroautomatico.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsuarioTest {

    @Test
    void testReducirSaldoEn() throws Exception {
        double reduccion = 25;
        Usuario u = new Usuario("Juan", 5, 500);
        u.reducirSaldoEn(reduccion);
    }

    @Test
    void testHayDineroEnCuenta(){
        double reduccion = 25;
        Usuario u = new Usuario("Juan", 5, 500);
        boolean total = u.hayDineroEnCuenta(reduccion);
        assertTrue(total);
    }


}
