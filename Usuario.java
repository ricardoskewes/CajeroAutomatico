
package cajeroautomatico;

/**
 *
 * @author ricardoskewes :)
 */

public class Usuario {
    public int PIN;
    public double dineroEnCuenta;
    public String nombre; 
    //Constructor requiere de nombre, pin, saldo
    public Usuario(String nom, int pin, double saldo){
        this.nombre = nom;
        this.PIN = pin;
        this.dineroEnCuenta = saldo;
    }
    public void reducirSaldoEn (double reduccion) throws Exception {
        double saldoAnterior = dineroEnCuenta;
        if (dineroEnCuenta >= reduccion){
            dineroEnCuenta = dineroEnCuenta - reduccion;
        } else {
            throw new Exception("No hay saldo disponible. Volviendo al menú.");
        }  
    	System.out.println("Saldo anterior: " + CajeroAutomatico.redondearYPresentar(saldoAnterior) + ". Saldo nuevo: " + CajeroAutomatico.redondearYPresentar(dineroEnCuenta));
    }
    public void imprimirSaldo (){
        System.out.println("Tu saldo es: " + CajeroAutomatico.redondearYPresentar(dineroEnCuenta));
    }
}
