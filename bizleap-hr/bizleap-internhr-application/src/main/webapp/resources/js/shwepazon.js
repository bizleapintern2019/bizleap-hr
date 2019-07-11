var active = "";
function loadAction(currentElement, activeId) {
	var request = new XMLHttpRequest;
	active = activeId;

	request.onreadystatechange = function() {
		if(request.readyState != 4)
			return;
		if(request.status != 200) {
			alert("Error return " + request.status);
			return;
		}
		document.getElementById("content").innerHTML = request.responseText;
		if(active == "location") {
			document.getElementById("location-content").className = "show";
			
		}
	
	}
		request.open("GET", active + "/all", true);
	
	request.send();
}