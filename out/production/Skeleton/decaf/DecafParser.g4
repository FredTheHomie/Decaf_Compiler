/*
 * A VERY minimal skeleton for your parser, provided by Emma Norling.
 *
 * Your parser should use the tokens provided by your lexer in rules.
 * Even if your lexer appeared to be working perfectly for stage 1,
 * you might need to adjust some of those rules when you implement
 * your parser.
 *
 * Remember to provide documentation too (including replacing this
 * documentation).
 *
 */
parser grammar DecafParser;
options { tokenVocab = DecafLexer; }

// This rule says that a program consists of the tokens CLASS ID LCURLY RCURLY EOF nothing more nothing less,
// in exactly that order. However obviously something (quite a lot of something) needs to go between the curly
// brackets. You need to write the rules (based on the provided grammar) to capture this.
program: CLASS ID LCURLY field_decl* method_decl* RCURLY EOF;
field_name: ID | (ID LSQUARE NUMBERS RSQUARE);
field_decl: type field_name (COMMA field_name)* EOL;
method_decl: (type | VOID) ID LBRACKET (type ID (COMMA type ID)*)? RBRACKET block;
block: LCURLY var_decl* statement* RCURLY;
var_decl: type var_name ((COMMA type var_name))* EOL;
var_name: ID;
type: INT | BOOLEAN;
statement: location assign_op expr EOL|
           method_call EOL|
           IF LBRACKET expr RBRACKET block (ELSE block)? |
           FOR ID ASSIGN expr COMMA expr block |
           RETURN (expr)? EOL|
           BREAK EOL|
           CONTINUE EOL|
           block;
assign_op: ASSIGN | PLUSEQUALS | MINUSEQUALS;
method_call: ID LBRACKET (expr (COMMA expr)*)? RBRACKET |
             CALLOUT LBRACKET STRING (COMMA callout_arg (COMMA callout_arg)*)? RBRACKET;
method_name: ID;
location: ID | ID LSQUARE expr RSQUARE;

expr: MINUS expr |
      NEGATION expr
      expr (MULTIPLY | DIVIDE | MODULUS) expr |
      expr (ADD | MINUS) expr |
      expr rel_op expr |
      expr eq_op expr |
      expr AND expr |
      expr DOUBLE_OR expr |
      location |
      method_call |
      literal |
      LBRACKET expr RBRACKET;

callout_arg: expr | STRING;
bin_op: arith_op | rel_op | eq_op | cond_op;
arith_op: ADD |
          MINUS |
          MULTIPLY |
          DIVIDE |
          MODULUS;
rel_op: LESS_THAN |
        LESS_THAN_OR_EQUAL |
        GREATER_THAN_OR_EQUAL |
        GREATER_THAN;
eq_op: EQUAL | NOT_EQUAL;
cond_op: AND | DOUBLE_OR;
literal: NUMBERS | CHAR | bool_literal;

decimal_literal: NUMBERS NUMBERS*;
bool_literal: TRUE | FALSE;