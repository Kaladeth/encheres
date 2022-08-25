package fr.eni.encheres.bo;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	int noUtilisateur;
	String pseudo;
	String nom;
	String prenom;
	String email;
	String telephone;
	String rue; 
	String codePostal;
	String ville;
	String motDePasse;
	int credit;
	boolean administrateur;
	
	List<ArticleVendu> articlesVendu;
	List<Enchere> encheres;
	
	//constructeur vide
	public Utilisateur() {
	}
	//constructeur sans id et sans listes : création d'un utilisateur
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse=motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.articlesVendu = new ArrayList<ArticleVendu>();
		this.encheres = new ArrayList<Enchere>();
	}
	// constructeur pour MAJ utilisateur
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) {
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		}
		

	// construteur sans mdp
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.articlesVendu = new ArrayList<ArticleVendu>();
		this.encheres = new ArrayList<Enchere>();
	}
	
	// construteur complet
	
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur,
			List<ArticleVendu> articlesVendu, List<Enchere> encheres) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.articlesVendu = articlesVendu;
		this.encheres = encheres;
	}
	// toString sans mot de passe 
	@Override
	public String toString() {
		return "Utilisateur : noUtilisateur : " + noUtilisateur + "; pseudo : " + pseudo + 
				"\nnom : " + nom + ", prenom : "+ prenom + 
				"\nemail : " + email + 
				"\ntelephone : " + telephone + 
				"\nrue : " + rue + 
				"\ncodePostal : " + codePostal + ", ville : " + ville + 
				"\ncredit : " + credit + 
				"\nadministrateur : " + administrateur
				+ "\narticles : " + articlesVendu + 
				"\nencheres:" + encheres;
	}

	public void addArticleVendu(ArticleVendu article) {
		articlesVendu.add(article);
	}
	public void removeArticleVendu(ArticleVendu article) {
		articlesVendu.remove(article);
		}
	
	public void addEnchere (Enchere enchere) {
		encheres.add(enchere);
	}
	public void removeEnchere(Enchere enchere) {
		encheres.remove(enchere);
		}
		
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getPrenomNom() {
		return prenom + " " + nom;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public boolean getAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}
	public List<ArticleVendu> getArticles() {
		return articlesVendu;
	}
	public void setArticles(List<ArticleVendu> articlesVendu) {
		this.articlesVendu = articlesVendu;
	}
	public List<Enchere> getEncheres() {
		return encheres;
	}
	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}
	
	
	
	
	

}

