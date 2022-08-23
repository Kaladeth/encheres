package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO extends DAO<Enchere>{
	public List<Enchere> selectAll() throws DALException ;
	public Enchere selectByArticle(int idArticle) throws DALException ;
	public List<Enchere> filtrerListeEncheres(String nomArticle, String categorie) throws DALException ;

}
