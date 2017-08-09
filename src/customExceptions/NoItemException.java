package customExceptions;

/**
 * Extends <i>RuntimeException</i>
 * and it's called when there are no items in the cart.
 * @author Paolo D'Arienzo
 * @version 1.4
 *
 */
public class NoItemException extends RuntimeException{
	
	//TODO
	//and update version
	//private static final long serialVersionUID = -7341795101873587936L;
	
	/**
     * Constructor of NoItemException
     * @param message passed to the superclass
	 */
	public NoItemException(String message){
		super(message);
	}

}