package com.sdzee.tp.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.Client;

public class CreationClientForm {

	public static final String CHAMP_NOM = "nomClient";
	public static final String CHAMP_PRENOM = "prenomClient";
	public static final String CHAMP_ADRESSE = "adresseClient";
	public static final String CHAMP_TELEPHONE = "telephoneClient";
	public static final String CHAMP_EMAIL = "emailClient";

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
	
	
	public Client insertClient(HttpServletRequest request) {

		String nom = getValeurChamp(request, CHAMP_NOM );
		String prenom = getValeurChamp(request, CHAMP_PRENOM );
		String adresse = getValeurChamp(request, CHAMP_ADRESSE );
		String telephone = getValeurChamp(request, CHAMP_TELEPHONE );
		String email = getValeurChamp(request, CHAMP_EMAIL );
		
		Client client = new Client();

		try {
			validationNom(nom);
		} catch (Exception e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		client.setNom(nom);
		
		try {
			validationPrenom(prenom);
		} catch (Exception e) {
			setErreur(CHAMP_PRENOM, e.getMessage());
		}
		client.setPrenom(prenom);

		try {
			validationAdresse(adresse);
		} catch (Exception e) {
			setErreur(CHAMP_ADRESSE, e.getMessage());
		}
		client.setAdresse(adresse);
		
		try {
			validationTelephone(telephone);
		} catch (Exception e) {
			setErreur(CHAMP_TELEPHONE, e.getMessage());
		}
		client.setTelephone(telephone);
		
		try {
			validationEmail(email);
		} catch (Exception e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		client.setEmail(email);

		if (erreurs.isEmpty()) {
			resultat = "Succès de l'inscription";
		} else {
			resultat = "Echec de l'inscription";
		}

		return client;
		
	}

	/* Ensemble des règles de validation
	 * 
	 */

	private void validationNom(String nom) throws Exception {
		if (nom != null) {
			if (nom.trim().length() < 2) {
				throw new Exception("Le nom d'utilisateur doit contenir au moins 2 caractères.");
			}
		} else {
			throw new Exception("Merci d'entrer le nom.");
		}
	}

	private void validationPrenom(String prenom) throws Exception {
		if (prenom!=null && prenom.trim().length() < 2) {
			throw new Exception("Le prenom d'utilisateur doit contenir au moins 2 caractères.");
		}
	}
	
	private void validationAdresse(String adresse) throws Exception {
		if (adresse != null) {
			if (adresse.trim().length() < 10) {
				throw new Exception("L'adresse doit contenir au moins 10 caractères.");
			}
		} else {
			throw new Exception("Merci d'entrer l'adresse.");
		}
	}
	
	private void validationTelephone(String telephone) throws Exception {
		if (telephone != null) {
			if (!telephone.matches("^\\d+$")) {
				throw new Exception( "Le numéro de téléphone doit uniquement contenir des chiffres." );
			} else if (telephone.trim().length() < 4) {
				throw new Exception("Le numéro de téléphone doit contenir au moins 4 chiffres.");
			}			
		} else {
            throw new Exception( "Merci d'entrer un numéro de téléphone." );
		}
	}
	
	private void validationEmail(String email) throws Exception {
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse mail valide.");
			}
		} else {
			throw new Exception("Merci de saisir une adresse mail.");
		}
	}
	
	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur==null || valeur.trim().length()==0) {
			return null;
		} else {
			return valeur.trim();
		}

	}
	
	/* Fin de Ensemble des règles de validation
	 * 
	 */

}
