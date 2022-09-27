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

- Recuerden que esta es la primera etapa del proyecto del curso, por eso entre más detallado y funcional esté, más
    facilidad van a tener en el resto de las etapas del proyecto

## Documentación

```
Se espera que sea un documento donde especifique lo siguiente:
```
1. Portada, índice, introducción
2. Estrategia de Solución: Una explicación de como lograron solucionar el proyecto. Puede incluir consideraciones de
    diseño, lógica o cualquier aspecto que consideren importante para llegar a la solución.
3. Análisis de Resultados: Deberá elaborar un listado de todas y cada una de las actividades y tareas que deben
    cubrirse a nivel funcional, para cada una de ellas debe aportar el porcentaje de realización y en caso de no ser el
    100% debe justificarse.
4. Lecciones aprendidas: Debe prepararse un listado de las lecciones aprendidas producto del desarrollo de la tarea
    programada. Las lecciones aprendidas pueden ser de carácter personal y/o técnico que involucre aspectos que han
    logrado un aprendizaje en temas de investigación, desarrollo de habilidades técnicas y habilidades blandas como
    trabajo en equipo, comunicación, forma de expresar ideas, entre otros.
5. Casos de pruebas: se espera que definan claramente cada prueba, cuáles son los resultados esperados y cuáles
    fueron los resultados obtenidos. No es necesario que sean grandes, pero deben evaluar la funcionalidad completa
    del programa.
6. Manual de usuario: especificar como compilar y correr su tarea.
7. Bitácora de trabajo durante las semanas de trabajo, incluyendo verificaciones realizadas (si existieran) de consultas
    realizadas con el profesor o asistente.
8. Bibliografía y fuentes digitales utilizadas

## Aspectos Administrativos

- El desarrollo de este programa debe de realizarse en grupos de exactamente tres personas salvo acuerdo con el
    profesor. Los grupos de trabajo deben permanecer iguales para los siguientes proyectos del curso.
- El trabajo se debe de entregar el día 27 de setiembre de 202 2 antes de medianoche, enviarlo por correo o subirlo
    al tec digital.


- Referencias:
    o [http://www.jflex.de/index.html](http://www.jflex.de/index.html)
    o [http://ejercicioscpp.blogspot.com/2012/09/literal-en-](http://ejercicioscpp.blogspot.com/2012/09/literal-en-)
       c.html#:~:text=Un%20literal%20es%20cualquier%20valor,car%C3%A1cter%20y%20cadena%20de%20car
       acteres.
    o https://baulderasec.wordpress.com/programacion/cc/elementos-del-lenguaje-c/literales/literales-de-
       un-solo-caracter/


