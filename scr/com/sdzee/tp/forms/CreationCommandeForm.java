package com.sdzee.tp.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.sdzee.tp.beans.Client;
import com.sdzee.tp.beans.Commande;

public class CreationCommandeForm {

	public static final String CHAMP_DATE = "dateCommande";
	public static final String CHAMP_MONTANT = "montantCommande";
	public static final String CHAMP_MODE_PAIEMENT = "modePaiementCommande";
	public static final String CHAMP_STATUT_PAIEMENT = "statutPaiementCommande";
	public static final String CHAMP_MODE_LIVRAISON = "modeLivraisonCommande";
	public static final String CHAMP_STATUT_LIVRAISON = "statutLivraisonCommande";

	public static final String FORMAT_DATE = "dd/MMM/yyyy HH:mm:ss";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	public Commande insertCommande(HttpServletRequest request) {

		CreationClientForm clientForm = new CreationClientForm();
		Client client = clientForm.insertClient(request);
		erreurs = clientForm.getErreurs();

		/* Récupération de la date courante */
		DateTime dt = new DateTime();
		DateTimeFormatter df = DateTimeFormat.forPattern(FORMAT_DATE);
		String date = dt.toString(df);

		String montant = getValeurChamp(request, CHAMP_MONTANT);
		String modePaiement = getValeurChamp(request, CHAMP_MODE_PAIEMENT);
		String statutPaiement = getValeurChamp(request, CHAMP_STATUT_PAIEMENT);
		String modeLivraison = getValeurChamp(request, CHAMP_MODE_LIVRAISON);
		String statutLivraison = getValeurChamp(request, CHAMP_STATUT_LIVRAISON);

		Commande commande = new Commande();
		commande.setClient(client);
		commande.setDate(date);

		double valeurMontant = -1;
		try {
			valeurMontant = validationMontant(montant);
		} catch (Exception e) {
			setErreur(CHAMP_MONTANT, e.getMessage());
		}
		commande.setMontant(valeurMontant);

		try {
			validationModePaiement(modePaiement);
		} catch (Exception e) {
			setErreur(CHAMP_MODE_PAIEMENT, e.getMessage());
		}
		commande.setModePaiement(modePaiement);

		try {
			validationStatutPaiement(statutPaiement);
		} catch (Exception e) {
			setErreur(CHAMP_STATUT_PAIEMENT, e.getMessage());
		}
		commande.setStatutPaiement(statutPaiement);

		try {
			validationModeLivraison(modeLivraison);
		} catch (Exception e) {
			setErreur(CHAMP_MODE_LIVRAISON, e.getMessage());
		}
		commande.setModeLivraison(modeLivraison);

		try {
			validationStatutLivraison(statutLivraison);
		} catch (Exception e) {
			setErreur(CHAMP_STATUT_LIVRAISON, e.getMessage());
		}
		commande.setStatutLivraison(statutLivraison);

		// Reste à faire

		if (erreurs.isEmpty()) {
			resultat = "Succès de l'inscription";
		} else {
			resultat = "Echec de l'inscription";
		}

		return commande;
	}

	/*
	 * Ensemble des règles de validation
	 * 
	 */

	private double validationMontant(String montant) throws Exception {
		double temp;
		if (montant != null) {
			try {
				temp = Double.parseDouble(montant);
				if (temp < 0) {
					temp=-1;
					throw new Exception("Le montant doit être un nombre positif.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new Exception("Le montant doit être un nombre.");
			}
		} else {
			temp = -1;
			throw new Exception("Merci d'entrer un montant.");
		}
		return temp;
	}

	private void validationModePaiement(String modePaiement) throws Exception {
		if (modePaiement != null) {
			if (modePaiement.trim().length() < 2) {
				throw new Exception("Le mode de paiement doit contenir au moins 2 caractères.");
			}
		} else {
			throw new Exception("Merci d'entrer le mode de paiement.");
		}
	}

	private void validationStatutPaiement(String statutPaiement) throws Exception {
		if (statutPaiement != null) {
			if (statutPaiement.trim().length() < 2) {
				throw new Exception("Le statut de paiement doit contenir au moins 2 caractères.");
			}
		} else {
			throw new Exception("Merci d'entrer le statut de paiement.");
		}
	}

	private void validationModeLivraison(String modeLivraison) throws Exception {
		if (modeLivraison != null) {
			if (modeLivraison.trim().length() < 2) {
				throw new Exception("Le mode de livraison doit contenir au moins 2 caractères.");
			}
		} else {
			throw new Exception("Merci d'entrer le mode de livraison.");
		}
	}

	private void validationStatutLivraison(String statutLivraison) throws Exception {
		if (statutLivraison != null) {
			if (statutLivraison.trim().length() < 2) {
				throw new Exception("Le statut de livraison doit contenir au moins 2 caractères.");
			}
		} else {
			throw new Exception("Merci d'entrer le statut de livraison.");
		}
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}

	}

	/*
	 * Fin de Ensemble des règles de validation
	 * 
	 */

}
