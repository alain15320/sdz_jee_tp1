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

import com.sdzee.tp.beans.Commande;
import com.sdzee.tp.forms.CreationCommandeForm;

/**
 * Servlet implementation class CreationCommande
 */
@WebServlet("/creationCommande")
public class CreationCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String ATT_COMMANDE = "commande";
	public static final String ATT_FORM = "form";
	public static final String VUE = "/WEB-INF/creerCommande.jsp";
	
	// -------------------- A verifier
	public static final String CHAMP_NOM = "nomClient";
	public static final String CHAMP_PRENOM = "prenomClient";
	public static final String CHAMP_ADRESSE = "adresseClient";
	public static final String CHAMP_TELEPHONE = "telephoneClient";
	public static final String CHAMP_EMAIL = "emailClient";

	public static final String CHAMP_DATE = "dateCommande";
	public static final String CHAMP_MONTANT = "montantCommande";
	public static final String CHAMP_MODE_PAIEMENT = "modePaiementCommande";
	public static final String CHAMP_STATUT_PAIEMENT = "statutPaiementCommande";
	public static final String CHAMP_MODE_LIVRAISON = "modeLivraisonCommande";
	public static final String CHAMP_STATUT_LIVRAISON = "statutLivraisonCommande";
	
	// public static final String ATT_COMMANDE = "commande";
	public static final String ATT_MESSAGE = "message";
	public static final String ATT_ERREUR = "erreur";
	
	public static final String FORMAT_DATE = "dd/MMM/yyyy HH:mm:ss";
	// -------------------- fin de A verifier
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationCommande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Affichage de la page d'inscription */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Préparation de l'objet formulaire */
		CreationCommandeForm form = new CreationCommandeForm();

        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
		Commande commande = form.insertCommande(request);

		/* Stockage du résultat et des messages d'erreur dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_COMMANDE, commande);

		/* Transmission de la paire d'objets request/response à notre JSP */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
		// ------------- A verifier
		String nom = request.getParameter( CHAMP_NOM );
		String prenom = request.getParameter( CHAMP_PRENOM );
		String adresse = request.getParameter( CHAMP_ADRESSE );
		String telephone = request.getParameter( CHAMP_TELEPHONE );
		String email = request.getParameter( CHAMP_EMAIL );
		
		/* Récupération de la date courante */
		DateTime dt = new DateTime();
		DateTimeFormatter df = DateTimeFormat.forPattern( FORMAT_DATE );
		String date = dt.toString(df);
		Double montant;
		
		try {
			montant = Double.parseDouble( request.getParameter( CHAMP_MONTANT ));			
		} catch( NumberFormatException e) {
			montant = -1.0;
		}
		String modePaiement = request.getParameter( CHAMP_MODE_PAIEMENT );
		String statutPaiement = request.getParameter( CHAMP_STATUT_PAIEMENT );
		String modeLivraison = request.getParameter( CHAMP_MODE_LIVRAISON );
		String statutLivraison = request.getParameter( CHAMP_STATUT_LIVRAISON );		

        String message;
        boolean erreur;
		if ( nom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty() || montant==0 || modePaiement.trim().isEmpty() || modeLivraison.trim().isEmpty() ) {
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> <a href=\"creerCommande.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'un client.";
            erreur = true;
		} else {
            message = "Commande créée avec succès !";
            erreur = false;
        }
		
		// Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setAdresse(adresse);
		client.setTelephone(telephone);
		client.setEmail(email);

		Commande commande = new Commande();
		commande.setClient(client);
		commande.setDate(date);
		commande.setMontant(montant);
		commande.setModePaiement(modePaiement);
		commande.setStatutPaiement(statutPaiement);
		commande.setModeLivraison(modeLivraison);
		commande.setStatutLivraison(statutLivraison);
		
		request.setAttribute( ATT_COMMANDE , commande );
		request.setAttribute( ATT_MESSAGE , message );
		//request.setAttribute( ATT_ERREUR , erreur );

		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );	
		// ------------- fin de A verifier
		
		
	}

}
