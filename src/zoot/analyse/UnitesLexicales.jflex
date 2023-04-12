package zoot.analyse ;

import java_cup.runtime.*;
import zoot.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

csteE = [0-9]+
csteB = faux|vrai
idf = [a-zA-Z][a-zA-Z0-9]*
finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]
type = entier|booleen

%%
"//".*                                    { /* DO NOTHING */ }

"variables"            { return symbol(CodesLexicaux.VARIABLES); }
"fonctions"            { return symbol(CodesLexicaux.FONCTIONS); }
"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }

"si"                   { return symbol(CodesLexicaux.SI); }
"sinon"                { return symbol(CodesLexicaux.SINON); }
"alors"                { return symbol(CodesLexicaux.ALORS); }
"finsi"                { return symbol(CodesLexicaux.FINSI); }

"repeter"              { return symbol(CodesLexicaux.REPETER); }
"jusqua"               { return symbol(CodesLexicaux.JUSQUA); }
"finrepeter"           { return symbol(CodesLexicaux.FINREPETER); }

"booleen"              { return symbol(CodesLexicaux.BOOLEEN, yytext()); }
"entier"               { return symbol(CodesLexicaux.ENTIER, yytext()); }
"="                    { return symbol(CodesLexicaux.AFC); }

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }
"retourne"             { return symbol(CodesLexicaux.RETURN); }

";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }
"("                    { return symbol(CodesLexicaux.PARENTHESE_OUVRANTE); }
")"                    { return symbol(CodesLexicaux.PARENTHESE_FERMANTE); }
","                    { return symbol(CodesLexicaux.VIRGULE); }

{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }
{csteB}      	       { return symbol(CodesLexicaux.CSTBOOLEENNE, yytext()); }
{idf}                  { return symbol(CodesLexicaux.IDF, yytext()); }

{espace}               { }
.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }

