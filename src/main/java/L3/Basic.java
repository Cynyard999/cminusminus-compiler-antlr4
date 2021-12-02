package L3;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class Basic extends Type{

    public Basic(String type) {
        switch (type) {
            case "int": setKind(Kind.INT); break;
            case "float": setKind(Kind.FLOAT); break;
            default: break;
        }
    }
}
