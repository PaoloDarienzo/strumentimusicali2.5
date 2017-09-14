package view;

import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.*;

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
     */
	public static synchronized int createProductID(){
	    return productID++;
	}
	
    /**
     * Updates the purchaseID that is unique per JVM run.
     * @return the updated value of <i>purchaseID</i>
     */
	public static synchronized int createPurchaseID(){
	    return purchaseID++;
	}
	
	/**
	 * Executes a query on the database <i>strumenti_professionali</i>, on the table indicated,
	 *  in search of the maximum value of <i>ID</i>.
	 * @param tabella is the table in which searching the maximum value
	 * @return an int representing the maximum value found for the ID
	 * @throws ClassNotFoundException if an error occurs with the connection to the database
	 */
	public static int maxIDInDatabase(String tabella) throws ClassNotFoundException{
		
		Class.forName("org.postgresql.Driver");
		
	   	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
	    	
	   		try(Statement st = con.createStatement()){
	   			
	   			ResultSet rs = st.executeQuery("SELECT MAX(ID) FROM " + tabella);
	   			
	   			while (rs.next()) {
	   				return rs.getInt(1);
	   			}
	   			
	   		} catch (SQLException e) {
    			System.out.println("Errore durante interrogazione della tabella strumento: " + e.getMessage());
    			return -1;
    		}
	   		
	   	} catch (SQLException e){
    		System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
    		return -2;
    	}
		return -3;
	   	
	}//End maxIDInDatabase

	public static void main(String[] args) throws ClassNotFoundException, NoSuchAlgorithmException, UnknownHostException{
		
		/*
		//Clearing del database
		svuotaTab("cliente");
		svuotaTab("strumento");
		svuotaTab("metododipagamento");
		svuotaTab("puntodiconsegna");
		svuotaTab("aggiunto");
		*/
		
		svuotaTab("cliente");
		
		//Creazione di utenti
		
		User admin0 = new User("lhe1995@gmail.com", "Lucy", Encode.cryptingString("minzione"),
				"Lucia", "Crivellini", "3909829182", "Verona",
				"LCLZCRV9484123", TipoCliente.ADMIN, "93980908890");
		
		User admin1 = new User("lorenzorik@gmail.com", "lorenzoricci", Encode.cryptingString("Sandalo94"),
								"Lorenzo", "Ricci", "3409320876", "San Giovanni Lupatoto",
								"LRNRCC9325", TipoCliente.ADMIN, "93980908890");
		
		User admin2 = new User("Pleasant94@gmail.com", "paolodarienzo", Encode.cryptingString("Sandalo94"),
								"Paolo", "D'Arienzo", "3460996201", "Povegliano Veronese",
								"DRNPLA9426NG", TipoCliente.ADMIN);
		
		User PC1 = new User("paolo.darienzo@studenti.univr.it", "paolodarienzo2", Encode.cryptingString("Criptami"),
									"Paolo", "D'Arienzo", "1234567890", "Verona", "DRNAPAMDWNO12F", TipoCliente.PROFESSIONISTA);
		
		User HC1 = new User("lorenzo.ricci@studenti.univr.it", "lorenzoricci2", 
				Encode.cryptingString("CriptamiTutto"), "Lorenzo", "Ricci", "1234567890", "Lugagnano", "ASDQO8N310L", TipoCliente.TITOLARE);
		
		User occasionale1 = new User("mario.rossi@gmail.com", "rossirossi", Encode.cryptingString("albero"),
				"Mario", "Rossi", "0987654321", "Roma", "RSSMAR102FLAS");

		//Aggiunta di utenti al database
		admin1.addInDatabase();
		admin2.addInDatabase();
		PC1.addInDatabase();
		HC1.addInDatabase();
		occasionale1.addInDatabase();
		
		admin0.addInDatabase();
		
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