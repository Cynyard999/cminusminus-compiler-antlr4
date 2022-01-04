package L4;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
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
        // Set default output file as err
        OutputHelper.setOutput(System.err);

        // Get input Cmm language
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

        // BEGIN SEMANTIC PART and build symbol table
        Function readFunction = new Function();
        readFunction.setReturnType(new Basic("int"));
        HashTable.getHashTable().addNode(readFunction, "read");
        Function writeFunction = new Function();
        Field writeParam = new Field();
        writeParam.setType(new Basic("int"));
        writeFunction.setParamListHead(writeParam);
        HashTable.getHashTable().addNode(writeFunction, "write");
        CmmSemanticAnalyzer cmmSemanticAnalyzer = new CmmSemanticAnalyzer();
        cmmSemanticAnalyzer.visit(tree);

        // BEGIN IR PART
        if (!FlagHelper.hasSemanticError) {
            if (args.length > 1) {
                OutputHelper.setOutput(new PrintStream(args[1]));
            } else {
//                OutputHelper.setOutput(new PrintStream("output.ir"));
                OutputHelper.resetOutput();
            }
            CmmInterCodeGenerator cmmInterCodeGenerator = new CmmInterCodeGenerator();
            InterCode interCodeHead = cmmInterCodeGenerator.visit(tree);
            OutputHelper.printInterCode(interCodeHead);
        }
    }
}
