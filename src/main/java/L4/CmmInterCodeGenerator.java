package L4;

/**
 * @author cynyard
 * @description
 * @date 1/1/22
 */
public class CmmInterCodeGenerator extends CmmParserBaseVisitor<InterCode> {

    @Override
    public InterCode visitProgram(CmmParser.ProgramContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExtDef(CmmParser.ExtDefContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExtDecList(CmmParser.ExtDecListContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitSpecifier(CmmParser.SpecifierContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitStructSpecifier(CmmParser.StructSpecifierContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitOptTag(CmmParser.OptTagContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitTag(CmmParser.TagContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitVarDec(CmmParser.VarDecContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitFunDec(CmmParser.FunDecContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitVarList(CmmParser.VarListContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitParamDec(CmmParser.ParamDecContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitCompSt(CmmParser.CompStContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitStmtList(CmmParser.StmtListContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitStmtExp(CmmParser.StmtExpContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitStmtCompSt(CmmParser.StmtCompStContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitStmtReturn(CmmParser.StmtReturnContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitStmtIf(CmmParser.StmtIfContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitStmtIfElse(CmmParser.StmtIfElseContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitStmtWhile(CmmParser.StmtWhileContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitDefList(CmmParser.DefListContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitDef(CmmParser.DefContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitDecList(CmmParser.DecListContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitDec(CmmParser.DecContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExpDot(CmmParser.ExpDotContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExpOr(CmmParser.ExpOrContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExpRelop(CmmParser.ExpRelopContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExpPlusMinus(CmmParser.ExpPlusMinusContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExpAssignop(CmmParser.ExpAssignopContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExpParenthesis(CmmParser.ExpParenthesisContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExpFloat(CmmParser.ExpFloatContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExpAnd(CmmParser.ExpAndContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExpFuncArgs(CmmParser.ExpFuncArgsContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExpStarDiv(CmmParser.ExpStarDivContext ctx) {
        return visitChildren(ctx);
    }


    @Override
    public InterCode visitExpInt(CmmParser.ExpIntContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExpMinusNot(CmmParser.ExpMinusNotContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExpId(CmmParser.ExpIdContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitExpBrackets(CmmParser.ExpBracketsContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public InterCode visitArgs(CmmParser.ArgsContext ctx) {
        return visitChildren(ctx);
    }
}
