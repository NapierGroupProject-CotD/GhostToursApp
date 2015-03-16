<!DOCTYPE html>
<html>
<head>
</head>
<body>
<nav>
	<g:link controller="staff" action="logout">Log Out</g:link>
</nav>

<div id="chooseViewBox">
	<g:form id="chooseViewForm" action="changeView">
		<fieldset>
			<legend>Choose the view you want to see</legend>
			<g:each in="${listOfRoles}" var="role">
				<g:radio name="role" value="${role}"/><label>${role}</label>
			</g:each>

		</fieldset>
		<input type="submit" value="Go To View"/>
	</g:form>
</div>

</body>
</html>
