<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Query results</title>
<link rel="stylesheet" href="Style.css" type="text/css" />
</head>
<body>
	<h3>Query results</h3>
	<br>

	<table>
		<s:iterator value="qs" status="itStatus">
			<tr>
				<td align="center"><s:property value="toString()" /></td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<br>
	<a href="index.jsp">Back</a>
</body>
</html>