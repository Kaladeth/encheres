package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO extends DAO<Enchere>{
	public List<Enchere> selectAll() throws DALException ;
	public List<Enchere> filtrerListeEncheresModeDeconnecte(String nomArticle, String categorie) throws DALException ;
	public List<Enchere> filtrerListeEncheresModeConnecte(int idUtilisateur, String nomArticle, String categorie, String encheres) throws DALException ;
	public int updateEnchere(int idArticle, int idAcheteur, int valeurEnchere) throws DALException;

}
