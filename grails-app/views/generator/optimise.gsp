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
		<button>Write Changes to Database.</button>
	</body>
</html>