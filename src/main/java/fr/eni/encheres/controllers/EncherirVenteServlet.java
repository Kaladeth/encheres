package fr.eni.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class EncherirVenteServlet
 */
@WebServlet("/connecte/afficher/enchere/encherir")
public class EncherirVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EncherirVenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		String idArticle = request.getParameter("noArticle");
		String valeurEnchere = request.getParameter("valeurEnchere");
	 	Utilisateur acheteur = (Utilisateur) session.getAttribute("utilisateur");
	 	
	
	 	try {
	     	EnchereManager mgr = EnchereManager.getInstance();
	     	ArticleVendu articleVendu = null; 
	     	
	     	String idArticleVendu =  Integer.toString(mgr.UpdateEnchere(idArticle, acheteur, valeurEnchere));
	     	
	     	ArticleVenduManager mgrArt = ArticleVenduManager.getInstance();
	     	articleVendu = mgrArt.SelectById(idArticleVendu);
	     	
	     	UtilisateurManager userMgr = UtilisateurManager.getInstance();
	     	acheteur = userMgr.SelectById(String.valueOf(acheteur.getNoUtilisateur()));    	
	    	
	        if(articleVendu != null) {
	        	request.setAttribute("articleVendu",articleVendu);
	        	session.setAttribute("utilisateur",acheteur);
	        	
	        	rd = request.getRequestDispatcher("/WEB-INF/afficherArticle.jsp");
	        	  	
	        	}        
	    } catch (BLLException e) {
	    	request.setAttribute("erreurs", e);
			  e.printStackTrace();
	    }
	    rd.forward(request, response);
	
	}

}
