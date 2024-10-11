function firstNameValidate() {
	'use strict';
	
	// validate first name
	var fName = document.getElementById("fName");
	var fNameValue = document.getElementById("fName").value;
	var fNameLen = fNameValue.length;
	

	// regExp that validates strings only
	var letters = /^[a-zA-Z]+$/;

	// conditional on first name. It isn't the len that needs to be letters
	if (fNameLen < 3 || !fNameValue.match(letters)) {
		document.getElementById("fNameErr").innerHTML = 'First name must be at least four characters and letters only.';	
		fName.focus();
		document.getElementById("fNameErr").style.color = "#fff";
		
		/*} else if(keyCode === 9) {
			preventDefault();*/
		
		
	
	} else {
		document.getElementById("fNameErr").innerHTML = 'First name is valid.';	
		document.getElementById("fNameErr").style.color = "#00af33";
	}
	
} // firstNameValidate func end


function lastNameValidate() {
	'use strict';

	/* */
	// validate last name
	var lName = document.getElementById("lName");
	var lNameValue = document.getElementById("lName").value;
	var lNameLen = lNameValue.length;

	// regExp that validates strings only
	var letters = /^[a-zA-Z]+$/;

	if (!lNameValue.match(letters) || lNameLen < 4) {
		document.getElementById("lNameErr").innerHTML = 'Last name must be at least four characters and letters only.';

		lName.focus();
		document.getElementById("lNameErr").style.color = "#fff";
		
	} else {
		document.getElementById("lNameErr").innerHTML = 'Last name is valid.';
		document.getElementById("lNameErr").style.color = "#00af33";

	}

} // lastNameValidate func end


function emailValidate() {
	'use strict';
	var regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
	var emailName = document.getElementById("email");
	var emailValue = document.getElementById("email").value;
	var emailLen = emailValue.length;

	if (!emailValue.match(regex) || emailLen === 0) {
		document.getElementById("emailErr").innerHTML = 'This email address is invalid';
		emailName.focus();
		document.getElementById("emailErr").style.color = '#fff';
	} else {
		document.getElementById("emailErr").innerHTML = 'This email address is valid';
		document.getElementById("emailErr").style.color = '#00af33';
	}

} // emailValidate func end

function phoneValidate() {
	'use strict';
	var regex = "^[0-9]+$";
	var phone = document.getElementById("phone");
	var phoneValue = document.getElementById("phone").value;
	var phoneLen = phoneValue.length;
	
	//if(!phoneValue.match(regex) || phoneLen < 15){
	if(!phoneValue.match(regex)) {
		document.getElementById("phone").innerHTML = 'Check that the phone number are digits, special characters are only a hyphen and () and at least 15 numbers in length';
		phone.focus();
		document.getElementById("phone").style.color = "#fff";
	 } else {
		document.getElementById("phone").innerHTML = 'Valid phone number.';
		document.getElementById("phone").style.color = "#00af33";
	}
}










