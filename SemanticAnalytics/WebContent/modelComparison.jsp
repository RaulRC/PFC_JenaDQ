<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Model Comparison</title>
<jsp:include page="/style/templates/styles.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/style/templates/header.jsp"></jsp:include>

	<h3>Model Comparison</h3>

	<p>
		En esta sección usted podrá realizar comparaciones sobre distintos
		modelos. Como resultado obtendrá un porcentaje de similitud así como
		un modelo que contendrá todas las <b>triplas no comunes</b>. Dicho
		modelo resultado podrá ser descargado en formatos <b>RDF</b> y <b>N3</b>.
	</p>

	<p>Para comenzar basta con que cargue dos modelos en los campos
		correspondientes y pulse el botón para obtener los resultados de la
		comparación.</p>

	<s:form action="modelComparison" method="post"
		enctype="multipart/form-data">
		<s:file name="file" label="Model A" />
		<s:file name="file" label="Model B" />
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
	<jsp:include page="/style/templates/footer.jsp"></jsp:include>
</body>
</html>