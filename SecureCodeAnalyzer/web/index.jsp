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
        Copy the source code here:
        <br>
        <br>
        <form method="post" enctype="multipart/form-data" action="SourceCodeServlet" id="sourcecodeform">
            <textarea name="sourcecode" id="java-code">
                ${requestScope.lastInput != null ? requestScope.lastInput : "System.out.print(\"Code to analize will appear here\");"}
            </textarea>
            <center>
                <br>
                <div class="fileUpload button">
                    <span>Or you can upload a file</span>
                    <input type="file" name="sourcefile" class="upload" onchange="readSingleFile(this)">
                </div>
                <br>

                <input type="submit" class="button" value="Analize code">
            </center>
        </form>
    </div>

    <div class="result">
        <form method="post" action="" id="errorcodeform">
            <h3>
                <p>${requestScope.suggestionType}</p></h3>
            <p>${requestScope.suggestionTypeURL == null ?  
                 "You can learn about secure code <a target=\"_blank\" href=\"https://www.securecoding.cert.org/confluence/display/java/2+Rules\">here</a>." : requestScope.suggestionTypeURL}
            </p>
            <p>${requestScope.suggestionLine}</p></h3>
            
            <div class="myBox">                
                <pre>
                <code class="java">${requestScope.wrongcode != null ? requestScope.wrongcode : "System.out.print(\"Vulnerable code will appear here\");"}</code>
                </pre>
            </div>
               
            <br>
            ${requestScope.recomendation != null ? "Consider changing your code to:" : "<br>"}
            <div class="myBox">
                <pre>
                    <code class="java">${requestScope.recomendation != null ? requestScope.recomendation : "System.out.print(\"Secure code will appear here\");"}</code>
                </pre>
            </div>
            <center>
                <input type="submit" class="button" value="Improve code">
            </center>
        </form>
        <form method="post" action="" id="errorcodeform">

            <center>
                <input type="submit" class="button" value="Next suggestion">
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
