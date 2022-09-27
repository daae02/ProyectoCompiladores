# I.T.C.R. Compiladores & Intérpretes^

### Departamento de Computación Scanner

### Prof: Ing. Erika Marín Schumann. II Semestre del 20 22

## Motivación

Muchas de las aplicaciones que se desarrollan actualmente requieren validar los datos de entrada, ya sea de interfaz o
de archivos. Para esto, se necesita los mismos conocimientos básicos que son utilizados en la construcción de la etapa de
análisis léxico de un compilador, de ahí que sea de suma importancia tener experiencia al respecto.

## Objetivos del proyecto

- Aprender a desarrollar un scanner para un lenguaje de programación (C)
- Utilizar herramientas ya diseñadas para facilitar el diseño del scanner (JFLEX)
- Diseñar la primera etapa del compilador del curso.

## Definición general

Esta primera etapa del proyecto conocida formalmente como Scanner o Análisis Léxico es de vital importancia que sea
desarrollada con cuidado y visión hacía el futuro, ya que el resto del proyecto se basará en una buena definición del lenguaje
a compilar.
Para esta primera etapa del proyecto se debe entregar un programa que reciba un código fuente escrito en C y realice
el análisis léxico respectivo. Para esto se debe definir el lenguaje aceptado por C utilizando la herramienta JFLex. Por lo
tanto, la tarea debe ser escrita en Java.
Al finalizar el scaneo el programa deberá desplegarle al usuario el resultado del Análisis Léxico que efectuó. Se esperan
dos aspectos del resultado.

**1. Listado de errores léxicos encontrados:** El programa debe desplegar una lista de todos los errores léxicos que se
    encontraron en el código fuente. Debe desplegar la línea en la que se encontró el error. Es importante que el
    programa debe poder recuperarse del error y no desplegar los errores en cascada ni terminar de hacer el scaneo al
    encontrar el primer error.
**2. Listado de los tokens encontrados:** Durante la ejecución del análisis léxico se debe llevar el control de todos los
    tokens o palabras aceptadas que se encuentren en el código fuente. Al finalizar el análisis, el programa debe ser
    capaz de desplegar una lista con cada uno de estos tokens, el tipo de token que son y las líneas del código fuente
    donde se presentan y la cantidad de ocurrencias del token en cada línea. Los tokens que presentaron errores no
    deben ser listados. Se espera que esta lista sea lo más ordenada posible y que sea fácil de leer.
**Ej:**
    Token Tipo de Token Línea
    Hola IDENTIFICADOR 5, 7, 50(2)
    + OPERADOR 6,8,15(3),36,

## Descripción Detallada

```
Se les sugiere tomar en cuenta los siguientes aspectos para asegurar la completitud del programa.
```
- El detalle de los tipos de Token será asignado por ustedes mismos. El tipo de Token no se refiere a los tokens que
    debe aceptar el programa, ya que esté debe aceptar todos los tokens que acepta cualquier compilador de C. Pero
    el tipo de cada uno de estos puede diferir en cada tarea. Por ejemplo, el token “+” puede ser de tipo “OPERADOR”
    o “OPERADOR ADITIVO”. Sin embargo, debe haber al menos 4 grandes grupos de Tokens:

#### • IDENTIFICADORES

#### • OPERADORES

#### • PALABRAS RESERVADAS

#### • LITERALES


- El programa debe identificar los comentarios y omitir todos los tokens que se encuentran dentro de ellos. Ya que se
    trata de un compilador de C, no se necesita que reconozca comentarios anidados. Debe reconocer comentarios de
    bloque y de línea. (/* */ y //)
- Los identificadores son palabras que representan constantes, variables, tipos de datos, funciones y algunos otros
    datos. Un identificador es una secuencia de caracteres, que inicia con una letra, no tienen espacios ni símbolos: &,
    !, *, etc. No tiene tildes y no es alguna palabra reservada. El lenguaje es CaseSensitive
- Deben permitirse todas las palabras reservadas de C:

```
auto,break,case,char,const,continue,default,do,double,else,enum,extern,float,
for,goto,if,int,long,register,return,short,signed,sizeof,static,struct,switch,
typedef,union,unsigned,void,volatile,while
```
- Los literales deben permitir número enteros, números flotantes, caracteres y strings. Los números se deben aceptar
    con cualquier formato aceptado por C (Octal, Hexadecimal, flotante con exponente)
- Debe aceptar todos los operadores aceptados por C. A continuación, se detalla una lista con muchos de los
    operadores. Se recomienda consultar si falta alguno:

"," ";" "++" "--" "==" ">=" ">" "?" "<=" "<" "!=" "||" "&&" "!"
"=" "+" "-" "*" "/" "%" "(" ")" "[" "]" "{" "}" ":" "."
"+=" "-=" "*=" "/=" "&" "^" "|" ">>" "<<" "~" "%=" "&=" "^=" "|="
"<<=" ">>=" "->"

