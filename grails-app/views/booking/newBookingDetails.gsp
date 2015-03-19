<%@ page import="ghost.Tour"%>
<%@ page import="ghost.TourType"%>

<!DOCTYPE html>
<html>
<head>
</head>
<body>
<nav>
	<g:link controller="staff" action="logout">Log Out</g:link>
</nav>

<div id="bookingDetailsBox">
	<g:form id="bookingDetailsForm" controller="booking" action="addBooking">
		<fieldset>
			<legend>Enter Booking Details</legend>
			<input type="text" name="custName" required placeholder="Customer Name"/>
			<input type="number" name="numberPeople" min="1" max="${remainingTourPlaces}"required placeholder="Number Of People"/> <label>${remainingTourPlaces} remaining! </label>
			<g:hiddenField name="tourId" value="${tour.id}"/>
		</fieldset>
		<input type="submit" value="Book"/>
	</g:form>
</div>

</body>
</html>
