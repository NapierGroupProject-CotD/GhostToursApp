<!DOCTYPE html>
<html>
<head>
	<asset:stylesheet src="structure.css"/>
</head>
<body>

<g:if test="${session.getAttribute("loggedInStaff") == null}">
	<g:link controller="staff" action="login">Log In</g:link>
</g:if>
<g:else>
	<g:link controller="staff" action="logout">Log Out</g:link>
</g:else>

</body>
</html>
