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
public class Funcion extends Dato{
    public LinkedHashMap<String,String> parametros;

    public Funcion() {
        this.parametros = new LinkedHashMap();
    }
    
}
