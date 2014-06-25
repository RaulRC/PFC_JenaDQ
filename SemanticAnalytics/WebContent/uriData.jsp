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
	<h3>Properties from the URI</h3>
	[
	<s:property value="format" />
	] -
	<a href="<s:property value="uri.toString()" />"> <s:property
			value="uri.toString()" />
	</a>
	<br>
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
	<s:form action="queryModel">
		<s:textarea label="Query" name="dataQuery" cols="100" rows="15" />
		<s:submit name="Send" value="Query"></s:submit>
	</s:form>
	<br>
	<br>

	<hr />
	<s:form action="dqassessment" method="post" enctype="multipart/form-data">
		<s:textfield label="Depth" name="depth"/>
		<s:file name="file" label="Use Rules File to upload" />
		<s:file name="file" label="Contextual Rules File to upload" />
		<s:submit name="dq" value="Dq Assessment" />
	</s:form>

	<hr />
</body>
</html>