<!DOCTYPE html>
<html>
<head>
</head>
<body>
<p>test</p>
<div id="staffMemberBox">
<table>
	<tr> 
		<th>Name</th><th>Phone</th><th>E-mail</th><th>Role</th><th>Actions</th>
	<tr>
	<g:each in="${staffList}" var="staffMember">
		
			<tr> 
				<td>${staffMember.name}</td><td>${staffMember.phone}</td><td>${staffMember.email}</td><td>${mapOfRoles.get(staffMember)}</td>
				<td><g:form action="view">
						<g:hiddenField name="test" value="value"/>
						<input type="submit" value="View Details">
					</g:form></td>
			</tr>
		
	</g:each>
</table>
</div>

</body>
</html>
