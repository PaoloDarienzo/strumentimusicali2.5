package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import model.User;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(urlPatterns = { "/cart"})
public class CartServlet extends HttpServlet {
    //TODO
	private static final long serialVersionUID = 5262857407246188300L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
    }

	/**
	 * 
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/cartView.jsp");

		dispatcher.forward(request, response);
		
	}

	/**
	 * 
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	int productID = Integer.parseInt(request.getParameter("productIDToAdd"));
    	
    	HttpSession session = request.getSession(true);
    	User loggedUser = (User) session.getAttribute("currentSessionUser");
    	
    	try {
			loggedUser.getShoppingCart().addToCart(ProductDAO.getFromDatabase(productID));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/cartView.jsp");
		
		dispatcher.forward(request, response);
    
    }
    
    

}