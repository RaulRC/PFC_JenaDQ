<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DQ Assessment Results</title>
<link rel="stylesheet" href="Style.css" type="text/css" />
</head>
<body>
	<h3>Results of the DQ Assessment</h3>
	<hr>
	<br>
	<br>
	<table border="1">
		<tr>
			<td align="center"><b>DQ Dimension</b></td>
			<td align="center"><b>Dimension metric</b></td>
			<td align="center"><b>Value</b></td>
			<td align="center"><b>Contextual Value</b></td>
		</tr>
		<tr>
			<td align="center"><s:property value="mr.getMDimension()" /></td>
			<td align="center"><s:property value="mr.getMName()" /></td>
			<td align="center"><s:property value="mr.getMResult()" /></td>
			<td align="center"><s:property value="mr.getContextualResult()" /></td>
		</tr>
	</table>
</body>
</html>