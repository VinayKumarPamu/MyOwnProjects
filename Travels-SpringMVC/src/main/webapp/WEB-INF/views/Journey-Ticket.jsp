<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page isELIgnored="false" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ticket Details</title>
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
body {
text-align: center;
}
table {
    margin: 0 auto; /* Center-align the table */
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
	<h1>Your Journey Ticket is:</h1>
	<br>
	<table border="1">
		<tr>
			<th>Source</th>
			<th>Destination</th>
			<th>Journey Date</th>
			<th>Number of Passengers</th>
			<th>Pricing</th>
		</tr>
		<tr>
			<td>${journey.getSource()}</td>
			<td>${journey.getDestination()}</td>
			<td>${journey.getJourneyDate()}</td>
			<td>${journey.getPassingerNum()}</td>
			<td>${journey.getPrice()}</td>
		</tr>
	</table>
	<h2>You Want to continue to Book Tickets?</h2>
	<a href="ConfirmTicket"><button>Yes</button></a>
	<a href="travel-book.jsp"><button>No</button></a>
</body>
</html>