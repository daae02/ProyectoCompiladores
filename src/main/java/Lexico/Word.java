/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexico;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import misc.Painter;


public class Word {
    public String word;
    public String token;
    public int aparitions;
    public Map<Integer,Integer> lines;
    public Color col;
    public Word(String word, String token,int line) {
        this.word = word;
        this.token = token;
        this.aparitions = 1;
        lines = new HashMap<Integer, Integer>();
        lines.put(line,1);
        this.col = Painter.generateColor(token);
    }
    void addAparition(int line){
        if (lines.containsKey(line)){
            lines.put(line, lines.get(line) + 1);
        }
        else
            lines.put(line, 1);
        aparitions++;
    }
    String printLines(){
        String res= "[";
        Integer[] keys = lines.keySet().toArray(new Integer[lines.keySet().size()]);
        for (int i = 0; i < keys.length; i++){
            res+=keys[i];
            if (lines.get(keys[i])>1)
                res+="("+lines.get(keys[i])+")";
            if (i+1<keys.length)
                res+=", ";
        }
        return res+"]";
    }
}
