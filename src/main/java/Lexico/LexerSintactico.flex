package Lexico;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
%column
L=[a-zA-Z_]+
D=[0-9]+
H=[0-9a-fA-F]+
O=[0-7]+

identificador = {L}({L}|{D})*
entero = [-+]?(0|[1-9][0-9]*)
flotante = [-+]?(0?\.{D}|[1-9][0-9]*\.[0-9]*)
flotanteExponente = [-+]?(0?\.{D}([eE][-+]?{D})|{D}\.[0-9]*([eE][-+]?{D})|[1-9]+([eE][-+]?{D}))
octal = [-+]?0{O}
octalFlotante = [-+]?0{O}\.{O}
hexadecimal = [-+]?0[xX]{H}
hexadecimalFlotante = [-+]?(0[xX]{H}\.{H}[pP][-+]?{D}|0[xX][0-9a-fA-F]*\.{H}[pP][-+]?{D})

numero = {entero} | {flotante} | {flotanteExponente} | {octal} | {octalFlotante} | {hexadecimal} | {hexadecimalFlotante}

espacio=[\ \t\r\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
    LineTerminator = \r|\n|\r\n
    InputCharacter = [^\r\n]
    WhiteSpace     = {LineTerminator} | [ \t\f]

    /* comments */
    Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

    TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
    // Comment can be the last line of the file, without line terminator.
    EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
    DocumentationComment = "/**" {CommentContent} "*"+ "/"
    CommentContent       = ( [^*] | \*+ [^/*] )*
%%
<YYINITIAL> {
    {Comment}                      { /* ignore */ }
}
break {return new Symbol(sym.Break, yyline, yycolumn, yytext());}
case {return new Symbol(sym.Case, yyline, yycolumn, yytext());}
char {return new Symbol(sym.Char, yyline, yycolumn, yytext());}
const {return new Symbol(sym.Const, yyline, yycolumn, yytext());}
continue {return new Symbol(sym.Continue, yyline, yycolumn, yytext());}
default {return new Symbol(sym.Default, yyline, yycolumn, yytext());}
else {return new Symbol(sym.Else, yyline, yycolumn, yytext());}
for {return new Symbol(sym.For, yyline, yycolumn, yytext());}
if {return new Symbol(sym.If, yyline, yycolumn, yytext());}
int {return new Symbol(sym.Int, yyline, yycolumn, yytext());}
long {return new Symbol(sym.Long, yyline, yycolumn, yytext());}
short {return new Symbol(sym.Short, yyline, yycolumn, yytext());}
switch {return new Symbol(sym.Switch, yyline, yycolumn, yytext());}
void {return new Symbol(sym.Void, yyline, yycolumn, yytext());}
while {return new Symbol(sym.While, yyline, yycolumn, yytext());}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"/*".* {/*Ignore*/}     
";" {return new Symbol(sym.OperadorPuntoComa, yyline, yycolumn, yytext());}
"," {return new Symbol(sym.OperadorComa, yyline, yycolumn, yytext());}

"==" {return new Symbol(sym.OperadorIgualIgual, yyline, yycolumn, yytext());}
">=" {return new Symbol(sym.OperadorMayorIgual, yyline, yycolumn, yytext());}
">" {return new Symbol(sym.OperadorMayor, yyline, yycolumn, yytext());}
"<=" {return new Symbol(sym.OperadorMenorIgual, yyline, yycolumn, yytext());}
"<" {return new Symbol(sym.OperadorMenor, yyline, yycolumn, yytext());}
"!=" {return new Symbol(sym.OperadorDesigual, yyline, yycolumn, yytext());}
"||" {return new Symbol(sym.OperadorORLazy, yyline, yycolumn, yytext());}
"&&" {return new Symbol(sym.OperadorANDLazy, yyline, yycolumn, yytext());}
"^" {return new Symbol(sym.OperadorXOR, yyline, yycolumn, yytext());}
"|" {return new Symbol(sym.OperadorOR, yyline, yycolumn, yytext());}
"&" {return new Symbol(sym.OperadorAND, yyline, yycolumn, yytext());}

"!" {return new Symbol(sym.OperadorNegacion, yyline, yycolumn, yytext());} 

"++" {return new Symbol(sym.OperadorIncremental, yyline, yycolumn, yytext());}
"--" {return new Symbol(sym.OperadorDecremental, yyline, yycolumn, yytext());}
"+" {return new Symbol(sym.OperadorMas, yyline, yycolumn, yytext());}
"-" {return new Symbol(sym.OperadorMenos, yyline, yycolumn, yytext());}
"*" {return new Symbol(sym.OperadorMultiplicacion, yyline, yycolumn, yytext());}
"/" {return new Symbol(sym.OperadorDivision, yyline, yycolumn, yytext());}
"%" {return new Symbol(sym.OperadorModulo, yyline, yycolumn, yytext());}
"=" {return new Symbol(sym.OperadorIgual, yyline, yycolumn, yytext());}
"+=" {return new Symbol(sym.OperadorMasIgual, yyline, yycolumn, yytext());}
"-=" {return new Symbol(sym.OperadorMenosIgual, yyline, yycolumn, yytext());}
"*=" {return new Symbol(sym.OperadorMultiIgual, yyline, yycolumn, yytext());}
"/=" {return new Symbol(sym.OperadorDivIgual, yyline, yycolumn, yytext());}
"%=" {return new Symbol(sym.OperadorModIgual, yyline, yycolumn, yytext());}
"(" {return new Symbol(sym.OperadorParentesisIz, yyline, yycolumn, yytext());}
")" {return new Symbol(sym.OperadorParentesisDer, yyline, yycolumn, yytext());}
"[" {return new Symbol(sym.OperadorParCuadradoIz, yyline, yycolumn, yytext());}
"]" {return new Symbol(sym.OperadorParCuadradoDer, yyline, yycolumn, yytext());}
"{" {return new Symbol(sym.OperadorCorcheteIz, yyline, yycolumn, yytext());}
"}" {return new Symbol(sym.OperadorCorcheteDer, yyline, yycolumn, yytext());}
":" {return new Symbol(sym.OperadorDoblePunto, yyline, yycolumn, yytext());}
'[^']' | '\\[^']+' {return new Symbol(sym.Caracter, yyline, yycolumn, yytext());}

{identificador} {return new Symbol(sym.Identificador, yyline, yycolumn, yytext());}
{entero} {return new Symbol(sym.Entero, yyline, yycolumn, yytext());}
{flotante} {return new Symbol(sym.Flotante, yyline, yycolumn, yytext());}
{flotanteExponente} {return new Symbol(sym.FlotanteExponente, yyline, yycolumn, yytext());}
{octal} {return new Symbol(sym.Octal, yyline, yycolumn, yytext());}
{octalFlotante} {return new Symbol(sym.OctalFlotante, yyline, yycolumn, yytext());}
{hexadecimal} {return new Symbol(sym.Hexadecimal, yyline, yycolumn, yytext());}
{hexadecimalFlotante} {return new Symbol(sym.HexadecimalFlotante, yyline, yycolumn, yytext());}
{numero}{identificador} {return new Symbol(sym.Error, yyline, yycolumn, yytext());}
[^] {return new Symbol(sym.Error, yyline, yycolumn, yytext());}