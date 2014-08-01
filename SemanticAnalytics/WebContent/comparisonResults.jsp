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

	<script language="javascript" type="text/javascript">
		function mostrarTabla(tr) {
			var item_tr = document.getElementById(tr);

			if (item_tr) {
				if (item_tr.style.display == "none") {
					item_tr.style.display = "";

				} else {
					item_tr.style.display = "none";
				}
			}
		}
	</script>



	<jsp:include page="/style/templates/header.jsp"></jsp:include>
	<h3>Comparison results</h3>
	<br>
	<p>A continuación puede encontrar la totalidad de las triplas de
		los modelos A y B así como un modelo que resulta de la comparación de
		éstos. Dicho modelo de comparación puede ser descargado pulsando en el
		link correspondiente. También encontrará un porcentaje de similitud
		entre ambos modelos.</p>
	<br>
	<table onclick="javascript:mostrarTabla('modeloA');"
		style="cursor: pointer; cursor: hand;">
		<tr>
			<td>Display Model A</td>
		</tr>
	</table>
	<table id="modeloA" style="display: none;">
		<tr>
			<td align="center"><b>Subject</b></td>
			<td align="center"><b>Predicate</b></td>
			<td align="center"><b>Object</b></td>
		</tr>
		<s:iterator value="modelA" status="itStatus">
			<tr>
				<td align="left"><s:property value="getSubject().toString()" /></td>
				<td align="left" style="padding-left: 12px;"><s:property
						value="getPredicate().toString()" /></td>
				<td align="left" style="padding-left: 12px;"><s:property
						value="getObject().toString()" /></td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<table onclick="javascript:mostrarTabla('modeloB');"
		style="cursor: pointer; cursor: hand;">
		<tr>
			<td>Display Model B</td>
		</tr>
	</table>
	<table id="modeloB" style="display: none;">
		<tr>
			<td align="center"><b>Subject</b></td>
			<td align="center"><b>Predicate</b></td>
			<td align="center"><b>Object</b></td>
		</tr>
		<s:iterator value="modelB" status="itStatus">
			<tr>
				<td align="left"><s:property value="getSubject().toString()" /></td>
				<td align="left" style="padding-left: 12px;"><s:property
						value="getPredicate().toString()" /></td>
				<td align="left" style="padding-left: 12px;"><s:property
						value="getObject().toString()" /></td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<table onclick="javascript:mostrarTabla('result');"
		style="cursor: pointer; cursor: hand;">
		<tr>
			<td>Display Result</td>
		</tr>
	</table>
	<table id="result" style="display: none;">
		<tr>
			<td align="center"><b>Subject</b></td>
			<td align="center"><b>Predicate</b></td>
			<td align="center"><b>Object</b></td>
		</tr>
		<s:iterator value="resultList" status="itStatus">
			<tr>
				<td align="left"><s:property value="getSubject().toString()" /></td>
				<td align="left" style="padding-left: 12px;"><s:property
						value="getPredicate().toString()" /></td>
				<td align="left" style="padding-left: 12px;"><s:property
						value="getObject().toString()" /></td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<br>

	<b>Models affinity</b>:
	<table>
		<tr>
			<td><s:property value="affinity" /> %</td>
		</tr>
	</table>
	<br>
	<br>
	<b>Download results</b>
	<s:url id="fileDownloadCompRDF" action="CdownloadRDF"></s:url>
	<s:url id="fileDownloadCompN3" action="CdownloadN3"></s:url>

	<table>
		<tr>
			<td><s:a href="%{fileDownloadCompRDF}">RDF/XML</s:a></td>
			<td><s:a href="%{fileDownloadCompN3}">N3</s:a></td>

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
	<a href="index.jsp">Back</a>

	<jsp:include page="/style/templates/footer.jsp"></jsp:include>
</body>
</html>