package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO extends DAO<Categorie>{
	public List <Categorie> selectAll() throws DALException ;

}
