package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeliveryPoint;
import model.User;

/**
 * Servlet implementation class ModifyDeliveryPointServlet
 * @author Paolo D'Arienzo
 * @version 1.5
 */
@WebServlet("/modifyDeliveryPoint")
public class ModifyDeliveryPointServlet extends HttpServlet {

	private static final long serialVersionUID = -8156871960398825817L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyDeliveryPointServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/addDlvPointView.jsp");

		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/userProfileView.jsp");
		
		HttpSession session = request.getSession(true);
    	User loggedUser = (User) session.getAttribute("currentSessionUser");
    	
    	String citta = request.getParameter("city");
		String via = request.getParameter("street");
		String civico = request.getParameter("civicNmbr");
		String CAP = request.getParameter("CAP");
		
		DeliveryPoint dlvPointSelezionato = null;
		
		if(request.getParameter("dlvPntHypothetical") != null) {
			for(DeliveryPoint dlvPntIpotetico : loggedUser.getDeliveryPoint()) {
				String dlvIpoteticoStr = dlvPntIpotetico.getUserMailFromDelPoint() + dlvPntIpotetico.toString();
				if((dlvIpoteticoStr).equals(request.getParameter("dlvPntHypothetical"))) {
					dlvPointSelezionato = dlvPntIpotetico;
				}
			}	
		}
		
		if(citta != null && via != null) { //Other values are obviously not null, because the form is required
			
			assert civico != null;
			assert CAP != null;
		
			DeliveryPoint newPoint = new DeliveryPoint(loggedUser.getMail(), citta, via, civico, CAP);
			
			try {
				loggedUser.addDeliveryPoint(newPoint);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dispatcher.forward(request, response);
			return;
		
		}
		else if(request.getParameter("remove") != null) {
			try {
				loggedUser.removeDeliveryPoint(dlvPointSelezionato);
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
