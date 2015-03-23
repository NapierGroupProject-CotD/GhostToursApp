<%@ page import="ghost.Tour"%>
<%@ page import="ghost.TourType"%>

<!DOCTYPE html>
<html>
<head>
</head>
<body>
<nav>
	<g:link controller="staff" action="logout">Log Out</g:link>
	<g:link controller="staff" action="bookerDashboard">Back to Dashboard</g:link>
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

<div id="otherBookingsBox">
	<table>
		<tr>
			<th>Customer Name</th><th>No. people</th><th>Booked by</th><th>Actions</th>
		</tr>
		<g:each in="${tourBookings}" var="booking">
			<tr>
				<td>${booking.custName}</td><td>${booking.numberPeople}</td><td>${booking.staff.name}</td>
				<td><g:form action="cancelBooking">
						<g:hiddenField name="bookingId" value="${booking.id}"/>
						<g:hiddenField name="chosenTour" value="${tour.id}"/>
						<input type="submit" value="Cancel" onclick="return confirm('Delete booking?')"/>
					</g:form>
				</td>
			</tr>
		</g:each>
	</table>
</div>
</body>
</html>
