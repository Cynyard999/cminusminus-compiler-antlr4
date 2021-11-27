package L2;

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
            Recorder.hasLexicalError = true;
            if (Recorder.currentLine < line) {
                System.err.println("Error type A at Line " + line + ": " + msg);
                Recorder.currentLine = line;
            }
        } else {
            Recorder.hasSyntaxError = true;
            if (Recorder.currentLine < line) {
                System.err.println("Error type B at Line " + line + ": " + msg);
                Recorder.currentLine = line;
            }
        }
    }
}
