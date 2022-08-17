package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur>{
	public Utilisateur validate(String login, String motDePasse) throws DALException;
}
