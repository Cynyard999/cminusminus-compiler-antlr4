package L4;

import L4.InterCode.AssignCode;
import L4.InterCode.BinOpCode;
import L4.InterCode.ConditionJumpCode;
import L4.InterCode.MemDecCode;
import java.util.function.Function;

/**
 * @author cynyard
 * @description
 * @date 1/1/22
 */
public enum CodeKind {
    // LABEL x :
    LABEL((InterCode interCode) ->
            "Label: " + ((InterCode.MonoOpCode) interCode).operand.toString() + " :"),
    // FUNCTION f :
    FUNCTION((InterCode interCode) ->
            "FUNCTION: " + ((InterCode.MonoOpCode) interCode).operand.toString() + " :"),
    // x := y
    ASSIGN((InterCode interCode) ->
            ((AssignCode) interCode).leftOperand.toString() + " := "
                    + ((AssignCode) interCode).rightOperand.toString()),

    // x := y + z
    ADD((InterCode interCode) ->
            ((BinOpCode) interCode).result.toString() + " := "
                    + ((BinOpCode) interCode).operand1.toString() + " + "
                    + ((BinOpCode) interCode).operand2.toString()),

    // x := y - z
    SUB((InterCode interCode) ->
            ((BinOpCode) interCode).result.toString() + " := "
                    + ((BinOpCode) interCode).operand1.toString() + " - "
                    + ((BinOpCode) interCode).operand2.toString()),

    // x := y * z
    MUL((InterCode interCode) ->
            ((BinOpCode) interCode).result.toString() + " := "
                    + ((BinOpCode) interCode).operand1.toString() + " * "
                    + ((BinOpCode) interCode).operand2.toString()),

    // x := y / z
    DIV((InterCode interCode) ->
            ((BinOpCode) interCode).result.toString() + " := "
                    + ((BinOpCode) interCode).operand1.toString() + " / "
                    + ((BinOpCode) interCode).operand2.toString()),

    // x := &y
    GET_ADDR((InterCode interCode) ->
            ((AssignCode) interCode).leftOperand.toString() + " := & "
                    + ((AssignCode) interCode).rightOperand.toString()),

    // x := *y
    READ_ADDR((InterCode interCode) ->
            ((AssignCode) interCode).leftOperand.toString() + " := * "
                    + ((AssignCode) interCode).rightOperand.toString()),

    // *x = y
    WRITE_ADDR((InterCode interCode) ->
            "*" + ((AssignCode) interCode).leftOperand.toString() + " := "
                    + ((AssignCode) interCode).rightOperand.toString()),

    // GOTO x
    GOTO((InterCode interCode) ->
            "GOTO: " + ((InterCode.MonoOpCode) interCode).operand.toString()),

    // IF x [relop] y GOTO z
    IF_GOTO((InterCode interCode) ->
            "IF " + ((ConditionJumpCode) interCode).x.toString() + " "
                    + ((ConditionJumpCode) interCode).relop + " "
                    + ((ConditionJumpCode) interCode).y.toString() + " GOTO "
                    + ((ConditionJumpCode) interCode).z.toString()),

    // RETURN x
    RETURN_((InterCode interCode) ->
            "RETURN " + ((InterCode.MonoOpCode) interCode).operand.toString()),

    // DEC x [size]
    DEC((InterCode interCode) ->
            "DEC " + ((InterCode.MemDecCode) interCode).operand.toString()
                    + " " + ((MemDecCode) interCode).size),

    // ARG x
    ARG((InterCode interCode) ->
            "ARG " + ((InterCode.MonoOpCode) interCode).operand.toString()),

    // x := CALL f
    CALL((InterCode interCode) ->
            ((AssignCode) interCode).leftOperand.toString() + " := CALL "
                    + ((AssignCode) interCode).rightOperand.toString()),

    // PARAM x
    PARAM((InterCode interCode) ->
            "PARAM " + ((InterCode.MonoOpCode) interCode).operand.toString()),

    // READ x
    READ((InterCode interCode) ->
            "READ " + ((InterCode.MonoOpCode) interCode).operand.toString()),

    // WRITE
    WRITE((InterCode interCode) ->
            "WRITE " + ((InterCode.MonoOpCode) interCode).operand.toString());

    private final Function<InterCode, String> interCodeStringifyMethod;

    public Function<InterCode, String> getInterCodeStringifyMethod() {
        return interCodeStringifyMethod;
    }

    CodeKind(Function<InterCode, String> printFunc) {
        this.interCodeStringifyMethod = printFunc;
    }
}
