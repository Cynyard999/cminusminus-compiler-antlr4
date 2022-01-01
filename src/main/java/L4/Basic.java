package L4;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public class Basic extends Type {

    public Basic(String type) {
        switch (type) {
            case "int": setSymbolKind(SymbolKind.INT); break;
            case "float": setSymbolKind(SymbolKind.FLOAT); break;
            default: break;
        }
    }
}
