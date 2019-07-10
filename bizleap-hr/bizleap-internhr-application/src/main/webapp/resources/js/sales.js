var totalDis = 0;
var rowtemp = 0;

function getCustomerBoId(event) {
	var custList = document.getElementById("customerList");
	var index = -1;
	for (var i = 0; i < customerList.options.length; i++) {
		if (customerList.options[i].value == event.value) {
			index = i;
			break;
		}
	}
	if (index == -1) {
		alert("Please choose Customer!");
		document.getElementById("daily-saleCustomerId").innerHTML = "";
		document.getElementById("saleCustomerId").value = "";
		document.getElementById("searchCustomer").setAttribute('style',
				'color:black;width: 250px;');
		return;
	} else {
		var customerBoId = $(custList.options[index]).attr("id");
		document.getElementById("daily-saleCustomerId").innerHTML = customerBoId;
		getValidRisk(customerBoId);
		$("#searchCustomer").focus();
	}
}

function getTransferCustomerBoId(event) {
	var custList = document.getElementById("customerList");
	var index = -1;
	for (var i = 0; i < customerList.options.length; i++) {
		if (customerList.options[i].value == event.value) {
			index = i;
			break;
		}
	}
	if (index == -1) {
		alert("Please choose Customer!");
		document.getElementById("daily-saleCustomerId").innerHTML = "";
		document.getElementById("saleCustomerId").value = "";
		document.getElementById("searchCustomer").setAttribute('style',
				'color:black;width: 250px;');
		return;
	} else {
		var customerBoId = $(custList.options[index]).attr("id");
		document.getElementById("daily-saleCustomerId").innerHTML = customerBoId;
		$("#searchCustomer").focus();
	}
}

function getValidRisk(customerBoId) {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState != 4)
			return;
		if (request.status != 200) {
			alert("Error return " + request.status);
			return;
		}
		// hideLoading();
		if (request.responseText != "") {
			var json = parseNewJson(request.responseText);
			if (json != null) {
				var valid = json.valid.toString();
				isCustomerValid(valid);
			}
		}
	}
	var parameter = {};
	parameter["customerBoId"] = customerBoId;
	console.log("parameter", parameter);
	request.open("GET", "searchValidRiskByCustomerBoId?input="
			+ JSON.stringify(parameter), "true");
	connectionStatus = true; // working
	request.send();
	// loading();
}

function isCustomerValid(isCustomerValid) {
	if (isCustomerValid == "false") {
		alert("Customer is beyond the Credit Period");
		document.getElementById("searchCustomer").setAttribute('style',
				'color:red;width: 250px;');
	} else {
		console.log("IsCustomerValid", isCustomerValid);
		document.getElementById("searchCustomer").setAttribute('style',
				'color:black;width: 250px;');
	}
}

function salesCancelOnClick(description, isRoute) {
	document.getElementById('dialog-temp').className = 'hide';
	$('#workspace').removeClass('myFilter');
	if (isRoute) {
		$("#" + $('#detail-location-name').attr("value")).click(
				clickOnRouteList());
	} else
		$("#" + $('#detail-location-name').attr("value")).click();
}

function clickOnRouteList() {
	$('#tab2').prop('checked', true);
}

function dailySalesForm(description, retailDaily, entityType) {
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	if (entityType == "SUPPLIER") {
		var customerBoId = document.getElementById("detail-customer-boId").innerHTML;
	}
	var request = new XMLHttpRequest;
	$('#workspace').addClass("myFilter");
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("salesDate").focus();
				document.getElementById("salesDate").valueAsDate = new Date();
			} else {
				alert("Please try again. Error code is " + request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["customerBoId"] = customerBoId;
	if (retailDaily == 'retailDaily') {
		parameter["retailDaily"] = retailDaily;
	} else {
		parameter["retailDaily"] = "";
	}
	loading();
	request.open("GET", "dailySalesForm?input=" + JSON.stringify(parameter),
			true);
	request.send(null);
}

function cancelForDailySale() {
	document.getElementById('dialog-temp').className = 'hide';
	document.getElementById('workspace').classList.remove('myFilter');
	this.id = document.getElementById("detail-location-boId").innerHTML;
	$(this).attr("data-type",
			document.getElementById("detail-location-type").innerHTML);
	aboutDetail(this, 'LOCATION');
}

function cancelForPurchase() {
	document.getElementById('dialog-temp').className = 'hide';
	document.getElementById('workspace').classList.remove('myFilter');
	this.id = document.getElementById("detail-customer-boId").innerHTML;
	// $(this).attr("data-type",
	// document.getElementById("detail-location-type").innerHTML);
	aboutDetail(this, 'SUPPLIER');
}

function getInvoiceForm(description, retailDaily) {
	var locationId = 'LOC00001';
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				$('#salesDate').datepicker({
					dateFormat : 'dd/mm/yy'
				});
				document.getElementById("salesDate").focus();
				document.getElementById("salesDate").value = today();
			} else {
				alert("Please try again. Error code is " + request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	if (retailDaily == 'retailDaily') {
		parameter["retailDaily"] = retailDaily;
	} else {
		parameter["retailDaily"] = "";
	}
	loading();
	request.open("GET", "dailySalesForm?input=" + JSON.stringify(parameter),
			true);
	request.send(null);
}

function addSalesConverPackageRow(parentId) {
	var table = document.getElementById(parentId);
	var childList = table.getElementsByTagName("tr");
	var newinnerhtml = childList[1].innerHTML;
	var row = table.insertRow(childList.length);
	$(row).html(newinnerhtml);
	$(row).find('input')[0].focus();
}

function addSalesRow(parentId) {
	var table = document.getElementById(parentId);
	var childList = table.getElementsByTagName("tr");
	var newinnerhtml = childList[1].innerHTML;
	var row = table.insertRow(childList.length);
	$(row).html(newinnerhtml);
	$(row).attr("name", $(childList[1]).attr("name"));
	$(row).find('input')[0].focus();
}

function enterAdditionalCostRow(event, parentId) {
	if (event.which == 13 || event.keyCode == 13) {
		addAdditionalCostRow(parentId);
		return false;
	}
}

function addAdditionalCostRow(parentId) {
	var table = document.getElementById(parentId);
	var childList = table.getElementsByTagName("tr");
	var newinnerhtml = childList[2].innerHTML;
	var row = table.insertRow(childList.length);
	$(row).html(newinnerhtml);
	$(row).attr("name", $(childList[2]).attr("name"));
	console.log(" row ", $(row).find('input')[0]);
	$(row).find('input')[0].focus();
}

function addSalesRow1(parentId) {
	var table = document.getElementById(parentId);
	var childList = table.getElementsByTagName("tr");
	var newinnerhtml = childList[1].innerHTML;
	var row = table.insertRow(childList.length);
	$(row).html(newinnerhtml);
	$(row).attr("name", $(childList[1]).attr("name"));
	$(row).find('input')[0].focus();
}

function checkEnterNameEvent(event) {
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
					var dataList = document.getElementById("customerList");
					dataList.innerHTML = "";
					for ( var index in customerList) {
						var option = document.createElement("option");
						$(option).attr("id", customerList[index].id);
						$(option).attr("data-type", customerList[index].type);
						// $(option).attr("valid",customerList[index].isValidCredit);
						option.innerHTML = customerList[index].name + ' ( '
								+ customerList[index].classType + ' ) ( '
								+ customerList[index].city + ' ) ';
						dataList.appendChild(option);
					}
				}
			}
		}
		var parameter = {};
		parameter["locationBoId"] = $("#salesLocationId").text();
		parameter["originlocationBoId"]=$("#originLocationBoId").text();
		parameter["originCustomerBoId"] = $("#customerBoId").text();
		parameter["value"] = $("#searchCustomer").val();
		parameter["isRetailDaily"] = $("#isRetailDaily").text();
		parameter["channelOfDistribution"] = $("#channelOfDistribution").text();
		console.log("parameter", parameter);
		request.open("GET", "searchCustomersByName?input="
				+ JSON.stringify(parameter), "true");
		connectionStatus = true; // working
		request.send();
		loading();
		return false;
	}
	return true;
}

function getOutputQuantity() {
	var count = document.getElementsByName("convertPackage_Qty").length;
	for (var i = 1; i < count; i++) {
		var input = document.getElementsByName("convertPackage_Qty")[i].value;
		var fromPackagingQuantity = document
				.getElementsByName("convertFromPackagingType")[i].value
				.split(",");
		var toPackagingQuantity = document.getElementsByName("toPackagingType")[i].value
				.split(",");
		if (fromPackagingQuantity[9] > toPackagingQuantity[1]) {
			document.getElementsByName("output_qty")[i].innerHTML = input
					* fromPackagingQuantity[9];
		} else {
			if ((fromPackagingQuantity[9] * input) % toPackagingQuantity[1] !== 0) {
				document.getElementsByName("output_qty")[i].innerHTML = "Invalid input";
			} else {
				document.getElementsByName("output_qty")[i].innerHTML = (fromPackagingQuantity[9] * input)
						/ toPackagingQuantity[1];
			}
		}

	}
}

function getConvertProductInfo(event, row) {
	if (event.which == 13 || event.keyCode == 13 || event.which == 9
			|| event.keyCode == 9) {
		var productCode = document
				.getElementById("convertPackage_Inventory_extraStock");
		var productName = document.getElementById("convertPackage_Name");
		var fromPackagingType = document.getElementById("convertPackage_Type");
		var toPackagingType = document.getElementById("convertPackageTo_Type");
		var tr = getParentByTagName(row, "tr");
		var index = -1;
		var d = row.parentNode.parentNode.rowIndex - 1;
		for (var i = 0; i < productList.options.length; i++) {
			if (productList.options[i].value == $(
					"[name=extraConvertProductcodeList]", tr).val()) {
				// document.getElementsByName("extrasalesProductcodeList")[d].value)
				// {
				index = i;
				break;
			}
		}
		if (index == -1) {
			alert("Please choose correct Product Code!");
			$(row).addClass("required");
			var id = row.parentNode.parentNode.id;
			errors[id] = "error";
			return false;
		}
		var boId = productCode.options[index].value;
		var name = productName.options[index].value;
		// var fromType = fromPackagingType.options[index].value;
		var toType = toPackagingType.options[index].value;
		// var qty = fromQty.options[index].value;
		document.getElementsByName("convertProductcodeList")[d].value = boId;
		document.getElementsByName("convertProductName")[d].innerHTML = name;
		// document.getElementsByName("convertFromPackagingType")[d].innerHTML =
		// fromType;
		var count = 1;
		$(document.getElementsByName("convertFromPackagingType")[d]).html("");
		for (var i = 0; i < fromPackagingType.length; i++) {
			var fromPack = fromPackagingType[i].value;
			var fromPackageString = fromPack.split(",");
			if (boId == fromPackageString[0]) {
				var option = $(document.createElement("option"));
				var select = $(document
						.getElementsByName("convertFromPackagingType")[d]);
				option.text(fromPackageString[1] + ',' + fromPackageString[3]
						+ ',' + fromPackageString[4]);
				option.attr("value", fromPackageString[1] + ','
						+ fromPackageString[2] + ',' + fromPackageString[3]
						+ ',' + fromPackageString[4] + ',' + fromPackageString);
				if (count == 1) {
					var qty = fromPackageString[2];
					count++;
				}
				select.append(option);
			}
		}
		document.getElementsByName("convertFromQty")[d].innerHTML = qty
				.split('.')[0];
		var packageTypeString = toType.split(";");
		$(document.getElementsByName("toPackagingType")[d]).html("");
		for (var t = 0; t < packageTypeString.length; t++) {
			var temp = packageTypeString[t];
			if (temp.trim() == "")
				continue;
			var tempPackage = temp.split(",");
			var option = $(document.createElement("option"));
			var select = $(document.getElementsByName("toPackagingType")[d]);
			option.text(tempPackage[1] + ', ' + tempPackage[6]);
			option.attr("value", tempPackage[1] + ',' + tempPackage[6]);
			select.append(option);
		}
		document.getElementsByName("convertPackage_Qty")[d].focus();
		$(row).removeClass("required");
		var id = row.parentNode.parentNode.id;
		if (errors[id])
			delete errors[id];
		return false;
	}
	return true;
}

function getProductInfo(event, row) {
	if (event.which == 13 || event.keyCode == 13 || event.which == 9
			|| event.keyCode == 9) {
		var productCode = document.getElementById("sales_Inventory_extraStock");
		var productName = document.getElementById("salesProductName");
		var brandName = document.getElementById("salesBrandName");
		var flavour = document.getElementById("salesFlavour");
		var productPrice = document.getElementById("salesProductPrice");
		var productCreditPrice = document
				.getElementById("salesProductCreditPrice");
		var productSpecialPrice = document
				.getElementById("salesProductSpecialPrice");
		var stockList = document.getElementById("sales_Inventory_Stock");
		var productDiscounted = document
				.getElementById("salesProductDiscounted");
		var packagingPrice = document.getElementById("salesPackagingPrice");
		var volumePriceString = document.getElementById("salesVolumePrice");
		var index = -1;
		var tr = getParentByTagName(row, "tr");
		var d = row.parentNode.parentNode.rowIndex - 1;
		for (var i = 0; i < productList.options.length; i++) {
			if (productList.options[i].value == $(
					"[name=extrasalesProductcodeList]", tr).val()) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			return false;
		}
		var boId = productCode.options[index].value;
		var name = productName.options[index].value;
		var brandName = brandName.options[index].value;
		var flavour = flavour.options[index].value;
		var price = productPrice.options[index].value;
		var creditPrice = productCreditPrice.options[index].value;
		var specialPrice = productSpecialPrice.options[index].value;
		// var discounted = productDiscounted.options[index].value;
		var packagePrice = packagingPrice.options[index].value;
		var volumePrice = volumePriceString.options[index].value;
		var packageTypeString = packagePrice.split(";");
		document.getElementsByName("salesProductcodeList")[d].value = boId;
		document.getElementsByName("dailySalesProductName")[d].innerHTML = name;
		document.getElementsByName("dailySalesBrandName")[d].innerHTML = brandName;
		document.getElementsByName("dailySalesFlavour")[d].innerHTML = flavour;
		if ($(document.getElementsByName("productAllPrice")).length != 0) {
			var num = -1;
			$(document.getElementsByName("productAllPrice")[d]).html("");
			var type = document.getElementById("detail-location-type").innerHTML;
			var locationBoId = document.getElementById("salesLocationId").innerHTML;
			if (type == 'BRANCH' || type == 'SUB_BRANCH'
					|| type == 'HEAD_OFFICE') {
				if (document.getElementById("isManualSelectPrice").innerHTML == "true") {
					var adjustPrice = -1;
					for (var t = 0; t < packageTypeString.length; t++) {
						var temp = packageTypeString[t];
						if (temp.trim() == "")
							continue;
						var tempPackage = temp.split(",");
						var optionGroup = $(document.createElement("optgroup"));
						var select = $(document
								.getElementsByName("productAllPrice")[d]);
						optionGroup.attr("label", tempPackage[0] + ","
								+ tempPackage[1]);
						select.append(optionGroup);
						for (var p = 2; p < tempPackage.length; p++) {
							var option = $(document.createElement("option"));
							if (adjustPrice == -1) {
								adjustPrice = tempPackage[p];
							}
							option.text(tempPackage[p]);
							option.attr("value", tempPackage[1] + ','
									+ tempPackage[1]);
							optionGroup.append(option);
						}
						if (num == -1) {
							num = tempPackage[0];
						}
					}
				} else {
					var adjustPrice = document.getElementById("adjustPrice").innerHTML;
					for (var t = 0; t < packageTypeString.length; t++) {
						var temp = packageTypeString[t];
						if (temp.trim() == "")
							continue;
						var tempPackage = temp.split(",");
						var select = $(document
								.getElementsByName("productAllPrice")[d]);
						var option = $(document.createElement("option"));
						option.text(tempPackage[0] + "," + tempPackage[1] + ","
								+ tempPackage[5]);
						option.val(tempPackage[0]);
						select.append(option);
						if (num == -1) {
							num = tempPackage[0];
						}
					}
				}
				document.getElementsByName("adjustPrice")[d].value = adjustPrice;
				document.getElementsByName("packaging_Type")[d].innerHTML = num;
				var type = document.getElementById("detail-location-type").innerHTML;
				var routeId = $("#saleMarketRoute").text();
				document.getElementsByName("dailySalesquantity")[d].disabled = false;
				getAvailableLocationList(row, boId, num, "", locationBoId,
						routeId);
				// document.getElementsByName("dailySalesProductDiscounted")[d].innerHTML
				// = discounted;
				document.getElementsByName("adjustPrice")[d].focus();
			} else {
				for (var t = 0; t < packageTypeString.length; t++) {
					var temp = packageTypeString[t];
					if (temp.trim() == "")
						continue;
					var tempPackage = temp.split(",");
					var select = $(document
							.getElementsByName("productAllPrice")[d]);
					var option = $(document.createElement("option"));
					if (document.getElementById("isManualSelectPrice").innerHTML == "true") {
						option.text(tempPackage[1]);
					} else {
						option.text(tempPackage[1] + "," + tempPackage[5]);
					}
					option.val(tempPackage[0]);
					select.append(option);
					if (num == -1) {
						num = tempPackage[0];
					}
				}
				document.getElementsByName("packaging_Type")[d].innerHTML = num;
				// document.getElementsByName("dailySalesProductDiscounted")[d].innerHTML
				// = discounted;
				if (errors[d]) {
					$(row.parentNode.parentNode).removeClass("required");
					delete errors[d];
					document.getElementsByName("dailySalesPackagePrice")[d].value = "";
					document.getElementsByName("dailySalesquantity")[d].value = "";
				}
				document.getElementsByName("dailySalesPackagePrice")[d].focus();
			}
		} else {
			updateDataList();
		}
		return false;
	}
	return true;

	function updateDataList() {
		var dailySalesProductPrice = document
				.getElementsByName("dailySalesProductPrice")[d];
		$(dailySalesProductPrice).empty();
		$("#volumePriceDataList").empty();
		var tempvolumePrice = volumePrice.split(";");
		var dataList = document.createElement("DATALIST");
		for (var i = 0; i < (tempvolumePrice.length - 1); i++) {
			var option = document.createElement("OPTION");
			option.setAttribute("text", tempvolumePrice[i]);
			option.setAttribute("value", tempvolumePrice[i]);
			dataList.appendChild(option);
		}
		var dynamic = new Date().getTime();
		dataList.setAttribute("id", dynamic);
		dailySalesProductPrice.setAttribute("list", dynamic);
		dailySalesProductPrice.appendChild(dataList);
		// document.getElementsByName("dailySalesProductPrice")[d].value =
		// price;
		// document.getElementsByName("dailySalesProductDiscounted")[d].innerHTML
		// = discounted;
		dailySalesProductPrice.focus();
	}
}

function checkQuantityEnterKey(event) {
	var d = event.target.parentNode.parentNode.parentNode.rowIndex - 1;
	var qty = document.getElementsByName("dailySalesquantity")[d].value;
	var select = $(document.getElementsByName("dailySales-available-location"));
	var productQty = select[d].value.split(",");
	if (event.which == 13 || event.keyCode == 13) {
		addSalesRow("sales-table");
		return false;
	}
	return true;

}

function checkQuantityEnterKey1(event) {
	if (event.which == 13 || event.keyCode == 13) {
		addSalesRow1("sales-table");
		return false;
	}
	return true;

}

function addEnterSalesConverPackageRow(event) {
	if (event.which == 13 || event.keyCode == 13) {
		addSalesConverPackageRow("convertPackage-table");
		return false;
	}
	return true;
}

function checkKeyPress(event) {
	var d = event.target.parentNode.parentNode.parentNode.rowIndex - 1;
	var row = event.target.parentNode.parentNode.parentNode;
	if (event.which == 13 || event.keyCode == 13 || event.which == 9
			|| event.keyCode == 9) {
		var codeList = document.getElementsByName("salesProductcodeList");
		var pkgList = document.getElementsByName("packaging_Type");
		var code = codeList[d].value;
		var pkg = pkgList[d].innerHTML;
		for (var i = 1; i < codeList.length - 1; i++) {
			if (code == codeList[i].value && pkgList[i].innerHTML == pkg) {
				alert("Code already existed");
				$(row).addClass("required");
				errors[d] = "error";
				return false;
			}
		}
		document.getElementsByName("dailySalesquantity")[d].focus();
		return false;
	}
	return true;
}

function checkCalculateFOC(event) {
	var d = event.target.parentNode.parentNode.rowIndex - 1;
	var check = document.getElementsByName("foc")[d].checked;
	var total = 0;
	if (check == false) {
		var qty = document.getElementsByName("dailySalesquantity")[d].value;
		if (document.getElementsByName("productAllPrice").length != 0) {
			if (document.getElementsByName("dailySalesPackagePrice").length != 0) {
				var price = document
						.getElementsByName("dailySalesPackagePrice")[d].value;
			} else {
				var price = document.getElementsByName("adjustPrice")[d].value;
			}
		} else {
			var price = document.getElementsByName("dailySalesProductPrice")[d].value;
		}
		total = qty * price;
	}
	document.getElementsByName("dailySalestotal")[d].innerHTML = total;
	totalSales();
}

function checkCalculateFOCEdit(event) {
	var d = event.target.parentNode.parentNode.rowIndex - 2;
	console.log(d);
	var check = document.getElementsByName("foc")[d].checked;
	console.log(check);
	var total = 0;
	if (check == false) {
		var qty = document.getElementsByName("dailySalesquantity")[d + 1].value;
		if (document.getElementsByName("productAllPrice").length != 0) {
			if (document.getElementsByName("dailySalesPackagePrice").length != 0) {
				var price = document
						.getElementsByName("dailySalesPackagePrice")[d + 1].value;
			} else {
				var price = document.getElementsByName("adjustPrice")[d + 1].value;
			}
		} else {
			var price = document.getElementsByName("dailySalesProductPrice")[d + 1].value;
		}
		total = qty * price;
	}
	document.getElementsByName("dailySalestotal")[d + 1].innerHTML = total;
	totalSales();
}

function changeCurrencyType() {
    var currencyType = $("input[name='currencyType']:checked").val();
    if (currencyType == "MMK") {
        $('#exchangeRateText').addClass("hide");
        $('#exchangeRateInputBox').addClass("hide");
        $('span[name=usdcurrency]').addClass("hide");
        $('td[name=additionalCostCurrencyTypeText]').addClass("hide");
        $('select[name=additionalCostCurrencyType]').addClass("hide");
    } else {
        $('#exchangeRateText').removeClass("hide");
        $('#exchangeRateInputBox').removeClass("hide");
        $('span[name=usdcurrency]').removeClass("hide");
        $('td[name=additionalCostCurrencyTypeText]').removeClass("hide");
        $('select[name=additionalCostCurrencyType]').removeClass("hide");
    }
}

function changeTotalBalance(){
    var currencyType = $("input[name='currencyType']:checked").val();
    if(currencyType == "MMK"){
    	document.getElementById("totalCreditBalance").innerHTML = document.getElementById("totalBalanceForMMK").innerHTML + " (MMK)";
	} else {
        document.getElementById("totalCreditBalance").innerHTML = document.getElementById("totalBalanceForUSD").innerHTML + " (USD)";
	}
}

// function changeCurrencyTypeForPayment() {
//     var currencyType = $("input[name='currencyType']:checked").val();
//     if (currencyType == "USD") {
//     	// $('#exchangeRateText').addClass("hide");
//         // $('#exchangeRateInputBox').addClass("hide");
//         $('#calculatedMMK').addClass("hide");
//     } else {
//     	// $('#exchangeRateText').removeClass("hide");
//     	// $('#exchangeRateInputBox').removeClass("hide");
//         $('#exchangeRateInputBox').val("");
//         $('#payment_amount').val("");
//         $('#luckyDrawAmount').val("");
//         $('#discount_amount').val("");
// 	}
// }

// function calculateMMK(){
// 	$('#calculatedMMK').removeClass("hide");
// 	var exchangeRate = document.getElementById("exchangeRateInputBox").value;
// 	var payment = document.getElementById("lastPayment").innerHTML;
// 	document.getElementById("calculatedMMK").innerHTML = " => " + (exchangeRate * payment) + " (MMK)";
// }

function checkCalculateAmount(event) {
	var d = event.target.parentNode.parentNode.parentNode.rowIndex - 1;
	rowtemp = d;
	var check = $(document.getElementsByName("foc")[d]);
	var total = 0;
	var qty = document.getElementsByName("dailySalesquantity")[d].value;
	if (check.prop("checked")) {
		total = 0;
	} else {
		if (document.getElementsByName("productAllPrice").length != 0) {
			if (document.getElementsByName("dailySalesPackagePrice").length != 0) {
				var price = document
						.getElementsByName("dailySalesPackagePrice")[d].value;
			} else {
				// var select =
				// $(document.getElementsByName("productAllPrice")[d]);
				// var price = $(":selected", select).text();
				var price = document.getElementsByName("adjustPrice")[d].value;
			}
		} else {
			var price = document.getElementsByName("dailySalesProductPrice")[d].value;
		}
		total = qty * price;
	}
	document.getElementsByName("dailySalestotal")[d].innerHTML = total;
	totalSales();
}

function checkProductPrice1(element) {
	var select = getParentByTagName(element, "select");
	var tr = getParentByTagName(element, "tr");
	$("[name=packaging_Type]", tr).text(element.value);
	var row = $(element).parent().parent();
	var d = row.index() - 1;
	console.log($(row), " ", d);
	if (errors[d]) {
		$(row).removeClass("required");
		delete errors[d];
		$("[name=dailySalesPackagePrice]", tr).val("");
		$("[name=dailySalesPackagePrice]", tr).val("");
	}
	$("[name=dailySalesPackagePrice]", tr).focus();
}

function checkConvertPackageType(element) {
	var select = getParentByTagName(element, "select");
	var tr = getParentByTagName(element, "tr");
	var qtyString = $("[name=convertFromPackagingType]", tr).val();
	var qty = qtyString.split(',');
	$("[name=convertFromQty]", tr).text(qty[1]);
}

function checkAdjustPrice(element) {
	var tr = getParentByTagName(element, "tr");
	var adjustPrice = $("[name=adjustPrice]", tr).val();
	var qty = $("[name=dailySalesquantity]", tr).val();
	$("[name=dailySalestotal]", tr).text(qty * adjustPrice);
	totalSales();
}

function checkProductPrice(element) {
	var select = getParentByTagName(element, "select");
	var tr = getParentByTagName(element, "tr");
	var qty = $("[name=dailySalesquantity]", tr).val();
	if (document.getElementsByName("dailySalesPackagePrice").length != 0) {
		var price = select.val();
	} else {
		var price = $(":selected", select).text();
	}
	$("[name=adjustPrice]", tr).val(price);
	if (document.getElementById("isManualSelectPrice").innerHTML == "true") {
		$("[name=dailySalestotal]", tr).text(qty * price);
	}

	var locationBoId = null;
	if (document.getElementById("isNewRouteForm").innerHTML == "YES") {
		locationBoId = document.getElementById("detail-location-boId").innerHTML;
	} else {
		locationBoId = document.getElementById("salesLocationId").innerHTML;
	}

	updateOptionGroup(locationBoId);
	if (document.getElementById("isNewRouteForm").innerHTML != "YES") {
		totalSales();
	}

	function updateOptionGroup(locationBoId) {
		if (document.getElementById("isManualSelectPrice").innerHTML == "true") {
			var optgroup = $(":selected", $(element)).parent().attr("label");
		} else {
			var optgroup = $(":selected", select).text();
		}
		if (!optgroup)
			return;
		var pkgId = optgroup.split(",");
		$("[name=packaging_Type]", tr).text(pkgId[0]);

		var pCode = null;
		if (document.getElementById("isManualSelectPrice").innerHTML == "true") {
			pCode = $("[name=route-product-boId", tr).val();
		} else {
			if (document.getElementById("isNewRouteForm").innerHTML != "YES") {
				pCode = $("[name=salesProductcodeList]", tr).val();
			} else {
				pCode = element.parentNode.parentNode.parentNode.childNodes[1].lastChild.value;
			}
		}

		var type = document.getElementById("detail-location-type").innerHTML;
		if (type == 'BRANCH' || type == 'SUB_BRANCH') {
			var result = getAvailableLocationList(element, pCode, pkgId[0],
					"checkPrice", locationBoId);
			if (result == 1)
				document.getElementsByName("dailySales-available-location")[d]
						.focus();
		}
	}
}

function getAvailableLocationList(element, pCode, pkId, checkPrice,
		locationBoId, routeId) {
	var isRetailDaily = document.getElementById("isRetailDaily").innerHTML;
	var parameter = {};
	parameter["pCode"] = pCode;
	parameter["pkId"] = pkId;
	parameter["locationBoId"] = locationBoId;
	parameter["routeId"] = routeId;
	parameter["isRetailDaily"] = isRetailDaily;
	console.log("Parameter", parameter);
	var request = new XMLHttpRequest;
	request.onreadystatechange = readyFunction;
	request.open("GET", "availableLocationList?input="
			+ JSON.stringify(parameter), "true");
	request.send();
	loading();
	function readyFunction() {
		if (request.readyState != 4)
			return;
		if (request.status != 200) {
			alert("Error :" + request.status);
			return;
		}
		var d = element.parentNode.parentNode.rowIndex - 1;
		if (checkPrice == "checkPrice") {
			d = element.parentNode.parentNode.parentNode.rowIndex - 1;
		}
		var tr = getParentByTagName(element, "tr");
		if (request.responseText != "") {
			var json = parseNewJson(request.responseText);
			var select = $("[name=dailySales-available-location]", tr);
			select[0].setEnabled = true;
			select[0].innerHTML = "";
			if (json != null) {
				for ( var index in json) {
					if (index == '-1111') {
						hideLoading();
						$('dailySales-available-location').children().remove();
						$('dailySales-available-location').attr("disabled",
								"disabled");
						if (document.getElementById("isNewRouteForm").innerHTML != "YES") {
							document.getElementsByName("dailySalesquantity")[d].value = "";
							document.getElementsByName("dailySalesquantity")[d].disabled = true;
							document.getElementsByName("dailySalestotal")[d].innerHTML = "";
						} else {
							document
									.getElementsByName("dailySales-available-location")[d].innerHTML = "0";
							// document.getElementsByName("route-product-qty")[d].value
							// = "";
							document.getElementsByName("route-product-qty")[d].disabled = true;
						}
						return 0;
					}
					if (document.getElementById("isNewRouteForm").innerHTML == "YES") {
						var newSpanTag = document.createElement("span");
						var productQty = json[index].split(",");
						newSpanTag.innerText = productQty[1].split('.')[0];
						select[0].appendChild(newSpanTag);
					} else {
						var option = document.createElement("option");
						var productQty = json[index].split(",");
						productQty[1] = productQty[1].split('.')[0];
						option.value = index + "," + productQty[1] + ","
								+ productQty[2];
						option.innerHTML = productQty;
						select[0].appendChild(option);
					}
				}
				if (document.getElementById("isNewRouteForm").innerHTML != "YES") {
					checkAvailableQuantity(element, checkPrice);
					document.getElementsByName("dailySalesquantity")[d].disabled = false;
				} else {
					document.getElementsByName("route-product-qty")[d].disabled = false;
				}
				hideLoading();
				return 1;
			}
		}

	}
}

function parseNewJson(string) {
	var result = JSON.parse(string);
	if (typeof result == "string")
		result = JSON.parse(result);
	return result;
}

function checkAvailableQuantity(element, checkPrice) {
	var d = element.parentNode.parentNode.parentNode.rowIndex - 1;
	var tr = getParentByTagName(element, "tr");
	var select = $("[name=dailySales-available-location]", tr);
	var productQty = select.val().split(",");
	if (productQty[1] <= 0 && checkPrice != "checkPrice") {
		alert("Location Product Quantity is less than 0,Are you sure to sell Product?");
	}
	var index = select[0].selectedIndex;
	var selectedVal = select[0].options[index].innerHTML;
	var availQty = selectedVal.split(", ");
	var input = $("[name=dailySalesquantity]", tr);
	input.max = availQty[1];
	input.placeholder = availQty[1];
}

function getParentByTagName(currentElement, tagName) {
	var element = $(currentElement);
	while (element != null) {
		if (element.prop("tagName").toLowerCase() == tagName)
			return element;
		element = element.parent();
	}
}

function checkPurchaseEditPrice(event) {
	var tr = $(event.target.parentNode.parentNode);
	var qty = $("[name=dailySalesquantity]", tr).val();
	var price = $("[name=productAllPrice]", tr).val();
	$("[name=dailySalestotal] span[name=dailySalestotal]", tr)
			.text(qty * price);
	document.getElementById("dailysales-total").innerHTML = PurchaseOrderCheck
			.calculateTotal();
	// totalSales();
}

function PurchaseOrderCheck() {
}

PurchaseOrderCheck.calculateTotal = function() {
	var salesTable = $("#sales-table td[name=dailySalestotal] span[name=dailySalestotal]");
	var total = 0;
	salesTable.each(function(index, element) {
		element = $(element);
		var amount = parseInt(element.text()) || 0;
		total += amount;
	});
	return total;
}

function checkCalculateAmountEdit(event) {
	var d = event.target.parentNode.parentNode.rowIndex - 1;
	var qty = document.getElementsByName("dailySalesquantity")[d].value;
	var type = document.getElementById("detail-location-type").innerHTML;
	var price = document.getElementsByName("dailySalesProductPrice")[d].value;
	document.getElementsByName("dailySalestotal")[d].innerHTML = qty * price;
	totalSales();
}

function checkToQuantity(event) {
	if (event.which == 13 || event.keyCode == 13 || event.which == 9
			|| event.keyCode == 9) {
		var d = event.target.parentNode.parentNode.parentNode.rowIndex - 1;
		document.getElementsByName("dailySalesquantity")[d].focus();
		return false;
	}
	return true;
}

function checkOtherEnterKey(event) {
	if (event.which == 13 || event.keyCode == 13) {
		addSalesRow("sales-table");
		return false;
	}
	return true;
}

function checkOtherCostEnterKey(event) {
	if (event.which == 13 || event.keyCode == 13) {
		addSalesRow("sales-table");
		return false;
	}
	return true;
}

function checkFoucuQuantityEnterKey(event) {
	if (event.which == 13 || event.keyCode == 13) {
		var d = event.target.parentNode.parentNode.parentNode.rowIndex - 1;
		document.getElementsByName("dailySalesquantity")[d].focus();
	}
}

function checkOtherCostCalculation(event) {
	// var d = event.target.parentNode.parentNode.parentNode.rowIndex - 1;
	totalSales();
}

function removeSalesRow(element) {
	element = $(element);
	var removeElement = element.parent().parent().parent();
	var table = removeElement.parent();
	if (table.children().length < 2) {
		alert("Can't remove last element");
		return;
	}
	var d = removeElement.index() - 1;
	if (errors[d])
		delete errors[d];
	removeElement.detach();
	totalSales();
}

function packagingRemoveSalesRow(element) {
	element = $(element);
	var removeElement = element.parent().parent();
	var table = removeElement.parent();
	if (table.children().length < 2) {
		alert("Can't remove last element");
		return;
	}
	removeElement.detach();
}

function totalSales() {
	var amtList = document.getElementsByName("dailySalestotal");
	var qtyList = document.getElementsByName("dailySalesquantity");
	var totalQty = 0;
	var totalAmt = 0;
	totalDis = 0;
	if (qtyList.length > 1) {
		for (var i = 1; i < qtyList.length; i++) {
			if (qtyList[i].value != "") {
				totalQty += parseInt(qtyList[i].value.trim() ? qtyList[i].value
						.trim() : 0);
				totalAmt += parseInt(amtList[i].innerHTML ? amtList[i].innerHTML
						: 0);
				totalDis += parseInt(amtList[i].innerHTML ? amtList[i].innerHTML
						: 0);
			}
		}
	}
	document.getElementById("dailysales-gross-total").innerHTML = totalAmt;
	var otherList = document.getElementsByName("othercost");
	for (var i = 0; i < otherList.length; i++) {
		totalAmt += parseInt(otherList[i].value.trim() ? otherList[i].value
				.trim() : 0);
	}
	document.getElementById("dailysales-total").innerHTML = totalAmt;
	document.getElementById("dailysales-totalqty").innerHTML = totalQty;
	calculatediscount();
	var discount = document.getElementById("salesDiscount").innerHTML;
	var cashdiscount = document.getElementById("salesCashDiscount").value
			.trim() ? document.getElementById("salesCashDiscount").value.trim()
			: 0;
	var additionalCost = 0;
	var additionalCostList = $(document
			.getElementsByName("additionalCost_Amount"));
	for (var i = 0; i < additionalCostList.length; i++) {
		additionalCost += Number(additionalCostList[i].value);
	}
	var luckyDrawPaid = document.getElementById("luckyDrawPaid").value;
	var net = totalAmt - discount;
	net = net - Number(cashdiscount) - Number(luckyDrawPaid);
	net = net + Number(additionalCost);
	document.getElementById("dailysales-netAmount").innerHTML = net;
}

function calculatediscount() {
	var totalAmount = document.getElementById("dailysales-gross-total").innerHTML
			.trim() ? document.getElementById("dailysales-gross-total").innerHTML
			.trim()
			: 0;
	var per = document.getElementById("salesDiscountPer").value.trim() ? document
			.getElementById("salesDiscountPer").value.trim()
			: 0;
	var value = Number(totalDis);
	var percentage = Number(per);
	if (value > 0) {
		var disAmt = value * (percentage / 100);
		document.getElementById("salesDiscount").innerHTML = parseInt(disAmt);
	}
	if (value == 0) {
		document.getElementById("salesDiscount").innerHTML = parseInt(totalAmount
				* percentage / 100);
	}
	calculateNetTotal();
}

function calculateNetTotal() {
	var totalAmount = document.getElementById("dailysales-total").innerHTML
			.trim() ? document.getElementById("dailysales-total").innerHTML
			.trim() : 0;
	var discount = document.getElementById("salesDiscount").innerHTML.trim() ? document
			.getElementById("salesDiscount").innerHTML.trim()
			: 0;
	var salesCashDiscount = document.getElementById("salesCashDiscount").value
			.trim() ? document.getElementById("salesCashDiscount").value.trim()
			: 0;

	var currencyType = $("input[name='currencyType']:checked").val();
	var additionalCost = 0;
	var additionalCostList = $(document
			.getElementsByName("additionalCost_Amount"));

	// For difference currency types
	for (var i = 0; i < additionalCostList.length; i++) {
		if (currencyType == "USD") {
			var additonalCostCurrencyType = document
					.getElementsByName("additionalCostCurrencyType")[i].value;
			if (additonalCostCurrencyType == "MMK") {
				var exchangeRate = $('#exchangeRateInputBox').val();
				additionalCost += Number(additionalCostList[i].value)
						/ exchangeRate;
			} else {
				additionalCost += Number(additionalCostList[i].value);
			}

		} else {
			additionalCost += Number(additionalCostList[i].value);
		}
	}

	salesCashDiscount = Number(salesCashDiscount);
	discount = Number(discount);
	var value = Number(totalDis);
	totalAmount = Number(totalAmount);
	var luckyDrawPaid = document.getElementById("luckyDrawPaid").value;
	var totalValue = totalAmount - value - Number(luckyDrawPaid);
	if (value >= 0) {
		value = value - discount;
		value = value - salesCashDiscount;
		value = totalValue + value + Number(additionalCost);
		document.getElementById("dailysales-netAmount").innerHTML = parseInt(value);
	}
}

function saveMergeCustomer() {
	var customerId = document.getElementById("customerBoId").innerHTML;
	var purchaseOrderId = document.getElementById("mergepurchaseOrder-Id").innerHTML;
	var otherCustomerId = document.getElementById("daily-saleCustomerId").innerHTML;
	if (otherCustomerId == "") {
		alert("Please choose Customer!");
		return;
	}
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				var result = document.createElement("div");
				result.innerHTML = request.responseText;
				result = $(result).children("span").text();
				if (result == "NotCustomer") {
					alert("Choose Customer!");
				}
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				loadAction(document.getElementById("customermenu"), 'customer');
				document.getElementById("search-text").value = customerId;
				this.id = customerId;
				search(this, 'ID', 'CUSTOMER');
				aboutDetail(this, 'CUSTOMER');
			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var saleRecords = {};
	saleRecords["customerId"] = customerId;
	saleRecords["purchaseOrderId"] = purchaseOrderId;
	saleRecords["otherCustomerId"] = otherCustomerId;
	console.log("Parameter ", saleRecords);
	loading();
	request.open("GET", "saveMergeCustomer?input="
			+ JSON.stringify(saleRecords), true);
	request.send(null);
}

function editPurchase() {
	var request = new XMLHttpRequest;
	var codeList = document.getElementsByName("salesProductcodeList");
	var qtyList = document.getElementsByName("dailySalesquantity"); // currentQuantity
	var hiddenQtyList = document.getElementsByName("dailySalesquantityHidden"); // hiddenQuantity
	var currentQtyFromLocation = $("#dailySales-available-location option"); // current
	// Stock
	// From
	// Location
	var freeOfChargeList = document.getElementsByName("foc");

	var availableLocList = document
			.getElementsByName("dailySales-available-location");
	if (document.getElementsByName("productAllPrice").length != 0) {
		var priceList = document.getElementsByName("adjustPrice");
		var packageTypeList = document.getElementsByName("packaging_Type");
	} else {
		var priceList = document.getElementsByName("dailySalesProductPrice");
	}
	var customerId = document.getElementById("detail-customer-boId").innerHTML;
	var locationId = document.getElementById("salesLocationId").innerHTML;
	var stockList = [];
	var quantityList = [];
	var pList = [];
	var pTList = [];
	var locationList = [];
	var focList = [];
	var stockTypeList = [];
	var saleRecords = {};
	if (document.getElementsByName("productAllPrice").length != 0) {
		for (var j = 1; j < document.getElementsByName("salesProductcodeList").length; j++) {
			if (parseFloat(qtyList[j].value)) {
				quantityList.push(parseInt(qtyList[j].value));
			}else{
				continue;
			}
			console.log(" dd" ,parseFloat(qtyList[j].value));
			var newQuantity = parseInt(qtyList[j].value) ? qtyList[j].value
					: "0";
			var hiddenQuantity = parseInt(hiddenQtyList[j].value) ? hiddenQtyList[j].value
					: "0";
			var currentQuantity = parseInt((currentQtyFromLocation[j - 1].innerHTML)
					.split(",")[1]) ? (currentQtyFromLocation[j - 1].innerHTML)
					.split(",")[1] : "0";
			var oldQuantity = parseInt(hiddenQuantity)
					+ parseInt(currentQuantity);

			/*
			 * if (newQuantity > oldQuantity) return window .alert("Insufficient
			 * product in available location!!! Product Code : " +
			 * codeList[j].value);
			 */

			stockList.push(codeList[j].value ? codeList[j].value : "0");
			pList.push($(priceList[j]).val() ? $(priceList[j]).val() : "0");
			pTList
					.push(packageTypeList[j].innerHTML ? packageTypeList[j].innerHTML
							: "0");
			availLoc = availableLocList[j].value.split(",");
			locationList.push(availLoc[0]);
			stockTypeList.push(availLoc[2]);
			var foc = freeOfChargeList[j - 1].checked;
			focList.push(foc);
		}
		saleRecords['packageTypeItem'] = pTList;
		saleRecords['lineItem'] = stockList;
		saleRecords['quantityList'] = quantityList;
		saleRecords['priceList'] = pList;
		saleRecords['locationList'] = locationList;
		saleRecords['foc'] = focList;
		saleRecords['stockTypeList'] = stockTypeList;
	} else {
		var lineItem = {};
		var priceItem = {};
		for (var i = 0; i < codeList.length; i++) {
			value = parseInt(qtyList[i].value.trim() ? qtyList[i].value.trim()
					: "0");
			var priceArray = priceList[i].value.split(',');
			var price = parseInt(priceArray[0]);
			if (value != 0) {
				var temp = lineItem[codeList[i].value.trim()] ? parseInt(lineItem[codeList[i].value
						.trim()])
						: 0;
				lineItem[codeList[i].value.trim()] = (temp + value).toString();
				priceItem[codeList[i].value.trim()] = price.toString();
			}
		}
		if (Object.keys(lineItem).length <= 0) {
			alert("Cann't Save Because you don't sale Product.");
			return;
		}
		saleRecords['lineItem'] = lineItem;
		saleRecords['priceItem'] = priceItem;
	}
	var otherList = document.getElementsByName("other");
	var othercostList = document.getElementsByName("othercost");
	var othercost = [];
	var otherNameList = [];
	for (var i = 0; i < othercostList.length; i++) {
		value = othercostList[i].value.trim() ? othercostList[i].value.trim()
				: "0";
		if (value != 0) {
			othercost.push(value);
		}
	}

	saleRecords['othercost'] = othercost;
	saleRecords['otherNameList'] = otherNameList;
	saleRecords['salesDate'] = document.getElementById("salesDate").value;
	saleRecords['routeId'] = document.getElementById("saleMarketRoute").innerHTML;
	saleRecords['salesInvoice'] = document.getElementById("salesInvoice").value;
	saleRecords['salesLocation'] = document.getElementById("salesLocationId").innerHTML;
	saleRecords["boId"] = document.getElementById("purchaseOrder-Id").innerHTML;
	saleRecords["locationId"] = locationId;
	saleRecords['saleCustomerId'] = customerId;
	saleRecords['discount'] = document.getElementById("salesDiscountPer").value
			.trim() ? document.getElementById("salesDiscountPer").value.trim()
			: "0";
	saleRecords['cashDiscount'] = document.getElementById("salesCashDiscount").value
			.trim() ? document.getElementById("salesCashDiscount").value : "0";
	saleRecords['netAmount'] = document.getElementById("dailysales-netAmount").innerHTML
			.trim() ? document.getElementById("dailysales-netAmount").innerHTML
			: "0";
	saleRecords['customerType'] = document.getElementById("edit-customer-type").innerHTML;
	var luckyDrawPaid = document.getElementById("luckyDrawPaid");
	saleRecords['luckyDrawPaid'] = luckyDrawPaid.value.trim() ? luckyDrawPaid.value
			: "0";
	// saleRecords['paidAmount'] = document.getElementById("salePaid").innerHTML
	// .trim() ? document.getElementById("salePaid").innerHTML : "0";

	if ($("#" + saleRecords['saleCustomerId']).attr("data-type") == "RETAIL") {
		if (saleRecords['paidAmount'] != saleRecords['netAmount']) {
			alert("Please paid payment because of Retail type");
			return;
		}
	}
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				var result = document.createElement("div");
				result.innerHTML = request.responseText;
				result = $(result).children("span").text();
				if (result == "PAYMENT") {
					alert("You have payments.Therefore you will first be to edit Payment-Date at PaymentDetail");
				}
				var startDate = $('#customerStartDate').val();
				var endDate = $('#customerEndDate').val();
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				loadAction(document.getElementById("customermenu"), 'customer');
				if (document.getElementById("isPrint") == "true") {
					printInvoiceAvailable("edit");
				} else {
					alert("Editing purchase order is successfully completed!!!");
				}
				document.getElementById("search-text").value = customerId;
				this.id = customerId;
				search(this, 'ID', 'CUSTOMER');
				aboutDetail(this, 'CUSTOMER', startDate, endDate);
			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	console.log("Parameter ", saleRecords);
	//loading();
	//request.open("GET", "editPurchaseOrderRecord?input="
	//		+ JSON.stringify(saleRecords), true);
	//request.send(null);
}

function salesPaid(force) {
	var request = new XMLHttpRequest;
	var i = 0;
	var codeList = document.getElementsByName("salesProductcodeList");
	var qtyList = document.getElementsByName("dailySalesquantity");
	var availableLocList = document
			.getElementsByName("dailySales-available-location");
	var freeOfChargeList = document.getElementsByName("foc");
	if (document.getElementsByName("productAllPrice").length != 0) {
		// var priceList = $(document.getElementsByName("productAllPrice"));
		var priceList = document.getElementsByName("adjustPrice");
		var packageTypeList = document.getElementsByName("packaging_Type");
	} else {
		var priceList = document.getElementsByName("dailySalesProductPrice");
	}
	var stockList = [];
	var quantityList = [];
	var pList = [];
	var pTList = [];
	var locationList = [];
	var focList = [];
	var stockTypeList = [];
	if (document.getElementsByName("productAllPrice").length != 0) {
		for (var j = 1; j < document.getElementsByName("salesProductcodeList").length; j++) {
			if (parseFloat(qtyList[j].value)) {
				quantityList.push(parseFloat(qtyList[j].value));
			} else {
				continue;
			}
			stockList.push(codeList[j].value ? codeList[j].value : "0");
			pList.push(priceList[j].value ? priceList[j].value : "0");
			pTList
					.push(packageTypeList[j].innerHTML ? packageTypeList[j].innerHTML
							: "0");
			availLoc = availableLocList[j].value.split(",");
			locationList.push(availLoc[0]);
			stockTypeList.push(availLoc[2]);
			var foc = freeOfChargeList[j].checked;
			focList.push(foc);
		}
	} else {
		for (var i = 0; i < codeList.length; i++) {
			stockList.push(codeList[i].value ? codeList[i].value : "0");
			quantityList.push(parseFloat(qtyList[i].value) ? qtyList[i].value
					: "0");
			pList.push(priceList[i].value ? priceList[i].value : "0");
			// locationList
			// .push(availableLocList[i].value ? availableLocList[i].value
			// : "0");
		}
	}
	var otherList = document.getElementsByName("other");
	var othercostList = document.getElementsByName("othercost");
	var othercost = [];
	var otherNameList = [];
	for (var i = 0; i < othercostList.length; i++) {
		value = othercostList[i].value.trim() ? othercostList[i].value.trim()
				: "0";
		if (value != 0) {
			// otherNameList.push(otherList[i].value.trim())
			othercost.push(value);
		}
	}
	var saleRecords = {};
	saleRecords['packageTypeItem'] = pTList;
	saleRecords['lineItem'] = stockList;
	saleRecords['quantityList'] = quantityList;
	saleRecords['priceList'] = pList;
	saleRecords['locationList'] = locationList;
	saleRecords['foc'] = focList;
	saleRecords['othercost'] = othercost;
	saleRecords['stockTypeList'] = stockTypeList;
	saleRecords['otherNameList'] = otherNameList;
	saleRecords['salesDate'] = document.getElementById("salesDate").value
			.split('-').reverse().join('/');
	saleRecords['routeId'] = document.getElementById("saleMarketRoute").innerHTML;
	saleRecords['salesInvoice'] = document.getElementById("salesInvoice").value;
	saleRecords['salesLocation'] = document.getElementById("salesLocationId").innerHTML;
	if (force) {
		saleRecords['force'] = force;
	}
	var custId = document.getElementById("daily-saleCustomerId").innerHTML;
	if (custId.trim() != "") {
		saleRecords['saleCustomerId'] = custId;
	} else {
		alert("Please choose Customer!");
		return;
	}
	saleRecords['discount'] = document.getElementById("salesDiscountPer").value
			.trim() ? document.getElementById("salesDiscountPer").value.trim()
			: "0";
	saleRecords['cashDiscount'] = document.getElementById("salesCashDiscount").value
			.trim() ? document.getElementById("salesCashDiscount").value : "0";
	saleRecords['netAmount'] = document.getElementById("dailysales-netAmount").innerHTML
			.trim() ? document.getElementById("dailysales-netAmount").innerHTML
			: "0";
	saleRecords['paidAmount'] = document.getElementById("salePaid").value
			.trim() ? document.getElementById("salePaid").value : "0";
	var luckyDrawPaid = document.getElementById("luckyDrawPaid");
	saleRecords['luckyDrawPaid'] = luckyDrawPaid.value.trim() ? luckyDrawPaid.value
			: "0";

	if ($("#" + saleRecords['saleCustomerId']).attr("data-type") == "RETAIL") {
		if (parseInt(saleRecords['paidAmount']) != parseInt(saleRecords['netAmount'])) {
			alert("Please paid payment because of Retail type ");
			return;
		}
	}
	var locationId = document.getElementById("salesLocationId").innerHTML;
	var locationName = document.getElementById("salesLocationName").innerHTML;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				var result = document.createElement("div");
				result.innerHTML = request.responseText;
				result = $(result).children("span").text();
				if (result == "ERROR") {
					var r = confirm("This InvoiceNo. is already exist.If you want to save,click 'OK'.");
					if (r == true) {
						salesPaid("froced");
					} else {
						$('#salesInvoice').focus();
					}
					return;
				}
				if (result == "ADJUSTMENT") {
					alert("You have adjustment before date,So you can't sale,Please Check Date!")
					return;
				}
				var print = document.getElementById("isPrint").innerHTML;
				if (print !== "false") {
					printInvoiceAvailable("", custBoId[1]);
				} else {
					alert("Sales process is successfully completed!!!");
					document.getElementById("dialog-temp").className = "dialog";
					document.getElementById("dialog-temp").attributes["class"].value = "hide";
					// loadAction(document.getElementById("locationmenu"),
					// 'location');
					// document.getElementById("search-text").value =
					// locationId;
					// search(this, 'ID', 'LOCATION');
					this.id = locationId;
					$(this)
							.attr(
									"data-type",
									document
											.getElementById("detail-location-type").innerHTML);
					aboutDetail(this, 'LOCATION');
					$('#workspace').removeClass("myFilter");
					dailySalesForm('daily');
				}
			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	console.log("SaveSaleRecord", saleRecords);
	 loading();
	 document.getElementById("btnPaid").disabled = true;
	 request.open("GET", "saveSaleRecord?input=" +
	 JSON.stringify(saleRecords),
	 true);
	 request.send(null);
}

function convertPackage() {
	var codeList = document.getElementsByName("convertProductcodeList");
	var toPackageTypeList = document.getElementsByName("toPackagingType");
	var fromPackageTypeList = document
			.getElementsByName("convertFromPackagingType");
	var toQtyList = document.getElementsByName("convertPackage_Qty");
	var damageQtyList = document.getElementsByName("convertPackage_Damage_Qty");
	var locationBoId = document.getElementById("locationBoId").innerHTML;
	if (document.getElementById("convertPackageDate").value == "") {
		alert("Pls,Enter Date!");
		return;
	}
	var code = [];
	var toPackageType = [];
	var fromPackageType = [];
	var damageQuantity = [];
	var fromStockType = [];
	var toQty = [];
	for (var i = 1; i < codeList.length; i++) {
		var packagingType = toPackageTypeList[i].value.split(",");
		code.push(codeList[i].value);
		if (packagingType[0] == "Select PackageType") {
			alert("Pls,Select PackageType!");
			return;
		}
		toPackageType.push(packagingType[0]);
		damageQuantity.push(damageQtyList[i].value ? damageQtyList[i].value
				: '0');
		var fromString = fromPackageTypeList[i].value;
		var from = fromString.split(',');
		fromPackageType.push(from[0]);
		fromStockType.push(from[3]);
		if (toQtyList[i].value == "") {
			alert("Pls,Fill Qty");
			return;
		}
		if (from[0] == packagingType[0]) {
			var convertConfirm = confirm("From Packaging Type and To Packaging Type are same. Are you sure to convert?");
			if (!convertConfirm)
				return;
		}
		toQty.push(toQtyList[i].value);
	}
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				var result = document.createElement("div");
				result.innerHTML = request.responseText;
				result = $(result).children("span").text();
				if (result == "ERROR") {
					alert("Invalid!");
					return;
				}
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				$('#workspace').removeClass("myFilter");
				$("#" + $('#detail-location-name').attr("value")).click();
			}
		}
	}
	var parameter = {};
	parameter['code'] = code;
	parameter['toPackageType'] = toPackageType;
	parameter['toQty'] = toQty;
	parameter['fromStockType'] = fromStockType
	parameter['locationBoId'] = locationBoId;
	parameter['convertPackageDate'] = document
			.getElementById("convertPackageDate").value;
	parameter['fromPackageType'] = fromPackageType;
	parameter['damageQuantity'] = damageQuantity;
	console.log("parameter ", parameter);
	loading();
	request.open("GET",
			"saveConvertPackage?input=" + JSON.stringify(parameter), true);
	request.send(null);
}

function salesPaid1(force) {
	var currencyType = $("input[name='currencyType']:checked").val();
	var exchangeRate = $('#exchangeRateInputBox').val() ? $(
			'#exchangeRateInputBox').val() : "0";
	console.log("exchangeRate: " + exchangeRate);
	var request = new XMLHttpRequest;
	var i = 0;
	var codeList = document.getElementsByName("salesProductcodeList");
	var qtyList = document.getElementsByName("dailySalesquantity");
	var freeOfChargeList = document.getElementsByName("foc");
	var additionalCostName = document.getElementsByName("additionalCost_Name");
	var additionalCostAmount = document
			.getElementsByName("additionalCost_Amount");
	var additionalCostCurrencyType = document
			.getElementsByName("additionalCostCurrencyType");
	var additionalCostNameList = [];
	var additionalCostAmountList = [];
	var additionalCostCurrencyTypeList = [];
	for (var i = 0; i < additionalCostAmount.length; i++) {
		if (additionalCostName[i].value == "")
			continue;
		additionalCostNameList.push(additionalCostName[i].value);
		additionalCostAmountList.push(additionalCostAmount[i].value);
		additionalCostCurrencyTypeList
				.push(additionalCostCurrencyType[i].value);
	}
	var locationId;
	if (document.getElementById("purchaseOrder-to-location") != null) {
		var locationId = document.getElementById("purchaseOrder-to-location").value;
		if (locationId == "Choose") {
			alert("Please Choose Purchase Location");
			return;
		}
	} else {
		locationId = null;
	}
	if (document.getElementsByName("productAllPrice").length != 0) {
		if (document.getElementsByName("dailySalesPackagePrice").length != 0) {
			var priceList = document
					.getElementsByName("dailySalesPackagePrice");
		} else {
			var priceList = document.getElementsByName("productAllPrice");
		}
		var packageTypeList = document.getElementsByName("packaging_Type");
	} else {
		var priceList = document.getElementsByName("dailySalesProductPrice");
	}
	var productCodeList = [];
	var quantityList = [];
	var pList = [];
	var packTypeList = [];
	var focList = [];
	if (document.getElementsByName("productAllPrice").length != 0) {
		for (var j = 1; j < codeList.length; j++) {
			var code = codeList[j].value;
			productCodeList.push(code);
			var packageType = packageTypeList[j].innerHTML ? packageTypeList[j].innerHTML
					: "0";
			packTypeList.push(packageType);
			var price = priceList[j].value ? priceList[j].value : "0";
			pList.push(price);
			var qty = qtyList[j].value ? qtyList[j].value : "0.0";
			quantityList.push(qty);
			var foc = freeOfChargeList[j].checked;
			focList.push(foc);
		}
	}
	var otherList = document.getElementsByName("other");
	var othercostList = document.getElementsByName("othercost");
	var othercost = {};
	for (var i = 0; i < otherList.length; i++) {
		value = othercostList[i].value.trim() ? othercostList[i].value.trim()
				: "0";
		if (value != 0)
			othercost[otherList[i].value.trim()] = value;
	}
	var saleRecords = {};
	saleRecords['currencyType'] = currencyType;
	saleRecords['exchangeRate'] = exchangeRate;
	saleRecords['additionalCostName'] = additionalCostNameList;
	saleRecords['additionalCostAmount'] = additionalCostAmountList;
	saleRecords['additionalCostCurrencyType'] = additionalCostCurrencyTypeList;
	saleRecords['code'] = productCodeList;
	saleRecords['packageType'] = packTypeList;
	saleRecords['price'] = pList;
	saleRecords['quantity'] = quantityList;
	saleRecords['foc'] = focList;
	saleRecords['othercost'] = othercost;
	saleRecords['salesDate'] = document.getElementById("salesDate").value;
	saleRecords['routeId'] = document.getElementById("saleMarketRoute").innerHTML;
	saleRecords['salesInvoice'] = document.getElementById("salesInvoice").value;
	saleRecords['salesTradeTerm'] = $("#salesTradeTerm").val();
	saleRecords['salesLocation'] = document.getElementById("salesLocationId").innerHTML;
	saleRecords["locationId"] = locationId;

	if (force) {
		saleRecords['force'] = force;
	}

	if ($(document.getElementById("daily-saleRetailCustomerId")).length > 0) {
		saleRecords['saleCustomerId'] = document
				.getElementById("daily-saleRetailCustomerId").innerHTML;
	} else {
		// var custList = document.getElementById("saleCustomerId");
		// var custId =
		// document.getElementById("daily-saleCustomerId").innerHTML;
		//
		// if (custId.trim() != "")
		// saleRecords['saleCustomerId'] = document
		// .getElementById("saleCustomerId").value;

		if (document.getElementById("saleCustomerId").value != "") {
			saleRecords['saleCustomerId'] = document
					.getElementById("saleCustomerId").value;
		} else {
			alert("Please choose Customer!");
			return;
		}
	}
	saleRecords['discount'] = document.getElementById("salesDiscountPer").value
			.trim() ? document.getElementById("salesDiscountPer").value.trim()
			: "0";
	saleRecords['cashDiscount'] = document.getElementById("salesCashDiscount").value
			.trim() ? document.getElementById("salesCashDiscount").value : "0";
	saleRecords['netAmount'] = document.getElementById("dailysales-netAmount").innerHTML
			.trim() ? document.getElementById("dailysales-netAmount").innerHTML
			: "0";
	saleRecords['paidAmount'] = document.getElementById("salePaid").value
			.trim() ? document.getElementById("salePaid").value : "0";
	var luckyDrawPaid = document.getElementById("luckyDrawPaid");
	saleRecords['luckyDrawPaid'] = luckyDrawPaid.value.trim() ? luckyDrawPaid.value
			: "0";
	if ($("#" + saleRecords['saleCustomerId']).attr("data-type") == "RETAIL") {
		if (saleRecords['paidAmount'] != saleRecords['netAmount']) {
			alert("Please paid payment because of Retail type");
			return;
		}
	}
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				var result = document.createElement("div");
				result.innerHTML = request.responseText;
				result = $(result).children("span").text();
				if (result == "ERROR") {
					var r = confirm("This InvoiceNo. is already exist.If you want to save,click 'OK'.");
					if (r == true) {
						salesPaid1("froced");
					} else {
						$('#salesInvoice').focus();
					}
					return;
				}
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				var print = document.getElementById("isPrint1").innerHTML;
				if (print !== "false") {
					printInvoice();
				} else {
					$('#workspace').removeClass('myFilter');
					alert("Purchasing process is successfully completed!!!");
					dailySalesForm('daily', '', 'SUPPLIER');
				}

				// $("#dailySale").click();
			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	console.log("saleRecords ", saleRecords);
	if (Object.keys(errors).length > 0) {
		alert("Please check List!")
		return;
	}
	loading();
	request.open("GET", "savePurchaseFromSupplier?input="
			+ JSON.stringify(saleRecords), true);
	request.send(null);
}

function marketRoute() {
	var locationId = document.getElementById("salesLocationId").innerHTML;
	var routeId = document.getElementById("salerouteId").value;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";

				document.getElementById("market-sales").className = "show";
				document.getElementById("market-route-requirement").className = "hide";
				document.getElementById("market-route-information").className = "show";
				document.getElementById("saleMarketRoute").innerHTML = routeId;

			} else {
				alert("Load view Salary form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["routeId"] = routeId;
	loading();
	request.open("GET",
			"getRouteByLocation?input=" + JSON.stringify(parameter), true);
	request.send(null);
}

function saleFromRoute(element) {
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var routeId = element.id;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("salesDate").value = today();
				$('#salesDate').focus();
			} else {
				alert("Load view Salary form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["routeId"] = routeId;
	loading();
	request.open("GET",
			"getRouteByLocation?input=" + JSON.stringify(parameter), true);
	request.send(null);
}

function closeRoute(element) {
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var routeId = element.id;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("adjustment-Date").value = today();
			} else {
				alert("Load Close Route Form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["routeId"] = routeId;
	loading();
	request.open("GET", "getAdjustmentStockForm?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

// For Route Change Detection
function routeHistory(element) {
	var routeId = element.id;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
			} else {
				alert("Load Route History Form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["routeId"] = routeId;
	loading();
	request.open("GET", "getRouteHistoryForm?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

function RecalculateCommission(element) {
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var routeId = element.id;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				loadAction(document.getElementById("locationmenu"), 'location');
				document.getElementById("search-text").value = locationId;
				search(this, 'ID', 'LOCATION');
				this.id = detailLocId;
				aboutDetail(this, 'LOCATION');
			} else {
				alert("Load Recalculate Commission form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};

	parameter["routeId"] = routeId;
	loading();
	request.open("GET", "recalculateCommission?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

function issueStockToRoute(element) {
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var routeId = element.id;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				$('#issue-Date').datepicker({
					dateFormat : 'dd/mm/yy'
				});
				document.getElementById("issue-Date").value = today();
			} else {
				alert("Load view Issue form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["routeId"] = routeId;
	loading();
	request.open("GET", "getIssueStockForm?input=" + JSON.stringify(parameter),
			true);
	request.send(null);
}

function closeZayCarRoute(element) {
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var routeId = element.id;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				loadAction(document.getElementById("locationmenu"), 'location');
				document.getElementById("search-text").value = locationId;
				search(this, 'ID', 'LOCATION');
				this.id = detailLocId;
				aboutDetail(this, 'LOCATION');
			} else {
				alert("Load view ZayCar Route form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["routeId"] = routeId;
	loading();
	request.open("GET", "closeZayCareRoute?input=" + JSON.stringify(parameter),
			true);
	request.send(null);
}

function viewStockList(element) {
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var routeId = element.id;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
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
	loading();
	request.open("GET", "getRouteStockListForm?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

$(function() {
	window.setInterval(
			function() {
				var request = new XMLHttpRequest;
				request.onreadystatechange = function() {
					if (request.readyState == 4) {
						if (request.status == 200) {
							var result = document.createElement("div");
							result.innerHTML = request.responseText;
							var remainingProcesses = $(result).children(
									"#remainingProcesses").text();
							var completedProcesses = $(result).children(
									"#completedProcesses").text();
							var totalTimeLeftInMin = $(result).children(
									"#totalTimeLeftInMin").text();
							var averageTime = $(result)
									.children("#averageTime").text();
							var totalCount = $(result).children("#totalCount")
									.text();

							showProcessingInfo(remainingProcesses,
									completedProcesses, totalTimeLeftInMin,
									averageTime, totalCount);
						}
					}
				};
				parameter = {};
				request.open("GET", "getQueueCount/", true);
				request.send();
			}, 5000)
})

function showProcessingInfo(remainingProcesses, completedProcesses,
		totalTimeLeftInMin, averageTime, totalCount) {

	// $('#completedProcesses').html("Complete Processes: " +
	// completedProcesses);
	$('#averageTime').html("Average Time per order: " + averageTime + "s");

	if (parseInt(remainingProcesses) != 0)
		$('#completedProcesses').html(
				"Completed: " + completedProcesses + "/" + totalCount).css(
				"color", "red");
	else
		$('#completedProcesses').html(
				"Completed: " + completedProcesses + "/" + totalCount).css(
				"color", "green");

	var totalTimeLeft = parseInt(totalTimeLeftInMin)
	if (totalTimeLeft < 60)
		$('#totalTimeLeftInMin')
				.html("Total Time Left: " + totalTimeLeft + "s");
	else {
		var totalMinLeft = Math.floor(totalTimeLeft / 60);
		var totalSecLeft = totalTimeLeft % 60;
		$('#totalTimeLeftInMin').html(
				"Total Time Left: " + totalMinLeft + "m " + totalSecLeft + "s");
	}
}

function getTestForm() {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				document.getElementById("inner-workspace").innerHTML = request.responseText;
				document.getElementById("detail-Test").className = "show";

			} else {
				alert("Please try again. Error code is " + request.status);
			}
		}
	};

	request.open("GET", "testForm", true);
	request.send(null);
}

function handleKeyPress(e) {
	var key = e.keyCode || e.which;
	if (key == 13) {
		alert('Hello')
	}
}

function viewSupplierSummary() {
	var customerBoId = document.getElementById("detail-customer-boId").innerHTML;
	var startDate = document.getElementById("customerStartDate").value.trim();
	var endDate = document.getElementById("customerEndDate").value.trim();
	if (startDate == "" && endDate == "") {
		alert("Please fill the date!!!");
		return;
	}
	if (startDate == "") {
		alert("Please fill the start date!!!");
		return;
	}
	if (endDate == "") {
		alert("Please fill the end date!!!");
		return;
	}

	startDate = changeDayMonthYear($('#customerStartDate').val());
	endDate = changeDayMonthYear($('#customerEndDate').val());

	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("saleListByCustomerId").innerHTML = request.responseText;
				$("#supplier-summary").removeClass("hide");
			} else {
				alert("Load supplier summary form error. Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var supplierRecord = {};
	supplierRecord['customerBoId'] = customerBoId;
	supplierRecord['startDate'] = startDate;
	supplierRecord['endDate'] = endDate;
	loading();
	request.open("GET", "getSupplierSummaryForm?input="
			+ JSON.stringify(supplierRecord), true);
	request.send(null);
}