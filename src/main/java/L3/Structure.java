package L3;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class Structure extends Type {

    private FieldList memberList;

    public Structure() {
        this.setKind(Kind.STRUCTURE);
    }

    public FieldList getMemberList() {
        return memberList;
    }

    public void setMemberList(FieldList memberList) {
        this.memberList = memberList;
    }
}
