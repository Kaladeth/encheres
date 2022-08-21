package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO extends DAO<Enchere>{
	public List<Enchere> selectAll() throws DALException ;

}
