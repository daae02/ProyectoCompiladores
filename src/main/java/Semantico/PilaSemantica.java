/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantico;

import java.util.ArrayList;

/**
 *
 * @author DiegoAlvarez
 */
public class PilaSemantica {
    public static ArrayList<RegistroSemantico> stack = new ArrayList();
    public static RegistroSemantico peek(){
        return stack.get(stack.size()-1);
    }
    public static RegistroSemantico pop(){
         RegistroSemantico aux =  stack.get(stack.size()-1);
         stack.remove(aux);
         return aux;
    }
    public static void push(RegistroSemantico newSR){
        stack.add(newSR);
    }
    public static RegistroSemantico search(RegistroSemantico SR){
        for (RegistroSemantico next : stack) {
            if (next.equals(SR))
                return next;
        }
        return null;
    }
    public static void printArray() {
        for (RegistroSemantico registroSemantico : stack) {
            System.out.println(registroSemantico.toString());
        }
    }
}
