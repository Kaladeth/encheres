package fr.eni.encheres.bo;

import java.util.List;

public class Categorie {
	private int noCategorie;
	private String libelle ;
	private List<ArticleVendu> listesArticles;
	
	
	// construteur complet
	
	
	public Categorie(int noCategorie, String libelle, List<ArticleVendu> listesArticles) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.listesArticles = listesArticles;
	}
	//construteur sans liste 
	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		
	}
	// construteur sans ID
	public Categorie(String libelle, List<ArticleVendu> listesArticles) {
		super();
		this.libelle = libelle;
		this.listesArticles = listesArticles;
	}
	
	public Categorie() {
		// TODO Auto-generated constructor stub
	}
	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public List<ArticleVendu> getListesArticles() {
		return listesArticles;
	}
	public void setListesArticles(List<ArticleVendu> listesArticles) {
		this.listesArticles = listesArticles;
	}
	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}
	
	public void addArticle(ArticleVendu articleVendu) {
		listesArticles.add(articleVendu);
	}
	public void removeArticle(ArticleVendu articleVendu) {
		listesArticles.remove(articleVendu);
	}
	
	
	
}
