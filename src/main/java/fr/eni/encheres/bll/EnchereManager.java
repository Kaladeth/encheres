package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.microsoft.sqlserver.jdbc.StringUtils;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
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

		// * * * * * METHODE FiltrerListeEncheres * * * * * 
		public List<Enchere> FiltrerListeEncheres(String nomArticle, String categorie) throws BLLException {
				BLLException bllExceptions = new BLLException();
				List<Enchere> listesEncheres = new ArrayList<Enchere>();

			try {
				listesEncheres = enchereDao.filtrerListeEncheres(nomArticle, categorie);
			} catch (DALException e) {
				Exception ex = new Exception("Erreur : imposssible d'afficher des articles");
				bllExceptions.addException(ex);
				throw bllExceptions;
			}
			return listesEncheres;			
		}

		// * * * * * Methode pour enchérir * * * * * 
		
		public int UpdateEnchere(String idArticle, Utilisateur acheteur, String valeurEnchere) throws BLLException {
			int idArticleMAJ = 0;
			BLLException bllExceptions = new BLLException();
			int idArticleInt;
			int valeurEnchereInt;
			int idAcheteur = acheteur.getNoUtilisateur();

											
			if(!StringUtils.isNumeric(idArticle)) {
				 Exception ex = new Exception("Erreur dans l'identifiant de l'enchere");
				 bllExceptions.addException(ex);
				 throw bllExceptions;
			}
			else{
				idArticleInt = Integer.valueOf(idArticle);}
			
			if(!StringUtils.isNumeric(valeurEnchere)) {
				 Exception ex = new Exception("Erreur dans le montant de l'enchère");
				 bllExceptions.addException(ex);
				 throw bllExceptions;
			}
			else{
				valeurEnchereInt = Integer.valueOf(valeurEnchere);}
			
			if(valeurEnchereInt > acheteur.getCredit()){
				Exception ex = new Exception("Crédit disponible insuffisant");
				 bllExceptions.addException(ex);
				 throw bllExceptions;
			}
			try {
				idArticleMAJ = enchereDao.updateEnchere(idArticleInt, idAcheteur, valeurEnchereInt);
				
				
				if(idArticleMAJ == 0) {
					Exception ex = new Exception("Erreur : article introuvable");
					bllExceptions.addException(ex);
					throw bllExceptions;
				}
			} catch (DALException e) {
				bllExceptions.addException(e);
				throw bllExceptions;
			}
			return idArticleMAJ;
		}
			
		
	

}
