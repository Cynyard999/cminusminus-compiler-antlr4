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
        // BEGIN LEXICAL PART
        CmmLexer lexer = new CmmLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new CustomErrorListener());
        List<? extends Token> allTokens = lexer.getAllTokens();
        if (!FlagHelper.hasLexicalError) {
//            OutputHelper.printTokens(allTokens);
        } else {
            return;
        }
        // BEGIN SYNTAX PART
        lexer.reset();
        FlagHelper.currentLine = -1;
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        CmmParser parser = new CmmParser(tokenStream);
        parser.removeErrorListeners();
        parser.addErrorListener(new CustomErrorListener());
        ParseTree tree = parser.program();
        if (!FlagHelper.hasSyntaxError) {
//            CmmVisitor cmmVisitor = new CmmVisitor();
//            cmmVisitor.visit(tree);
        } else {
            return;
        }
        // BEGIN SEMANTIC PART
        CmmSemanticAnalyzer cmmSemanticAnalyzer = new CmmSemanticAnalyzer();
        cmmSemanticAnalyzer.visit(tree);

    }
}
