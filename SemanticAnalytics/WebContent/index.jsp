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
	<h3>
		Qué es <i>LODQA Tool (Linked Open Data Quality Assessment)</i>
	</h3>
	<p>
		<b>LODQA</b> es una herramienta creada para la evaluación de calidad
		de <b>Linked Open Data</b>. Esta herramienta hace uso del framework <a
			href="http://jena.apache.org"> Apache Jena</a> así como de una <b>extensión</b>
		creada para tal fin, denominada <b>Jena DQ</b>. El objetivo principal
		es realizar análisis de calidad de datos considerando el contexto de
		uso de los mismos. Para ello, se facilita una serie de primitivas
		encapsuldadas en la extensión del framework, una <a
			href="javadoc/index.html">API</a> y un vocabulario de resultados.
		Tanto la aplicación <b>LODQA Tool</b> como <b>Jena DQ</b> se enmarcan
		en el proyecto fin de carrera (PFC) del alumno <b>Raúl Reguillo
			Carmona</b>.
	</p>
	<img src="figures/jena-architecture.png"  height="50%" width="50%" style="padding-left: 150px;" />
	<h3>
		Cómo funciona <i>LODQA Tool</i>
	</h3>

	<p>
		<b>LODQA Tool</b> analiza la calidad de los datos según dos
		dimensiones de calidad: <b>Completeness</b> y <b>Accessibility</b>.
		Para conocer los detalles del análisis contextual, así como de ambas
		dimensiones de calidad, diríjase a la sección <a
			href="completenessAssessment.jsp"> Contextual Assessment</a>.
	</p>
	<h4>Realizar una evaluación</h4>

	<p>Existen dos formas de realizar una evaluación de calidad de
		datos:</p>
	<ul>
		<li><b><u>Evaluar un único modelo de datos</u></b>: se
			especificará una única evaluación, indicando una <b>URI</b>, un <b>Endpoint</b>,
			un <b>identificador de evaluación</b>, conjunto de <b>dimensiones
				de calidad</b> a evaluar y el conjunto de <b>reglas</b> que se
			utilizarán. Esta sección permite conocer algunas <b>propiedades
				del modelo</b> así como realizar pequeñas <b>consultas</b> sobre el
			mismo.</li>
		<li><b><u>Realizar un plan de evaluación</u></b>: se creará un
			nuevo plan de evaluación como un <b>conjunto de evaluaciones</b>
			iniciales. Los datos de cada evaluación se indicarán tal y como se
			haría en el apartado anterior, pero se añadirán al plan en lugar de
			ser ejecutados al momento. Una vez se tenga el plan completamente
			construido, se puede llevar a cabo el análisis mediante la e<b>jecución
				del plan de evaluación</b>.</li>
	</ul>

	<p>
		Para más información, diríjase a la sección <b>Assessment &
			Operations</b> en el apartado correspondiente.
	</p>
	<h4>Obtener los resultados de la evaluación</h4>

	<p>Una vez se ha llevado a cabo la evaluación, podrá consultar los
		resultados de la misma de dos formas:
	<ul>
		<li>La aplicación le mostrará un cuadro que contiene los
			resultados numéricos de las métricas así como los contextuales,
			inferidos a través de las reglas indicadas durante el proceso de
			evaluación.</li>
		<li>Podrá descargar dichos resultados como un <b>modelo de
				datos semánticos</b> en formato <b>RDF</b> y <b>N3</b>. El modelo
			generado obedecerá al vocabulario indicado en <b>Jena DQ</b> para la
			evaluación de calidad de datos y será almacenado a su vez en un
			contenedor de triplas, facilitando la URI a través de la cual dicho
			modelo será accesible y podrá consultarse a través del endpoint de la
			aplicación.
		</li>
	</ul>
	</p>
	<h4>Comparación de modelos y resultados</h4>

	<p>
		Si desea comparar dos modelos, bien de resultados o de otros datos,
		diríjase a la sección <a href="modelComparison.jsp">Model
			Comparisons</a> y especifique los modelos que desea comparar. Dichos
		modelos deberá cargarlos desde su equipo y deben estar en formato <b>RDF</b>.
	</p>

	<jsp:include page="/style/templates/footer.jsp"></jsp:include>
</body>
</html>