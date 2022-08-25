
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
	// * * * * * METHODE VALIDATE * * * * * 
	public Utilisateur authentification(String login, String pw) throws BLLException {
		BLLException bllExceptions = new BLLException();
		Utilisateur utilisateur = null;
		// VERIFICATION DES REGLES METIER
		if(login == null) {
			Exception e = new Exception("Veuillez renseigner un identifiant");
			bllExceptions.addException(e);
		}
		if(pw == null) {
			Exception e = new Exception("Veuillez renseigner un mot de passe");
			bllExceptions.addException(e);
		}
		if(!bllExceptions.isEmpty()) {
			throw bllExceptions;
		}
		
		// VERIFICATION
		try {
			utilisateur = utilisateurDao.validate(login, pw);
			if(utilisateur == null) {
				Exception ex = new Exception("Erreur : identifiant ou mot de passe incorrect");
				bllExceptions.addException(ex);
				throw bllExceptions;
			}
		} catch (DALException e) {
			e.getMessage();
		}
		return utilisateur;
	}
	
	


	
	// * * * * * METHODE DELETE * * * * *
	public void supprimerUtilisateur(int id) {
		
	}
	
}
