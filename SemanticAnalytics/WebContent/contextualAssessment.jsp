<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contextual Assessment</title>
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
	<h3>Contextual Assessment</h3>

	<p>Existen dos perspectivas respecto a la Calidad de los datos:</p>
	<ul>
		<li><b><i>Meeting Requirements</i></b>: Lo que se refiere a la
			calidad por el diseño, por la arquitectura de los propios datos. Los
			requisitos de los datos son especificados mediante algún tipo de
			modelo como podría ser el <b>E/R</b> en las bases de datos.</li>

		<li><b><i>Fitness for Use</i></b>: se dice que los datos tienen
			calidad si son válidos para el propósito por el que son requeridos.
			Para ello se require modelar de alguna manera el <b>contexto</b> en
			el que los datos son evaluados, puesto que en contextos distintos, la
			calidad de datos puede variar enormemente. Esta es la perspectiva
			adoptada en este trabajo.</li>
	</ul>

	<p>
		El análisis contextual de los datos requiere de por lo tanto de un
		modelado del contexto previo a la evaluación. El modo en que se
		modelará el contexto será mediante <b>reglas de contexto</b> que
		definen una serie de parámetros para los cuales consideraremos los
		datos.
	</p>

	<p>
		Estas <b>reglas de contexto</b> pueden adoptar diferentes formas y
		están especificadas en un lenguaje propio del framework. Para más
		información, consulte en la página web del proyecto <a
			href="http://jena.apache.org">Jena</a>.
	</p>

	<p>En el presente trabajo, se utilizan dos tipos de reglas
		contextuales:</p>
	<ul>
		<li><b>Reglas de Uso</b>: Aquellas que definen qué propiedades se
			esperan en los conjuntos de datos.</li>
		<li><b>Reglas de Contexto (valores de contexto)</b>: Definen
			rangos de valores respecto de propiedades en el conjunto de datos.</li>
	</ul>
	<br>
	<img src="figures/rules.png" style="padding-left: 200px;" />
	<br>
	<br>
	<table onclick="javascript:mostrarTabla('useRules');"
		style="cursor: pointer; cursor: hand;">
		<tr>
			<td>Mostrar ejemplo de reglas de uso</td>
		</tr>
	</table>
	<table id="useRules" style="display: none;">
		<tr>
			<td align="left"><tt>@prefix rdf:
					http://www.w3.org/1999/02/22-rdf-syntax-ns#</tt></td>
		</tr>
		<tr>
			<td align="left"><tt>[rule1: ( ?x rdf:type ?y ) -> print(?x
					'has RDF TYPE property' ?y) ]</tt></td>
		</tr>
	</table>
	<br>
	<table onclick="javascript:mostrarTabla('cRules');"
		style="cursor: pointer; cursor: hand;">
		<tr>
			<td>Mostrar ejemplo de reglas de valores de contexto</td>
		</tr>
	</table>
	<table id="cRules" style="display: none;">
		<tr>
			<td align="left"><tt>@prefix rdf:
					http://www.w3.org/1999/02/22-rdf-syntax-ns#</tt></td>
		</tr>
		<tr>
			<td align="left"><tt>@prefix ex:
					http://www.dqassessment.org/ontologies/2014/9/DQA.owl#</tt></td>
		</tr>

		<tr>
			<td align="left"><tt>[completeness_low: (?x
					ex:CompletenessMeasure ?y) lessThan(?y, 0.3) ge(?y, 0.0) -> print (
					?x 'has LOW value >>>> ' ?y)]</tt></td>
		</tr>
	</table>

	<br>
	<p>
		Estas reglas son aplicadas a las dos dimensiones de calidad
		implementadas en este proyecto: <a href="accessibilityAssessment.jsp">Accessibility</a>
		y <a href="completenessAssessment.jsp">Completeness</a>. Si desea
		conocer más información acerca de los detalles de cada dimensión,
		diríjase a la sección correspondiente.
	</p>

	<jsp:include page="/style/templates/footer.jsp"></jsp:include>
</body>
</html>