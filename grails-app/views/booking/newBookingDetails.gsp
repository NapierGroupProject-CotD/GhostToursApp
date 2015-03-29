<%@ page import="ghost.Tour"%>
<%@ page import="ghost.TourType"%>

<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="ghostview"/>
</head>
<body>
<h4>${tour.datetime.format("EEEE")} - ${tour.datetime.format("dd MMMM yyyy - HH:mm")} - ${tour.tourType.typeName}</h4>
<label>Booked placed: ${tour.tourType.spaces - tour.getRemainingPlaces()} / ${tour.tourType.spaces}</label><br/><br/>


<g:if test="${session.getAttribute("isBooker") && tour.datetime.getTime() > System.currentTimeMillis() && remainingTourPlaces>0}">
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
</div><br/><br/>
</g:if>
<g:elseif test="${remainingTourPlaces==0}">
	<h4>This tour is fully booked!</h4>
</g:elseif>
<div id="otherBookingsBox">
	<table>
		<tr>
			<th>Customer Name</th><th>No. people</th><th>Booked by</th>
		</tr>
		<g:each in="${tourBookings}" var="booking">
			<tr>
				<td>${booking.custName}</td><td>${booking.numberPeople}</td><td>${booking.staff.name}</td>
				<g:if test="${session.getAttribute("isBooker") && tour.datetime.getTime() > System.currentTimeMillis()}">
				<td><g:form action="cancelBooking">
						<g:hiddenField name="bookingId" value="${booking.id}"/>
						<g:hiddenField name="chosenTour" value="${tour.id}"/>
						<input type="submit" value="Cancel" onclick="return confirm('Delete booking?')"/>
					</g:form>
				</td>
				</g:if>
			</tr>
		</g:each>
	</table>
</div>
</body>
</html>
