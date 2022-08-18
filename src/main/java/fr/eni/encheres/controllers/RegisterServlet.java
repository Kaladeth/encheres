package fr.eni.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.Manager;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("creer") != null) {
		
		 String pseudo = request.getParameter("pseudo");
	     String nom = request.getParameter("lastname");
	     String prenom = request.getParameter("firstname");
	     String email = request.getParameter("email");
	     String telephone = request.getParameter("phone");
	     String rue = request.getParameter("street");
	     String cp = request.getParameter("zipcode");
	     String ville = request.getParameter("city");
	     String mdp = request.getParameter("password");
	     String confirmationMDP = request.getParameter("confirm_password");
	     int credit = 100;
		 boolean admin = false;
			
	     Manager mgr = Manager.getInstance();
			
			  try { 
				  	mgr.ajouterUtilisateur( pseudo, nom, prenom, email, telephone, rue, cp, ville, mdp, confirmationMDP, credit , admin);
					response.sendRedirect(request.getContextPath()+"/Accueil");
				  } 
			  catch (BLLException e) {
				  e.printStackTrace(); 
			  }
		}
		else if (request.getParameter("annuler") != null) {	
		
			response.sendRedirect(request.getContextPath()+"/Accueil");
		}

	}
}
