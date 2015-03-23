<%@ page import="ghost.Role"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>City of the Dead"</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'skull-big.png')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'skull-big.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<asset:stylesheet src="structure.css"/>
		<asset:stylesheet src="bootstrap.css"/>
		<asset:stylesheet src="bootstrap-theme.css"/>
		<asset:stylesheet src="bootstrap.min.css"/>
		<asset:javascript src="application.js"/>
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
