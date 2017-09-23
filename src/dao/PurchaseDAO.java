package dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.DeliveryPoint;
import model.MetodoDiConsegna;
import model.Payment;
import model.ProductInCart;
import model.Purchase;

public class PurchaseDAO {
	
	private final static String JDBC_URL = "jdbc:postgresql://localhost:5432/strumenti_database";
    private final static String JDBC_USERNAME = "postgres";
    private final static String JDBC_PASSWORD = "effe";
    
    private final static String NOME_TABELLA = "ordine";
	
	public static void addInDatabase(Purchase acquisto) throws ClassNotFoundException {
		
		//Insertion occurs in table "aggiunto"
    	//username:= postgres
    	//password:= effe
    	//Database name:=strumenti_database
		
		Class.forName("org.postgresql.Driver");
		
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){
    		
    		//insertion in "aggiunto"
    		try (PreparedStatement pst = con.prepareStatement(
    				"INSERT INTO " + NOME_TABELLA
    				+ "(id, ora, data, ipacquirente, metododiconsegna, "
    				+ "prezzototale, carrelloutente, via, civico, cap, "
    				+ "nomemetodo, credenziali) "
    				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)")) {
    			
    			pst.setInt(1, acquisto.getIDPurchase());
    			pst.setTime(2, acquisto.getPurchaseTime());
    			pst.setDate(3, acquisto.getPurchaseDate());
    			pst.setString(4, acquisto.getPurchaseIP().toString());
    			pst.setString(5, acquisto.getMetodoDiConsegnaFromPurchase().toString());
    			pst.setFloat(6, acquisto.getFinalPriceFromPurchase());
    			pst.setString(7, acquisto.getUserMailFromPurchase());;
    			pst.setString(8, acquisto.getPuntoDiConsegnaFromPurchase().getVia());
    			pst.setString(9, acquisto.getPuntoDiConsegnaFromPurchase().getCivico());
    			pst.setString(10, acquisto.getPuntoDiConsegnaFromPurchase().getCAP());
    			pst.setString(11, acquisto.getPaymentFromPurchase().getNomeMetodo());
    			pst.setString(12, acquisto.getPaymentFromPurchase().getCredenziali());
    			
    			int n = pst.executeUpdate();
    			System.out.println("Inserite " + n + " righe in tabella ordine: "
    			+ acquisto.getUserMailFromPurchase() + ", " + acquisto.getIDPurchase() + ".");
    			
    		} catch (SQLException e) {
    			System.out.println("Errore durante inserimento dati: " + e.getMessage());
    		}
    		
    	} catch (SQLException e){
    		System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
    	}
		
	}
	
	public static Purchase getFromDatabase(int purchaseID) throws ClassNotFoundException, UnknownHostException {
    	
    	//Query occurs in table "ordine"
    	//username:= postgres
    	//password:= effe
    	//Database name:=strumenti_database
		
    	Class.forName("org.postgresql.Driver");
    	
    	try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)){

    		try (PreparedStatement pst = con.prepareStatement(
    				"SELECT * FROM " + NOME_TABELLA + " "
    				+ "WHERE id = ?")) {
    			
    			pst.setInt(1, purchaseID);
    			
    			ResultSet rs = pst.executeQuery();
    			
    			while (rs.next()) {

    				Time ora = rs.getTime("ora");
    				Date data = rs.getDate("data");
    				String indirizzoIP = rs.getString("ipacquirente");
    				MetodoDiConsegna metodo = MetodoDiConsegna.valueOf(rs.getString("metododiconsegna"));
    				float prezzoTotale = rs.getFloat("prezzototale");
    				String proprietarioCarrello = rs.getString("carrelloutente");
    				
    				Payment pagamentoUsato = new Payment(proprietarioCarrello,
    											rs.getString("nomemetodo"), rs.getString("credenziali"));
    				
    				DeliveryPoint puntoUsato = new DeliveryPoint(proprietarioCarrello,
    											rs.getString("citta"), rs.getString("via"),
    											rs.getString("civico"), rs.getString("cap"));
    				
    				InetAddress ipAcquirente = InetAddress.getByName(indirizzoIP);
    				
    				List<ProductInCart> articoliAcquistati = new ArrayList<ProductInCart>();
    				
    				return new Purchase(data, ora, proprietarioCarrello, purchaseID, ipAcquirente, metodo,
    						pagamentoUsato, puntoUsato, prezzoTotale, articoliAcquistati);
    				 
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
