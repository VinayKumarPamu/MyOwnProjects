<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>
<title>New SignIn</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script type='text/javascript'>
	function registration() {
		var name = document.getElementById("t1").value;
		var email = document.getElementById("t2").value;
		var uname = document.getElementById("t3").value;
		var pwd = document.getElementById("t4").value;
		var cpwd = document.getElementById("t5").value;
		//Email Regular Expression
		var pwd_expression = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])/;
		var letters = /^[A-Za-z]+$/;
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (name == '') {
			alert('Enter your name');
		} else if (!letters.test(name)) {
			alert('Name Must be Alphabets');
		} else if (email == '') {
			alert('Enter Valid Mail id');
		} else if (!filter.test(email)) {
			alert('Invalid email');
		} else if (uname == '') {
			alert('Enter Valid User Name.');
		} else if (!letters.test(uname)) {
			alert('Name Must be Alphabets');
		} else if (pwd == '') {
			alert('Enter Valid Password');
		} else if (cpwd == '') {
			alert('Enter Confirm Password');
		} else if (!pwd_expression.test(pwd)) {
			alert('Mixed Characters for Password');
		} else if (pwd != cpwd) {
			alert('Password Miss Matched');
		} else if (document.getElementById("t5").value.length < 6) {
			alert('Password min. Length is 6');
		} else if (document.getElementById("t5").value.length > 12) {
			alert('Password maximum Length is 12');
		} else {
			var redirectToURL = "http://www.FLM.com";
			console.log('Form submitted successfully');
			alert('Thank You for Logging In');
			console.log('Redirecting to: ' + redirectToURL);
			window.location.href = redirectToURL;
		}
	}
	function clearFunc() {
		document.getElementById("t1").value = "";
		document.getElementById("t2").value = "";
		document.getElementById("t3").value = "";
		document.getElementById("t4").value = "";
		document.getElementById("t5").value = "";
	}
</script>
<style>
.container {
	position: relative;
	opacity: 0.999;
}

.fixed-image {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 100%;
	height: 100%;
	background-image:
		url("https://images.unsplash.com/photo-1476820865390-c52aeebb9891?auto=format&fit=crop&q=80&w=1470&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center;
	opacity: 0.9;
	z-index: 9999;
}

body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	color: rgb(255, 255, 255);
	text-align: center;
}

form {
	display: inline;
}

input {
	margin-bottom: 2px;
	box-shadow: 0 0 5px rgba(0, 0, 0, 0.15);
	width: 250px;
	height: 40px;
	border-radius: 10px;
}

input:hover {
	background-color: burlywood;
}

.sub {
	width: 125px;
	margin-top: 20px;
	border-radius: 10px;
	background-color: darkgreen;
	text-align: center;
}

.sub:hover {
	background-color: rgb(0, 207, 0);
}

.cancel {
	width: 125px;
	border-radius: 10px;
	background-color: rgb(121, 24, 24);
	text-align: center;
}

.cancel:hover {
	background-color: rgb(255, 0, 0);
}

.Name, .email, .UserName, .Password, .ConfirmPassword {
	margin-left: 11px;
	float: left;
}

.reg {
	opacity: 0.9;
}

h4, h2 {
	color: threedshadow;
}
</style>
</head>
<body>
	<div class="container">
		<div class="fixed-image"></div>
	</div>
	<div class="reg">
		<h2>REGISTRATION</h2>
		<form onreset="clearFunc()" action="create-user">
			<div class="Name">
				<h4>Name:</h4>
				<input type="text" placeholder="Name" id="t1" required name="Name"/>
			</div>
			<div class="email">
				<h4>Email:</h4>
				<input type="text" placeholder="email address" id="t2" required name="email"/>
			</div>
			<div class="UserName">
				<h4>User Name:</h4>
				<input type="text" placeholder="User Name" id="t3" required name="UserName"/>
			</div>
			<div class="Password">
				<h4>Password:</h4>
				<input type="password" placeholder="Password" id="t4" required name="password" />
			</div>
			<div class="ConfirmPassword">
				<h4>Confirm Password:</h4>
				<input type="password" placeholder="Confirm Password" id="t5" name="confirm-password"
					required /><br>
			</div>
			<div>
				<input type="submit" value="submit" class="sub" onclick="registration()"> 
					<input type="reset"value="cancel" class="cancel">
			</div>
		</form>
	</div>
</body>
</html>