/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.awt.Color;

/**
 *
 * @author DiegoAlvarez
 */
public class Painter {
    public static Color generateColor(String token){
        switch(token){
            case "Reservadas": return Color.ORANGE;
            case "Identificador": return Color.BLUE;
            case "OperadorIgual":
            case"OperadorGato":
            case "OperadorSuma":
            case "OperadorResta":
            case "OperadorMultiplicacion":
            case "OperadorDivision":
            case "OperadorComa":
            case "OperadorPuntoComa":
            case "OperadorIncremento":
            case "OperadorDecremento":
            case "OperadorIgualIgual":
            case "OperadorMayorIgual":
            case "OperadorMayor":
            case "OperadorPregunta":
            case "OperadorMenorIgual":
            case "OperadorMenor":
            case "OperadorDiferente":
            case "OperadorO":
            case "OperadorY":
            case "OperadorNegacion":
            case "OperadorModulo":
            case "OperadorParentesisIz":
            case "OperadorParentesisDer":
            case "OperadorParCuadradoIz":
            case "OperadorParCuadradoDer":
            case "OperadorCorcheteIz":
            case "OperadorCorcheteDer":
            case "OperadorDoblePunto":
            case "OperadorPunto":
            case "OperadorSumaAsignacion":
            case "OperadorRestaAsignacion":
            case "OperadorMultAsignacion":
            case "OperadorDivAsignacion":
            case "OperadorDireccion":
            case "OperadorXOR":
            case "OperadorOR":
            case "OperadorDesplDer":
            case "OperadorDesplIz":
            case "OperadorComplementoAUno":
            case "OperadorModuloAsignacion":
            case "OperadorANDAsignacion":
            case "OperadorXORAsignacion":
            case "OperadorORAsignacion":
            case "OperadorDesplIzAsig":
            case "OperadorDesplDerAsig":
            case "OperadorMiembroPuntero": return Color.MAGENTA;
            case "Entero":
            case "Flotante":
            case "FlotanteExponente" :
            case "Octal":
            case "OctalFlotante":
            case "Hexadecimal":
            case "HexadecimalFlotante": return Color.darkGray;
            case "Hilera":
            case "Caracter" : return Color.GREEN;
            case "ERROR": return Color.RED;
        }
        return Color.BLACK;
    }
}
