<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" 
  import="java.util.List, java.util.ArrayList, java.util.Iterator"
  import="model.Product, model.ProductInCart, model.User" %>
  
<!DOCTYPE html>

<html>

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="CSS/Styles.css" rel="stylesheet" type="text/css">
		<meta charset="UTF-8">
		
		<meta name="description" content="Negozio di strumenti musicali">
		<meta name="keywords" content="strumenti, musicali, negozio">
		<meta name="author" content="Paolo D'Arienzo">
		
		<link rel="icon" href="images/favicon-32x32.png" />
		
		<title>zumzum.it</title>
		
	</head>
	
	<body>
	
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		
		<h3>Prodotti nel carrello</h3>

				<% User currentUser = (User) session.getAttribute("currentSessionUser");
				if (currentUser != null){ %>

			<table class= "qrtable">
				<tr><th colspan = "4">Articoli totali:</th></tr>
				<tr><th colspan = "3">Nome</th><th colspan = "1">Prezzo</th></tr>
	
			<% List<ProductInCart> cartProducts = currentUser.getShoppingCart().getArticoliInCarrello();
		
			for(ProductInCart product : cartProducts) { %>
			
				<tr>
					<td colspan="3"><% out.println(product.getProduct().getNome()); %></td>
					<td colspan="1"><% out.println(product.getProduct().getPrezzo()); %></td>
				</tr>
			
				<%}%>		
			<tr>
				<th colspan="3">Articoli totali:</th>
				<td colspan="1"><%= currentUser.getShoppingCart().getArticoliInCarrello().size() %></td>
			</tr>
			
			<tr>
				<th colspan="3">Totale:</th>
				<td colspan="1"><%= currentUser.getShoppingCart().getTotalPrice() %></td>
			</tr>

		</table>
		<br/> <br/>
						<% }
				else{
					
				} %>

		
		<jsp:include page="_footer.jsp"></jsp:include>
	
	</body>
	
</html>