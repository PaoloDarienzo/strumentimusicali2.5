package dao;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ProductInCart;
import model.ShoppingCart;
import model.User;

/**
 * ShoppingCartDAO class contains all the methods that interact with the database relatively to 
 * the shopping cart and to the classes that extend it.
 * @author Paolo D'Arienzo
 * @version 1.4
 *
 */
public class ShoppingCartDAO {
	
    private final static String JDBC_URL = "jdbc:postgresql://localhost:5432/strumenti_database";
    private final static String JDBC_USERNAME = "postgres";
    private final static String JDBC_PASSWORD = "effe";
    
    private final static String NOME_TABELLA = "aggiunto";
    
    /**
     * Adds an item and the number of it to the user's shopping cart in the database
     * @param userMail id of the owner of the shopping cart
     * @param prodotto is the product to add to the cart in the database
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     */
	public static void addOneItem(String userMail, ProductInCart prodotto) throws ClassNotFoundException{
		
		//Insertion occurs in table "aggiunto"
    	//username:= postgres
    	//password:= effe
    	//Database name:=strumenti_database
    	
    	Class.forName("org.postgresql.Driver");
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
    		
    		try (PreparedStatement pst = con.prepareStatement(
    				"INSERT INTO " + NOME_TABELLA + " "
    				+ "(strumento, cliente, npezzi) "
    				+ "VALUES (?,?,?)")) {
    			
    			pst.setInt(1, prodotto.getProduct().getID());
    			pst.setString(2, userMail);
    			pst.setInt(3, prodotto.getNumeroProdotto());
    			
    			int n = pst.executeUpdate();
    			System.out.println("Inserite " + n + " righe in tabella " 
    								+ NOME_TABELLA + ": "
    								+ userMail + ", " + prodotto.getProduct().getNome() + ", " + prodotto.getNumeroProdotto() + ".");
    			
    		} catch (SQLException e) {
    			System.out.println("Errore durante inserimento dati: " + e.getMessage());
    		}
    		
    	} catch (SQLException e){
    		System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
    	}
		
	}

    /**
     * Updates the shopping cart of the user; it sets a new quantity to the product indicated
     * @param userMail id of the owner of the shopping cart
     * @param productID is the ID of the product to update in the cart, in the database
     * @param newQuantity is the new quantity to set for the product
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     */
    public static void updateInDatabase(String userMail, int productID, int newQuantity) throws ClassNotFoundException{
    	
    	//Update occurs in table "aggiunto"
    	//username:= postgres
    	//password:= effe
    	//Database name:=strumenti_database
    	
    	Class.forName("org.postgresql.Driver");
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
    		
    		try (PreparedStatement pst = con.prepareStatement(
    				"UPDATE " + NOME_TABELLA
    				+ " SET npezzi = ?"
    				+ " WHERE strumento = ? AND cliente = ?")) {
    			
    			pst.setInt(1, newQuantity);
    			pst.setInt(2, productID);
    			pst.setString(3, userMail);
    			
    			int n = pst.executeUpdate();
    			System.out.println("Modificate " + n + " righe in tabella " + NOME_TABELLA + ": " +
    					"user: " + userMail + ", product and new num: " + productID + ", " + newQuantity + ".");
    			
    		} catch (SQLException e) {
    			System.out.println("Errore durante aggiornamento dati: " + e.getMessage());
    		}
    		
    	} catch (SQLException e){
    		System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
    	}
    }
    
    /**
     * Removes the product from the shopping cart of the user from database
     * @param userMail is the id of the owner of the shopping cart
     * @param productID is the ID of the product that have to be removed
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     */
    public static void deleteProductFromDatabase(String userMail, int productID) throws ClassNotFoundException{
    	
    	//Deletion occurs in table "aggiunto"
    	//username:= postgres
    	//password:= effe
    	//Database name:=strumenti_database
    	
    	Class.forName("org.postgresql.Driver");
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
    		
    		try (PreparedStatement pst = con.prepareStatement(
    				"DELETE FROM " + NOME_TABELLA + " "
    				+ "WHERE strumento = ? AND "
    				+ "cliente = ?")) {
    			
    			pst.setInt(1, productID);
    			pst.setString(2, userMail);
    			
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
     * Cleans the shopping cart of the indicated user in the database
     * @param userMail ID of the owner of the shopping cart
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     */
    public static void cleanShoppingCart(String userMail) throws ClassNotFoundException {
    	
    	//Deletion occurs in table "aggiunto"
    	//username:= postgres
    	//password:= effe
    	//Database name:=strumenti_database
    	
    	Class.forName("org.postgresql.Driver");
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
    		
    		try (PreparedStatement pst = con.prepareStatement(
    				"DELETE FROM " + NOME_TABELLA + " "
    				+ "WHERE cliente = ?")) {
    			
    			pst.setString(1, userMail);
    			
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
	 * Queries the database in search of the shopping cart of the indicated user
	 * @param utente is the user, owner of the shopping cart
	 * @param mailUtente indicates the user from which take the shopping cart
	 * @return the shopping cart of the user
     * @throws ClassNotFoundException if an error occurs with the connection to the database
	 * @throws UnknownHostException if an error occurs with the determination of the IP address
	 */
	public static ShoppingCart getShoppingCartFromDatabase(User utente, String mailUtente) throws ClassNotFoundException, UnknownHostException {
		
		//Query occurs in table "aggiunto"
    	//username:= postgres
    	//password:= effe
    	//Database name:=strumenti_database
		
		ShoppingCart carrelloAggiornato = new ShoppingCart(mailUtente);
    	
    	Class.forName("org.postgresql.Driver");
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
    		
    		try (PreparedStatement pst = con.prepareStatement(
    				"SELECT * FROM  " + NOME_TABELLA + " "
    				+ "WHERE cliente = ?")) {
    			
    			pst.setString(1, mailUtente);
    			
    			ResultSet rs = pst.executeQuery();
    			
    			while (rs.next()) {

    				int productID = rs.getInt("strumento");
    				int npezzi = rs.getInt("npezzi");
    				
    				utente.getShoppingCart().removeAllFromCart();
    				
    				carrelloAggiornato.addToCart(ProductDAO.getFromDatabase(productID), npezzi);

    			}
    			
    		} catch (SQLException e) {
    			System.out.println("Errore durante query dei dati: " + e.getMessage());
    		}
    		
    	} catch (SQLException e){
    		System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
    	}
    	
    	return carrelloAggiornato;
		
	}
	
}