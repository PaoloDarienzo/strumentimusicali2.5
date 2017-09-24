package tests;

import static org.junit.Assert.assertEquals;

import java.net.UnknownHostException;
import java.sql.Date;
import java.util.List;

import org.junit.Test;

import model.LivelloStrumento;
import model.Product;
import view.Main;

/**
 * This class contains tests on user.
 * @author Paolo D'Arienzo
 * @version 1.5
 */
public class TestProduct {

	/**
	 * 
	 * Tests the creation of <i>Product</i> and the <i>get</i> methods.
	 * @throws UnknownHostException if an error occurs with the determination of IP address
	 * @throws ClassNotFoundException if an error occurs with the connection to the database
	 */
	@Test
	public void testCreation() throws UnknownHostException, ClassNotFoundException {		
		
		int i = Main.createProductID() + 500;
		
		List<Product> products = InitializationForTests.productInit();

		/* Testing:
		 * - Product creation
		 * - some of the get and set methods
		 * - Field of the products that refers to a Enum type
		 */
		assertEquals(products.get(0).getNome(), "EKO S300 Sunburst");
		assertEquals(products.get(0).getID(), i + 1);
		assertEquals(products.get(1).getID(), i + 2);
		assertEquals(products.get(0).getDataInserimento(), new Date(2016-1900,11,02));
		
		assertEquals(products.get(0).getLivelloConsigliato(), LivelloStrumento.ND);
		assertEquals(products.get(2).getLivelloConsigliato(), LivelloStrumento.PRINCIPIANTE);
		
	}

}
