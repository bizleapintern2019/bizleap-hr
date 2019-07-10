var temDate="03/2016";
function viewSalary(){
	var empId=document.getElementById("detail-employee-boId").innerHTML;
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				hideLoading();
				document.getElementById("dialog-temp").innerHTML=request.responseText;
				document.getElementById("dialog-temp").className="dialog";		
				document.getElementById("salary-emp-name").innerHTML=document.getElementById("detail-employee-name").innerHTML;
				document.getElementById("salary-emp-rank").innerHTML=document.getElementById("detail-employee-position").innerHTML;
				//document.getElementById("salary-emp-location").innerHTML=document.getElementById("detail-employee-location").innerHTML;			
					
				 $("#salary-date").datepicker({
								dateFormat: 'mm/yy',
								changeMonth: true,
								changeYear: true,
								showButtonPanel: true,

								onClose: function(dateText, inst) {
									var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
									var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
									$(this).val($.datepicker.formatDate('mm/yy', new Date(year, month, 1)));
								}
					});

					$("#salary-date").focus(function () {
						$(".ui-datepicker-calendar").hide();
						$("#ui-datepicker-div").position({
							my: "center top",
							at: "center bottom",
							of: $(this)
						});
					});	
					
			}else{
				alert("Load view Salary form error.Please try again. Error code is "+request.status);
			hideLoading();
			}
		}
	};
	var parameter={};
	parameter["empId"]=empId;
	request.open("GET","viewSalary?input="+JSON.stringify(parameter),true);
	request.send(null);
}

function OpenSalaryByLocation(){
	var locationId=document.getElementById("detail-location-boId").innerHTML;
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				hideLoading();
				document.getElementById("dialog-temp").innerHTML=request.responseText;
				document.getElementById("dialog-temp").className="dialog";		
				document.getElementById("location-salary-name").innerHTML=document.getElementById("detail-location-name").innerHTML;
				document.getElementById("location-salary-Id").innerHTML=document.getElementById("detail-location-boId").innerHTML;
				$("#location-salary-date").datepicker({
								dateFormat: 'mm/yy',
								changeMonth: true,
								changeYear: true,
								showButtonPanel: true,

								onClose: function(dateText, inst) {
									var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
									var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
									$(this).val($.datepicker.formatDate('mm/yy', new Date(year, month, 1)));
								}
					});

				$("#location-salary-date").focus(function () {
						$(".ui-datepicker-calendar").hide();
						$("#ui-datepicker-div").position({
							my: "center top",
							at: "center bottom",
							of: $(this)});
					});	
					
			}else{
				alert("Load view Salary form error.Please try again. Error code is "+request.status);
			}
		}
	};
	request.open("GET","openSalaryByLocation",true);
	request.send(null);
}

function viewSalaryByLocation(){
	var locationId=document.getElementById("detail-location-boId").innerHTML;
	var date =document.getElementById("location-salary-date").value;
	var back=document.getElementById("BACK");
	console.log("Back: "+back);
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
			hideLoading();
				document.getElementById("dialog-temp").innerHTML=request.responseText;
				document.getElementById("dialog-temp").className="dialog";
				document.getElementById("location-data").className="hide";	
				//document.getElementById("location-detail-emplployeedata").className="show";
				if(back==null){
				document.getElementById("location-salary--selected-date").innerHTML=date;
				}
				else{
					document.getElementById("location-salary--selected-date").innerHTML=temDate;
				}
				
			}else{
				alert("Load view Salary form error.Please try again. Error code is "+request.status);
				hideLoading();
			}
		}
	};
	var parameter={};
	parameter["locationId"]=locationId;
	if(back==null){
		if(date==''){
			alert('Please fill date!');		
			return false;
		}
		parameter["date"]=date;
		temDate=date;
	}else {
		console.log("TemDate: "+temDate);
		parameter["date"]=temDate;
	}
	loading();
	request.open("GET","viewSalaryByLocation?input="+JSON.stringify(parameter),true);
	request.send(null);
}
function saveSalaryByLocation(){
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				hideLoading();
				console.log(request.responseText);
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				
			}else{
				alert("Load view Salary form error.Please try again. Error code is "+request.status);
				hideLoading();
			}
		}
	};
	
	request.open("GET","saveSalaryListByLocation",true);
	request.send(null);
}


function closeViewSalary(){
	document.getElementById("dialog-temp").attributes["class"].value = "hide";
}
function deleteEmployeeRow(deleteRow){
	try{
		var table=document.getElementById("location-salary-emp-table");
		var rowCount=table.rows.length;
		for(var i=1;i<rowCount;i++){
			var  empId= table.rows[i].cells[1].childNodes[0].id;
			if(empId==deleteRow.id)
				break;
		}
		table.deleteRow(i);
		}catch(e){
		alert(e);
	}
}
function deleteSalary(){
	var date = document.getElementById("location-salary--selected-date").innerHTML;
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				alert("Successfully Salary Deleted");
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				hideLoading();	
			}else{
				alert("Load delete Salary form error.Please try again. Error code is "+request.status);
				hideLoading();	
			}
		}
	};
	parameter["locationId"]=locationId;
	parameter["date"]=date;
	loading();	
	
	request.open("GET","makeDeleteSalary?input="+JSON.stringify(parameter),true);
	request.send(null);		
}

function calculateSalarybyLocation(){
	var date = document.getElementById("location-salary--selected-date").innerHTML;
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
            hideLoading();			
				document.getElementById("dialog-temp").innerHTML=request.responseText;
				document.getElementById("dialog-temp").className="dialog";
				document.getElementById("location-detail-salaryList").className="show";	
				document.getElementById("location-data").className="hide";					
				document.getElementById("location-salary--selected-date").innerHTML=date;			
				document.getElementById("location-salary-name").innerHTML=document.getElementById("detail-location-name").innerHTML;
				document.getElementById("location-salary-Id").innerHTML=document.getElementById("detail-location-boId").innerHTML;
			}else{
				alert("Load transfer form error.Please try again. Error code is "+request.status);
			}
		}
	};
	var parameter={};
	var employee={}
	parameter["locationId"]=locationId;
	parameter["date"]=date;
	var table=document.getElementById("location-salary-emp-table");
	var rowCount=table.rows.length;
	if(rowCount==2){
		alert("There is no employee.");
		return;
	}
	for(var i=1;i<rowCount-1;i++){
		var  empId= table.rows[i].cells[1].childNodes[0].id;
		var requirement=[];
		requirement.push(table.rows[i].cells[3].childNodes[0].value.trim()?table.rows[i].cells[3].childNodes[0].value:"0");
		requirement.push(table.rows[i].cells[4].childNodes[0].value.trim()?table.rows[i].cells[4].childNodes[0].value:"0");
		requirement.push($(table.rows[i].cells[5].childNodes[0]).is(":checked")?"1":"0");
		requirement.push($(table.rows[i].cells[6].childNodes[0]).is(":checked")?"1":"0");
		requirement.push(table.rows[i].cells[7].childNodes[0].value.trim()?table.rows[i].cells[7].childNodes[0].value:"0");
		requirement.push(table.rows[i].cells[8].childNodes[0].value.trim()?table.rows[i].cells[8].childNodes[0].value:"0");
		requirement.push(table.rows[i].cells[9].childNodes[0].value.trim()?table.rows[i].cells[9].childNodes[0].value:"0");
		requirement.push(table.rows[i].cells[10].childNodes[0].value.trim()?table.rows[i].cells[10].childNodes[0].value:"0");		
		requirement.push(table.rows[i].cells[11].childNodes[0].value.trim()?table.rows[i].cells[11].childNodes[0].value:"0");	
		requirement.push(table.rows[i].cells[12].childNodes[0].value.trim()?table.rows[i].cells[12].childNodes[0].value:"0");
		requirement.push(table.rows[i].cells[13].childNodes[0].value.trim()?table.rows[i].cells[13].childNodes[0].value:"0");
		requirement.push(table.rows[i].cells[14].childNodes[0].value.trim()?table.rows[i].cells[14].childNodes[0].value:"0");
		requirement.push(table.rows[i].cells[15].childNodes[0].value.trim()?table.rows[i].cells[15].childNodes[0].value:"0");
		requirement.push(table.rows[i].cells[16].childNodes[0].value.trim()?table.rows[i].cells[16].childNodes[0].value:"0");
		requirement.push(table.rows[i].cells[17].childNodes[0].value.trim()?table.rows[i].cells[17].childNodes[0].value:"0");
		requirement.push(table.rows[i].cells[18].childNodes[0].value.trim()?table.rows[i].cells[18].childNodes[0].value:"0");
		requirement.push(table.rows[i].cells[19].childNodes[0].value.trim()?table.rows[i].cells[19].childNodes[0].value:"0");
		employee[empId]=requirement;
	}
	parameter["employeeList"]=employee;
	loading();
	request.open("GET","calculateSalarybyLocation?input="+JSON.stringify(parameter),true);
	request.send(null);
}
function expandSalary(element){
	element.value="-";
	element.attributes["onclick"].value="lessSalary(this);";
	$("#location-salary-detail"+element.id).show();		
	
}
function lessSalary(element){
	element.value="+";
	element.attributes["onclick"].value="expandSalary(this);";
	$("#location-salary-detail"+element.id).hide();
		
}

