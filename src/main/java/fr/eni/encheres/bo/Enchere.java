package fr.eni.encheres.bo;

import java.util.Date;

public class Enchere {
	
	private int noUtilisateur;
	private int noArticle;
	private Date dateEnchere;
	private int montant_enchere;
	/**
	 * @param noUtilisateur
	 * @param noArticle
	 * @param dateEnchere
	 * @param montant_enchere
	 */
	public Enchere(int noUtilisateur, int noArticle, Date dateEnchere, int montant_enchere) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}
	/**
	 * 
	 */
	public Enchere() {
		super();
	}
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public int getNoArticle() {
		return noArticle;
	}
	public Date getDateEnchere() {
		return dateEnchere;
	}
	public int getMontant_enchere() {
		return montant_enchere;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	@Override
	public String toString() {
		return "Enchere [noUtilisateur=" + noUtilisateur + ", noArticle=" + noArticle + ", dateEnchere=" + dateEnchere
				+ ", montant_enchere=" + montant_enchere + "]";
	}
	
	
}

