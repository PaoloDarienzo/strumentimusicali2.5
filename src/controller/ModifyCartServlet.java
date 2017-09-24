package controller;


import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customExceptions.NoItemException;
import dao.ProductDAO;
import model.Product;
import model.User;

/**
 * Servlet implementation class ModifyCartServlet
 * @author Paolo D'Arienzo
 * @version 1.5
 */
@WebServlet("/modifyCart")
public class ModifyCartServlet extends HttpServlet {

	private static final long serialVersionUID = 9138833635296097912L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
    	User loggedUser = (User) session.getAttribute("currentSessionUser");
    	int productID = Integer.parseInt(request.getParameter("productID"));
    	
    	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/cartView.jsp");
    	
    	Product productToModify = null;
		try {
			productToModify = ProductDAO.getFromDatabase(productID);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(request.getParameter("increment") != null) {
			try {
				loggedUser.getShoppingCart().incrementInCart(productToModify);
			} catch (NoSuchElementException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispatcher.forward(request, response);
			return;
		}
		
		if(request.getParameter("decrement") != null) {
			try {
				loggedUser.getShoppingCart().decrementInCart(productToModify);
			} catch (NoItemException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispatcher.forward(request, response);
			return;
		}
		
		if(request.getParameter("remove") != null) {
			try {
				loggedUser.getShoppingCart().removeFromCart(productToModify);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispatcher.forward(request, response);
			return;			
		}
		
		dispatcher.forward(request, response);
		
	}

}
