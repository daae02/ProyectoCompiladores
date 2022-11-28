/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sintactico;

import java.util.ArrayList;

/**
 *
 * @author DiegoAlvarez
 */
public class ListaErroresSintactico {
    public static ArrayList<ErrorSintactico> errores = new ArrayList<ErrorSintactico>();
    @Override
    public String toString(){
        String res= "";
        res = errores.stream().map(error -> error.toString()).reduce(res, String::concat);
        return res;
    }
}
