parser grammar CmmParser;

options {
  tokenVocab=CmmLexer;
}

// High-level Definitions
program: extDef* EOF;

extDef: specifier extDecList SEMI
  | specifier SEMI
  | specifier funDec compSt
  ;

extDecList: varDec (COMMA varDec)*;


// Specifier
specifier: structSpecifier
  | TYPE
  ;

structSpecifier: STRUCT optTag LC defList RC
  | STRUCT tag
  ;

optTag: ID
  |
  ;

tag: ID;

// Declarators
//varDec: ID (LB (INT|FLOAT|ID) RB)* // Antlr4 helps to import needed pacakge
//{
//    if ($ctx.ID().size() > 1 || $ctx.FLOAT().size() > 0) {
//        List<TerminalNode> IDList = $ctx.ID();
//        List<TerminalNode> FLOATList = $ctx.FLOAT();
//        List<TerminalNode> ErrorList = new ArrayList<>();
//        ErrorList.addAll(IDList);
//        ErrorList.addAll(FLOATList);
//        ErrorList.sort((node1, node2) ->
//                node1.getSymbol().getLine() - node2.getSymbol().getLine() == 0 ?
//                        node1.getSymbol().getCharPositionInLine() - node2.getSymbol()
//                                .getCharPositionInLine()
//                        : node1.getSymbol().getLine() - node2.getSymbol().getLine());
//        boolean skip = true;
//        for (TerminalNode id : ErrorList) {
//            if (skip) {
//                skip = false;
//                continue;
//            }
//            notifyErrorListeners(id.getSymbol(),
//                    "array size must be an integer constant, not " + id.getText(),
//                    null);
//        }
//    }
//};

varDec: ID (LB (INT| (errorToken = FLOAT|errorToken = ID)
{notifyErrorListeners($errorToken, "array size must be an integer constant, not "+$errorToken.getText(), null);}
) RB)*;



funDec: ID LP varList RP
  | ID LP RP
  ;

varList: paramDec (COMMA paramDec)*;

paramDec: specifier varDec;

// Statements
compSt: LC defList stmtList RC;

stmtList: stmt*;

stmt: exp SEMI
  | compSt
  | RETURN exp SEMI
  | IF LP exp RP stmt
  | IF LP exp RP stmt ELSE stmt
  | WHILE LP exp RP stmt
  ;

// Local Definitions
defList: def*;

def: specifier decList SEMI;

decList: dec (COMMA dec)*
;

dec: varDec
  | varDec ASSIGNOP exp
  ;

// Expressions
exp: ID LP args RP
  | ID LP RP
  | LP exp RP
  | exp LB exp RB
  | exp DOT ID
  | <assoc=right>(MINUS | NOT) exp
  | exp (STAR | DIV) exp
  | exp (PLUS | MINUS) exp
  | exp RELOP exp
  | exp AND exp
  | exp OR exp
  |<assoc=right>exp ASSIGNOP exp
  | ID
  | INT
  | FLOAT
  ;

args: exp (COMMA exp)*;