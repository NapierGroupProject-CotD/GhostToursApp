<%@ page import="ghost.Role"%>

<!DOCTYPE html>
<html>
<head>
	<asset:stylesheet src="structure.css"/>
</head>
<body>
<nav>
	<g:link controller="staff" action="managerDashboard">Back to Dashboard</g:link>
	<g:link controller="staff" action="logout">Log Out</g:link>
</nav>
<div id="staffMemberBox">
<h4>List of all members</h4>
<table>
	<tr> 
		<th>Name</th><th>Phone</th><th>E-mail</th><th>Role</th><th>Actions</th>
	<tr>
	<g:each in="${staffList}" var="staffMember">
			<tr> 
				<td>${staffMember.name}</td><td>${staffMember.phone}</td><td>${staffMember.email}</td><td>${mapOfRoles.get(staffMember).name}</td>
				<td><g:form action="viewStaff">
						<g:hiddenField name="staffId" value="${staffMember.id}"/>
						<input type="submit" value="View Details">
					</g:form>
					
					<g:form action="deleteStaff">
						<g:hiddenField name="staffId" value="${staffMember.id}"/>
						<input type="submit" value="Delete" onclick="return confirm('Permanently delete staff member details?')">
					</g:form>
				
				</td>
			</tr>
		
	</g:each>
</table>
</div>
<div id="newStaffFormBox">
	<h4>Form for adding new staff member</h4>
	<g:form id="newStaffForm" action="saveStaff">
		<fieldset>
			<input type="text" name="name" required placeholder="Full Name"/><br/>
			<input type="text" name="phone" required placeholder="Telephone Number"/><br/>
			<input type="text" name="email" required placeholder="E-Mail"/><br/>
			<input type="text" name="username"required placeholder="Username"/><br/>
			<input type="text" name="password" required placeholder="Password"/><br/>
			<g:each in="${Role.list()}" var="role">
				<g:checkBox name="role" value="${role.id}" checked="false"/><label>${role.name}</label>
			</g:each>
		</fieldset>
		<input type="submit" value="add"/>
	</g:form>
		
</div>
<br/><br/>

<g:form controller="tour" action="generateTours">
	<input type="submit" value="Generate Tours"/>
</g:form>

</body>
</html>
