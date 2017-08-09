package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

/**
 * UserDAO class contains all the methods that interact with the database relatively to the user.
 * @author Paolo D'Arienzo
 * @version 1.4
 *
 */
public class UserDAO {
	
    private final static String JDBC_URL = "jdbc:postgresql://localhost:5432/strumenti_database";
    private final static String JDBC_USERNAME = "postgres";
    private final static String JDBC_PASSWORD = "effe";
    
    private final static String NOME_TABELLA = "cliente";
      
    /**
     * Adds the user in the database
     * @param utente is the user to add in the database
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     */
    public static void addInDatabase(User utente) throws ClassNotFoundException{
    	
    	//Insertion occurs in table "cliente"
    	//username:= postgres
    	//password:= effe
    	//Database name:=strumenti_database
    	
    	Class.forName("org.postgresql.Driver");
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
    		
    		try (PreparedStatement pst = con.prepareStatement(
    				"INSERT INTO " + NOME_TABELLA + " "
    				+ "(mail, nomeutente, password, nome, cognome, cf, ntelefono, ncellulare, cittadiresidenza, tipo) "
    				+ "VALUES (?,?,?,?,?,?,?,?,?,?)")) {
    			pst.clearParameters();
    			
    			pst.setString(1, utente.getMail());
    			pst.setString(2, utente.getNomeUtente());
    			pst.setString(3, utente.getPassword());
    			pst.setString(4, utente.getNome());
    			pst.setString(5, utente.getCognome());
    			pst.setString(6, utente.getCF());
    			pst.setString(7, utente.getNumeroTelefono());
    			pst.setString(8, utente.getNumeroCellulare());
    			pst.setString(9, utente.getCittaDiResidenza());
    			pst.setString(10, utente.getTipo().toString());
    			int n = pst.executeUpdate();
    			System.out.println("Inserite " + n + " righe in tabella cliente: " + utente.getMail() + ".");
    			
    		} catch (SQLException e) {
    			System.out.println("Errore durante inserimento dati: " + e.getMessage());
    		}
    		
    	} catch (SQLException e){
    		System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
    	}
    	
    }
    
    /**
     * Updates the user in the database; the only thing that can be updated is the private telephone number
     * @param utente is the user to update in the database
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     */
    public static void updateInDatabase(User utente) throws ClassNotFoundException{
    	
    	//Update occurs in table "cliente"
    	//username:= postgres
    	//password:= effe
    	//Database name:=strumenti_database
    	
    	Class.forName("org.postgresql.Driver");
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
    		
    		try (PreparedStatement pst = con.prepareStatement(
    				"UPDATE " + NOME_TABELLA + " "
    				+ "SET ncellulare = ?"
    				+ "WHERE mail = ?")) {
    			pst.clearParameters();
    			
    			pst.setString(1, utente.getNumeroCellulare());
    			pst.setString(2, utente.getMail());
    			
    			int n = pst.executeUpdate();
    			System.out.println("Modificate " + n + " righe in tabella cliente: " + utente.getMail() + ".");
    			
    		} catch (SQLException e) {
    			System.out.println("Errore durante aggiornamento dati: " + e.getMessage());
    		}
    		
    	} catch (SQLException e){
    		System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
    	}
    }
    
    /**
     * Removes the user from database
     * @param userMail is the mail of the user that have to be deleted in the database
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     */
    public static void deleteFromDatabase(String userMail) throws ClassNotFoundException{
    	
    	//Deletion occurs in table "cliente"
    	//username:= postgres
    	//password:= effe
    	//Database name:=strumenti_database
    	
    	Class.forName("org.postgresql.Driver");
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
    		
    		try (Statement st = con.createStatement()){
    			st.executeUpdate(
    					"DELETE FROM " + NOME_TABELLA + " "
    					+ "WHERE mail = " + userMail);
    			
    			int n = st.getUpdateCount();
    			System.out.println("Rimosse " + n + " righe.");
    			
    		} catch (SQLException e) {
    			System.out.println("Errore durante cancellazione dati: " + e.getMessage());
    		}
    		
    	} catch (SQLException e){
    		System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
    	}
    	
    }

}