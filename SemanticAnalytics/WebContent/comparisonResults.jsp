<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Model Comparison</title>
<link rel="stylesheet" href="Style.css" type="text/css" />
</head>
<body>
	<h3>Comparison results</h3>
	<br>
	<br>
	<p>
		<b>Model A:</b>
	</p>
	<table>
		<s:iterator value="modelA" status="itStatus">
			<tr>
				<td align="center"><s:property value="getSubject().toString()" /></td>
				<td align="center"><s:property
						value="getPredicate().toString()" /></td>
				<td align="center"><s:property value="getObject().toString()" /></td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<p>
		<b>Model B:</b>
	</p>
	<table>
		<s:iterator value="modelB" status="itStatus">
			<tr>
				<td align="center"><s:property value="getSubject().toString()" /></td>
				<td align="center"><s:property
						value="getPredicate().toString()" /></td>
				<td align="center"><s:property value="getObject().toString()" /></td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<br>
	<p>
		<b>Model difference:</b>
	</p>
	<table>
		<s:iterator value="resultList" status="itStatus">
			<tr>
				<td align="center"><s:property value="getSubject().toString()" /></td>
				<td align="center"><s:property
						value="getPredicate().toString()" /></td>
				<td align="center"><s:property value="getObject().toString()" /></td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<br>
	
	<b>Models affinity</b>: 
	<table>
		<tr>
			<td> <s:property value="affinity" /> %
			</td>
		</tr>
	</table>
	<br>
	<br>
	<a href="index.jsp">Back</a>
</body>
</html>