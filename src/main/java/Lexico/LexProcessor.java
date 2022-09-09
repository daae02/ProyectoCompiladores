/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexico;

import GUI.Upload;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DiegoAlvarez
 */
public class LexProcessor {
    Reader read;
    public String simpleAnalisis(String path){
        try {
                read = new BufferedReader(new FileReader(path));
                Lexer lexer = new Lexer(read);
                String res = "";
                while(true){
                    Tokens  tokens = lexer.yylex();
                    if (tokens == null){
                        res += "FIN";
                        System.out.println(res);
                        return res;
                    }
                    switch (tokens) {
                        case ERROR:
                            res += "Simbolo no definido\n";
                            break;
                        case Identificador: case Numero: case Reservadas:
                            res += lexer.lexeme + ": Es un " + tokens + "\n";
                            break;
                        default:
                            res += "Token: " + tokens + "\n";
                            break;
                    }                  

                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
            }
        return "Couldn't access the file";
    }
}
