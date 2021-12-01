package L3;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class Main {
    public static void main(String[] args) throws Exception {

        InputStream is = System.in;
        if (args.length > 0) {
            String inputFile = args[0];
            is = new FileInputStream(inputFile);
        }
        CharStream input = CharStreams.fromStream(is);

        CmmLexer lexer = new CmmLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new CustomErrorListener());
        List<? extends Token> allTokens = lexer.getAllTokens();
        if (!FlagHelper.hasLexicalError) {
            //printTokens(allTokens);
        } else {
            return;
        }

        lexer.reset();
        FlagHelper.currentLine = -1;

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        CmmParser parser = new CmmParser(tokenStream);
        parser.removeErrorListeners();
        parser.addErrorListener(new CustomErrorListener());
        ParseTree tree = parser.program();
//        System.out.println(tree.getClass().getName());
//        System.out.println(tree.toStringTree(parser));
        if (parser.getNumberOfSyntaxErrors() == 0) {
            CmmTreeAnalyzer visitor = new CmmTreeAnalyzer();
            visitor.visit(tree);
        }
    }

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
