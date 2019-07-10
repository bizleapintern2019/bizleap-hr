function login(){
	// console.log("Inside Login method in js >>>>>>>>>>>>>");
	var username=document.getElementById("username").value;
	var password=document.getElementById("password").value;	
	//alert(username+"  pass="+password);
	var request=new XMLHttpRequest();
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				hideLoading();
				document.getElementById("temp").innerHTML=request.responseText;
				var loginId=document.getElementById("id").innerHTML;
				var temp=document.createElement("div");
				temp.innerHTML=request.responseText;
				// console.log("........"+request.responseText);
				// console.log("//////"+loginId);
				// if(temp.getElementsByTagName("span")[0].innerHTML=="သင်သည်၀င်ခွင့်မရှိ​​​​ပါ"){
                if(document.getElementById("id").innerHTML == "Not authorized person"){
					$(".login-ani").effect("shake",{times:4},500);
					// document.getElementById("username").placeholder="Username";
					document.getElementById("password").value="";
					// document.getElementById("password").placeholder="Password";
					document.getElementById("username").value="";
					document.getElementById("username").focus();
					temp.innerHTML="";
					return;
				}
				else{
				// console.log("LoginId>>>>"+loginId);
				loadMainPage(loginId);
				document.getElementById("error").innerHTML=loginId
				}	
				}else{
				alert("error return :"+request.status);
			}
		}
	};
	
	function parseNewJSON(string) {
		var result = JSON.parse(string);
		if (typeof result == "string")
			result = JSON.parse(result);
		return result;
	}
	;
	
	var parameter={};
	parameter["username"]=username;
	parameter["password"]=password;
	console.log("parameter",parameter);
	loading();
	request.open("POST","login?input="+JSON.stringify(parameter),true);
	request.send(null);
}
function loadMainPage(loginId){
	var request=new XMLHttpRequest();
	request.onreadystatechange=function(){
		if(request.readyState==4){
			if(request.status==200){
				document.getElementsByTagName("body")[0].innerHTML=request.responseText;
				$('#outletSale').click();
				//loadScript();
			}else{
				alert("error return :"+request.status);
			}
		}
	};
	if(loginId!="Not authorized person")
		request.open("GET","successLogin?id="+loginId,true);
	else{
		document.getElementsByTagName("error").innerHTML=loginId;
		document.getElementById("error").className="error-text";
	}
	request.send(null);
}

function CloseLoginForm(){
	document.getElementById("username").innerHTML="";
	document.getElementById("password").innerHTML="";
	document.getElementById("username").value="";
	document.getElementById("password").value="";
}


function logout() {
	var request = new XMLHttpRequest();
	request.onreadystatechange = readyFunction;
	//request.sendToController("GET", "logout", true);
	request.open("GET", "logout", true);
	request.send(null);

	function readyFunction() {
		if (request.readyState != 4) return;
		if (request.status != 200) {
			alert(message["logout.failure"]);
			return;
		}
		console.log("Success");
		location.reload(true);
		}
}