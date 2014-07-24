<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DQ Assessment Results</title>
<jsp:include page="/style/templates/styles.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/style/templates/header.jsp"></jsp:include>
	<h3>Results of the DQ Assessment</h3>
	<table>
		<tr>
			<td align="center"><b>DQ Dimension</b></td>
			<td align="center"><b>Dimension metric</b></td>
			<td align="center"><b>Value</b></td>
			<td align="center"><b>Contextual Value</b></td>
		</tr>
		<s:iterator value="mr" status="itStatus">
			<tr>
				<td align="center"><s:property value="getMDimension()" /></td>
				<td align="center"><s:property value="getMName()" /></td>
				<td align="center"><s:property value="getMResult()" /></td>
				<td align="center"><s:property value="getmContextualResult()" /></td>
			</tr>
		</s:iterator>
	</table>
	<hr>
	<br>
	<p><b>URI for results (fuseki Server)</b>: <s:property value="uriAssessment.toString()" /> </p>
<br>
	<b>Download results</b>
	<s:url id="fileDownloadRDF" action="downloadRDF"></s:url>
	<s:url id="fileDownloadN3" action="downloadN3"></s:url>

	<table>
		<tr>
			<td><s:a href="%{fileDownloadRDF}">RDF/XML</s:a></td>
			<td><s:a href="%{fileDownloadN3}">N3</s:a></td>

		</tr>
	</table>
	
	<br>
	<br>
	<br>
	<a href="index.jsp">Back</a>
	<jsp:include page="/style/templates/footer.jsp"></jsp:include>
</body>
</html>