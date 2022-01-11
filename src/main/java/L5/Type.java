package L5;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public abstract class Type implements Returnable {

    private SymbolKind symbolKind;

    public SymbolKind getSymbolKind() {
        return symbolKind;
    }

    public void setSymbolKind(SymbolKind symbolKind) {
        this.symbolKind = symbolKind;
    }
}
