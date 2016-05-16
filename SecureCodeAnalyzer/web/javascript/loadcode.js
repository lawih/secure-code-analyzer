function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            document.getElementsByName("sourcecode")[0].innerHTML = e.target.result;
        };

        reader.readAsDataURL(input.files[0]);
    }
}


function readSingleFile(input) {
    //Retrieve the first (and only!) File from the FileList object
    var f = input.files[0];
    if (!f) {
        alert("Failed to load file");        
    } else {
        //alert("hello");
        var r = new FileReader();
        r.onload = function (e) {
            var contents = e.target.result;
            document.getElementsByName("sourcecode")[0].innerHTML = contents;
        };
        r.readAsText(f);
    }
}

