package L5;

/**
 * @author cynyard
 * @description
 * @date 1/2/22
 */
public abstract class InterCode {

    CodeKind codeKind;
    InterCode next;

    public InterCode(CodeKind codeKind) {
        this.codeKind = codeKind;
    }


    public static class MonoOpCode extends InterCode {

        Operand operand;

        public MonoOpCode(CodeKind codeKind) {
            super(codeKind);
        }

        public MonoOpCode(CodeKind codeKind, Operand operand) {
            super(codeKind);
            this.operand = operand;
        }
    }

    public static class BinOpCode extends InterCode {

        Operand operand1;
        Operand operand2;
        Operand result;

        public BinOpCode(CodeKind codeKind) {
            super(codeKind);
        }

        public BinOpCode(
                CodeKind codeKind, Operand operand1, Operand operand2, Operand result) {
            super(codeKind);
            this.operand1 = operand1;
            this.operand2 = operand2;
            this.result = result;
        }
    }

    public static class AssignCode extends InterCode {

        Operand leftOperand;
        Operand rightOperand;

        public AssignCode(CodeKind codeKind) {
            super(codeKind);
        }

        public AssignCode(CodeKind codeKind, Operand leftOperand, Operand rightOperand) {
            super(codeKind);
            this.leftOperand = leftOperand;
            this.rightOperand = rightOperand;
        }
    }

    public static class ConditionJumpCode extends InterCode {

        Operand op1;
        String relop;
        Operand op2;
        Operand label;

        public ConditionJumpCode(CodeKind codeKind) {
            super(codeKind);
        }

        public ConditionJumpCode(CodeKind codeKind, Operand op1, String relop, Operand op2,
                Operand label) {
            super(codeKind);
            this.op1 = op1;
            this.relop = relop;
            this.op2 = op2;
            this.label = label;
        }
    }

    public static class MemDecCode extends InterCode {

        Operand operand;
        int size;

        public MemDecCode(CodeKind codeKind) {
            super(codeKind);
        }

        public MemDecCode(CodeKind codeKind, Operand operand, int size) {
            super(codeKind);
            this.operand = operand;
            this.size = size;
        }
    }

    @Override
    public String toString() {
        return this.codeKind.getInterCodeStringifyMethod().apply(this);
    }
}
