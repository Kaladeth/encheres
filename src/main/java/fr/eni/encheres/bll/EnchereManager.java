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
import fr.eni.encheres.dal.ArticleVenduDAO;
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

		
		// * * * * * Methode pour enchérir * * * * * 
		
		public int UpdateEnchere(String idArticle, Utilisateur acheteur, String valeurEnchere) throws BLLException {
			int idArticleMAJ = 0;
			BLLException bllExceptions = new BLLException();
			int idArticleInt;
			int valeurEnchereInt;
			int idAcheteur = acheteur.getNoUtilisateur();
			ArticleVendu article;
			ArticleVenduManager artMgr = ArticleVenduManager.getInstance();
			
			if(!StringUtils.isNumeric(idArticle)) {
				 Exception ex = new Exception("Erreur dans l'identifiant de l'enchere");
				 bllExceptions.addException(ex);
				 throw bllExceptions;
			}
			else{
				idArticleInt = Integer.valueOf(idArticle);}
			
			// vérification que l'enchère est en cours et que l'article n'appartient pas à l'enchérisseur
			try {
				article = artMgr.SelectById(idArticle);
				if(article == null) {
					Exception ex = new Exception("Erreur : Article introuvable");
					bllExceptions.addException(ex);
					throw bllExceptions;
				}
			
			} catch (BLLException e) {
				bllExceptions.addException(e);
				throw bllExceptions;
			}
			if((article.getUtilisateur()) == acheteur.getNoUtilisateur()) {
				Exception ex = new Exception("Petit coquinou ! \n Il n'est pas possible d'enchérir sur un article vous appartenant !");
				bllExceptions.addException(ex);
				throw bllExceptions;
			}
//			if(article.getEtatVente().equals("CR")) {
//				Exception ex = new Exception("Il n'est pas possible d'enchérir sur un article car l'enchère n'a pas débuté !");
//				bllExceptions.addException(ex);
//				throw bllExceptions;
//			}
//			if(!article.getEtatVente().equals("ER")) {
//				Exception ex = new Exception("Il n'est plus possible d'enchérir sur cet article car l'enchère est terminée !");
//				bllExceptions.addException(ex);
//				throw bllExceptions;
//			}
										
			
			
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
