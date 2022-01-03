package L4;

/**
 * @author cynyard
 * @description 仅仅使用链表节点来表示整个链表数据结构，添加了tail指针方便添加
 * 但使用有很多限制
 * 例如一个链表中的每个节点的tail可能都不同，但保证头节点的tail一定是当前链表的tail
 * @date 12/1/21
 */
public class Field implements Returnable {

    private String name;
    private Type type;
    private Field next;
    private Field tail;

    public Field() {
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

    public Field getNext() {
        return next;
    }

    public void setNext(Field next) {
        this.next = next;
    }

    public Field getTail() {
        return tail;
    }

    public void setTail(Field tail) {
        this.tail = tail;
    }

    public void addField(Field newField) {
        if (newField == null) {
            return;
        }
        this.tail.next = newField;
        this.tail = newField.tail;
    }
}
