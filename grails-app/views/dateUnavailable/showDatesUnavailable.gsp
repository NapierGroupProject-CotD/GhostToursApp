<!DOCTYPE html>
<html>
<head>
<asset:stylesheet src="structure.css"/>
<asset:stylesheet src="datepicker.css"/>
<asset:javascript src="datepicker.js"/>
<meta name="layout" content="ghostview"/>
</head>
<body>
<div id="datePickerBox">
	<g:form action="saveDateUnavailable">
		<input type="text" id="inp1" name="date" />
				<script>
					// Attach a datepicker to the above input element
					datePickerController.createDatePicker({
						formElements : {
							"inp1" : "%d/%m/%Y"
						}
					});
				</script>
		<input type="submit" value="Add date"/>
	</g:form>
</div>
<div id="dateListBox">
	<g:if test="${flash.message}">
		<label class="label-danger">${flash.message}</label>
	</g:if>
	<table>
		<tr>
			<th>Unavailable Date</th>
		</tr>
		<g:each in="${datesUnavailable.sort{it.date}}" var="dateUnavailable">
			<tr>
				<td>${dateUnavailable.date.format("d-MMM-yy")}</td>
				<td><g:form action="deleteDateUnavailable">
						<g:hiddenField name="dateUnavailableId" value="${dateUnavailable.id}"/>
						<input type="submit" value="X" onclick="return confirm('Delete date?')"/>
					</g:form>
				</td>
			</tr>
		</g:each>
		
	</table>

</div>
</body>
</html>
