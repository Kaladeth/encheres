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
			Exception ex = new Exception("Erreur : imposssible d'afficher des articles");
			bllExceptions.addException(ex);
			throw bllExceptions; 
		}
		
		
		return listesEncheres;
		
	}

	// * * * * * METHODE filtrerListeEncheres * * * * * 
		public Enchere filtrerListeEncheres(int idArticle) throws BLLException {
				BLLException bllExceptions = new BLLException();
				Enchere enchere = new Enchere();

			try {
				enchere = enchereDao.selectByArticle(idArticle);
			} catch (DALException e) {
				Exception ex = new Exception("Erreur : imposssible d'afficher des articles");
				bllExceptions.addException(ex);
				throw bllExceptions;
			}
			//CHAQUE ARTICLE A UNE SEULE ENCHERE EN COURS
			return enchere;
			
		}

	

}
