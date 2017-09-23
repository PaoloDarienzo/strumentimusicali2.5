package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import dao.DeliveryPointDAO;
import dao.PaymentDAO;
import dao.PurchaseDAO;
import dao.ShoppingCartDAO;
import dao.UserDAO;

import static java.util.stream.Collectors.toList;

import java.net.InetAddress;
import java.net.UnknownHostException;

import view.Main;

/**
 * The User class represents whoever wants to connect to the website.
 * @author Paolo D'Arienzo
 * @version 1.5
 *
 */
public class User {
    
    private final String mail;
    private final String nomeUtente;
    private final String password;
    private final String nome;
    private final String cognome;
    private final String numeroTelefono;
    private String numeroCellulare; //if not given, is set to null
    private final String cittaDiResidenza;
    private final String CF;
    private final TipoCliente tipoCliente;
    private ShoppingCart shoppingCart;
    private List<Payment> payment;
    private List<DeliveryPoint> deliveryPoint;
    private final List<Purchase> purchase;    
    
    /**
     * Constructor of the User.
     * @param mail is the e-mail of the user
     * @param nomeUtente is the nickname of the user
     * @param password stores the encrypted password of the user
     * @param nome is the name of the user
     * @param cognome is the surname of the user
     * @param numeroTelefono is the telephone number of the user
     * @param cittaDiResidenza is the city in which resides the user
     * @param CF is the fiscal code of the user
     * @param tipoCliente indicates the type of user
     * @param numeroCellulare is the private telephone number of the user
     */
    public User(String mail, String nomeUtente, String password, String nome, String cognome, 
            String numeroTelefono, String cittaDiResidenza,
            String CF, TipoCliente tipoCliente, String numeroCellulare){
		
        this.mail = mail;
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.numeroTelefono =numeroTelefono;
        this.numeroCellulare = numeroCellulare;
        this.cittaDiResidenza = cittaDiResidenza;
        this.CF = CF;
        this.tipoCliente = tipoCliente;
        this.shoppingCart = new ShoppingCart(mail);
        this.payment = new ArrayList<Payment>();
        this.deliveryPoint = new ArrayList<DeliveryPoint>();
        this.purchase = new ArrayList<Purchase>();
    
    }
	
    /**
     * Constructor of the User with <i>numeroCellulare</i> not specified.
     * @param mail is the e-mail of the user
     * @param nomeUtente is the nickname of the user
     * @param password stores the encrypted password of the user
     * @param nome is the name of the user
     * @param cognome is the surname of the user
     * @param numeroTelefono is the telephone number of the user
     * @param cittaDiResidenza is the city in which resides the user
     * @param CF is the fiscal code of the user
     * @param tipoCliente indicates the type of user
     */
    public User(String mail, String nomeUtente, String password, String nome, String cognome, 
    		String numeroTelefono, String cittaDiResidenza, String CF, TipoCliente tipoCliente){
    	
        this.mail = mail;
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.numeroTelefono =numeroTelefono;
        this.numeroCellulare = null;
        this.cittaDiResidenza = cittaDiResidenza;
        this.CF = CF;
        this.tipoCliente = tipoCliente;
        this.shoppingCart = new ShoppingCart(mail);
        this.payment = new ArrayList<Payment>();
        this.deliveryPoint = new ArrayList<DeliveryPoint>();
        this.purchase = new ArrayList<Purchase>();
        
    }
    
    /**
     * Constructor of the User where neither <i>numeroCellulare</i> nor <i>tipoCliente</i> are specified.
     * <i>tipoCliente</i> is set to <i>OCCASIONALE</i>.
     * @param mail is the e-mail of the user
     * @param nomeUtente is the nickname of the user
     * @param password stores the encrypted password of the user
     * @param nome is the name of the user
     * @param cognome is the surname of the user
     * @param numeroTelefono is the telephone number of the user
     * @param cittaDiResidenza is the city in which resides the user
     * @param CF is the fiscal code of the user
     */
    public User(String mail, String nomeUtente, String password, String nome, String cognome, 
    		String numeroTelefono, String cittaDiResidenza, String CF){
    	
        this.mail = mail;
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.numeroTelefono =numeroTelefono;
        this.numeroCellulare = null;
        this.cittaDiResidenza = cittaDiResidenza;
        this.CF = CF;
        this.tipoCliente = TipoCliente.OCCASIONALE;
        this.shoppingCart = new ShoppingCart(mail);
        this.payment = new ArrayList<Payment>();
        this.deliveryPoint = new ArrayList<DeliveryPoint>();
        this.purchase = new ArrayList<Purchase>();
    
    }

    /**
     * Returns the user mail
     * @return the mail of the user
     */
    public String getMail(){
        return this.mail;
    }

    /**
     * Returns the nickname of the user
     * @return the nickname of the user
     */
    public String getNomeUtente(){
        return this.nomeUtente;
    }

    /**
     * Returns the encrypted password of the user
     * @return the encrypted password of the user
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Returns the name of the user
     * @return the name of the user
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * Returns the surname of the user
     * @return the surname of the user
     */
    public String getCognome(){
        return this.cognome;
    }

    /**
     * Returns the telephone number of the user
     * @return the telephone number of the user
     */
    public String getNumeroTelefono(){
        return this.numeroTelefono;
    }

    /**
     * Returns the city in which the user lives
     * @return the city in which the user lives
     */
    public String getCittaDiResidenza(){
        return this.cittaDiResidenza;
    }

    /**
     * Returns the fiscal code of the user
     * @return the fiscal code of the user
     */
    public String getCF(){
        return this.CF;
    }

    /**
     * Returns the type of the user
     * @return the type of the user
     */
    public TipoCliente getTipo(){
        return this.tipoCliente;
    }
    
    /**
     * Returns the private telephone number of the user if exists, otherwise returns <i>N/A</i>
     * @return the private telephone number of the user if exists, otherwise returns <i>N/A</i>
     */
    public String getNumeroCellulare(){        
        return (this.numeroCellulare != null) ? this.numeroCellulare : "N/A";        
    }
    
    /**
     * Sets a new private telephone number of the user
     * @param numeroCellulare is the new private telephone number of the user
     */
    public void setNumeroCellulare(String numeroCellulare){
        this.numeroCellulare = numeroCellulare;
    }

    /**
     * Returns the shopping cart
     * @return the shopping cart
     */
    public ShoppingCart getShoppingCart(){
        return this.shoppingCart;
    }
    
    /**
     * Sets the shopping cart after querying the database
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     * @throws UnknownHostException if an error occurs with the determination of the IP address
     * @see ShoppingCartDAO#getShoppingCartFromDatabase(User, String)
     */
    public void setShoppingCart() throws ClassNotFoundException, UnknownHostException{
    	this.shoppingCart = ShoppingCartDAO.getShoppingCartFromDatabase(this, this.mail);
    }
    
    /**
     * Returns the list of payment methods
     * @return the list of payment methods
     */
    public List<Payment> getPayment(){
        return this.payment;
    }
    
    /**
     * Sets the list of payment methods after querying the database
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     * @see PaymentDAO#getPaymentsFromDatabase(String)
     */
    public void setPayment() throws ClassNotFoundException{
    	this.payment.clear();
    	this.payment = PaymentDAO.getPaymentsFromDatabase(this.mail);
    }
    
    /**
     * Adds a payment method in the list
     * @param newMethod is the new payment method that is added
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     * @see PaymentDAO#addOnePayment(Payment)
     */
    public void addPayment(Payment newMethod) throws ClassNotFoundException{
    	
    	Optional<Payment> result = this.payment.stream()
        		.filter(a -> Objects.equals(a, newMethod))
                .findFirst();
    	
    	if(!result.isPresent()) {
    		this.payment.add(newMethod);
    		PaymentDAO.addOnePayment(newMethod);
    	}
    	else
    		System.out.println("Metodo gia' esistente.");
        
    }
    
    /**
     * Removes a payment method from the list of payment methods associated to the user;
     * removes the first occurrence of the parameter passed
     * @param paymentToRemove is the payment that has to be removed from the list of payment methods
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     * @see PaymentDAO#removeOnePayment(Payment)
     */
    public void removePayment(Payment paymentToRemove) throws ClassNotFoundException{
    	this.payment.remove(paymentToRemove);
    	PaymentDAO.removeOnePayment(paymentToRemove);
    }
    
    /**
     * Returns the list of delivery points
     * @return the list of delivery points
     */
    public List<DeliveryPoint> getDeliveryPoint(){
        return this.deliveryPoint;        
    }
    
    /**
     * Sets the list of delivery points after querying the database
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     * @see DeliveryPointDAO#getDeliveryPointsFromDatabase(String)
     */
    public void setDeliveryPoint() throws ClassNotFoundException{
    	this.deliveryPoint.clear();
    	this.deliveryPoint = DeliveryPointDAO.getDeliveryPointsFromDatabase(this.mail);
    }

    /**
     * Adds a delivery point in the list
     * @param newPoint is the new delivery point that is added
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     * @see DeliveryPointDAO#addOneDeliveryPoint(DeliveryPoint)
     */
    public void addDeliveryPoint(DeliveryPoint newPoint) throws ClassNotFoundException{
    	
    	Optional<DeliveryPoint> result = this.deliveryPoint.stream()
        		.filter(a -> Objects.equals(a, newPoint))
                .findFirst();
    	
    	if(!result.isPresent()) {
    		this.deliveryPoint.add(newPoint);
    		DeliveryPointDAO.addOneDeliveryPoint(newPoint);
    	}
    	else
    		System.out.println("Punto di consegna gia' esistente.");
    	
    } 
    
    /**
     * Removes a delivery point from the list of delivery points associated to the user;
     * removes the first occurrence of the parameter passed
     * @param pointToRemove is the delivery point that has to be removed from the list of delivery points
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     * @see DeliveryPointDAO#removeOneDeliveryPoint(DeliveryPoint)
     */
    public void removeDeliveryPoint(DeliveryPoint pointToRemove) throws ClassNotFoundException{
    	this.deliveryPoint.remove(pointToRemove);
    	DeliveryPointDAO.removeOneDeliveryPoint(pointToRemove);
    }
    
    /**
     * Returns the purchase list of the user
     * @return the purchase list of the user
     */
    public List<Purchase> getPurchase(){
    	return this.purchase;
    }
    
    /**
     * Returns the purchase of the indicated index in the list
     * @param index is the index of the required purchase
     * @return the purchase of the index indicated
     */
    public Purchase getPurchase(int index){
    	return this.purchase.get(index);
    }
    
    /**
     * Returns the purchase of the indicated date in the list
     * @param date is the date of the required purchase
     * @return the purchase of the date indicated
     */
    public Purchase getPurchase(Date date){
    	
        Optional<Purchase> result = this.purchase.stream()
        		.filter(a -> Objects.equals(a.getPurchaseDate(), date))
                .findFirst();
    	
    	return result.get();
    }
    
    /**
     * Creates a new <i>purchase</i> and adds it in the user's purchase list
     * @param pagamento is the purchase that has to be added
     * @param metodoDiConsegna is the delivery method chosen by the user
     * @param puntoDiConsegna is the delivery point chosen by the user
     * @param host IP address of the client
     * @throws UnknownHostException when is not possible to determine the IP address
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     */
    public void confirmPurchase(Payment pagamento, MetodoDiConsegna metodoDiConsegna, 
    							DeliveryPoint puntoDiConsegna, String host) 
    		throws UnknownHostException, ClassNotFoundException{
    	
    	//if the cart is empty, there is nothing to register
    	if (this.shoppingCart.getArticoliInCarrello().isEmpty()){
    		System.out.println("Nothing to register; the shopping cart is empty.");
    	}
    	
    	else{
    		//Copying shopping cart
    		List<ProductInCart> articoliCopia = this.shoppingCart.getArticoliInCarrello()
					.stream()
					.collect(toList());
    		
    		InetAddress UserIP = InetAddress.getByName(host);
    		
			Purchase acquisto = new Purchase(this.mail, Main.createPurchaseID(), UserIP, 
					metodoDiConsegna, pagamento, puntoDiConsegna,
					getTotalPrice(), articoliCopia);
			
			this.purchase.add(acquisto);
			
			this.getShoppingCart().removeAllFromCart();

			PurchaseDAO.addInDatabase(acquisto);
			
    	}
    	
    }
    
    /**
     * Returns the total price of the items in the shopping cart
     * @return the total price of the items in the shopping cart
     */
    public float getTotalPrice(){
    	return this.shoppingCart.getTotalPrice();
    }
    
    /**
     * Adds the user in the database
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     * @see UserDAO#addInDatabase(User)
     */
    public void addInDatabase() throws ClassNotFoundException {
    	UserDAO.addInDatabase(this);
    }
    
    /**
     * Updates the user in the database
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     * @see UserDAO#updateInDatabase(User)
     */
    public void updateInDatabase() throws ClassNotFoundException {
    	UserDAO.updateInDatabase(this);
    }
    
    /**
     * Removing the user from database
     * @throws ClassNotFoundException if an error occurs with the connection to the database
     * @see UserDAO#deleteFromDatabase(String)
     */
    public void deleteFromDatabase() throws ClassNotFoundException {
    	UserDAO.deleteFromDatabase(this.mail);
    }
    
}//End User class