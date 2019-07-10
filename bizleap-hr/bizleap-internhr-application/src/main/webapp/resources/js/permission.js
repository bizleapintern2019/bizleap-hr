var roles ={};

function addNewPermission(){
    var isChange = document.getElementById("isChange").innerHTML;
    if(isChange == "yes"){
        alert("Please save changes before adding new permission!");
        return;
    }

    var request = new XMLHttpRequest;
    request.onreadystatechange = function() {
        if (request.readyState == 4) {
            if (request.status == 200) {
                hideLoading();
                var role = parseJson(request.responseText);
                roles[role.boId] = role;
                var select = $("#permission-role");
                var option = document.createElement("option")
                select.append(option);
                option = $(option);
                option.text(role.name+"( "+role.boId+" )");
                option.attr("value",role.boId);
                option.attr("selected","selected");
                option.attr("class","green");

                var role = $("#permission-role option:selected").text();
                $("#role-description").val(role);

            } else {
                alert("Error return :" + request.status);
            }
        }
    };

    var parameter = {};
    loading();
    request.open("POST", "addPermission?input=" + JSON.stringify(parameter), true);
    request.send();
}

function deletePermission(){
    // console.log("in deletePermission..");

    if(!confirm("Are you sure you want to delete?"))
        return;

    var request = new XMLHttpRequest;
    request.onreadystatechange = function() {
        if (request.readyState == 4) {
            if (request.status == 200) {
                hideLoading();
                var result = parseJson(request.responseText);

                if(result.status){
                    $("#permission-role option:selected").detach();
                }else{
                   alert("Error: "+result.message);
                }
            } else {
                alert("Error return :" + request.status);
            }
        }
    };

    var permission = {};
    permission.roleId = $("#permission-role").val();
    loading();
    request.open("DELETE", "deletePermission?input=" + JSON.stringify(permission), true);
    request.send();
}


function savePermission(){
    var request = new XMLHttpRequest;
    request.onreadystatechange = function() {
        if (request.readyState == 4) {
            if (request.status == 200) {
                hideLoading();
                var response = request.responseText;
                document.getElementById("dialog-temp").attributes["class"].value = "hide";
                $('#workspace').removeClass("myFilter");
            } else {
                alert("Error return :" + request.status);
            }
        }
    };

    var permission = {};
    permission.boId = $("#permission-role").val();
    permission.description = $("#role-description").val();
    permission.location = collectPermission($("tr[name=location]"));
    permission.employee = collectPermission($("tr[name=employee]"));
    permission.customer = collectPermission($("tr[name=customer]"));
    permission.supplier = collectPermission($("tr[name=supplier]"));
    permission.product = collectPermission($("tr[name=product]"));
    permission.risk = collectPermission($("tr[name=risk]"));
    permission.purchaseOrder = collectPermission($("tr[name=purchaseOrder]"));
    permission.report = collectPermission($("tr[name=report]"));
    permission.changeDetection = collectPermission($("tr[name=changeDetection]"));

    loading();
    request.open("POST", "savePermission?input=" + JSON.stringify(permission), true);
    request.send();
}

function collectPermission(userPermission) {
    var permission = {};
    permission.overall = collect("overall", userPermission);
    return permission;

    function collect(type, tr) {
        var permission = {};
        permission.all = $("[name=all]", tr).prop("checked") || false;
        permission.edit = $("[name=edit]", tr).prop("checked") || false;
        permission.add = $("[name=add]", tr).prop("checked") || false;
        permission.delete = $("[name=delete]", tr).prop("checked") || false;
        permission.view = $("[name=view]", tr).prop("checked") || false;
        permission.self = $("[name=self]", tr).prop("checked") || false;
        console.log(permission);
        return permission;
    }
}

function getPermissionForm(){
    // console.log("in get permission form");
    var request = new XMLHttpRequest;
    $('#workspace').addClass("myFilter");
    request.onreadystatechange = function() {
        if (request.readyState == 4) {
            if (request.status == 200) {
                hideLoading();
                document.getElementById("dialog-temp").innerHTML = request.responseText;
                document.getElementById("dialog-temp").attributes["class"].value = "dialog";
                if (Object.keys(errors).length > 0)
                    errors = {};

                roles = parseJson($("#roleJson").text());
                var selectedRole = $("#permission-role").val();
                var role = roles[selectedRole];
                updatePermission(role);

                $("#permission-role").on("change", onChange)
                function onChange() {
                    var element = $("#permission-role").val();
                    updatePermission(roles[element]);
                }

            } else {
                alert("Load Permission Form error.Please try again. Error code is "
                    + request.status);
            }
        }
    };
    loading();
    var parameter = {};
    request.open("GET", "getPermissionForm?input="
        + JSON.stringify(parameter), true);
    request.send();
}


function removeOperation(operationId,element){
    document.getElementById(operationId).textContent = "";
    document.getElementById(operationId).classList.remove("operations");
    element.className = "hide";
}

function parseJson(string) {
    var result = JSON.parse(string);
    if (typeof result == "string")
        result = JSON.parse(result);
    return result;
}

function updatePermission(role) {
    var allPermissionTable = $("#permission-parent");
    $("#role-description").val(role.description);
    for (var type in role.permissions) {
        // console.log("Type: "+type);
        var overall = $("tr[name="+type+"]");
        $("[name=all]", overall).prop("checked", role.permissions[type].overall.all);
        $("[name=edit]", overall).prop("checked", role.permissions[type].overall.edit);
        $("[name=add]", overall).prop("checked", role.permissions[type].overall.add);
        $("[name=view]", overall).prop("checked", role.permissions[type].overall.view);
        $("[name=delete]", overall).prop("checked", role.permissions[type].overall.delete);
        $("[name=self]", overall).prop("checked", role.permissions[type].overall.self);
    }

    // $("#permission-location input[name=location]").prop("checked",false);
    // for (var index in role.location) {
    //     $("#permission-location input[name=location]").each(function (innerIndex, element) {
    //         element = $(element);
    //         if (element.val() == role.location[index]) {
    //             element.prop("checked", true);
    //         }
    //     });
    // }
    // sortTable();
    //
    // function sortTable(){
    //     var tables = $("table[name=location]");
    //     tables.tablesorter();
    // }
}

function preventAddFeature(){
    $("#isChange").text("yes");
}