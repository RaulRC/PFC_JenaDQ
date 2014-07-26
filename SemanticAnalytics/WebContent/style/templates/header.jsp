<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<?xml version="1.0" encoding="ISO-8859-1" ?>

<div id="container">
	<div id="intro">
		<div id="pageHeader">
			<h1>
				<span>Linked Open Data Quality Assessment</span>
			</h1>
			<h2>
				<span>Raúl Reguillo Carmona</span>
			</h2>

		</div>

		<div id="quickSummary">
			<%--		<p class="p1"><span>A demonstration of what can be accomplished visually through <acronym title="Cascading Style Sheets">CSS</acronym>-based design.</span></p>
			<p class="p2"><span>Download the sample <a href="http://blog.lebrijo.com/wp-content/uploads/basic_templating.zip" title="Eclipse zip.">eclipse zip file</a>. </span></p>
			<p class="p3"><span>Based on <a href="http://www.csszengarden.com/?cssfile=124/124.css" title="css sample">CSS zend garden sample</a>. </span></p>
			--%>
			<br>
			<p>Assessment & Operations</p>
			<ul>
				<li><a href="<%=request.getContextPath()%>/uriAssessment.jsp">Single
						URI Assessment</a></li>
				<li><s:url id="assessmentPlan" action="assessmentPlanInit"></s:url>
					<s:a href="%{assessmentPlan}">Multiple URI Assessment
						(Assessment Plan)</s:a></li>
				<li><a href="<%=request.getContextPath()%>/modelComparison.jsp">Model
						Comparisons</a></li>
				<li><a href="<%=request.getContextPath()%>/rOperation.jsp">
						R Operations</a></li>
			</ul>
			<HR />
			<p>Data Quality</p>
			<ul>
				<li><a
					href="<%=request.getContextPath()%>/contextualAssessment.jsp">
						Contextual Assessment</a>
					<ul>
						<li><a
							href="<%=request.getContextPath()%>/completenessAssessment.jsp">Completeness
								Dimension</a></li>
						<li><a
							href="<%=request.getContextPath()%>/accessibilityAssessment.jsp">Accessibility
								Dimension</a></li>
					</ul></li>
			</ul>
		</div>
		<%--
		<div id="preamble">
			<h3><span>Preamble</span></h3>
			<p class="p1"><span>A demonstration of what can be accomplished visually through <acronym title="Cascading Style Sheets">CSS</acronym>-based design.</span></p>
			<p class="p2"><span>Download the sample <a href="http://blog.lebrijo.com/wp-content/uploads/basic_templating.zip" title="Eclipse zip.">eclipse zip file</a>. </span></p>
			<p class="p3"><span>Based on <a href="http://www.csszengarden.com/?cssfile=124/124.css" title="css sample">CSS zend garden sample</a>. </span></p>
		</div>
		 --%>
	</div>



	<div id="supportingText">