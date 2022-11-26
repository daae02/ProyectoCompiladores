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
    private static String ensamblador;
    
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
}
