/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sintactico;

/**
 *
 * @author DiegoAlvarez
 */
public class ErrorSintactico {
    String error;
    String descripcion;
    int linea;
    public ErrorSintactico(String error, String descripcion) {
        this.error = error;
        this.descripcion = descripcion;
        this.linea = 0;
        
    }
    @Override
    public String toString(){
        return error+"\n"+descripcion+"\n"+linea;
    }
}
