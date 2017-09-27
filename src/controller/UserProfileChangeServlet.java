package controller;

import java.io.IOException;

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
 * Servlet implementation class UserProfileChangeServlet
 * @author Paolo D'Arienzo
 * @version 1.5
 */
@WebServlet("/userProfileChange")
public class UserProfileChangeServlet extends HttpServlet {

	private static final long serialVersionUID = -5346906787921634932L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/userProfileChange.jsp");

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ncellulare = request.getParameter("cellPhone");
		
		HttpSession session = request.getSession(true);
    	User loggedUser = (User) session.getAttribute("currentSessionUser");
    	
    	loggedUser.setNumeroCellulare(ncellulare);
    	
    	try {
			UserDAO.updateInDatabase(loggedUser);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/userProfileView.jsp");

		dispatcher.forward(request, response);
		
	}

}
