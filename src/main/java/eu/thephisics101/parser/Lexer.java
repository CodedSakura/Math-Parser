package eu.thephisics101.parser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ThePhisics101 on 17.20.4.
 *
 * Lexer class
 */
class Lexer {
    private Scanner scanner;
    private String c1, c2;
    private Character character;
    private List whitespace = Arrays.asList(" ", "\t", "\n"), keywords = Arrays.asList(
            "tau",
            "abs",
            "acos", "cos", "cosh",
            "asin", "sin", "sinh",
            "atan", "atan2", "tan", "tanh",
            "cbrt", "cb",
            "exp", "expm1",
            "ceil", "floor", "round",
            "log", "log10", "loglp",
            "random",
            "signum",
            "sqrt", "sqr",
            "toDegrees", "deg",
            "toRadians", "rad",
            "toInteger", "int"
    ), oneCharSymbols = Arrays.asList(
            "=",
            "(", ")",
            "<", ">",
            "/", "*", "+", "-",
            "!", "^",
            ".",
            "&", "|",
            "e"
    ), twoCharSymbols = Arrays.asList(
            "==",
            "<=", ">=",
            "<>", "!=",
            "**",
            "&&", "||",
            "++", "--",
            "+=", "-=",
            "pi"
    );

    Lexer(String sourceText) {
        scanner = new Scanner(sourceText);
        getChar();
    }

    Token get() throws TokenException {
        Token token;

        // Read past all comments and whitespace
        while (whitespace.contains(c1) || c2.equals("/*")) {

            while (whitespace.contains(c1)) {
                token = new Token(character);
                token.type = Token.WHITESPACE;
                getChar();

                while (whitespace.contains(c1)) {
                    token.cargo += c1;
                    getChar();
                }

//                return token;  // only if we want the lexer to return whitespace
            }

            while (c2.equals("/*")) {
                token = new Token(character);
                token.type = Token.COMMENT;
                token.cargo = c2;

                getChar();
                getChar();

                while (!c2.equals("*/")) {
                    if (c1.equals("\0"))
                        throw new TokenException("Found end of file before end of comment");
                    token.cargo += c1;
                    getChar();
                }

                token.cargo += c2;

                getChar();
                getChar();

//                return token; // only if we want the lexer to return comments
            }

        }

        token = new Token(character);

        if (c1.equals("\0")) {
            token.type = Token.EOF;
            return token;
        }

        if (c1.matches("[a-zA-Z]")) {
            token.type = Token.IDENTIFIER;
            getChar();

            while (c1.matches("[a-zA-Z0-9_]")) {
                token.cargo += c1;
                getChar();
            }

            if (keywords.contains(token.cargo.toLowerCase()))
                token.type = token.cargo;
            return token;
        }

        if (c1.matches("[0-9.]")) {
            token.type = Token.NUMBER;
            getChar();

            while (c1.matches("[0-9]*(\\.|)[0-9]*([0-9]+(\\.|)[0-9]*E[0-9]*|)")) {
                token.cargo += c1;
                getChar();
            }
            return token;
        }

        if (twoCharSymbols.contains(c2)) {
            token.cargo = c2;
            token.type = c2;
            getChar();
            getChar();
            return token;
        }

        if (oneCharSymbols.contains(c1)) {
            token.type = c1;
            getChar();
            return token;
        }

        throw new TokenException("Unrecognized character or symbol found: " + c1);
    }

    private void getChar() {
        character = scanner.get();
        c1 = character.cargo;
        c2 = c1 + scanner.getNext();
    }
}
