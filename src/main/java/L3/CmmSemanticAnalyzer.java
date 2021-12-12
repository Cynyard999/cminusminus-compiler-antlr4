package L3;

import java.util.*;
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
            FieldList varDec = (FieldList) visit(varDecContext);
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
                            .printSemanticError(ErrorType.DUPLIC_STRUCT, ctx.getStart().getLine(),structureName);
                    return defaultResult();
                }
                table.addNode(structure, structureName);
            }
            structure.setMemberList((FieldList) visit(ctx.defList()));
            return structure;
        } else {
            Type type = table.getType(ctx.tag().getText());
            if (type == null) {
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
        FieldList fieldList = new FieldList();
        fieldList.setName(ctx.ID(0).getText());
        if (table.checkDuplicate(fieldList.getName())) {
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
            fieldList.setType(firstArray);
        } else {
            fieldList.setType(type);
        }
        return fieldList;
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
            function.setParamList((FieldList) visit(ctx.varList()));
        }
        table.addNode(function, functionName);
        return function;
    }

    @Override
    public Returnable visitVarList(CmmParser.VarListContext ctx) {
        FieldList fieldList = null;
        for (int i = 0; i < ctx.paramDec().size(); i++) {
            FieldList nextField = (FieldList) visit(ctx.paramDec(i));
            if (fieldList == null && nextField != null) {
                fieldList = nextField;
                continue;
            }
            if (fieldList != null) {
                fieldList.addField(nextField);
            }
        }
        return fieldList;
    }

    @Override
    public Returnable visitParamDec(CmmParser.ParamDecContext ctx) {
        Type specifier = (Type) visit(ctx.specifier());
        if (specifier == null) {
            return defaultResult();
        }
        typeStack.push(specifier);
        FieldList param = (FieldList) visit(ctx.varDec());
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
        if (exp != null && exp.getKind() != Kind.INT) {
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
        if (exp != null && exp.getKind() != Kind.INT) {
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
        if (exp != null && exp.getKind() != Kind.INT) {
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
        FieldList fieldList = null;
        for (int i = 0; i < ctx.getChildCount(); i++) {
            FieldList nextField = (FieldList) visit(ctx.def(i));
            if (nextField != null && fieldList == null) {
                fieldList = nextField;
                continue;
            }
            if (fieldList != null) {
                fieldList.addField(nextField);
            }
        }
        return fieldList;
    }

    @Override
    public Returnable visitDef(CmmParser.DefContext ctx) {
        Type specifier = (Type) visit(ctx.specifier());
        if (specifier == null) {
            return defaultResult();
        }
        typeStack.push(specifier);
        FieldList fieldList = (FieldList) visit(ctx.decList());
        typeStack.pop();
        return fieldList;
    }

    @Override
    public Returnable visitDecList(CmmParser.DecListContext ctx) {
        FieldList fieldList = null;
        for (int i = 0; i < ctx.dec().size(); i++) {
            FieldList nextField = (FieldList) visit(ctx.dec(i));
            if (nextField != null && fieldList == null) {
                fieldList = nextField;
                continue;
            }
            if (fieldList != null) {
                fieldList.addField(nextField);
            }
        }
        return fieldList;
    }

    @Override
    public Returnable visitDec(CmmParser.DecContext ctx) {
        FieldList varDec;
        if (ctx.getChildCount() > 1) {
            // if in struct there is ASSIGNOP, do not visit varDec
            if (ctx.inStruct) {
                OutputHelper.printSemanticError(ErrorType.REDEF_FIELD,
                        ctx.ASSIGNOP().getSymbol().getLine(), ctx.varDec().getText());
                return defaultResult();
            }
            varDec = (FieldList) visit(ctx.varDec());
            Type exp = (Type) visit(ctx.exp());
            if (exp == null) {
                // 跳过类型检查
            } else if (varDec != null && !CheckHelper.isTypeEqual(varDec.getType(), exp)) {
                OutputHelper.printSemanticError(ErrorType.MISMATCH_ASSIGN,
                        ctx.exp().getStart().getLine());
                return defaultResult();
            }
        } else {
            varDec = (FieldList) visit(ctx.varDec());
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
        if (func.getKind() != Kind.FUNCTION) {
            OutputHelper.printSemanticError(ErrorType.NON_FUNC, ctx.ID().getSymbol().getLine(),
                    ctx.ID().getText());
            return defaultResult();
        }
        if (ctx.args() == null) {
            if (((Function) func).getParamList() != null) {
                OutputHelper.printSemanticError(ErrorType.MISMATCH_PARAM,
                        ctx.LP().getSymbol().getLine());
                return defaultResult();
            }
        } else {
            FieldList args = (FieldList) visit(ctx.args());
            if (args == null) {
                return defaultResult();
            }
            if (!CheckHelper.isFieldListEqual(((Function) func).getParamList(), args)) {
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
        if (array.getKind() != Kind.ARRAY) {
            OutputHelper.printSemanticError(ErrorType.NON_ARRAY, ctx.exp(0).getStart().getLine(),
                    ctx.exp(0).getText());
            return defaultResult();
        }
        if (index.getKind() != Kind.INT) {
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
        if (exp.getKind() != Kind.STRUCTURE) {
            OutputHelper.printSemanticError(ErrorType.ILLEGAL_DOT, ctx.DOT().getSymbol().getLine());
            return defaultResult();
        }
        FieldList memberList = ((Structure) exp).getMemberList();
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
        if (exp.getKind() != Kind.INT && exp.getKind() != Kind.FLOAT) {
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
        if (firstExp.getKind() != Kind.INT && firstExp.getKind() != Kind.FLOAT) {
            OutputHelper.printSemanticError(ErrorType.MISMATCH_OPRAND, exp.getStart().getLine());
            return defaultResult();
        }
        if (secondExp.getKind() != Kind.INT && secondExp.getKind() != Kind.FLOAT) {
            OutputHelper.printSemanticError(ErrorType.MISMATCH_OPRAND, exp2.getStart().getLine());
            return defaultResult();
        }
        if (firstExp.getKind() != secondExp.getKind()) {
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
        if (firstExp.getKind() != Kind.INT && firstExp.getKind() != Kind.FLOAT) {
            OutputHelper
                    .printSemanticError(ErrorType.MISMATCH_OPRAND, ctx.exp(0).getStart().getLine());
            return defaultResult();
        }
        if (secondExp.getKind() != Kind.INT && firstExp.getKind() != Kind.FLOAT) {
            OutputHelper
                    .printSemanticError(ErrorType.MISMATCH_OPRAND, ctx.exp(1).getStart().getLine());
            return defaultResult();
        }
        if (firstExp.getKind() != secondExp.getKind()) {
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
        if (firstExp.getKind() != Kind.INT) {
            OutputHelper.printSemanticError(ErrorType.MISMATCH_OPRAND, exp.getStart().getLine());
            return defaultResult();
        }
        if (secondExp.getKind() != Kind.INT) {
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
            OutputHelper.printSemanticError(ErrorType.NOT_LETF_EXP_ASSIGN, ctx.exp(0).getStart().getLine());
            return defaultResult();
        }
        // check type
        if (firstExp.getKind() == Kind.FUNCTION) {
            OutputHelper.printSemanticError(ErrorType.NOT_LETF_EXP_ASSIGN, ctx.exp(0).getStart().getLine());
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
        FieldList fieldList = null;
        FieldList currentField;
        for (int i = 0; i < ctx.exp().size(); i++) {
            Type exp = (Type) visit(ctx.exp(i));
            if (exp == null) {
                continue;
            }
            currentField = new FieldList();
            currentField.setType(exp);
            if (fieldList == null) {
                fieldList = currentField;
            } else {
                fieldList.addField(currentField);
            }
        }
        return fieldList;
    }
}
