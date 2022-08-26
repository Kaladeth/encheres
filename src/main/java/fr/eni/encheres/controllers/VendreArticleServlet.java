package fr.eni.encheres.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;

/**
 * Servlet implementation class VendreArticleServlet
 */
@WebServlet("/connecte/vendre/article")
public class VendreArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendreArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
		String nom = request.getParameter("article");
		String description = request.getParameter("description");
		LocalDateTime dateDebut = null;
		LocalDateTime dateFin = null;
		if(!request.getParameter("debut").isEmpty() && request.getParameter("debut") != null) {
			dateDebut = LocalDateTime.parse(request.getParameter("debut")+"T00:00:00");
		}
		if(!request.getParameter("fin").isEmpty() && request.getParameter("fin") != null) {
			dateFin = LocalDateTime.parse(request.getParameter("fin")+"T00:00:00");
		}
		
		int miseAPrix = Integer.parseInt(request.getParameter("map"));
		int prixVente = Integer.parseInt(request.getParameter("map"));
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		int noUser = user.getNoUtilisateur();
		String cp = request.getParameter("cp");
		String rue = request.getParameter("rue");
		String ville = request.getParameter("ville");
		Retrait retrait = new Retrait(rue, cp, ville);
		ArticleVenduManager mgr = ArticleVenduManager.getInstance();
		try {
			mgr.AjouterArticle(nom, description, dateDebut, dateFin, miseAPrix, prixVente, noUser, retrait);
			rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		} catch (BLLException e) {
			 request.setAttribute("erreurs", e);
			 e.printStackTrace();
		}
		rd.forward(request, response);
	}

}
