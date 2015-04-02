<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="ghostview"/>
	<asset:stylesheet src="datepicker.css"/>
	<asset:javascript src="datepicker.js"/>
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
			<g:each in="${futureToursList.sort{it.datetime}}" var="tour" status="i">

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

<g:link controller="booking" action="nextDay">Next Day</g:link><br/>
<div id="datePickerBox">
	<g:if test="${flash.dateMessage}">
		<label>${flash.dateMessage}</label>
	</g:if>
	<g:form controller="booking" action="pickDate">
		<input type="text" id="inp1" name="date" />
				<script>
					// Attach a datepicker to the above input element
					datePickerController.createDatePicker({
						formElements : {
							"inp1" : "%d/%m/%Y"
						}
						
					});
				</script>
		<input type="submit" value="Confirm"/>
	</g:form>
</div>
</body>
</html>
