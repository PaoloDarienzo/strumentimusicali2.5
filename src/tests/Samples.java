package tests;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;

import model.Brand;
import model.DeliveryPoint;
import model.LivelloStrumento;
import model.MetodoDiConsegna;
import model.Payment;
import model.Product;
import model.ProductInCart;
import model.TipoCliente;
import model.TipoStrumento;
import model.User;
import view.Main;

public class Samples {
	
	@SuppressWarnings("deprecation")
	private void sample() throws ClassNotFoundException, NoSuchAlgorithmException, UnknownHostException {
		
		//Setting productID based on IDs already present in the database (in table strumento)
				for(int i = 0; i < Main.maxIDInDatabase("strumento"); i++){
					Main.createProductID();
				}
				//Setting productID based on IDs already present in the database (in table acquisto)
				for(int i = 0; i < Main.maxIDInDatabase("ordine"); i++){
					Main.createPurchaseID();
				}
				
				//Creazione di utenti
				
				User admin1 = new User("lorenzorik@gmail.com", "lorenzoricci", "Sandalo94",
										"Lorenzo", "Ricci", "3409320876", "San Giovanni Lupatoto",
										"LRNRCC9325", TipoCliente.ADMIN, "93980908890");
				
				User admin2 = new User("Pleasant94@gmail.com", "paolodarienzo", "Sandalo94",
										"Paolo", "D'Arienzo", "3460996201", "Povegliano Veronese",
										"DRNPLA9426NG", TipoCliente.ADMIN);
				
				User PC1 = new User("paolo.darienzo@studenti.univr.it", "paolodarienzo2", "Criptami",
											"Paolo", "D'Arienzo", "1234567890", "Verona", "DRNAPAMDWNO12F", TipoCliente.PROFESSIONISTA);
				
				User HC1 = new User("lorenzo.ricci@studenti.univr.it", "lorenzoricci2", 
						"CriptamiTutto", "Lorenzo", "Ricci", "1234567890", "Lugagnano", "ASDQO8N310L", TipoCliente.TITOLARE);
				
				User occasionale1 = new User("mario.rossi@gmail.com", "rossirossi", "albero",
						"Mario", "Rossi", "0987654321", "Roma", "RSSMAR102FLAS");

				//Aggiunta di utenti al database
				admin1.addInDatabase();
				admin2.addInDatabase();
				PC1.addInDatabase();
				HC1.addInDatabase();
				occasionale1.addInDatabase();

				//Creazione di alcuni prodotti
				InetAddress AdminIP = InetAddress.getLocalHost();
				
				Product EKO_S300_SUNBURST = new Product(Main.createProductID(), "EKO S300 Sunburst", 27, 
						"La serie Starter nasce per offrire la possibilita' a tutti i chitarristi, "
						+ "in particolar modo i principianti, di avere uno strumento con caratteristiche simili a "
						+ "modelli di chitarra che hanno fatto la storia della musica.", 
						(float) 5.00, (float) 89.00, TipoStrumento.PIZZICO, Brand.EKO,
						new Date(2016-1900,11,02), AdminIP, "c", 0, 0, null, false);
				
				Product ltd_alexi = new Product(Main.createProductID(), "ltd alexi", 2, 
						"Chitarra Elettrica Solid Body Signature Alexi Laiho. \n" + "Pick-Up: ESP Designed LH-301B.", 
						(float) 6.66, (float) 649.00, TipoStrumento.PIZZICO, Brand.ESP,
						new Date(2017-1900,06,06), AdminIP, "p",
						10, 0, null, true);
				
				Product CASIO_SA46 = new Product(Main.createProductID(), "Casio SA46", 150, 
						"Piu' che un giocattolo: "
						+ "d'altronde i piu' piccoli devono sviluppare sui 32 tasti la "
						+ "passione per la musica fin dall'inizio.", (float) 1.2, (float) 45.00, 
						TipoStrumento.TASTIERA, Brand.CASIO, new Date(2016-1900,06,06), AdminIP, "s",
						15, 8, LivelloStrumento.PRINCIPIANTE, false);
				
				//Aggiunta di prodotti al database
				EKO_S300_SUNBURST.addInDatabase();
				ltd_alexi.addInDatabase();
				CASIO_SA46.addInDatabase();
				
				
				//Questo pezzo di codice crea 2 delivery points, li aggiunge ad un utente
				//Stampa il primo, rimuove il primo, stampa il primo;
				//si puo' notare che viene rimosso dalla lista correttamente
				DeliveryPoint dp1 = new DeliveryPoint(admin1.getMail(), "Verona", "Via a", "Civ2", "37064");
				DeliveryPoint dp2 = new DeliveryPoint(admin1.getMail(), "Verona", "Via b", "Civ2", "80123");
				admin1.addDeliveryPoint(dp1);
				admin1.addDeliveryPoint(dp2);
				admin2.addDeliveryPoint(new DeliveryPoint(admin1.getMail(), "Citta", "Via", "Civico", "CAP"));
				System.out.println(admin1.getDeliveryPoint().get(0).getVia());
				System.out.println();
				admin1.removeDeliveryPoint(dp1); //funziona anche con dp2, obv
				System.out.println(admin1.getDeliveryPoint().get(0).getVia());
				System.out.println();
				
				System.out.println();
				
				List<ProductInCart> articoliUtente1 = admin1.getShoppingCart().getArticoliInCarrello();
				
				admin1.getShoppingCart().addToCart(ltd_alexi);
				
				System.out.println("Lista di: " + admin1.getNomeUtente());
		        articoliUtente1.forEach((prodottoInCarrello) -> {
		        	System.out.println(prodottoInCarrello.getProduct().getNome() + " " + prodottoInCarrello.getNumeroProdotto());
		        });
		        System.out.println();
				
		        admin1.getShoppingCart().updateCart(ltd_alexi, 3);
				
				System.out.println("Lista di: " + admin1.getNomeUtente());
				articoliUtente1.forEach((prodottoInCarrello) -> {
					System.out.println(prodottoInCarrello.getProduct().getNome() + " " + prodottoInCarrello.getNumeroProdotto());
				});
				System.out.println();
				
				
				admin2.getShoppingCart().addToCart(EKO_S300_SUNBURST);
				admin2.getShoppingCart().addToCart(CASIO_SA46, 2);
				admin2.getShoppingCart().updateCart(CASIO_SA46, 5);
				
				admin1.getShoppingCart().addToCart(CASIO_SA46);
				admin1.getShoppingCart().updateCart(CASIO_SA46, 25);
				
				admin1.getShoppingCart().incrementInCart(ltd_alexi);
				
				System.out.println("Lista di: " + admin1.getNomeUtente());
				articoliUtente1.forEach((prodottoInCarrello) -> {
					System.out.println(prodottoInCarrello.getProduct().getNome() + " " + prodottoInCarrello.getNumeroProdotto());
				});
				System.out.println();
			   
				admin1.getShoppingCart().decrementInCart(ltd_alexi);
			    
				System.out.println("Lista di: " + admin1.getNomeUtente());
				articoliUtente1.forEach((prodottoInCarrello) -> {
					System.out.println(prodottoInCarrello.getProduct().getNome() + " " + prodottoInCarrello.getNumeroProdotto());
				});
				System.out.println();
			    
				admin1.getShoppingCart().removeFromCart(CASIO_SA46);
			    
				System.out.println("Lista di: " + admin1.getNomeUtente());
				articoliUtente1.forEach((prodottoInCarrello) -> {
					System.out.println(prodottoInCarrello.getProduct().getNome() + " " + prodottoInCarrello.getNumeroProdotto());
				});
				System.out.println();
			    
				admin1.getShoppingCart().decrementInCart(ltd_alexi);
				System.out.println("Lista di: " + admin1.getNomeUtente());
				articoliUtente1.forEach((prodottoInCarrello) -> {
					System.out.println(prodottoInCarrello.getProduct().getNome() + " " + prodottoInCarrello.getNumeroProdotto());
				});
				System.out.println();
				
				admin1.getShoppingCart().decrementInCart(ltd_alexi);
				System.out.println("Lista di: " + admin1.getNomeUtente());
				articoliUtente1.forEach((prodottoInCarrello) -> {
					System.out.println(prodottoInCarrello.getProduct().getNome() + " " + prodottoInCarrello.getNumeroProdotto());
				});
				System.out.println();
				
				admin1.getShoppingCart().decrementInCart(ltd_alexi);
				System.out.println("Lista di: " + admin1.getNomeUtente());
				articoliUtente1.forEach((prodottoInCarrello) -> {
					System.out.println(prodottoInCarrello.getProduct().getNome() + " " + prodottoInCarrello.getNumeroProdotto());
				});
				System.out.println();
				
				admin1.getShoppingCart().addToCart(ltd_alexi, 7);
				admin1.getShoppingCart().addToCart(CASIO_SA46);
				
				admin1.addPayment(new Payment(admin1.getMail(), "payPal", "123-123-123"));
				admin1.confirmPurchase(admin1.getPayment().get(0), MetodoDiConsegna.INGIORNATA, null);
				
				Payment pagamento = new Payment(admin1.getMail(), "VISA", "321-321-321");
				admin1.addPayment(pagamento);
				//admin1.confirmPurchase(admin1.getPayment().get(1));
				
				System.out.println("Parto da qui...");
				
				System.out.println(admin1.getPurchase().get(0).getIDPurchase() + " " + admin1.getPayment().get(0).getNomeMetodo());
				//System.out.println(admin1.getPurchase().get(1).getIDPurchase() + admin1.getPayment().get(1).getNomeMetodo());
				
				System.out.println(admin1.getPurchase().get(0).getListOfProductsPurchased().get(0).getProduct().getNome());		
				
				//admin1.getShoppingCart().removeAllFromCart();
				admin1.getShoppingCart().removeFromCart(ltd_alexi);
				
				System.out.println(admin1.getPurchase().get(0).getListOfProductsPurchased().get(0).getProduct().getNome());
				
				System.out.println("PROVA");

				System.out.println("Lista di: " + admin1.getNomeUtente());
				articoliUtente1.forEach((prodottoInCarrello) -> {
					System.out.println(prodottoInCarrello.getProduct().getNome() + " " + prodottoInCarrello.getNumeroProdotto());
				});
				System.out.println();
				
				System.out.println(admin1.getPurchase().get(0).getListOfProductsPurchased().get(0).getProduct().getNome());
				
				System.out.println();System.out.println();System.out.println();
				
				occasionale1.setNumeroCellulare("+39123123091");
				
				occasionale1.updateInDatabase();
				
				admin1.removePayment(pagamento);
				
				admin1.addPayment(new Payment(admin1.getMail(), "CDC", "ASD-DASD1-123"));
				
				admin1.setPayment();
				
				admin2.addPayment(new Payment(admin2.getMail(), "BONIFICO", "D-DA123-123"));
				
				System.out.println("Lista di pagamenti di: " + admin1.getNomeUtente());
				admin1.getPayment().forEach((Payment) -> {
					System.out.println(Payment.getNomeMetodo() + ", " + Payment.getCredenziali());
				});
				System.out.println();
				
				System.out.println("Lista di: " + admin1.getNomeUtente());
				articoliUtente1.forEach((prodottoInCarrello) -> {
					System.out.println(prodottoInCarrello.getProduct().getNome() + " " + prodottoInCarrello.getNumeroProdotto());
				});
				System.out.println();
				
				admin1.setShoppingCart();
				
				System.out.println("Lista di: " + admin1.getNomeUtente());
				articoliUtente1.forEach((prodottoInCarrello) -> {
					System.out.println(prodottoInCarrello.getProduct().getNome() + " " + prodottoInCarrello.getNumeroProdotto());
				});
				System.out.println();System.out.println();System.out.println();System.out.println();
				

				List<ProductInCart> articoliUtente2 = admin2.getShoppingCart().getArticoliInCarrello();
				
				articoliUtente2.forEach((prodottoInCarrello) -> {
					System.out.println(prodottoInCarrello.getProduct().getNome() + " " + prodottoInCarrello.getNumeroProdotto());
				});
				System.out.println();System.out.println();System.out.println();System.out.println();
				admin2.setShoppingCart();
				articoliUtente2 = admin2.getShoppingCart().getArticoliInCarrello();
				articoliUtente2.forEach((prodottoInCarrello) -> {
					System.out.println(prodottoInCarrello.getProduct().getNome() + " " + prodottoInCarrello.getNumeroProdotto());
				});
		
	}

}
