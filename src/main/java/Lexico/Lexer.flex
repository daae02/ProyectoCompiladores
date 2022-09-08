package Lexico;
import static Lexico.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z]+
D=[0-9]+
H=[0-9,A,B,C,D,E,F]
espacio=[ ,\t,\r,\n]+
%{
    public String lexeme;
%}
%%
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
while {lexeyytext()me=yytext(); return Reservadas;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"/*".* {/*Ignore*/}     
"," {return OperadorComa;}
";" {return OperadorPuntoComa;}
"++" {return OperadorIncremento;}
"--" {return OperadorDecremento;}
"==" {return OperadorIgualIgual;}
">=" {return OperadorMayorIgual;}
">" {return OperadorMayor;}
"?" {return OperadorPregunta;}
"<=" {return OperadorMenorIgual;}
"<" {return OperadorMenor;}
"!=" {return OperadorDiferente;}
"||" {return OperadorO;}
"&&" {return OperadorY;}
"!" {return OperadorNegacion;}
"=" {return OperadorIgual;}
"+" {return OperadorSuma;}
"-" {return OperadorResta;}
"*" {return OperadorMultiplicacion;}
"/" {return OperadorDivision;}
"%" {return OperadorModulo;}
"(" {return OperadorParentesisIz;}
")" {return OperadorParentesisDer;}
"[" {return OperadorParCuadradoIz;}
"]" {return OperadorParCuadradoDer;}
"{" {return OperadorCorcheteIz;}
"}" {return OperadorCorcheteDer;}
":" {return OperadorDoblePunto;}
"." {return OperadorPunto;}
"+=" {return OperadorSumaAsignacion;}
"-=" {return OperadorRestaAsignacion;}
"*=" {return OperadorMultAsignacion;}
"/=" {return OperadorDivAsignacion;}
"&" {return OperadorAND;}
"^" {return OperadorXOR;}
"|" {return OperadorOR;}
">>" {return OperadorDesplDer;}
"<<" {return OperadorDesplIz;}
"~" {return OperadorComplementoAUno;}
"%=" {return OperadorModuloAsignacion;}
"&=" {return OperadorANDAsignacion;}
"^=" {return OperadorXORAsignacion;}
"|=" {return OperadorORAsignacion;}
"<<=" {return OperadorDesplIzAsig;}
">>=" {return OperadorDesplDerAsig;}
"->" {return OperadorMiembroPuntero;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
 . {return ERROR;}