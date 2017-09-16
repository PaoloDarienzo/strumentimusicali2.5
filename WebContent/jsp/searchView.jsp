<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" 
  import="java.util.List, java.util.ArrayList, java.util.Iterator"
  import="model.Product" %>
  
<!DOCTYPE html>

<html>

	<head>
		<link href="CSS/Styles.css" rel="stylesheet" type="text/css">
		<meta charset="UTF-8">
		
		<meta name="description" content="Negozio di strumenti musicali">
		<meta name="keywords" content="strumenti, musicali, negozio">
		<meta name="author" content="Paolo D'Arienzo">
			
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<link rel="icon" href="images/favicon-32x32.png" />
		
		<title>zumzum.it</title>
		
	</head>
	
	<body
	 style = "background-color: #cce6ff">
	
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		
		<h3>Results</h3>
		
		<br/> <br/>
		
		<% List<Product> queryResults = (ArrayList<Product>) request.getAttribute("queryResults");
		
		for(Product product : queryResults) { %>
		
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
			<td> <% out.println(product.getPrezzo()); %> € </td>
		</tr>
		
		</table>
		
		<% } %>
		
		<jsp:include page="_footer.jsp"></jsp:include>
	
	</body>
	
</html>