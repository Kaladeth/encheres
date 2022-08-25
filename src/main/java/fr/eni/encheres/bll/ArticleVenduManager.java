package fr.eni.encheres.bll;

import java.time.LocalDateTime;
import java.time.Period;
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

	// * * * * * METHODE selectAll * * * * * 
		public List<ArticleVendu> selectAll() throws BLLException {
				BLLException bllExceptions = new BLLException();
				List<ArticleVendu> listesArticles = new ArrayList<ArticleVendu>();

			try {
				listesArticles = articleVenduDAO.selectAll();
											
			} catch (DALException e) {
				Exception ex = new Exception("Erreur : imposssible d'afficher des articles");
				bllExceptions.addException(ex);
				throw bllExceptions; 
			}
			
			
			return listesArticles;
			
		}

		// * * * * * METHODE FiltrerListeModeDeconnecte * * * * * 
				public List<ArticleVendu> FiltrerListeModeDeconnecte(String nomArticle, String categorie) throws BLLException {
						BLLException bllExceptions = new BLLException();
						List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();

					try {
						listeArticles = articleVenduDAO.filtrerListeModeDeconnecte(nomArticle, categorie);
					} catch (DALException e) {
						Exception ex = new Exception("Erreur : mode deconnecté - imposssible d'afficher des articles");
						bllExceptions.addException(ex);
						throw bllExceptions;
					}
					return listeArticles;			
				}

				// * * * * * METHODE FiltrerListeModeConnecteEnchere * * * * * 
				public List<ArticleVendu> FiltrerListeModeConnecteEnchere(int idUtilisateur, String nomArticle, String categorie, String encheres) throws BLLException {
						BLLException bllExceptions = new BLLException();
						List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();

						try {
							listeArticles = articleVenduDAO.filtrerListeModeConnecteEnchere(idUtilisateur, nomArticle, categorie, encheres);
						} catch (DALException e) {
							Exception ex = new Exception("Erreur : mode connecté - imposssible d'afficher des articles");
							bllExceptions.addException(ex);
							throw bllExceptions;
						}
						return listeArticles;			
				}

				// * * * * * METHODE FiltrerListeModeConnecteVentes * * * * * 
				public List<ArticleVendu> FiltrerListeModeConnecteVentes(int idUtilisateur, String nomArticle, String categorie, String ventes) throws BLLException {
						BLLException bllExceptions = new BLLException();
						List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();

						try {
							listeArticles = articleVenduDAO.filtrerListeModeConnecteVentes(idUtilisateur, nomArticle, categorie, ventes);
						} catch (DALException e) {
							Exception ex = new Exception("Erreur : mode connecté - imposssible d'afficher des articles");
							bllExceptions.addException(ex);
							throw bllExceptions;
						}
						return listeArticles;			
				}

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
	// * * * * * METHODE execution procedure stockée MAJ encheres * * * * * 
    public void executeProcedureStockee() throws BLLException {
    	BLLException bllExceptions = new BLLException();
        try{
        	articleVenduDAO.executeProcedureStockee();
        } catch (DALException e) {
            bllExceptions.addException(e);
            throw bllExceptions;
        }
    }
	 
	
	// * * * * * METHODE INSERT * * * * * 
	public ArticleVendu AjouterArticle(String nom, String description, LocalDateTime dateDebut,
			LocalDateTime dateFin, int miseAPrix, int prixVente, int user, Retrait retrait) throws BLLException {
		
		BLLException bllExceptions = new BLLException();
		// VERIFICATION DES REGLES METIER
		if(nom == null || nom.isBlank() || nom.isEmpty()) {
			Exception e = new Exception("Le nom de l'article est obligatoire !");
			bllExceptions.addException(e);
		}
		if(description == null || description.isBlank() || description.isEmpty()) {
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
		if(dateDebut != null && dateFin != null){
			if (dateDebut.isBefore(LocalDateTime.now().minus(Period.ofDays(1)))) {
			Exception e = new Exception("La date de début d'enchère ne peut être antérieure à aujourd'hui !");
			bllExceptions.addException(e);
			}
			if (dateDebut.isAfter(dateFin)){
				Exception e = new Exception("La date de fin d'enchère ne peut être antérieure à son début !");
				bllExceptions.addException(e);
			}
		}
		if(miseAPrix <= 0) {
			Exception e = new Exception("La valeur de la mise à prix doit être supérieure à 0 !");
			bllExceptions.addException(e);
		}
		if(retrait.getVille() == null || retrait.getVille().isBlank() || retrait.getVille().isEmpty()) {
			Exception e = new Exception("Veuillez reseignez une ville pour le retrait !");
			bllExceptions.addException(e);
		}
		if(retrait.getCode_postale() == null || retrait.getCode_postale().length() != 5 || retrait.getCode_postale().isBlank() || retrait.getCode_postale().isEmpty()) {
			Exception e = new Exception("Code postal incorrect on non renseigné !");
			bllExceptions.addException(e);
		}
		if(retrait.getRue() == null || retrait.getRue().isBlank() || retrait.getRue().isEmpty()) {
			Exception e = new Exception("Veuillez reseignez une rue pour le retrait !");
			bllExceptions.addException(e);
		}
		if (!bllExceptions.isEmpty()) {
			throw bllExceptions;
		}
		// CREATION DE L'ARTICLE
		ArticleVendu article = new ArticleVendu(nom, description, dateDebut, dateFin, miseAPrix, prixVente, user, retrait);
		try {
			articleVenduDAO.insert(article);
		} catch (DALException e) {
			bllExceptions.addException(e);
			throw bllExceptions;
		}
		return article;
	}
	
}
