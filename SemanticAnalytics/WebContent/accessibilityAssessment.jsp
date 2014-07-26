<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data Quality Dimension: Accessibility</title>
<jsp:include page="/style/templates/styles.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/style/templates/header.jsp"></jsp:include>
	<h3>Data Quality Dimension: Accessibility</h3>
	<h4>Definición</h4>
	<p>
		Se define <i>Accessibility como </i> <i>el grado en el que los
			datos puedan ser accedidos en un contexto de uso específico,
			particularmente por gente que necesite tecnología de apoyo o una
			configuración especial debido a alguna discapacidad.</i> Sin embargo en
		el trabajo realizado se tiene en cuenta el contexto de los datos,
		entendiendo que la calidad medida desde el punto de vista de la
		accesibilidad, se refiere al grado de interconexiones de los datos
		enlazados más que el modo en que se presenta o es accesible para
		grupos concretos de personas.
	</p>

	<h4>Reglas utilizadas</h4>

	<p>
		Para el cálculo de la métrica de esta dimensión, se hace uso de <b>reglas
			contextuales</b> (para valores del contexto) únicamente.
	</p>

	<h4>Breve explicación de funcionamiento</h4>
	<p>
		Dado un conjunto de <i>Linked Open Data</i>, se evaluará la
		accessibilidad contabilizando para cada tripla, cuántos de los
		recursos son enlaces a nuevos recursos en lugar de datos literales. En
		esta métrica no se tiene en cuenta la profundidad del modelo, es
		decir, desreferenciar los recursos que son apuntados y comprobar en
		ese segundo nivel (en general en niveles sucesivos) el estado de las
		interconexiones.
	</p>
	<br>
	<br>
	<br>
	<br>
	<br>



	<jsp:include page="/style/templates/footer.jsp"></jsp:include>
</body>
</html>