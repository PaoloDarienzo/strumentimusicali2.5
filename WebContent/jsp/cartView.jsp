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
		
		<h3 style="text-align: center;">Carrello</h3>

		<%
		java.util.Formatter totalPrice = new java.util.Formatter();
		User currentUser = (User) session.getAttribute("currentSessionUser");
		if (currentUser != null){ %>

			<table class="qrtable">
			
				<tr><th colspan="6">Prodotti nel carrello</th></tr>
				<tr>
					<th>Nome</th>
					<th>Prezzo unitario</th>
					<th colspan="2">Numero articoli</th>
					<th>Sconto praticabile all'articolo</th>
					<th>Prezzo totale</th>
				</tr>
	
				<% List<ProductInCart> cartProducts = currentUser.getShoppingCart().getArticoliInCarrello();
		
				for(ProductInCart productInCart : cartProducts) { 
					java.util.Formatter unitaryPrice = new java.util.Formatter();
					java.util.Formatter productsPrice = new java.util.Formatter();
				%>
			
					<tr>
						<td><% out.println(productInCart.getProduct().getNome()); %></td>
						<td><%=unitaryPrice.format("%.2f", productInCart.getProduct().getPrezzo())%> €</td>
						<td><% out.println(productInCart.getNumeroProdotto()); %></td>
						<td>
							<form action="${pageContext.request.contextPath}/modifyCart" method="POST">
								<input type="hidden" name="productID" value="<%=productInCart.getProduct().getID()%>"/>
								<button class="plusBtn" name="increment" type="submit" value="plusBtn">+</button>
								<button class="minusBtn" name="decrement" type="submit" value="minusBtn">-</button>
								<button class="removeBtn" name="remove" type="submit" value="removeBtn">x</button>
							</form>
						</td>
						<td><% out.println(productInCart.getProduct().getSconto()); %></td>
						<td><%=productsPrice.format("%.2f", (productInCart.getPrezzo()))%> €</td>
					</tr>
			
				<%
					unitaryPrice.close();
					productsPrice.close();
				}%>		
				<tr><th colspan="6">RIEPILOGO</th></tr>
				<tr>
					<th colspan="3">Articoli totali: </th>
					<td colspan="3"><%=currentUser.getShoppingCart().getNumberOfItems()%></td>
				</tr>
				
				
			
				<tr>
					<th colspan="3">Totale: </th>
					<td colspan="2"><%=totalPrice.format("%.2f", currentUser.getShoppingCart().getTotalPrice())%> €</td>
					<td>
						<form action="${pageContext.request.contextPath}/purchase" method="GET">
							<button name="remove" type="submit" value="purchaseConfirm">Check out!</button>
						</form>
					</td>
				</tr>

			</table>
	
		<br/> <br/>
		<% }
		else{ %>
			<h6 style="text-align: center;">Utente non loggato.</h6>
		<% }
		totalPrice.close();
		%>

	</body>
	
</html>