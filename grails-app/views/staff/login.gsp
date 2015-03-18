<%@ page import="ghost.Role"%>

<!DOCTYPE html>
<html>
<head>
	<asset:stylesheet src="structure.css"/>

	<asset:stylesheet src="bootstrap.css"/>

</head>
<body>
<nav>
	<g:link controller="main" action="index">Back to index</g:link>
</nav>

<div id="loginFormBox" class="container">
	<g:if test="${flash.message}">
		<div style="display: block">${flash.message}</div>
	</g:if>
	<g:form id="loginForm" action="chooseView" class="form-signin">
		<fieldset>
			<legend class="form-signin-heading">Enter login credentials</legend>
			
			<input type="text" name="username"required placeholder="Username" class="form-control"/><br/>
			<input type="text" name="password" required placeholder="Password" class="form-control"/><br/>
		</fieldset>
		<input type="submit" value="Log In" class="btn btn-lg btn-primary btn-block"/>

	</g:form>
</div>

</body>
</html>
