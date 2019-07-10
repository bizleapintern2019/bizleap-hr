var searchType = 'mainwarehouse'; // default search Type
var searchId = null;
var connectionStatus = false;
var isExport = false; // Export status
var previousId;
var pageNumber=1;

var SEARCH={};
(function(){
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState!=4)
			return;
		if(request.status!=200){
			alert("Error"+request.status);
			return;
		}
		var result=request.responseText.replace("<span>","").replace("</span>","");
		SEARCH.PAGE_SIZE=parseInt(result.trim());
		Object.freeze(SEARCH);
	};
	request.open("GET","init",true);
	request.send();
})();
function expandList(object){
        var childList = object.parentNode.getElementsByTagName('ul');
        for(var j = 0; j< childList.length;j++){
            var currentState = childList[j].style.display;
            if(currentState=="none"){
                childList[j].style.display="block";
            }else{
                childList[j].style.display="none";
            }
        }
    }
function showBusyMessage(message) {
	var popUp = document.getElementById("temp");
	popUp.innerHTML = message;
	popUp.attributes["class"].value = "pop-up-error";
	setTimeout(function() {
		popUp.attributes["class"].value = "hide";
	}, 5000);
}

function loadAction(currentElement,activeId){
	if(activeId == "employee"){
		document.getElementById("search-employee-area").className="search-area";
		document.getElementById("employee-search-text").value="Employee";
		document.getElementById("search-customer-area").className="hide";
		document.getElementById("search-location-area").className="hide";
		document.getElementById("employee-content").className="hide";
		document.getElementById("customer-content").className="hide";
		document.getElementById("location-content").className="hide";
		document.getElementById("detail-table").className="hide";
		document.getElementById("search-employee-result").innerHTML="";
		document.getElementById("customer-search-text").value="";
		document.getElementById("search-customer-result").innerHTML="";
		document.getElementById("location-search-text").value="";
		document.getElementById("search-location-result").innerHTML="";
		
	}
	else if(activeId == "customer")
	{
	document.getElementById("search-customer-area").className="search-area";
	document.getElementById("customer-search-text").value="Customer";
	document.getElementById("search-employee-area").className="hide";
	document.getElementById("search-location-area").className="hide";
	document.getElementById("employee-content").className="hide";
	document.getElementById("customer-content").className="hide";
	document.getElementById("location-content").className="hide";
	document.getElementById("detail-table").className="hide";
	document.getElementById("employee-search-text").value="";
	document.getElementById("search-employee-result").innerHTML="";
	document.getElementById("search-customer-result").innerHTML="";
	document.getElementById("location-search-text").value="";
	document.getElementById("search-location-result").innerHTML="";
	}
	else {
		document.getElementById("search-location-area").className="search-area";
		document.getElementById("location-search-text").value="Location";
		document.getElementById("search-employee-area").className="hide";
		document.getElementById("search-customer-area").className="hide";
		document.getElementById("employee-content").className="hide";
		document.getElementById("customer-content").className="hide";
		document.getElementById("location-content").className="hide";
		document.getElementById("detail-table").className="hide";
		document.getElementById("employee-search-text").value="";
		document.getElementById("search-employee-result").innerHTML="";
		document.getElementById("customer-search-text").value="";
		document.getElementById("search-customer-result").innerHTML="";
		document.getElementById("search-location-result").innerHTML="";
	}
}
function showMoreEmployee(element){
	$("#show-more-employee").show(700);
	element.value="<<";
	element.attributes["onclick"].value="showLessEmployee(this);";
}
function showLessEmployee(element){
	$("#show-more-employee").hide(700);
	element.value=">>";
	element.attributes["onclick"].value="showMoreEmployee(this);";
}
function showMoreCustomer(element){
	$("#show-more-customer").show(700);
	element.value="<<";
	element.attributes["onclick"].value="showLessCustomer(this);";
}
function showLessCustomer(element){
	$("#show-more-customer").hide(700);
	element.value=">>";
	element.attributes["onclick"].value="showMoreCustomer(this);";
}
function searchEmployee(element, type, pageNumber){
	if(!previousId){
		previousId=element;
		document.getElementById("search-all-employee").className="";
		element.className="search-active";
	}else{
		previousId.className="";
		element.className="search-active";
		previousId=element;
	}
	var parameter={};
	parameter["type"]=type;
	parameter["value"]=document.getElementById("employee-search-text").value;
	if( typeof pageNumber === 'undefined' ) pageNumber=1;
	parameter["pageNumber"]=pageNumber.toString(10);
	parameter["pageSize"]=SEARCH.PAGE_SIZE.toString(10);
	
	if(type=="Joining_Date"){ 
		var searchDate=parameter["value"].trim().split("to");
		console.log(searchDate.length);
		if(searchDate.length==1){
			var parameterDate=convertDate(parameter["value"]);
			if(!parameterDate.status){
				document.getElementById("search-employee-result").innerHTML=parameterDate.error;
				return;
			}
			parameter["value"]=parameterDate["day"]+"/"+parameterDate["month"]+"/"+parameterDate["year"];
		}else if(searchDate.length==2){
			var startDate=convertDate(searchDate[0]);
			var endDate=convertDate(searchDate[1]);
			if(!startDate.status){
				document.getElementById("search-employee-result").innerHTML=startDate.error;
			}else if(!startDate.status){
				document.getElementById("search-employee-result").innerHTML=endDate.error;
			}else{
				parameter["startDate"]=startDate.startDay+"/"+startDate.startMonth+"/"+startDate.startYear;
				parameter["endDate"]=endDate.endDay+"/"+endDate.endMonth+"/"+endDate.endYear;
				parameter["type"]="Date_Range";
			}
		}else{
			document.getElementById("search-employee-result").innerHTML="Invalid input";
		}
	}
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				document.getElementById("content").innerHTML=request.responseText;
				document.getElementById("employee-content").className="show";
				//document.getElementById("employee-detail-table").className="hide";
				var totalFoundEmployee=document.getElementById("total-employee-size").innerHTML;
				displayPaginationState(totalFoundEmployee,parameter.pageNumber);
				if(totalFoundEmployee<=SEARCH.PAGE_SIZE) document.getElementById("employeePagination").className="hide";
				if(totalFoundEmployee>0)
					document.getElementById("search-employee-result").innerHTML="&nbsp;&nbsp;"+totalFoundEmployee+" employees found.";
				else
					document.getElementById("search-employee-result").innerHTML="&nbsp;&nbsp;No employee founds.";
				//document.getElementById("detail-table").className="hide";
			}else{
				showError("Search Error",request.status);
			}
		}
	};
	console.log(parameter);
	parameter.type=parameter.type.toUpperCase();
	request.open("GET","searchEmployeeByCriteria?input="+JSON.stringify(parameter),true);
	request.send(null);
}
function displayPaginationState(size,pageNumber){
	var pageCount=( Math.ceil(parseInt(size/SEARCH.PAGE_SIZE))+( size%SEARCH.PAGE_SIZE >0 ? 1 : 0) );
	pageNumber=Math.max(Math.min(pageNumber,pageCount),1);
	document.getElementById("employee-pagination-descriptor").innerHTML=pageNumber+"/"+pageCount ;
}
function employeeSearchEnd(){
	var text=previousId.attributes["onclick"].value;
	var pageCount=document.getElementById("total-employee-size").innerHTML;
	var index=total%SEARCH.SIZE ==0 ? (total/SEARCH.PAGE_SIZE-1)*SEARCH.SIZE : parseInt(total/SEARCH.PAGE_SIZEage_SIZE)*SEARCH.SIZE;
	searchEmployee(previousId,text.substring(text.indexOf("'")+1,text.lastIndexOf("'")),SEARCH.PAGE_SIZE);
	
}
function employeeSearchFirst(){
	var text=previousId.attributes["onclick"].value;
	var total=document.getElementById("total-employee-size").innerHTML;
	searchEmployee(previousId,text.substring(text.indexOf("'")+1,text.lastIndexOf("'")),0,SEARCH.SIZE);
}
function employeeSearchNext(){
	var text=previousId.attributes["onclick"].value;
	pageNumber=parseInt(document.getElementById("employee-pagination-descriptor").innerHTML.split("/")[0])+1;
	console.log(pageNumber);
	searchEmployee(previousId,text.substring(text.indexOf("'")+1,text.lastIndexOf("'")),pageNumber);
}
function employeeSearchPrevious(){
	var text=previousId.attributes["onclick"].value;
	pageNumber=(document.getElementById("employee-pagination-descriptor").innerHTML.split("/")[0]-1);
	searchEmployee(previousId,text.substring(text.indexOf("'")+1,text.lastIndexOf("'")),pageNumber);
}
var detailId=false;
function employeeDetail(element){
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				document.getElementById("detail").innerHTML=request.responseText;
				document.getElementById("detail-table").className="show";
				document.getElementById("employee-detail-table").className="show";
				if(detailId){
					detailId.className="";
					element.className="active";
				}else{
					element.className="active";
				}
				detailId=element;
			}else{
				alert("Error return :"+request.status);
			}
		}
	};
	parameter={};
	parameter["boId"]=element.id;
	request.open("GET","detailEmployee?input="+JSON.stringify(parameter),true);
	request.send(null);
}

function searchCustomer(element, type, pageNumber){
	if(!previousId){
		previousId=element;
		document.getElementById("search-all-customer").className="";
		element.className="search-active";
	}else{
		previousId.className="";
		element.className="search-active";
		previousId=element;
	}
	var parameter={};
	parameter["type"]=type;
	parameter["value"]=document.getElementById("employee-search-text").value;
	if( typeof pageNumber === 'undefined' ) pageNumber=1;
	parameter["pageNumber"]=pageNumber.toString(10);
	parameter["pageSize"]=SEARCH.PAGE_SIZE.toString(10);
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				document.getElementById("content").innerHTML=request.responseText;
				document.getElementById("customer-content").className="show";
				//document.getElementById("customer-detail-table").className="hide";
				var totalFoundCustomer=document.getElementById("total-customer-size").innerHTML;
				doCustomerPagination(totalFoundCustomer,parameter.startIndex/SEARCH.SIZE+1);
				if(totalFoundCustomer<=SEARCH.SIZE) document.getElementById("customerPagination").className="hide";
				if(totalFoundCustomer>0)
					document.getElementById("search-customer-result").innerHTML=totalFoundCustomer+" customers found.";
				else
					document.getElementById("search-customer-result").innerHTML="&nbsp;&nbsp;No customer found.";
				document.getElementById("detail-table").className="hide";
			}else{
				showError("Search Error"+request.status);
			}
		}
	};
	console.log(parameter);
	request.open("GET","searchCustomerByCriteria?input="+JSON.stringify(parameter),true);
	request.send(null);
}
function doCustomerPagination(size,currentIndex){
	var maxIndex=( (parseInt(size/SEARCH.SIZE))+( size%SEARCH.SIZE >0 ? 1 : 0) );
	if(currentIndex>maxIndex) currentIndex=maxIndex;
	if(currentIndex<1) currentIndex=1;
	document.getElementById("customer-pagination-descriptor").innerHTML=currentIndex+"/"+maxIndex ;
}
function customerSearchEnd(){
	var text=previousId.attributes["onclick"].value;
	var total=document.getElementById("total-customer-size").innerHTML;
	var index=total%SEARCH.SIZE ==0 ? (total/SEARCH.SIZE-1)*SEARCH.SIZE : parseInt(total/SEARCH.SIZE)*SEARCH.SIZE;
	searchCustomer(previousId,text.substring(text.indexOf("'")+1,text.lastIndexOf("'")),index,total);
	
}
function customerSearchFirst(){
	var text=previousId.attributes["onclick"].value;
	var total=document.getElementById("total-customer-size").innerHTML;
	searchCustomer(previousId,text.substring(text.indexOf("'")+1,text.lastIndexOf("'")),0,SEARCH.SIZE);
}
function customerSearchNext(){
	var text=previousId.attributes["onclick"].value;
	currentIndex=document.getElementById("customer-pagination-descriptor").innerHTML.split("/")[0]*SEARCH.SIZE;
	searchCustomer(previousId,text.substring(text.indexOf("'")+1,text.lastIndexOf("'")),currentIndex,currentIndex+SEARCH.SIZE);
}
function customerSearchPrevious(){
	var text=previousId.attributes["onclick"].value;
	currentIndex=(document.getElementById("customer-pagination-descriptor").innerHTML.split("/")[0]-2)*SEARCH.SIZE;
	searchCustomer(previousId,text.substring(text.indexOf("'")+1,text.lastIndexOf("'")),currentIndex,currentIndex+SEARCH.SIZE);
}
var detailId=false;
function customerDetail(element){
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				document.getElementById("detail").innerHTML=request.responseText;
				document.getElementById("detail-table").className="show";
				document.getElementById("customer-detail-table").className="show";
				if(detailId){
					detailId.className="";
					element.className="active";
				}else{
					element.className="active";
				}
				detailId=element;
			}else{
				alert("Error return :"+request.status);
			}
		}
	};
	parameter={};
	parameter["boId"]=element.id;
	request.open("GET","detailCustomer?input="+JSON.stringify(parameter),true);
	request.send(null);
}

function searchLocation(element, type, pageNumber){
	if(!previousId){
		previousId=element;
		document.getElementById("search-all-location").className="";
		element.className="search-active";
	}else{
		previousId.className="";
		element.className="search-active";
		previousId=element;
	}
	var parameter={};
	parameter["type"]=type;
	parameter["value"]=document.getElementById("employee-search-text").value;
	if( typeof pageNumber === 'undefined' ) pageNumber=1;
	parameter["pageNumber"]=pageNumber.toString();
	parameter["pageSize"]=SEARCH.PAGE_SIZE.toString();
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){ 
		if(request.readyState==4){
			if(request.status==200){
				document.getElementById("content").innerHTML=request.responseText;
				document.getElementById("location-content").className="show";
				//document.getElementById("location-detail-table").className="hide";
				var totalFoundLocation=document.getElementById("total-location-size").innerHTML;
				doLocationPagination(totalFoundLocation,parameter.startIndex/SEARCH.SIZE+1);
				if(totalFoundLocation<=SEARCH.SIZE) document.getElementById("locationPagination").className="hide";
				if(totalFoundLocation>0)
					document.getElementById("search-location-result").innerHTML=totalFoundLocation+" locations found.";
				else
					document.getElementById("search-location-result").innerHTML="&nbsp;&nbsp;No location found.";
				document.getElementById("detail-table").className="hide";
			}else{
				showError("Search Error"+request.status);
			}
		}
	};
	console.log(parameter);
	request.open("GET","searchLocationByCriteria?input="+JSON.stringify(parameter),true);
	request.send(null);
}
function doLocationPagination(size,currentIndex){
	var maxIndex=( (parseInt(size/SEARCH.SIZE))+( size%SEARCH.SIZE >0 ? 1 : 0) );
	if(currentIndex>maxIndex) currentIndex=maxIndex;
	if(currentIndex<1) currentIndex=1;
	document.getElementById("location-pagination-descriptor").innerHTML=currentIndex+"/"+maxIndex ;
}
function locationSearchEnd(){
	var text=previousId.attributes["onclick"].value;
	var total=document.getElementById("total-location-size").innerHTML;
	var index=total%SEARCH.SIZE ==0 ? (total/SEARCH.SIZE-1)*SEARCH.SIZE : parseInt(total/SEARCH.SIZE)*SEARCH.SIZE;
	searchLocation(previousId,text.substring(text.indexOf("'")+1,text.lastIndexOf("'")),index,total);	
}
function locationSearchFirst(){
	var text=previousId.attributes["onclick"].value;
	var total=document.getElementById("total-location-size").innerHTML;
	searchLocation(previousId,text.substring(text.indexOf("'")+1,text.lastIndexOf("'")),0,SEARCH.SIZE);
}
function locationSearchNext(){
	var text=previousId.attributes["onclick"].value;
	currentIndex=document.getElementById("location-pagination-descriptor").innerHTML.split("/")[0]*SEARCH.SIZE;
	searchLocation(previousId,text.substring(text.indexOf("'")+1,text.lastIndexOf("'")),currentIndex,currentIndex+SEARCH.SIZE);
}
function locationSearchPrevious(){
	var text=previousId.attributes["onclick"].value;
	currentIndex=(document.getElementById("location-pagination-descriptor").innerHTML.split("/")[0]-2)*SEARCH.SIZE;
	searchLocation(previousId,text.substring(text.indexOf("'")+1,text.lastIndexOf("'")),currentIndex,currentIndex+SEARCH.SIZE);
}
var detailId=false;
function locationDetail(element){
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				document.getElementById("detail").innerHTML=request.responseText;
				document.getElementById("transfer").className="show";
				document.getElementById("detail-table").className="show";
				document.getElementById("location-detail-table").className="show";
				if(detailId){
					detailId.className="";
					element.className="active";
				}else{
					element.className="active";
				}
				detailId=element;
			}else{
				alert("Error return :"+request.status);
			}
		}
	};
	parameter={};
	parameter["boId"]=element.id;
	request.open("GET","detailLocation?input="+JSON.stringify(parameter),true);
	request.send(null);
}

function loadTransferForm(){
	var locationId=document.getElementById("detail-location-boId").innerHTML;
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				document.getElementById("dialog-temp").innerHTML=request.responseText;
				document.getElementById("dialog-temp").className="dialog";
			}else{
				alert("Load transfer form error.Please try again. Error code is "+request.status);
			}
		}
	};
	var parameter={};
	parameter["locationId"]=locationId;
	request.open("GET","getTransferForm?input="+JSON.stringify(parameter),true);
	request.send(null);
}
function changeLocation(element){
	var locations=document.getElementById(element.id).getElementsByTagName("option");
	var currentIndex=element.selectedIndex;
	var locationId = locations[currentIndex].value;
	var request=new XMLHttpRequest;
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				document.getElementById("dialog-temp").innerHTML=request.responseText;
				document.getElementById("dialog-temp").className="dialog";
			}else{
				alert("Load transfer form error.Please try again. Error code is "+request.status);
			}
		}
	};
	var parameter={};
	parameter["locationId"]=locationId;
	request.open("GET","getTransferForm?input="+JSON.stringify(parameter),true);
	request.send(null);
}
function increase(){
	var amount = document.getElementById("transfer-amount").attributes["value"].value;
	amount += 100;
	document.getElementById("transfer-amount").innerHTML= amount;
}
function decrease(){
	var amount = document.getElementById("transfer-amount").attributes["value"].value;
	amount -= 100;
	document.getElementById("transfer-amount").innerHTML= amount;
}
function transferProduct(){
	
}
function transferProductList(){
	
}
function getAjaxObject() {
	return new XMLHttpRequest();
}
function connectToServer(connection, request) {
	request.open(connection.method, connection.url, true);
	request.send(null);
}
var dayInMonth=[31,28,31,30,31,30,31,31,30,31,30,31];
function convertDate(string){
	var currentDate=new Date;
	if(currentDate.getFullYear()%4==0){ //for leap year
					dayInMonth[1]=29;
				}
	var date={};
	var inputDate=string.split("-");
	if(inputDate.length>3){
		date["status"]=false;
		date["error"]="Invalid date format.";
		return date;
}
	if(inputDate.length==1 && inputDate[0]==""){
		date["status"]=true;
		var currentDate=new Date();
		date["day"]=currentDate.getDate();
		date["month"]=currentDate.getMonth()+1;
		date["year"]=currentDate.getFullYear();
		date["startDay"]=1;
		date["endDay"]=dayInMonth[currentDate.getMonth()];
		date["startMonth"]=1;
		date["endMonth"]=12;
		date["startYear"]=1980; //assume this company is found 1980
		date["endYear"]=currentDate.getFullYear();
		return date;
}
	if(inputDate.length==1){ //year
		var year=parseInt(inputDate[0]);
		if(isNaN(year)){
			date["status"]=false;
			date["error"]="Invalid format.";
			return date;
		}
		if(year<1){
			date["status"]=false;
			date["error"]="Input can\'t be less than one.";
			return date;
		}
		if(year<32){
			if(year>dayInMonth[currentDate.getMonth()]){
				date["status"]=false;
				date["error"]="Day is this can\'t be greater than "+ (dayInMonth[currentDate.getMonth()]);
				return date;
			}
			date["status"]=true;
			date["day"]=year;
			date["month"]=currentDate.getMonth()+1;
			date["year"]=currentDate.getFullYear();
			date["startDay"]=year;
			date["endDay"]=year;
			date["startMonth"]=date["month"];
			date["endMonth"]=date["month"];
			date["startYear"]=date["year"];
			date["endYear"]=date["year"];
			return date;
		}
		if(year>currentDate.getFullYear()){
			date["status"]=false;
			date["error"]="Year can\'t be greater than current year.";
			return date;
		}
		date["status"]=true;
		date["day"]=currentDate.getDate();
		date["month"]=currentDate.getMonth()+1;
		date["year"]=year;
		date["startDay"]=1;
		date["endDay"]=dayInMonth[currentDate.getMonth()];
		date["startMonth"]=1;
		date["endMonth"]=12;
		date["startYear"]=date["year"];
		date["endYear"]=date["year"];
		return date;
	}
	if(inputDate.length==2){
		inputDate[0]=parseInt(inputDate[0]);
		if(isNaN(inputDate[0])){
			date["status"]=false;
			date["error"]="Invalid date format.";
			return date;
		}
		inputDate[1]=parseInt(inputDate[1]);
		if(isNaN(inputDate[1])){
			date["status"]=false;
			date["error"]="Invalid date format.";
			return date;
		}
		if(inputDate[0] <1 || inputDate[1] <1){
			date["status"]=false;
			date["error"]="Invalid date format.";
			return date;
		}
		if(inputDate[1]>12){ // it would be year
			if(inputDate[0]>12){
				date["status"]=false;
				date["error"]="Month can\'t be greater than 12.";
				return date;
			}
			if(inputDate[1]>currentDate.getFullYear()){
				date["status"]=false;
				date["error"]="Year can\'t by greater than current year.";
				return date;
			}
			date["status"]=true;
			date["day"]=currentDate.getDate();
			date["month"]=inputDate[0];
			date["year"]=inputDate[1];
			date["startDate"]=1;
			date["endDate"]=dayInMonth[currentDate.getMonth()];
			date["startMonth"]=date["month"];
			date["endMonth"]=date["month"];
			date["startYear"]=inputDate[1];
			date["endYear"]=inputDate[1];
			return date;
		}else{
			if(inputDate[0]>dayInMonth[currentDate.getMonth()]){
				date["status"]=false;
				date["error"]="Day can\'t be greater than "+(dayInMonth[currentDate.getMonth()]);
				return date;
			}
			date["status"]=true;
			date["day"]=inputDate[0];
			date["month"]=inputDate[1];
			date["year"]=currentDate.getFullYear();
			date["startDay"]=inputDate[0];
			date["endDay"]=inputDate[0];
			date["startMonth"]=inputDate[1];
			date["endMonth"]=inputDate[1];
			date["startYear"]=date["year"];
			date["endYear"]=date["year"];
			return date;
		}
	}else{
		inputDate[0]=parseInt(inputDate[0]);
		if(isNaN(inputDate[0])){
			date["status"]=false;
			date["error"]="Invalid date format.";
			return date;
		}
		inputDate[1]=parseInt(inputDate[1]);
		if(isNaN(inputDate[1])){
			date["status"]=false;
			date["error"]="Invalid date format";
			return date;
		}
		inputDate[2]=parseInt(inputDate[2]);
		if(isNaN(inputDate[2])){
			date["status"]=false;
			date["error"]="Invalid date format.";
			return date;
		}
		if(inputDate[0]<1 || inputDate[1]<1 || inputDate[2]<1){
			date["status"]=false;
			date["error"]="Input date can\'t be  less than 1.";
			return date;
		}
		if(inputDate[0]>dayInMonth[currentDate.getMonth()]){
			date["status"]=false;
			date["error"]="Day can\'t be greater than "+(dayInMonth[currnetDate.getMonth()]);
			return date;
		}
		if(inputDate[1]>12){
			date["status"]=false;
			date["error"]="Month can\'t be greater than 12.";
			return date;
		}
		if(inputDate[2]>currentDate.getFullYear()){
			date["status"]=false;
			date["error"]="Year can\'t be greater than current year.";
			return date;
		}
		date["status"]=true;
		date["day"]=inputDate[0];
		date["month"]=inputDate[1];
		date["year"]=inputDate[2];
		date["startDay"]=date["day"];
		date["endDay"]=date["day"];
		date["startMonth"]=date["month"];
		date["endMonth"]=date["month"];
		date["startYear"]=date["year"];
		date["endYear"]=date["year"];
		return date;
	}	
}
function showError(message,errorCode){
	if(errorCode==0){
		alert("Can't connect to server. Please restart your application server");
		return;
	}
	alert(message+" error code is :"+errorCode);
}