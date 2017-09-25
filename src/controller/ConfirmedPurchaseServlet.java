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
import model.MetodoDiConsegna;
import model.Payment;
import model.User;

/**
 * Servlet implementation class ConfirmedPurchaseServlet
 * @author Paolo D'Arienzo
 * @version 1.5
 */
@WebServlet("/confirmedPurchase")
public class ConfirmedPurchaseServlet extends HttpServlet {

	private static final long serialVersionUID = -7327126854806825734L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmedPurchaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/purchaseSuccessView.jsp");
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
    	User loggedUser = (User) session.getAttribute("currentSessionUser");
    	
    	String dlvMethod = request.getParameter("dlvMethod"); 
    	String dlvPoint = request.getParameter("dlvPoint"); 
    	String paymt = request.getParameter("paymt"); 
    	
    	Payment pagamentoScelto = null;
    	
    	if(paymt != null) {
    		int indexPayment = Integer.parseInt(paymt);
    		pagamentoScelto = loggedUser.getPayment().get(indexPayment);
    	}
    	
    	DeliveryPoint puntoDiConsegnaScelto = null;
    	
    	if(dlvPoint != null) {
    		int indexDlvPoint = Integer.parseInt(dlvPoint);
    		puntoDiConsegnaScelto = loggedUser.getDeliveryPoint().get(indexDlvPoint);
    	}
    	
    	MetodoDiConsegna metodoDiConsegnaScelto = null;
    	
    	if(dlvMethod != null) {
    		metodoDiConsegnaScelto = MetodoDiConsegna.valueOf(dlvMethod); 
    	}
    	
    	String IPClient = getClientIpAddr(request);
    	
    	try {
			loggedUser.confirmPurchase(pagamentoScelto, metodoDiConsegnaScelto, puntoDiConsegnaScelto, IPClient);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

	/**
	 * Gets the IP address (IPv6) of the client
	 * @param request passed from client
	 * @return String containing the IP address
	 */
	private static String getClientIpAddr(HttpServletRequest request) {  
        String ip = request.getHeader("X-Forwarded-For");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }
	
}
