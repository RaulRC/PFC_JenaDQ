<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data from the URI</title>
<link rel="stylesheet" href="Style.css" type="text/css" />
</head>
<body>
	<h3>Assessment</h3>
	<br>
	<a href="<s:property value="uri.toString()" />"> <s:property
			value="uri.toString()" />
	</a>
	<br>
	<br>
	<s:form action="dqassessment" method="post"
		enctype="multipart/form-data">
		<s:textfield label="DQAssessment Identifier" name="identifier" />
		<s:textfield label="Depth" name="depth" />
		<s:file name="file" label="Contextual Rules File to upload" />
		<s:file name="file" label="Use Rules File to upload" />

		<s:checkbox name="completeness" fieldValue="true"
			label="Completeness Dimension" />
		<s:checkbox name="accessibility" fieldValue="true"
			label="Accessibility Dimension" />
		<s:submit name="dq" value="DQ Assessment" />

	</s:form>
	<br>
	<hr />
	<br>
	<h3>Query Model</h3>
	<br>
	<s:form action="queryModel">
		<s:textarea label="Query" name="dataQuery" cols="100" rows="15" />
		<s:submit name="Send" value="Query"></s:submit>
	</s:form>
	<br>
	<br>

	<hr />
	<br>
	<h3>Model Properties</h3>
	<br>
	<table border="1">
		<s:iterator value="modelProperties" status="itStatus">
			<tr>
				<td align="center"><a href="<s:property value="toString()" />">
						<s:property value="toString()" />
				</a></td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>