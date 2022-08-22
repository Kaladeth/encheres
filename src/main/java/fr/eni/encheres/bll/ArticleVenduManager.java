package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.StringUtils;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.DALException;

public class ArticleVenduManager {


	private static ArticleVenduManager mgr;
	private ArticleVenduDAO articleVenduDAO;
	
	
	// SINGLETON MANAGER
	private ArticleVenduManager() {
		articleVenduDAO = DAOFactory.getArticleVenduDao();
	}
	
	public static ArticleVenduManager getInstance() {
		if(mgr==null) {
			mgr= new ArticleVenduManager();
		}
		return mgr;
	}
	
	// - - - - - - - - - - METHODES ARTICLEVENDU

	// * * * * * METHODE SELECTBYIB * * * * * 
	public ArticleVendu SelectById(String id) throws BLLException {
		System.out.println(1);
		BLLException bllExceptions = new BLLException();
		System.out.println(2);
		System.out.println(id);
		ArticleVendu article = null;
		System.out.println(3);
		int idInt = 0;
		if(!StringUtils.isNumeric(id)) {
			 Exception ex = new Exception("Erreur dans l'identifiant de l'article");
			 System.out.println("4a");
			 bllExceptions.addException(ex);
			 throw bllExceptions;
		}
		
		else{
			idInt = Integer.valueOf(id);}
		System.out.println("4b = " + id);	
		try {
			article = articleVenduDAO.selectById(idInt);
			if(article == null) {
				Exception ex = new Exception("Erreur : Article introuvable");
				bllExceptions.addException(ex);
				throw bllExceptions;
			}
		} catch (DALException e) {
			bllExceptions.addException(e);
			throw bllExceptions;
		}
		System.out.println(article);
		return article;
	}
	
	public  List<ArticleVendu> SelectByNameArticle(String nomArticle) throws BLLException {
		BLLException bllExceptions = new BLLException();
		List<ArticleVendu> listeArticleVendu = new ArrayList<ArticleVendu>();

		try {
			
			listeArticleVendu = articleVenduDAO.selectByNamArticle(nomArticle);
		} catch (DALException e) {
			Exception ex = new Exception(e.getMessage());
			throw bllExceptions;
		}
		return listeArticleVendu;
	}
	
//	// * * * * * METHODE INSERT * * * * *
//	public void ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
//			String cp, String ville, String mdp, int credit, boolean admin) throws BLLException {
//		BLLException bllExceptions = new BLLException();
//		
//		// VERIFICATION DES REGLES METIER
//		if(pseudo == null) {
//			Exception e = new Exception("L'identifiant est obligatoire !");
//			bllExceptions.addException(e);
//		}
//		if(pseudo.indexOf('@') != -1) {
//			Exception e = new Exception("L'identifiant ne peut pas contenir d'arobase !");
//			bllExceptions.addException(e);
//		}
//		if(nom == null) {
//			Exception e = new Exception("Le nom est obligatoire !");
//			bllExceptions.addException(e);
//		}
//		if(prenom == null) {
//			Exception e = new Exception("Le prénom est obligatoire !");
//			bllExceptions.addException(e);
//		}
//		if(email == null) {
//			Exception e = new Exception("L'e-mail est obligatoire !");
//			bllExceptions.addException(e);
//		}
//		if(telephone == null) {
//			Exception e = new Exception("Le numéro de téléphone est obligatoire !");
//			bllExceptions.addException(e);
//		}
//		if(rue == null) {
//			Exception e = new Exception("La rue est obligatoire !");
//			bllExceptions.addException(e);
//		}
//		if(cp == null) {
//			Exception e = new Exception("Le code postal est obligatoire !");
//			bllExceptions.addException(e);
//		}
//		if(ville == null) {
//			Exception e = new Exception("La ville est obligatoire !");
//			bllExceptions.addException(e);
//		}
//		if(mdp == null) {
//			Exception e = new Exception("Le mot de passe est obligatoire !");
//			bllExceptions.addException(e);
//		}
//		
//		// CREATION DE L'UTILISATEUR A ENVOYER EN BASE DE DONNEES
//		credit = 0;
//		admin = false;
//		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, cp, ville, mdp, credit, admin);
//		try {
//			utilisateurDao.insert(utilisateur);
//		} catch (DALException e) {
//			Exception ex = new Exception(e.getMessage());
//			bllExceptions.addException(ex);
//			throw bllExceptions;
//		}
//	}
//	
//	// * * * * * METHODE DELETE * * * * *
//	public void supprimerUtilisateur(int id) {
//		
//	}
	
}
