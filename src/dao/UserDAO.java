package dao;

import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Encode;
import model.TipoCliente;
import model.User;

/**
 * UserDAO class contains all the methods that interact with the database relatively to the user.
 * @author Paolo D'Arienzo
 * @version 1.5
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
    					+ "WHERE mail = '" + userMail + "'");
    			
    			int n = st.getUpdateCount();
    			System.out.println("Rimosse " + n + " righe da " + NOME_TABELLA + ": " + userMail + ".");
    			
    		} catch (SQLException e) {
    			System.out.println("Errore durante cancellazione dati: " + e.getMessage());
    		}
    		
    	} catch (SQLException e){
    		System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
    	}
    	
    }
    
    /**
     * Queries the database and returns the user with the indicated ID, if the password is correct
     * @param userID it's the user ID; it can be the user mail or the nickname of the user
     * @param psw i'ts the password inserted from the user
     * @return the user found in the database
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     * @throws NoSuchAlgorithmException if the algorithm used for encrypting the password cannot be found
     * @throws UnknownHostException if an error occurs with the determination of the IP address
     */
    public static User getUser(String userID, String psw) 
    		throws ClassNotFoundException, NoSuchAlgorithmException, UnknownHostException{
    	
    	User userFound = null;
    	
    	psw = Encode.cryptingString(psw);
    	
    	Class.forName("org.postgresql.Driver");
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
			
			try (Statement st = con.createStatement()) {
				
				String loginQuery = "SELECT * FROM cliente WHERE "
						+ "(mail ILIKE '" + userID + "' OR nomeutente ILIKE '" + userID + "') "
						+ "AND password = '" + psw + "'";
				
				st.executeQuery(loginQuery);
				
				ResultSet rs = st.getResultSet();
				
				if(rs.next()) {
					
					String mail = rs.getString("mail");
					String nomeutente = rs.getString("nomeutente");
					String nome = rs.getString("nome");
					String cognome = rs.getString("Cognome");
					String cf = rs.getString("cf");
					String ntelefono = rs.getString("ntelefono");
					String ncellulare = rs.getString("ncellulare");
					String cittadiresidenza = rs.getString("cittadiresidenza");
					String tipo = rs.getString("tipo");
    				
    				TipoCliente tipoEnum = TipoCliente.valueOf(tipo);
    				
    				userFound = new User(mail, nomeutente, psw, nome, cognome, 
    						ntelefono, cittadiresidenza, cf, tipoEnum, ncellulare);
    				
    				userFound.setDeliveryPoint();
    				userFound.setPayment();
    				userFound.setShoppingCart();
					
				}
			
		} catch (SQLException e) {
			System.out.println("Errore durante query dei dati: " + e.getMessage());
			}
			
		} catch (SQLException e){
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			}

    	return userFound;
    	
    }
    
    /**
     * Returns if the parameter inserted is in the database, as email or as nickname
     * @param parameterToSearch is the parameter to check
     * @return true if the parameter is in the database, false otherwise
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     */
    public static Boolean isUserInDatabase(String parameterToSearch) throws ClassNotFoundException {
    	
    	Class.forName("org.postgresql.Driver");
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
				
				String loginQuery = "SELECT * FROM cliente WHERE "
						+ "mail ILIKE '" + parameterToSearch + "' OR nomeutente ILIKE '" + parameterToSearch + "'";
				
				st.executeQuery(loginQuery);
				
				ResultSet rs = st.getResultSet();
				
				return rs.first(); //True if ResultSet has something, false otherwise
			
		} catch (SQLException e) {
			System.out.println("Errore durante query dei dati: " + e.getMessage());
			}
			
		} catch (SQLException e){
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			}
    	
    	return null;
    	
    }
    
}