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
	
		%>

		<form action="${pageContext.request.contextPath}/modifyDeliveryPoint" method="POST">
			<fieldset>
				<legend>Add delivery point:</legend>
				
				<label><b>Città:</b></label>
				<input type="text" placeholder="Inserisci città" name="city" required>
				
				<label><b>Via:</b></label>
				<input type="text" placeholder="Inserisci via" name="street" required>
				
				<label><b>Civico:</b></label>
				<input type="text" placeholder="Inserisci numero civico" name="civicNmbr" required>
				
				<label><b>CAP:</b></label>
				<input type="text" placeholder="Inserisci il CAP" name="CAP" required>
				
				<button type="submit">Add delivery point</button>
				<a href="${pageContext.request.contextPath}/userProfile">Cancel</a>
			</fieldset>
		</form>

	</body>
	
</html>