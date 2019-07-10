function showAboutUs() {
    var request = new XMLHttpRequest;
    $('#workspace').addClass("myFilter");
    request.onreadystatechange = function() {
        if (request.readyState == 4) {
            if (request.status == 200) {
                hideLoading();
                document.getElementById("dialog-temp").innerHTML = request.responseText;
                document.getElementById("dialog-temp").attributes["class"].value = "dialog";
            }
        }
    };
        loading();
        request.open("GET", "showAboutUs/", true);
        request.send();
}


function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}