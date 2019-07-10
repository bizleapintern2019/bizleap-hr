var searchType = 'mainwarehouse'; // default search Type
var searchId = null;
var connectionStatus = false;
var isExport = false; // Export status
var previousId;
var pageNumber = 1;
var SEARCH = {};
var tempParameter = "";
var tempEntity = ""; // added for change detection paging
(function() {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState != 4)
			return;
		if (request.status != 200) {
			alert("Error" + request.status);
			return;
		}
		var result = request.responseText.replace("<span>", "").replace(
				"</span>", "");
		SEARCH.PAGE_SIZE = parseInt(result.trim());
		Object.freeze(SEARCH);
		// loadAction(document.getElementById("locationmenu"),'location');
	};
	request.open("GET", "init", true);
	request.send();
})();

function expandList(object) {
	var childList = object.parentNode.getElementsByTagName('ul');
	for (var j = 0; j < childList.length; j++) {
		var currentState = childList[j].style.display;
		if (currentState == "none") {
			childList[j].style.display = "block";
		} else {
			childList[j].style.display = "none";
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
var active = "";

function loadAction(currentElement, activeId, supplier) {
	active = activeId;
	if (active == "employee") {
		document.getElementById("action-employee").className = "show";
		document.getElementById("search-employee").className = "search-button";
		document.getElementById("action-supplier").className = "hide";
		document.getElementById("search-supplier").className = "hide";
		document.getElementById("search-customer").className = "hide";
		document.getElementById("search-location").className = "hide";
		document.getElementById("search-product").className = "hide";
		document.getElementById("action-customer").className = "hide";
		document.getElementById("action-Location").className = "hide";
		document.getElementById("action-product").className = "hide";
		document.getElementById("search-Text").className = "show";
		document.getElementById("search-risk").className = "hide";
		document.getElementById("editProduct") ? document
				.getElementById("editProduct").className = "hide" : "";
		// document.getElementById("deleteProduct")?document.getElementById("deleteProduct").className="hide"
		// :"";
		document.getElementById("reportCustomerReturn").className = "hide";
		if ($(document.getElementById("transfer")).length > 0) {
			document.getElementById("delete-transfer").className = "hide";
			document.getElementById("edit-transfer").className = "hide";
			document.getElementById("transfer").className = "hide";
			document.getElementById("action-transfer_from-to").className = "hide";
		}
		document.getElementById("editCustomer").className = "hide";
		document.getElementById("deleteCustomer").className = "hide";
		document.getElementById("editLocation").className = "hide";
		document.getElementById("deleteLocation").className = "hide";
		document.getElementById("convertPackageType").className = "hide";
		// document.getElementById("createRoute").className="hide";
		document.getElementById("dailySale").className = "hide";
		document.getElementById("dailySaleRetail").className = "hide";
		// document.getElementById("viewSalary").className="hide";
		document.getElementById("deleteEmployee").className = "hide";
		document.getElementById("compensationEmployee") ? document
				.getElementById("compensationEmployee").className = "hide" : "";
		document.getElementById("editEmployee").className = "hide";
		document.getElementById("location-salary") ? document
				.getElementById("location-salary").className = "hide" : "";
		document.getElementById("location-monthlyBonus") ? document
				.getElementById("location-monthlyBonus").className = "hide"
				: "";
		// document.getElementById("dailySaleMarket").className="hide";
		document.getElementById("action-purchase_order").className = "hide";
		document.getElementById("action-borrowerRisk").className = "hide";
		document.getElementById("addNewBorrowerRisk").className = "hide";
		document.getElementById("addAccount").className = "hide";
		document.getElementById("editBorrowerRisk").className = "hide";
		document.getElementById("normal").className = "show";
		document.getElementById("advance").className = "hide";
		document.getElementById("advance2").className = "hide";
		document.getElementById("advance3").className = "hide";
        document.getElementById("advance4").className = "hide";
		document.getElementById("adjustmentstock").className = "hide";
		// document.getElementById("adjustment-detail-table").className =
		// "hide";
		document.getElementById("action-adjustment").className = "hide";
		document.getElementById("reportAdjustment").className = "hide";
	} else if (active == "supplier") {
		document.getElementById("action-supplier").className = "show";
		document.getElementById("search-supplier").className = "search-button";
		document.getElementById("action-customer").className = "hide";
		document.getElementById("search-customer").className = "hide";
		document.getElementById("search-employee").className = "hide";
		document.getElementById("search-product").className = "hide";
		document.getElementById("search-location").className = "hide";
		document.getElementById("action-employee").className = "hide";
		document.getElementById("search-Text").className = "show";
		document.getElementById("search-risk").className = "hide";
		document.getElementById("action-Location").className = "hide";
		document.getElementById("action-product").className = "hide";
		document.getElementById("editProduct") ? document
				.getElementById("editProduct").className = "hide" : "";
        document.getElementById("reportCustomerReturn").className = "hide";
		if ($(document.getElementById("transfer")).length > 0) {
			document.getElementById("delete-transfer").className = "hide";
			document.getElementById("edit-transfer").className = "hide";
			document.getElementById("transfer").className = "hide";
			document.getElementById("action-transfer_from-to").className = "hide";
		}
		// document.getElementById("deleteProduct")?document.getElementById("deleteProduct").className="hide"
		// :"";

		document.getElementById("editEmployee").className = "hide";
		document.getElementById("compensationEmployee") ? document
				.getElementById("compensationEmployee").className = "hide" : "";
		document.getElementById("deleteEmployee").className = "hide";
		// document.getElementById("viewSalary").className="hide";
		// document.getElementById("createRoute").className="hide";
		document.getElementById("editLocation").className = "hide";
		document.getElementById("deleteLocation").className = "hide";
		document.getElementById("convertPackageType").className = "hide";
		document.getElementById("dailySale").className = "hide";
		document.getElementById("dailySaleRetail").className = "hide";
		document.getElementById("editCustomer").className = "hide";
		document.getElementById("deleteCustomer").className = "hide";
		document.getElementById("purchase").className = "hide";
		document.getElementById("editSupplier").className = "hide";
		document.getElementById("deleteSupplier").className = "hide";
		document.getElementById("location-salary") ? document
				.getElementById("location-salary").className = "hide" : "";
		document.getElementById("action-borrowerRisk").className = "hide";
		document.getElementById("addNewBorrowerRisk").className = "hide";
		document.getElementById("editBorrowerRisk").className = "hide";
		document.getElementById("location-monthlyBonus") ? document
				.getElementById("location-monthlyBonus").className = "hide"
				: "";
		// document.getElementById("dailySaleMarket").className="hide";
		document.getElementById("action-purchase_order").className = "hide";

		// document.getElementById("adjustment-detail-table").className =
		// "hide";
		document.getElementById("action-adjustment").className = "hide";
		document.getElementById("reportAdjustment").className = "hide";
		document.getElementById("normal").className = "show";
		document.getElementById("advance2").className = "hide";
		document.getElementById("advance3").className = "hide";
        document.getElementById("advance4").className = "hide";
		document.getElementById("advance").className = "hide";
		document.getElementById("adjustmentstock").className = "hide";
	} else if (active == "customer") {
		document.getElementById("action-customer").className = "show";
		document.getElementById("search-customer").className = "search-button";
		document.getElementById("action-supplier").className = "hide";
		document.getElementById("search-supplier").className = "hide";
		document.getElementById("search-employee").className = "hide";
		document.getElementById("search-product").className = "hide";
		document.getElementById("search-location").className = "hide";
		document.getElementById("action-employee").className = "hide";
		document.getElementById("addAccount").className = "hide";
		document.getElementById("search-Text").className = "show";
		document.getElementById("search-risk").className = "hide";
		document.getElementById("action-Location").className = "hide";
		document.getElementById("action-product").className = "hide";
		document.getElementById("editProduct") ? document
				.getElementById("editProduct").className = "hide" : "";
        document.getElementById("reportCustomerReturn").className = "hide";
		if ($(document.getElementById("transfer")).length > 0) {
			document.getElementById("delete-transfer").className = "hide";
			document.getElementById("edit-transfer").className = "hide";
			document.getElementById("transfer").className = "hide";
			document.getElementById("action-transfer_from-to").className = "hide";
		}
		// document.getElementById("deleteProduct")?document.getElementById("deleteProduct").className="hide"
		// :"";

		document.getElementById("editEmployee").className = "hide";
		document.getElementById("compensationEmployee") ? document
				.getElementById("compensationEmployee").className = "hide" : "";
		document.getElementById("deleteEmployee").className = "hide";
		// document.getElementById("viewSalary").className="hide";
		// document.getElementById("createRoute").className="hide";
		document.getElementById("editLocation").className = "hide";
		document.getElementById("deleteLocation").className = "hide";
		document.getElementById("convertPackageType").className = "hide";
		document.getElementById("dailySale").className = "hide";
		document.getElementById("dailySaleRetail").className = "hide";
		document.getElementById("editCustomer").className = "hide";
		document.getElementById("deleteCustomer").className = "hide";
		document.getElementById("location-salary") ? document
				.getElementById("location-salary").className = "hide" : "";
		document.getElementById("action-borrowerRisk").className = "hide";
		document.getElementById("addNewBorrowerRisk").className = "hide";
		document.getElementById("editBorrowerRisk").className = "hide";
		document.getElementById("location-monthlyBonus") ? document
				.getElementById("location-monthlyBonus").className = "hide"
				: "";
		// document.getElementById("dailySaleMarket").className="hide";
		document.getElementById("action-purchase_order").className = "hide";

		// document.getElementById("adjustment-detail-table").className =
		// "hide";
		document.getElementById("action-adjustment").className = "hide";
		document.getElementById("reportAdjustment").className = "hide";
		document.getElementById("normal").className = "show";
		document.getElementById("advance2").className = "hide";
		document.getElementById("advance3").className = "hide";
        document.getElementById("advance4").className = "hide";
		document.getElementById("advance").className = "hide";
		document.getElementById("adjustmentstock").className = "hide";
	} else if (active == "location") {
		document.getElementById("action-Location").className = "show";
		document.getElementById("search-location").className = "search-button";
		document.getElementById("action-supplier").className = "hide";
		document.getElementById("search-supplier").className = "hide";
		document.getElementById("search-customer").className = "hide";
		document.getElementById("search-product").className = "hide";
		document.getElementById("search-employee").className = "hide";
		document.getElementById("search-Text").className = "show";
		document.getElementById("search-risk").className = "hide";
		document.getElementById("addAccount").className = "hide";
		document.getElementById("action-customer").className = "hide";
		document.getElementById("action-employee").className = "hide";
		document.getElementById("action-product") ? document
				.getElementById("action-product").className = "hide" : "";
		document.getElementById("editProduct") ? document
				.getElementById("editProduct").className = "hide" : "";
        document.getElementById("reportCustomerReturn").className = "hide";
		if ($(document.getElementById("transfer")).length > 0) {
			document.getElementById("delete-transfer").className = "hide";
			document.getElementById("edit-transfer").className = "hide";
			document.getElementById("transfer").className = "hide";
			document.getElementById("action-transfer_from-to").className = "hide";
		}
		// document.getElementById("deleteProduct")?document.getElementById("deleteProduct").className="hide"
		// :"";
		document.getElementById("action-borrowerRisk").className = "hide";
		document.getElementById("addNewBorrowerRisk").className = "hide";
		document.getElementById("editBorrowerRisk").className = "hide";
		// document.getElementById("createRoute").className="hide";
		document.getElementById("editEmployee").className = "hide";
		document.getElementById("compensationEmployee") ? document
				.getElementById("compensationEmployee").className = "hide" : "";
		document.getElementById("deleteEmployee").className = "hide";
		// document.getElementById("viewSalary").className="hide";
		document.getElementById("editCustomer").className = "hide";
		document.getElementById("deleteCustomer").className = "hide";
		document.getElementById("dailySale").className = "hide";
		document.getElementById("dailySaleRetail").className = "hide";
		document.getElementById("editLocation").className = "hide";
		document.getElementById("deleteLocation").className = "hide";
		document.getElementById("convertPackageType").className = "hide";
		document.getElementById("location-salary") ? document
				.getElementById("location-salary").className = "hide" : "";
		document.getElementById("location-monthlyBonus") ? document
				.getElementById("location-monthlyBonus").className = "hide"
				: "";
		// document.getElementById("dailySaleMarket").className="hide";
		document.getElementById("action-purchase_order").className = "hide";
		// document.getElementById("adjustment-detail-table").className =
		// "hide";
		document.getElementById("action-adjustment").className = "hide";
		document.getElementById("reportAdjustment").className = "hide";
		document.getElementById("normal").className = "show";
		document.getElementById("advance").className = "hide";
		document.getElementById("advance2").className = "hide";
		document.getElementById("advance3").className = "hide";
        document.getElementById("advance4").className = "hide";
		document.getElementById("adjustmentstock").className = "hide";
		document.getElementById("location_pandf") ? document
				.getElementById("location_pandf").className = "hide" : "";
	} else if (active == "product") {
		document.getElementById("action-product").className = "show";
		document.getElementById("search-product").className = "search-button";
		document.getElementById("action-supplier").className = "hide";
		document.getElementById("search-supplier").className = "hide";
		document.getElementById("search-location").className = "hide";
		document.getElementById("search-customer").className = "hide";
		document.getElementById("search-employee").className = "hide";
		document.getElementById("action-customer").className = "hide";
		document.getElementById("action-Location").className = "hide";
		document.getElementById("action-employee").className = "hide";
		document.getElementById("search-risk").className = "hide";
		document.getElementById("addAccount").className = "hide";
		document.getElementById("search-Text").className = "show";
		document.getElementById("editEmployee").className = "hide";
		document.getElementById("compensationEmployee") ? document
				.getElementById("compensationEmployee").className = "hide" : "";
		// document.getElementById("createRoute").className="hide";
		document.getElementById("deleteEmployee").className = "hide";
		// document.getElementById("viewSalary").className="hide";
		document.getElementById("editCustomer").className = "hide";
		document.getElementById("deleteCustomer").className = "hide";
		document.getElementById("editLocation").className = "hide";
		document.getElementById("deleteLocation").className = "hide";
		document.getElementById("convertPackageType").className = "hide";
        document.getElementById("reportCustomerReturn").className = "hide";
		if ($(document.getElementById("transfer")).length > 0) {
			document.getElementById("transfer").className = "hide";
			document.getElementById("delete-transfer").className = "hide";
			document.getElementById("edit-transfer").className = "hide";
			document.getElementById("action-transfer_from-to").className = "hide";
		}
		document.getElementById("dailySale").className = "hide";
		document.getElementById("dailySaleRetail").className = "hide";
		document.getElementById("editProduct") ? document
				.getElementById("editProduct").className = "hide" : "";
		// document.getElementById("deleteProduct")?document.getElementById("deleteProduct").className="hide"
		// :"";
		document.getElementById("action-borrowerRisk").className = "hide";
		document.getElementById("addNewBorrowerRisk").className = "hide";
		document.getElementById("editBorrowerRisk").className = "hide";
		document.getElementById("location-salary") ? document
				.getElementById("location-salary").className = "hide" : "";
		document.getElementById("location-monthlyBonus") ? document
				.getElementById("location-monthlyBonus").className = "hide"
				: "";
		// document.getElementById("dailySaleMarket").className="hide";
		document.getElementById("action-purchase_order").className = "hide";
		document.getElementById("normal").className = "show";
		document.getElementById("advance").className = "hide";
		document.getElementById("advance2").className = "hide";
		document.getElementById("advance3").className = "hide";
        document.getElementById("advance4").className = "hide";
		document.getElementById("adjustmentstock").className = "hide";
		document.getElementById("editVolumeProduct") ? document
				.getElementById("editVolumeProduct").className = "hide" : "";
		document.getElementById("deleteVolumeProduct") ? document
				.getElementById("deleteVolumeProduct").className = "hide" : "";
	} else if (active == "borrowerrisk") {
		document.getElementById("action-borrowerRisk").className = "show";
        document.getElementById("addNewBorrowerRisk").className = "show";
		document.getElementById("action-supplier").className = "hide";
		document.getElementById("search-supplier").className = "hide";
		document.getElementById("editBorrowerRisk").className = "hide";
		document.getElementById("action-product").className = "hide";
		document.getElementById("search-product").className = "hide";
		document.getElementById("search-risk").className = "search-button";
		document.getElementById("search-location").className = "hide";
		document.getElementById("search-customer").className = "hide";
		document.getElementById("search-employee").className = "hide";
		document.getElementById("action-customer").className = "hide";
		document.getElementById("addAccount").className = "hide";
		document.getElementById("action-Location").className = "hide";
		document.getElementById("action-employee").className = "hide";
		document.getElementById("search-Text").className = "show";
		document.getElementById("editEmployee").className = "hide";
		document.getElementById("compensationEmployee") ? document
				.getElementById("compensationEmployee").className = "hide" : "";
		// document.getElementById("createRoute").className="hide";
		document.getElementById("deleteEmployee").className = "hide";
		// document.getElementById("viewSalary").className="hide";
		document.getElementById("editCustomer").className = "hide";
		document.getElementById("deleteCustomer").className = "hide";
		document.getElementById("editLocation").className = "hide";
		document.getElementById("deleteLocation").className = "hide";
		document.getElementById("convertPackageType").className = "hide";
        document.getElementById("reportCustomerReturn").className = "hide";
		if ($(document.getElementById("transfer")).length > 0) {
			document.getElementById("transfer").className = "hide";
			document.getElementById("delete-transfer").className = "hide";
			document.getElementById("edit-transfer").className = "hide";
			document.getElementById("action-transfer_from-to").className = "hide";

		}
		document.getElementById("dailySale").className = "hide";
		document.getElementById("dailySaleRetail").className = "hide";
		document.getElementById("editProduct") ? document
				.getElementById("editProduct").className = "hide" : "";
		// document.getElementById("deleteProduct")?document.getElementById("deleteProduct").className="hide"
		// :"";
		document.getElementById("location-salary") ? document
				.getElementById("location-salary").className = "hide" : "";
		document.getElementById("location-monthlyBonus") ? document
				.getElementById("location-monthlyBonus").className = "hide"
				: "";
		// document.getElementById("dailySaleMarket").className="hide";
		document.getElementById("action-purchase_order").className = "hide";
		document.getElementById("normal").className = "show";
		document.getElementById("advance").className = "hide";
		document.getElementById("advance2").className = "hide";
		document.getElementById("advance3").className = "hide";
        document.getElementById("advance4").className = "hide";
		document.getElementById("adjustmentstock").className = "hide";
		document.getElementById("editVolumeProduct") ? document
				.getElementById("editVolumeProduct").className = "hide" : "";
		document.getElementById("deleteVolumeProduct") ? document
				.getElementById("deleteVolumeProduct").className = "hide" : "";
	} else if (active == "purchase_order") {
		var request = new XMLHttpRequest;
		request.onreadystatechange = function() {
			if (request.readyState != 4)
				return;

			if (request.status != 200) {
				alert("Error" + request.status);
				return;
			}
			var result = parseNewJson(request.responseText);
			SEARCH.PAGE_SIZE = result.pageSize;
			var locationList = result.locationList;
			var customerList = result.customerList;
			if (supplier == "supplier") {
				$("#searchReportCustomer").attr("placeholder","Choose Supplier..");
				document.getElementById("locationNameReport").className = "hide";
				document.getElementById("supplier_purchaseOrder").className = "show";
				document.getElementById("location_purchaseOrder").className = "hide";
				var select = document.getElementById("porderlocationname");
				select.innerHTML = "";
				for ( var index in locationList) {
					if (locationList[index].type == "SUPPLIER") {
						var option = document.createElement("option");
						option.value = locationList[index].id;
						option.innerHTML = locationList[index].name;
						select.appendChild(option);
					}
				}
				var dataList = document.getElementById("customerList");
				dataList.innerHTML = "";
				for ( var index in customerList) {
					if (customerList[index].type == "BRANCH") {
						var option = document.createElement("option");
						var span = document.createElement("span");
						option.setAttribute("id", customerList[index].id);
						span.innerHTML = customerList[index].name;
						option.appendChild(span);
						dataList.appendChild(option);
					}
				}
				var select1 = document.getElementById("saleCustomerId");
				select1.innerHTML = "";
				for ( var index in customerList) {
					if (customerList[index].type == "BRANCH") {
						var option = document.createElement("option");
						option.value = customerList[index].id;
						option.innerHTML = customerList[index].name;
						select1.appendChild(option);
					}
				}
			} else {
				$("#searchReportCustomer").attr("placeholder","Choose Customer..");
				document.getElementById("porderlocationname").className = "show";
				document.getElementById("locationNameReport").className = "show";
				document.getElementById("supplier_purchaseOrder").className = "hide";
				document.getElementById("location_purchaseOrder").className = "show";
				var select = document.getElementById("porderlocationname");
				select.innerHTML = "";
				if (locationList.length != 1) {
					var option = document.createElement("option");
					option.value = "ALL";
					option.innerHTML = "ALL";
					select.appendChild(option);
				}
				for ( var index in locationList) {
					if (locationList[index].type != "SUPPLIER") {
						var option = document.createElement("option");
						option.value = locationList[index].id;
						option.innerHTML = locationList[index].name;
						select.appendChild(option);
					}
				}
				var dataList = document.getElementById("customerList");
				dataList.innerHTML = "";
				for ( var index in customerList) {
					if (customerList[index].type != "BRANCH") {
						var option = document.createElement("option");
						var span = document.createElement("span");
						option.setAttribute("id", customerList[index].id);
						span.innerHTML = customerList[index].name;
						option.appendChild(span);
						dataList.appendChild(option);
					}
				}
				var select1 = document.getElementById("saleCustomerId");
				select1.innerHTML = "";
				for ( var index in customerList) {
					if (customerList[index].type != "BRANCH") {
						var option = document.createElement("option");
						option.value = customerList[index].id;
						option.innerHTML = customerList[index].name;
						select1.appendChild(option);
					}
				}
			}
			// parseInt(result.trim());
			// updateLocationList(result.locationList);

			Object.freeze(SEARCH);

			$('#porderlocationname').prop('selectedIndex', 0);
			$('#searchReport').prop('selectedIndex', 0);
			$("#searchReportSupplier").prop('selectedIndex',0);
			document.getElementById("porderEndDate").value = "";
			document.getElementById("porderStartDate").value = "";
			document.getElementById("selectedRoute").className = "hide";
			document.getElementById("action-supplier").className = "hide";
			document.getElementById("search-supplier").className = "hide";
			document.getElementById("action-product").className = "hide";
			document.getElementById("searchReportCustomer").value = "";
			document.getElementById("ChannelOfDistributionSelect").className = "hide";
			// document.getElementById("locationNameReport").className = "show";
			document.getElementById("porderlocationname").className = "show";
			document.getElementById("search-product").className = "hide";
			document.getElementById("search-location").className = "hide";
			document.getElementById("search-customer").className = "hide";
			document.getElementById("search-risk").className = "hide";
			document.getElementById("search-employee").className = "hide";
			document.getElementById("action-customer").className = "hide";
			document.getElementById("action-Location").className = "hide";
			document.getElementById("action-employee").className = "hide";
			document.getElementById("editEmployee").className = "hide";
			document.getElementById("addAccount").className = "hide";
			document.getElementById("customerSearch").className = "hide";
			// document.getElementById("createRoute").className="hide";
			document.getElementById("deleteEmployee").className = "hide";
			document.getElementById("compensationEmployee") ? document
					.getElementById("compensationEmployee").className = "hide"
					: "";
			document.getElementById("action-borrowerRisk").className = "hide";
			document.getElementById("addNewBorrowerRisk").className = "hide";
			document.getElementById("editBorrowerRisk").className = "hide";
			// document.getElementById("viewSalary").className="hide";
			document.getElementById("editCustomer").className = "hide";
			document.getElementById("deleteCustomer").className = "hide";
			document.getElementById("editLocation").className = "hide";
			document.getElementById("deleteLocation").className = "hide";
			document.getElementById("convertPackageType").className = "hide";
			if ($(document.getElementById("transfer")).length > 0) {
				document.getElementById("transfer").className = "hide";
				document.getElementById("action-transfer_from-to").className = "hide";
				document.getElementById("delete-transfer").className = "hide";
				document.getElementById("edit-transfer").className = "hide";
			}

			document.getElementById("dailySale").className = "hide";
			document.getElementById("dailySaleRetail").className = "hide";
			document.getElementById("editProduct") ? document
					.getElementById("editProduct").className = "hide" : "";
			document.getElementById("action-purchase_order").className = "hide";
			// document.getElementById("adjustment-detail-table").className =
			// "hide";
			document.getElementById("action-adjustment").className = "hide";
			document.getElementById("reportAdjustment").className = "hide";
			// document.getElementById("deleteProduct")?document.getElementById("deleteProduct").className="hide"
			// :"";

			document.getElementById("location-salary") ? document
					.getElementById("location-salary").className = "hide" : "";
			document.getElementById("location-monthlyBonus") ? document
					.getElementById("location-monthlyBonus").className = "hide"
					: "";
			document.getElementById("selectForInvoice").className = "hide";
			document.getElementById("search-textForInvoice").className = "hide";
			document.getElementById("searchadvance").className = "hide";
			document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
			// document.getElementById("dailySaleMarket").className="hide";
			document.getElementById("normal").className = "hide";
			document.getElementById("advance").className = "show";
			document.getElementById("advance2").className = "hide";
			document.getElementById("advance3").className = "hide";
            document.getElementById("advance4").className = "hide";
			document.getElementById("adjustmentstock").className = "hide";

			$("#porderStartDate").removeClass("hide");
			$("#porderEndDate").removeClass("hide");
			$("#myStartDate").removeClass("hide");
			$("#myEndDate").removeClass("hide");
			$("#idStartDate").removeClass("hide");
			$("#idEndDate").removeClass("hide");
		};
		request.open("GET", "init/json", true);
		request.send();

		/*
		 * function updateLocationList(locationList){ var select =
		 * $("#porderlocationname"); select[0].innerHTML = ""; var allOption =
		 * document.createElement("option"); allOption.innerHTML = "All";
		 * allOption.value = "ALL" select.append(allOption); for(var index in
		 * locationList){ var option = document.createElement("option");
		 * option.innerHTML = locationList[index].name; option.value =
		 * locationList[index].id; select.append(option); } }
		 */

	} else if (active == "salary") {
		var request = new XMLHttpRequest;
		request.onreadystatechange = function() {
			if (request.readyState != 4)
				return;

			if (request.status != 200) {
				alert("Error" + request.status);
				return;
			}
			var result = request.responseText.replace("<span>", "").replace(
					"</span>", "");
			SEARCH.PAGE_SIZE = parseInt(result.trim());
			Object.freeze(SEARCH);
			$("#salary-date")
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
											$.datepicker.formatDate('mm/yy',
													new Date(year, month, 1)));
								}
							});
			$("#salary-date").focus(function() {
				$(".ui-datepicker-calendar").hide();
				$("#ui-datepicker-div").position({
					my : "center top",
					at : "center bottom",
					of : $(this)
				});
			});

			// document.getElementById("adjustment-detail-table").className =
			// "hide";
			document.getElementById("action-adjustment").className = "hide";
			document.getElementById("reportAdjustment").className = "hide";
			document.getElementById("action-purchase_order").className = "hide";
			if ($(document.getElementById("transfer")).length > 0) {
				document.getElementById("action-transfer_from-to").className = "hide";
				document.getElementById("delete-transfer").className = "hide";
				document.getElementById("edit-transfer").className = "hide";
				document.getElementById("transfer").className = "hide";
			}

			document.getElementById("action-product").className = "hide";
			document.getElementById("search-product").className = "hide";
			document.getElementById("action-supplier").className = "hide";
			document.getElementById("search-supplier").className = "hide";
			document.getElementById("search-risk").className = "hide";
			document.getElementById("search-location").className = "hide";
			document.getElementById("search-customer").className = "hide";
			document.getElementById("search-employee").className = "hide";
			document.getElementById("action-customer").className = "hide";
			document.getElementById("action-Location").className = "hide";
			document.getElementById("action-employee").className = "hide";
			document.getElementById("editEmployee").className = "hide";
			// document.getElementById("createRoute").className="hide";
			document.getElementById("action-borrowerRisk").className = "hide";
			document.getElementById("addNewBorrowerRisk").className = "hide";
			document.getElementById("editBorrowerRisk").className = "hide";
			document.getElementById("deleteEmployee").className = "hide";
			// document.getElementById("viewSalary").className="hide";
			document.getElementById("editCustomer").className = "hide";
			document.getElementById("deleteCustomer").className = "hide";
			document.getElementById("editLocation").className = "hide";
			document.getElementById("deleteLocation").className = "hide";
			document.getElementById("convertPackageType").className = "hide";
			document.getElementById("dailySale").className = "hide";
			document.getElementById("dailySaleRetail").className = "hide";
			document.getElementById("editProduct") ? document
					.getElementById("editProduct").className = "hide" : "";

			// document.getElementById("deleteProduct")?document.getElementById("deleteProduct").className="hide"
			// :"";

			document.getElementById("location-salary") ? document
					.getElementById("location-salary").className = "hide" : "";
			document.getElementById("location-monthlyBonus") ? document
					.getElementById("location-monthlyBonus").className = "hide"
					: "";

			// document.getElementById("dailySaleMarket").className="hide";
			document.getElementById("normal").className = "hide";
			document.getElementById("advance").className = "hide";
			document.getElementById("advance2").className = "show";
			document.getElementById("advance3").className = "hide";
            document.getElementById("advance4").className = "hide";
			document.getElementById("adjustmentstock").className = "hide";
		};
		request.open("GET", "init", true);
		request.send();

	} else if (active == "specialReport") {
		/*
		 * $('#specialStartDate').datepicker({ dateFormat : 'dd/mm/yy' });
		 * $('#specialEndDate').datepicker({ dateFormat : 'dd/mm/yy' });
		 * $('#speciallocationname').prop('selectedIndex', 0);
		 * document.getElementById("specialEndDate").value = "";
		 * document.getElementById("specialStartDate").value = "";
		 */
		$("#specialDate")
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
										$.datepicker.formatDate('mm/yy',
												new Date(year, month, 1)));
							}
						});

		$("#specialDate").focus(function() {
			$(".ui-datepicker-calendar").hide();
			$("#ui-datepicker-div").position({
				my : "center top",
				at : "center bottom",
				of : $(this)
			});
		});
		document.getElementById("specialDate").value = "";

		document.getElementById("action-product").className = "hide";
		document.getElementById("search-product").className = "hide";
		document.getElementById("action-supplier").className = "hide";
		document.getElementById("search-supplier").className = "hide";
		document.getElementById("search-location").className = "hide";
		document.getElementById("search-customer").className = "hide";
		document.getElementById("search-employee").className = "hide";
		document.getElementById("action-customer").className = "hide";
		document.getElementById("search-risk").className = "hide";
		document.getElementById("action-Location").className = "hide";
		document.getElementById("action-employee").className = "hide";
		document.getElementById("editEmployee").className = "hide";
		document.getElementById("compensationEmployee") ? document
				.getElementById("compensationEmployee").className = "hide" : "";
		document.getElementById("deleteEmployee").className = "hide";
		document.getElementById("editCustomer").className = "hide";
		document.getElementById("deleteCustomer").className = "hide";
		document.getElementById("editLocation").className = "hide";
		document.getElementById("deleteLocation").className = "hide";
		document.getElementById("convertPackageType").className = "hide";
		if ($(document.getElementById("transfer")).length > 0) {
			document.getElementById("transfer").className = "hide";
			document.getElementById("action-transfer_from-to").className = "hide";

		}

		document.getElementById("dailySale").className = "hide";
		document.getElementById("dailySaleRetail").className = "hide";
		document.getElementById("editProduct") ? document
				.getElementById("editProduct").className = "hide" : "";
		document.getElementById("action-borrowerRisk").className = "hide";
		document.getElementById("addNewBorrowerRisk").className = "hide";
		document.getElementById("editBorrowerRisk").className = "hide";
		document.getElementById("location-salary") ? document
				.getElementById("location-salary").className = "hide" : "";
		document.getElementById("location-monthlyBonus") ? document
				.getElementById("location-monthlyBonus").className = "hide"
				: "";
		// document.getElementById("dailySaleMarket").className="hide";
		document.getElementById("action-purchase_order").className = "hide";
		document.getElementById("normal").className = "hide";
		document.getElementById("advance").className = "hide";
		document.getElementById("advance2").className = "hide";
        document.getElementById("advance4").className = "hide";
		document.getElementById("advance3").className = "show";
		document.getElementById("adjustmentstock").className = "hide";
		document.getElementById("editVolumeProduct") ? document
				.getElementById("editVolumeProduct").className = "hide" : "";
		document.getElementById("deleteVolumeProduct") ? document
				.getElementById("deleteVolumeProduct").className = "hide" : "";
	}
	//For Change Detection
	else if(active == "changeDetection"){
		//Show and Hide components
         document.getElementById("porderEndDate").value = "";
         document.getElementById("porderStartDate").value = "";
         document.getElementById("advance4").className = "show";

		 document.getElementById("search-result").className = "hide";
         document.getElementById("action-borrowerRisk").className = "hide";
         document.getElementById("addNewBorrowerRisk").className = "hide";
         document.getElementById("action-supplier").className = "hide";
         document.getElementById("search-supplier").className = "hide";
         document.getElementById("editBorrowerRisk").className = "hide";
         document.getElementById("action-product").className = "hide";
         document.getElementById("search-product").className = "hide";
         document.getElementById("search-risk").className = "hide";
         document.getElementById("search-location").className = "hide";
         document.getElementById("search-customer").className = "hide";
         document.getElementById("search-employee").className = "hide";
         document.getElementById("action-customer").className = "hide";
         document.getElementById("addAccount").className = "hide";
         document.getElementById("action-Location").className = "hide";
         document.getElementById("action-employee").className = "hide";
         document.getElementById("search-Text").className = "hide";
         document.getElementById("editEmployee").className = "hide";
         document.getElementById("compensationEmployee") ? document.getElementById("compensationEmployee").className = "hide" : "";
         document.getElementById("deleteEmployee").className = "hide";
         document.getElementById("editCustomer").className = "hide";
         document.getElementById("deleteCustomer").className = "hide";
         document.getElementById("editLocation").className = "hide";
         document.getElementById("deleteLocation").className = "hide";
         document.getElementById("convertPackageType").className = "hide";
         document.getElementById("reportCustomerReturn").className = "hide";
         if ($(document.getElementById("transfer")).length > 0) {
             document.getElementById("transfer").className = "hide";
             document.getElementById("delete-transfer").className = "hide";
             document.getElementById("edit-transfer").className = "hide";
             document.getElementById("action-transfer_from-to").className = "hide";
         }
         document.getElementById("dailySale").className = "hide";
         document.getElementById("dailySaleRetail").className = "hide";
         document.getElementById("editProduct") ? document.getElementById("editProduct").className = "hide" : "";
         document.getElementById("location-salary") ? document.getElementById("location-salary").className = "hide" : "";
         document.getElementById("location-monthlyBonus") ? document.getElementById("location-monthlyBonus").className = "hide" : "";
         document.getElementById("action-purchase_order").className = "hide";
         document.getElementById("normal").className = "show";
         document.getElementById("advance").className = "hide";
         document.getElementById("advance2").className = "hide";
         document.getElementById("advance3").className = "hide";
         document.getElementById("adjustmentstock").className = "hide";
         document.getElementById("editVolumeProduct") ? document.getElementById("editVolumeProduct").className = "hide" : "";
         document.getElementById("deleteVolumeProduct") ? document.getElementById("deleteVolumeProduct").className = "hide" : "";
		 //Call ChangeDetection List With Page Number
         getEntityListByPageNumber();
	} else {
		// document.getElementById("advance").className="hide";
		// document.getElementById("normal").className="show";
	}

	document.getElementById("content").innerHTML = "";
	document.getElementById("detail").innerHTML = "";
	document.getElementById("search-result").innerHTML = "";
	document.getElementById("search-text").value = "";
	var action = document.getElementById("action-menu").getElementsByTagName(
			"li");
	for (var i = 0; i < action.length; i++) {
		action[i].className = "";
	}
	currentElement.className = "action-active";

	$("#normal input[type=text]").val("");
	$("#advance input[type=text]").val("");
	$("#advance2 input[type=text]").val("");
}

function getEntityListByPageNumber(pageNumber){
    // check is another proccess is still working
    if (connectionStatus || active == "")
        return;

    var parameter = {};

    if (typeof pageNumber === 'undefined')
        pageNumber = 1;
    parameter["pageNumber"] = pageNumber.toString();
    parameter["pageSize"] = SEARCH.PAGE_SIZE.toString();

    var request = new XMLHttpRequest;
    request.onreadystatechange = function() {
        if (request.readyState != 4)
            return;
        if (request.status != 200) {
            alert("Error return " + request.status);
            return;
        }
        hideLoading();
        hideButtonBySearch();
        document.getElementById("content").innerHTML = request.responseText;

        var total = document.getElementById("search-total").innerHTML;
        var pageCount = Math.ceil(total / SEARCH.PAGE_SIZE);
        document.getElementById("pagination-descriptor").innerHTML = parameter.pageNumber
            + "/" + pageCount;
        if (total < 10)
            document.getElementById("pagination").className = "hide";
        document.getElementById("detail").innerHTML = "";
    }

    tempEntity = parameter;

    console.log("parameter ", parameter);
    request.open("GET", "getEntityChange?input="+ JSON.stringify(parameter), true);
    connectionStatus = true; // working
    request.send();
    loading();
}


var detailId = false;

function getFormatedTodayDate() {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1;
	var yy = today.getFullYear();

	if (dd < 10)
		dd = '0' + dd;

	if (mm < 10)
		mm = '0' + mm;

	return dd + '/' + mm + '/' + yy;
}

function getCustomerBoIdReport(event) {
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
		document.getElementById("searchReportCustomer").setAttribute('style',
		'color:red;width: 250px;');
		$("#searchCustomer").focus();
		return;
	} else {
		document.getElementById("daily-saleCustomerId").innerHTML = $(custList.options[index]).attr("id");
		document.getElementById("saleCustomerId").value =$(custList.options[index]).attr("id");
		document.getElementById("searchReportCustomer").setAttribute('style',
		'color:black;width: 250px;');
		 $("#searchReportCustomer").focus();
	}
}

function aboutDetail(element, entityType, startDate, endDate, isRoute) {
    var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("detail").innerHTML = request.responseText;
				document.getElementById("detail-table").className = "show";
				if (entityType == "EMPLOYEE") {
					$("#employee-detail-table").removeClass("hide").addClass("show");
					// document.getElementById("viewSalary").className="show";
					$("#compensationEmployee").removeClass("hide").addClass("show");
					$("#deleteEmployee").removeClass("hide").addClass("show");
					$("#editEmployee").removeClass("hide").addClass("show");
					$("#addAccount").removeClass("hide").addClass("show");
				} else if (entityType == "LOCATION") {
					if (isRoute)
						document.getElementById('tab2').checked = true;
					$("#detail-stockList").tablesorter({
						widgets : [ 'zebra' ]
					});
					// $("#transfer").addClass("show");
					$("#detail-table").removeClass("hide").addClass("show");
					//$("#editLocation").addClass("show");
					$("#editLocation").removeClass("hide").addClass("show");
					$("#deleteLocation").removeClass("hide").addClass("show");
					$("#import").removeClass("hide").addClass("show");
					$("#convertPackageType").removeClass("hide").addClass("show");
					$("#location-detail-table").removeClass("hide").addClass("show");

					// document.getElementById("createRoute").className="show";
					// document.getElementById("dailySaleMarket").className="show";
					// /document.getElementById("location_pandf") ? document
					// .getElementById("location_pandf").className = "show" :
					// "";
					var type=element['data-type'] || $(element).attr("data-type");	      
					var id= element.id;
					console.log("type ",type);
					if (type == "FACTORY" || type == "LONG_TERM_WAREHOUSE") {
						document.getElementById("dailySale").className = "hide";
						document.getElementById("dailySaleRetail").className = "hide";
						document.getElementById("report-stockList").className = "hide";
						document.getElementById("adjustmentstock").className = "hide";
						document.getElementById("location-salary") ? document
								.getElementById("location-salary").className = "hide"
								: "";
						document.getElementById("location-monthlyBonus") ? document
								.getElementById("location-monthlyBonus").className = "hide"
								: "";
						document.getElementById("location_pandf") ? document
								.getElementById("location_pandf").className = "hide"
								: "";
						if ($("#transfer").length > 0) {
							$("#transfer").removeClass("hide").addClass("show");
						}
					} else if (type == "CAR" || type == "OUTLET"
							|| type == "MAIN_WAREHOUSE"
							|| type == "RETAIL_WAREHOUSE") {
						document.getElementById("dailySale").className = "hide";
						document.getElementById("dailySaleRetail").className = "hide";
						document.getElementById("adjustmentstock").className = "show";
						$("#location-salary").addClass("show");
						$("#location-monthlyBonus").removeClass("hide").addClass("show");
						if ($(document.getElementById("transfer")).length > 0) {
							document.getElementById("transfer").className = "show";
						}
						document.getElementById("location_pandf") ? document
								.getElementById("location_pandf").className = "hide"
								: "";
						// document.getElementById("location-salary").className
						// = "show"
						// document.getElementById("location-monthlyBonus").className
						// = "show"
					} else if (type == "SUPPLIER") {
						document.getElementById("dailySale").value = "Purchase";
						document.getElementById("dailySale").className = "show";
						document.getElementById("dailySaleRetail").className = "hide";
						document.getElementById("adjustmentstock").className = "hide";
						document.getElementById("location-info").className = "hide";
						$("#location-salary").removeClass("hide").addClass("show");
						$("#location-monthlyBonus").addClass("show");
						document.getElementById("location_pandf") ? document
								.getElementById("location_pandf").className = "hide"
								: "";
						if ($(document.getElementById("transfer")).length > 0) {
							document.getElementById("transfer") ? document
									.getElementById("transfer").className = "hide"
									: "";
						}
						document.getElementById("convertPackageType").className = "hide";
                        getPurchaseOrderListWithCredit();
                        document.getElementById("customerStartDate").valueAsDate = new Date();
                        document.getElementById("customerEndDate").valueAsDate = new Date();
						// document.getElementById("location-salary").className
						// = "show"
						// document.getElementById("location-monthlyBonus").className
						// = "show"
					} else if (type == "WAREHOUSE") {
						document.getElementById("dailySale").className = "hide";
						document.getElementById("dailySaleRetail").className = "hide";
						document.getElementById("adjustmentstock").className = "show";
						document.getElementById("location-salary") ? document
								.getElementById("location-salary").className = "show"
								: "";
						document.getElementById("location-monthlyBonus") ? document
								.getElementById("location-monthlyBonus").className = "show"
								: "";
						document.getElementById("location_pandf") ? document
								.getElementById("location_pandf").className = "show"
								: "";
						if ($("#transfer").length > 0) {
							$("#transfer").addClass("show");
						}
					} else if (type == "MAIN_LOCATION") {
						document.getElementById("dailySale").className = "show";
						document.getElementById("dailySaleRetail").className = "show";
						document.getElementById("adjustmentstock").className = "hide";
						$("#location-salary").addClass("show");
						$("#location-monthlyBonus").addClass("show");
						document.getElementById("location_pandf") ? document
								.getElementById("location_pandf").className = "hide"
								: "";
						if ($(document.getElementById("transfer")).length > 0) {
							document.getElementById("transfer") ? document
									.getElementById("transfer").className = "hide"
									: "";
						}

						// document.getElementById("location-salary").className
						// = "show"
						// document.getElementById("location-monthlyBonus").className
						// = "show"
					} else {
						if ($("#isNonProfitAndLoss").text()) {
							document.getElementById("dailySale").className = "show";
							document.getElementById("dailySale").value = "Dealer";
							document.getElementById("dailySaleRetail").value = "Consumer";
							document.getElementById("dailySaleRetail").className = "show";
						} else {
							document.getElementById("dailySale").className = "show";
							document.getElementById("dailySaleRetail").className = "show";
						}
						document.getElementById("adjustmentstock").className = "show";
						document.getElementById("location-salary") ? document
								.getElementById("location-salary").className = "show"
								: "";
						document.getElementById("location-monthlyBonus") ? document
								.getElementById("location-monthlyBonus").className = "show"
								: "";
						document.getElementById("location_pandf") ? document
								.getElementById("location_pandf").className = "show"
								: "";
						if ($("#transfer").length > 0) {
							$("#transfer").addClass("show");
						}
					}
				} else if (entityType == "CUSTOMER") {
					document.getElementById("customer-detail-table").className = "show";
					document.getElementById("editCustomer").className = "show";
					document.getElementById("deleteCustomer").className = "show";
                    getPurchaseOrderListWithCredit();

					// if (startDate == undefined || endDate == undefined) {
                    //     document.getElementById("customerStartDate").valueAsDate = new Date();
                    //     document.getElementById("customerEndDate").valueAsDate = new Date();
					// } else {
					// 	$('#customerStartDate').val(startDate);
					// 	$('#customerEndDate').val(endDate);
					// }
					// $('#searchInvoiceCustomer').click();
				} else if (entityType == "SUPPLIER") {
                   	document.getElementById("supplier-detail-table").className = "show";
					document.getElementById("editSupplier").className = "show";
					document.getElementById("deleteSupplier").className = "show";
					document.getElementById("purchase").className = "show";
                    getPurchaseOrderListWithCredit();
					// if (startDate == undefined || endDate == undefined) {
                    //     document.getElementById("customerStartDate").valueAsDate = new Date();
                    //     document.getElementById("customerEndDate").valueAsDate = new Date();
					// } else {
					// 	$('#customerStartDate').val(startDate);
					// 	$('#customerEndDate').val(endDate);
					// }
					// $('#searchInvoiceCustomer').click();
				} else if (entityType == "PRODUCT") {
					document.getElementById("product-detail-table").className = "show";
					document.getElementById("editProduct") ? document
							.getElementById("editProduct").className = "show"
							: "";
					// document.getElementById("deleteProduct")?document.getElementById("deleteProduct").className="show"
					// :"";
					document.getElementById("editVolumeProduct") ? document
							.getElementById("editVolumeProduct").className = "show"
							: "";
					document.getElementById("deleteVolumeProduct") ? document
							.getElementById("deleteVolumeProduct").className = "show"
							: "";

				} else if (entityType == "BORROWERRISK") {
					document.getElementById("borrowerrisk-detail-table").className = "show";
					// document.getElementById("action-borrowerRisk").className="hide";
					// document.getElementById("addNewBorrowerRisk").className="hide";
					document.getElementById("editBorrowerRisk").className = "show";
				} else if (entityType == "PURCHASE_ORDER") {
					document.getElementById("action-purchase_order").className = "show";
					document.getElementById("purchase_order-detail-table").className = "show";
					if ($(document.getElementById("action-transfer_from-to")).length > 0) {
						document.getElementById("action-transfer_from-to").className = "hide";
						document.getElementById("delete-transfer").className = "hide";
						document.getElementById("edit-transfer").className = "hide";
					}
					document.getElementById("action-adjustment").className = "hide";
					document.getElementById("reportAdjustment").className = "hide";
				} else if (entityType == "TRANSFER") {
					document.getElementById("transfer-detail-table").className = "show";
					document.getElementById("action-transfer_from-to").className = "show";
					document.getElementById("delete-transfer").className = "show";
					document.getElementById("edit-transfer").className = "show";
					document.getElementById("action-adjustment").className = "hide";
					document.getElementById("action-purchase_order").className = "hide";
					document.getElementById("reportAdjustment").className = "hide";
				} else if (entityType == "ADJUSTMENT") {
					document.getElementById("adjustment-detail-table").className = "show";
					document.getElementById("action-adjustment").className = "show";
					document.getElementById("reportAdjustment").className = "show";
					if ($(document.getElementById("action-transfer_from-to")).length > 0) {
						document.getElementById("delete-transfer").className = "hide";
						document.getElementById("edit-transfer").className = "hide";
						document.getElementById("action-transfer_from-to").className = "hide";
					}
					document.getElementById("action-purchase_order").className = "hide";
				} else if (entityType == "PAYMENT") {
					//search("","","");
					document.getElementById("payment-detail-table").className = "show";
					document.getElementById("invoice-number").innerHTML = document.getElementById("pid").innerHTML;
                    document.getElementById("customer-name").innerHTML = document.getElementById("cname").innerHTML;
				} else if (entityType == "CUSTOMER_RETURN") {
					document.getElementById("customerreturn-detail-table").className = "show";
				} else if (entityType == "CUSTOMER_REJECT") {
					document.getElementById("customerreject-detail-table").className = "show";
				} else {

				}

				if (detailId) {
					detailId.className = "";
					// element.className = "active";
				}
				element.className = "active";
				detailId = element;
			} else {
				alert("Error return :" + request.status);
			}
		}
	};
	parameter = {};
	console.log("Purchase Order BOID : ", element.id);
	parameter["boId"] = element.id;
	if (entityType == "PURCHASE_ORDER" || entityType == "TRANSFER") {
		parameter["locId"] = document.getElementById(element.id + "loc").innerHTML;
		if (entityType == "PURCHASE_ORDER") {
			var tr = getParentByTagName(element, "tr");
			parameter["customerBoId"] = $("[name=customerBoId]", tr).text();
		}
	}
	console.log("parameter", parameter);
	loading();
	request.open("GET", "detail/" + entityType + "?input="
			+ JSON.stringify(parameter), true);
	request.send();
}

function showMoreEmployee(element) {
	$("#show-more-employee").show(700);
	element.value = "<<";
	element.attributes["onclick"].value = "showLessEmployee(this);";
}

function showLessEmployee(element) {
	$("#show-more-employee").hide(700);
	element.value = ">>";
	element.attributes["onclick"].value = "showMoreEmployee(this);";
}

function showMoreCustomer(element) {
	$("#show-more-customer").show(700);
	element.value = "<<";
	element.attributes["onclick"].value = "showLessCustomer(this);";
}

function showLessCustomer(element) {
	$("#show-more-customer").hide(700);
	element.value = ">>";
	element.attributes["onclick"].value = "showMoreCustomer(this);";
}

function displayPaginationState(size, pageNumber) {
	var pageCount = (Math.ceil(parseInt(size / SEARCH.PAGE_SIZE)) + (size
			% SEARCH.PAGE_SIZE > 0 ? 1 : 0));
	pageNumber = Math.max(Math.min(pageNumber, pageCount), 1);
	document.getElementById("employee-pagination-descriptor").innerHTML = pageNumber
			+ "/" + pageCount;
}
var detailId = false;

function viewSalary() {
	var empId = document.getElementById("detail-employee-boId").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("salary-emp-name").innerHTML = document
						.getElementById("detail-employee-name").innerHTML;
				document.getElementById("salary-emp-rank").innerHTML = document
						.getElementById("detail-employee-position").innerHTML;
				// document.getElementById("salary-emp-location").innerHTML=document.getElementById("detail-employee-location").innerHTML;
				$("#salary-date")
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

				$("#salary-date").focus(function() {
					$(".ui-datepicker-calendar").hide();
					$("#ui-datepicker-div").position({
						my : "center top",
						at : "center bottom",
						of : $(this)
					});
				});
			} else {
				alert("Load view Salary form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["empId"] = empId;
	loading();
	request.open("GET", "viewSalary?input=" + JSON.stringify(parameter), true);
	request.send(null);
}

function openPaymentForm(element, locationBoId, totalBalance) {
	var customerId = document.getElementById("detail-customer-boId").innerHTML;
	var totalBalance = totalBalance;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
                if(document.getElementById(element.id + 'currency_type').innerHTML == "MMK"){
                    document.getElementById("currency").className = "hide";
                }
				document.getElementById("payment_paymentDate").value = today();
				document.getElementById("paymet_location").innerHTML = document
						.getElementById("detail-customer-location").innerHTML;
				document.getElementById("paymet_location_id").innerHTML = document
						.getElementById("detail-customer-location-id").innerHTML;
				document.getElementById("payment_location_type").innerHTML=document.getElementById("detail-location-type").innerHTML;
				document.getElementById("payment_customerId").innerHTML = document
						.getElementById("detail-customer-boId").innerHTML;
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
				document.getElementById("payment_customername").innerHTML = fullName;
				document.getElementById("payment_balance").innerHTML = document
						.getElementById(element.id + 'balance').innerHTML;
				document.getElementById('lastPayment').innerHTML = document
						.getElementById(element.id + 'balance').innerHTML;
				document.getElementById("payment_netAmount").innerHTML = document
						.getElementById(element.id + 'netAmount').innerHTML;
				document.getElementById("payment_invoice_no").innerHTML = document
						.getElementById(element.id + 'invoice').innerHTML;
				document.getElementById("payInvoice").value = document
						.getElementById(element.id + 'invoice').innerHTML
						+ '-' + document.getElementById("payInvoice").value;
				document.getElementById("payment_boId").innerHTML = document
						.getElementById(element.id + 'boId').innerHTML;
				document.getElementById("totalBalance").innerHTML = totalBalance;
				var currencyType = document.getElementById(element.id + 'currency_type').innerHTML;
                $('span[name=currency]').text("("+currencyType+")");
                document.getElementById("currencyType").innerHTML = currencyType;
			} else {
				alert("Load payment form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["customerId"] = customerId;
	parameter["purchaseOrdeId"] = element.id;
	parameter["locationBoId"] = locationBoId;
	loading();
	request.open("GET", "customerPaymentForm?input="
			+ JSON.stringify(parameter), true);
	request.send(null);

}

function editPayment(element) {
	var purchaseOrderId = document.getElementById("purchaseOrderId_payment").innerHTML;
	var customerId = document.getElementById("customerId_payment").innerHTML;
	var dateStr = document.getElementById("purchaseOrderId_date").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("edit_payment_location_type").innerHTML = document.getElementById("detail-location-type").innerHTML;
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
                document.getElementById("edit_payment_customer_name").innerHTML = fullName;
				var obj = $("#payment-edit tr td input[name=payment_date]");
				obj.each(function(index, element) {
					element = $(element);
					/*element.datepicker({
						dateFormat : 'dd-mm-yy',
						changeYear : true,
						changeMonth : true,
						minDate : new Date(dateStr)
					});*/
				});
				document.getElementById("edit-purchaseOrderId_payment").innerHTML = purchaseOrderId;
				document.getElementById("edit-customerId_payment").innerHTML = customerId;
				//document.getElementById("edit-purchaseOrderId_date").innerHTML = dateStr;
				
			} else {
				alert("Load payment form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["customerId"] = customerId;
	parameter["purchaseOrdeId"] = purchaseOrderId;
	loading();
	request.open("GET", "editPaymentDetailForm?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

function editSavePaymentList() {
	var customerId = document.getElementById("edit-customerId_payment").innerHTML;
    var locationType=document.getElementById("edit_payment_location_type").innerHTML;
    var customerName=document.getElementById("edit_payment_customer_name").innerHTML;
	var pOrder_date = document.getElementById("edit-purchaseOrderId_date").innerHTML;
	var pDate = new Date(pOrder_date);
	var obj = $("#payment-edit tr td");
	var payMentIdList = $("#payment-edit tr td span[name=payment_boId]");
	var dateList = $("#payment-edit tr td input[name=payment_date]");
	var payAmountList = $("#payment-edit tr td input[name=payment_amount]");
	var luckyDrawAmountList = $("#payment-edit tr td input[name=luckyDrawAmount]")
	var commissionList = $("#payment-edit tr td input[name=payment_commission]");
	// var payTypeList=$("#payment-edit tr td input[name=pay-type]");
	var remarkList = $("#payment-edit tr td input[name=payment_remark]");
	var invoiceList = $("#payment-edit tr td input[name=payment_invoiceId]");
	var currencyTypeList = $("#payment-edit tr td span[name=payment_currencyType]");
	var exchangeRateList = $("#payment-edit tr td input[name=payment_exchangeRate]");
	var date = {};
	var payAmount = {};
	var luckyDrawAmount = {};
	var commission = {};
	var payType = {};
	var remark = {};
	var invoice = {};
	var payTypeList = {};
	var currencyType = {};
	var exchangeRate = {};
	for (var i = 0; i < payMentIdList.length; i++) {
		date[payMentIdList[i].innerHTML] = dateList[i].value.trim() ? dateList[i].value
				.trim()
				: "";
		var d = new Date(date[payMentIdList[i].innerHTML].trim());
		if (pDate > d)
			errors[payMentIdList[i].innerHTML] = "error";
		else {
			if (errors[payMentIdList[i].innerHTML])
				delete errors[payMentIdList[i].innerHTML];
		}

		var payAmountInt = parseInt(payAmountList[i].value.trim() ? payAmountList[i].value
				.trim()
				: "0");
		var luckyDrawAmountInt = parseInt(luckyDrawAmountList[i].value.trim() ? luckyDrawAmountList[i].value
				.trim()
				: "0");
		payAmount[payMentIdList[i].innerHTML] = (payAmountInt + luckyDrawAmountInt)
				+ "";

		luckyDrawAmount[payMentIdList[i].innerHTML] = luckyDrawAmountList[i].value
				.trim() ? luckyDrawAmountList[i].value.trim() : "";
		commission[payMentIdList[i].innerHTML] = commissionList[i].value.trim() ? commissionList[i].value
				.trim()
				: "";
		payTypeList[payMentIdList[i].innerHTML] = $(
				"#payment-edit tr td input[name=pay-type-" + i + "]:checked")
				.val();

		invoice[payMentIdList[i].innerHTML] = invoiceList[i].value.trim() ? invoiceList[i].value
				.trim()
				: "";
		remark[payMentIdList[i].innerHTML] = remarkList[i].value.trim() ? remarkList[i].value
				.trim()
				: "";
        currencyType[payMentIdList[i].innerHTML] = currencyTypeList[i].innerHTML.trim() ? currencyTypeList[i].innerHTML
                .trim()
            : "";
        exchangeRate[payMentIdList[i].innerHTML] = exchangeRateList[i].value.trim() ? exchangeRateList[i].value
                .trim()
            : "";
	}
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				// loadAction(document.getElementById("customermenu"), 'customer');
				// document.getElementById("search-text").value = customerId;
				// search(this, 'ID', 'CUSTOMER');
				// this.id = customerId;
				// aboutDetail(this, 'CUSTOMER');
                if(locationType=="SUPPLIER"){
                    loadAction(document.getElementById("suppliermenu"), 'supplier');
                    document.getElementById("search-text").value = customerName;
                    search(this, 'NAME', 'SUPPLIER');
                    this.id = customerId;
                    aboutDetail(this, 'SUPPLIER');
                }else{
                    loadAction(document.getElementById("customermenu"), 'customer');
                    document.getElementById("search-text").value = customerName;
                    search(this, 'NAME', 'CUSTOMER');
                    this.id = customerId;
                    aboutDetail(this, 'CUSTOMER');
                }
			} else {
				alert("Load payment form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	if (Object.keys(errors).length > 0) {
		alert("Please Check Payment Date!")
		return;
	}
	parameter["purchaseOrdeId"] = document
			.getElementById("edit-purchaseOrderId_payment").innerHTML;
	parameter["customerId"] = document
			.getElementById("edit-customerId_payment").innerHTML;
	parameter["date"] = date;
	parameter["invoiceId"] = invoice;
	parameter["payAmount"] = payAmount;
	parameter["luckyDrawAmount"] = luckyDrawAmount;
	parameter["commission"] = commission;
	parameter["remark"] = remark;
	parameter["currencyType"] = currencyType;
	parameter["exchangeRate"] = exchangeRate;
	parameter["payType"] = payTypeList;
	console.log("parameter", parameter);
	loading();
	request.open("GET", "editSavePaymentList?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

function openPaymentViewDetail(element) {
	var customerId = document.getElementById("detail-customer-boId").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("purchaseOrderId_payment").innerHTML = element.id;
				document.getElementById("purchaseOrderId_date").innerHTML = document
						.getElementById(element.id + 'date').innerHTML;
				document.getElementById("customerId_payment").innerHTML = customerId;
			} else {
				alert("Load payment form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["customerId"] = customerId;
	parameter["purchaseOrdeId"] = element.id;
	request.open("GET", "getPaymentDetailForm?input="
			+ JSON.stringify(parameter), true);
	request.send(null);

}

function changeLocation(element) {
	var locations = document.getElementById(element.id).getElementsByTagName(
			"option");
	var currentIndex = element.selectedIndex;
	var locationId = locations[currentIndex].value;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	request.open("GET", "getTransferForm?input=" + JSON.stringify(parameter),
			true);
	request.send(null);
}

function loadTransferForm() {
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var request = new XMLHttpRequest;
	$('#workspace').addClass("myFilter");
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("from-location").innerHTML = document
						.getElementById("detail-location-name").innerHTML;

				document.getElementById("transfer-date").value = today();
			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	loading();
	request.open("GET", "getTransferForm?input=" + JSON.stringify(parameter),
			true);
	request.send(null);
}

function increase() {
	var amount = document.getElementById("transfer-amount").attributes["value"].value;
	amount += 100;
	document.getElementById("transfer-amount").innerHTML = amount;
}

function decrease() {
	var amount = document.getElementById("transfer-amount").attributes["value"].value;
	amount -= 100;
	document.getElementById("transfer-amount").innerHTML = amount;
}

function transferProduct() {

}

function getAjaxObject() {
	return new XMLHttpRequest();
}

function connectToServer(connection, request) {
	request.open(connection.method, connection.url, true);
	request.send(null);
}
var dayInMonth = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

function convertDate(string) {
	var currentDate = new Date;
	if (currentDate.getFullYear() % 4 == 0) { // for leap year
		dayInMonth[1] = 29;
	}
	var date = {};
	string = string.replace(/\//g, "-");
	var inputDate = string.split("-");
	if (inputDate.length > 3) {
		date["status"] = false;
		date["error"] = "Invalid date format.";
		return date;
	}
	if (inputDate.length == 1 && inputDate[0] == "") {
		date["status"] = true;
		var currentDate = new Date();
		date["day"] = currentDate.getDate();
		date["month"] = currentDate.getMonth() + 1;
		date["year"] = currentDate.getFullYear();
		date["startDay"] = 1;
		date["endDay"] = dayInMonth[currentDate.getMonth()];
		date["startMonth"] = 1;
		date["endMonth"] = 12;
		date["startYear"] = 1980; // assume this company is found 1980
		date["endYear"] = currentDate.getFullYear();
		return date;
	}
	if (inputDate.length == 1) { // year
		var year = parseInt(inputDate[0]);
		if (isNaN(year)) {
			date["status"] = false;
			date["error"] = "Invalid format.";
			return date;
		}
		if (year < 1) {
			date["status"] = false;
			date["error"] = "Input can\'t be less than one.";
			return date;
		}
		if (year < 32) {
			if (year > dayInMonth[currentDate.getMonth()]) {
				date["status"] = false;
				date["error"] = "Day is this can\'t be greater than "
						+ (dayInMonth[currentDate.getMonth()]);
				return date;
			}
			date["status"] = true;
			date["day"] = year;
			date["month"] = currentDate.getMonth() + 1;
			date["year"] = currentDate.getFullYear();
			date["startDay"] = year;
			date["endDay"] = year;
			date["startMonth"] = date["month"];
			date["endMonth"] = date["month"];
			date["startYear"] = date["year"];
			date["endYear"] = date["year"];
			return date;
		}
		if (year > currentDate.getFullYear()) {
			date["status"] = false;
			date["error"] = "Year can\'t be greater than current year.";
			return date;
		}
		date["status"] = true;
		date["day"] = currentDate.getDate();
		date["month"] = currentDate.getMonth() + 1;
		date["year"] = year;
		date["startDay"] = 1;
		date["endDay"] = dayInMonth[currentDate.getMonth()];
		date["startMonth"] = 1;
		date["endMonth"] = 12;
		date["startYear"] = date["year"];
		date["endYear"] = date["year"];
		return date;
	}
	if (inputDate.length == 2) {
		inputDate[0] = parseInt(inputDate[0]);
		if (isNaN(inputDate[0])) {
			date["status"] = false;
			date["error"] = "Invalid date format.";
			return date;
		}
		inputDate[1] = parseInt(inputDate[1]);
		if (isNaN(inputDate[1])) {
			date["status"] = false;
			date["error"] = "Invalid date format.";
			return date;
		}
		if (inputDate[0] < 1 || inputDate[1] < 1) {
			date["status"] = false;
			date["error"] = "Invalid date format.";
			return date;
		}
		if (inputDate[1] > 12) { // it would be year
			if (inputDate[0] > 12) {
				date["status"] = false;
				date["error"] = "Month can\'t be greater than 12.";
				return date;
			}
			if (inputDate[1] > currentDate.getFullYear()) {
				date["status"] = false;
				date["error"] = "Year can\'t by greater than current year.";
				return date;
			}
			date["status"] = true;
			date["day"] = currentDate.getDate();
			date["month"] = inputDate[0];
			date["year"] = inputDate[1];
			date["startDate"] = 1;
			date["endDate"] = dayInMonth[currentDate.getMonth()];
			date["startMonth"] = date["month"];
			date["endMonth"] = date["month"];
			date["startYear"] = inputDate[1];
			date["endYear"] = inputDate[1];
			return date;
		} else {
			if (inputDate[0] > dayInMonth[currentDate.getMonth()]) {
				date["status"] = false;
				date["error"] = "Day can\'t be greater than "
						+ (dayInMonth[currentDate.getMonth()]);
				return date;
			}
			date["status"] = true;
			date["day"] = inputDate[0];
			date["month"] = inputDate[1];
			date["year"] = currentDate.getFullYear();
			date["startDay"] = inputDate[0];
			date["endDay"] = inputDate[0];
			date["startMonth"] = inputDate[1];
			date["endMonth"] = inputDate[1];
			date["startYear"] = date["year"];
			date["endYear"] = date["year"];
			return date;
		}
	} else {
		inputDate[0] = parseInt(inputDate[0]);
		if (isNaN(inputDate[0])) {
			date["status"] = false;
			date["error"] = "Invalid date format.";
			return date;
		}
		inputDate[1] = parseInt(inputDate[1]);
		if (isNaN(inputDate[1])) {
			date["status"] = false;
			date["error"] = "Invalid date format";
			return date;
		}
		inputDate[2] = parseInt(inputDate[2]);
		if (isNaN(inputDate[2])) {
			date["status"] = false;
			date["error"] = "Invalid date format.";
			return date;
		}
		if (inputDate[0] < 1 || inputDate[1] < 1 || inputDate[2] < 1) {
			date["status"] = false;
			date["error"] = "Input date can\'t be  less than 1.";
			return date;
		}
		if (inputDate[0] > dayInMonth[currentDate.getMonth()]) {
			date["status"] = false;
			date["error"] = "Day can\'t be greater than "
					+ (dayInMonth[currnetDate.getMonth()]);
			return date;
		}
		if (inputDate[1] > 12) {
			date["status"] = false;
			date["error"] = "Month can\'t be greater than 12.";
			return date;
		}
		if (inputDate[2] > currentDate.getFullYear()) {
			date["status"] = false;
			date["error"] = "Year can\'t be greater than current year.";
			return date;
		}
		date["status"] = true;
		date["day"] = inputDate[0];
		date["month"] = inputDate[1];
		date["year"] = inputDate[2];
		date["startDay"] = date["day"];
		date["endDay"] = date["day"];
		date["startMonth"] = date["month"];
		date["endMonth"] = date["month"];
		date["startYear"] = date["year"];
		date["endYear"] = date["year"];
		return date;
	}
}

function showError(message, errorCode) {
	if (errorCode == 0) {
		alert("Can't connect to server. Please restart your application server");
		return;
	}
	alert(message + " error code is :" + errorCode);
}


function search(element, searchType, entityType, pageNumber, routeReport) {
	// check is another proccess is still working
	if (connectionStatus || active == "")
		return;

	if (tempParameter != "" && tempParameter != undefined) {
		var previous = tempParameter.element;
		previous.className = "";
	}
	var parameter = {};
	parameter["searchType"] = searchType;
	parameter["value"] = document.getElementById("search-text").value.trim();
	var trimtext = document.getElementById("search-text").value.trim();
	if (searchType != "ALL" && searchType != "LOCATIONID_DATE"
			&& searchType != "CHANNEL_OF_DISTRIBUTION"
			&& searchType != "ROUTEID_DATE" && searchType != "DATE_RANGE"
			&& searchType != "ROUTE_PURCHASE_ORDER"
			&& searchType != "CUSTOMERBOID_DATE" && trimtext == ""
			&& searchType != "TRANSFER_TO_DATE"
			&& searchType != "TRANSFER_FROM_DATE"
			&& searchType != "ADJUSTMENT_DATE"
			&& searchType != "INVOICE"
			&& searchType != "PAGINATION") {
		document.getElementById("search-result").className = "errortext";
		document.getElementById("search-result").innerHTML = "Please insert  text to search!";
		connectionStatus = false;
		return;
	}
	if (typeof pageNumber === 'undefined')
		pageNumber = 1;
	parameter["pageNumber"] = pageNumber.toString();
	parameter["pageSize"] = SEARCH.PAGE_SIZE.toString();
	parameter.entityType = entityType;
	parameter.element = element;
	element.className = "search-active ui button basic blue small";
	if (searchType == "INVOICE") {
		if ($("#porderStartDate").val() == "") {
			alert("Please fill Start Date!");
			return;
		}
		if ($("#porderEndDate").val() == "") {
			alert("Please fill End Date!");
			return;
		}
		var index = document.getElementById("porderlocationname").selectedIndex;
		parameter["value"] = document.getElementById("search-textForInvoice").value
				.trim() ? document.getElementById("search-textForInvoice").value
				.trim()
				: "ALL";
		parameter["locationBoId"] = document
				.getElementById("porderlocationname").value;
		parameter["locationName"] = document
				.getElementById("porderlocationname").options[index].innerHTML;
		parameter["pordersearchdate"] = changeDayMonthYear(document
				.getElementById("porderStartDate").value);
		parameter["porderenddate"] =  changeDayMonthYear(document.getElementById("porderEndDate").value);
	}
	if (searchType == "Joining_Date") {
		parameter.searchType = "JOINING_DATE";
		var searchDate = parameter["value"].trim().split("to");
		if (searchDate.length == 1) {
			var parameterDate = convertDate(parameter["value"]);
			if (!parameterDate.status) {
				document.getElementById("search-result").innerHTML = parameterDate.error;
				return;
			}
			parameter["value"] = parameterDate["day"] + "/"
					+ parameterDate["month"] + "/" + parameterDate["year"];
		} else if (searchDate.length == 2) {
			var startDate = convertDate(searchDate[0]);
			var endDate = convertDate(searchDate[1]);
			if (!startDate.status) {
				document.getElementById("search-result").innerHTML = startDate.error;
			} else if (!startDate.status) {
				document.getElementById("search-result").innerHTML = endDate.error;
			} else {
				parameter["startDate"] = startDate.startDay + "/"
						+ startDate.startMonth + "/" + startDate.startYear;
				parameter["endDate"] = endDate.endDay + "/" + endDate.endMonth
						+ "/" + endDate.endYear;
				parameter.searchType = "DATE_RANGE";
			}
		} else {
			document.getElementById("search-result").innerHTML = "Invalid input";
			return;
		}
	}
	if ((searchType == "ROUTEID_DATE" || searchType == "DATE_RANGE")
			&& entityType == "ROUTE") {
		if ($("#porderStartDate").val() == "") {
			alert("Please fill Start Date!");
			return;
		}
		if ($("#porderEndDate").val() == "") {
			alert("Please fill End Date!");
			return;
		}
		var index = document.getElementById("porderlocationname").selectedIndex;
		if (document.getElementById("porderlocationname").options[index].value != null
				&& document.getElementById("porderStartDate").value != null
				&& document.getElementById("porderEndDate").value != null) {
			if (searchType == 'ROUTEID_DATE') {
				parameter["value"] = element.value;
			} else {
				parameter["value"] = document
						.getElementById("porderlocationname").options[index].value;
			}
            parameter["pordersearchdate"] = changeDayMonthYear(document
                .getElementById("porderStartDate").value);
            parameter["porderenddate"] = changeDayMonthYear(document
                .getElementById("porderEndDate").value);
		}
		document.getElementById("searchadvance").className = "hide";
		document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
		document.getElementById("action-adjustment").className = "hide";
		document.getElementById("reportAdjustment").className = "hide";
	}
	if (searchType == "CHANNEL_OF_DISTRIBUTION"
			&& entityType == "PURCHASE_ORDER") {
		if ($("#porderStartDate").val() == "") {
			alert("Please fill Start Date!");
			return;
		}
		if ($("#porderEndDate").val() == "") {
			alert("Please fill End Date!");
			return;
		}
		var index = document.getElementById("porderlocationname").selectedIndex;
		if (document.getElementById("porderlocationname").options[index].value != null
				&& document.getElementById("porderStartDate").value != null
				&& document.getElementById("porderEndDate").value != null) {
			parameter["value"] = document
					.getElementById("ChannelOfDistributionSelect").value;
			parameter["locationBoId"] = document
					.getElementById("porderlocationname").options[index].value;
            parameter["pordersearchdate"] = changeDayMonthYear(document
                .getElementById("porderStartDate").value);
            parameter["porderenddate"] = changeDayMonthYear(document
                .getElementById("porderEndDate").value);
		}
		document.getElementById("searchadvance").className = "hide";
		document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
		document.getElementById("action-adjustment").className = "hide";
		document.getElementById("reportAdjustment").className = "hide";
	}
	if (searchType == "CUSTOMERBOID_DATE" && entityType == "PURCHASE_ORDER") {
		if ($("#porderStartDate").val() == "") {
			alert("Please fill Start Date!");
			return;
		}
		if ($("#porderEndDate").val() == "") {
			alert("Please fill End Date!");
			return;
		}
		var index = document.getElementById("porderlocationname").selectedIndex;
		if (document.getElementById("porderlocationname").options[index].value != null
				&& document.getElementById("porderStartDate").value != null
				&& document.getElementById("porderEndDate").value != null) {
			parameter["value"] = document.getElementById("daily-saleCustomerId").innerHTML;
            parameter["pordersearchdate"] = changeDayMonthYear(document
                .getElementById("porderStartDate").value);
            parameter["porderenddate"] = changeDayMonthYear(document
                .getElementById("porderEndDate").value);
		}
		document.getElementById("action-adjustment").className = "hide";
		document.getElementById("reportAdjustment").className = "hide";
		document.getElementById("searchadvance").className = "hide";
		document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
	}
	if (searchType == "LOCATIONID_DATE" && entityType == "PURCHASE_ORDER") {
		if ($("#porderStartDate").val() == "") {
			alert("Please fill Start Date!");
			return;
		}
		if ($("#porderEndDate").val() == "") {
			alert("Please fill End Date!");
			return;
		}
		var index = document.getElementById("porderlocationname").selectedIndex;
		if (document.getElementById("porderlocationname").options[index].value != null
				&& document.getElementById("porderStartDate").value != null
				&& document.getElementById("porderEndDate").value != null) {
			parameter["value"] = document.getElementById("porderlocationname").options[index].value;
            parameter["pordersearchdate"] = changeDayMonthYear(document
                .getElementById("porderStartDate").value);
            parameter["porderenddate"] = changeDayMonthYear(document
                .getElementById("porderEndDate").value);
		}

		document.getElementById("action-adjustment").className = "hide";
		document.getElementById("reportAdjustment").className = "hide";
		document.getElementById("searchadvance").className = "hide";
		document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
	}

    if (searchType == "LOCATIONID_DATE" && entityType == "CUSTOMER_RETURN") {
        if ($("#porderStartDate").val() == "") {
            alert("Please fill Start Date!");
            return;
        }
        if ($("#porderEndDate").val() == "") {
            alert("Please fill End Date!");
            return;
        }
        var index = document.getElementById("porderlocationname").selectedIndex;
        if (document.getElementById("porderlocationname").options[index].value != null
            && document.getElementById("porderStartDate").value != null
            && document.getElementById("porderEndDate").value != null) {
            parameter["value"] = document.getElementById("porderlocationname").options[index].value;
            parameter["pordersearchdate"] = changeDayMonthYear(document
                .getElementById("porderStartDate").value);
            parameter["porderenddate"] = changeDayMonthYear(document
                .getElementById("porderEndDate").value);
        }
        document.getElementById("reportPurcahseOrder").className = "hide";
        document.getElementById("action-adjustment").className = "hide";
        document.getElementById("reportAdjustment").className = "hide";
        document.getElementById("searchadvance").className = "hide";
        document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
    }

    if (searchType == "CUSTOMERBOID_DATE" && entityType == "CUSTOMER_RETURN") {
        if ($("#porderStartDate").val() == "") {
            alert("Please fill Start Date!");
            return;
        }
        if ($("#porderEndDate").val() == "") {
            alert("Please fill End Date!");
            return;
        }
        var index = document.getElementById("porderlocationname").selectedIndex;
        if (document.getElementById("porderlocationname").options[index].value != null
            && document.getElementById("porderStartDate").value != null
            && document.getElementById("porderEndDate").value != null) {
            parameter["value"] = document.getElementById("daily-saleCustomerId").innerHTML;
            parameter["pordersearchdate"] = changeDayMonthYear(document
                .getElementById("porderStartDate").value);
            parameter["porderenddate"] = changeDayMonthYear(document
                .getElementById("porderEndDate").value);
        }
        document.getElementById("reportPurcahseOrder").className = "hide";
        document.getElementById("action-adjustment").className = "hide";
        document.getElementById("reportAdjustment").className = "hide";
        document.getElementById("searchadvance").className = "hide";
        document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
    }

	if (searchType == "TRANSFER_TO_DATE" && entityType == "TRANSFER") {
		if ($("#porderStartDate").val() == "") {
			alert("Please fill Start Date!");
			document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
			return;
		}
		if ($("#porderEndDate").val() == "") {
			alert("Please fill End Date!");
			document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
			return;
		}
		var index = document.getElementById("porderlocationname").selectedIndex;
		if (document.getElementById("porderlocationname").options[index].value != null
				&& document.getElementById("porderStartDate").value != null
				&& document.getElementById("porderEndDate").value != null) {
			parameter["value"] = document.getElementById("porderlocationname").options[index].value;
            parameter["pordersearchdate"] = changeDayMonthYear(document
                .getElementById("porderStartDate").value);
            parameter["porderenddate"] = changeDayMonthYear(document
                .getElementById("porderEndDate").value);
		}
		document.getElementById("action-adjustment").className = "hide";
		document.getElementById("reportAdjustment").className = "hide";
		document.getElementById("searchadvance").className = "hide";
		document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
	}

	if (searchType == "TRANSFER_FROM_DATE" && entityType == "TRANSFER") {
		if ($("#porderStartDate").val() == "") {
			alert("Please fill Start Date!");
			document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
			return;
		}
		if ($("#porderEndDate").val() == "") {
			alert("Please fill End Date!");
			document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
			return;
		}
		var index = document.getElementById("porderlocationname").selectedIndex;
		if (document.getElementById("porderlocationname").options[index].value != null
				&& document.getElementById("porderStartDate").value != null
				&& document.getElementById("porderEndDate").value != null) {
			parameter["value"] = document.getElementById("porderlocationname").options[index].value;
            parameter["pordersearchdate"] = changeDayMonthYear(document
                .getElementById("porderStartDate").value);
            parameter["porderenddate"] = changeDayMonthYear(document
                .getElementById("porderEndDate").value);
		}
		document.getElementById("action-adjustment").className = "hide";
		document.getElementById("reportAdjustment").className = "hide";
		document.getElementById("searchadvance").className = "hide";
		document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
	}

	if (searchType == "ADJUSTMENT_DATE" && entityType == "ADJUSTMENT") {
		if ($("#porderStartDate").val() == "") {
			alert("Please fill Start Date!");
			document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
			return;
		}
		if ($("#porderEndDate").val() == "") {
			alert("Please fill End Date!");
			document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
			return;
		}
		var index = document.getElementById("porderlocationname").selectedIndex;
		if (document.getElementById("porderlocationname").options[index].value != null
				&& document.getElementById("porderStartDate").value != null
				&& document.getElementById("porderEndDate").value != null) {
			parameter["value"] = document.getElementById("porderlocationname").options[index].value;
            parameter["pordersearchdate"] = changeDayMonthYear(document
                .getElementById("porderStartDate").value);
            parameter["porderenddate"] = changeDayMonthYear(document
                .getElementById("porderEndDate").value);
		}
		if ($(document.getElementById("action-transfer_from-to")).length > 0) {
			document.getElementById("action-transfer_from-to").className = "hide";
		}
		document.getElementById("searchadvance").className = "hide";
		document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
	}
    var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState != 4)
			return;
		if (request.status != 200) {
			alert("Error return " + request.status);
			return;
		}
		hideLoading();
		if (entityType == "PURCHASE_ORDER") {
			document.getElementById("action-purchase_order").className = "show";
			document.getElementById("reportPurcahseOrder").className = "show";
            document.getElementById("reportCustomerReturn").className = "hide";
			document.getElementById("reportSalesItems").className = "hide";
			$('#reportPurcahseOrder').attr("onclick",
					"reportPurchaseOrder(this,'reportPurcahseOrder');");
			if ($(document.getElementById("reportTransferFrom")).length > 0) {
				document.getElementById("reportTransferFrom").className = "hide";
				document.getElementById("delete-transfer").className = "hide";
				document.getElementById("edit-transfer").className = "hide";
				document.getElementById("reportTransferTo").className = "hide";
			}
			// element.className = "search-active ui button basic blue small";
		}
		if (entityType == "ROUTE") {
			document.getElementById("selectedRoute").className = "show";
			document.getElementById("searchadvanceForInvoice").className = "show ui button blue basic small";
			document.getElementById("search-textForInvoice").className = "hide";
			document.getElementById("selectForInvoice").className = "hide";
			document.getElementById("searchadvance").className = "hide";
			document.getElementById("customerSearch").className = "hide";
			document.getElementById("locationNameReport").className = "show";
			document.getElementById("porderlocationname").className = "show";
            document.getElementById("reportCustomerReturn").className = "hide";
		}
		if (entityType == "INVOICE") {
			document.getElementById("searchadvance").className = "hide";
			element.className = "search-active ui button basic blue small";
            document.getElementById("reportCustomerReturn").className = "hide";
		}
		if (entityType == "PAYMENT") {
			document.getElementById("reportPurcahseOrder").className = "hide";
			document.getElementById("searchadvance").className = "hide";
			element.className = "search-active ui button basic blue small";
            document.getElementById("reportCustomerReturn").className = "hide";
		}

		if (entityType == "CUSTOMER_RETURN") {
            document.getElementById("action-customer-return").className = "show";
			document.getElementById("reportCustomerReturn").className = "show";
			document.getElementById("searchadvance").className = "hide";
			element.className = "search-active ui button basic blue small";
		}

		if (entityType == "CUSTOMER_REJECT") {
			document.getElementById("reportPurcahseOrder").className = "hide";
			document.getElementById("searchadvance").className = "hide";
			element.className = "search-active ui button basic blue small";
            document.getElementById("reportCustomerReturn").className = "hide";
		}

		if (entityType == "TRANSFER") {
			document.getElementById("reportPurcahseOrder").className = "show";
			document.getElementById("searchadvanceForInvoice").className = "show ui button blue basic small";
			document.getElementById("searchadvance").className = "hide";
            document.getElementById("reportCustomerReturn").className = "hide";
			element.className = "search-active ui button basic blue small";
			$('#reportPurcahseOrder').attr("onclick",
					"reportTransferFromStockList()")
		}
		if (searchType == "TRANSFER_FROM_DATE") {
			document.getElementById("action-transfer_from-to").className = "show";
			document.getElementById("reportTransferFrom").className = "show";
			// document.getElementById("delete-transfer").className="show";
			// document.getElementById("edit-transfer").className="show";
			document.getElementById("reportTransferTo").className = "hide";
            document.getElementById("edit-transfer").className = "hide";
            document.getElementById("delete-transfer").className = "hide";
			document.getElementById("action-purchase_order").className = "hide";
			document.getElementById("searchadvance").className = "hide";
			element.className = "search-active ui button basic blue small";
            document.getElementById("reportCustomerReturn").className = "hide";
		}
		if (searchType == "TRANSFER_TO_DATE") {
			document.getElementById("action-transfer_from-to").className = "show";
            document.getElementById("reportTransferTo").className = "show";
			// document.getElementById("delete-transfer").className="show";
			// document.getElementById("edit-transfer").className="show";
			document.getElementById("reportTransferFrom").className = "hide";
            document.getElementById("edit-transfer").className = "hide";
            document.getElementById("delete-transfer").className = "hide";
			document.getElementById("searchadvance").className = "hide";
			document.getElementById("action-purchase_order").className = "hide";
			element.className = "search-active ui button basic blue small";
            document.getElementById("reportCustomerReturn").className = "hide";
		}
		if (searchType == "ADJUSTMENT_DATE") {
			if ($(document.getElementById("action-transfer_from-to")).length > 0) {
				document.getElementById("action-transfer_from-to").className = "hide";
				document.getElementById("reportTransferTo").className = "hide";
				document.getElementById("reportTransferFrom").className = "hide";
				document.getElementById("delete-transfer").className = "hide";
				document.getElementById("edit-transfer").className = "hide";
			}
			document.getElementById("searchadvance").className = "hide";
			document.getElementById("action-purchase_order").className = "hide";
            document.getElementById("reportCustomerReturn").className = "hide";
			element.className = "search-active ui button basic blue small";
		}
		hideButtonBySearch();
		document.getElementById("content").innerHTML = request.responseText;
		var total = document.getElementById("search-total").innerHTML;
        $('#search-result').removeClass('errortext');
		document.getElementById("search-result").innerHTML = "(" + total
				+ ") found.";
		document.getElementById("search-text").value = parameter["value"];
		var pageCount = Math.ceil(total / SEARCH.PAGE_SIZE);
		document.getElementById("pagination-descriptor").innerHTML = parameter.pageNumber
				+ "/" + pageCount;
		if (total < 10)
			document.getElementById("pagination").className = "hide";
		document.getElementById("detail").innerHTML = "";

	}
	tempParameter = parameter;

	console.log("parameter ", parameter);
	request.open("GET", "search/" + entityType + "?input="
			+ JSON.stringify(parameter), true);
	connectionStatus = true; // working
	request.send();
	loading();
}

function hideButtonBySearch(){
	$("#editLocation").removeClass("show").addClass("hide");
	$("#adjustmentstock").removeClass("show").addClass("hide");
	$("#transfer").removeClass("show").addClass("hide");
	$("#dailySale").removeClass("show").addClass("hide");
	$("#dailySaleRetail").removeClass("show").addClass("hide");
	$("#convertPackageType").removeClass("show").addClass("hide");
	$("#deleteLocation").removeClass("show").addClass("hide");
	$("#editEmployee").removeClass("show").addClass("hide");
	$("#addAccount").removeClass("show").addClass("hide");
	$("#deleteEmployee").removeClass("show").addClass("hide");
	$("#purchase").removeClass("show").addClass("hide");
	$("#editSupplier").removeClass("show").addClass("hide");
	$("#deleteSupplier").removeClass("show").addClass("hide");
	$("#editCustomer").removeClass("show").addClass("hide");
	$("#deleteCustomer").removeClass("show").addClass("hide");
	$("#editProduct").removeClass("show").addClass("hide");
	$("#editBorrowerRisk").removeClass("show").addClass("hide");
}

function changeDayMonthYear(yearMonthDay) {
    var tempDate = yearMonthDay.split('-');
    return tempDate[2] + "/" + tempDate[1] + "/" + tempDate[0];
}

function searchInvoiceByCustomerWithDate() {
	var customerBoId = $('#detail-customer-boId').html();
    var startDate = changeDayMonthYear($('#customerStartDate').val());
    var endDate = changeDayMonthYear($('#customerEndDate').val());

    if($('#customerStartDate').val() == "" || $('#customerEndDate').val() == ""){
    	alert("Please Check Start Date and End Date");
	}else{
        if (startDate != '' && endDate != '') {
            var http = new XMLHttpRequest();

            http.onreadystatechange = function() {
                if (http.readyState == 4 && http.status == 200) {
                    hideLoading();
                    document.getElementById("saleListByCustomerId").innerHTML = http.responseText;
                }
            };
            var parameter = {};
            parameter["customerBoId"] = customerBoId;
            parameter["startDate"] = startDate;
            parameter["endDate"] = endDate;
            loading();
            http.open("GET", "searchPurchaseOrderByCustomer?input="
                + JSON.stringify(parameter), true);
            http.send();
        }
	}
}

function getPurchaseOrderListWithCredit(){
	var customerBoId = $('#detail-customer-boId').html();

    var http = new XMLHttpRequest();

    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            hideLoading();
            document.getElementById("saleListByCustomerId").innerHTML = http.responseText;
        }
    };
    var parameter = {};
    parameter["customerBoId"] = customerBoId;
    loading();
    http.open("GET", "purchaseOrderListWithCredit?input="
        + JSON.stringify(parameter), true);
    http.send();
}

function checkInvoiceForReport(value, supplier) {
	if (value == "INVOICE") {
		document.getElementById("search-textForInvoice").className = "show";
		document.getElementById("selectForInvoice").className = "show";
		document.getElementById("searchadvanceForInvoice").className = "show ui button blue basic small";
		document.getElementById("searchadvance").className = "hide";
		document.getElementById("customerSearch").className = "hide";
		document.getElementById("locationNameReport").className = "show";
		document.getElementById("porderlocationname").className = "show";
		document.getElementById("selectedRoute").className = "hide";
		document.getElementById("ChannelOfDistributionSelect").className = "hide";
	} else if (value == "CUSTOMERBOID_DATE") {
        document.getElementById("customerSearch").className = "show ui input";
        document.getElementById("searchReportCustomer").value = "";
        document.getElementById("search-textForInvoice").className = "hide";
        document.getElementById("selectForInvoice").className = "hide";
        document.getElementById("searchadvanceForInvoice").className = "show ui button blue basic small";
        document.getElementById("searchadvance").className = "hide";
        document.getElementById("locationNameReport").className = "hide";
        document.getElementById("porderlocationname").className = "hide"
        document.getElementById("ChannelOfDistributionSelect").className = "hide";
        document.getElementById("selectedRoute").className = "hide";
	} else if (value == "ROUTE") {
		document.getElementById("selectedRoute").className = "show";
		document.getElementById("searchadvanceForInvoice").className = "show ui button blue basic small";
		document.getElementById("search-textForInvoice").className = "hide";
		document.getElementById("selectForInvoice").className = "hide";
		document.getElementById("searchadvance").className = "hide";
		document.getElementById("customerSearch").className = "hide";
		document.getElementById("locationNameReport").className = "show";
		document.getElementById("porderlocationname").className = "show";
		document.getElementById("ChannelOfDistributionSelect").className = "hide";
        var select = document.getElementById("selectedRoute");
		select.innerHTML = "";
	} else if (value == "CHANNEL_OF_DISTRIBUTION") {
		document.getElementById("ChannelOfDistributionSelect").className = "show";
		document.getElementById("selectedRoute").className = "hide";
		document.getElementById("searchadvanceForInvoice").className = "show ui button blue basic small";
		document.getElementById("search-textForInvoice").className = "hide";
		document.getElementById("selectForInvoice").className = "hide";
		document.getElementById("searchadvance").className = "hide";
		document.getElementById("customerSearch").className = "hide";
		document.getElementById("locationNameReport").className = "show";
		document.getElementById("porderlocationname").className = "show";
	} else if (value == "ADJUSTMENT_DATE" || value == "TRANSFER_TO_DATE" || value == "TRANSFER_FROM_DATE" || value == "reportCustomer" || value == "customerclosing") {
        document.getElementById("locationNameReport").className = "show";
		document.getElementById("porderlocationname").className = "show";
		document.getElementById("customerSearch").className = "hide";
        document.getElementById("search-textForInvoice").className = "hide";
        document.getElementById("selectForInvoice").className = "hide";
        document.getElementById("ChannelOfDistributionSelect").className = "hide";
        document.getElementById("selectedRoute").className = "hide";
	} else if (value == "CUSTOMER_RETURN_LOCATION") {
        document.getElementById("locationNameReport").className = "show";
        document.getElementById("porderlocationname").className = "show";
        document.getElementById("customerSearch").className = "hide";
        document.getElementById("search-textForInvoice").className = "hide";
        document.getElementById("selectForInvoice").className = "hide";
        document.getElementById("ChannelOfDistributionSelect").className = "hide";
        document.getElementById("selectedRoute").className = "hide";
	} else if (value == "CUSTOMER_RETURN_CUSTOMER") {
        document.getElementById("locationNameReport").className = "hide";
        document.getElementById("porderlocationname").className = "hide";
        document.getElementById("customerSearch").className = "show ui input";
        document.getElementById("search-textForInvoice").className = "hide";
        document.getElementById("selectForInvoice").className = "hide";
        document.getElementById("ChannelOfDistributionSelect").className = "hide";
        document.getElementById("selectedRoute").className = "hide";
    } else {
		if (supplier == "supplier")
			document.getElementById("locationNameReport").className = "hide";
		else
			document.getElementById("locationNameReport").className = "show";
		document.getElementById("ChannelOfDistributionSelect").className = "hide";
		document.getElementById("search-textForInvoice").className = "hide";
		document.getElementById("selectForInvoice").className = "hide";
		document.getElementById("customerSearch").className = "hide";
		document.getElementById("searchadvance").className = "hide";
		document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
		document.getElementById("porderlocationname").className = "show";
		document.getElementById("selectedRoute").className = "hide";
	}

	if (value == "profitandloss") {
		$("#porderStartDate").addClass("hide");
		$("#porderEndDate").addClass("hide");
		$("#myStartDate").addClass("hide");
		$("#myEndDate").addClass("hide");
		$("#idStartDate").addClass("hide")
		$("#idEndDate").addClass("hide")
	} else {
		$("#porderStartDate").removeClass("hide");
		$("#porderEndDate").removeClass("hide");
		$("#myStartDate").removeClass("hide");
		$("#myEndDate").removeClass("hide");
		$("#idStartDate").removeClass("hide");
		$("#idEndDate").removeClass("hide");
	}
}

function searchFirst() {
	search("", tempParameter.searchType, tempParameter.entityType, 1);
	document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
}

//Search Only First 10 Lists
function searchFirstPerPage() {
    getEntityListByPageNumber(1);
}

function searchEnd() {
	var endPage = Math.ceil(document.getElementById("search-total").innerHTML
			/ SEARCH.PAGE_SIZE);
	search("", tempParameter.searchType, tempParameter.entityType, endPage);
	document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
}

//Search Only End 10 Lists
function searchEndPerPage() {
    var endPage = Math.ceil(document.getElementById("search-total").innerHTML
        / SEARCH.PAGE_SIZE);
    getEntityListByPageNumber(endPage);
}

function searchPrevious() {
	tempParameter.pageNumber = Math.max(2, tempParameter.pageNumber);
	search("", tempParameter.searchType, tempParameter.entityType,
			tempParameter.pageNumber - 1);
	document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
}

//Search Only Previous 10 Lists
function searchPreviousPerPage() {
    tempEntity.pageNumber = Math.max(2, tempEntity.pageNumber);
    getEntityListByPageNumber(tempEntity.pageNumber - 1);
}

function searchNext() {
	var endPage = Math.ceil(document.getElementById("search-total").innerHTML
			/ SEARCH.PAGE_SIZE) - 1;
	tempParameter.pageNumber = Math.min(endPage, tempParameter.pageNumber);
	search("", tempParameter.searchType, tempParameter.entityType,
			parseInt(tempParameter.pageNumber) + 1);
	document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
}

//Search Only Next 10 Lists
function searchNextPerPage() {
    var endPage = Math.ceil(document.getElementById("search-total").innerHTML
        / SEARCH.PAGE_SIZE) - 1;
    tempEntity.pageNumber = Math.min(endPage, tempEntity.pageNumber);
    getEntityListByPageNumber(parseInt(tempEntity.pageNumber) + 1);
}

function viewDetailSalary() {
	var salaryinfo = {};
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				document.getElementById("detail-salary-content").innerHTML = request.responseText;

			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	salaryinfo['empId'] = document.getElementById("detail-employee-boId").innerHTML;
	salaryinfo['date'] = document.getElementById("salary-date").value;
	// alert(salaryinfo['empId'] + " " + salaryinfo['date'] + " ");
	request.open("GET", "viewDetailSalary?input=" + JSON.stringify(salaryinfo),
			true);
	request.send(null);
}

function saveDetailSalary() {
	var salaryinfo = {};
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				document.getElementById("detail-salary-content").innerHTML = request.responseText;

			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	salaryinfo['empId'] = document.getElementById("detail-employee-boId").innerHTML;
	salaryinfo['date'] = document.getElementById("view-date").options[document
			.getElementById("view-date").selectedIndex].value;
	salaryinfo['salary-basicSalary'] = document
			.getElementById("salary-basicSalary").value;
	salaryinfo['salary-emp-salesCommission'] = document
			.getElementById("salary-emp-salesCommission").value;
	salaryinfo['salary-emp-fieldTrip'] = document
			.getElementById("salary-emp-fieldTrip").value;
	salaryinfo['salary-emp-fieldTrip-salary'] = document
			.getElementById("salary-emp-fieldTrip-salary").value;
	salaryinfo['salary-emp-saleQty'] = document
			.getElementById("salary-emp-saleQty").value;
	salaryinfo['salary-emp-onTime'] = document
			.getElementById("salary-emp-onTime").value;
	salaryinfo['salary-emp-withoutLeave'] = document
			.getElementById("salary-emp-withoutLeave").value;
	salaryinfo['salary-emp-award'] = document
			.getElementById("salary-emp-award").value;
	salaryinfo['salary-emp-Home'] = document.getElementById("salary-emp-Home").value;

	salaryinfo['salary-emp-snackAllowance'] = document
			.getElementById("salary-emp-snackAllowance").value;
	salaryinfo['salary-emp-carAllowance'] = document
			.getElementById("salary-emp-carAllowance").value;
	salaryinfo['salary-emp-ansCode'] = document
			.getElementById("salary-emp-ansCode").value;
	salaryinfo['salary-emp-saveCommision'] = document
			.getElementById("salary-emp-saveCommision").value;
	salaryinfo['salary-emp-savSaleQty'] = document
			.getElementById("salary-emp-savSaleQty").value;
	salaryinfo['salary-emp-timeAllowance'] = document
			.getElementById("salary-emp-timeAllowance").value;

	salaryinfo['salary-emp-leaveFine'] = document
			.getElementById("salary-emp-leaveFine").value;
	salaryinfo['salary-emp-lateFine'] = document
			.getElementById("salary-emp-lateFine").value;
	salaryinfo['salary-emp-returnFine'] = document
			.getElementById("salary-emp-returnFine").value;
	salaryinfo['salary-emp-fineallowance'] = document
			.getElementById("salary-emp-fineallowance").value;
	// salaryinfo['salary.totalSalary']=
	// document.getElementById("salary.totalSalary").value;
	request.open("GET", "saveDetailSalary?input=" + JSON.stringify(salaryinfo),
			true);
	request.send(null);
}

function getEmployeeData() {
	var employList = document.getElementById("route-employee-list");
	var index = employList.selectedIndex;
	var boId = employList.value;
	var name = employList.options[index].innerHTML;
	var employeediv = document.getElementById("route-employee");
	var empSpan = document.createElement('span');
	empSpan.id = boId;
	empSpan.innerHTML = name;
	employeediv.appendChild(empSpan);

}

function loading() {
	// var opts = {
	// lines: 8, // The number of lines to draw
	// length: 20, // The length of each line
	// width: 5, // The line thickness
	// radius: 5, // The radius of the inner circle
	// corners: 1, // Corner roundness (0..1)
	// rotate: 3, // The rotation offset
	// direction: 1, // 1: clockwise, -1: counterclockwise
	// color: '#aaa', // #rgb or #rrggbb or array of colors
	// speed: 0.8, // Rounds per second
	// trail: 57, // Afterglow percentage
	// shadow: true, // Whether to render a shadow
	// hwaccel: true, // Whether to use hardware acceleration
	// className: 'spinner', // The CSS class to assign to the spinner
	// zIndex: 2e9, // The z-index (defaults to 2000000000)
	// top: '50%', // Top position relative to parent
	// left: '50%' // Left position relative to parent
	// };

	// var target = document.getElementById('loading');
	// var spinner = new Spinner(opts).spin(target);
	// $(target).data('spinner', spinner);
	$('#loading27').addClass('dialog');
	$('#colorBalls').addClass('colorBalls');
}

function hideLoading() {
	$('#loading27').removeClass('dialog');
	$('#colorBalls').removeClass('colorBalls');
	connectionStatus = false;
	// $('#loading').data('spinner').stop();
	// $('#loading').removeClass('dialog');
	// $('#loading').addClass('hide');
	// $('#loading27').addClass('hide');
	// $('#loading27').addClass("hide");
}

function getRouteListForReport() {
	if ($("#porderStartDate").val() == "") {
		alert("Please fill Start Date!");
		return;
	}
	if ($("#porderEndDate").val() == "") {
		alert("Please fill End Date!");
		return;
	}
	var parameter = {};
	parameter["value"] = document.getElementById("porderlocationname").value;
	parameter["pordersearchdate"] = changeDayMonthYear(document.getElementById("porderStartDate").value);
	parameter["porderenddate"] = changeDayMonthYear(document.getElementById("porderEndDate").value);

	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState != 4)
			return;
		if (request.status != 200) {
			alert("Error" + request.status);
			return;
		}
		var result = parseNewJson(request.responseText);
		var routeList = result.routeList;
		var select = document.getElementById("selectedRoute");
		select.innerHTML = "";
		var option = document.createElement("option");
		option.innerHTML = "ALL";
		select.appendChild(option);
		for ( var index in routeList) {
			var option = document.createElement("option");
			option.value = routeList[index].id;
			option.innerHTML = routeList[index].name;
			select.appendChild(option);
		}
	};
	console.log("Parameter", parameter);
	request.open("GET", "getRouteListDateRange?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

function removeOptions(selectbox) {
	var i;
	for (i = selectbox.options.length - 1; i >= 0; i--) {
		if (selectbox.options[i].innerHTML == "ALL")
			selectbox.remove(i);
	}
}

function getRoutePurchaseOrder(element) {
	if (element.value == "Search") {
		getRouteListForReport();
		search(element, 'DATE_RANGE', 'ROUTE');
	} else {
		var select = document.getElementById("selectedRoute");
		removeOptions(select);
		search(element, 'ROUTEID_DATE', 'ROUTE');
	}
}

function reportSearch(element) {
	var searchValue = "";
	if (document.getElementById("supplier_purchaseOrder").className == "show")
		searchValue = document.getElementById("searchReportSupplier").value;
	else
		searchValue = document.getElementById("searchReport").value;
	switch (searchValue) {
	case "PURCHASE_ORDER":
		search(element, 'LOCATIONID_DATE', 'PURCHASE_ORDER');
		break;
	case "CUSTOMERBOID_DATE":
		search(element, 'CUSTOMERBOID_DATE', 'PURCHASE_ORDER');
		break;
	case "CUSTOMER_RETURN_LOCATION":
		search(element, 'LOCATIONID_DATE', 'CUSTOMER_RETURN');
		break;
	case "CUSTOMER_RETURN_CUSTOMER":
		search(element, 'CUSTOMERBOID_DATE', 'CUSTOMER_RETURN');
		break;
	case "CHANNEL_OF_DISTRIBUTION":
		search(element, 'CHANNEL_OF_DISTRIBUTION', 'PURCHASE_ORDER');
		break;
	case "ROUTE":
		getRoutePurchaseOrder(element);
		break;
	case "INVOICE":
		search(element, 'INVOICE',
				document.getElementById("selectForInvoice").value);
		break;
	case "profitandloss":
		profitAndLossByLocation(element);
		break;
	case "stock_average":
		stockReport();
		break;
	case "TRANSFER_TO_DATE":
		search(element, 'TRANSFER_TO_DATE', 'TRANSFER');
		break;
	case "TRANSFER_FROM_DATE":
		search(element, 'TRANSFER_FROM_DATE', 'TRANSFER');
		break;
	case "ADJUSTMENT_DATE":
		search(element, 'ADJUSTMENT_DATE', 'ADJUSTMENT');
		break;
	case "reportCustomer":
		reportPurchaseOrder(element, 'reportCustomer');
		break;
	case "stockbalance":
		reportPurchaseOrder(element, 'stockbalance');
		break;
	case "stockbalanceproduct":
		reportPurchaseOrder(element, 'stockbalanceproduct');
		break;
	case "customerclosing":
		reportPurchaseOrder(element, 'customerclosing');
		break;
	case "totalincomereport":
		reportPurchaseOrder(element, 'totalincomereport');
		break;
	case "routeincomereport":
		reportPurchaseOrder(element, 'routeincomereport');
		break;
    }
}
/*
 * function beginrefresh() { setInterval("refresh()", 5000) } function refresh() {
 * method = "post"; var form = document.createElement("form");
 * form.setAttribute("method", method); form.setAttribute("action", path);
 * 
 * var hiddenField = document.createElement("input");
 * hiddenField.setAttribute("name", "refresh"); form.appendChild(hiddenField);
 * form.submit(); } window.onload = beginrefresh;
 */

// to use table scroll just add "scroll" in class
// Change the selector if needed
// var table = $('table.scroll'), $bodyCells = $table.find('tbody tr:first')
// .children(), colWidth;
// Adjust the width of thead cells when window resizes
/*
 * $(window).resize(function() { // Get the tbody columns width array colWidth =
 * $bodyCells.map(function() { return $(this).width(); }).get(); // Set the
 * width of thead columns $table.find('thead tr').children().each(function(i, v) {
 * $(v).width(colWidth[i]); }); }).resize();
 */

function getActiveRouteStocks() {
	var locationId = $('#detail-location-name').attr('value');
	var parameter = {};
	parameter["locationId"] = locationId;

	$.get("openRouteStockList?input=" + JSON.stringify(parameter)).then(
			function(data) {
				document.getElementById("dialog-temp").innerHTML = data;
				document.getElementById("dialog-temp").className = "dialog";
			});
}