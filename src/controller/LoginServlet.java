package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class LoginServlet
 * @author Paolo D'Arienzo
 * @version 1.5
 */
@WebServlet(urlPatterns = { "/login"})
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 5876378905741836199L;

	public LoginServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/loginView.jsp");

		dispatcher.forward(request, response);
		
	}
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String userID = request.getParameter("uname");    
        String psw = request.getParameter("psw");
        
        try {
        	
			User user = UserDAO.getUser(userID, psw);
			
			if(user != null) {
				
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", user);
				
				response.sendRedirect("/strumentimusicali2.5"); //success
			}
			
			else {
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/loginResponseView.jsp");
				
				request.setAttribute("loginResponse", "Error: username or password are wrong.");
				dispatcher.forward(request, response);
				return;
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	
    }
 
}