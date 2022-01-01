package L4;

/**
 * @author cynyard
 * @description
 * @date 1/1/22
 */
public abstract class InterCode {

    CodeKind codeKind;
    InterCode next;
    InterCode tail;

    public static class MonoOpCode extends InterCode {

        Operand operand;
    }

    public static class BinOpCode extends InterCode {

        Operand operand1;
        Operand operand2;
        Operand result;
    }

    public static class AssignCode extends InterCode {

        Operand leftOperand;
        Operand rightOperand;

    }

    public static class ConditionJumpCode extends InterCode {

        Operand x;
        Operand y;
        Operand z;
        String relop;
    }

    public static class MemDecCode extends InterCode {

        Operand operand;
        int size;
    }

    public static class ArgCode extends InterCode {

        Operand operand;
        ArgCode nextArg;
    }

}
