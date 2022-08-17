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
import fr.eni.encheres.bll.Manager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ConnecterServlet
 */
@WebServlet("/ConnecterServlet")
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
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connecter/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 	String pseudo = request.getParameter("pseudo");
	        String password = request.getParameter("password");
	     
	        try {
	        	
	        	Manager mgr = new Manager();
	        	Utilisateur utilisateur = mgr.authentification(pseudo , password);
	           if (utilisateur != null) {
	        	   
	                HttpSession session = request.getSession();
	                session.setAttribute("pseudo",pseudo);
	                response.sendRedirect("index.jsp");
	            } else {
	                HttpSession session = request.getSession();
	                session.setAttribute("pseudo", pseudo);
	                response.sendRedirect("login.jsp");
	                String message=" L'identifiant ou le mot de passe incorrect";
	                request.setAttribute("message", message);
	            }
	        } catch (BLLException e) {
	            e.printStackTrace();
	        }
		
	

}
}
