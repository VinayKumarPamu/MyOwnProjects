<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">
<title>Vicky Travels</title>
<style type="text/css">
#cssmenu {
	padding: 0;
	margin: 0;
	border: 0;
	line-height: 1;
	width: 150px;
	background: #f7f7f7;
	font-family: sans-serif;
	font-weight: bold;
	font-size: 12px;
	zoom: 1;
}

#cssmenu ul, #cssmenu ul li {
	list-style: none;
	margin: 0;
	padding: 0;
}

#cssmenu ul li.hover, #cssmenu ul li:hover {
	position: relative;
	z-index: 599;
	cursor: default;
}

#cssmenu a {
	display: block;
	padding: 15px 20px;
	color: #f26724;
	text-decoration: none;
	text-transform: uppercase;
	border-bottom: 1px solid #ffffff;
}

#cssmenu>ul {
	width: 150px;
}

#cssmenu>ul>li>a {
	border-left: 5px solid #095586;
	color: #095586;
}

#cssmenu>ul>li a:hover {
	background: #095586;
	background-image: url(temp1.jpg);
	background-size: cover;
	color: rgb(235, 179, 179);
}

.container {
	position: relative;
	opacity: 0.999;
}

.fixed-image {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 600px;
	height: 600px;
	background-image: url(logo-transparent-png.png);
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center;
	opacity: 0.5;
	z-index: 9999;
}

header {
	text-align-last: right;
	position: relative;
	width: 100%;
	height: 300px;
	overflow: hidden;
	background-color: blanchedalmond;
}

header p {
	margin-left: -50px;
	text-align: end;
	width: 100%;
	height: fit-content;
	font-size: 100px;
}

header img {
	float: left;
	height: 300px
}

header h2, h3 {
	margin-right: 55px;
}

.content {
	padding: 20px;
	flex-grow: 1;
	margin-left: 150px; /* Width of the left-side menu */
	margin-top: -290px;
}

.content div {
	display: none;
}

.content div.show {
	display: block;
}

.body {
	margin-top: 15%;
	background-color: bisque;
}

.a {
	color: black
}

body div {
	text-align: center;
}

.tbox1, .tbox2 {
	background-color: rgb(240, 232, 222);
	margin-bottom: 2px;
	width: 210px;
}

.tbox1 {
	border-top-left-radius: 8px;
	border-top-right-radius: 8px;
}

.tbox2 {
	border-bottom-left-radius: 8px;
	border-bottom-right-radius: 8px;
}

.tbox1:hover, .tbox2:hover {
	background-color: rgb(240, 219, 193);
}

.btn {
	border-radius: 10px;
	background-color: brown;
	color: aliceblue;
}

.btn:hover {
	background-color: rgb(168, 29, 29);
	color: aliceblue;
}

.img {
	margin-top: 80px;
	transform: scale(0.5) rotate(350deg);
	float: left;
}

div p {
	margin-top: -150px;
	font-size: 50px;
	color: brown;
	text-align: center;
	z-index: 0;
}

.text {
	margin-top: 450px;
	margin-left: -600px;
	opacity: 0.99;
	color: rgba(255, 255, 255, 0.377);
	text-align: center;
	transform: rotate(345deg) scale(1.7);
}

.container1 {
	position: relative;
	opacity: 0.999;
}

.fixed-image1 {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 100%;
	height: 100%;
	margin-top: 0px;
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center;
	opacity: 0.9;
	z-index: 9999;
}

.container1-main-container1 {
	position: relative;
	margin-top: -300px;
	margin-left: 600px;
	opacity: 0.8;
}

a button {
	margin-top: 10px;
	border-radius: 15px;
	background-color: darkgreen;
	color: aliceblue;
}

a button:hover {
	background-color: rgb(0, 78, 0);
}

.fgt {
	height: 30px;
	margin-top: 15px;
	border-radius: 15px;
	background-color: cornsilk;
	color: rgb(0, 0, 0);
}

.fgt:hover {
	background-color: rgb(255, 238, 168);
}
</style>
</head>
<body class="body">
	<div class="container1">
		<img
			src="https://images.unsplash.com/photo-1560719168-32825e16d2c6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8bmF0dXJlJTIwcm9hZHxlbnwwfHwwfHx8MA%3D%3D&w=1000&q=80"
			class="fixed-image1" class="img">
	</div>
	<div>
		<p class="container1">Vicky Travels</p>
	</div>
	<div class="text">
		<p style="font-size: 30px; color: rgba(255, 255, 255, 0.377);">The
			World is Yours</p>
		<h4>Travel AnyWhere You Want</h4>
	</div>
	<div class="container1-main-container1">
		<form class="login-box" action="validate-login">
			<div class="header">
				<h2>Login Page</h2>
			</div>
			<div class="login">
				<div class="form-control1">
					<input type="text" placeholder="Valid User Name" class="tbox1" name="userName"
						required />
				</div>
				<div class="form-control1">
					<input type="password" placeholder="*********" class="tbox2" name="password"
						required />
				</div>
				<div class="form-control1">
					<input type="submit" value="Login Now" class="btn" /> 
					<a href="#" class="link">
						<button class="fgt">forgot password</button>
					</a><br>
				</div>
			</div>
		</form>
		<a href="signup.jsp" class="link" class="a">
			<button>New User Regstration</button>
		</a>
	</div>
</body>
</html>