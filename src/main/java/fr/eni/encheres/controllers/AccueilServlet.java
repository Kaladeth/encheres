package fr.eni.encheres.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.UtilisateurDAO;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("/Accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilServlet() {
        super();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		EnchereManager enchereMgr = EnchereManager.getInstance();
     	ArticleVenduManager articleMgr = ArticleVenduManager.getInstance();
		request.setAttribute("articleMgr", articleMgr);
		UtilisateurManager utilisateurMgr = UtilisateurManager.getInstance();
		request.setAttribute("utilisateurMgr", utilisateurMgr);

		try {
			List<Enchere> listeEncheres = enchereMgr.selectAllEncheres();
			request.setAttribute("listeEncheres", listeEncheres);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
        rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 if (request.getParameter("filtrer") != null) {
				
				String nomArticle = request.getParameter("nomArticle");
			    String categorie = request.getParameter("categorie");
		 try {
		     	ArticleVenduManager mgr = ArticleVenduManager.getInstance();
		    	ArticleVendu articleVendu = mgr.SelectById(categorie);
		        request.setAttribute("articleVendu",articleVendu);
		        
		 }catch (Exception e) {
			// TODO: handle exception
		}
		
		doGet(request, response);

	}
}


}
}