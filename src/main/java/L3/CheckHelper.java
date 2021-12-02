package L3;

import L3.CmmParser.ExpParenthesisContext;

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
        if (first.getKind() != second.getKind()) {
            return false;
        }
        switch (first.getKind()) {
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
        FieldList firstMember = first.getMemberList();
        FieldList secondMember = second.getMemberList();
        while (firstMember != null && secondMember != null) {
            if (!isTypeEqual(firstMember.getType(), secondMember.getType())){
                return false;
            }
            firstMember = firstMember.getNext();
            secondMember = secondMember.getNext();
        }
        return firstMember == null && secondMember == null;
    }

    public static boolean isFieldListEqual(FieldList first, FieldList second) {
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
        if (ctx instanceof CmmParser.ExpParenthesisContext) {
            return isLeftExp(((ExpParenthesisContext) ctx).exp());
        }
        return false;
    }

}
