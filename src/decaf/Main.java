/**
 *  This is the main class for your compiler. It uses the command line interface (CLI) tools found in java6G6Z1110.tools.CLI.
 *  It is <em>very</em> incomplete. For the first stage, you will need to update the section handling the CLI.SCAN option.
 *  Study this section of code to work out what it is doing, and read the specifications carefully to understand what it
 *  <em>should</em> be doing, then modify it accordingly.
 *  For the second stage, some code is provided that may help you with your debugging, but you must change it to give appropriate
 *  correct behaviour for submission.
 *  For the final stage, you need to think carefully about what you will need to do here.
 *  
 *  DO NOT FORGET TO CHANGE THESE COMMENTS AS WELL AS THE CODE
 */
package decaf;

import java.io.*;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.*;

import java6G6Z1110.tools.CLI.*;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * @author Emma Norling (based on code from MIT OpenCourseWare http://ocw.mit.edu
 * for the subject 6.035 Computer Language Engineering, Spring 2010)
 *
 */
public class Main {

	/**
	 * @param args - command line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            CLI.parse (args, new String[0]);
            
            InputStream inputStream = args.length == 0 ?
                    System.in : new java.io.FileInputStream(CLI.infile);
            ANTLRInputStream antlrIOS = new ANTLRInputStream(inputStream);

            if (CLI.target == CLI.SCAN || CLI.target == CLI.DEFAULT)
            {
                DecafLexer lexer = new DecafLexer(antlrIOS);
                Token token;
                boolean done = false;
                while (!done)
                {
                    try
                    {
                        for (token=lexer.nextToken();
                            token.getType()!=Token.EOF; token=lexer.nextToken()) {
                            String type = "";
                            String text = token.getText();

                            switch (token.getType()) {
                                case DecafLexer.ID:
                                    type = " IDENTIFIER";
                                    break;
                                case DecafLexer.NUMBERS:
                                    type = " NUMBER";
                                    break;
                                case DecafLexer.CLASS:
                                    type = " CLASS";
                                    break;
                                case DecafLexer.BOOLEAN:
                                    type = " BOOLEAN";
                                    break;
                                case DecafLexer.BREAK:
                                    type = " BREAK";
                                    break;
                                case DecafLexer.CALLOUT:
                                    type = " CALLOUT";
                                    break;
                                case DecafLexer.CONTINUE:
                                    type = " CONTINUE";
                                    break;
                                case DecafLexer.ELSE:
                                    type = " ELSE";
                                    break;
                                case DecafLexer.FALSE:
                                    type = " FALSE";
                                    break;
                                case DecafLexer.FOR:
                                    type = " FOR";
                                    break;
                                case DecafLexer.IF:
                                    type = " IF";
                                    break;
                                case DecafLexer.INT:
                                    type = " INT";
                                    break;
                                case DecafLexer.RETURN:
                                    type = " RETURN";
                                    break;
                                case DecafLexer.TRUE:
                                    type = " TRUE";
                                    break;
                                case DecafLexer.COMMA:
                                    type = " COMMA";
                                    break;
                                case DecafLexer.EOL:
                                    type = " EOL";
                                    break;
                                case DecafLexer.OR:
                                    type = " OR";
                                    break;
                                case DecafLexer.ADD:
                                    type = " ADD";
                                    break;
                                case DecafLexer.MINUS:
                                    type = " MINUS";
                                    break;
                                case DecafLexer.MULTIPLY:
                                    type = " MULTIPLY";
                                    break;
                                case DecafLexer.LESS_THAN:
                                    type = " LESS_THAN";
                                    break;
                                case DecafLexer.LESS_THAN_OR_EQUAL:
                                    type = " LESS_THAN_OR_EQUAL";
                                    break;
                                case DecafLexer.GREATER_THAN:
                                    type = " GREATER_THAN";
                                    break;
                                case DecafLexer.NOT_EQUAL:
                                    type = " NOT_EQUAL";
                                    break;
                                case DecafLexer.AND:
                                    type = " AND";
                                    break;
                                case DecafLexer.LSQUARE:
                                    type = " LSQUARE";
                                    break;
                                case DecafLexer.RSQUARE:
                                    type = " RSQUARE";
                                    break;
                                case DecafLexer.ASSIGN:
                                    type = " ASSIGN";
                                    break;
                                case DecafLexer.LBRACKET:
                                    type = " LBRACKET";
                                    break;
                                case DecafLexer.RBRACKET:
                                    type = " RBRACKET";
                                    break;
                                case DecafLexer.LCURLY:
                                    type = " LCURLY";
                                    break;
                                case DecafLexer.RCURLY:
                                    type = " RCURLY";
                                    break;
                                case DecafLexer.DOT:
                                    type = " DOT";
                                    break;
                                case DecafLexer.UPPERCASE_LETTERS:
                                    type = " UPPERCASE_LETTERS";
                                    break;
                                case DecafLexer.LOWERCASE_LETTERS:
                                    type = " LOWERCASE_LETTERS";
                                    break;
                                case DecafLexer.WS_:
                                    type = " WS_";
                                    break;
                                case DecafLexer.SL_COMMENT:
                                    type = " SL_COMMENT";
                                    break;
                                case DecafLexer.CHAR:
                                    type = " CHAR";
                                    break;
                                case DecafLexer.STRING:
                                    type = " STRING";
                                    break;
                        }
                            System.out.println (token.getLine() + type + " " + text);
                        }
                        done = true;
                    } catch(Exception e) {
                        // print the error:
                        System.out.println(CLI.infile+" "+e);
                    }
                }
            }
            else if (CLI.target == CLI.PARSE)
            {
                DecafLexer lexer = new DecafLexer(antlrIOS);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                DecafParser parser = new DecafParser (tokens);
                ParseTree tree = parser.program();
                if (CLI.debug) {
                    TreePrinterListener listener = new TreePrinterListener(parser);
	                ParseTreeWalker.DEFAULT.walk(listener, tree);
	                String formatted = listener.toString();
	                System.out.println(formatted);
                }
            }
            
        } catch(Exception e) {
            // print the error:
            System.out.println(CLI.infile+" "+e);
        }
    }
}
