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
    String traductor;
    
    public void startIf(int linea){
        RSIf rsIf = new RSIf("exitLabel" + linea, "elseLabel" + linea);
        PilaSemantica.push(rsIf);
    }
    
    public void testIF(){
        //realizar el pop del RSDO de la pila
        RSDataObject rsDO = (RSDataObject) PilaSemantica.pop(); //Obtiene el rsdo
        //Generar el codigo de la evaluacion segun la doreccion del rs_do
        //No se como se evalua??????? AYUDAAAAAAAAAAAAAAAA
        
        //Generar Jump Condicional con rsif
        String jmp = "je " + rsDO.value;
        traductor += "\n" + jmp;
    }
    
    public void startElse(){
        //Generar "JUMP" + RS_IF.exit_label
        RSIf rsif = (RSIf) PilaSemantica.peek(); //Verificar si siempre sera el tope o se tiene que buscar
        String jumpExit = "jmp" + rsif.exitLabel;
        this.traductor += "\n" + jumpExit;
        
        // Generar RS_EF.else_label + ":"
        String elseLabel = rsif.elseLabel + ":";
        this.traductor += "\n" + elseLabel;
    }
    
    public void endIF(){
        //Generar RS_IF.exit_label + ":"
        //POP RS_IF la pila queda igual que antes del IF
        RSIf rsif = (RSIf) PilaSemantica.pop(); //Verificar si siempre sera el tope o se tiene que buscar
        String exitLabel = rsif.exitLabel + ":";
        this.traductor += "\n" + exitLabel;
    }
}
