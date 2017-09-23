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

		<form action="${pageContext.request.contextPath}/modifyPayment" method="POST">
			<fieldset>
				<legend>Add payment method:</legend>
				<label><b>Type of payment:</b></label>
				<input type="text" placeholder="Enter type of payment" name="pmtType" required>
				
				<label><b>Credentials:</b></label>
				<input type="text" placeholder="Enter credentials" name="credentials" required>
				<button type="submit">Add payment method</button>
				<a href="${pageContext.request.contextPath}/userProfile">Cancel</a>
			</fieldset>
		</form>

	</body>
	
</html>