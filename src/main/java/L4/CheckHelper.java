package L4;

/**
 * @author cynyard
 * @description help to check
 * @date 12/1/21
 */
public class CheckHelper {

    public static boolean isTypeEqual(Type first, Type second) {
        if (first == null && second == null) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        if (first.getSymbolKind() != second.getSymbolKind()) {
            return false;
        }
        switch (first.getSymbolKind()) {
            case INT:
            case FLOAT:
            case FUNCTION: {
                return true;
            }
            case ARRAY: {
                return isTypeEqual(((Array) first).getElements(), ((Array) second).getElements());
            }
            case STRUCTURE: {
                return isStructureEqual((Structure) first, (Structure) second);
            }
            default:
                return false;
        }
    }

    public static boolean isStructureEqual(Structure first, Structure second) {
        // 个数相同，并且类型的顺序也需要相同
        Field firstMember = first.getMemberListHead();
        Field secondMember = second.getMemberListHead();
        while (firstMember != null && secondMember != null) {
            if (!isTypeEqual(firstMember.getType(), secondMember.getType())){
                return false;
            }
            firstMember = firstMember.getNext();
            secondMember = secondMember.getNext();
        }
        return firstMember == null && secondMember == null;
    }

    public static boolean isFieldListEqual(Field first, Field second) {
        while (first != null && second != null && isTypeEqual(first.getType(), second.getType())) {
            first = first.getNext();
            second = second.getNext();
        }
        return first == null && second == null;
    }

    public static boolean isLeftExp(CmmParser.ExpContext ctx){
        if (ctx instanceof CmmParser.ExpIdContext) {
            return true;
        }
        if (ctx instanceof CmmParser.ExpDotContext) {
            return true;
        }
        if (ctx instanceof  CmmParser.ExpBracketsContext){
            return true;
        }
        if (ctx instanceof CmmParser.ExpAssignopContext){
            return isLeftExp(((CmmParser.ExpAssignopContext) ctx).exp(0));
        }
        if (ctx instanceof CmmParser.ExpParenthesisContext) {
            return isLeftExp(((CmmParser.ExpParenthesisContext) ctx).exp());
        }
        return false;
    }

}
