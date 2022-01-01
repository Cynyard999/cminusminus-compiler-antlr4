package L4;

/**
 * @author cynyard
 * @description
 * @date 1/1/22
 */
public enum CodeKind {
    // LABEL x :
    LABEL,
    // FUNCTION f :
    FUNCTION,
    // x := y
    ASSIGN,
    // x := y + z
    ADD,
    // x := y - z
    SUB,
    // x := y * z
    MUL,
    // x := y / z
    DIV,
    // x := &y
    GET_ADDR,
    // x := *y
    READ_ADDR,
    // *x = y
    WRITE_ADDR,
    // GOTO x 10
    GOTO,
    // IF x [relop] y GOTO z
    IF_GOTO,
    // RETURN x
    RETURN_,
    // DEC x [size]
    DEC,
    // ARG x
    ARG,
    // x := CALL f
    CALL,
    // PARAM x
    PARAM,
    // READ x
    READ,
    // WRITE
    WRITE

}
