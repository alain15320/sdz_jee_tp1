<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fiche client</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
	<c:import url="/inc/menu.jsp" />
	<div id="corp">
		<%-- Affichage de la chaîne "message" transmise par la servlet --%>
		<p class="${empty form.erreurs ? 'succes' : 'erreur' }">${form.resultat}</p>
		<%-- Puis affichage des données enregistrées dans le bean "client" transmis par la servlet --%>
		<p>Nom : <c:out value="${client.nom}"/></p>
		<p>Prénom : <c:out value="${client.prenom}"/></p>
		<p>Adresse : <c:out value="${client.adresse}"/></p>
		<p>Numéro de téléphone : <c:out value="${client.telephone}"/></p>
		<p>Email : <c:out value="${client.email}"/></p>
	</div>
</body>
</html>