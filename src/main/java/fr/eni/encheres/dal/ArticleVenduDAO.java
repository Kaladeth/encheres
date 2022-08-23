package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO extends DAO<ArticleVendu>{
	public ArticleVendu AfficherParCategorie(String categorie) throws DALException;

}
