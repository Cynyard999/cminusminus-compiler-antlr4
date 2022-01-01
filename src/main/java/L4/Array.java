package L4;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class Array extends Type {
    Type elements;
    int size;

    public Array() {
        setSymbolKind(SymbolKind.ARRAY);
    }

    public Type getElements() {
        return elements;
    }

    public void setElements(Type elements) {
        this.elements = elements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
