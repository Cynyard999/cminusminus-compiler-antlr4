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

extDecList: varDec[false] (COMMA varDec[false])*;


// Specifier
specifier: structSpecifier
  | TYPE
  ;

structSpecifier: STRUCT optTag LC defList[true] RC
  | STRUCT tag
  ;

optTag: ID
  |
  ;

tag: ID;

// Declarators
varDec[boolean inStruct]: ID (LB (INT| (errorToken = FLOAT|errorToken = ID)
{notifyErrorListeners($errorToken, "array size must be an integer constant, not "+$errorToken.getText(), null);}
) RB)*;

funDec: ID LP varList RP
  | ID LP RP
  ;

varList: paramDec (COMMA paramDec)*;

paramDec: specifier varDec[false];

// Statements
compSt: LC defList[false] stmtList RC;

stmtList: stmt*;

stmt: exp SEMI #stmtExp
  | compSt #stmtCompSt
  | RETURN exp SEMI #stmtReturn
  | IF LP exp RP stmt #stmtIf
  | IF LP exp RP stmt ELSE stmt #stmtIfElse
  | WHILE LP exp RP stmt #stmtWhile
  ;

// Local Definitions
defList[boolean inStruct]: def[$inStruct]*;

def[boolean inStruct]: specifier decList[$inStruct] SEMI;

decList[boolean inStruct]: dec[$inStruct] (COMMA dec[$inStruct])*
;

dec[boolean inStruct]: varDec[$inStruct]
  | varDec[$inStruct] ASSIGNOP exp
  ;

// Expressions
exp: ID LP args? RP #expFuncArgs
  | LP exp RP #expParenthesis
  | exp LB exp RB #expBrackets
  | exp DOT ID #expDot
  | <assoc=right>(MINUS | NOT) exp #expMinusNot
  | exp (STAR | DIV) exp #expStarDiv
  | exp (PLUS | MINUS) exp #expPlusMinus
  | exp RELOP exp #expRelop
  | exp AND exp #expAnd
  | exp OR exp #expOr
  |<assoc=right>exp ASSIGNOP exp #expAssignop
  | ID #expId
  | INT #expInt
  | FLOAT #expFloat
  ;

args: exp (COMMA exp)*;