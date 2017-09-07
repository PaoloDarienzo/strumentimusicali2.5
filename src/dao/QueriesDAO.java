//TODO
//JAVADOC

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class QueriesDAO {
	
    private final static String JDBC_URL = "jdbc:postgresql://localhost:5432/strumenti_database";
    private final static String JDBC_USERNAME = "postgres";
    private final static String JDBC_PASSWORD = "effe";
    
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

    public static List<Product> getProducts(String search, String brandSelected, String selectedInstrumentType, 
    										String selectedUsedStatus, String selectedProductType) throws ClassNotFoundException{
    	
    	//TODO
    	
    	if(brandSelected == "All")
    		brandSelected = "%";
     	
    	if(selectedInstrumentType == "All")
    		brandSelected = "%";
    	
     	if(selectedProductType == "All")
     		selectedProductType="%";
    	
    	Class.forName("org.postgresql.Driver");
    	
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
		    		
			try (PreparedStatement pst = con.prepareStatement(
		    				"SELECT * FROM strumento "
		    				+ "WHERE marca LIKE ? AND "
		    				+ "classificazione LIKE ? AND "
		    				+ "producttype = ?" )) {
				
				pst.setString(1, brandSelected);
				pst.setString(2, selectedInstrumentType);
				pst.setString(3, selectedProductType);
				
			ResultSet rs = pst.executeQuery();
			
			//System.out.println(rs.));
			System.out.println("DIOGA'");
			while (rs.next()) {
				System.out.println(rs.getInt(0));
				System.out.println("DIOGA");
			}
			
		} catch (SQLException e) {
			System.out.println("Errore durante query dei dati: " + e.getMessage());
			}
			
		} catch (SQLException e){
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			}    	
    	
    	return null;
    	
    }
    
}
