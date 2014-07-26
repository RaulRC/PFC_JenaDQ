<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data Quality Dimension: Completeness</title>
<jsp:include page="/style/templates/styles.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/style/templates/header.jsp"></jsp:include>
	<h3>Data Quality Dimension: Completeness</h3>

	<h4>Definición</h4>
	<p>
		Se define <i>Completeness</i> como <i>el grado en el que toda la
			información requerida está presente en un conjunto de datos
			particular</i>. En general, esta definición se puede extender sobre otros
		factores tales como profundidad de los datos, anchura y alcance para
		la tarea que se quiera realizar.
	</p>
	<p>
		Por otro lado, la norma ISO 25012 define <i>Completeness</i> como <i>el
			grado en el que los datos asociados con una entidad tienen valores
			para todos los atributos esperados e instancias relacionadas en un
			contexto de uso específico</i>.
	</p>
	<p>
		Para un conjunto de datos (que denominaremos grafo), se ha
		especificado <i>Completeness</i> de la siguiente manera: dado un nivel
		de profundidad n en el grafo del conjunto de datos, se define
		completeness como el grado en el que para un determinado conjunto de
		propiedades existen valores que las describan, para cada nivel
		comprendido entre 0 (dataset origen) y n (n-ésima expansión de los
		nodos del grafo, evitando ciclos) .
	</p>

	<p>De esta manera, si P es un conjunto de propiedades que deben
		poseer valor y n es el nivel de profundidad hasta donde interesa
		evaluar esta dimensión, se obtendrán los siguientes resultados:</p>
	<ul>
		<li>Nivel 0 dataset origen: el valor de completeness en este
			punto será siempre dicotómico: {0, 1}, puesto que en el momento en
			que una de esas propiedades se encuentre sin valor, condicionará el
			resultado.</li>
		<li>Niveles 1 - N: el valor oscilará entre [0,1], obteniendo así
			una métrica capa por capa según la profundidad en la que se esté
			evaluando.</li>
	</ul>
	<h4>Reglas utilizadas</h4>

	<p>
		Para el cálculo de la métrica de esta dimensión, se hace uso de <b>reglas
			contextuales</b> (para valores del contexto) y <b>reglas de uso</b> con
		el fin de especificar qué propiedades requerirán un valor en los
		modelos.
	</p>

	<h4>Breve explicación de funcionamiento</h4>
	<p>
		Dado un conjunto de <i>Linked Open Data</i>, se evaluará la
		completitud contabilizando para cada modelo en cada nivel si reúne los
		valores en las propiedades indicadas en la regla. Así, el modelo base
		como se ha explicado, contará con un
		<tt>Sí</tt>
		o
		<tt>No</tt>
		como resultado, mientras que en los niveles sucesivos se contará
		cuántos de los modelos cumplen la regla y cuántos no, obteniendo así
		una métrica comprendida entre [0,1].
	</p>

	<p>Una vez hallada la métrica, el siguiente paso es hacer uso de
		las reglas de valores de contexto para evaluar los niveles de calidad
		contextual.</p>
	<br>

	<jsp:include page="/style/templates/footer.jsp"></jsp:include>
</body>
</html>