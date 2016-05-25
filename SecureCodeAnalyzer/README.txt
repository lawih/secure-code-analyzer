SecureCodeAnalyzer	Versión 1.0   25-05-2016 

DESCRIPCIÓN
----------------------------------------------------------------
El proyecto analiza código fuente en Java con el fin de detectar fragmentos de código inseguro.
Usando las reglas de SEI CERT es posible dar sugerencias  sobre mejoras en el código.

----------------------------------------------------------------

NOTAS DE USO GENERAL
----------------------------------------------------------------
— El programa fue desarrollado en Netbeans IDE.
— El programa fue ejecutado en Elementary OS, una distribución de Linux basada en Ubuntu.
— Para acceder a cada comando se debe digitar la opción seguida de la tecla ENTER.
— Al finalizar la acción el programa continuará después de haberse oprimido cualquier tecla.
— Al eliminar o consultar un registro, el programa muestra una lista de ID's disponibles.
— La búsqueda de registros se realiza por el nombre del perro, y sólo se obtendran los registros que coincidan exactamente con la cadena ingresada.
----------------------------------------------------------------

MANUAL DE EJECUCIÓN
-------------------------------------
Se requiere de Java EE para ejecutar el programa.
Netbeans IDE cuenta con Java EE y se puede descargar en: https://netbeans.org/downloads/

Al abrir el proyecto en Netbeans IDE, se mostraran las carpetas:
- Web Pages
- Source Packages
- Test Packages
- Libraries
- Test Libraries
- Configuration Files

Se debe importar la libreria de ANTLR4, la cual se encuentra en la carpeta del proyecto SecureCodeAnalyzer.

EJECUCIÓN:
En Netbeans se debe hacer click derecho en el proyecto, seguido de la opción ejecutar.

El programa cargará el servidor de la aplicación. Es normal que tome unos minutos.
----------------------------------------------------------------

MANUAL DE USO
-------------------------------------
La aplicación se ejecutará en el navegador web.

Existen dos opciones para ingresar el código fuente:
- Escribir o copiar el código en la página web
- Cargar un archivo con el código fuente

La aplicación ofrece varios archivos de prueba, se pueden encontrar en:
..\SecureCodeAnalyzer\test\exampleCodes

Para ejecutar el análisis del código, se debe hacer click en "Analize code".

En la parte derecha de la página web aparecerá la línea con código vulnerable, si existe alguna, y se mostrará una sugerencia de mejoría.

Para mejorar el código se debe dar click en "Improve code" y la aplicación mostrará el nuevo código mejorado.

Para evaluar la siguiente sugerencia se debe dar click en "Next suggestion".
----------------------------------------------------------------

================================================================

CONTACTO

 Desarrolladores:
 — Laura Viviana Álvarez Carvajal		- lvalvarezc@unal.edu.co
 — Yeison David García Gómez			- yedgarciago@unal.edu.co
