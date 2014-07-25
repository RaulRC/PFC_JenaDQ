<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Single URI Assessment</title>
<jsp:include page="/style/templates/styles.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/style/templates/header.jsp"></jsp:include>
	<h3>Use an URI and ENDPOINT (single URI Assessment)</h3>

	<p>
		En esta sección usted podrá realizar una evaluación de calidad de los
		datos respecto de un único modelo. Del mismo modo puede realizar una
		consulta sobre dicho modelo y conocer sus propiedades. Para comenzar,
		únicamente debe indicar una <b>URI</b> y un <b>Endpoint</b>.
	</p>
	<p>
		Si desea probar la aplicación, puede hacer click en el enlace que
		aparece abajo y se abrirá en una nueva pestaña una página aleatoria de
		<a href="http://dbpedia.org">DBPedia</a>. Únicamente deberá copiar
		dicho enlace pero tendrá que <b>sustituir</b> de la URI la palabra <b>page</b>
		por <b>resource</b>. Ejemplo:
	</p>
	<ul>
		<li><b>URI</b>: <a href="http://dbpedia.org/resource/Metallica">http://dbpedia.org/resource/Metallica</a>
		</li>
		<li><b>Endpoint</b>: <a href="http://dbpedia.org/sparql">http://dbpedia.org/sparql</a>
		</li>
	</ul>
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