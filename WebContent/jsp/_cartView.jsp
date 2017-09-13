<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" 
  import="java.util.List, java.util.ArrayList, java.util.Iterator"
  import="model.Product"
  import="model.ShoppingCart" %>
  


		
		<h3>Carrello</h3>
		
		<br/> <br/>
		
		<% if(session.getAttribute("currentSessionUser") != null)
		   User currentUser = (User) session.getAttribute("currentSessionUser"); 
		   ShoppingCart userCart = currentUser.getShoppingCart();
		
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
			<td> <%  out.println(product.getPeso()); %> Kg </td>
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
			<% 
			Boolean stato = product.getUsato();
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
			<td> <%  out.println(product.getDataInserimento()); %> </td>
		</tr>
		
		<tr>
			<td> Prezzo </td>
			<td> <%  out.println(product.getPrezzo()); %> € </td>
		</tr>
		
		</table>
		
		<% } %>
		
		<jsp:include page="_footer.jsp"></jsp:include>
	
	</body>
	
</html>