package L3;

/**
 * @author cynyard
 * @description
 * @date 12/1/21
 */
public abstract class Type implements Returnable {

    private Kind kind;

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }
}
