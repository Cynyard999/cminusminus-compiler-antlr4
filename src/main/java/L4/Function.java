package L4;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class Function extends Type {

    private Type returnType;
    private Field paramListHead;

    public Function() {
        setSymbolKind(SymbolKind.FUNCTION);
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public Field getParamListHead() {
        return paramListHead;
    }

    public void setParamListHead(Field paramListHead) {
        this.paramListHead = paramListHead;
    }
}
