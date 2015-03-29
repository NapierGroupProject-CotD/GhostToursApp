<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="ghostview"/>
</head>
<body>

<div id="upcommingToursBox">
	<g:form id="chooseTourForm" controller="booking" action="newBookingDetails">
		<fieldset>
			<legend>Choose the tour for booking</legend>
			<g:if test="${flash.message}">
				<label>${flash.message}</label><br/>
			</g:if>
			<h4>${futureToursList[0].datetime.format("EEEE")} - ${futureToursList[0].datetime.format("dd MMMM yyyy")}</h4>
			<g:each in="${futureToursList}" var="tour" status="i">

				<g:radio name="chosenTour" value="${tour.id}"/><label>${tour.datetime.format("HH:mm")} - ${tour.tourType.typeName} - ${tour.getRemainingPlaces()} places left!</label><br/>
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

</body>
</html>
