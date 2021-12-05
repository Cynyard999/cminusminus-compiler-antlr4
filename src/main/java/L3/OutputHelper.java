package L3;

import java.util.List;
import org.antlr.v4.runtime.Token;

/**
 * @author cynyard
 * @description
 * @date 12/4/21
 */
public class OutputHelper {

    public static void printTokens(List<? extends Token> allTokens) {
        for (Token token : allTokens) {
            if (token.getType() >= 1) {
                String tokenText = getTokenText(token);
                System.err.println(
                        CmmLexer.ruleNames[token.getType() - 1] + " " + tokenText + " at Line "
                                + token.getLine() + ".");
            }
        }
    }

    private static String getTokenText(Token token) {
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

    public static void printLexicalError(int line, String msg) {
        FlagHelper.hasLexicalError = true;
        if (FlagHelper.currentLine < line) {
            System.err.println("Error type A at Line " + line + ": " + msg);
            FlagHelper.currentLine = line;
        }
    }

    public static void printSyntaxError(int line, String msg) {
        FlagHelper.hasSyntaxError = true;
        if (FlagHelper.currentLine < line) {
            System.err.println("Error type B at Line " + line + ": " + msg);
            FlagHelper.currentLine = line;
        }
    }

    public static void printSemanticError(ErrorType errorType, int lineNo) {
        FlagHelper.hasSemanticError = true;
        System.err.println("Error type " + errorType.getErrorNo() + " at Line " + lineNo
                + " : " + errorType.getErrorMsg());
    }

    public static void printSemanticError(ErrorType errorType, int lineNo, String errorName) {
        FlagHelper.hasSemanticError = true;
        System.err.println("Error type " + errorType.getErrorNo() + " at Line " + lineNo
                + " : " + String.format(errorType.getErrorMsg(), errorName));
    }

}
