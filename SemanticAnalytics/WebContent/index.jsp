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
		Qu� es <i>LODQA Tool (Linked Open Data Quality Assessment)</i>
	</h3>
	<p>
		<b>LODQA</b> es una herramienta creada para la evaluaci�n de calidad
		de <b>Linked Open Data</b>. Esta herramienta hace uso del framework <a
			href="http://jena.apache.org"> Apache Jena</a> as� como de una <b>extensi�n</b>
		creada para tal fin, denominada <b>Jena DQ</b>. El objetivo principal
		es realizar an�lisis de calidad de datos considerando el contexto de
		uso de los mismos. Para ello, se facilita una serie de primitivas
		encapsuldadas en la extensi�n del framework, una <a
			href="javadoc/index.html">API</a> y un vocabulario de resultados.
		Tanto la aplicaci�n <b>LODQA Tool</b> como <b>Jena DQ</b> se enmarcan
		en el proyecto fin de carrera (PFC) del alumno <b>Ra�l Reguillo
			Carmona</b>.
	</p>
	<img src="figures/jena-architecture.png"  height="50%" width="50%" style="padding-left: 150px;" />
	<h3>
		C�mo funciona <i>LODQA Tool</i>
	</h3>

	<p>
		<b>LODQA Tool</b> analiza la calidad de los datos seg�n dos
		dimensiones de calidad: <b>Completeness</b> y <b>Accessibility</b>.
		Para conocer los detalles del an�lisis contextual, as� como de ambas
		dimensiones de calidad, dir�jase a la secci�n <a
			href="completenessAssessment.jsp"> Contextual Assessment</a>.
	</p>
	<h4>Realizar una evaluaci�n</h4>

	<p>Existen dos formas de realizar una evaluaci�n de calidad de
		datos:</p>
	<ul>
		<li><b><u>Evaluar un �nico modelo de datos</u></b>: se
			especificar� una �nica evaluaci�n, indicando una <b>URI</b>, un <b>Endpoint</b>,
			un <b>identificador de evaluaci�n</b>, conjunto de <b>dimensiones
				de calidad</b> a evaluar y el conjunto de <b>reglas</b> que se
			utilizar�n. Esta secci�n permite conocer algunas <b>propiedades
				del modelo</b> as� como realizar peque�as <b>consultas</b> sobre el
			mismo.</li>
		<li><b><u>Realizar un plan de evaluaci�n</u></b>: se crear� un
			nuevo plan de evaluaci�n como un <b>conjunto de evaluaciones</b>
			iniciales. Los datos de cada evaluaci�n se indicar�n tal y como se
			har�a en el apartado anterior, pero se a�adir�n al plan en lugar de
			ser ejecutados al momento. Una vez se tenga el plan completamente
			construido, se puede llevar a cabo el an�lisis mediante la e<b>jecuci�n
				del plan de evaluaci�n</b>.</li>
	</ul>

	<p>
		Para m�s informaci�n, dir�jase a la secci�n <b>Assessment &
			Operations</b> en el apartado correspondiente.
	</p>
	<h4>Obtener los resultados de la evaluaci�n</h4>

	<p>Una vez se ha llevado a cabo la evaluaci�n, podr� consultar los
		resultados de la misma de dos formas:
	<ul>
		<li>La aplicaci�n le mostrar� un cuadro que contiene los
			resultados num�ricos de las m�tricas as� como los contextuales,
			inferidos a trav�s de las reglas indicadas durante el proceso de
			evaluaci�n.</li>
		<li>Podr� descargar dichos resultados como un <b>modelo de
				datos sem�nticos</b> en formato <b>RDF</b> y <b>N3</b>. El modelo
			generado obedecer� al vocabulario indicado en <b>Jena DQ</b> para la
			evaluaci�n de calidad de datos y ser� almacenado a su vez en un
			contenedor de triplas, facilitando la URI a trav�s de la cual dicho
			modelo ser� accesible y podr� consultarse a trav�s del endpoint de la
			aplicaci�n.
		</li>
	</ul>
	</p>
	<h4>Comparaci�n de modelos y resultados</h4>

	<p>
		Si desea comparar dos modelos, bien de resultados o de otros datos,
		dir�jase a la secci�n <a href="modelComparison.jsp">Model
			Comparisons</a> y especifique los modelos que desea comparar. Dichos
		modelos deber� cargarlos desde su equipo y deben estar en formato <b>RDF</b>.
	</p>

	<jsp:include page="/style/templates/footer.jsp"></jsp:include>
</body>
</html>