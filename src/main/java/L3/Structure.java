package L3;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class Structure extends Type {
    private String name;
    private FieldList memberList;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldList getMemberList() {
        return memberList;
    }

    public void setMemberList(FieldList memberList) {
        this.memberList = memberList;
    }
}
