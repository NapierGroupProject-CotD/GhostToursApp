<%@ page import="ghost.Tour"%>
<%@ page import="ghost.TourType"%>

<!DOCTYPE html>
<html>
<head>
</head>
<body>
<nav>
	<g:link action="logout">Log out</g:link>
</nav>

<div id="upcommingToursBox">
	<g:form id="chooseTourForm" controller="booking" action="newBookingDetails">
		<fieldset>
			<legend>Choose the the tour for booking</legend>
			<g:if test="${flash.message}">
				<label>${flash.message}</label><br/>
			</g:if>
			
			<g:each in="${futureToursList}" var="tour" status="i">

				<g:radio name="chosenTour" value="${tour.id}"/><label>${tour.datetime.format("d-MMM-yy HH:mm")} - ${tour.tourType.typeName} - ${tour.getRemainingPlaces()} places left!</label><br/>
			</g:each>
		</fieldset>
		<input type="submit" value="Proceed to Booking"/>
	</g:form>
</div><br/><br/>

<g:if test="${!session.getAttribute("today")}">
	<g:link controller="booking" action="today">Today</g:link>
	<g:link controller="booking" action="previousDay">Previous Day</g:link>
</g:if>
<g:else>
	<label>Today</label>
	<label>Previous Day</label>
</g:else>

<g:link controller="booking" action="nextDay">Next Day</g:link>
<g:link action="logoutStaff">Log out</g:link>
</body>
</html>
