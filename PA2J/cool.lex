/*
 *  The scanner definition for COOL.
 */

import java.lang.Boolean;
import java_cup.runtime.Symbol;

%%

%{

/*  Stuff enclosed in %{ %} is copied verbatim to the lexer class
 *  definition, all the extra variables/functions you want to use in the
 *  lexer actions should go here.  Don't remove or modify anything that
 *  was there initially.  */

    // Max size of string constants
    static int MAX_STR_CONST = 1025;

    // For assembling string constants
    StringBuffer string_buf = new StringBuffer();

    private int curr_lineno = 1;
    int get_curr_lineno() {
	return curr_lineno;
    }

    private AbstractSymbol filename;

    void set_filename(String fname) {
	filename = AbstractTable.stringtable.addString(fname);
    }

    AbstractSymbol curr_filename() {
	return filename;
    }
%}

%init{

/*  Stuff enclosed in %init{ %init} is copied verbatim to the lexer
 *  class constructor, all the extra initialization you want to do should
 *  go here.  Don't remove or modify anything that was there initially. */

    // empty for now
%init}

%eofval{

/*  Stuff enclosed in %eofval{ %eofval} specifies java code that is
 *  executed when end-of-file is reached.  If you use multiple lexical
 *  states and want to do something special if an EOF is encountered in
 *  one of those states, place your code in the switch statement.
 *  Ultimately, you should return the EOF symbol, or your lexer won't
 *  work.  */

    switch(yy_lexical_state) {
    case YYINITIAL:
	/* nothing special to do in the initial state */
	break;
	/* If necessary, add code for other states here, e.g: */
    case COMMENT:
        yybegin(YYINITIAL);
        return new Symbol(TokenConstants.ERROR, "EOF in comment");
    case STRING:
    case STRING_ERROR:
        yybegin(YYINITIAL);
        return new Symbol(TokenConstants.ERROR, "EOF in string constant");
    }
    return new Symbol(TokenConstants.EOF);
%eofval}

%class CoolLexer
%cup
%unicode

%{
  private int comment_count = 0;
%}

%state COMMENT
%state STRING
%state STRING_ERROR

DIGIT=[0-9]
DIGITS={DIGIT}+
LOWERCASE=[\a-\z]
UPPERCASE=[\A-\Z]
LETTER={LOWERCASE}|{UPPERCASE}
NONNEWLINE_WHITESPACE=[\ \f\r\t\013]+
STR_CHAR=[^\n\"\\\x00]
COMMENT_TEXT=([^()*\n]|[^*\n]")"|"("[^*\n]|"*"[^)\n]|[^(\n]"*"[^)\n])*
NEWLINE=\n|\r|\r\n
								       
%%

<YYINITIAL>";"                  { return new Symbol(TokenConstants.SEMI);        }
<YYINITIAL>"{"                  { return new Symbol(TokenConstants.LBRACE);      }
<YYINITIAL>"}"                  { return new Symbol(TokenConstants.RBRACE);      }
<YYINITIAL>"("                  { return new Symbol(TokenConstants.LPAREN);      }
<YYINITIAL>")"                  { return new Symbol(TokenConstants.RPAREN);      }
<YYINITIAL>","                  { return new Symbol(TokenConstants.COMMA);       }
<YYINITIAL>":"                  { return new Symbol(TokenConstants.COLON);       }
<YYINITIAL>"@"                  { return new Symbol(TokenConstants.AT);          }
<YYINITIAL>"."                  { return new Symbol(TokenConstants.DOT);         }
<YYINITIAL>"+"                  { return new Symbol(TokenConstants.PLUS);        }
<YYINITIAL>"-"                  { return new Symbol(TokenConstants.MINUS);       }
<YYINITIAL>"*"                  { return new Symbol(TokenConstants.MULT);        }
<YYINITIAL>"/"                  { return new Symbol(TokenConstants.DIV);         }
<YYINITIAL>"~"                  { return new Symbol(TokenConstants.NEG);         }
<YYINITIAL>"<"                  { return new Symbol(TokenConstants.LT);          }
<YYINITIAL>"="                  { return new Symbol(TokenConstants.EQ);          }
<YYINITIAL>"<="                 { return new Symbol(TokenConstants.LE);          }
<YYINITIAL>"<-"                 { return new Symbol(TokenConstants.ASSIGN);      }
<YYINITIAL>"=>"			{ return new Symbol(TokenConstants.DARROW);      }

<YYINITIAL>[c|C][l|L][a|A][s|S][s|S]                { return new Symbol(TokenConstants.CLASS);   }
<YYINITIAL>[i|I][n|N][h|H][e|E][r|R][i|I][t|T][s|S] { return new Symbol(TokenConstants.INHERITS);    }
<YYINITIAL>[i|I][f|F]                               { return new Symbol(TokenConstants.IF);          }
<YYINITIAL>[t|T][h|H][e|E][n|N]                     { return new Symbol(TokenConstants.THEN);        }
<YYINITIAL>[e|E][l|L][s|S][e|E]                     { return new Symbol(TokenConstants.ELSE);        }
<YYINITIAL>[f|F][i|I]                               { return new Symbol(TokenConstants.FI);          }
<YYINITIAL>[w|W][h|H][i|I][l|L][e|E]                { return new Symbol(TokenConstants.WHILE);       }
<YYINITIAL>[l|L][o|O][o|O][p|P]                     { return new Symbol(TokenConstants.LOOP);        }
<YYINITIAL>[p|P][o|O][o|O][l|L]                     { return new Symbol(TokenConstants.POOL);        }
<YYINITIAL>[l|L][e|E][t|T]                          { return new Symbol(TokenConstants.LET);         }
<YYINITIAL>[i|I][n|N]                               { return new Symbol(TokenConstants.IN);          } 
<YYINITIAL>[c|C][a|A][s|S][e|E]                     { return new Symbol(TokenConstants.CASE);        }
<YYINITIAL>[o|O][f|F]                               { return new Symbol(TokenConstants.OF);          }
<YYINITIAL>[e|E][s|S][a|A][c|C]                     { return new Symbol(TokenConstants.ESAC);        }
<YYINITIAL>[n|N][e|E][w|W]                          { return new Symbol(TokenConstants.NEW);         }
<YYINITIAL>[i|I][s|S][v|V][o|O][i|I][d|D]           { return new Symbol(TokenConstants.ISVOID);      }
<YYINITIAL>[n|N][o|O][t|T]                          { return new Symbol(TokenConstants.NOT);         }

<YYINITIAL>{DIGITS}             { return new Symbol(TokenConstants.INT_CONST,
				      AbstractTable.inttable.addString(yytext())); }

<YYINITIAL>{UPPERCASE}([{DIGIT}|{LETTER}|"_")*
                                { return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
<YYINITIAL>{LOWERCASE}({DIGIT}|{LETTER}|"_")*
                                { if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}

<YYINITIAL>\"                   { yybegin(STRING); string_buf.setLength(0); }
<STRING>\"                      { yybegin(YYINITIAL);
                                  if (string_buf.length() >= MAX_STR_CONST) {
                                    return new Symbol(TokenConstants.ERROR, "String constant too long");
                                  }
				  return new Symbol(TokenConstants.STR_CONST,
				      AbstractTable.stringtable.addString(string_buf.toString()));
                                }
<STRING>{STR_CHAR}+             { string_buf.append(yytext()); }
<STRING>\\b                     { string_buf.append('\b'); }
<STRING>\\t                     { string_buf.append('\t'); }
<STRING>\\n                     { string_buf.append('\n'); }
<STRING>\\f                     { string_buf.append('\f'); }
<STRING>\\{NEWLINE}             { curr_lineno = curr_lineno + 1;
                                  string_buf.append("\n"); }
<STRING>\x00                    { yybegin(STRING_ERROR);
                                  return new Symbol(TokenConstants.ERROR, "String contains null character."); }
<STRING>\\\x00                  { yybegin(STRING_ERROR);
                                  return new Symbol(TokenConstants.ERROR, "String contains escaped null character."); }
<STRING>\\.                     { string_buf.append(yytext().charAt(1)); }
  
<STRING>{NEWLINE}               { curr_lineno = curr_lineno + 1;
                                  yybegin(YYINITIAL);
                                  return new Symbol(TokenConstants.ERROR, "Unterminated string constant"); }
<STRING_ERROR>(.)*\"            { yybegin(YYINITIAL); }
  <STRING_ERROR>(.)*{NEWLINE}   { yybegin(YYINITIAL); }
    
<YYINITIAL>"--"(.)*             { }
<YYINITIAL>"(*"                 { yybegin(COMMENT); comment_count = comment_count + 1; }
<YYINITIAL>"*)"                 { return new Symbol(TokenConstants.ERROR, "Unmatched *)"); }
<COMMENT>"(*"                   { comment_count = comment_count + 1; }
<COMMENT>"*)"                   {
				    comment_count = comment_count - 1;
				    if (comment_count == 0) {
				      yybegin(YYINITIAL);
				    }
                                }
<COMMENT>.                      { }

<YYINITIAL>{NONNEWLINE_WHITESPACE}      { }
<YYINITIAL,COMMENT>{NEWLINE}    { curr_lineno = curr_lineno + 1; }

<YYINITIAL,COMMENT,STRING>.     { return new Symbol(TokenConstants.ERROR, yytext()); }
