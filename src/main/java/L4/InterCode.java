package L4;

/**
 * @author cynyard
 * @description 仅仅使用链表节点来表示整个链表数据结构，添加了tail指针方便添加 但使用有很多限制 例如一个链表中的一些节点的tail可能都不同，但保证头节点的tail一定是当前链表的tail
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

        public BinOpCode(CodeKind codeKind, Operand operand1, Operand operand2, Operand result) {
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
        if (code1 == null) {
            return code2;
        }
        code1.addInterCode(code2);
        return code1;
    }

    public void addInterCode(InterCode newCode) {
        if (newCode == null) {
            return;
        }
        this.tail.next = newCode;
        this.tail = newCode.tail;
    }
}
