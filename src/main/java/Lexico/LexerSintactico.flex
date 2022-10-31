package Lexico;
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

espacio=[ \t\r\n]+
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
break {return new Symbol(sym.Break, yyline, yychar, yytext());}
case {return new Symbol(sym.Case, yyline, yychar, yytext());}
char {return new Symbol(sym.Char, yyline, yychar, yytext());}
const {return new Symbol(sym.Const, yyline, yychar, yytext());}
continue {return new Symbol(sym.Continue, yyline, yychar, yytext());}
default {return new Symbol(sym.Default, yyline, yychar, yytext());}
else {return new Symbol(sym.Else, yyline, yychar, yytext());}
for {return new Symbol(sym.For, yyline, yychar, yytext());}
if {return new Symbol(sym.If, yyline, yychar, yytext());}
int {return new Symbol(sym.Int, yyline, yychar, yytext());}
long {return new Symbol(sym.Long, yyline, yychar, yytext());}
short {return new Symbol(sym.Short, yyline, yychar, yytext());}
switch {return new Symbol(sym.Switch, yyline, yychar, yytext());}
void {return new Symbol(sym.Void, yyline, yychar, yytext());}
while {return new Symbol(sym.While, yyline, yychar, yytext());}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"/*".* {/*Ignore*/}     
";" {return new Symbol(sym.OperadorPuntoComa, yyline, yychar, yytext());}
"," {return new Symbol(sym.OperadorComa, yyline, yychar, yytext());}

"==" {return new Symbol(sym.OperadorIgualIgual, yyline, yychar, yytext());}
">=" {return new Symbol(sym.OperadorMayorIgual, yyline, yychar, yytext());}
">" {return new Symbol(sym.OperadorMayor, yyline, yychar, yytext());}
"<=" {return new Symbol(sym.OperadorMenorIgual, yyline, yychar, yytext());}
"<" {return new Symbol(sym.OperadorMenor, yyline, yychar, yytext());}
"!=" {return new Symbol(sym.OperadorDesigual, yyline, yychar, yytext());}
"||" {return new Symbol(sym.OperadorORLazy, yyline, yychar, yytext());}
"&&" {return new Symbol(sym.OperadorANDLazy, yyline, yychar, yytext());}
"^" {return new Symbol(sym.OperadorXOR, yyline, yychar, yytext());}
"|" {return new Symbol(sym.OperadorOR, yyline, yychar, yytext());}
"&" {return new Symbol(sym.OperadorAND, yyline, yychar, yytext());}

"!" {return new Symbol(sym.OperadorNegacion, yyline, yychar, yytext());}

"++" {return new Symbol(sym.OperadorIncremental, yyline, yychar, yytext());}
"--" {return new Symbol(sym.OperadorDecremental, yyline, yychar, yytext());}
"+" {return new Symbol(sym.OperadorMas, yyline, yychar, yytext());}
"-" {return new Symbol(sym.OperadorMenos, yyline, yychar, yytext());}
"*" {return new Symbol(sym.OperadorMultiplicacion, yyline, yychar, yytext());}
"/" {return new Symbol(sym.OperadorDivision, yyline, yychar, yytext());}
"%" {return new Symbol(sym.OperadorModulo, yyline, yychar, yytext());}
"=" {return new Symbol(sym.OperadorIgual, yyline, yychar, yytext());}
"+=" {return new Symbol(sym.OperadorMasIgual, yyline, yychar, yytext());}
"-=" {return new Symbol(sym.OperadorMenosIgual, yyline, yychar, yytext());}
"*=" {return new Symbol(sym.OperadorMultiIgual, yyline, yychar, yytext());}
"/=" {return new Symbol(sym.OperadorDivIgual, yyline, yychar, yytext());}
"%=" {return new Symbol(sym.OperadorModIgual, yyline, yychar, yytext());}
"(" {return new Symbol(sym.OperadorParentesisIz, yyline, yychar, yytext());}
")" {return new Symbol(sym.OperadorParentesisDer, yyline, yychar, yytext());}
"[" {return new Symbol(sym.OperadorParCuadradoIz, yyline, yychar, yytext());}
"]" {return new Symbol(sym.OperadorParCuadradoDer, yyline, yychar, yytext());}
"{" {return new Symbol(sym.OperadorCorcheteIz, yyline, yychar, yytext());}
"}" {return new Symbol(sym.OperadorCorcheteDer, yyline, yychar, yytext());}
":" {return new Symbol(sym.OperadorDoblePunto, yyline, yychar, yytext());}
'[^']' | '\\[^']+' {return new Symbol(sym.Caracter, yyline, yychar, yytext());}

{identificador} {return new Symbol(sym.Identificador, yyline, yychar, yytext());}
{entero} {return new Symbol(sym.Entero, yyline, yychar, yytext());}
{flotante} {return new Symbol(sym.Flotante, yyline, yychar, yytext());}
{flotanteExponente} {return new Symbol(sym.FlotanteExponente, yyline, yychar, yytext());}
{octal} {return new Symbol(sym.Octal, yyline, yychar, yytext());}
{octalFlotante} {return new Symbol(sym.OctalFlotante, yyline, yychar, yytext());}
{hexadecimal} {return new Symbol(sym.Hexadecimal, yyline, yychar, yytext());}
{hexadecimalFlotante} {return new Symbol(sym.HexadecimalFlotante, yyline, yychar, yytext());}
numero identificador {return new Symbol(sym.Error, yyline, yychar, yytext());}
[^] {return new Symbol(sym.Error, yyline, yychar, yytext());}