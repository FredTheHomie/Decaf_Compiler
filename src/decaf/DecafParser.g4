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
method_decl: (type | VOID) ID ((type ID) (COMMA type ID)*) block;
block: LCURLY var_decl*;
var_decl: (type ID) (COMMA type ID)*;
type: INT | BOOLEAN;
method_call: ID expr (COMMA expr)*;
expr: ID | method_call | literal | expr bin_op expr | MINUS expr | NEGATION expr;
callout_arg: ;
bin_op: arith_op | rel_op | eq_op | cond_op;
arith_op: ADD | MINUS | MULTIPLY | DIVIDE | MODULUS;
rel_op: LESS_THAN | GREATER_THAN | LESS_THAN_OR_EQUAL | GREATER_THAN_OR_EQUAL;
eq_op: EQUAL | NOT_EQUAL;
cond_op: AND | DOUBLE_OR;
literal: int_literal | CHAR | bool_literal;
alpha_num: alpha | NUMBERS;
alpha: UPPERCASE_LETTERS | LOWERCASE_LETTERS | UNDER;
hex_digit: NUMBERS | HEX_DIGITS;
int_literal: decimal_literal | hex_literal;
decimal_literal: NUMBERS NUMBERS*;
hex_literal: HEX hex_digit hex_digit* ;
bool_literal: TRUE | FALSE;