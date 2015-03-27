<%@page import="ghost.Role"%>
<head>
<meta name="layout" content="ghostview"/>
</head>

<body>
<div id="staffDetailsBox" class="panel panel-primary">
<h4>Details for </h4>
<g:form id="updateStaffForm" action="updateStaff">
<g:hiddenField name="staffId" value="${staffMember.id}"/>
<table class="table table-striped table condensed">
	<tr class="primary"> 
		<th><span class="glyphicon glyphicon-user text-primary"></span>Name</th>
		<th>Phone</th>
		<th><span class="glyphicon glyphicon-envelope text-success"></span>E-mail</th>
	</tr>
	<tr>
		<td><input name="name" value="${staffMember.name}" style="background-color:#111111"></td>
		<td><input name="phone" value="${staffMember.phone}" style="background-color:#111111"></td>
		<td><input name="email" value="${staffMember.email}" style="background-color:#111111"></td>
	</tr>	
</table>
<input type="submit" value="Update Details"/>
<input Type="button" value="Back" onClick="history.go(-1);return true;"/>
</g:form>
</div>

</body>