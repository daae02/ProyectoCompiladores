/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantico;

import java_cup.runtime.Symbol;
import org.graalvm.compiler.nodes.util.ConstantFoldUtil;

/**
 *
 * @author DiegoAlvarez
 */
public class Traductor {
    
    private static String ensamblador;
    
    public static void recuerdaId(String token) {
        
        RSIdentificador rs_id = new RSIdentificador(token);
        PilaSemantica.push(rs_id);
        
    }
    
    public static void recuerdaTipo(String token) {
        
        RSTipo rs_tipo = new RSTipo(token);
        PilaSemantica.push(rs_tipo);
        
    }
    
    public static void insertarTS (Symbol symbol) {
        
        RSTipo rs_tipo = (RSTipo) PilaSemantica.search("Tipo");
        RSIdentificador rs_id;
        Dato dato;
        while (!PilaSemantica.peek().name.equals("Tipo")) {
            rs_id = (RSIdentificador) PilaSemantica.pop();         
            if (!TablaSimbolos.tabla.containsKey(rs_id.nombre)) {
                dato = new Dato();
                dato.tipo = rs_tipo.tipo;
                TablaSimbolos.tabla.put(rs_id.nombre, dato);
            } else {
                ErrorSemantico error = new ErrorSemantico(symbol, "Error: Variable no definida", rs_id.nombre);
                ListaErroresSemantico.erroresSemanticos.add(error);
            }
        }
        
    }
    
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
        
        String tipo = "";
        String valor = "";
        Dato dato = new Dato();
    
        RSDataObject rs_do = new RSDataObject();
        rs_do.tipo = DOType.DREGISTRO;
        rs_do.value = token;
        
        if (!TablaSimbolos.tabla.containsKey(rs_do.value)) {
            dato.valor = "Error";
            TablaSimbolos.tabla.put(rs_do.value, dato);
        }
        PilaSemantica.push(rs_do);
        
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
            String resultado = constantFolding(rs_do1.value, rs_do2.value, rs_operador.operador);
            rs_do.tipo = DOType.CONSTANTE;
            rs_do.value = resultado;
        } else {
            rs_do.tipo = DOType.DREGISTRO;
            rs_do.value = rs_operador.operador;
            //devolver el == o el >= <=  > < 
        }
        
        PilaSemantica.push(rs_do);

    }
    
    public static String constantFolding (String value1, String value2, String operador) {
        String resultado = "";
        int num = 0;
        switch(operador) {
            case "+":
                num = Integer.parseInt(value1) + Integer.parseInt(value2);
                break;
            case "-":
                num = Integer.parseInt(value1) - Integer.parseInt(value2);
                break;
            case "*":
                num = Integer.parseInt(value1) * Integer.parseInt(value2);
                break;
            case "/":
                num = Integer.parseInt(value1) / Integer.parseInt(value2);
                break;
            case "%":
                num = Integer.parseInt(value1) % Integer.parseInt(value2);
                break;
        }
        resultado = Integer.toString(num);
        return resultado;
    }
    
    public static void startIf(int linea){
        RSIf rsIf = new RSIf("exitLabel" + linea, "elseLabel" + linea);
        PilaSemantica.push(rsIf);
    }
    
    public static void testIF(){
        //realizar el pop del RSDO de la pila
        RSDataObject rsDO = (RSDataObject) PilaSemantica.pop(); //Obtiene el rsdo
        //Generar el codigo de la evaluacion segun la doreccion del rs_do
        //No se como se evalua??????? AYUDAAAAAAAAAAAAAAAA
        String jmp = "";
        RSIf rsif = (RSIf) PilaSemantica.peek(); //Verificar si siempre sera el tope o se tiene que buscar
        switch(rsDO.value){
            case "==":
                jmp = "jz " + rsif.elseLabel;
                break;
            case "<":
                jmp = "jb " + rsif.elseLabel;
                break;
            case ">":
                jmp = "ja " + rsif.elseLabel;
                break;
            case "<=":
                jmp = "jbe " + rsif.elseLabel;
                break;
            case ">=":
                jmp = "jae " + rsif.elseLabel;
                break;
            default:
                jmp = "jz " + rsif.elseLabel;
                break;      
        }
        ensamblador += "\n" + jmp;
    }
    
    public static void startElse(){
        //Generar "JUMP" + RS_IF.exit_label
        RSIf rsif = (RSIf) PilaSemantica.peek(); //Verificar si siempre sera el tope o se tiene que buscar
        String jumpExit = "jmp" + rsif.exitLabel;
        ensamblador += "\n" + jumpExit;
        
        // Generar RS_EF.else_label + ":"
        String elseLabel = rsif.elseLabel + ":";
        ensamblador += "\n" + elseLabel;
    }
    
    public static void endIF(){
        //Generar RS_IF.exit_label + ":"
        //POP RS_IF la pila queda igual que antes del IF
        RSIf rsif = (RSIf) PilaSemantica.pop(); //Verificar si siempre sera el tope o se tiene que buscar
        String exitLabel = rsif.exitLabel + ":";
        ensamblador += "\n" + exitLabel;
    }

    public static String getEnsamblador() {
        return ensamblador;
    }
    
    public static void recuerdaFuncion(String token){
        RSFuncion rsFuncion = new RSFuncion();
        rsFuncion.name = token; //Guarda el nombre de la funcion
        RSTipo valorRetorno = (RSTipo) PilaSemantica.pop();
        Dato tipoFuncion = new Dato();
        rsFuncion.dato = tipoFuncion;
        tipoFuncion.tipo = valorRetorno.tipo; //Guarda el tipo que retorna la funcion
        tipoFuncion.funcion = true; //Si es una funcion
        
        PilaSemantica.push(rsFuncion);
    }
    
    
  
}

