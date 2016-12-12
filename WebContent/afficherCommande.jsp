<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fiche commande</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
	<c:import url="/inc/menu.jsp" />
	<div id="corp">
		<%-- Affichage de la chaîne "message" transmise par la servlet --%>
		<p class="info">${ message }</p>
		<%-- Puis affichage des données enregistrées dans le bean "client" transmis par la servlet --%>
		<c:if test="${ !erreur }">
			<%-- Puis affichage des données enregistrées dans le bean "commande" transmis par la servlet --%>
			<p>Client</p>
			<%-- Les 5 expressions suivantes accèdent aux propriétés du client, qui est lui-même une propriété du bean commande --%>
			<p>Nom : ${ commande.client.nom }</p>
			<p>Prénom : ${ commande.client.prenom }</p>
			<p>Adresse : ${ commande.client.adresse }</p>
			<p>Numéro de téléphone : ${ commande.client.telephone }</p>
			<p>Email : ${ commande.client.email }</p>
			<p>Commande</p>
			<p>Date : ${ commande.date }</p>
			<p>Montant : ${ commande.montant }</p>
			<p>Mode de paiement : ${ commande.modePaiement }</p>
			<p>Statut du paiement : ${ commande.statutPaiement }</p>
			<p>Mode de livraison : ${ commande.modeLivraison }</p>
			<p>Statut de la livraison : ${ commande.statutLivraison }</p>
		</c:if>
	</div>
</body>
</html>