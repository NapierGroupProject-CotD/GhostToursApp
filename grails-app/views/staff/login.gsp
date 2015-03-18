<%@ page import="ghost.Role"%>

<!DOCTYPE html>
<html>
<head>
	<asset:stylesheet src="structure.css"/>
</head>
<body>
<nav>
	<g:link controller="main" action="index">Back to index</g:link>
</nav>

<div id="loginFormBox">
	<g:if test="${flash.message}">
		<div style="display: block">${flash.message}</div>
	</g:if>
	<g:form id="loginForm" action="chooseView">
		<fieldset>
			<legend>Enter login credentials</legend>
			
			<input type="text" name="username"required placeholder="Username"/><br/>
			<input type="text" name="password" required placeholder="Password"/><br/>
		</fieldset>
		<input type="submit" value="Log In"/>
	</g:form>
</div>

</body>
</html>
