package L3;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class Function extends Type {

    private String name;
    private Type returnType;
    private FieldList paramList;

    public Function() {
        setKind(Kind.FUNCTION);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
