package L4;

/**
 * @author cynyard
 * @description
 * @date 1/1/22
 */
public class Operand {

    OperandKind operandKind;
    String value;

    @Override
    public String toString() {
        if (operandKind == OperandKind.CONSTANT) {
            return "#" + value;
        }
        return value;
    }
}
