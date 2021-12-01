package L3;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * @author cynyard
 * @description
 * @date 11/22/21
 */
public class CustomErrorListener extends BaseErrorListener {


    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
            int charPositionInLine, String msg, RecognitionException e) {
        if (e instanceof LexerNoViableAltException) {
            FlagHelper.hasLexicalError = true;
            if (FlagHelper.currentLine < line) {
                System.err.println("Error type A at Line " + line + ": " + msg);
                FlagHelper.currentLine = line;
            }
        } else {
            FlagHelper.hasSyntaxError = true;
            if (FlagHelper.currentLine < line) {
                System.err.println("Error type B at Line " + line + ": " + msg);
                FlagHelper.currentLine = line;
            }
        }
    }
}
