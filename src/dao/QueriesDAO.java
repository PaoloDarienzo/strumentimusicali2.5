package dao;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;

/**
 * QueriesDAO class contains all the queries on the databases needed for the project.
 * @author Paolo D'Arienzo
 * @version 1.5
 */
public class QueriesDAO {
	
    private final static String JDBC_URL = "jdbc:postgresql://localhost:5432/strumenti_database";
    private final static String JDBC_USERNAME = "postgres";
    private final static String JDBC_PASSWORD = "effe";
    
    /**
     * Queries the database looking for all the brands
     * @return the list of brands present in the database
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     */
    public static List<String> getBrands() throws ClassNotFoundException {
    	
    	List<String> result = new ArrayList<String>();
    	
    	Class.forName("org.postgresql.Driver");
    	
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
		    		
			try (PreparedStatement pst = con.prepareStatement(
		    				"SELECT nome FROM marca")) {
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				result.add(rs.getString(1));				
			}
			
		} catch (SQLException e) {
			System.out.println("Errore durante query dei dati: " + e.getMessage());
			}
			
		} catch (SQLException e){
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			}
		
		return result;
			
	}
    
    /**
     * Queries the database looking for all the instrument types
     * @return the list of instrument types present in the database
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     */
    public static List<String> getInstrumentType() throws ClassNotFoundException {
    	
    	List<String> result = new ArrayList<String>();
    	
    	Class.forName("org.postgresql.Driver");
    	
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
		    		
			try (PreparedStatement pst = con.prepareStatement(
		    				"SELECT nome FROM classificazione")) {
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				result.add(rs.getString(1));				
			}
			
		} catch (SQLException e) {
			System.out.println("Errore durante query dei dati: " + e.getMessage());
			}
			
		} catch (SQLException e){
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			}
		
		return result;
			
	}

    /**
     * Queries the database looking for all the products with the indicated filters
     * @param search is the string containing all the key words to look for
     * @param brandSelected is the filter for the brand
     * @param selectedInstrumentType is the filter for the instrument type
     * @param selectedUsedStatus is the filter for the used status
     * @param selectedProductType is the filter of the product type
     * @return the list of products found in the database with the indicated filters
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     * @throws UnknownHostException  if an error occurs with the determination of the IP address
     */
    public static List<Product> getProducts(String search, String brandSelected, String selectedInstrumentType, 
    										String selectedUsedStatus, String selectedProductType) 
    												throws ClassNotFoundException, UnknownHostException{
    	
    	//TODO
    	//Search
    	
    	List<Product> queryResults = new ArrayList<Product>();
    	
    	if(brandSelected.equals("All"))
    		brandSelected = "%";
     	
    	if(selectedInstrumentType.equals("All"))
    		selectedInstrumentType = "%";
    	
     	if(selectedProductType.equals("All"))
     		selectedProductType = "%";
     	
     	if(selectedUsedStatus.equals("0"))
     		selectedUsedStatus = "NOT UNKNOWN";     		
    	
    	Class.forName("org.postgresql.Driver");
    	
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
		    
			String query = "SELECT * FROM strumento "
							+ "WHERE marca LIKE '" + brandSelected
							+ "' AND classificazione LIKE '" + selectedInstrumentType
							+ "' AND producttype LIKE '" + selectedProductType 
							+ "' AND usato IS " + selectedUsedStatus;
			
			System.out.println("Query:\n" + query);
			
			try (Statement st = con.createStatement()) {
				st.executeQuery(query);
				
				ResultSet rs = st.getResultSet();
				
				while (rs.next()) {
					queryResults.add(ProductDAO.getFromDatabase(rs.getInt(1)));
				}
			
		} catch (SQLException e) {
			System.out.println("Errore durante query dei dati: " + e.getMessage());
			}
			
		} catch (SQLException e){
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			}
    	
    	return queryResults;
    	
    }
    
}
