package edu.wm.cs.cs301.memorygame.model;

public class CherokeeAlphabet implements Alphabet {
    private char[] symbols;

    public CherokeeAlphabet() {
        this.symbols = new char[] {
            'Ꭰ', 'Ꭱ', 'Ꭲ', 'Ꭳ', 'Ꭴ', 'Ꭵ', 'Ꭶ', 'Ꭷ', 'Ꭸ', 'Ꭹ', 'Ꭺ', 'Ꭻ', 'Ꭼ', 'Ꭽ',
            'Ꭾ', 'Ꭿ', 'Ꮀ', 'Ꮁ', 'Ꮂ', 'Ꮃ', 'Ꮄ', 'Ꮅ', 'Ꮆ', 'Ꮇ', 'Ꮈ', 'Ꮉ', 'Ꮊ', 'Ꮋ'
        };
    }

    @Override
    public char[] toCharArray() {
        return symbols;
    }
}