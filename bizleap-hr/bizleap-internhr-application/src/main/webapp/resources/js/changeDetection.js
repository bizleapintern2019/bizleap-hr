var detailId = false;

function getChangeRecord(element){
	var entityChangeBoId = element.id;
    var request = new XMLHttpRequest;
    request.onreadystatechange = function() {
        if (request.readyState == 4) {
            if (request.status == 200) {
                hideLoading();
                document.getElementById("detail").innerHTML = request.responseText;

                if (detailId) {
                    detailId.className = "changeDetection";
                }
                element.className = "changeDetection active";
                detailId = element;
            }
        }
    };

    parameter = {};
    parameter["entityChangeBoId"] = entityChangeBoId;
    loading();
    request.open("GET", "getChangeRecord?input="
        + JSON.stringify(parameter), true);
    request.send();
}

function getEntityChangeByDate() {
    var entity = $('#changeDetectionEntity').val();
    var startDate = $('#changeDetectionStartDate').val();
    var endDate = $('#changeDetectionEndDate').val();

    if($('#changeDetectionStartDate').val() == "" || $('#changeDetectionEndDate').val() == ""){
        alert("Please Check Start Date and End Date");
    }else{
        if (startDate != '' && endDate != '') {
            document.getElementById("detail").innerHTML = "";
            var http = new XMLHttpRequest();
            http.onreadystatechange = function() {
                if (http.readyState == 4 && http.status == 200) {
                    hideLoading();
                    document.getElementById("content").innerHTML = http.responseText;
                }
            };
            var parameter = {};
            parameter["entity"] = entity;
            parameter["startDate"] = startDate;
            parameter["endDate"] = endDate;
            loading();
            http.open("GET", "getEntityChangeByDate?input="
                + JSON.stringify(parameter), true);
            http.send();
        }
    }
}