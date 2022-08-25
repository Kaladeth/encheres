
package fr.eni.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;


@WebServlet("/connecte/visualiser/profil")
public class VisualiserProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
    public VisualiserProfilServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = request.getParameter("pseudo");
        UtilisateurManager mgr = UtilisateurManager.getInstance();
        try {
			
			Utilisateur utilisateur = mgr.SelectBypseudo(pseudo);
                request.setAttribute("utilisateur", utilisateur);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/VisualiserProfil.jsp");
	        rd.forward(request, response); 
		} catch (BLLException e) {
			response.sendRedirect(request.getContextPath()+"/Accueil");
		}
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		 doGet(request, response);

	}

}
                                                                                                                                                                                                                                                                                                                                                                                                                                                           
