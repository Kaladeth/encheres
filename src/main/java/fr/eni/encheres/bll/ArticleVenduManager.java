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
	
}
