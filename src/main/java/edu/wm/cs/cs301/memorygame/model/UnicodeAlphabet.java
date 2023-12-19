package edu.wm.cs.cs301.memorygame.model;

public class UnicodeAlphabet implements Alphabet {
    private char[] symbols;

    public UnicodeAlphabet() {
    	this.symbols = new char[] {
    			'\u2723', '\u2724', '\u2725', '\u2726', '\u2727', '\u2729', '\u272A', 
    			'\u272B', '\u272C', '\u272D', '\u272E', '\u272F', '\u2730', '\u2731', 
    			'\u2732', '\u2733', '\u2734', '\u2735', '\u2736', '\u2737', '\u2738', 
    			'\u2739', '\u273A', '\u273B', '\u273C', '\u273D', '\u273E', '\u273F' 
        };
    }

    @Override
    public char[] toCharArray() {
        return symbols;
    }
}