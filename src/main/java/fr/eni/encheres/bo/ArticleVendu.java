package fr.eni.encheres.bo;

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
	private List<Enchere> encheres;
	private int categorie;
	
	// - - - - - - - - - - CONSTRUCTEURS - - - - - - - - - - 
	
	// CONSTRUCTEUR VIDE
	public ArticleVendu() {
		
	}
	
	// CONSTRUCTEUR TOTAL
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, String etatVente, int utilisateur, 
	List<Enchere> encheres, int categorie) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.encheres = encheres;
		this.categorie = categorie;
	}
	
	// CONSTRUCTEUR DANS LE NO ARTICLE
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, String etatVente, int utilisateur, 
			List<Enchere> encheres, int categorie) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.encheres = encheres;
		this.categorie = categorie;
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

	public void setDateDebutEncheres(LocalDateTime localDateTime) {
		this.dateDebutEncheres = localDateTime;
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

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	public int getCategorie() {
		return categorie;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}

	// - - - - - - - - - - METHODE TO STRING - - - - - - - - - - 
	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", utilisateur=" + utilisateur
				+ ", encheres=" + encheres + ", categorie=" + categorie + "]";
	}
	
	// - - - - - - - - - - ADDER & REMOVER POUR LA LISTE - - - - - - - - - - 
	public void addEnchere(Enchere enchere) {
		encheres.add(enchere);
	}
	public void removeEnchere(Enchere enchere) {
		encheres.remove(enchere);
	}
	
}



