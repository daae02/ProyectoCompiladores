/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    
    public static void main(String[] args) throws Exception {
        String ruta1 = "./src/main/java/Lexico/Lexer.flex";
        String ruta2 = "./src/main/java/Lexico/LexerSintactico.flex";
        String[] rutaS = {"-parser", "Sintax", "./src/main/java/Sintactico/Sintax.cup"};
        generar(ruta1, ruta2, rutaS);
    }
    public static void generar(String ruta1, String ruta2, String[] rutaS) throws IOException, Exception{
        File archivo;
        archivo = new File(ruta1);
        JFlex.Main.generate(archivo);
        archivo = new File(ruta2);
        JFlex.Main.generate(archivo);
        System.out.println("Run cup");
        
        java_cup.Main.main(rutaS);
        System.out.println("Runned cup");
        Path rutaSym = Paths.get("./src/main/java/Sintactico/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get("./sym.java"), 
                Paths.get("./src/main/java/Sintactico/sym.java")
        );
        Path rutaSin = Paths.get("./src/main/java/Sintactico/Sintax.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get("./Sintax.java"), 
                Paths.get("./src/main/java/Sintactico/Sintax.java")
        );
    }
}