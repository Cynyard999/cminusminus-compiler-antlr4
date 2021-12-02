package L3;

import java.util.Stack;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class CmmTreeAnalyzer extends CmmParserBaseVisitor<Returnable> {

    HashTable table = HashTable.getHashTable();

    // Only the function that pushes can pop and called sub_function just uses peek to get needed element
    Stack<Type> typeStack = new Stack<>();


    @Override
    public Returnable visitProgram(CmmParser.ProgramContext ctx) {
        visitChildren(ctx);
        return defaultResult();
    }

    @Override
    public Returnable visitExtDef(CmmParser.ExtDefContext ctx) {
        Type specifier = (Type) visit(ctx.specifier());
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
        visitChildren(ctx);
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
            structure.setName(ctx.optTag().getText());
            structure.setMemberList((FieldList) visit(ctx.defList()));
            if ((structure.getName() != null) && (!structure.getName().isEmpty())) {
                if (table.checkDuplicate(structure.getName())) {
                    printError(ErrorType.DUPLIC_STRUCT, ctx.getStart().getLine());
                    return defaultResult();
                }
                table.addNode(structure, structure.getName());
            }
            return structure;
        } else {
            Type type = table.getType(ctx.tag().getText());
            if (type == null) {
                printError(ErrorType.UNDEF_STRUCT, ctx.getStart().getLine());
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
            printError(ErrorType.REDEF_FEILD, ctx.ID(0).getSymbol().getLine());
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
        function.setName(ctx.ID().getText());
        // just dump duplicate func
        if (table.checkDuplicate(function.getName())) {
            printError(ErrorType.REDEF_FUNC, ctx.getStart().getLine());
            return defaultResult();
        }
        function.setReturnType(type);
        if (ctx.varList() != null) {
            function.setParamList((FieldList) visit(ctx.varList()));
        }
        table.addNode(function, function.getName());
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
                fieldList.addField((FieldList) visit(ctx.paramDec(i)));
            }
        }
        return fieldList;
    }

    @Override
    public Returnable visitParamDec(CmmParser.ParamDecContext ctx) {
        Type specifier = (Type) visit(ctx.specifier());
        typeStack.push(specifier);
        FieldList param = (FieldList) visit(ctx.varDec());
        typeStack.pop();
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
        Type returnType = typeStack.peek();
        if (!CheckHelper.isTypeEqual(exp, returnType)) {
            printError(ErrorType.MISMATCH_RETURN, ctx.exp().getStart().getLine());
        }
        return defaultResult();
    }

    @Override
    public Returnable visitStmtIf(CmmParser.StmtIfContext ctx) {
        Type exp = (Type) visit(ctx.exp());
        if (exp.getKind() != Kind.INT) {
            printError(ErrorType.MISMATCH_OPRAND, ctx.exp().getStart().getLine());
        }
        // continue to visit stmt, despite error occurring
        visit(ctx.stmt());
        return defaultResult();
    }

    @Override
    public Returnable visitStmtIfElse(CmmParser.StmtIfElseContext ctx) {
        Type exp = (Type) visit(ctx.exp());
        if (exp.getKind() != Kind.INT) {
            printError(ErrorType.MISMATCH_OPRAND, ctx.exp().getStart().getLine());
        }
        // continue to visit stmt, despite error occurring
        visit(ctx.stmt(0));
        visit(ctx.stmt(1));
        return defaultResult();
    }

    @Override
    public Returnable visitStmtWhile(CmmParser.StmtWhileContext ctx) {
        Type exp = (Type) visit(ctx.exp());
        if (exp.getKind() != Kind.INT) {
            printError(ErrorType.MISMATCH_OPRAND, ctx.exp().getStart().getLine());
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
                fieldList.addField((FieldList) visit(ctx.def(i)));
            }
        }
        return fieldList;
    }

    @Override
    public Returnable visitDef(CmmParser.DefContext ctx) {
        Type specifier = (Type) visit(ctx.decList());
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
                fieldList.addField((FieldList) visit(ctx.dec(i)));
            }
        }
        return fieldList;
    }

    @Override
    public Returnable visitDec(CmmParser.DecContext ctx) {
        FieldList varDec = (FieldList) visit(ctx.varDec());
        Type exp = null;
        if (ctx.exp() != null) {
            exp = (Type) visit(ctx.exp());
        }
        if (varDec != null && exp != null && !CheckHelper.isTypeEqual((varDec).getType(), exp)) {
            printError(ErrorType.MISMATCH_ASSIGN, ctx.exp().getStart().getLine());
            return defaultResult();
        }
        if (varDec != null) {
            table.addNode(varDec.getType(), varDec.getName());
        }
        return varDec;
    }

    @Override
    public Returnable visitExpDot(CmmParser.ExpDotContext ctx) {
        return super.visitExpDot(ctx);
    }

    @Override
    public Returnable visitExpFunc(CmmParser.ExpFuncContext ctx) {
        return super.visitExpFunc(ctx);
    }

    @Override
    public Returnable visitExpOr(CmmParser.ExpOrContext ctx) {
        return super.visitExpOr(ctx);
    }

    @Override
    public Returnable visitExpRelop(CmmParser.ExpRelopContext ctx) {
        return super.visitExpRelop(ctx);
    }

    @Override
    public Returnable visitExpPlusMinus(CmmParser.ExpPlusMinusContext ctx) {
        return super.visitExpPlusMinus(ctx);
    }

    @Override
    public Returnable visitExpAssignop(CmmParser.ExpAssignopContext ctx) {
        return super.visitExpAssignop(ctx);
    }

    @Override
    public Returnable visitExpParenthesis(CmmParser.ExpParenthesisContext ctx) {
        return super.visitExpParenthesis(ctx);
    }

    @Override
    public Returnable visitExpFloat(CmmParser.ExpFloatContext ctx) {
        return super.visitExpFloat(ctx);
    }

    @Override
    public Returnable visitExpAnd(CmmParser.ExpAndContext ctx) {
        return super.visitExpAnd(ctx);
    }

    @Override
    public Returnable visitExpFuncArgs(CmmParser.ExpFuncArgsContext ctx) {
        return super.visitExpFuncArgs(ctx);
    }

    @Override
    public Returnable visitExpStarDiv(CmmParser.ExpStarDivContext ctx) {
        return super.visitExpStarDiv(ctx);
    }

    @Override
    public Returnable visitExpInt(CmmParser.ExpIntContext ctx) {
        return super.visitExpInt(ctx);
    }

    @Override
    public Returnable visitExpMinusNot(CmmParser.ExpMinusNotContext ctx) {
        return super.visitExpMinusNot(ctx);
    }

    @Override
    public Returnable visitExpId(CmmParser.ExpIdContext ctx) {
        return super.visitExpId(ctx);
    }

    @Override
    public Returnable visitExpBrackets(CmmParser.ExpBracketsContext ctx) {
        return super.visitExpBrackets(ctx);
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

    private void printError(ErrorType errorType, int lineNo) {
        System.err.println("Error type " + errorType.getErrorNo() + " at Line " + lineNo
                + " : check by yourself plz.");
    }
}
