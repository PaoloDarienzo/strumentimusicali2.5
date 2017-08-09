package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Payment;



/**
 * PaymentDAO class contains all the methods that interact with the database relatively to 
 * the payments and to the classes that extend it.
 * @author Paolo D'Arienzo
 * @version 1.4
 */
public class PaymentDAO {
	
    private final static String JDBC_URL = "jdbc:postgresql://localhost:5432/strumenti_database";
    private final static String JDBC_USERNAME = "postgres";
    private final static String JDBC_PASSWORD = "effe";
    
    private final static String NOME_TABELLA = "metododipagamento";
	
    /**
     * Adds one payment method to the user's list of payment methods in the database
     * @param pagamento payment method that has to be added
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     */
	public static void addOnePayment(Payment pagamento) throws ClassNotFoundException{
		
		//Insertion occurs in table "metododipagamento"
    	//username:= postgres
    	//password:= effe
    	//Database name:=strumenti_database
    	
    	Class.forName("org.postgresql.Driver");
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
    		
    		try (PreparedStatement pst = con.prepareStatement(
    				"INSERT INTO " + NOME_TABELLA + " "
    				+ "(cliente, nomemetodo, credenziali) "
    				+ "VALUES (?,?,?)")) {
    			pst.clearParameters();
    			
    			pst.setString(1, pagamento.getUserMailFromPayment());
    			pst.setString(2, pagamento.getNomeMetodo());
    			pst.setString(3, pagamento.getCredenziali());
    			
    			int n = pst.executeUpdate();
    			System.out.println("Inserite " + n + " righe in tabella " 
    								+ NOME_TABELLA + ": " + pagamento.getUserMailFromPayment() + ".");
    			
    		} catch (SQLException e) {
    			System.out.println("Errore durante inserimento dati: " + e.getMessage());
    		}
    		
    	} catch (SQLException e){
    		System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
    	}
		
	}
	
	/**
	 * Removes one payment method from the user's list of payments methods in the database
	 * @param pagamento payment to remove
     * @throws ClassNotFoundException if an error occurs with the connection to the database
	 */
	public static void removeOnePayment(Payment pagamento) throws ClassNotFoundException{
		
		//Deletion occurs in table "metododipagamento"
    	//username:= postgres
    	//password:= effe
    	//Database name:=strumenti_database
    	
    	Class.forName("org.postgresql.Driver");
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
    		
    		try (PreparedStatement pst = con.prepareStatement(
    				"DELETE FROM " + NOME_TABELLA + " "
    				+ "WHERE cliente = ? AND "
    				+ "nomemetodo = ? AND "
    				+ "credenziali = ?")) {
    			pst.clearParameters();
    			
    			pst.setString(1, pagamento.getUserMailFromPayment());
    			pst.setString(2, pagamento.getNomeMetodo());
    			pst.setString(3, pagamento.getCredenziali());
    			
    			int n = pst.executeUpdate();
    			System.out.println("Rimosse " + n + " righe.");
    			
    		} catch (SQLException e) {
    			System.out.println("Errore durante cancellazione dati: " + e.getMessage());
    		}
    		
    	} catch (SQLException e){
    		System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
    	}
		
	}
	
	/**
	 * Queries the database in search of all the payment methods of the indicated user
	 * @param mailUtente indicates the user from which take the payments
	 * @return the ArrayList of payments of the user
     * @throws ClassNotFoundException if an error occurs with the connection to the database
	 */
	public static List<Payment> getPaymentsFromDatabase(String mailUtente) throws ClassNotFoundException {
		
		//Query occurs in table "metododipagamento"
    	//username:= postgres
    	//password:= effe
    	//Database name:=strumenti_database
		
		List<Payment> pagamentiAggiornati = new ArrayList<Payment>();
    	
    	Class.forName("org.postgresql.Driver");
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
    		
    		try (PreparedStatement pst = con.prepareStatement(
    				"SELECT * FROM  " + NOME_TABELLA + " "
    				+ "WHERE cliente = ?")) {
    			pst.clearParameters();
    			
    			pst.setString(1, mailUtente);
    			
    			ResultSet rs = pst.executeQuery();
    			
    			while (rs.next()) {

    				String userMail = rs.getString("cliente");
    				String nomeMetodo = rs.getString("nomemetodo");
    				String credenziali = rs.getString("credenziali");
    				
    				pagamentiAggiornati.add(new Payment(userMail, nomeMetodo, credenziali));

    			}
    			
    		} catch (SQLException e) {
    			System.out.println("Errore durante query dei dati: " + e.getMessage());
    		}
    		
    	} catch (SQLException e){
    		System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
    	}
    	
    	return pagamentiAggiornati;
		
	}

}