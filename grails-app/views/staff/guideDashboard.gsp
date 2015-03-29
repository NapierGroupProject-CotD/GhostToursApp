<!DOCTYPE html>
<html>
<head>
<asset:stylesheet src="structure.css"/>
<asset:stylesheet src="datepicker.css"/>
<asset:javascript src="datepicker.js"/>
<meta name="layout" content="ghostview"/>
</head>
<body>
<div id="timetableBox">
<g:if test="${!session.getAttribute("isManager")}">
	<g:link controller="dateUnavailable" action="showDatesUnavailable">Update Availability</g:link>
	<table>
		<tr>
			<th>Date</th><th>3.30 UG</th><th>7.30 DD</th><th>8pm UG</th><th>8.30 GY</th><th>9.30 DD</th>
		</tr>
		<g:each in="${dateList}" var="date">
			<tr>
				<td>${date.format("d-MMM-yy")}</td>
				<g:each in="${tourMap.get(date)}" var="tour">
					<td>
						<g:if test="${tour.staff != null}">
							${tour.staff.name}
						</g:if>
						<g:else>
							not allocated
						</g:else>
					</td>
				</g:each>
			</tr>
		</g:each>
	</table>
</g:if>
<g:else>
	<table>
		<tr>
			<th>Date</th><th>3.30 UG</th><th>7.30 DD</th><th>8pm UG</th><th>8.30 GY</th><th>9.30 DD</th>
		</tr>
		<g:each in="${dateList}" var="date">
			<tr>
				<td>${date.format("d-MMM-yy")}</td>
				<g:each in="${tourMap.get(date)}" var="tour">
					<td>
						<g:if test="${tour.staff != null}">
							<g:form controller="tour" action="setTourStaff">
								<g:hiddenField name="tourId" value="${tour.id}"/>
								<g:select name="staff" from="${availableGuideMap.get(date.get(Calendar.DAY_OF_WEEK))}" optionKey="id" optionValue="name" noSelection="${[(tour.staff.id):(tour.staff.name)]}" onChange="this.form.submit()"/>
							</g:form>
						</g:if>
						<g:else>
							<g:form controller="tour" action="setTourStaff">
								<g:hiddenField name="tourId" value="${tour.id}"/>
								<g:select name="staff" from="${availableGuideMap.get(date.get(Calendar.DAY_OF_WEEK))}" optionKey="id" optionValue="name" noSelection="['':'not allocated']" onChange="this.form.submit()"/>
							</g:form>
						</g:else>
					</td>
				</g:each>
			</tr>
		</g:each>
	</table>
</g:else>
</div><br/><br/>

</body>
</html>
