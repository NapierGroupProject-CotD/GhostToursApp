<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="ghostview"/>
</head>
<body>

<div id="chooseViewBox"  class="container">

	<g:form id="chooseViewForm" action="changeView">
		<fieldset>
			<legend>Choose the view you want to see</legend>
			<g:each in="${listOfRoles}" var="role">
				<g:radio name="role" value="${role.id}"/><label>${role.name}</label>
			</g:each>

		</fieldset>
		<input type="submit" value="Go To View"/>
	</g:form>
</div>


</body>
</html>
