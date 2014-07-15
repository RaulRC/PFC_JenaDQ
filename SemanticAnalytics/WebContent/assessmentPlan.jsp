<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assessment Plan</title>
<link rel="stylesheet" href="Style.css" type="text/css" />
</head>
<body>
	<h3>Add an Assessment Plan</h3>
	<br>
	<br>
	<br>
	<s:if test="hasActionErrors()">
		<div class="welcome">
			<s:actionmessage />
		</div>
	</s:if>
	<hr />
	<s:form action="assessmentPlanAdd" method="post"
		enctype="multipart/form-data">
		<s:textfield label="URI" name="uri" value="" />
		<s:textfield label="Endpoint" name="endpoint" value="" />
		<s:textfield label="DQAssessment Identifier" name="identifier"
			value="" />
		<s:textfield label="Depth" name="depth" value="0" />
		<s:file name="file" label="Contextual Rules File to upload" />
		<s:file name="file" label="Use Rules File to upload" />
		<s:checkbox name="completeness" fieldValue="true" value="true"
			label="Completeness Dimension" />
		<s:checkbox name="accessibility" fieldValue="true" value="true"
			label="Accessibility Dimension" />
		<s:submit name="dq" value="Add Assessment" />

	</s:form>
	<br>
	<s:form action="assessmentPlanExecute" method="post">
		<s:submit name="dqplan" value="Execute DQ Assessment Plan" />
	</s:form>

	<br>
	<br>
	<table border="1">
		<tr>
			<td><a
				href="http://richard.cyganiak.de/2007/09/random-dbpedia.php?go=1"
				target="_blank"> Pick a random URI [New Tab]</a></td>
			<td><a
				href="http://richard.cyganiak.de/2007/09/random-dbpedia.php"
				target="_blank"> By Richard cyganiak - Random-dbpedia</a></td>
		</tr>
	</table>
	<br>
	<br>

</body>
</html>