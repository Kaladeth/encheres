package fr.eni.encheres.dal;

import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.jdbc.ArticleVenduDaoJdbcImpl;
import fr.eni.encheres.dal.jdbc.UtilisateurDaoJdbcImpl;

@SuppressWarnings("unused")
public class DAOFactory {
	public static UtilisateurDAO getUtilisateurDao() {
		UtilisateurDAO utilisateurDao =  new UtilisateurDaoJdbcImpl();
		return utilisateurDao;
	}
	public static ArticleVenduDAO getArticleVenduDao() {
		ArticleVenduDAO articleVenduDAO =  new ArticleVenduDaoJdbcImpl();
		return articleVenduDAO;
	}
}
