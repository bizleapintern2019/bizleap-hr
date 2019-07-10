var rowtemp = 0;
var errors = {};
var importDataArray = null;

function getAcountForm(entityType) {
	var request = new XMLHttpRequest;
	$('#workspae').addClass("myFilter");
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").attributes["class"].value = "dialog";
			} else {
				alert("Load New Form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	loading();
	parameter = {};
	parameter['employeeBoId'] = document.getElementById("detail-employee-boId").innerHTML;
	request.open("GET", "getAcountForm/" + entityType + "?input="
	+ JSON.stringify(parameter), true);
	request.send();
}

function getNewForm(entityType) {
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
				if (entityType == 'EMPLOYEE') {
					$('#add_employee_name').focus();
                    document.getElementById("add_employee_entranceDate").valueAsDate = new Date();
				} else if (entityType == 'CUSTOMER') {
					$('#add_customer_name').focus();
                    document.getElementById("add_customer_openingDate").valueAsDate = new Date();
				} else if (entityType == 'SUPPLIER') {
					$('#add_customer_name').focus();
					document.getElementById("add_customer_openingDate").valueAsDate = new Date();
				} else if (entityType == 'LOCATION') {
					$('#add_location_name').focus();
                    document.getElementById('add_customer_openingDate').valueAsDate = new Date();
				} else if (entityType == 'PRODUCT') {
					$('#add_product_boId_code').focus();
				} else if (entityType == 'BORROWERRISK') {

				} else {
				}
			} else {
				alert("Load New Form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	loading();
	parameter = {};
	console.log("EntityType ", entityType);
	request.open("GET", "getNewForm/" + entityType + "?input="
			+ JSON.stringify(parameter), true);
	request.send();
}

function editForm(entityType, editTemp) {
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
				if (entityType == 'PRODUCT') {
					document.getElementById("add_product_code").value = document
							.getElementById("detail-product-boId").innerHTML;
					document.getElementById("add_product_boId_code").value = document
							.getElementById("detail-product-boId-code").innerHTML;
					document.getElementById("add_product_location").value = document
							.getElementById("detail-product-locatin-boId").innerHTML;
					$('#add_product_boId_code').attr('disabled', 'disabled');
					document.getElementById("add_product_name").value = document
							.getElementById("detail-product-name").innerHTML;

					document.getElementById("add_brand_name").value = document
							.getElementById("detail-brand-name").innerHTML;

					document.getElementById("add_flavour").value = document
							.getElementById("detail-flavour").innerHTML;

					$("#add_product_price").val(
							$("#detail-product-price").text());
					var creditPrice = document
							.getElementById("detail-product-creditPrice");
					if (creditPrice != null) {
						document.getElementById("add_product_creditPrice").value = creditPrice.innerHTML;
					}
					var specialPrice = document
							.getElementById("detail-product-specialPrice");
					if (specialPrice != null) {
						document.getElementById("add_product_specialPrice").value = specialPrice.innerHTML;
					}
					var discounted = document
							.getElementById("detail-product-discounted").innerHTML;
					if (discounted == 'true')
						document.getElementById("add_product_discounted").checked = true;

					// document.getElementById("add_product_boId_code").disabled
					// = "true";
					document.getElementById("add_product_location").disabled = "true";

					document.getElementById("product_header").innerHTML = "Edit Product";
					document.getElementById("add-newform-save").value = "Edit";
					document.getElementById("newProductactionForm").action = "javascript:addEditProduct('contentEdit');";
				} else if (entityType == 'EMPLOYEE') {
					document.getElementById("add_employee_boId").innerHTML = document
							.getElementById("detail-employee-boId").innerHTML;
					var fullName = document.getElementById(
							"detail-employee-name")
							.getElementsByTagName('span')[0].innerHTML
							+ ' '
							+ document.getElementById("detail-employee-name")
									.getElementsByTagName('span')[1].innerHTML
							+ ' '
							+ document.getElementById("detail-employee-name")
									.getElementsByTagName('span')[2].innerHTML
							+ ' '
							+ document.getElementById("detail-employee-name")
									.getElementsByTagName('span')[3].innerHTML;
					document.getElementById("add_employee_name").value = fullName;
					$('#add_employee_name').focus();
					document.getElementById("add_employee_position").value = document
							.getElementById("detail-employee-position").innerHTML;
					document.getElementById("add_employee_baseSalary").value = document
							.getElementById("detail-employee-baseSalary").innerHTML;
					document.getElementById("add_employee_putaSideAmount").value = document
							.getElementById("detail-employee-putaSideAmount").innerHTML;
					document.getElementById("add_employee_nrc").value = document
							.getElementById("detail-employee-nrc").innerHTML;
					if (document
							.getElementById("detail-employee-date-of-birth") != null)
						document.getElementById("add_employee_dob").value =parseStringToInputDate(document.getElementById("detail-employee-date-of-birth").innerHTML);
					document.getElementById("add_employee_education").value = document
							.getElementById("detail-employee-education").innerHTML;
					document.getElementById("add_employee_atm").value = document
							.getElementById("detail-employee-atm").innerHTML;
					if (document
							.getElementById("detail-employee-entrance-date") != null)
						document.getElementById("add_employee_entranceDate").value = parseStringToInputDate(document.getElementById("detail-employee-entrance-date").innerHTML);
					document.getElementById("add_employee_location").value = document
							.getElementById("detail-employee-location-id").innerHTML;
					document.getElementById("add_employee_email").value = document
							.getElementById("detail-employee-email").innerHTML;
					document.getElementById("add_employee_phoneNumber").value = document
							.getElementById("detail-employee-phone-number").innerHTML;
					document.getElementById("add_employee_addressLine1").value = document
							.getElementById("detail-employee-address-line1").innerHTML;
					// document.getElementById("add_employee_addressLine2").value = document
					// 		.getElementById("detail-employee-address-line2").innerHTML;
					// document.getElementById("add_employee_city").value = document
					// 		.getElementById("detail-employee-city").innerHTML;
					// document.getElementById("add_employee_state").value = document
					// 		.getElementById("detail-employee-state").innerHTML;
					// document.getElementById("add_employee_country").value = document
					// 		.getElementById("detail-employee-country").innerHTML;
					// document.getElementById("add_employee_postalCode").value = document
					// 		.getElementById("detail-employee-postalCode").innerHTML;
					// document.getElementById("add_employee_location").disabled
					// = "true";
					document.getElementById("employee_header").innerHTML = "Edit Employee";
					document.getElementById("add-newform-save").value = "Edit";
					document.getElementById("newEmployeeForm").action = "javascript:addEditEmployee('contentEdit');";
				} else if (entityType == 'CUSTOMER') {
					if (document.getElementById("isOpening-DateandAmount")) {
						document.getElementById("add_customer_openingDate").value = parseStringToInputDate(document
								.getElementById("purchaseOrder-openingDate").innerHTML);
						document.getElementById("add_customer_openingBalance").value = document
								.getElementById("purchaseOrder-openingNetAmount").innerHTML;
						/*
						 * document.getElementById("add_customer_remark").value =
						 * document
						 * .getElementById("purchaseOrder-openingRemark").innerHTML;
						 */
					}

					document.getElementById("add_customer_boId").innerHTML = document
							.getElementById("detail-customer-boId").innerHTML;
					var fullName = document.getElementById(
							"detail-customer-name")
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
					document.getElementById("add_customer_name").value = fullName;
					$('#add_customer_name').focus();
					document.getElementById("add_customer_nrc").value = document
							.getElementById("detail-customer-nrc").innerHTML;
					if (document
							.getElementById("detail-customer-date-of-birth") != null)
						document.getElementById("add_customer_dob").value = document
								.getElementById("detail-customer-date-of-birth").innerHTML;
					document.getElementById("add_customer_location").value = document
							.getElementById("detail-customer-location-id").innerHTML;
					console.log("outletType",document.getElementById("outletType").value);
					document.getElementById("outletType").value = document
							.getElementById("detail-customer-outletType").innerHTML;
					document.getElementById("add_risk_level").value = document
							.getElementById("detail-customer-risk-level").innerHTML;
					document.getElementById("add_customer_email").value = document
							.getElementById("detail-customer-email").innerHTML;
					document.getElementById("add_customer_phoneNumber").value = document
							.getElementById("detail-customer-phone-number").innerHTML;
					document.getElementById("add_customer_addressLine1").value = document
							.getElementById("detail-customer-address-line1").innerHTML;
					/*document.getElementById("add_customer_addressLine2").value = document
							.getElementById("detail-customer-address-line2").innerHTML;*/
					document.getElementById("add_customer_city").value = document
							.getElementById("detail-customer-city").innerHTML;
					/*document.getElementById("add_customer_state").value = document
							.getElementById("detail-customer-state").innerHTML;*/
					document.getElementById("add_customer_country").value = document
							.getElementById("detail-customer-country").innerHTML;
					/*document.getElementById("add_customer_postalCode").value = document
							.getElementById("detail-customer-postalCode").innerHTML;*/
					document.getElementById("add_customer_Type").value = document
							.getElementById("detail-customer-customerType").innerHTML;
					document.getElementById("customerClass").value = document
							.getElementById("detail-customer-classType").innerHTML;
					if (document.getElementById("detail-customer-customerType").innerHTML == 'OUTLET') {
						document.getElementById("outletType").className = "show";
					}
					document.getElementById("customer_header").innerHTML = "Edit Customer";
					document.getElementById("add-newform-save").value = "Edit";
					document.getElementById("newCustomerForm").action = "javascript:addEditCustomer('contentEdit');";
				}
				// Supplier Edit Form
				else if (entityType == 'SUPPLIER') {
					// Title
					document.getElementById("customer_header").innerHTML = "Edit Supplier";

					// Opening Balance
					if (document.getElementById("isOpening-DateandAmount")) {
                        document.getElementById("add_customer_openingDate").value = parseStringToInputDate(document
                            .getElementById("purchaseOrder-openingDate").innerHTML);
						document.getElementById("add_customer_openingBalance").value = document
								.getElementById("purchaseOrder-openingNetAmount").innerHTML;
						document.getElementById("add_customer_remark").value = document
								.getElementById("purchaseOrder-openingRemark").innerHTML;

					}

					// Customer BoId
					document.getElementById("add_customer_boId").innerHTML = document
							.getElementById("detail-customer-boId").innerHTML;

					// Company Name
					var fullName = document.getElementById(
							"detail-customer-name")
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
					document.getElementById("add_company_name").value = fullName;
					$('#add_customer_name').focus();

					// Date of Birth
					if (document
							.getElementById("detail-customer-date-of-birth") != null)
						document.getElementById("add_customer_dob").value = document
								.getElementById("detail-customer-date-of-birth").innerHTML;

					// Supplier's Location(i.e. Supplier 1[locationType])
					document.getElementById("add_customer_location").value = document
							.getElementById("detail-customer-location-id").innerHTML;

					// Authorized Person
					document.getElementById("add_authorized_person").value = document
							.getElementById("detail-customer-authorized-person").innerHTML;

					// Risk Level
					document.getElementById("add_risk_level").value = document
							.getElementById("detail-customer-risk-level").innerHTML;

					// Email
					document.getElementById("add_customer_email").value = document
							.getElementById("detail-customer-email").innerHTML;

					// Phone Number
					document.getElementById("add_customer_phoneNumber").value = document
							.getElementById("detail-customer-phone-number").innerHTML;

					// Address Detail
					document.getElementById("add_customer_addressLine1").value = document
							.getElementById("detail-customer-address-line1").innerHTML;
					document.getElementById("add_customer_addressLine2").value = document
							.getElementById("detail-customer-address-line2").innerHTML;
					document.getElementById("add_customer_city").value = document
							.getElementById("detail-customer-city").innerHTML;
					document.getElementById("add_customer_state").value = document
							.getElementById("detail-customer-state").innerHTML;
					document.getElementById("add_country_origin").value = document
							.getElementById("detail-customer-country").innerHTML;
					document.getElementById("add_customer_postalCode").value = document
							.getElementById("detail-customer-postalCode").innerHTML;

					// Customer Type
					document.getElementById("add_customer_Type").value = document
							.getElementById("detail-customer-customerType").innerHTML;

					// Class
					document.getElementById("customerClass").value = document
							.getElementById("detail-customer-classType").innerHTML;

					/*
					 * if
					 * (document.getElementById("detail-customer-customerType").innerHTML ==
					 * 'BRANCH') {
					 * document.getElementById("add_customer_Branch").disabled =
					 * false;
					 * document.getElementById("add_customer_Branch").value =
					 * document.getElementById("detail-customer-branch-boId").innerHTML; }
					 */

					// Edit Button
					document.getElementById("add-newform-save").value = "Edit";
					document.getElementById("newSupplierForm").action = "javascript:addEditSupplier('contentEdit');";
				} else if (entityType == 'LOCATION') {

					if (document.getElementById("detail-location-openingDate") != null) {
						document.getElementById("add_location_openingDate").value = document
								.getElementById("detail-location-openingDate").innerHTML;
						document.getElementById("add_location_openingDate").disabled = true;
					}
					document.getElementById("add_location_boId").innerHTML = document
							.getElementById("detail-location-boId").innerHTML;
					document.getElementById("add_location_name").value = document
							.getElementById("detail-location-name").innerHTML;
					$('#add_location_name').focus();

					document.getElementById("new_edit_table").className = "hide";
					// document.getElementById("new_edit_table").className="show";

					document.getElementById("add_location_type").value = document
							.getElementById("detail-location-type").innerHTML;
					if (document.getElementById("add_location_type").value == "RETAIL") {
						var codeChanged = document
								.getElementById("detail-location-codeChanged").innerHTML;
						if (codeChanged == 'true')
							document.getElementById("add_location_codeChanged").checked = true;

					}
					document.getElementById("add_location_type").disabled = true;
					document.getElementById("customer_header").innerHTML = "Edit Location";
					document.getElementById("add-newform-save").value = "Edit";
					document.getElementById("newLocationForm").action = "javascript:addEditLocation('contentEdit');";
				} else if (entityType == 'BORROWERRISK') {
					var riskLevel = document.getElementById("detail-risk-level").innerHTML;

					if(riskLevel==0){
                        document.getElementById("risk-maximumLimit").setAttribute("disabled","disabled");
                        document.getElementById("risk-credit_periods").setAttribute("disabled","disabled");
                        document.getElementById("risk-description").setAttribute("disabled","disabled");
					}

					document.getElementById("risk-level").innerHTML = riskLevel;
					document.getElementById("risk-maximumLimit").value = document
							.getElementById("detail-risk-max").innerHTML;
					document.getElementById("risk-description").value = document
							.getElementById("detail-risk-descript").innerHTML;
					document.getElementById("risk-credit_periods").value = document
							.getElementById("detail-risk-credit-periods").innerHTML;
					document.getElementById("borrower-header").innerHTML = "Edit BorrowerRisk";
					document.getElementById("add-newform-save").value = "Edit";
					document.getElementById("new_borrowerRisk_form").action = "javascript:addEditBorrowerRisk('contentEdit');";
				} else if (entityType == 'VOLUMEPRODUCT') {
					document.getElementById("add_product_code").value = document
							.getElementById("detail-product-boId").innerHTML;
					document.getElementById("add_product_code").disabled = true;
					document.getElementById("add_product_name").value = document
							.getElementById("detail-product-name").innerHTML;
					$('#add_product_name').focus();
					var discount = document
							.getElementById("detail-product-discounted").innerHTML;
					if (discount == "true")
						document.getElementById("add_product_discounted").checked = true;
					else
						document.getElementById("add_product_discounted").checked = false;
					var detailmin = document
							.getElementById("volumeprice_table")
							.querySelectorAll("#detail_volume_min");
					var detailmax = document
							.getElementById("volumeprice_table")
							.querySelectorAll("#detail_volume_max");
					var detailprice = document.getElementById(
							"volumeprice_table").querySelectorAll(
							"#detail_volume_price");
					for (var j = 0; j < detailmin.length; j++)
						addVolumeRow('volumeproduct');
					var inputmin = document.getElementById("volumeproduct")
							.querySelectorAll("#volumeproduct_min");
					var inputmax = document.getElementById("volumeproduct")
							.querySelectorAll("#volumeproduct_max");
					var inputprice = document.getElementById("volumeproduct")
							.querySelectorAll("#volumeproduct_price");
					for (var i = 0; i < detailmin.length; i++) {
						inputmin[i + 1].value = detailmin[i].innerHTML;
						inputmax[i + 1].value = detailmax[i].innerHTML;
						inputprice[i + 1].value = detailprice[i].innerHTML;
					}
					document.getElementById("product_header").innerHTML = "Edit Volume Product";
					document.getElementById("add-newform-save").value = "Edit";
					document.getElementById("new_volume_product_form").action = "javascript:addNewVolumeProduct('contentEdit');";
				} else {
				}

			} else {
				alert("Load New Form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};

	var parameter = {};
	parameter["edit"] = editTemp;
	parameter["boId"] = $("#detail-product-boId-code").text();
	loading();
	request.open("GET", "getNewForm/" + entityType + "?input="
			+ JSON.stringify(parameter), true);
	request.send();
}

function addEditBorrowerRisk(description) {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				var result = JSON.parse(request.responseText);
				if (result.status == 'ERROR') {
					alert("Error : MaximumLimit is already existed.")
				} else {
					alert("Successfully Recorded!");
					searchFirst();
					document.getElementById("dialog-temp").attributes["class"].value = "hide";
					$('#workspace').removeClass('myFilter');
				}
			} else {
				alert("Error return :" + request.status);
			}
		}
	};
	var parameter = {};
	parameter["edit-riskLevel"] = document.getElementById("risk-level").innerHTML;
	parameter["edit-riskMax"] = document.getElementById("risk-maximumLimit").value;
	parameter["edit-creditPeriods"] = document
			.getElementById("risk-credit_periods").value;
	parameter["edit-riskDescript"] = document
			.getElementById("risk-description").value;
	console.log("Parameter ", parameter);
	loading();
	request.open("POST", "saveEditBorrowerRisk?input="
			+ JSON.stringify(parameter), true);
	request.send();
}

function checkLocationType() {
	var type = document.getElementById("add_location_type").value;
	if (type == "RETAIL")
		document.getElementById("add_location_codeChanged").disabled = false;
	else {
		document.getElementById("add_location_codeChanged").disabled = true;
		document.getElementById("add_location_codeChanged").checked = false;
	}
}

function loadOpeningData() {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				loadAction(document.getElementById("locationmenu"), 'location');
			} else {
				alert("Error return :" + request.status);
			}
		}
	};
	parameter["add_location_boId"] = document
			.getElementById("detail-location-boId").innerHTML;
	parameter["fileName"] = "10st.txt";
	console.log(parameter);
	request.open("POST", "loadOpeningDataForLocation?input="
			+ JSON.stringify(parameter), true);
	request.send();
}

function deleteForm(entityType) {
	document.getElementById("confirm-text").innerHTML = "Are you sure you want to delete?";
	document.getElementById("confirm").attributes["class"].value = "dialog";
	document.getElementById("confirm-delete").attributes["onclick"].value = "deleteConfirm('"
			+ entityType + "');";
}

function deleteConfirm(entityType) {
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("confirm").attributes["class"].value = "hide";
				var result = parseNewJson(request.responseText);
				if (result.status == 'ERROR') {
					alert("Error : You must delete sales List first!")
				} else {
					alert("Successfully Deleted!");
					document.getElementById("search-text").value = "";
					search(this, 'PAGINATION', entityType);
				}
			} else {
				alert("Delete error return :" + request.status);
			}
		}

	};
	var parameter = {};
	if (entityType == "EMPLOYEE") {
		parameter["boId"] = document.getElementById("detail-employee-boId").innerHTML;
	} else if (entityType == "LOCATION") {
		parameter["boId"] = document.getElementById("detail-location-boId").innerHTML;
	} else if (entityType == "CUSTOMER") {
		parameter["boId"] = document.getElementById("detail-customer-boId").innerHTML;
	} else if (entityType == "SUPPLIER") {
		parameter["boId"] = document.getElementById("detail-customer-boId").innerHTML;
	} else if (entityType == "PRODUCT") {
		parameter["boId"] = document.getElementById("detail-product-boId").innerHTML;
	} else {
	}
	console.log(parameter);
	request.open("DELETE", "delete/" + entityType + "?input="
			+ JSON.stringify(parameter), true);
	request.send();
	loading();

}

function addNewProduct(description) {
	var table = $("#newproductForm");
	var packagingType = $("td [name=packaging_type]", table);
	if (packagingType.length < 1) {
		alert("Empty Packaging can't allow!");
		return;
	}

	var packagingGram = $("td span [name=gram]", table);
	var packagingNormal_Price = $("td span [name=normal_price]", table);
	var packagingCredit_Price = $("td span [name=credit_price]", table);
	var packagingSpecial_Price = $("td span [name=special_price]", table);
	var packagingQty = $("td span [name=packaging_qty]", table);
	var packagingDescription = $("td span [name=packaging_description]", table);

	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				var result = parseNewJson(request.responseText);
				if (result.status == "ERROR") {
					alert("Error : product code is already existed.")
				} else {
					alert("Successfully Recorded!");
					getNewForm('PRODUCT');
				}
			} else {
				alert("Error return :" + request.status);
			}
		}
	};
	var parameter = {};
	var packagingObject = {};
	for (var i = 0; i < packagingType.length; i++) {
		packagingObject[packagingType[i].innerHTML] = {
			PType : packagingType[i].innerHTML,
			PGram : $(packagingGram[i]).val() ? $(packagingGram[i]).val() : "0",
			NPrice : $(packagingNormal_Price[i]).val() ? $(
					packagingNormal_Price[i]).val() : "0",
			CPrice : $(packagingCredit_Price[i]).val() ? $(
					packagingCredit_Price[i]).val() : "0",
			SPrice : $(packagingSpecial_Price[i]).val() ? $(
					packagingSpecial_Price[i]).val() : "0",
			PQty : $(packagingQty[i]).val() ? $(packagingQty[i]).val() : "0",
			PDesc : $(packagingDescription[i]).val() ? $(
					packagingDescription[i]).val() : ""
		};
	}
	parameter.key = packagingObject;
	console.log("PackagingObject ", packagingObject);
	parameter["add_product_code"] = document
			.getElementById("add_product_boId_code").value.trim();
	parameter["add_product_name"] = document.getElementById("add_product_name").value
			.trim();
	parameter["add_brand_name"] = document.getElementById("add_brand_name").value
			.trim();
	parameter["add_flavour"] = document.getElementById("add_flavour").value
			.trim();

	parameter["add_product_price"] = $("#add_product_price").val();
	var creditPrice = document.getElementById("add_product_creditPrice");
	if (creditPrice != null) {
		parameter["add_product_creditPrice"] = creditPrice.value.trim();
	}
	var specialPrice = document.getElementById("add_product_specialPrice");
	if (specialPrice != null) {
		parameter["add_product_specialPrice"] = specialPrice.value.trim();
	}
	parameter["add_product_discounted"] = document
			.getElementById("add_product_discounted").checked;
	parameter["productPrefix"] = document
			.getElementById("add_product_location").value;

	console.log("parameter", parameter);
	request.open("POST", "saveNewProduct?input=" + JSON.stringify(parameter),
			true);
	loading();
	request.send();
}

function addEditProduct(description) {
	var packagingId = $("tr[name=edit] td [name=packaging_id]");
	var packagingType = $("tr[name=edit] td [name=packaging_type]");
	if (packagingType.length < 1) {
		alert("Empty Packaging cann't allow!");
		return;
	}

	var packagingGram = $("tr[name=edit] td span [name=gram]");
	var packagingNormal_Price = $("tr[name=edit] td span [name=normal_price]");
	var packagingCredit_Price = $("tr[name=edit] td span [name=credit_price]");
	var packagingSpecial_Price = $("tr[name=edit] td span [name=special_price]");
	var packagingQty = $("tr[name=edit] td span [name=packaging_qty]");
	var packagingDescription = $("tr[name=edit] td span [name=packaging_description]");
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				var result = parseNewJson(request.responseText);
				if (result.status == 'ERROR') {
					alert("Error : product code is already existed.")
				} else {
					document.getElementById("dialog-temp").attributes["class"].value = "hide";
					$('#workspace').removeClass('myFilter');
					loadAction(document.getElementById("productmenu"),
							'product');
					var newCode = document
							.getElementById("add_product_boId_code").value
							.trim();

					document.getElementById("search-text").value = newCode;
					search(this, 'ID', 'PRODUCT');
					this.id = newCode;
					aboutDetail(this, 'PRODUCT');
				}
			} else {
				alert("Error return :" + request.status);
			}
		}
	};

	var parameter = {};
	var packagingObject = {};
	for (var i = 0; i < packagingType.length; i++) {
		packagingObject[packagingType[i].innerHTML] = {
			PID : packagingId[i].innerHTML,
			PType : packagingType[i].innerHTML,
			PGram : $(packagingGram[i]).val() || "0",
			NPrice : $(packagingNormal_Price[i]).val() || "0",
			CPrice : $(packagingCredit_Price[i]).val() || "0",
			SPrice : $(packagingSpecial_Price[i]).val() || "0",
			PQty : $(packagingQty[i]).val() || "0",
			PDesc : $(packagingDescription[i]).val() || ""
		};
	}
	parameter.key = packagingObject;
	parameter["old_product_code"] = document
			.getElementById("detail-product-boId").innerHTML;
	parameter["add_product_code"] = document
			.getElementById("add_product_boId_code").value.trim();

	parameter["add_product_name"] = document.getElementById("add_product_name").value
			.trim();
	parameter["add_brand_name"] = document.getElementById("add_brand_name").value
			.trim();
	parameter["add_flavour"] = document.getElementById("add_flavour").value
			.trim();

	if ($("#add_product_price").val() != null) {
		parameter["add_product_price"] = $("#add_product_price").val().trim()
				.replace(",", "");
	}
	var creditPrice = document.getElementById("add_product_creditPrice");
	if (creditPrice != null) {
		parameter["add_product_creditPrice"] = creditPrice.value.trim();
	}
	var specialPrice = document.getElementById("add_product_specialPrice");
	if (specialPrice != null) {
		parameter["add_product_specialPrice"] = specialPrice.value.trim();
	}
	parameter["add_product_discounted"] = document
			.getElementById("add_product_discounted").checked;
	// parameter["productPrefix"] =
	// document.getElementById("add_product_location").value;
	console.log(parameter);
	loading();
	request.open("POST", "saveEditProduct?input=" + JSON.stringify(parameter),
			true);
	request.send();
}

function addUserAccount(description) {
	var isNewAccount = document.getElementById("isNewAccount").innerHTML;
	var userName = document.getElementById("add_user_name").value;
	var password = document.getElementById("add_user_password").value;
	var confirmPassword = document.getElementById("add_user_confirm_password").value;
	var role = document.getElementById("selectRole").value;
	var employeeBoId = document.getElementById("employeeBoId").innerHTML;
	if (role == "") {
		alert("Please, Choose Role in Select Role!");
		return;
	}
	if (password != confirmPassword) {
		alert("Check! Password and ConfirmPassword not equal!");
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
					if (result == "INVALIDUSER") {
						alert("Username has already exist!");
						return;
					}
                if(isNewAccount == "no"){
                    logout();
                    return;
                }
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				$('#workspace').removeClass('myFilter');
				loadAction(document.getElementById("employeemenu"), 'employee');
				document.getElementById("search-text").value = document
						.getElementById("employeeBoId").innerHTML;
				search(this, 'ID', 'EMPLOYEE');
				this.id = document.getElementById("employeeBoId").innerHTML;
				aboutDetail(this, 'EMPLOYEE');
			} else {
				alert("Error return: " + request.status);
			}
		}
	};
	var parameter = {};
	parameter["userName"] = userName;
	parameter["password"] = password;
	parameter["role"] = role;
	parameter["employeeBoId"] = employeeBoId;
	console.log("parameter", parameter);
	loading();
	request.open("POST", "saveUserAcount?input=" + JSON.stringify(parameter),
			true);
	request.send();
}

function addNewEmployee(description) {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				$('#workspace').removeClass('myFilter');
				loadAction(document.getElementById("employeemenu"), 'employee');
				alert("Successfully Recorded!")
				document.getElementById("search-text").value = document.getElementById("add_employee_name").value;
				search(this, 'NAME', 'EMPLOYEE');
			} else {
				alert("Error return :" + request.status);
			}
		}
	};
	var parameter = {};
	parameter["add_employee_name"] = document
			.getElementById("add_employee_name").value.trim();
	parameter["add_employee_position"] = document
			.getElementById("add_employee_position").value.trim();
	parameter["add_employee_baseSalary"] = document
			.getElementById("add_employee_baseSalary").value.trim() ? document
			.getElementById("add_employee_baseSalary").value.trim() : "0";
	parameter["add_employee_putaSideAmount"] = document
			.getElementById("add_employee_putaSideAmount").value.trim() ? document
			.getElementById("add_employee_putaSideAmount").value.trim()
			: "0";
	parameter["add_employee_nrc"] = document.getElementById("add_employee_nrc").value
			.trim();
	parameter["add_employee_dob"] = document.getElementById("add_employee_dob").value
			.trim();
	parameter["add_employee_education"] = document
			.getElementById("add_employee_education").value.trim();
	parameter["add_employee_atm"] = document.getElementById("add_employee_atm").value
			.trim();
	parameter["add_employee_entranceDate"] = document
			.getElementById("add_employee_entranceDate").value.trim();
	parameter["add_employee_location"] = document
			.getElementById("add_employee_location").value.trim();
	parameter["add_employee_email"] = document
			.getElementById("add_employee_email").value.trim();
	parameter["add_employee_phoneNumber"] = document
			.getElementById("add_employee_phoneNumber").value.trim();
	parameter["add_employee_addressLine1"] = document
			.getElementById("add_employee_addressLine1").value.trim();
	// parameter["add_employee_addressLine2"] = document
	// 		.getElementById("add_employee_addressLine2").value.trim();
	// parameter["add_employee_city"] = document
	// 		.getElementById("add_employee_city").value.trim();
	// parameter["add_employee_state"] = document
	// 		.getElementById("add_employee_state").value.trim();
	// parameter["add_employee_country"] = document
	// 		.getElementById("add_employee_country").value.trim();
	// parameter["add_employee_postalCode"] = document
	// 		.getElementById("add_employee_postalCode").value.trim();
	console.log(parameter);
	loading();
	request.open("POST", "saveNewEmployee?input=" + JSON.stringify(parameter),
			true);
	request.send();
}

function addEditEmployee(description) {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				$('#workspace').removeClass('myFilter');
				loadAction(document.getElementById("employeemenu"), 'employee');
				document.getElementById("search-text").value = document
						.getElementById("add_employee_boId").innerHTML;
				search(this, 'ID', 'EMPLOYEE');
				this.id = document.getElementById("add_employee_boId").innerHTML;
				aboutDetail(this, 'EMPLOYEE');
			} else {
				alert("Error return :" + request.status);
			}
		}
	};
	var parameter = {};
	parameter["add_employee_boId"] = document
			.getElementById("add_employee_boId").innerHTML;
	parameter["add_employee_name"] = document
			.getElementById("add_employee_name").value.trim();
	parameter["add_employee_position"] = document
			.getElementById("add_employee_position").value.trim();
	parameter["add_employee_baseSalary"] = document
			.getElementById("add_employee_baseSalary").value.trim() ? document
			.getElementById("add_employee_baseSalary").value.trim() : "0";
	parameter["add_employee_putaSideAmount"] = document
			.getElementById("add_employee_putaSideAmount").value.trim() ? document
			.getElementById("add_employee_putaSideAmount").value.trim()
			: "0";
	parameter["add_employee_nrc"] = document.getElementById("add_employee_nrc").value
			.trim();
	parameter["add_employee_dob"] = document.getElementById("add_employee_dob").value
			.trim();
	parameter["add_employee_education"] = document
			.getElementById("add_employee_education").value.trim();
	parameter["add_employee_atm"] = document.getElementById("add_employee_atm").value
			.trim();
	parameter["add_employee_entranceDate"] = document
			.getElementById("add_employee_entranceDate").value.trim();
	parameter["add_employee_location"] = document
			.getElementById("add_employee_location").value.trim();
	parameter["add_employee_email"] = document
			.getElementById("add_employee_email").value.trim();
	parameter["add_employee_phoneNumber"] = document
			.getElementById("add_employee_phoneNumber").value.trim();
	parameter["add_employee_addressLine1"] = document
			.getElementById("add_employee_addressLine1").value.trim();
	// parameter["add_employee_addressLine2"] = document
	// 		.getElementById("add_employee_addressLine2").value.trim();
	// parameter["add_employee_city"] = document
	// 		.getElementById("add_employee_city").value.trim();
	// parameter["add_employee_state"] = document
	// 		.getElementById("add_employee_state").value.trim();
	// parameter["add_employee_country"] = document
	// 		.getElementById("add_employee_country").value.trim();
	// parameter["add_employee_postalCode"] = document
	// 		.getElementById("add_employee_postalCode").value.trim();
	console.log(parameter);
	loading();
	request.open("POST", "saveEditEmployee?input=" + JSON.stringify(parameter),
			true);
	request.send();
}

function addNewCustomer(description, entityType) {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				$('#workspace').removeClass('myFilter');
				if (entityType == "CUSTOMER") {
					loadAction(document.getElementById("customermenu"),
							'customer');
				} else {
					loadAction(document.getElementById("suppliermenu"),
							'supplier');
				}
				alert("Successfully Recorded!");
				if (document.getElementById("add_customer_Type").innerHTML == "BRANCH") {
					document.getElementById("search-text").value = document
							.getElementById("add_company_name").value;
					search(this, 'NAME', 'SUPPLIER');
				} else {
					document.getElementById("search-text").value = document
							.getElementById("add_customer_name").value;
					search(this, 'NAME', 'CUSTOMER');
				}

			} else {
				alert("Error return :" + request.status);
			}
		}
	};
	var parameter = {};
	if (document.getElementById("add_customer_Type").value == "OUTLET") {
		parameter["add_outlet_type"] = document.getElementById("outletType").value;
	} else if (document.getElementById("add_customer_Type").innerHTML == "BRANCH") {
		parameter["add_outlet_type"] = document
				.getElementById("add_customer_Type").innerHTML;
	} else {
		parameter["add_outlet_type"] = document
				.getElementById("add_customer_Type").value;
	}

	// For Supplier New Form
	if (document.getElementById("add_customer_Type").innerHTML == "BRANCH") {
		console.log("In Supplier New Form");
		parameter["add_customer_name"] = document
				.getElementById("add_company_name").value.trim();
		parameter["add_authorized_person"] = document
				.getElementById("add_authorized_person").value.trim() ? document
				.getElementById("add_authorized_person").value.trim()
				: "";
		parameter["add_customer_country"] = document
				.getElementById("add_country_origin").value.trim() ? document
				.getElementById("add_country_origin").value.trim() : "";
		parameter["add_customer_type"] = document
				.getElementById("add_customer_Type").innerHTML;
		parameter["add_customer_location"] = document
				.getElementById("add_customer_location").innerHTML;
		parameter["add_customer_Branch"] = document
				.getElementById("add_customer_Branch").value;
	}
	// For Customer New Form
	else {
		console.log("In Customer New Form");
		parameter["add_customer_name"] = document
				.getElementById("add_customer_name").value.trim();
		parameter["add_customer_type"] = document
				.getElementById("add_customer_Type").value.trim();
		parameter["add_customer_country"] = document
				.getElementById("add_customer_country").value.trim() ? document
				.getElementById("add_customer_country").value.trim() : "";
		parameter["add_customer_type"] = document
				.getElementById("add_customer_Type").value.trim();
		parameter["add_customer_location"] = document
				.getElementById("add_customer_location").value.trim();
		parameter["add_authorized_person"] = "";
	}
	parameter["add_customer_nrc"] = document.getElementById("add_customer_nrc").value
			.trim();
	parameter["add_customer_dob"] = document.getElementById("add_customer_dob").value
			.trim();

	parameter["add_customer_email"] = document
			.getElementById("add_customer_email").value.trim();
	parameter["add_customer_phoneNumber"] = document
			.getElementById("add_customer_phoneNumber").value.trim();
	parameter["add_customer_addressLine1"] = document
			.getElementById("add_customer_addressLine1").value.trim() ? document
			.getElementById("add_customer_addressLine1").value.trim()
			: "";
	parameter["add_customer_addressLine2"] = "";
	parameter["add_customer_city"] = document
			.getElementById("add_customer_city").value.trim() ? document
			.getElementById("add_customer_city").value.trim() : "";
	parameter["add_customer_state"] = document
        .getElementById("add_customer_state").value.trim() ? document
        .getElementById("add_customer_state").value.trim() : "";

	parameter["add_customer_postalCode"] = "";
	// opening balance
	parameter["add_customer_openingDate"] = document
			.getElementById("add_customer_openingDate").value.trim();
	parameter["add_customer_openingBalance"] = document
			.getElementById("add_customer_openingBalance").value.trim() ? document
			.getElementById("add_customer_openingBalance").value.trim()
			: "0";
	parameter["add_customer_riskLevel"] = document
			.getElementById("add_risk_level").value;
	parameter["add_customer_class"] = document.getElementById('customerClass').value;

	parameter["add_customer_remark"] = document
			.getElementById("add_customer_remark").value.trim();

	console.log(parameter);
	loading();
	request.open("POST", "saveNewCustomer?input=" + JSON.stringify(parameter),
			true);
	request.send();
}

function addEditCustomer(description) {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				$('#workspace').removeClass('myFilter');
				loadAction(document.getElementById("customermenu"), 'customer');
				document.getElementById("search-text").value = document
						.getElementById("add_customer_boId").innerHTML;
				search(this, 'ID', 'CUSTOMER');
				this.id = document.getElementById("add_customer_boId").innerHTML;
				aboutDetail(this, 'CUSTOMER');
			} else {
				alert("Error return :" + request.status);
			}
		}
	};
	var parameter = {};
	if (document.getElementById("add_customer_Type").value == "OUTLET") {
		parameter["add_outlet_type"] = document.getElementById("outletType").value;
	} else {
		parameter["add_outlet_type"] = document
				.getElementById("add_customer_Type").value;
	}
	parameter["add_customer_boId"] = document
			.getElementById("add_customer_boId").innerHTML;
	parameter["add_customer_name"] = document
			.getElementById("add_customer_name").value.trim();
	parameter["add_customer_nrc"] = document.getElementById("add_customer_nrc").value
			.trim();
	parameter["add_customer_type"] = document
			.getElementById("add_customer_Type").value.trim();
	parameter["add_customer_dob"] = document.getElementById("add_customer_dob").value
			.trim();
	parameter["add_customer_location"] = document
			.getElementById("add_customer_location").value.trim();
	parameter["add_customer_email"] = document
			.getElementById("add_customer_email").value.trim();
	parameter["add_customer_phoneNumber"] = document
			.getElementById("add_customer_phoneNumber").value.trim();
	parameter["add_customer_addressLine1"] = document
			.getElementById("add_customer_addressLine1").value.trim();
	/*parameter["add_customer_addressLine2"] = document
			.getElementById("add_customer_addressLine2").value.trim();*/
	parameter["add_customer_city"] = document
			.getElementById("add_customer_city").value.trim();
	/*parameter["add_customer_state"] = document
			.getElementById("add_customer_state").value.trim();*/
	parameter["add_customer_country"] = document
			.getElementById("add_customer_country").value.trim();
	/*parameter["add_customer_postalCode"] = document
			.getElementById("add_customer_postalCode").value.trim();
*/
	// opening balance
	parameter["add_customer_openingDate"] = document
			.getElementById("add_customer_openingDate").value.trim();
	parameter["add_customer_openingBalance"] = document
			.getElementById("add_customer_openingBalance").value.trim() ? document
			.getElementById("add_customer_openingBalance").value.trim()
			: "0";
	parameter["add_customer_risk"] = document.getElementById("add_risk_level").value;
	parameter["add_customer_class"] = document.getElementById('customerClass').value;
	/*
	 * parameter["add_customer_remark"] = document
	 * .getElementById("add_customer_remark").value.trim();
	 */
	console.log(parameter);
	loading();
	request.open("POST", "saveEditCustomer?input=" + JSON.stringify(parameter),
			true);
	request.send();
}

function addEditSupplier(description) {
	if (document.getElementById("add_customer_location").value == document
			.getElementById("add_customer_Branch").value
			&& document.getElementById("add_customer_Type").value == "BRANCH") {
		alert("Check Location and Branch,they are same");
		return;
	}
   var name=document.getElementById("add_company_name").value;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				$('#workspace').removeClass('myFilter');
				loadAction(document.getElementById("suppliermenu"), 'supplier');
				document.getElementById("search-text").value = name;
				this.value=name;
				search(this, 'NAME', 'SUPPLIER');
				this.id = document.getElementById("add_customer_boId").innerHTML;
				aboutDetail(this, 'SUPPLIER');
			} else {
				alert("Error return :" + request.status);
			}
		}
	};
	var parameter = {};
	parameter["add_customer_boId"] = document
			.getElementById("add_customer_boId").innerHTML;
	parameter["add_customer_name"] = document
			.getElementById("add_company_name").value.trim();
	parameter["add_customer_nrc"] = document.getElementById("add_customer_nrc").value
			.trim();
	parameter["add_authorized_person"] = document
			.getElementById("add_authorized_person").value.trim();
	parameter["add_customer_type"] = document
			.getElementById("add_customer_Type").value.trim();
	parameter["add_customer_dob"] = document.getElementById("add_customer_dob").value
			.trim();
	parameter["add_customer_location"] = document
			.getElementById("add_customer_location").value.trim();
	parameter["add_customer_email"] = document
			.getElementById("add_customer_email").value.trim();
	parameter["add_customer_phoneNumber"] = document
			.getElementById("add_customer_phoneNumber").value.trim();
	parameter["add_customer_addressLine1"] = document
			.getElementById("add_customer_addressLine1").value.trim();
	parameter["add_customer_addressLine2"] = document
			.getElementById("add_customer_addressLine2").value.trim();
	parameter["add_customer_city"] = document
			.getElementById("add_customer_city").value.trim();
	parameter["add_customer_state"] = document
			.getElementById("add_customer_state").value.trim();
	parameter["add_customer_country"] = document
			.getElementById("add_country_origin").value.trim();
	parameter["add_customer_postalCode"] = document
			.getElementById("add_customer_postalCode").value.trim();
	parameter["add_outlet_type"] = "BRANCH";
	// opening balance
	parameter["add_customer_openingDate"] = document
			.getElementById("add_customer_openingDate").value.trim();
	parameter["add_customer_openingBalance"] = document
			.getElementById("add_customer_openingBalance").value.trim() ? document
			.getElementById("add_customer_openingBalance").value.trim()
			: "0";
	parameter["add_customer_Branch"] = document
			.getElementById("add_customer_Branch").value.trim();
	parameter["add_customer_risk"] = document.getElementById("add_risk_level").value;
	parameter["add_customer_class"] = document.getElementById('customerClass').value;
	/*
	 * parameter["add_customer_remark"] = document
	 * .getElementById("add_customer_remark").value.trim();
	 */
	console.log(parameter);
	 loading();
	 request.open("POST", "saveEditCustomer?input=" +
	 JSON.stringify(parameter),
	 true);
	 request.send();
}

function addNewLocation(description) {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				var result = document.createElement("div");
				result.innerHTML = request.responseText;
				result = $(result).children("span").text();
				if (result == "CheckBranch") {
					alert("You have already Branch Location!");
					return;
				}
				alert("Successfully Recorded!");
				$('#workspace').removeClass('myFilter');
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				loadAction(document.getElementById("locationmenu"), 'location');
				document.getElementById("search-text").value = document
						.getElementById("add_location_name").value;
				search(this, 'NAME', 'LOCATION');
			} else {
				alert("Error return :" + request.status);
			}
		}
	};
	if (Object.keys(errors).length > 0) {
		alert("Please choose correct product Code.")
		return;
	}
	var parameter = {};
	parameter["add_location_name"] = document
			.getElementById("add_location_name").value.trim();
	parameter["add_location_type"] = document
			.getElementById("add_location_type").value.trim();
	var openingDate = document.getElementById("add_location_openingDate").value;
	var boIdList = document.getElementsByName("add-location-product-boId");
	var qtyList = document.getElementsByName("add_location_product_qty");
	var productList = {};
	var packagingTypeList = {};
	if ($(document.getElementsByName("add_location_product_price")).length != 0) {
		for (var i = 1; i < boIdList.length; i++) {
			var value = parseInt(qtyList[i].value.trim() ? qtyList[i].value
					.trim() : 0);
			if (value != 0) {
				if ((boIdList[i].value.trim() in productList)) {
					productList[boIdList[i].value.trim()] += value;
				} else {
					productList[boIdList[i].value.trim()] = value;
				}
			}
		}
		parameter["productList"] = productList;
	} else {
		var packageTypeList = document
				.getElementsByName("newLocationPackageType");
		for (var i = 1; i < boIdList.length; i++) {
			var value = parseInt(qtyList[i].value.trim() ? qtyList[i].value
					.trim() : 0);
			var packAndBoId = boIdList[i].value.trim() + ','
					+ packageTypeList[i].value;
			if (value != 0) {
				if (packAndBoId in packagingTypeList) {
					packagingTypeList[packAndBoId] += value;
				} else {
					packagingTypeList[packAndBoId] = value;
				}
			}
		}
		parameter["packagingTypeList"] = packagingTypeList;
	}
	parameter["codeChanged"] = document
			.getElementById("add_location_codeChanged").checked;
	parameter["openingDate"] = openingDate;
	// parameter["remark"] =
	// document.getElementById("add_location_remark").value;
	console.log(parameter);
	loading();
	request.open("POST", "saveNewLocation?input=" + JSON.stringify(parameter),
			true);
	request.send();
}

function addEditLocation(description) {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				$('#workspace').removeClass('myFilter');
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				loadAction(document.getElementById("locationmenu"), 'location');
				document.getElementById("search-text").value = document
						.getElementById("add_location_boId").innerHTML;
				search(this, 'ID', 'LOCATION');
				this.id = document.getElementById("add_location_boId").innerHTML;
				aboutDetail(this, 'LOCATION');
				//var updateSeletedPorder = $("#porderlocationname option[value="
					//	+ this.id + "]");
				//var updateSeletedSalary = $("#salarylocation option[value="
					//	+ this.id + "]");
			//	updateSeletedPorder[0].innerHTML = parameter["add_location_name"];
				//updateSeletedSalary[0].innerHTML = parameter["add_location_name"];
			} else {
				alert("Error return :" + request.status);
			}
		}
	};
	if (Object.keys(errors).length > 0) {
		alert("Please choose correct product Code.")
		return;
	}
	var parameter = {};
	parameter["add_location_boId"] = document
			.getElementById("add_location_boId").innerHTML;
	parameter["add_location_name"] = document
			.getElementById("add_location_name").value.trim();
	parameter["add_location_type"] = document
			.getElementById("add_location_type").value.trim();
	var openingDate = document.getElementById("add_location_openingDate").value;
	var boIdList = document.getElementsByName("add-location-product-boId");
	var alterCodeList = document
			.getElementsByName("add_location_alternate_productcode");
	var qtyList = document.getElementsByName("add_location_product_qty");
	var productCodeList = {};
	var productList = {};
	for (var i = 1; i < boIdList.length; i++) {
		var value = parseInt(qtyList[i].value.trim() ? qtyList[i].value.trim()
				: 0);
		var alterCode = alterCodeList[i].value.trim() ? alterCodeList[i].value
				.trim() : "";
		if (value != 0) {
			if ((boIdList[i].value.trim() in productList)) {
				productList[boIdList[i].value.trim()] += value;
			} else {
				productList[boIdList[i].value.trim()] = value;
			}
		}
		// productList[boIdList[i].value] = qtyList[i].value;
		productCodeList[boIdList[i].value.trim()] = alterCode;
	}
	parameter["productList"] = productList;
	parameter["openingDate"] = openingDate;
	parameter["productCodeList"] = productCodeList;
	// parameter["remark"] =
	// document.getElementById("add_location_remark").value;
	console.log(parameter);
	loading();
	request.open("POST", "saveEditLocation?input=" + JSON.stringify(parameter),
			true);
	request.send();
}

function getStockList(description) {
	var table = document.getElementById("transfer-table");
	var rowCount = table.rows.length;
	if (rowCount > 1) {
		for (var i = 1; i < rowCount; i++) {
			var pcode = table.rows[i].cells[0].childNodes[0].innerHTML;
			if (pcode == document
					.getElementById("transfer-from-loc-stock-code").value) {
				alert("Already inserted in transfer stock list!");
				document.getElementById("transfer-quantity").disabled = true;
				document.getElementById("transfer-button").disabled = true;
				// document.getElementById("transfer-dialog-button").disabled =
				// true;
			} else {
				var index = document
						.getElementById("transfer-from-loc-stock-code").selectedIndex;
				document.getElementById("transfer-from-loc-stock-name").value = document
						.getElementById("transfer-from-loc-stock-name").options[index].value;
				document.getElementById("transfer-from-loc-stock-price").value = document
						.getElementById("transfer-from-loc-stock-price").options[index].value;
				if (description == 'stock') {
					document.getElementById("transfer-from-loc-stock-qty").value = document
							.getElementById("transfer-from-loc-stock-qty").options[index].value;
					document.getElementById("transfer-quantity").max = document
							.getElementById("transfer-from-loc-stock-qty").value;
				}

				document.getElementById("transfer-quantity").value = "";
				document.getElementById("transfer-quantity").disabled = false;
				document.getElementById("transfer-button").disabled = true;
			}
		}
	} else {
		var index = document.getElementById("transfer-from-loc-stock-code").selectedIndex;
		document.getElementById("transfer-from-loc-stock-name").value = document
				.getElementById("transfer-from-loc-stock-name").options[index].value;
		document.getElementById("transfer-from-loc-stock-price").value = document
				.getElementById("transfer-from-loc-stock-price").options[index].value;
		if (description == 'stock') {
			document.getElementById("transfer-from-loc-stock-qty").value = document
					.getElementById("transfer-from-loc-stock-qty").options[index].value;
			document.getElementById("transfer-quantity").max = document
					.getElementById("transfer-from-loc-stock-qty").value;
		}
		document.getElementById("transfer-quantity").value = "";
		document.getElementById("transfer-quantity").disabled = false;
		document.getElementById("transfer-button").disabled = true;
	}
}

function convertPackageTypeForm() {
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var request = new XMLHttpRequest;
	$('#workspace').addClass("myFilter");
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
			}
		}
	}
	var parameter = {};
	parameter["locationId"] = locationId;
	loading();
	request.open("GET", "getConvertPackageTypeForm?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

function deleteRoute(element) {
	document.getElementById("confirm-text").innerHTML = "Are you sure you want to Delete?";
	document.getElementById("confirm").attributes["class"].value = "dialog";
	document.getElementById("confirm-delete").attributes["onclick"].value = "deleteRouteConfirm('"
			+ element.id + "');";
}

function deleteRouteConfirm(boId) {
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("confirm").attributes["class"].value = "hide";
				loadAction(document.getElementById("locationmenu"), 'location');
				document.getElementById("search-text").value = locationId;
				search(this, 'ID', 'LOCATION');
				this.id = locationId;
				aboutDetail(this, 'LOCATION');
			} else {
				alert("Delete error return :" + request.status);
			}
		}
	};
	var parameter = {};
	parameter["boId"] = boId;
	console.log(boId);
	parameter["locationId"] = locationId;
	console.log(parameter["locationId"]);
	request.open("DELETE", "deleteRouteConfirm?input="
			+ JSON.stringify(parameter), true);
	request.send();
	loading();
}

function editRouteForm(element) {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("route-location").innerHTML = document
						.getElementById("detail-location-name").innerHTML;

				//Load All Packaging Type
                var count = document.getElementsByName("route-product-extra-boId").length;
                for(var i =1 ; i <= count - 1  ; i++){
                      getRouteProductInformation2(document.getElementsByName("route-product-extra-boId")[i]);
				}
			} else {
				alert("Please try again. Error code is " + request.status);
			}
		}
	};
	parameter["boId"] = element.id;
	console.log("Location BoId",document.getElementById("detail-location-boId"));
	parameter["locationId"] = document.getElementById("detail-location-boId").innerHTML;
	console.log("parameter", parameter);
	loading();
	request.open("GET", "editRouteForm?input=" + JSON.stringify(parameter),
			true);
	request.send(null);
}

function adjustmentStockForm() {
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var request = new XMLHttpRequest;
	$('#workspace').addClass("myFilter");
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";

				// document.getElementById("adjustment-Date").value = today();
			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["routeId"] = "";
	loading();
	request.open("GET", "getAdjustmentStockForm?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

function editAdjustmentStock() {
	var locationId = document.getElementById("adjustment-location-id").innerHTML;
	var locationType=document.getElementById("adjustment-location-type").innerHTML;
	var detailLocId = document.getElementById("detail-location-boId").innerHTML;
	var route = document.getElementById("adjustment-route-id").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				$('#workspace').removeClass('myFilter');
				if (route == "") {
					loadAction(document.getElementById("locationmenu"),
							'location');
					document.getElementById("search-text").value = locationId;
					search(this, 'ID', 'LOCATION');
					this.id = locationId;
					this['data-type']=locationType;
					aboutDetail(this, 'LOCATION');
				} else {
					loadAction(document.getElementById("locationmenu"),
							'location');
					document.getElementById("search-text").value = detailLocId;
					search(this, 'ID', 'LOCATION');
					this.id = detailLocId;
					aboutDetail(this, 'LOCATION');
				}
			} else {
				alert("Load Adjustment form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["adjustment-Date"] = document.getElementById("adjustment_date").innerHTML;
	parameter["route"] = route;
	parameter["detailLocId"] = detailLocId;
	var boIdList = document.getElementsByName("adjustmentStock_boIdList");
	// var editboIdList = $("[name=add-adjustment-product-boId-extra1]");
	var editboIdList = $("[name=adjustmentStock_boIdList1]");
	var qtyList = document.getElementsByName("origin_valueList");
	var groundList = $("[name=edit-adjustmentStock_groundList]");
	var eqtyList = document.getElementsByName("origin_valueList1");
	var egroundList = $("[name=edit-adjustmentStock_groundList1]");
	var ee = document.getElementsByName("adjustmentStock_boIdList1");
	var stockType = $("[name=adjustmentStock_stockType]");
	var editStockType = $("[name=editAdjustment_stockType]");
	var pp = $("[name=purchasePrice]");
	var pp1 = $("[name=purchasePrice1]");
	var groundProductList = {};
	var productList = {};
	var packagingTypeList = {};
	var priceList = {};
	if ($(document.getElementsByName("edit_adjustment_packageType")).length == 0) {
		for (var i = 0; i < boIdList.length; i++) {
			var value = parseInt(groundList[i].value.trim() ? groundList[i].value
					.trim()
					: "0");
			productList[boIdList[i].innerHTML.trim()] = value;
		}
		for (var i = 1; i < editboIdList.length; i++) {
			var value = parseInt(egroundList[i].value.trim() ? egroundList[i].value
					.trim()
					: "0");
			if ((editboIdList[i].innerHTML.trim() in productList)) {
				productList[editboIdList[i].innerHTML.trim()] += value;
			} else {
				productList[editboIdList[i].innerHTML.trim()] = value;
			}
		}
		parameter["productList"] = productList;
	} else {
		var packageType = document
				.getElementsByName("edit_adjustment_packageType");
		var epackageType = document
				.getElementsByName("edit_adjustment_packageType1");

		for (var i = 0; i < boIdList.length; i++) {
			var value = parseFloat(groundList[i].value.trim() ? groundList[i].value
					.trim()
					: 0.0);
			var packAndBoIdAndStockType = boIdList[i].innerHTML.trim() + ','
					+ packageType[i].innerHTML + ',' + stockType[i].innerHTML;
			var price = "0";
			if (pp.length != 0) {
				if ($(pp[i]).children().is('input'))
					price = $(pp[i]).find('input').val().trim();
				else
					price = pp[i].innerHTML.trim();
				price = parseInt(price == "" ? "0" : price);
			}
			if (packAndBoIdAndStockType in packagingTypeList) {
				value = parseFloat(packagingTypeList[packAndBoIdAndStockType])
						+ value;
				packagingTypeList[packAndBoIdAndStockType] = value + "";
			} else {
				packagingTypeList[packAndBoIdAndStockType] = value + "";
				priceList[packAndBoIdAndStockType] = price;
			}
		}
		for (var j = 1; j < editboIdList.length; j++) {
			var evalue = parseFloat(egroundList[j].value.trim() ? egroundList[j].value
					.trim()
					: 0.0);
			var epackAndBoIdAndStockType = editboIdList[j].innerHTML.trim()
					+ ',' + epackageType[j].value + ','
					+ editStockType[j].value;
			var eprice = "0";
			if ($(pp1[j]).children().is('input'))
				eprice = $(pp1[j]).find('input').val().trim();
			else
				eprice = $(pp1[j]).find('span').text().trim();

			eprice = eprice == "" ? "0" : eprice;
			console.log(eprice);

			if (epackAndBoIdAndStockType in packagingTypeList) {
				evalue = parseFloat(packagingTypeList[epackAndBoIdAndStockType])
						+ evalue;
				packagingTypeList[epackAndBoIdAndStockType] = evalue + "";
			} else {
				packagingTypeList[epackAndBoIdAndStockType] = evalue + "";
				priceList[epackAndBoIdAndStockType] = eprice;
			}
		}
		parameter["packagingTypeList"] = packagingTypeList;
		parameter["priceList"] = priceList;
	}
	console.log("Parameter ", parameter);
	loading();
	request.open("POST", "editAdjustmentStock", true);
	request.send(submitFormData(parameter));
}

function closeAdjustment() {
	var locationId = document.getElementById("adjustment-location-id").innerHTML;
	var locationType=document.getElementById("adjustment-location-type").innerHTML;
	var detailLocId = document.getElementById("detail-location-boId").innerHTML;
	var route = document.getElementById("adjustment-route-id").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				$('#workspace').removeClass('myFilter');
				if (route == "") {
					loadAction(document.getElementById("locationmenu"),
							'location');
					document.getElementById("search-text").value = locationId;
					search(this, 'ID', 'LOCATION');
					this.id = locationId;
					this['data-type']=locationType;
					aboutDetail(this, 'LOCATION');
				} else {
					loadAction(document.getElementById("locationmenu"),
							'location');
					document.getElementById("search-text").value = detailLocId;
					search(this, 'ID', 'LOCATION');
					this.id = detailLocId;
					aboutDetail(this, 'LOCATION');
				}
			} else {
				alert("Load Adjustment form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["adjustment-Date"] = document.getElementById("adjustment_date").innerHTML;
	parameter["route"] = route;
	parameter["detailLocId"] = detailLocId;
	var boIdList = document.getElementsByName("adjustmentStock_boIdList");
	var editboIdList = $("[name=adjustmentStock_boIdList1]");
	var qtyList = document.getElementsByName("origin_valueList");
	var groundList = $("[name=edit-adjustmentStock_groundList]");
	var eqtyList = document.getElementsByName("origin_valueList1");
	var egroundList = $("[name=edit-adjustmentStock_groundList1]");
	var ee = document.getElementsByName("adjustmentStock_boIdList1");
	var stockTypeList = $("[name=adjustmentStock_stockType]");
	var editStockTypeList = $("[name=editAdjustment_stockType]");
	var pp = $("[name=purchasePrice]");
	var pp1 = $("[name=purchasePrice1]");
	var groundProductList = {};
	var productList = {};
	var packagingTypeList = {};
	var priceList = {};

	if ($(document.getElementsByName("edit_adjustment_packageType")).length == 0) {
		for (var i = 0; i < boIdList.length; i++) {
			var value = parseFloat(groundList[i].value.trim() ? groundList[i].value
					.trim()
					: 0.0);
			var price = "0";
			if (pp.lenght != 0) {
				if ($(pp[i]).children().is('input'))
					price = $(pp[i]).find('input').val().trim();
				else
					price = pp[i].innerHTML.trim();
			}
			price = price == "" ? "0" : price;
			console.log(price);
			productList[boIdList[i].innerHTML.trim()] = value + 0.0;
			priceList[boIdList[i].innerHTML.trim()] = price;
		}
		for (var i = 1; i < editboIdList.length; i++) {
			var value = parseFloat(egroundList[i].value.trim() ? egroundList[i].value
					.trim()
					: 0.0);
			var eprice = "0";

			if ($(pp1[j]).children().is('input'))
				eprice = $(pp1[j]).find('input').val().trim();
			else
				eprice = $(pp1[j]).find('span').text().trim();

			eprice = parseInt(eprice == "" ? "0" : eprice);
			console.log(eprice);
			if ((editboIdList[i].innerHTML.trim() in productList)) {
				value = Float.parseFloat(productList[editboIdList[i].innerHTML
						.trim()])
						+ value
				productList[editboIdList[i].innerHTML.trim()] = value + "";
			} else {
				productList[editboIdList[i].innerHTML.trim()] = value + "";
				priceList[editboIdList[i].innerHTML.trim()] = eprice;
			}
		}
		parameter["productList"] = productList;
		parameter["priceList"] = priceList;

	} else {
		var packageType = document
				.getElementsByName("edit_adjustment_packageType");
		var epackageType = document
				.getElementsByName("edit_adjustment_packageType1");
		for (var i = 0; i < boIdList.length; i++) {
			var value = parseFloat(groundList[i].value.trim() ? groundList[i].value
					.trim()
					: 0.0);
			var price = "0";
			if (pp.length != 0) {
				if ($(pp[i]).children().is('input'))
					price = $(pp[i]).find('input').val().trim();
				else
					price = pp[i].innerHTML.trim();
			}
			price = price == "" ? "0" : price;
			console.log(price);
			var packAndBoIdAndStockType = boIdList[i].innerHTML.trim() + ','
					+ packageType[i].innerHTML + ','
					+ stockTypeList[i].innerHTML;
			if (packAndBoIdAndStockType in packagingTypeList) {
				value = parseFloat(packageTypeList[packAndBoIdAndStockType])
						+ value;
				packageTypeList[packAndBoIdAndStockType] = value + "";
			} else {
				packagingTypeList[packAndBoIdAndStockType] = value + "";
				priceList[packAndBoIdAndStockType] = price;
			}
		}

		for (var j = 1; j < editboIdList.length; j++) {
			var evalue = parseFloat(egroundList[j].value.trim() ? egroundList[j].value
					.trim()
					: 0.0);
			var eprice = "0";
			if (pp1.length != 0) {
				if ($(pp1[j]).children().is('input'))
					eprice = $(pp1[j]).find('input').val().trim();
				else
					eprice = $(pp1[j]).find('span').text().trim();
			}
			eprice = eprice == "" ? "0" : eprice;
			console.log(eprice);
			var epackAndBoIdAndStockType = editboIdList[j].innerHTML.trim()
					+ ',' + epackageType[j].value + ','
					+ editStockTypeList[j].value;
			if (epackAndBoIdAndStockType in packagingTypeList) {
				evalue = parseFloat(packagingTypeList[epackAndBoIdAndStockType])
						+ evalue;
				packagingTypeList[epackAndBoIdAndStockType] = evalue + "";
			} else {
				packagingTypeList[epackAndBoIdAndStockType] = evalue + "";
				priceList[epackAndBoIdAndStockType] = eprice;
			}
		}
		console.log("PackagingTypeList : ", packagingTypeList);
		parameter["packagingTypeList"] = packagingTypeList;
		parameter["priceList"] = priceList;

	}
	console.log("Parameter ", parameter);
	loading();
	request.open("POST", "closeAdjustmentStock", true);
	request.send(submitFormData(parameter));
}

// To Save Import Data from spreadSheet
function saveImportData(entityType) {
	if(entityType == 'LOCATION') {
		console.log("In Location saveImportData");
        var importData = importDataArray.getData();
        var jsonData = JSON.stringify(importData);
        var locationId = document.getElementById("import-location-id").innerHTML;
        var detailLocId = document.getElementById("detail-location-boId").innerHTML;
        var request = new XMLHttpRequest;
        request.onreadystatechange = function () {
            if (request.readyState == 4) {
                if (request.status == 200) {
                    hideLoading();
                    document.getElementById("dialog-temp").attributes["class"].value = "hide";
                    $('#workspace').removeClass('myFilter');
                    loadAction(document.getElementById("locationmenu"), 'location');
                    document.getElementById("search-text").value = locationId;
                    search(this, 'ID', 'LOCATION');
                    this.id = locationId;
                    aboutDetail(this, 'LOCATION');
                } else {
                    alert("Load Adjustment form error.Please try again. Error code is "
                        + request.status);
                }
            }
        };

        var parameter = {};
        parameter["entityType"] = entityType;
        parameter["locationId"] = locationId;
        parameter["detailLocId"] = detailLocId;
        parameter["importData"] = importData;
        console.log("Parameter ::", parameter);
        loading();
        request.open("POST", "saveImportData", true);
        request.send(submitFormData(parameter));
    } else if (entityType == "CUSTOMER") {
        console.log("In Location saveImportData");
        var importData = importDataArray.getData();
        var request = new XMLHttpRequest;
        request.onreadystatechange = function () {
            if (request.readyState == 4) {
                if (request.status == 200) {
                    hideLoading();
                    document.getElementById("dialog-temp").attributes["class"].value = "hide";
                    $('#workspace').removeClass('myFilter');
                    loadAction(document.getElementById("customermenu"), 'customer');
                } else {
                    alert("Load Adjustment form error.Please try again. Error code is "
                        + request.status);
                }
            }
        };
		//START HERE
        var parameter = {};
        parameter["entityType"] = entityType;
        parameter["importData"] = importData;

        //Default Values.......
        parameter["add_customer_country"] = "";
        parameter["add_customer_location"] = "LOC00001";
        parameter["add_authorized_person"] = "";
        parameter["add_customer_nrc"] = "";
        parameter["add_customer_dob"] = "";
        parameter["add_customer_email"] = "";
        parameter["add_customer_phoneNumber"] = "";
        parameter["add_customer_addressLine2"] =  "";
        parameter["add_customer_postalCode"] = "";
        parameter["add_customer_riskLevel"] = "0";
        parameter["add_customer_remark"] = "";

        console.log("Parameter ::", parameter);
        loading();
        request.open("POST", "saveImportData", true);
        request.send(submitFormData(parameter));
	}
}
// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function getCurrentDate() {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; // January is 0!
	var yyyy = today.getFullYear();
	if (dd < 10) {
		dd = '0' + dd
	}
	if (mm < 10) {
		mm = '0' + mm
	}
	today = mm + '/' + dd + '/' + yyyy;
	return today;
}

// Import Data Form
function getImportForm(entityType) {
	if(entityType == 'LOCATION'){
        var locationId = document.getElementById("detail-location-boId").innerHTML;
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
                    var container = document.getElementById('example');
                    importDataArray = new Handsontable(container, {
                        rowHeaders : true,
                        colHeaders : [ 'Product Code', 'Packaging Type', 'Gram',
                            'Quantity', 'Stock Type' ],
                        startRows : 1,

                        minSpareRows : 1, // Increase Row
                        colWidths : 200,
                        maxCols : 5,
                        manualColumnResize : true,
                        manualRowResize : true,
                        contextMenu : true
                    });
                } else {
                    alert("Load Import Form error.Please try again. Error code is "
                        + request.status);
                }
            }
        };
        loading();
        var parameter = {};
        parameter['locationId'] = locationId;
        console.log("EntityType :", entityType);
        request.open("GET", "getImportForm/" + entityType + "?input="
            + JSON.stringify(parameter), true);
        request.send();
	} else if(entityType == 'CUSTOMER'){
		console.log("In customer import form....");
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
                    var container = document.getElementById('example');
                    importDataArray = new Handsontable(container, {
                        rowHeaders : true,
                        colHeaders : [ 'Name', 'Type', 'Outlet Type','Class','Opening Balance','Address','Township','State'],
						columns: [
							{},
							{type : 'dropdown', source: ['RETAIL','OUTLET','DEALER']},
							{type : 'dropdown', source: ['GT_CONVENIENCE_STORE',
								'GROCERY_SHOP',
								'BETEL_NUT_SHOP',
								'TRADITIONAL_MINI_MART',
								'BAR',
								'NIGHT_CLUB',
								'HOTEL',
								'MYANMAR_TRADITIONAL_FOOD_STORE',
								'TEA_SHOP',
								'BEER_STATION',
								'KARAOKE',
								'Entertainment_Outlets_ENT',
								'RESTAURANT',
								'MT_CONVENIENCE_STORE',
								'SUPERMARKET',
								'SUB_WHOLESALE',
								'WHOLESALE',
								'BRANCH']},
							{type : 'dropdown', source: ['A','B','C']},
							{},
							{},
							{},
							{}
						],

                        startRows : 1,

                        minSpareRows : 1, // Increase Row
                        colWidths : [150,100,300,50,120,100,100,100],
                        maxCols : 8,
                        manualColumnResize : true,
                        manualRowResize : true,
                        contextMenu : true
                    });
                } else {
                    alert("Load Import Form error.Please try again. Error code is "
                        + request.status);
                }
            }
        };
        loading();
        var parameter = {};
        parameter['locationId'] = locationId;
        console.log("EntityType :", entityType);
        request.open("GET", "getImportForm/" + entityType + "?input="
            + JSON.stringify(parameter), true);
        request.send();
	}
}

function saveAdjustmentStock() {
	var locationId = document.getElementById("adjustment-location-id").innerHTML;
	var detailLocId = document.getElementById("detail-location-boId").innerHTML;
	var type=document.getElementById("adjustment-location-type").innerHTML;
	var route = document.getElementById("adjustment-route-id").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				$('#workspace').removeClass('myFilter');
				if (route == "") {
					loadAction(document.getElementById("locationmenu"),
							'location');
					document.getElementById("search-text").value = locationId;
					search(this, 'ID', 'LOCATION');
					this.id = locationId;
					this['data-type']=type;
					aboutDetail(this, 'LOCATION');
				} else {
					loadAction(document.getElementById("locationmenu"),
							'location');
					document.getElementById("search-text").value = detailLocId;
					search(this, 'ID', 'LOCATION');
					this.id = detailLocId;
					aboutDetail(this, 'LOCATION', '', '', true);
				}
			} else {
				alert("Load Adjustment form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["adjustment-Date"] = document
			.getElementById("adjustment-date-span").innerHTML;
	parameter["route"] = route;
	parameter["detailLocId"] = detailLocId;
	var boIdList = document.getElementsByName("adjustmentStock_boIdList");
	var qtyList = document.getElementsByName("origin_valueList");
	var groundList = document.getElementsByName("adjustmentStock_groundList");
	var packagingIds = document
			.getElementsByName("adjustmentStock_PackageType");
	var stockTypes = document.getElementsByName("adjustmentStockType");
	var productBoIdList = [];
	var groundQuantityList = [];
	var quantityList = [];
	var packagingIdList = [];
	var stockTypeList = [];
	if ($(document.getElementsByName("adjustmentStock_PackageType")).length != 0) {
		var packageTypeList = document
				.getElementsByName("adjustmentStock_PackageType");
		for (var i = 0; i < boIdList.length; i++) {
			productBoIdList.push(boIdList[i].innerHTML);
			groundQuantityList.push(groundList[i].innerHTML);
			packagingIdList.push(packagingIds[i].innerHTML);
			stockTypeList.push(stockTypes[i].innerHTML);
			quantityList.push(qtyList[i].innerHTML);
		}
		parameter["packagingIdList"] = packagingIdList;
		parameter["stockTypeList"] = stockTypeList;
		parameter["productBoIdList"] = productBoIdList;
		parameter["quantityList"] = quantityList;
		parameter["groundQuantityList"] = groundQuantityList;
	}
	console.log("Parameter ", parameter);
	loading();
	request.open("POST", "saveAdjustmentStock", true);
	request.send(submitFormData(parameter));
}

function submitFormData(input) {
	var formData = new FormData();
	formData.append("input", JSON.stringify(input));
	return formData;
}

function calculateGroundStock() {
	var locationId = document.getElementById("adjustment-location-id").innerHTML;
	var locationType=document.getElementById("adjustment-location-type").innerHTML;
	if ($('#adjustment-Date').val() == "") {
		alert("Please, check Date!");
		$('#adjustment-Date').focus();
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
				if (result == "ERROR") {
					alert("Check Date. Adjustment, before the adjustment, is not allowed!")
				} else {
					document.getElementById("dialog-temp").innerHTML = request.responseText;
					document.getElementById("dialog-temp").className = "dialog";
				}
			} else {
				alert("Load Adjustment form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["route"] = document.getElementById("adjustment-route-id").innerHTML;
	if (document.getElementById("adjustment-Date").value.trim() == null
			|| document.getElementById("adjustment-Date").value.trim() == "") {
		$('#adjustment-Date').addClass("required");
		return;
	}
	$('#adjustment-Date').removeClass("required");
	parameter["adjustment-Date"] = document.getElementById("adjustment-Date").value;
	var boIdList = document.getElementsByName("add-adjustment-product-boId");
	var qtyList = document.getElementsByName("add_adjustment_product_qty");
	var stockTypes = document.getElementsByName("adjustment_stockType");
	var productBoIdList = [];
	var packagingIdList = [];
	var packagingTypeList = [];
	var gramList = [];
	var quantityList = [];
	var stockTypeList = [];
	if ($(document.getElementsByName("productAllPrice")).length != 0) {
		var packageTypeList = document.getElementsByName("productAllPrice");
		for (var i = 1; i < boIdList.length; i++) {
			var value = qtyList[i].value.trim() ? qtyList[i].value.trim() : "0";
			var packageTy = packageTypeList[i].value.split(',');
			packagingIdList.push(packageTy[0]);
			packagingTypeList.push(packageTy[1]);
			gramList.push(packageTy[2]);
			productBoIdList.push(boIdList[i].value.trim());
			quantityList.push(value);
			stockTypeList.push(stockTypes[i].value);
		}
		parameter["packagingIdList"] = packagingIdList;
		parameter["packagingTypeList"] = packagingTypeList;
		parameter["gramList"] = gramList;
		parameter["productBoIdList"] = productBoIdList;
		parameter["quantityList"] = quantityList;
		parameter["stockTypeList"] = stockTypeList;
	} else {
		for (var i = 1; i < boIdList.length; i++) {
			var value = parseInt(qtyList[i].value.trim() ? qtyList[i].value
					.trim() : 0);
			if (value != 0) {
				if ((boIdList[i].value.trim() in productList)) {
					productList[boIdList[i].value.trim()] += value;
				} else {
					productList[boIdList[i].value.trim()] = value;
				}
			}
		}
		parameter["productList"] = productList;
	}
	console.log("parameter", parameter);
	loading();
	request.open("GET", "calculateGroundStock?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

function saveIssueStockToRoute(force) {
	var route = document.getElementById("issue-location-id").innerHTML;
	var locationId = document.getElementById("issue-outlet-location-id").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				var result = document.createElement("div");
				result.innerHTML = request.responseText;
				result = $(result).children("span").text();
				if (result == "ERROR") {
					var r = confirm("This InvoiceNo. is already exist.If you want to save, click 'OK'.");
					if (r == true) {
						saveIssueStockToRoute("forced");
					} else {
						$('#issueInvoice').focus();
					}
					return;
				}
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				loadAction(document.getElementById("locationmenu"), 'location');
				document.getElementById("search-text").value = locationId;
				search(this, 'ID', 'LOCATION');
				this.id = locationId;
				aboutDetail(this, 'LOCATION');

			} else {
				alert("Load Issue form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["route"] = route;
	if (document.getElementById("issue-Date").value.trim() == null
			|| document.getElementById("issue-Date").value.trim() == "") {
		$('#issue-Date').addClass("required");
		return;
	}
	$('#issue-Date').removeClass("required");
	parameter["issue-Date"] = document.getElementById("issue-Date").value;
	parameter["issueInvoice"] = document.getElementById("issueInvoice").value;

	var boIdList = document.getElementsByName("add-issue-product-boId");
	var qtyList = document.getElementsByName("add_issue_product_qty");
	var productList = {};
	for (var i = 1; i < boIdList.length; i++) {
		var value = parseInt(qtyList[i].value.trim() ? qtyList[i].value.trim()
				: 0);
		if (value != 0) {
			if ((boIdList[i].value.trim() in productList)) {
				productList[boIdList[i].value.trim()] += value;
			} else {
				productList[boIdList[i].value.trim()] = value;
			}
		}

	}
	parameter["productList"] = productList;
	if (force) {
		parameter['force'] = force;
	}
	loading();
	request.open("GET", "saveIssueStockToRoute?input="
			+ JSON.stringify(parameter), true);
	request.send(null);

}

function checkTransferQuantity(description) {
	var value = document.getElementById("transfer-quantity").value.trim();
	value = Number(value);
	if (isNaN(value)) {
		return;
	}
	if (description == 'stock') {
		var maxValue = document.getElementById("transfer-from-loc-stock-qty").value;
		if (value > 0 && value <= maxValue) {
			console.log(maxValue + "success" + value);
			document.getElementById("transfer-button").disabled = false;
		} else {
			console.log(maxValue + "fail" + value);
			document.getElementById("transfer-button").disabled = true;
		}
	} else if (description == 'product') {
		if (value > 0)
			document.getElementById("transfer-button").disabled = false;

	} else {
	}
}

function totalTransferQty() {
	var table = document.getElementById("transfer-table");
	var rowCount = table.rows.length;
	var totalQty = 0;
	var totalAmt = 0;
	if (rowCount > 2) {
		for (var i = 1; i < rowCount - 1; i++) {
			var qty = table.rows[i].cells[3].childNodes[0].innerHTML;
			var amt = table.rows[i].cells[4].childNodes[0].innerHTML;
			totalQty += Number(qty);
			totalAmt += Number(amt);
		}
	}
	document.getElementById("transfer_totalQty").innerHTML = totalQty;
	document.getElementById("transfer_totalAmt").innerHTML = totalAmt;
}

function transferProductStock() {
	var table = document.getElementById("transfer-table");
	var rowCount = table.rows.length;
	if (rowCount > 2) {
		for (var i = 1; i < rowCount - 1; i++) {
			var pcode = table.rows[i].cells[0].childNodes[0].innerHTML;
			if (pcode == document
					.getElementById("transfer-from-loc-stock-code").value) {
				alert("Already inserted in transfer stock list!");
				return;
			}
		}
	}
	var colCount = table.rows[0].cells.length;
	var row = table.insertRow(rowCount - 1);
	for (var i = 0; i < 6; i++) {
		var newcell = row.insertCell(i);
		newcell.innerHTML = table.rows[0].cells[i].innerHTML;
		switch (i) {
		case 0:
			newcell.childNodes[0].innerHTML = document
					.getElementById("transfer-from-loc-stock-code").value;
			break;
		case 1:
			newcell.childNodes[0].innerHTML = document
					.getElementById("transfer-from-loc-stock-name").value;
			break;
		case 2:
			newcell.childNodes[0].innerHTML = document
					.getElementById("transfer-from-loc-stock-price").value;
			break;
		case 3:
			newcell.childNodes[0].innerHTML = document
					.getElementById("transfer-quantity").value;
			break;
		case 4:
			newcell.childNodes[0].innerHTML = Number(document
					.getElementById("transfer-from-loc-stock-price").value)
					* Number(document.getElementById("transfer-quantity").value);
			break;
		case 5:
			var element1 = document.createElement("input");
			element1.setAttribute('type', 'button');
			element1.setAttribute('id', rowCount - 1);
			element1.setAttribute('value', 'Edit');
			element1.setAttribute('onclick', 'editTransferRow(this)');
			newcell.appendChild(element1);
			var element2 = document.createElement("input");
			element2.setAttribute('type', 'button');
			element2.setAttribute('id', rowCount - 1);
			element2.setAttribute('value', 'Delete');
			element2.setAttribute('onclick', 'deleteTransferRow(this)');
			newcell.appendChild(element2);
		}
	}
	document.getElementById("transfer-dialog-button").disabled = false;
	totalTransferQty();
}

function editTransferRow(editRow) {
	var table = document.getElementById("transfer-table");
	document.getElementById("transfer-from-loc-stock-code").value = table.rows[editRow.id].cells[0].childNodes[0].innerHTML;
	var index = document.getElementById("transfer-from-loc-stock-code").selectedIndex;
	document.getElementById("transfer-from-loc-stock-name").value = table.rows[editRow.id].cells[1].childNodes[0].innerHTML;
	document.getElementById("transfer-from-loc-stock-price").value = table.rows[editRow.id].cells[2].childNodes[0].innerHTML;
	document.getElementById("transfer-from-loc-stock-qty").value = document
			.getElementById("transfer-from-loc-stock-qty").options[index].value;
	document.getElementById("transfer-quantity").value = table.rows[editRow.id].cells[3].childNodes[0].innerHTML;
	document.getElementById("transfer-button").value = "Update";
	table.rows[editRow.id].cells[5].childNodes[0].disabled = true;
	table.rows[editRow.id].cells[5].childNodes[1].disabled = true;
	// document.getElementById("transfer-dialog-button").disabled = true;
	document.getElementById("transfer-button").onclick = function() {
		table.rows[editRow.id].cells[0].childNodes[0].innerHTML = document
				.getElementById("transfer-from-loc-stock-code").value;
		table.rows[editRow.id].cells[1].childNodes[0].innerHTML = document
				.getElementById("transfer-from-loc-stock-name").value;
		table.rows[editRow.id].cells[2].childNodes[0].innerHTML = document
				.getElementById("transfer-from-loc-stock-price").value;
		table.rows[editRow.id].cells[3].childNodes[0].innerHTML = document
				.getElementById("transfer-quantity").value;
		table.rows[editRow.id].cells[4].childNodes[0].innerHTML = Number(document
				.getElementById("transfer-from-loc-stock-price").value)
				* Number(document.getElementById("transfer-quantity").value);
		table.rows[editRow.id].cells[5].childNodes[1].disabled = false;
		table.rows[editRow.id].cells[5].childNodes[0].disabled = false;
		document.getElementById("transfer-dialog-button").disabled = false;
		document.getElementById("transfer-button").value = "Transfer";
		document.getElementById("transfer-button").setAttribute('onclick',
				'transferProductStock()');
		totalTransferQty();
	}
}

function deleteTransferRow(deleteRow) {
	try {
		var table = document.getElementById("transfer-table");
		var rowCount = table.rows.length;
		// table.deleteRow(deleteRow.id);
		var d = deleteRow.parentNode.parentNode.rowIndex;
		table.deleteRow(d);
		if (rowCount == 2)
			document.getElementById("transfer-dialog-button").disabled = true;
		totalTransferQty();
	} catch (e) {
		alert(e);
	}
}

function deleteRouteRow(deleteRow) {
	try {
		var table = document.getElementById("route-stock-table");
		// table.deleteRow(deleteRow.id);
		var d = deleteRow.parentNode.parentNode.rowIndex;
		table.deleteRow(d);
		var rowCount = table.rows.length;
		if (rowCount == 1)
			document.getElementById("route-dialog-button").disabled = true;
	} catch (e) {
		alert(e);
	}
}

function nextEditTransfer(force) {
	var fromLocation = document.getElementById("edit_locationBoId").innerHTML
			.trim();
	var toLocation = document.getElementById("to-location").value;
	var date = document.getElementById("transfer-date").value.trim();
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				var result = document.createElement("div");
				result.innerHTML = request.responseText;
				result = $(result).children("span").text();
				if (result == "ERROR") {
					var r = confirm("This InvoiceNo. is already exist.If you want to save, click 'OK'.");
					if (r == true) {
						nextEditTransfer("forced");
					} else {
						$('#transfer-Invoice').focus();
					}
					return;
				}
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";

			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	if (document.getElementById("transfer-Invoice").value == 'RT') {
		alert("Check Invoice No.,Don't let 'RT'.");
		return;
	}
	loading();
	var transferRecord = {};
	transferRecord['transfer-date'] = date;
	transferRecord['from-location'] = fromLocation;
	transferRecord['to-location'] = toLocation;
	transferRecord['transfer_boId'] = document.getElementById("transfer_boId").innerHTML;
	transferRecord['transferInvoice'] = document
			.getElementById("transfer-Invoice").value;
	if (force) {
		transferRecord['force'] = force;
	}
	request.open("GET", "getEditTransferLocation?input="
			+ JSON.stringify(transferRecord), true);
	request.send(null);
}

function nextTransfer(force) {
	var fromLocation = document.getElementById("detail-location-boId").innerHTML
			.trim();
	var location_type=document.getElementById("location-type").innerHTML;
	var toLocation = document.getElementById("to-location").value;
	var toLocationBoId = toLocation.split(',')[0];
	var fromLocationName = document.getElementById("from-location").innerHTML;
	var toLocationName = toLocation.split(',')[1];
	var date = document.getElementById("transfer-date").value.trim();
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				var result = document.createElement("div");
				result.innerHTML = request.responseText;
				result = $(result).children("span").text();
				if (result == "ERROR") {
					var r = confirm("This InvoiceNo. is already exist.If you want to save, click 'OK'.");
					if (r == true) {
						nextTransfer("forced");
					} else {
						$('#transfer-Invoice').focus();
					}
					return;
				}
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	if (document.getElementById("transfer-Invoice").value == 'RT') {
		alert("Check Invoice No.,Don't let 'RT'.");
		return;
	}
	loading();
	var transferRecord = {};
	transferRecord['transfer-date'] = date;
	transferRecord['from-location'] = fromLocation;
	transferRecord['to-location'] = toLocationBoId;
	transferRecord['to-locationName'] = toLocationName;
	transferRecord['from-locationName'] = fromLocationName;
	transferRecord['locationType']=location_type;
	transferRecord['transferInvoice'] = document
			.getElementById("transfer-Invoice").value;
	if (force) {
		transferRecord['force'] = force;
	}
	request.open("GET", "getTransferLocation?input="
			+ JSON.stringify(transferRecord), true);
	request.send(null);
}

function saveTransferStockList() {
	var fromLocation = document.getElementById("transfer-from-location").innerHTML
			.trim();
	var type=document.getElementById("location_type").innerHTML;
	var toLocation = document.getElementById("transfer-to-location").innerHTML
			.trim();
	var date = document.getElementById("transfer-date").innerHTML.trim();
	var request = new XMLHttpRequest;
	var table = document.getElementById("transfer-stock-table");
	var rowCount = table.rows.length;
	var stockList = {};
	var extraStockList = {};
	var transferRecord = {};
	var extraBoIdList = null;
	var priceList = {};
	var packagingTypeList = {};
	var stockTypeList = {};
	var boIdList = document.getElementsByName("transfer-product-boId");
	var stockTypes = document.getElementsByName("transfer_stockType");
	var  availableQtyList= document.getElementsByName("transfer-available-quantity");
	if (document.getElementById("transfer_code_check").innerHTML == 'true')
		extraBoIdList = document.getElementsByName("transfer-to-product-boId");
	var qtyList = document.getElementsByName("transfer-product-qty");
	if ($(document.getElementsByName("transfer-product-price")).length != 0) {
		var pList = document.getElementsByName("transfer-product-price");
		for (var i = 1; i < boIdList.length; i++) {
			var value = parseInt(qtyList[i].value.trim() ? qtyList[i].value
					.trim() : 0);
			if (value != 0) {
				if ((boIdList[i].value.trim() in stockList)) {
					stockList[boIdList[i].value.trim()] += value;

				} else {
					stockList[boIdList[i].value.trim()] = value;
				}
				if (document.getElementById("transfer_code_check").innerHTML == 'true')
					extraStockList[boIdList[i].value.trim()] = extraBoIdList[i].value
							.trim();
				priceList[i] = pList[i].value;
			}
			// productList[boIdList[i].value] = qtyList[i].value;
		}
		transferRecord['priceList'] = priceList;
		if (document.getElementById("transfer_code_check").innerHTML == 'true')
			transferRecord['extra'] = extraStockList;
		transferRecord['stockList'] = stockList;
	} else {
		var packageTypeList = document.getElementsByName("transferPackageType");
		for (var i = 1; i < boIdList.length; i++) {
			var availableQty=$(availableQtyList[i]).text();
			if(availableQty<=0 && $("#edit_TransferBoId").length <=0){
            alert("Check product avaliable quantity at inventory. It must be greater than 0");
            return;
			}
			var value = parseInt(qtyList[i].value.trim() ? qtyList[i].value
					.trim() : 0);
			var packAndBoId = boIdList[i].value.trim() + ','
					+ packageTypeList[i].value + ',' + stockTypes[i].value;
			if (value != 0) {
				if (packAndBoId in packagingTypeList) {
					packagingTypeList[packAndBoId] += value;
				} else {
					packagingTypeList[packAndBoId] = value;
				}
			}
		}
		transferRecord['packagingTypeList'] = packagingTypeList;
	}
	if ($("#edit_TransferBoId").length > 0) {
		transferRecord['transfer_editTransferBoId'] = $("#edit_TransferBoId")
				.text();
	}
	transferRecord['transfer-date'] = date;
	transferRecord['from-location'] = fromLocation;
	transferRecord['to-location'] = toLocation;
	transferRecord['transferInvoice'] = document
			.getElementById("transfer-invoice").innerHTML;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				var result = document.createElement("div");
				result.innerHTML = request.responseText;
				result = $(result).children("span").text();
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				$('#workspace').removeClass('myFilter');
				if ($("#edit_TransferBoId").length > 0) {
					this.id = fromLocation;
					search(this, 'TRANSFER_TO_DATE', 'TRANSFER');
				} else {
                    loadAction(document.getElementById("locationmenu"),
                        'location');
                    document.getElementById("search-text").value = document.getElementById("from-location").innerHTML;                    search(this, 'NAME', 'LOCATION');
                    search(this,'NAME','LOCATION');
					this.id = fromLocation;
                    aboutDetail(this, 'LOCATION');
				}
			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	console.log("transferRecord ", transferRecord);
	loading();
	request.open("GET", "saveTransferStockList?input="
		+ JSON.stringify(transferRecord), true);
	request.send(null);
}

function savePayment(force) {
	var customerId = document.getElementById("payment_customerId").innerHTML;
	var locationType=document.getElementById("payment_location_type").innerHTML;
	var force;
	var request = new XMLHttpRequest;
	var parameter = {};
	parameter["locationId"] = document.getElementById("paymet_location_id").innerHTML;
	parameter["customerId"] = document.getElementById("payment_customerId").innerHTML;
	parameter["payment"] = document.getElementById("payment_amount").value
			.trim() ? document.getElementById("payment_amount").value.trim()
			: "0";
	parameter["discount"] = document.getElementById("discount_amount").value
			.trim() ? document.getElementById("discount_amount").value.trim()
			: "0";
	parameter["remark"] = document.getElementById("payment_remark").value;
	parameter["paymentDate"] = document.getElementById("payment_paymentDate").value;
	parameter["payInvoice"] = document.getElementById("payInvoice").value;
	parameter["luckyDrawAmount"] = $('#luckyDrawAmount').val().trim() ? $(
			'#luckyDrawAmount').val().trim() : "0";
    parameter["exchangeRate"] = $('#exchangeRateInputBox').val().trim() ? $(
            '#exchangeRateInputBox').val().trim() : "0";
    parameter["currencyType"] = document.getElementById("currencyType").innerHTML.trim() ? document.getElementById("currencyType").innerHTML.trim() : "";
	var name=document.getElementById("payment_customername").innerHTML;
	if (document.getElementById("payment-Cash").checked == true) {
		parameter["paymentType"] = document.getElementById("payment-Cash").value;
	} else {
		parameter["paymentType"] = document.getElementById("payment-Bank").value;
	}
	parameter["purchaseOrderId"] = document.getElementById("payment_boId").innerHTML;
	if (force) {
		parameter["force"] = force;
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
						savePayment("forced");
					} else {
						$('#salesInvoice').focus();
					}

					return;
				}
				var print = document.getElementById("isPrint").innerHTML;
				console.log("Print is", print);
				if (print !== "false") {
					printingPayment();
				} else {
					alert("Your payment is received!!!");
					document.getElementById("dialog-temp").attributes["class"].value = "hide";
				}
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				var startDate = $('#customerStartDate').val();
				var endDate = $('#customerEndDate').val();
				if(locationType=="SUPPLIER"){
					loadAction(document.getElementById("suppliermenu"), 'supplier');
					document.getElementById("search-text").value = name;
					search(this, 'NAME', 'SUPPLIER');
					this.id = customerId;
					aboutDetail(this, 'SUPPLIER', startDate, endDate);
				}else{
					loadAction(document.getElementById("customermenu"), 'customer');
					document.getElementById("search-text").value = name;
					search(this, 'NAME', 'CUSTOMER');
					this.id = customerId;
					aboutDetail(this, 'CUSTOMER', startDate, endDate);
				}
			} else {
				alert("Load payment form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	loading();
	request.open("GET", "savePayment?input=" + JSON.stringify(parameter), true);
	request.send(null);
}

function openTotalCreditForm() {
	var customerId = document.getElementById("detail-customer-boId").innerHTML;
	var locationId = document.getElementById("detail-customer-location-id").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
                document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("totalCreditPayDate").valueAsDate =new Date();
                document.getElementById("totalCreditBalance").innerHTML = document.getElementById("totalBalanceForMMK").innerHTML + " (MMK)";
				/*$('#totalCreditPayDate').datepicker({
					dateFormat : 'dd/mm/yy'
				});*/

			} else {
				alert("Load payment form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	loading();
	request.open("GET", "totalCreditPaymentForm", true);
	request.send(null);
}

function saveTotalCreditForm() {
	var customerId = $('#detail-customer-boId').text();
	var date = $('#totalCreditPayDate').val();
	var inputAmount = $('#totalCreditPayAmount').val();
	var amount = inputAmount ? inputAmount : 0;
	var description = $('#totalCreditPayDescription').val();
    var currencyType = $("input[name='currencyType']:checked").val();
    var locationType=document.getElementById("edit_payment_location_type").innerHTML;
    var customerName=document.getElementById("edit_payment_customer_name").innerHTML;

	if (date == "") {
		window.alert("Please fill date!");
		return;
	}

	if (amount <= 0) {
		window.alert("Amount must be greater than zero");
		return;
	}

	var parameter = {};
	parameter["customerId"] = customerId;
	parameter["date"] = date;
	parameter["amount"] = amount;
	parameter["description"] = description;
	parameter["currencyType"] = currencyType;

	var totalBalanceForMMK = $('#totalBalanceForMMK').text();
    if (parseInt(amount) > parseInt(totalBalanceForMMK)) {
		window.alert("Please check Payment!!!");
		return;
	}

	$.get("saveTotalCreditForm?input=" + JSON.stringify(parameter))
			.then(
					function() {
						document.getElementById("dialog-temp").attributes["class"].value = "hide";
						var startDate = $('#customerStartDate').val();
						var endDate = $('#customerEndDate').val();
                        if(locationType=="SUPPLIER"){
                            loadAction(document.getElementById("suppliermenu"), 'supplier');
                            document.getElementById("search-text").value = customerName;
                            search(this, 'NAME', 'SUPPLIER');
                            this.id = customerId;
                            aboutDetail(this, 'SUPPLIER',startDate, endDate);
                        }else{
                            loadAction(document.getElementById("customermenu"), 'customer');
                            document.getElementById("search-text").value = customerName;
                            search(this, 'NAME', 'CUSTOMER');
                            this.id = customerId;
                            aboutDetail(this, 'CUSTOMER',startDate, endDate);
                        }
						// loadAction(document.getElementById("customermenu"),
						// 		'customer');
						// document.getElementById("search-text").value = customerId;
						// search(this, 'ID', 'CUSTOMER');
						// this.id = customerId;
						// aboutDetail(this, 'CUSTOMER', startDate, endDate);
					});
}

function OnDropDownChange(event) {
	var index = event.selectedIndex;
	document.getElementById("daily-sale-price").innerHTML = document
			.getElementById("daily-sale-priceList").options[index].value;
	document.getElementById("daily-sale-stock").innerHTML = document
			.getElementById("daily-sale-quantityList").options[index].value;

}

function loadRouteForm() {
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("route-location").innerHTML = document
						.getElementById("detail-location-name").innerHTML;
				document.getElementById("route-from-date").value = today();
				document.getElementById("route-to-date").value = today();
			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	loading();
	request.open("GET", "createRouteForm?input=" + JSON.stringify(parameter),
			true);
	request.send(null);
}

function moveSelected(from, to) {
	$('#' + from + ' option:selected').remove().appendTo('#' + to);
}

function moveSelectedPackageType(from, to, event) {
	element = $(event.target);
	var tr = getParentByTagName(element, "tr");
	var fromElement = $("[name=" + from + "]", tr);
	var toElement = $("[name=" + to + "]", tr);
	var qty = $("[name=transfer-product-qty]", tr);
	var transferElement = $("option:selected", fromElement);
	transferElement.text(transferElement.text() + "," + qty.val());
	$("[name=transfer-product-qty]", tr).val("");
	transferElement.remove().appendTo(toElement);
}

function moveSelectedToPackageType(from, to, event) {
	element = $(event.target);
	var tr = getParentByTagName(element, "tr");
	var fromElement = $("[name=" + from + "]", tr);
	var toElement = $("[name=" + to + "]", tr);
	var transferElement = $("option:selected", fromElement);
	var qtyString = transferElement.text();
	var qty = qtyString.split(",");
	$("[name=transfer-product-qty]", tr).val(qty[1]);
	transferElement.text(qty[0]);
	transferElement.remove().appendTo(toElement);
}

function moveSelectedNewLocationPackageType(from, to, event) {
	element = $(event.target);
	var tr = getParentByTagName(element, "tr");
	var fromElement = $("[name=" + from + "]", tr);
	var toElement = $("[name=" + to + "]", tr);
	var qty = $("[name=add_location_product_qty]", tr);
	var transferElement = $("option:selected", fromElement);
	transferElement.text(transferElement.text() + "," + qty.val());
	$("[name=add_location_product_qty]", tr).val("");
	transferElement.remove().appendTo(toElement);
}

function moveSelectedNewLocationToPackageType(from, to, event) {
	element = $(event.target);
	var tr = getParentByTagName(element, "tr");
	var fromElement = $("[name=" + from + "]", tr);
	var toElement = $("[name=" + to + "]", tr);
	var transferElement = $("option:selected", fromElement);
	var qtyString = transferElement.text();
	var qty = qtyString.split(",");
	$("[name=add_location_product_qty]", tr).val(qty[1]);
	transferElement.text(qty[0]);
	transferElement.remove().appendTo(toElement);
}

function getLocationStockList(description) {
	var table = document.getElementById("route-stock-table");
	var rowCount = table.rows.length;
	if (rowCount > 1) {
		for (var i = 1; i < rowCount; i++) {
			var pcode = table.rows[i].cells[0].childNodes[0].innerHTML;
			if (pcode == document.getElementById("route-loc-stock-code").value) {
				alert("Already inserted in route stock list!");
				document.getElementById("route-quantity").disabled = true;
				document.getElementById("route-button").disabled = true;
				// document.getElementById("route-dialog-button").disabled =
				// true;

			} else {
				var index = document.getElementById("route-loc-stock-code").selectedIndex;
				document.getElementById("route-loc-stock-name").value = document
						.getElementById("route-loc-stock-name").options[index].value;
				document.getElementById("route-loc-stock-qty").value = document
						.getElementById("route-loc-stock-qty").options[index].value;
				document.getElementById("route-quantity").max = document
						.getElementById("route-loc-stock-qty").value;
				document.getElementById("route-quantity").value = "";
				document.getElementById("route-quantity").disabled = false;
				document.getElementById("route-button").disabled = true;
			}
		}
	} else {
		var index = document.getElementById("route-loc-stock-code").selectedIndex;
		document.getElementById("route-loc-stock-name").value = document
				.getElementById("route-loc-stock-name").options[index].value;
		document.getElementById("route-loc-stock-qty").value = document
				.getElementById("route-loc-stock-qty").options[index].value;
		document.getElementById("route-quantity").max = document
				.getElementById("route-loc-stock-qty").value;
		document.getElementById("route-quantity").value = "";
		document.getElementById("route-quantity").disabled = false;
		document.getElementById("route-button").disabled = true;
	}
	/*
	 * var index =
	 * document.getElementById("route-loc-stock-code").selectedIndex;
	 * document.getElementById("route-loc-stock-name").value =
	 * document.getElementById("route-loc-stock-name").options[index].value;
	 * document.getElementById("route-loc-stock-qty").value =
	 * document.getElementById("route-loc-stock-qty").options[index].value;
	 * document.getElementById("transfer-quantity").max=
	 * document.getElementById("route-loc-stock-qty").value;
	 */
}

function transferRouteStock() {
	var table = document.getElementById("route-stock-table");
	var rowCount = table.rows.length;
	if (rowCount > 1) {
		for (var i = 1; i < rowCount; i++) {
			var pcode = table.rows[i].cells[0].childNodes[0].innerHTML;
			if (pcode == document.getElementById("route-loc-stock-code").value) {
				alert("Already inserted in stock list!");
				return;
			}
		}
	}
	var colCount = table.rows[0].cells.length;
	var row = table.insertRow(rowCount);
	for (var i = 0; i < 4; i++) {
		var newcell = row.insertCell(i);
		newcell.innerHTML = table.rows[0].cells[i].innerHTML;
		switch (i) {
		case 0:
			newcell.childNodes[0].innerHTML = document
					.getElementById("route-loc-stock-code").value;
			break;
		case 1:
			newcell.childNodes[0].innerHTML = document
					.getElementById("route-loc-stock-name").value;
			break;
		case 2:
			newcell.childNodes[0].innerHTML = document
					.getElementById("route-quantity").value;
			break;
		case 3:
			var element1 = document.createElement("input");
			element1.setAttribute('type', 'button');
			element1.setAttribute('id', rowCount);
			element1.setAttribute('value', 'Edit');
			element1.setAttribute('onclick', 'editRouteRow(this)');
			newcell.appendChild(element1);
			var element2 = document.createElement("input");
			element2.setAttribute('type', 'button');
			element2.setAttribute('id', rowCount);
			element2.setAttribute('value', 'Delete');
			element2.setAttribute('onclick', 'deleteRouteRow(this)');
			newcell.appendChild(element2);
		}
	}
	document.getElementById("route-dialog-button").disabled = false;

}

function editRouteRow(editRow) {
	var table = document.getElementById("route-stock-table");
	document.getElementById("route-loc-stock-code").value = table.rows[editRow.id].cells[0].childNodes[0].innerHTML;
	document.getElementById("route-loc-stock-name").value = table.rows[editRow.id].cells[1].childNodes[0].innerHTML;
	document.getElementById("route-quantity").value = table.rows[editRow.id].cells[2].childNodes[0].innerHTML;
	document.getElementById("route-button").value = "Update";
	table.rows[editRow.id].cells[3].childNodes[0].disabled = true;
	table.rows[editRow.id].cells[3].childNodes[1].disabled = true;
	document.getElementById("route-button").onclick = function() {
		table.rows[editRow.id].cells[0].childNodes[0].innerHTML = document
				.getElementById("route-loc-stock-code").value;
		table.rows[editRow.id].cells[1].childNodes[0].innerHTML = document
				.getElementById("route-loc-stock-name").value;
		table.rows[editRow.id].cells[2].childNodes[0].innerHTML = document
				.getElementById("route-quantity").value;
		table.rows[editRow.id].cells[3].childNodes[1].disabled = false;
		table.rows[editRow.id].cells[3].childNodes[0].disabled = false;
		document.getElementById("route-button").value = "Add";
		document.getElementById("route-button").setAttribute('onclick',
				'transferRouteStock()');
	}
}

function checkRouteQuantity(description) {
	var value = document.getElementById("route-quantity").value.trim();
	value = Number(value);
	if (isNaN(value)) {
		return;
	}
	if (description == 'stock') {
		var maxValue = document.getElementById("route-loc-stock-qty").value;
		if (value > 0 && value <= maxValue) {
			console.log(maxValue + "success" + value);
			document.getElementById("route-button").disabled = false;
		} else {
			console.log(maxValue + "fail" + value);
			document.getElementById("route-button").disabled = true;
		}
	} else {
	}
}

function getFieldTripAllowance() {
	var index = document.getElementById("route-fieldTrip-allowance").selectedIndex;
	document.getElementById("routecashallowance").value = document
			.getElementById("route-fieldTrip-cashAllowance").options[index].value;
	document.getElementById("routequota").value = document
			.getElementById("route-fieldTrip-saleQuota").options[index].value;
	document.getElementById("routecashDownSaleComm").value = document
			.getElementById("route-fieldTrip-cashDownSaleCommission").options[index].value;
	document.getElementById("routecreditSaleComm").value = document
			.getElementById("route-fieldTrip-creditSaleCommission").options[index].value;
	document.getElementById("routeShortFallFine").value = document
			.getElementById("route-fieldTrip-shortFallFine").options[index].value;
	document.getElementById("routeReturnFine").value = document
			.getElementById("route-fieldTrip-returnFine").options[index].value;
	document.getElementById("routeDiscountCashDownSaleComm").value = document
			.getElementById("route-fieldTrip-discountCashDownSaleCommission").options[index].value;
	document.getElementById("routeDiscountCreditSaleComm").value = document
			.getElementById("route-fieldTrip-discountCreditSaleCommission").options[index].value;
}
/*
 * function saveTransferStockList(force) { var fromLocation =
 * document.getElementById("detail-location-boId").innerHTML .trim(); var
 * toLocation = document.getElementById("to-location").value; var date =
 * document.getElementById("transfer-date").value.trim(); var request = new
 * XMLHttpRequest; var table = document.getElementById("transfer-stock-table");
 * var rowCount = table.rows.length; var stockList = {}; var transferRecord =
 * {};
 * 
 * 
 * for (var i = 1; i < rowCount - 1; i++) { var pcode =
 * table.rows[i].cells[0].childNodes[0].innerHTML; var price =
 * table.rows[i].cells[2].childNodes[0].innerHTML; var qty =
 * table.rows[i].cells[3].childNodes[0].innerHTML; stockList += pcode + ":" +
 * price + ":" + qty + ";"; }
 * 
 * var boIdList = document.getElementsByName("transfer-product-boId"); var
 * qtyList = document.getElementsByName("transfer-product-qty"); for (var i = 1;
 * i < boIdList.length; i++) { var value = parseInt(qtyList[i].value.trim() ?
 * qtyList[i].value.trim() : 0); if (value != 0) { if ((boIdList[i].value.trim()
 * in stockList)) { stockList[boIdList[i].value.trim()] += value; } else {
 * stockList[boIdList[i].value.trim()] = value; } } //
 * productList[boIdList[i].value] = qtyList[i].value; }
 * 
 * transferRecord['transfer-date'] = date; transferRecord['from-location'] =
 * fromLocation; transferRecord['to-location'] = toLocation;
 * transferRecord['stockList'] = stockList; transferRecord['transferInvoice'] =
 * document.getElementById("salesInvoice").value; if (force) {
 * transferRecord['force'] = force; } console.log(transferRecord);
 * request.onreadystatechange = function() { if (request.readyState == 4) { if
 * (request.status == 200) { hideLoading(); var result =
 * document.createElement("div"); result.innerHTML = request.responseText;
 * result = $(result).children("span").text(); if (result == "ERROR") { var r =
 * confirm("This InvoiceNo. is already exist.If you want to save, click 'OK'.");
 * if (r == true) { saveTransferStockList("forced"); } else {
 * $('#salesInvoice').focus(); } return; }
 * document.getElementById("dialog-temp").attributes["class"].value = "hide";
 * loadAction(document.getElementById("locationmenu"), 'location');
 * document.getElementById("search-text").value = fromLocation; search(this,
 * 'ID', 'LOCATION'); this.id = fromLocation; aboutDetail(this, 'LOCATION'); }
 * else { alert("Load transfer form error.Please try again. Error code is " +
 * request.status); } } }; loading(); request.open("GET",
 * "saveTransferStockList?input=" + JSON.stringify(transferRecord), true);
 * request.send(null); }
 */
function saveRouteStockList(force) {
	var fromLocation = document.getElementById("detail-location-boId").innerHTML;
	var request = new XMLHttpRequest;
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
						saveRouteStockList("forced");
					} else {
						$('#routeInvoice').focus();
					}
					return;
				}
				alert("Successfully Recorded!");
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				loadAction(document.getElementById("locationmenu"), 'location');
				document.getElementById("search-text").value = fromLocation;
				search(this, 'ID', 'LOCATION');
				this.id = fromLocation;
				aboutDetail(this, 'LOCATION', '', '', true);
			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var table = document.getElementById("route-stock-table");
	var rowCount = table.rows.length;
	var productBoIdList = [];
	var quantityList = [];
	var packageIdList = [];
	var priceList = [];
	var routeRecord = {};
	var boIdList = document.getElementsByName("route-product-boId");
	var qtyList = document.getElementsByName("route-product-qty");
	var packageIds = document.getElementsByName("route-product-packaging-gram");
	var prices = document.getElementsByName("route-product-price");
	for (var i = 1; i < boIdList.length; i++) {
		var value = parseInt(qtyList[i].value.trim() ? qtyList[i].value.trim()
				: 0);
		if (value != 0) {
			productBoIdList.push(boIdList[i].value.trim());
			quantityList.push(qtyList[i].value.trim());
			packageIdList.push(packageIds[i].value.trim());
			priceList.push(prices[i].value.trim());
		}
	}
	routeRecord['location'] = fromLocation;
	routeRecord['routeName'] = document.getElementById("route-name").value;
	routeRecord['township'] = document.getElementById("townShip").value;
	routeRecord['vehicleName'] = document.getElementById("vehicleName").value;
	routeRecord['channelOfDistribution'] = document
			.getElementById("route_channelOfDistribution").value;
	routeRecord['mealAllowance'] = document.getElementById("mealAllowance").value ? document
			.getElementById("mealAllowance").value
			: "0";
	routeRecord['fuelCharges'] = document.getElementById("fuelCharges").value ? document
			.getElementById("fuelCharges").value
			: "0";
	routeRecord['roadAndTollgateFees'] = document
			.getElementById("roadAndTollgateFees").value ? document
			.getElementById("roadAndTollgateFees").value : "0";
	routeRecord['labourCharges'] = document.getElementById("labourCharges").value ? document
			.getElementById("labourCharges").value
			: "0";
	routeRecord['transportCharges'] = document
			.getElementById("transportCharges").value ? document
			.getElementById("transportCharges").value : "0";
	routeRecord['otherCharges'] = document.getElementById("otherCharges").value ? document
			.getElementById("otherCharges").value
			: "0";
	routeRecord['from-date'] = document.getElementById("route-from-date").value;
	routeRecord['to-date'] = document.getElementById("route-to-date").value;
	var x = document.getElementById('route-to-employee-list');
	var employeeList = "";
	for (var i = 0; i < x.length; i++) {
		employeeList += x[i].value + ":";
	}
	routeRecord['employeeList'] = employeeList;
	if (force) {
		routeRecord['force'] = force;
	}
	routeRecord['productBoIdList'] = productBoIdList;
	routeRecord['quantityList'] = quantityList;
	routeRecord['packageIdList'] = packageIdList;
	routeRecord['priceList'] = priceList;
	console.log(routeRecord);
	loading();
	request.open("GET", "saveRouteStockList?input="
			+ JSON.stringify(routeRecord), true);
	request.send(null);
}

function editSaveRouteStockList(force) {
	var fromLocation = document.getElementById("route-location-boId").innerHTML;
	var fieldTripRoutBoId = document.getElementById("field-trip-route-boId").innerHTML;
	var request = new XMLHttpRequest;
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
						editSaveRouteStockList("forced");
					} else {
						hideLoading();
						$('#routeInvoice').focus();
					}
					return;
				}
				if (result == "CHECKDATE") {
					alert("Check your date, end date is beyond start date");
					return;
				}
				alert("Route is successfully edited......");
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				// loadAction(document.getElementById("locationmenu"), 'location');
				// document.getElementById("search-text").value = fromLocation;
				// search(this, 'ID', 'LOCATION');
				// this.id = fromLocation;
				// aboutDetail(this, 'LOCATION');
			} else {
				alert("Load route form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var table = document.getElementById("route-stock-table");
	var rowCount = table.rows.length;
	var productBoIdList = [];
	var quantityList = [];
	var packageIdList = [];
	var priceList = [];
	var routeRecord = {};
	var boIdList = document.getElementsByName("route-product-boId");
	var qtyList = document.getElementsByName("route-product-qty");
	var packageIds = document.getElementsByName("route-product-packaging-gram");
	var prices = document.getElementsByName("route-product-price");
	for (var i = 1; i < boIdList.length; i++) {
		var value = parseInt(qtyList[i].value.trim() ? qtyList[i].value.trim()
				: 0);
		if (value != 0) {
			productBoIdList.push(boIdList[i].value.trim());
			quantityList.push(qtyList[i].value.trim());
			packageIdList.push(packageIds[i].value.trim());
			priceList.push(prices[i].value.trim());
		}
	}
	routeRecord['location'] = fromLocation;
	routeRecord['routeName'] = document.getElementById("route-name").value;
	routeRecord['township'] = document.getElementById("townShip").value;
	routeRecord['vehicleName'] = document.getElementById("vehicleName").value;
	routeRecord['channelOfDistribution'] = document
			.getElementById("route_channelOfDistribution").value;
	routeRecord['mealAllowance'] = document.getElementById("mealAllowance").value ? document
			.getElementById("mealAllowance").value
			: "0";
	routeRecord['fuelCharges'] = document.getElementById("fuelCharges").value ? document
			.getElementById("fuelCharges").value
			: "0";
	routeRecord['roadAndTollgateFees'] = document
			.getElementById("roadAndTollgateFees").value ? document
			.getElementById("roadAndTollgateFees").value : "0";
	routeRecord['labourCharges'] = document.getElementById("labourCharges").value ? document
			.getElementById("labourCharges").value
			: "0";
	routeRecord['transportCharges'] = document
			.getElementById("transportCharges").value ? document
			.getElementById("transportCharges").value : "0";
	routeRecord['otherCharges'] = document.getElementById("otherCharges").value ? document
			.getElementById("otherCharges").value
			: "0";
	routeRecord['from-date'] = document.getElementById("route-from-date-new").value;
	routeRecord['to-date'] = document.getElementById("route-to-date-new").value;
	var x = document.getElementById('route-to-employee-list');
	var employeeList = "";
	for (var i = 0; i < x.length; i++) {
		employeeList += x[i].value + ":";
	}
	routeRecord['employeeList'] = employeeList;
	if (force) {
		routeRecord['force'] = force;
	}
	routeRecord['productBoIdList'] = productBoIdList;
	routeRecord['quantityList'] = quantityList;
	routeRecord['packageIdList'] = packageIdList;
	routeRecord['priceList'] = priceList;
	routeRecord['routeBoId'] = fieldTripRoutBoId;
	loading();
	console.log(routeRecord);
	request.open("GET", "editSaveRouteStockList?input="
		+ JSON.stringify(routeRecord), true);
	request.send(null);
}

function openReturnForm(element, locationBoId) {
	var customerId = document.getElementById("detail-customer-boId").innerHTML;
	var locationId = document.getElementById("detail-customer-location-id").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("return-Date").value = today();
				var locationIdAndName=document.getElementById("detail-customer-location-id").innerHTML+','+document.getElementById("detail-customer-location").innerHTML;
				document.getElementById("return-location-id").innerHTML =locationIdAndName;
				document.getElementById("return_customerId").innerHTML = document
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
				document.getElementById("return_customername").innerHTML = fullName;
				document.getElementById("return_invoice_no").innerHTML = document
						.getElementById(element.id + 'invoice').innerHTML;
				/*document.getElementById("returnInvoice").value = document
						.getElementById("return_invoice_no").innerHTML
						+ '-' + document.getElementById("returnInvoice").value;*/
				document.getElementById("return_boId").innerHTML = document
						.getElementById(element.id + 'boId').innerHTML;
				document.getElementById("totalCredit").innerHTML = document
						.getElementById("totalBalance").innerHTML;
			} else {
				alert("Load payment form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["customerId"] = customerId;
	parameter["purchaseOrdeId"] = element.id;
	parameter["locationId"] = locationId;
	parameter["locationBoId"] = locationBoId;
	loading();
	request.open("GET", "createCustomerReturnForm?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

function openCancelPurchaseOrderForm(element) {
	document.getElementById("confirm-text").innerHTML = "Are you sure you want to Cancel?";
	document.getElementById("confirm").attributes["class"].value = "dialog";
	document.getElementById("confirm-delete").attributes["onclick"].value = "cancelPurchaseOrderConfirm('"
			+ element.id + "');";
}

function cancelPurchaseOrderConfirm(purchaseOrderBoId) {
	var customerId = document.getElementById("detail-customer-boId").innerHTML;
	var locationId = document.getElementById("detail-customer-location-id").innerHTML;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("confirm").attributes["class"].value = "hide";
				loadAction(document.getElementById("customermenu"), 'customer');
				document.getElementById("search-text").value = customerId;
				search(this, 'ID', 'CUSTOMER');
				this.id = customerId;
				aboutDetail(this, 'CUSTOMER');
			} else {
				alert("Delete error return :" + request.status);
			}
		}
	};
	var parameter = {};
	parameter["boId"] = purchaseOrderBoId;
	parameter["locationId"] = locationId;
	console.log(parameter);
	request.open("DELETE", "cancelPurchaseOrder?input="
			+ JSON.stringify(parameter), true);
	request.send();
	loading();
}

function getProductInformation(event, row) {
	if (event.which == 13 || event.keyCode == 13 || event.which == 9
			|| event.keyCode == 9) {
		var productName = document
				.getElementById("add_location_product_name_lst");
		var productPrice = document
				.getElementById("add_location_product_price_lst");
		var productPackagingType = document
				.getElementById("newLocation-product-packageType");
		var index = -1;
		var d = row.parentNode.parentNode.rowIndex - 1;
		for (var i = 0; i < add_location_productList.options.length; i++) {
			if (add_location_productList.options[i].value == document
					.getElementsByName("add-location-product-boId")[d].value) {
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
		var name = productName.options[index].value;
		var price = productPrice.options[index].value;
		var packageType = productPackagingType[index].value;
		var packageTypeString = packageType.split(";");
		if ($(document.getElementsByName("newLocationPackageType").length != 0)) {
			$(document.getElementsByName("newLocationPackageType")[d]).html("");
			for (var t = 0; t < packageTypeString.length; t++) {
				var temp = packageTypeString[t];
				if (temp.trim() == "")
					continue;
				var tempPackage = temp.split(",");
				var option = $(document.createElement("option"));
				var select = $(document
						.getElementsByName("newLocationPackageType")[d]);
				option.text(tempPackage[1]);
				option.attr("value", tempPackage[0]);
				select.append(option);
			}
			// $(document.getElementsByName("newLocationPackageType")[d]).prop(
			// "required", "required");
		} else {
			document.getElementsByName("add_location_product_price")[d].innerHTML = price;
		}
		document.getElementsByName("add_location_product_name")[d].innerHTML = name;
		document.getElementsByName("add_location_product_qty")[d].focus();
		$(row).removeClass("required");
		var id = row.parentNode.parentNode.id;
		if (errors[id])
			delete errors[id];
		return false;
	}
}

function getTransferToProductInformation(event, row) {
	if (event.which == 13 || event.keyCode == 13 || event.which == 9
			|| event.keyCode == 9) {

		var boIdList = document.getElementById("transfer_to_extra_productList");
		var index = -1;
		var d = row.parentNode.parentNode.rowIndex - 1;
		for (var i = 0; i < transfer_to_productList.options.length; i++) {
			if (transfer_to_productList.options[i].value == document
					.getElementsByName("transfer-to-product-extra-boId")[d].value) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			alert("Please choose correct Product Code!");
			$(row).addClass("required");
			var id = row.parentNode.parentNode.id;
			errors[id] = "error";
			document.getElementById("transfer-dialog-button").disabled = true;
			return false;
		}
		document.getElementById("transfer-dialog-button").disabled = false;
		var boId = boIdList.options[index].value;

		document.getElementsByName("transfer-to-product-boId")[d].value = boId;
		document.getElementsByName("transfer-product-qty")[d].focus();

		$(row).removeClass("required");
		var id = row.parentNode.parentNode.id;
		if (errors[id])
			delete errors[id];
		return false;
	}

}

function getTransferProductInformation(event, row) {
	if (event.which == 13 || event.keyCode == 13 || event.which == 9
			|| event.keyCode == 9) {
		var boIdList = document.getElementById("transfer_extra_productList");
		var productNameList = document
				.getElementById("transfer-product-name-lst");
		var brandNameList = document.getElementById("transfer-brand-name-lst");
		var flavourList = document.getElementById("transfer-flavour-lst");
		var packageTypeList = document
				.getElementById("transfer-product-packageType");
		var productPrice = document
				.getElementById("transfer-product-price-lst");

		var index = -1;
		var d = row.parentNode.parentNode.rowIndex - 1;
		for (var i = 0; i < transfer_productList.options.length; i++) {
			if (transfer_productList.options[i].value == document
					.getElementsByName("transfer-product-extra-boId")[d].value) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			alert("Please choose correct Product Code!");
			$(row).addClass("required");
			var id = row.parentNode.parentNode.id;
			errors[id] = "error";
			document.getElementById("transfer-dialog-button").disabled = true;
			return false;
		}
		document.getElementById("transfer-dialog-button").disabled = false;
		var boId = boIdList.options[index].value;
		var name = productNameList.options[index].value;
		var brandName = brandNameList.options[index].value;
		var flavour = flavourList.options[index].value;
		var price = productPrice.options[index].value;
		var packageType = packageTypeList.options[index].value;
		var packageTypeString = packageType.split(";");
        var packagingType =""; // Example PACK ,BOX , BOTTLE
		if ($(document.getElementsByName("transferPackageType").length != 0)) {
			$(document.getElementsByName("transferPackageType")[d]).html("");
			for (var t = 0; t < packageTypeString.length; t++) {
				var temp = packageTypeString[t];
				if (temp.trim() == "")
					continue;
				var tempPackage = temp.split(",");
				var option = $(document.createElement("option"));
				var select = $(document
						.getElementsByName("transferPackageType")[d]);
				option.text(tempPackage[1] + "," + tempPackage[5]);
				if(t==0){
                    packagingType = tempPackage[1];
				}
				option.attr("value", tempPackage[0]);
				option.attr("onclick","getAvailableQuantity(this)");
				option.attr("name", boId + ":" + tempPackage[1] + ":FRESH");
				select.append(option);

			}
		} else {
			document.getElementsByName("transfer-product-price")[d].innerHTML = price;
		}
		document.getElementsByName("transfer-product-boId")[d].value = boId;
		document.getElementsByName("transfer-product-name")[d].innerHTML = name;
		document.getElementsByName("transfer-brand-name")[d].innerHTML = brandName;
		document.getElementsByName("transfer-flavour")[d].innerHTML = flavour;

		// For Avaliable Quantity
		var availableQuantity = $(document.getElementById(boId + ":" + packagingType + ":FRESH")).text();
		if(availableQuantity.length<=0){
			alert("Can't this product to transfer cause its do't exist at inventory!");
			document.getElementsByName("transfer-product-extra-boId")[d].focus();
			return;
		}
        document.getElementsByName("transfer-available-quantity")[d].innerHTML = availableQuantity;
        
		if (document.getElementById("transfer_code_check").innerHTML == 'true')
			document.getElementsByName("transfer-to-product-extra-boId")[d]
					.focus();
		else
			document.getElementsByName("transfer-product-qty")[d].focus();

        $(row).removeClass("required");
        var id = row.parentNode.parentNode.id;
		if (errors[id])
            delete errors[id];
            return false;
	}
	return true;
}

// used for Transfer's available quantity
function getAvailableQuantity(element){
	var packagingType = element.getAttribute("name"); //get element's name attribute
	var quantityElement = document.getElementById(packagingType); // get product's quantity element in location
	var avaliableQuantityNode = element.parentNode.parentNode.nextElementSibling; // get next td (that is Available Location)
	if(quantityElement !== null){	// to check if product's quantity element is not defined
        var availableQuantity = quantityElement.innerHTML;
        avaliableQuantityNode.innerHTML = availableQuantity;
	} else{
		avaliableQuantityNode.innerHTML = "0";
		alert("Can't this product to transfer cause its do't exist at inventory!");
		return;
	}
}

function getRouteProductInformation(event, row) {
	if (event.which == 13 || event.keyCode == 13 || event.which == 9
			|| event.keyCode == 9) {
		var productName = document.getElementById("route-product-name-lst");
		var productBrandName = document
				.getElementById("route-product-brand-name-lst");
		var productFlavour = document
				.getElementById("route-product-flavour-lst");
		var productPackageType = document
				.getElementById("route-product-packageType-gram-lst");
		var boIdList = document.getElementById("route_extra_productList");
		var index = -1;
		var d = row.parentNode.parentNode.rowIndex - 1;
		for (var i = 0; i < route_productList.options.length; i++) {
			if (route_productList.options[i].value == document
					.getElementsByName("route-product-extra-boId")[d].value) {
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
		var boId = boIdList.options[index].value;
		var name = productName.options[index].value;
		var brandName = productBrandName.options[index].value;
		var flavour = productFlavour.options[index].value;
		var typeAndGram = productPackageType.options[index].value;
		var typeAndGramSplit = typeAndGram.split(";");
		var packageId = -1;
		document.getElementsByName("route-product-boId")[d].value = boId;
		document.getElementsByName("route-product-name")[d].innerHTML = name;
		document.getElementsByName("route-product-brand-name")[d].innerHTML = brandName;
		document.getElementsByName("route-product-flavour")[d].innerHTML = flavour;
		for (var t = 0; t < typeAndGramSplit.length; t++) {
			var temp = typeAndGramSplit[t];
			if (temp.trim() == "")
				continue;
			var tempPackage = temp.split(",");
			var select = $(document
					.getElementsByName("route-product-packaging-gram")[d]);
			var option = $(document.createElement("option"));
			option.text(tempPackage[0] + ", " + tempPackage[1] + ", "
					+ tempPackage[2]);
			option.val(tempPackage[0]);
			select.append(option);
			if (packageId == -1) {
				packageId = tempPackage[0];
			}
		}
		document.getElementsByName("packaging_Type").text = packageId;

		// Available Location
		var locationBoId = document.getElementById("detail-location-boId").innerHTML;
		getAvailableLocationList(row, boId, packageId, "", locationBoId, "");
		document.getElementsByName("route-product-qty")[d].focus();
		$(row).removeClass("required");
		var id = row.parentNode.parentNode.id;
		if (errors[id])
			delete errors[id];
		return false;
	}
}

//Same as original function. Just removed Click Event. Aim For Route Edit's Product List
function getRouteProductInformation2(row) {
        console.log("In Product Info...");
        var productName = document.getElementById("route-product-name-lst");
        var productBrandName = document
            .getElementById("route-product-brand-name-lst");
        var productFlavour = document
            .getElementById("route-product-flavour-lst");
        var productPackageType = document
            .getElementById("route-product-packageType-gram-lst");
        var boIdList = document.getElementById("route_extra_productList");
        var index = -1;
        var d = row.parentNode.parentNode.rowIndex - 1;
        for (var i = 0; i < route_productList.options.length; i++) {
            if (route_productList.options[i].value == document
                .getElementsByName("route-product-extra-boId")[d].value) {
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
        var boId = boIdList.options[index].value;
        var name = productName.options[index].value;
        var brandName = productBrandName.options[index].value;
        var flavour = productFlavour.options[index].value;
        var typeAndGram = productPackageType.options[index].value;
        var typeAndGramSplit = typeAndGram.split(";");
        var packageId = -1;
        document.getElementsByName("route-product-boId")[d].value = boId;
        document.getElementsByName("route-product-name")[d].innerHTML = name;
        document.getElementsByName("route-product-brand-name")[d].innerHTML = brandName;
        document.getElementsByName("route-product-flavour")[d].innerHTML = flavour;

		var select = $(document.getElementsByName("route-product-packaging-gram")[d]);
		var optgroup = $(document.createElement("optgroup"));
		optgroup.attr("label", "Available Packaging");

        for (var t = 0; t < typeAndGramSplit.length; t++) {
        	var temp = typeAndGramSplit[t];
            if (temp.trim() == "")
                continue;
            var tempPackage = temp.split(",");
            var option = $(document.createElement("option"));
            option.attr("name", boId + ":" + tempPackage[1] + ":FRESH");
            option.text(tempPackage[0] + ", " + tempPackage[1] + ", "
                + tempPackage[2]);
            option.val(tempPackage[0]);
            optgroup.append(option);

            if (packageId == -1) {
                packageId = tempPackage[0];
            }

        }
    	select.append(optgroup);
        document.getElementsByName("packaging_Type").text = packageId;

		//Available Quantity
		var product = document.getElementById(boId+":product").innerHTML;
		var quantity = document.getElementById(product).innerHTML;
		document.getElementsByName("dailySales-available-location")[d].innerHTML = quantity;

        document.getElementsByName("route-product-qty")[d].focus();
        $(row).removeClass("required");
        var id = row.parentNode.parentNode.id;
        if (errors[id])
            delete errors[id];
        return false;
}

function getAdjustmentProductInfo(event, row) {
	if (event.which == 13 || event.keyCode == 13 || event.which == 9
			|| event.keyCode == 9) {
		var productCode = document
				.getElementById("add_adjustment_productList_extra");
		var productName = document
				.getElementById("add_adjustment_product_name_lst");
		var productBrandName = document
				.getElementById("add_adjustment_product_brand_name");
		var productFlavour = document
				.getElementById("add_adjustment_product_flavour");
		var productPrice = document
				.getElementById("add_adjustment_product_price_lst");
		var productPackageType = document
				.getElementById("adjustmentPackagingPrice");
		var index = -1;
		var d = row.parentNode.parentNode.rowIndex - 1;
		for (var i = 0; i < add_adjustment_productList.options.length; i++) {
			if (add_adjustment_productList.options[i].value == document
					.getElementsByName("add-adjustment-product-boId-extra")[d].value) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			alert("Please choose correct Product Code!");
			$(row).addClass("required");
			document.getElementsByName("add-adjustment-product-boId-extra")[d]
					.focus();
			var id = row.parentNode.parentNode.id;
			errors[id] = "error";
			return false;
		}
		var boId = productCode.options[index].value;
		var name = productName.options[index].value;
		var price = productPrice.options[index].value;
		var brandName = productBrandName.options[index].value;
		var flavour = productFlavour.options[index].value;
		var packageType = productPackageType.options[index].value;
		var packageTypeString = packageType.split(";");
		if ($(document.getElementsByName("productAllPrice")).length != 0) {
			$(document.getElementsByName("productAllPrice")[d]).html("");
			for (var t = 0; t < packageTypeString.length; t++) {
				var temp = packageTypeString[t];
				if (temp.trim() == "")
					continue;
				var tempPackage = temp.split(",");
				console.log("tempackage", tempPackage);
				var option = $(document.createElement("option"));
				var select = $(document.getElementsByName("productAllPrice")[d]);
				option.text(tempPackage[1] + ',' + tempPackage[2]);
				option.attr("value", tempPackage[0] + ',' + tempPackage[1]
						+ ',' + tempPackage[2]);
				select.append(option);
			}
		}
		if ($(document.getElementsByName("add_adjustment_product_price")).length != 0) {
			document.getElementsByName("add_adjustment_product_price")[d].innerHTML = price;
		}
		document.getElementsByName("add-adjustment-product-boId")[d].value = boId;
		document.getElementsByName("add_adjustment_brand_name")[d].innerHTML = brandName;
		document.getElementsByName("add_adjustment_product_flavour")[d].innerHTML = flavour;
		document.getElementsByName("add_adjustment_product_name")[d].innerHTML = name;
		document.getElementsByName("add_adjustment_product_qty")[d].focus();
		$(row).removeClass("required");
		var id = row.parentNode.parentNode.id;
		if (errors[id])
			delete errors[id];
		return false;
	}
	return true;
}

function getAdjustmentProductInfo1(event, row) {
	if (event.which == 13 || event.keyCode == 13 || event.which == 9
			|| event.keyCode == 9) {
		var productCode = document
				.getElementById("add_adjustment_productList_extra");

		var productName = document
				.getElementById("add_adjustment_product_name_lst");
		var productBrandName = document
				.getElementById("add_adjustment_product_brand_name");
		var productFlavour = document
				.getElementById("add_adjustment_product_flavour");
		var productPrice = document
				.getElementById("add_adjustment_product_price_lst");
		var productPackageType = document
				.getElementById("editAdjustmentPackagingPrice");
		var index = -1;
		var d = $(row).parent().parent();
		console.log(d);
		for (var i = 0; i < add_adjustment_productList.options.length; i++) {
			if (add_adjustment_productList.options[i].value == d.children(
					"td[name=add-adjustment-product-boId-extra]").children(
					"input").val()) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			alert("Please choose correct Product Code!");
			console.log("----------Error---------")
			$(row).addClass("required");
			d.children("td[name=add-adjustment-product-boId-extra]").children(
					"input").focus();
			var id = row.parentNode.parentNode.id;
			errors[id] = "error";
			return false;
		}
		var boId = productCode.options[index].value;
		var name = productName.options[index].value;
		var brandName = productBrandName.options[index].value;
		var flavour = productFlavour.options[index].value;
		var price = productPrice.options[index].value;
		var packageType = productPackageType.options[index].value;
		var packageTypeString = packageType.split(";");
		if ($(document.getElementsByName("add_adjustment_product_price").length == 0)) {
			$(document.getElementsByName("edit_adjustment_packageType1")[d])
					.html("");
			var num = 1;
			for (var t = 0; t < packageTypeString.length; t++) {
				var temp = packageTypeString[t];
				if (temp.trim() == "")
					continue;
				var tempPackage = temp.split(",");
				var option = $(document.createElement("option"));
				var select = d.children("td").children(
						"select[name=edit_adjustment_packageType1]");
				option.text(tempPackage[1] + ',' + tempPackage[2]);
				option.attr("value", tempPackage[0]);
				select.append(option);

				if (num == 1) {
					num = tempPackage[0];
				}

			}
		} else {
			d.children("td[name=add_adjustment_product_price]").text(price);
		}
		d.children("td[name=add-adjustment-product-boId-extra]").children(
				"span").text(boId);
		d.children("td[name=add_adjustment_product_name]").text(name);
		d.children("td[name=add_adjustment_product_brandName]").text(brandName);
		d.children("td[name=add_adjustment_product_flavour]").text(flavour);
		d.children("td[name=origin_valueList1]").text("0");
		if ($(document.getElementsByName("purchasePrice1")).length != 0) {
			var result = getPurchasePrice(row, boId, num);
		}
		d.children("td").children("span").children("input").focus();

		$(row).removeClass("required");
		var id = row.parentNode.parentNode.id;
		if (errors[id])
			delete errors[id];
		return false;
	}
	return true;
}

function checkIssueEnterKey(event, obj) {
	if (event.which == 13 || event.keyCode == 13) {
		if ($(obj).val() == "" || $(obj).val() == 0) {
			$(obj).addClass("required");
			return false;
		}
		$(obj).removeClass("required");
		addRow("add-issue-stock-table");
		return false;
	}
	return true;
}

function getIssueProductInfo(event, row) {
	if (event.which == 13 || event.keyCode == 13 || event.which == 9
			|| event.keyCode == 9) {
		var productCode = document
				.getElementById("add_issue_productList_extra");
		var productName = document.getElementById("add_issue_product_name_lst");
		var productPrice = document
				.getElementById("add_issue_product_price_lst");
		var index = -1;
		var d = row.parentNode.parentNode.rowIndex - 1;
		for (var i = 0; i < add_issue_productList.options.length; i++) {
			if (add_issue_productList.options[i].value == document
					.getElementsByName("add-issue-product-boId-extra")[d].value) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			alert("Please choose correct Product Code!");
			$(row).addClass("required");
			document.getElementsByName("add-issue-product-boId-extra")[d]
					.focus();
			var id = row.parentNode.parentNode.id;
			errors[id] = "error";
			return false;
		}
		var boId = productCode.options[index].value;
		var name = productName.options[index].value;
		var price = productPrice.options[index].value;
		document.getElementsByName("add-issue-product-boId")[d].value = boId;
		document.getElementsByName("add_issue_product_name")[d].innerHTML = name;
		document.getElementsByName("add_issue_product_price")[d].innerHTML = price;
		document.getElementsByName("add_issue_product_qty")[d].focus();
		$(row).removeClass("required");
		var id = row.parentNode.parentNode.id;
		if (errors[id])
			delete errors[id];
		return false;
	}
}

function checkEnterKey(event, obj) {
	if (event.which == 13 || event.keyCode == 13) {
		if ($(document.getElementsByName("add_location_product_price")).length != 0) {
			if ($(obj).val() == "" || $(obj).val() == 0) {
				$(obj).addClass("required");
				return false;
			}
			var d = event.target.parentNode.parentNode.parentNode.rowIndex - 1;
			var price = document
					.getElementsByName("add_location_product_price")[d].innerHTML;
			var qty = document.getElementsByName("add_location_product_qty")[d].value;
			$(obj).removeClass("required");
			document.getElementsByName("add_location_product_amount")[d].innerHTML = price
					* qty;
			addRow("add-location-stock-table");
		} else {
			addRow("add-location-stock-table");
		}
		return false;
	}
	return true;
}

function checkTransferEnterKey(event, obj) {
	if (event.which == 13 || event.keyCode == 13) {
		var d = event.target.parentNode.parentNode.parentNode.rowIndex - 1;
		rowtemp = d;
		if (document.getElementsByName("transfer-product-price").length != 0) {
			if ($(obj).val() == "" || $(obj).val() == 0) {
				$(obj).addClass("required");
				return false;
			}
			var price = document.getElementsByName("transfer-product-price")[d].innerHTML;
			var qty = document.getElementsByName("transfer-product-qty")[d].value;
			$(obj).removeClass("required");
			document.getElementsByName("transfer-product-amount")[d].innerHTML = price
					* qty;
			addRow("transfer-stock-table");
			return false;
		} else {
			addRow("transfer-stock-table");
			return false;
		}
	}
	return true;
}

function checkTransferEnterKeyEdit(event) {
	var d = rowtemp;
	var price = document.getElementsByName("transfer-product-price")[d].innerHTML;
	var qty = document.getElementsByName("transfer-product-qty")[d].value;
	document.getElementsByName("transfer-product-amount")[d].innerHTML = price
			* qty;
}

function checkTotalAmount(event) {
	var d = event.target.parentNode.parentNode.parentNode.rowIndex - 1;
	var price = document.getElementsByName("route-product-price")[d].value;
	var qty = document.getElementsByName("route-product-qty")[d].value;
	document.getElementsByName("route-product-amount")[d].innerHTML = price
			* qty;
}

function checkRouteEnterKey(event, obj) {
	if (event.which == 13 || event.keyCode == 13) {
		if ($(obj).val() == "" || $(obj).val() == 0) {
			$(obj).addClass("required");
			return false;
		}
		var d = event.target.parentNode.parentNode.parentNode.rowIndex - 1;
		var price = document.getElementsByName("route-product-price")[d].value;
		var qty = document.getElementsByName("route-product-qty")[d].value;
		$(obj).removeClass("required");
		document.getElementsByName("route-product-amount")[d].innerHTML = price
				* qty;
		addRow("route-stock-table");
		return false;
	}
	return true;
}

function checkAdjustmentEnterKey(event, obj) {
	var qtyList = $("[name=add_adjustment_product_qty]");
	var total_adj = 0;
	qtyList.each(function(index, element) {
		element = $(element);
		total_adj += Number(element.val());
	});
	if (event.which == 13 || event.keyCode == 13) {
		if ($(obj).val() == "" || $(obj).val() == 0) {
			$(obj).addClass("required");
			return false;
		}
		$(obj).removeClass("required");
		addRow("add-adjustment-stock-table");
		document.getElementById("total-adjust").innerHTML = total_adj;
		return false;
	}
	return true;
}

function checkAdjustmentEnterKeyUp(event) {
	var qtyList = $("[name=add_adjustment_product_qty]");
	var total_adj = 0;
	qtyList.each(function(index, element) {
		element = $(element);
		total_adj += Number(element.val());
	});
	document.getElementById("total-adjust").innerHTML = total_adj;
}

function checkNewAdjustmentEnterKey(event, obj) {
	var qtyList = $("[name=adjustmentStock_groundList]");
	console.log(qtyList);
	var total_adj = 0;
	qtyList.each(function(index, element) {
		element = $(element);
		total_adj += Number(element.val());
	});
	if (event.which == 13 || event.keyCode == 13) {
		if ($(obj).val() == "") {
			$(obj).addClass("required");
			return false;
		}
		$(obj).removeClass("required");
		addRow("editAdjustment-StockList");
		document.getElementById("total-adjust").innerHTML = total_adj;
		return false;
	}
	return true;
}

function addRowAdj(parentId, row) {
	var tempRow = $("#" + parentId).children("tbody").children(
			"tr[name=template]").clone();
	tempRow.attr("name", row);
	tempRow.removeClass("hide");
	$("#" + parentId).children("tbody").append(tempRow);
	tempRow.children("td[name=add-adjustment-product-boId-extra]").children(
			"input:not([class=hide])").get(0).focus();
	return false;
}

function checkAdjustmentKeyup(event) {
	var d = event.target.parentNode.parentNode.parentNode.rowIndex - 1;
	var oringinQty = parseInt(document.getElementsByName("origin_valueList")[d].innerHTML);
	var groundQty = parseInt(document
			.getElementsByName("edit-adjustmentStock_groundList")[d].value) ? parseInt(document
			.getElementsByName("edit-adjustmentStock_groundList")[d].value)
			: 0;
	document.getElementsByName("adjustmentStock_valueList")[d].innerHTML = 0 - (oringinQty - groundQty);
}

function checkAdjustmentKeyup1(event) {
	var d = event.target.parentNode.parentNode.parentNode.rowIndex - 1;
	var l = document.getElementsByName("origin_valueList1").length - 1;
	var oringinQty = parseInt(document.getElementsByName("origin_valueList1")[l].innerHTML);
	var groundQty = parseInt(document
			.getElementsByName("edit-adjustmentStock_groundList1")[l].value) ? parseInt(document
			.getElementsByName("edit-adjustmentStock_groundList1")[l].value)
			: 0;
	document.getElementsByName("adjustmentStock_valueList")[d].innerHTML = 0 - (oringinQty - groundQty);
}

function addRowAdj1(event, row) {
	if (event.which == 13 || event.keyCode == 13) {
		if ($(row).val() == "") {
			$(row).addClass("required");
			return false;
		}
		$(row).removeClass("required");
		addRowAdj("editAdjustment-StockList", row);
		return false;
	}
	return true;
}

function addRow(parentId) {
	var table = document.getElementById(parentId);
	var temp = $("#" + parentId + " tr[name=skip]");
	temp.detach();
	var childList = table.getElementsByTagName("tr");
	var newinnerhtml = childList[1].innerHTML;
	var row = table.insertRow(childList.length);
	$(row).html(newinnerhtml);
	$(row).attr("id", new Date().toString());
	$(row).attr("name", $(childList[1]).attr("name"));
	$("#" + parentId + " tr:last td:first-child input").focus();
	$(table).children("tbody").append(temp);
}

function appendRow(parentId) {
	var table = document.getElementById(parentId);
	var childList = table.getElementsByTagName("tr");
	var row = table.insertRow(childList.length);
	row.innerHTML = childList[1].innerHTML;
	$(row).attr("name", $(childList[1]).attr("name"));
	var firstColumn = $("#" + parentId + " tr td:first-child");
	firstColumn.each(function(index, element) {
		if (!index)
			return true;
		if (index == firstColumn.length - 1)
			return true;
		$(this).text(index - 1 + ".");
		return true;
	});
}

function removeRow(element) {
	element = $(element);
	var removeElement = element.parent().parent();
	var table = removeElement.parent();
	if (table.children().length < 2) {
		alert("Can't remove last element");
		return;
	}
	if (errors[removeElement.attr("id")])
		delete errors[removeElement.attr("id")];
	removeElement.detach();
	var parentId = table.parent().attr("id");
}

function removeCurrentRow(currentElement) {
	console.log(currentElement);
	var parent = $(currentElement).parent();
	removeElement.detach();
	console.log(parent);
	parent.detach();
}

function addNewBorrowerRisk(description) {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				$('#workspace').removeClass('myFilter');
				var result = JSON.parse(request.responseText);
				if (result.status == 'ERROR') {
					alert("Please, check maximumLimit, these are already exist");
				} else {
					alert("Successfully Recorded!");
					document.getElementById("search-text").value = document
							.getElementById("risk-level").innerHTML;
					search(this, 'RISK_LEVEL', 'BORROWERRISK');
				}
			} else {
				alert("Error return :" + request.status);
			}
		}
	};
	var parameter = {};
	// parameter["risk-level"]=document.getElementById("risk_level").value;
	parameter["maximumLimit"] = document.getElementById("risk-maximumLimit").value ? document.getElementById("risk-maximumLimit").value :"0";
	parameter["description"] = document.getElementById("risk-description").value;
	parameter["credit_period"] = document.getElementById("risk-credit_periods").value ? document.getElementById("risk-credit_periods").value : "0";
	console.log("Parameter ", parameter);
	loading();
	request.open("POST", "saveNewBorrowerRisk?input="
			+ JSON.stringify(parameter), true);
	request.send();
}

function addNewVolumeProduct(description) {
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				// var result = JSON.parse(request.responseText);
				var result = parseNewJSON(request.responseText);
				console.log("Result from controller" + result);
				if (result.status == 'ERROR') {
					alert("Error : product code is already existed.");
				} else if (result.status == 'ZERO') {
					alert("Error : Please Check! Quantity must not zero!");
				} else if (result.status == 'DUPLICATE') {
					alert("Error : Please Check! Duplicate Record in this Product Code!");
				} else if (result.status == 'INVALID') {
					alert("Error : Please Check! Invalid Quantity in this Product Code!");
				} else {
					alert("Successfully Recorded!");
					searchFirst();
					document.getElementById("dialog-temp").attributes["class"].value = "hide";
				}
			} else {
				alert("Error return :" + request.status);
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
	var parameter = {};
	parameter["description"] = description;
	parameter["add_product_code"] = document.getElementById("add_product_code").value
			.trim();
	parameter["add_product_name"] = document.getElementById("add_product_name").value
			.trim();
	parameter["add_product_discounted"] = document
			.getElementById("add_product_discounted").checked;
	var min = document.getElementById("volumeproduct_min").value.trim();
	var minArray = [];
	var maxArray = [];
	var priceArray = [];
	var minvar = document.getElementById("volumeproduct").querySelectorAll(
			"#volumeproduct_min");
	var maxvar = document.getElementById("volumeproduct").querySelectorAll(
			"#volumeproduct_max");
	var pricevar = document.getElementById("volumeproduct").querySelectorAll(
			"#volumeproduct_price");
	for (var i = 1; i < minvar.length; i++) {
		minArray[minArray.length] = minvar[i].value.trim().replace(",", "");
		maxArray[maxArray.length] = maxvar[i].value.trim().replace(",", "");
		priceArray[priceArray.length] = pricevar[i].value.trim().replace(",",
				"");
	}
	parameter["volumeproduct_min"] = minArray;
	parameter["volumeproduct_max"] = maxArray;
	parameter["volumeproduct_price"] = priceArray;
	request.open("POST", "saveNewVolumeProduct?input="
			+ JSON.stringify(parameter), true);
	loading();
	console.log(parameter);
	request.send();
}

function checkVolumePriceEnterKey(event) {
	if (event.which == 13 || event.keyCode == 13) {
		addVolumeRow("volumeproduct");
		return false;
	}
	return true;
}

function addVolumeRow(parentId) {
	var table = document.getElementById(parentId);
	if (table != null) {
		var childList = table.getElementsByTagName("tr");
		var newinnerhtml = childList[1].innerHTML;
		var row = table.insertRow(childList.length);
		$(row).html(newinnerhtml);
		$(row).attr("value", $(childList[1]).attr("value"));
		$(row).attr("id", $(childList[1]).attr("id"));
		$(row).find('input')[0].focus();
	}
}

function carLoadRouteForm() {
	var locationId = document.getElementById("detail-location-boId").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("route-location").innerHTML = document
						.getElementById("detail-location-name").innerHTML;
			/*	$('#route-from-date').datepicker({
					dateFormat : 'dd/mm/yy'
				});
				$('#carroute-to-date').datepicker({
					dateFormat : 'dd/mm/yy'
				});*/
				document.getElementById("route-from-date").value = today();
				document.getElementById("carroute-to-date").value = today();
			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	loading();
	request.open("GET",
			"createCarRouteForm?input=" + JSON.stringify(parameter), true);
	request.send(null);
}

function saveCarRoute() {
	var fromLocation = document.getElementById("detail-location-boId").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").attributes["class"].value = "hide";
				loadAction(document.getElementById("locationmenu"), 'location');
				document.getElementById("search-text").value = fromLocation;
				search(this, 'ID', 'LOCATION');
				this.id = fromLocation;
				aboutDetail(this, 'LOCATION');
				alert("Successfully Recorded!");
			} else {
				alert("Load transfer form error.Please try again. Error code is "
						+ request.status);
			}
		}
	};
	var routeRecord = {};
	routeRecord['location'] = fromLocation;
	routeRecord['routeName'] = document.getElementById("route-name").value;
	// routeRecord['fieldTripAllowance'] =
	// document.getElementById("route-fieldTrip-allowance").value;
	routeRecord['city'] = document.getElementById("route-city").value;
	routeRecord['expense'] = document.getElementById("route-expense").value
			.trim() ? document.getElementById("route-expense").value.trim()
			: "0";
	var x = document.getElementById('route-to-employee-list');
	var employeeList = "";
	for (var i = 0; i < x.length; i++) {
		employeeList += x[i].value + ":";
	}
	routeRecord['employeeList'] = employeeList;
	routeRecord['from-date'] = document.getElementById("route-from-date").value;
	routeRecord['to-date'] = document.getElementById("carroute-to-date").value;

	routeRecord['presentFine'] = document.getElementById("presentFine").value
			.trim() ? document.getElementById("presentFine").value.trim() : "0";
	// routeRecord['debtCollectionCommission'] =
	// document.getElementById("debtCollectionCommission").value
	// .trim() ?
	// document.getElementById("debtCollectionCommission").value.trim()
	// : "0";
	routeRecord['saleQuota'] = document.getElementById("saleQuota").value
			.trim() ? document.getElementById("saleQuota").value.trim() : "0";

	routeRecord['cashDownSalesQuantityCommission'] = document
			.getElementById("cashDownSalesQuantityCommission").value.trim() ? document
			.getElementById("cashDownSalesQuantityCommission").value.trim()
			: "0";
	routeRecord['creditSalesQuantityCommission'] = document
			.getElementById("creditSalesQuantityCommission").value.trim() ? document
			.getElementById("creditSalesQuantityCommission").value.trim()
			: "0";
	routeRecord['salesQuantityWithinOneMonthCommission'] = document
			.getElementById("salesQuantityWithinOneMonthCommission").value
			.trim() ? document
			.getElementById("salesQuantityWithinOneMonthCommission").value
			.trim() : "0";

	routeRecord['returnQuantityCommission'] = document
			.getElementById("returnQuantityCommission").value.trim() ? document
			.getElementById("returnQuantityCommission").value.trim() : "0";
	routeRecord['depreciation'] = document.getElementById("depreciation").value
			.trim() ? document.getElementById("depreciation").value.trim()
			: "0";
	routeRecord['shortFallFine'] = document.getElementById("shortFallFine").value
			.trim() ? document.getElementById("shortFallFine").value.trim()
			: "0";
	routeRecord['saleRevenuePercentage'] = document
			.getElementById("saleRevenuePercentage").value.trim() ? document
			.getElementById("saleRevenuePercentage").value.trim() : "0";

	console.log(routeRecord);
	loading();
	request.open("GET", "saveCarRoute?input=" + JSON.stringify(routeRecord),
			true);
	request.send(null);
}

function openDeleteTransferForm(element) {
	document.getElementById("confirm-text").innerHTML = "Are you sure you want to Delete?";
	document.getElementById("confirm").attributes["class"].value = "dialog";
	document.getElementById("confirm-delete").attributes["onclick"].value = "deleteTransferConfirm('"
			+ document.getElementById("transfer-BoId").innerHTML + "');";
}

function deleteTransferConfirm(transferBoId) {
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("confirm").attributes["class"].value = "hide";
				document.getElementById("search-text").value = "";
				searchFirst();
			} else {
				alert("Delete error return :" + request.status);
			}
		}
	};
	var parameter = {};
	parameter["boId"] = transferBoId;
	parameter["locationId"] = document.getElementById("location-id").innerHTML;
	request.open("DELETE", "deleteTransfer?input=" + JSON.stringify(parameter),
			true);
	request.send();
	loading();
}

function importGroundDataList() {
	var fileName = document.getElementById("groundDataList").value;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("confirm").attributes["class"].value = "hide";
				document.getElementById("search-text").value = "";
				searchFirst();
			} else {
				alert("Delete error return :" + request.status);
			}
		}
	};
	var parameter = {};
	parameter["fileName"] = "Location1.txt";
	parameter["add_location_boId"] = document
			.getElementById("detail-location-boId").innerHTML;
	request.open("POST", "loadOpeningDataForLocationOld?input="
			+ JSON.stringify(parameter), true);
	request.send(null);
}

function editTransferForm(element) {
	var locationId = document.getElementById("location-from-id").innerHTML;
	var transferBoId = document.getElementById("transfer-BoId").innerHTML;
	var request = new XMLHttpRequest;
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				hideLoading();
				document.getElementById("dialog-temp").innerHTML = request.responseText;
				document.getElementById("dialog-temp").className = "dialog";
				document.getElementById("to-location").value = document
						.getElementById("detail-transfer-to-BoId").innerHTML;
				document.getElementById("searchadvanceForInvoice").className = "show ui button basic blue small";
				/*$('#transfer-date').datepicker({
					dateFormat : 'dd/mm/yy'
				});*/
			} else {
				alert("Please try again. Error code is " + request.status);
			}
		}
	};
	var parameter = {};
	parameter["locationId"] = locationId;
	parameter["transferBoId"] = transferBoId;
	loading();
	request.open("GET", "editTransferForm?input=" + JSON.stringify(parameter),
			true);
	request.send(null);
}

function enableOutletType() {
	if (document.getElementById("add_customer_Type").value == 'OUTLET') {
		document.getElementById("outletType").className = "show";
	} else {
		document.getElementById("outletType").className = "hide";
	}
}

function getPackagingPrice(element) {
	var select = getParentByTagName(element, "select");
	var tr = getParentByTagName(element, "tr");
	var pCode = $("[name=adjustmentStock_boIdList1]", tr).val();
	var pkgId = select.val();
	var result = getPurchasePrice(element, pCode, pkgId);
}

function getPurchasePrice(element, pCode, pkId) {
	var parameter = {};
	parameter["pCode"] = pCode;
	parameter["pkId"] = pkId;
	var request = new XMLHttpRequest;
	request.onreadystatechange = readyFunction;
	request.open("GET", "getPurchasePrice?input=" + JSON.stringify(parameter),
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
		var d = element.parentNode.parentNode.rowIndex - 1;
		var tr = getParentByTagName(element, "tr");

		if (request.responseText != "") {
			var json = parseNewJson(request.responseText);
			var td = $("[name=purchasePrice1]", tr);
			td[0].innerHTML = "";
			if (json != null) {
				for ( var index in json) {
					if (json[index] != 0) {
						var span = document.createElement("span");
						span.innerHTML = json[index];
						td[0].appendChild(span);

					} else {
						var element1 = document.createElement("input");
						element1.setAttribute('type', 'number');
						element1.setAttribute('name', 'othercost');
						element1.setAttribute('placeholder', json[index]);
						element1.setAttribute('onkeypress',
								'return addRowAdj1(event,this)');
						element1.setAttribute('required', 'required');
						element1.setAttribute('min', '1');
						td[0].appendChild(element1);
					}
				}
				hideLoading();
				return 1;
			}
		} else {
			hideLoading();
			return 0;
		}
	}
}

function searchRouteByDate() {
	var locationBoId = $("#detail-location-name").attr('value');
	var startDate = document.getElementById('routeStartDate').value;
	var endDate = document.getElementById('routeEndDate').value;

	// TODO:: check date validation

	var parameter = {};
	parameter["locationBoId"] = locationBoId;
	parameter["startDate"] = startDate;
	parameter["endDate"] = endDate;

	loading();

	$.get(
			"searchRouteListByLocationWithDates?input="
					+ JSON.stringify(parameter)).then(function(data) {
		$("#routeListId").html(data);
		hideLoading();
	});

}

function formatDate(date) {
	var d = new Date(date), month = '' + (d.getMonth() + 1), day = ''
			+ d.getDate(), year = d.getFullYear();

	if (month.length < 2)
		month = '0' + month;
	if (day.length < 2)
		day = '0' + day;

	return [ year, month, day ].join('-');
}

function parseStringToInputDate(input) {
    var inputDate = input.split("/"); // 0 - DD / 1 - MM / 2 -YYYY
    return inputDate[2] + "-" + inputDate[1] + "-" + inputDate[0];
}