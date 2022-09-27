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
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            System.out.println(cword.word+" "+cword.token+" "+cword.aparitions+" "+cword.printLines());
        }
    }
    private ResultPanel createPanel(Word panelWord){
        return new ResultPanel(String.valueOf(panelWord.aparitions),panelWord.printLines(),panelWord.token,panelWord.word);
    }
    private void showResults(int h,int w, String filename){
        panel = new ResultsPanel();
        panel.setTitle("Tabla de Tokens de "+filename);
        panel.setLocation(w, h);
        javax.swing.JPanel cont = new javax.swing.JPanel();
        cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
        panel.addWords(new ResultPanel("Apariciones","Lineas","Identificador","Token"),cont);
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
    private void showErrors(int h,int w, String filename){
        if (errors.size() > 0){
            panel = new ResultsPanel();
            panel.setTitle("Tabla de Errores de "+ filename);
            panel.setLocation(w, h);
            javax.swing.JPanel cont = new javax.swing.JPanel();
            cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
            panel.addWords(new ResultPanel("Apariciones","Lineas","Identificador","Token"),cont);
            for (int i = 0; i < errors.size(); i++) {
                panel.addWords(createPanel(errors.get(i)),cont,Color.RED);
            }
            panel.setVisible(true);
            sendMessage("Se han encontrado errores.\n No se ha podido compilar.","/cancel.png", filename);
            return;
        }
        sendMessage("Compilado con éxito.","/checked.png",filename);
    }
    public void simpleAnalisis(String path){
        try {
            read = Files.newBufferedReader(new File(path).toPath(),StandardCharsets.UTF_8);
            String filename = new File(path).getName();
            Lexer lexer = new Lexer(read);
            results = new  ArrayList<>();
            errors = new  ArrayList<>();
            while(true){
                Tokens  tokens = lexer.yylex();
                if (tokens == null){
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    showResults(0,0,filename);
                    showErrors(screenSize.height/3,0,filename);
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
