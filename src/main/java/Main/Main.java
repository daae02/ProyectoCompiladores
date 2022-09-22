/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.File;

/**
 *
 * @author DiegoAlvarez
 */
public class Main {
    public static void main(String[] args) {
        String path = "./src/main/java/Lexico/Lexer.flex";
        generateLexer(path);
    
    }
    public static void generateLexer(String path){
        File jflexFile = new File(path);
        JFlex.Main.generate(jflexFile);
    }
}
