/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantico;

/**
 *
 * @author DiegoAlvarez
 */
public class Traductor {
    
   public static void recuerdaConstante(String token) {

        RSDataObject rs_do = new RSDataObject();
        rs_do.tipo = DOType.CONSTANTE;
        rs_do.value = token;
        PilaSemantica.push(rs_do);
    
    }    
   
    public static void recuerdaOperador(String token) {

        RSOperador rs_operador = new RSOperador();
        rs_operador.operador = token;
        PilaSemantica.push(rs_operador);

    }
    
    public static void recuerdaVariable(String token) {
    
        RSDataObject rs_do = new RSDataObject();
        rs_do.tipo = DOType.DREGISTRO;
        rs_do.value = token;
        
        // if () {
        // } else {
            // Insertar en tabla de símbolos con tag de error
        // } 
      //  PilaSemantica.push(rs_do);
    }
    
    public static void evalExpression () {

        RSDataObject rs_do1;
        RSOperador rs_operador;
        RSDataObject rs_do2;
        RSDataObject rs_do = new RSDataObject();

        rs_do2 = (RSDataObject) PilaSemantica.pop();
        rs_operador = (RSOperador) PilaSemantica.pop();
        rs_do1 = (RSDataObject) PilaSemantica.pop();
        

        if (rs_do2.tipo == DOType.CONSTANTE && rs_do1.tipo == DOType.CONSTANTE) {
            String resultado = "0"; //calcular resultado
            rs_do.tipo = DOType.CONSTANTE;
            rs_do.value = resultado;
        } else {
            rs_do.tipo = DOType.DREGISTRO;
            //rs_do.value = token;
            //devolver el == o el >= <=  > < 
        }

    }
    
}
