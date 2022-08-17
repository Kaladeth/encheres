package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class Manager {
	private static Manager mgr;
	private UtilisateurDAO utilisateurDao;
	
	
// SINGLETON MANAGER
	private Manager() {
		utilisateurDao = DAOFactory.getUtilisateurDao();
	}
	
	public static Manager getInstance() {
		if(mgr==null) {
			mgr= new Manager();
		}
		return mgr;
	}
	
// - - - - - - - - - - METHODES UTILISATEUR - - - - - - - - - - 
	// * * * * * METHODE INSERT * * * * *
	public void ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String cp, String ville, String mdp, int credit, byte admin) throws BLLException {
		BLLException bllExceptions = new BLLException();
		
		// VERIFICATION DES REGLES METIER
		if(pseudo == null) {
			Exception e = new Exception("L'identifiant est obligatoire !");
			bllExceptions.addException(e);
		}
		if(pseudo.indexOf('@') != -1) {
			Exception e = new Exception("L'identifiant ne peut pas contenir d'arobase !");
			bllExceptions.addException(e);
		}
		if(nom == null) {
			Exception e = new Exception("Le nom est obligatoire !");
			bllExceptions.addException(e);
		}
		if(prenom == null) {
			Exception e = new Exception("Le prénom est obligatoire !");
			bllExceptions.addException(e);
		}
		if(email == null) {
			Exception e = new Exception("L'e-mail est obligatoire !");
			bllExceptions.addException(e);
		}
		if(telephone == null) {
			Exception e = new Exception("Le numéro de téléphone est obligatoire !");
			bllExceptions.addException(e);
		}
		if(rue == null) {
			Exception e = new Exception("La rue est obligatoire !");
			bllExceptions.addException(e);
		}
		if(cp == null) {
			Exception e = new Exception("Le code postal est obligatoire !");
			bllExceptions.addException(e);
		}
		if(ville == null) {
			Exception e = new Exception("La ville est obligatoire !");
			bllExceptions.addException(e);
		}
		if(mdp == null) {
			Exception e = new Exception("Le mot de passe est obligatoire !");
			bllExceptions.addException(e);
		}
		
		// CREATION DE L'UTILISATEUR A ENVOYER EN BASE DE DONNEES
		credit = 0;
		admin = 0;
		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, cp, ville, mdp, credit, admin);
		try {
			utilisateurDao.insert(utilisateur);
		} catch (DALException e) {
			Exception ex = new Exception(e.getMessage());
			bllExceptions.addException(ex);
			throw bllExceptions;
		}
	}
	
	// * * * * * METHODE DELETE * * * * *

	
}
