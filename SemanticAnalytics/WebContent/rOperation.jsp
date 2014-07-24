<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Operations with R</title>
<jsp:include page="/style/templates/styles.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/style/templates/header.jsp"></jsp:include>

<h3>Using The R-Project for statistical computing</h3>
Lore Ipsum
	<p>
		<b>Type a R expression</b>:
	</p>
	<s:form action="calculations">
		<s:textfield name="data" label="R expression" />
		<s:submit />
	</s:form>

	<br>
	<br>
	<p>
		<b>Type a R expression with graphical result</b>:
	</p>
	<s:form action="graph">
		<s:textfield name="data" label="R expression with graphical result" />
		<s:submit />
	</s:form>

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
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
<jsp:include page="/style/templates/footer.jsp"></jsp:include>
</body>
</html>