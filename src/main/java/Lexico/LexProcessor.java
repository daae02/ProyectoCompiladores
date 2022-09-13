/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexico;

import GUI.ResultPanel;
import GUI.ResultsPanel;
import GUI.Upload;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;

/**
 *
 * @author DiegoAlvarez
 */
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
            System.out.println(cword.word+" "+cword.token+" "+cword.aparitions+" "+cword.printLines());
            System.out.println("color: "+cword.col.toString());
        }
    }
    public void simpleAnalisis(String path){
        try {
                read = new BufferedReader(new FileReader(path));
                Lexer lexer = new Lexer(read);
                results = new  ArrayList<>();
                errors = new  ArrayList<>();
                while(true){
                    Tokens  tokens = lexer.yylex();
                    if (tokens == null){
                        System.out.println("RESULTADOS");
                        printResults(results);
                        System.out.println("ERRORES");
                        printResults(errors);
                        showResults();
                        showErrors();
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
    private ResultPanel createPanel(Word panelWord){
        return new ResultPanel(String.valueOf(panelWord.aparitions),panelWord.printLines(),panelWord.token,panelWord.word);
        
        
    }
    private void showResults(){
        panel = new ResultsPanel();
        javax.swing.JPanel cont = new javax.swing.JPanel();
        cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
        panel.addWords(new ResultPanel("Apariciones","Lineas","Identificador","Token"),cont);
        for (int i = 0; i < results.size(); i++) {
            panel.addWords(createPanel(results.get(i)),cont,results.get(i).col);
        }
        panel.setVisible(true);
    }
    private void showErrors(){
        if (errors.size() > 0){
            panel = new ResultsPanel();
            javax.swing.JPanel cont = new javax.swing.JPanel();
            cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
            panel.addWords(new ResultPanel("Apariciones","Lineas","Identificador","Token"),cont);
            for (int i = 0; i < results.size(); i++) {
                panel.addWords(createPanel(results.get(i)),cont,Color.RED);
            }
            panel.setVisible(true);
        }
    }
}
