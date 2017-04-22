package eu.thephisics101.parser;

/**
 * Created by ThePhisics101 on 20/04/2017.
 *
 * Character class
 */
class Character {
    public char c;
    public int sourceIndex;
    public String sourceText, cargo;

    Character(char c, int sourceIndex, String sourceText) {
        this.c = c;
        cargo = java.lang.Character.toString(c);
        this.sourceIndex = sourceIndex;
        this.sourceText = sourceText;
    }

    @Override
    public String toString() {
        String cargo = java.lang.Character.toString(c);
        String index = Integer.toString(sourceIndex);

        if (cargo.equals(" ")) cargo = "  space";
        else if (cargo.equals("\0")) cargo = "  EoF";

        while (index.length() < 4) index = " " + index;

        return index + "  " + cargo;
    }
}
