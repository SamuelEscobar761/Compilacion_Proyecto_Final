package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    public string lexeme;
%}
%%
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"=" {return Igual;}
Coseno {lexeme=yytext(); return Coseno;}
Seno {lexeme=yytext(); return Seno;}
Tangente {lexeme=yytext(); return Tangente;}
{L} ({L} | {D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")") | {D}+ {lexeme=yytext(); return Numero;}
 . {return ERROR;}