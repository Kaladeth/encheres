
package fr.eni.encheres.bll;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		if(pseudo == null || pseudo.isEmpty() || pseudo.isBlank()) {
			Exception e = new Exception("L'identifiant est obligatoire !");
			bllExceptions.addException(e);
		}
		if(pseudo.indexOf('@') != -1) {
			Exception e = new Exception("L'identifiant ne peut pas contenir d'arobase !");
			bllExceptions.addException(e);
		}
		if(pseudo.matches("[a-zA-Z0-9]")) {
			Exception e = new Exception("L'identifiant n�accepte que des caractéres alphanumériques !");
			bllExceptions.addException(e);
		}
		if(nom == null || nom.isEmpty() || nom.isBlank()) {
			Exception e = new Exception("Le nom est obligatoire !");
			bllExceptions.addException(e);
		}
		if(prenom == null || prenom.isEmpty() || prenom.isBlank()) {
			Exception e = new Exception("Le prénom est obligatoire !");
			bllExceptions.addException(e);
		}
		if(email == null || email.isEmpty() || email.isBlank()) {
			Exception e = new Exception("L'e-mail est obligatoire !");
			bllExceptions.addException(e);
		}// email au format XXX@XXX.XXX
        if (!Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$").matcher(email.toUpperCase()).matches()) {
        	Exception e = new Exception("L'e-mail n'est pas au bon format !");
			bllExceptions.addException(e);
        }
		String telephoneCorrige = telephone;
		if(telephone == null || telephone.isEmpty() || telephone.isBlank()) {
			telephoneCorrige = "";
			}
		if(rue == null || rue.isEmpty() || rue.isBlank()) {
			Exception e = new Exception("La rue est obligatoire !");
			bllExceptions.addException(e);
		}
		if(cp == null || cp.isEmpty() || cp.isBlank()) {
			Exception e = new Exception("Le code postal est obligatoire !");
			bllExceptions.addException(e);
		}
		if(ville == null|| ville.isEmpty() || ville.isBlank()) {
			Exception e = new Exception("La ville est obligatoire !");
			bllExceptions.addException(e);
		}
		if(mdp == null || mdp.isEmpty() || mdp.isBlank()) {
			Exception e = new Exception("Le mot de passe est obligatoire !");
			bllExceptions.addException(e);
		}
		if(!mdp.equals(cmdp)) {
			Exception e = new Exception("Les mots de passe sont différents !");
			bllExceptions.addException(e);
		}
		
		if (!bllExceptions.isEmpty()) {
			throw bllExceptions;
			
		}
		// CREATION DE L'UTILISATEUR A ENVOYER EN BASE DE DONNEES
		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephoneCorrige, rue, cp, ville, mdp, credit, admin);
		try {
			utilisateurDao.insert(utilisateur);
		} catch (Exception e) {
			Exception ex = new Exception(e.getMessage(), e);
			bllExceptions.addException(ex);
			throw bllExceptions;
		}
	}
	
	
	// * * * * * METHODE UPDATE * * * * *
    public void updateUtilisateur(int idUser, String pseudo, String nom, String prenom, String email, String telephone, String rue,
            String cp, String ville, String ancienMdp, String mdpActuel, String mdp, String confirmationMdp) throws BLLException {
        BLLException bllExceptions = new BLLException();
        // VERIFICATION DES REGLES METIER
        if(pseudo == null || pseudo.isEmpty() || pseudo.isBlank()) {
            Exception e = new Exception("L'identifiant est obligatoire !");
            bllExceptions.addException(e);
        }
        if(pseudo.indexOf('@') != -1) {
            Exception e = new Exception("L'identifiant ne peut pas contenir d'arobase !");
            bllExceptions.addException(e);
        }
        if(pseudo.matches("[a-zA-Z0-9]")) {
            Exception e = new Exception("L'identifiant n�accepte que des caractères alphanumériques !");
            bllExceptions.addException(e);
        }
        if(nom == null || nom.isEmpty() || nom.isBlank()) {
            Exception e = new Exception("Le nom est obligatoire !");
            bllExceptions.addException(e);
        }
        if(prenom ==null || prenom.isEmpty() || nom.isBlank()) {
            Exception e = new Exception("Le prénom est obligatoire !");
            bllExceptions.addException(e);
        }
        if(email == null|| email.isEmpty() || email.isBlank()) {
            Exception e = new Exception("L'e-mail est obligatoire !");
            bllExceptions.addException(e);
        }
        // email au format XXX@XXX.XXX
        if (!Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$").matcher(email.toUpperCase()).matches()) {
            Exception e = new Exception("L'e-mail n'est pas au bon format !");
            bllExceptions.addException(e);
        }
        String telephoneCorrige = telephone;
        if(telephone == null || telephone.isEmpty() || telephone.isBlank()) {
            telephoneCorrige = "";
            }
        if(rue == null|| rue.isEmpty() || rue.isBlank()) {
            Exception e = new Exception("La rue est obligatoire !");
            bllExceptions.addException(e);
        }
        if(cp == null|| cp.isEmpty() || cp.isBlank()) {
            Exception e = new Exception("Le code postal est obligatoire !");
            bllExceptions.addException(e);
        }
        if(ville == null|| ville.isEmpty() || ville.isBlank()) {
            Exception e = new Exception("La ville est obligatoire !");
            bllExceptions.addException(e);
        }
        if(!mdpActuel.equals(ancienMdp)){
            Exception e = new Exception("Le mot de passe est incorrect, la modification ne peut donc pas être faite !");
            bllExceptions.addException(e);
        }
        String mdpATransmettre = null;
        if(mdp == null || mdp.isBlank()) {
            mdpATransmettre = mdpActuel;
        }else{
            if(!mdp.equals(confirmationMdp)){
            Exception e = new Exception("Les nouveaux mots de passe ne sont pas identiques pas !");
            bllExceptions.addException(e);
            }
            if(mdpActuel.equals(mdp)) {
            Exception e = new Exception("Pour modifier le mot de passe, merci d'en saisir un nouveau !");
            bllExceptions.addException(e);}
            mdpATransmettre= mdp;
            }
        if (!bllExceptions.isEmpty()) {
            throw bllExceptions;
        }
        // CREATION DE L'UTILISATEUR A ENVOYER EN BASE DE DONNEES
        Utilisateur utilisateur = new Utilisateur(idUser, pseudo, nom, prenom, email, telephoneCorrige, rue, cp, ville, mdpATransmettre);
        try {
            utilisateurDao.update(utilisateur);
        } catch (DALException e) {
            bllExceptions.addException(e);
            throw bllExceptions;
        }
    }
	
	// * * * * * METHODE SELECT BY ID * * * * *
	
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
			idInt = Integer.valueOf(id);
			}
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
	
	// * * * * * METHODE VISUALISER DU PROFIL * * * * *
	 public Utilisateur SelectBypseudo(String pseudo) throws BLLException {
	    	Utilisateur utilisateur = null ;
	    	try {
				utilisateur = utilisateurDao.selectBypseudo(pseudo);
			} catch (DALException e) {
				BLLException blle = new BLLException();
				blle.addException(e);
				throw blle;
			}
	    	
	    	
			return utilisateur ;
	    	
	    }
}


