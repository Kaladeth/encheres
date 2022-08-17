package fr.eni.encheres.bll;

import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class Manager {
	private static Manager mgr;
	private UtilisateurDAO utilisateurDao;
	
	private Manager() {
		utilisateurDao = DAOFactory.getUtilisateurDao();
	}
	
	public static Manager getInstance() {
		if(mgr==null) {
			mgr= new Manager();
		}
		return mgr;
	}
	
	
}
