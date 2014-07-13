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
	<s:form action="assessmentPlanAdd" method="post"
		enctype="multipart/form-data">
		<s:textfield label="URI" name="uri" />
		<s:textfield label="Endpoint" name="endpoint" />
		<s:textfield label="DQAssessment Identifier" name="identifier" />
		<s:textfield label="Depth" name="depth" />
		<s:file name="file" label="Use Rules File to upload" />
		<s:file name="file" label="Contextual Rules File to upload" />
		<s:checkbox name="completeness" fieldValue="true"
			label="Completeness Dimension" />
		<s:checkbox name="accessibility" fieldValue="true"
			label="Accessibility Dimension" />
		<s:submit name="dq" value="Add Assessment" />

	</s:form>

	<s:form action="assessmentPlanExecute" method="post">
		<s:submit name="dqplan" value="Execute DQ Assessment Plan" />
	</s:form>
</body>
</html>