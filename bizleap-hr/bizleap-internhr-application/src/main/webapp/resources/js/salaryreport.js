function reportSalary1(element,description){
	if(tempParameter!="" && tempParameter!=undefined){
		var previous =tempParameter.element;
		previous.className="";
	}
	var parameter={};
	parameter.description=description;
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				hideLoading();
			}else{
				alert("Load Report Form error.Please try again. Error code is "+request.status);
			}
		}
	};
	parameter.element=element;
	element.className="search-active";
	tempParameter=parameter;
	var index = document.getElementById("porderlocationname").selectedIndex;
	parameter["value"] =  document.getElementById("porderlocationname").options[index].value;
	parameter["name"]=document.getElementById("porderlocationname").options[index].text;
	parameter["pordersearchdate"]=document.getElementById("porderStartDate").value;
	parameter["porderenddate"]=document.getElementById("porderEndDate").value;
	parameter["description"]=description;
	
	
	
	if(description =="reportPurcahseOrder"){
		
		//request.open("GET","report?input="+JSON.stringify(parameter),true);
		window.open("report/SaleReport.xlsx?input="
				+ JSON.stringify(parameter),"_blank");
	}
	else{
		var dateStr = parameter["location-salary-date"];
		var res = dateStr.replace("/", "_"); 
		
		window.open("purchaseOrderReport/SalaryReport_"+parameter["name"]+"_"+res+".xlsx?input="
				+ JSON.stringify(parameter),"_blank");
		
		//request.open("GET","purchaseOrderReport?input="+JSON.stringify(parameter),true);
	}
	
	//request.send();
	//loading();
	
}
