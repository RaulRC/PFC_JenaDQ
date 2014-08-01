<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About</title>
<jsp:include page="/style/templates/styles.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/style/templates/header.jsp"></jsp:include>

	<h3>About...</h3>

	<ul>
		<li><b>Author</b>: Raúl Reguillo Carmona
			<ul>
				<li><b>E-mail</b>: <a
					href="mailto:raul.reguillo@gmail.com?Subject=LODQA%20Tool%20Webpage">raul.reguillo@gmail.com</a></li>
			</ul></li>
		<li><b>Director</b>: Dr. Ismael Caballero Muñoz-Reja</li>
		<li><b>University</b>: <a href="http://www.uclm.es">UCLM</a>
			(Universidad de Castilla-La Mancha)</li>
		<li><b>LODQA Tool Technology Stack:</b>
			<ul>
				<li><b>Core</b>: <a href="http://jena.apache.org">Apache
						Jena Framework</a> and <a href="javadoc/index.html">Jena DQ</a></li>
				<li><b>Triple Store</b>: <a
					href="http://jena.apache.org/documentation/tdb/index.html">Apache
						Jena TDB</a></li>
				<li><b>Triple Server/Endpoint</b>: <a
					href="http://jena.apache.org/documentation/serving_data/index.html">Apache
						Jena Fuseki</a></li>
				<li><b>Web Development</b>: <a
					href="http://struts.apache.org/development/2.x/">Apache Struts
						2</a></li>
			</ul></li>

		<li><b>Last update</b>: 27-VII-2014</li>
	</ul>
	<br>

	<a href="https://twitter.com/Einath_Kelaven"
		class="twitter-follow-button" data-show-count="false">Follow
		@Einath_Kelaven</a>
	<script>
		!function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/
					.test(d.location) ? 'http' : 'https';
			if (!d.getElementById(id)) {
				js = d.createElement(s);
				js.id = id;
				js.src = p + '://platform.twitter.com/widgets.js';
				fjs.parentNode.insertBefore(js, fjs);
			}
		}(document, 'script', 'twitter-wjs');
	</script>

	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/es_ES/sdk.js#xfbml=1&version=v2.0";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	<br>
	<div class="fb-like"
		data-href="http://212.122.105.160/SemanticAnalytics"
		data-layout="standard" data-action="like" data-show-faces="true"
		data-share="true"></div>

	<br>

	<div class="fb-follow" data-href="http://www.facebook.com/einath.kelaven"
		data-colorscheme="light" data-layout="standard" data-show-faces="true"></div>

	<br>
	<script type="text/javascript"
		src="https://apis.google.com/js/plusone.js"></script>
	<div class="g-plus"
		data-href="https://plus.google.com/u/0/+RaúlReguillo/" rel="author"></div>

	<br>
	<br>
	<br>
	<br>
	<br>

	<br>

	<jsp:include page="/style/templates/footer.jsp"></jsp:include>
</body>
</html>