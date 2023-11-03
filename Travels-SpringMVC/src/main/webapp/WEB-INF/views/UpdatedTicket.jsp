<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.header {
	background: radial-gradient(#fff, #ffd6d6);
}

.container {
	max-width: 1300px;
	margin: auto;
	padding-left: 25px;
	padding-right: 25px;
}

nav ul li {
	display: inline-block;
	margin-right: 20px;
}

nav ul {
	display: inline-block;
	list-style-type: none;
}

nav {
	flex: 1;
	text-align: right;
}

.navbar {
	display: flex;
	align-items: center;
	padding: 20px;
}

a {
	text-decoration: none;
	color: #555;
}
</style>
</head>
<body>
<div class="header">
		<div class="container">
			<div class="navbar">
				<nav>
					<ul id="menuItems">
						<li><a href='Travels-menu'><span>Home</span></a></li>
						<li><a href='travel-book'><span>PLAN your Journey</span></a></li>
						<li><a href='paymentHistory'><span>Payments</span></a></li>
						<li><a href='EditTicket'><span>Update Tickets</span></a></li>
						<li><a href='history'><span>History</span></a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
<form action="UpdateTicket" style="border:1px solid #ccc">
  <div class="container">
    <h1>Booking</h1>
    <hr>

    <label for="from_address"><b>From</b></label>
    <input type="text" placeholder="From City" name="from_address" required>

    <label for="to_address"><b>To</b></label>
    <input type="text" placeholder="To City" name="to_address" required>
    
    <label for="passinger_Number"><b>Number of Passengers</b></label>
    <input type="number" placeholder="No. of Passengers" name="passinger_Number" required>
<label>
      <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Terms & Conditons
    </label>
    <p>By Clicking this  you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

    <div class="clearfix">
      <button type="submit" class="cancelbtn">Submit</button>
     <a href="#"><button type="reset">Delete Ticket</button></a>
    </div>
  </div>
</form>
</body>
</html>