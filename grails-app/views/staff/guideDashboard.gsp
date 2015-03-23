<!DOCTYPE html>
<html>
<head>
<asset:stylesheet src="structure.css"/>
</head>
<body>
<nav>
	<g:link action="logout">Log out</g:link>
</nav>

<div id="timetableBox">
	<table>
		<tr>
			<th>Date</th><th>3.30 UG</th><th>7.30 DD</th><th>8pm UG</th><th>8.30 GY</th><th>9.30 DD</th>
		</tr>
		<g:each in="${dateList}" var="date">
			<tr>
				<td>${date.format("d-MMM-yy")}</td>
				<g:if test="${date.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY || date.get(Calendar.DAY_OF_WEEK)==Calendar.THURSDAY || date.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY || date.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || date.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY}">
					<g:each in="${tourMap.get(date)}" var="tour">
						<td>
							<g:if test="${tour.staff?.id == loggedInStaff.id}">
								<g:link controller="tour" action="releaseShift" params="${[tourId:tour.id]}" onclick="return confirm('Release shift?')">${tour.staff.name}</g:link>
							</g:if>
							<g:elseif test="${tour.staff != null}">
								${tour.staff.name}
							</g:elseif>
							<g:else>
								<g:link controller="tour" action="grabShift" params="${[tourId:tour.id]}" onclick="return confirm('Grab shift?')">not allocated</g:link>
							</g:else>
						</td>
					</g:each>
				</g:if>
				<g:elseif test="${date.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY}">
					<g:each in="${tourMap.get(date)}" var="tour" status="i">
						<g:if test="${i == 0}">
							<td>
								<g:if test="${tour.staff?.id == loggedInStaff.id}">
									<g:link controller="tour" action="releaseShift" params="${[tourId:tour.id]}" onclick="return confirm('Release shift?')">${tour.staff.name}</g:link>
								</g:if>
								<g:elseif test="${tour.staff != null}">
									${tour.staff.name}
								</g:elseif>
								<g:else>
									<g:link controller="tour" action="grabShift" params="${[tourId:tour.id]}" onclick="return confirm('Grab shift?')">not allocated</g:link>
								</g:else>
							</td><td>****</td>
						</g:if>
						<g:else>
							<td>
								<g:if test="${tour.staff?.id == loggedInStaff.id}">
									<g:link controller="tour" action="releaseShift" params="${[tourId:tour.id]}" onclick="return confirm('Release shift?')">${tour.staff.name}</g:link>
								</g:if>
									<g:elseif test="${tour.staff != null}">
									${tour.staff.name}
								</g:elseif>
								<g:else>
									<g:link controller="tour" action="grabShift" params="${[tourId:tour.id]}" onclick="return confirm('Grab shift?')">not allocated</g:link>
								</g:else>
							</td>
						</g:else>
					</g:each>
				</g:elseif>
				<g:else>
					<g:each in="${tourMap.get(date)}" var="tour" status="i">
						<g:if test="${i == 1}">
							<td>
								<g:if test="${tour.staff?.id == loggedInStaff.id}">
									<g:link controller="tour" action="releaseShift" params="${[tourId:tour.id]}" onclick="return confirm('Release shift?')">${tour.staff.name}</g:link>
								</g:if>
								<g:elseif test="${tour.staff != null}">
									${tour.staff.name}
								</g:elseif>
								<g:else>
									<g:link controller="tour" action="grabShift" params="${[tourId:tour.id]}" onclick="return confirm('Grab shift?')">not allocated</g:link>
								</g:else>
							</td><td>****</td>
						</g:if>
						<g:else>
							<td><g:if test="${tour.staff != null}"><g:link controller="tour" action="releaseShift" params="${[tourId:tour.id]}" onclick="return confirm('Release shift?')">${tour.staff.name}</g:link></g:if>
							<g:else><g:link controller="tour" action="grabShift" params="${[tourId:tour.id]}" onclick="return confirm('Grab shift?')">not allocated</g:link></g:else></td>
						</g:else>
					</g:each>
				</g:else>
			</tr>
		</g:each>
	</table>
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
