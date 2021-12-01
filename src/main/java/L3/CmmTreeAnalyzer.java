package L3;


/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class CmmTreeAnalyzer extends CmmParserBaseVisitor<Type> {

    @Override
    public Type visitProgram(CmmParser.ProgramContext ctx) {
        visitChildren(ctx);
        return defaultResult();
    }

    @Override
    public Type visitExtDef(CmmParser.ExtDefContext ctx) {
        return super.visitExtDef(ctx);
    }

    @Override
    public Type visitExtDecList(CmmParser.ExtDecListContext ctx) {
        return super.visitExtDecList(ctx);
    }

    @Override
    public Type visitSpecifier(CmmParser.SpecifierContext ctx) {
        if (ctx.TYPE()!=null){
            Basic basic = new Basic();
            switch (ctx.TYPE().getSymbol().getText()){
                case "int": basic.setKind(Kind.INT); break;
                case "float": basic.setKind(Kind.FLOAT); break;
                default: break;
            }
            return basic;
        }
        else {
            return visit(ctx.structSpecifier());
        }
    }

    @Override
    public Type visitStructSpecifier(CmmParser.StructSpecifierContext ctx) {
        Structure structure = new Structure();
        if (ctx.defList() != null) {

        }
        else {

        }
        return new Structure();
    }

    @Override
    public Type visitOptTag(CmmParser.OptTagContext ctx) {
        return super.visitOptTag(ctx);
    }

    @Override
    public Type visitTag(CmmParser.TagContext ctx) {
        return super.visitTag(ctx);
    }

    @Override
    public Type visitVarDec(CmmParser.VarDecContext ctx) {
        return super.visitVarDec(ctx);
    }

    @Override
    public Type visitFunDec(CmmParser.FunDecContext ctx) {
        return super.visitFunDec(ctx);
    }

    @Override
    public Type visitVarList(CmmParser.VarListContext ctx) {
        return super.visitVarList(ctx);
    }

    @Override
    public Type visitParamDec(CmmParser.ParamDecContext ctx) {
        return super.visitParamDec(ctx);
    }

    @Override
    public Type visitCompSt(CmmParser.CompStContext ctx) {
        return super.visitCompSt(ctx);
    }

    @Override
    public Type visitStmtList(CmmParser.StmtListContext ctx) {
        return super.visitStmtList(ctx);
    }

    @Override
    public Type visitStmt(CmmParser.StmtContext ctx) {
        return super.visitStmt(ctx);
    }

    @Override
    public Type visitDefList(CmmParser.DefListContext ctx) {
        return super.visitDefList(ctx);
    }

    @Override
    public Type visitDef(CmmParser.DefContext ctx) {
        return super.visitDef(ctx);
    }

    @Override
    public Type visitDecList(CmmParser.DecListContext ctx) {
        return super.visitDecList(ctx);
    }

    @Override
    public Type visitDec(CmmParser.DecContext ctx) {
        return super.visitDec(ctx);
    }

    @Override
    public Type visitExp(CmmParser.ExpContext ctx) {
        return super.visitExp(ctx);
    }

    @Override
    public Type visitArgs(CmmParser.ArgsContext ctx) {
        return super.visitArgs(ctx);
    }
}
