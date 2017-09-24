package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Payment;
import model.User;

/**
 * Servlet implementation class ModifyPaymentServlet
 * @author Paolo D'Arienzo
 * @version 1.5
 */
@WebServlet("/modifyPayment")
public class ModifyPaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 6624623560151401224L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyPaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/addPaymentView.jsp");

		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/userProfileView.jsp");
		
		HttpSession session = request.getSession(true);
    	User loggedUser = (User) session.getAttribute("currentSessionUser");
    	
    	String nomeMetodo = request.getParameter("pmtType");
		String credenziali = request.getParameter("credentials");
		
		Payment pagamentoSelezionato = null;
		
		if(request.getParameter("paymentHypothetical") != null) {
			for(Payment pagamentoIpotetico : loggedUser.getPayment()) {
				String pagamentoIpoteticoStr = pagamentoIpotetico.getUserMailFromPayment() + pagamentoIpotetico.getNomeMetodo() + pagamentoIpotetico.getCredenziali();
				if((pagamentoIpoteticoStr).equals(request.getParameter("paymentHypothetical"))) {
					pagamentoSelezionato = pagamentoIpotetico;
				}
			}	
		}
		
		if(nomeMetodo != null && credenziali != null) {
		
			Payment newMethod = new Payment(loggedUser.getMail(), nomeMetodo, credenziali);
			
			try {
				loggedUser.addPayment(newMethod);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dispatcher.forward(request, response);
			return;
		
		}
		else if(request.getParameter("remove") != null) {
			try {
				loggedUser.removePayment(pagamentoSelezionato);
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
