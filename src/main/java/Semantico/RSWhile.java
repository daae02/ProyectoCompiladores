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
public class RSWhile extends RegistroSemantico{
    String exitLabel;
    String whileLabel;

    public RSWhile(String exitLabel, String whileLabel) {
        this.exitLabel = exitLabel;
        this.whileLabel = whileLabel;
    }

}