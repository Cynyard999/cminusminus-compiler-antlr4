package L3;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class FieldList implements Returnable {

    private String name;
    private Type type;
    private FieldList next;
    private FieldList tail;

    public FieldList() {
        // CRUCIAL!!
        this.tail = this;
    }

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

    public FieldList getTail() {
        return tail;
    }

    public void setTail(FieldList tail) {
        this.tail = tail;
    }

    public void addField(FieldList newField) {
        if (newField == null) {
            return;
        }
        this.tail.next = newField;
        this.tail = newField.tail;
    }
}
