package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {
	private static EnchereManager mgr;
	private EnchereDAO enchereDao;
	
	
	// SINGLETON MANAGER
	private EnchereManager() {
		enchereDao = DAOFactory.getEnchereDAO();
	}
	
	public static EnchereManager getInstance() {
		if(mgr==null) {
			mgr= new  EnchereManager();
		}
		return mgr;
	}

	// - - - - - - - - - - METHODES ENCHERE - - - - - - - - - - 
	
	// * * * * * METHODE selectAllEncheres * * * * * 
	public List<Enchere> selectAllEncheres() throws BLLException {
			BLLException bllExceptions = new BLLException();
			List<Enchere> listesEncheres = new ArrayList<Enchere>();

		try {
			
			listesEncheres = enchereDao.selectAll();
		} catch (DALException e) {
			Exception ex = new Exception(e.getMessage());
			throw bllExceptions;
		}
		return listesEncheres;
		
	}

		// * * * * * METHODE FiltrerListeEncheres * * * * * 
		public List<Enchere> FiltrerListeEncheres(String nomArticle, String categorie) throws BLLException {
				BLLException bllExceptions = new BLLException();
				List<Enchere> listesEncheres = new ArrayList<Enchere>();

			try {
				listesEncheres = enchereDao.filtrerListeEncheres(nomArticle, categorie);
			} catch (DALException e) {
				Exception ex = new Exception(e.getMessage());
				throw bllExceptions;
			}
			return listesEncheres;			
		}

	

}
