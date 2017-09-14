package model;

import java.net.InetAddress;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Purchase class represents a purchase done by a user.
 * @author Paolo D'Arienzo
 * @version 1.5
 *
 */
public class Purchase {
    
	private final String userMail;
    private final int ID;
    private final Date data;
    private final Time ora;
    private final InetAddress IPAddress;
    private final MetodoDiConsegna metodoDiConsegna;
    private final Payment pagamento;
    private final float prezzoTotale;
    private final List<ProductInCart> articoliAcquistati;
    
    /**
     * Constructor of purchase.
     * @param mail is the e-mail of the user
     * @param ID is the ID of the purchase
     * @param IPAddress is the IP address of the user who execute the purchase
     * @param metodoDiConsegna is the delivery method chosen by the user
     * @param pagamento is the payment used for the purchase
     * @param prezzoTotale is the total price of the purchase
     * @param articoliAcquistati is the list of products purchased
     */
    public Purchase(String mail, int ID, InetAddress IPAddress, MetodoDiConsegna metodoDiConsegna,
    				Payment pagamento, float prezzoTotale, List<ProductInCart> articoliAcquistati){
    	this.userMail = mail;
        this.ID = ID;
        this.data = new Date(System.currentTimeMillis());
        this.ora = new Time(System.currentTimeMillis());
        this.IPAddress = IPAddress;
        this.metodoDiConsegna = metodoDiConsegna;
        this.pagamento = pagamento;
        this.prezzoTotale = prezzoTotale;
        this.articoliAcquistati = articoliAcquistati;
    }
    
    /**
     * Returns the mail of the user
     * @return the mail of the user
     */
    public String getUserMailFromPurchase(){
    	return this.userMail;
    }
    
    /**
     * Returns the ID of the purchase
     * @return the ID of the purchase
     */
    public int getIDPurchase(){
    	return this.ID;
    }
    
    /**
     * Returns the date of purchase
     * @return the date of purchase
     */
    public Date getPurchaseDate(){
    	return this.data;
    }
    
    /**
     * Returns the time of purchase
     * @return the time of purchase
     */
    public Time getPurchaseTime(){
    	return this.ora;
    }
    
    /**
     * Returns the IP of the client
     * @return the IP of the client
     */
    public InetAddress getPurchaseIP(){
    	return this.IPAddress;
    }
    
    /**
     * Returns the delivery method of the purchase
     * @return the delivery method of the purchase
     */
    public MetodoDiConsegna getMetodoDiConsegnaFromPurchase(){
    	return this.metodoDiConsegna;
    }
    
    /**
     * Returns the payment method of the purchase
     * @return the payment method of the purchase
     */
    public Payment getPaymentFromPurchase(){
    	return this.pagamento;
    }
    
    /**
     * Returns the final price of the shopping cart, i.e. the sum of the products purchased
     * @return the final price of the shopping cart, i.e. the sum of the products purchased
     */
    public float getFinalPriceFromPurchase(){
    	return this.prezzoTotale;
    }
    
    /**
     * Returns the list of products purchased
     * @return the list of products purchased
     */
    public List<ProductInCart> getListOfProductsPurchased(){
    	return this.articoliAcquistati;
    }
    
    //TODO
    public void addInDatabase(){    	
    }
    
    //TODO
    public void updateInDatabase(){
    }
    
    //TODO
    public void deleteFromDatabase(){
    }
    
}