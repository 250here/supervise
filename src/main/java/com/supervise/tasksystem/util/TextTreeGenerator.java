package com.supervise.tasksystem.util;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class TextTreeGenerator {
    private String text;
    private boolean isLineStart=true;
    @Getter
    @Setter
    int indentLevel=0;
    final static String indentStr="  ";
    public TextTreeGenerator(){
        text="";
    }
    public void left(){
        indentLevel--;
    }
    public void right(){
        indentLevel++;
    }
    public void add(String s){
        String str=s;
        if(str.indexOf('\n')>=0){
            str.replaceAll("\n","\\n");
        }
        if(isLineStart){
            addIndent();
        }
        text+=str;
    }
    public void addSpace(String s){
        add(s);
        text+=" ";
    }
    public void addLine(String s){
        add(s);
        text+="\n";
        isLineStart=true;
    }
    public void addLine(){
        addLine("");
    }
    public void addPair(String k,String v){
        add(k+":"+v+", ");
    }
    public void addPair(String k,int v){
        add(k+":"+v+", ");
    }
    public void addPair(String k, Date v){
        add(k+":"+v.toString()+", ");
    }
    public void addPair(String k, boolean v){
        add(k+":"+(v?"是":"否")+", ");
    }
    private void addIndent(){
        for(int i=0;i<indentLevel;i++){
            text+=indentStr;
        }
        isLineStart=false;
    }

    public String getText() {
        return text+'\n';
    }
}
