//TODO
//Javadoc
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(urlPatterns = { "/search"})
public class SearchServlet extends HttpServlet {

	//TODO
	//To autogenerate
	private static final long serialVersionUID = 1770411980658250964L;	
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public SearchServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		String searchParameters= request.getParameter("search"); 
		
		String brandSelected= request.getParameter("brand"); 
		String selectedInstrumentType= request.getParameter("instrumentType"); 
		String selectedUsedStatus= request.getParameter("used"); 
		String selectedProductType= request.getParameter("productType");
		
		System.out.println("Inserted: " + searchParameters + ", "
							+ brandSelected + ", "
							+ selectedInstrumentType + ", "
							+ selectedUsedStatus + ", "
							+ selectedProductType + ".");
		
		//QueriesDAO.getProducts(searchParameters, brandSelected, selectedInstrumentType, selectedInstrumentType, selectedProductType);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/searchView.jsp");

		dispatcher.forward(request, response);
		
		
	}

}
