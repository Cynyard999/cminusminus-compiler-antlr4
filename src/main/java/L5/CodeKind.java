package L5;

import java.util.function.Function;

/**
 * @author cynyard
 * @description 枚举了InterCode的类型，将不同类型InterCode打印方法和类型绑定在一起，后期可以考虑一个更好的方法去耦合
 * @date 1/1/22
 */
public enum CodeKind {
    // LABEL x :
    LABEL((InterCode interCode) ->
            "LABEL " + ((InterCode.MonoOpCode) interCode).operand.toString() + " :"),
    // FUNCTION f :
    FUNCTION((InterCode interCode) ->
            "FUNCTION " + ((InterCode.MonoOpCode) interCode).operand.toString() + " :"),
    // x := y
    ASSIGN((InterCode interCode) ->
            ((InterCode.AssignCode) interCode).leftOperand.toString() + " := "
                    + ((InterCode.AssignCode) interCode).rightOperand.toString()),

    // x := y + z
    ADD((InterCode interCode) ->
            ((InterCode.BinOpCode) interCode).result.toString() + " := "
                    + ((InterCode.BinOpCode) interCode).operand1.toString() + " + "
                    + ((InterCode.BinOpCode) interCode).operand2.toString()),

    // x := y - z
    SUB((InterCode interCode) ->
            ((InterCode.BinOpCode) interCode).result.toString() + " := "
                    + ((InterCode.BinOpCode) interCode).operand1.toString() + " - "
                    + ((InterCode.BinOpCode) interCode).operand2.toString()),

    // x := y * z
    MUL((InterCode interCode) ->
            ((InterCode.BinOpCode) interCode).result.toString() + " := "
                    + ((InterCode.BinOpCode) interCode).operand1.toString() + " * "
                    + ((InterCode.BinOpCode) interCode).operand2.toString()),

    // x := y / z
    DIV((InterCode interCode) ->
            ((InterCode.BinOpCode) interCode).result.toString() + " := "
                    + ((InterCode.BinOpCode) interCode).operand1.toString() + " / "
                    + ((InterCode.BinOpCode) interCode).operand2.toString()),

    // x := &y
    GET_ADDR((InterCode interCode) ->
            ((InterCode.AssignCode) interCode).leftOperand.toString() + " := &"
                    + ((InterCode.AssignCode) interCode).rightOperand.toString()),

    // x := *y
    READ_ADDR((InterCode interCode) ->
            ((InterCode.AssignCode) interCode).leftOperand.toString() + " := *"
                    + ((InterCode.AssignCode) interCode).rightOperand.toString()),

    // *x = y
    WRITE_ADDR((InterCode interCode) ->
            "*" + ((InterCode.AssignCode) interCode).leftOperand.toString() + " := "
                    + ((InterCode.AssignCode) interCode).rightOperand.toString()),

    // GOTO x
    GOTO((InterCode interCode) ->
            "GOTO " + ((InterCode.MonoOpCode) interCode).operand.toString()),

    // IF x [relop] y GOTO z
    IF_GOTO((InterCode interCode) ->
            "IF " + ((InterCode.ConditionJumpCode) interCode).op1.toString() + " "
                    + ((InterCode.ConditionJumpCode) interCode).relop + " "
                    + ((InterCode.ConditionJumpCode) interCode).op2.toString() + " GOTO "
                    + ((InterCode.ConditionJumpCode) interCode).label.toString()),

    // RETURN x
    RETURN_((InterCode interCode) ->
            "RETURN " + ((InterCode.MonoOpCode) interCode).operand.toString()),

    // DEC x [size]
    DEC((InterCode interCode) ->
            "DEC " + ((InterCode.MemDecCode) interCode).operand.toString()
                    + " " + ((InterCode.MemDecCode) interCode).size),

    // ARG x
    ARG((InterCode interCode) ->
            "ARG " + ((InterCode.MonoOpCode) interCode).operand.toString()),

    // x := CALL f
    CALL((InterCode interCode) ->
            ((InterCode.AssignCode) interCode).leftOperand.toString() + " := CALL "
                    + ((InterCode.AssignCode) interCode).rightOperand.toString()),

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
