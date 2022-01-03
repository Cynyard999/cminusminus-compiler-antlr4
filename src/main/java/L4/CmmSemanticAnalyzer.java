package L4;

import java.util.ArrayDeque;
import java.util.Deque;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class CmmSemanticAnalyzer extends CmmParserBaseVisitor<Returnable> {

    HashTable table = HashTable.getHashTable();
    // Only the function that pushes can pop and called sub_function just uses peek to get needed element
    Deque<Type> typeStack = new ArrayDeque<>();


    @Override
    public Returnable visitProgram(CmmParser.ProgramContext ctx) {
        visitChildren(ctx);
        return defaultResult();
    }

    @Override
    public Returnable visitExtDef(CmmParser.ExtDefContext ctx) {
        Type specifier = (Type) visit(ctx.specifier());
        if (specifier == null) {
            return defaultResult();
        }
        typeStack.push(specifier);
        if (ctx.extDecList() != null) {
            visit(ctx.extDecList());
        } else if (ctx.funDec() != null) {
            Function function = (Function) visit(ctx.funDec());
            if (function != null) {
                typeStack.push(function.getReturnType());
                visit(ctx.compSt());
                typeStack.pop();
            }
        }
        typeStack.pop();
        return defaultResult();
    }

    @Override
    public Returnable visitExtDecList(CmmParser.ExtDecListContext ctx) {
        for (CmmParser.VarDecContext varDecContext : ctx.varDec()) {
            Field varDec = (Field) visit(varDecContext);
            if (varDec != null) {
                table.addNode(varDec.getType(), varDec.getName());
            }
        }
        return defaultResult();
    }

    @Override
    public Returnable visitSpecifier(CmmParser.SpecifierContext ctx) {
        if (ctx.TYPE() != null) {
            return new Basic(ctx.TYPE().getSymbol().getText());
        } else {
            return visit(ctx.structSpecifier());
        }
    }

    @Override
    public Returnable visitStructSpecifier(CmmParser.StructSpecifierContext ctx) {
        Structure structure = new Structure();
        if (ctx.defList() != null) {
            String structureName = ctx.optTag().getText();
            structure.setName(structureName);
            if ((structureName != null) && (!structureName.isEmpty())) {
                // if struct name is duplicate skip this struct
                if (table.checkDuplicate(structureName)) {
                    OutputHelper
                            .printSemanticError(ErrorType.DUPLIC_STRUCT, ctx.getStart().getLine(),
                                    structureName);
                    return defaultResult();
                }
                table.addNode(structure, structureName);
            }
            structure.setMemberListHead((Field) visit(ctx.defList()));
            return structure;
        } else {
            Type type = table.getType(ctx.tag().getText());
            if (type == null) {
                OutputHelper.printSemanticError(ErrorType.UNDEF_STRUCT, ctx.getStart().getLine(),
                        ctx.tag().getText());
                return defaultResult();
            }
            if (type.getSymbolKind() != SymbolKind.STRUCTURE) {
                OutputHelper.printSemanticError(ErrorType.UNDEF_STRUCT, ctx.getStart().getLine(),
                        ctx.tag().getText());
                return defaultResult();
            }
            if (!((Structure) type).getName().equals(ctx.tag().getText())) {
                // tag is not a structure name but a variable name whose type is structure
                OutputHelper.printSemanticError(ErrorType.UNDEF_STRUCT, ctx.getStart().getLine(),
                        ctx.tag().getText());
                return defaultResult();
            }
            return type;
        }
    }

    @Override
    public Returnable visitOptTag(CmmParser.OptTagContext ctx) {
        return defaultResult();
    }

    @Override
    public Returnable visitTag(CmmParser.TagContext ctx) {
        return defaultResult();
    }

    @Override
    public Returnable visitVarDec(CmmParser.VarDecContext ctx) {
        Type type = typeStack.peek();
        Field field = new Field();
        field.setName(ctx.ID(0).getText());
        if (table.checkDuplicate(field.getName())) {
            if (ctx.inStruct) {
                OutputHelper
                        .printSemanticError(ErrorType.REDEF_FIELD, ctx.ID(0).getSymbol().getLine(),
                                ctx.ID(0).getText());
            } else {
                OutputHelper
                        .printSemanticError(ErrorType.REDEF_VAR, ctx.ID(0).getSymbol().getLine(),
                                ctx.ID(0).getText());
            }
            return defaultResult();
        }
        if (ctx.getChildCount() > 1) {
            Array firstArray = new Array();
            Array currentArray = firstArray;
            // GOOD
            for (int i = 0; i < ctx.INT().size(); i++) {
                currentArray.setSize(Integer.parseInt(ctx.INT(i).getText()));
                if (i == ctx.INT().size() - 1) {
                    currentArray.setElements(type);
                } else {
                    currentArray.setElements(new Array());
                    currentArray = (Array) currentArray.getElements();
                }
            }
            field.setType(firstArray);
        } else {
            field.setType(type);
        }
        return field;
    }

    @Override
    public Returnable visitFunDec(CmmParser.FunDecContext ctx) {
        Type type = typeStack.peek();
        Function function = new Function();
        String functionName = ctx.ID().getText();
        // just dump duplicate func
        if (table.checkDuplicate(functionName)) {
            OutputHelper.printSemanticError(ErrorType.REDEF_FUNC, ctx.getStart().getLine(),
                    functionName);
            return defaultResult();
        }
        function.setReturnType(type);
        if (ctx.varList() != null) {
            function.setParamListHead((Field) visit(ctx.varList()));
        }
        table.addNode(function, functionName);
        return function;
    }

    @Override
    public Returnable visitVarList(CmmParser.VarListContext ctx) {
        Field field = null;
        for (int i = 0; i < ctx.paramDec().size(); i++) {
            Field nextField = (Field) visit(ctx.paramDec(i));
            if (field == null && nextField != null) {
                field = nextField;
                continue;
            }
            if (field != null) {
                field.addField(nextField);
            }
        }
        return field;
    }

    @Override
    public Returnable visitParamDec(CmmParser.ParamDecContext ctx) {
        Type specifier = (Type) visit(ctx.specifier());
        if (specifier == null) {
            return defaultResult();
        }
        typeStack.push(specifier);
        Field param = (Field) visit(ctx.varDec());
        typeStack.pop();
        if (param != null) {
            table.addNode(param.getType(), param.getName());
        }
        return param;
    }

    @Override
    public Returnable visitCompSt(CmmParser.CompStContext ctx) {
        visit(ctx.defList());
        visit(ctx.stmtList());
        return defaultResult();
    }

    @Override
    public Returnable visitStmtList(CmmParser.StmtListContext ctx) {
        visitChildren(ctx);
        return defaultResult();
    }

    @Override
    public Returnable visitStmtExp(CmmParser.StmtExpContext ctx) {
        visitChildren(ctx);
        return defaultResult();
    }

    @Override
    public Returnable visitStmtCompSt(CmmParser.StmtCompStContext ctx) {
        visitChildren(ctx);
        return defaultResult();
    }

    @Override
    public Returnable visitStmtReturn(CmmParser.StmtReturnContext ctx) {
        Type exp = (Type) visit(ctx.exp());
        if (exp == null) {
            return defaultResult();
        }
        Type returnType = typeStack.peek();
        if (!CheckHelper.isTypeEqual(exp, returnType)) {
            OutputHelper
                    .printSemanticError(ErrorType.MISMATCH_RETURN, ctx.exp().getStart().getLine());
        }
        return defaultResult();
    }

    @Override
    public Returnable visitStmtIf(CmmParser.StmtIfContext ctx) {
        Type exp = (Type) visit(ctx.exp());
        if (exp != null && exp.getSymbolKind() != SymbolKind.INT) {
            OutputHelper
                    .printSemanticError(ErrorType.MISMATCH_OPRAND, ctx.exp().getStart().getLine());
        }
        // continue to visit stmt, despite error occurring
        visit(ctx.stmt());
        return defaultResult();
    }

    @Override
    public Returnable visitStmtIfElse(CmmParser.StmtIfElseContext ctx) {
        Type exp = (Type) visit(ctx.exp());
        if (exp != null && exp.getSymbolKind() != SymbolKind.INT) {
            OutputHelper
                    .printSemanticError(ErrorType.MISMATCH_OPRAND, ctx.exp().getStart().getLine());
        }
        // continue to visit stmt, despite error occurring
        visit(ctx.stmt(0));
        visit(ctx.stmt(1));
        return defaultResult();
    }

    @Override
    public Returnable visitStmtWhile(CmmParser.StmtWhileContext ctx) {
        Type exp = (Type) visit(ctx.exp());
        if (exp != null && exp.getSymbolKind() != SymbolKind.INT) {
            OutputHelper
                    .printSemanticError(ErrorType.MISMATCH_OPRAND, ctx.exp().getStart().getLine());
        }
        // continue to visit stmt, despite error occurring
        visit(ctx.stmt());
        return defaultResult();
    }

    @Override
    public Returnable visitDefList(CmmParser.DefListContext ctx) {
        if (ctx.getChildCount() == 0) {
            return null;
        }
        Field field = null;
        for (int i = 0; i < ctx.getChildCount(); i++) {
            Field nextField = (Field) visit(ctx.def(i));
            if (nextField != null && field == null) {
                field = nextField;
                continue;
            }
            if (field != null) {
                field.addField(nextField);
            }
        }
        return field;
    }

    @Override
    public Returnable visitDef(CmmParser.DefContext ctx) {
        Type specifier = (Type) visit(ctx.specifier());
        if (specifier == null) {
            return defaultResult();
        }
        typeStack.push(specifier);
        Field field = (Field) visit(ctx.decList());
        typeStack.pop();
        return field;
    }

    @Override
    public Returnable visitDecList(CmmParser.DecListContext ctx) {
        Field field = null;
        for (int i = 0; i < ctx.dec().size(); i++) {
            Field nextField = (Field) visit(ctx.dec(i));
            if (nextField != null && field == null) {
                field = nextField;
                continue;
            }
            if (field != null) {
                field.addField(nextField);
            }
        }
        return field;
    }

    @Override
    public Returnable visitDec(CmmParser.DecContext ctx) {
        Field varDec;
        if (ctx.getChildCount() > 1) {
            // if in struct there is ASSIGNOP, do not visit varDec
            if (ctx.inStruct) {
                OutputHelper.printSemanticError(ErrorType.REDEF_FIELD,
                        ctx.ASSIGNOP().getSymbol().getLine(), ctx.varDec().getText());
                return defaultResult();
            }
            varDec = (Field) visit(ctx.varDec());
            Type exp = (Type) visit(ctx.exp());
            if (exp == null) {
                // 跳过类型检查
            } else if (varDec != null && !CheckHelper.isTypeEqual(varDec.getType(), exp)) {
                OutputHelper.printSemanticError(ErrorType.MISMATCH_ASSIGN,
                        ctx.exp().getStart().getLine());
                return defaultResult();
            }
        } else {
            varDec = (Field) visit(ctx.varDec());
        }
        // add to symbol table until
        if (varDec != null) {
            table.addNode(varDec.getType(), varDec.getName());
        }
        return varDec;
    }

    @Override
    public Returnable visitExpFuncArgs(CmmParser.ExpFuncArgsContext ctx) {
        Type func = table.getType(ctx.ID().getText());
        if (func == null) {
            OutputHelper.printSemanticError(ErrorType.UNDEF_FUNC, ctx.ID().getSymbol().getLine(),
                    ctx.ID().getText());
            return defaultResult();
        }
        if (func.getSymbolKind() != SymbolKind.FUNCTION) {
            OutputHelper.printSemanticError(ErrorType.NON_FUNC, ctx.ID().getSymbol().getLine(),
                    ctx.ID().getText());
            return defaultResult();
        }
        if (ctx.args() == null) {
            if (((Function) func).getParamListHead() != null) {
                OutputHelper.printSemanticError(ErrorType.MISMATCH_PARAM,
                        ctx.LP().getSymbol().getLine());
                return defaultResult();
            }
        } else {
            Field args = (Field) visit(ctx.args());
            if (args == null) {
                return defaultResult();
            }
            if (!CheckHelper.isFieldListEqual(((Function) func).getParamListHead(), args)) {
                OutputHelper.printSemanticError(ErrorType.MISMATCH_PARAM,
                        ctx.LP().getSymbol().getLine());
                return defaultResult();
            }
        }
        return ((Function) func).getReturnType();
    }

    @Override
    public Returnable visitExpParenthesis(CmmParser.ExpParenthesisContext ctx) {
        return visit(ctx.exp());
    }

    @Override
    public Returnable visitExpBrackets(CmmParser.ExpBracketsContext ctx) {
        Type array = (Type) visit(ctx.exp(0));
        Type index = (Type) visit(ctx.exp(1));
        if (array == null || index == null) {
            return defaultResult();
        }
        if (array.getSymbolKind() != SymbolKind.ARRAY) {
            OutputHelper.printSemanticError(ErrorType.NON_ARRAY, ctx.exp(0).getStart().getLine(),
                    ctx.exp(0).getText());
            return defaultResult();
        }
        if (index.getSymbolKind() != SymbolKind.INT) {
            OutputHelper.printSemanticError(ErrorType.NON_INT, ctx.exp(0).getStart().getLine(),
                    ctx.exp(0).getText());
            return defaultResult();
        }
        return ((Array) array).getElements();
    }

    @Override
    public Returnable visitExpDot(CmmParser.ExpDotContext ctx) {
        Type exp = (Type) visit(ctx.exp());
        if (exp == null) {
            return defaultResult();
        }
        if (exp.getSymbolKind() != SymbolKind.STRUCTURE) {
            OutputHelper.printSemanticError(ErrorType.ILLEGAL_DOT, ctx.DOT().getSymbol().getLine());
            return defaultResult();
        }
        Field memberList = ((Structure) exp).getMemberListHead();
        while (memberList != null) {
            if (memberList.getName().equals(ctx.ID().getText())) {
                return memberList.getType();
            }
            memberList = memberList.getNext();
        }
        OutputHelper.printSemanticError(ErrorType.UNDEF_FIELD, ctx.ID().getSymbol().getLine(),
                ctx.ID().getText());
        return defaultResult();
    }

    @Override
    public Returnable visitExpMinusNot(CmmParser.ExpMinusNotContext ctx) {
        Type exp = (Type) visit(ctx.exp());
        if (exp == null) {
            return defaultResult();
        }
        if (exp.getSymbolKind() != SymbolKind.INT && exp.getSymbolKind() != SymbolKind.FLOAT) {
            OutputHelper
                    .printSemanticError(ErrorType.MISMATCH_OPRAND, ctx.exp().getStart().getLine());
            return defaultResult();
        }
        return exp;
    }

    @Override
    public Returnable visitExpStarDiv(CmmParser.ExpStarDivContext ctx) {
        return checkArithmeticOperation(ctx.exp(0), ctx.exp(1), (TerminalNode) ctx.getChild(1));
    }

    @Override
    public Returnable visitExpPlusMinus(CmmParser.ExpPlusMinusContext ctx) {
        return checkArithmeticOperation(ctx.exp(0), ctx.exp(1), (TerminalNode) ctx.getChild(1));
    }

    private Returnable checkArithmeticOperation(CmmParser.ExpContext exp, CmmParser.ExpContext exp2,
            TerminalNode operand) {
        Type firstExp = (Type) visit(exp);
        Type secondExp = (Type) visit(exp2);
        if (firstExp == null || secondExp == null) {
            return defaultResult();
        }
        if (firstExp.getSymbolKind() != SymbolKind.INT && firstExp.getSymbolKind() != SymbolKind.FLOAT) {
            OutputHelper.printSemanticError(ErrorType.MISMATCH_OPRAND, exp.getStart().getLine());
            return defaultResult();
        }
        if (secondExp.getSymbolKind() != SymbolKind.INT && secondExp.getSymbolKind() != SymbolKind.FLOAT) {
            OutputHelper.printSemanticError(ErrorType.MISMATCH_OPRAND, exp2.getStart().getLine());
            return defaultResult();
        }
        if (firstExp.getSymbolKind() != secondExp.getSymbolKind()) {
            OutputHelper.printSemanticError(ErrorType.MISMATCH_OPRAND,
                    operand.getSymbol().getLine());
            return defaultResult();
        }
        return firstExp;
    }

    @Override
    public Returnable visitExpRelop(CmmParser.ExpRelopContext ctx) {
        Type firstExp = (Type) visit(ctx.exp(0));
        Type secondExp = (Type) visit(ctx.exp(1));
        if (firstExp == null || secondExp == null) {
            return defaultResult();
        }
        if (firstExp.getSymbolKind() != SymbolKind.INT && firstExp.getSymbolKind() != SymbolKind.FLOAT) {
            OutputHelper
                    .printSemanticError(ErrorType.MISMATCH_OPRAND, ctx.exp(0).getStart().getLine());
            return defaultResult();
        }
        if (secondExp.getSymbolKind() != SymbolKind.INT && firstExp.getSymbolKind() != SymbolKind.FLOAT) {
            OutputHelper
                    .printSemanticError(ErrorType.MISMATCH_OPRAND, ctx.exp(1).getStart().getLine());
            return defaultResult();
        }
        if (firstExp.getSymbolKind() != secondExp.getSymbolKind()) {
            OutputHelper.printSemanticError(ErrorType.MISMATCH_OPRAND,
                    ctx.RELOP().getSymbol().getLine());
            return defaultResult();
        }
        return new Basic("int");
    }

    @Override
    public Returnable visitExpAnd(CmmParser.ExpAndContext ctx) {
        return checkLogicalOperation(ctx.exp(0), ctx.exp(1));
    }

    @Override
    public Returnable visitExpOr(CmmParser.ExpOrContext ctx) {
        return checkLogicalOperation(ctx.exp(0), ctx.exp(1));
    }

    private Returnable checkLogicalOperation(CmmParser.ExpContext exp, CmmParser.ExpContext exp2) {
        Type firstExp = (Type) visit(exp);
        Type secondExp = (Type) visit(exp2);
        if (firstExp == null || secondExp == null) {
            return defaultResult();
        }
        if (firstExp.getSymbolKind() != SymbolKind.INT) {
            OutputHelper.printSemanticError(ErrorType.MISMATCH_OPRAND, exp.getStart().getLine());
            return defaultResult();
        }
        if (secondExp.getSymbolKind() != SymbolKind.INT) {
            OutputHelper.printSemanticError(ErrorType.MISMATCH_OPRAND, exp2.getStart().getLine());
            return defaultResult();
        }
        return new Basic("int");
    }

    @Override
    public Returnable visitExpAssignop(CmmParser.ExpAssignopContext ctx) {
        Type firstExp = (Type) visit(ctx.exp(0));
        Type secondExp = (Type) visit(ctx.exp(1));
        if (firstExp == null || secondExp == null) {
            return defaultResult();
        }
        // check exp
        if (!CheckHelper.isLeftExp(ctx.exp(0))) {
            OutputHelper.printSemanticError(ErrorType.NOT_LETF_EXP_ASSIGN,
                    ctx.exp(0).getStart().getLine());
            return defaultResult();
        }
        // check type
        if (firstExp.getSymbolKind() == SymbolKind.FUNCTION) {
            OutputHelper.printSemanticError(ErrorType.NOT_LETF_EXP_ASSIGN,
                    ctx.exp(0).getStart().getLine());
            return defaultResult();
        }
        if (!CheckHelper.isTypeEqual(firstExp, secondExp)) {
            OutputHelper.printSemanticError(ErrorType.MISMATCH_ASSIGN,
                    ctx.ASSIGNOP().getSymbol().getLine());
            return defaultResult();
        }
        return firstExp;
    }

    @Override
    public Returnable visitExpId(CmmParser.ExpIdContext ctx) {
        Type ID = table.getType(ctx.ID().getText());
        if (ID == null) {
            OutputHelper.printSemanticError(ErrorType.UNDEF_VAR, ctx.ID().getSymbol().getLine(),
                    ctx.ID().getText());
            return defaultResult();
        }
        return ID;
    }

    @Override
    public Returnable visitExpInt(CmmParser.ExpIntContext ctx) {
        return new Basic("int");
    }

    @Override
    public Returnable visitExpFloat(CmmParser.ExpFloatContext ctx) {
        return new Basic("float");
    }

    @Override
    public Returnable visitArgs(CmmParser.ArgsContext ctx) {
        Field field = null;
        Field currentField;
        for (int i = 0; i < ctx.exp().size(); i++) {
            Type exp = (Type) visit(ctx.exp(i));
            if (exp == null) {
                continue;
            }
            currentField = new Field();
            currentField.setType(exp);
            if (field == null) {
                field = currentField;
            } else {
                field.addField(currentField);
            }
        }
        return field;
    }
}
