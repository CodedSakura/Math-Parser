package eu.thephisics101.parser;

/**
 * Created by ThePhisics101 on 20/04/2017.
 *
 * Scanner class
 */

class Scanner {
    private String sourceText;
    private int index = -1;

    Scanner(String input) {
        sourceText = input;
    }

    Character get() {
        index++;
        if (index >= sourceText.length())
            return new Character('\0', index, sourceText);
        return new Character(sourceText.charAt(index), index, sourceText);
    }

    String getNext() {
        int index = this.index + 1;
        if (index >= sourceText.length())
            return "\0";
        return sourceText.substring(index, index + 1);
    }
}
