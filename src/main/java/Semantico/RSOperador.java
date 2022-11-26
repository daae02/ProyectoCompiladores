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
public class RSOperador extends RegistroSemantico{
    String operador;
    public RSOperador(String operador){
        this.operador = operador;
        this.name = "Operador";
    }
}