
package fr.eni.encheres.bll;

import com.microsoft.sqlserver.jdbc.StringUtils;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	private static UtilisateurManager mgr;
	private UtilisateurDAO utilisateurDao;
	
	
// SINGLETON MANAGER
	private UtilisateurManager() {
		utilisateurDao = DAOFactory.getUtilisateurDao();
	}
	
	public static UtilisateurManager getInstance() {
		if(mgr==null) {
			mgr= new UtilisateurManager();
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
			bllExceptions.addException(e);
			throw bllExceptions;
		}
		return utilisateur;
	}
	
	
	// * * * * * METHODE INSERT * * * * *
	public void ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String cp, String ville, String mdp,String cmdp, int credit, boolean admin) throws BLLException {
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
		if(pseudo.matches("[a-zA-Z0-9]")) {
			Exception e = new Exception("L'identifiant níaccepte que des caractËres alphanumÈriques !");
			bllExceptions.addException(e);
		}
		if(nom == null) {
			Exception e = new Exception("Le nom est obligatoire !");
			bllExceptions.addException(e);
		}
		if(prenom == null) {
			Exception e = new Exception("Le pr√©nom est obligatoire !");
			bllExceptions.addException(e);
		}
		if(email == null) {
			Exception e = new Exception("L'e-mail est obligatoire !");
			bllExceptions.addException(e);
		}
		if(telephone == null) {
			Exception e = new Exception("Le num√©ro de t√©l√©phone est obligatoire !");
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
		if(mdp == cmdp) {
			Exception e = new Exception("Les mots de passe sont diffÈrents !");
			bllExceptions.addException(e);
		}
		
		// CREATION DE L'UTILISATEUR A ENVOYER EN BASE DE DONNEES
		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, cp, ville, mdp, credit, admin);
		try {
			utilisateurDao.insert(utilisateur);
		} catch (DALException e) {
			Exception ex = new Exception(e.getMessage());
			bllExceptions.addException(ex);
			throw bllExceptions;
		}
	}
	
	public Utilisateur SelectById(String id) throws BLLException {
		BLLException bllExceptions = new BLLException();
		Utilisateur utilisateur = null;
		int idInt = 0;
		if(!StringUtils.isNumeric(id)) {
			 Exception ex = new Exception("Erreur dans l'identifiant de l'utilisateur");
			 bllExceptions.addException(ex);
			 throw bllExceptions;
		}
		else{
			idInt = Integer.valueOf(id);}
			
		try {
			utilisateur = utilisateurDao.selectById(idInt);
			if(utilisateur == null) {
				Exception ex = new Exception("Erreur : Utilisateur introuvable");
				bllExceptions.addException(ex);
				throw bllExceptions;
			}
		} catch (DALException e) {
			bllExceptions.addException(e);
			throw bllExceptions;
		}
		return utilisateur;
	}
	
	// * * * * * METHODE DELETE * * * * *
	public void supprimerUtilisateur(int id) {
		
	}
	
}
