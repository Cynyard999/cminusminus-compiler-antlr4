lexer grammar CmmLexer;


Whitespace
    :   [ \t]+
        -> skip
    ;

Newline
    :   (   '\r' '\n'?
        |   '\n'
        )
        -> skip
    ;

BlockComment
    :   '/*' .*? '*/'
        -> skip
    ;

LineComment
    :   '//' ~[\r\n]*
        -> skip
    ;

DO : 'do';
WHILE : 'while';
IF : 'if';
ELSE : 'else';
RETURN : 'return';
STRUCT : 'struct';

TYPE : 'int' | 'float';

LP : '(';
RP : ')';
LB : '[';
RB : ']';
LC : '{';
RC : '}';

RELOP : '<' | '<=' | '>' | '>=' | '==' | '!=';

PLUS : '+';
MINUS : '-';
STAR : '*';
DIV : '/';

AND : '&&';  // &
OR : '||';  // |
NOT : '!';

SEMI : ';';
COMMA : ',';

ASSIGNOP : '=';

DOT : '.';



ID : Nondigit (Nondigit | Digit)*;

INT : Int8 | Int10 | Int16;

FLOAT : Science | Real;


fragment
Nondigit
    :   [a-zA-Z_]
    ;

fragment
Digit
    :   [0-9]
    ;

fragment
NonZeroDigit
    :   [1-9]
    ;

fragment
Int10
    :    NonZeroDigit Digit*
    ;

fragment
Int8
    :   '0' ([0-7])*
    ;

fragment
Int16
    :   '0' ('x'|'X') ([a-fA-F] | Digit)+  // should be '+' but not '*'
    ;

fragment
Real
    :   Digit+ DOT Digit+
    ;

fragment
Science
    :   ((Digit* DOT Digit+)|(Digit+ DOT Digit*)) ('e'|'E') (('+'|'-')?) Digit+
    ;