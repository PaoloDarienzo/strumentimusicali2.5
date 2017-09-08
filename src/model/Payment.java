package model;

/**
 * Payment class represents the method of payment of the user.
 * @author Paolo D'Arienzo
 * @version 1.4
 */
public class Payment {
    
	private final String userMail;
    private final String nomeMetodo;
    private final String credenziali;

    /**
     * Constructor of class Payment.
     * @param mail is the mail of the user, which identify the owner of the payment method
     * @param nomeMetodo is the name of the payment method
     * @param credenziali are the credentials of the method
     */
    public Payment(String mail, String nomeMetodo, String credenziali){
        
    	this.userMail = mail;
        this.nomeMetodo = nomeMetodo;
        this.credenziali = credenziali;
        
    }
    
    /**
     * Returns the mail of the user
     * @return the mail of the user
     */
    public String getUserMailFromPayment(){
    	return this.userMail;
    }
    
    /**
     * Returns the name of the payment method
     * @return the name of the payment method
     */
    public String getNomeMetodo(){
        return this.nomeMetodo;
    }
    
    /**
     * Returns the credentials of the payment method
     * @return the credentials of the payment method
     */
    public String getCredenziali(){
        return this.credenziali;
    }
    

    
}