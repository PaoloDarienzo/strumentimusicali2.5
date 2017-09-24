package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QueriesDAO;
import model.Product;

/**
 * Servlet implementation class SearchServlet
 * @author Paolo D'Arienzo
 * @version 1.5
 */
@WebServlet(urlPatterns = { "/search"})
public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = -3228470462210190485L;

	/**
     * @see HttpServlet#HttpServlet()
     */
	public SearchServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		List<Product> queryResults = new ArrayList<Product>();
		
		String searchParameters = request.getParameter("search");
		
		Boolean searchStringIsVoid = (searchParameters.equals("")) ? true : false;
		
		/**
		 * The search bar looks for <i>ALL</i> the inserted words, as an <i>AND</i>,
		 * and not for every products that contains words inserted in the search bar, as an <i>OR</i>.
		 */
		String search = searchParameters.replaceAll(" ", " & ");
		
		String brandSelected = request.getParameter("brand"); 
		String selectedInstrumentType = request.getParameter("instrumentType"); 
		String selectedProductType = request.getParameter("productType");
		String selectedUsedStatus = request.getParameter("used"); 
		
		System.out.println("Inserted: " + searchParameters + ", "
							+ brandSelected + ", "
							+ selectedInstrumentType + ", "
							+ selectedUsedStatus + ", "
							+ selectedProductType + ".");
		
		try {
			
			queryResults= QueriesDAO.getProducts(searchStringIsVoid, search,
					brandSelected, selectedInstrumentType, selectedUsedStatus, selectedProductType);
		
		} catch (ClassNotFoundException e) {
			System.out.println("Error with the connection to the database.");
			e.printStackTrace();
		}
		
		for(Product p: queryResults) {
			System.out.println("ID: " + p.getID() + ", nome: " + p.getNome() + ".");
		}
		
		request.setAttribute("queryResults", queryResults);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/searchView.jsp");
		
		dispatcher.forward(request, response);
		
	}

}
