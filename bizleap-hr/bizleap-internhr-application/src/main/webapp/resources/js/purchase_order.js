function sales(element, description, entityType) {
	if (tempParameter != "" && tempParameter != undefined) {
		var previous = tempParameter.element;
		previous.className = "";
	}
	var parameter = {};
	var index = document.getElementById("porderlocationname").selectedIndex;
	parameter["value"] = document.getElementById("porderlocationname").options[index].value;
	parameter["pordersearchdate"] = document.getElementById("porderStartDate").value;
	parameter["porderenddate"] = document.getElementById("porderEndDate").value;
	parameter["description"] = description;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState != 4)
			return;
		if (request.status != 200) {
			alert("Error return " + request.status);
			return;
		}
		hideLoading();
		document.getElementById("action-purchase_order").className = "show";
		if (description == "salesitems") {
			document.getElementById("reportSalesItems").className = "show";
			document.getElementById("reportPurcahseOrder").className = "hide";
			document.getElementById("reportLeftSalesItems").className = "hide";
		}
		if (description == "leftsalesitems") {
			document.getElementById("reportLeftSalesItems").className = "show";
			document.getElementById("reportSalesItems").className = "hide";
			document.getElementById("reportPurcahseOrder").className = "hide";
		}
		if (description == "reportInventory") {
			document.getElementById("reportLeftSalesItems").className = "hide";
			document.getElementById("reportSalesItems").className = "hide";
			document.getElementById("reportPurcahseOrder").className = "hide";

		}
		if (description == "customerClosingReport") {
			document.getElementById("reportLeftSalesItems").className = "hide";
			document.getElementById("reportSalesItems").className = "hide";
			document.getElementById("reportPurcahseOrder").className = "hide";

		}

		// loadAction(document.getElementById(entityType.toLowerCase()+"menu"),
		// entityType.toLowerCase());
		element.className = "search-active ui button basic green small";
		document.getElementById("detail").innerHTML = request.responseText;
		document.getElementById("content").innerHTML = "";
		var selects = document.getElementById("porderlocationname");
		st = selects.options[selects.selectedIndex].text;
		document.getElementById("content").innerHTML = "<center>" + st
				+ "</center>";
		document.getElementById("salesitems_detail_table").className = "show";
	};
	parameter.element = element;
	element.className = "search-active ui button basic green small";
	parameter.entityType = entityType;
	tempParameter = parameter;
	request.open("GET", "sales/" + description + "?input="
			+ JSON.stringify(parameter), true);
	connectionStatus = true; // working
	request.send();
	loading();

}

function viewHistory(){
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").attributes["class"].value = "dialog";
				document.getElementById("locationName").innerHTML=document.getElementById("purchaseOrder_locationName").innerHTML;
				document.getElementById("customerName").innerHTML=document.getElementById("purchaseOrder_customerName").innerHTML;
				document.getElementById("invoiceNo").innerHTML=document.getElementById("detail-purchase_order-invoiceNo").innerHTML;
			}
		}
	};
	var parameter = {};
	parameter["invoiceNo"] = document.getElementById("detail-purchase_order-invoiceNo").innerHTML;
	parameter["customerBoId"]=document.getElementById("purchaseOrder_customerBoId").innerHTML;
	parameter["locationBoid"]=document.getElementById("purchaseOrder_locationBoId").innerHTML;
	console.log(" Parameter " ,parameter);
	request.open("GET", "getViewPurchaseOrderHistory?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

function viewTransferHistory() {
    var request = new XMLHttpRequest;
    request.onreadystatechange = function() {
        if (request.readyState == 4) {
            if (request.status == 200) {
                document.getElementById("dialog-temp").innerHTML = request.responseText;
                document.getElementById("dialog-temp").attributes["class"].value = "dialog";
                document.getElementById("fromLocationName").innerHTML=document.getElementById("detail-transfer-from-name").innerHTML;
                document.getElementById("toLocationName").innerHTML=document.getElementById("detail-transfer-to-name").innerHTML;
                document.getElementById("transferNo").innerHTML=document.getElementById("detail-transfer-invoiceNo").innerHTML;
            }
        }
    };
    var parameter = {};
    parameter["transferBoId"] = document.getElementById("transfer-BoId").innerHTML;
    console.log(" Parameter ",parameter);
    request.open("GET", "getViewTransferHistory?input="
        + JSON.stringify(parameter), true);
    request.send(null);
}

function checkEnterNameEventReport(event) {
	if (event.which == 13 || event.keyCode == 13 || event.which == 9
			|| event.keyCode == 9) {
		var request = new XMLHttpRequest;
		request.onreadystatechange = function() {
			if (request.readyState != 4)
				return;
			if (request.status != 200) {
				alert("Error return " + request.status);
				return;
			}
			hideLoading();
			if (request.responseText != "") {
				var json = parseNewJson(request.responseText);
				var customerList = json.customerList;
				if (json != null) {
					var dataList=document.getElementById("customerList");
					dataList.innerHTML="";
					for ( var index in customerList) {
						var option = document.createElement("option");
						$(option).attr("id",customerList[index].id);
						$(option).attr("data-type",customerList[index].type);
						//$(option).attr("valid",customerList[index].isValidCredit);
						option.innerHTML = customerList[index].name+' ( '+customerList[index].classType+' ) ( '+customerList[index].city+' ) ';
						dataList.appendChild(option);
					}
				}
				//$("#searchReportCustomer").focus();
			}
		}
		var parameter = {};
		parameter["value"] =document.getElementById("searchReportCustomer").value;
		console.log("parameter", parameter);
		request.open("GET", "searchReportCustomersByName?input="
				+ JSON.stringify(parameter), "true");
		connectionStatus = true; // working
		request.send();
		loading();
	return false;
	}
	return true;
}

function reportPurchaseOrder(element, description) {
	document.getElementById("reportPurcahseOrder") ? document
			.getElementById("reportPurcahseOrder").className = "hide" : "";
	if (tempParameter != "" && tempParameter != undefined) {
		var previous = tempParameter.element;
		previous.className = "";
	}

	var parameter = {};
	parameter.description = description;
	parameter.element = element;
	element.className = "show";
	document.getElementById("searchadvanceForInvoice").className="show ui button blue basic small";
	tempParameter = parameter;
	var index = document.getElementById("porderlocationname").selectedIndex;
	parameter["value"] = document.getElementById("porderlocationname").options[index].value;
	parameter["name"] = document.getElementById("porderlocationname").options[index].text;
	parameter["pordersearchdate"] = changeDayMonthYear(document.getElementById("porderStartDate").value);
	if (document.getElementById("porderEndDate") != undefined)
		parameter["porderenddate"] = changeDayMonthYear(document.getElementById("porderEndDate").value);
	if (document.getElementById("salary-date") != undefined)
		parameter["location-salary-date"] = document
				.getElementById("salary-date").value;
	parameter["description"] = description;
	if (description == "reportPurcahseOrder") {
		window.open(
				"report/SaleReport.xlsx?input=" + JSON.stringify(parameter),
				"_blank");
	} else {
        if(document.getElementById("porderlocationname").value=='ALL'){
            alert("Please, Choose Location Name!");
            return;
        }
        if (document.getElementById("porderStartDate").value == "") {
            alert("Please fill Start Date!");
            return;
        }
        if (document.getElementById("porderEndDate").value == "") {
            alert("Please fill Start Date!");
            return;
        }
		// request.open("GET", "purchaseOrderReport?input="
		// + JSON.stringify(parameter), true);
		window.open("purchaseOrderReport/Report-" + parameter["name"] + ":"
				+ ".xlsx?input=" + JSON.stringify(parameter),
				"_blank");
	}
	console.log(parameter);
	// request.send();
	// loading();
}
function reportCustomerReturnList(element,description) {
    document.getElementById("searchadvanceForInvoice").className="show ui button blue basic small";
    var parameter = {};
    parameter.element = element;
    element.className = "show";
    document.getElementById("searchadvanceForInvoice").className="show ui button blue basic small";
    parameter.description = description;
    var index = document.getElementById("porderlocationname").selectedIndex;
    parameter["value"] = document.getElementById("porderlocationname").options[index].value;
    parameter["name"] = document.getElementById("porderlocationname").options[index].text;
    parameter["pordersearchdate"] = changeDayMonthYear(document.getElementById("porderStartDate").value);
    if (document.getElementById("porderEndDate") != undefined)
        parameter["porderenddate"] = changeDayMonthYear(document.getElementById("porderEndDate").value);
    if (document.getElementById("salary-date") != undefined)
        parameter["location-salary-date"] = document
            .getElementById("salary-date").value;
    parameter["description"] = description;
    window.open("customerReturnReport/CustomerReturnReport.xlsx?input="
        + JSON.stringify(parameter), "_blank");
}

function reportLocationStockList() {
	var locationBoId = $("#detail-location-name").attr("value");
	var parameter = {};
	parameter.boId = locationBoId;
	window.open("locationStockListReport/report.xlsx?input="
			+ JSON.stringify(parameter), "_blank");
}

function reportTransferFromStockList() {
	var parameter = {};
	var index = document.getElementById("porderlocationname").selectedIndex;
	parameter["value"] = document.getElementById("porderlocationname").options[index].value;
	window.open("transferFromReport/TransferReport.xlsx?input="
			+ JSON.stringify(parameter), "_blank");
}

function reportAdjustmentStockList() {
	var parameter = {};
	var index = document.getElementById("porderlocationname").selectedIndex;
	parameter["value"] = document.getElementById("porderlocationname").options[index].value;
	window.open(
			"adjustmentStockListReport/AdjustmentStockListReport.xlsx?input="
					+ JSON.stringify(parameter), "_blank");
}

function reportSalary(element, description) {
	if (tempParameter != "" && tempParameter != undefined) {
		var previous = tempParameter.element;
		previous.className = "";
	}
	if (document.getElementById("salary-date").value == "") {
		alert("Enter a month");
		return null;
	}
	var parameter = {};
	parameter.description = description;
	/*
	 * var request = new XMLHttpRequest; request.onreadystatechange = function() {
	 * if (request.readyState == 4) { if (request.status == 200) {
	 * hideLoading(); $("#salary-date") .datepicker( { dateFormat : 'mm/yy',
	 * changeMonth : true, changeYear : true, showButtonPanel : true,
	 * 
	 * onClose : function(dateText, inst) { var month = $( "#ui-datepicker-div
	 * .ui-datepicker-month :selected") .val(); var year = $(
	 * "#ui-datepicker-div .ui-datepicker-year :selected") .val(); $(this).val(
	 * $.datepicker.formatDate( 'mm/yy', new Date(year, month, 1))); } });
	 * 
	 * $("#salary-date").focus(function() { $(".ui-datepicker-calendar").hide();
	 * $("#ui-datepicker-div").position({ my : "center top", at : "center
	 * bottom", of : $(this) }); }); } else { alert("Load Report Form
	 * error.Please try again. Error code is " + request.status); } } };
	 */
	parameter.element = element;
	element.className = "search-active ui button basic green small";
	tempParameter = parameter;
	var index = document.getElementById("salarylocation").selectedIndex;
	parameter["value"] = document.getElementById("salarylocation").options[index].value;
	parameter["name"] = document.getElementById("salarylocation").options[index].text;
	parameter["location-salary-date"] = document.getElementById("salary-date").value;
	// request.open("GET", "purchaseOrderReport?input="
	// + JSON.stringify(parameter), true);

	var dateStr = parameter["location-salary-date"];
	var res = dateStr.replace("/", "_");

	window.open("purchaseOrderReport/SalaryReport_" + parameter["name"] + "_"
			+ res + ".xlsx?input=" + JSON.stringify(parameter), "_blank");

	console.log(parameter);
	// request.send();
	// loading();
}

function compensationForm() {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").attributes["class"].value = "dialog";
				// to fill data
				document.getElementById("empid").innerHTML = document
						.getElementById("detail-employee-boId").innerHTML;
				document.getElementById("employee_name").innerHTML = document
						.getElementById("employee_title").innerHTML
						+ " "
						+ document.getElementById("employee_firstName").innerHTML
						+ " "
						+ document.getElementById("employee_middleName").innerHTML
						+ " "
						+ document.getElementById("employee_lastName").innerHTML;

				// document.getElementById("codeAllowance").value="0";
				document.getElementById("foodAllowance").value = document
						.getElementById("compensation_foodAllowance") != null ? document
						.getElementById("compensation_foodAllowance").innerHTML
						: "0";
				document.getElementById("houseAttendanceAllowance").value = document
						.getElementById("compensation_houseAttendanceAllowance") != null ? document
						.getElementById("compensation_houseAttendanceAllowance").innerHTML
						: "0";

				document.getElementById("onTimeAllowance").value = document
						.getElementById("compensation_onTimeAllowance") != null ? document
						.getElementById("compensation_onTimeAllowance").innerHTML
						: "0";
				document.getElementById("goodAttendanceAllowance").value = document
						.getElementById("compensation_goodAttendanceAllowance") != null ? document
						.getElementById("compensation_goodAttendanceAllowance").innerHTML
						: "0";
				document.getElementById("commuteAllowance").value = document
						.getElementById("compensation_commuteAllowance") != null ? document
						.getElementById("compensation_commuteAllowance").innerHTML
						: "0";

				document.getElementById("codeExamAllowance").value = document
						.getElementById("compensation_codeExamAllowance") != null ? document
						.getElementById("compensation_codeExamAllowance").innerHTML
						: "0";
				document.getElementById("codeWrongFine").value = document
						.getElementById("compensation_codeWrongFine") != null ? document
						.getElementById("compensation_codeWrongFine").innerHTML
						: "0";
				document.getElementById("priceWrongFine").value = document
						.getElementById("compensation_priceWrongFine") != null ? document
						.getElementById("compensation_priceWrongFine").innerHTML
						: "0";

				document.getElementById("customerSignatureFine").value = document
						.getElementById("compensation_customerSignatureFine") != null ? document
						.getElementById("compensation_customerSignatureFine").innerHTML
						: "0";
				document.getElementById("salesQuantityCommission").value = document
						.getElementById("compensation_salesQuantityCommission") != null ? document
						.getElementById("compensation_salesQuantityCommission").innerHTML
						: "0";
				document.getElementById("cashDownSalesQuantityCommission").value = document
						.getElementById("compensation_cashDownSalesQuantityCommission") != null ? document
						.getElementById("compensation_cashDownSalesQuantityCommission").innerHTML
						: "0";

				document.getElementById("putASideSalesQuantityCommmission").value = document
						.getElementById("compensation_putASideSalesQuantityCommmission") != null ? document
						.getElementById("compensation_putASideSalesQuantityCommmission").innerHTML
						: "0";
				document.getElementById("salesRevenueCommission").value = document
						.getElementById("compensation_salesRevenueCommission") != null ? document
						.getElementById("compensation_salesRevenueCommission").innerHTML
						: "0";
				document.getElementById("cashDownSalesRevenueCommission").value = document
						.getElementById("compensation_cashDownSalesRevenueCommission") != null ? document
						.getElementById("compensation_cashDownSalesRevenueCommission").innerHTML
						: "0";

				document.getElementById("putAsideSalesRevenueCommission").value = document
						.getElementById("compensation_putAsideSalesRevenueCommission") != null ? document
						.getElementById("compensation_putAsideSalesRevenueCommission").innerHTML
						: "0";
				document.getElementById("quarterlySalesRevenueCommission").value = document
						.getElementById("compensation_quarterlySalesRevenueCommission") != null ? document
						.getElementById("compensation_quarterlySalesRevenueCommission").innerHTML
						: "0";
				document.getElementById("extraSalesRevenueCommission").value = document
						.getElementById("compensation_extraSalesRevenueCommission") != null ? document
						.getElementById("compensation_extraSalesRevenueCommission").innerHTML
						: "0";

				document
						.getElementById("salesRevenueDebitCollectionCommission").value = document
						.getElementById("compensation_salesRevenueDebitCollectionCommission") != null ? document
						.getElementById("compensation_salesRevenueDebitCollectionCommission").innerHTML
						: "0";
				document.getElementById("debitDuePenalty").value = document
						.getElementById("compensation_debitDuePenalty") != null ? document
						.getElementById("compensation_debitDuePenalty").innerHTML
						: "0";
				document.getElementById("fieldGoingPerDayAllowance").value = document
						.getElementById("compensation_fieldGoingPerDayAllowance") != null ? document
						.getElementById("compensation_fieldGoingPerDayAllowance").innerHTML
						: "0";

				document.getElementById("downTownMarketAllowance").value = document
						.getElementById("compensation_downTownMarketAlreportCustomerReturnListlowance") != null ? document
						.getElementById("compensation_downTownMarketAllowance").innerHTML
						: "0";
				document.getElementById("monthlySaleAmountCommissionForShop").value = document
						.getElementById("compensation_monthlySaleAmountCommissionForShop") != null ? document
						.getElementById("compensation_monthlySaleAmountCommissionForShop").innerHTML
						: "0";
				document.getElementById("cashierCommission").value = document
						.getElementById("compensation_cashierCommission") != null ? document
						.getElementById("compensation_cashierCommission").innerHTML
						: "0";

				document.getElementById("firstAwardAllowance").value = document
						.getElementById("compensation_firstAwardAllowance") != null ? document
						.getElementById("compensation_firstAwardAllowance").innerHTML
						: "0";
				document.getElementById("secondAwardAllowance").value = document
						.getElementById("compensation_secondAwardAllowance") != null ? document
						.getElementById("compensation_secondAwardAllowance").innerHTML
						: "0";
				document.getElementById("thirdAwardAllowance").value = document
						.getElementById("compensation_thirdAwardAllowance") != null ? document
						.getElementById("compensation_thirdAwardAllowance").innerHTML
						: "0";

				document.getElementById("auditAllowance").value = document
						.getElementById("compensation_auditAllowance") != null ? document
						.getElementById("compensation_auditAllowance").innerHTML
						: "0";
				document
						.getElementById("promotionSalesQuantityCommissionForRetail").value = document
						.getElementById("compensation_promotionSalesQuantityCommissionForRetail") != null ? document
						.getElementById("compensation_promotionSalesQuantityCommissionForRetail").innerHTML
						: "0";
				document.getElementById("overTimeAllowance").value = document
						.getElementById("compensation_overTimeAllowance") != null ? document
						.getElementById("compensation_overTimeAllowance").innerHTML
						: "0";

				document.getElementById("absentDayFine").value = document
						.getElementById("compensation_absentDayFine") != null ? document
						.getElementById("compensation_absentDayFine").innerHTML
						: "0";

				document.getElementById("zayCarFieldTripAllowance").value = document
						.getElementById("compensation_zayCarFieldTripAllowance") != null ? document
						.getElementById("compensation_zayCarFieldTripAllowance").innerHTML
						: "0";
				document.getElementById("commissionPercentage").value = document
						.getElementById("compensation_commissionPercentage") != null ? document
						.getElementById("compensation_commissionPercentage").innerHTML
						: "0";
				document.getElementById("putAsideValue").value = document
						.getElementById("compensation_putAsideValue") != null ? document
						.getElementById("compensation_putAsideValue").innerHTML
						: "0";
				document.getElementById("returnPenalty").value = document
						.getElementById("compensation_returnPenalty") != null ? document
						.getElementById("compensation_returnPenalty").innerHTML
						: "0";
				document.getElementById("producedValue").value = document
						.getElementById("compensation_producedValue") != null ? document
						.getElementById("compensation_producedValue").innerHTML
						: "0";
				document.getElementById("returnPercentage").value = document
						.getElementById("compensation_returnPercentage") != null ? document
						.getElementById("compensation_returnPercentage").innerHTML
						: "0";

			}
		}
		;
	}
	request.open("GET", "getCompensationForm", true);
	request.send();
}

function updateCompensation() {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				// loadAction(document.getElementById("employeemenu"),
				// 'employee');
				alert("Successfully save compensation data");
			} else {
				alert("Error return :" + request.status);
			}
		}
	};
	var parameter = {};
	parameter["empid"] = document.getElementById("empid").innerHTML;
	// parameter["codeAllowance"] =
	// document.getElementById("codeAllowance").value;
	// parameter["codeAllowance"] = "0";
	parameter["foodAllowance"] = document.getElementById("foodAllowance").value
			.trim() ? document.getElementById("foodAllowance").value.trim()
			: "0";
	parameter["houseAttendanceAllowance"] = document
			.getElementById("houseAttendanceAllowance").value.trim() ? document
			.getElementById("houseAttendanceAllowance").value.trim() : "0";

	parameter["onTimeAllowance"] = document.getElementById("onTimeAllowance").value
			.trim() ? document.getElementById("onTimeAllowance").value.trim()
			: "0";
	parameter["goodAttendanceAllowance"] = document
			.getElementById("goodAttendanceAllowance").value.trim() ? document
			.getElementById("goodAttendanceAllowance").value.trim() : "0";
	parameter["commuteAllowance"] = document.getElementById("commuteAllowance").value
			.trim() ? document.getElementById("commuteAllowance").value.trim()
			: "0";

	parameter["codeExamAllowance"] = document
			.getElementById("codeExamAllowance").value.trim() ? document
			.getElementById("codeExamAllowance").value.trim() : "0";
	parameter["codeWrongFine"] = document.getElementById("codeWrongFine").value
			.trim() ? document.getElementById("codeWrongFine").value.trim()
			: "0";
	parameter["priceWrongFine"] = document.getElementById("priceWrongFine").value
			.trim() ? document.getElementById("priceWrongFine").value.trim()
			: "0";

	parameter["customerSignatureFine"] = document
			.getElementById("customerSignatureFine").value.trim() ? document
			.getElementById("customerSignatureFine").value.trim() : "0";
	parameter["salesQuantityCommission"] = document
			.getElementById("salesQuantityCommission").value.trim() ? document
			.getElementById("salesQuantityCommission").value.trim() : "0";
	parameter["cashDownSalesQuantityCommission"] = document
			.getElementById("cashDownSalesQuantityCommission").value.trim() ? document
			.getElementById("cashDownSalesQuantityCommission").value.trim()
			: "0";

	parameter["putASideSalesQuantityCommmission"] = document
			.getElementById("putASideSalesQuantityCommmission").value.trim() ? document
			.getElementById("putASideSalesQuantityCommmission").value.trim()
			: "0";
	parameter["salesRevenueCommission"] = document
			.getElementById("salesRevenueCommission").value.trim() ? document
			.getElementById("salesRevenueCommission").value.trim() : "0";
	parameter["cashDownSalesRevenueCommission"] = document
			.getElementById("cashDownSalesRevenueCommission").value.trim() ? document
			.getElementById("cashDownSalesRevenueCommission").value.trim()
			: "0";

	parameter["putAsideSalesRevenueCommission"] = document
			.getElementById("putAsideSalesRevenueCommission").value.trim() ? document
			.getElementById("putAsideSalesRevenueCommission").value.trim()
			: "0";
	parameter["quarterlySalesRevenueCommission"] = document
			.getElementById("quarterlySalesRevenueCommission").value.trim() ? document
			.getElementById("quarterlySalesRevenueCommission").value.trim()
			: "0";
	parameter["extraSalesRevenueCommission"] = document
			.getElementById("extraSalesRevenueCommission").value.trim() ? document
			.getElementById("extraSalesRevenueCommission").value.trim()
			: "0";

	parameter["salesRevenueDebitCollectionCommission"] = document
			.getElementById("salesRevenueDebitCollectionCommission").value
			.trim() ? document
			.getElementById("salesRevenueDebitCollectionCommission").value
			.trim() : "0";
	parameter["debitDuePenalty"] = document.getElementById("debitDuePenalty").value
			.trim() ? document.getElementById("debitDuePenalty").value.trim()
			: "0";
	parameter["fieldGoingPerDayAllowance"] = document
			.getElementById("fieldGoingPerDayAllowance").value.trim() ? document
			.getElementById("fieldGoingPerDayAllowance").value.trim()
			: "0";

	parameter["downTownMarketAllowance"] = document
			.getElementById("downTownMarketAllowance").value.trim() ? document
			.getElementById("downTownMarketAllowance").value.trim() : "0";
	parameter["monthlySaleAmountCommissionForShop"] = document
			.getElementById("monthlySaleAmountCommissionForShop").value.trim() ? document
			.getElementById("monthlySaleAmountCommissionForShop").value.trim()
			: "0";
	parameter["cashierCommission"] = document
			.getElementById("cashierCommission").value.trim() ? document
			.getElementById("cashierCommission").value.trim() : "0";

	parameter["firstAwardAllowance"] = document
			.getElementById("firstAwardAllowance").value.trim() ? document
			.getElementById("firstAwardAllowance").value.trim() : "0";
	parameter["secondAwardAllowance"] = document
			.getElementById("secondAwardAllowance").value.trim() ? document
			.getElementById("secondAwardAllowance").value.trim() : "0";
	parameter["thirdAwardAllowance"] = document
			.getElementById("thirdAwardAllowance").value.trim() ? document
			.getElementById("thirdAwardAllowance").value.trim() : "0";

	parameter["auditAllowance"] = document.getElementById("auditAllowance").value
			.trim() ? document.getElementById("auditAllowance").value.trim()
			: "0";
	parameter["promotionSalesQuantityCommissionForRetail"] = document
			.getElementById("promotionSalesQuantityCommissionForRetail").value
			.trim() ? document
			.getElementById("promotionSalesQuantityCommissionForRetail").value
			.trim() : "0";
	parameter["overTimeAllowance"] = document
			.getElementById("overTimeAllowance").value.trim() ? document
			.getElementById("overTimeAllowance").value.trim() : "0";

	parameter["absentDayFine"] = document.getElementById("absentDayFine").value
			.trim() ? document.getElementById("absentDayFine").value.trim()
			: "0";

	parameter["zayCarFieldTripAllowance"] = document
			.getElementById("zayCarFieldTripAllowance").value.trim() ? document
			.getElementById("zayCarFieldTripAllowance").value.trim() : "0";
	parameter["commissionPercentage"] = document
			.getElementById("commissionPercentage").value.trim() ? document
			.getElementById("commissionPercentage").value.trim() : "0";
	parameter["putAsideValue"] = document.getElementById("putAsideValue").value
			.trim() ? document.getElementById("putAsideValue").value.trim()
			: "0";
	parameter["returnPenalty"] = document.getElementById("returnPenalty").value
			.trim() ? document.getElementById("returnPenalty").value.trim()
			: "0";
	parameter["producedValue"] = document.getElementById("producedValue").value
			.trim() ? document.getElementById("producedValue").value.trim()
			: "0";
	parameter["returnPercentage"] = document.getElementById("returnPercentage").value
			.trim() ? document.getElementById("returnPercentage").value.trim()
			: "0";

	console.log(parameter);
	request.open("POST", "updateCompensation?input="
			+ JSON.stringify(parameter), true);
	request.send();
}

function viewStockListCar(element) {
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var routeId = element.id;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
			} else {
				alert("Load view Salary form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["routeId"] = routeId;
	request.open("GET", "getRouteStockListFormCar?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

function today() {
	var currentTime = new Date();
	var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	if (day < 10)
		day = "0" + day;
	if (month < 10)
		month = "0" + month;
	return (day + "/" + month + "/" + year);
}

function printingPayment() {
	document.getElementById("printPaymentDate").innerHTML = document
			.getElementById("payment_paymentDate").value;
	document.getElementById("printPaymentLocation").innerHTML = document
			.getElementById("paymet_location").innerHTML;
	document.getElementById("printPaymentInvNo").innerHTML = document
			.getElementById("payInvoice").value;
	document.getElementById("printPaymentName").innerHTML = document
			.getElementById("payment_customername").innerHTML;
	document.getElementById("printPaymentAmount").innerHTML = document
			.getElementById("payment_amount").value;
	document.getElementById("printLuckyDraw").innerHTML = document
			.getElementById("luckyDrawAmount").value ? document
			.getElementById("luckyDrawAmount").value : 0;
	console.log("TotalBalance 1 ",
			document.getElementById("totalBalance").innerHTML);
	var customerId = document.getElementById("payment_customerId").innerHTML;
	getTotalBalance(customerId, 'printPaymentArea', 'printPaymentBalance');
}

function printTime() {
	var currentTime = new Date();
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	var seconds = currentTime.getSeconds();
	return (hours + ":" + minutes + ":" + seconds);
}

function printPurchaseOrderInvoice() {
	document.getElementById("printSalesName").innerHTML = document
			.getElementById("purchaseOrder_customerName").innerHTML;
	document.getElementById("printSalesNameOtherLocation").innerHTML = document
			.getElementById("purchaseOrder_customerName").innerHTML;
	document.getElementById("printSalesNameForBox").innerHTML = document
			.getElementById("purchaseOrder_customerName").innerHTML;
	document.getElementById("printSalesDate").innerHTML = document
			.getElementById("detail-purchase_order-date").innerHTML;
	document.getElementById("printSalesDateOtherLocation").innerHTML = document
			.getElementById("detail-purchase_order-date").innerHTML;
	document.getElementById("printSalesDateForBox").innerHTML = document
			.getElementById("detail-purchase_order-date").innerHTML;
	document.getElementById("printSalesInvNo").innerHTML = document
			.getElementById("detail-purchase_order-invoiceNo").innerHTML;
	document.getElementById("printSalesInvNoOtherLocation").innerHTML = document
			.getElementById("detail-purchase_order-invoiceNo").innerHTML;
	document.getElementById("printSalesInvNoForBox").innerHTML = document
			.getElementById("detail-purchase_order-invoiceNo").innerHTML;
	document.getElementById("printTime").innerHTML = printTime();
	document.getElementById("printTimeLocation").innerHTML = printTime();
	document.getElementById("printTimeForBox").innerHTML = printTime();
	document.getElementById("printSalesLocation").innerHTML = document
			.getElementById("purchaseOrder_locationName").innerHTML;
	document.getElementById("printSalesLocationOtherLocation").innerHTML = document
			.getElementById("purchaseOrder_locationName").innerHTML;
	document.getElementById("printSalesLocationForBox").innerHTML = document
			.getElementById("purchaseOrder_locationName").innerHTML;
	$("#printSalesCredit").text($("#purchaseOrder_customer_balance").text());
	$("#printSalesOtherCost").text($("#purchaseOrder_addcost").text());
	$("#printSalesAdditionCash").text(
			$("#detail-purchase_order-additionalCashDiscount").text());
	$("#printSalesNetAmount")
			.text($("#detail-purchase_order-netAmount").text());
	$("#printLuckyDraw").text($("#purchaseOrder_luckyDrawAmount").text());
	$("#printPayAmount").text($("#purchaseOrder_paidAmount").text());
	$("#printtoalBalance")
			.text(
					(parseInt($("#purchaseOrder_customer_balance").text() ? $(
							"#purchaseOrder_customer_balance").text() : 0) + parseInt($(
							"#detail-purchase_order-netAmount").text()))
							- (parseInt($("#purchaseOrder_paidAmount").text() ? $(
									"#purchaseOrder_paidAmount").text()
									: 0) + parseInt($(
									"#purchaseOrder_luckyDrawAmount").text() ? $(
									"#purchaseOrder_luckyDrawAmount").text()
									: 0)));
	var productName = document
			.getElementsByName("detail-lineitem_purchaseOrder_name");
	var productType = document
			.getElementsByName("detail-lineitem_purchaseOrder_type");
	var productQuantity = document
			.getElementsByName("detail-lineitem_purchaseOrder_qty");
	var productTotal = document
			.getElementsByName("detail-lineitem_purchaseOrder_totalAmount");
	var productCode = document
			.getElementsByName("detail-lineitem_purchaseOrder-boId");
	var productPrice = document
			.getElementsByName("detail-lineitem_purchaseOrder_price");
	var productLocation = document
			.getElementsByName("detail-lineitem_purchaseOrder_locationName");
	var table = document.getElementById("salesItemsList");
	for (var j = table.rows.length; 1 < j; j--) {
		if (j == 1)
			break;
		table.deleteRow(j - 1);
	}
	for (var i = 1; i <= productCode.length; i++) {
		table.insertRow(i);
		if (productCode[i - 1].value == "") {
			continue;
		}
		table.rows[i].insertCell(0).style.textAlign = 'left';
		table.rows[i].cells[0].appendChild(document.createTextNode($(
				productName[i - 1]).text()));
		table.rows[i].insertCell(1).style.textAlign = 'right';
		table.rows[i].cells[1].appendChild(document.createTextNode($(
				productQuantity[i - 1]).text()));
		table.rows[i].insertCell(2).style.textAlign = 'left';
		var value = $(productType[i - 1]).text();
		table.rows[i].cells[2].appendChild(document
				.createTextNode(packageTypeToMyanmar(value)));
		table.rows[i].insertCell(3).style.textAlign = 'right';
		table.rows[i].cells[3].appendChild(document.createTextNode($(
				productPrice[i - 1]).text()));
		table.rows[i].insertCell(4).style.textAlign = 'right';
		table.rows[i].cells[4].appendChild(document.createTextNode($(
				productTotal[i - 1]).text()));
	}
	var tableOtherLocation = document.getElementById("salesItemsListLocation");
	for (var j = tableOtherLocation.rows.length; 1 < j; j--) {
		if (j == 1)
			break;
		tableOtherLocation.deleteRow(j - 1);
	}

	var totalQty = 0;
	var branchLocation = true;
	for (var i = 1; i <= productCode.length; i++) {
		tableOtherLocation.insertRow(i);
		if (productCode[i - 1].value == "") {
			continue;
		}
		branchLocation = false;
		tableOtherLocation.rows[i].insertCell(0).style.textAlign = 'right';
		tableOtherLocation.rows[i].cells[0].appendChild(document
				.createTextNode($(productLocation[i - 1]).text()));
		tableOtherLocation.rows[i].insertCell(1).style.textAlign = 'left';
		tableOtherLocation.rows[i].cells[1].appendChild(document
				.createTextNode($(productName[i - 1]).text()));
		tableOtherLocation.rows[i].insertCell(2).style.textAlign = 'right';
		tableOtherLocation.rows[i].cells[2].appendChild(document
				.createTextNode(productQuantity[i - 1].innerHTML));
		totalQty += parseInt(productQuantity[i - 1].innerHTML);
		tableOtherLocation.rows[i].insertCell(3).style.textAlign = 'right';
		var value = $(productType[i - 1]).text();
		tableOtherLocation.rows[i].cells[3].appendChild(document
				.createTextNode(packageTypeToMyanmar(value)));
	}
	var tableLocationForBox = document
			.getElementById("salesItemsListLocationForBox");
	for (var j = tableLocationForBox.rows.length; 1 < j; j--) {
		if (j == 1)
			break;
		tableLocationForBox.deleteRow(j - 1);
	}
	for (var i = 1; i <= productCode.length; i++) {
		tableLocationForBox.insertRow(i);
		if (productCode[i - 1].value == "") {
			continue;
		}
		tableLocationForBox.rows[i].insertCell(0).style.textAlign = 'left';
		tableLocationForBox.rows[i].cells[0].appendChild(document
				.createTextNode($(productName[i - 1]).text()));
		tableLocationForBox.rows[i].insertCell(1).style.textAlign = 'right';
		tableLocationForBox.rows[i].cells[1].appendChild(document
				.createTextNode(productQuantity[i - 1].innerHTML));
		tableLocationForBox.rows[i].insertCell(2).style.textAlign = 'left';
		var value = $(productType[i - 1]).text();
		tableLocationForBox.rows[i].cells[2].appendChild(document
				.createTextNode(packageTypeToMyanmar(value)));
	}
	if (parseInt($("#printtoalBalance").text()) < 0) {
		document.getElementById("lblPrinttoalBalance").innerHTML = "ပိုငွေ:";
	}
	printing("printArea", "", "", "printInovice");
}

function printInvoiceAvailable(edit, customBoId) {
	document.getElementById("printSalesDate").innerHTML = document
			.getElementById("salesDate").value;
	document.getElementById("printSalesDateOtherLocation").innerHTML = document
			.getElementById("salesDate").value;
	document.getElementById("printSalesDateForBox").innerHTML = document
			.getElementById("salesDate").value;
	document.getElementById("printSalesLocation").innerHTML = document
			.getElementById("salesLocationName").innerHTML;
	document.getElementById("printSalesLocationOtherLocation").innerHTML = document
			.getElementById("salesLocationName").innerHTML;
	document.getElementById("printSalesLocationForBox").innerHTML = document
			.getElementById("salesLocationName").innerHTML;
	document.getElementById("printSalesInvNo").innerHTML = document
			.getElementById("salesInvoice").value;
	document.getElementById("printSalesInvNoOtherLocation").innerHTML = document
			.getElementById("salesInvoice").value;
	document.getElementById("printSalesInvNoForBox").innerHTML = document
			.getElementById("salesInvoice").value;
	if ($(document.getElementById("printDiscountCount")).length > 0) {
		document.getElementById("printDiscountCount").innerHTML = document
				.getElementById("salesDiscountPer").value;
		document.getElementById("printSalesDiscount").innerHTML = document
				.getElementById("salesDiscount").innerHTML;
	}
	document.getElementById("printSalesAdditionCash").innerHTML = document
			.getElementById("salesCashDiscount").value;
	document.getElementById("printLuckyDraw").innerHTML = document
			.getElementById("luckyDrawPaid").value;
	document.getElementById("printSalesNetAmount").innerHTML = document
			.getElementById("dailysales-netAmount").innerHTML;
	document.getElementById("printPayAmount").innerHTML = document
			.getElementById("salePaid").value ? document
			.getElementById("salePaid").value : "0";
	document.getElementById("printTime").innerHTML = printTime();
	var custList = document.getElementById("saleCustomerId");
	var custname = custList.options[custList.selectedIndex].text;
	if (custname.indexOf(",") > -1) {
		custname = custname.substring(0, custname.indexOf(","));
	}
	var custId = custList.options[custList.selectedIndex].value.split(',');
	document.getElementById("printSalesName").innerHTML = custname;
	document.getElementById("printSalesNameOtherLocation").innerHTML = custname;
	document.getElementById("printSalesNameForBox").innerHTML = custname;
	var productName = document.querySelectorAll("#dailySalesProductName");
	var productType = document.querySelectorAll("#productAllPrice");
	if (document.querySelectorAll("#dailySalesProductPrice").length > 0) {
		var productPrice = document.querySelectorAll("#dailySalesProductPrice");
	} else {
		var productPrice = document.querySelectorAll("#productAllPrice");
	}
	var productQuantity = document.querySelectorAll("#dailySalesquantity");
	var productTotal = document.querySelectorAll("#dailySalestotal");
	var productCode = document.querySelectorAll("#salesProduct");
	var tableLocationForBox = document
			.getElementById("salesItemsListLocationForBox");
	for (var j = tableLocationForBox.rows.length; 1 < j; j--) {
		if (j == 1)
			break;
		tableLocationForBox.deleteRow(j - 1);
	}
	for (var i = 1; i <= productCode.length; i++) {
		tableLocationForBox.insertRow(i);
		if (productCode[i - 1].value == "") {
			continue;
		}
		tableLocationForBox.rows[i].insertCell(0).style.textAlign = 'left';
		tableLocationForBox.rows[i].cells[0].appendChild(document
				.createTextNode($(productName[i - 1]).text()));
		// table.rows[i].cells[1].appendChild(document
		// .createTextNode(productName[i - 1].innerHTML));
		tableLocationForBox.rows[i].insertCell(1).style.textAlign = 'right';
		tableLocationForBox.rows[i].cells[1].appendChild(document
				.createTextNode(productQuantity[i - 1].value));
		tableLocationForBox.rows[i].insertCell(2).style.textAlign = 'left';
		var value = $(productType[i - 1]).val();
		/*
		 * if(value==null) continue;
		 */
		if ($(document.getElementById("dailySales-available-location")).length > 0
				|| edit == "edit") {
			if (value != null)
				tableLocationForBox.rows[i].cells[2].appendChild(document
						.createTextNode(packageTypeToMyanmar($(
								productType[i - 1]).val().split(',')[1])));
		} else {
			tableLocationForBox.rows[i].cells[2].appendChild(document
					.createTextNode(packageTypeToMyanmar($(productType[i - 1])
							.text().split(',')[1])));
		}
	}
	var table = document.getElementById("salesItemsList");
	for (var j = table.rows.length; 1 < j; j--) {
		if (j == 1)
			break;
		table.deleteRow(j - 1);
	}
	for (var i = 1; i <= productCode.length; i++) {
		table.insertRow(i);
		if (productCode[i - 1].value == "") {
			continue;
		}
		table.rows[i].insertCell(0).style.textAlign = 'left';
		table.rows[i].cells[0].appendChild(document.createTextNode($(
				productName[i - 1]).text()));
		// table.rows[i].cells[1].appendChild(document
		// .createTextNode(productName[i - 1].innerHTML));
		table.rows[i].insertCell(1).style.textAlign = 'right';
		table.rows[i].cells[1].appendChild(document
				.createTextNode(productQuantity[i - 1].value));
		table.rows[i].insertCell(2).style.textAlign = 'left';
		var value = $(productType[i - 1]).val();
		/*
		 * if(value==null) continue;
		 */
		if ($(document.getElementById("dailySales-available-location")).length > 0
				|| edit == "edit") {
			if (value != null)
				table.rows[i].cells[2].appendChild(document
						.createTextNode(packageTypeToMyanmar($(
								productType[i - 1]).val().split(',')[1])));
		} else {
			table.rows[i].cells[2].appendChild(document
					.createTextNode(packageTypeToMyanmar($(productType[i - 1])
							.text().split(',')[1])));
		}
		table.rows[i].insertCell(3).style.textAlign = 'right';
		if ($(document.getElementById("dailySales-available-location")).length > 0) {
			var select = $(productPrice[i - 1]);
			table.rows[i].cells[3].appendChild(document.createTextNode($(
					":selected", select).text()));
		} else {
			table.rows[i].cells[3].appendChild(document.createTextNode($(
					productPrice[i - 1]).val()));
		}
		table.rows[i].insertCell(4).style.textAlign = 'right';
		table.rows[i].cells[4].appendChild(document.createTextNode($(
				productTotal[i - 1]).text()));
		/*
		 * table.rows[i].insertCell(6).style.textAlign = 'right'; var select =
		 * $(productLocation[i - 1]); var stringLoc = $(":selected",
		 * select).text(); var loc = stringLoc.split(",");
		 * //table.rows[i].cells[6].appendChild(document.createTextNode(loc[0]));
		 */
	}
	var productLocation = document
			.querySelectorAll("#dailySales-available-location");
	var tableOtherLocation = document.getElementById("salesItemsListLocation");
	for (var j = tableOtherLocation.rows.length; 1 < j; j--) {
		if (j == 1)
			break;
		tableOtherLocation.deleteRow(j - 1);
	}

	var totalQty = 0;
	var branchLocation = true;
	for (var i = 1; i <= productCode.length; i++) {
		tableOtherLocation.insertRow(i);
		if (productCode[i - 1].value == "") {
			continue;
		}
		var select = $(productLocation[i - 1]);
		var stringLoc = $(":selected", select).text();
		var loc = stringLoc.split(",");
		if (loc[0] == document.getElementById("salesLocationName").innerHTML) {
			continue;
		}
		branchLocation = false;
		tableOtherLocation.rows[i].insertCell(0).style.textAlign = 'right';
		tableOtherLocation.rows[i].cells[0].appendChild(document
				.createTextNode(loc[0]));
		tableOtherLocation.rows[i].insertCell(1).style.textAlign = 'left';
		tableOtherLocation.rows[i].cells[1].appendChild(document
				.createTextNode($(productName[i - 1]).text()));
		tableOtherLocation.rows[i].insertCell(2).style.textAlign = 'right';
		tableOtherLocation.rows[i].cells[2].appendChild(document
				.createTextNode(productQuantity[i - 1].value));
		totalQty += parseInt(productQuantity[i - 1].value);
		tableOtherLocation.rows[i].insertCell(3).style.textAlign = 'right';
		var value = $(productType[i - 1]).val();
		if ($(document.getElementById("dailySales-available-location")).length > 0
				|| edit == "edit") {
			if (value != null)
				tableOtherLocation.rows[i].cells[3].appendChild(document
						.createTextNode(packageTypeToMyanmar($(
								productType[i - 1]).val().split(',')[1])));
		} else {
			tableOtherLocation.rows[i].cells[3].appendChild(document
					.createTextNode(packageTypeToMyanmar($(productType[i - 1])
							.text().split(',')[1])));
		}
	}
	document.getElementById("printTimeLocation").innerHTML = printTime();
	document.getElementById("printTimeForBox").innerHTML = printTime();
	tableOtherLocation.insertRow(tableOtherLocation.rows.length);
	tableOtherLocation.rows[tableOtherLocation.rows.length - 1].insertCell(0).style.textAlign = 'right';
	tableOtherLocation.rows[tableOtherLocation.rows.length - 1].cells[0]
			.appendChild(document.createTextNode(""));
	tableOtherLocation.rows[tableOtherLocation.rows.length - 1].insertCell(1).style.textAlign = 'right';
	tableOtherLocation.rows[tableOtherLocation.rows.length - 1].cells[1]
			.appendChild(document.createTextNode(""));
	tableOtherLocation.rows[tableOtherLocation.rows.length - 1].insertCell(2).style.textAlign = 'right';
	tableOtherLocation.rows[tableOtherLocation.rows.length - 1].cells[2]
			.appendChild(document.createTextNode(totalQty));
	var printAreaOtherLocation = "Other";
	if (branchLocation == false) {
		printAreaOtherLocation = "printAreaOtherLocation";
	}
	var other = document.getElementById("other").value;
	var otherCost = document.getElementById("othercost").value;
	document.getElementById("printSalesOtherCostName").innerHTML = other;
	document.getElementById("printSalesOtherCost").innerHTML = otherCost;
	getTotalBalance(custId[0], 'printArea', printAreaOtherLocation,
			'printtoalBalance', edit, 'printAreaForBox', customBoId);
}

function packageTypeToMyanmar(type) {
	switch (type) {
	case "METER":
        return "meter";
    case "YARD":
    	return "yard";
	case "PACKING":
		return "ထုပ်";
		break;
	case "INDIVIDUAL":
		return "ခု";
		break;
	case "CARD":
		return "ကဒ်";
		break;
	case "BOX":
		return "ဖာ";
		break;
	case "DRUM":
		return "ပီပါ";
		break;
	case "GALLON":
		return "ဂါလံ";
		break;
	case "HALF_GALLON":
		return "ဂါလံဝက်";
		break;
	case "BOTTLE":
		return "ပုလင်း";
		break;
	case "CARTON":
		return "carton";
		break;
	case "BAG":
		return "အိတ်";
		break;
	case "PIECES":
		return "ခု";
		break;
	}
}

function printing(area, otherLocationArea, printAreaForBox, customBoId,edit) {
	if (area == "printPaymentArea") {
		var prtContent = document.getElementById(area);
		var firstData = prtContent.innerHTML;
		firstOne(firstData);
	} else {
		var prtContent = document.getElementById(area);
		var firstData = prtContent.innerHTML;
		var secondData = $("#printAreaOtherLocation")[0].innerHTML;
		var thirdData = $("#printAreaForBox")[0].innerHTML;
		firstOne(firstData);
		secondPrint(secondData);
		thirdFunction(thirdData);
		if ("printInovice" != customBoId && edit!="edit") {
			if (customBoId == "RETAIL") {
				$("#dailySaleRetail").click();
				$("#retailSale").click();
			} else {
				$("#dailySale").click();
				$("#outletSale").click();
			}
		}
	}

	function firstOne(data) {
		var WinPrint = window
				.open('', '_blank1',
						'left=0,top=0,width=400,height=500,toolbar=0,scrollbars=0,status=0');
		WinPrint.document.write(data);
		WinPrint.document.close();
		WinPrint.focus();
		WinPrint.print();
		WinPrint.close();
	}

	function secondPrint(data) {
		var prtContentLocation = document.getElementById(otherLocationArea);
		if (otherLocationArea == "printAreaOtherLocation") {
			var WinPrint1 = window
					.open('', '_blank2',
							'left=0,top=0,width=400,height=500,toolbar=0,scrollbars=0,status=0');
			WinPrint1.document.write(data);
			WinPrint1.document.close();
			WinPrint1.focus();
			WinPrint1.print();
			WinPrint1.close();
		}
	}

	function thirdFunction(data) {
		var prtContentAreaForBox = document.getElementById(printAreaForBox);
		if (printAreaForBox == "printAreaForBox") {
			var WinPrintArea = window
					.open('', '_blank3',
							'left=0,top=0,width=400,height=500,toolbar=0,scrollbars=0,status=0');
			WinPrintArea.document.write(data);
			WinPrintArea.document.close();
			WinPrintArea.focus();
			WinPrintArea.print();
			WinPrintArea.close();
		}
	}
}

function getTotalBalance(custId, area, otherLocationArea, balArea, edit,
		printAreaForBox, customBoId) {
	var result = 0;
	var parameter = {};
	parameter["customerId"] = custId;
	var request = new XMLHttpRequest;
	request.onreadystatechange = readyFunction;
	request.open("GET", "getTotalBalance?input=" + JSON.stringify(parameter),
			"true");
	request.send();
	loading();

	function readyFunction() {
		if (request.readyState != 4)
			return;

		if (request.status != 200) {
			alert("Error :" + request.status);
			return;
		}
		if (request.responseText != "") {
			var json = parseNewJson(request.responseText);
			if ($(document.getElementById("lastPayment")).length > 0) {
				document.getElementById("printSalesCredit").innerHTML = document
						.getElementById("totalBalance").innerHTML;
				document.getElementById("printPaymentBalance").innerHTML = parseInt(document
						.getElementById("totalBalance").innerHTML)
						- (parseInt(document
								.getElementById("printPaymentAmount").innerHTML) + parseInt(document
								.getElementById("printLuckyDraw").innerHTML));
			} else {
				var str = json.totalBal.split(",");
				if (str[1] == "RETAIL") {
					document.getElementById("printSalesCredit").innerHTML = 0;
					document.getElementById("printtoalBalance").innerHTML = 0;
				} else {
					if (edit == "edit") {
						var salePaid=(document.getElementById("salePaid").innerHTML).replace(/,/g,'');
						var netAmount=(document.getElementById("printSalesNetAmount").innerHTML).replace(/,/g,'');
						document.getElementById("printPayAmount").innerHTML = document
								.getElementById("salePaid").innerHTML;
						document.getElementById("printLuckyDraw").innerHTML = document
								.getElementById("luckyDrawPaid").innerHTML;
						document.getElementById("printSalesCredit").innerHTML = parseInt(document
								.getElementById("purchaseOrder_totalCredit").innerHTML);
						document.getElementById("printtoalBalance").innerHTML = (parseInt(document
								.getElementById("purchaseOrder_totalCredit").innerHTML) + parseInt(netAmount))
								- ((parseInt(salePaid ? salePaid: "0")) + parseInt(document.getElementById("luckyDrawPaid").innerHTML));
					} else {
                        var amt = 0;
						if (balArea == 'supplierVouncher') {
							document.getElementById("printSalesCredit").innerHTML = parseInt(str[0])
									- parseInt(document
											.getElementById("printSalesNetAmount").innerHTML);
							amt = parseInt(str[0]);
						} else {
							document.getElementById("printSalesCredit").innerHTML = parseInt(str[0]);
							amt = parseInt(str[0])
									+ parseInt(document
											.getElementById("printSalesNetAmount").innerHTML);
						}
						var leftpaid = amt
								- (parseInt(document.getElementById("salePaid").value ? document
										.getElementById("salePaid").value
										: "0") + parseInt(document
										.getElementById("luckyDrawPaid").value ? document
										.getElementById("luckyDrawPaid").value
										: "0"));

						document.getElementById("printtoalBalance").innerHTML = leftpaid;
					}
				}
			}
			if (parseInt($("#printtoalBalance").text()) < 0) {
				document.getElementById("lblPrinttoalBalance").innerHTML = "ပိုငွေ:";
			}
			hideLoading();
			result = 1;
			printing(area, otherLocationArea, printAreaForBox, customBoId,edit);
		}
	}
}

function printInvoiceReturn(custBoId) {
	var tableReturn = document.getElementById("saleReturnList");
	var productQuantity = document.querySelectorAll("#return_product_qty");
	var productName = document.querySelectorAll("#return_product_name");
	var productPurchasePrice = document
			.querySelectorAll("#return_Purchase_Price");
	console.log("Price", productPurchasePrice);
	for (var j = tableReturn.rows.length; 1 < j; j--) {
		if (j == 1)
			break;
		tableReturn.deleteRow(j - 1);
	}
	var total = 0;
	for (var i = 1; i <= productName.length; i++) {
		tableReturn.insertRow(i);
		if (productName[i - 1].innerHTML == "") {
			continue;
		}
		tableReturn.rows[i].insertCell(0).style.textAlign = 'left';
		tableReturn.rows[i].cells[0].appendChild(document.createTextNode($(
				productName[i - 1]).text()));
		tableReturn.rows[i].insertCell(1).style.textAlign = 'left';
		tableReturn.rows[i].cells[1].appendChild(document.createTextNode($(
				productQuantity[i - 1]).val()));
		tableReturn.rows[i].insertCell(2).style.textAlign = 'left';
		tableReturn.rows[i].cells[2].appendChild(document.createTextNode($(
				productPurchasePrice[i - 1]).val()));
		total += $(productQuantity[i - 1]).val()
				* $(productPurchasePrice[i - 1]).val();
		tableReturn.rows[i].insertCell(3).style.textAlign = 'left';
		tableReturn.rows[i].cells[3].appendChild(document.createTextNode($(
				productQuantity[i - 1]).val()
				* $(productPurchasePrice[i - 1]).val()));
	}
	document.getElementById("printReturnCredit").innerHTML = document
			.getElementById("totalCredit").innerHTML;
	document.getElementById("printReturnTotal").innerHTML = Number(document
			.getElementById("totalCredit").innerHTML)
			- total;
	/*
	 * loadAction(document.getElementById("customermenu"), 'customer');
	 * document.getElementById("search-text").value = custBoId; search(this,
	 * 'ID', 'CUSTOMER'); this.id = custBoId; aboutDetail(this, 'CUSTOMER');
	 */
	var prtContent = document.getElementById('printAreaReturn');
	var WinPrint = window
			.open('', '_blank1',
					'left=0,top=0,width=400,height=500,toolbar=0,scrollbars=0,status=0');
	WinPrint.document.write(prtContent.innerHTML);
	WinPrint.document.close();
	WinPrint.focus();
	WinPrint.print();
	WinPrint.close();
}

function printInvoice(edit) {
	document.getElementById("printSalesDate").innerHTML = document
			.getElementById("salesDate").value;
	document.getElementById("printSalesLocation").innerHTML = document
			.getElementById("salesLocationName").innerHTML;
	document.getElementById("printSalesInvNo").innerHTML = document
			.getElementById("salesInvoice").value;
	document.getElementById("printDiscountCount").innerHTML = document
			.getElementById("salesDiscountPer").value;
	document.getElementById("printSalesDiscount").innerHTML = document
			.getElementById("salesDiscount").innerHTML;
	document.getElementById("printSalesAdditionCash").innerHTML = document
			.getElementById("salesCashDiscount").value;
	document.getElementById("printSalesNetAmount").innerHTML = document
			.getElementById("dailysales-netAmount").innerHTML;
	document.getElementById("printPayAmount").innerHTML = document
			.getElementById("salePaid").value ? document
			.getElementById("salePaid").value : "0";
	document.getElementById("printTime").innerHTML = printTime();
	document.getElementById("printLuckyDraw").innerHTML = document
			.getElementById("luckyDrawPaid").value ? document
			.getElementById("luckyDrawPaid").value : 0;
	var custList = document.getElementById("saleCustomerId");
	var custname = custList.options[custList.selectedIndex].text;
	if (custname.indexOf(",") > -1) {
		custname = custname.substring(0, custname.indexOf(","));
	}
	document.getElementById("printSalesName").innerHTML = custname;
	var productLocation = document
			.querySelectorAll("#dailySales-available-location");
	var productName = document.querySelectorAll("#dailySalesProductName");
	var productType = document.querySelectorAll("#productAllPrice");

	if (document.querySelectorAll("#dailySalesPackagePrice").length > 0) {
		var productPrice = document.querySelectorAll("#dailySalesPackagePrice");
	} else {
		var productPrice = document.querySelectorAll("#productAllPrice");
	}

	var productQuantity = document.querySelectorAll("#dailySalesquantity");
	var productTotal = document.querySelectorAll("#dailySalestotal");
	var productCode = document.querySelectorAll("#salesProduct");
	var table = document.getElementById("salesItemsList");
	for (var j = table.rows.length; 1 < j; j--) {
		if (j == 1)
			break;
		table.deleteRow(j - 1);
	}
	for (var i = 1; i <= productName.length; i++) {
		table.insertRow(i);
		if (productCode[i - 1].value == "") {
			continue;
		}
		table.rows[i].insertCell(0).style.textAlign = 'left';
		table.rows[i].cells[0].appendChild(document.createTextNode($(
				productName[i - 1]).text()));
		table.rows[i].insertCell(1).style.textAlign = 'right';
		table.rows[i].cells[1].appendChild(document
				.createTextNode(productQuantity[i - 1].value));
		/*
		 * table.rows[i].insertCell(2).style.textAlign='left'; var value =
		 * $(productType[i-1]).val(); if(value==null) continue;
		 * table.rows[i].cells[2].appendChild(document.createTextNode($(productType[i-1]).val()));
		 */
		table.rows[i].insertCell(2).style.textAlign = 'left';
		var select = $(productType[i - 1]);
		var stringLoc = $(":selected", select).text();
		var loc = stringLoc.split(",");
		table.rows[i].cells[2].appendChild(document
				.createTextNode(packageTypeToMyanmar(loc[0])));
		table.rows[i].insertCell(3).style.textAlign = 'right';
		if ($(document.getElementById("dailySales-available-location")).length > 0) {
			var select = $(productPrice[i - 1]);
			table.rows[i].cells[3].appendChild(document.createTextNode($(
					":selected", select).text()));
		} else {
			if ($(productPrice[i - 1]).val() != null)
				table.rows[i].cells[3].appendChild(document.createTextNode($(
						productPrice[i - 1]).val()));
		}
		table.rows[i].insertCell(4).style.textAlign = 'right';
		table.rows[i].cells[4].appendChild(document.createTextNode($(
				productTotal[i - 1]).text()));
	}

	var custId = document.getElementById("saleCustomerId").value;
	getTotalBalance(custId, 'printArea', 'printtoalBalance',
			'supplierVouncher', edit);
}

function myp(event) {
	var d = event.target.parentNode.parentNode.parentNode.rowIndex - 1;
}
var pandLlid = "";

function profitAndLossByLocation(element) {
	if ($("#porderlocationname").val() == "ALL") {
		alert("Please Choose Location Name");
		return;
	}
	// document.getElementById("pandfLocationId").innerHTML=document.getElementById("detail-location-boId").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").attributes["class"].value = "dialog";
				/*
				 * $('#pandfStartDate').datepicker({ dateFormat : 'dd/mm/yy' });
				 * $('#pandfEndDate').datepicker({ dateFormat : 'dd/mm/yy' });
				 */
				$("#pandfStartDate")
						.datepicker(
								{
									dateFormat : 'mm/yy',
									changeMonth : true,
									changeYear : true,
									showButtonPanel : true,

									onClose : function(dateText, inst) {
										var month = $(
												"#ui-datepicker-div .ui-datepicker-month :selected")
												.val();
										var year = $(
												"#ui-datepicker-div .ui-datepicker-year :selected")
												.val();
										$(this).val(
												$.datepicker.formatDate(
														'mm/yy', new Date(year,
																month, 1)));
									}
								});
				$("#pandfStartDate").focus(function() {
					$(".ui-datepicker-calendar").hide();
					$("#ui-datepicker-div").position({
						my : "center top",
						at : "center bottom",
						of : $(this)
					});
				});
				// special
				document.getElementById("detail").innerHTML = "";
				document.getElementById("pandfStartDate").value = document
						.getElementById("specialDate").value ? document
						.getElementById("specialDate").value : thisMonth();
				var index = document.getElementById("porderlocationname").selectedIndex;
				var value = document.getElementById("porderlocationname").options[index].value;
				pandLlid = value;
			}
		}
	};
	var parameter = {};
	loading();
	request.open("GET", "profitAndLossForm?input=" + JSON.stringify(parameter),
			true);
	request.send(null);
}

function calculateProfitandLoss() {
	pandLlid = document.getElementById("detail-location-boId") ? document
			.getElementById("detail-location-boId").innerHTML : pandLlid;
	var startDate = document.getElementById("pandfStartDate").value ? document
			.getElementById("pandfStartDate").value : thisMonth();
	// var other = document.getElementById("pandfOtherCost").value ? document
	// .getElementById("pandfOtherCost").value : "0";
	var expense = document.getElementById("pandfExpense").value ? document
			.getElementById("pandfExpense").value : "0";
	var tax = document.getElementById("pandfTax").value ? document
			.getElementById("pandfTax").value : "0";
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("pandfStartDate").value = startDate;
				// document.getElementById("pandfOtherCost").value = other;
				document.getElementById("pandfExpense").value = expense;
				document.getElementById("pandfTax").value = tax;
				document.getElementById("pandfresultdiv").innerHTML = request.responseText;
				document.getElementById("calpandlbut").className = 'hide';
				document.getElementById("cancelpandlbut").className = 'hide';
			}
		}
	};
	var parameter = {};
	parameter.pandLlid = pandLlid;
	parameter.startDate = startDate;
	// parameter.other = other;
	parameter.expense = expense;
	parameter.tax = tax;
	console.log(parameter);
	request.open("GET", "calculateProfitAndLoss?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
	loading();
}

function thisMonth() {
	var month = today();
	month = month.substring(month.indexOf("/") + 1, month.length);
	return month;
}

function changePriceTransfer() {
	alert("price="
			+ document.getElementById("transfer-product-price").innerHTML);
}

function stockReportForm(description) {
	// document.getElementById("pandfLocationId").innerHTML=document.getElementById("detail-location-boId").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").attributes["class"].value = "dialog";
				/*
				 * $('#pandfStartDate').datepicker({ dateFormat : 'dd/mm/yy' });
				 * $('#pandfEndDate').datepicker({ dateFormat : 'dd/mm/yy' });
				 */

				$("#pandfStartDate")
						.datepicker(
								{
									dateFormat : 'mm/yy',
									changeMonth : true,
									changeYear : true,
									showButtonPanel : true,

									onClose : function(dateText, inst) {
										var month = $(
												"#ui-datepicker-div .ui-datepicker-month :selected")
												.val();
										var year = $(
												"#ui-datepicker-div .ui-datepicker-year :selected")
												.val();
										$(this).val(
												$.datepicker.formatDate(
														'mm/yy', new Date(year,
																month, 1)));
									}
								});

				$("#pandfStartDate").focus(function() {
					$(".ui-datepicker-calendar").hide();
					$("#ui-datepicker-div").position({
						my : "center top",
						at : "center bottom",
						of : $(this)
					});
				});
			}
		}
	};
	var parameter = {};
	request.open("GET", "stockSpecialReportForm?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

function stockReport(description) {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				document.getElementById("detail").innerHTML = request.responseText;
				document.getElementById("monthStock").innerHTML = document
						.getElementById("specialDate").value;
				// document.getElementById("detail-table").className = "show";
			}
		}
	};
	/*
	 * var start=document.getElementById("specialStartDate").value; var
	 * end=document.getElementById("specialEndDate").value;
	 */
	var month = document.getElementById("specialDate").value;
	if (month == "") {
		alert("Please Fill View Month");
		return;
	}
	var index = document.getElementById("speciallocationname").selectedIndex;
	var value = document.getElementById("speciallocationname").options[index].value;
	if (value == "ALL") {
		alert("Please Choose Location Name");
		return;
	}
	var parameter = {};
	/*
	 * parameter.start=start; parameter.end=end;
	 */
	parameter.value = value;
	parameter.month = month;
	console.log(parameter);
	request.open("GET",
			"stockSpecialReport?input=" + JSON.stringify(parameter), true);
	request.send(null);
}

function salesLedgerReport(description) {
	var month = document.getElementById("specialDate").value;
	if (month == "")
		return;
	var index = document.getElementById("speciallocationname").selectedIndex;
	var value = document.getElementById("speciallocationname").options[index].value;
	var name = document.getElementById("speciallocationname").options[index].text;
	if (value == "ALL")
		return;
	var parameter = {};
	/*
	 * parameter.start=start; parameter.end=end;
	 */
	parameter.value = value;
	parameter.name = name;
	parameter.month = month;
	parameter.description = description;
	console.log(parameter);
	window.open("salesLedgerReport/Saleledger.xlsx?input="
			+ JSON.stringify(parameter), "_blank");
}

function checkPayment() {
	var payment = document.getElementById("payment_amount").value ? document
			.getElementById("payment_amount").value : 0;
	var discount = document.getElementById("discount_amount").value ? document
			.getElementById("discount_amount").value : 0;
	var luckyDrawAmount = $('#luckyDrawAmount').val().trim() ? $(
			'#luckyDrawAmount').val().trim() : 0;
    var netAmount = document.getElementById("lastPayment").innerHTML;
    var total = Number(payment) + Number(discount) + Number(luckyDrawAmount);
    var balance = Number(netAmount) - Number(total);
	document.getElementById("payment_balance").innerHTML = balance;
}

function checkDiscount() {
	var discount = document.getElementById("discount_amount").value ? document
			.getElementById("discount_amount").value : 0;
	var payment = document.getElementById("payment_amount").value ? document
			.getElementById("payment_amount").value : 0;
	var netAmount = document.getElementById("lastPayment").innerHTML;
	var balance = (Number(netAmount) - Number(payment)) - Number(discount);
	document.getElementById("payment_balance").innerHTML = balance;
}
