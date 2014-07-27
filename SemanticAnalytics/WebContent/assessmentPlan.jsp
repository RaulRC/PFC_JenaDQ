<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assessment Plan</title>
<jsp:include page="/style/templates/styles.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/style/templates/header.jsp"></jsp:include>
	<h3>Add an Assessment Plan</h3>

	<p>
		En esta sección usted podrá realizar una evaluación de calidad de los
		datos respecto de varios modelos. Cada evaluación puede llevarse a
		cabo en distintos <b>endpoint</b> si se desea, siempre que los datos
		sean válidos. Igualmente puede indicar como <b>identificador</b> el
		mismo nombre o puede poner nombres distintos para cada una de las
		evaluaciones. Para comenzar, rellene los campos que aparecen en el
		formulario y presione <b>Add Assessment</b> para añadir esa evaluación
		al plan. Una vez introduzca todas las evaluaciones que desee, bastará
		con pulsar <b>Execute DQ Assessment Plan</b> y esperar los resultados.
	</p>
	<p>
		Si desea probar la aplicación, puede hacer click en el enlace que
		aparece abajo y se abrirá en una nueva pestaña una página aleatoria de
		<a href="http://dbpedia.org">DBPedia</a>. Únicamente deberá copiar
		dicho enlace pero tendrá que <b>sustituir</b> de la URI la palabra <b>page</b>
		por <b>resource</b>. Ejemplo:
	</p>
	<ul>
		<li><b>URI</b>: <a href="http://dbpedia.org/resource/Iron_Maiden">http://dbpedia.org/resource/Iron_Maiden</a>
		</li>
		<li><b>Endpoint</b>: <a href="http://dbpedia.org/sparql">http://dbpedia.org/sparql</a>
		</li>
	</ul>
	<s:if test="hasActionErrors()">
		<div class="welcome">
			<s:actionmessage />
		</div>
	</s:if>
	<script language="javascript" type="text/javascript">
		function init() {
			var item = document.getElementById("loading");
			item.style.display = "";
		}
	</script>
	<img id="loading" src="figures/loading.gif" border=0
		style="position: absolute; width: 10%; text-align: center; top: 600px; right: 500px; display: none">
	<s:form action="assessmentPlanAdd" method="post"
		enctype="multipart/form-data">
		<s:textfield label="URI" name="uri" value="YourUri"
			onclick="this.value='';" pattern="https?://.+" />
		<s:textfield label="Endpoint" name="endpoint" value="YourEndpoint"
			onclick="this.value='';" pattern="https?://.+" />
		<s:textfield label="DQAssessment Identifier" name="identifier"
			value="YourIdentifier" onclick="this.value='';" />
		<s:textfield label="Depth" name="depth" value="1"
			onclick="this.value='';" />
		<s:file name="file" label="Contextual-Values Rules File to upload" />
		<s:file name="file" label="Use Rules File to upload" />
		<s:checkbox name="completeness" fieldValue="true" value="true"
			label="Completeness Dimension" />
		<s:checkbox name="accessibility" fieldValue="true" value="true"
			label="Accessibility Dimension" />
		<s:submit name="dq" value="Add Assessment" />

	</s:form>
	<br>
	<s:form action="assessmentPlanExecute" method="post" onsubmit="javascript:init()">
		<s:submit name="dqplan" value="Execute DQ Assessment Plan" />
	</s:form>

	<br>
	<br>
	<table>
		<tr>
			<td><a
				href="http://richard.cyganiak.de/2007/09/random-dbpedia.php?go=1"
				target="_blank"> Pick a random URI [New Tab]</a></td>
		</tr>
		<tr>
			<td><a
				href="http://richard.cyganiak.de/2007/09/random-dbpedia.php"
				target="_blank"> By Richard cyganiak - Random-dbpedia</a></td>
		</tr>
	</table>
	<br>
	<br>
	<jsp:include page="/style/templates/footer.jsp"></jsp:include>
</body>
</html>