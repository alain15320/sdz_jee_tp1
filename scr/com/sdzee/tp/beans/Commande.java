package com.sdzee.tp.beans;

public class Commande {

    private Client client;
	private String date;
    private Double montant;
    private String modePaiement;
    private String statutPaiement;
    private String modeLivraison;
    private String statutLivraison;

    public Commande() {
    	
    }
    
    public Commande(Client client, String date, Double montant,
			String modePaiement, String statutPaiement, String modeLivraison,
			String statutLivraison) {
		this.client = client;
		this.date = date;
		this.montant = montant;
		this.modePaiement = modePaiement;
		this.statutPaiement = statutPaiement;
		this.modeLivraison = modeLivraison;
		this.statutLivraison = statutLivraison;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}

	public String getStatutPaiement() {
		return statutPaiement;
	}

	public void setStatutPaiement(String statutPaiement) {
		this.statutPaiement = statutPaiement;
	}

	public String getModeLivraison() {
		return modeLivraison;
	}

	public void setModeLivraison(String modeLivraison) {
		this.modeLivraison = modeLivraison;
	}

	public String getStatutLivraison() {
		return statutLivraison;
	}

	public void setStatutLivraison(String statutLivraison) {
		this.statutLivraison = statutLivraison;
	}

	@Override
	public String toString() {
		return "Commande [client=" + client + ", date=" + date + ", montant="
				+ montant + ", modePaiement=" + modePaiement
				+ ", statutPaiement=" + statutPaiement + ", modeLivraison="
				+ modeLivraison + ", statutLivraison=" + statutLivraison + "]";
	}
  
}
