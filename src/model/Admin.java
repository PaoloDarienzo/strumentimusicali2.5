package model;

import java.security.NoSuchAlgorithmException;

/**
 * Admin class represent an user who is the admin of the website.
 * Admin extends User class and automatically set the type as <i>ADMIN</i>.
 * @author Paolo D'Arienzo
 * @version 1.4
 *
 */
public class Admin extends User{

    /**
     *  Constructor of Admin; ignores the param <i>TipoCliente</i> passed because 
     *  it's always <i>ADMIN</i>.
     * @param mail is the e-mail of the user
     * @param nomeUtente is the nickname of the user
     * @param password stores the encrypted password of the user
     * @param nome is the name of the user
     * @param cognome is the surname of the user
     * @param numeroTelefono is the telephone number of the user
     * @param cittaDiResidenza is the city in which resides the user
     * @param CF is the fiscal code of the user
     * @param tipoCliente indicates the type of user; it's always <i>ADMIN</i>
     * @param numeroCellulare is the private telephone number of the user
     * @throws NoSuchAlgorithmException if an error occurs with the encryption of the password, 
     * i.e. the algorithm used for the encryption can't be found
     */
    public Admin(String mail, String nomeUtente, String password, String nome, String cognome, 
    		String numeroTelefono, String cittaDiResidenza, String CF, 
    		TipoCliente tipoCliente, String numeroCellulare) throws NoSuchAlgorithmException{

        super(mail, nomeUtente, password, nome, cognome, numeroTelefono, 
        		cittaDiResidenza, CF, TipoCliente.ADMIN, numeroCellulare);		

    }
	
    /**
     * Constructor of Admin; called when <i>numeroCellulare</i> isn't specified.
     * @param mail is the e-mail of the user
     * @param nomeUtente is the nickname of the user
     * @param password stores the encrypted password of the user
     * @param nome is the name of the user
     * @param cognome is the surname of the user
     * @param numeroTelefono is the telephone number of the user
     * @param cittaDiResidenza is the city in which resides the user
     * @param CF is the fiscal code of the user
     * @param tipoCliente indicates the type of user; it's always <i>ADMIN</i>
     * @throws NoSuchAlgorithmException if an error occurs with the encryption of the password, 
     * i.e. the algorithm used for the encryption can't be found
     */
    public Admin(String mail, String nomeUtente, String password, String nome, String cognome, 
    		String numeroTelefono, String cittaDiResidenza, 
    		String CF, TipoCliente tipoCliente) throws NoSuchAlgorithmException {

        super(mail, nomeUtente, password, nome, cognome, numeroTelefono, cittaDiResidenza, CF, TipoCliente.ADMIN);

    }
	
    /**
     * Constructor of Admin; called when neither <i>TipoCliente</i> nor <i>numeroCellulare</i> are specified.
     * @param mail is the e-mail of the user
     * @param nomeUtente is the nickname of the user
     * @param password stores the encrypted password of the user
     * @param nome is the name of the user
     * @param cognome is the surname of the user
     * @param numeroTelefono is the telephone number of the user
     * @param cittaDiResidenza is the city in which resides the user
     * @param CF is the fiscal code of the user
     * @throws NoSuchAlgorithmException if an error occurs with the encryption of the password, 
     * i.e. the algorithm used for the encryption can't be found
     */
    public Admin(String mail, String nomeUtente, String password, String nome, String cognome, String numeroTelefono,
            String cittaDiResidenza, String CF) throws NoSuchAlgorithmException {

        super(mail, nomeUtente, password, nome, cognome, numeroTelefono, cittaDiResidenza, CF, TipoCliente.ADMIN);

    }
	
}