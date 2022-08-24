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
		  request.setAttribute("articleMgr", articleMgr);
		  
		  UtilisateurManager utilisateurMgr = UtilisateurManager.getInstance();
		  request.setAttribute("utilisateurMgr", utilisateurMgr);
		 	
		  try { 
			  EnchereManager enchereMgr = EnchereManager.getInstance(); 
			  if (request.getAttribute("listeEncheresFiltres") == null)
			  {
				  List<Enchere> listeEncheres = enchereMgr.selectAllEncheres();
				  request.setAttribute("listeEncheres", listeEncheres); 
			  }
			  else
			  {
				  List<Enchere> listeEncheresFiltres = (List<Enchere>) request.getAttribute("listeEncheresFiltres");
				  request.setAttribute("listeEncheres", listeEncheresFiltres); 
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
			    
			    EnchereManager enchereMgr = EnchereManager.getInstance();
			    List<Enchere> listeEncheres = new ArrayList<Enchere>();
			    
		    	try { 
		    		if (connecte)
		    		{
			    		listeEncheres = enchereMgr.FiltrerListeEncheresModeConnecte(utilisateur.getNoUtilisateur(), nomArticle, categorie, encheres); 
		    		}
		    		else
		    		{
			    		listeEncheres = enchereMgr.FiltrerListeEncheresModeDeconnecte(nomArticle, categorie);
		    		}
					request.setAttribute("listeEncheresFiltres", listeEncheres); 
		    	} catch (BLLException e) { 
		    		// TODO Auto-generated catch block 
		    		request.setAttribute("erreurs", e);
		    		e.printStackTrace(); 
		    	}
		    	doGet(request, response);

	}
}


}
