package codigo;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }

    private Symbol symbol(int type){
            return new Symbol(type, yyline, yycolumn, value);
        }
%}
%%
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"=" {return new Symbol(sym.Igual, yychar, yyline, yytext());}
Coseno {return new Symbol(sym.Coseno, yychar, yyline, yytext());}
Seno {return new Symbol(sym.Seno, yychar, yyline, yytext());}
Tangente {return new Symbol(sym.Tangente, yychar, yyline, yytext());}
{L} ({L} | {D})* {return new Symbol(sym.Identificador, yychar, yyline, yytext());}
("(-"{D}+")") | {D}+ {return new Symbol(sym.Numero, yychar, yyline, yytext());}
 . {return new Symbol(sym.Error, yychar, yyline, yytext());}