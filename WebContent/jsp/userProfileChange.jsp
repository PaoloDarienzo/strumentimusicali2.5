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
	<h3 style="text-align: center;">Modifica profilo personale</h3>

	<%User currentUser = (User) session.getAttribute("currentSessionUser");%>
	
	<form action="${pageContext.request.contextPath}/userProfileChange" method="POST">
			<fieldset>
				<legend>Change cellphone number</legend>
				<label><b>New cellphone number:</b></label>
				<input type="text" placeholder="Enter cellphone number" name="cellPhone" required>
				<button type="submit">Add cellphone number</button>
				<a href="${pageContext.request.contextPath}/userProfile">Cancel</a>
			</fieldset>
		</form>
	
	</body>
	
</html>