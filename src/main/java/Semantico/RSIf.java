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
public class RSIf extends RegistroSemantico{
    String exitLabel;
    String elseLabel;

    public RSIf(String exitLabel, String elseLabel) {
        this.exitLabel = exitLabel;
        this.elseLabel = elseLabel;
    }
}