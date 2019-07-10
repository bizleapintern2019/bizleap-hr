function getReturnProductInformation(event, row) {
	if (event.which == 13 || event.keyCode == 13 || event.which == 9
			|| event.keyCode == 9) {
		var productNameList = document.getElementById("return_product_name_lst");
        var brandNameList = document.getElementById("return_brand_name_lst");
        var flavourList = document.getElementById("return_flavour_lst");
		var productPriceList = document.getElementById("return_product_price_lst");
		var productDiscountedList = document.getElementById("return_product_discounted_1st");
		var packagingPrice = document.getElementById("salesPackagingPrice");
		var index = -1;
		var d = row.parentNode.parentNode.rowIndex - 1;
		for (var i = 0; i < return_productList.options.length; i++) {
			if (return_productList.options[i].value == document.getElementsByName("return-product-boId")[d].value) {
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
		var name = productNameList.options[index].value;
        var brandName = brandNameList.options[index].value;
        var flavour = flavourList.options[index].value;
		var price = productPriceList.options[index].value;
		var discounted = productDiscountedList.options[index].value;
		/*
		 * var packagePrice = packagingPrice.options[index].value; var
		 * packageTypeString = packagePrice.split(";"); if
		 * ($(document.getElementsByName("productAllPrice").length != 0)) { var
		 * num = 1;
		 * $(document.getElementsByName("productAllPrice")[d]).html(""); for
		 * (var t = 0; t < packageTypeString.length; t++) { var temp =
		 * packageTypeString[t]; if (temp.trim() == "") continue; var
		 * tempPackage = temp.split(","); var optionGroup =
		 * $(document.createElement("optgroup")); var select =
		 * $(document.getElementsByName("productAllPrice")[d]); optionGroup
		 * .attr("label", tempPackage[0] + ',' + tempPackage[1]);
		 * select.append(optionGroup); for (var p = 2; p < tempPackage.length;
		 * p++) { var option = $(document.createElement("option"));
		 * option.text(tempPackage[p]); optionGroup.append(option); } if (num ==
		 * 1) { num = tempPackage[0]; } }
		 */

		// var price = productPrice.options[index].value;
		var packageType = packagingPrice.options[index].value;
		var packageTypeString = packageType.split(";");
		if ($(document.getElementsByName("productAllPrice").length != 0)) {
			$(document.getElementsByName("productAllPrice")[d]).html("");
			var num;
			for (var t = 0; t < packageTypeString.length; t++) {
				var temp = packageTypeString[t];
				if (temp.trim() == "")
					continue;
				var tempPackage = temp.split(",");
				var option = $(document.createElement("option"));
				var select = $(document.getElementsByName("productAllPrice")[d]);
				option.text(tempPackage[1]+", "+tempPackage[5]);
				option.attr("value", tempPackage[0]);
				if (t == 0)
					document.getElementsByName("return-packageId")[d].innerHTML = tempPackage[0];
				select.append(option);
			}

		} else {
			document.getElementsByName("return-packageId")[d].innerHTML = price;
		}

		document.getElementsByName("return_product_name")[d].innerHTML = name;
        document.getElementsByName("return_brand_name")[d].innerHTML = brandName;
        document.getElementsByName("return_flavour")[d].innerHTML = flavour;
		// document.getElementsByName("return_product_price")[d].innerHTML =
		// price;
		// document.getElementsByName("return_product_discounted")[d].innerHTML
		// = discounted;
		if ($(document.getElementsByName("return_Purchase_Price")[d]).length > 0) {
			document.getElementsByName("return_Purchase_Price")[d].focus();
		} else {
			document.getElementsByName("return_product_price")[d].focus();
			// document.getElementsByName("return_product_promotion")[d].focus();
		}
		$(row).removeClass("required");
		var id = row.parentNode.parentNode.id;
		if (errors[id])
			delete errors[id];
		return false;
	}
}

function checkReturnOpening(element) {
	var select = getParentByTagName(element, "select");
	var tr = getParentByTagName(element, "tr");
	updateOptionGroup();

	function updateOptionGroup() {
		// var optgroup = $(":selected", $(element)).parent().attr("label");
		var optgroup = $(":selected", $(element)).val();
		if (!optgroup)
			return;
		// var pkgId = optgroup.split(",");
		var pkgId = optgroup;
		$("[name=return-packageId]", tr).text(pkgId);
	}
}

function getParentByTagName(currentElement, tagName) {
	var element = $(currentElement);
	while (element != null) {
		if (element.prop("tagName").toLowerCase() == tagName)
			return element;
		element = element.parent();
	}
}

function mergeCustomerForm(element) {
	locationId = document.getElementById("detail-customer-location-id").innerHTML;
	var customerId = document.getElementById("detail-customer-boId").innerHTML;
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["customerId"] = customerId;
	var purchaseOrderBoidAndInvoice = element.id;
	var purchaseOrderBoId = purchaseOrderBoidAndInvoice.split(',')[0];
	var purchaseOrderInvoice = purchaseOrderBoidAndInvoice.split(',')[1];
	parameter["purchaseOrdeId"] = purchaseOrderBoId;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				var fullName = document.getElementById("detail-customer-name")
						.getElementsByTagName('span')[0].innerHTML
						+ ' '
						+ document.getElementById("detail-customer-name")
								.getElementsByTagName('span')[1].innerHTML
						+ ' '
						+ document.getElementById("detail-customer-name")
								.getElementsByTagName('span')[2].innerHTML
						+ ' '
						+ document.getElementById("detail-customer-name")
								.getElementsByTagName('span')[3].innerHTML;
				document.getElementById("mergepurchaseOrder-Id").innerHTML = purchaseOrderBoId;
				document.getElementById("mergePurchaseOrder_Date").innerHTML = document
						.getElementById("purchaseOrder_Date").innerHTML;
				document.getElementById("mergePurchaseOrder_invoiceId").innerHTML = purchaseOrderInvoice;
				document.getElementById("edit-customer-name").innerHTML = fullName;
				document.getElementById("customerBoId").innerHTML = customerId;
				document.getElementById("salesLocationId").innerHTML=document.getElementById("detail-customer-location-id").innerHTML;
			} else {
				alert("Please try again. Error code is " + request.status);
			}
		}
	};
	console.log("Parameter", parameter);
	loading();
	request.open("GET", "mergeCustomer?input=" + JSON.stringify(parameter),
			true);
	request.send(null);
}

function editPurchaseOrderForm(element) {
	locationId = document.getElementById("detail-customer-location-id").innerHTML;
	var customerId = document.getElementById("detail-customer-boId").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("salesDate").focus();
				var fullName = document.getElementById("detail-customer-name")
						.getElementsByTagName('span')[0].innerHTML
						+ ' '
						+ document.getElementById("detail-customer-name")
								.getElementsByTagName('span')[1].innerHTML
						+ ' '
						+ document.getElementById("detail-customer-name")
								.getElementsByTagName('span')[2].innerHTML
						+ ' '
						+ document.getElementById("detail-customer-name")
								.getElementsByTagName('span')[3].innerHTML;
				document.getElementById("edit-customer-name").innerHTML = fullName;
				document.getElementById("purchaseOrder-Id").innerHTML = document
						.getElementById(element.id + 'boId').innerHTML;
				document.getElementById("saleCustomerId").value = document
						.getElementById("detail-customer-boId").innerHTML;
				document.getElementById("edit-customer-type").innerHMTL = document
						.getElementById("detail-customer-customerType").innerHTML;
				document.getElementById("salesInvoice").value = document
						.getElementById(element.id + 'invoice').innerHTML;
			} else {
				alert("Please try again. Error code is " + request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["customerId"] = customerId;
	parameter["customerType"]=document.getElementById("detail-customer-customerType").innerHTML;
	parameter["purchaseOrdeId"] = element.id;
	loading();
	request.open("GET", "editPurchaseOrderForm?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

function checkTotaleditKey(event, element) {
	element = $(element);
	var tr = element.parent().parent();
	var qty = tr.children("td[name=edit_NameQty]").children("input").val();
	var price = tr.children("td[name=edit_NamePrice]").children("span").text();
	tr.children("td[name=dailySalestotal]").children("span").text(qty * price);
	document.getElementById("dailysales-total").innerHTML = PurchaseOrder
			.calculateTotal();
}

function PurchaseOrder() {
}

PurchaseOrder.calculateTotal = function() {
	var salesTable = $("#sales-table td[name=dailySalestotal] span");
	var total = 0;
	salesTable.each(function(index, element) {
		element = $(element);
		var amount = parseInt(element.text()) || 0;
		total += amount;
	});
	return total;
}

function checkTotaleditKeyNew(event, element) {
	var qty = $(element).val();
	var tr = $(element).parent().parent().parent();
	var price = tr.children("td[name=dailySalesProductPrice]").children(
			"select[name=productAllPrice]").val();
	tr.children("td[name=dailySalestotal]").children("span").text(qty * price);
	document.getElementById("dailysales-total").innerHTML = PurchaseOrder
			.calculateTotal();

}

function checkReturnEnterKey(event) {
	if (event.which == 13 || event.keyCode == 13) {
		var d = event.target.parentNode.parentNode.parentNode.rowIndex - 1;
		addRow("return-detail-purchaseOrder");
		return false;
	}
	return true;
}

function saveReturnList(description, force) {
	var custBoId = document.getElementById("return_customerId").innerHTML;
	var invoiceNumber = document.getElementById("return_invoice_no").innerHTML;
	var location = document.getElementById("return-location-id").innerHTML;
	var locationId=location.split(',');
	var request = new XMLHttpRequest;
	var parameter = {};
	parameter["return-Date"] = document.getElementById("return-Date").value;
	parameter["customerId"] = custBoId;
	parameter["locationId"] = document.getElementById("from_location_boId").innerHTML;
	parameter["toLocationBoId"]=locationId[0];
	parameter["toLocationName"]=locationId[1];
	parameter["purchaseOrderId"] = document.getElementById("return_boId").innerHTML;
	parameter["returnInvoice"] = document.getElementById("returnInvoice").value;
	if (force) {
		parameter["force"] = force;
	}
	var table = document.getElementById("return-detail-purchaseOrder");
	var rowCount = table.rows.length;
	var returnList = [];
	var originalQtyList=[];
	var rejectList = [];
	var packageTypeList = [];
	var priceList = [];
	var productCodeList = [];
	var stockTypeList=[];
	for (var i = 1; i < rowCount; i++) {
		var pcode = "";
		if (table.rows[i].cells[0].childNodes[0].getAttribute('type') == 'search')
			pcode = table.rows[i].cells[0].childNodes[0].value;
		else
			pcode = table.rows[i].cells[0].childNodes[0].innerHTML;
		var price = "";
		if(pcode=="")
			continue;
		var packageBoolean = false;
		var returnInvoiceNumber=(document.getElementById("return_invoice_no").innerHTML).toLowerCase();
        if (returnInvoiceNumber=="opening b/f") {
			var tableSelect = table.rows[i].cells[4];
			var packageType = $("select",$(tableSelect)).val();// table.rows[i].cells[2].childNodes[0].value.trim();
			var tablePrice= table.rows[i].cells[5];
			price =$("[name=return_Purchase_Price]",$(tablePrice)).val();
			var tableQty= table.rows[i].cells[6];

			var returnQty = "0";
			var tableReturnQty = $("[name=return_product_qty]",$(tableQty)).val();
			if (tableReturnQty!=""){
				returnQty=tableReturnQty;
			}

			var tableStockType=table.rows[i].cells[7];
			var stockType=$("[name=nonProfitAndLoss_StockType]",$(tableStockType)).val();
			stockTypeList.push(stockType);
			 productCodeList.push(pcode);
			 priceList.push(price);
			packageTypeList.push(packageType);
			returnList.push(returnQty);
		}
		else if ($(document.getElementById("nonProfitAndLoss_StockType")).length != 0) {
			var price=table.rows[i].cells[4].childNodes[0].innerHTML;
			priceList.push(price);
			var packageType = table.rows[i].cells[5].childNodes[1].innerHTML;

            var returnQty = "0";
            var tableReturnQty=table.rows[i].cells[7].childNodes[0].childNodes[1].value.trim();
            var tableOriginalQty=table.rows[i].cells[6].childNodes[0].innerHTML;
            originalQtyList.push(tableOriginalQty);
            if(tableReturnQty != ""){
                returnQty=tableReturnQty;
			}
		       returnList.push(returnQty);
		       productCodeList.push(pcode);
			var stockType = table.rows[i].cells[8].childNodes[0].childNodes[1].value
					.trim();
		       stockTypeList.push(stockType);
			   packageTypeList.push(packageType);
		} else {
			if (table.rows[i].cells[7].childNodes[0].childNodes[1]
					.getAttribute('type') == 'number')
				price = table.rows[i].cells[7].childNodes[0].childNodes[1].value
						.trim() ? table.rows[i].cells[7].childNodes[0].childNodes[1].value
						.trim()
						: "0";
			var tableReturnQty= table.rows[i].cells[8].childNodes[0].childNodes[1].value.trim();
			var returnQty = "0";
			if(tableReturnQty!=""){
				returnQty = tableReturnQty;
			}
			var reject = table.rows[i].cells[7].childNodes[0].childNodes[1].checked;
			var packageType = table.rows[i].cells[7].childNodes[0].childNodes[2].innerHTML;
			packageBoolean = true;
			if (returnQty > 0) {
				productCodeList.push(pcode);
				priceList.push(price);
				if (packageBoolean == true) {
					packageTypeList.push(packageType);
				}
				if (reject == false) {
					returnList.push(returnQty);
					rejectList.push("0.0");
				} else {
					rejectList.push(returnQty);
					returnList.push("0.0");
				}
				if (price == 0) {
					alert("Please insert Purchasing Price");
					return;
				}
				if (returnList.length == 0 && rejectList.length == 0) {
					alert("There is no return and reject quantity.");
					return;
				}
			}
		}
	}
	parameter["stockType"]=stockTypeList;
	parameter["productCodeList"] = productCodeList;
	parameter["returnList"] = returnList;
	parameter["rejectList"] = rejectList;
	parameter["priceList"] = priceList;
	parameter["originalQtyList"]=originalQtyList;
	parameter["packageTypeList"] = packageTypeList;
	var name=document.getElementById("return_customername").innerHTML;
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
						saveReturnList(description, "forced");
					} else {
						$('#salesInvoice').focus();
					}
					return;
				}
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				loadAction(document.getElementById("suppliermenu"), 'supplier');
				document.getElementById("search-text").value =name;
				search(this, 'NAME', 'SUPPLIER');
				this.id = custBoId;
				aboutDetail(this, 'SUPPLIER');
			} else {
				alert("Load Return form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	if (Object.keys(errors).length > 0) {
		alert("Please choose correct product Code.")
		return;
	}
	console.log(parameter);
	loading();
	request.open("GET", "saveReturnList?input=" + JSON.stringify(parameter),
			true);
	request.send();
	return;
}

function saveReturnPurchaseList(description, force) {
	var custBoId = document.getElementById("return_customerId").innerHTML;
	var invoiceNumber = document.getElementById("return_invoice_no").innerHTML;
	var toLocationId = "";;
	var toLocationName="";
	if (document.getElementById("to-location") != null) {
		var locationValue = document.getElementById("to-location").value;
		if (locationValue == "Choose") {
			alert("Please Choose Location To Return");
			return;
		}
	    locId=locationValue.split(",");
		toLocationId = locId[0];
		toLocationName=locId[1]
	}
	var locationId = document.getElementById("return-location-id").innerHTML;
	var request = new XMLHttpRequest;
	var parameter = {};
	parameter["return-Date"] = document.getElementById("return-Date").value;
	parameter["customerId"] = custBoId;
	parameter["locationId"] = locationId.split(',')[0];
	parameter["toLocationId"] = toLocationId;
	parameter["toLocationName"]=toLocationName;
	parameter["purchaseOrderId"] = document.getElementById("return_boId").innerHTML;
	parameter["returnInvoice"] = document.getElementById("returnInvoice").value;
	if (force) {
		parameter["force"] = force;
	}
	var table = document.getElementById("return-detail-purchaseOrder");
	var rowCount = table.rows.length;
	var returnList = [];
	var originalQtyList=[];
	var rejectList = [];
	var packageTypeList = [];
	var priceList = [];
	var productCodeList = [];
	var stockTypeList=[];
	for (var i = 1; i < rowCount; i++) {
		var pcode = "";
		if (table.rows[i].cells[0].childNodes[0].getAttribute('type') == 'search')
			pcode = table.rows[i].cells[0].childNodes[0].value;
		else
			pcode = table.rows[i].cells[0].childNodes[0].innerHTML;
		var price = "";
		if(pcode=="")
			continue;
		var packageBoolean = false;
		var returnInvoiceNumber=(document.getElementById("return_invoice_no").innerHTML).toLowerCase();
        if (returnInvoiceNumber=="opening b/f") {
			var tableSelect = table.rows[i].cells[4];
			var packageType = $("select",$(tableSelect)).val();// table.rows[i].cells[2].childNodes[0].value.trim();
			var tablePrice= table.rows[i].cells[5];
			price =$("[name=return_Purchase_Price]",$(tablePrice)).val();
			var tableQty= table.rows[i].cells[6];
			var returnQty = "0";
			var tableReturnQty = $("[name=return_product_qty]",$(tableQty)).val();
			if (tableReturnQty!=""){
				returnQty=tableReturnQty;
			}

			var tableStockType=table.rows[i].cells[7];
			var stockType=$("[name=nonProfitAndLoss_StockType]",$(tableStockType)).val();
			stockTypeList.push(stockType);
			 productCodeList.push(pcode);
			 priceList.push(price);
			packageTypeList.push(packageType);
			returnList.push(returnQty);
		}
		else if ($(document.getElementById("nonProfitAndLoss_StockType")).length != 0) {
			var price=table.rows[i].cells[4].childNodes[0].innerHTML;
			priceList.push(price);
			var packageType = table.rows[i].cells[5].childNodes[1].innerHTML;

            var returnQty = "0";
            var tableOriginalQty=table.rows[i].cells[6].childNodes[0].innerHTML;
            originalQtyList.push(tableOriginalQty);
            var tableReturnQty=table.rows[i].cells[7].childNodes[0].childNodes[1].value.trim();
            if(tableReturnQty != ""){
                returnQty=tableReturnQty;
			}
		       returnList.push(returnQty);
		       productCodeList.push(pcode);
			var stockType = table.rows[i].cells[8].childNodes[0].childNodes[1].value
					.trim();
		       stockTypeList.push(stockType);
			   packageTypeList.push(packageType);
		} else {
			if (table.rows[i].cells[7].childNodes[0].childNodes[1]
					.getAttribute('type') == 'number')
				price = table.rows[i].cells[7].childNodes[0].childNodes[1].value
						.trim() ? table.rows[i].cells[7].childNodes[0].childNodes[1].value
						.trim()
						: "0";
			var tableReturnQty= table.rows[i].cells[8].childNodes[0].childNodes[1].value.trim();
			var returnQty = "0";
			if(tableReturnQty!=""){
				returnQty = tableReturnQty;
			}

			var reject = table.rows[i].cells[7].childNodes[0].childNodes[1].checked;
			var packageType = table.rows[i].cells[7].childNodes[0].childNodes[2].innerHTML;
			packageBoolean = true;
			if (returnQty > 0) {
				productCodeList.push(pcode);
				priceList.push(price);
				if (packageBoolean == true) {
					packageTypeList.push(packageType);
				}
				if (reject == false) {
					returnList.push(returnQty);
					rejectList.push("0.0");
				} else {
					rejectList.push(returnQty);
					returnList.push("0.0");
				}
				if (price == 0) {
					alert("Please insert Purchasing Price");
					return;
				}
				if (returnList.length == 0 && rejectList.length == 0) {
					alert("There is no return and reject quantity.");
					return;
				}
			}
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
						saveReturnPurchaseList(description, "forced");
					} else {
						$('#salesInvoice').focus();
					}
					return;
				}
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				// printInvoiceReturn(custBoId);
				loadAction(document.getElementById("customermenu"), 'customer');
				document.getElementById("search-text").value = custBoId;
				search(this, 'ID', 'CUSTOMER');
				this.id = custBoId;
				aboutDetail(this, 'CUSTOMER');
			} else {
				alert("Load Return form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	if (Object.keys(errors).length > 0) {
		alert("Please choose correct product Code.")
		return;
	}
	parameter["stockType"]=stockTypeList;
	parameter["productCodeList"] = productCodeList;
	parameter["returnList"] = returnList;
	parameter["rejectList"] = rejectList;
	parameter["priceList"] = priceList;
	parameter["originalQtyList"]=originalQtyList;
	parameter["packageTypeList"] = packageTypeList;
	console.log("RETURN PARAMETER: ",parameter);
	loading();
	request.open("GET", "saveReturnPurchaseList?input=" +
	JSON.stringify(parameter), true);
	request.send();
	return;
}
