/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantico;

import java.util.LinkedHashMap;

/**
 *
 * @author DiegoAlvarez
 */
public class Dato {
    public boolean funcion = false;
    public String tipo;
    public String valor;
    public LinkedHashMap<String,Dato> parametros = null;
    
    public void funcion(){
        this.funcion = true;
        this.parametros = new LinkedHashMap<>();
    }
}
