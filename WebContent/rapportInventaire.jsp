<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rapport d'inventaire</title>
</head>
<body>
	<c:import url="inventaire.xml" varReader="documentXML">
		<x:parse var="doc" doc="${documentXML}"></x:parse>

		<x:forEach var="element" select="$doc/inventaire/livre">
			Auteur : <x:out select="$element/auteur"></x:out>
			<br>
			Titre : <x:out select="$element/titre"></x:out>
			<br>
			Date : <x:out select="$element/date"></x:out>
			<br>
			Prix : <x:out select="$element/prix"></x:out>
			<br>
			Quantité en stock : <x:out select="$element/stock"></x:out>
			<br>
			Quantité minimum : <x:out select="$element/minimum"></x:out>
			<br>
			<x:if select="$element[stock<minimum]">
			<strong>Attention il n'y a pas assez d'articles en stock</strong><br>
			</x:if>
			<hr>
		</x:forEach>
	</c:import>
</body>
</html>