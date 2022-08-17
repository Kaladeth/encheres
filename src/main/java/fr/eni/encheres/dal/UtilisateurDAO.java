package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	public Utilisateur validate(String login, String motDePasse);
}
