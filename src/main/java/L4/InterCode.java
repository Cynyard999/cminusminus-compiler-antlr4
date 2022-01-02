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

    public InterCode(CodeKind codeKind) {
        this.codeKind = codeKind;
        this.tail = this;
    }


    public static class MonoOpCode extends InterCode {

        Operand operand;

        public MonoOpCode(CodeKind codeKind) {
            super(codeKind);
        }
    }

    public static class BinOpCode extends InterCode {

        Operand operand1;
        Operand operand2;
        Operand result;

        public BinOpCode(CodeKind codeKind) {
            super(codeKind);
        }
    }

    public static class AssignCode extends InterCode {

        Operand leftOperand;
        Operand rightOperand;

        public AssignCode(CodeKind codeKind) {
            super(codeKind);
        }
    }

    public static class ConditionJumpCode extends InterCode {

        Operand x;
        Operand y;
        Operand z;
        String relop;

        public ConditionJumpCode(CodeKind codeKind) {
            super(codeKind);
        }
    }

    public static class MemDecCode extends InterCode {

        Operand operand;
        int size;

        public MemDecCode(CodeKind codeKind) {
            super(codeKind);
        }
    }

//    public static class ArgCode extends InterCode {
//
//        Operand operand;
//        ArgCode nextArg;
//
//        public ArgCode(CodeKind codeKind) {
//            super(codeKind);
//        }
//    }

    public static InterCode join(InterCode code1, InterCode code2) {
        code1.addInterCode(code2);
        return code1;
    }

    private void addInterCode(InterCode newCode) {
        if (newCode == null) {
            return;
        }
        this.tail.next = newCode;
        this.tail = newCode.tail;
    }
}
