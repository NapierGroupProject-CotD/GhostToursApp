<%@ page import="ghost.Role"%>

<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="ghostview"/>
	<asset:stylesheet src="structure.css"/>
</head>
<body>

<div id="staffMemberBox" class="panel panel-primary">
<h4>List of active members</h4>
<g:link action="viewInactiveStaff">View list of inactive staff</g:link>
<table class="table table-striped table condensed">
	<tr class="primary"> 
		
		<th><span class="glyphicon glyphicon-user text-primary"></span>Name</th>
		<th>Phone</th>
		<th><span class="glyphicon glyphicon-envelope text-success"></span>E-mail</th>
		<th><span class="glyphicon glyphicon-eye-open text-danger"></span>Current Roles</th>
		<th>Add Role</th>
		<th>Other Actions</th>
	<tr class="info">
	<g:each in="${activeStaffList}" var="staffMember">
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
						<input type="submit" value="Set inactive" onclick="return confirm('Set staff status to inactive?')" class="btn btn-lg btn-info btn-xs">
					</g:form>
					</li>
					</ul>
				</td>
			</tr>
		
	</g:each>
</table>
<g:link action="viewInactiveStaff">View list of inactive staff</g:link>
</div>
<div id="newStaffFormBox" class="panel-primary">
	<h4 class="panel-heading">Form for adding new staff member</h4>
	<div id="errorMessage">
		<g:if test="${flash.errorMessage}">
			<label>${flash.errorMessage}</label>
		</g:if>
	</div>
	<g:form id="newStaffForm" controller="staff" action="saveStaff" class="well span6">
		<fieldset>
			<input type="text" name="name" required placeholder="Full Name"/><br/>
			<input type="text" name="phone" required placeholder="Telephone Number"/><br/>
			<input type="email" name="email" required placeholder="E-Mail"/><br/>
			<input type="text" name="username"required placeholder="Username"/><br/>
			<g:hiddenField name="password" value="changeme"/>
			<g:each in="${Role.list()}" var="role">
				<g:checkBox name="role" value="${role.id}" checked="false"/><label>${role.name}</label>
			</g:each>
		</fieldset>
		<input type="submit" value="add"/>
	</g:form>
		
</div>
<br/><br/>
</body>
</html>
