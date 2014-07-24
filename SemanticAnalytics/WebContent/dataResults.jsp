<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data Results</title>
<jsp:include page="/style/templates/styles.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/style/templates/header.jsp"></jsp:include>
	<h3>DataQuery results</h3>
	<br>
	<hr>
	<s:form action="graph">
		<p>
			<b>R output:</b>
			<s:property value="result" />
		</p>
	</s:form>
	<br>
	<br>
	<a href="index.jsp">Back</a>
	<jsp:include page="/style/templates/footer.jsp"></jsp:include>
</body>
</html>