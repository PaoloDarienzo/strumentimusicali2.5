//TODO
//Ridefinire tutta la pagina jsp

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
import model.Encode;
import model.User;

@WebServlet(urlPatterns = { "/login"})
public class LoginServlet extends HttpServlet {
	
	//TODO
	//TO autogenerate
	private static final long serialVersionUID = 5876378905741836199L;
	
    public LoginServlet() {
        super();
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String userID = request.getParameter("uname");    
        String psw = request.getParameter("psw");
        
        System.out.println(psw);
        
        try {
        	
			User user = UserDAO.getUser(userID, psw);
			
			if(user != null) {
				
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", user);
				
				response.sendRedirect("/strumentimusicali2.5"); //success
			}
			
			else {
				//TODO
				System.exit(1);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	
    }
 
}