function editProfile(){
	document.getElementById('fname').readOnly = false;
	document.getElementById('lname').readOnly = false;
}

function checkPasswordMatch() {
	var password = $("#txtNewPassword").val();
	var confirmPassword = $("#txtConfirmPassword").val();

	if (password != confirmPassword)
		$("#divCheckPasswordMatch").html("Passwords do not match!");
	else
		$("#divCheckPasswordMatch").html("Passwords match.");
}


function checkEmail() {
	var email = document.getElementById('email');
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var goodColor = "#66cc66";
	var badColor = "#ff6666";
	if (!filter.test(email.value)) {
		email.style.color = badColor;

		email.focus;
		return false;
	}else {
		email.style.color = goodColor;
	}
}





function checkPass()
{

//	Store the password field objects into variables ...
	var pass1 = document.getElementById('pass1');
	var pass2 = document.getElementById('pass2');
//	Store the Confimation Message Object ...
	var message = document.getElementById('confirmMessage');
//	Set the colors we will be using ...
	var goodColor = "#66cc66";
	var badColor = "#ff6666";
//	Compare the values in the password field 
//	and the confirmation field

	if(pass1.value == pass2.value && pass1.value.length != 0){
//		The passwords match. 
//		Set the color to the good color and inform
//		the user that they have entered the correct password 
		pass2.style.backgroundColor = goodColor;
		message.style.color = goodColor;
		message.innerHTML = "Passwords Match!";
		var fname = document.getElementById('fname');
		var lname = document.getElementById('lname');
		if(fname.value.length != 0 && lname.value.length != 0) {
			document.getElementById("mySubmit").disabled = false;
		}
	}else if(pass1.value != pass2.value) {
//		The passwords do not match.
//		Set the color to the bad color and
//		notify the user.
		pass2.style.backgroundColor = badColor;
		message.style.color = badColor;
		message.innerHTML = "Passwords Do Not Match!"
			document.getElementById("mySubmit").disabled = true;

	}
}  



