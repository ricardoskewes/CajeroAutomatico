
package cajeroautomatico;

/**
 *
 * @author ricardoskewes :)
 */

public class Usuario {
    private int PIN;
    private double dineroEnCuenta;
    private String nombre;
    //Constructor requiere de nombre, pin, saldo
    public Usuario(String nom, int pin, double saldo){
        this.nombre = nom;
        this.PIN = pin;
        this.dineroEnCuenta = saldo;
    }
    public void reducirSaldoEn (double reduccion) throws Exception {
        double saldoAnterior = dineroEnCuenta;
        if (hayDineroEnCuenta(reduccion)){
            dineroEnCuenta = dineroEnCuenta - reduccion;
        } else {
            throw new Exception("No hay saldo disponible. Volviendo al menú.");
        }  
    	System.out.println("Saldo anterior: " + CalculoCajero.redondearYPresentar(saldoAnterior) + ". Saldo nuevo: " + CalculoCajero.redondearYPresentar(dineroEnCuenta));
    }

    public boolean hayDineroEnCuenta(double reduccion){
        return dineroEnCuenta >= reduccion;
    }

    public void imprimirSaldo (){
        System.out.println("Tu saldo es: " + CalculoCajero.redondearYPresentar(dineroEnCuenta));
    }

    public int getPIN() {
        return PIN;
    }

    public double getDineroEnCuenta() {
        return dineroEnCuenta;
    }

    public String getNombre() {
        return nombre;
    }
}
