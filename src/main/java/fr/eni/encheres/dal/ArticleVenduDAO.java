package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;

public interface ArticleVenduDAO extends DAO<ArticleVendu>{
	public ArticleVendu AfficherParCategorie(String categorie) throws DALException;
	public List<ArticleVendu> filtrerListeModeDeconnecte(String nomArticle, String categorie) throws DALException;
	public List<ArticleVendu> filtrerListeModeConnecteEnchere(int idUtilisateur, String nomArticle, String categorie, String encheres) throws DALException ;
	public List<ArticleVendu> filtrerListeModeConnecteVentes(int idUtilisateur, String nomArticle, String categorie, String ventes) throws DALException ;
	public void executeProcedureStockee() throws DALException;;
}
