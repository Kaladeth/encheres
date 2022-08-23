package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;

public class CategorieManager {
	private static CategorieManager mgr;
	private CategorieDAO categorieDao;
	
	// SINGLETON MANAGER
	private CategorieManager() {
		categorieDao = DAOFactory.getCategorieDao();
	}
	
	public static CategorieManager getInstance() {
		if(mgr==null) {
			mgr= new  CategorieManager();
		}
		return mgr;
	}

	// - - - - - - - - - - METHODES CATEGORIE - - - - - - - - - - 
	
	// * * * * * METHODE selectAllCategorie * * * * * 
	public List<Categorie> selectAllCategorie() throws BLLException {
			BLLException bllExceptions = new BLLException();
			List<Categorie> listesCategories = new ArrayList<Categorie>();

		try {
			
			listesCategories= categorieDao.selectAll();
		} catch (DALException e) {
			Exception ex = new Exception("Erreur : imposssible d'afficher de catégorie");
			bllExceptions.addException(ex);
			throw bllExceptions;
		}
		
		return listesCategories;
		
	}
	
}
