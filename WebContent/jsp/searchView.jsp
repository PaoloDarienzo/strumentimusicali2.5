<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" 
  import="java.util.List, java.util.ArrayList, java.util.Iterator"
  import="model.Product" %>
  
<!DOCTYPE html>

<html>

<link href="CSS/Styles.css" rel="stylesheet" type="text/css">

	<head>
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
		
		<br/>
			
		HERE THERE WILL BE THE RESULTS OF THE QUERY. <br/>
		
		<% List<Product> queryResults = (ArrayList<Product>) request.getAttribute("queryResults");
		
		for(Product product : queryResults) { %>
		
		<table>
		<tr> 
			<th> <% out.println(product.getNome()); %> </th> 
		</tr>
		
		<tr>
			<td> Descrizione </td>
			<td> <% out.println(product.getDescrizione()); %> </td>
		</tr>
		
		<tr>
			<td> Peso </td>
			<td> <%  out.println(product.getPeso()); %> </td>
		</tr>
		
		<tr>
			<td> Marca </td>
			<td> <%  out.println(product.getMarca()); %> </td>
		</tr>
		
		<tr>
			<td> Classificazione </td>
			<td> <%  out.println(product.getClassificazione()); %> </td>
		</tr>
		
		<tr>
			<td> Stato </td>
			<% Boolean stato = product.getUsato(); %>
			<% if(stato == true) { %>
			<td> Usato </td>
			<% } else if(stato == false) { %>
			<td> Nuovo </td>
			<% } %>
		</tr>
		
		<tr>
			<td> Data di inserimento </td>
			<td> <%  out.println(product.getDataInserimento()); %> </td>
		</tr>
		
		<tr>
			<td> Prezzo </td>
			<td> <%  out.println(product.getPrezzo()); %> € </td>
		</tr>
		
		</table>
		
		<br/>
		
		<% } %>
						
		<jsp:include page="_footer.jsp"></jsp:include>
	
	</body>
	
</html>