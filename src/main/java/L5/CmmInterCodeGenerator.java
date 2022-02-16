package L5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;

/**
 * @author cynyard
 * @description
 * @date 1/1/22
 */
public class CmmInterCodeGenerator extends CmmParserBaseVisitor<InterCodeList> {

    HashTable table = HashTable.getHashTable();
    Deque<Operand> operandStack = new ArrayDeque<>();

    // contains names of structures which are defined as function params and used without symbol & in funciton body
    HashSet<String> paramStructSet = new HashSet<>();

    @Override
    public InterCodeList visitProgram(CmmParser.ProgramContext ctx) {
        InterCodeList interCodeList = null;
        List<CmmParser.ExtDefContext> extDefList = ctx.extDef();
        for (CmmParser.ExtDefContext extDefContext : extDefList) {
            InterCodeList nextInterCodeList = visit(extDefContext);
            if (interCodeList == null) {
                interCodeList = nextInterCodeList;
                continue;
            }
            interCodeList.addInterCodeList(nextInterCodeList);
        }
        return interCodeList;
    }

    @Override
    public InterCodeList visitExtDef(CmmParser.ExtDefContext ctx) {
        // exclude global variables and type define
        if (ctx.SEMI() != null) {
            return defaultResult();
        }
        return InterCodeList.join(visit(ctx.funDec()), visit(ctx.compSt()));
    }

    @Override
    public InterCodeList visitVarDec(CmmParser.VarDecContext ctx) {
        Operand target = operandStack.peek();
        String idText = ctx.ID(0).getText();
        Type idType = table.getType(idText);
        if (idType.getSymbolKind() == SymbolKind.INT
                || idType.getSymbolKind() == SymbolKind.FLOAT) {
            if (target == null) {
                return defaultResult();
            }
            target.value = idText;
            return defaultResult();
        } else {
            Operand operand = makeNewVariable(idText);
            return new InterCodeList(new InterCode.MemDecCode(CodeKind.DEC, operand,
                    getTypeSize(table.getType(operand.value))));
        }
    }

    @Override
    public InterCodeList visitFunDec(CmmParser.FunDecContext ctx) {
        Operand op = new Operand(OperandKind.FUNCTION, ctx.ID().getText());
        InterCode.MonoOpCode funcDefineCode = new InterCode.MonoOpCode(CodeKind.FUNCTION, op);
        InterCodeList interCodeList = new InterCodeList(funcDefineCode);
        Field curParam = ((Function) table.getType(op.value)).getParamListHead();
        while (curParam != null) {
            if (curParam.getType().getSymbolKind() == SymbolKind.STRUCTURE) {
                // in function body, this param is used as address. record such param temporarily
                paramStructSet.add(curParam.getName());
            }
            Operand paramOp = new Operand(OperandKind.VARIABLE, curParam.getName());
            InterCode paramCode = new InterCode.MonoOpCode(CodeKind.PARAM, paramOp);
            interCodeList.addInterCode(paramCode);
            curParam = curParam.getNext();
        }
        return interCodeList;
    }

    @Override
    public InterCodeList visitCompSt(CmmParser.CompStContext ctx) {
        return InterCodeList.join(visit(ctx.defList()), visit(ctx.stmtList()));
    }

    @Override
    public InterCodeList visitStmtList(CmmParser.StmtListContext ctx) {
        InterCodeList interCodeList = null;
        List<CmmParser.StmtContext> stmtList = ctx.stmt();
        for (CmmParser.StmtContext stmtContext : stmtList) {
            InterCodeList nextInterCodeList = visit(stmtContext);
            if (interCodeList == null) {
                interCodeList = nextInterCodeList;
                continue;
            }
            interCodeList.addInterCodeList(nextInterCodeList);
        }
        return interCodeList;
    }

    @Override
    public InterCodeList visitStmtExp(CmmParser.StmtExpContext ctx) {
        return visit(ctx.exp());
    }

    @Override
    public InterCodeList visitStmtCompSt(CmmParser.StmtCompStContext ctx) {
        return visit(ctx.compSt());
    }

    @Override
    public InterCodeList visitStmtReturn(CmmParser.StmtReturnContext ctx) {
        InterCodeList interCodeList = new InterCodeList();
        Operand temp = makeNewTemp();
        operandStack.push(temp);
        InterCodeList expCode = visit(ctx.exp());
        operandStack.pop();
        interCodeList.addInterCodeList(expCode);
        if (temp.operandKind == OperandKind.ADDRESS) {
            Operand temp2 = makeNewTemp();
            InterCode readAddrCode = new InterCode.AssignCode(CodeKind.READ_ADDR, temp2, temp);
            InterCode returnCode = new InterCode.MonoOpCode(CodeKind.RETURN_, temp2);
            interCodeList.addInterCode(readAddrCode);
            interCodeList.addInterCode(returnCode);
        } else {
            InterCode returnCode = new InterCode.MonoOpCode(CodeKind.RETURN_, temp);
            interCodeList.addInterCode(returnCode);
        }
        return interCodeList;
    }

    @Override
    public InterCodeList visitStmtIf(CmmParser.StmtIfContext ctx) {
        InterCodeList interCodeList = new InterCodeList();
        Operand label1 = makeNewLabel();
        Operand label2 = makeNewLabel();
        InterCodeList ifConditionCode = generateConditionInterCode(ctx.exp(), label1, label2);
        interCodeList.addInterCodeList(ifConditionCode);

        InterCode label1Code = new InterCode.MonoOpCode(CodeKind.LABEL, label1);
        interCodeList.addInterCode(label1Code);

        InterCodeList stmtCode = visit(ctx.stmt());
        interCodeList.addInterCodeList(stmtCode);

        InterCode label2Code = new InterCode.MonoOpCode(CodeKind.LABEL, label2);
        interCodeList.addInterCode(label2Code);

        return interCodeList;
    }

    @Override
    public InterCodeList visitStmtIfElse(CmmParser.StmtIfElseContext ctx) {
        InterCodeList interCodeList = new InterCodeList();
        Operand label1 = makeNewLabel();
        Operand label2 = makeNewLabel();
        Operand label3 = makeNewLabel();

        InterCodeList ifConditionCode = generateConditionInterCode(ctx.exp(), label1, label2);
        interCodeList.addInterCodeList(ifConditionCode);

        InterCode label1Code = new InterCode.MonoOpCode(CodeKind.LABEL, label1);
        interCodeList.addInterCode(label1Code);

        InterCodeList stmt1Code = visit(ctx.stmt(0));
        interCodeList.addInterCodeList(stmt1Code);

        InterCode gotoCode = new InterCode.MonoOpCode(CodeKind.GOTO, label3);
        interCodeList.addInterCode(gotoCode);

        InterCode label2Code = new InterCode.MonoOpCode(CodeKind.LABEL, label2);
        interCodeList.addInterCode(label2Code);

        InterCodeList stmt2Code = visit(ctx.stmt(1));
        interCodeList.addInterCodeList(stmt2Code);

        InterCode label3Code = new InterCode.MonoOpCode(CodeKind.LABEL, label3);
        interCodeList.addInterCode(label3Code);

        return interCodeList;
    }

    @Override
    public InterCodeList visitStmtWhile(CmmParser.StmtWhileContext ctx) {
        InterCodeList interCodeList = new InterCodeList();

        Operand label1 = makeNewLabel();
        Operand label2 = makeNewLabel();
        Operand label3 = makeNewLabel();
        InterCode label1Code = new InterCode.MonoOpCode(CodeKind.LABEL, label1);
        interCodeList.addInterCode(label1Code);

        InterCodeList expCode = generateConditionInterCode(ctx.exp(), label2, label3);
        interCodeList.addInterCodeList(expCode);

        InterCode label2Code = new InterCode.MonoOpCode(CodeKind.LABEL, label2);
        interCodeList.addInterCode(label2Code);

        InterCodeList stmtCode = visit(ctx.stmt());
        interCodeList.addInterCodeList(stmtCode);

        InterCode gotoLabel1Code = new InterCode.MonoOpCode(CodeKind.GOTO, label1);
        interCodeList.addInterCode(gotoLabel1Code);

        InterCode label3Code = new InterCode.MonoOpCode(CodeKind.LABEL, label3);
        interCodeList.addInterCode(label3Code);

        return interCodeList;
    }

    @Override
    public InterCodeList visitDefList(CmmParser.DefListContext ctx) {
        InterCodeList interCodeList = null;
        List<CmmParser.DefContext> defList = ctx.def();
        for (CmmParser.DefContext defContext : defList) {
            InterCodeList nextInterCodeList = visit(defContext);
            if (interCodeList == null) {
                interCodeList = nextInterCodeList;
                continue;
            }
            interCodeList.addInterCodeList(nextInterCodeList);
        }
        return interCodeList;
    }

    @Override
    public InterCodeList visitDef(CmmParser.DefContext ctx) {
        return visit(ctx.decList());
    }

    @Override
    public InterCodeList visitDecList(CmmParser.DecListContext ctx) {
        InterCodeList interCodeList = null;
        List<CmmParser.DecContext> decList = ctx.dec();
        for (CmmParser.DecContext decContext : decList) {
            InterCodeList nextInterCodeList = visit(decContext);
            if (interCodeList == null) {
                interCodeList = nextInterCodeList;
                continue;
            }
            interCodeList.addInterCodeList(nextInterCodeList);
        }
        return interCodeList;
    }

    @Override
    public InterCodeList visitDec(CmmParser.DecContext ctx) {
        if (ctx.getChildCount() == 1) {
            return visit(ctx.varDec());
        } else {
            InterCodeList interCodeList = new InterCodeList();
            Operand temp1 = makeNewTemp();
            operandStack.push(temp1);
            InterCodeList varDecInterCodeList = visit(ctx.varDec());
            interCodeList.addInterCodeList(varDecInterCodeList);
            operandStack.pop();

            Operand temp2 = makeNewTemp();
            operandStack.push(temp2);
            InterCodeList expInterCodeList = visit(ctx.exp());
            interCodeList.addInterCodeList(expInterCodeList);
            operandStack.pop();

            if (temp2.operandKind == OperandKind.ADDRESS) {
                Operand tempValue = makeNewTemp();
                InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR,
                        tempValue,
                        temp2);
                temp2 = tempValue;
                interCodeList.addInterCode(readAddrInterCode);
            }

            InterCode assignInterCode = new InterCode.AssignCode(CodeKind.ASSIGN, temp1,
                    temp2);
            interCodeList.addInterCode(assignInterCode);

            return interCodeList;
        }
    }

    @Override
    public InterCodeList visitExpDot(CmmParser.ExpDotContext ctx) {
        Operand target = operandStack.peek();
        if (target == null) {
            return defaultResult();
        }
        Type structType = getExpType(ctx.exp());
        if (structType.getSymbolKind() != SymbolKind.STRUCTURE) {
            return defaultResult();
        }
        InterCodeList interCodeList = new InterCodeList();

        // compute base addr
        Operand baseAddr = makeNewTemp(OperandKind.ADDRESS);
        operandStack.push(baseAddr);
        InterCodeList getBaseAddrInterCodeList = visit(ctx.exp());
        interCodeList.addInterCodeList(getBaseAddrInterCodeList);
        operandStack.pop();

        // compute offset
        String fieldName = ctx.ID().getText();
        Field curField = ((Structure) structType).getMemberListHead();
        int offset = 0;
        // definitely can fieldName be found in structure
        while (curField != null) {
            if (curField.getName().equals(fieldName)) {
                break;
            }
            offset += getTypeSize(curField.getType());
            curField = curField.getNext();
        }
        // exp DOT ID returns address for upper level to deal
        if (target.operandKind != OperandKind.ADDRESS) {
            target.operandKind = OperandKind.ADDRESS;
        }
        // compute addr
        if (offset == 0) {
            // reduce use of constant
            target.value = baseAddr.value;
            return interCodeList;
        }
        InterCode computeAddrInterCode = new InterCode.BinOpCode(CodeKind.ADD, baseAddr,
                makeNewConstant(String.valueOf(offset)), target);
        interCodeList.addInterCode(computeAddrInterCode);

        return interCodeList;
    }

    @Override
    public InterCodeList visitExpOr(CmmParser.ExpOrContext ctx) {
        Operand target = operandStack.peek();
        if (target == null) {
            return defaultResult();
        }
        return generateLogicResultInterCode(target, ctx);
    }

    @Override
    public InterCodeList visitExpRelop(CmmParser.ExpRelopContext ctx) {
        Operand target = operandStack.peek();
        if (target == null) {
            return defaultResult();
        }
        return generateLogicResultInterCode(target, ctx);
    }

    @Override
    public InterCodeList visitExpPlusMinus(CmmParser.ExpPlusMinusContext ctx) {
        Operand target = operandStack.peek();
        if (target == null) {
            return defaultResult();
        }
        InterCodeList interCodeList = new InterCodeList();

        Operand temp1 = makeNewTemp();
        operandStack.push(temp1);
        InterCodeList expCode1 = visit(ctx.exp(0));
        interCodeList.addInterCodeList(expCode1);
        operandStack.pop();

        if (temp1.operandKind == OperandKind.ADDRESS) {
            Operand temp = makeNewTemp();
            InterCode readAddrCode = new InterCode.AssignCode(CodeKind.READ_ADDR, temp, temp1);
            interCodeList.addInterCode(readAddrCode);
            temp1 = temp;
        }

        Operand temp2 = makeNewTemp();
        operandStack.push(temp2);
        InterCodeList expCode2 = visit(ctx.exp(1));
        interCodeList.addInterCodeList(expCode2);
        operandStack.pop();

        if (temp2.operandKind == OperandKind.ADDRESS) {
            Operand temp = makeNewTemp();
            InterCode readAddrCode = new InterCode.AssignCode(CodeKind.READ_ADDR, temp, temp2);
            interCodeList.addInterCode(readAddrCode);
            temp2 = temp;
        }

        if (ctx.PLUS() != null) {
            InterCode plusInterCode = new InterCode.BinOpCode(CodeKind.ADD, temp1, temp2,
                    target);
            interCodeList.addInterCode(plusInterCode);
        } else {
            InterCode minusInterCode = new InterCode.BinOpCode(CodeKind.SUB, temp1, temp2, target);
            interCodeList.addInterCode(minusInterCode);
        }
        return interCodeList;
    }

    @Override
    public InterCodeList visitExpAssignop(CmmParser.ExpAssignopContext ctx) {

        InterCodeList interCodeList = new InterCodeList();

        Operand target = operandStack.peek();
        Operand temp1 = makeNewTemp();
        operandStack.push(temp1);
        InterCodeList expCode1 = visit(ctx.exp(0));
        interCodeList.addInterCodeList(expCode1);
        operandStack.pop();

        Operand temp2 = makeNewTemp();
        operandStack.push(temp2);
        InterCodeList expCode2 = visit(ctx.exp(1));
        interCodeList.addInterCodeList(expCode2);
        operandStack.pop();

        // temp1 = temp2
        InterCode assignCode = new InterCode.AssignCode(CodeKind.ASSIGN, temp1, temp2);
        InterCodeList assignCodeList = new InterCodeList(assignCode);

        // *temp1 = temp2
        if (temp1.operandKind == OperandKind.ADDRESS && temp2.operandKind != OperandKind.ADDRESS) {
            assignCode.codeKind = CodeKind.WRITE_ADDR;
        }

        // temp1 = *temp2
        if (temp1.operandKind != OperandKind.ADDRESS && temp2.operandKind == OperandKind.ADDRESS) {
            assignCode.codeKind = CodeKind.READ_ADDR;
        }

        // temp3 = *temp2
        // *temp1 = temp3
        if (temp1.operandKind == OperandKind.ADDRESS && temp2.operandKind == OperandKind.ADDRESS) {
            Operand temp3 = makeNewTemp();
            InterCode temp2Totemp3AssignCode = new InterCode.AssignCode(CodeKind.READ_ADDR,
                    temp3,
                    temp2);
            InterCode temp3totemp1AssignCode = new InterCode.AssignCode(CodeKind.WRITE_ADDR,
                    temp1,
                    temp3);
            // dump original assignCode
            assignCodeList = new InterCodeList(temp2Totemp3AssignCode);
            assignCodeList.addInterCode(temp3totemp1AssignCode);
        }

        // write value of temp1 to target
        if (target != null) {
            // target = temp1
            InterCode assignToTargetCode = new InterCode.AssignCode(CodeKind.ASSIGN, target,
                    temp1);
            InterCodeList assignToTargetCodeList = new InterCodeList(assignToTargetCode);

            // *target = temp1
            if (target.operandKind == OperandKind.ADDRESS
                    && temp1.operandKind != OperandKind.ADDRESS) {
                assignToTargetCode.codeKind = CodeKind.WRITE_ADDR;
            }

            // target = *temp1
            if (target.operandKind != OperandKind.ADDRESS
                    && temp1.operandKind == OperandKind.ADDRESS) {
                assignToTargetCode.codeKind = CodeKind.READ_ADDR;
            }

            // temp = *temp1
            // *target = temp
            if (target.operandKind == OperandKind.ADDRESS
                    && temp1.operandKind == OperandKind.ADDRESS) {
                Operand temp = makeNewTemp();
                InterCode temp1ToTemp = new InterCode.AssignCode(CodeKind.READ_ADDR, temp,
                        temp1);
                InterCode tempToTarget = new InterCode.AssignCode(CodeKind.WRITE_ADDR, temp1,
                        temp);
                // dump original assignToTargetCode
                assignToTargetCodeList = new InterCodeList(temp1ToTemp);
                assignToTargetCodeList.addInterCode(tempToTarget);
            }
            assignCodeList.addInterCodeList(assignToTargetCodeList);
        }

        interCodeList.addInterCodeList(assignCodeList);
        return interCodeList;

    }

    @Override
    public InterCodeList visitExpParenthesis(CmmParser.ExpParenthesisContext ctx) {
        return visit(ctx.exp());
    }

    @Override
    public InterCodeList visitExpFloat(CmmParser.ExpFloatContext ctx) {
        Operand target = operandStack.peek();
        if (target != null) {
            return new InterCodeList(new InterCode.AssignCode(CodeKind.ASSIGN, target,
                    makeNewConstant(ctx.FLOAT().getText())));
        }
        return defaultResult();
    }

    @Override
    public InterCodeList visitExpAnd(CmmParser.ExpAndContext ctx) {
        Operand target = operandStack.peek();
        if (target == null) {
            return defaultResult();
        }
        return generateLogicResultInterCode(target, ctx);
    }

    @Override
    public InterCodeList visitExpFuncArgs(CmmParser.ExpFuncArgsContext ctx) {
        if ("write".equals(ctx.ID().getText())) {
            InterCodeList interCodeList = new InterCodeList();
            Operand temp = makeNewTemp();
            operandStack.push(temp);
            interCodeList.addInterCodeList(visit(ctx.args().exp(0)));
            operandStack.pop();
            if (temp.operandKind == OperandKind.ADDRESS) {
                Operand temp2 = makeNewTemp();
                InterCode readAddrCode = new InterCode.AssignCode(CodeKind.READ_ADDR, temp2,
                        temp);
                interCodeList.addInterCode(readAddrCode);
                temp = temp2;
            }
            InterCode writeInterCode = new InterCode.MonoOpCode(CodeKind.WRITE, temp);
            interCodeList.addInterCode(writeInterCode);

            return interCodeList;
        }

        Operand target = operandStack.peek();
        if ("read".equals(ctx.ID().getText())) {
            if (target == null) {
                return defaultResult();
            }
            return new InterCodeList(new InterCode.MonoOpCode(CodeKind.READ, target));
        }

        if (ctx.args() != null) {
            if (target == null) {
                target = makeNewTemp();
            }
            InterCodeList argsCodeList = visit(ctx.args());
            InterCode callFunc = new InterCode.AssignCode(CodeKind.CALL, target,
                    new Operand(OperandKind.FUNCTION, ctx.ID().getText()));
            argsCodeList.addInterCode(callFunc);
            return argsCodeList;
        } else {
            if (target == null) {
                target = makeNewTemp();
            }
            return new InterCodeList(new InterCode.AssignCode(CodeKind.CALL, target,
                    new Operand(OperandKind.FUNCTION, ctx.ID().getText())));
        }
    }

    @Override
    public InterCodeList visitExpStarDiv(CmmParser.ExpStarDivContext ctx) {
        Operand target = operandStack.peek();
        if (target == null) {
            return defaultResult();
        }
        InterCodeList interCodeList = new InterCodeList();

        Operand temp1 = makeNewTemp();
        operandStack.push(temp1);
        interCodeList.addInterCodeList(visit(ctx.exp(0)));
        operandStack.pop();

        if (temp1.operandKind == OperandKind.ADDRESS) {
            Operand tempValue = makeNewTemp();
            InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR,
                    tempValue,
                    temp1);
            temp1 = tempValue;
            interCodeList.addInterCode(readAddrInterCode);
        }

        Operand temp2 = makeNewTemp();
        operandStack.push(temp2);
        interCodeList.addInterCodeList(visit(ctx.exp(1)));
        operandStack.pop();

        if (temp2.operandKind == OperandKind.ADDRESS) {
            Operand tempValue = makeNewTemp();
            InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR,
                    tempValue,
                    temp2);
            temp2 = tempValue;
            interCodeList.addInterCode(readAddrInterCode);
        }

        if (ctx.STAR() != null) {
            InterCode starInterCode = new InterCode.BinOpCode(CodeKind.MUL, temp1, temp2,
                    target);
            interCodeList.addInterCode(starInterCode);
        } else {
            InterCode divInterCode = new InterCode.BinOpCode(CodeKind.DIV, temp1, temp2,
                    target);
            interCodeList.addInterCode(divInterCode);
        }
        return interCodeList;
    }


    @Override
    public InterCodeList visitExpInt(CmmParser.ExpIntContext ctx) {
        Operand target = operandStack.peek();
        if (target != null) {
            // reduce use of temp var
            target.operandKind = OperandKind.CONSTANT;
            target.value = ctx.INT().getText();
        }
        return defaultResult();
    }

    @Override
    public InterCodeList visitExpMinusNot(CmmParser.ExpMinusNotContext ctx) {
        Operand target = operandStack.peek();
        if (ctx.MINUS() != null) {
            InterCodeList interCodeList = new InterCodeList();

            if (target == null) {
                return defaultResult();
            }
            Operand temp = makeNewTemp();
            operandStack.push(temp);
            // temp = xxx
            interCodeList.addInterCodeList(visit(ctx.exp()));
            operandStack.pop();

            // if temp.kind == address, read this address
            if (temp.operandKind == OperandKind.ADDRESS) {
                Operand tempValue = makeNewTemp();
                InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR,
                        tempValue,
                        temp);
                temp = tempValue;
                interCodeList.addInterCode(readAddrInterCode);
            }

            // target = 0 - temp
            InterCode minusInterCode = new InterCode.BinOpCode(CodeKind.SUB,
                    makeNewConstant("0"), temp, target);
            interCodeList.addInterCode(minusInterCode);
            return interCodeList;

        } else {
            if (target == null) {
                return defaultResult();
            }
            return generateLogicResultInterCode(target, ctx);
        }
    }

    @Override
    public InterCodeList visitExpId(CmmParser.ExpIdContext ctx) {
        Operand target = operandStack.peek();
        String idText = ctx.ID().getText();
        Type idType = table.getType(idText);
        if (idType.getSymbolKind() == SymbolKind.INT
                || idType.getSymbolKind() == SymbolKind.FLOAT) {
            // 直接把temp的名字改为ID的名字，少了一行tempX = xxx的代码
            if (target != null) {
                target.value = idText;
            }
            return defaultResult();
        }
        // exp LB exp RB中的第一个exp
        else if (idType.getSymbolKind() == SymbolKind.ARRAY) {
            if (target != null) {
                target.operandKind = OperandKind.ADDRESS;
                return new InterCodeList(new InterCode.AssignCode(CodeKind.GET_ADDR, target,
                        makeNewVariable(idText)));
            }
        }
        // exp DOT ID中的第一个exp
        else if (idType.getSymbolKind() == SymbolKind.STRUCTURE) {
            if (target != null) {
                if (paramStructSet.contains(idText)) {
                    target.value = idText;
                    target.operandKind = OperandKind.ADDRESS;
                    return defaultResult();
                } else {
                    target.operandKind = OperandKind.ADDRESS;
                    return new InterCodeList(new InterCode.AssignCode(CodeKind.GET_ADDR, target,
                            makeNewVariable(idText)));
                }
            }
        }
        return defaultResult();
    }

    /*
     * @author cynyard
     * @date 1/4/22
     * @param ctx
     * @return InterCode
     * @description change target kind to ADDRESS to which READ/WRITE happen in upper level
     */
    @Override
    public InterCodeList visitExpBrackets(CmmParser.ExpBracketsContext ctx) {
        Operand target = operandStack.peek();
        if (target == null) {
            return defaultResult();
        }
        InterCodeList interCodeList = new InterCodeList();

        // compute base address
        Operand baseAddr = makeNewTemp(OperandKind.ADDRESS);
        operandStack.push(baseAddr);
        InterCodeList getBaseAddrInterCodeList = visit(ctx.exp(0));
        interCodeList.addInterCodeList(getBaseAddrInterCodeList);
        operandStack.pop();

        // get index
        Operand arrayIndex = makeNewTemp();
        operandStack.push(arrayIndex);
        InterCodeList getIndexInterCodeList = visit(ctx.exp(1));
        interCodeList.addInterCodeList(getIndexInterCodeList);
        operandStack.pop();

        // compute offset
        Operand offset = makeNewTemp();
        // 直接计算出offset
        if (arrayIndex.operandKind == OperandKind.CONSTANT) {
            offset.operandKind = OperandKind.CONSTANT;
            // ctx.exp(0) is array type for sure
            offset.value = String.valueOf(
                    Integer.parseInt(arrayIndex.value) * getTypeSize(
                            ((Array) getExpType(ctx.exp(0))).elements));
        }

        // 如果不是用常数来表示的index，那index * elementSize
        else {
            InterCode computeOffsetInterCode = new InterCode.BinOpCode(CodeKind.MUL,
                    makeNewConstant(String.valueOf(getTypeSize(
                            ((Array) getExpType(ctx.exp(0))).elements))),
                    arrayIndex, offset);
            interCodeList.addInterCode(computeOffsetInterCode);
        }

        if (target.operandKind != OperandKind.ADDRESS) {
            target.operandKind = OperandKind.ADDRESS;
        }
        // compute actual addr
        InterCode computeAddrInterCode = new InterCode.BinOpCode(CodeKind.ADD, baseAddr,
                offset,
                target);

        interCodeList.addInterCode(computeAddrInterCode);
        return interCodeList;
    }


    @Override
    public InterCodeList visitArgs(CmmParser.ArgsContext ctx) {
        List<CmmParser.ExpContext> expList = ctx.exp();
        InterCodeList interCodeList = new InterCodeList();
        for (int i = expList.size() - 1; i >= 0; i--) {
            Operand temp = makeNewTemp();
            operandStack.push(temp);
            interCodeList.addInterCodeList(visit(expList.get(i)));
            operandStack.pop();

            InterCode argCode;
            Type expType = getExpType(expList.get(i));
            if (temp.operandKind == OperandKind.ADDRESS) {
                // sub(a); // a is struct or array
                if (expType.getSymbolKind() == SymbolKind.ARRAY
                        || expType.getSymbolKind() == SymbolKind.STRUCTURE) {
                    argCode = new InterCode.MonoOpCode(CodeKind.ARG, temp);
                }
                // sub(a.b); sub(a[0]) // a is struct or array
                else {
                    Operand temp2 = makeNewTemp();
                    InterCode readAddrCode = new InterCode.AssignCode(CodeKind.READ_ADDR, temp2,
                            temp);
                    interCodeList.addInterCode(readAddrCode);
                    argCode = new InterCode.MonoOpCode(CodeKind.ARG, temp2);
                }
            }
            // a is normal variable
            else {
                argCode = new InterCode.MonoOpCode(CodeKind.ARG, temp);
            }
            interCodeList.addInterCode(argCode);
        }
        return interCodeList;
    }

    private InterCodeList generateLogicResultInterCode(Operand target, CmmParser.ExpContext ctx) {
        InterCodeList interCodeList = new InterCodeList();

        // target = 0
        InterCode setToDefaultInterCode = new InterCode.AssignCode(CodeKind.ASSIGN, target,
                makeNewConstant("0"));
        interCodeList.addInterCode(setToDefaultInterCode);

        Operand label1 = makeNewLabel();
        Operand label2 = makeNewLabel();
        // if xxx goto label1
        InterCodeList conditionJudgeCode = generateConditionInterCode(ctx, label1, label2);
        interCodeList.addInterCodeList(conditionJudgeCode);

        // Label label1
        InterCode label1Code = new InterCode.MonoOpCode(CodeKind.LABEL, label1);
        interCodeList.addInterCode(label1Code);

        // target = 1
        InterCode setToTrueInterCode = new InterCode.AssignCode(CodeKind.ASSIGN, target,
                makeNewConstant("1"));
        interCodeList.addInterCode(setToTrueInterCode);

        // Label label2
        InterCode label2Code = new InterCode.MonoOpCode(CodeKind.LABEL, label2);
        interCodeList.addInterCode(label2Code);

        return interCodeList;
    }

    private InterCodeList generateIfGoToInterCode(Operand op1, Operand op2, String relop,
            Operand labelTrue, Operand labelFalse) {
        InterCode ifGoToInterCode = new InterCode.ConditionJumpCode(CodeKind.IF_GOTO, op1,
                relop,
                op2, labelTrue);
        InterCode gotoInterCode = new InterCode.MonoOpCode(CodeKind.GOTO, labelFalse);

        InterCodeList interCodeList = new InterCodeList(ifGoToInterCode);
        interCodeList.addInterCode(gotoInterCode);
        return interCodeList;
    }

    private InterCodeList generateConditionInterCode(CmmParser.ExpContext ctx, Operand labelTrue,
            Operand labelFalse) {
        if (ctx instanceof CmmParser.ExpMinusNotContext) {
            // NOT exp
            if (((CmmParser.ExpMinusNotContext) ctx).NOT() != null) {
                return generateConditionInterCode(((CmmParser.ExpMinusNotContext) ctx).exp(),
                        labelFalse,
                        labelTrue);
            }
            // MINUS exp
            else {
                InterCodeList interCodeList = new InterCodeList();

                Operand temp = makeNewTemp();
                operandStack.push(temp);
                // temp = xxx
                interCodeList.addInterCodeList(visit(((CmmParser.ExpMinusNotContext) ctx).exp()));
                operandStack.pop();

                if (temp.operandKind == OperandKind.ADDRESS) {
                    Operand tempValue = makeNewTemp();
                    InterCode readAddrInterCode = new InterCode.AssignCode(
                            CodeKind.READ_ADDR,
                            tempValue,
                            temp);
                    temp = tempValue;
                    interCodeList.addInterCode(readAddrInterCode);
                }

                Operand tempResult = makeNewTemp();
                // tempResult = 0 - temp
                InterCode minusInterCode = new InterCode.BinOpCode(CodeKind.SUB,
                        tempResult, makeNewConstant("0"), temp);
                interCodeList.addInterCode(minusInterCode);

                // if tempResult != 0 goto labelTrue
                // goto labelFalse
                InterCodeList ifGoToInterCodeList = generateIfGoToInterCode(tempResult,
                        makeNewConstant("0"),
                        "!=", labelTrue, labelFalse);
                interCodeList.addInterCodeList(ifGoToInterCodeList);

                return interCodeList;
            }
        }
        // exp AND exp
        else if (ctx instanceof CmmParser.ExpAndContext) {
            Operand label1 = makeNewLabel();
            InterCodeList code1 = generateConditionInterCode(((CmmParser.ExpAndContext) ctx).exp(0),
                    label1,
                    labelFalse);
            InterCode label1Code = new InterCode.MonoOpCode(CodeKind.LABEL, label1);
            code1.addInterCode(label1Code);

            InterCodeList code2 = generateConditionInterCode(((CmmParser.ExpAndContext) ctx).exp(1),
                    labelTrue,
                    labelFalse);

            return InterCodeList.join(code1, code2);
        }

        // exp OR exp
        else if (ctx instanceof CmmParser.ExpOrContext) {
            Operand label1 = makeNewLabel();
            InterCodeList code1 = generateConditionInterCode(((CmmParser.ExpOrContext) ctx).exp(0),
                    labelTrue,
                    label1);
            InterCode label1Code = new InterCode.MonoOpCode(CodeKind.LABEL, label1);
            code1.addInterCode(label1Code);

            InterCodeList code2 = generateConditionInterCode(((CmmParser.ExpOrContext) ctx).exp(1),
                    labelTrue,
                    labelFalse);

            return InterCodeList.join(code1, code2);
        }
        // exp RELOP exp
        else if (ctx instanceof CmmParser.ExpRelopContext) {
            InterCodeList interCodeList = new InterCodeList();

            Operand temp1 = makeNewTemp();
            operandStack.push(temp1);
            interCodeList.addInterCodeList(visit(((CmmParser.ExpRelopContext) ctx).exp(0)));
            operandStack.pop();
            if (temp1.operandKind == OperandKind.ADDRESS) {
                Operand temp1Value = makeNewTemp();
                InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR,
                        temp1Value,
                        temp1);
                temp1 = temp1Value;
                interCodeList.addInterCode(readAddrInterCode);
            }

            Operand temp2 = makeNewTemp();
            operandStack.push(temp2);
            interCodeList.addInterCodeList(visit(((CmmParser.ExpRelopContext) ctx).exp(1)));
            operandStack.pop();
            if (temp2.operandKind == OperandKind.ADDRESS) {
                Operand temp2Value = makeNewTemp();
                InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR,
                        temp2Value,
                        temp2);
                temp2 = temp2Value;
                interCodeList.addInterCode(readAddrInterCode);
            }

            InterCodeList ifGoToInterCodeList = generateIfGoToInterCode(temp1, temp2,
                    ((CmmParser.ExpRelopContext) ctx).RELOP().getText(), labelTrue, labelFalse);
            interCodeList.addInterCodeList(ifGoToInterCodeList);

            return interCodeList;
        } else {
            InterCodeList interCodeList = new InterCodeList();
            Operand temp = makeNewTemp();
            operandStack.push(temp);
            interCodeList.addInterCodeList(visit(ctx));
            operandStack.pop();

            if (temp.operandKind == OperandKind.ADDRESS) {
                Operand tempValue = makeNewTemp();
                InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR,
                        tempValue,
                        temp);
                temp = tempValue;
                interCodeList.addInterCode(readAddrInterCode);
            }


            InterCodeList ifGoToInterCodeList = generateIfGoToInterCode(temp, makeNewConstant("0"),
                    "!=",
                    labelTrue, labelFalse);
            interCodeList.addInterCodeList(ifGoToInterCodeList);
            return interCodeList;
        }
    }

    private Operand makeNewLabel() {
        return new Operand(OperandKind.LABEL, "label" + FlagHelper.labelCount++);
    }

    private Operand makeNewTemp() {
        return new Operand(OperandKind.VARIABLE, "t" + FlagHelper.tempVariableCount++);
    }

    private Operand makeNewTemp(OperandKind kind) {
        return new Operand(kind, "t" + FlagHelper.tempVariableCount++);
    }

    private Operand makeNewConstant(String value) {
        return new Operand(OperandKind.CONSTANT, value);
    }

    private Operand makeNewVariable(String name) {
        return new Operand(OperandKind.VARIABLE, name);
    }


    private Type getExpType(CmmParser.ExpContext ctx) {
        if (ctx instanceof CmmParser.ExpIdContext) {
            return table.getType(((CmmParser.ExpIdContext) ctx).ID().getText());
        }
        if (ctx instanceof CmmParser.ExpParenthesisContext) {
            return getExpType(((CmmParser.ExpParenthesisContext) ctx).exp());
        }
        if (ctx instanceof CmmParser.ExpDotContext) {
            return table.getType(((CmmParser.ExpDotContext) ctx).ID().getText());
        }
        if (ctx instanceof CmmParser.ExpBracketsContext) {
            Type expType = getExpType(((CmmParser.ExpBracketsContext) ctx).exp(0));
            if (expType.getSymbolKind() == SymbolKind.ARRAY) {
                return getArrayBasicElementType(expType);
            }
            return expType;
        }
        if (ctx instanceof CmmParser.ExpFuncArgsContext) {
            return ((Function) table.getType(((CmmParser.ExpFuncArgsContext) ctx).ID().getText()))
                    .getReturnType();
        }
        if (ctx instanceof CmmParser.ExpStarDivContext) {
            return getExpType(((CmmParser.ExpStarDivContext) ctx).exp(0));
        }
        if (ctx instanceof CmmParser.ExpPlusMinusContext) {
            return getExpType(((CmmParser.ExpPlusMinusContext) ctx).exp(0));
        }
        if (ctx instanceof CmmParser.ExpIntContext) {
            return new Basic("int");
        }
        if (ctx instanceof CmmParser.ExpFloatContext) {
            return new Basic("float");
        } else {
            return new Basic("int");
        }
    }

    private Type getArrayBasicElementType(Type array) {
        if (((Array) array).elements.getSymbolKind() == SymbolKind.ARRAY) {
            return getArrayBasicElementType((Array) ((Array) array).getElements());
        }
        return ((Array) array).getElements();
    }


    private int getTypeSize(Type type) {
        if (type.getSymbolKind() == SymbolKind.INT
                || type.getSymbolKind() == SymbolKind.FLOAT) {
            return 4;
        } else if (type.getSymbolKind() == SymbolKind.STRUCTURE) {
            int res = 0;
            Field curField = ((Structure) type).getMemberListHead();
            while (curField != null) {
                res += getTypeSize(curField.getType());
                curField = curField.getNext();
            }
            return res;
        } else if (type.getSymbolKind() == SymbolKind.ARRAY) {
            return ((Array) type).size * getTypeSize(((Array) type).elements);
        } else {
            return 0;
        }
    }

}
