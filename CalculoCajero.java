package cajeroautomatico;

import java.io.IOException;

public class CalculoCajero {
    //Barra de progreso. Meramente estética.
    //mensajeDeCarga: String. Aparece un mensaje especificando el proceso.
    //miliSegPorChar: Long. Especifica cuántos milisegundos se tarda en actualizar al BufferString
    public static void barraDeProgreso(String mensajeDeCarga, long miliSegPorChar){
        System.out.print("\n\n");
        System.out.println(mensajeDeCarga + "\n");
        StringBuffer anim = new StringBuffer(30);
        for (int i = 0; i < 30; i++){
            anim.append(" ");
        }
        for (int x =0 ; x < 30 ; x++) {
            anim.setCharAt(x, '\u25A0'); //Código para un recuadro negro
            double porcentaje = (double)(x+1)*10/3;
            porcentaje = Math.round(porcentaje*10.0)/10.0;
            String data =  "\r"+ "|" +  anim.toString() + "|" + " "  + porcentaje + "%" ;
            try{
                System.out.write(data.getBytes());
                Thread.sleep(miliSegPorChar);
            }
            catch (InterruptedException e1) {
                System.out.println(e1.getMessage());
            }
            catch (IOException e2) {
                System.out.println(e2.getMessage());
            }
        }
        System.out.print("\n\n");
    }

    //Presenta en formato de moneda un número
    //@param num: double. Numero a convertir
    public static String redondearYPresentar(double num){
        num = Math.round(num*100.0)/100.0;
        return String.format("$"+"%.2f", num);
    }
}
