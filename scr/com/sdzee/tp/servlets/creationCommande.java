package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.sdzee.tp.beans.Client;
import com.sdzee.tp.beans.Commande;

/**
 * Servlet implementation class creationCommande
 */
@WebServlet("/creationCommande")
public class creationCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public creationCommande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nom = request.getParameter("nomClient");
		String prenom = request.getParameter("prenomClient");
		String adresse = request.getParameter("adresseClient");
		String telephone = request.getParameter("telephoneClient");
		String email = request.getParameter("emailClient");
		
		Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setAdresse(adresse);
		client.setTelephone(telephone);
		client.setEmail(email);
		
		DateTime dt = new DateTime();
		DateTimeFormatter df = DateTimeFormat.forPattern("dd/mm/yyyy HH:mm:ss");
		String date = dt.toString(df);
		Double montant;
		try {
			montant = Double.parseDouble( request.getParameter("montantCommande"));			
		} catch( NumberFormatException e) {
			montant = 0.0;
		}
		String modePaiement = request.getParameter("modePaiementCommande");
		String statutPaiement = request.getParameter("statutPaiementCommande");
		String modeLivraison = request.getParameter("modeLivraisonCommande");
		String statutLivraison = request.getParameter("statutLivraisonCommande");		

		Commande commande = new Commande();
		commande.setClient(client);
		commande.setDate(date);
		commande.setMontant(montant);
		commande.setModePaiement(modePaiement);
		commande.setStatutPaiement(statutPaiement);
		commande.setModeLivraison(modeLivraison);
		commande.setStatutLivraison(statutLivraison);

        String message;
		if ( nom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty() || montant==0 || modePaiement.trim().isEmpty() || modeLivraison.trim().isEmpty() ) {
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> <a href=\"creerCommande.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'un client.";
        } else {
            message = "Commande créée avec succès !";
        }
		
		request.setAttribute("commande", commande);
		request.setAttribute("message", message);

		this.getServletContext().getRequestDispatcher( "/afficherCommande.jsp" ).forward( request, response );	
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
