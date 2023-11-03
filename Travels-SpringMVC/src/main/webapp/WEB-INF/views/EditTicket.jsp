<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit-Ticket</title>
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
    <h1>Select a Journey to Edit</h1>
    <table border="1">
        <tr>
			<th>Journey Date</th>
            <th>Source</th>
			<th>Destination</th>
			<th>Number of Passengers</th>
			<th>Pricing</th>
        </tr>
        <c:forEach items="${history}" var="journey" varStatus="status">
			<tr>
				<td>${journey.getJourneyDate()}</td>
				<td>${journey.getSource()}</td>
				<td>${journey.getDestination()}</td>
				<td>${journey.getPassingerNum()}</td>
				<td>${journey.getPrice()}</td>
				<td>
                    <a href="#" onclick="setIndex(${status.index})">Click</a>
                </td>
			</tr>
		</c:forEach>
    </table>
    
    <form id="indexForm" action="UpdateTickets" method="post">
        <input type="hidden" name="selectedIndex" id="selectedIndex" value="">
    </form>
    
    <script>
        function setIndex(index) {
            document.getElementById("selectedIndex").value = index;
            document.getElementById("indexForm").submit();
        }
    </script>
</body>
</html>
