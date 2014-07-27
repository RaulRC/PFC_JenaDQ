<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error page</title>
<jsp:include page="/style/templates/styles.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/style/templates/header.jsp"></jsp:include>
	Something was wrong x_X
	<br>
	<br>
	<br>
	<table>
		<tr>
			<td><b><s:property value="errorMsg.toString()" /></b></td>
		</tr>
		<tr>
			<td>Exception: <s:property value="e.getMessage()" /></td>
		</tr>
	</table>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<br>
	<a href="index.jsp">Back</a>
	<jsp:include page="/style/templates/footer.jsp"></jsp:include>
</body>
</html>