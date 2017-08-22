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
	//update version
	private static final long serialVersionUID = 231911268268396171L;

	/**
     * Constructor of NoItemException
     * @param message passed to the superclass
	 */
	public NoItemException(String message){
		super(message);
	}

}