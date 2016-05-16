function readSingleFile(input) {
    //Retrieve the first (and only!) File from the FileList object
    var f = input.files[0];
    if (!f) {
        alert("Failed to load file");
    } else {
        var r = new FileReader();
        r.onload = function (e) {
            var contents = e.target.result;
            var textArea = document.getElementById('java-code');
            //var editor = CodeMirror.fromTextArea(textArea);
            javaEditor.getDoc().setValue(contents);
        };
        r.readAsText(f);
    }
}