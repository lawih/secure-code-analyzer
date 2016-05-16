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
        <script src="javascript/codepress.js" type="text/javascript"></script>
        <script src="javascript/highlight.pack.js" type="text/javascript"></script>        
        <script>hljs.initHighlightingOnLoad();</script>
        <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
        <title>Secure Code Analyzer</title>
    </head>

    <body>
        <center>
        <h1>Secure Code Analyzer</h1>
        <div class="source">        
            <form method="post" enctype="multipart/form-data" action="SourceCodeServlet" id="sourcecodeform">
                <textarea rows="28" cols="50" name="sourcecode" class="codepress java linenumbers-off">
${requestScope.utilOutput != null ? requestScope.utilOutput : "Copia aquí el código fuente."}
                </textarea>
                <br>
                <div class="fileUpload button">
                    <span>O sube un archivo</span>
                    <input type="file" name="sourcefile" class="upload" onchange="readSingleFile(this)">
                </div>
                <br>
                <input type="submit" class="button" value="Analizar código">
            </form>
        </div>
        </center>    

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
    </body>
</html>
