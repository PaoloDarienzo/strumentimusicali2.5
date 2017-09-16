package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.Brand;
import model.Encode;
import model.TipoCliente;
import model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	//TODO
	private static final long serialVersionUID = 2152688902826659767L;

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
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/registerView.jsp");

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mail = request.getParameter("email");
		String username = request.getParameter("username");
		String psw = request.getParameter("psw");
		String pswRepeated = request.getParameter("psw-repeat");
		String name = request.getParameter("uname");
		String surname = request.getParameter("usurname");
		String cf = request.getParameter("cf");
		String ntelefono = request.getParameter("ntelefono");
		String ncellulare = request.getParameter("ncellulare");
		String city = request.getParameter("city");
		String userType = request.getParameter("userType");
		
		if(psw.equals(pswRepeated)) {
			
			//try {
				//UserDAO.isUserInDatabase(mail)
				if (false) { //mail already inserted in database
					System.exit(-1);
				}
			/*} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			try {
				if (UserDAO.isUserInDatabase(username)) { //username already picked
					System.exit(-1);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Passwords are equals, mail and username are free, registration can happens
			
			try {
				psw = Encode.cryptingString(psw);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			TipoCliente userTypeEnum;
			
			switch(userType) {
			case "c":
				userTypeEnum = TipoCliente.OCCASIONALE;
				break;
			case "p":
				userTypeEnum = TipoCliente.PROFESSIONISTA;
				break;
			case "s":
				userTypeEnum = TipoCliente.TITOLARE;
				break;
			default:
				userTypeEnum = TipoCliente.OCCASIONALE;
			}
			
			User utente = null;
			
			if (ncellulare != "") {
				utente = new User(mail, username, psw, name, surname, ntelefono, city, cf, userTypeEnum);
			}
			else {
				utente = new User(mail, username, psw, name, surname, ntelefono, city, cf, userTypeEnum, ncellulare);
			}
			
			if(utente != null) {
				try {
					UserDAO.addInDatabase(utente);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else { //Error in creation of user
				System.exit(-1);
			}
			
		}
		else { //Password are different
			System.exit(-1);
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/homeView.jsp");
		
		dispatcher.forward(request, response);
		
	}

}
