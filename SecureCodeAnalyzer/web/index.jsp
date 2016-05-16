<%-- 
    Document   : index
    Created on : May 9, 2016, 6:52:37 PM
    Author     : laura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/style.css">
        <link rel="stylesheet" href="styles/github-gist.css">
        <script src="javascript/loadcode.js" type="text/javascript"></script>
        <script src="javascript/highlight.pack.js" type="text/javascript"></script>        
        <script>hljs.initHighlightingOnLoad();</script>

        <!-- Text Area syntax highlight -->
        <link rel="stylesheet" href="codemirror/codemirror.css">
        <link rel="stylesheet" href="codemirror/neo.css">
        <script src="codemirror/codemirror.js"></script>
        <script src="codemirror/clike.js"></script>

        <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
        <title>Secure Code Analyzer</title>
    </head>

    <body>
    <center>
        <h1>Secure Code Analyzer</h1>
    </center>

    <div class="source">        
        Copia aquí el código fuente:
        <br>
        <br>
        <form method="post" enctype="multipart/form-data" action="SourceCodeServlet" id="sourcecodeform">
            <textarea name="sourcecode" id="java-code">
${requestScope.utilOutput != null ? requestScope.utilOutput : "System.out.print(\"Aqui aparecera el codigo a analizar\");"}
            </textarea>
            <center>
                <br>
                <div class="fileUpload button">
                    <span>O sube un archivo</span>
                    <input type="file" name="sourcefile" class="upload" onchange="readSingleFile(this)">
                </div>
                <br>

                <input type="submit" class="button" value="Analizar código">
            </center>
        </form>
    </div>

    <div class="result">
        <form method="post" action="" id="errorcodeform">
            <div class="myBox">
                <pre>
                <code class="java">${requestScope.utilOutput != null ? requestScope.utilOutput : "System.out.print(\"Aqui aparecera el codigo vulnerable\");"}</code>
                </pre>
            </div>
            <center>
                <input type="submit" class="button" value="Mejorar código">
            </center>
        </form>
        <form method="post" action="" id="errorcodeform">
            <div class="myBox">
                <pre>
                    <code class="java">${requestScope.utilOutput != null ? requestScope.utilOutput : "System.out.print(\"Aqui aparecera el codigo mejorado\");"}</code>
                </pre>
            </div>
            <center>
                <input type="submit" class="button" value="Siguiente sugerencia">
            </center>
        </form>          
    </div>
    <script>
        var javaEditor = CodeMirror.fromTextArea(document.getElementById("java-code"), {
            height: 1000,
            lineNumbers: true,
            matchBrackets: true,
            mode: "text/x-java",
            theme: "neo" 
        });
    </script>
</body>
</html>
