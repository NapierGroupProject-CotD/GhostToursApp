<%@ page import="ghost.Role"%>

<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="ghostview"/>
	<asset:stylesheet src="structure.css"/>
</head>
<body>

<div id="staffMemberBox" class="panel panel-primary">
<h4>List of all members</h4>
<table class="table table-striped table condensed">
	<tr class="primary"> 
		
		<th><span class="glyphicon glyphicon-user text-primary"></span>Name</th><th>Phone</th><th><span class="glyphicon glyphicon-envelope text-success"></span>E-mail</th><th><span class="glyphicon glyphicon-eye-open text-danger"></span>Role</th><th>Actions</th>
	<tr class="info">
	<g:each in="${staffList}" var="staffMember">
			<tr class="light"> 
				<td>${staffMember.name}</td><td>${staffMember.phone}</td><td>${staffMember.email}</td><td>${mapOfRoles.get(staffMember).name}</td>
				<td>
					<ul class="list-inline">
					<li>
					<g:form action="viewStaff">
						<g:hiddenField name="staffId" value="${staffMember.id}"/>
						<input type="submit" value="View Details" class="btn btn-lg btn-info btn-xs">
					</g:form>
					</li>
					<li>
					<g:form action="deleteStaff">
						<g:hiddenField name="staffId" value="${staffMember.id}"/>
						<input type="submit" value="Delete" onclick="return confirm('Permanently delete staff member details?')" class="btn btn-lg btn-info btn-xs">
					</g:form>
					</li>
					</ul>
				</td>
			</tr>
		
	</g:each>
</table>
</div>
<div id="newStaffFormBox" class="panel-primary">
	<h4 class="panel-heading">Form for adding new staff member</h4>
	<div id="errorMessage">
		<g:if test="${flash.errorMessage}">
			<label>${flash.errorMessage}</label>
		</g:if>
	</div>
	<g:form id="newStaffForm" action="saveStaff" class="well span6">
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

<g:form controller="tour" action="generateTours">
	<input type="submit" value="Generate Tours"/>
</g:form>

</body>
</html>
