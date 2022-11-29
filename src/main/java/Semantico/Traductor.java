/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;
import java_cup.runtime.Symbol;

/**
 *
 * @author DiegoAlvarez
 */
public class Traductor {
    
    private static String ensamblador;
    
    public static void recuerdaId(String token) {

        RSFuncion rs_funcion = (RSFuncion) PilaSemantica.search("Funcion");
        if (rs_funcion == null) {
            RSIdentificador rs_id = new RSIdentificador(token);
            PilaSemantica.push(rs_id);
        } else {
            RSTipo tipoParametro = (RSTipo)PilaSemantica.pop();
            RSFuncion rsFuncion = (RSFuncion) PilaSemantica.peek();
            Dato dato = new Dato();
            dato.tipo = tipoParametro.tipo; //sacar el tipo del parametro
            rsFuncion.dato.parametros.put(token, dato);
        }

    }
    
    public static void recuerdaTipo(String token) {
        RSTipo rs_tipo = new RSTipo(token);
        PilaSemantica.push(rs_tipo);
        
    }
    
    public static void insertarTS (int fila, int columna) {
        System.out.println("Antes");
        System.out.println(TablaSimbolos.tabla.toString());
        PilaSemantica.printArray();
        RSTipo rs_tipo = (RSTipo) PilaSemantica.search("Tipo");
        RSIdentificador rs_id;
        Dato dato;
        while (!PilaSemantica.peek().name.equals("Tipo")) {
            rs_id = (RSIdentificador) PilaSemantica.pop();  
            System.out.println("Popea de la pila:"+rs_id.nombre);
            System.out.println(!TablaSimbolos.tabla.containsKey(rs_id.nombre));
            if (!TablaSimbolos.tabla.containsKey(rs_id.nombre)) {
                System.out.println("No se encontró en la tabla");
                dato = new Dato();
                dato.tipo = rs_tipo.tipo;
                TablaSimbolos.tabla.put(rs_id.nombre, dato);
            } else {
                System.out.println(rs_id.nombre+" ya se registró");
                System.out.println(fila+", "+columna);
                ErrorSemantico error = new ErrorSemantico(fila,columna, "Error: Variable doblemente definida", rs_id.nombre);
                ListaErroresSemantico.erroresSemanticos.add(error);
            }
        }
        System.out.println("Despues");
        System.out.println(TablaSimbolos.tabla.toString());
        PilaSemantica.pop();
        PilaSemantica.printArray();
        
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
    
    public static void recuerdaVariable(String token, int fila, int columna) {
        
        String tipo = "";
        String valor = "";
        Dato dato = new Dato();
    
        RSDataObject rs_do = new RSDataObject();
        rs_do.tipo = DOType.DREGISTRO;
        rs_do.value = token;
        
        if (!TablaSimbolos.tabla.containsKey(rs_do.value)) {
            dato.valor = "Error";
            TablaSimbolos.tabla.put(rs_do.value, dato);
            ErrorSemantico error = new ErrorSemantico(fila, columna, "Error: Variable no definida", token);
            ListaErroresSemantico.erroresSemanticos.add(error);
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
        double num = 0;
        switch(operador) {
            case "+":
                num = Double.parseDouble(value1) + Double.parseDouble(value2);
                break;
            case "-":
                num = Double.parseDouble(value1) - Double.parseDouble(value2);
                break;
            case "*":
                num = Double.parseDouble(value1) * Double.parseDouble(value2);
                break;
            case "/":
                num = Double.parseDouble(value1) / Double.parseDouble(value2);
                break;
            case "%":
                num = Double.parseDouble(value1) % Double.parseDouble(value2);
                break;
        }
        resultado = Double.toString(num);
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
        System.out.println("Recuerda funcion");
        RSFuncion rsFuncion = new RSFuncion();
        rsFuncion.id = token; //Guarda el nombre de la funcion
        RSTipo valorRetorno = (RSTipo) PilaSemantica.pop();
        Dato tipoFuncion = new Dato();
        rsFuncion.dato = tipoFuncion;
        tipoFuncion.tipo = valorRetorno.tipo; //Guarda el tipo que retorna la funcion
        tipoFuncion.funcion(); //Si es una funcion
        
        PilaSemantica.push(rsFuncion);
    }
    
    
    public static void insertarFuncion(int fila,int columna){
        RSFuncion rsFuncion = (RSFuncion)PilaSemantica.pop();
        if (!TablaSimbolos.tabla.containsKey(rsFuncion.id)) {
            TablaSimbolos.tabla.put(rsFuncion.id, rsFuncion.dato);
            System.out.println("No se encontró");
        } else {
            ErrorSemantico error = new ErrorSemantico(fila, columna, "Error: Funcion ya definida", rsFuncion.id);
            ListaErroresSemantico.erroresSemanticos.add(error);
            System.out.println("Se encontró");
        }
    }
  
    public static void recuerdaLlamadoFuncion(String token){
        RSFuncion rsFuncion = new RSFuncion();
        rsFuncion.id = token; //Guarda el nombre de la funcion
        Dato tipoFuncion = new Dato();
        rsFuncion.dato = tipoFuncion;
        tipoFuncion.funcion(); //Confirmar que es una funcion para iniciar los parametros
        
        PilaSemantica.push(rsFuncion);
    }
    
    public static void sacarParametros(String token){
        RSIdentificador dataO = new RSIdentificador(token);
        PilaSemantica.push(dataO);
    }
    private static String esConstante(String num){
        Pattern intP = Pattern.compile("^\\d+$");
        Pattern floatP = Pattern.compile("^([+-]?\\d*.\\d*)$");
        Pattern charP = Pattern.compile("^\'[ A-Za-z0-9_@./#&+-]\'$");
        if (intP.matcher(num).matches()){
            return "int";
        }
        else if (floatP.matcher(num).matches()){
            return "float";
        }
        else if (charP.matcher(num).matches()){
            return "char";
        }
        else{
            return "no";
        }
    }
    public static void testFuncion(int fila,int columna){
        RSFuncion rsFuncion = (RSFuncion)PilaSemantica.pop();
        Dato datoFuncion;
        
        ArrayList<RSIdentificador> parametrosEntrada = new ArrayList();
        while (!PilaSemantica.peek().name.equals("Funcion")){
            parametrosEntrada.add( (RSIdentificador)PilaSemantica.pop());
        }
        
        if (TablaSimbolos.tabla.containsKey(rsFuncion.id)) {
            datoFuncion = TablaSimbolos.tabla.get(rsFuncion.id);
            int largoPFuncion = datoFuncion.parametros.keySet().size();
            if (largoPFuncion != parametrosEntrada.size()){
                ErrorSemantico error = new ErrorSemantico(fila, columna, "Error: Parametros recibidos: "+parametrosEntrada.size()+" esperados: "+largoPFuncion, rsFuncion.id);
                ListaErroresSemantico.erroresSemanticos.add(error);
            }
            else{
                ArrayList<String> nParametros = new ArrayList<>(datoFuncion.parametros.keySet());
                Collections.reverse(nParametros);
                for (int i = parametrosEntrada.size()-1; i >= 0; i--) {
                    if(TablaSimbolos.tabla.containsKey(parametrosEntrada.get(i).nombre)){ 
                        if (!datoFuncion.parametros.get(nParametros.get(i)).tipo.equals(TablaSimbolos.tabla.get(parametrosEntrada.get(i).nombre))){
                            ErrorSemantico error = new ErrorSemantico(fila, columna, "Error: Parametros no correspondientes", rsFuncion.id);
                            ListaErroresSemantico.erroresSemanticos.add(error);
                        }
                    }
                    else if(!esConstante(parametrosEntrada.get(i).nombre).equals("no")){
                         if (!datoFuncion.parametros.get(nParametros.get(i)).tipo.equals(esConstante(parametrosEntrada.get(i).nombre))){
                            ErrorSemantico error = new ErrorSemantico(fila, columna, "Error: Parametros no correspondientes", rsFuncion.id);
                            ListaErroresSemantico.erroresSemanticos.add(error);
                         }
                    }
                    else{
                        ErrorSemantico error = new ErrorSemantico(fila, columna, "Error: Variable no declarada", rsFuncion.id);
                        ListaErroresSemantico.erroresSemanticos.add(error);
                    }
                }   
            }
        } else {
            ErrorSemantico error = new ErrorSemantico(fila, columna, "Error: Funcion no declarada", rsFuncion.id);
            ListaErroresSemantico.erroresSemanticos.add(error);
        }
    }
}

