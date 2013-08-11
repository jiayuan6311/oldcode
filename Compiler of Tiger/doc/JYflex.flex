package parse;
import errormsg.ErrorMsg;
import java.io.InputStream;
import java_cup.runtime.Symbol;

%%

%class JYTiger
%implements Lexer
%function nextToken
%type java_cup.runtime.Symbol
%char


%{
int comment_c=0;

StringBuffer string = new StringBuffer();
 
private void newline() {
  errorMsg.newline(yychar);
}

private void err(int pos, String s) {
  errorMsg.error(pos,s);
}

private void err(String s) {
  err(yychar,s);
}
private Symbol symbol(int type) {
  return new Symbol(type, yyline, yycolumn);
} 
private java_cup.runtime.Symbol symbol(int kind, Object value) { 
  return new java_cup.runtime.Symbol(kind, yychar, yychar+yylength(), value); 
}
private errormsg.ErrorMsg errorMsg;
JYTiger(java.io.InputStream s, ErrorMsg e) { 
  this(s); 
errorMsg=e; 
}
%}

%eofval{ 

         {if (yystate()==COMMENT) err("Comment symbol don't match!"); 

          if (yystate()==STRING) err("String presentation error!"); 

          if (yystate()==STRING1) err("String presentation error!"); 

          return tok(sym.EOF, null); 

            } 
%eofval} 

%state STRING
%state COMMENT

LineTerminator=\r|\n|\r\n

WhiteSpace=  {LineTerminator}|[ \t\f]

	/* identifiers */
Identifier = [a-zA-Z][a-zA-Z0-9_]*

	/* integer literals */
DecIntegerLiteral = [0-9]+
  /* String */
StringCharacter = [^\r\n\"\\]


%%
<YYINITIAL>{
	
	/* reserved words */
	
	"while"					{ return symbol(sym.WHILE);}
	"for"						{ return symbol(sym.FOR);}
	"to"						{ return symbol(sym.TO);}
	"break"					{ return symbol(sym.BREAK);}
	"let"						{ return symbol(sym.LET);}
	"in"						{ return symbol(sym.IN);}
	"end"						{ return symbol(sym.END);}
	"function"			{ return symbol(sym.FUNCTION);}
	"var"						{ return symbol(sym.VAR);}
	"type"					{ return symbol(sym.TYPE);}
	"array"					{ return symbol(sym.ARRAY);}
	"if"						{ return symbol(sym.IF);}
	"then"					{ return symbol(sym.THEN);}
	"else"					{ return symbol(sym.ELSE);}
	"do"						{ return symbol(sym.DO);}
	"of"						{ return symbol(sym.OF);}
	"nil"						{ return symbol(sym.NIL);}
	
	/* separators */
  "("                            { return symbol(sym.LPAREN); }
  ")"                            { return symbol(sym.RPAREN); }
  "{"                            { return symbol(sym.LBRACE); }
  "}"                            { return symbol(sym.RBRACE); }
  "["                            { return symbol(sym.LBRACK); }
  "]"                            { return symbol(sym.RBRACK); }
  ";"                            { return symbol(sym.SEMICOLON); }
  ","                            { return symbol(sym.COMMA); }
  "."                            { return symbol(sym.DOT); }
  
	/* operators */
  ":"							               { return symbol(sym.COLON); }
  "+"                            { return symbol(sym.PLUS); }
  "-"                            { return symbol(sym.MINUS); }
  "*"                            { return symbol(sym.TIMES); }
  "/"                            { return symbol(sym.DIVIDE); }
  "="                            { return symbol(sym.EQ); }
  "<>"                           { return symbol(sym.NEQ); }
  "<"                            { return symbol(sym.LT); }
  "<="                           { return symbol(sym.LE); }
  ">"                            { return symbol(sym.GT); }
  ">="                           { return symbol(sym.GE); }
  ":="                           { return symbol(sym.ASSIGN); }
  "&"							 { return symbol(sym.AND);}  
  "|"							 { return symbol(sym.OR);}    
	/* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }
	/* comment */
  "/*"							 { yybegin(COMMENT);comment_c=1;}
  
    
	/* integer literal */
  {DecIntegerLiteral}            { return symbol(sym.INT, new Integer(yytext())); }

	/* whitespace */
  {WhiteSpace}*                   { /* ignore */ }
  
  	/* identifiers */ 
  {Identifier}                   { return symbol(sym.ID, yytext()); }  
  }
  
  <STRING> 
  {
  \"                             { yybegin(YYINITIAL); return symbol(sym.STRING, string.toString());  }
   
  /* escape sequences */
  
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\\\"                         { string.append( '\\' ); }
  \\[0-9][0-9][0-9]				 {char val = (char) Integer.parseInt(yytext().substring(1),8);string.append( val ); } 
  
  
  {StringCharacter}+             { string.append( yytext() ); }
  }


	/* comments */
<COMMENT>{	
  "/*"							 {comment_c++;}
  "*/"							 {--comment_c; if ( comment_c==0 ) yybegin(YYINITIAL);}
  
  [^]                        		 { /* ignore */ } 
  }
 <<EOF>>                          { return symbol(sym.EOF); }
