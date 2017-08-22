package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {

	//TODO
	//To autogenerate
	private static final long serialVersionUID = -1953084286713579746L;
	
	public SearchServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		String brandSelected= request.getParameter("brand"); 
		String selectedInstrumentType= request.getParameter("instrumentType"); 
		String selectedUsedStatus= request.getParameter("used"); 
		String selectedProductType= request.getParameter("productType");
		
		System.out.println("Inseriti: " + brandSelected + ", "
							+ selectedInstrumentType + ", "
							+ selectedUsedStatus + ", "
							+ selectedProductType + ".");
		
		
	}

}
