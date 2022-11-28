/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantico;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author DiegoAlvarez
 */
public class TablaSimbolos {
    public static Map<String,Dato> tabla = new LinkedHashMap<>();
    public int largest(){
        int maximo = 0;
        for (String clave:tabla.keySet()) {
            if(tabla.get(clave).funcion && tabla.get(clave).parametros.size()> maximo)
                maximo = tabla.get(clave).parametros.size();
        }
        return maximo;
    }
    @Override
    public String toString(){
        String res = ("CLASE\tNOMBRE\tTIPO\t");
        int parametros = largest();
        for(int i = 0; i<parametros; i++){
            res += ("PARAMETRO #"+i);
        }
        res +="\n";
        for (String clave:tabla.keySet()) {
            String nombre =clave+"\t";
            String tipo = tabla.get(clave).tipo+"\t";
            String par = "";
            if(tabla.get(clave).funcion){
                res+="FUNCION\t";
                par = tabla.get(clave).parametros.keySet().stream().map(nombres -> 
                tabla.get(clave).parametros.get(nombres).tipo+" / "+nombres+"\t").reduce(par, String::concat);}
            else 
                res+="VARIABLE\t";
            res+=nombre+tipo+par+"\n";  
        }
        return res;
    }
}
