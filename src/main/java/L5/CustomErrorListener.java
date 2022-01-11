package L5;

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
            OutputHelper.printLexicalError(line, msg);
        } else {
            OutputHelper.printSyntaxError(line, msg);
        }
    }
}
