<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jena App v 1.0</title>
<jsp:include page="/style/templates/styles.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/style/templates/header.jsp"></jsp:include>
	<h1>Introducción</h1>
	<table>
		<tr>
			<td><s:a href="javadoc/index.html">API SemDQ - Jena DQ </s:a></td>
		</tr>
	</table>
	<%-- 
	<p>
		Put directly the <b>URI of the resource</b>
	</p>
	<s:form action="getURI">
		<s:textfield name="uri" label="Put here your URI" />
		<s:submit name="Send" value="Get URI"></s:submit>
	</s:form>
	
	<br>
	<p>
		<b>Upload a file</b> from your computer
	</p>
	<s:form action="upload" method="post" enctype="multipart/form-data">
		<s:file name="upload" label="File to upload" />
		<s:submit />
	</s:form>
	<br>
	<br>
	
	--%>
	<br>
	<h3>Use an URI and ENDPOINT (single URI Assessment)</h3>
	<s:form action="uriEndpoint" method="post"
		enctype="multipart/form-data">
		<s:textfield name="uri" label="Put your URI here " />
		<s:textfield name="endpoint" label="Put your ENDPOINT here " />
		<s:submit />
	</s:form>
	<br>
	<table>
		<tr>
			<td><a
				href="http://richard.cyganiak.de/2007/09/random-dbpedia.php?go=1"
				target="_blank"> Pick a random URI [New Tab]</a></td>
			<td><a
				href="http://richard.cyganiak.de/2007/09/random-dbpedia.php"
				target="_blank"> By Richard cyganiak - Random-dbpedia</a></td>
		</tr>
	</table>
	<br>
	<br>
	<br>
	
		<h3>Create an Assessment Plan (multiple URI Assessment)</h3>


	<s:url id="initAssessmentPlan" action="assessmentPlanInit"></s:url>

	<table>
		<tr>
			<td><s:a href="%{initAssessmentPlan}">Multiple URI - Multiple
					Dimension Assessment</s:a></td>
		</tr>

	</table>
	<br>
	<br>

		<h3>Model Comparison</h3>
	
	<s:form action="modelComparison" method="post"
		enctype="multipart/form-data">
		<s:file name="file" label="Model A" />
		<s:file name="file" label="Model B" />
		<s:submit />
	</s:form>
	<br>
	<br>
	<br>
	<hr />
	<br>
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

<jsp:include page="/style/templates/footer.jsp"></jsp:include>
</body>
</html>