package L5;

import java.io.PrintStream;
import java.util.List;
import org.antlr.v4.runtime.Token;

/**
 * @author cynyard
 * @description
 * @date 12/4/21
 */
public class OutputHelper {

    private static PrintStream output = System.out;

    public static void setOutput(PrintStream output) {
        OutputHelper.output.flush();
        OutputHelper.output = output;
    }

    public static void resetOutput() {
        OutputHelper.output.flush();
        OutputHelper.output = System.out;
    }

    public static void printTokens(List<? extends Token> allTokens) {
        for (Token token : allTokens) {
            if (token.getType() >= 1) {
                String tokenText = getTokenText(token);
                output.println(
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
            output.println("Error type A at Line " + line + ": " + msg);
            FlagHelper.currentLine = line;
        }
    }

    public static void printSyntaxError(int line, String msg) {
        FlagHelper.hasSyntaxError = true;
        if (FlagHelper.currentLine < line) {
            output.println("Error type B at Line " + line + ": " + msg);
            FlagHelper.currentLine = line;
        }
    }

    public static void printSemanticError(ErrorType errorType, int lineNo) {
        FlagHelper.hasSemanticError = true;
        output.println("Error type " + errorType.getErrorNo() + " at Line " + lineNo
                + ": " + errorType.getErrorMsg());
    }

    public static void printSemanticError(ErrorType errorType, int lineNo, String errorName) {
        FlagHelper.hasSemanticError = true;
        output.println("Error type " + errorType.getErrorNo() + " at Line " + lineNo
                + ": " + String.format(errorType.getErrorMsg(), errorName));
    }


    public static void print(String str) {
        output.print(str);
    }

    public static void println() {
        output.println();
    }

    public static void println(String str) {
        output.println(str);
    }

    public static void println(String str, int indentSize) {
        output.println(getIndent(indentSize));
        output.println(str);
    }

    public static void printInterCode(InterCode interCode) {
        while (interCode != null) {
            output.println(interCode.toString());
            interCode = interCode.next;
        }
    }

    private static String getIndent(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length - 1; i++) {
            stringBuilder.append("  ");
        }
        return stringBuilder.toString();
    }

}
