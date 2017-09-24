<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" 
  import="java.util.List, java.util.ArrayList, java.util.Iterator"
  import="model.Product, model.User" %>
  
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
		
		<h3>Results</h3>
		
		<% List<Product> queryResults = (ArrayList<Product>) request.getAttribute("queryResults");
		
		for(Product product : queryResults) { 
		
			java.util.Formatter formatter = new java.util.Formatter(); %>
		
			<table class = "qrtable">
				<tr> 
					<th colspan="2"> <% out.println(product.getNome()); %> </th> 
				</tr>
				
				<tr>
					<td> Descrizione </td>
					<td> <% out.println(product.getDescrizione()); %> </td>
				</tr>
				
				<tr>
					<td> Peso </td>
					<td> <% out.println(product.getPeso()); %> Kg </td>
				</tr>
				
				<tr>
					<td> Marca </td>
					<td> <% out.println(product.getMarca()); %> </td>
				</tr>
				
				<tr>
					<td> Classificazione </td>
					<td> <% out.println(product.getClassificazione()); %> </td>
				</tr>
				
				<tr>
					<td> Stato </td>
					<% 
					Boolean stato = product.isUsato();
					String printStato = "N/A";
					if(stato == true) {
					printStato = "Usato";
					} else if(stato == false) {
						printStato = "Nuovo";
					} %>
					<td> <% out.println(printStato); %> </td>
				</tr>
				
				<tr>
					<td> Data di inserimento </td>
					<td> Disponibile da: <% out.println(product.getDataInserimento()); %> </td>
				</tr>
				
				<tr>
					<td> Prezzo </td>
					<td> <%=formatter.format("%.2f", product.getPrezzo())%> €</td>
				</tr>
								
				
				<%	String livello = product.getLivelloConsigliato().toString(); 
				if(livello != "ND"){ %>
				
				<tr>
					<td> Livello consigliato </td>
					<td> <% out.println(product.getLivelloConsigliato()); %> </td>
				</tr><% } %>

				<% if(product.getSconto() != 0){%>
				<tr>
					<td> Sconto praticabile </td>
					<% if(product.getNumeroPezziMinimo() == 0){ %>
						<td> <% out.println(product.getSconto()); %> % </td>
					<% } else{ %>
					<td> <% out.println(product.getSconto()); %> % -- <b>PEZZI MINIMI: </b> <% out.println(product.getNumeroPezziMinimo()); %> </td>
				</tr><% }} %>
				
				<% User currentUser = (User) session.getAttribute("currentSessionUser");
					if (currentUser != null){%>
						<tr>
							<td colspan="2">  
								<form action="${pageContext.request.contextPath}/cart" method="POST">
									<input type="hidden" name="productIDToAdd" value="<%=product.getID()%>" />
									<input type="submit" value="Aggiungi al Carrello"/>
								</form>
							</td>
						</tr>
					<%} else{%>
						<tr>
							<td colspan="2"><b><a href="${pageContext.request.contextPath}/register">Account required to buy.</a></b></td>
						</tr>
					<%} %>
								
			</table>
			
			<% 
				formatter.close();
			} %>
			
	</body>
	
</html>