package L4;

/**
 * @author cynyard
 * @description
 * @date 11/24/21
 */
public class FlagHelper {

    static boolean hasLexicalError = false;
    static boolean hasSyntaxError = false;
    static boolean hasSemanticError = false;
    static int currentLine = -1;
    static int tempVariableCount = 0;
    static int labelCount = 0;
}
