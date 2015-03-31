<%@ page import="ghost.Role"%>

<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="ghostview"/>
	<asset:stylesheet src="structure.css"/>
</head>
<body>

<div id="staffMemberBox" class="panel panel-primary">
<h4>List of inactive members</h4>
<g:link action="managerDashboard">Go back</g:link>
<table class="table table-striped table condensed">
	<tr class="primary"> 
		
		<th><span class="glyphicon glyphicon-user text-primary"></span>Name</th>
		<th>Phone</th>
		<th><span class="glyphicon glyphicon-envelope text-success"></span>E-mail</th>
		<th><span class="glyphicon glyphicon-eye-open text-danger"></span>Current Roles</th>
		<th>Add Role</th>
		<th>Other Actions</th>
	<tr class="info">
	<g:each in="${inactiveStaffList}" var="staffMember">
			<tr class="light"> 
				<td>${staffMember.name}</td>
				<td>${staffMember.phone}</td>
				<td>${staffMember.email}</td>
				<td>
					<g:each in="${staffMember.roles()}" var="role">
					<g:form controller="staff" action="removeRole">
						<g:hiddenField name="roleId" value="${role.id}"/> 
						<g:hiddenField name="staffId" value="${staffMember.id}"/>
						<input type="submit" value="${role.name}"/>
					</g:form>
					</g:each>
					Click role to remove.
				</td>
				<td>
					<g:form controller="staff" action="addRole">
						<g:hiddenField name="staffId" value="${staffMember.id}"/>
						<g:select name="roleToAdd" from="${Role.list()}" optionKey="id" optionValue="name" noSelection="['':'Select...']" onChange="this.form.submit()"/>
					</g:form>
				</td>
				<td>
					<ul class="list-inline">
					<li>
					<g:form controller="staff" action="viewStaff">
						<g:hiddenField name="staffId" value="${staffMember.id}"/>
						<input type="submit" value="Edit Details" class="btn btn-lg btn-info btn-xs">
					</g:form>
					</li>
					<li>
					<g:form controller="staff" action="toggleStaffStatus">
						<g:hiddenField name="staffId" value="${staffMember.id}"/>
						<input type="submit" value="Set active" onclick="return confirm('Set staff status to active?')" class="btn btn-lg btn-info btn-xs">
					</g:form>
					</li>
					</ul>
				</td>
			</tr>
		
	</g:each>
</table>
<g:link action="managerDashboard">Go back</g:link>
</div>
</body>
</html>
