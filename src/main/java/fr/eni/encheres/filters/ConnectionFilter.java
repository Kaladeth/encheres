package fr.eni.encheres.filters;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class ConnectionFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/connecte/*" })
public class ConnectionFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public ConnectionFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
				//cast http servlet request pour transformer la request en http request pour avoir acces à l a methode getSession() 
				HttpServletRequest httpRequest = (HttpServletRequest) request;
				
				HttpSession session = httpRequest.getSession();
				//des infos sur l'état du feu ? 
				// - attribut existant dans la session => feu au vert
				// - sinon => feu au rouge
				String feu = null;
				if (session.getAttribute("feu") != null) {
					feu = String.valueOf(session.getAttribute("feu"));
				}
				//le feu est il rouge ?
				if (feu == null) {
					//intercepter la requete en cours et établir une redirection
					httpRequest.setAttribute("messageErreur", "Vous devez être connecté pour accéder à cette partie du site !");
					httpRequest.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

				} else {
					// pass the request along the filter chain
					chain.doFilter(request, response);
				}
			}


	/**
	 * @see Filter#init(FilterConfig)
	 * 
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
