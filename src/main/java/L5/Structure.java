package L5;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class Structure extends Type {

    private String name;
    private Field memberListHead;

    public Structure() {
        this.setSymbolKind(SymbolKind.STRUCTURE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Field getMemberListHead() {
        return memberListHead;
    }

    public void setMemberListHead(Field memberListHead) {
        this.memberListHead = memberListHead;
    }
}
