package fr.eni.encheres.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ArticleVendu {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private String etatVente;
	private int utilisateur;
	private Utilisateur vendeur;
	private Enchere enchere;
	private Categorie categorie;
	private Retrait retrait;
	

	// - - - - - - - - - - CONSTRUCTEURS - - - - - - - - - -

	// CONSTRUCTEUR VIDE
	public ArticleVendu() {

	}

	// CONSTRUCTEUR TOTAL
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, String etatVente, int utilisateur, Enchere enchere,
			Categorie categorie, Retrait retrait) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = miseAPrix;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.enchere = enchere;
		this.categorie = categorie;
		this.retrait = retrait;
	}

	// CONSTRUCTEUR DANS LE NO ARTICLE
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, String etatVente, int utilisateur, Enchere enchere,
			Categorie categorie, Retrait retrait) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.enchere = enchere;
		this.categorie = categorie;
		this.retrait = retrait;
	}
	
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, String etatVente, int utilisateur,
			Categorie categorie, Retrait retrait) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = miseAPrix;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.retrait = retrait;
	}
	
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, int utilisateur, Retrait retrait) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = miseAPrix;
		this.utilisateur = utilisateur;
		this.retrait = retrait;
	}

	// - - - - - - - - - - GETTERS & SETTERS - - - - - - - - - -

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDateTime dateDebutEnchere) {
		this.dateDebutEncheres = dateDebutEnchere;
	}

	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public int getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(int utilisateur) {
		
		this.utilisateur = utilisateur;
	}

	public Enchere getEnchere() {
		return enchere;
	}

	public void setEncheres(Enchere enchere) {
		this.enchere = enchere;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		if (this.categorie == null) {
			this.categorie = new Categorie();
		}	
			this.categorie = categorie;
		
	}

		
	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		if(this.retrait == null) {
			this.retrait = new Retrait();
		}
		this.retrait = retrait;
	}
	
	

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		if(this.vendeur == null) {
			this.vendeur = new Utilisateur();
		}		
		this.vendeur = vendeur;
	}

	public void setEnchere(Enchere enchere) 
		{if(this.enchere == null) {
			this.enchere = new Enchere();		
			}
		this.enchere = enchere;
	}

	// - - - - - - - - - - METHODE TO STRING - - - - - - - - - -
	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", utilisateur=" + utilisateur
				+ ", encheres=" + enchere + ", categorie=" + categorie + "]" + ", retrait=" + retrait;
	}
}
