/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexico;

import GUI.ResultPanelL;
import GUI.ResultPanelS;
import GUI.ResultsPanel;
import GUI.Upload;
import Semantico.PilaSemantica;
import Sintactico.ErrorSintactico;
import static Sintactico.ListaErrores.errores;
import Sintactico.Sintax;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java_cup.runtime.Symbol;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class LexProcessor {
    Reader read;
    ArrayList<Word> results;
    ArrayList<Word> errors;
    ResultsPanel panel;
    private Word isRegistered(String rWord,ArrayList<Word> array){
        for (int i = 0; i < array.size(); i++) {
            if(array.get(i).word.equals(rWord))
                return array.get(i);
        }
        return null;
    }
    private void addToArray(ArrayList<Word> array,Lexer lex, Tokens tok){
        Word currentWord = isRegistered(lex.lexeme,array);
        if (currentWord != null){
            currentWord.addAparition(lex.line);
        }
        else
            array.add(new Word(lex.lexeme,tok.toString(),lex.line));
    }
    private void printResults(ArrayList<Word> array){
        for (int i = 0; i < array.size(); i++) {
            Word cword = array.get(i);
            System.out.println(cword.word+"\t\t"+cword.token+"\t\t"+cword.aparitions+"\t\t"+cword.printLines());
        }
    }
    private ResultPanelL createPanel(Word panelWord){
        return new ResultPanelL(String.valueOf(panelWord.aparitions),panelWord.printLines(),panelWord.token,panelWord.word);
    }
    private void showResults(int h,int w, String filename){
        printResults(results);
        System.out.println("ERRORES");
        printResults(errors);
        panel = new ResultsPanel();
        panel.setTitle("Tabla de Tokens de "+filename);
        panel.setLocation(w, h);
        javax.swing.JPanel cont = new javax.swing.JPanel();
        cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
        panel.addWords(new ResultPanelL("Apariciones","Lineas","Identificador","Token"),cont);
        for (int i = 0; i < results.size(); i++) {
            panel.addWords(createPanel(results.get(i)),cont,results.get(i).col);
        }
        panel.setVisible(true);
    }
    private void sendMessage(String description, String icon,String filename){
            JFrame mes = new JFrame();  
            JOptionPane.showMessageDialog(mes,description,
            "Resultado de compilación de "+filename,  
            JOptionPane.INFORMATION_MESSAGE,
            new ImageIcon(getClass().getResource(icon)));
    }
    private void showErrors(int h,int w, String filename, int sErrors){
        if (errors.size()+sErrors > 0){
            panel = new ResultsPanel();
            panel.setTitle("Tabla de Errores de "+ filename);
            panel.setLocation(w, h);
            if (errors.size() > 0 ){
                javax.swing.JPanel cont = new javax.swing.JPanel();
                cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
                panel.addWords(new ResultPanelL("Apariciones","Lineas","Identificador","Token"),cont);
                for (int i = 0; i < errors.size(); i++) {
                    panel.addWords(createPanel(errors.get(i)),cont,Color.RED);
                }  
            }
            if (sErrors  > 0 ){
                System.out.println("Entra");
                javax.swing.JPanel cont = new javax.swing.JPanel();
                cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
                panel.addWords(new ResultPanelS("Token","Descripción","Lineas"),cont);
                for (int i = 0; i < errores.size(); i++) {
                    ErrorSintactico e = errores.get(i);
                    panel.addWords(new ResultPanelS(e.error,e.descripcion,e.fila+", "+e.col),cont,Color.RED);
                }  
            }
            panel.setVisible(true);
            sendMessage("Se han encontrado errores.\n No se ha podido compilar.","/cancel.png", filename);
            return;
        }
        
        sendMessage("Compilado con éxito.","/checked.png",filename);
    }
    public void simpleAnalisis(String path){
        try {
            Path f = new File(path).toPath();
            read = Files.newBufferedReader(f,StandardCharsets.UTF_8);
            String filename = new File(path).getName();
            String content = new String(Files.readAllBytes(f),StandardCharsets.UTF_8);
            Lexer lexer = new Lexer(read);
            results = new  ArrayList<>();
            errors = new  ArrayList<>();
            while(true){
                Tokens  tokens = lexer.yylex();
                if (tokens == null){
                    Sintax s;
                    errores = new ArrayList();
                    s = new Sintax(new LexerCup(new StringReader(content)));
                    try {
                        s.parse();
                    } catch (Exception ex) {
                        /*Symbol sym = s.getS();
                        System.out.println("ENCONTRÓ ERROR");
                        System.out.println(String.valueOf(sym.right+1)+" "+String.valueOf(sym.left+1)+" "+String.valueOf(sym.value.toString())+" ");*/
                    }
                    System.out.println(errores.toString());
                    showErrors(0,0,filename, errores.size());
                    return;
                }
                else{
                    switch (tokens) {
                        case ERROR:
                            addToArray(errors,lexer,tokens);
                            break;
                        default:
                            addToArray(results,lexer,tokens);
                            break;
                    }                  
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
