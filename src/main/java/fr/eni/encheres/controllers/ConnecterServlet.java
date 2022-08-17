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
import fr.eni.encheres.dal.DALException;

/**
 * Servlet implementation class ConnecterServlet
 */
@WebServlet("/Connecter")
public class ConnecterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnecterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/login.jsp");
		 	String pseudo = request.getParameter("pseudo");
	        String password = request.getParameter("password");
	     
	        try {
	        	
	        	UtilisateurManager mgr = UtilisateurManager.getInstance();
	        	Utilisateur utilisateur = mgr.authentification(pseudo , password);
	            HttpSession session = request.getSession();
	            session.setAttribute("pseudo",pseudo);
	                
	           
//	                else {
//	                HttpSession session = request.getSession();
//	                session.setAttribute("pseudo", pseudo);
//	                response.sendRedirect("login.jsp");
//	                String message=" L'identifiant ou le mot de passe incorrect";
//	                request.setAttribute("message", message);
//	            }
	        } catch (BLLException e) {
	        	request.setAttribute("erreurs", e);
	        }
	        rd.forward(request, response);
	

}
}
