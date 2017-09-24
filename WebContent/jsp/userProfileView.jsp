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
	<h3 style="text-align: center;">Profilo personale</h3>
	<h3>Dati utente:</h3>
	<%User currentUser = (User) session.getAttribute("currentSessionUser");%>
	Nome utente: <%=currentUser.getNomeUtente() %><br>
	Nome: <%=currentUser.getNome() %><br>
	Cognome: <%=currentUser.getCognome() %><br>
	Mail: <%=currentUser.getMail() %><br>
	Telefono: <%=currentUser.getNumeroTelefono() %><br>
	Residenza: <%=currentUser.getCittaDiResidenza() %><br>
	Codice Fiscale: <%=currentUser.getCF() %><br>
	<br><br><br>


	<% %>
	
	<!-- Zona pagamento -->
	
	<div>
	
		<table class="qrtable">
			<tr><th colspan="3">Metodi di pagamento presenti:</th></tr>
			<%
			if(currentUser.getPayment().isEmpty()){%>
				<tr><td>Nessun pagamento ancora presente.</td></tr>
			<%} else {
			for(Payment pagamentoPresente : currentUser.getPayment()){ %>
				<tr>
					<td><%=pagamentoPresente.getNomeMetodo()%></td>
					<td><%=pagamentoPresente.getCredenzialiCensurate()%></td>
					<td>
					<form action="${pageContext.request.contextPath}/modifyPayment" method="POST">
					<% String strToPass = pagamentoPresente.getUserMailFromPayment() + pagamentoPresente.getNomeMetodo() + pagamentoPresente.getCredenziali(); %>
					<input type="hidden" name="paymentHypothetical" value="<%=strToPass%>"/>
						<button class="removeBtn" name="remove" type="submit" value="removeBtn">x</button>
					</form>
					</td>
				</tr>
				<% } %>
			<% } %>
			<tr><td colspan="3"><input type="button" onclick="location.href='modifyPayment';" value="Add payment"/></td></tr>
		</table>
	
	</div>
	
	<br/>
	
	<!-- Zona delivery point -->
	
	<div>
	
		<table class="qrtable">
			<tr><th colspan="2">Punti di consegna presenti:</th></tr>
			<%
			if(currentUser.getDeliveryPoint().isEmpty()){%>
				<tr><td>Nessun punto di consegna ancora presente.</td></tr>
			<%} else {
				for(DeliveryPoint dlvPointPresente : currentUser.getDeliveryPoint()){ %>
				<tr>
					<td><%=dlvPointPresente.toString()%></td>
					<td>
					<form action="${pageContext.request.contextPath}/modifyDeliveryPoint" method="POST">
					<% String strToPass = dlvPointPresente.getUserMailFromDelPoint() + dlvPointPresente.toString(); %>
					<input type="hidden" name="dlvPntHypothetical" value="<%=strToPass%>"/>
						<button class="removeBtn" name="remove" type="submit" value="removeBtn">x</button>
					</form>
					</td>
				</tr>
				<% } %>
			<% } %>
			<tr><td colspan="2"><input type="button" onclick="location.href='modifyDeliveryPoint';" value="Add delivery point"/></td></tr>
		</table>
	
	</div>
	
	<br/>
	
	<!-- Zona storico acquisti -->
	
	<div>
		
	</div>
	
	</body>
	
</html>