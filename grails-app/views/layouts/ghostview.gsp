<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="City of the Dead"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'skull-big.png')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'skull-big.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<asset:stylesheet src="structure.css"/>
		<asset:stylesheet src="bootstrap.css"/>
		<asset:stylesheet src="bootstrap-theme.css"/>
		<asset:stylesheet src="bootstrap.min.css"/>
		<asset:javascript src="jquery-1.9.1.js"/>
		<asset:javascript src="application.js"/>
		<asset:javascript src="bootstrap.js"/>
		<asset:javascript src="bootstrap.min.js"/>
		<asset:javascript src="npm.js"/>
		<g:layoutHead/>
	</head>

	<nav class="navbar">
		<div  class="container">
			<div class="navbar-header">
				<ul class="nav nav-tabs" role="tablist">
				<li id="booker" role="presentation"><g:link controller="staff" action="bookerDashboard">Bookings</g:link></li>
				<li id="guide" role="presentation"><g:link controller="staff" action="guideDashboard">Guide</g:link></li>
				<li id="manager" role="presentation"><g:link controller="staff" action="managerDashboard">Manager</g:link></li>
				<li role="presentation"><g:link controller="staff" action="logout">Logout</g:link></li>
				</ul>
			</div>
		</div>
	</nav>
	<body>
	
		<%-- <div id="cotdLogo" role="banner"><a href="http://www.cityofthedeadtours.com/"><asset:image src="timthumb.png" alt="City of the Dead"/></a></div>--%>
		
		<div class="container">
		<g:layoutBody/>
		</div>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
	</body>
	<script>
		$("#booker, #guide, #manager").on("click", function() {
			$(".nav nav-tabs li").removeClass("active");
			$(this.addClass("active");
		});
	</script>
</html>
