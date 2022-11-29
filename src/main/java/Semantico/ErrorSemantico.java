/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantico;

import Sintactico.*;
import java_cup.runtime.Symbol;

/**
 *
 * @author DiegoAlvarez
 */
public class ErrorSemantico {
    public String error;
    public String descripcion;
    public int fila;
    public int col;

/*
    ErrorSemantico(Symbol s, String desc) {
        this.error = s.value.toString();
        this.descripcion = desc;
        this.fila = s.left+1;
        this.col = s.right+1;
    }
    ErrorSemantico(Symbol s, String desc, String name) {
        this.error = name;
        this.descripcion = desc;
        this.fila = s.left+1;
        this.col = s.right+1;
    } */
    ErrorSemantico(int f, int c, String desc, String name) {
        this.error = name;
        this.descripcion = desc;
        this.fila = c+1;
        this.col = f+1;
    }
    @Override
    public String toString(){
        return error+"\n"+descripcion+"\n"+fila+", "+col;
    }
}
