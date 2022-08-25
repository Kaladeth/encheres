package fr.eni.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ModifierMonProfilServlet
 */
@WebServlet("/connecte/modifier/profil")
public class ModifierMonProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierMonProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp"); 
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		int idUser = ((Utilisateur) session.getAttribute("utilisateur")).getNoUtilisateur();
		String pseudo = request.getParameter("pseudo");
	    String nom = request.getParameter("lastname");
	    String prenom = request.getParameter("firstname");
	    String email = request.getParameter("email");
	    String telephone = request.getParameter("phone");
	    String rue = request.getParameter("street");
	    String cp = request.getParameter("zipcode");
	    String ville = request.getParameter("city");
	    String ancienMdp = request.getParameter("old_password");
	    String mdpActuel = utilisateur.getMotDePasse();
	    System.out.println(mdpActuel);
	    String mdp = request.getParameter("password");
	    String confirmationMDP = request.getParameter("confirm_password");
	    

	    
	    try {
	     	UtilisateurManager mgr = UtilisateurManager.getInstance();
	    	mgr.updateUtilisateur(idUser, pseudo, nom, prenom, email, telephone, rue, cp, ville, ancienMdp, mdpActuel, mdp, confirmationMDP);
	    	//à modifier en aficher profil quand il sera fait
	    	
	    	} catch (BLLException e) {
	    		rd = request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp");
	    		request.setAttribute("erreurs", e);
				e.printStackTrace();
	    }
	    utilisateur.setPseudo(pseudo);
	    utilisateur.setNom(nom);
	    utilisateur.setPrenom(prenom);
	    utilisateur.setEmail(email);
	    utilisateur.setTelephone(telephone);
	    utilisateur.setRue(rue);
	    utilisateur.setCodePostal(cp);
	    utilisateur.setVille(ville);
	    session.setAttribute("utilisateur",utilisateur);
	    rd.forward(request, response);
	
	
	}

}
