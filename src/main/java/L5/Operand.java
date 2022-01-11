package L5;

/**
 * @author cynyard
 * @description
 * @date 1/1/22
 */
public class Operand {

    OperandKind operandKind;
    String value;

    public Operand(OperandKind operandKind) {
        this.operandKind = operandKind;
    }

    public Operand(OperandKind operandKind, String value) {
        this.operandKind = operandKind;
        this.value = value;
    }

    @Override
    public String toString() {
        if (operandKind == OperandKind.CONSTANT) {
            return "#" + value;
        }
        return value;
    }
}
