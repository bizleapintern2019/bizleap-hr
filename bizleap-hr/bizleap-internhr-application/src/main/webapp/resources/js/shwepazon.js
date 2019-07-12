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
		else if(active == "department") {
			document.getElementById("department-content").className = "show";
		}
		else if(active == "job") {
			document.getElementById("job-content").className = "show";
		}
		else if(active == "position") {
			document.getElementById("position-content").className = "show";
		}
		else if(active == "employee") {
			document.getElementById("employee-content").className = "show";
		}
		else if(active == "address") {
			document.getElementById("address-content").className = "show";
		}	
	}
		request.open("GET", active + "/all", true);
	
	request.send();
}

function loadDepartment() {
	var content = document.getElementById("content");
	content.innerHTML = "";
	var request = new XMLHttpRequest;

	request.onreadystatechange = function() {
		if(request.readyState != 4)
			return;
		if(request.status != 200) {
			alert("Error return " + request.status);
			return;
		}
		
		var departmentJSON = JSON.parse(request.responseText);
		departmentJSON.forEach(data => {
			var department = document.createElement("div");
			department.setAttribute("id","department-content");
			var br = document.createElement("br");
			var name = document.createElement("span");
			var boId = document.createElement("span");
			name.innerHTML = data.name;
			boId.innerHTML = data.boId;
			department.append(name);
			department.append(br);
			department.append(boId);
			content.append(department);

		});

		document.getElementById("department-content").onmouseover = function(){
				this.style.background = "grey";
		}

	}
	request.open("GET", "departments/list", true);
	request.send();
}

var detailId;
function getDetail(element, entityType) {

	var request = new XMLHttpRequest;

	request.onreadystatechange = function() {
		if(request.readyState != 4)
			return;
		if(request.status != 200) {
			alert("Error return " + request.status);
			return;
		}
		document.getElementById("detail").innerHTML = request.responseText;
		document.getElementById("detail-table").className = "show";
		if(entityType == "LOCATION") {
			document.getElementById("location-detail-table").className = "show";
		}
		else if(entityType == "DEPARTMENT") {
			document.getElementById("department-detail-table").className = "show";
		}
		else if(entityType == "JOB") {
			document.getElementById("job-detail-table").className = "show";
		}
		else if(entityType == "EMPLOYEE") {
			document.getElementById("employee-detail-table").className = "show";
		}
	}
	detailId = element;
	var boId = element.id;
	parameter = {};
	parameter["boId"] = boId;
	request.open("GET", "detail/"+ entityType + "?input=" + JSON.stringify(parameter), true);
	request.send();
}

function addNewLocation() {
	document.getElementById("locationForm").className = "show";
	document.getElementById("location-detail-table").className = "hide";
}

function saveLocation() {
	var locationBoid = document.getElementById("location-boid").value;
	var locationName = document.getElementById("location-name").value;
	
	var request = new XMLHttpRequest;
	
	request.onreadystatechange = function() {
		if(request.readyState != 4)
			return;
		if(request.status != 200) {
			alert("Error return " + request.status);
			return;
		}
		alert("Successfully saved!");
		loadAction("", "location");
		document.getElementById("locationForm").className = "hide";
	}
	
	parameter = {};
	parameter["boId"] = locationBoid;
	parameter["name"] = locationName;
	request.open("POST", "locations/new", true);
	request.setRequestHeader('Content-Type', 'application/json');
	request.send(JSON.stringify(parameter));
}

function addNewDepartment() {
document.getElementById("department-detail-table").className = "hide";
}

function addNewJob() {
document.getElementById("job-detail-table").className = "hide";
}

function addNewEmployee() {
document.getElementById("employee-detail-table").className = "hide";
}