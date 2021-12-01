package L3;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class FieldList {

    private String name;
    private Type type;
    private FieldList next;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public FieldList getNext() {
        return next;
    }

    public void setNext(FieldList next) {
        this.next = next;
    }
}
