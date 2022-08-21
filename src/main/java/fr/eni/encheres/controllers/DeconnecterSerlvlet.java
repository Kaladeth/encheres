package fr.eni.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeconnecterSerlvlet
 */
@WebServlet("/Deconnecter")
public class DeconnecterSerlvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeconnecterSerlvlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String pseudo = (String) session.getAttribute("pseudo");
	    if(session != null){
	    session.invalidate();
	    }
	    RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/index.jsp");
	    rd.forward(request, response);
	    }


}