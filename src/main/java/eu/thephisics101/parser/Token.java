package eu.thephisics101.parser;

/**
 * Created by ThePhisics101 on 20/04/2017.
 *
 * Token class
 */
class Token {
    static final String IDENTIFIER = "Identifier";
    static final String NUMBER = "Number";
    static final String WHITESPACE = "Whitespace";
    static final String COMMENT = "Comment";
    static final String EOF = "EoF";

    String sourceText;
    String type = null, cargo;
    int sourceIndex;

    Token(Character startChar) {
        cargo = java.lang.Character.toString(startChar.c);
        sourceText = startChar.sourceText;
        sourceIndex = startChar.sourceIndex;
    }

    @Override
    public String toString() {
        String type = this.type;
        while (type.length() < 12) type += ".";

        String index = Integer.toString(sourceIndex);
        while (index.length() < 4) index = " " + index;

        String output = "  " + index + "  ";

        if (this.type.equals(cargo))
            output += "Symbol......: " + cargo;
        else if (cargo.equals(" "))
            output += type + ":   space";
        else
            output += type + ": " + cargo;
        return output;
    }


}
