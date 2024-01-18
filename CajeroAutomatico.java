
package cajeroautomatico;
import java.util.*;
import java.io.*;
/**
 *
 * @author ricardoskewes :)
 */
public class CajeroAutomatico {
    //Permite tener múltiples cuentas de banco a la vez:
    static Usuario cliente1 = new Usuario("Ricardo Skewes", 4375, 5000);
    //Añadir otro cliente. Ejemplo: 
    static Usuario cliente2 = new Usuario("Diana Yael Montero", 8209, 10000000);
    //
    //Seleccionar qué cliente metió su tarjeta (Se puede cambiar fácilmente):
    static Usuario clienteActual = cliente1;

    static Scanner lector = new Scanner(System.in);
    //Variable para guiar flujo del programa. Si true, pide PIN
    static boolean primeraVez = true;
    //Variables para almacenar última respuesta, dependiendo del tipo de dato
    static int respInt;
    static double respDouble;
    static float respFloat;
    static boolean respBoolean;
    static long respLong;
    
    //MAIN
    public static void main(String[] args) {
        if(primeraVez){
            System.out.print("\n\n---            BANCO CENTRAL            ---\n\n"+
                                 "--- Gracias por usar nuestros servicios ---\n\n");
            pedirPin();
            primeraVez = false;
        }else {
            System.out.print("\n\n---            BANCO CENTRAL            ---\n\n");
        }
        //BONUS: El sistema usa lenguaje inclusivx!!
        System.out.println("Bienvenid@, " + clienteActual.getNombre() + "\nTu saldo es: " + CalculoCajero.redondearYPresentar(clienteActual.getDineroEnCuenta()));
        pedirInput("\n\n¿Qué servicio requieres realizar? (Ingresar el número de acuerdo con la siguiente lista) \n" + 
                   "RETIRO --- 1\n"+
                   "TRANSFERENCIA --- 2\n" +
                   "RECARGA TELEFÓNICA --- 3\n  " +
                   "SALIR --- Cualquier otro dígito", "int", 1);
        switch(respInt){
            case 1: retiro();
                    break;
            case 2: transferencia();
                    break;
            case 3: recarga();
                    break;
            default: regresarAMenu();
                     break;
                    
        }
        
    }
    
    //Método que pide el PIN y corrobora que sea el mismo que el asociado a la instancia del objeto USUARIO
    public static void pedirPin() { 
        while(true){
        pedirInput("Ingresa tu número de pin de 4 dígitos", "int", 4);
        if (respInt == clienteActual.getPIN()){
            break;
        }
        System.out.println("PIN Incorrecto. Intenta de nuevo.");
        }
    }
    //Lleva a cabo el retiro
    public static void retiro(){
        System.out.println("\n\n---RETIRO---");
        pedirInput("Ingresa la cantidad que deseas retirar en moneda nacional", "double", 0);
        pedirInput("¿Estás segur@ que deseas retirar " + CalculoCajero.redondearYPresentar(respDouble)  + " ? \nPRESIONA CUALQUIER TECLA PARA CONTINUAR o '0' si deseas cancelar la operación", "boolean", 0);
        if (respBoolean){
            try{
                clienteActual.reducirSaldoEn(respDouble);
                CalculoCajero.barraDeProgreso("DESPACHANDO EFECTIVO", 200);
                System.out.println("Has retirado " + CalculoCajero.redondearYPresentar(respDouble) + " de tu cuenta de ahorros.");
                System.out.println("NO OLVIDES RECOGER TU DINERO.");
                donacion();
                clienteActual.imprimirSaldo();
                regresarAMenu();
            } catch (Exception exc){
                System.out.println(exc.getMessage());
                String[] args = {};
                CajeroAutomatico.main(args);
            }
        }
        else{
            System.out.println("Has cancelado esta operación.");
            clienteActual.imprimirSaldo();
            regresarAMenu();
        }
             
    }
    //Lleva a cabo la transferencia. 
    public static void transferencia(){
        double comision = 0.0;
        System.out.println("\n\n---TRANSFERENCIA---");
        pedirInput("¿A qué banco deseas transferir? (Ingresar el número de acuerdo con la siguiente lista de bancos) \n\n\n" + 
                   "BANCO CENTRAL --- 1\n\n"+
                   "BANCO LATERAL, filial de BANCO CENTRAL --- 2\n\n" +
                   "BANCO GARZA --- 3\n\n" + 
                   "BANCO LAGÜERA --- 4\n\n" +
                   "BANCO ALPHA-65 --- 5\n\n", "INT", 1);
        if (respInt<1 || respInt >5){
            System.out.println("Esa opción no existe. Intenta de nuevo");
            transferencia();
        }
        pedirInput("Ingresa tu número de cuenta de 10 dígitos", "long", 10);
        pedirInput("Ingresa la cantidad que deseas transferir en moneda nacional", "double", 0);

        switch(respInt){
            case 1: pedirInput("¿Estás segur@ que deseas transferir " + CalculoCajero.redondearYPresentar(respDouble) + " a la cuenta "  + respLong +  " del BANCO CENTRAL? (LIBRE DE COMISIÓN)\nPRESIONA CUALQUIER TECLA PARA CONTINUAR o '0' si deseas cancelar la operación", "boolean", 0);
                    comision = 0.0;
                    break;
            case 2: pedirInput("¿Estás segur@ que deseas transferir "+ CalculoCajero.redondearYPresentar(respDouble)+  " a la cuenta " + respLong + " del BANCO LATERAL? (LIBRE DE COMISIÓN)\nPRESIONA CUALQUIER TECLA PARA CONTINUAR o '0' si deseas cancelar la operación", "boolean", 0);
                    comision = 0.0;
                    break;
            case 3: pedirInput("¿Estás segur@ que deseas transferir "+ CalculoCajero.redondearYPresentar(respDouble)  + " a la cuenta " + respLong +  " del BANCO GARZA? (SE COBRARÁ 3 % DE COMISIÓN)\nPRESIONA CUALQUIER TECLA PARA CONTINUAR o '0' si deseas cancelar la operación", "boolean", 0);
                    comision =0.03;
                    break;
            case 4: pedirInput("¿Estás segur@ que deseas transferir "+ CalculoCajero.redondearYPresentar(respDouble)+  " a la cuenta " + respLong +  " del BANCO LAGÜERA? (SE COBRARÁ 25 % DE COMISIÓN)\nPRESIONA CUALQUIER TECLA PARA CONTINUAR o '0' si deseas cancelar la operación", "boolean", 0);
                    comision =0.25;
                    break;
            case 5: pedirInput("¿Estás segur@ que deseas transferir " + CalculoCajero.redondearYPresentar(respDouble)  + " a la cuenta " + respLong +  " del BANCO LAGÜERA? (SE COBRARÁ 360 % DE COMISIÓN)\nPRESIONA CUALQUIER TECLA PARA CONTINUAR o '0' si deseas cancelar la operación", "boolean", 0);
                    comision =3.6;
                    break;
        }
        if (respBoolean){
            try{
                clienteActual.reducirSaldoEn(respDouble* (1+comision));
                CalculoCajero.barraDeProgreso("REALIZANDO TRANSFERENCIA", 120);
                System.out.println("Has transferido " + CalculoCajero.redondearYPresentar(respDouble) + " a la cuenta " + respLong);
                donacion();
                clienteActual.imprimirSaldo();
                regresarAMenu();
            } catch (Exception exc){
                System.out.println(exc.getMessage());
                String[] args = {};
                CajeroAutomatico.main(args);
            }
        } else{
            System.out.println("Has cancelado esta operación.");
            clienteActual.imprimirSaldo();
            regresarAMenu();
        }
    }   
    //Lleva a cabo la recarga de tel al número especificado
    public static void recarga(){
    double comision = 0.0;
    int compania = 0;
        System.out.println("\n\n---RECARGA---");
        pedirInput("¿De qué compañía es el celular al que deseas recargar? (Ingresar el número de acuerdo con la siguiente lista de compañías telefónicas) \n\n\n" + 
                   "TELICENTRAL --- 1\n\n"+
                   "TEC-TEL --- 2\n\n" +
                   "TELANCE --- 3\n\n","INT", 1);
        if (respInt<1 || respInt >3){
            System.out.println("Esa opción no existe. Intenta de nuevo");
            recarga();
        }
        compania = respInt;
        pedirInput("Ingresa el número de teléfono de 10 dígitos", "long", 10);
        Map <Integer, Double> dic = new HashMap <Integer, Double> ();
        do{
        pedirInput("Selecciona una opción para recargar (TODAVÍA NO INCLUYE COMISIÓN. Ingresar el número de acuerdo con la siguiente lista de recargas) \n\n\n" + 
                   "$30 --- 1\n\n"+
                   "$50 --- 2\n\n" +
                   "$100 --- 3\n\n"+
                   "$200 --- 4\n\n","INT", 1);
        dic.put(1, 30.0);
        dic.put(2, 50.0);
        dic.put(3, 100.0);
        dic.put(4,200.0);
        if (respInt<1 || respInt >4){
            System.out.println("Esa opción no existe. Intenta de nuevo");
        }
        }while(respInt<1 || respInt >4);
        respDouble = dic.get(respInt);


        switch(compania){
            case 1: pedirInput("¿Estás segur@ que deseas recargar " + CalculoCajero.redondearYPresentar(respDouble) + " al número "  + respLong +  " de TELICENTRAL (LIBRE DE COMISIÓN)\nPRESIONA CUALQUIER TECLA PARA CONTINUAR o '0' si deseas cancelar la operación", "boolean", 0);
                    comision = 0.0;
                    break;
            case 2: pedirInput("¿Estás segur@ que deseas recargar "+ CalculoCajero.redondearYPresentar(respDouble) +  " a la cuenta " + respLong + " de TEC-TEL? (SE COBRARÁ 8.3% DE COMISIÓN)\nPRESIONA CUALQUIER TECLA PARA CONTINUAR o '0' si deseas cancelar la operación", "boolean", 0);
                    comision = 0.083;
                    break;
            case 3: pedirInput("¿Estás segur@ que deseas recargar "+ CalculoCajero.redondearYPresentar(respDouble)   + " a la cuenta " + respLong +  " de TELANCE? (SE COBRARÁ 29.2 % DE COMISIÓN)\nPRESIONA CUALQUIER TECLA PARA CONTINUAR o '0' si deseas cancelar la operación", "boolean", 0);
                    comision =0.292;
                    break;
        }
        if (respBoolean){
            try{
                clienteActual.reducirSaldoEn(respDouble* (1+comision));
                CalculoCajero.barraDeProgreso("REALIZANDO RECARGA", 120);
                System.out.println("Has RECARGADO " + CalculoCajero.redondearYPresentar(respDouble) + " al número " + respLong);
                donacion();
                clienteActual.imprimirSaldo();
                regresarAMenu();
            } catch (Exception exc){
                System.out.println(exc.getMessage());
                String[] args = {};
                CajeroAutomatico.main(args);
            }
        } else{
            System.out.println("Has cancelado esta operación.");
            clienteActual.imprimirSaldo();
            regresarAMenu();
        }
    }

    public static void donacion() throws Exception{
    pedirInput("\n\n---DONACIÓN 'AYUDA A PREVENIR EL INSOMNIO'---\n¿Deseas donar 1 peso a la fundación que ayuda a adolescentes a lidiar con la carga emocional del IB?\nPresiona '1' si estás de acuerdo o '0' si no.", "int" , 1);
    try{
        if (respInt == 1){
            clienteActual.reducirSaldoEn(1);
        }
    }catch(Exception ex){
        throw ex;
    }
    }
    //Trata de forzar a un tipo de dato. Si hay una excepción, quiere decir que no es el tipo de dato correcto o tiene algo mal
    public static Boolean esDeTipo(String tipo, String prueba) {
        try {
            Number n = validacion(tipo, prueba);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    //Método para preguntar continuamente hasta que se satisfaga el tipo de dato y el largo establecido.
    //@param tipoDeDato: String. Puede ser {"float", "int", "double", "boolean"}. Qué se pide.
    //largo: Integer. Especifica qué tan largo estamos buscando el dato (en cuanto a caracteres)
    public static void pedirInput(String pregunta, String tipoDeDato, int largo) {
        Boolean error = false;
        String userInp = "";
        do {
            System.out.println(pregunta);
            userInp = lector.nextLine().replaceAll("\\s+","");
            if (!esDeTipo(tipoDeDato, userInp)) {
                error = true;
                throw new RuntimeException("Error: Dato inválido. Intenta de nuevo.");
            } else if (userInp.length() != largo && largo!=0) {
                error = true;
                throw new RuntimeException("Error: Ingresa el número correcto de caracteres. Intenta de nuevo.");
            } else {
                error = false;
            }
        } while (error == true);

        cambiosDatos(tipoDeDato, userInp, pregunta, largo);

    }

    public static void cambiosDatos(String tipoDeDato, String userInp, String pregunta, int largo){
        if (tipoDeDato.equalsIgnoreCase("float")){
            respFloat = Float.parseFloat(userInp);
        } else if(tipoDeDato.equalsIgnoreCase("double")){
            respDouble = Double.parseDouble(userInp);
        } else if(tipoDeDato.equalsIgnoreCase("int")){
            respInt = Integer.parseInt(userInp);
        } else if (tipoDeDato.equalsIgnoreCase("boolean")){
            if (userInp.equals("0")){
                respBoolean = false;
            } else{
                respBoolean = true;
            }
        } else if (tipoDeDato.equalsIgnoreCase("long")){
            respLong = Long.parseLong(userInp);
        }

        if (respDouble<0 || respInt <0 || respLong <0){
            System.err.println("Error: Dato inválido. No se permiten negativos. Intenta de nuevo.");
            pedirInput(pregunta, tipoDeDato, largo);
        }
    }

    public static Number validacion(String tipo, String prueba){
        Number n = 0;
        if (tipo.equalsIgnoreCase("float")) {
            n = Float.parseFloat(prueba);
        } else if (tipo.equalsIgnoreCase("int")) {
            n = Integer.parseInt(prueba);
        } else if (tipo.equalsIgnoreCase("double")) {
            n = Double.parseDouble(prueba);
        }else if(tipo.equalsIgnoreCase("long")){
            n = Long.parseLong(prueba);
        }
        return n;
    }

    //Ejecuta el main() nuevamente o termina el programa
    public static void regresarAMenu(){
        pedirInput("(Pulsa cualquier tecla para SALIR o '0' para volver al menú)", "boolean", 0);
        if (respBoolean){
            System.out.println("---Gracias por usar nuestros servicios.---\n\n");
            System.exit(0);
        }else{
            String[] args = {};
            CajeroAutomatico.main(args);
        }

    }


}
