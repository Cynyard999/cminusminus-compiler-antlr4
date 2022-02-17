package L5;

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

        // Get Cmm language input
        InputStream is = System.in;
        if (args.length > 0) {
            String inputFile = args[0];
            is = new FileInputStream(inputFile);
        }
        CharStream input = CharStreams.fromStream(is);

        CmmLexer cmmLexer = lexerStart(input);

        ParseTree tree = parserStart(cmmLexer);

        ParseTree checkedTree = semanticsStart(tree);

        InterCodeList interCodeList = intermediateRepresentationStart(checkedTree);

        if (args.length > 1) {
            assemblyCodeStart(interCodeList, new PrintStream(args[1]));
        } else {
            assemblyCodeStart(interCodeList, System.out);
        }

        OutputHelper.resetOutput();
    }

    private static CmmLexer lexerStart(CharStream input) {
        // BEGIN LEXICAL PART
        CmmLexer lexer = new CmmLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new CustomErrorListener());
        List<? extends Token> allTokens = lexer.getAllTokens();
        if (!FlagHelper.hasLexicalError) {
//            OutputHelper.printTokens(allTokens);
            lexer.reset();
            return lexer;
        } else {
            return null;
        }
    }

    private static ParseTree parserStart(CmmLexer lexer) {
        if (lexer == null) {
            return null;
        }
        // BEGIN SYNTAX PART
        FlagHelper.currentLine = -1;
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        CmmParser parser = new CmmParser(tokenStream);
        parser.removeErrorListeners();
        parser.addErrorListener(new CustomErrorListener());
        ParseTree tree = parser.program();
        if (!FlagHelper.hasSyntaxError) {
//            CmmVisitor cmmVisitor = new CmmVisitor();
//            cmmVisitor.visit(tree);
            return tree;
        } else {
            return null;
        }
    }

    private static ParseTree semanticsStart(ParseTree tree) {
        if (tree == null) {
            return null;
        }
        initSymbolTable();
        // BEGIN SEMANTIC PART
        CmmSemanticAnalyzer cmmSemanticAnalyzer = new CmmSemanticAnalyzer();
        cmmSemanticAnalyzer.visit(tree);
        if (!FlagHelper.hasSemanticError) {
            return tree;
        } else {
            return null;
        }
    }

    private static InterCodeList intermediateRepresentationStart(ParseTree tree) {
        if (tree == null) {
            return null;
        }
        // BEGIN IR PART
        CmmInterCodeGenerator cmmInterCodeGenerator = new CmmInterCodeGenerator();
        return cmmInterCodeGenerator.visit(tree);
//        InterCode interCodeHead = cmmInterCodeGenerator.visit(tree);
//        InterCode currentInterCode = interCodeHead;
//        while (currentInterCode != null) {
//            OutputHelper.println(currentInterCode.toString());
//            currentInterCode = currentInterCode.next;
//        }
//        return interCodeHead;
    }

    private static void assemblyCodeStart(InterCodeList interCodeList, PrintStream output) {
        if (interCodeList == null || interCodeList.isEmpty()) {
            return;
        }
        // BEGIN ASSEMBLY PART
        OutputHelper.setOutput(output);
        AssemblyCodeGenerator assemblyCodeGenerator = new AssemblyCodeGenerator(interCodeList.head);
        assemblyCodeGenerator.start();
    }

    private static void initSymbolTable() {
        Function readFunction = new Function();
        readFunction.setReturnType(new Basic("int"));
        HashTable.getHashTable().addNode(readFunction, "read");
        Function writeFunction = new Function();
        Field writeParam = new Field();
        writeParam.setType(new Basic("int"));
        writeFunction.setParamListHead(writeParam);
        HashTable.getHashTable().addNode(writeFunction, "write");
    }


}
