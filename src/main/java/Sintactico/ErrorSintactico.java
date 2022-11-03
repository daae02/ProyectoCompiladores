/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sintactico;

import java_cup.runtime.Symbol;

/**
 *
 * @author DiegoAlvarez
 */
public class ErrorSintactico {
    public String error;
    public String descripcion;
    public int fila;
    public int col;


    ErrorSintactico(Symbol s, String desc) {
        this.error = s.value.toString();
        this.descripcion = desc;
        this.fila = s.left+1;
        this.col = s.right+1;
    }
    @Override
    public String toString(){
        return error+"\n"+descripcion+"\n"+fila+", "+col;
    }
}
