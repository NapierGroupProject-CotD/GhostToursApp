<!DOCTYPE html>
<html>
<head>
<asset:stylesheet src="structure.css"/>
<meta name="layout" content="ghostview"/>
</head>
<body>
<nav>
	<g:link action="logout">Log out</g:link>
</nav>

<div id="timetableBox">
<g:if test="${!session.getAttribute("isManager")}">
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
							<g:if test="${tour.staff != null}">
								${tour.staff.name}
							</g:if>
							<g:else>
								not allocated
							</g:else>
						</td>
					</g:each>
				</g:if>
				<g:elseif test="${date.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY}">
					<g:each in="${tourMap.get(date)}" var="tour" status="i">
						<g:if test="${i == 0}">
							<td>
								
								<g:if test="${tour.staff != null}">
									${tour.staff.name}
								</g:if>
								<g:else>
									not allocated
								</g:else>
							</td><td>****</td>
						</g:if>
						<g:else>
							<td>
								<g:if test="${tour.staff != null}">
									${tour.staff.name}
								</g:if>
								<g:else>
									not allocated
								</g:else>
							</td>
						</g:else>
					</g:each>
				</g:elseif>
				<g:else>
					<g:each in="${tourMap.get(date)}" var="tour" status="i">
						<g:if test="${i == 1}">
							<td>
								<g:if test="${tour.staff != null}">
									${tour.staff.name}
								</g:if>
								<g:else>
									not allocated
								</g:else>
							</td><td>****</td>
						</g:if>
						<g:else>
							<td>
								<g:if test="${tour.staff != null}">
									${tour.staff.name}
								</g:if>
								<g:else>
									not allocated
								</g:else>
							</td>
						</g:else>
					</g:each>
				</g:else>
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
				<g:if test="${date.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY || date.get(Calendar.DAY_OF_WEEK)==Calendar.THURSDAY || date.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY || date.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || date.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY}">
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
				</g:if>
				<g:elseif test="${date.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY}">
					<g:each in="${tourMap.get(date)}" var="tour" status="i">
						<g:if test="${i == 0}">
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
							</td><td>****</td>
						</g:if>
						<g:else>
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
						</g:else>
					</g:each>
				</g:elseif>
				<g:else>
					<g:each in="${tourMap.get(date)}" var="tour" status="i">
						<g:if test="${i == 1}">
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
							</td><td>****</td>
						</g:if>
						<g:else>
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
						</g:else>
					</g:each>
				</g:else>
			</tr>
		</g:each>
	</table>
</g:else>
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
