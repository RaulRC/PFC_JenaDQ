<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jena App v 0.1</title>
<link rel="stylesheet" href="Style.css" type="text/css" />
</head>
<body>
	<h1>Hello :D</h1>
	<p>The purpose of this app is to get habilities with Apache Jena
		Framework.</p>
	<br>
	<br>
	<p>
		Put directly the <b>URI of the resource</b>
	</p>
	<s:form action="getURI">
		<s:textfield name="uri" label="Put here your URI" />
		<s:submit name="Send" value="Get URI"></s:submit>
	</s:form>
	<br>
	<br>
	<table border="1">
		<tr>
			<td><a
				href="http://richard.cyganiak.de/2007/09/random-dbpedia.php?go=1"
				target="_blank"> Pick a random URI [New Tab] (select Dataset
					format below)</a></td>
			<td><a
				href="http://richard.cyganiak.de/2007/09/random-dbpedia.php"
				target="_blank"> By Richard cyganiak - Random-dbpedia</a></td>
		</tr>
	</table>
	<br>
	<br>
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
	<p>
		<b>Type a R expression</b>:
	</p>
	<s:form action="calculations">
		<s:textfield name="data" label="R expression:" />
		<s:submit />
	</s:form>

	<br>
	<br>
	<p>
		<b>Type a R expression with graphical result</b>:
	</p>
	<s:form action="graph">
		<s:textfield name="data" label="R expression with graphical result:" />
		<s:submit />
	</s:form>

</body>
</html>