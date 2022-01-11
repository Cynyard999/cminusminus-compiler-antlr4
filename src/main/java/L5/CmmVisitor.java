package L5;

import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * @author cynyard
 * @description
 * @date 11/17/21
 */
public class CmmVisitor extends CmmParserBaseVisitor<Void> {

    static int depth = 0;

    private String getIndent(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length - 1; i++) {
            stringBuilder.append("  ");
        }
        return stringBuilder.toString();
    }

    private void printTerminal(int depth, String name) {
        System.err.println(
                String.format("%s%s", getIndent(depth), name));
    }

    private void printTerminal(int depth, String name, String value) {
        System.err.println(
                String.format("%s%s: %s", getIndent(depth), name,
                        value));
    }

    private void printTerminal(int depth, String name, int value) {
        System.err.println(
                String.format("%s%s: %d", getIndent(depth), name,
                        value));
    }

    private void printTerminal(int depth, String name, double value) {
        System.err.println(
                String.format("%s%s: %.6f", getIndent(depth), name,
                        value));
    }

    private void printRule(int depth, int line, String name) {
        System.err.println(
                String.format("%s%s (%d)", getIndent(depth), name, line));
    }

    @Override
    public Void visitTerminal(TerminalNode node) {
        if (node.getSymbol().getType() < 1) {
            return defaultResult();
        }
        if (node.getSymbol().getType() == CmmLexer.ID
                || node.getSymbol().getType() == CmmLexer.TYPE) {
            printTerminal(depth + 1, CmmLexer.ruleNames[node.getSymbol().getType() - 1],
                    node.getText());
        } else if (node.getSymbol().getType() == CmmLexer.FLOAT) {
            printTerminal(depth + 1, CmmLexer.ruleNames[node.getSymbol().getType() - 1],
                    Double.parseDouble(node.getText()));
        } else if (node.getSymbol().getType() == CmmLexer.INT) {
            printTerminal(depth + 1, CmmLexer.ruleNames[node.getSymbol().getType() - 1],
                    Integer.parseInt(node.getText()));
        } else {
            printTerminal(depth + 1, CmmLexer.ruleNames[node.getSymbol().getType() - 1]);
        }
        return defaultResult();
    }

    @Override
    public Void visitProgram(CmmParser.ProgramContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "Program");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitExtDef(CmmParser.ExtDefContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "ExtDef");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitExtDecList(CmmParser.ExtDecListContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "ExtDecList");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitSpecifier(CmmParser.SpecifierContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "Specifier");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitStructSpecifier(CmmParser.StructSpecifierContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "StructSpecifier");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitOptTag(CmmParser.OptTagContext ctx) {
        if (ctx.getChildCount() == 0) {
            return defaultResult();
        }
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "OptTag");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitTag(CmmParser.TagContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "Tag");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitVarDec(CmmParser.VarDecContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "VarDec");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitFunDec(CmmParser.FunDecContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "FunDec");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitVarList(CmmParser.VarListContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "VarList");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitParamDec(CmmParser.ParamDecContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "ParamDec");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitCompSt(CmmParser.CompStContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "CompSt");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitStmtList(CmmParser.StmtListContext ctx) {
        if (ctx.getChildCount() == 0) {
            return defaultResult();
        }

        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "StmtList");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitStmtExp(CmmParser.StmtExpContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "Stmt");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    private Void visitStmt(CmmParser.StmtContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "Stmt");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitStmtCompSt(CmmParser.StmtCompStContext ctx) {
        return visitStmt(ctx);
    }

    @Override
    public Void visitStmtReturn(CmmParser.StmtReturnContext ctx) {
        return visitStmt(ctx);
    }

    @Override
    public Void visitStmtIf(CmmParser.StmtIfContext ctx) {
        return visitStmt(ctx);
    }

    @Override
    public Void visitStmtIfElse(CmmParser.StmtIfElseContext ctx) {
        return visitStmt(ctx);
    }

    @Override
    public Void visitStmtWhile(CmmParser.StmtWhileContext ctx) {
        return visitStmt(ctx);
    }

    @Override
    public Void visitDefList(CmmParser.DefListContext ctx) {
        if (ctx.getChildCount() == 0) {
            return defaultResult();
        }
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "DefList");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitDef(CmmParser.DefContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "Def");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitDecList(CmmParser.DecListContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "DecList");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitDec(CmmParser.DecContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "Dec");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    private Void visitExp(CmmParser.ExpContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "Exp");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }

    @Override
    public Void visitExpDot(CmmParser.ExpDotContext ctx) {
        return visitExp(ctx);
    }

    @Override
    public Void visitExpOr(CmmParser.ExpOrContext ctx) {
        return visitExp(ctx);
    }

    @Override
    public Void visitExpRelop(CmmParser.ExpRelopContext ctx) {
        return visitExp(ctx);
    }

    @Override
    public Void visitExpPlusMinus(CmmParser.ExpPlusMinusContext ctx) {
        return visitExp(ctx);
    }

    @Override
    public Void visitExpAssignop(CmmParser.ExpAssignopContext ctx) {
        return visitExp(ctx);
    }

    @Override
    public Void visitExpParenthesis(CmmParser.ExpParenthesisContext ctx) {
        return visitExp(ctx);
    }

    @Override
    public Void visitExpFloat(CmmParser.ExpFloatContext ctx) {
        return visitExp(ctx);
    }

    @Override
    public Void visitExpAnd(CmmParser.ExpAndContext ctx) {
        return visitExp(ctx);
    }

    @Override
    public Void visitExpFuncArgs(CmmParser.ExpFuncArgsContext ctx) {
        return visitExp(ctx);
    }

    @Override
    public Void visitExpStarDiv(CmmParser.ExpStarDivContext ctx) {
        return visitExp(ctx);
    }

    @Override
    public Void visitExpInt(CmmParser.ExpIntContext ctx) {
        return visitExp(ctx);
    }

    @Override
    public Void visitExpMinusNot(CmmParser.ExpMinusNotContext ctx) {
        return visitExp(ctx);
    }

    @Override
    public Void visitExpId(CmmParser.ExpIdContext ctx) {
        return visitExp(ctx);
    }

    @Override
    public Void visitExpBrackets(CmmParser.ExpBracketsContext ctx) {
        return visitExp(ctx);
    }

    @Override
    public Void visitArgs(CmmParser.ArgsContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "Args");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
    }
}
