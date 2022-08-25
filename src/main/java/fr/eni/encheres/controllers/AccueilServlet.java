package fr.eni.encheres.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;


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
		
		  HttpSession session = request.getSession(); 
		  boolean connecte = (session.getAttribute("utilisateur") == null) ? false : true;
		  request.setAttribute("connecte", connecte);
		  ArticleVenduManager  articleMgr = ArticleVenduManager.getInstance();
		 	
		  try { 
			  if (request.getAttribute("listeArticlesFiltres") == null)
			  {
				  List<ArticleVendu> listeArticles = articleMgr.selectAll();
				  request.setAttribute("listeArticles", listeArticles); 
				  request.setAttribute("valeurCheckbox", "CR"); 
			  }
			  else
			  {
				  List<ArticleVendu> listeArticlesFiltres = (List<ArticleVendu>) request.getAttribute("listeArticlesFiltres");
				  request.setAttribute("listeArticles", listeArticlesFiltres); 
			  }
			  
		  } 
		  catch (BLLException e) {
			  // essai affichage exhaustif  
			  request.setAttribute("erreurs", e);
			  e.printStackTrace();
		  }
		 
		  try {
			  CategorieManager categorieMgr = CategorieManager.getInstance();
			  List<Categorie> listesCategories = categorieMgr.selectAllCategorie();
			  request.setAttribute("listesCategories", listesCategories);
		  } catch (BLLException e) {

			request.setAttribute("erreurs", e);
			e.printStackTrace();

			  request.setAttribute("erreurs", e);
			  e.printStackTrace();

		  }
		  
		  RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
	      rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		boolean connecte = (utilisateur == null) ? false : true;
		
		 if (request.getParameter("filtrer") != null) {
				
				String nomArticle = request.getParameter("nomArticle");
			    String categorie = request.getParameter("categorie");
			    String encheres = request.getParameter("encheres");
			    String ventes = request.getParameter("ventes");
			    
  			    request.setAttribute("valeurCheckbox", encheres); 
  			      			    
  			    ArticleVenduManager  articleMgr = ArticleVenduManager.getInstance();
  			    List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
			    
		    	try { 
		    		if (connecte & encheres != null)
		    		{
		    			listeArticles = articleMgr.FiltrerListeModeConnecteEnchere(utilisateur.getNoUtilisateur(), nomArticle, categorie, encheres); 
		    		}
		    		else if (connecte & ventes != null)
		    		{
		    			listeArticles = articleMgr.FiltrerListeModeConnecteVentes(utilisateur.getNoUtilisateur(), nomArticle, categorie, ventes); 
		    		}
		    		else
		    		{
		    			listeArticles = articleMgr.FiltrerListeModeDeconnecte(nomArticle, categorie);
		    		}
					request.setAttribute("listeArticlesFiltres", listeArticles); 
					
		    	} catch (BLLException e) { 

		    		request.setAttribute("erreurs", e); 
		    		e.printStackTrace();

		    		// TODO Auto-generated catch block 
		    		request.setAttribute("erreurs", e);
		    		e.printStackTrace(); 

		    	}
		    	doGet(request, response);

	}
}


}
