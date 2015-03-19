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

<div id="upcommingToursBox">
	<g:form id="chooseTourForm" controller="booking" action="newBookingDetails">
		<fieldset>
			<legend>Choose the the tour for booking</legend>

			<g:each in="${futureToursList}" var="tour" status="i">

				<g:radio name="chosenTour" value="${tour.id}"/><label>${tour.datetime} - ${tour.tourType.typeName} - ${tour.getRemainingPlaces()} places left!</label><br/>
			</g:each>
		</fieldset>
		<input type="submit" value="Next"/>
	</g:form>
</div>

</body>
</html>
