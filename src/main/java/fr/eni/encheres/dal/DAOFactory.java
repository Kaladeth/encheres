package fr.eni.encheres.dal;

import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.jdbc.UtilisateurDaoJdbcImpl;

@SuppressWarnings("unused")
public class DAOFactory {
	public static UtilisateurDAO getUtilisateurDao() {
		UtilisateurDAO utilisateurDao =  new UtilisateurDaoJdbcImpl();
		return utilisateurDao;
	}
}
