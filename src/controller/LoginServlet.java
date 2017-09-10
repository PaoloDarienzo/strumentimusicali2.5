//TODO
//Ridefinire tutta la pagina jsp

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/login"})
public class LoginServlet extends HttpServlet {
	
	//TODO
	//TO autogenerate
	private static final long serialVersionUID = 5876378905741836199L;
 
    public LoginServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
         
        // Forward to /jsp/loginView.jsp     
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/loginView.jsp");
         
        dispatcher.forward(request, response);
         
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}