<html>
	<body>
		<table>
			<tr>
				<g:each in="${message}" var="meh">
					<tr>
						<td>
							${meh}
						</td>
					</tr>
				</g:each>
			</tr>
		</table>
		<g:link controller="generator">Write Changes to Database.</g:link>
	</body>
</html>