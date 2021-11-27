package L1;

import org.antlr.v4.runtime.*;
import java.io.*;
import java.util.*;
import org.antlr.v4.runtime.misc.Interval;


public class Main {


    public static void main(String[] args) throws Exception {

        InputStream is = System.in;
        if (args.length > 0) {
            String inputFile = args[0];
            is = new FileInputStream(inputFile);
        }

        CharStream input = CharStreams.fromStream(is);
        List<String> errorTypeAList = new ArrayList<>();
        CmmLexer lexer = new CmmLexer(input) {
            @Override
            public void notifyListeners(LexerNoViableAltException e) {
                String text = _input.getText(Interval.of(_tokenStartCharIndex, _input.index()));
                String msg = "undefined symbols " + getErrorDisplay(text.trim()) + ".";
                errorTypeAList.add("Error type A at Line " + _tokenStartLine + ": " + msg);
            }
        };

        List<? extends Token> allTokens = lexer.getAllTokens();
        if (errorTypeAList.isEmpty()) {
            for (Token token : allTokens) {
                if (token.getType() >= 1) {
                    String tokenText = getTokenText(token);
                    System.err.println(
                            CmmLexer.ruleNames[token.getType() - 1] + " " + tokenText + " at Line "
                                    + token.getLine() + ".");
                }
            }
        } else {
            for (String errorString : errorTypeAList) {
                System.err.println(errorString);
            }
        }

                /*
        CmmLexer lexer = new CmmLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        tokenStream.fill(); // get all tokens from lexer and store them in tokenStream.tokens
        List<Token> tokenList = tokenStream.getTokens();
        for (Token i: tokenList){
            System.out.println(i.getText());
        }
        List<? extends Token> tokenList2 = lexer.getAllTokens();
        for (Token i: tokenList2){
            System.out.println(i.getText());
        }
        lexer.reset();
        List<? extends Token> tokenList3 = lexer.getAllTokens();
        for (Token i: tokenList3){
            System.out.println(i.getText());
        }
        lexer.reset();
        CommonTokenStream tokenStream1 = new CommonTokenStream(lexer);
        tokenStream1.fill();
        List<Token> tokenList1 = tokenStream1.getTokens();
        for (Token i: tokenList1){
            System.out.println(i.getText());
        }
        */
    }


    public static String getTokenText(Token token) {
        final String tokenText = token.getText();
        if (token.getType() == CmmLexer.INT) {
            if (tokenText.length() >= 2 && tokenText.charAt(0) == '0') {
                if (tokenText.charAt(1) == 'x' || tokenText.charAt(1) == 'X') {
                    return String.valueOf(Integer.valueOf(tokenText.substring(2), 16));
                } else if (tokenText.charAt(1) >= '0' && tokenText.charAt(1) <= '7') {
                    return String.valueOf(Integer.valueOf(tokenText.substring(1), 8));
                }
            }
        } else if (token.getType() == CmmLexer.FLOAT) {
            return String.format("%.6f", Double.parseDouble(tokenText));
        }
        return tokenText;
    }


}