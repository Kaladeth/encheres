package fr.eni.encheres.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.StringUtils;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
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
		BLLException bllExceptions = new BLLException();
		ArticleVendu article = null;
		int idInt = 0;
		if(!StringUtils.isNumeric(id)) {
						 Exception ex = new Exception("Erreur dans l'identifiant de l'article");
			 bllExceptions.addException(ex);
			 throw bllExceptions;
			 }
		
		else{
			idInt = Integer.valueOf(id);
			}
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
		return article;
	}
	
	// * * * * * METHODE INSERT * * * * * 
	public ArticleVendu AjouterArticle(int no, String nom, String description, LocalDateTime dateDebut,
			LocalDateTime dateFin, int miseAPrix, int prixVente, String etat, int user,
			Categorie categorie, Retrait retrait) throws BLLException {
		
		BLLException bllExceptions = new BLLException();
		// VERIFICATION DES REGLES METIER
		if(nom == null) {
			Exception e = new Exception("Le nom de l'article est obligatoire !");
			bllExceptions.addException(e);
		}
		if(description == null) {
			Exception e = new Exception("La description de l'article est obligatoire !");
			bllExceptions.addException(e);
		}
		if(dateDebut == null) {
			Exception e = new Exception("Veuillez saisir une date de début d'enchère !");
			bllExceptions.addException(e);
		}
		if(dateFin == null) {
			Exception e = new Exception("Veuillez saisir une date de fin d'enchère !");
			bllExceptions.addException(e);
		}
		if(retrait.getVille() == null) {
			Exception e = new Exception("Veuillez reseignez une ville pour le retrait !");
			bllExceptions.addException(e);
		}
		if(retrait.getCode_postale() == null || retrait.getCode_postale().length() != 5) {
			Exception e = new Exception("Code postal incorrect on non renseigné !");
			bllExceptions.addException(e);
		}
		if(retrait.getRue() == null) {
			Exception e = new Exception("Veuillez reseignez une rue pour le retrait !");
			bllExceptions.addException(e);
		}
		
		// CREATION DE L'ARTICLE
		ArticleVendu article = new ArticleVendu(no, nom, description, dateDebut, dateFin, miseAPrix, prixVente, etat, user, categorie, retrait);
		try {
			articleVenduDAO.insert(article);
		} catch (DALException e) {
			bllExceptions.addException(e);
			throw bllExceptions;
		}
		return article;
	}
	
}
