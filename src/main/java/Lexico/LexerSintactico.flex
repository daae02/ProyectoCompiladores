package Lexico;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
$full
%line
%char
%unicode
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
auto {return new Symbol(sym.Auto, yyline, yychar, yytext());}
break {return new Symbol(sym.Break, yyline, yychar, yytext());}
case {return new Symbol(sym.Case, yyline, yychar, yytext());}
char {return new Symbol(sym.Char, yyline, yychar, yytext());}
const {return new Symbol(sym.Const, yyline, yychar, yytext());}
continue {return new Symbol(sym.Continue, yyline, yychar, yytext());}
default {return new Symbol(sym.Default, yyline, yychar, yytext());}
do {return new Symbol(sym.Do, yyline, yychar, yytext());}
double {return new Symbol(sym.Double, yyline, yychar, yytext());}
else {return new Symbol(sym.Else, yyline, yychar, yytext());}
enum {return new Symbol(sym.Enum, yyline, yychar, yytext());}
extern {return new Symbol(sym.Extern, yyline, yychar, yytext());}
float {return new Symbol(sym.Float, yyline, yychar, yytext());}
for {return new Symbol(sym.For, yyline, yychar, yytext());}
goto {return new Symbol(sym.Goto, yyline, yychar, yytext());}
if {return new Symbol(sym.If, yyline, yychar, yytext());}
int {return new Symbol(sym.Int, yyline, yychar, yytext());}
long {return new Symbol(sym.Long, yyline, yychar, yytext());}
register {return new Symbol(sym.Register, yyline, yychar, yytext());}
return {return new Symbol(sym.Return, yyline, yychar, yytext());}
short {return new Symbol(sym.Short, yyline, yychar, yytext());}
signed {return new Symbol(sym.Signed, yyline, yychar, yytext());}
sizeof {return new Symbol(sym.Sizeof, yyline, yychar, yytext());}
static {return new Symbol(sym.Static, yyline, yychar, yytext());}
struct {return new Symbol(sym.Struct, yyline, yychar, yytext());}
switch {return new Symbol(sym.Switch, yyline, yychar, yytext());}
typedef {return new Symbol(sym.Typedef, yyline, yychar, yytext());}
union {return new Symbol(sym.Union, yyline, yychar, yytext());}
unsigned {return new Symbol(sym.Unsigned, yyline, yychar, yytext());}
void {return new Symbol(sym.Void, yyline, yychar, yytext());}
volatile {return new Symbol(sym.Volatile, yyline, yychar, yytext());}
while {return new Symbol(sym.While, yyline, yychar, yytext());}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"/*".* {/*Ignore*/}     
";" {return new Symbol(sym.OperadorPuntoComa, yyline, yychar, yytext());}
"," {return new Symbol(sym.OperadorComa, yyline, yychar, yytext());}
"++" {return new Symbol(sym.OperadorIncremento, yyline, yychar, yytext());}
"--" {return new Symbol(sym.OperadorDecremento, yyline, yychar, yytext());}
"==" {return new Symbol(sym.OperadorIgualIgual, yyline, yychar, yytext());}

">=" {return new Symbol(sym.OperadorBooleano, yyline, yychar, yytext());}
">" {return new Symbol(sym.OperadorBooleano, yyline, yychar, yytext());}
"<=" {return new Symbol(sym.OperadorBooleano, yyline, yychar, yytext());}
"<" {return new Symbol(sym.OperadorBooleano, yyline, yychar, yytext());}
"!=" {return new Symbol(sym.OperadorBooleano, yyline, yychar, yytext());}
"||" {return new Symbol(sym.OperadorBooleano, yyline, yychar, yytext());}
"&&" {return new Symbol(sym.OperadorBooleano, yyline, yychar, yytext());}
"!" {return new Symbol(sym.OperadorBooleano, yyline, yychar, yytext());}
"^" {return new Symbol(sym.OperadorBooleano, yyline, yychar, yytext());}
"|" {return new Symbol(sym.OperadorBooleano, yyline, yychar, yytext());}

"?" {return new Symbol(sym.OperadorPregunta, yyline, yychar, yytext());}
"=" {return new Symbol(sym.OperadorIgual, yyline, yychar, yytext());}
"+" {return new Symbol(sym.OperadorSuma, yyline, yychar, yytext());}
"-" {return new Symbol(sym.OperadorResta, yyline, yychar, yytext());}
"*" {return new Symbol(sym.OperadorMultiplicacion, yyline, yychar, yytext());}
"/" {return new Symbol(sym.OperadorDivision, yyline, yychar, yytext());}
"%" {return new Symbol(sym.OperadorModulo, yyline, yychar, yytext());}
"(" {return new Symbol(sym.OperadorParentesisIz, yyline, yychar, yytext());}
")" {return new Symbol(sym.OperadorParentesisDer, yyline, yychar, yytext());}
"[" {return new Symbol(sym.OperadorParCuadradoIz, yyline, yychar, yytext());}
"]" {return new Symbol(sym.OperadorParCuadradoDer, yyline, yychar, yytext());}
"{" {return new Symbol(sym.OperadorCorcheteIz, yyline, yychar, yytext());}
"}" {return new Symbol(sym.OperadorCorcheteDer, yyline, yychar, yytext());}
":" {return new Symbol(sym.OperadorDoblePunto, yyline, yychar, yytext());}
"." {return new Symbol(sym.OperadorPunto, yyline, yychar, yytext());}
"+=" {return new Symbol(sym.OperadorSumaAsignacion, yyline, yychar, yytext());}
"-=" {return new Symbol(sym.OperadorRestaAsignacion, yyline, yychar, yytext());}
"*=" {return new Symbol(sym.OperadorMultAsignacion, yyline, yychar, yytext());}
"/=" {return new Symbol(sym.OperadorDivAsignacion, yyline, yychar, yytext());}
"&" {return new Symbol(sym.OperadorDireccion, yyline, yychar, yytext());}
">>" {return new Symbol(sym.OperadorDesplDer, yyline, yychar, yytext());}
"<<" {return new Symbol(sym.OperadorDesIz, yyline, yychar, yytext());}
"~" {return new Symbol(sym.OperadorComplementoAUno, yyline, yychar, yytext());}
"%=" {return new Symbol(sym.OperadorModuloAsignacion, yyline, yychar, yytext());}
"&=" {return new Symbol(sym.OperadorANDAsignacion, yyline, yychar, yytext());}
"^=" {return new Symbol(sym.OperadorXORAsignacion, yyline, yychar, yytext());}
"|=" {return new Symbol(sym.OperadorORAsignacion, yyline, yychar, yytext());}
"<<=" {return new Symbol(sym.OperadorDesplIzAsig, yyline, yychar, yytext());}
">>=" {return new Symbol(sym.OperadorDesplDerAsig, yyline, yychar, yytext());}
"->" {return new Symbol(sym.OperadorMiembroPuntero, yyline, yychar, yytext());}
"#" {return new Symbol(sym.OperadorGato, yyline, yychar, yytext());}

\"(\\\"|[^\"])+\" | \"\" {lexeme=yytext(); return Hilera;}
'[^']' | '\\[^']+' {lexeme=yytext(); return Caracter;}

{identificador} {lexeme=yytext(); return Identificador;}
{entero} {lexeme=yytext(); return Entero;}
{flotante} {lexeme=yytext(); return Flotante;}
{flotanteExponente} {lexeme=yytext(); return FlotanteExponente;}
{octal} {lexeme=yytext(); return Octal;}
{octalFlotante} {lexeme=yytext(); return OctalFlotante;}
{hexadecimal} {lexeme=yytext(); return Hexadecimal;}
{hexadecimalFlotante} {lexeme=yytext(); return HexadecimalFlotante;}
{numero}{identificador} {lexeme = yytext(); return ERROR;}

[^] {lexeme = yytext(); return ERROR;}