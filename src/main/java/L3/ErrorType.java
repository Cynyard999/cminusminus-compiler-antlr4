package L3;

/**
 * @author cynyard
 * @description errorType
 * @date 12/1/21
 */
public enum ErrorType {
    // Undefined Variable
    UNDEF_VAR(1),
    // Undefined Function
    UNDEF_FUNC(2),
    // Redefined Variable
    REDEF_VAR(3),
    // Redefined Function
    REDEF_FUNC(4),
    // Mismatched Types Assigned
    MISMATCH_ASSIGN(5),
    // Right-Value Expression Assigned
    EXP_ASSIGN(6),
    // Mismatched Types for Operands.
    MISMATCH_OPRAND(7),
    // Mismatched Return Type
    MISMATCH_RETURN(8),
    // Mismatched Parameters for Called Function
    MISMATCH_PARAM(9),
    // Calling Non-Array
    NON_ARRAY(10),
    // Calling Non-Function
    NON_FUNC(11),
    // Access Array with Non-Integer
    NON_INT(12),
    // Illegal Dot Used
    ILLEGAL_DOT(13),
    // Undefined Field
    UNDEF_FIELD(14),
    // Redefined Field
    REDEF_FEILD(15),
    // Duplicate Structure Name
    DUPLIC_STRUCT(16),
    // Undefined Structure
    UNDEF_STRUCT(17);
    private final int errorNo;

    ErrorType(int i) {
        this.errorNo = i;
    }
    public int getErrorNo() {
        return this.errorNo;
    }
}
