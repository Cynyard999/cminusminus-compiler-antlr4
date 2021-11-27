package L2;

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
    public Void visitStmt(CmmParser.StmtContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "Stmt");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
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

    @Override
    public Void visitExp(CmmParser.ExpContext ctx) {
        int temp = depth;
        depth = ctx.depth();
        printRule(ctx.depth(), ctx.getStart().getLine(), "Exp");
        visitChildren(ctx);
        depth = temp;
        return defaultResult();
//        if (ctx.getChildCount() == 1) {
//            TerminalNode terminalNode;
//            if ((terminalNode = ctx.ID()) != null) {
//                printTerminal(ctx.depth() + 1, "ID", terminalNode.getText());
//            } else if ((terminalNode = ctx.INT()) != null) {
//                printTerminal(ctx.depth() + 1, "INT", Integer.parseInt(terminalNode.getText()));
//            } else if ((terminalNode = ctx.FLOAT()) != null) {
//                printTerminal(ctx.depth() + 1, "FLOAT", Double.parseDouble(terminalNode.getText()));
//            }
//        } else if (ctx.getChildCount() == 2) {
//            printTerminal(ctx.depth() + 1, ctx.getChild(0).getText());
//            visit(ctx.exp(0));
//        } else if (ctx.getChildCount() == 3) {
//            // LP exp RP
//            if (ctx.LP() != null && ctx.exp(0) != null) {
//                printTerminal(ctx.depth() + 1, "LP");
//                visit(ctx.exp(0));
//                printTerminal(ctx.depth() + 1, "RP");
//            }
//            // ID LP RP
//            else if (ctx.LP() != null && ctx.RP() != null) {
//                printTerminal(ctx.depth() + 1, "ID", ctx.ID().getText());
//                printTerminal(ctx.depth() + 1, "LP");
//                printTerminal(ctx.depth() + 1, "RP");
//            }
//            // exp DOT ID
//            else if (ctx.DOT() != null) {
//                visit(ctx.exp(0));
//                printTerminal(ctx.depth() + 1, "DOT");
//                printTerminal(ctx.depth() + 1, "ID", ctx.ID().getText());
//            } else {
//                visit(ctx.exp(0));
//                if (ctx.getChild(1) instanceof TerminalNode) {
//                    TerminalNode terminalNode = (TerminalNode) ctx.getChild(1);
//                    printTerminal(ctx.depth() + 1,
//                            CmmLexer.ruleNames[terminalNode.getSymbol().getType() - 1]);
//                }
//                visit(ctx.exp(1));
//            }
//        } else if (ctx.getChildCount() == 4) {
//            // ID LP args RP
//            if (ctx.ID() != null) {
//                printTerminal(ctx.depth() + 1, "ID", ctx.ID().getText());
//                printTerminal(ctx.depth() + 1, "LP");
//                visit(ctx.args());
//                printTerminal(ctx.depth() + 1, "RP");
//            }
//            // exp LB exp RB
//            else {
//                visit(ctx.exp(0));
//                printTerminal(ctx.depth() + 1, "LB");
//                visit(ctx.exp(1));
//                printTerminal(ctx.depth() + 1, "RB");
//            }
//        }
//        return defaultResult();
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
