<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="java.util.List, java.util.Iterator, model.*, java.util.ArrayList" %>
  
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
		
	<%
	
	User currentUser = (User) session.getAttribute("currentSessionUser");
	Boolean isDlvPointOk = null;
	Boolean isPaymtOk = null;
	
	if(currentUser.getDeliveryPoint().isEmpty()){
		isDlvPointOk = false;
	}
	else
		isDlvPointOk = true;
	
	if (currentUser.getPayment().isEmpty()){
		isPaymtOk= false;
	}
	else
		isPaymtOk = true;
	
	%>
	
	
	<% if (isPaymtOk && isDlvPointOk){ %>
	
		<h3 style="text-align: center;">Check out</h3>
		
		<table class= "qrtable" style="align: center;">
		
			<tr><th colspan="5">Prodotti in fase di acquisto</th></tr>
			<tr>
				<th>Nome</th>
				<th>Prezzo unitario</th>
				<th>Numero articoli</th>
				<th>Prezzo totale</th>
			</tr>
			<% List<ProductInCart> cartProducts = currentUser.getShoppingCart().getArticoliInCarrello();
				java.util.Formatter totalPrice = new java.util.Formatter();
				for(ProductInCart productInCart : cartProducts) { 
					java.util.Formatter unitaryPrice = new java.util.Formatter();
					java.util.Formatter productsPrice = new java.util.Formatter();
				%>
				<tr>
					<td><% out.println(productInCart.getProduct().getNome()); %></td>
					<td><%=unitaryPrice.format("%.2f", productInCart.getProduct().getPrezzo())%> €</td>
					<td><% out.println(productInCart.getNumeroProdotto()); %></td>
					<td><%=productsPrice.format("%.2f", (productInCart.getProduct().getPrezzo()) * (productInCart.getNumeroProdotto()))%> €</td>
				</tr>				
				
				<%
					unitaryPrice.close();
					productsPrice.close();
				}%>
				
				<tr>
					<th colspan="2">Riassunto</th>
					<td>Totale articoli: <%=currentUser.getShoppingCart().getNumberOfItems()%></td>
					<td>Prezzo totale: <%=totalPrice.format("%.2f", currentUser.getShoppingCart().getTotalPrice())%> €</td>
				</tr>
				<% totalPrice.close(); %>
		</table>
		
		<form action="${pageContext.request.contextPath}/confirmedPurchase" method="POST">
	    	<fieldset>
	        	<legend><b>Choose delivery method:</b></legend>
		        CORRIERE <input type="radio" name="dlvMethod" value="CORRIERE"/>
		        RAPIDO  <input type="radio" name="dlvMethod" value="RAPIDO"/>
		        IN GIORNATA <input type="radio" name="dlvMethod" value="INGIORNATA"/>
	    	</fieldset>
	    	
	    	<br/>
	    	
	    	<fieldset>
				<legend><b>Choose delivery point:</b></legend>
				<select name="dlvPoint">
					<% for(DeliveryPoint delPoint : currentUser.getDeliveryPoint()){ %>
			   			<option value="<%=currentUser.getDeliveryPoint().indexOf(delPoint)%>"><%=delPoint.toString()%></option>
			   		<% } %>
			  	</select>
			 </fieldset>
			 
			 <br/>
			 
			 <fieldset>
				<legend><b>Choose payment method:</b></legend>
				<select name="paymt">
					<% for(Payment pagamento : currentUser.getPayment()){ %>
			   			<option value="<%=currentUser.getPayment().indexOf(pagamento)%>"><%=pagamento.toString()%></option>
			   		<% } %>
			  	</select>
			 </fieldset>
			 
			<button type="submit"> CHECK OUT! </button>
			 
		</form>
		
	<% } else { 
		if(!isDlvPointOk){ %>
			
			<b>You need at least 1 delivery point set.</b>
			<a href="${pageContext.request.contextPath}/userProfile"><i>Go to update your profile.</i></a>
			<br/>
			
		<% }
		if (!isPaymtOk) { %>
		
			<b>You need at least 1 payment method set.</b>
			<a href="${pageContext.request.contextPath}/userProfile"><i>Go to update your profile.</i></a>
			<br/>
			
		<% } 
		if(currentUser.getShoppingCart().getNumberOfItems() == 0){ %>
		
			<b>Your cart is empty.</b>
			<a href="${pageContext.request.contextPath}/home"><i>Visit our shop!</i></a>
			<br/>
		
		<% }
	 } %>
	
	</body>
	
</html>