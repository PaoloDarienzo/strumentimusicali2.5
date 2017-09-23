//TODO
//ID in DAO?

package view;

import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.QueriesDAO;

/**
 * 
 * @author Paolo D'Arienzo
 * @version 1.4
 *
 */
public class Main {
	
	private static int purchaseID = 0;
	
	private static int productID = 0;
	
    private final static String JDBC_URL = "jdbc:postgresql://localhost:5432/strumenti_database";
    private final static String JDBC_USERNAME = "postgres";
    private final static String JDBC_PASSWORD = "effe";
	
    /**
     * Updates the productID that is unique per JVM run.
     * @return the updated value of <i>productID</i>
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     */
	public static synchronized int createProductID() throws ClassNotFoundException{
		int maxID = QueriesDAO.maxIDInDatabase("strumento");
		while(productID <= maxID) {
			productID++;
		}
	    return productID++;
	}
	
    /**
     * Updates the purchaseID that is unique per JVM run.
     * @return the updated value of <i>purchaseID</i>
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     */
	public static synchronized int createPurchaseID() throws ClassNotFoundException{
		int maxID = QueriesDAO.maxIDInDatabase("ordine");
		while(purchaseID <= maxID) {
			purchaseID++;
		}
	    return purchaseID++;
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchAlgorithmException, UnknownHostException{
		
		/*
		//Clearing del database
		svuotaTab("cliente");
		svuotaTab("strumento");
		svuotaTab("metododipagamento");
		svuotaTab("puntodiconsegna");
		svuotaTab("aggiunto");
		*/
		
	}
	
	//TODO
	//Da rimuovere, serve per svuotare il database
	private static void svuotaTab(String nomeTabella) throws ClassNotFoundException{
		
		Class.forName("org.postgresql.Driver");
		
	   	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
	    	
	   		try(Statement st = con.createStatement()){
	   			st.executeUpdate("DELETE FROM " + nomeTabella);
	   		} catch (SQLException e) {
    			System.out.println("Errore durante eliminazione dati da " + nomeTabella + ": " + e.getMessage());
    		}
	   		
	   	} catch (SQLException e){
    		System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
    	}
		
	}	
	
}//End of Main class