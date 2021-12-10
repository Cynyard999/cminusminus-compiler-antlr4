package L3;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class Function extends Type {

    private Type returnType;
    private FieldList paramList;

    public Function() {
        setKind(Kind.FUNCTION);
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public FieldList getParamList() {
        return paramList;
    }

    public void setParamList(FieldList paramList) {
        this.paramList = paramList;
    }
}
