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