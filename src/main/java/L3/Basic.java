package L3;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class Basic extends Type{

    public Basic(String text) {
        switch (text) {
            case "int": setKind(Kind.INT); break;
            case "float": setKind(Kind.FLOAT); break;
            default: break;
        }
    }
}
