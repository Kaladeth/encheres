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
import fr.eni.encheres.bo.ArticleVendu;


/**
 * Servlet implementation class VendreServlet
 */
@WebServlet("/connecte/afficher/enchere")
public class AfficherUneEnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherUneEnchereServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
	 	String id = request.getParameter("noArticleVendu");
	 	System.out.println(id);
	    try {
	    	ArticleVenduManager mgr = ArticleVenduManager.getInstance();
	    	ArticleVendu articleVendu = mgr.SelectById(id);
	        HttpSession session = request.getSession();
	        if(articleVendu != null) {
	        	session.setAttribute("articleVendu",articleVendu);
	        	rd = request.getRequestDispatcher("/WEB-INF/afficheArticle.jsp");
	        	}        
	    } catch (BLLException e) {
	    	request.setAttribute("erreurs", e);
	    }
	    rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
