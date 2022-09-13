package Lexico;
import static Lexico.Tokens.*;
%%
%class Lexer
%type Tokens
%line
L=[a-zA-Z]+
D=[0-9]+
H=[0-9,A,B,C,D,E,F]
espacio=[ ,\t,\r,\n]+
%{
    public String lexeme;
    public int line;
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
auto |
break |
case |
char |
const |
continue |
default |
do |
double |
else |
enum |
extern |
float |
for |
goto |
if |
int |
long |
register |
return |
short |
signed |
sizeof |
static |
struct |
switch |
typedef |
union |
unsigned |
void |
volatile |
while {lexeme=yytext(); line=yyline+1; return Reservadas;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"/*".* {/*Ignore*/}     
"," {lexeme = yytext(); line=yyline+1; return OperadorComa;}
";" {lexeme = yytext(); line=yyline+1; return OperadorPuntoComa;}
"++" {lexeme = yytext(); line=yyline+1; return OperadorIncremento;}
"--" {lexeme = yytext(); line=yyline+1; return OperadorDecremento;}
"==" {lexeme = yytext(); line=yyline+1; return OperadorIgualIgual;}
">=" {lexeme = yytext(); line=yyline+1; return OperadorMayorIgual;}
">" {lexeme = yytext(); line=yyline+1; return OperadorMayor;}
"?" {lexeme = yytext(); line=yyline+1; return OperadorPregunta;}
"<=" {lexeme = yytext(); line=yyline+1; return OperadorMenorIgual;}
"<" {lexeme = yytext(); line=yyline+1; return OperadorMenor;}
"!=" {lexeme = yytext(); line=yyline+1; return OperadorDiferente;}
"||" {lexeme = yytext(); line=yyline+1; return OperadorO;}
"&&" {lexeme = yytext(); line=yyline+1; return OperadorY;}
"!" {lexeme = yytext(); line=yyline+1; return OperadorNegacion;}
"=" {lexeme = yytext(); line=yyline+1; return OperadorIgual;}
"+" {lexeme = yytext(); line=yyline+1; return OperadorSuma;}
"-" {lexeme = yytext(); line=yyline+1; return OperadorResta;}
"*" {lexeme = yytext(); line=yyline+1; return OperadorMultiplicacion;}
"/" {lexeme = yytext(); line=yyline+1; return OperadorDivision;}
"%" {lexeme = yytext(); line=yyline+1; return OperadorModulo;}
"(" {lexeme = yytext(); line=yyline+1; return OperadorParentesisIz;}
")" {lexeme = yytext(); line=yyline+1; return OperadorParentesisDer;}
"[" {lexeme = yytext(); line=yyline+1; return OperadorParCuadradoIz;}
"]" {lexeme = yytext(); line=yyline+1; return OperadorParCuadradoDer;}
"{" {lexeme = yytext(); line=yyline+1; return OperadorCorcheteIz;}
"}" {lexeme = yytext(); line=yyline+1; return OperadorCorcheteDer;}
":" {lexeme = yytext(); line=yyline+1; return OperadorDoblePunto;}
"." {lexeme = yytext(); line=yyline+1; return OperadorPunto;}
"+=" {lexeme = yytext(); line=yyline+1; return OperadorSumaAsignacion;}
"-=" {lexeme = yytext(); line=yyline+1; return OperadorRestaAsignacion;}
"*=" {lexeme = yytext(); line=yyline+1; return OperadorMultAsignacion;}
"/=" {lexeme = yytext(); line=yyline+1; return OperadorDivAsignacion;}
"&" {lexeme = yytext(); line=yyline+1; return OperadorDireccion;}
"^" {lexeme = yytext(); line=yyline+1; return OperadorXOR;}
"|" {lexeme = yytext(); line=yyline+1; return OperadorOR;}
">>" {lexeme = yytext(); line=yyline+1; return OperadorDesplDer;}
"<<" {lexeme = yytext(); line=yyline+1; return OperadorDesplIz;}
"~" {lexeme = yytext(); line=yyline+1; return OperadorComplementoAUno;}
"%=" {lexeme = yytext(); line=yyline+1; return OperadorModuloAsignacion;}
"&=" {lexeme = yytext(); line=yyline+1; return OperadorANDAsignacion;}
"^=" {lexeme = yytext(); line=yyline+1; return OperadorXORAsignacion;}
"|=" {lexeme = yytext(); line=yyline+1; return OperadorORAsignacion;}
"<<=" {lexeme = yytext(); line=yyline+1; return OperadorDesplIzAsig;}
">>=" {lexeme = yytext(); line=yyline+1; return OperadorDesplDerAsig;}
"->" {lexeme = yytext(); line=yyline+1; return OperadorMiembroPuntero;}
"#" {lexeme = yytext(); line=yyline+1; return OperadorGato;}
\"(\\\"|[^\"])+\" {lexeme=yytext(); return Hilera;}
'[^']' {lexeme=yytext(); return Caracter;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
[-+]?0|[1-9]{D}* {lexeme=yytext(); return Entero;}
[-+]?0|[1-9][0-9]*\.[0-9]+ {lexeme=yytext(); return Flotante;}
[-+]?0|[1-9][0-9]*\.[0-9]+([eE][-+]?[0-9]+) {lexeme=yytext(); return FlotanteExponente;}
[-+]?0[0-7]+ {lexeme=yytext(); return Octal;}
[-+]?0[0-7]+\.[0-7]+ {lexeme=yytext(); return OctalFlotante;}
[-+]?0[xX][0-9a-fA-F]+ {lexeme=yytext(); return Hexadecimal;}
[-+]?0[xX][0-9a-fA-F]+\.[0-9a-fA-F]+ {lexeme=yytext(); return HexadecimalFlotante;}
 . {return ERROR;}