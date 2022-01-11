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
public class CmmInterCodeGenerator extends CmmParserBaseVisitor<InterCode> {

    HashTable table = HashTable.getHashTable();
    Deque<Operand> operandStack = new ArrayDeque<>();

    // contains names of structures which are defined as function params and used without symbol & in funciton body
    HashSet<String> paramStructSet = new HashSet<>();

    @Override
    public InterCode visitProgram(CmmParser.ProgramContext ctx) {
        InterCode interCodeHead = null;
        List<CmmParser.ExtDefContext> extDefList = ctx.extDef();
        for (CmmParser.ExtDefContext extDefContext : extDefList) {
            InterCode nextInterCode = visit(extDefContext);
            if (interCodeHead == null) {
                interCodeHead = nextInterCode;
                continue;
            }
            interCodeHead.addInterCode(nextInterCode);
        }
        return interCodeHead;
    }

    @Override
    public InterCode visitExtDef(CmmParser.ExtDefContext ctx) {
        // exclude global variables and type define
        if (ctx.SEMI() != null) {
            return defaultResult();
        }
        return InterCode.join(visit(ctx.funDec()), visit(ctx.compSt()));
    }

    @Override
    public InterCode visitVarDec(CmmParser.VarDecContext ctx) {
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
            return new InterCode.MemDecCode(CodeKind.DEC, operand,
                    getTypeSize(table.getType(operand.value)));
        }
    }

    @Override
    public InterCode visitFunDec(CmmParser.FunDecContext ctx) {
        Operand op = new Operand(OperandKind.FUNCTION, ctx.ID().getText());
        InterCode.MonoOpCode funcDefineCode = new InterCode.MonoOpCode(CodeKind.FUNCTION, op);
        Field curParam = ((Function) table.getType(op.value)).getParamListHead();
        while (curParam != null) {
            if (curParam.getType().getSymbolKind() == SymbolKind.STRUCTURE) {
                // in function body, this param is used as address
                paramStructSet.add(curParam.getName());
            }
            Operand paramOp = new Operand(OperandKind.VARIABLE, curParam.getName());
            InterCode paramCode = new InterCode.MonoOpCode(CodeKind.PARAM, paramOp);
            funcDefineCode.addInterCode(paramCode);
            curParam = curParam.getNext();
        }
        return funcDefineCode;
    }

    @Override
    public InterCode visitCompSt(CmmParser.CompStContext ctx) {
        return InterCode.join(visit(ctx.defList()), visit(ctx.stmtList()));
    }

    @Override
    public InterCode visitStmtList(CmmParser.StmtListContext ctx) {
        InterCode interCodeHead = null;
        List<CmmParser.StmtContext> stmtList = ctx.stmt();
        for (CmmParser.StmtContext stmtContext : stmtList) {
            InterCode nextInterCode = visit(stmtContext);
            if (interCodeHead == null) {
                interCodeHead = nextInterCode;
                continue;
            }
            interCodeHead.addInterCode(nextInterCode);
        }
        return interCodeHead;
    }

    @Override
    public InterCode visitStmtExp(CmmParser.StmtExpContext ctx) {
        return visit(ctx.exp());
    }

    @Override
    public InterCode visitStmtCompSt(CmmParser.StmtCompStContext ctx) {
        return visit(ctx.compSt());
    }

    @Override
    public InterCode visitStmtReturn(CmmParser.StmtReturnContext ctx) {
        Operand temp = makeNewTemp();
        operandStack.push(temp);
        InterCode expCode = visit(ctx.exp());
        operandStack.pop();
        if (temp.operandKind == OperandKind.ADDRESS) {
            Operand temp2 = makeNewTemp();
            InterCode readAddrCode = new InterCode.AssignCode(CodeKind.READ_ADDR, temp2, temp);
            InterCode returnCode = new InterCode.MonoOpCode(CodeKind.RETURN_, temp2);
            return InterCode.join(InterCode.join(expCode, readAddrCode), returnCode);
        } else {
            InterCode returnCode = new InterCode.MonoOpCode(CodeKind.RETURN_, temp);
            return InterCode.join(expCode, returnCode);
        }
    }

    @Override
    public InterCode visitStmtIf(CmmParser.StmtIfContext ctx) {
        Operand label1 = makeNewLabel();
        Operand label2 = makeNewLabel();
        InterCode ifConditionCode = generateConditionInterCode(ctx.exp(), label1, label2);
        InterCode label1Code = new InterCode.MonoOpCode(CodeKind.LABEL, label1);
        InterCode stmtCode = visit(ctx.stmt());
        InterCode label2Code = new InterCode.MonoOpCode(CodeKind.LABEL, label2);
        return InterCode.join(
                InterCode.join(
                        InterCode.join(ifConditionCode, label1Code),
                        stmtCode),
                label2Code);
    }

    @Override
    public InterCode visitStmtIfElse(CmmParser.StmtIfElseContext ctx) {
        Operand label1 = makeNewLabel();
        Operand label2 = makeNewLabel();
        Operand label3 = makeNewLabel();
        InterCode ifConditionCode = generateConditionInterCode(ctx.exp(), label1, label2);
        InterCode label1Code = new InterCode.MonoOpCode(CodeKind.LABEL, label1);
        InterCode stmt1Code = visit(ctx.stmt(0));
        InterCode gotoCode = new InterCode.MonoOpCode(CodeKind.GOTO, label3);
        InterCode label2Code = new InterCode.MonoOpCode(CodeKind.LABEL, label2);
        InterCode stmt2Code = visit(ctx.stmt(1));
        InterCode label3Code = new InterCode.MonoOpCode(CodeKind.LABEL, label3);
        return InterCode.join(
                InterCode.join(
                        InterCode.join(
                                InterCode.join(
                                        InterCode.join(
                                                InterCode.join(
                                                        ifConditionCode, label1Code),
                                                stmt1Code),
                                        gotoCode),
                                label2Code),
                        stmt2Code),
                label3Code);
    }

    @Override
    public InterCode visitStmtWhile(CmmParser.StmtWhileContext ctx) {
        Operand label1 = makeNewLabel();
        Operand label2 = makeNewLabel();
        Operand label3 = makeNewLabel();
        InterCode label1Code = new InterCode.MonoOpCode(CodeKind.LABEL, label1);
        InterCode expCode = generateConditionInterCode(ctx.exp(), label2, label3);
        InterCode label2Code = new InterCode.MonoOpCode(CodeKind.LABEL, label2);
        InterCode stmtCode = visit(ctx.stmt());
        InterCode gotoLabel1Code = new InterCode.MonoOpCode(CodeKind.GOTO, label1);
        InterCode label3Code = new InterCode.MonoOpCode(CodeKind.LABEL, label3);
        return InterCode.join(
                InterCode.join(
                        InterCode.join(
                                InterCode.join(
                                        InterCode.join(label1Code, expCode),
                                        label2Code),
                                stmtCode),
                        gotoLabel1Code),
                label3Code);
    }

    @Override
    public InterCode visitDefList(CmmParser.DefListContext ctx) {
        InterCode interCodeHead = null;
        List<CmmParser.DefContext> defList = ctx.def();
        for (CmmParser.DefContext defContext : defList) {
            InterCode nextInterCode = visit(defContext);
            if (interCodeHead == null) {
                interCodeHead = nextInterCode;
                continue;
            }
            interCodeHead.addInterCode(nextInterCode);
        }
        return interCodeHead;
    }

    @Override
    public InterCode visitDef(CmmParser.DefContext ctx) {
        return visit(ctx.decList());
    }

    @Override
    public InterCode visitDecList(CmmParser.DecListContext ctx) {
        InterCode interCodeHead = null;
        List<CmmParser.DecContext> decList = ctx.dec();
        for (CmmParser.DecContext decContext : decList) {
            InterCode nextInterCode = visit(decContext);
            if (interCodeHead == null) {
                interCodeHead = nextInterCode;
                continue;
            }
            interCodeHead.addInterCode(nextInterCode);
        }
        return interCodeHead;
    }

    @Override
    public InterCode visitDec(CmmParser.DecContext ctx) {
        if (ctx.getChildCount() == 1) {
            return visit(ctx.varDec());
        } else {
            Operand temp1 = makeNewTemp();
            operandStack.push(temp1);
            InterCode varDecInterCode = visit(ctx.varDec());
            operandStack.pop();

            Operand temp2 = makeNewTemp();
            operandStack.push(temp2);
            InterCode expInterCode = visit(ctx.exp());
            operandStack.pop();
            if (temp2.operandKind == OperandKind.ADDRESS) {
                Operand tempValue = makeNewTemp();
                InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR, tempValue,
                        temp2);
                temp2 = tempValue;
                expInterCode = InterCode.join(expInterCode, readAddrInterCode);
            }

            InterCode assignInterCode = new InterCode.AssignCode(CodeKind.ASSIGN, temp1, temp2);
            return InterCode.join(InterCode.join(varDecInterCode, expInterCode), assignInterCode);
        }
    }

    @Override
    public InterCode visitExpDot(CmmParser.ExpDotContext ctx) {
        Operand target = operandStack.peek();
        if (target == null) {
            return defaultResult();
        }
        Type structType = getExpType(ctx.exp());
        if (structType.getSymbolKind() != SymbolKind.STRUCTURE) {
            return defaultResult();
        }

        // compute base addr
        Operand baseAddr = makeNewTemp(OperandKind.ADDRESS);
        operandStack.push(baseAddr);
        InterCode getBaseAddrInterCode = visit(ctx.exp());
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
        if (target.operandKind != OperandKind.ADDRESS) {
            target.operandKind = OperandKind.ADDRESS;
        }
        // compute addr
        if (offset == 0) {
            // reduce use of constant
            target.value = baseAddr.value;
            return getBaseAddrInterCode;
        }
        InterCode computeAddrInterCode = new InterCode.BinOpCode(CodeKind.ADD, baseAddr,
                makeNewConstant(String.valueOf(offset)), target);
        return InterCode.join(getBaseAddrInterCode, computeAddrInterCode);
    }

    @Override
    public InterCode visitExpOr(CmmParser.ExpOrContext ctx) {
        Operand target = operandStack.peek();
        if (target == null) {
            return defaultResult();
        }
        return generateLogicResultInterCode(target, ctx);
    }

    @Override
    public InterCode visitExpRelop(CmmParser.ExpRelopContext ctx) {
        Operand target = operandStack.peek();
        if (target == null) {
            return defaultResult();
        }
        return generateLogicResultInterCode(target, ctx);
    }

    @Override
    public InterCode visitExpPlusMinus(CmmParser.ExpPlusMinusContext ctx) {
        Operand target = operandStack.peek();
        if (target == null) {
            return defaultResult();
        }
        Operand temp1 = makeNewTemp();
        operandStack.push(temp1);
        InterCode expCode1 = visit(ctx.exp(0));
        operandStack.pop();

        if (temp1.operandKind == OperandKind.ADDRESS) {
            Operand temp = makeNewTemp();
            InterCode readAddrCode = new InterCode.AssignCode(CodeKind.READ_ADDR, temp, temp1);
            expCode1 = InterCode.join(expCode1, readAddrCode);
            temp1 = temp;
        }

        Operand temp2 = makeNewTemp();
        operandStack.push(temp2);
        InterCode expCode2 = visit(ctx.exp(1));
        operandStack.pop();

        if (temp2.operandKind == OperandKind.ADDRESS) {
            Operand temp = makeNewTemp();
            InterCode readAddrCode = new InterCode.AssignCode(CodeKind.READ_ADDR, temp, temp2);
            expCode2 = InterCode.join(expCode2, readAddrCode);
            temp2 = temp;
        }

        if (ctx.PLUS() != null) {
            InterCode plusInterCode = new InterCode.BinOpCode(CodeKind.ADD, temp1, temp2, target);
            return InterCode.join(InterCode.join(expCode1, expCode2), plusInterCode);
        } else {
            InterCode minusInterCode = new InterCode.BinOpCode(CodeKind.SUB, temp1, temp2, target);
            return InterCode.join(InterCode.join(expCode1, expCode2), minusInterCode);
        }

    }

    @Override
    public InterCode visitExpAssignop(CmmParser.ExpAssignopContext ctx) {
        Operand target = operandStack.peek();
        Operand temp1 = makeNewTemp();
        operandStack.push(temp1);
        InterCode expCode1 = visit(ctx.exp(0));
        operandStack.pop();
        Operand temp2 = makeNewTemp();
        operandStack.push(temp2);
        InterCode expCode2 = visit(ctx.exp(1));
        operandStack.pop();
        // temp1 = temp2
        InterCode assignCode = new InterCode.AssignCode(CodeKind.ASSIGN, temp1, temp2);
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
            InterCode temp2Totemp3AssignCode = new InterCode.AssignCode(CodeKind.READ_ADDR, temp3,
                    temp2);
            InterCode temp3totemp1AssignCode = new InterCode.AssignCode(CodeKind.WRITE_ADDR, temp1,
                    temp3);
            // dump original assignCode
            assignCode = temp2Totemp3AssignCode;
            assignCode.addInterCode(temp3totemp1AssignCode);
        }
        // write value of temp1 to target
        if (target != null) {
            // target = temp1
            InterCode assignToTargetCode = new InterCode.AssignCode(CodeKind.ASSIGN, target, temp1);
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
                // dump original assignCode
                assignToTargetCode = temp1ToTemp;
                assignToTargetCode.addInterCode(tempToTarget);
            }
            assignCode.addInterCode(assignToTargetCode);
        }
        return InterCode.join(InterCode.join(expCode1, expCode2), assignCode);
    }

    @Override
    public InterCode visitExpParenthesis(CmmParser.ExpParenthesisContext ctx) {
        return visit(ctx.exp());
    }

    @Override
    public InterCode visitExpFloat(CmmParser.ExpFloatContext ctx) {
        Operand target = operandStack.peek();
        if (target != null) {
            return new InterCode.AssignCode(CodeKind.ASSIGN, target,
                    makeNewConstant(ctx.FLOAT().getText()));
        }
        return defaultResult();
    }

    @Override
    public InterCode visitExpAnd(CmmParser.ExpAndContext ctx) {
        Operand target = operandStack.peek();
        if (target == null) {
            return defaultResult();
        }
        return generateLogicResultInterCode(target, ctx);
    }

    @Override
    public InterCode visitExpFuncArgs(CmmParser.ExpFuncArgsContext ctx) {
        if ("write".equals(ctx.ID().getText())) {
            Operand temp = makeNewTemp();
            operandStack.push(temp);
            InterCode expInterCode = visit(ctx.args().exp(0));
            operandStack.pop();
            if (temp.operandKind == OperandKind.ADDRESS) {
                Operand temp2 = makeNewTemp();
                InterCode readAddrCode = new InterCode.AssignCode(CodeKind.READ_ADDR, temp2, temp);
                expInterCode = InterCode.join(expInterCode, readAddrCode);
                temp = temp2;
            }
            InterCode writeInterCode = new InterCode.MonoOpCode(CodeKind.WRITE, temp);
            return InterCode.join(expInterCode, writeInterCode);
        }
        Operand target = operandStack.peek();
        if ("read".equals(ctx.ID().getText())) {
            if (target == null) {
                return defaultResult();
            }
            return new InterCode.MonoOpCode(CodeKind.READ, target);
        }
        if (ctx.args() != null) {
            if (target == null) {
                target = makeNewTemp();
            }
            InterCode argsCode = visit(ctx.args());
            InterCode callFunc = new InterCode.AssignCode(CodeKind.CALL, target,
                    new Operand(OperandKind.FUNCTION, ctx.ID().getText()));
            return InterCode.join(argsCode, callFunc);
        } else {
            if (target == null) {
                target = makeNewTemp();
            }
            return new InterCode.AssignCode(CodeKind.CALL, target,
                    new Operand(OperandKind.FUNCTION, ctx.ID().getText()));
        }
    }

    @Override
    public InterCode visitExpStarDiv(CmmParser.ExpStarDivContext ctx) {
        Operand target = operandStack.peek();
        if (target == null) {
            return defaultResult();
        }
        Operand temp1 = makeNewTemp();
        operandStack.push(temp1);
        InterCode expCode1 = visit(ctx.exp(0));
        operandStack.pop();
        if (temp1.operandKind == OperandKind.ADDRESS) {
            Operand tempValue = makeNewTemp();
            InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR, tempValue,
                    temp1);
            temp1 = tempValue;
            expCode1 = InterCode.join(expCode1, readAddrInterCode);
        }

        Operand temp2 = makeNewTemp();
        operandStack.push(temp2);
        InterCode expCode2 = visit(ctx.exp(1));
        operandStack.pop();

        if (temp2.operandKind == OperandKind.ADDRESS) {
            Operand tempValue = makeNewTemp();
            InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR, tempValue,
                    temp2);
            temp2 = tempValue;
            expCode2 = InterCode.join(expCode2, readAddrInterCode);
        }

        if (ctx.STAR() != null) {
            InterCode starInterCode = new InterCode.BinOpCode(CodeKind.MUL, temp1, temp2, target);
            return InterCode.join(InterCode.join(expCode1, expCode2), starInterCode);
        } else {
            InterCode divInterCode = new InterCode.BinOpCode(CodeKind.DIV, temp1, temp2, target);
            return InterCode.join(InterCode.join(expCode1, expCode2), divInterCode);
        }
    }


    @Override
    public InterCode visitExpInt(CmmParser.ExpIntContext ctx) {
        Operand target = operandStack.peek();
        if (target != null) {
            // reduce use of temp var
            target.operandKind = OperandKind.CONSTANT;
            target.value = ctx.INT().getText();
        }
        return defaultResult();
    }

    @Override
    public InterCode visitExpMinusNot(CmmParser.ExpMinusNotContext ctx) {
        Operand target = operandStack.peek();
        if (ctx.MINUS() != null) {
            if (target == null) {
                return defaultResult();
            }
            Operand temp = makeNewTemp();
            operandStack.push(temp);
            // temp = xxx
            InterCode expInterCode = visit(ctx.exp());
            operandStack.pop();

            // if temp.kind == address, read this addres
            if (temp.operandKind == OperandKind.ADDRESS) {
                Operand tempValue = makeNewTemp();
                InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR,
                        tempValue,
                        temp);
                temp = tempValue;
                expInterCode = InterCode.join(expInterCode, readAddrInterCode);
            }

            // target = 0 - temp
            InterCode minusInterCode = new InterCode.BinOpCode(CodeKind.SUB,
                    makeNewConstant("0"), temp, target);
            return InterCode.join(expInterCode, minusInterCode);
        } else {
            if (target == null) {
                return defaultResult();
            }
            return generateLogicResultInterCode(target, ctx);
        }
    }

    @Override
    public InterCode visitExpId(CmmParser.ExpIdContext ctx) {
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
                return new InterCode.AssignCode(CodeKind.GET_ADDR, target,
                        makeNewVariable(idText));
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
                    return new InterCode.AssignCode(CodeKind.GET_ADDR, target,
                            makeNewVariable(idText));
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
    public InterCode visitExpBrackets(CmmParser.ExpBracketsContext ctx) {
        Operand target = operandStack.peek();
        if (target == null) {
            return defaultResult();
        }
        // compute base address
        Operand baseAddr = makeNewTemp(OperandKind.ADDRESS);
        operandStack.push(baseAddr);
        InterCode getBaseAddrInterCode = visit(ctx.exp(0));
        operandStack.pop();

        // get index
        Operand arrayIndex = makeNewTemp();
        operandStack.push(arrayIndex);
        InterCode getIndexInterCode = visit(ctx.exp(1));
        operandStack.pop();

        // compute offset
        Operand offset = makeNewTemp();
        InterCode computeOffsetInterCode = null;
        if (arrayIndex.operandKind == OperandKind.CONSTANT) {
            offset.operandKind = OperandKind.CONSTANT;
            // ctx.exp(0) is array type for sure
            offset.value = String.valueOf(
                    Integer.parseInt(arrayIndex.value) * getTypeSize(
                            ((Array) getExpType(ctx.exp(0))).elements));
        }
        // 如果不是用常数来表示的index，那index * elementSize
        else {
            computeOffsetInterCode = new InterCode.BinOpCode(CodeKind.MUL,
                    makeNewConstant(String.valueOf(getTypeSize(
                            ((Array) getExpType(ctx.exp(0))).elements))),
                    arrayIndex, offset);
        }

        if (target.operandKind != OperandKind.ADDRESS) {
            target.operandKind = OperandKind.ADDRESS;
        }
        // compute actual addr
        InterCode computeAddrInterCode = new InterCode.BinOpCode(CodeKind.ADD, baseAddr, offset,
                target);
        return InterCode.join(
                InterCode.join(
                        InterCode.join(
                                getBaseAddrInterCode, getIndexInterCode),
                        computeOffsetInterCode),
                computeAddrInterCode);
    }


    @Override
    public InterCode visitArgs(CmmParser.ArgsContext ctx) {
        InterCode expCodeHead = null;
        InterCode argCodeHead = null;
        List<CmmParser.ExpContext> expList = ctx.exp();
        for (int i = expList.size() - 1; i >= 0; i--) {
            Operand temp = makeNewTemp();
            operandStack.push(temp);
            InterCode expCode = visit(expList.get(i));
            operandStack.pop();
            if (expCodeHead == null) {
                expCodeHead = expCode;
            } else {
                expCodeHead.addInterCode(expCode);
            }
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
                    InterCode.join(expCodeHead, readAddrCode);
                    argCode = new InterCode.MonoOpCode(CodeKind.ARG, temp2);
                }
            }
            // a is normal variable
            else {
                argCode = new InterCode.MonoOpCode(CodeKind.ARG, temp);
            }
            if (argCodeHead == null) {
                argCodeHead = argCode;
            } else {
                argCodeHead.addInterCode(argCode);
            }
        }
        return InterCode.join(expCodeHead, argCodeHead);
    }

    private InterCode generateLogicResultInterCode(Operand target, CmmParser.ExpContext ctx) {
        // target = 0
        InterCode setToDefaultInterCode = new InterCode.AssignCode(CodeKind.ASSIGN, target,
                makeNewConstant("0"));
        Operand label1 = makeNewLabel();
        Operand label2 = makeNewLabel();
        // if xxx goto label1
        InterCode conditionJudgeCode = generateConditionInterCode(ctx, label1, label2);
        // Label label1
        InterCode label1Code = new InterCode.MonoOpCode(CodeKind.LABEL, label1);
        // target = 1
        InterCode setToTrueInterCode = new InterCode.AssignCode(CodeKind.ASSIGN, target,
                makeNewConstant("1"));
        // Label label2
        InterCode label2Code = new InterCode.MonoOpCode(CodeKind.LABEL, label2);
        return InterCode.join(
                InterCode.join(
                        InterCode.join(
                                InterCode.join(
                                        setToDefaultInterCode, conditionJudgeCode),
                                label1Code),
                        setToTrueInterCode),
                label2Code);
    }

    private InterCode generateIfGoToInterCode(Operand op1, Operand op2, String relop,
            Operand labelTrue, Operand labelFalse) {
        InterCode ifGoToInterCode = new InterCode.ConditionJumpCode(CodeKind.IF_GOTO, op1, relop,
                op2, labelTrue);
        InterCode gotoInterCode = new InterCode.MonoOpCode(CodeKind.GOTO, labelFalse);
        return InterCode.join(ifGoToInterCode, gotoInterCode);
    }

    private InterCode generateConditionInterCode(CmmParser.ExpContext ctx, Operand labelTrue,
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
                Operand temp = makeNewTemp();
                operandStack.push(temp);
                // temp = xxx
                InterCode expInterCode = visit(((CmmParser.ExpMinusNotContext) ctx).exp());
                operandStack.pop();

                if (temp.operandKind == OperandKind.ADDRESS) {
                    Operand tempValue = makeNewTemp();
                    InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR,
                            tempValue,
                            temp);
                    temp = tempValue;
                    expInterCode = InterCode.join(expInterCode, readAddrInterCode);
                }

                Operand tempResult = makeNewTemp();
                // tempResult = 0 - temp
                InterCode minusInterCode = new InterCode.BinOpCode(CodeKind.SUB,
                        tempResult, makeNewConstant("0"), temp);
                // if tempResult != 0 goto labelTrue
                // goto labelFalse
                InterCode ifGoToInterCode = generateIfGoToInterCode(tempResult,
                        makeNewConstant("0"),
                        "!=", labelTrue, labelFalse);
                return InterCode
                        .join(InterCode.join(expInterCode, minusInterCode), ifGoToInterCode);

            }
        }
        // exp AND exp
        else if (ctx instanceof CmmParser.ExpAndContext) {
            Operand label1 = makeNewLabel();
            InterCode code1 = generateConditionInterCode(((CmmParser.ExpAndContext) ctx).exp(0),
                    label1,
                    labelFalse);
            InterCode label1Code = new InterCode.MonoOpCode(CodeKind.LABEL, label1);
            code1.addInterCode(label1Code);
            InterCode code2 = generateConditionInterCode(((CmmParser.ExpAndContext) ctx).exp(1),
                    labelTrue,
                    labelFalse);
            return InterCode.join(code1, code2);
        }
        // exp OR exp
        else if (ctx instanceof CmmParser.ExpOrContext) {
            Operand label1 = makeNewLabel();
            InterCode code1 = generateConditionInterCode(((CmmParser.ExpOrContext) ctx).exp(0),
                    labelTrue,
                    label1);
            InterCode label1Code = new InterCode.MonoOpCode(CodeKind.LABEL, label1);
            code1.addInterCode(label1Code);
            InterCode code2 = generateConditionInterCode(((CmmParser.ExpOrContext) ctx).exp(1),
                    labelTrue,
                    labelFalse);
            return InterCode.join(code1, code2);
        }
        // exp RELOP exp
        else if (ctx instanceof CmmParser.ExpRelopContext) {
            Operand temp1 = makeNewTemp();
            operandStack.push(temp1);
            InterCode expCode1 = visit(((CmmParser.ExpRelopContext) ctx).exp(0));
            operandStack.pop();
            if (temp1.operandKind == OperandKind.ADDRESS) {
                Operand temp1Value = makeNewTemp();
                InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR,
                        temp1Value,
                        temp1);
                temp1 = temp1Value;
                expCode1 = InterCode.join(expCode1, readAddrInterCode);
            }

            Operand temp2 = makeNewTemp();
            operandStack.push(temp2);
            InterCode expCode2 = visit(((CmmParser.ExpRelopContext) ctx).exp(1));
            operandStack.pop();
            if (temp2.operandKind == OperandKind.ADDRESS) {
                Operand temp2Value = makeNewTemp();
                InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR,
                        temp2Value,
                        temp2);
                temp2 = temp2Value;
                expCode2 = InterCode.join(expCode2, readAddrInterCode);
            }
            InterCode ifGoToInterCode = generateIfGoToInterCode(temp1, temp2,
                    ((CmmParser.ExpRelopContext) ctx).RELOP().getText(), labelTrue, labelFalse);

            return InterCode.join(InterCode.join(expCode1, expCode2), ifGoToInterCode);
        } else {
            Operand temp = makeNewTemp();
            operandStack.push(temp);
            InterCode expInterCode = visit(ctx);
            operandStack.pop();

            if (temp.operandKind == OperandKind.ADDRESS) {
                Operand tempValue = makeNewTemp();
                InterCode readAddrInterCode = new InterCode.AssignCode(CodeKind.READ_ADDR,
                        tempValue,
                        temp);
                temp = tempValue;
                expInterCode = InterCode.join(expInterCode, readAddrInterCode);
            }
            InterCode ifGoToInterCode = generateIfGoToInterCode(temp, makeNewConstant("0"), "!=",
                    labelTrue, labelFalse);
            return InterCode.join(expInterCode, ifGoToInterCode);
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
